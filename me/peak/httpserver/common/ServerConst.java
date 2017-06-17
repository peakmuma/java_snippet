package me.peak.httpserver.common;

import java.util.HashMap;

/**
 * Created by peak on 2017/1/1.
 */
public class ServerConst {


    public static final HashMap<String, String> MIMETypeMap = new HashMap<>();

    static {
        MIMETypeMap.put("html","text/html");
        MIMETypeMap.put("htm","text/html");
        MIMETypeMap.put("js","application/x-javascript");
        MIMETypeMap.put("css","text/css");
        MIMETypeMap.put("jpg","image/jpeg");
        MIMETypeMap.put("jpeg","image/jpeg");
        MIMETypeMap.put("gif","image/gif");
        MIMETypeMap.put("png","image/png");
    }

    public static final HashMap<String,String> MethodMap = new HashMap<>();

    static {
        MethodMap.put("GET","");
        MethodMap.put("POST","");
        MethodMap.put("PUT","");
        MethodMap.put("DELETE","");
        MethodMap.put("HEAD","");
        MethodMap.put("TRACE","");
        MethodMap.put("CONNECT","");
        MethodMap.put("PATCH","");
        MethodMap.put("OPTIONS","");
    }
}
