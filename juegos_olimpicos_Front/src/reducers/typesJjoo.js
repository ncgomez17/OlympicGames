import { fulfiledType, pendingType ,rejectedType } from "../utils";
import {  DELETE_TYPE_JJOO, SEARCH_TYPES_JJOO, CLEAR_ERRORS} from "../actions";

const initialState = {
    cargando: false,
    todos: [],
    error: null
}

export function typesJjoo(state = initialState.todos, {type,payload}){

    switch(type){
        case fulfiledType(SEARCH_TYPES_JJOO):
        case fulfiledType(DELETE_TYPE_JJOO):
            return payload

        case pendingType(SEARCH_TYPES_JJOO):
        case pendingType(DELETE_TYPE_JJOO):
        case rejectedType(SEARCH_TYPES_JJOO):
        case rejectedType(DELETE_TYPE_JJOO):
            return state

        default:
            return state

    }
}

export function typesJjooPending(state = initialState.cargando, {type}){

    switch(type){
        case pendingType(SEARCH_TYPES_JJOO):
        case pendingType(DELETE_TYPE_JJOO):
            return true

        case fulfiledType(SEARCH_TYPES_JJOO):
        case fulfiledType(DELETE_TYPE_JJOO):
        case rejectedType(SEARCH_TYPES_JJOO):
        case rejectedType(DELETE_TYPE_JJOO):
            return false

        default:
            return state

    }
}
export function typesJjooRejected(state = initialState.error, {type, payload}){

    switch(type){
        case rejectedType(SEARCH_TYPES_JJOO):
        case rejectedType(DELETE_TYPE_JJOO):
            return payload

        case fulfiledType(SEARCH_TYPES_JJOO):
        case fulfiledType(DELETE_TYPE_JJOO):
        case pendingType(SEARCH_TYPES_JJOO):
        case pendingType(DELETE_TYPE_JJOO):
        case CLEAR_ERRORS:
            return null

        default:
            return state

    }
}