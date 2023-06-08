package Cryptoo.com.example.Cryptoo.services;

import Cryptoo.com.example.Cryptoo.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
	
UserDto createUser(UserDto user);



    UserDto getUser(String email);

UserDto getUserByUserId(String userId);

    void updateUser(String id, UserDto userDto);

void deleteUser(String userId);
    boolean getPaswword(String email,String password);


    boolean checkMessages(String userId);
    boolean checkNotifications(String userId);

}
