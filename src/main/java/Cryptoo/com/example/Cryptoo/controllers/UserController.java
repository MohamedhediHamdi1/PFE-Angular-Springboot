package Cryptoo.com.example.Cryptoo.controllers;

import Cryptoo.com.example.Cryptoo.entities.ImageEntity;
import Cryptoo.com.example.Cryptoo.entities.UserEntity;
import Cryptoo.com.example.Cryptoo.repositories.ImageMetadataRepository;
import Cryptoo.com.example.Cryptoo.repositories.UserRepository;
import Cryptoo.com.example.Cryptoo.requests.SimpleRequest;
import Cryptoo.com.example.Cryptoo.requests.UserLoginRequest;
import Cryptoo.com.example.Cryptoo.requests.UserRequest;
import Cryptoo.com.example.Cryptoo.responses.ResetPasswordResponse;
import Cryptoo.com.example.Cryptoo.responses.UserResponse;
import Cryptoo.com.example.Cryptoo.services.FileStorageService;
import Cryptoo.com.example.Cryptoo.services.UserService;
import Cryptoo.com.example.Cryptoo.services.impl.LogsService;
import Cryptoo.com.example.Cryptoo.services.impl.UpdateUserService;
import Cryptoo.com.example.Cryptoo.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;
@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/users") // Localhost:8080/users
public class UserController {

	@Autowired
	FileStorageService fileStorageService;

	@Autowired
	ImageMetadataRepository imageRepository;

	@Autowired
	UserService userService;

	@Autowired
	UpdateUserService updateUserService;


	@Autowired
	UserRepository userRepository;
	@Autowired
	LogsService logsService;

	@GetMapping(path = "/{id}")
	public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
		UserDto userDto = userService.getUserByUserId(id);
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(userDto, userResponse);
		if(userService.checkMessages(id)){userResponse.setMessage(true);}
		else{userResponse.setMessage(false);}
		if(userService.checkNotifications(id)){userResponse.setNotification(true);}
		else{userResponse.setNotification(false);}
		return new ResponseEntity<UserResponse>( userResponse,HttpStatus.OK);
	}

	@PostMapping(path = "/profile/{userId}")
	public ResponseEntity<SimpleRequest> updateProfile(@PathVariable String userId,@RequestBody SimpleRequest request){
		SimpleRequest response=new SimpleRequest();
		UserEntity userEntity=userRepository.findByUserId(userId);
		if(request.getRequest().equals("Phone")){
			UserEntity phoneUser=userRepository.findByPhone(request.getResponse());
			if(phoneUser!=null) {
				response.setResponse("Phone Already Exist");
				return new ResponseEntity<SimpleRequest>(response, HttpStatus.OK);
			}
		}else if(request.getRequest().equals("Email")){
			UserEntity phoneUser=userRepository.findByEmail(request.getResponse());
			if(phoneUser!=null){
				response.setResponse("Email Already Exist");
				return new ResponseEntity<SimpleRequest>(response,HttpStatus.OK);
			}
		}
		if(userEntity!=null && updateUserService.checkUpdate(userId,request.getRequest())){
		if(request.getRequest().equals("Email")){
			UserEntity phoneUser=userRepository.findByEmail(request.getResponse());
			if(phoneUser!=null){
				response.setResponse("Email Already Exist");
				return new ResponseEntity<SimpleRequest>(response,HttpStatus.OK);
			}
			userEntity.setEmail(request.getResponse());
		}else if(request.getRequest().equals("FirstName")){
			userEntity.setFirstname(request.getResponse());
		}else if(request.getRequest().equals("LastName")){
			userEntity.setLastname(request.getResponse());
		}else if(request.getRequest().equals("Phone")){
			UserEntity phoneUser=userRepository.findByPhone(request.getResponse());
			if(phoneUser!=null){
				response.setResponse("Phone Already Exist");
				return new ResponseEntity<SimpleRequest>(response,HttpStatus.OK);
			}
			userEntity.setPhone(request.getResponse());
		} else if(request.getRequest().equals("Image")){
			ImageEntity imageEntity=imageRepository.findById(userEntity.getImageId());
			try {
				fileStorageService.deleteFile(imageEntity.getName());
			} catch (FileNotFoundException e) {
			}
			userEntity.setImageId(request.getResponse());
		}
		userRepository.save(userEntity);
		response.setResponse("done");
		logsService.addToLogs("User "+userId+" updated his "+request.getRequest()+" successfully.");
		}
		else{
			response.setResponse("You can only update your "+request.getRequest()+" once every 7 days.");
		}
		return new ResponseEntity<SimpleRequest>(response,HttpStatus.OK);
	}

	@PostMapping(path = "/checklogin")
	public ResponseEntity<ResetPasswordResponse> checkUser(@RequestBody UserLoginRequest request) {
		ResetPasswordResponse response =new ResetPasswordResponse();
		try{
		UserDto userDto = userService.getUserByUserId(request.getPassword());
		if(userDto != null && request.getEmail().equals(userDto.getEmail())){
			response.setResponse("true");
		}else{
			response.setResponse("false");
		}
		 }catch (Exception e){
			response.setResponse("false");
		 }
		return new ResponseEntity<ResetPasswordResponse>( response,HttpStatus.OK);
	}


	@PostMapping
	public ResponseEntity<UserResponse> createUser(@Valid @RequestBody  UserRequest userRequest) throws Exception{
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userRequest, UserDto.class);
		UserDto createUser = userService.createUser(userDto);
		UserResponse userResponse = modelMapper.map(createUser, UserResponse.class);
		logsService.addToLogs("A new user has been created with id : "+ userResponse.getUserId()+" .");
		return new ResponseEntity<UserResponse>( userResponse,HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable String id) {
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
