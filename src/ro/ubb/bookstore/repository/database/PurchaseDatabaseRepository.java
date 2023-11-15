package ro.ubb.bookstore.repository.database;

import ro.ubb.bookstore.domain.Book;
import ro.ubb.bookstore.domain.Purchase;
import ro.ubb.bookstore.domain.validators.Validator;
import ro.ubb.bookstore.domain.validators.ValidatorException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PurchaseDatabaseRepository extends DatabaseRepository<Long, Purchase> {

    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";
    String jdbcURL = "jdbc:postgresql://localhost:5432/postgres";

    public PurchaseDatabaseRepository(Validator<Purchase> validator) {

        super(validator);
    }

    @Override
    public Optional<Purchase> findOne(Long id) throws SQLException {
        Optional<Purchase> optional = super.findOne(id);
        if (optional.isPresent()) {
            return optional;
        }
        Connection connection = DriverManager.getConnection(jdbcURL, USER, PASSWORD);
        String sqlString = "select * from purchase where (id=?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Purchase purchase = new Purchase();

        if (resultSet.next()) {
            purchase.setId(id);
            long bookId = resultSet.getLong("bookId");
            purchase.setBookId(bookId);
            long clientId = resultSet.getLong("clientId");
            purchase.setClientId(clientId);
            int numberSold = resultSet.getInt("numberSold");
            purchase.setNumberSold(numberSold);
            LocalDate dateOfPurchase = resultSet.getDate("dateOfPurchase").toLocalDate();
            purchase.setDateOfPurchase(dateOfPurchase);
            connection.close();

            return Optional.of(purchase);

        }else {
            connection.close();
            return Optional.empty();
        }
    }

    @Override
    public List<Purchase> findAll() throws SQLException {
        Connection connection = DriverManager.getConnection(jdbcURL, USER, PASSWORD);
        String sqlString = "select * from purchase";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Purchase> bookList = new ArrayList<>();

        while (resultSet.next()) {
            Purchase purchase = new Purchase();
            Long id = resultSet.getLong("id");
            purchase.setId(id);
            long bookId = resultSet.getLong("bookId");
            purchase.setBookId(bookId);
            long clientId = resultSet.getLong("clientId");
            purchase.setClientId(clientId);
            int numberSold = resultSet.getInt("numberSold");
            purchase.setNumberSold(numberSold);
            LocalDate dateOfPurchase = resultSet.getDate("dateOfPurchase").toLocalDate();
            purchase.setDateOfPurchase(dateOfPurchase);
            bookList.add(purchase);
        }

        connection.close();
        return bookList;
    }

    @Override
    public Optional<Purchase> save(Purchase entity) throws ValidatorException, SQLException {

        Optional<Purchase> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }

        Connection connection = DriverManager.getConnection(jdbcURL, USER, PASSWORD);

        String sqlString = "insert into postgres.public.purchase (id,  bookId,  clientId,  numberSold,  dateOfPurchase) values (?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
        preparedStatement.setLong(1, entity.getId());
        preparedStatement.setLong(2, entity.getBookId());
        preparedStatement.setLong(3, entity.getClientId());
        preparedStatement.setInt(4, entity.getNumberSold());
        preparedStatement.setObject(5, entity.getDateOfPurchase());
        preparedStatement.executeUpdate();

        connection.close();

        return Optional.empty();

    }

    @Override
    public Optional<Purchase> delete(Long id) throws SQLException {
        Optional<Purchase> optional = super.delete(id);
        if (optional.isPresent()) {
            return optional;
        }
        Connection connection = DriverManager.getConnection(jdbcURL, USER, PASSWORD);
        String sqlString = "delete from purchase where (id=?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
        connection.close();
        return Optional.empty();
    }

    @Override
    public Optional<Purchase> update(Purchase entity) throws ValidatorException, SQLException {
        Optional<Purchase> optional = super.update(entity);
        if (optional.isPresent()) {
            return optional;
        }

        Connection connection = DriverManager.getConnection(jdbcURL, USER, PASSWORD);

        String sqlString = "update postgres.public.purchase set bookId=?,  clientId=?,  numberSold=?,  dateOfPurchase=? where id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
        preparedStatement.setLong(5, entity.getId());
        preparedStatement.setLong(1, entity.getBookId());
        preparedStatement.setLong(2, entity.getClientId());
        preparedStatement.setInt(3, entity.getNumberSold());
        preparedStatement.setObject(4, entity.getDateOfPurchase());
        preparedStatement.executeUpdate();

        connection.close();
        return Optional.empty();
    }
}
