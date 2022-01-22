import React, { useState, useEffect } from 'react';
import { Modal, Button, FormGroup, FormLabel, FormControl, FormText, Form } from 'react-bootstrap';
import { useDispatch } from 'react-redux';
import { addHeadquarter, updateHeadquarter } from '../../actions';


const ModalAddHeadquarter = (props) =>{

  const [isNewYearClean, setIsNewYearClean] = useState(true)
  const [newYear, setNewYear] = useState("")
  const [newCity, setNewCity] = useState(1)
  const [newType, setNewType] = useState(1)

  const dispatch = useDispatch()

  function handleUpdate(year, city, type){
    dispatch(updateHeadquarter(year, city, type))
  }

  function handleAdd(year,city, type){
    dispatch(addHeadquarter(year, city, type))
  }

  function getValidationStateYear() {
    if(newYear.length >0) return 'success';
    else if(!isNewYearClean) return 'error';
    return null;
  }

  function handleChangeYear(year){
    setNewYear(year)
    setIsNewYearClean(false)
  }

  function handleChangeCity(city){
    setNewCity(city)
  }

  function handleChangeType(type){
    setNewType(type)
  }


  function handleSubmit() {
    console.log(newCity)
    console.log(newYear)
    console.log(newType)
      props.headquarter?
      handleUpdate(newYear, {id:Number(newCity)}, {id:Number(newType)})
      :    
      handleAdd(newYear, {id:Number(newCity)}, {id:Number(newType)})
      
      props.hideModal()
  }

  useEffect(() => {
    if(props.headquarter){
      setNewYear(props.headquarter.id.year)
      setNewCity(props.headquarter.city.id)
      setNewType(props.headquarter.id.type.id)
    }

  },[props.headquarter]);

  return(
  <Modal show={props.isShowing} onHide={() => props.hideModal()}>
    <Modal.Header closeButton>
    {props.headquarter?
    <Modal.Title>Editing Headquarter</Modal.Title>
    :
    <Modal.Title>Adding a new Headquarter</Modal.Title>}
    </Modal.Header>

    <Modal.Body>
        <form>
            <FormGroup
                validation={getValidationStateYear()}
                >
                    <FormLabel>Year</FormLabel>
                    <FormControl
                        type="number"
                        value={newYear}
                        placeholder="year"
                        onChange={(event) => handleChangeYear(event.target.value)}
                        />
                        {getValidationStateYear() === 'error' &&
                            <FormText>The field can not be empty</FormText>
                            }
                            <FormControl.Feedback />
                </FormGroup>
                <FormGroup>
                    <FormLabel>City</FormLabel>
                    <Form.Select 
                        onChange={(event) => handleChangeCity(event.target.value)}
                        >{props.headquarter?<option key={props.headquarter.city.id} value={props.headquarter.city.id}>
                        {props.headquarter.city.name}</option>:""}
                        {
                        props.cities.map((each) =>(
                        <option key={each.id} value={each.id}>{each.name}</option>
                        ))}
                        </Form.Select> 
                            <FormControl.Feedback />
                </FormGroup>
                <FormGroup>
                    <FormLabel>Type</FormLabel>
                    <Form.Select 
                        onChange={(event) => handleChangeType(event.target.value)}
                        >{props.headquarter?<option key={props.headquarter.id.type.id} value={props.headquarter.id.type.id}>
                        {props.headquarter.id.type.description}</option>:""}
                        {
                        props.types.map((each) =>(
                        <option key={each.id} value={each.id}>{each.description}</option>
                        ))}
                        </Form.Select> 
                            <FormControl.Feedback />
                </FormGroup>
        </form>
    </Modal.Body>
    <Modal.Footer>
        <Button onClick={() => props.hideModal()}>Cancel</Button>
        <Button variant="primary" onClick={() => handleSubmit()}>Save</Button>
    </Modal.Footer>
  </Modal>
  );
}

export default ModalAddHeadquarter;