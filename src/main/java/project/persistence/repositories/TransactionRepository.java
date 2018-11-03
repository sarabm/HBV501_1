package project.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.persistence.entities.Transaction;

import java.util.List;

/**
 * By extending the {@link JpaRepository} we have access to powerful methods.
 * For detailed information, see:
 * http://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
 * http://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
 *
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Transaction save(Transaction Transaction);

    void delete(Transaction Transaction);

    List<Transaction> findAll();


    @Query(value = "SELECT p FROM Transaction p WHERE p.id = ?1")
    Transaction findOne(Long id);

    @Query(value = "SELECT p FROM Transaction p WHERE p.id = ?1")
    Transaction findByUserId(Long id);

}
