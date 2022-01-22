import React,{useState} from "react";
import { useHistory } from "react-router";
import { Col,Button  } from "react-bootstrap";
import axios from "axios";
import api from "../../api";

const SignInForm = () => {

  const history = useHistory();

  const [nickName, setNickName] = useState("")
  const [password, setPassword] = useState("");

  function onSubmit(e){
    e.preventDefault()
    axios.post(
      `${api.baseRestUrl}/user/authenticate`,
      {
        nickName,
        password,
        "role":["USER"]
      }
      ).then(data =>{
        sessionStorage.setItem("token",data.data.token)
        if(data.data.isOk){
          history.push(`/`)
        }else{
          alert("Contraseña Incorrecta");
        }}).catch(err => alert(err.response.data.message))
  }

  return (
    <>
    <form onSubmit={(e) => onSubmit(e)}>
      <h4 className="text-center">Sign In</h4>
      <Col md={{ span: 6, offset: 3 }}>
      <div className="form-group row">
        <label className="">Username</label>
        <input
          type="user"
          className="form-control"
          placeholder="Enter username"
          onChange={() => setNickName(event.target.value)}
        />
      </div>
      </Col>
      <Col md={{ span: 6, offset: 3 }}>
      <div className="form-group row">
        <label>Password</label>
        <input
          type="password"
          className="form-control"
          placeholder="Enter password"
          onChange={() => setPassword(event.target.value)}
        />
      </div>
      </Col>
      <br></br>
      <Col md={{ span: 6, offset: 3 }}>
      <Button type="submit" variant="primary">
        Login
      </Button>
      <p className="forgot-password text-right">
        Forgot <a href="#"onClick={()=>history.push(`/register/`)}>password?</a>
      </p>
      </Col>
    </form>
    </>
  );
};
export default SignInForm;
