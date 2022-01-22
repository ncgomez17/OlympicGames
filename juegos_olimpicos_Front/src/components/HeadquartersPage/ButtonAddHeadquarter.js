import React, {useState}  from "react";
import { Button } from 'react-bootstrap';
import ModalAddHeadquarter from './ModalAddHeadquarter';
import { useDispatch } from 'react-redux';
import { searchCitiesOfHeadquarter} from '../../actions';


const ButtonAddHeadquarter = (props) =>{

  const [showModal, setShowModal] = useState(false)
  const dispatch = useDispatch();

  function handleOnClick(){
    dispatch(searchCitiesOfHeadquarter())
    setShowModal(true);
  }

  function handleHideModal(){
    setShowModal(false);
  }

  return(
    <div className="alineacion-derecha margin-bottom-1">
      <Button variant="primary" onClick={() => handleOnClick()}>Add Headquarter</Button>
      <ModalAddHeadquarter isShowing = {showModal } hideModal={ () => handleHideModal()} cities={props.cities}
        types={props.types}
      />
    </div>
  );

}

export default ButtonAddHeadquarter;