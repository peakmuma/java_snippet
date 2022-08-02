package me.peak.util;

import com.uber.h3core.H3Core;
import com.uber.h3core.util.GeoCoord;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class H3Util {

    static H3Core h3;

    static {
        try {
            log.info("init h3util start");
            h3 = H3Core.newInstance();
            log.info("init h3util end");
        } catch (Exception e) {
            log.error("init h3util error", e);
        }
    }

    public static List<Long> polyfill(List<GeoCoord> points, List<List<GeoCoord>> holes, int res) {
        return h3.polyfill(points, holes, res);
    }

    public static GeoCoord h3ToGeo(Long h3Code){
        if(h3Code == null){
            return null;
        }
        return h3.h3ToGeo(h3Code);
    }

    static Map<Integer, Double> h3LevelAreaMap = new HashMap<Integer, Double>(){{
        put(5,252.9033645);
        put(6,36.1290521);
        put(7,5.1612932);
        put(8,0.7373276);
        put(9,0.1053325);
        put(10,0.0150475);
        put(11,0.0021496);
        put(12,0.0003071);
        put(13,0.0000439);
    }};

    public static void testMulti(){
        int res = 10;

        List<GeoCoord> points = new ArrayList<>();
        GeoCoord geoCoord1 = new GeoCoord(39.925716329983885, 116.50663543235305);
        GeoCoord geoCoord2 = new GeoCoord(39.925716329983885, 116.55921688453259);
        GeoCoord geoCoord3 = new GeoCoord(40.00068964513176, 116.55921688453259);
        GeoCoord geoCoord4 = new GeoCoord(40.00068964513176, 116.50663543235305);
        points.add(geoCoord1);
        points.add(geoCoord2);
        points.add(geoCoord3);
        points.add(geoCoord4);

        long start = System.nanoTime();
        List<Long> h3Codes = polyfill(points, null, res);
        System.out.println("size " + h3Codes.size());
        System.out.println("cost " + (System.nanoTime() - start)/1000);

        start = System.nanoTime();
        for (Long h3Code : h3Codes) {
            h3ToGeo(h3Code);
        }
        System.out.println("cost " + (System.nanoTime() - start)/1000);
    }

    public static void calcArea() {
        double lon1 = 116.50663543235305;
        double lon2 = 116.55921688453259;
        double lat1 = 39.925716329983885;
        double lat2 = 40.00068964513176;
        double ten1 = getDistance(lon1, lat1, lon1, lat2);
        double ten2 = getDistance(lon1, lat1, lon2, lat1);
        double ten3 = getDistance(lon2, lat1, lon2, lat2);
        double ten4 = getDistance(lon1, lat2, lon2, lat2);
        System.out.println(ten1);
        System.out.println(ten2);
        System.out.println(ten3);
        System.out.println(ten4);
        System.out.println((ten1 * ten2)/1000000);

    }


    private static final  double EARTH_RADIUS = 6378137;//赤道半径
    private static double rad(double d){
        return d * Math.PI / 180.0;
    }

    public static double getDistance(double lon1,double lat1,double lon2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2)+Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        return s;//单位米
    }

    public static void main(String[] args) throws InterruptedException {
//        testMulti();
        calcArea();
    }
}
