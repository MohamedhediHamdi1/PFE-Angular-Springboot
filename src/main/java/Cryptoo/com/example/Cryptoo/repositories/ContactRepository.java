package Cryptoo.com.example.Cryptoo.repositories;

import Cryptoo.com.example.Cryptoo.entities.ContactEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends PagingAndSortingRepository<ContactEntity,String> {
    ContactEntity findByIpAddress(String email);
    @Query("SELECT c FROM contact c WHERE c.id=:id")
    ContactEntity findbyids(@Param("id") String id);

    @Query("SELECT c FROM contact c WHERE c.guest = :guest ORDER BY CASE WHEN c.admin = '0' THEN 0 ELSE 1 END")
    Page<ContactEntity> findcontactsbytype(Pageable pageableRequest,@Param("guest") boolean guest);
}
