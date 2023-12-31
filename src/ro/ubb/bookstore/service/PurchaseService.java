package ro.ubb.bookstore.service;

import ro.ubb.bookstore.domain.*;
import ro.ubb.bookstore.domain.validators.PurchaseValidator;
import ro.ubb.bookstore.domain.validators.ValidatorException;
import ro.ubb.bookstore.repository.Repository;
import ro.ubb.bookstore.repository.database.BookDatabaseRepository;
import ro.ubb.bookstore.repository.database.ClientDatabaseRepository;
import ro.ubb.bookstore.repository.database.PurchaseDatabaseRepository;

import java.sql.SQLException;
import java.util.*;

public class PurchaseService {
    private final Repository<Long, Purchase> purchaseRepository;
    private final Repository<Long, Book> bookDatabaseRepository;
    private final Repository<Long, Client> clientDatabaseRepository;

    public PurchaseService(Repository<Long, Purchase> purchaseDatabaseRepository, Repository<Long, Book>
            bookDatabaseRepository, Repository<Long, Client> clientDatabaseRepository) {
        this.purchaseRepository = purchaseDatabaseRepository;
        this.bookDatabaseRepository = bookDatabaseRepository;
        this.clientDatabaseRepository = clientDatabaseRepository;
    }

    public void addPurchase(Purchase purchase) throws SQLException {
        this.purchaseRepository.save(purchase);
    }

    public Set<Purchase> getAllPurchases() throws SQLException {
        return new HashSet<>((Collection) purchaseRepository.findAll());
    }

    public Optional<Purchase> readOnePurchase(Long id) throws ValidatorException, SQLException {
        return this.purchaseRepository.findOne(id);
    }

    public void deleteOnePurchase(Long id) throws SQLException {
        purchaseRepository.delete(id);
    }

    public void updatePurchase(Purchase purchase) throws ValidatorException, SQLException {
        this.purchaseRepository.update(purchase);
    }



    public List<BookProfitabilityDTO> getBookProfitability() throws SQLException {
        Map<String, Double> profitability = new HashMap<>();
        List<Purchase> purchaseList = (List<Purchase>) purchaseRepository.findAll();

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
        List<Purchase> purchaseList = (List<Purchase>) purchaseRepository.findAll();
        Map<String, Double> spending = new HashMap<>();

        for (Purchase purchase : purchaseList) {
            long clientId = purchase.getClientId();
            long bookId = purchase.getBookId();


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
