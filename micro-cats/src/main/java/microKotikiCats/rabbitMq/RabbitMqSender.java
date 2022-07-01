package microKotikiCats.rabbitMq;

import dto.CatResponse;
import dto.OwnerRequest;
import dto.OwnerResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RabbitMqSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public OwnerResponse getOwnerByIDForCat(int id){
        return (OwnerResponse) rabbitTemplate.convertSendAndReceive("getOwnerByIDForCat",id);
    }




}