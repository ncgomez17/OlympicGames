import React, {useState}  from "react";
import { Button } from 'react-bootstrap';
import ModalAddCity from './ModalAddCity';
import {  useDispatch } from 'react-redux';
import { searchCountriesOfCity} from '../../actions';

const ButtonAddCity = (props) =>{
  const [showModal, setShowModal] = useState(false)
  const dispatch = useDispatch();

  function handleOnClick(){
    dispatch(searchCountriesOfCity())
    setShowModal(true);
  }

  function handleHideModal(){
    setShowModal(false);
  }

  return(
    <div className="alineacion-derecha margin-bottom-1">
      <Button variant="primary" onClick={() => handleOnClick()}>Add City</Button>
      <ModalAddCity isShowing = {showModal } hideModal={ () => handleHideModal()} countries={props.countries}/>
    </div>
  );

}

export default ButtonAddCity;