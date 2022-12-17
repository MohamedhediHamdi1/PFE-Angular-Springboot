package Cryptoo.com.example.Cryptoo.controllers;

import Cryptoo.com.example.Cryptoo.entities.PnlEntity;
import Cryptoo.com.example.Cryptoo.repositories.PnlRepository;
import Cryptoo.com.example.Cryptoo.requests.PnlRequest;
import Cryptoo.com.example.Cryptoo.responses.PnlResponse;
import Cryptoo.com.example.Cryptoo.services.impl.Pnl_service;
import Cryptoo.com.example.Cryptoo.shared.dto.PnlDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pnll")
public class PnlController {

    @Autowired
    Pnl_service pnl_service;

    @Autowired
    PnlRepository pnlRepository;

    @GetMapping(path = "/{id}")
    public ResponseEntity<PnlResponse> getPnl(@PathVariable String id){
        PnlDto pnlDto = pnl_service.getPnl(id);
        PnlResponse pnlResponse =new PnlResponse();
        BeanUtils.copyProperties(pnlDto,pnlResponse);
        return new ResponseEntity<PnlResponse>( pnlResponse, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> update(@PathVariable String id,@RequestBody PnlRequest pnlRequest){
        PnlDto pnlDto=new PnlDto();
        BeanUtils.copyProperties(pnlRequest,pnlDto);
        PnlEntity pnlEntity=pnlRepository.findByPnlid(id);
        pnlEntity.setDay7(pnlEntity.getDay7()+pnlDto.getDay7());
        pnlRepository.save(pnlEntity);
        return new ResponseEntity<String>("done",HttpStatus.OK);
    }
}
