import React from "react";
import { Modal, Button } from 'react-bootstrap';
import { useDispatch } from 'react-redux';
import { deleteHeadquarter } from '../../actions';

const ModalDeleteHeadquarter = (props) =>{

  const dispatch = useDispatch();

  function handleDelete(year, typeId){
    dispatch(deleteHeadquarter(year, typeId));
    props.hideModal()
  }

  return(
      <Modal show={props.isShowing} onHide={() => props.hideModal()}>
        <Modal.Header closeButton>
          <Modal.Title>Delete a Headquarter</Modal.Title>
        </Modal.Header>
        <Modal.Body><p>The headquarter will be deleted</p></Modal.Body>
        <Modal.Footer>
          <Button onClick={() => handleDelete(props.headquarter.id.year,props.headquarter.id.type.id)}>Accept</Button>
          <Button onClick={() => props.hideModal()}>Cancel</Button>
        </Modal.Footer>
      </Modal>  
  );
}

export default ModalDeleteHeadquarter;