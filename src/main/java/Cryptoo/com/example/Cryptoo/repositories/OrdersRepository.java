package Cryptoo.com.example.Cryptoo.repositories;

import Cryptoo.com.example.Cryptoo.entities.OrdersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends PagingAndSortingRepository<OrdersEntity,String> {


    @Query("SELECT s FROM orders s WHERE s.company LIKE :search AND s.status LIKE %:search1%")
    Page<OrdersEntity> findAllOrdersByCompanyId(Pageable pageable, @Param("search") String search, @Param("search1") String search1);

    @Query("SELECT s FROM orders s WHERE s.user LIKE :search AND s.status LIKE %:search1%")
    Page<OrdersEntity> findAllOrdersByuser(Pageable pageable, @Param("search") String search, @Param("search1") String search1);
    OrdersEntity findByOrderId(String id);
    List<OrdersEntity> findAllByUser(String id);
    List<OrdersEntity> findAllByOrderId(String id);
    List<OrdersEntity> findAllByCompany(String id);

}
