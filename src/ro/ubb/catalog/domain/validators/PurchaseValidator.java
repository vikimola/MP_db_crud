package ro.ubb.catalog.domain.validators;

import ro.ubb.catalog.domain.Purchase;

import java.time.LocalDate;


public class PurchaseValidator implements Validator<Purchase>{
    public void validate(Purchase purchase) throws ValidatorException{
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
    }
}
