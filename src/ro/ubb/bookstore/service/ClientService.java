package ro.ubb.bookstore.service;

import ro.ubb.bookstore.domain.Client;
import ro.ubb.bookstore.domain.validators.ClientValidator;
import ro.ubb.bookstore.domain.validators.ValidatorException;
import ro.ubb.bookstore.repository.ClientFileRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ClientService {
    private ClientFileRepository clientFileRepository;
    private final ClientValidator clientValidator;
    public ClientService(ClientFileRepository clientFileRepository, ClientValidator clientValidator){
        this.clientFileRepository=clientFileRepository;
        this.clientValidator=clientValidator;
    }
    public void addClient(Client client){
//        Long newId = findHighestExistingId() +1;
//        client.setId(newId);
        this.clientValidator.validate(client);
        this.clientFileRepository.save(client);
    }

//    private Long findHighestExistingId() {
//        Set<Client> clients = getAllClients();
//        Long highestId = 0L;
//
//        for (Client client : clients) {
//            Long clientId = client.getId();
//            if (clientId > highestId) {
//                highestId = clientId;
//            }
//        }
//
//        return highestId;
//    }    private Long findHighestExistingId() {
//        Set<Client> clients = getAllClients();
//        Long highestId = 0L;
//
//        for (Client client : clients) {
//            Long clientId = client.getId();
//            if (clientId > highestId) {
//                highestId = clientId;
//            }
//        }
//
//        return highestId;
//    }

    public Set<Client> getAllClients(){
        Set<Client> clients = new HashSet<>();
        this.clientFileRepository.findAll().forEach(clients::add);
        return clients;
    }
    public Optional<Client> readOneClient(Long id) throws ValidatorException {
        return this.clientFileRepository.findOne(id);
    }
    public void deleteOneClient(Long id){
        this.clientFileRepository.delete(id);
    }
    public void updateClient(Client client) throws ValidatorException{
        this.clientValidator.validate(client);
        this.clientFileRepository.update(client);
    }
}


