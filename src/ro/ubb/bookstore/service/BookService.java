package ro.ubb.bookstore.service;

import ro.ubb.bookstore.domain.Book;
import ro.ubb.bookstore.domain.validators.BookValidator;
import ro.ubb.bookstore.domain.validators.ValidatorException;
import ro.ubb.bookstore.repository.BookDatabaseRepository;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class BookService {
    private final BookDatabaseRepository bookDatabaseRepository;
    public BookService(BookDatabaseRepository bookDatabaseRepository) {
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

    public void addBook(Book book) throws ValidatorException, SQLException {
        this.bookDatabaseRepository.save(book);
    }

    public void updateBook(Book book) throws ValidatorException{
        this.bookDatabaseRepository.update(book);
    }

    public void deleteOneBook(Long id){
        this.bookDatabaseRepository.delete(id);
    }
}
