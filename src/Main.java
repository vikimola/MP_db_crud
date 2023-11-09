import ro.ubb.bookstore.service.BookService;
import ro.ubb.bookstore.service.ClientService;
import ro.ubb.bookstore.service.PurchaseService;
import ro.ubb.bookstore.userInterface.Console;
import ro.ubb.bookstore.domain.validators.BookValidator;
import ro.ubb.bookstore.domain.validators.ClientValidator;
import ro.ubb.bookstore.domain.validators.PurchaseValidator;
import ro.ubb.bookstore.repository.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String bookFilePath = "src/ro/ubb/bookstore/domain/resources/book.xml";
        String clientFilePath = "src/ro/ubb/bookstore/domain/resources/client.xml";
        String purchaseFilePath = "src/ro/ubb/bookstore/domain/resources/purchase.xml";


        BookValidator bookValidator = new BookValidator();
        BookDatabaseRepository bookDatabaseRepository = new BookDatabaseRepository(bookValidator);
        BookService bookService = new BookService(bookDatabaseRepository);

        ClientValidator clientValidator = new ClientValidator();
        ClientFileRepository clientFileRepository = new ClientFileRepository(clientValidator, clientFilePath);
        ClientService clientService = new ClientService(clientFileRepository, clientValidator);

        PurchaseValidator purchaseValidator = new PurchaseValidator();
        PurchaseFileRepository purchaseFileRepository = new PurchaseFileRepository(purchaseValidator, purchaseFilePath);
        PurchaseService purchaseService = new PurchaseService(purchaseFileRepository, bookDatabaseRepository, clientFileRepository, purchaseValidator);

        Console console=new Console(clientService, bookService, purchaseService);
        console.runConsole();

        }
    }


