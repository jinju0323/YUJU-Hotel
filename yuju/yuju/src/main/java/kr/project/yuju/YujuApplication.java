package kr.project.yuju;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// 스케쥴러 활성화
// --> import org.springframework.scheduling.annotation.EnableScheduling; 
@EnableScheduling
@SpringBootApplication
public class YujuApplication {

	public static void main(String[] args) {
		SpringApplication.run(YujuApplication.class, args);
	}

}
