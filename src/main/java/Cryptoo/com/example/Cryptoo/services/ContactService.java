package Cryptoo.com.example.Cryptoo.services;

import Cryptoo.com.example.Cryptoo.shared.dto.ContactDto;


public interface ContactService  {
    ContactDto getContact1(Long id);

    ContactDto updateWallet(String id, ContactDto contactDto);

    ContactDto getContact(String id);

    void manageOrder();
}
