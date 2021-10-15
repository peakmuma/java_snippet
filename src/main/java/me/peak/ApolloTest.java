package me.peak;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.*;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ApolloTest {

    static String host = "http://10.185.141.178:8086";
//    static String onlineHost = "http://10.20.57.10:8086";
    static String onlineHost = "https://bike.sankuai.com/apollo/selfbiservice";
    static String accessToken = "eAGFjytLBFEYhjmyyLJJJhkn7k5wzu0753wmnWXE6CUIFpk5l6h_wODaNFrEDV5gYRFBMa1YFLTYxWpSJwkaDeIuYja95eHleepk4vvoayzefx90b1PekK5gFIKTYTrmBhkIAeAdSu0FMiWSIFRZ2KLQXGePJGqt-HLZ-nW_KTVVyOisnuM5IMsQQKLK25ryjOeSxoPd0-e7tFnj_x6bkdIMmf94uD-8SReePq-Hs02SxvjiUnvD-Sh66fWry5PXnbO340513qsuOpO1eOvqoNX8hfdI_U-sS6aoxeCFBx2CdqABTXBlCbZkBXqjzNowjBkjKJXMwGosGJNoKViWKEnRoA1QeD0igCrvfgCipWYh**eAEFwYEBwDAEBMCVEKLGeT72H6F3pm78TMGbui3pHRWdD4c3TGsFQ3UZyXfCkAVwGNK9bu8HKtgRxQ";
    static OkHttpClient httpClient = new OkHttpClient();

    static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");
    static final MediaType WWW_FORM_TYPE = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");

    public static void main(String[] args) throws IOException {
        String urlPath = "/bimetadata/getQueryTableInfo";
        Map<String,String> param = new HashMap<>();
        param.put("platform", "Apollo");
        param.put("username", "gaolei33@meituan.com");
        compareWithOnline(urlPath, param);

        urlPath = "/bi/getTableResult";
        param.clear();
        param.put("username", "gaolei33@meituan.com");
        param.put("platform", "Apollo");
        param.put("table_filter", "{\"AND\":[{\"column\":\"dt\",\"feature\":\"normal\",\"type\":\"Date\",\"operator\":\"PREVIOUS\",\"value\":[\"1,day\"],\"defaultDtType\":\"raw\",\"dimOrMetric\":1,\"caliberDescRelevance\":\"\"},{\"column\":\"region_code\",\"feature\":\"normal\",\"type\":\"String\",\"operator\":\"=\",\"value\":[\"MBK_86\",\"R024\",\"R10001009\",\"R500106\",\"R0579\"],\"dimOrMetric\":1,\"caliberDescRelevance\":\"\"}]}");
        param.put("table_model_id", "7761");
        param.put("extraQueryParams", "{\"dailyRate\":false,\"orders\":[],\"queryTotal\":false,\"weeklyRate\":false}");
        compareWithOnline(urlPath, param);

        urlPath = "/bi/getTableModelList";
        param.clear();
        param.put("isCheckPermission", "false");
        compareWithOnline(urlPath, param);

        urlPath = "/bi/getReportResult";
        param.clear();
        param.put("username", "gaolei33@meituan.com");
        param.put("platform", "Apollo");
        param.put("citycode", "MBK_86");
        param.put("report_id", "741");
        compareWithOnline(urlPath, param);

        urlPath = "/bimetadata/getTag";
        param.clear();
        param.put("tagType", "1");
        param.put("platform", "Apollo");
        compareWithOnline(urlPath, param);

//        urlPath = "/user/info";
//        param.clear();
//        param.put("platform", "Apollo");
//        compareWithOnline(urlPath, param);
//
//        urlPath = "/user/authorised/cities?platform=Apollo&username=gaolei33%40meituan.com";
//        param.clear();
//        param.put("platform", "Apollo");
//        param.put("username", "gaolei33@meituan.com");
//        compareWithOnline(urlPath, param);

//        urlPath = "/authorised/hasPermission";
//        param.clear();
//        param.put("resourceCode", "apollo_vtwo_menu_main");
//        compareWithOnline(urlPath, param);

        urlPath = "/bimetadata/getFieldTypeOperator";
        param.clear();
        param.put("platform", "Apollo");
        compareWithOnline(urlPath, param);

//        urlPath = "/mainpagekpi/getRealTimeCoreData";
//        param.clear();
//        param.put("reportdate","2021-09-17");
//        param.put("platform", "Apollo");
//        param.put("citycode", "MBK_86");
//        param.put("realTimeType", "BIKE_ORDERS");
//        compareWithOnline(urlPath, param);


    }

    public static boolean compareWithOnline(String urlPath, Map<String, String> param) throws IOException {
        JsonObject res1 = getResponse(host + urlPath, param);
        JsonObject res2 = getResponse(onlineHost + urlPath, param);
        return compareJson(res1, res2);
    }

    public static boolean compareWithExpectValue(String urlPath, Map<String, String> param, String expectValue) throws IOException {
        JsonElement jsonElement = JsonParser.parseString(expectValue);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        return compareWithExpectValue(urlPath, param, jsonObject);
    }

    public static boolean compareWithExpectValue(String urlPath, Map<String, String> param, JsonObject expectValue) throws IOException {
        JsonObject res1 = getResponse(host + urlPath, param);
        return compareJson(res1, expectValue);
    }

    public static JsonObject getResponse(String url, Map<String,String> param) throws IOException {
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        for (String key : param.keySet()) {
            bodyBuilder.add(key, param.get(key));
        }
        RequestBody requestBody = bodyBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .header("access-token", accessToken)
                .post(requestBody).build();
        Response response = httpClient.newCall(request).execute();
        String responseStr = response.body().string();
        JsonElement jsonElement = JsonParser.parseString(responseStr);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Assert.isTrue(jsonObject.get("resultCode").getAsInt() == 200, "result not 200");
        return jsonObject;
    }

    public static JsonObject getGetResponse(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .header("access-token", accessToken).build();
        Response response = httpClient.newCall(request).execute();
        String responseStr = response.body().string();
        JsonElement jsonElement = JsonParser.parseString(responseStr);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Assert.isTrue(jsonObject.get("resultCode").getAsInt() == 200, "result not 200");
        return jsonObject;
    }


    public static boolean compareJson(JsonObject json1, JsonObject json2) {
        if (!json1.equals(json2)) {
            System.out.println("compare res false");
            System.out.println("json1: " + json1.toString());
            System.out.println("json2: " + json2.toString());
        }
        System.out.println("compare res true");
        return true;
    }

    public static boolean compareJson(String jsonStr1, String jsonStr2) {
        JsonElement jsonElement1 = JsonParser.parseString(jsonStr1);
        JsonElement jsonElement2 = JsonParser.parseString(jsonStr2);
        JsonObject jsonObject1 = jsonElement1.getAsJsonObject();
        JsonObject jsonObject2 = jsonElement2.getAsJsonObject();
        System.out.println(jsonObject1.equals(jsonObject2));
        if (!jsonElement1.equals(jsonElement2)) {
            System.out.println("compare res false");
            System.out.println("str1: " + jsonStr1);
            System.out.println("str2: " + jsonStr2);
            return false;
        }
        System.out.println("str1: " + jsonStr1);
        System.out.println("str2: " + jsonStr2);
        System.out.println("compare res true");
        return true;
    }

}
