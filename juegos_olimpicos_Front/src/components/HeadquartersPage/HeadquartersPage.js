import React, {useState, useEffect} from "react";
import { useSelector, useDispatch } from 'react-redux';
import {searchCitiesOfHeadquarter, searchTypesOfHeadquarter, findHeadquarterList, clearErrors} from '../../actions';
import * as fromState from '../../reducers';
import GenericTable from "../Tables/GenericTable";
import ModalError from "../Errors/ModalError"
import {FaEdit,FaTrashAlt} from 'react-icons/fa';
import ButtonAddHeadquarter from "./ButtonAddHeadquarter";
import ModalDeleteHeadquarter from "./ModalDeleteHeadquarter";
import ModalAddHeadquarter from "./ModalAddHeadquarter";
import EntityPagination from "../Pagination/EntityPagination";
import EntitySearch from "../SearchBar/EntitySearch";
const HeadquartersPage = () =>{
  
  const [tableHeadquarters] = useState([
    {
      name: "YEAR",
      property: (item) => item.id.year,
      
  },
  {
      name: "CITYNAME",
      property: (item) => item.city.name,
  },
  {
      name: "TYPE",
      property: (item) => item.id.type.description,
  },
  {
    name:"EDIT",
    property: (item) => <span name="edit" onClick={()=>editHeadquarter(item)}><FaEdit color="blue"/></span>
},
{
    name:"DELETE",
    property: (item) => <span name="delete" onClick={()=>deleteHeadquarter(item)}><FaTrashAlt color="red"/></span>
}
  ]);
  const [showModalDelete, setShowModalDelete] = useState(false)
  const [showModalEdit, setShowModalEdit] = useState(false)
  const [headquarter, setHeadquarter] = useState("")

  const dispatch = useDispatch();

  const cities = useSelector(state => fromState.getCitiesHeadquarter(state));
  const types = useSelector(state => fromState.getTypesHeadquarter(state));
  const headquarters = useSelector(state => fromState.getHeadquarters(state));
  const pagination = useSelector(state => fromState.getHeadquartersPagination(state));
  const estoyCargando = useSelector(state => fromState.isHeadquartersPending(state));
  const error = useSelector(state => fromState.isHeadquartersRejected(state));

  const filters = ['year', 'cityName']

  const clearError = () =>{

    dispatch(clearErrors());

  };

  const searchHeadquarter = (filter,offset,limit,sortDirection,sortProperty) =>{

    dispatch(findHeadquarterList(filter.year,filter.cityName,offset,limit,sortDirection,sortProperty))
    
  };
  function editHeadquarter(headquarter){
    dispatch(searchCitiesOfHeadquarter());
    setShowModalEdit(true);
    setHeadquarter(headquarter);
}
  function deleteHeadquarter(headquarter){
      
      setShowModalDelete(true);
      setHeadquarter(headquarter);
  }
  function handleHideModal(){
      
    setShowModalEdit(false);
    setShowModalDelete(false)
  }

  useEffect(() =>{
    
    if(headquarters && headquarters.length ===0 || !Array.isArray(headquarters)){
      searchHeadquarter({year: null, cityName:null},0,6,'asc','id.year')
      dispatch(searchCitiesOfHeadquarter());
      dispatch(searchTypesOfHeadquarter());
    }
  },[headquarters])

  return(
    <div>
      <h1>Headquarters</h1>
      <ButtonAddHeadquarter cities={cities} types={types}/>
      <div className="mb-4">
      <EntitySearch filters={filters} search={searchHeadquarter} offset={0}
        limit={2} sortOrder='asc' sortProperty='id.year'
      />
      </div>
      <div>
        {headquarters && headquarters.length >0
        ?
          <GenericTable headers={tableHeadquarters} data={headquarters} />
          :
          <div>
            {estoyCargando ? "Loading ...":"There are not Headquarters to show"}
          </div>}
      </div>
      <EntityPagination paged={pagination} search={searchHeadquarter}/>
      <ModalError isShowing={!!error} hideModal={clearError} error={error} title="Error in Headquarters"/>
      <ModalAddHeadquarter isShowing={showModalEdit}  cities={cities} types={types} headquarter={headquarter} hideModal={()=>handleHideModal()}/>
      <ModalDeleteHeadquarter isShowing={showModalDelete}  headquarter={headquarter} hideModal={() =>handleHideModal()}/>
    </div>
  )
}

export default HeadquartersPage