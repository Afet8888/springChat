package main.model.request;

/**
 * Created by Sherif on 6/15/2018.
 */
public class RequestHeader {

    private String receiverId;

    public String getReceiverId() {
        return receiverId;
    }

    public RequestHeader setReceiverId(String receiverId) {
        this.receiverId = receiverId;
        return this;
    }
}
