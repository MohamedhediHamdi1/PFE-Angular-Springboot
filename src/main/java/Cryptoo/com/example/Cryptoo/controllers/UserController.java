package Cryptoo.com.example.Cryptoo.controllers;
import Cryptoo.com.example.Cryptoo.requests.UserRequest;
import Cryptoo.com.example.Cryptoo.responses.UserResponse;
import Cryptoo.com.example.Cryptoo.services.UserService;
import Cryptoo.com.example.Cryptoo.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/users") // Localhost:8080/users
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<UserResponse> getUser(@PathVariable long id) {
		UserDto userDto = userService.getUserByUserId(id);
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(userDto, userResponse);
		return new ResponseEntity<UserResponse>( userResponse,HttpStatus.OK);
	}


	@GetMapping
	public List<UserResponse> getAllUsers(@RequestParam(value = "page",defaultValue = "1") int page,@RequestParam(value = "limit",defaultValue = "15") int limit,@RequestParam(value = "search",defaultValue = "") String search){
		List<UserResponse> usersResponse = new ArrayList<>();
		List<UserDto> users =userService.getUsers(page,limit,search);
		for(UserDto userDto: users) {
			//UserResponse user = new UserResponse();
			//BeanUtils.copyProperties(userDto, user);
			ModelMapper modelMapper = new ModelMapper();
			UserResponse user = modelMapper.map(userDto,UserResponse.class);
			usersResponse.add(user);
			
		}
		return usersResponse;
	}
	
	@PostMapping
	public ResponseEntity<UserResponse> createUser(@Valid @RequestBody  UserRequest userRequest) throws Exception{

		//UserDto userDto = new UserDto();
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userRequest, UserDto.class);


		//BeanUtils.copyProperties(userRequest, userDto);
		
		UserDto createUser = userService.createUser(userDto);

		UserResponse userResponse = modelMapper.map(createUser, UserResponse.class);
		
		return new ResponseEntity<UserResponse>( userResponse,HttpStatus.CREATED);
	}


	@PutMapping(path="/{id}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable long id, @RequestBody UserRequest userRequest) {

		System.out.println("WAtched_Ad");

		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(userRequest, userDto);

		UserDto updateUser = userService.updateUser(id, userDto);

		UserResponse userResponse = new UserResponse();

		BeanUtils.copyProperties(updateUser, userResponse);

		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.ACCEPTED);
	}
	

	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable String id) {
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
