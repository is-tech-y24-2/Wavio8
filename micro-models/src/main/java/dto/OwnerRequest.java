package dto;

import models.Owner;


import java.io.Serializable;
import java.sql.Timestamp;

public class OwnerRequest implements Serializable {
    private String name;

    private java.sql.Timestamp birthDateOwner;

    public OwnerRequest() {
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

    public Owner toOwner(){
        Owner owner=new Owner();
        owner.setName(this.name);
        owner.setBirthDateOwner(this.birthDateOwner);
        return owner;
    }
}
