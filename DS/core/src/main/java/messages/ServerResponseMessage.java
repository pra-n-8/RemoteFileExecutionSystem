package messages;

import models.Response;
public class ServerResponseMessage {
    private Response message;

    public Response getMessage() {
        return message;
    }

    public void setMessage(Response message) {
        this.message = message;
    }
}
