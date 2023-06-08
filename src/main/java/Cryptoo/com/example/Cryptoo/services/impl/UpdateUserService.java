package Cryptoo.com.example.Cryptoo.services.impl;

import Cryptoo.com.example.Cryptoo.entities.UpdateUserEntity;
import Cryptoo.com.example.Cryptoo.repositories.UpdateUserRepository;
import Cryptoo.com.example.Cryptoo.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class UpdateUserService {

    @Autowired
    UpdateUserRepository userRepository;

    @Autowired
    Utils util;

    public  boolean checkUpdate(String userId,String type){
        Date newDate=new Date();
        UpdateUserEntity entity= userRepository.findByUserId(userId);
        if(entity==null){
            UpdateUserEntity newEntity=new UpdateUserEntity();
            if(type.equals("Email")){
                newEntity.setEmail(newDate);
            }else if(type.equals("FirstName")){
                newEntity.setFirstname(newDate);
            }else if(type.equals("LastName")){
                newEntity.setLastname(newDate);
            }else if(type.equals("Phone")){
                newEntity.setPhone(newDate);
            } else if(type.equals("Image")){
                newEntity.setImageId(newDate);
            }
            newEntity.setId(util.generateStringId(20));
            newEntity.setUserId(userId);
            userRepository.save(newEntity);
            return true;
        }else{
            if(type.equals("Email")){
                if(entity.getEmail()!=null){
                    Date lastDate= entity.getEmail();
                    LocalDate oldDate = lastDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate newDate1 = LocalDateTime.now().toLocalDate();
                    Duration duration = Duration.between(oldDate.atStartOfDay(), newDate1.atStartOfDay());
                    long diff = Math.abs(duration.toDays());
                    if(diff>7){entity.setEmail(newDate);}
                    return diff > 7;
                }else{
                    entity.setEmail(newDate);
                    userRepository.save(entity);
                    return true;
                }
            }else if(type.equals("FirstName")){
                if(entity.getFirstname()!=null){
                    Date lastDate= entity.getFirstname();
                    LocalDate oldDate = lastDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate newDate1 = LocalDateTime.now().toLocalDate();
                    Duration duration = Duration.between(oldDate.atStartOfDay(), newDate1.atStartOfDay());
                    long diff = Math.abs(duration.toDays());
                    if(diff>7){entity.setFirstname(newDate);}
                    return diff > 7;
                }else{
                    entity.setFirstname(newDate);
                    userRepository.save(entity);
                    return true;
                }
            }else if(type.equals("LastName")){
                if(entity.getLastname()!=null){
                    Date lastDate= entity.getLastname();
                    LocalDate oldDate = lastDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate newDate1 = LocalDateTime.now().toLocalDate();
                    Duration duration = Duration.between(oldDate.atStartOfDay(), newDate1.atStartOfDay());
                    long diff = Math.abs(duration.toDays());
                    if(diff>7){entity.setLastname(newDate);}
                    return diff > 7;
                }else{
                    entity.setLastname(newDate);
                    userRepository.save(entity);
                    return true;
                }
            }else if(type.equals("Phone")){
                if(entity.getPhone()!=null){
                    Date lastDate= entity.getPhone();
                    LocalDate oldDate = lastDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate newDate1 = LocalDateTime.now().toLocalDate();
                    Duration duration = Duration.between(oldDate.atStartOfDay(), newDate1.atStartOfDay());
                    long diff = Math.abs(duration.toDays());
                    if(diff>7){entity.setPhone(newDate);}
                    return diff > 7;
                }else{
                    entity.setPhone(newDate);
                    userRepository.save(entity);
                    return true;
                }
            }else if(type.equals("Image")){
                if(entity.getImageId()!=null){
                    Date lastDate= entity.getImageId();
                    LocalDate oldDate = lastDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate newDate1 = LocalDateTime.now().toLocalDate();
                    Duration duration = Duration.between(oldDate.atStartOfDay(), newDate1.atStartOfDay());
                    long diff = Math.abs(duration.toDays());
                    if(diff>7){entity.setImageId(newDate);}
                    return diff > 7;
                }else{
                    entity.setImageId(newDate);
                    userRepository.save(entity);
                    return true;
                }
            } else{
                return false;
            }


        }
    }

}
