package main.model.response;

import main.model.request.Body;

/**
 * Created by Sherif on 6/15/2018.
 */
public class ResponseObject {
    private ResponseHeader header;
    private Body body;

    public ResponseHeader getHeader() {
        return header;
    }

    public ResponseObject setHeader(ResponseHeader header) {
        this.header = header;
        return this;
    }

    public Body getBody() {
        return body;
    }

    public ResponseObject setBody(Body body) {
        this.body = body;
        return this;
    }
}
