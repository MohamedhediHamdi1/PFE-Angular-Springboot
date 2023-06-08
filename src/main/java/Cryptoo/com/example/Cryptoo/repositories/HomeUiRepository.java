package Cryptoo.com.example.Cryptoo.repositories;

import Cryptoo.com.example.Cryptoo.entities.HomeUiEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeUiRepository extends PagingAndSortingRepository<HomeUiEntity, String> {
    HomeUiEntity findById(Long id);
}
