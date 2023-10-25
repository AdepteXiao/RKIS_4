package ru.ad.lab4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.ad.lab4.userInterfaces.CUI;

@SpringBootApplication
public class Lab4Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Lab4Application.class, args);
		CUI cui =  context.getBean("CUI", CUI.class);
		cui.menu();

	}

}
