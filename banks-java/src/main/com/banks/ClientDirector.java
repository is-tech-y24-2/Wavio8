package com.banks;

public class ClientDirector {
    private ClientBuilder clientBuilder;

    public ClientDirector(ClientBuilder clientBuilder, String nameClient, String surname) {
        this.clientBuilder = clientBuilder;
        this.clientBuilder.addNameAndSurname(nameClient, surname);
    }

    public ClientBuilder getClientBuilder() {
        return clientBuilder;
    }
}