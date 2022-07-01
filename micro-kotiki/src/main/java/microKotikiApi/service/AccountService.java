package microKotikiApi.service;

import microKotikiApi.repository.AccountRepository;
import models.Account;
import models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Objects;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;


    public void saveAccount(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        if (!accountRepository.existsAccountByRole(Role.ADMIN.toString())) {
            account.setRole(Role.ADMIN);
        }
        accountRepository.save(account);
    }

    public Account findByLogin(String login) {
        return accountRepository.findByLogin(login);
    }

    public Account findByLoginAndPassword(String login, String password) {
        Account account = findByLogin(login);
        if (account != null) {
            if ((passwordEncoder.matches(password, account.getPassword()))) {
                return account;
            }
        }
        return null;
    }

    public void deleteUserByLogin(String login) {
        if (accountRepository.existsAccountByLogin(login)) {
            accountRepository.deleteById(accountRepository.findByLogin(login).getId());
        }
    }

    public boolean checkUserRole(String role) {
        return Objects.equals(role, "USER");
    }

    public void createUser(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setRole(Role.USER);
        accountRepository.save(account);
    }

}
