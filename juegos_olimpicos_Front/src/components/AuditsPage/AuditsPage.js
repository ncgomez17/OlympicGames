import React, {useState, useEffect} from "react";
import { useSelector, useDispatch } from 'react-redux';
import { findAuditList, clearErrors} from '../../actions';
import * as fromState from '../../reducers';
import GenericTable from "../Tables/GenericTable";
import ModalError from "../Errors/ModalError"
import EntityPagination from "../Pagination/EntityPagination";
import EntitySearch from "../SearchBar/EntitySearch";
const AuditsPage = () =>{
  
  const [tableAudits] = useState([
    {
      name: "ID_ENTITY",
      property: (item) => JSON.stringify(item.id),
      
  },
  {
      name: "ENTITY_NAME",
      property: (item) => item.entity,
  },
  {
      name: "ACTION",
      property: (item) => item.action,
  },
  {
    name: "LAST_MODIFIED_BY",
    property: (item) => item.lastModifiedBy,
},
  {
      name: "DATA",
      property: (item) => JSON.stringify(item.data),
  },
  {
    name: "LAST_MODIFIED_DATE",
    property: (item) => item.lastModifiedDate,
}
  ]);

  const dispatch = useDispatch();

  const audits = useSelector(state => fromState.getAudits(state));
  const pagination = useSelector(state => fromState.getAuditsPagination(state));
  const estoyCargando = useSelector(state => fromState.isAuditsPending(state));
  const error = useSelector(state => fromState.isAuditsRejected(state));

  const filters = ['user', 'entity','lastModifiedDate']

  const clearError = () =>{

    dispatch(clearErrors());

  };

  const searchAudit = (filter,offset,limit,sortDirection,sortProperty) =>{

    dispatch(findAuditList(filter.user, filter.entity,filter.lastModifiedDate, offset, limit, 'desc', sortProperty));
    
  };
  useEffect(() =>{
    if(audits && audits.length ===0 || !Array.isArray(audits)){
      searchAudit({id: null, name:null},0,4,'desc','lastModifiedDate'); 
    }
  },[audits])

  return(
    <div>
      <h1>Audits</h1>
      <div className="mb-4">
      <EntitySearch filters={filters} search={searchAudit} offset={0}
        limit={2} sortOrder='asc' sortProperty='id'
      />
      </div>
      <div>
        {audits && audits.length >0
        ?
          <GenericTable headers={tableAudits} data={audits} />
          :
          <div>
            {estoyCargando ? "Loading ...":"There are not Audits to show"}
          </div>}
      </div>
      <EntityPagination paged={pagination} search={searchAudit}/>
      <ModalError isShowing={!!error} hideModal={clearError} error={error} title="Error in Audits"/>
    </div>
  )
}

export default AuditsPage