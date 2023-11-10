package ro.ubb.bookstore.repository.database;

import ro.ubb.bookstore.domain.Book;
import ro.ubb.bookstore.domain.Client;
import ro.ubb.bookstore.domain.validators.Validator;
import ro.ubb.bookstore.domain.validators.ValidatorException;
import ro.ubb.bookstore.repository.database.DatabaseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ClientDatabaseRepository extends DatabaseRepository<Long, Client> {

    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";
    String jdbcURL = "jdbc:postgresql://localhost:5432/postgres";

    public ClientDatabaseRepository(Validator<Client> validator) {super(validator);}

    @Override
    public Optional<Client> findOne(Long id) throws SQLException {
        Optional<Client> optional = super.findOne(id);
        if (optional.isPresent()) {
            return optional;
        }
        Connection connection = DriverManager.getConnection(jdbcURL, USER, PASSWORD);
        String sqlString = "select * from client where (id=?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Client client = new Client();

        if (resultSet.next()) {
            client.setId(id);
            String firstName = resultSet.getString("firstName");
            client.setFirstName(firstName);
            String lastName = resultSet.getString("lastName");
            client.setLastName(lastName);
            String phoneNumber = resultSet.getString("phoneNumber");
            client.setPhoneNumber(phoneNumber);

            connection.close();
            return Optional.of(client);

        }else {
            connection.close();
            return Optional.empty();
        }



    }

    @Override
    public List<Client> findAll() throws SQLException {
        Connection connection = DriverManager.getConnection(jdbcURL, USER, PASSWORD);
        String sqlString = "select * from client";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Client> clientList = new ArrayList<>();

        while (resultSet.next()) {
            Client client = new Client();
            Long id = resultSet.getLong("id");
            client.setId(id);
            String firstName = resultSet.getString("firstName");
            client.setFirstName(firstName);
            String lastName = resultSet.getString("lastName");
            client.setLastName(lastName);
            String phoneNumber = resultSet.getString("phoneNumber");
            client.setPhoneNumber(phoneNumber);

            clientList.add(client);
        }

        connection.close();
        return clientList;
    }

    @Override
    public Optional<Client> save(Client entity) throws ValidatorException, SQLException {

        Optional<Client> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }

        Connection connection = DriverManager.getConnection(jdbcURL, USER, PASSWORD);

        String sqlString = "insert into postgres.public.client (id, firstName,lastName, phoneNumber) values (?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
        preparedStatement.setLong(1, entity.getId());
        preparedStatement.setString(2, entity.getFirstName());
        preparedStatement.setString(3, entity.getLastName());
        preparedStatement.setString(4, entity.getPhoneNumber());
        preparedStatement.executeUpdate();

        connection.close();

        return Optional.empty();

    }

    @Override
    public Optional<Client> delete(Long id) throws SQLException {
        Optional<Client> optional = super.delete(id);
        if (optional.isPresent()) {
            return optional;
        }
        Connection connection = DriverManager.getConnection(jdbcURL, USER, PASSWORD);
        String sqlString = "delete from client where (id=?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
        connection.close();
        return Optional.empty();
    }

    @Override
    public Optional<Client> update(Client entity) throws ValidatorException, SQLException {
        Optional<Client> optional = super.update(entity);
        if (optional.isPresent()) {
            return optional;
        }

        Connection connection = DriverManager.getConnection(jdbcURL, USER, PASSWORD);

        String sqlString = "update postgres.public.client set firstName=?,lastName=?, phoneNumber=? where id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
        preparedStatement.setLong(4, entity.getId());
        preparedStatement.setString(1, entity.getFirstName());
        preparedStatement.setString(2, entity.getLastName());
        preparedStatement.setString(3, entity.getPhoneNumber());
        preparedStatement.executeUpdate();

        connection.close();
        return Optional.empty();
    }
}
