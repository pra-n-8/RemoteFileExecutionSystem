package messages;

import models.Response;

public class ClientResponseMessage extends Response {

    public ClientResponseMessage(String execType, String url) {
        super(execType, url);
    }
}
