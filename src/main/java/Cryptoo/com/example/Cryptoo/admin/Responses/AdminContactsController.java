package Cryptoo.com.example.Cryptoo.admin.Responses;

import Cryptoo.com.example.Cryptoo.entities.ContactEntity;
import Cryptoo.com.example.Cryptoo.entities.ReportEntity;
import Cryptoo.com.example.Cryptoo.repositories.ContactRepository;
import Cryptoo.com.example.Cryptoo.repositories.ReportRepository;
import Cryptoo.com.example.Cryptoo.responses.ContactResponse;
import Cryptoo.com.example.Cryptoo.services.impl.LogsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/admin/contact")
public class AdminContactsController {

    @Autowired
    ContactRepository contactRepository;
    @Autowired
    ReportRepository reportRepository;

    @Autowired
    LogsService logsService;

    @GetMapping("/{type}/{page}")
    public List<ContactResponse> getAll(@PathVariable String type, @PathVariable int page) {
        List<ContactResponse> list = new ArrayList<>();
        Pageable pageRequest = PageRequest.of(page - 1, 20);
        Page<ContactEntity> pageList;
        if (type.equals("guest")) {
            pageList = contactRepository.findcontactsbytype(pageRequest, true);
        } else if (type.equals("users")) {
            pageList = contactRepository.findcontactsbytype(pageRequest, false);
        } else {
            return list;
        }
        for (ContactEntity entity : pageList) {
            ModelMapper modelMapper = new ModelMapper();
            list.add(modelMapper.map(entity, ContactResponse.class));
        }
        logsService.addToLogs("Admin receives server helps.");
        return list;
    }

    @PostMapping("/{contactId}/{admin}")
    public ResponseEntity updateContact(@PathVariable String contactId,@PathVariable String admin){
        ContactEntity entity=contactRepository.findbyids(contactId);
        if(entity==null)
            return ResponseEntity.badRequest().build();
        entity.setAdmin(admin);
        contactRepository.save(entity);
        logsService.addToLogs(admin+" replied to help "+contactId+" .");
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping("/reports/{reportId}/{admin}")
    public ResponseEntity updateReports(@PathVariable String reportId,@PathVariable String admin){
        ReportEntity entity=reportRepository.findByReportId(reportId);
        if(entity==null)
            return ResponseEntity.badRequest().build();
        entity.setAdmin(admin);
        reportRepository.save(entity);
        logsService.addToLogs(admin+" replied to report "+reportId+" .");
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping("/reports/{page}")
    public List<AdminReportResponse> getAllReports(@PathVariable int page) {

        logsService.addToLogs("Admin receives server reports.");

            List<AdminReportResponse> response = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page - 1, 20);
        Page<ReportEntity> pageList=reportRepository.findcontactsbytype(pageableRequest);
        for(ReportEntity entity:pageList){
            ModelMapper modelMapper=new ModelMapper();
            response.add(modelMapper.map(entity,AdminReportResponse.class));
        }
        return response;
    }
}
