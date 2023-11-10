import ro.ubb.bookstore.repository.database.BookDatabaseRepository;
import ro.ubb.bookstore.repository.database.PurchaseDatabaseRepository;
import ro.ubb.bookstore.repository.file.BookFileRepository;
import ro.ubb.bookstore.repository.file.ClientFileRepository;
import ro.ubb.bookstore.repository.file.PurchaseFileRepository;
import ro.ubb.bookstore.service.BookService;
import ro.ubb.bookstore.service.ClientService;
import ro.ubb.bookstore.service.PurchaseService;
import ro.ubb.bookstore.userInterface.Console;
import ro.ubb.bookstore.domain.validators.BookValidator;
import ro.ubb.bookstore.domain.validators.ClientValidator;
import ro.ubb.bookstore.domain.validators.PurchaseValidator;

public class Main {
    public static void main(String[] args) throws Exception {
        String bookFilePath = "src/ro/ubb/bookstore/domain/resources/book.xml";
        String clientFilePath = "src/ro/ubb/bookstore/domain/resources/client.xml";
        String purchaseFilePath = "src/ro/ubb/bookstore/domain/resources/purchase.xml";


        BookValidator bookValidator = new BookValidator();
        BookFileRepository bookFileRepository = new BookFileRepository(bookValidator, bookFilePath);
        BookDatabaseRepository bookDatabaseRepository = new BookDatabaseRepository(bookValidator);
        BookService bookService = new BookService(bookDatabaseRepository);

        ClientValidator clientValidator = new ClientValidator();
        ClientFileRepository clientFileRepository = new ClientFileRepository(clientValidator, clientFilePath);
        ro.ubb.bookstore.repository.database.ClientDatabaseRepository clientDatabaseRepository = new ro.ubb.bookstore.repository.database.ClientDatabaseRepository(clientValidator);
        ClientService clientService = new ClientService(clientDatabaseRepository, clientValidator);

        PurchaseValidator purchaseValidator = new PurchaseValidator();
        PurchaseFileRepository purchaseFileRepository = new PurchaseFileRepository(purchaseValidator, purchaseFilePath);
        PurchaseDatabaseRepository purchaseDatabaseRepository = new PurchaseDatabaseRepository(purchaseValidator);
        PurchaseService purchaseService = new PurchaseService(purchaseDatabaseRepository, bookDatabaseRepository, clientDatabaseRepository, purchaseValidator);

        Console console=new Console(clientService, bookService, purchaseService);
        console.runConsole();

        }
    }


