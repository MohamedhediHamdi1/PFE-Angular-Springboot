package Cryptoo.com.example.Cryptoo.controllers;

import Cryptoo.com.example.Cryptoo.entities.HomeUiEntity;
import Cryptoo.com.example.Cryptoo.repositories.HomeUiRepository;
import Cryptoo.com.example.Cryptoo.responses.HomeUiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("homeui")
public class HomeUiController {

    @Autowired
    HomeUiRepository homeUiRepository;

    @PostMapping
    public ResponseEntity<HomeUiResponse> getHome(){
        ModelMapper modelMapper = new ModelMapper();
        HomeUiEntity homeUiEntity=homeUiRepository.findById(1L);
        HomeUiResponse home = modelMapper.map(homeUiEntity, HomeUiResponse.class);
        return new ResponseEntity<HomeUiResponse>(home, HttpStatus.OK);
    }

    @PostMapping
    @RequestMapping("/update")
    public ResponseEntity<HomeUiEntity> updtae(@RequestBody HomeUiResponse message){
        HomeUiEntity homeUiEntity=homeUiRepository.findById(1L);
        if (homeUiEntity==null){
            HomeUiEntity home=new HomeUiEntity();
            home.setId(1L);
            home.setTitle1(message.getTitle1());
            home.setTitle2(message.getTitle2());
            home.setTitle3(message.getTitle3());
            home.setTitle4(message.getTitle4());
            home.setDesc1(message.getDesc1());
            home.setDesc2(message.getDesc2());
            home.setDesc3(message.getDesc3());
            home.setDesc4(message.getDesc4());
            homeUiRepository.save(home);
            return new ResponseEntity<HomeUiEntity>(home,HttpStatus.OK);
        }else{
            homeUiEntity.setTitle1(message.getTitle1());
            homeUiEntity.setTitle2(message.getTitle2());
            homeUiEntity.setTitle3(message.getTitle3());
            homeUiEntity.setTitle4(message.getTitle4());
            homeUiEntity.setDesc1(message.getDesc1());
            homeUiEntity.setDesc2(message.getDesc2());
            homeUiEntity.setDesc3(message.getDesc3());
            homeUiEntity.setDesc4(message.getDesc4());
            homeUiRepository.save(homeUiEntity);
            return new ResponseEntity<HomeUiEntity>(homeUiEntity,HttpStatus.OK);
        }
    }


}
