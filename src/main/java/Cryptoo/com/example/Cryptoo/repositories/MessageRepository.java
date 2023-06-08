package Cryptoo.com.example.Cryptoo.repositories;

import Cryptoo.com.example.Cryptoo.entities.MessageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends PagingAndSortingRepository<MessageEntity,String> {
    MessageEntity findByMessageId(String id);

    @Query("SELECT s FROM messages s WHERE s.channelID LIKE :search")
    Page<MessageEntity> findMesseges(String search, Pageable page);


    //Page<MessageEntity> findMesseges(String channelId, Pageable pageable, Sort sort);

    Page<MessageEntity> findAllByChannelID(String channelId, Pageable pageable);

    List<MessageEntity> findAllByChannelIDOrderBySendDate(String channelId);

}
