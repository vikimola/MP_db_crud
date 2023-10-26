package ro.ubb.catalog.domain;


import java.time.LocalDate;

public class Purchase extends BaseEntity<Long> {
    private final long bookId;
    private final long clientId;
    private int numberSold;
    private LocalDate dateOfPurchase;

    public Purchase(long id, long bookId, long clientId, int numberSold, LocalDate dateOfPurchase) {
        super(id);
        this.bookId = bookId;
        this.clientId = clientId;
        this.numberSold = numberSold;
        this.dateOfPurchase = dateOfPurchase;
    }
    public long getBookId() {
        return bookId;
    }

    public long getClientId() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Purchase purchase = (Purchase) o;
        if (bookId != purchase.bookId) return false;
        if (clientId != purchase.clientId) return false;
        if (numberSold  != purchase.numberSold) return false;
        return dateOfPurchase.equals(purchase.dateOfPurchase);
    }

    public int hashCode() {
        int result = Long.hashCode(bookId);
        result = 31 * result + Long.hashCode(clientId);
        result = 31 * result + numberSold;
        result = 31 * result + dateOfPurchase.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "purchaseId=" + getId() +
                "bookId=" + bookId +
                ", clientId=" + clientId +
                ", numberSold=" + numberSold +
                ", dateOfPurchase='" + dateOfPurchase + '\'' +
                '}';
    }
}

