import React from "react";

const GenericRow = ({headers, data}) =>{

  return(
    <tr>
      {headers.map((each) =>(
        <td className="clickable"  key={each.property}>{each.property(data)}</td>
      ))}
    </tr>
  );

}
export default GenericRow;