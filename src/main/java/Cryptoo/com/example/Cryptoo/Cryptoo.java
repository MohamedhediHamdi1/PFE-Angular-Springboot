package Cryptoo.com.example.Cryptoo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableScheduling
public class Cryptoo {

	public static void main(String[] args) {
		SpringApplication.run(Cryptoo.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder (){
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SpringApplicationContext springApplicationContext(){
		return new SpringApplicationContext();
	}



}
