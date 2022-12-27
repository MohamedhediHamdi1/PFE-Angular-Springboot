package Cryptoo.com.example.Cryptoo.controllers;

import Cryptoo.com.example.Cryptoo.entities.HistoryEntity;
import Cryptoo.com.example.Cryptoo.repositories.HistoryRepository;
import Cryptoo.com.example.Cryptoo.requests.HistoryRequest;
import Cryptoo.com.example.Cryptoo.responses.HistoryResponse;
import Cryptoo.com.example.Cryptoo.services.impl.HistoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    HistoryRepository historyRepository;

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody HistoryRequest historyRequest){
        HistoryEntity historyEntity= historyRepository.findById(id).get();
        HistoryEntity historyEntity1= HistoryService.historyupdate(historyRequest.getPair1(),historyRequest.getEntryprice1(),historyRequest.getCloseprice1(),historyRequest.getEntrytime1(),historyRequest.getClosetime1(),historyRequest.getPnl1(),historyRequest.getQuantity1(),historyRequest.getBuy_sell1(),historyEntity);
        historyRepository.save(historyEntity1);
        return new ResponseEntity<String>("Updated", HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<HistoryResponse> gethistory(@PathVariable Long id){
        HistoryResponse historyResponse=new HistoryResponse();
        HistoryEntity historyEntity= historyRepository.findById(id).get();
        BeanUtils.copyProperties(historyEntity,historyResponse);
        return new ResponseEntity<HistoryResponse>(historyResponse,HttpStatus.OK);
    }


}
