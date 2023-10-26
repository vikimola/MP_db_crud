package ro.ubb.catalog.domain.validators;

import ro.ubb.catalog.domain.Book;
import ro.ubb.catalog.domain.Purchase;
import ro.ubb.catalog.repository.Repository;

import java.util.Objects;

public class BookValidator implements Validator<Book> {
    public void validate(Book book) throws ValidatorException{
        if(book.getId() < 0){
            throw new ValidatorException("Book ID must  not be negative.");
        }

        if (book.getTitle().isEmpty()){
            throw new ValidatorException("Title must not be empty.");
        }

        if (book.getAuthor().trim().isEmpty()){
            throw new ValidatorException("Author must not be empty.");
        }

        if (book.getPublisher().trim().isEmpty()){
            throw new ValidatorException("Publisher must not be empty.");
        }

        if (book.getPrice() < 0){
            throw new ValidatorException("Price must not be negative.");
        }
        if (book.getStock() < 0){
            throw new ValidatorException("Stock must not be negative.");
        }
    }

    @Override
    public void validate(Purchase purchase, Repository repository) throws ValidatorException {

    }
}
