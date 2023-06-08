package Cryptoo.com.example.Cryptoo.repositories;

import Cryptoo.com.example.Cryptoo.entities.UpdateUserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpdateUserRepository extends PagingAndSortingRepository<UpdateUserEntity,String> {

    UpdateUserEntity findByUserId(String id);

}
