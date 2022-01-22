import React, {useState, useEffect} from "react";
import { Navbar, Nav, Container } from "react-bootstrap";
import  { withRouter } from "react-router-dom";
import * as Icon from 'react-bootstrap-icons'
import axios from "axios";
import api from "../../api"

const TopNavBar = withRouter(props =>{

  const [isAdmin, setIsAdmin] = useState(false)

  const handleOnClickInicio = () => {
    props.history.push(`/`)
  }

  const handleOnClickOlympicGames = () => {
    props.history.push(`/olympicGames`)
  }

  const handleOnClickCountries = () => {
    props.history.push(`/countries`)
  }

  const handleOnClickCities = () => {
    props.history.push(`/cities`)
  }

  const handleOnClickHeadquarters = () => {
    props.history.push(`/headquarters`)
  }

  const handleOnClickTypesJjoo = () => {
    props.history.push(`/typesJjoo`)
  }

  const handleOnClickAudits = () => {
    props.history.push(`/audits`)
  }
  useEffect(() =>{
    axios.get(
      `${api.baseRestUrl}/user/isAdmin`,
      ).then(data =>{
          setIsAdmin(data.data);
        })
  },[sessionStorage.getItem('token')])



  return(
    <Navbar expand="lg" bg="light" variant="light" hidden={!sessionStorage.getItem('token')}>
      <Container>
          <Navbar.Brand >
            <Icon.Box alt=""
              width="30"
              height="30"
              className="d-inline-block align-top"/>
          Menu
          </Navbar.Brand>
          <Navbar.Toggle aria-controls="responsive-navbar-nav" />
          <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="me-auto">
              <Nav.Link onClick={() => handleOnClickInicio()}>Home</Nav.Link>
              <Nav.Link onClick={() => handleOnClickOlympicGames()}>OlympicGames</Nav.Link>
              <Nav.Link onClick={() => handleOnClickCountries()}>Countries</Nav.Link>
              <Nav.Link onClick={() => handleOnClickCities()}>Cities</Nav.Link>
              <Nav.Link onClick={() => handleOnClickHeadquarters()}>Headquarters</Nav.Link>
              <Nav.Link onClick={() => handleOnClickTypesJjoo()}>TypesJjoo</Nav.Link>
              {isAdmin && <Nav.Link  onClick={() => handleOnClickAudits()}>Audit</Nav.Link>}
          </Nav>
          </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}
)
export default TopNavBar
