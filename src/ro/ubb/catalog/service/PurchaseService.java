package ro.ubb.catalog.Service;

import ro.ubb.catalog.domain.Client;
import ro.ubb.catalog.domain.Purchase;
import ro.ubb.catalog.domain.validators.ValidatorException;
import ro.ubb.catalog.repository.BookFileRepository;
import ro.ubb.catalog.repository.ClientFileRepository;
import ro.ubb.catalog.repository.PurchaseFileRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
}
