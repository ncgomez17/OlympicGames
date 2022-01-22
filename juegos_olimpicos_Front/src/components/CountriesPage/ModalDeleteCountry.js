import React from "react";
import { Modal, Button } from 'react-bootstrap';
import { useDispatch } from 'react-redux';
import { deleteCountry } from '../../actions';

const ModalDeleteCountry = (props) =>{

  const dispatch = useDispatch();

  function handleDelete(id){
    dispatch(deleteCountry(id));
    props.hideModal()
  }

  return(
      <Modal show={props.isShowing} onHide={() => props.hideModal()}>
        <Modal.Header closeButton>
          <Modal.Title>Delete a Country</Modal.Title>
        </Modal.Header>
        <Modal.Body><p>The country with name: {props.country.name} will be deleted</p></Modal.Body>
        <Modal.Footer>
          <Button onClick={() => handleDelete(props.country.id)}>Accept</Button>
          <Button onClick={() => props.hideModal()}>Cancel</Button>
        </Modal.Footer>
      </Modal>  
  );
}

export default ModalDeleteCountry;