package dto;

import models.Cat;
import models.Owner;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class CatResponse implements Serializable {
    private int id;
    private String name;

    private java.sql.Timestamp birthDateOwner;
    private String colour;
    private String bread;
    private OwnerResponse owner;
//    private List<Cat> friends;

    public CatResponse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getBirthDateOwner() {
        return birthDateOwner;
    }

    public void setBirthDateOwner(Timestamp birthDateOwner) {
        this.birthDateOwner = birthDateOwner;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getBread() {
        return bread;
    }

    public void setBread(String bread) {
        this.bread = bread;
    }

    public OwnerResponse getOwner() {
        return owner;
    }

    public void setOwner(OwnerResponse owner) {
        this.owner = owner;
    }
//
//    public List<Cat> getFriends() {
//        return friends;
//    }
//
//    public void setFriends(List<Cat> friends) {
//        this.friends = friends;
//    }

    public CatResponse toCatResponse(Cat cat){
        CatResponse catResponse=new CatResponse();
        catResponse.setId(cat.getId());
        catResponse.setBread(cat.getBread());
        catResponse.setColour(cat.getColour());
//        catResponse.setFriends(cat.getFriends());
        catResponse.setBirthDateOwner(cat.getBirthDate());
        OwnerResponse ownerResponse=new OwnerResponse();
        catResponse.setOwner(ownerResponse.toOwnerResponse(cat.getOwner()));
        catResponse.setName(cat.getName());

        return catResponse;
    }
}