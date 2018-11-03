package project.service;

import project.persistence.entities.Account;
import project.persistence.entities.Account;
import project.persistence.entities.User;

import java.util.List;

public interface AccountManagementService {

    /**
     * Save a {@link Account}
     * @param Account {@link Account} to be saved
     * @return {@link Account} that was saved
     */
    Account save(Account Account);

    /**
     * Delete {@link Account}
     * @param Account {@link Account} to be deleted
     */
    void delete(Account Account);

    /**
     * Get all {@link Account}s
     * @return A list of {@link Account}s
     */
    List<Account> findAll();

    /**
     * Find one  {@link Account}
     * @param accountId
     * @return
     */
    Account findOne(Long accountId);

    /**
     * Find by user {@link Account}
     * @param user
     * @return
     */
    //Account findByUser(User user);

    /**
     * Find by userId {@link Account}
     *      * @param User
     * @param userId
     * @return
     */
    Account findByUserId(Long userId);

}
