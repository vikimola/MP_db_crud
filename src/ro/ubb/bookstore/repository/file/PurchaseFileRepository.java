package ro.ubb.bookstore.repository.file;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ro.ubb.bookstore.domain.Purchase;
import ro.ubb.bookstore.domain.validators.Validator;
import ro.ubb.bookstore.domain.validators.ValidatorException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author radu.
 */
public class PurchaseFileRepository extends InMemoryRepository<Long, Purchase> {
    private final String filePath;

    public PurchaseFileRepository(Validator<Purchase> validator, String filePath) {
        super(validator);
        this.filePath = filePath;
//        loadData();
        readXml();
    }



    public void readXml() {
        try{
            Path path = Paths.get(filePath);
            File file = new File(String.valueOf(path));

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("purchase");

            for (int itr = 0; itr < nodeList.getLength(); itr++)
            {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element) node;
                    try{
                        long id = Long.parseLong(element.getAttribute("id"));
                        long bookId = Long.parseLong(element.getElementsByTagName("bookId").item(0).getTextContent());
                        long clientId = Long.parseLong(element.getElementsByTagName("clientId").item(0).getTextContent());
                        int numberSold = Integer.parseInt(element.getElementsByTagName("numberSold").item(0).getTextContent());
                        LocalDate dateOfPurchase = LocalDate.parse(element.getElementsByTagName("dateOfPurchase").item(0).getTextContent());

                        Purchase purchase = new Purchase(id, bookId, clientId, numberSold, dateOfPurchase);

                        try {
                            super.save(purchase);
                        } catch (ValidatorException e) {
                            e.printStackTrace();
                        }

                    } catch (NumberFormatException e ){
                        throw new ValidatorException("String not convertible to number.");
                    }


                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void loadData() {
        Path path = Paths.get(filePath);

        try {
            Files.lines(path, StandardCharsets.UTF_8).forEach(line -> {
                List<String> items = Arrays.asList(line.split(","));

                Long id = Long.valueOf(items.get(0));
                long bookId = Long.parseLong(items.get(1));
                long clientId = Long.parseLong(items.get(2));
                int numberSold = Integer.parseInt(items.get(3));
                LocalDate dateOfPurchase = LocalDate.parse(items.get(4));

                Purchase purchase = new Purchase(id, bookId, clientId, numberSold, dateOfPurchase);
//                purchase.setId(id);

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
        Path path = Paths.get(filePath);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write(
                    entity.getId() + "," + entity.getBookId() + "," + entity.getClientId() + "," + entity.getNumberSold() + "," + entity.getDateOfPurchase());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
