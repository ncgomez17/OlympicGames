import React from "react";
import { useParams } from "react-router";
import { useState,useEffect } from "react";
import { getCountry, citiesOfCountry } from "../../actions";
import { useDispatch, useSelector } from "react-redux";
import * as fromState from '../../reducers';
import { Container,ListGroup, Col, Row } from "react-bootstrap";
import GenericTable from "../Tables/GenericTable";
import {FaMapMarkerAlt} from 'react-icons/fa';


const ViewDetailCountry = () =>{

  const id = useParams();
  const dispatch = useDispatch();
  const country = useSelector(state => fromState.getCountries(state));
  const cities = useSelector(state => fromState.getCountriesCities(state));


  const [tableCities] = useState([
    {
      name: "CITYID",
      property: (item) => item.id,
      
  },
  {
      name: "NAME",
      property: (item) => item.name,
  },
  {
      name: "COUNTRY",
      property: (item) => item.country.name,
  },
  {
      name: "VALUE",
      property: (item) => item.value,
  },
  {
    name:"MAP",
    property: (item) => <span name="details" onClick={()=>handleShowMap(item.latitude, item.longitude)}><FaMapMarkerAlt size={35}/></span>
  }]);
  const [cityMap,setCityMap] = useState({latitude:"",longitude:""})

  function handleShowMap(latitude, longitude){
    setCityMap({latitude:latitude,longitude:longitude});
  }



  useEffect(() => {
    dispatch(getCountry(id.id))
    dispatch(citiesOfCountry(id.id))
  }, [])

    return( 
        <Container style={{marginTop:"40px",marginBottom:"40px"}}>
        {country && !Array.isArray(country)
        ?
          <Row>
          <Col sm={4}>
          <h3 style={{marginBottom:"20px"}}>Country {id.id} Information:</h3>
            {Object.entries(country).map((each) =>(
            <React.Fragment key= {each}  >
            <ListGroup as="ul" variant="flush" >
              <ListGroup.Item  as="h6" variant="info">{each[0]}:{each[1]}</ListGroup.Item>
            </ListGroup>
            </React.Fragment>
            ))}
            </Col>
            <Col sm={6}>
            <h3 style={{marginBottom:"20px"}}>Cities Information:</h3>
            <GenericTable headers={tableCities} data={cities} />
            </Col>
        </Row>
        :"Nothing to show"}
        {cityMap.latitude && cityMap.longitude?
          <Row>
            <h3>City Map:</h3>
            <iframe src={"http://maps.google.com/maps?q="+cityMap.latitude+","+cityMap.longitude+"&z=16&output=embed"} height="450" width="600"></iframe>
        </Row>:""   }
        </Container>
    
    );
}

export default ViewDetailCountry;