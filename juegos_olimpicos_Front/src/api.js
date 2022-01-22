/**
 * Aqui se condensan las llamadas a backend, para ser usadas en los action creators de redux.
 * Si el archivo llegara a crecer demasiado, podría subdividirse por las entidades que se acceden 
 * en backend 
 */ 

/**
 * Las funciones que retornen llamadas ejecutadas con axios están retornando promesas realmente, no 
 * los datos en sí. Ojo con la naturaleza asíncrona de JavaScript
 * 
 * Promesas en JavaScript
 * https://developer.mozilla.org/es/docs/Web/JavaScript/Referencia/Objetos_globales/Promise
 */
import axios from 'axios';

// Configurable
const baseRestUrl = 'http://localhost:8080';

axios.interceptors.request.use(function (config) {
  const token = sessionStorage.getItem("token");
  config.headers.Authorization =  token;
  return config;
});

const authenticate =(nickName,password,role) =>{
  return axios.post(
    `${baseRestUrl}/user/authenticate`,
    {
      nickName,
      password,
      role
    }
    ).then(data => data)
}

/**
 * Return all OlympicGames in BD
 */

const searchOlympicGames =() =>{
  return axios.get(
    `${baseRestUrl}/headquarter/olympicGames`
    ).then(data => data.data)
}
const findOLympicGamesList =(offsetParam, limit,
  sortDirection, sorProperty) =>{
  return axios.get(
    `${baseRestUrl}/headquarter/findOlympicGamesList`,
    {params:{
      offsetParam: offsetParam, limit: limit,
      sortDirection: sortDirection, sortProperty: sorProperty}}
  ).then(data => data.data)
}

/**
 * Return all countries
 */
const searchCountries =() =>{
  return axios.get(
    `${baseRestUrl}/country/findCountryList`
  ).then(data => data.data)
}

const findCountryList =(id, name, cityId, offsetParam, limit,
  sortDirection, sorProperty ) =>{
  return axios.get(
    `${baseRestUrl}/country/findCountryList`,
    {params:{id: id, name: name, cityId: cityId,
      offsetParam: offsetParam, limit: limit,
      sortDirection: sortDirection, sortProperty: sorProperty}}
  ).then(data => data.data)
}

/**
 * Get a country
 */
const getCountry =(id) =>{
  return axios.get(
    `${baseRestUrl}/country/${id}`
  ).then(data => data.data)
}

/**
 * Add a country
 */
const addCountry =(name, code, value) =>{
  return axios.post(
    `${baseRestUrl}/country`,
    {
      name,
      code,
      value
    }
  ).then(
    axios.get(
      `${baseRestUrl}/country`
    ).then(data => data.data))
}

/**
 * Remove a country
 */
const deleteCountry =(id) =>{
  return axios.delete(
    `${baseRestUrl}/country/${id}`,
  ).then(
    axios.get(
      `${baseRestUrl}/country`
    ).then(data => data.data))
}

/**
 * Update a country
 */
const updateCountry =(id, name, code, value) =>{
  return axios.put(
    `${baseRestUrl}/country`,
    {
      id,
      name,
      code,
      value
    }
  ).then(
    axios.get(
      `${baseRestUrl}/country`
    ).then(data => data.data))
}

/**
 * Get all cities country
 */
const citiesOfCountry =(countryId) =>{
  return axios.get(
    `${baseRestUrl}/country/country-cities${countryId}`
  ).then(data => data.data)
}
/**
 * Get a city
 */
const getCity =(id) =>{
  return axios.get(
    `${baseRestUrl}/city/${id}`
  ).then(data => data.data)
}

/**
 * Return all cities
 */
const searchCities =() =>{
  return axios.get(
    `${baseRestUrl}/city/findCityList`
  ).then(data => data.data)
}

const findCityList =(id, name, offsetParam, limit,
  sortDirection, sorProperty) =>{
  return axios.get(
    `${baseRestUrl}/city/findCityList`,
    {params:{id: id, name: name,
      offsetParam: offsetParam, limit: limit,
      sortDirection: sortDirection, sortProperty: sorProperty}}
  ).then(data => data.data)
}

/**
 * Add a city
 */
const addCity =(name, country, value, latitude, longitude) =>{
  return axios.post(
    `${baseRestUrl}/city`,
    {
      name,
      country,
      value,
      latitude,
      longitude
    }
  ).then(
    axios.get(
      `${baseRestUrl}/city`
    ).then(data => data.data))
}


/**
 * Remove a city
 */
const deleteCity =(id) =>{
  return axios.delete(
    `${baseRestUrl}/city/${id}`,
  ).then(
    axios.get(
      `${baseRestUrl}/city`
    ).then(data => data.data))
}

/**
 * Update a city
 */
const updateCity =(id, name, country, value, latitude, longitude) =>{
  return axios.put(
    `${baseRestUrl}/city`,
    {
      id,
      name,
      country,
      value,
      latitude,
      longitude
    }
  ).then(
    axios.get(
      `${baseRestUrl}/city`
    ).then(data => data.data))
}

/**
 * Return all headquarters
 */
const searchHeadquarters =() =>{
  return axios.get(
    `${baseRestUrl}/headquarter/findHeadquarterList`
  ).then(data => data.data)
}

const findHeadquarterList =(year, cityName, offsetParam, limit,
  sortDirection, sorProperty ) =>{
  return axios.get(
    `${baseRestUrl}/headquarter/findHeadquarterList`,
    {params:{year: year, cityName: cityName,
      offsetParam: offsetParam, limit: limit,
      sortDirection: sortDirection, sortProperty: sorProperty}}
  ).then(data => data.data)
}

/**
 * Get a headquarter
 */

const getHeadquarter =(year,id) =>{
  return axios.get(
    `${baseRestUrl}/headquarter/`,{params: {year: year,
    cityId:id}}
  ).then(data => data.data)
}

/**
 * Add a headquarter
 */
const addHeadquarter =(year, city, type) =>{
  return axios.post(
    `${baseRestUrl}/headquarter`,
    {
      id:{year,type},
      city,
    }
  ).then(
    axios.get(
      `${baseRestUrl}/headquarter`
    ).then(data => data.data))
}

/**
 * Remove a city
 */
const deleteHeadquarter =(year, typeId) =>{
  return axios.delete(
    `${baseRestUrl}/headquarter/?year=${year}&typeId=${typeId}`
  ).then(
    axios.get(
      `${baseRestUrl}/headquarter`
    ).then(data => data.data))
}

/**
 * Update a city
 */
const updateHeadquarter =(year, city, type) =>{
  return axios.put(
    `${baseRestUrl}/headquarter`,
    {
      id:{year,type},
      city
    }
  ).then(
    axios.get(
      `${baseRestUrl}/headquarter`
    ).then(data => data.data))
}

/**
 * Return all TypesJjoo
 */
const searchTypesJjoo =() =>{
  return axios.get(
    `${baseRestUrl}/typeJjoo`
  ).then(data => data.data)
}

/**
 * Remove a TypeJjoo
 */
const deleteTypeJjoo =(id) =>{
  return axios.delete(
    `${baseRestUrl}/typeJjoo/${id}`,
  ).then(
    axios.get(
      `${baseRestUrl}/typeJjoo`
    ).then(data => data.data))
}

const findAuditList =(user, entity,lastModifiedDate, offsetParam, limit,
  sortDirection, sorProperty ) =>{
  return axios.get(
    `${baseRestUrl}/audit/findAuditList`,
    {params:{user: user, entity: entity, lastModifiedDate:lastModifiedDate,
      offsetParam: offsetParam, limit: limit,
      sortDirection: sortDirection, sortProperty: sorProperty}}
  ).then(data => data.data)
}


export default {
  baseRestUrl,
  authenticate,
  searchOlympicGames,
  findOLympicGamesList,
  searchCountries,
  findCountryList,
  addCountry,
  getCountry,
  deleteCountry,
  updateCountry,
  citiesOfCountry,
  searchCities,
  findCityList,
  addCity,
  getCity,
  deleteCity,
  updateCity,
  searchHeadquarters,
  findHeadquarterList,
  addHeadquarter,
  getHeadquarter,
  deleteHeadquarter,
  updateHeadquarter,
  searchTypesJjoo,
  deleteTypeJjoo, 
  findAuditList
}