import React, {useState, useEffect} from "react";
import { useSelector, useDispatch } from 'react-redux';
import { clearErrors, findCountryList} from '../../actions';
import * as fromState from '../../reducers';
import GenericTable from "../Tables/GenericTable";
import ModalError from "../Errors/ModalError"
import {FaEdit,FaTrashAlt} from 'react-icons/fa';
import { FcViewDetails } from 'react-icons/fc'
import ButtonAddCountry from "./ButtonAddCountry";
import ModalDeleteCountry from "./ModalDeleteCountry";
import ModalAddCountry from "./ModalAddCountry";
import EntityPagination from "../Pagination/EntityPagination";
import EntitySearch from "../SearchBar/EntitySearch";
import { useHistory } from "react-router";
const CountriesPage = () =>{
  
  const [tableCountries] = useState([
    {
      name: "COUNTRYID",
      property: (item) => item.id,
      
  },
  {
      name: "NAME",
      property: (item) => item.name,
  },
  {
      name: "CODE",
      property: (item) => item.code,
  },
  {
      name: "VALUE",
      property: (item) => item.value,
  },
  {
    name:"EDIT",
    property: (item) => <span name="edit" onClick={()=>editCountry(item)}><FaEdit  size={24} color="blue"/></span>
},
{
    name:"DELETE",
    property: (item) => <span name="delete" onClick={()=>deleteCountry(item)}><FaTrashAlt size={24} color="red"/></span>
},
{
  name:"DETAILS",
  property: (item) => <span name="details" onClick={()=>handleShowDetails(item.id)}><FcViewDetails size={35}/></span>
}

  ]);
  const [showModalDelete, setShowModalDelete] = useState(false)
  const [showModalEdit, setShowModalEdit] = useState(false)
  const [country, setCountry] = useState("")

  const dispatch = useDispatch();

  const countries = useSelector(state => fromState.getCountries(state));
  const pagination = useSelector(state => fromState.getCountriesPagination(state));
  const estoyCargando = useSelector(state => fromState.isCountriesPending(state));
  const error = useSelector(state => fromState.isCountriesRejected(state));

  const filters = ['id', 'name', 'cityId']
  const history = useHistory();

  const clearError = () =>{

    dispatch(clearErrors());

  };

  const searchCountry = (filter,offset,limit,sortDirection,sortProperty) =>{

    dispatch(findCountryList(filter.id,filter.name,filter.cityId,
      offset,limit,sortDirection,sortProperty));
    
  };
  function editCountry(country){

    setShowModalEdit(true);
    setCountry(country);
}
  function deleteCountry(country){
      
      setShowModalDelete(true);
      setCountry(country);
  }
  function handleHideModal(){

    setShowModalEdit(false);
    setShowModalDelete(false)
  }

  function handleShowDetails(id){
    history.push(`/countries/${id}`)
  }

  useEffect(() =>{
    if(countries && countries.length ===0 || !Array.isArray(countries)){
      
      searchCountry({id: null, name:null, cityId:null},0,2,'asc','id'); 
    }
  },[countries])

  return(
    <div>
      <h1>Countries</h1>
      <ButtonAddCountry/>
      <div className="mb-4">
      <EntitySearch filters={filters} search={searchCountry} offset={0}
        limit={2} sortOrder='asc' sortProperty='id'
      />
      </div>
      <div>
        {countries && countries.length >0
        ?
          <GenericTable headers={tableCountries}  data={countries}  />
          :
          <div>
            {estoyCargando ? "Loading ...":"There are not Countries to show"}
          </div>}
      </div>
      <EntityPagination paged={pagination} search={searchCountry} />
      <ModalError isShowing={!!error} hideModal={clearError} error={error} title="Error in Countries"/>
      <ModalAddCountry isShowing={showModalEdit} country={country} hideModal={()=>handleHideModal()}/>
      <ModalDeleteCountry isShowing={showModalDelete} country={country} hideModal={() =>handleHideModal()}/>
    </div>
  )
}

export default CountriesPage