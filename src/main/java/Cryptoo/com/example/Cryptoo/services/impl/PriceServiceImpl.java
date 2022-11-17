package Cryptoo.com.example.Cryptoo.services.impl;


import Cryptoo.com.example.Cryptoo.entities.PriceEntity;
import Cryptoo.com.example.Cryptoo.repositories.PriceRepository;
import Cryptoo.com.example.Cryptoo.services.PriceService;
import Cryptoo.com.example.Cryptoo.shared.dto.PriceDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceServiceImpl implements PriceService {


    @Autowired
    PriceRepository priceRepository;

    @Override
    public PriceDto updateprice(PriceDto priceDto) {
        PriceEntity priceEntity = (PriceEntity) priceRepository.findByName("btc");
        priceEntity.setPrice(priceDto.getBtcprice());
        PriceEntity priceEntity1 = (PriceEntity) priceRepository.findByName("eth");
        priceEntity1.setPrice(priceDto.getEthprice());
        PriceEntity priceEntity2 = (PriceEntity) priceRepository.findByName("bnb");
        priceEntity2.setPrice(priceDto.getBnbprice());
        PriceEntity updated = priceRepository.save(priceEntity);
        PriceDto priceDto1 = new PriceDto();
        BeanUtils.copyProperties(updated,priceDto1);
        return priceDto1;
    }

   /* @Override
    public PriceDto createPrice(PriceDto price) {

        System.out.println("111111");

        ModelMapper modelMapper = new ModelMapper();
        System.out.println("222");

        PriceEntity priceEntity = modelMapper.map(price, PriceEntity.class);
        System.out.println("333");

        PriceEntity newprice = (PriceEntity) priceRepository.save(priceEntity);
        System.out.println("444");


        PriceDto priceDto = modelMapper.map(newprice, PriceDto.class);
        System.out.println("555");

        return priceDto;
    }*/



}
