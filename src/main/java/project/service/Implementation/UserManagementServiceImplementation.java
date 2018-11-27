package project.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.User;
import project.persistence.repositories.UserRepository;
import project.service.UserManagementService;

import java.util.List;

@Service
public class UserManagementServiceImplementation implements UserManagementService {

    // Instance Variables
    UserRepository repository;


    //Dependency Injection
    @Autowired
    public UserManagementServiceImplementation(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public void delete(User user) {
        repository.delete(user);
    }


    @Override
    public User findByUserName(String userName) {
        User isak = new User();
        isak.setUserName("Sakkattack");
        isak.setEmail("mymail@mail.com");
        isak.setFirstName("Ísak");
        isak.setLastName("Kolbeins");
        return isak ; //repository.findByUserName(userName);
    }

    @Override
    public User findByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

   /* @Override
    public Boolean validateUser(String username, String password) {
        //if false return errormessage
        return repository.validateUser(username, password);
    }*/

    @Override
    public Boolean isLogedIn(Boolean loggedIn){
        //í módelinu?
        //if false
        // validateUse
        return true;
    }

    @Override
    public List<User> getFriends(User user){
        // Returns lit of user friens
        return user.getFriendlist();
    }
}

