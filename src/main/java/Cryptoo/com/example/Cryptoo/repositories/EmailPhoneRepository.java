package Cryptoo.com.example.Cryptoo.repositories;

import Cryptoo.com.example.Cryptoo.entities.EmailPhoneEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailPhoneRepository extends PagingAndSortingRepository<EmailPhoneEntity,String> {
    EmailPhoneEntity findByEmail(String email);
    EmailPhoneEntity findByPhone(String phone);
    EmailPhoneEntity findById(Long id);
}
