package me.peak.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by peak on 2016/11/25.
 */
public class HttpClientSnippet {
   public static void main(String[] args){
      CloseableHttpClient httpClient = HttpClients.createDefault();
      String url = "http://www.baidu.com";
      HttpGet httpGet = new HttpGet(url);
      try {
         CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
         HttpEntity entity = httpResponse.getEntity();
         String result = EntityUtils.toString(entity, Charset.forName("utf-8"));
         System.out.println(result);
         httpResponse.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
