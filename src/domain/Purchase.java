package domain;


import java.time.LocalDate;

public class Purchase extends Entity {
    private final int bookId;
    private final int clientId;
    private int numberSold;
    private LocalDate dateOfPurchase;

    public Purchase(int purchaseId, int bookId, int clientId, int numberSold, LocalDate dateOfPurchase) {
        super(purchaseId);
        this.bookId = bookId;
        this.clientId = clientId;
        this.numberSold = numberSold;
        this.dateOfPurchase = dateOfPurchase;
    }
    public int getBookId() {
        return bookId;
    }

    public int getClientId() {
        return clientId;
    }

    public int getNumberSold() {
        return numberSold;
    }

    public void setNumberSold(int numberSold) {
        this.numberSold = numberSold;
    }
    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }
    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }


    @Override
    public String toString() {
        return "Purchase{" +
                "bookId=" + bookId +
                ", clientId=" + clientId +
                ", numberSold=" + numberSold +
                ", dateOfPurchase='" + dateOfPurchase + '\'' +
                '}';
    }
}

