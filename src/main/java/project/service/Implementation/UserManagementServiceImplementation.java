package project.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.User;
import project.persistence.repositories.UserRepository;
import project.service.UserManagementService;

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
        return  repository.findByUserName(userName);
    }

    @Override
    public User findByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

}

