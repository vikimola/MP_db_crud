package ro.ubb.bookstore.domain.validators;

import ro.ubb.bookstore.domain.Purchase;
import ro.ubb.bookstore.repository.database.BookDatabaseRepository;
import ro.ubb.bookstore.repository.database.ClientDatabaseRepository;
import ro.ubb.bookstore.repository.file.ClientFileRepository;
import ro.ubb.bookstore.repository.Repository;

import java.sql.SQLException;
import java.time.LocalDate;


public class PurchaseValidator implements Validator<Purchase>{



    public void validate(Purchase purchase, BookDatabaseRepository bookDatabaseRepository, ClientDatabaseRepository clientDatabaseRepository) throws ValidatorException, SQLException {
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

        if(bookDatabaseRepository.findOne(purchase.getBookId()).isEmpty()){
            throw new ValidatorException("Book Id does not exist.");

        }
        if(clientDatabaseRepository.findOne(purchase.getClientId()).isEmpty()){
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
