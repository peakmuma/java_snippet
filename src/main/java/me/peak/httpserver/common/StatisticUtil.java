package me.peak.httpserver.common;

import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by peak on 2016/12/28.
 */
public class StatisticUtil {

    private static final ConcurrentHashMap<String, AtomicInteger> statisticIPMap = new ConcurrentHashMap<String, AtomicInteger>();

    public static void statisticIP(Socket socket){
        InetAddress clientAddress = socket.getInetAddress();
        String clientIP = clientAddress.getHostAddress();
        statisticIP(clientIP);
    }

    public static void statisticIP(String ip){
        System.out.println( "ip: " + ip);
        AtomicInteger visitCount = statisticIPMap.get(ip);
        if(visitCount == null){
            statisticIPMap.put(ip, new AtomicInteger(1));
        }else {
            visitCount.getAndIncrement();
        }
        System.out.println("count: " + statisticIPMap.get(ip).toString());
    }

    public static int getVisitCount(Socket socket){
        InetAddress clientAddress = socket.getInetAddress();
        String clientIP = clientAddress.getHostAddress();
        return getVisitCount(clientIP);
    }

    public static int getVisitCount(String ip){
        return statisticIPMap.get(ip).intValue();
    }
}
