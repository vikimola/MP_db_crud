package ro.ubb.bookstore.domain.validators;

import ro.ubb.bookstore.domain.Purchase;
import ro.ubb.bookstore.repository.Repository;

public interface Validator<T> {
    void validate(T entity) throws ValidatorException;

    void validate(Purchase purchase, Repository bookRepository) throws ValidatorException;
}
