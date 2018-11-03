package project.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.Transaction;
import project.persistence.repositories.TransactionRepository;
import project.service.TransactionManagementService;


import java.util.Collections;
import java.util.List;

@Service
public class TransactionManagementServiceImplementation implements TransactionManagementService {

    // Instance Variables
    TransactionRepository repository;

    //Dependency Injection
    @Autowired
    public TransactionManagementServiceImplementation(TransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Transaction save(Transaction Transaction) {
        return repository.save(Transaction);
    }

    @Override
    public void delete(Transaction Transaction) {
        repository.delete(Transaction);
    }

    @Override
    public List<Transaction> findAll() {
        return repository.findAll();
    }


    @Override
    public Transaction findOne(Long transactionId) {
        return repository.findOne(transactionId);
    }
/*
    @Override
    public Transaction findByUserId(Long userId) {
        return repository.findByUserId(userId);
    }
*/
}
