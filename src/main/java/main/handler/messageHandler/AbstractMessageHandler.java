package main.handler.messageHandler;

public interface AbstractMessageHandler {
    <T>  T convertToObject(String message,Class<T> clazz) throws  Exception;
    <T> String stringify(T t) throws Exception;
}
