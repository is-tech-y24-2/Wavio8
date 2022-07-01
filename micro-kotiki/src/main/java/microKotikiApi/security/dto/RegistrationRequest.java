package microKotikiApi.security.dto;

import models.Account;


public class RegistrationRequest {

    private String login;

    private String password;


    public RegistrationRequest() {
    }

    public RegistrationRequest(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account toAccount(){
        Account account=new Account();
        account.setPassword(this.password);
        account.setLogin(this.login);
        return account;
    }

}
