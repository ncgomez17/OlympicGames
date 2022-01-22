import React from "react";
import { Modal, Button } from 'react-bootstrap';
import { useDispatch } from 'react-redux';
import { deleteCity } from '../../actions';

const ModalDeleteCity = (props) =>{

  const dispatch = useDispatch();

  function handleDelete(id){
    dispatch(deleteCity(id));
    props.hideModal()
  }

  return(
      <Modal show={props.isShowing} onHide={() => props.hideModal()}>
        <Modal.Header closeButton>
          <Modal.Title>Delete a City</Modal.Title>
        </Modal.Header>
        <Modal.Body><p>The city with name: {props.city.name} will be deleted</p></Modal.Body>
        <Modal.Footer>
          <Button onClick={() => handleDelete(props.city.id)}>Accept</Button>
          <Button onClick={() => props.hideModal()}>Cancel</Button>
        </Modal.Footer>
      </Modal>  
  );
}

export default ModalDeleteCity;