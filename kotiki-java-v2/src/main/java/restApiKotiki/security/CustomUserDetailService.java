package restApiKotiki.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import restApiKotiki.models.Account;
import restApiKotiki.service.AccountService;

@Component
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account accountByLogin = accountService.findByLogin(username);
        if (accountByLogin == null) {
            throw new UsernameNotFoundException("Unknown user: " + username);
        }
        UserDetails account = User.builder()
                .username(accountByLogin.getLogin())
                .password(accountByLogin.getPassword())
                .roles(accountByLogin.getRole().toString())
                .build();
        return account;
    }

//    @Override
//    public Account loadUserByUsername(String username) throws UsernameNotFoundException {
//        Account account =accountService.findByLogin(username);
//
//        return account;
//    }
}
