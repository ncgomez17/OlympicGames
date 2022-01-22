import { fulfiledType, pendingType ,rejectedType } from "../utils";
import { FIND_AUDIT_LIST,CLEAR_ERRORS } from "../actions";

const initialState = {
    cargando: false,
    todos: [],
    pagination: [],
    rejected: null,
}

export function audits(state = initialState.todos, {type, payload}){

    switch(type){
        case fulfiledType(FIND_AUDIT_LIST):
            return payload.audits;

        case pendingType(FIND_AUDIT_LIST):
        case rejectedType(FIND_AUDIT_LIST):
            return state

        default:
            return state

    }
}

export function auditsPending(state = initialState.cargando, {type}){

    switch(type){
        case pendingType(FIND_AUDIT_LIST):
            return true

        case fulfiledType(FIND_AUDIT_LIST):
        case rejectedType(FIND_AUDIT_LIST):
            return false

        default:
            return state
    }
}
export function auditsPagination(state = initialState.pagination, {type, payload}){

    switch(type){

        case fulfiledType(FIND_AUDIT_LIST):
            return payload.pagination;

        case pendingType(FIND_AUDIT_LIST):            
        case rejectedType(FIND_AUDIT_LIST):            
            return state

        default:
            return state

    }
}

export function citiesRejected(state = initialState.rejected, {type, payload}){

    switch(type){ 
        case rejectedType(FIND_AUDIT_LIST):
            return payload

        case fulfiledType(FIND_AUDIT_LIST):
        case pendingType(FIND_AUDIT_LIST):
        case CLEAR_ERRORS:
            return null

        default:
            return state
    
    } 
}