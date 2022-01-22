import React from "react";
import { Table,Container } from "react-bootstrap";
import GenericRow from "../Rows/GenericRow";


const GenericTable = (props) =>{

  return(
    <Container>
      <Table striped bordered hover responsive>
        <thead>
          <tr>
            {props.headers.map((each) =>
            <th key={each.name}>
              {each.name}
            </th>)}
          </tr>
        </thead>
        <tbody>
          {props.data.map((each,index) => (
            <GenericRow key={index} headers={props.headers} data={each} />
          ))}
        </tbody>
      </Table>
    </Container>
  )

}
export default GenericTable