package Cryptoo.com.example.Cryptoo.repositories;

import Cryptoo.com.example.Cryptoo.entities.NotificationsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationsRepository extends PagingAndSortingRepository<NotificationsEntity,String> {
    NotificationsEntity findByNotificationId(String id);
    Page<NotificationsEntity> findAllByUser(Pageable pageable, String id);

    @Query("SELECT s FROM notifications s where s.user like :id")
    List<NotificationsEntity> findallusers(@Param("id") String id);
}
