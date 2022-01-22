import React from "react";
import * as Icon from 'react-bootstrap-icons';

const Footer = () =>{
    return(
<div className="text-center position-relative ">
    <footer className=" bg-dark justify-content-center"  style={{paddingTop:"20px",margin:0}}>
        <div className="container text-center">
            <ul className="nav navbar-nav list-inline d-inline">
              <li className=" list-inline-item mr-3"><a className="text-white text-decoration-none" href="#/">Terms & Conditions</a></li>
              <li className=" list-inline-item mr-3"><a className="text-white text-decoration-none" href="#/">Privacy</a></li>
              <li className=" list-inline-item mr-3"><a className="text-white text-decoration-none" href="#/">Cookies</a></li>
            </ul>
            <div className="row" style={{marginTop:"20px"}}>
            <ul className="d-inline list-inline">
              <li className="list-inline-item" ><a href="https://github.com/ncgomez17"><Icon.Github color="white" size={30}/></a></li>
              <li className="list-inline-item"><a href="https://www.instagram.com/nico_9cid/"><Icon.Instagram color="purple" size={30}/></a></li>
              <li className="list-inline-item"><a href="https://twitter.com/niquinho_cid"><Icon.Twitter color="lightblue" size={30}/></a></li>
            </ul>
            </div>
            <div className="mr-3 text-white">
                Copyright Nicolás Cid Gómez © 2021
            </div>
        </div>
    </footer>
</div>
    );
}

export default Footer;