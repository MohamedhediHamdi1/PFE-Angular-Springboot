package Cryptoo.com.example.Cryptoo.repositories;

import Cryptoo.com.example.Cryptoo.entities.HistoryEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HistoryRepository extends PagingAndSortingRepository<HistoryEntity, Long> {
}
