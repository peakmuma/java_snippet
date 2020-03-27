package me.peak.httpserver.common;

/**
 * Created by peak on 2016/12/31.
 */
public class BusinessException extends Exception{

    private String message;

    public BusinessException(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
