package Cryptoo.com.example.Cryptoo.services.impl;

import Cryptoo.com.example.Cryptoo.entities.AnalyticsEntity;
import Cryptoo.com.example.Cryptoo.entities.CompanyEntity;
import Cryptoo.com.example.Cryptoo.repositories.AnalyticsRepository;
import Cryptoo.com.example.Cryptoo.repositories.CompanyRepository;
import Cryptoo.com.example.Cryptoo.requests.CompanyRequest;
import Cryptoo.com.example.Cryptoo.shared.Utils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AnalyticsRepository analyticsRepository;

    @Autowired
    Utils util;



    public void createCompnay(String userId, CompanyRequest request){
        CompanyEntity newEntity=new CompanyEntity();
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.map(request,newEntity);
        newEntity.setCompanyId(util.generateStringId(32));
        newEntity.setVerified(false);
        newEntity.setActive(false);
        newEntity.setUserId(userId);
        newEntity.setReview(0.0);
        companyRepository.save(newEntity);
        AnalyticsEntity analyticsEntity=new AnalyticsEntity();
        analyticsEntity.setCompanyId(newEntity.getCompanyId());
        analyticsEntity.setAnaylticId(util.generateStringId(32));
        analyticsRepository.save(analyticsEntity);
    }

    public boolean checkCompany(String corporationId,String businessNumber) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String url = "https://corporations-ised-isde.api.canada.ca/api/v1/corporations/"+businessNumber+".json";
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("user-key", "10c673af856605a0f98207c068bf41d6");
        httpGet.addHeader("Content-Type", "application/json");
        CloseableHttpResponse response = httpClient.execute(httpGet);

        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        reader.close();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(stringBuilder.toString());
        String response_corporationId="0000";
        String response_businessNumber="0000";
        if (rootNode.isArray()) {
            for (JsonNode node : rootNode) {
                response_corporationId = node.get("corporationId").asText();
                response_businessNumber = node.get("businessNumbers").get("businessNumber").asText();
            }
        }
        response.close();
        httpClient.close();

        if(response_businessNumber.equals(businessNumber) && response_corporationId.equals(corporationId)){
            return true;
        }
        return false;
    }


}
