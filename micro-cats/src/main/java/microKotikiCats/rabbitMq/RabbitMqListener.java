package microKotikiCats.rabbitMq;

import dto.*;
import microKotikiCats.service.CatsService;

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
    CatsService catsService;

    @RabbitListener(queues = "getCat")
    public CatResponse getCat(int id){
        CatResponse catResponse=new CatResponse();
        return catResponse.toCatResponse(catsService.getCatById(id));
    }
    @RabbitListener(queues = "createCatWithOwner")
    public int createCatWithOwner(CatRequest catRequest){
        return catsService.createCatWithOwner(catRequest.toCat(),catRequest.getIdOwner());
    }

    @RabbitListener(queues = "getNumAllCats")
    public int getNumAllCats(String t){

        return catsService.getAllCats().size()-1;
    }
    @RabbitListener(queues = "getAllCats")
    public CatResponse getAllCats(int i){
        CatResponse catResponse=new CatResponse();
        return catResponse.toCatResponse(catsService.getAllCats().get(i));
    }
    @RabbitListener(queues = "deleteCatByID")
    public void deleteCatByID(int id){
        catsService.deleteCatByID(id);
    }
    @RabbitListener(queues = "getNumCatByColour")
    public int getNumCatByColour( AccountResponse accountResponse){

        return catsService.getCatByColour(accountResponse.getColour()).size()-1;
    }
//    @RabbitListener(queues = "getAllOwners")
//    public OwnerResponse getAllOwners(int i){
//        OwnerResponse ownerResponse=new OwnerResponse();
//        return ownerResponse.toOwnerResponse(ownerService.getAllOwners().get(i));
//    }
//
//    @RabbitListener(queues = "getNumAllOwners")
//    public int getNumAllOwners(String t){
//
//        return ownerService.getAllOwners().size()-1;
//    }

//
//    @RabbitListener(queues = "changeBirthDateOwner")
//    public void changeBirthDateOwner(OwnerResponse owner){
//        ownerService.changeBirthDateOwner(owner.getId(), owner.getBirthDateOwner());
//    }

}