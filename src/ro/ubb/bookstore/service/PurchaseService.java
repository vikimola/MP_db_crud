package ro.ubb.bookstore.service;

import ro.ubb.bookstore.domain.*;
import ro.ubb.bookstore.domain.validators.PurchaseValidator;
import ro.ubb.bookstore.domain.validators.ValidatorException;
import ro.ubb.bookstore.repository.BookDatabaseRepository;
import ro.ubb.bookstore.repository.ClientFileRepository;
import ro.ubb.bookstore.repository.PurchaseFileRepository;

import java.sql.SQLException;
import java.util.*;

public class PurchaseService {
    private final PurchaseFileRepository purchaseFileRepository;
    private final BookDatabaseRepository bookDatabaseRepository;
    private final ClientFileRepository clientFileRepository;
    private final PurchaseValidator purchaseValidator;
    public PurchaseService(PurchaseFileRepository purchaseFileRepository, BookDatabaseRepository
                           bookFileRepository, ClientFileRepository clientFileRepository, PurchaseValidator purchaseValidator){
        this.purchaseFileRepository=purchaseFileRepository;
        this.bookDatabaseRepository = bookFileRepository;
        this.clientFileRepository = clientFileRepository;
        this.purchaseValidator = purchaseValidator;
    }
    public void addPurchase(Purchase purchase) throws SQLException {
//        Long newId = findHighestExistingId() +1;
//        purchase.setId(newId);
        this.purchaseValidator.validate(purchase, bookDatabaseRepository, clientFileRepository);
        this.purchaseFileRepository.save(purchase);
    }

//    private Long findHighestExistingId() {
//        Set<Purchase> purchases = getAllPurchases();
//        Long highestId = 0L;
//
//        for (Purchase purchase : purchases) {
//            Long purchaseId = purchase.getId();
//            if (purchaseId > highestId) {
//                highestId = purchaseId;
//            }
//        }
//
//        return highestId;
//    }

    public Set<Purchase> getAllPurchases(){
        Set<Purchase> purchases = new HashSet<>();
        purchaseFileRepository.findAll().forEach(purchases::add);
        return purchases;
    }
    public Optional<Purchase> readOnePurchase(Long id) throws ValidatorException {
        return this.purchaseFileRepository.findOne(id);
    }
    public void deleteOnePurchase(Long id){
        purchaseFileRepository.delete(id);
    }
    public void updatePurchase(Purchase purchase) throws ValidatorException, SQLException {
        this.purchaseValidator.validate(purchase, bookDatabaseRepository, clientFileRepository);
        this.purchaseFileRepository.update(purchase);
    }

//    public List<BookProfitabilityDTO> getBookProfitability() {
//        Iterable<Purchase> purchaseList = purchaseFileRepository.findAll();
//        Map<Long, Double> bookIdToRevenue = new HashMap();
//
//        for (Purchase purchase : purchaseList) {
//            long bookId = purchase.getBookId();
//
//            Optional<Book> bookOptional = bookDatabaseRepository.findOne(bookId);
//            if (bookOptional.isPresent()) {
//                Book book = bookOptional.get();
//                String bookTitle = book.getTitle();
//                double revenue = purchase.getNumberSold() * book.getPrice();
//
//                bookIdToRevenue.put(bookId, bookIdToRevenue.getOrDefault(bookId, 0.0) + revenue);
//            }
//        }
//
//        List<BookProfitabilityDTO> res = new ArrayList<>();
//        bookIdToRevenue.entrySet().stream()
//                .sorted(Map.Entry.<Long, Double>comparingByValue().reversed())
//                .forEach(entry -> {
//                    long bookId = entry.getKey();
//                    double profitability = entry.getValue();
//                    Optional<Book> bookOptional = bookDatabaseRepository.findOne(bookId);
//                    if (bookOptional.isPresent()) {
//                        Book book = bookOptional.get();
//                        String bookTitle = book.getTitle();
//                        res.add(new BookProfitabilityDTO(bookTitle, profitability));
//                    }
//                });
//
//        return res;
//    }
//
//public List<ClientSpendingDTO> getClientSpending() {
//    Iterable<Purchase> purchaseList = purchaseFileRepository.findAll();
//    Map<Long, Double> clientIdToSpending = new HashMap<>();
//
//    for (Purchase purchase : purchaseList) {
//        long clientId = purchase.getClientId();
//        long bookId = purchase.getBookId();
//
//        // Retrieve client information from clientFileRepository
//        Optional<Client> clientOptional = clientFileRepository.findOne(clientId);
//        if (clientOptional.isPresent()) {
//            Client client = clientOptional.get();
//            String clientName = client.getFirstName() + " " + client.getLastName();
//
//            double amount = purchase.getNumberSold() * bookDatabaseRepository.findOne(bookId).get().getPrice();
//
//            clientIdToSpending.put(clientId, clientIdToSpending.getOrDefault(clientId, 0.0) + amount);
//        }
//    }
//
//    List<ClientSpendingDTO> res = new ArrayList<>();
//    clientIdToSpending.entrySet().stream()
//            .sorted(Map.Entry.<Long, Double>comparingByValue().reversed())
//            .forEach(entry -> {
//                long clientId = entry.getKey();
//                double spending = entry.getValue();
//                Optional<Client> clientOptional = clientFileRepository.findOne(clientId);
//                if (clientOptional.isPresent()) {
//                    Client client = clientOptional.get();
//                    String clientName = client.getFirstName() + " " + client.getLastName();
//                    res.add(new ClientSpendingDTO(clientName, spending));
//                }
//            });
//
//    return res;
//}

}
