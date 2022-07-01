package microKotikiApi.rabbitMq;

import dto.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RabbitMqSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public OwnerResponse getOwnerByID(int id){
        return (OwnerResponse) rabbitTemplate.convertSendAndReceive("getOwnerByID",id);
    }

    public int createOwner(OwnerRequest owner){
        var ans=rabbitTemplate.convertSendAndReceive("createOwner",owner);

        if(ans!=null){
            return (Integer)ans;
        }else {
            return -1;
        }
    }
    public int getNumAllOwners(){
        var ans=rabbitTemplate.convertSendAndReceive("getNumAllOwners","t");
        if(ans!=null){
            return (Integer)ans;
        }else {
            return 0;
        }

    }
    public List<OwnerResponse> getAllOwners(){
        int count=getNumAllOwners();
        List<OwnerResponse> ownerResponses=new ArrayList<>();
        for (int i=0;i<count;i++) {
            ownerResponses.add((OwnerResponse) rabbitTemplate.convertSendAndReceive("getAllOwners",i));
        }
        return ownerResponses;

    }
    public void deleteOwnerByID(int id){
        rabbitTemplate.convertSendAndReceive("deleteOwnerByID",id);
    }
    public void changeBirthDateOwner(int id, java.sql.Timestamp birthDateOwner){
        OwnerResponse owner = getOwnerByID(id);
        owner.setBirthDateOwner(birthDateOwner);
        rabbitTemplate.convertSendAndReceive("changeBirthDateOwner",owner);
    }

    public CatResponse getCat(int id){
        return  (CatResponse) rabbitTemplate.convertSendAndReceive("getCat", id);
    }
    public int createCatWithOwner(CatRequest catRequest){
        var ans=rabbitTemplate.convertSendAndReceive("createCatWithOwner",catRequest);

        if(ans!=null){
            return (Integer)ans;
        }else {
            return -1;
        }
    }
    public int getNumAllCats(){
        var ans=rabbitTemplate.convertSendAndReceive("getNumAllCats","t");
        if(ans!=null){
            return (Integer)ans;
        }else {
            return 0;
        }
    }

    public List<CatResponse> getAllCats(){
        int count=getNumAllCats();
        List<CatResponse> catResponses=new ArrayList<>();
        for (int i=0;i<count;i++) {
            catResponses.add((CatResponse) rabbitTemplate.convertSendAndReceive("getAllCats",i));
        }
        return catResponses;

    }
    public void deleteCatByID(int id){
        rabbitTemplate.convertSendAndReceive("deleteCatByID",id);
    }
    public void getCatByColour( AccountResponse accountResponse){
        rabbitTemplate.convertSendAndReceive("getCatByColour",accountResponse);
    }
    public int getNumCatByColour(AccountResponse accountResponse){
        var ans=rabbitTemplate.convertSendAndReceive("getNumCatByColour","t");
        if(ans!=null){
            return (Integer)ans;
        }else {
            return 0;
        }
    }
}
