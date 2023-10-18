package domain.validators;

import domain.Book;

public class BookValidator implements Validator<Book> {
    public void validate(Book book) throws ValidatorException{
        if(book.getId() <0){
            throw new ValidatorException("Book ID must  not be negative.");
        }

        if (book.getTitle().isEmpty()){
            throw new ValidatorException("Title must not be empty.");
        }

        if (book.getAuthor().isEmpty()){
            throw new ValidatorException("Author must not be empty.");
        }

        if (book.getPublisher().isEmpty()){
            throw new ValidatorException("Publisher must not be empty.");
        }

        if(book.getPrice() < 0){
            throw new ValidatorException("Price must not be negative.");
        }
        if(book.getStock() < 0){
            throw new ValidatorException("Stock must not be negative.");
        }
    }
}
