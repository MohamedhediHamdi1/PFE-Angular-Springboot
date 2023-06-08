package Cryptoo.com.example.Cryptoo.repositories;

import Cryptoo.com.example.Cryptoo.entities.AnalyticsEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyticsRepository extends PagingAndSortingRepository<AnalyticsEntity,String> {

    AnalyticsEntity findByCompanyId(String id);

}
