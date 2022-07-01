package microKotikiCats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



//@SpringBootApplication(excludeName = "restApiKotiki.controller")

@EnableJpaRepositories("microKotikiCats.repository")
@EntityScan("models")
@SpringBootApplication(excludeName = "microKotikiCats")
public class MainCats {
    public static void main( String[] args){
        SpringApplication.run(MainCats.class, args);
    }
}
