package ro.ubb.catalog.domain.validators;

import ro.ubb.catalog.domain.Book;
import ro.ubb.catalog.domain.Purchase;
import ro.ubb.catalog.repository.BookFileRepository;
import ro.ubb.catalog.repository.ClientFileRepository;
import ro.ubb.catalog.repository.Repository;

import java.time.LocalDate;


public class PurchaseValidator implements Validator<Purchase>{



    public void validate(Purchase purchase, BookFileRepository bookFileRepository, ClientFileRepository clientFileRepository) throws ValidatorException{
        if(purchase.getId() < 0){
            throw new ValidatorException("Purchase ID must  not be negative.");
        }

        if(purchase.getBookId() < 0){
            throw new ValidatorException("Book ID must  not be negative.");
        }

        if(purchase.getNumberSold() < 0){
            throw new ValidatorException("Number sold must  not be negative.");
        }

        // Date format: yyyy-mm-dd (pl. 2023-10-18)
        if(purchase.getDateOfPurchase().isAfter(LocalDate.now())){
            throw new ValidatorException("Date of purchase cannot be in the future.");
        }

        if(bookFileRepository.findOne(purchase.getBookId()).isEmpty()){
            throw new ValidatorException("Book Id does not exist.");

        }
        if(clientFileRepository.findOne(purchase.getClientId()).isEmpty()){
            throw new ValidatorException("Client Id does not exist.");
        }



    }


    @Override
    public void validate(Purchase entity) throws ValidatorException {

    }

    @Override
    public void validate(Purchase purchase, Repository repository) throws ValidatorException {

    }
}
