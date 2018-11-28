package project.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.persistence.entities.User;
import project.persistence.repositories.UserRepository;
import project.service.UserManagementService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserManagementServiceImplementation implements UserManagementService {

    // Instance Variables
    private UserRepository repository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //Dependency Injection
    @Autowired
    public UserManagementServiceImplementation(UserRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.repository = repository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        List<User> friendlist = new ArrayList<>();
        user.setFriendlist(friendlist);
        return repository.save(user);
    }

    @Override
    public void delete(User user) {
        repository.delete(user);
    }


    @Override
    public User findByUsername(String username) {
        /*User isak = new User();
        isak.setUserName("Sakkattack");
        isak.setEmail("mymail@mail.com");
        isak.setFirstName("Ísak");
        isak.setLastName("Kolbeins");
        return isak ; //*/
        return repository.findByUsername(username);
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

    @Override
    public void addFriend(User user, User friend) {
        List<User> userFriends = user.getFriendlist();
        if (userFriends == null){
            userFriends = new ArrayList<User>();
        }
        userFriends.add(friend);
        user.setFriendlist(userFriends);

        List<User> friendFriends = friend.getFriendlist();
        if (friendFriends == null){
            friendFriends = new ArrayList<User>();
        }
        friendFriends.add(user);
        friend.setFriendlist(friendFriends);
        repository.save(user);
        repository.save(friend);
    }
}

