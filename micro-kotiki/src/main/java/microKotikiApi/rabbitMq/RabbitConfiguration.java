package microKotikiApi.rabbitMq;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
    @Bean
    public ConnectionFactory connectionFactory(){
        return new CachingConnectionFactory("localhost");
    }
    @Bean
    public AmqpAdmin amqpAdmin(){
        return new RabbitAdmin(connectionFactory());
    }
    @Bean
    public RabbitTemplate rabbitTemplate(){
        return new RabbitTemplate(connectionFactory());
    }
    @Bean
    public Queue getOwnerByID(){
        return new Queue("getOwnerByID");
    }
    @Bean
    public Queue createOwner(){
        return new Queue("createOwner");
    }
    @Bean
    public Queue getNumAllOwners(){
        return new Queue("getNumAllOwners");
    }
    @Bean
    public Queue getAllOwners(){
        return new Queue("getAllOwners");
    }
    @Bean
    public Queue deleteOwnerByID(){
        return new Queue("deleteOwnerByID");
    }
    @Bean
    public Queue changeBirthDateOwner(){
        return new Queue("changeBirthDateOwner");
    }
    @Bean
    public Queue getCat(){
        return new Queue("getCat");
    }
    @Bean
    public Queue createCatWithOwner(){
        return new Queue("createCatWithOwner");
    }
    @Bean
    public Queue getNumAllCats(){
        return new Queue("getNumAllCats");
    }
    @Bean
    public Queue getAllCats(){
        return new Queue("getAllCats");
    }
    @Bean
    public Queue deleteCatByID(){
        return new Queue("deleteCatByID");
    }
    @Bean
    public Queue getCatByColour(){
        return new Queue("getCatByColour");
    }
    @Bean
    public Queue getNumCatByColour(){
        return new Queue("getNumCatByColour");
    }
}
