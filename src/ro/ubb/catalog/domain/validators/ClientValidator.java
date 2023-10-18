package ro.ubb.catalog.domain.validators;

import ro.ubb.catalog.domain.Client;

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



    }
}
