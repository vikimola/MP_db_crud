import ro.ubb.catalog.Service.BookService;
import ro.ubb.catalog.Service.ClientService;
import ro.ubb.catalog.Service.PurchaseService;
import ro.ubb.catalog.UserInterface.Console;
import ro.ubb.catalog.domain.Book;
import ro.ubb.catalog.domain.validators.BookValidator;
import ro.ubb.catalog.domain.validators.ClientValidator;
import ro.ubb.catalog.domain.validators.PurchaseValidator;
import ro.ubb.catalog.domain.validators.Validator;
import ro.ubb.catalog.repository.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Validator<Book> bookValidator = new BookValidator();
        ClientValidator clientValidator = new ClientValidator();
        PurchaseValidator purchaseValidator = new PurchaseValidator();

        BookFileRepository bookFileRepository = new BookFileRepository(bookValidator, "");
        BookService bookService = new BookService(bookFileRepository);

        ClientFileRepository clientFileRepository = new ClientFileRepository(clientValidator, "");
        ClientService clientService = new ClientService(clientFileRepository);

        PurchaseFileRepository purchaseFileRepository = new PurchaseFileRepository(purchaseValidator, "");
        PurchaseService purchaseService = new PurchaseService(purchaseFileRepository, bookFileRepository, clientFileRepository);

        Console console=new Console(clientService, bookService, purchaseService);

        console.runConsole();

        System.out.println("Bye");
        }
    }
