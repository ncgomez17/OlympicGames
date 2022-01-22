import React, {useState, useEffect} from "react";
import { useSelector, useDispatch } from 'react-redux';
import { findCityList, searchCountriesOfCity, clearErrors} from '../../actions';
import * as fromState from '../../reducers';
import GenericTable from "../Tables/GenericTable";
import ModalError from "../Errors/ModalError"
import {FaEdit,FaTrashAlt} from 'react-icons/fa';
import ButtonAddCity from "./ButtonAddCity";
import ModalDeleteCity from "./ModalDeleteCity";
import ModalAddCity from "./ModalAddCity";
import EntityPagination from "../Pagination/EntityPagination";
import EntitySearch from "../SearchBar/EntitySearch";
const CitiesPage = () =>{
  
  const [tableCities] = useState([
    {
      name: "CITYID",
      property: (item) => item.id,
      
  },
  {
      name: "NAME",
      property: (item) => item.name,
  },
  {
      name: "COUNTRY",
      property: (item) => item.country.name,
  },
  {
      name: "VALUE",
      property: (item) => item.value,
  },
  {
    name:"EDIT",
    property: (item) => <span name="edit" onClick={()=>editCity(item)}><FaEdit color="blue"/></span>
},
{
    name:"DELETE",
    property: (item) => <span name="delete" onClick={()=>deleteCity(item)}><FaTrashAlt color="red"/></span>
}
  ]);
  const [showModalDelete, setShowModalDelete] = useState(false)
  const [showModalEdit, setShowModalEdit] = useState(false)
  const [city, setCity] = useState("")

  const dispatch = useDispatch();

  const cities = useSelector(state => fromState.getCities(state));
  const countries = useSelector(state => fromState.getCountriesCity(state));
  const pagination = useSelector(state => fromState.getCitiesPagination(state));
  const estoyCargando = useSelector(state => fromState.isCitiesPending(state));
  const error = useSelector(state => fromState.isCitiesRejected(state));

  const filters = ['id', 'name']

  const clearError = () =>{

    dispatch(clearErrors());

  };

  const searchCity = (filter,offset,limit,sortDirection,sortProperty) =>{

    dispatch(findCityList(filter.id, filter.name, offset, limit, sortDirection, sortProperty));
    
  };
  function editCity(city){
    dispatch(searchCountriesOfCity())
    setShowModalEdit(true);
    setCity(city);
}
  function deleteCity(city){
      setShowModalDelete(true);
      setCity(city);
  }
  function handleHideModal(){
      
    setShowModalEdit(false);
    setShowModalDelete(false)
  }

  useEffect(() =>{
    if(cities && cities.length ===0 || !Array.isArray(cities)){
      searchCity({id: null, name:null},0,4,'asc','id'); 
      dispatch(searchCountriesOfCity())
    }
  },[cities],[countries])

  return(
    <div>
      <h1>Cities</h1>
      <ButtonAddCity countries={countries}/>
      <div className="mb-4">
      <EntitySearch filters={filters} search={searchCity} offset={0}
        limit={2} sortOrder='asc' sortProperty='id'
      />
      </div>
      <div>
        {cities && cities.length >0
        ?
          <GenericTable headers={tableCities} data={cities} />
          :
          <div>
            {estoyCargando ? "Loading ...":"There are not Cities to show"}
          </div>}
      </div>
      <EntityPagination paged={pagination} search={searchCity}/>
      <ModalError isShowing={!!error} hideModal={clearError} error={error} title="Error in Cities"/>
      <ModalAddCity isShowing={showModalEdit} countries={countries} city={city} hideModal={()=>handleHideModal()}/>
      <ModalDeleteCity isShowing={showModalDelete} countries={countries} city={city} hideModal={() =>handleHideModal()}/>
    </div>
  )
}

export default CitiesPage