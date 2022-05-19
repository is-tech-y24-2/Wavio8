package restApiKotiki.security.dto;

import restApiKotiki.models.Account;
import restApiKotiki.models.Owner;

public class RegistrationUserRequest {

    private String login;

    private String password;

    private Owner owner;


    public RegistrationUserRequest() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Account toAccount(){
        Account account=new Account();
        account.setPassword(this.password);
        account.setLogin(this.login);
        account.setOwner(this.owner);
        return account;
    }
}
