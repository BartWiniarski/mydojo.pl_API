package pl.mydojo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {


	public static void main(String[] args) {
		usersGenerator();
	}

	public static void springBoot(String[] args){
		SpringApplication.run(Application.class, args);
	}

	public static void usersGenerator(){
		System.out.println("test");
	}
}
