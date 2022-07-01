package dto;

import models.Cat;


import java.io.Serializable;
import java.sql.Timestamp;

public class CatRequest implements Serializable {
    private String name;

    private java.sql.Timestamp birthDateCat;
    private String colour;
    private String bread;
    private int idOwner;

    public int getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(int idOwner) {
        this.idOwner = idOwner;
    }

    public CatRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getBirthDateCat() {
        return birthDateCat;
    }

    public void setBirthDateCat(Timestamp birthDateCat) {
        this.birthDateCat = birthDateCat;
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

    public Cat toCat(){
        Cat cat=new Cat();
        cat.setName(this.name);
        cat.setBirthDate(this.birthDateCat);
        cat.setColour(this.colour);
        cat.setBread(this.bread);
        return cat;
    }
}
