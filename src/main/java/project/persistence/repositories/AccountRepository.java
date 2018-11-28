package project.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.persistence.entities.Account;

import java.util.List;

/**
 * By extending the {@link JpaRepository} we have access to powerful methods.
 * For detailed information, see:
 * http://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
 * http://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
 *
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account save(Account Account);

    void delete(Account Account);

    List<Account> findAll();


    @Query(value = "SELECT p FROM Account p WHERE p.id = ?1")
    Account findOne(Long id);

    // @Query(value = "SELECT * p FROM Account p WHERE p.id = ?*")
    // List<Account> findByUserId(Long id);


    //Account findByUser(User user);

}
