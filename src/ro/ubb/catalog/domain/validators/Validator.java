package ro.ubb.catalog.domain.validators;

import ro.ubb.catalog.domain.Book;
import ro.ubb.catalog.domain.Purchase;
import ro.ubb.catalog.repository.Repository;

public interface Validator<T> {
    void validate(T entity) throws ValidatorException;

    void validate(Purchase purchase, Repository bookRepository) throws ValidatorException;
}
