package ro.ubb.bookstore.repository.file;

import org.w3c.dom.*;
import ro.ubb.bookstore.domain.Book;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author radu.
 */
public class BookFileRepository extends InMemoryRepository<Long, Book> {
    private final String filePath;

    public BookFileRepository(Validator<Book> validator, String filePath) {
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
                NodeList nodeList = doc.getElementsByTagName("book");

                for (int itr = 0; itr < nodeList.getLength(); itr++)
                {
                    Node node = nodeList.item(itr);
                    if (node.getNodeType() == Node.ELEMENT_NODE)
                    {
                        Element element = (Element) node;
                        String title = element.getElementsByTagName("title").item(0).getTextContent();
                        String author = element.getElementsByTagName("author").item(0).getTextContent();
                        String publisher = element.getElementsByTagName("publisher").item(0).getTextContent();

                        try{
                            Long id = Long.parseLong(element.getAttribute("id"));
                            double price = Double.parseDouble(element.getElementsByTagName("price").item(0).getTextContent());
                            int stock = Integer.parseInt(element.getElementsByTagName("stock").item(0).getTextContent());

                            Book book = new Book(id, title, author, publisher, price, stock);

                            try {
                                super.save(book);
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
                String title = items.get(1);
                String author = items.get(2);
                String publisher = items.get(3);
                double price = Double.parseDouble(items.get(4));
                int stock = Integer.parseInt(items.get(5));

                Book book = new Book(id, title, author, publisher, price, stock);

                try {
                    super.save(book);
                } catch (ValidatorException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }







    @Override
    public Optional<Book> save(Book entity) throws ValidatorException {
        Optional<Book> optional = super.save(entity);
        if (optional.isEmpty()) {
            return optional;
        }
        saveToFile(entity);
        return Optional.empty();
    }

    private void saveToFile(Book entity) {
        Path path = Paths.get(filePath);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write(
                    entity.getId() + "," + entity.getTitle() + "," + entity.getAuthor() + "," + entity.getPublisher()+ "," + entity.getPrice()+ "," + entity.getStock());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
