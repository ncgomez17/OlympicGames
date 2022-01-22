import React from 'react';
import { useHistory } from "react-router";
const HomePage = () =>{

  const history = useHistory();

  function handleLogin(){
    history.push(`/login`)
  }
  return(
    <div>
    {sessionStorage.getItem('token')?
    <>
      <h1>Inicio</h1>
      <p>Bienvenid@ al ejercicio de React/Redux de Qindel.</p>
    </>
    :
    handleLogin()
    }
    </div>
  );
}
export default HomePage