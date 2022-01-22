import React, { useState, useEffect } from 'react';
import { Modal, Button, FormGroup, FormLabel, FormControl, FormText } from 'react-bootstrap';
import { useDispatch } from 'react-redux';
import { addCountry, updateCountry } from '../../actions';


const ModalAddCountry = (props) =>{

  const [isNewNameClean, setIsNewNameClean] = useState(true)
  const [isNewCodeClean, setIsNewCodeClean] = useState(true)
  const [isNewValueClean, setIsNewValueClean] = useState(true)
  const [newName, setNewName] = useState("")
  const [newCode, setNewCode] = useState("")
  const [newValue, setNewValue] = useState("")

  const dispatch = useDispatch()

  function handleUpdate(id, name, code, value){
    dispatch(updateCountry(id, name, code, value))
  }

  function handleAdd(name, code, value){
    dispatch(addCountry(name, code, value))
  }

  function getValidationStateName() {
    if(newName.length >0) return 'success';
    else if(!isNewNameClean) return 'error';
    return null;
  }

  function getValidationStateCode() {
      if(newCode.length >0) return 'success';
      else if(!isNewCodeClean) return 'error';
      return null;
  }

  function getValidationStateValue() {
      if(newValue) return 'success';
      else if(!isNewValueClean) return 'error';
      return null;
  }

  function handleChangeName(nameCountry){
    setNewName(nameCountry)
    setIsNewNameClean(false)
  }

  function handleChangeCode(code){
    setNewCode(code)
    setIsNewCodeClean(false)
  }

  function handleChangeValue(value){
    setNewValue(value)
    setIsNewValueClean(false)
  }

  function handleSubmit() {
      if (getValidationStateName() === 'success' && getValidationStateCode() ==='success'
      && getValidationStateValue() === 'success'){
          props.country?handleUpdate(props.country.id, newName, newCode, newValue)
          :    
          handleAdd(newName, newCode, newValue)
      }
      props.hideModal()
  }

  useEffect(() => {
    if(props.country){
      setNewName(props.country.name)
      setNewCode(props.country.code)
      setNewValue(props.country.value)
    }

  },[props.country]);

  return(
  <Modal show={props.isShowing} onHide={() => props.hideModal()}>
    <Modal.Header closeButton>
    {props.country?
    <Modal.Title>Editing Country</Modal.Title>
    :
    <Modal.Title>Adding a new Country</Modal.Title>}
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
                <FormGroup
                validation={getValidationStateCode()}
                >
                    <FormLabel>Code</FormLabel>
                    <FormControl
                        type="text"
                        value={newCode}
                        maxLength="2"
                        placeholder="code"
                        onChange={(event) => handleChangeCode(event.target.value)}
                        />
                        {getValidationStateCode() === 'error' &&
                            <FormText>The field can not be empty</FormText>
                            }
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
        </form>
    </Modal.Body>
    <Modal.Footer>
        <Button onClick={() => props.hideModal()}>Cancel</Button>
        <Button variant="primary" onClick={() => handleSubmit()}>Save</Button>
    </Modal.Footer>
  </Modal>
  );
}

export default ModalAddCountry;