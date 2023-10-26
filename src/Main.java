import ro.ubb.catalog.service.BookService;
import ro.ubb.catalog.service.ClientService;
import ro.ubb.catalog.service.PurchaseService;
import ro.ubb.catalog.userInterface.Console;
import ro.ubb.catalog.domain.Book;
import ro.ubb.catalog.domain.validators.BookValidator;
import ro.ubb.catalog.domain.validators.ClientValidator;
import ro.ubb.catalog.domain.validators.PurchaseValidator;
import ro.ubb.catalog.domain.validators.Validator;
import ro.ubb.catalog.repository.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String bookFilePath = "src/ro/ubb/catalog/domain/resources/book.xml";
        String clientFilePath = "src/ro/ubb/catalog/domain/resources/client.xml";
        String purchaseFilePath = "src/ro/ubb/catalog/domain/resources/purchase.xml";


        BookValidator bookValidator = new BookValidator();
        BookFileRepository bookFileRepository = new BookFileRepository(bookValidator, bookFilePath);
        BookService bookService = new BookService(bookFileRepository, bookValidator);

        ClientValidator clientValidator = new ClientValidator();
        ClientFileRepository clientFileRepository = new ClientFileRepository(clientValidator, clientFilePath);
        ClientService clientService = new ClientService(clientFileRepository, clientValidator);

        PurchaseValidator purchaseValidator = new PurchaseValidator();
        PurchaseFileRepository purchaseFileRepository = new PurchaseFileRepository(purchaseValidator, purchaseFilePath);
        PurchaseService purchaseService = new PurchaseService(purchaseFileRepository, bookFileRepository, clientFileRepository, purchaseValidator);

        Console console=new Console(clientService, bookService, purchaseService);
        console.runConsole();

        }
    }


