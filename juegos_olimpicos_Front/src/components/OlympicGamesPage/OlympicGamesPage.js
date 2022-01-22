import React, {useState, useEffect} from "react";
import { useSelector, useDispatch } from 'react-redux';
import {findOlympicGamesList, clearErrors} from '../../actions';
import * as fromState from '../../reducers';
import GenericTable from "../Tables/GenericTable";
import ModalError from "../Errors/ModalError";
import EntityPagination from "../Pagination/EntityPagination";

const OlympicGamesPage = () =>{
  
  const [tableOlympicGames] = useState([
    {
      name: "COUNTRYID",
      property: (item) => item.countryId,
      
  },
  {
      name: "COUNTRYNAME",
      property: (item) => item.countryName,
  },
  {
      name: "CITYID",
      property: (item) => item.cityId,
  },
  {
      name: "CITYNAME",
      property: (item) => item.cityName,
  },
  {
      name: "VALUE",
      property: (item) => item.cityValue,
  },
  {
      name: "DESCRIPTION",
      property: (item) => item.description,
  },
  {
      name: "NUMHEADQUARTERS",
      property: (item) => item.countHeadquarters,
  }
  ]);

  const dispatch = useDispatch();

  const olympicGames = useSelector(state => fromState.getOlympicGames(state));
  const estoyCargando = useSelector(state => fromState.isOlympicGamesPending(state));
  const pagination = useSelector(state => fromState.getOlympicGamesPagination(state));
  const error = useSelector(state => fromState.isOlympicGamesRejected(state));

  const clearError = () =>{

    dispatch(clearErrors());

  };

  const searchOlympicGames = (offset,limit,sortDirection,sortProperty) =>{

    dispatch(findOlympicGamesList(offset,limit,sortDirection,sortProperty));
    
  };

  useEffect(() =>{
    if(olympicGames && olympicGames.length ===0 | !Array.isArray(olympicGames)){
      searchOlympicGames(0,4,'asc','countryId');
    }

  },[olympicGames])

  return(
    <div>
      <h1>OlympicGames</h1>
      <div>
        {olympicGames && olympicGames.length >0
        ?
          <GenericTable headers={tableOlympicGames} data={olympicGames} />
          :
          <div>
            {estoyCargando ? "Loading ...":"There are not OlympicGames to show"}
          </div>}
      </div>
      <EntityPagination paged={pagination} search={searchOlympicGames} filter={false}/>
      <ModalError isShowing={!!error} hideModal={clearError} error={error} title="Error in OlympicGames"/>
    </div>
  )
}

export default OlympicGamesPage