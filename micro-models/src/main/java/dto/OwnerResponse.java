package dto;

import models.Owner;


import java.io.Serializable;
import java.sql.Timestamp;

public class OwnerResponse implements Serializable {
    private int id;
    private String name;
    private java.sql.Timestamp birthDateOwner;

    public OwnerResponse() {
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

    public OwnerResponse toOwnerResponse(Owner owner){
        OwnerResponse ownerResponse=new OwnerResponse();
        ownerResponse.setId(owner.getId());
        ownerResponse.setName(owner.getName());
        ownerResponse.setBirthDateOwner(owner.getBirthDateOwner());
        return ownerResponse;
    }
}
