package main.model.request;

/**
 * Created by Sherif on 6/15/2018.
 */
public class RequestObject {

    private RequestHeader header;
    private Body body;

    public RequestHeader getHeader() {
        return header;
    }

    public RequestObject setHeader(RequestHeader header) {
        this.header = header;
        return this;
    }

    public Body getBody() {
        return body;
    }

    public RequestObject setBody(Body body) {
        this.body = body;
        return this;
    }
}
