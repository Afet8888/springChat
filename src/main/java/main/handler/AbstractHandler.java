package main.handler;

import main.handler.messageHandler.AbstractMessageHandler;
import main.registery.Clientregistery;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class AbstractHandler extends TextWebSocketHandler {
    protected Clientregistery registery;
    protected AbstractMessageHandler messageHandler;

    public AbstractHandler (Clientregistery registery,AbstractMessageHandler messageHandler){
        this.registery = registery;
        this.messageHandler = messageHandler;
    }
}
