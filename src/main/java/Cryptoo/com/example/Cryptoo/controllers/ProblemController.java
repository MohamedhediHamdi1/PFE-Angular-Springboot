package Cryptoo.com.example.Cryptoo.controllers;

import Cryptoo.com.example.Cryptoo.requests.ProblemRequest;
import Cryptoo.com.example.Cryptoo.responses.ProblemResponse;
import Cryptoo.com.example.Cryptoo.services.ProblemService;
import Cryptoo.com.example.Cryptoo.shared.dto.ProblemDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    ProblemService problemService;


    @PostMapping
    public ResponseEntity<ProblemResponse> createproblem(@Valid @RequestBody ProblemRequest problemRequest) throws Exception{

        //UserDto userDto = new UserDto();
        System.out.println("create problem");
        ModelMapper modelMapper = new ModelMapper();
        ProblemDto problemDto = modelMapper.map(problemRequest, ProblemDto.class);


        //BeanUtils.copyProperties(userRequest, userDto);

        ProblemDto createproblem = problemService.createproblem(problemDto);

        ProblemResponse problemResponse = modelMapper.map(createproblem, ProblemResponse.class);

        return new ResponseEntity<ProblemResponse>( problemResponse,HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteProblem(@PathVariable String id) {
        problemService.deleteProblem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
