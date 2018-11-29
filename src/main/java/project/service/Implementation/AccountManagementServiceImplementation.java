package project.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.Account;
import project.persistence.entities.Transaction;
import project.persistence.entities.User;
import project.persistence.repositories.AccountRepository;
import project.persistence.repositories.TransactionRepository;
import project.service.AccountManagementService;


import java.lang.reflect.Array;
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

    @Override
    public Account findAccountByUsers(String user1, String user2)
        {return repository.findAccountByUsers(user1, user2);}

    @Override
    public void updateBalance(Double ammount, Account account){
        Double newAmmount = account.getNetBalance()+ammount;
        account.setNetBalance(newAmmount);
        repository.save(account);
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
    public List<Account> findByUsername(String username) {
        List<Account> allAccounts = repository.findAll();

        List<Account> userAccounts = new ArrayList<Account>();

        for (Account account: allAccounts){
            if(account.getUser1().equals(username) || account.getUser2().equals(username)){
                userAccounts.add(account);
            }
        }
        return userAccounts;
    }

/*
    @Override
    public void createNew(User user1, User user2) {
        Account account = new Account();
        User[] users = {user1, user2};
        account.setUsers(users);
        account.setNetBalance(0.0);
        save(account);
    }*/
}
