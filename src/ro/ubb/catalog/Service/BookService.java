package ro.ubb.catalog.Service;

import ro.ubb.catalog.domain.Book;
import ro.ubb.catalog.domain.validators.ValidatorException;
import ro.ubb.catalog.repository.BookFileRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class BookService {
    private BookFileRepository bookFileRepository;
    public BookService(BookFileRepository bookFileRepository){
        this.bookFileRepository=bookFileRepository;
    }
    public void addBook(Book book) throws ValidatorException {
        Long newId = findHighestExistingId() +1;
        book.setId(newId);
        this.bookFileRepository.save(book);
    }
    public Set<Book> getAllBooks() throws ValidatorException{
        Set<Book> books = new HashSet<>();
        bookFileRepository.findAll().forEach(books::add);
        return books;
    }
    public Optional<Book> readOneBook(Long id) throws ValidatorException{
        return this.bookFileRepository.findOne(id);
    }
    public Optional<Book> deleteOneBook(Long id){

            return bookFileRepository.delete(id);
        }
    public Optional<Book> updateBook(Book book) throws ValidatorException{
        return bookFileRepository.update(book);
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
