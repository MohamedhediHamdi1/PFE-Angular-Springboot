package Cryptoo.com.example.Cryptoo.controllers;


import Cryptoo.com.example.Cryptoo.requests.SimpleRequest;
import Cryptoo.com.example.Cryptoo.services.impl.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    LogsService logsService;

    @PostMapping ("/login")
        public ResponseEntity login(@RequestBody SimpleRequest request){
        if(request.getRequest().equals("admin1")){
            if(request.getResponse().equals("6yeo4_HI&zoY8x')B-#h~y&n@4")){
                logsService.addToLogs("Admin1 logged in successfully.");
                return ResponseEntity.ok().build();
            }
        }else if(request.getRequest().equals("admin2")){
            if(request.getResponse().equals("eb$bQy{~ibi{d.uTQ$tU=[1tiv")){
                logsService.addToLogs("Admin2 logged in successfully.");
                return ResponseEntity.ok().build();
            }
        }else if(request.getRequest().equals("admin3")){
            if(request.getResponse().equals("[WM%q))Qu[)Nh].Z'=}=yACs)B")){
                logsService.addToLogs("Admin3 logged in successfully.");
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

}
