package group.Food_order_bachelor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class FoodOrderBachelorApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodOrderBachelorApplication.class, args);
	}

}
