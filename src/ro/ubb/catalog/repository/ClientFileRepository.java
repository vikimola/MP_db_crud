package ro.ubb.catalog.repository;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ro.ubb.catalog.domain.Book;
import ro.ubb.catalog.domain.Client;
import ro.ubb.catalog.domain.validators.ClientValidator;
import ro.ubb.catalog.domain.validators.Validator;
import ro.ubb.catalog.domain.validators.ValidatorException;

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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author radu.
 */
public class ClientFileRepository extends InMemoryRepository<Long, Client> {
    private final String filePath;


    public ClientFileRepository(Validator<Client> validator, String filePath) {
        super(validator);
        this.filePath = filePath;
//        loadData();
        readXml();
    }



    public void readXml() {
        try
        {
            Path path = Paths.get(filePath);
            File file = new File(String.valueOf(path));

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("client");

            for (int itr = 0; itr < nodeList.getLength(); itr++)
            {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element) node;

                    String firstName = element.getElementsByTagName("firstName").item(0).getTextContent();
                    String lastName = element.getElementsByTagName("lastName").item(0).getTextContent();
                    String phoneNumber = element.getElementsByTagName("phoneNumber").item(0).getTextContent();

                    try{
                        Long id = Long.parseLong(element.getAttribute("id"));
                        Client client = new Client(id, firstName, lastName, phoneNumber);

                        try {
                            super.save(client);
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
                String firstName = items.get(1);
                String lastName = items.get((2));
                String phoneNumber = items.get((3));

                Client client = new Client(id, firstName, lastName, phoneNumber);
                client.setId(id);

                try {
                    super.save(client);
                } catch (ValidatorException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Client> save(Client entity) throws ValidatorException {
        Optional<Client> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        saveToFile(entity);
        return Optional.empty();
    }

    private void saveToFile(Client entity) {
        Path path = Paths.get(filePath);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write(
                    entity.getId() + "," + entity.getFirstName() + "," + entity.getLastName() + "," + entity.getPhoneNumber());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
