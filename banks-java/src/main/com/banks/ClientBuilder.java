package com.banks;

public class ClientBuilder {
    private String name;
    private String surname;
    private String address;
    private int passportNumber;

    public ClientBuilder addNameAndSurname(String name, String surname) {
        this.name = name;
        this.surname = surname;
        return this;
    }

    public ClientBuilder addAddress(String address) {
        this.address = address;
        return this;
    }

    public ClientBuilder addPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
        return this;
    }

    public Client makeClient() {
        return new Client(name, surname, address, passportNumber);
    }
}
