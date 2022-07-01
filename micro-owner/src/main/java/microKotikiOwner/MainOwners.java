package microKotikiOwner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


//@SpringBootApplication(excludeName = "restApiKotiki.controller")

@EnableJpaRepositories("microKotikiOwner.repository")
@EntityScan("models")
@SpringBootApplication(excludeName = "microKotiki")
public class MainOwners {
    public static void main( String[] args){
        SpringApplication.run(MainOwners.class, args);
    }
}
