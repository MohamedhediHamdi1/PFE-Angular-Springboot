package Cryptoo.com.example.Cryptoo.repositories;

import Cryptoo.com.example.Cryptoo.entities.ContactEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends PagingAndSortingRepository<ContactEntity, Long> {
    ContactEntity findByContactId(String contactId);
    ContactEntity findByIdd(long idd);
}
