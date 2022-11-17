package Cryptoo.com.example.Cryptoo.repositories;

import Cryptoo.com.example.Cryptoo.entities.PriceEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends PagingAndSortingRepository<PriceEntity, Long> {
    PriceEntity findByName(String name);
}
