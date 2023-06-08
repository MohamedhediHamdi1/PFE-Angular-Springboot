package Cryptoo.com.example.Cryptoo.repositories;

import Cryptoo.com.example.Cryptoo.entities.CompanyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends PagingAndSortingRepository<CompanyEntity,String> {

    CompanyEntity findByCompanyId(String copanyId);
    CompanyEntity findByUserId(String userId);
    CompanyEntity findByCorporation(String corporation);
    CompanyEntity findByBusinessNumber(String businessNumber);

    @Query(value = "SELECT u FROM company u where u.fullName LIKE %:search% or u.displayName LIKE %:search% or u.companyId like %:search% or u.userId like %:search%")
    Page<CompanyEntity> findAllCompanies(Pageable pageableRequest, String search);

    @Query(value = "SELECT u FROM company u where (u.fullName LIKE %:search% or u.displayName LIKE %:search%) and (u.active = :active)")
    Page<CompanyEntity> findAllCompaniesWithActive(Pageable pageableRequest, @Param("search") String search,@Param("active") boolean active);

    @Query(value = "SELECT u FROM company u where (u.fullName LIKE %:search% or u.displayName LIKE %:search%) and (u.verified = :active)")
    Page<CompanyEntity> findAllCompaniesWithVerified(Pageable pageableRequest, @Param("search") String search,@Param("active") boolean active);


}
