package project.service;

import project.persistence.entities.User;

public interface UserManagementService {

    /**
     * Save a {@link User}
     * @param user {@link User} to be saved
     * @return {@link User} that was saved
     */
    User save(User user);

    /**
     * Delete {@link User}
     * @param user {@link User} to be deleted
     */
    void delete(User user);

    /**
     * Finds {@link User} by username
     * @param userName
     * @return
     */
    User findByUserName(String userName );

    /**
     * Find User by User Id {@link User}
     * @param userId
     * @return
     */
     User findByUserId(Long userId);


    /**
     * Checks if username and pw is correct
     * @param username
     * @param password
     * @return
     *
     Boolean validateUser(String username, String password);
    */

    /**
     * Returns true if user is logged in
     * @param loggedIn
     * @return
     */
     Boolean isLogedIn(Boolean loggedIn);

}
