package ro.ubb.bookstore.domain.validators;

import ro.ubb.bookstore.domain.Client;
import ro.ubb.bookstore.domain.Purchase;
import ro.ubb.bookstore.repository.Repository;

public class ClientValidator implements Validator<Client>{
    public void validate(Client client) throws ValidatorException{
        if(client.getId() <0){
            throw new ValidatorException("Client ID must  not be negative.");
        }

        if(client.getFirstName().isEmpty()){
            throw new ValidatorException("First name must not be empty.");
        }

        if(client.getLastName().isEmpty()){
            throw new ValidatorException("Last name must not be empty.");
        }
        if(client.getPhoneNumber().isEmpty()){
            throw new ValidatorException("Phone number must not be empty.");
        }
        if(client.getPhoneNumber().length()<10){
            throw new ValidatorException("Phone number must be at least 10 digits.");
        }



    }

    @Override
    public void validate(Purchase purchase, Repository repository) throws ValidatorException {

    }
}
