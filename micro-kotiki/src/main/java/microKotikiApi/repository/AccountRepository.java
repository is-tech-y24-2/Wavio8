package microKotikiApi.repository;

import models.Account;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByLogin(String login);
    Account findAccountByRole(String role);

    void deleteAccountByLogin(String login);

    boolean existsAccountByLogin(String login);

    boolean existsAccountByRole(String role);
}

