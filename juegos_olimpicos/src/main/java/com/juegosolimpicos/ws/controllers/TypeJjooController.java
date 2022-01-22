package com.juegosolimpicos.ws.controllers;

import com.juegosolimpicos.openapi.api.TypeJjooApi;
import com.juegosolimpicos.openapi.model.TypeJjooDto;
import com.juegosolimpicos.services.ITypeJjooService;
import com.juegosolimpicos.exceptions.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
public class TypeJjooController implements TypeJjooApi {

    @Autowired
    private ITypeJjooService typeJjooService;

    @Override
    public ResponseEntity<List<TypeJjooDto>> getTypesJjoo(){
        final List<TypeJjooDto> response = this.typeJjooService.getTypesJjoo();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TypeJjooDto> getTypeJjooById(@Min(1) @Valid final Integer typeJjooId) throws BaseException{
        final TypeJjooDto response = this.typeJjooService.getById(typeJjooId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteTypeJjoo(@Min(1) @Valid final Integer typeJjooId) throws BaseException {
        this.typeJjooService.deleteById(typeJjooId);
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
