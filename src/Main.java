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
        String bookFilePath = "src/ro/ubb/catalog/domain/book.txt";
        String clientFilePath = "src/ro/ubb/catalog/domain/client.txt";
        String purchaseFilePath = "src/ro/ubb/catalog/domain/purchase.txt";
        Validator<Book> bookValidator = new BookValidator();

        ClientValidator clientValidator = new ClientValidator();
        PurchaseValidator purchaseValidator = new PurchaseValidator();

        BookFileRepository bookFileRepository = new BookFileRepository(bookValidator, bookFilePath);
        BookService bookService = new BookService(bookFileRepository);

        ClientFileRepository clientFileRepository = new ClientFileRepository(clientValidator, clientFilePath);
        ClientService clientService = new ClientService(clientFileRepository);

        PurchaseFileRepository purchaseFileRepository = new PurchaseFileRepository(purchaseValidator, purchaseFilePath);
        PurchaseService purchaseService = new PurchaseService(purchaseFileRepository, bookFileRepository, clientFileRepository);



        Console console=new Console(clientService, bookService, purchaseService);

        console.runConsole();

        System.out.println("Bye");
        }
    }
