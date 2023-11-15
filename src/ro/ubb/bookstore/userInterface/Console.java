package ro.ubb.bookstore.userInterface;
import ro.ubb.bookstore.domain.*;
import ro.ubb.bookstore.service.BookService;
import ro.ubb.bookstore.service.ClientService;
import ro.ubb.bookstore.service.PurchaseService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Console {
    private Scanner scanner;
    private ClientService clientService;
    private BookService bookService;
    private PurchaseService purchaseService;
    private static void print(String string){
        System.out.println(string);
    }
    public Console(ClientService clientService, BookService bookService,
                   PurchaseService purchaseService){
        this.bookService=bookService;
        this.clientService=clientService;
        this.purchaseService=purchaseService;
        this.scanner=new Scanner(System.in);
    }
    private static void menu(){
        print("1. Operations for Books");
        print("2. Operations for the Clients");
        print("3. Operations for the Purchases");
        print("4. See Profitability of Books");
        print("5. See Client Spending");
        //print("6. Get all available books");
        print("0. Exit \n");
    }
    public void runConsole() throws Exception {
        while (true) {
            menu();
            int option = scanner.nextInt();
            switch (option) {
                case 1 -> handleBookSubmenu();
                case 2 -> handleClientSubmenu();
                case 3 -> handlePurchases();
                case 4 -> handleBookProfitability();
                case 5 -> handleClientSpending();
                case 6 -> bookService.getAllBooks();
                case 0 -> {
                    return;
                }
                default -> print("Invalid option.\n");
            }

        }
    }

    private void handleBookProfitability() throws SQLException {
        for (BookProfitabilityDTO profit : purchaseService.getBookProfitability()) {
            System.out.println(profit);
        }
        System.out.println("\n");
    }

    private void handleClientSpending() throws SQLException {
        for (ClientSpendingDTO amount : purchaseService.getClientSpending()) {
            System.out.println(amount);
        }
        System.out.println("\n");
    }

    private void handlePurchases() throws Exception {
        while (true) {
            print("1. Add a new purchase ");
            print("2. Update a purchase ");
            print("3. Delete a purchase ");
            print("4. Show a purchase ");
            print("5. Show all Purchases ");
            print("0. Return to main menu! \n");

            int option = scanner.nextInt();
            switch (option) {
                case 1 -> this.handleAddPurchase();
                case 2 -> this.handleUpdatePurchase();
                case 3 -> this.handleDeletePurchase();
                case 4 -> this.handleShowOnePurchase();
                case 5 -> this.handleShowAllPurchases();
                case 0 -> {return;}
                default-> print("Invalid option.\n");

            }
        }
    }
    private void handleShowAllPurchases() throws SQLException {
        if (!this.purchaseService.getAllPurchases().isEmpty()) {
            for (Purchase p :
                    this.purchaseService.getAllPurchases()) {
                print(p.toString());
            }
        } else {
            print("Couldn't find purchases.\n");
        }

    }
    private void handleShowOnePurchase() throws SQLException {
        print("Which purchase would you like to see? ");
        Long id =scanner.nextLong();
        Optional<Purchase> purchase = this.purchaseService.readOnePurchase(id);
        if (purchase.isEmpty()) {
            print("Couldn't find the purchase you searched for!\n");
        } else {
            System.out.println(purchase);
        }

    }
    private void handleDeletePurchase() throws SQLException {
        print("Give me the id of book: ");
        Long  id =scanner.nextLong();
        System.out.println(this.purchaseService.readOnePurchase(id) + " will be deleted!");
        this.purchaseService.deleteOnePurchase(id);
        print("Purchase deleted.\n ");
    }
    private Purchase getPurchasedata() {
        InputStreamReader isr = new InputStreamReader(System.in);
        print("Enter Id");
        long id = scanner.nextLong();
        print("Enter Book Id");
        long bookId = scanner.nextLong();
        print("Enter the Client Id: ");
        long clientId = scanner.nextLong();
        print("Enter number of books sold: ");
        int numberSold = scanner.nextInt();
        print("Give me the date of Purchase: ");
        LocalDate dateOfPurchase = LocalDate.parse(scanner.next());
        return new Purchase(id, bookId, clientId, numberSold, dateOfPurchase);

    }

    private void handleUpdatePurchase() {

        try {
            Purchase purchase = getPurchasedata();
            this.purchaseService.updatePurchase(purchase);
            print("Purchase Update Completed!\n");
        } catch (Exception exception) {
            print("Error during update!\n");

        }

    }
    private void handleAddPurchase() {
        try {
            Purchase purchase = getPurchasedata();
            this.purchaseService.addPurchase(purchase);
        } catch (InputMismatchException ime) {
            print("Wrong data type entered!\n");
            scanner.next();
        } catch (Exception exception) {
            print(exception.getMessage());
            print("\n");


        }
    }
    private void handleBookSubmenu() throws SQLException {
        while (true) {
            print("1. Add a new book ");
            print("2. Update a book ");
            print("3. Delete a book ");
            print("4. Show a book ");
            print("5. Show all books ");
            print("6. Show books by a certain title");
            print("0. Return to main menu!\n");
            int option = scanner.nextInt();
            switch (option) {
                case 1 -> this.handleAddBook();
                case 2 -> this.handleUpdateBook();
                case 3 -> this.handleDeleteBook();
                case 4 -> this.handleShowOneBook();
                case 5 -> this.handleShowAllBooks();
                case 6 -> this.handleShowBooksByTitle();
                case 0 ->
                        {
                    return;}
                default ->
                    print("Invalid option.\n");

            }
        }
    }

    private void handleShowAllBooks() throws SQLException {
        if (!this.bookService.getAllBooks().isEmpty()) {
            for (Book book :
                    this.bookService.getAllBooks()) {
                print(book.toString());
            }
        } else {
            System.out.println("Couldn't find books.\n");
        }
    }
    private void handleShowOneBook() throws SQLException {
        print("Enter Book Id");
        long bookId = scanner.nextLong();
        Optional<Book> book = this.bookService.readOneBook(bookId);
        if (book.isEmpty()) {
            System.out.println("Couldn't find the book you searched for!\n");
        } else {
            System.out.println(book);
        }
    }

    private void handleShowBooksByTitle() throws SQLException {
        print("Enter Book Title");
        String bookTitle = scanner.next();
        Optional<Book> book = this.bookService.readBookByTitle(bookTitle);
        if (book.isEmpty()) {
            System.out.println("Couldn't find the book you searched for!\n");
        } else {
            System.out.println(book);
        }
    }

    private void handleDeleteBook() {
        try {
            print("Enter Book Id you want to delete: ");
            long bookId = scanner.nextLong();
            System.out.println(this.bookService.readOneBook(bookId) + " will be deleted!");
            this.bookService.deleteOneBook(bookId);
            print("Deletion completed!\n");

        } catch (Exception exception) {
            print("Error occurred during delete...\n");
        }
    }

    public Book getBookdata() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        print("Enter Book Id: ");
        Long id = scanner.nextLong();
        print("Enter Book Title: ");
        String title = br.readLine();
        print("Enter Book Author: ");
        String author = br.readLine();
        print("Enter publisher: ");
        String publisher = br.readLine();
        print("Enter the Price: ");
        double price = scanner.nextDouble();
        print("Enter the Stock: ");
        int stock = scanner.nextInt();

        return new Book(id, title, author, publisher, price, stock);
    }

    public void handleAddBook() {
        try {
            Book book = getBookdata();
            this.bookService.addBook(book);
        } catch (InputMismatchException ime) {
            print("Wrong data type entered!\n");
            scanner.next();
        } catch (Exception exception) {
            print(exception.getMessage());
            print("\n");

        }
    }

//    private void handleAddBook() {
//        while (true) {
//            Book student = readStudent();
//            if (student == null || student.getId() < 0) {
//                break;
//            }
//            try {
//                studentService.addStudent(student);
//            } catch (ValidatorException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    private void handleUpdateBook() {
        try {
            Book book = getBookdata();
            bookService.updateBook(book);
            print("Book Update Completed!\n");
        } catch (Exception exception) {
            print("Error during update!\n");
        }
    }

    private void handleClientSubmenu() throws SQLException {
        while (true) {
            print("1. Add a new client ");
            print("2. Update a client ");
            print("3. Delete a client ");
            print("4. Show a client ");
            print("5. Show all clients ");
            print("0. Return to main menu! \n");
            int option = scanner.nextInt();
            switch (option) {
                case 1 -> this.handleAddClient();
                case 2 -> this.handleUpdateClient();
                case 3 -> this.handleDeleteClient();
                case 4 -> this.handleShowOneClient();
                case 5 -> this.handleShowAllClients();
                case 0 -> {
                    return;}
                default ->
                    print("Invalid option.\n");

            }
        }
    }

    private void handleShowAllClients() throws SQLException {
        if (!this.clientService.getAllClients().isEmpty()) {
            for (Client client :
                    this.clientService.getAllClients()) {
                System.out.println(client.toString());
            }
        } else {
            System.out.println("Couldn't find clients.\n");
        }

    }

    private void handleShowOneClient() throws SQLException {
        print("Enter Client Id");
        Long id = scanner.nextLong();
        Optional<Client> client = this.clientService.readOneClient(id);
        if (client.isEmpty()) {
            print("Couldn't find the client with the given ID!\n");
        } else {
            System.out.println(client);
        }
    }

    private void handleDeleteClient() {
        try {
            print("Enter Client Id you want to delete: ");
            Long clientId = scanner.nextLong();
            System.out.println(this.clientService.readOneClient(clientId) + " will be deleted!");
            this.clientService.deleteOneClient(clientId);
            print("Deletion completed!\n");

        } catch (Exception exception) {
            print("Error occurred during delete...\n");
        }
    }

    public Client getClientData() {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        print("Add Client Id!: ");
        long id = scanner.nextLong();
        print("Enter Client First Name: ");
        String firstName = scanner.next();
        print("Enter Client Last Name: ");
        String lastName = scanner.next();
        print("Enter client phone number: ");
        String phoneNumber = scanner.next();

        return new Client(id, firstName, lastName, phoneNumber);
    }

    private void handleUpdateClient() {
        try {
            Client client = getClientData();
            this.clientService.updateClient(client);
            print("Client Update Completed!\n");
        } catch (Exception exception) {
            print("Error during update!\n");

        }
    }

    private void handleAddClient() {
        try {
            Client client = getClientData();
            this.clientService.addClient(client);
        } catch (InputMismatchException ime) {
            print("Wrong data type entered!\n");
            scanner.next();
        } catch (Exception exception) {
            print(exception.getMessage());
            print("\n");


        }
    }
}
