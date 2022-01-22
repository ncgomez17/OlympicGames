import { fulfiledType, pendingType ,rejectedType } from "../utils";
import { SEARCH_HEADQUARTERS, ADD_HEADQUARTER, DELETE_HEADQUARTER, UPDATE_HEADQUARTER, CLEAR_ERRORS
,GET_HEADQUARTER, FIND_HEADQUARTER_LIST, SEARCH_CITIES_OF_HEADQUARTER, SEARCH_TYPES_OF_HEADQUARTER } from "../actions";

const initialState = {
    cargando: false,
    todos: [],
    pagination: [],
    error: null,
    cities: [],
    types: []
}

export function headquarters(state = initialState.todos, {type, payload}){

    switch(type){
        case fulfiledType(ADD_HEADQUARTER):
        case fulfiledType(DELETE_HEADQUARTER):
        case fulfiledType(UPDATE_HEADQUARTER):
        case fulfiledType(GET_HEADQUARTER):
            return payload;
        case fulfiledType(SEARCH_HEADQUARTERS):
        case fulfiledType(FIND_HEADQUARTER_LIST):
            return payload.headquarters;

        case rejectedType(SEARCH_HEADQUARTERS):
        case rejectedType(FIND_HEADQUARTER_LIST):
        case rejectedType(SEARCH_CITIES_OF_HEADQUARTER):
        case rejectedType(SEARCH_TYPES_OF_HEADQUARTER):
        case rejectedType(ADD_HEADQUARTER):
        case rejectedType(DELETE_HEADQUARTER):
        case rejectedType(UPDATE_HEADQUARTER):
        case rejectedType(GET_HEADQUARTER):
        case pendingType(SEARCH_HEADQUARTERS):
        case pendingType(FIND_HEADQUARTER_LIST):
        case pendingType(SEARCH_CITIES_OF_HEADQUARTER):
        case pendingType(SEARCH_TYPES_OF_HEADQUARTER):
        case pendingType(ADD_HEADQUARTER):
        case pendingType(DELETE_HEADQUARTER):
        case pendingType(UPDATE_HEADQUARTER):
        case pendingType(GET_HEADQUARTER):
            return state

        default:
            return state

    }
}

export function headquartersPending(state = initialState.cargando, {type}){

    switch(type){
        case pendingType(SEARCH_HEADQUARTERS):
        case pendingType(FIND_HEADQUARTER_LIST):
        case pendingType(SEARCH_CITIES_OF_HEADQUARTER):
        case pendingType(SEARCH_TYPES_OF_HEADQUARTER):
        case pendingType(ADD_HEADQUARTER):
        case pendingType(DELETE_HEADQUARTER):
        case pendingType(UPDATE_HEADQUARTER):
        case pendingType(GET_HEADQUARTER):
            return true

        case fulfiledType(SEARCH_HEADQUARTERS):
        case fulfiledType(FIND_HEADQUARTER_LIST):
        case fulfiledType(SEARCH_CITIES_OF_HEADQUARTER):
        case fulfiledType(SEARCH_TYPES_OF_HEADQUARTER):
        case fulfiledType(ADD_HEADQUARTER):
        case fulfiledType(DELETE_HEADQUARTER):
        case fulfiledType(UPDATE_HEADQUARTER):
        case fulfiledType(GET_HEADQUARTER):
        case rejectedType(SEARCH_HEADQUARTERS):
        case rejectedType(FIND_HEADQUARTER_LIST):
        case rejectedType(SEARCH_CITIES_OF_HEADQUARTER):
        case rejectedType(SEARCH_TYPES_OF_HEADQUARTER):
        case rejectedType(ADD_HEADQUARTER):
        case rejectedType(DELETE_HEADQUARTER):
        case rejectedType(UPDATE_HEADQUARTER):
        case rejectedType(GET_HEADQUARTER):
            return false

        default:
            return state

    }
}
export function headquartersPagination(state = initialState.pagination, {type, payload}){

    switch(type){

        case fulfiledType(FIND_HEADQUARTER_LIST):
            return payload.pagination;

        case pendingType(FIND_HEADQUARTER_LIST):            
        case rejectedType(FIND_HEADQUARTER_LIST):            
            return state

        default:
            return state

    }
}

export function citiesOfHeadquarter(state = initialState.cities, {type, payload}){

    switch(type){

        case fulfiledType(SEARCH_CITIES_OF_HEADQUARTER):
            return payload.cities;

        case pendingType(SEARCH_CITIES_OF_HEADQUARTER):            
        case rejectedType(SEARCH_CITIES_OF_HEADQUARTER):            
            return state

        default:
            return state

    }
}

export function typesOfHeadquarter(state = initialState.types, {type, payload}){

    switch(type){

        case fulfiledType(SEARCH_TYPES_OF_HEADQUARTER):
            return payload;

        case pendingType(SEARCH_TYPES_OF_HEADQUARTER):            
        case rejectedType(SEARCH_TYPES_OF_HEADQUARTER):            
            return state

        default:
            return state

    }
}
export function headquartersRejected(state = initialState.error, {type, payload}){

    switch(type){
        case rejectedType(SEARCH_HEADQUARTERS):
        case rejectedType(FIND_HEADQUARTER_LIST):
        case rejectedType(SEARCH_CITIES_OF_HEADQUARTER):
        case rejectedType(SEARCH_TYPES_OF_HEADQUARTER):
        case rejectedType(ADD_HEADQUARTER):
        case rejectedType(DELETE_HEADQUARTER):
        case rejectedType(UPDATE_HEADQUARTER):
        case rejectedType(GET_HEADQUARTER):
            return payload

        case fulfiledType(SEARCH_HEADQUARTERS):
        case fulfiledType(FIND_HEADQUARTER_LIST):
        case fulfiledType(SEARCH_CITIES_OF_HEADQUARTER):
        case fulfiledType(SEARCH_TYPES_OF_HEADQUARTER):
        case fulfiledType(ADD_HEADQUARTER):
        case fulfiledType(DELETE_HEADQUARTER):
        case fulfiledType(UPDATE_HEADQUARTER):
        case fulfiledType(GET_HEADQUARTER):
        case pendingType(SEARCH_HEADQUARTERS):
        case pendingType(FIND_HEADQUARTER_LIST):
        case pendingType(SEARCH_CITIES_OF_HEADQUARTER):
        case pendingType(SEARCH_TYPES_OF_HEADQUARTER):
        case pendingType(ADD_HEADQUARTER):
        case pendingType(DELETE_HEADQUARTER):
        case pendingType(UPDATE_HEADQUARTER):
        case pendingType(GET_HEADQUARTER):
        case CLEAR_ERRORS:
            return null

        default:
            return state

    }
}