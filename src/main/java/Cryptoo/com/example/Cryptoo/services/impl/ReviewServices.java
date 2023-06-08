package Cryptoo.com.example.Cryptoo.services.impl;

import Cryptoo.com.example.Cryptoo.entities.ReviewEntity;
import Cryptoo.com.example.Cryptoo.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class ReviewServices {

    @Autowired
    ReviewRepository reviewRepository;

    public String time_Diff(Date date) {
        // Get the dates
        LocalDateTime lastDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime currentDate = LocalDateTime.now();

        // Calculate the difference between the dates
        long diff = ChronoUnit.MINUTES.between(lastDate, currentDate);

        // Determine the appropriate time unit and output the result
        String timeUnit;
        long result;
        if (diff < 60) {
            if(diff<1){
                timeUnit = "minute";
            }else{
                timeUnit = "minutes";
            }
            result = diff;
        } else if (diff < 1440) {
            if(diff<120){
                timeUnit = "hour";
            }else{
                timeUnit = "hours";
            }
            result = ChronoUnit.HOURS.between(lastDate, currentDate);
        } else if (diff < 43200) {
            if(diff<2880){
                timeUnit = "day";
            }else{
                timeUnit = "days";
            }
            result = ChronoUnit.DAYS.between(lastDate, currentDate);
        } else if (diff < 525600) {
            if(diff<86400){
                timeUnit = "month";
            }else{
                timeUnit = "months";
            }
            result = ChronoUnit.MONTHS.between(lastDate, currentDate);
        } else {
            if(diff<1051200){
                timeUnit = "year";
            }else{
                timeUnit = "years";
            }
            result = ChronoUnit.YEARS.between(lastDate, currentDate);
        }
        return String.valueOf(result)+ " " +timeUnit;
    }

    public String time_Diff_Messages(Date date) {
        // Get the dates
        LocalDateTime lastDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime currentDate = LocalDateTime.now();

        // Calculate the difference between the dates
        long diff = ChronoUnit.MINUTES.between(lastDate, currentDate);

        // Determine the appropriate time unit and output the result
        String timeUnit;
        long result;
        if (diff < 60) {
                timeUnit = "min";
            result = diff;
        } else if (diff < 1440) {
                timeUnit = "H";
            result = ChronoUnit.HOURS.between(lastDate, currentDate);
        } else if (diff < 43200) {
                timeUnit = "day";
            result = ChronoUnit.DAYS.between(lastDate, currentDate);
        } else if (diff < 525600) {
            if(diff<86400){
                timeUnit = "month";
            }else{
                timeUnit = "months";
            }
            result = ChronoUnit.MONTHS.between(lastDate, currentDate);
        } else {
            if(diff<1051200){
                timeUnit = "year";
            }else{
                timeUnit = "years";
            }
            result = ChronoUnit.YEARS.between(lastDate, currentDate);
        }
        return String.valueOf(result)+timeUnit;
    }

    public double calculRate(String id){
        List<ReviewEntity> entities = reviewRepository.findAllByServiceId(id);
        double rate=0;
        int nb=0;
        for (ReviewEntity entity : entities) {
            rate+=entity.getRate();
            nb+=1;
        }
        return rate/nb;
    }

}
