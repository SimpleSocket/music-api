package net.unknown.musicapi.exceptions;

public class ApiRequestFailed extends RuntimeException{

    public ApiRequestFailed(String message){
        super(message);
    }

    public ApiRequestFailed(String message, Throwable cause){
        super(message, cause);
    }

}
