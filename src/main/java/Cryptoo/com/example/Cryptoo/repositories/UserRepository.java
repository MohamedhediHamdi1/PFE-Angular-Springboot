package Cryptoo.com.example.Cryptoo.repositories;


import Cryptoo.com.example.Cryptoo.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository< UserEntity, Long> {
	
	UserEntity findByEmail(String email);
	
	UserEntity findByUserId(String userId);

	UserEntity findByPhone(String phone);

	//UserEntity findById(long userId);

	@Query(value = "SELECT u FROM users u where u.firstname LIKE %:search% or u.lastname LIKE %:search%")
	Page<UserEntity> findAllUsers(Pageable pageableRequest,String search);



	

}
