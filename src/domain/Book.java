package domain;

public class Book extends Entity{
    private String title;
    private String author;
    private String publisher;
    private double price;
    private int stock;
    public Book(int bookId, String title, String author, String publisher, double price,
                int stock){
        super(bookId);
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.stock = stock;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + getId() +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}

