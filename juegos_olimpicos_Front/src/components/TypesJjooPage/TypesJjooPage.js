import React, {useState, useEffect} from "react";
import { useSelector, useDispatch } from 'react-redux';
import {searchTypesJjoo, clearErrors} from '../../actions';
import * as fromState from '../../reducers';
import GenericTable from "../Tables/GenericTable";
import ModalError from "../Errors/ModalError"

const TypesJjooPage = () =>{
  
  const [tableTypesJjoo] = useState([
    {
      name: "ID",
      property: (item) => item.id,
      
  },
  {
      name: "DESCRIPTION",
      property: (item) => item.description
  }
  ]);

  const dispatch = useDispatch();

  const types = useSelector(state => fromState.getTypesJjoo(state));
  const estoyCargando = useSelector(state => fromState.isTypesJjooPending(state));
  const error = useSelector(state => fromState.isTypesJjooRejected(state));

  const clearError = () =>{

    dispatch(clearErrors());

  };

  const searchTypes = () =>{

    dispatch(searchTypesJjoo());
    
  };

  useEffect(() =>{
    if(types && types.length ===0 || !Array.isArray(types)){
    searchTypes()
    }
  },[types])

  return(
    <div>
      <h1>TypesJjoo</h1>
      <div>
        {types && types.length >0
        ?
          <GenericTable headers={tableTypesJjoo} data={types} />
          :
          <div>
            {estoyCargando ? "Loading ...":"There are not typesJjoo to show"}
          </div>}
      </div>
      <ModalError isShowing={!!error} hideModal={clearError} error={error} title="Error in TypesJjoo"/>
    </div>
  )
}

export default TypesJjooPage