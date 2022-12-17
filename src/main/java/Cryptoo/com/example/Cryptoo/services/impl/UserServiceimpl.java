package Cryptoo.com.example.Cryptoo.services.impl;
import Cryptoo.com.example.Cryptoo.entities.UserEntity;
import Cryptoo.com.example.Cryptoo.repositories.ContactRepository;
import Cryptoo.com.example.Cryptoo.repositories.UserRepository;
import Cryptoo.com.example.Cryptoo.services.UserService;
import Cryptoo.com.example.Cryptoo.shared.Utils;
import Cryptoo.com.example.Cryptoo.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;

@Service
public class UserServiceimpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ContactRepository contactRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	Utils util;

	@Override
	public UserDto createUser(UserDto user) {

		UserEntity checkUser = userRepository.findByUsername(user.getUsername());
		if (checkUser != null)
			throw new RuntimeException("user already exist ");
		//user.getContact().setIdd(userRepository.count()+1);
		user.getContact().setContactId(util.generateStringId(30));
		user.getContact().setUser(user);
		user.getPnl().setPnlid(String.valueOf(userRepository.count()+1));
		user.getPnl().setUser(user);
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = modelMapper.map(user, UserEntity.class);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setId(userRepository.count()+1);
		userEntity.setUserId(util.generateStringId(32));
		userEntity.setCountry(user.getCountry());
		userEntity.setNb_ads(user.getNb_ads());
		userEntity.getOptions1(user.getOptions1());
		UserEntity newUser = userRepository.save(userEntity);
		UserDto userDto = modelMapper.map(newUser, UserDto.class);

		return userDto;
	}


	@Override
	public UserDto updateUser(long userId, UserDto userDto) {

		UserEntity userEntity = userRepository.findById(userId);

		double x=parseDouble(userEntity.getNb_ads());
		userEntity.setNb_ads(Double.toString(x+1));

		UserEntity userUpdated = userRepository.save(userEntity);

		UserDto user = new UserDto();

		BeanUtils.copyProperties(userUpdated, user);

		return user;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByUsername(username);
		if (userEntity == null)
			throw new UsernameNotFoundException(username);

		return new User(userEntity.getUsername(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUser(String username) {
		UserEntity userEntity = userRepository.findByUsername(username);
		if (userEntity == null)
			throw new UsernameNotFoundException(username);
		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(userEntity, userDto);
		return userDto;
	}

	@Override
	public UserDto getUserByUserId(long userId) {
		UserEntity userEntity = userRepository.findById(userId);
		/*if (userEntity == null)
			throw new UsernameNotFoundException(userId);*/
		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(userEntity, userDto);
		return userDto;
	}



	@Override
	public void deleteUser(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new UsernameNotFoundException(userId);
		userRepository.delete(userEntity);
		
	}

	@Override
	public List<UserDto> getUsers(int page, int limit,String search) {
		if(page>0) page-=1;
		List<UserDto> usersDto = new ArrayList<>();
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<UserEntity> userPage;

			userPage = userRepository.findAllUsers(pageableRequest);


		List<UserEntity> users = userPage.getContent();
		for(UserEntity userEntity: users) {

			ModelMapper modelMapper = new ModelMapper();
			UserDto user = modelMapper.map(userEntity,UserDto.class);
			usersDto.add(user);
			
		}
		return usersDto;
	}

}
