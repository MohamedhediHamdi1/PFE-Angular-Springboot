package Cryptoo.com.example.Cryptoo.services;

import Cryptoo.com.example.Cryptoo.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{
	
UserDto createUser(UserDto user);

UserDto getUser(String email);

UserDto getUserByUserId(String userId);

void deleteUser(String userId);



List<UserDto> getUsers(int page,int limit,String search);

}
