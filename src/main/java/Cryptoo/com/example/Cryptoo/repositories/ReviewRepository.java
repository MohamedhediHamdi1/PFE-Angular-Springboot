package Cryptoo.com.example.Cryptoo.repositories;

import Cryptoo.com.example.Cryptoo.entities.ReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends PagingAndSortingRepository<ReviewEntity,String> {

    ReviewEntity findByReviewid(String id);

    ReviewEntity findByCompanyId(String id);

    ReviewEntity findByServiceId(String id);
    ReviewEntity findByOrderId(String id);

    @Query("SELECT r FROM reviews r WHERE r.serviceId LIKE %:search% ORDER BY r.date DESC")
    Page<ReviewEntity> findByServiceIdPageByDate(Pageable pageable, @Param("search") String search);

    @Query("SELECT r FROM reviews r WHERE r.serviceId LIKE %:search% ORDER BY r.rate DESC")
    Page<ReviewEntity> findByServiceIdPageByRate(Pageable pageable, @Param("search") String search);

    @Query("SELECT r FROM reviews r WHERE r.companyId LIKE %:search% ORDER BY r.date DESC")
    Page<ReviewEntity> findByCompanyPageByDate(Pageable pageable, @Param("search") String search);

    @Query("SELECT r FROM reviews r WHERE r.companyId LIKE %:search% ORDER BY r.rate DESC")
    Page<ReviewEntity> findByCompanyPageByRate(Pageable pageable, @Param("search") String search);
    List<ReviewEntity> findAllByServiceId(String id);
    List<ReviewEntity> findAllByCompanyId(String id);

}
