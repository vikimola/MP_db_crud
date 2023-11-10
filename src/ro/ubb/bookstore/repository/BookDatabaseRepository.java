package ro.ubb.bookstore.repository;

import ro.ubb.bookstore.domain.Book;
import ro.ubb.bookstore.domain.validators.Validator;
import ro.ubb.bookstore.domain.validators.ValidatorException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDatabaseRepository extends DatabaseRepository<Long, Book> {

    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";
    String jdbcURL = "jdbc:postgresql://localhost:5432/postgres";

    public BookDatabaseRepository(Validator<Book> validator) {
        super(validator);
    }

    @Override
    public Optional<Book> findOne(Long id) throws SQLException {
        Optional<Book> optional = super.findOne(id);
        if (optional.isPresent()) {
            return optional;
        }
        Connection connection = DriverManager.getConnection(jdbcURL, "postgres", "admin");
        String sqlString = "select * from book where (id=?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Book book = new Book();

        if (resultSet.next()) {
        book.setId(id);
        String title = resultSet.getString("title");
        book.setTitle(title);
        String author = resultSet.getString("author");
        book.setAuthor(author);
        String publisher = resultSet.getString("publisher");
        book.setPublisher(publisher);
        double price = resultSet.getDouble("price");
        book.setPrice(price);
        int stock = resultSet.getInt("stock");
        book.setStock(stock);
        connection.close();

        return Optional.of(book);

        }else {
            connection.close();
            return Optional.empty();
        }



    }

    @Override
    public List<Book> findAll() throws SQLException {
        Connection connection = DriverManager.getConnection(jdbcURL, "postgres", "admin");
        String sqlString = "select * from book";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Book> bookList = new ArrayList<>();

        while (resultSet.next()) {
            Book book = new Book();
            Long id = resultSet.getLong("id");
            book.setId(id);
            String title = resultSet.getString("title");
            book.setTitle(title);
            String author = resultSet.getString("author");
            book.setAuthor(author);
            String publisher = resultSet.getString("publisher");
            book.setPublisher(publisher);
            double price = resultSet.getDouble("price");
            book.setPrice(price);
            int stock = resultSet.getInt("stock");
            book.setStock(stock);

            bookList.add(book);
        }

        connection.close();
        return bookList;
    }

    @Override
    public Optional<Book> save(Book entity) throws ValidatorException, SQLException {

        Optional<Book> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }

        Connection connection = DriverManager.getConnection(jdbcURL, "postgres", "admin");

        String sqlString = "insert into postgres.public.book (id, title, author, publisher, price, stock) values (?,?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
        preparedStatement.setLong(1, entity.getId());
        preparedStatement.setString(2, entity.getTitle());
        preparedStatement.setString(3, entity.getAuthor());
        preparedStatement.setString(4, entity.getPublisher());
        preparedStatement.setDouble(5, entity.getPrice());
        preparedStatement.setInt(6, entity.getStock());
        preparedStatement.executeUpdate();

        connection.close();

        return Optional.empty();

    }

    @Override
    public Optional<Book> delete(Long id) throws SQLException {
        Optional<Book> optional = super.delete(id);
        if (optional.isPresent()) {
            return optional;
        }
        Connection connection = DriverManager.getConnection(jdbcURL, "postgres", "admin");
        String sqlString = "delete from book where (id=?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
        connection.close();
        return Optional.empty();
    }

    @Override
    public Optional<Book> update(Book entity) throws ValidatorException, SQLException {
        Optional<Book> optional = super.update(entity);
        if (optional.isPresent()) {
            return optional;
        }

        Connection connection = DriverManager.getConnection(jdbcURL, "postgres", "admin");

        String sqlString = "update postgres.public.book set title=?, author=?, publisher=?, price=?, stock=? where id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
        preparedStatement.setLong(6, entity.getId());
        preparedStatement.setString(1, entity.getTitle());
        preparedStatement.setString(2, entity.getAuthor());
        preparedStatement.setString(3, entity.getPublisher());
        preparedStatement.setDouble(4, entity.getPrice());
        preparedStatement.setInt(5, entity.getStock());
        preparedStatement.executeUpdate();

        connection.close();
        return Optional.empty();
    }
}
