package ro.ubb.bookstore.service;

import ro.ubb.bookstore.domain.Client;
import ro.ubb.bookstore.domain.validators.ClientValidator;
import ro.ubb.bookstore.domain.validators.ValidatorException;
import ro.ubb.bookstore.repository.database.ClientDatabaseRepository;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ClientService {
    private ClientDatabaseRepository clientDatabaseRepository;
    private final ClientValidator clientValidator;
    public ClientService(ClientDatabaseRepository clientDatabaseRepository, ClientValidator clientValidator){
        this.clientDatabaseRepository=clientDatabaseRepository;
        this.clientValidator=clientValidator;
    }
    public void addClient(Client client) throws SQLException {
        this.clientValidator.validate(client);
        this.clientDatabaseRepository.save(client);
    }


    public Set<Client> getAllClients() throws SQLException {
        Set<Client> clients = new HashSet<>();
        this.clientDatabaseRepository.findAll().forEach(clients::add);
        return clients;
    }
    public Optional<Client> readOneClient(Long id) throws ValidatorException, SQLException {
        return this.clientDatabaseRepository.findOne(id);
    }
    public void deleteOneClient(Long id) throws SQLException {
        this.clientDatabaseRepository.delete(id);
    }
    public void updateClient(Client client) throws ValidatorException, SQLException {
        this.clientValidator.validate(client);
        this.clientDatabaseRepository.update(client);
    }
}


