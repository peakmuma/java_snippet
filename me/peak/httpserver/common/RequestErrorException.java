package me.peak.httpserver.common;

/**
 * Created by peak on 2017/1/2.
 */
public class RequestErrorException extends Exception{

    private String message;

    public RequestErrorException(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
