package restApiKotiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restApiKotiki.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByLogin(String login);
    Account findAccountByRole(String role);

    void deleteAccountByLogin(String login);

    boolean existsAccountByLogin(String login);

    boolean existsAccountByRole(String role);
}
