package microKotikiOwner.rabbitMq;

import dto.OwnerRequest;
import dto.OwnerResponse;
import microKotikiOwner.service.OwnerService;
import models.Owner;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@EnableRabbit
@Component
public class RabbitMqListener {
    @Autowired
    OwnerService ownerService;

    @RabbitListener(queues = "getOwnerByID")
    public OwnerResponse getOwnerByID(int id){
        OwnerResponse ownerResponse=new OwnerResponse();
        return ownerResponse.toOwnerResponse(ownerService.getOwnerByID(id));
    }
    @RabbitListener(queues = "createOwner")
    public int createOwner(OwnerRequest owner){
        return ownerService.createOwner(owner.toOwner());
    }
    @RabbitListener(queues = "getAllOwners")
    public OwnerResponse getAllOwners(int i){
        OwnerResponse ownerResponse=new OwnerResponse();
        return ownerResponse.toOwnerResponse(ownerService.getAllOwners().get(i));
    }

    @RabbitListener(queues = "getNumAllOwners")
    public int getNumAllOwners(String t){

        return ownerService.getAllOwners().size()-1;
    }
    @RabbitListener(queues = "deleteOwnerByID")
    public void deleteOwnerByID(int id){
        ownerService.deleteOwnerByID(id);
    }

    @RabbitListener(queues = "changeBirthDateOwner")
    public void changeBirthDateOwner(OwnerResponse owner){
        ownerService.changeBirthDateOwner(owner.getId(), owner.getBirthDateOwner());
    }
    @RabbitListener(queues = "getOwnerByIDForCat")
    public OwnerResponse getOwnerByIDForCat(int id){
        OwnerResponse ownerResponse=new OwnerResponse();
        return ownerResponse.toOwnerResponse(ownerService.getOwnerByID(id));
    }


}
