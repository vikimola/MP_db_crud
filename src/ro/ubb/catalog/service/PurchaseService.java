package ro.ubb.catalog.service;

import ro.ubb.catalog.domain.*;
import ro.ubb.catalog.domain.validators.ValidatorException;
import ro.ubb.catalog.repository.BookFileRepository;
import ro.ubb.catalog.repository.ClientFileRepository;
import ro.ubb.catalog.repository.PurchaseFileRepository;

import java.util.*;

public class PurchaseService {
    private PurchaseFileRepository purchaseFileRepository;
    private BookFileRepository bookFileRepository;
    private ClientFileRepository clientFileRepository;
    public PurchaseService(PurchaseFileRepository purchaseFileRepository, BookFileRepository
                           bookFileRepository, ClientFileRepository clientFileRepository){
        this.purchaseFileRepository=purchaseFileRepository;
        this.bookFileRepository = bookFileRepository;
        this.clientFileRepository = clientFileRepository;
    }
    public void addPurchase(Purchase purchase){
        Long newId = findHighestExistingId() +1;
        purchase.setId(newId);
        this.purchaseFileRepository.save(purchase);
    }

    private Long findHighestExistingId() {
        Set<Purchase> purchases = getAllPurchases();
        Long highestId = 0L;

        for (Purchase purchase : purchases) {
            Long purchaseId = purchase.getId();
            if (purchaseId > highestId) {
                highestId = purchaseId;
            }
        }

        return highestId;
    }

    public Set<Purchase> getAllPurchases(){
        Set<Purchase> purchases = new HashSet<>();
        purchaseFileRepository.findAll().forEach(purchases::add);
        return purchases;
    }
    public Optional<Purchase> readOnePurchase(Long id) throws ValidatorException {
        return this.purchaseFileRepository.findOne(id);
    }
    public Optional<Purchase> deleteOnePurchase(Long id){
        return purchaseFileRepository.delete(id);
    }
    public Optional<Purchase> updatePurchase(Purchase purchase) throws ValidatorException{
        return purchaseFileRepository.update(purchase);
    }
    public List<BookProfitabilityDTO> getBookProfitability() {
        Iterable<Purchase> purchaseList = purchaseFileRepository.findAll();
        Map<Long, Double> bookIdToRevenue = new HashMap();

        for (Purchase purchase : purchaseList) {
            long bookId = purchase.getBookId();

            Optional<Book> bookOptional = bookFileRepository.findOne(bookId);
            if (bookOptional.isPresent()) {
                Book book = bookOptional.get();
                String bookTitle = book.getTitle();
                double revenue = purchase.getNumberSold() * book.getPrice();

                bookIdToRevenue.put(bookId, bookIdToRevenue.getOrDefault(bookId, 0.0) + revenue);
            }
        }

        List<BookProfitabilityDTO> res = new ArrayList<>();
        bookIdToRevenue.entrySet().stream()
                .sorted(Map.Entry.<Long, Double>comparingByValue().reversed())
                .forEach(entry -> {
                    long bookId = entry.getKey();
                    double profitability = entry.getValue();
                    Optional<Book> bookOptional = bookFileRepository.findOne(bookId);
                    if (bookOptional.isPresent()) {
                        Book book = bookOptional.get();
                        String bookTitle = book.getTitle();
                        res.add(new BookProfitabilityDTO(bookTitle, profitability));
                    }
                });

        return res;
    }

public List<ClientSpendingDTO> getClientSpending() {
    Iterable<Purchase> purchaseList = purchaseFileRepository.findAll();
    Map<Long, Double> clientIdToSpending = new HashMap<>();

    for (Purchase purchase : purchaseList) {
        long clientId = purchase.getClientId();
        long bookId = purchase.getBookId();

        // Retrieve client information from clientFileRepository
        Optional<Client> clientOptional = clientFileRepository.findOne(clientId);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            String clientName = client.getFirstName() + " " + client.getLastName();

            double amount = purchase.getNumberSold() * bookFileRepository.findOne(bookId).get().getPrice();

            clientIdToSpending.put(clientId, clientIdToSpending.getOrDefault(clientId, 0.0) + amount);
        }
    }

    List<ClientSpendingDTO> res = new ArrayList<>();
    clientIdToSpending.entrySet().stream()
            .sorted(Map.Entry.<Long, Double>comparingByValue().reversed())
            .forEach(entry -> {
                long clientId = entry.getKey();
                double spending = entry.getValue();
                Optional<Client> clientOptional = clientFileRepository.findOne(clientId);
                if (clientOptional.isPresent()) {
                    Client client = clientOptional.get();
                    String clientName = client.getFirstName() + " " + client.getLastName();
                    res.add(new ClientSpendingDTO(clientName, spending));
                }
            });

    return res;
}

}
