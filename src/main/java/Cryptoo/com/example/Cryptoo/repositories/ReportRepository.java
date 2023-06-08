package Cryptoo.com.example.Cryptoo.repositories;

import Cryptoo.com.example.Cryptoo.entities.ReportEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends PagingAndSortingRepository<ReportEntity,String> {

    @Query("SELECT c FROM report c ORDER BY CASE WHEN c.admin = '0' THEN 0 ELSE 1 END")
    Page<ReportEntity> findcontactsbytype(Pageable pageableRequest);

    ReportEntity findByReportId(String id);
}
