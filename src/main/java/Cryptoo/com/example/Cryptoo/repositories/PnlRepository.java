package Cryptoo.com.example.Cryptoo.repositories;

import Cryptoo.com.example.Cryptoo.entities.PnlEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PnlRepository extends PagingAndSortingRepository<PnlEntity, Long> {
    PnlEntity findByPnlid(String pnlid);
}
