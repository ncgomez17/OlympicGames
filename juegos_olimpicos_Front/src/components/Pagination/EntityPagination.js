import React,{useState} from "react";
import { Pagination } from "react-bootstrap";

const EntityPagination = (props) =>{
  const [currentPage, setCurrentPage] = useState(0);
  

  const handleEvents =(index)=>{
    setCurrentPage(index);
    searchPage(index);
  }

  const handlePrevPage =()=>{
    if(!props.paged.first){
      if(props.filter == false){
        props.search(props.paged.offset+1 ,props.paged.limit,'asc','id')
      }
      else{
    props.search({},props.paged.offset-1 ,props.paged.limit,'asc','id')
    }
    setCurrentPage(currentPage-1);
  }
}

  const handleNextPage =()=>{
    if(!props.paged.last){ 
      if(props.filter == false){
        props.search(props.paged.offset+1 ,props.paged.limit,'asc','id')
      }
      else{
      props.search({},props.paged.offset+1 ,props.paged.limit,'asc','id')
      }
      setCurrentPage(currentPage+1);
  }
  }
  const handleFinalPage =()=>{
      if(props.filter == false){
        props.search(props.paged.totalPages-1, props.paged.limit,'asc','id')
      }
      else{
      props.search({},props.paged.totalPages-1, props.paged.limit,'asc','id')
      }
  }
  const handleFirstPage =()=>{
      if(props.filter == false){
        props.search(0,props.paged.limit,'asc','id')
      }
      else{
      props.search({},0,props.paged.limit,'asc','id')
      }
  }
  const searchPage =(index)=>{
    if(props.filter == false){
      props.search(index,props.paged.limit,'asc','id')
    }
    else{
    props.search({},index,props.paged.limit,'asc','id')
    }
  }
  let cont= 0;
  return(
    <div style={{ display: "flex", justifyContent: "center" }}>
    {props.paged && !props.paged.empty?
    <Pagination>
      <Pagination.First onClick={()=>handleFirstPage()}/>
      <Pagination.Prev onClick={()=>handlePrevPage()} />
      {[...Array(props.paged.totalPages)].map((key, index) =>
      cont++<5?
        <Pagination.Item  active={currentPage==index}onClick={() => handleEvents(index)}  key={{key}+index}>{index}</Pagination.Item>
        :null
      )}
      <Pagination.Next onClick={()=>handleNextPage()}/>
      <Pagination.Last onClick={()=>handleFinalPage()} />
    </Pagination>
    :""}
  </div>
  
  );
}
export default EntityPagination;