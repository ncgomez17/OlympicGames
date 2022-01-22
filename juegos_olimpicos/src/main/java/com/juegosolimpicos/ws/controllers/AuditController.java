package com.juegosolimpicos.ws.controllers;

import com.juegosolimpicos.exceptions.BaseException;
import com.juegosolimpicos.openapi.api.AuditApi;
import com.juegosolimpicos.openapi.model.AuditDto;
import com.juegosolimpicos.openapi.model.FindAuditRequestDto;
import com.juegosolimpicos.openapi.model.FindAuditResponseDto;
import com.juegosolimpicos.services.IAuditService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuditController implements AuditApi {

    @Autowired
    private IAuditService auditService;

    @Override
    public ResponseEntity<List<AuditDto>> getAudits(){
        final List<AuditDto> response = this.auditService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FindAuditResponseDto> findAuditList(FindAuditRequestDto filter, Integer offsetParam,
                                                              Integer limit, String sortDirection, String sortProperty) throws BaseException {
        final FindAuditResponseDto response = this.auditService.findAuditList(filter, offsetParam, limit, sortDirection, sortProperty);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> diff(String id) throws BaseException {
        Object map =auditService.diff(new ObjectId(id));
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
}
