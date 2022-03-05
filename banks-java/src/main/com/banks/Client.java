package com.banks;

public class Client {
    private String name;
    private String surname;
    private String address;
    private int passportNumber;

    public Client(String name, String surname, String address, int passportNumber) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.passportNumber = passportNumber;
    }
    public String getAddress(){
        return address;
    }
    public int getPassportNumber(){
        return passportNumber;
    }


}
