import { fulfiledType, pendingType ,rejectedType } from "../utils";
import { SEARCH_OLYMPIC_GAMES, FIND_OLYMPIC_GAMES, CLEAR_ERRORS } from "../actions";

const initialState = {
    cargando : false,
    todos: [],
    pagination: [],
    error:null
}

export function olympicGames(state = initialState.todos, {type, payload}){

    switch(type){
        case fulfiledType(SEARCH_OLYMPIC_GAMES):   
            return payload
        case fulfiledType(FIND_OLYMPIC_GAMES): 
            return payload.olympicGames
        case pendingType(FIND_OLYMPIC_GAMES):
        case rejectedType(FIND_OLYMPIC_GAMES):    
        case pendingType(SEARCH_OLYMPIC_GAMES):
        case rejectedType(SEARCH_OLYMPIC_GAMES):
            return state
            
        default:
            return state
    }
}
export function olympicGamesPending(state = initialState.cargando, {type}){

    switch(type){
        case pendingType(SEARCH_OLYMPIC_GAMES):
        case pendingType(FIND_OLYMPIC_GAMES):
            return true
        case fulfiledType(FIND_OLYMPIC_GAMES):
        case rejectedType(FIND_OLYMPIC_GAMES):    
        case fulfiledType(SEARCH_OLYMPIC_GAMES):
        case rejectedType(SEARCH_OLYMPIC_GAMES):
            return false
        default:
            return state
    }
}

export function olympicGamesRejected(state = initialState.error, {type,payload}){

    switch(type){
        case rejectedType(FIND_OLYMPIC_GAMES):
        case rejectedType(SEARCH_OLYMPIC_GAMES):
            return payload

        case pendingType(FIND_OLYMPIC_GAMES):
        case fulfiledType(FIND_OLYMPIC_GAMES):
        case pendingType(SEARCH_OLYMPIC_GAMES):
        case fulfiledType(SEARCH_OLYMPIC_GAMES):
        case CLEAR_ERRORS:
            return null

        default:
            return state
    }
}
export function olympicGamesPagination(state = initialState.pagination, {type,payload}){

    switch(type){
        case fulfiledType(FIND_OLYMPIC_GAMES):
            return payload.pagination

        case pendingType(FIND_OLYMPIC_GAMES):
        case rejectedType(FIND_OLYMPIC_GAMES):     
            return state

        default:
            return state
    }
}