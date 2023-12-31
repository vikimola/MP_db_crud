package ro.ubb.bookstore.service;

import ro.ubb.bookstore.domain.Book;
import ro.ubb.bookstore.domain.validators.ValidatorException;
import ro.ubb.bookstore.repository.Repository;
import ro.ubb.bookstore.repository.database.BookDatabaseRepository;

import java.sql.SQLException;
import java.util.*;

public class BookService {
    private final Repository<Long, Book> bookDatabaseRepository;
    public BookService(Repository<Long, Book> bookDatabaseRepository) {
        this.bookDatabaseRepository = bookDatabaseRepository;
    }
    public Set<Book> getAllBooks() throws ValidatorException, SQLException {
        Set<Book> books = new HashSet<>();
        this.bookDatabaseRepository.findAll().forEach(e -> books.add((Book) e));
        return books;
    }
    public Optional<Book> readOneBook(Long id) throws ValidatorException, SQLException {
        return this.bookDatabaseRepository.findOne(id);
    }
    public Optional<Book> readBookByTitle(String title) throws ValidatorException, SQLException {
        List<Book> bookListAll = (List<Book>) this.bookDatabaseRepository.findAll();
        for (Book book : bookListAll) {
            if (book.getTitle().equals(title)) {
                return Optional.of(book);
            }
        }
       return Optional.empty();

    }

    public void addBook(Book book) throws ValidatorException, SQLException {
        this.bookDatabaseRepository.save(book);
    }

    public void updateBook(Book book) throws ValidatorException, SQLException {
        this.bookDatabaseRepository.update(book);
    }

    public void deleteOneBook(Long id) throws SQLException {
        this.bookDatabaseRepository.delete(id);
    }
}
