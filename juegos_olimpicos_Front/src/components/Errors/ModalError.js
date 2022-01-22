import React from "react";
import { Modal, Button } from "react-bootstrap";


const ModalError = (props) => {

  return(
    <Modal show={props.isShowing} onHide={() => props.hideModal()}>
    <Modal.Header closeButton>
        <Modal.Title>{props.title}</Modal.Title>
    </Modal.Header>

    <Modal.Body>
    {props.error?<h3>Code error:{props.error.response.data.code}</h3>:""}
    {props.error?<p>Description:{props.error.response.data.message}</p>:""}
    </Modal.Body>
    <Modal.Footer>
        <Button onClick={() => props.hideModal()}>Close</Button>
    </Modal.Footer>
</Modal>
  );

}
export default ModalError