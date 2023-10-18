package ro.ubb.catalog.repository;

import ro.ubb.catalog.domain.Purchase;
import ro.ubb.catalog.domain.validators.Validator;
import ro.ubb.catalog.domain.validators.ValidatorException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author radu.
 */
public class PurchaseFileRepository extends InMemoryRepository<Long, Purchase> {
    private String fileName;

    public PurchaseFileRepository(Validator<Purchase> validator, String fileName) {
        super(validator);
        this.fileName = fileName;

        loadData();
    }

    private void loadData() {
        Path path = Paths.get(fileName);

        try {
            Files.lines(path).forEach(line -> {
                List<String> items = Arrays.asList(line.split(","));

                Long id = Long.valueOf(items.get(0));
                long bookId = Long.parseLong(items.get(1));
                long clientId = Long.parseLong(items.get(2));
                int numberSold = Integer.parseInt(items.get(3));
                LocalDate dateOfPurchase = LocalDate.parse(items.get(4));

                // ! Ha error a fenti parse, akkor ez: (otherwise delete this)
//                String dateString = items.get(4);
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//                LocalDate dateOfPurchase = LocalDate.parse(dateString, formatter);

                Purchase purchase = new Purchase(bookId, clientId, numberSold, dateOfPurchase);
                purchase.setId(id);

                try {
                    super.save(purchase);
                } catch (ValidatorException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Purchase> save(Purchase entity) throws ValidatorException {
        Optional<Purchase> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        saveToFile(entity);
        return Optional.empty();
    }

    private void saveToFile(Purchase entity) {
        Path path = Paths.get(fileName);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write(
                    entity.getId() + "," + entity.getBookId() + "," + entity.getClientId() + "," + entity.getNumberSold() + "," + entity.getDateOfPurchase());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
