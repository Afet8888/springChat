package main.handler;

import main.handler.messageHandler.AbstractMessageHandler;
import main.model.request.Body;
import main.model.request.RequestHeader;
import main.model.request.RequestObject;
import main.model.response.ResponseHeader;
import main.model.response.ResponseObject;
import main.registery.Clientregistery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;

import static main.utility.Log.*;

@Component
public class MyHandler extends AbstractHandler {


    @Autowired
    public MyHandler(Clientregistery registery, AbstractMessageHandler messageHandler) {
        super(registery, messageHandler);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) {
        info("New connection established Session id : " + webSocketSession.getId());
        publishToAll(1, webSocketSession.getId());
        List<String> clientList = new ArrayList<>(registery.getAll().keySet());
        try {
            ResponseObject responseObject = buildResponseObject(3, null, messageHandler
                    .stringify(clientList));
            sendMessage(webSocketSession, responseObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        registery.addClient(webSocketSession.getId(), webSocketSession);
    }

    @Override
    public void handleTextMessage(WebSocketSession webSocketSession, TextMessage message) {
        //info("New message from session id: "+webSocketSession.getId()+" message: "+message.getPayload());
        try {
            RequestObject msg = messageHandler.convertToObject(message.getPayload(), RequestObject.class);
            ResponseObject responceObject = buildResponseObject(0, webSocketSession.getId(), msg.getBody().getMessage());
            sendMessage(msg.getHeader().getReceiverId(), responceObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus status) {
        info("Session closed id: " + webSocketSession.getId());
        registery.remove(webSocketSession.getId());
        publishToAll(2, webSocketSession.getId());
    }

    private void publishToAll(Integer type, String id) {
        ResponseObject responseObject = buildResponseObject(type, null, id);
        registery.getAll().forEach((k, v) -> {
            sendMessage((WebSocketSession) v, responseObject);
        });
    }

    private void sendMessage(String receiverId, ResponseObject message) {
        WebSocketSession session = (WebSocketSession) registery.getClient(receiverId);
        sendMessage(session, message);
    }

    private void sendMessage(WebSocketSession session, ResponseObject message) {
        try {
            session.sendMessage(new TextMessage(messageHandler.stringify(message)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ResponseObject buildResponseObject(Integer type, String senderId, String msg) {
        return new ResponseObject()
                .setHeader(new ResponseHeader()
                        .setType(type)
                        .setSenderId(senderId))
                .setBody(new Body()
                        .setMessage(msg));
    }
}
