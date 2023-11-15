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

    public Optional<Purchase> readOnePurchase(Long id) throws ValidatorException, SQLException {
        return this.purchaseDatabaseRepository.findOne(id);
    }

    public void deleteOnePurchase(Long id) throws SQLException {
        purchaseDatabaseRepository.delete(id);
    }

    public void updatePurchase(Purchase purchase) throws ValidatorException, SQLException {
        this.purchaseDatabaseRepository.update(purchase);
    }



    public List<BookProfitabilityDTO> getBookProfitability() throws SQLException {
        Map<String, Double> profitability = new HashMap<>();
        List<Purchase> purchaseList = purchaseDatabaseRepository.findAll();

        for (Purchase purchase : purchaseList) {
            long bookId = purchase.getBookId();
            bookDatabaseRepository.findOne(bookId).ifPresent(book -> {
                String bookTitle = book.getTitle();
                double revenue = purchase.getNumberSold() * book.getPrice();
                profitability.merge(bookTitle, revenue, Double::sum);

            });
        }


        List<BookProfitabilityDTO> res = new ArrayList<>();
        for (String bookTitle : profitability.keySet()) {
            res.add(new BookProfitabilityDTO(bookTitle, profitability.get(bookTitle)));
        }

        res.sort(Comparator.comparingDouble(BookProfitabilityDTO::getBookProfitability).reversed());


        return res;
    }

    public List<ClientSpendingDTO> getClientSpending() throws SQLException {
        List<Purchase> purchaseList = purchaseDatabaseRepository.findAll();
        Map<String, Double> spending = new HashMap<>();

        for (Purchase purchase : purchaseList) {
            long clientId = purchase.getClientId();
            long bookId = purchase.getBookId();

//            String clientName="";
//            double amount = 0.0;

            clientDatabaseRepository.findOne(clientId).ifPresent(client -> {
               String clientName = client.getFirstName() + " " + client.getLastName();

                try {
                    bookDatabaseRepository.findOne(bookId).ifPresent(book -> {
                       double amount = purchase.getNumberSold() * book.getPrice();

                    spending.merge(clientName, amount, Double::sum);

                    });
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });

        }

        List<ClientSpendingDTO> res = new ArrayList<>();
        for (String clientName : spending.keySet()) {
            res.add(new ClientSpendingDTO(clientName, spending.get(clientName)));
        }

        res.sort(Comparator.comparingDouble(ClientSpendingDTO::getAmount).reversed());

        return res;



    }




}
