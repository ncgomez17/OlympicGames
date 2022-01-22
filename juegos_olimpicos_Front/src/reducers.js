import { combineReducers } from 'redux';
import {olympicGames, olympicGamesPending, olympicGamesRejected, olympicGamesPagination} from './reducers/olympicGames';
import {countries, countriesPending, countriesRejected, countriesPagination, citiesCountry} from './reducers/countries';
import {cities, citiesPending, citiesPagination, citiesRejected, countriesOfCity} from './reducers/cities';
import {headquarters, headquartersPending, headquartersPagination, headquartersRejected, 
  citiesOfHeadquarter, typesOfHeadquarter} from './reducers/headquarters';
import {typesJjoo, typesJjooPending, typesJjooRejected} from './reducers/typesJjoo';
import {audits, auditsPending, auditsPagination} from './reducers/audits';
/**
 * El export default de este archivo corresponde a un reducer general formado por cada
 * uno de los reducers independientes que componen el proyecto.
 */
export default combineReducers({
  olympicGames,
  olympicGamesPending,
  olympicGamesRejected,
  olympicGamesPagination,
  countries,
  countriesPending,
  countriesRejected,
  countriesPagination,
  citiesCountry,
  cities,
  citiesPending,
  citiesRejected,
  citiesPagination,
  countriesOfCity,
  headquarters,
  headquartersPending,
  headquartersRejected,
  headquartersPagination,
  citiesOfHeadquarter,
  typesOfHeadquarter,
  typesJjoo,
  typesJjooPending,
  typesJjooRejected,
  audits,
  auditsPending,
  auditsPagination
});

/**
 * Aquí se exportan de manera específica zonas concretas del state de redux en forma de métodos.
 * Lo hacemos de esta manera para mantener el control sobre el mismo y no realizar un acceso indiscriminado
 * al state.
 * Todas las funciones deben contar al menos con el parámetro "state" representando el state de redux en cada
 * momento. Accedemos siempre a state.<nombreDelReducer>.<propiedad>, y la devolvemos o tratamos una copia de 
 * la misma antes de devolverla.
 */

export const getOlympicGames = (state) => state.olympicGames;
export const isOlympicGamesPending = (state) => state.olympicGamesPending;
export const isOlympicGamesRejected = (state) => state.olympicGamesRejected;
export const getOlympicGamesPagination = (state) => state.olympicGamesPagination;

export const getCountries = (state) => state.countries;
export const isCountriesPending = (state) => state.countriesPending;
export const isCountriesRejected = (state) => state.countriesRejected;
export const getCountriesPagination = (state) => state.countriesPagination;
export const getCountriesCities = (state) => state.citiesCountry;

export const getCities = (state) => state.cities;
export const isCitiesPending = (state) => state.citiesPending;
export const isCitiesRejected = (state) => state.citiesRejected;
export const getCitiesPagination = (state) => state.citiesPagination;
export const getCountriesCity = (state) => state.countriesOfCity;

export const getHeadquarters = (state) => state.headquarters;
export const isHeadquartersPending = (state) => state.headquartersPending;
export const isHeadquartersRejected = (state) => state.headquartersRejected;
export const getHeadquartersPagination = (state) => state.headquartersPagination;
export const getCitiesHeadquarter = (state) => state.citiesOfHeadquarter;
export const getTypesHeadquarter = (state) => state.typesOfHeadquarter;

export const getTypesJjoo = (state) => state.typesJjoo;
export const isTypesJjooPending = (state) => state.typesJjooPending;
export const isTypesJjooRejected = (state) => state.typesJjooRejected;

export const getAudits = (state) => state.audits;
export const isAuditsPending = (state) => state.auditsPending;
export const getAuditsPagination = (state) => state.auditsPagination;
export const isAuditsRejected = (state) => state.auditsRejected;


