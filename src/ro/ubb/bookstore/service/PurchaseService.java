package ro.ubb.bookstore.service;

import ro.ubb.bookstore.domain.*;
import ro.ubb.bookstore.domain.validators.PurchaseValidator;
import ro.ubb.bookstore.domain.validators.ValidatorException;
import ro.ubb.bookstore.repository.database.BookDatabaseRepository;
import ro.ubb.bookstore.repository.database.ClientDatabaseRepository;
import ro.ubb.bookstore.repository.database.PurchaseDatabaseRepository;

import java.sql.SQLException;
import java.util.*;

public class PurchaseService {
    private final PurchaseDatabaseRepository purchaseDatabaseRepository;
    private final BookDatabaseRepository bookDatabaseRepository;
    private final ClientDatabaseRepository clientDatabaseRepository;
    private final PurchaseValidator purchaseValidator;

    public PurchaseService(PurchaseDatabaseRepository purchaseDatabaseRepository, BookDatabaseRepository
            bookDatabaseRepository, ClientDatabaseRepository clientDatabaseRepository, PurchaseValidator purchaseValidator) {
        this.purchaseDatabaseRepository = purchaseDatabaseRepository;
        this.bookDatabaseRepository = bookDatabaseRepository;
        this.clientDatabaseRepository = clientDatabaseRepository;
        this.purchaseValidator = purchaseValidator;
    }

    public void addPurchase(Purchase purchase) throws SQLException {
        this.purchaseValidator.validate(purchase, bookDatabaseRepository, clientDatabaseRepository);
        this.purchaseDatabaseRepository.save(purchase);
    }

    public Set<Purchase> getAllPurchases() throws SQLException {
        Set<Purchase> purchases = new HashSet<>();
        purchaseDatabaseRepository.findAll().forEach(purchases::add);
        return purchases;
    }

    public Optional<Purchase> readOnePurchase(Long bookId, Long clientId) throws ValidatorException, SQLException {
        return this.purchaseDatabaseRepository.findOne(bookId, clientId);
    }

    public void deleteOnePurchase(Long bookId, Long clientId) throws SQLException {
        purchaseDatabaseRepository.delete(bookId, clientId);
    }

    public void updatePurchase(Purchase purchase) throws ValidatorException, SQLException {
        this.purchaseValidator.validate(purchase, bookDatabaseRepository, clientDatabaseRepository);
        this.purchaseDatabaseRepository.update(purchase);
    }

}
