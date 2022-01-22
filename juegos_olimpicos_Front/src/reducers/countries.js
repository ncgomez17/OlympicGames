import { fulfiledType, pendingType ,rejectedType } from "../utils";
import { SEARCH_COUNTRIES,ADD_COUNTRY, DELETE_COUNTRY, UPDATE_COUNTRY, CITIES_COUNTRY, CLEAR_ERRORS
,GET_COUNTRY, FIND_COUNTRY_LIST} from "../actions";

const initialState = {
    cargando: false,
    todos: [],
    pagination: [],
    cities:[],
    error: null
}

export function countries(state = initialState.todos, {type, payload}){

    switch(type){

        case fulfiledType(ADD_COUNTRY):
        case fulfiledType(DELETE_COUNTRY):
        case fulfiledType(UPDATE_COUNTRY):
        case fulfiledType(GET_COUNTRY):
            return payload;
        case fulfiledType(SEARCH_COUNTRIES):
        case fulfiledType(FIND_COUNTRY_LIST):
            return payload.countries;

        case pendingType(SEARCH_COUNTRIES):
        case pendingType(FIND_COUNTRY_LIST):            
        case pendingType(ADD_COUNTRY):
        case pendingType(DELETE_COUNTRY):
        case pendingType(UPDATE_COUNTRY):
        case pendingType(GET_COUNTRY):
        case rejectedType(SEARCH_COUNTRIES):
        case rejectedType(FIND_COUNTRY_LIST):            
        case rejectedType(ADD_COUNTRY):
        case rejectedType(DELETE_COUNTRY):
        case rejectedType(UPDATE_COUNTRY):
        case rejectedType(GET_COUNTRY):
            return state

        default:
            return state

    }
}

export function countriesPending(state = initialState.cargando, {type}){

    switch(type){
        case pendingType(SEARCH_COUNTRIES):
        case pendingType(FIND_COUNTRY_LIST):
        case pendingType(ADD_COUNTRY):
        case pendingType(DELETE_COUNTRY):
        case pendingType(UPDATE_COUNTRY):
        case pendingType(GET_COUNTRY):
            return true

        case fulfiledType(SEARCH_COUNTRIES):
        case fulfiledType(FIND_COUNTRY_LIST):
        case fulfiledType(ADD_COUNTRY):
        case fulfiledType(DELETE_COUNTRY):
        case fulfiledType(UPDATE_COUNTRY):
        case fulfiledType(GET_COUNTRY):
        case rejectedType(SEARCH_COUNTRIES):
        case rejectedType(FIND_COUNTRY_LIST):
        case rejectedType(ADD_COUNTRY):
        case rejectedType(DELETE_COUNTRY):
        case rejectedType(UPDATE_COUNTRY):
        case rejectedType(GET_COUNTRY):
            return false
            
        default:
            return state

    }
}
export function countriesPagination(state = initialState.pagination, {type, payload}){

    switch(type){

        case fulfiledType(FIND_COUNTRY_LIST):
            return payload.pagination;

        case pendingType(FIND_COUNTRY_LIST):            
        case rejectedType(FIND_COUNTRY_LIST):            
            return state

        default:
            return state

    }
}

export function citiesCountry(state = initialState.cities, {type, payload}){

    switch(type){

        case fulfiledType(CITIES_COUNTRY):
            return payload;

        case pendingType(CITIES_COUNTRY):            
        case rejectedType(CITIES_COUNTRY):            
            return state

        default:
            return state

    }
}


export function countriesRejected(state = initialState.error, {type,payload}){

    switch(type){
        case rejectedType(SEARCH_COUNTRIES):
        case rejectedType(FIND_COUNTRY_LIST):
        case rejectedType(ADD_COUNTRY):
        case rejectedType(DELETE_COUNTRY):
        case rejectedType(UPDATE_COUNTRY):
        case rejectedType(GET_COUNTRY):
            return payload

        case pendingType(SEARCH_COUNTRIES):
        case pendingType(FIND_COUNTRY_LIST):
        case pendingType(ADD_COUNTRY):
        case pendingType(DELETE_COUNTRY):
        case pendingType(UPDATE_COUNTRY):
        case pendingType(GET_COUNTRY):
        case fulfiledType(SEARCH_COUNTRIES):
        case fulfiledType(FIND_COUNTRY_LIST):
        case fulfiledType(ADD_COUNTRY):
        case fulfiledType(DELETE_COUNTRY):
        case fulfiledType(UPDATE_COUNTRY):
        case fulfiledType(CITIES_COUNTRY):
        case fulfiledType(GET_COUNTRY):
        case CLEAR_ERRORS:
            return null
            
        default:
            return state

    }
}
