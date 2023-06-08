package Cryptoo.com.example.Cryptoo.repositories;

import Cryptoo.com.example.Cryptoo.entities.ChannelMessageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChannelMessageRepository extends PagingAndSortingRepository<ChannelMessageEntity,String> {
    ChannelMessageEntity findByChannelId(String id);
    ChannelMessageEntity findBySenderIdAndRecieverId(String sender,String reciever);
    List<ChannelMessageEntity> findAllByRecieverId(String id);

    @Query("SELECT s FROM channelMessage s WHERE (s.recieverId LIKE :reciever or s.senderId LIKE :reciever) and (s.recieverId LIKE :sender or s.senderId LIKE :sender)")
    ChannelMessageEntity findSenderIdAndRecieverId(String sender,String reciever);

    @Query("SELECT s FROM channelMessage s WHERE (s.recieverId LIKE :id or s.senderId LIKE :id) and (s.senderName LIKE %:search% or s.recieverName LIKE %:search%) ")
    Page<ChannelMessageEntity> findAllChannelsearch(Pageable pageableRequest,String id,String search);

    @Query("SELECT s FROM channelMessage s WHERE s.recieverId LIKE :id or s.senderId LIKE :id ")
    Page<ChannelMessageEntity> findAllChannel(Pageable pageableRequest,String id);

    @Query("SELECT s FROM channelMessage s WHERE s.recieverId LIKE :id or s.senderId LIKE :id ")
    List<ChannelMessageEntity> findAll_Channel(String id);
}
