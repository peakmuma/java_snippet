package me.peak.httpserver.common;

import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

/**
 * Created by peak on 2017/1/1.
 */
public class Request {

    private String methodName;

    private String uri;

    private String protocol;

    private HashMap<String, String> fieldsMap = new HashMap<>();

    public static Request parseReqFromBytes(byte[] buf, int length) throws BusinessException, RequestErrorException {
        if(buf == null || length>buf.length){
            throw new BusinessException("buffer error");
        }
        Request request = new Request();
        String firstLine = readLine(buf, 0);

        int firstSpaceIndex = firstLine.indexOf(' ');
        if(firstSpaceIndex<0){
            throw new RequestErrorException("Request method did not found");
        }

        request.methodName = firstLine.substring(0, firstSpaceIndex);

        if(ServerConst.MethodMap.get(request.methodName) == null){
            throw new RequestErrorException("Request method invalid");
        }

        int secondSpaceIndex = firstLine.indexOf(' ', firstSpaceIndex+1);
        if(secondSpaceIndex<0){
            throw new RequestErrorException("Request uri did not found");
        }

        String uri = firstLine.substring(firstSpaceIndex+1, secondSpaceIndex);
        try {
            request.uri = URLDecoder.decode(uri,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if(secondSpaceIndex+1 == firstLine.length()){
            throw new RequestErrorException("Request protocol did not found");
        }
        request.protocol = firstLine.substring(secondSpaceIndex+1);

        int lineStartIndex = firstLine.length()+2;
        while (lineStartIndex<length){
            String line = readLine(buf, lineStartIndex);
            int splitIndex = line.indexOf(':');
            if(splitIndex>0){
                String fieldName = line.substring(0, splitIndex).trim().toLowerCase();
                String fieldValue = line.substring(splitIndex+1).trim();
                request.fieldsMap.put(fieldName, fieldValue);
            }
            lineStartIndex += line.length()+2;
        }
        return request;
    }

    private static String readLine(byte[] buf, int start){
        StringBuilder sb = new StringBuilder();
        for(int i=start; i+1<buf.length; i++){
            if(buf[i]=='\r' && buf[i+1]=='\n'){
                break;
            }else{
                sb.append((char)buf[i]);
            }
        }
        return sb.toString();
    }

    public String getUri() {
        return this.uri;
    }

    public HashMap getFieldsMap(){
        return this.fieldsMap;
    }
}
