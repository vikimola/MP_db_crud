package ro.ubb.bookstore.domain;

public class ClientSpendingDTO {
    public String clientName;
    public Double amount;

    public ClientSpendingDTO(String clientName, Double amount) {
        this.clientName = clientName;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ClientSpendingDTO{" +
                "clientName='" + clientName + '\'' +
                ", amount=" + amount +
                '}';
    }
}
