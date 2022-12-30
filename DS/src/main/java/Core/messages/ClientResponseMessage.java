package Core.messages;

public class ClientResponseMessage {
    private String endpoint;

    public ClientResponseMessage() {
    }

    public ClientResponseMessage(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return this.endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

}
