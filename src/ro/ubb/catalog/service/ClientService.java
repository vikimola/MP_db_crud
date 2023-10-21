package ro.ubb.catalog.service;

import ro.ubb.catalog.domain.Client;
import ro.ubb.catalog.domain.validators.ValidatorException;
import ro.ubb.catalog.repository.ClientFileRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ClientService {
    private ClientFileRepository clientFileRepository;
    public ClientService(ClientFileRepository clientFileRepository){
        this.clientFileRepository=clientFileRepository;
    }
    public void addClient(Client client){
        Long newId = findHighestExistingId() +1;
        client.setId(newId);
        this.clientFileRepository.save(client);
    }

    private Long findHighestExistingId() {
        Set<Client> clients = getAllClients();
        Long highestId = 0L;

        for (Client client : clients) {
            Long clientId = client.getId();
            if (clientId > highestId) {
                highestId = clientId;
            }
        }

        return highestId;
    }

    public Set<Client> getAllClients(){
        Set<Client> clients = new HashSet<>();
        clientFileRepository.findAll().forEach(clients::add);
        return clients;
    }
    public Optional<Client> readOneClient(Long id) throws ValidatorException {
        return this.clientFileRepository.findOne(id);
    }
    public Optional<Client> deleteOneClient(Long id){
            return clientFileRepository.delete(id);
        }
    public Optional<Client> updateClient(Client client) throws ValidatorException{
        return clientFileRepository.update(client);
    }
}


