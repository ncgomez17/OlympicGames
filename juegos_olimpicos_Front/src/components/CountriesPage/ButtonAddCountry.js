import React, {useState}  from "react";
import { Button } from 'react-bootstrap';
import ModalAddCountry from './ModalAddCountry';


const ButtonAddCountry = () =>{

  const [showModal, setShowModal] = useState(false)


  function handleOnClick(){
    setShowModal(true);
  }

  function handleHideModal(){
    setShowModal(false);
  }

  return(
    <div className="alineacion-derecha margin-bottom-1">
      <Button variant="primary" onClick={() => handleOnClick()}>Add Country</Button>
      <ModalAddCountry isShowing = {showModal } hideModal={ () => handleHideModal()}/>
    </div>
  );

}

export default ButtonAddCountry;