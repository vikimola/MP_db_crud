package ro.ubb.catalog.service;

import ro.ubb.catalog.domain.Book;
import ro.ubb.catalog.domain.validators.BookValidator;
import ro.ubb.catalog.domain.validators.ValidatorException;
import ro.ubb.catalog.repository.BookFileRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class BookService {
    private final BookFileRepository bookFileRepository;
    private final BookValidator bookValidator;
    public BookService(BookFileRepository bookFileRepository, BookValidator bookValidator) {
        this.bookFileRepository = bookFileRepository;
        this.bookValidator = bookValidator;
    }
    public void addBook(Book book) throws ValidatorException {
        this.bookFileRepository.save(book);
    }

    public Set<Book> getAllBooks() throws ValidatorException{
        Set<Book> books = new HashSet<>();
        this.bookFileRepository.findAll().forEach(books::add);
//        BookFileRepository.readBookXml();
        return books;
    }
    public Optional<Book> readOneBook(Long id) throws ValidatorException{
        return this.bookFileRepository.findOne(id);
    }
    public void deleteOneBook(Long id){
        bookFileRepository.delete(id);
    }
    public void updateBook(Book book) throws ValidatorException{
        this.bookFileRepository.update(book);

    }

    public Long findHighestExistingId() {
        Set<Book> books = getAllBooks();
        Long highestId = 0L;

        for (Book book : books) {
            Long bookId = book.getId();
            if (bookId > highestId) {
                highestId = bookId;
            }
        }

        return highestId;
    }
}
