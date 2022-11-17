package Cryptoo.com.example.Cryptoo.repositories;


import Cryptoo.com.example.Cryptoo.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository< UserEntity, Long> {
	
	UserEntity findByUsername(String username);
	
	UserEntity findByUserId(String userId);

	@Query(value = "SELECT * FROM users",nativeQuery = true)
	Page<UserEntity> findAllUsers(Pageable pageableRequest);



	

}
