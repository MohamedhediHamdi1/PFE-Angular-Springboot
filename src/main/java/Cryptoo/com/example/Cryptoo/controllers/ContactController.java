package Cryptoo.com.example.Cryptoo.controllers;


import Cryptoo.com.example.Cryptoo.requests.ContactRequest;
import Cryptoo.com.example.Cryptoo.responses.ContactRsponse;
import Cryptoo.com.example.Cryptoo.services.ContactService;
import Cryptoo.com.example.Cryptoo.shared.dto.ContactDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/contacts")
public class ContactController {


    @Autowired
    ContactService contactService;

    @GetMapping
    public String message(){
        return "done";
    }

    @PutMapping("/wallet/{id}")
    public ResponseEntity<ContactRsponse> updateWallet(@PathVariable(name="id") String contactId,@RequestBody ContactRequest contactRequest) {
        ContactDto contactDto = new ContactDto();
        BeanUtils.copyProperties(contactRequest, contactDto);
        ContactDto updateWallet = contactService.updateWallet(contactId,contactDto);
        ContactRsponse contactRsponse = new ContactRsponse();
        BeanUtils.copyProperties(updateWallet,contactRsponse);


        return new ResponseEntity<ContactRsponse>(contactRsponse, HttpStatus.ACCEPTED);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<ContactRsponse> getContact(@PathVariable String id){
        ContactDto contactDto = contactService.getContact(id);
        ContactRsponse contactRsponse =new ContactRsponse();
        BeanUtils.copyProperties(contactDto,contactRsponse);
        return new ResponseEntity<ContactRsponse>( contactRsponse,HttpStatus.OK);
    }
}
