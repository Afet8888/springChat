package main.model.response;

/**
 * Created by Sherif on 6/15/2018.
 */
public class ResponseHeader {

    private String senderId;
    private Integer type = 0;

    public String getSenderId() {
        return senderId;
    }

    public ResponseHeader setSenderId(String senderId) {
        this.senderId = senderId;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public ResponseHeader setType(Integer type) {
        this.type = type;
        return this;
    }
}
