package Cryptoo.com.example.Cryptoo.repositories;

import Cryptoo.com.example.Cryptoo.entities.ResetPasswordEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Passwordrepository extends PagingAndSortingRepository<ResetPasswordEntity, String> {

    ResetPasswordEntity findById(Long id);
    ResetPasswordEntity findByEmail(String email);

}
