import React,{useState} from 'react';
import { useHistory } from 'react-router';
import { Col } from "react-bootstrap";
import axios from "axios";
import api from "../../api"

const SignUpForm = () => {
  const history = useHistory()

  const [nickName, setNickName] = useState("")
  const [password, setPassword] = useState("");
  const [secondPassword, setSecondPassword] = useState("");

  function onSubmit(){
    if(password === secondPassword){
      axios.post(
        `${api.baseRestUrl}/user/register`,
        {
          nickName,
          password,
          "role":["USER"]
        }
        ).then(data =>{
          sessionStorage.setItem("token",data.data.token)
          if(data.data.isOk){
            history.push(`/`)
          }}).catch(err => alert(err.response.data.message))
    }else{
      alert("Las contraseñas no coinciden")
    }
  }

return(
    <form>
    <h4 className="text-center">Sign Up</h4>
    <Col md={{ span: 6, offset: 3 }}>
    <div className="form-group">
        <label>User</label>
        <input type="text"
        className="form-control"
        placeholder="username"
        onChange={() => setNickName(event.target.value)} />
    </div>
    </Col>
    <Col md={{ span: 6, offset: 3 }}>
    <div className="form-group">
        <label>Password</label>
        <input type="password"
        className="form-control"
        placeholder="Password"
        onChange={() => setPassword(event.target.value)}/>
    </div>
    </Col>
    <Col md={{ span: 6, offset: 3 }}>
    <div className="form-group">
        <label>Repeat password</label>
        <input type="password"
        className="form-control"
        placeholder="Repeat password"
        onChange={() => setSecondPassword(event.target.value)}/>
    </div>
    </Col>
    <br></br>
    <Col md={{ span: 6, offset: 3 }}>
    <button type="button" onClick={() => onSubmit()} className="btn btn-primary btn-block">Sign Up</button>
    <p className="forgot-password text-right">
        Already registered <a href="#"onClick={()=>history.push(`/login/`)}>sign in?</a>
    </p>
    </Col>
</form>
  );
}
export default SignUpForm