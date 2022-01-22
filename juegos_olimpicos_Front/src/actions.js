// Este archivo contiene exportables de actions y action creators de Redux
import api from "./api";
// ACTIONS OLYMPICGAMES 
export const SEARCH_OLYMPIC_GAMES = "SEARCH_OLYMPIC_GAMES";
export const FIND_OLYMPIC_GAMES = "FIND_OLYMPIC_GAMES";
// ACTIONS COUNTRIS
export const SEARCH_COUNTRIES = "SEARCH_COUNTRIES";
export const FIND_COUNTRY_LIST = "FIND_COUNTRY_LIST";
export const ADD_COUNTRY = "ADD_COUNTRY";
export const GET_COUNTRY = "GET_COUNTRY";
export const DELETE_COUNTRY = "DELETE_COUNTRY";
export const UPDATE_COUNTRY = "UPDATE_COUNTRY";
export const CITIES_COUNTRY = "CITIES_COUNTRY";
// ACTIONS CITIES
export const SEARCH_CITIES = "SEARCH_CITIES";
export const SEARCH_COUNTRIES_FOR_CITY = "SEARCH_COUNTRIES_FOR_CITY";
export const FIND_CITY_LIST = "FIND_CITY_LIST";
export const ADD_CITY = "ADD_CITY";
export const GET_CITY = "GET_CITY";
export const DELETE_CITY = "DELETE_CITY";
export const UPDATE_CITY = "UPDATE_CITY";
// ACTIONS HEADQUARTERS
export const SEARCH_HEADQUARTERS = "SEARCH_HEADQUARTERS";
export const SEARCH_CITIES_OF_HEADQUARTER = "SEARCH_CITIES_OF_HEADQUARTER";
export const SEARCH_TYPES_OF_HEADQUARTER = "SEARCH_TYPES_OF_HEADQUARTER";
export const FIND_HEADQUARTER_LIST = "FIND_HEADQUARTER_LIST";
export const ADD_HEADQUARTER = "ADD_HEADQUARTER";
export const GET_HEADQUARTER = "GET_HEADQUARTER";
export const DELETE_HEADQUARTER = "DELETE_HEADQUARTER";
export const UPDATE_HEADQUARTER = "UPDATE_HEADQUARTER";
// ACTIONS TYPESJJOO
export const SEARCH_TYPES_JJOO = "SEARCH_TYPES_JJOO";
export const DELETE_TYPE_JJOO = "DELETE_TYPE_JJOO";
export const CLEAR_ERRORS = "CLEAR_ERRORS";

//ACTIONS AUDIT
export const FIND_AUDIT_LIST = "FIND_AUDIT_LIST";

// ACTION  OLYMPICGAMES

export const searchOlympicGames = () =>({
  type: SEARCH_OLYMPIC_GAMES,
  payload: api.searchOlympicGames(),
})

// ACTION COUNTRIES

export const searchCountries = () =>({
  type: SEARCH_COUNTRIES,
  payload: api.searchCountries(),
})

export const findOlympicGamesList = (offsetParam, 
  limit, sortDirection, sortProperty) =>({
  type: FIND_OLYMPIC_GAMES,
  payload: api.findOLympicGamesList(offsetParam, 
    limit, sortDirection, sortProperty),
})
export const findCountryList = (id, name, cityId, offsetParam, 
  limit, sortDirection, sortProperty) =>({
  type: FIND_COUNTRY_LIST,
  payload: api.findCountryList(id, name, cityId, offsetParam, 
    limit, sortDirection, sortProperty),
})

export const addCountry = (name, code, value) =>({
  type: ADD_COUNTRY,
  payload: api.addCountry(name, code, value),
})

export const getCountry = (id) =>({
  type: GET_COUNTRY,
  payload: api.getCountry(id),
})

export const deleteCountry = (id) =>({
  type: DELETE_COUNTRY,
  payload: api.deleteCountry(id),
})

export const updateCountry = (id, name, code, value) =>({
  type: UPDATE_COUNTRY,
  payload: api.updateCountry(id, name, code, value),
})

export const citiesOfCountry = (id) =>({
  type: CITIES_COUNTRY,
  payload: api.citiesOfCountry(id),
})


// ACTION CITIES

export const searchCities = () =>({
  type: SEARCH_CITIES,
  payload: api.searchCities(),
})

export const searchCountriesOfCity = () =>({
  type: SEARCH_COUNTRIES_FOR_CITY,
  payload: api.searchCountries(),
})

export const findCityList = (id, name, offsetParam, 
  limit, sortDirection, sortProperty) =>({
  type: FIND_CITY_LIST,
  payload: api.findCityList(id, name, offsetParam, 
    limit, sortDirection, sortProperty),
})

export const addCity = (name, country, value, latitude, longitude) =>({
  type: ADD_CITY,
  payload: api.addCity(name, country, value, latitude, longitude)
})

export const getCity = (id) =>({
  type: GET_CITY,
  payload: api.getCity(id),
})

export const deleteCity = (id) =>({
  type: DELETE_CITY,
  payload: api.deleteCity(id),
})

export const updateCity = (id, name, country, value, latitude, longitude) =>({
  type: UPDATE_CITY,
  payload: api.updateCity(id, name, country, value, latitude, longitude),
})

// ACTION HEADQUARTERS

export const searchHeadquarters = () =>({
  type: SEARCH_HEADQUARTERS,
  payload: api.searchHeadquarters(),
})

export const searchCitiesOfHeadquarter = () =>({
  type: SEARCH_CITIES_OF_HEADQUARTER,
  payload: api.searchCities(),
})

export const searchTypesOfHeadquarter = () =>({
  type: SEARCH_TYPES_OF_HEADQUARTER,
  payload: api.searchTypesJjoo(),
})

export const findHeadquarterList = (year, cityName, offsetParam, 
  limit, sortDirection, sortProperty) =>({
  type: FIND_HEADQUARTER_LIST,
  payload: api.findHeadquarterList(year, cityName, offsetParam, 
    limit, sortDirection, sortProperty),
})

export const addHeadquarter = (year, city, type) =>({
  type: ADD_HEADQUARTER,
  payload: api.addHeadquarter(year, city, type),
})

export const getHeadquarter = (year, id) =>({
  type: GET_HEADQUARTER,
  payload: api.getheadquarter(year, id),
})

export const deleteHeadquarter = (year, typeId) =>({
  type: DELETE_HEADQUARTER,
  payload: api.deleteHeadquarter(year, typeId),
})

export const updateHeadquarter = (year, city, type) =>({
  type: UPDATE_HEADQUARTER,
  payload: api.updateHeadquarter(year, city, type),
})

// ACTION TYPESJJOO

export const searchTypesJjoo = () =>({
  type: SEARCH_TYPES_JJOO,
  payload: api.searchTypesJjoo(),
})

export const deleteTypeJjoo = (id) =>({
  type: DELETE_TYPE_JJOO,
  payload: api.deleteTypeJjoo(id),
})

//ACTION AUDIT
export const findAuditList = (user, entity, lastModifiedDate, offsetParam, 
  limit, sortDirection, sortProperty) =>({
  type: FIND_AUDIT_LIST,
  payload: api.findAuditList(user, entity, lastModifiedDate, offsetParam, 
    limit, sortDirection, sortProperty),
})

export const clearErrors = () =>({
  type: CLEAR_ERRORS,
})