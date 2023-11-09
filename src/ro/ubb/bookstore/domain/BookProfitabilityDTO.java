package ro.ubb.bookstore.domain;

public class BookProfitabilityDTO {
    private String bookTitle;
    private double bookProfitability;
    public BookProfitabilityDTO(String bookTitle, double bookProfitability){
        this.bookTitle =bookTitle;
        this.bookProfitability=bookProfitability;

    }
    public String toString() {
        return "BookProfitabilityDTO{" +
                "bookTitle='" + bookTitle + '\'' +
                ", bookProfitability=" + bookProfitability +
                '}';
    }
}
