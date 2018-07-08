package main.handler.messageHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component

public class JacksonMessageHandler implements AbstractMessageHandler {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public <T> T convertToObject(String message, Class<T> clazz) throws IOException {
        return mapper.readValue(message,clazz);
    }

    @Override
    public <T> String stringify(T t) throws JsonProcessingException {
        return mapper.writeValueAsString(t);
    }
}
