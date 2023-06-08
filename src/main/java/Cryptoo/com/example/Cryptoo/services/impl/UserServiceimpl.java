package Cryptoo.com.example.Cryptoo.services.impl;

import Cryptoo.com.example.Cryptoo.entities.*;
import Cryptoo.com.example.Cryptoo.repositories.*;
import Cryptoo.com.example.Cryptoo.services.UserService;
import Cryptoo.com.example.Cryptoo.shared.Utils;
import Cryptoo.com.example.Cryptoo.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceimpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	EmailPhoneRepository emailPhoneRepository;

	@Autowired
	NotificationRepository notificationRepository;



	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	ChannelMessageRepository channelMessageRepository;
	@Autowired
	NotificationsRepository notificationsRepository;



	@Autowired
	Utils util;

	@Override
	public UserDto createUser(UserDto user) {
		UserEntity checkUser = userRepository.findByEmail(user.getEmail());
		EmailPhoneEntity emailPhoneEntity=emailPhoneRepository.findByEmail(user.getEmail());
		String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(user.getEmail());

		if (checkUser != null || !matcher.matches() || emailPhoneEntity==null)
			throw new RuntimeException("user already exist ");
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = modelMapper.map(user, UserEntity.class);
		userEntity.setEmail(user.getEmail());
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setUserId(util.generateStringId(32));
		userEntity.setFirstname(user.getFirstname());
		userEntity.setLastname(user.getLastname());
		userEntity.setPhone(user.getPhone());
		userEntity.setCountry(user.getCountry());
		userEntity.setState(user.getState());
		userEntity.setCity(user.getCity());
		userEntity.setZipcode(user.getZipcode());
		userEntity.setAddress1(user.getAddress1());
		userEntity.setImageId("ztjRfoJdh5YpXJCszNKo");
		userEntity.setLastLoginAttempt(null);
		userEntity.setLoginAttempts(0);
		userEntity.setActive(true);
		if(user.getAddress2() != null ) {
			userEntity.setAddress2(user.getAddress2());
		}
		userEntity.setPrestataireID("0");
		userEntity.setMembership(user.getMembership());
		UserEntity newUser = userRepository.save(userEntity);
		UserEntity newUser1 = userRepository.findByEmail(user.getEmail());
		UserDto userDto = modelMapper.map(newUser1, UserDto.class);
		createNotification(newUser1.getUserId());
		return userDto;
	}


	@Override
	public void updateUser(String userId, UserDto userDto) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		userEntity.setLastLoginAttempt(userDto.getLastLoginAttempt());
		userEntity.setLoginAttempts(userDto.getLoginAttempts());
		userRepository.save(userEntity);
	}



	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(userEntity, userDto);
		return userDto;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		/*if (userEntity == null)
			throw new UsernameNotFoundException(userId);*/
		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(userEntity, userDto);
		return userDto;
	}
	@Override
	public boolean getPaswword(String email,String password) {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		return bCryptPasswordEncoder.matches(password,userEntity.getEncryptedPassword());


	}



	@Override
	public void deleteUser(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new UsernameNotFoundException(userId);
		userRepository.delete(userEntity);
		
	}


	public void createNotification(String userId){
		NotificationEntity newEntity=new NotificationEntity();
		newEntity.setUserId(userId);
		newEntity.setInboxMessages(true);
		newEntity.setOrderMessages(true);
		newEntity.setOrderUpdates(true);
		newEntity.setRatingReminders(true);
		newEntity.setBuyerBriefs(true);
		notificationRepository.save(newEntity);
	}
	public boolean checkMessages(String userId){
		List<ChannelMessageEntity> entityList =channelMessageRepository.findAll_Channel(userId);
		if(entityList.size()==0)
			return false;
		for(ChannelMessageEntity entity:entityList){
			if (!entity.isVue()){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

	public boolean checkNotifications(String userId){
		List<NotificationsEntity> list=notificationsRepository.findallusers(userId);
		if(list.size()==0)
			return false;
		for(NotificationsEntity entity:list){
			if(entity.isClick()==false)
				return true;
		}
		return false;
	}

}
