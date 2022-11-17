package Cryptoo.com.example.Cryptoo.controllers;


import Cryptoo.com.example.Cryptoo.requests.PriceRequest;
import Cryptoo.com.example.Cryptoo.responses.PriceResponse;
import Cryptoo.com.example.Cryptoo.services.PriceService;
import Cryptoo.com.example.Cryptoo.shared.dto.PriceDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/price")
public class PriceController {

    @Autowired
    PriceService priceService;

    @PutMapping
    public ResponseEntity<PriceResponse> updateprice(@RequestBody PriceRequest priceRequest) {
        PriceDto priceDto = new PriceDto();
        BeanUtils.copyProperties(priceRequest, priceDto);
        PriceDto updateprice = priceService.updateprice(priceDto);
        PriceResponse priceResponse = new PriceResponse();
        BeanUtils.copyProperties(updateprice,priceResponse);


        return new ResponseEntity<PriceResponse>(priceResponse, HttpStatus.ACCEPTED);
    }


   /* @GetMapping
    public String String(){
        System.out.println("getmessage");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        String ip = request.getRemoteAddr();
        System.out.println(ip);
        return "done";
    }*/

   /* @PostMapping
    public ResponseEntity<PriceResponse> createPrice(@Valid @RequestBody PriceRequest priceRequest) throws Exception{

        System.out.println("111111");
        ModelMapper modelMapper = new ModelMapper();
        System.out.println("222");
        PriceDto priceDto = modelMapper.map(priceRequest, PriceDto.class);
        System.out.println("333");


        PriceDto createPrice = priceService.createPrice(priceDto);
        System.out.println("444");
        PriceResponse priceResponse = modelMapper.map(createPrice, PriceResponse.class);
        System.out.println("555");
        return new ResponseEntity<PriceResponse>( priceResponse, HttpStatus.CREATED);
    }*/

}
