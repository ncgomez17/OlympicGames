import React, { useState, useEffect } from 'react';
import { Modal, Button, FormGroup, FormLabel, FormControl, FormText, Form } from 'react-bootstrap';
import { useDispatch } from 'react-redux';
import { addCity, updateCity } from '../../actions';


const ModalAddCity = (props) =>{

  const [isNewNameClean, setIsNewNameClean] = useState(true)
  const [isNewValueClean, setIsNewValueClean] = useState(true)
  const [newName, setNewName] = useState("")
  const [newCountry, setNewCountry] = useState(1)
  const [newValue, setNewValue] = useState('')
  const [newLatitude, setNewLatitude] = useState('')
  const [newLongitude, setNewLongitude] = useState('')

  const dispatch = useDispatch()

  function handleUpdate(id, name, country, value, latitude, longitude){
    dispatch(updateCity(id, name, country, value, latitude, longitude))
  }

  function handleAdd(name, country, value, latitude, longitude){
    dispatch(addCity(name, country, value, latitude, longitude))
  }

  function getValidationStateName() {
    if(newName.length >0) return 'success';
    else if(!isNewNameClean) return 'error';
    return null;
  }


  function getValidationStateValue() {
      if(newValue) return 'success';
      else if(!isNewValueClean) return 'error';
      return null;
  }

  function handleChangeName(nameCity){
    setNewName(nameCity)
    setIsNewNameClean(false)
  }

  function handleChangeCountry(country){
    setNewCountry(country)
  }

  function handleChangeValue(value){
    setNewValue(value)
    setIsNewValueClean(false)
  }

  function handleChangeLatitude(value){
    setNewLatitude(value)
  }

  function handleChangeLongitude(value){
    setNewLongitude(value)
  }

  function handleSubmit() {
      if (getValidationStateName() === 'success' 
      && getValidationStateValue() === 'success'){
          props.city?handleUpdate(props.city.id, newName, {id:Number(newCountry)}, newValue, newLatitude, newLongitude)
          :    
          handleAdd(newName, {id:Number(newCountry)}, newValue, newLatitude, newLongitude)
      }
      props.hideModal()
  }

  useEffect(() => {
    if(props.city){
      setNewName(props.city.name)
      setNewCountry(props.city.country)
      props.city.value?setNewValue(props.city.value):setNewValue("")
      props.city.latitude?setNewLatitude(props.city.latitude):setNewLatitude("")
      props.city.longitude?setNewLongitude(props.city.longitude):setNewLongitude("")
    }

  },[props.city]);

  return(
  <Modal show={props.isShowing} onHide={() => props.hideModal()}>
    <Modal.Header closeButton>
    {props.city?
    <Modal.Title>Editing City</Modal.Title>
    :
    <Modal.Title>Adding a new City</Modal.Title>}
    </Modal.Header>

    <Modal.Body>
        <form>
            <FormGroup
                validation={getValidationStateName()}
                >
                    <FormLabel>Name</FormLabel>
                    <FormControl
                        type="text"
                        value={newName}
                        placeholder="name"
                        onChange={(event) => handleChangeName(event.target.value)}
                        />
                        {getValidationStateName() === 'error' &&
                            <FormText>The field can not be empty</FormText>
                            }
                            <FormControl.Feedback />
                </FormGroup>
                <FormGroup>
                    <FormLabel>Country</FormLabel>
                    <Form.Select 
                        onChange={(event) => handleChangeCountry(event.target.value)}
                        >{props.city?<option key={props.city.country.id} value={props.city.country.id}>{props.city.country.name}</option>:""}
                        {props.countries.map((each) =>(
                        <option key={each.id} value={each.id}>{each.name}</option>
                        ))}
                        </Form.Select> 
                            <FormControl.Feedback />
                </FormGroup>
                <FormGroup
                validation={getValidationStateValue()}
                >
                    <FormLabel>Value</FormLabel>
                    <FormControl
                        type="number"
                        value={newValue}
                        placeholder="value"
                        onChange={(event) => handleChangeValue(event.target.value)}
                        />
                        {getValidationStateValue() === 'error' &&
                            <FormText>The field can not be empty</FormText>
                            }
                            <FormControl.Feedback />
                </FormGroup>
                <FormGroup>
                    <FormLabel>Latitude</FormLabel>
                    <FormControl
                        type="number"
                        value={newLatitude}
                        placeholder="Latitude"
                        onChange={(event) => handleChangeLatitude(event.target.value)}
                        />
                </FormGroup>
                <FormGroup>
                    <FormLabel>Longitude</FormLabel>
                    <FormControl
                        type="number"
                        value={newLongitude}
                        placeholder="Longitude"
                        onChange={(event) => handleChangeLongitude(event.target.value)}
                        />
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

export default ModalAddCity;