package project.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.Account;
import project.persistence.entities.Transaction;
import project.persistence.entities.User;
import project.persistence.repositories.AccountRepository;
import project.service.AccountManagementService;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AccountManagementServiceImplementation implements AccountManagementService {

    // Instance Variables
    AccountRepository repository;

    //Dependency Injection
    @Autowired
    public AccountManagementServiceImplementation(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Account save(Account account) {
        return repository.save(account);
    }

    @Override
    public void delete(Account account) {
        repository.delete(account);
    }

    @Override
    public List<Account> findAll() {
        return repository.findAll();
    }


    @Override
    public Account findOne(Long accountId) {
        return repository.findOne(accountId);
    }

  /*
    @Override
    public Transaction getTransactions(Account account){
        return List<Transaction>;
    } */

    /*@Override
    public Account findByUser(User user) {
        return  repository.findByUserId(user);
    }*/

    @Override
    public List<Account> findByUser(User user) {
        List<Account> allAccounts = repository.findAll();

        List<Account> userAccounts = new ArrayList<Account>();

        for (Account account: allAccounts){
            if(account.getUsers()[0].equals(user) || account.getUsers()[0].equals(user)){
                userAccounts.add(account);
            }
        }

        return userAccounts;
    }

}
