package ro.ubb.catalog.domain;

public class Book extends BaseEntity<Long> {
    private String title;
    private String author;
    private String publisher;
    private double price;
    private int stock;
    public Book(String title, String author, String publisher, double price,
                int stock){
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;
        if (!title.equals(book.title)) return false;
        if (!author.equals(book.author)) return false;
        if (!publisher.equals(book.publisher)) return false;
        if (price != book.price) return false;
        return stock != book.stock;
    }

    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + publisher.hashCode();
        result = 31 * result + Double.hashCode(price);
        result = 31 * result + stock;
        return result;
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

