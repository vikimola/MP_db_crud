package ro.ubb.bookstore.repository;

import ro.ubb.bookstore.domain.BaseEntity;
import ro.ubb.bookstore.domain.validators.ValidatorException;

import java.sql.SQLException;
import java.util.Optional;

/**
 * Interface for generic CRUD operations on a ro.ubb.catalog.domain.ro.ubb.catalog.repository for a specific type.
 *
 * @author radu.
 *
 */
public interface Repository<ID, T extends BaseEntity<ID>> {
    /**
     * Find the entity with the given {@code id}.
     * 
     * @param id
     *            must be not null.
     * @return an {@code Optional} encapsulating the entity with the given id.
     * @throws IllegalArgumentException
     *             if the given id is null.
     */
    Optional<T> findOne(ID id) throws SQLException;

    /**
     *
     * @return all entities.
     */
    Iterable<T> findAll() throws SQLException;

    /**
     * Saves the given entity.
     *
     * @param entity
     *            must not be null.
     * @return an {@code Optional} - null if the entity was saved otherwise (e.g. id already exists) returns the entity.
     * @throws IllegalArgumentException
     *             if the given entity is null.
     * @throws ValidatorException
     *             if the entity is not valid.
     */
    Optional<T> save(T entity) throws ValidatorException, SQLException;

    /**
     * Removes the entity with the given id.
     *
     * @param id
     *            must not be null.
     * @return an {@code Optional} - null if there is no entity with the given id, otherwise the removed entity.
     * @throws IllegalArgumentException
     *             if the given id is null.
     */
    Optional<T> delete(ID id) throws SQLException;

    /**
     * Updates the given entity.
     * 
     * @param entity
     *            must not be null.
     * @return an {@code Optional} - null if the entity was updated otherwise (e.g. id does not exist) returns the
     *         entity.
     * @throws IllegalArgumentException
     *             if the given entity is null.
     * @throws ValidatorException
     *             if the entity is not valid.
     */
    Optional<T> update(T entity) throws ValidatorException, SQLException;

}
