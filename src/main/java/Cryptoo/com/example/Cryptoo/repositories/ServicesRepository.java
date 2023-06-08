package Cryptoo.com.example.Cryptoo.repositories;

import Cryptoo.com.example.Cryptoo.entities.ServiceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicesRepository extends PagingAndSortingRepository<ServiceEntity,String> {

    ServiceEntity findByPrivateId(String id);
    List<ServiceEntity> findAllByCompanyId(String id);

    @Query(value = "SELECT * FROM services",nativeQuery = true)
    Page<ServiceEntity> findAllServices(Pageable pageableRequest);

    @Query("SELECT s FROM services s WHERE s.name LIKE %:search%" )
    Page<ServiceEntity> findAllServices_search(Pageable pageable, @Param("search") String search);

    @Query("SELECT s FROM services s WHERE s.name LIKE %:search% or s.companyId LIKE %:search% or s.privateId LIKE %:search% " )
    Page<ServiceEntity> findAllServices_search1(Pageable pageable, @Param("search") String search);

    @Query("SELECT s FROM services s WHERE s.category LIKE %:search%")
    Page<ServiceEntity> findAllRecomandation_search(Pageable pageable, @Param("search") String search);


}
