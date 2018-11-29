package project.service;

import project.persistence.entities.Transaction;

import java.util.List;

public interface TransactionManagementService {

    /**
     * Save a {@link Transaction}
     * @param Transaction {@link Transaction} to be saved
     * @return {@link Transaction} that was saved
     */
    Transaction save(Transaction Transaction);

    /**
     * Delete {@link Transaction}
     * @param Transaction {@link Transaction} to be deleted
     */
    void delete(Transaction Transaction);

    List<Transaction> findBySplitId(Long splitId);

    /**
     * Get all {@link Transaction}s
     * @return A list of {@link Transaction}s
     */
    List<Transaction> findAll();

    /**
     * Find one  {@link Transaction}
     * @param transactionId
     * @return
     */
    Transaction findOne(Long transactionId);

    /**
     * Find transaction by User Id {@link Transaction}
     * @param userId
     * @return
     */
    /*List<Transaction> findByUserId(Long userId);*/
}
