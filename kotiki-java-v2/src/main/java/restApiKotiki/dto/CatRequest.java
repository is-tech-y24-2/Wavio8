package restApiKotiki.dto;

import restApiKotiki.models.Cat;
import restApiKotiki.models.Owner;

import java.sql.Timestamp;

public class CatRequest {
    private String name;

    private java.sql.Timestamp birthDateOwner;
    private String colour;
    private String bread;

    public CatRequest() {
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

    public Cat toCat(){
        Cat cat=new Cat();
        cat.setName(this.name);
        cat.setBirthDate(this.birthDateOwner);
        cat.setColour(this.colour);
        cat.setBread(this.bread);
        return cat;
    }
}
