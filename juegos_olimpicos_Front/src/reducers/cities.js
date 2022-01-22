import { fulfiledType, pendingType ,rejectedType } from "../utils";
import { SEARCH_CITIES, ADD_CITY, DELETE_CITY, UPDATE_CITY,CLEAR_ERRORS
, GET_CITY, FIND_CITY_LIST, SEARCH_COUNTRIES_FOR_CITY } from "../actions";

const initialState = {
    cargando: false,
    todos: [],
    pagination: [],
    rejected: null,
    countries: []
}

export function cities(state = initialState.todos, {type, payload}){

    switch(type){
        case fulfiledType(ADD_CITY):
        case fulfiledType(DELETE_CITY):
        case fulfiledType(UPDATE_CITY):
        case fulfiledType(GET_CITY):
            return payload;
        case fulfiledType(SEARCH_CITIES):
        case fulfiledType(FIND_CITY_LIST):
            return payload.cities;

        case pendingType(SEARCH_CITIES):
        case pendingType(FIND_CITY_LIST):
        case pendingType(SEARCH_COUNTRIES_FOR_CITY):
        case pendingType(ADD_CITY):
        case pendingType(DELETE_CITY):
        case pendingType(UPDATE_CITY):
        case pendingType(GET_CITY):
        case rejectedType(SEARCH_CITIES):
        case rejectedType(FIND_CITY_LIST):
        case rejectedType(ADD_CITY):
        case rejectedType(DELETE_CITY):
        case rejectedType(UPDATE_CITY):
        case rejectedType(GET_CITY):
            return state

        default:
            return state

    }
}

export function citiesPending(state = initialState.cargando, {type}){

    switch(type){
        case pendingType(SEARCH_CITIES):
        case pendingType(FIND_CITY_LIST):
        case pendingType(SEARCH_COUNTRIES_FOR_CITY):
        case pendingType(ADD_CITY):
        case pendingType(DELETE_CITY):
        case pendingType(UPDATE_CITY):
        case pendingType(GET_CITY):
            return true

        case fulfiledType(SEARCH_CITIES):
        case fulfiledType(FIND_CITY_LIST):
        case fulfiledType(SEARCH_COUNTRIES_FOR_CITY):
        case fulfiledType(ADD_CITY):
        case fulfiledType(DELETE_CITY):
        case fulfiledType(UPDATE_CITY):
        case fulfiledType(GET_CITY):
        case rejectedType(SEARCH_CITIES):
        case rejectedType(FIND_CITY_LIST):
        case rejectedType(SEARCH_COUNTRIES_FOR_CITY):    
        case rejectedType(ADD_CITY):
        case rejectedType(DELETE_CITY):
        case rejectedType(UPDATE_CITY):
        case rejectedType(GET_CITY):
            return false

        default:
            return state
    }
}
export function citiesPagination(state = initialState.pagination, {type, payload}){

    switch(type){

        case fulfiledType(FIND_CITY_LIST):
            return payload.pagination;

        case pendingType(FIND_CITY_LIST):            
        case rejectedType(FIND_CITY_LIST):            
            return state

        default:
            return state

    }
}
export function countriesOfCity(state = initialState.countries, {type, payload}){

    switch(type){

        case fulfiledType(SEARCH_COUNTRIES_FOR_CITY):
            return payload.countries;
            
        case pendingType(SEARCH_COUNTRIES_FOR_CITY):            
        case rejectedType(SEARCH_COUNTRIES_FOR_CITY):            
            return state

        default:
            return state

    }
}

export function citiesRejected(state = initialState.rejected, {type, payload}){

    switch(type){ 
        case rejectedType(SEARCH_CITIES):
        case rejectedType(FIND_CITY_LIST):
        case rejectedType(SEARCH_COUNTRIES_FOR_CITY):
        case rejectedType(ADD_CITY):
        case rejectedType(DELETE_CITY):
        case rejectedType(UPDATE_CITY):
        case rejectedType(GET_CITY):
            return payload

        case fulfiledType(SEARCH_CITIES):
        case fulfiledType(FIND_CITY_LIST):
        case fulfiledType(SEARCH_COUNTRIES_FOR_CITY):
        case fulfiledType(ADD_CITY):
        case fulfiledType(DELETE_CITY):
        case fulfiledType(UPDATE_CITY):
        case fulfiledType(GET_CITY):
        case pendingType(SEARCH_CITIES):
        case pendingType(FIND_CITY_LIST):
        case pendingType(SEARCH_COUNTRIES_FOR_CITY):
        case pendingType(ADD_CITY):
        case pendingType(DELETE_CITY):
        case pendingType(UPDATE_CITY):
        case pendingType(GET_CITY):
        case CLEAR_ERRORS:
            return null

        default:
            return state
    
    } 
}