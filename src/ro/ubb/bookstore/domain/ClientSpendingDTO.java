package ro.ubb.bookstore.domain;

public class ClientSpendingDTO {
    public String clientName;
    public Double amount;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

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
