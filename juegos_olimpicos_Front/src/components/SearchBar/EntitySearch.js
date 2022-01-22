import React,{useState} from "react";
import { Button, FloatingLabel, Form, InputGroup,
  Container, Col, Row } from "react-bootstrap";
  import {FaSearch} from 'react-icons/fa';

const EntitySearch = (props) =>{

  const [filter,setFilter] = useState(props.filters[0])
  const [content,setContent] = useState(null)

  const handleChangeFilter=(value)=>{
    setFilter(value);
  }

  const handleChangeContent=(value)=>{
    setContent(value);
  }

  const handleSubmit=()=>{
    props.search({[filter]:content},props.offset,props.limit,
    props.sortOrder,props.sortProperty);
  }
  return(
    <Container>
    <InputGroup>
    <Row>
    <Col sm="4">
    <FloatingLabel controlId="floatingSelect" label="Filter">
      <Form.Select aria-label="Floating label select example"
      onChange={(event) => handleChangeFilter(event.target.value)}>
        {props.filters.map((each)=>
        <option key={each} value={each}>{each}</option>
        )}
      </Form.Select>
    </FloatingLabel>
    </Col>
    <Col sm="6">
      <Form.Control
      type="text"
      placeholder="search for ..."
      required
      onKeyUp={() => handleSubmit()}
      onChange={(event) => handleChangeContent(event.target.value)}
      />
      </Col>
      <Col>
      <Button onClick={() => handleSubmit()}><FaSearch color="white"/></Button>
      </Col>
      </Row>
    </InputGroup>
    </Container>
  );
}
export default EntitySearch