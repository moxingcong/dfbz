package com.ktc.gathering;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @Description
 * @author admin
 * @date 2020-11-26 10:33:26
*/
@SpringBootApplication
@EnableCaching		//开启SpringCache缓存
public class GatheringApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatheringApplication.class, args);
	}

	@Bean
	public IdWorker idWorker(){
		return new IdWorker();
	}
	
}
