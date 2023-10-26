package ro.ubb.catalog.repository;

import ro.ubb.catalog.domain.Book;
import ro.ubb.catalog.domain.validators.Validator;
import ro.ubb.catalog.domain.validators.ValidatorException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.BufferedWriter;
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
                //creating a constructor of file class and parsing an XML file
                Path path = Paths.get(filePath);
                File file = new File(String.valueOf(path));

                //an instance of factory that gives a document builder
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

                //an instance of builder to parse the specified xml file
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(file);
                doc.getDocumentElement().normalize();
//                System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
                NodeList nodeList = doc.getElementsByTagName("book");
                // nodeList is not iterable, so we are using for loop

                for (int itr = 0; itr < nodeList.getLength(); itr++)
                {
                    Node node = nodeList.item(itr);
//                    System.out.println("\nNode Name: " + node.getNodeName());
//                    System.out.println("\nitr: " + itr);
                    if (node.getNodeType() == Node.ELEMENT_NODE)
                    {
                        Element element = (Element) node;
//                        System.out.println("Item ID: " + element.getAttribute("id"));
//                        System.out.println("Name: " + element.getElementsByTagName("title").item(0).getTextContent());
//                        System.out.println("Price: " + element.getElementsByTagName("author").item(0).getTextContent());
//                        System.out.println("Price: " + element.getElementsByTagName("publisher").item(0).getTextContent());
//                        System.out.println("Price: " + element.getElementsByTagName("price").item(0).getTextContent());
//                        System.out.println("Price: " + element.getElementsByTagName("stock").item(0).getTextContent());
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
                book.setId(id);

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
        if (optional.isPresent()) {
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
