package me.peak.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.apache.http.impl.client.HttpClients.*;

public class HttpClientUtil {

	private RequestConfig requestConfig = RequestConfig.custom()
			.setSocketTimeout(60000)
			.setConnectTimeout(15000)
			.setConnectionRequestTimeout(15000)
			.build();
	private static HttpClientUtil instance = null;
	private HttpClientUtil(){}
	public static synchronized HttpClientUtil getInstance(){
		if (instance == null) {
			instance = new HttpClientUtil();
		}
		return instance;
	}

	public static void main(String[] args) {
//		String url = "https://127.0.0.1:8443/sv/rest/tts/callbill";
		String url = "http://127.0.0.1:8080/sv/rest/tts/callbill";
		String params = "{\"reqId\":789, \"endTime\":\"2018-03-14 19:38:05\", \"callDuration\":4}";
		String res = getInstance().sendHttpPost(url, params);
		System.out.println(res);
	}

	public String doGetWithStringResult(String uri) {
		String json = null;
		HttpGet request = new HttpGet(uri);
		try {
			request.setConfig(requestConfig);
			HttpResponse response = createDefault().execute(request);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				json = EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
			} else {
				if (response.getEntity() != null) {
					EntityUtils.consume(response.getEntity());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			request.releaseConnection();
		}
		return json;
	}

	/**
	 * 发送 post请求
	 * @param httpUrl 地址
	 */
	public String sendHttpPost(String httpUrl) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		return sendHttpPost(httpPost);
	}

	/**
	 * 发送 post请求
	 * @param httpUrl 地址
	 * @param params 参数(格式:key1=value1&key2=value2)
	 */
	public String sendHttpPost(String httpUrl, String params) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		try {
			//设置参数
			StringEntity stringEntity = new StringEntity(params, "UTF-8");
//			stringEntity.setContentType("application/x-www-form-urlencoded");
			stringEntity.setContentType("application/json");
			httpPost.setEntity(stringEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpPost(httpPost);
	}


	/**
	 * 发送 post请求
	 * @param httpUrl 地址
	 * @param params 参数
	 */
	public String sendHttpSoapPost(String httpUrl, String params, String cookie) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		try {
			//设置参数
			StringEntity stringEntity = new StringEntity(params, "UTF-8");
			stringEntity.setContentType("application/soap+xml");
			httpPost.setEntity(stringEntity);
			if (cookie != null) {
				httpPost.setHeader("Cookie", cookie);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpPost(httpPost);
	}


	/**
	 * 发送 post请求
	 * @param httpUrl 地址
	 * @param maps 参数
	 */
	public String sendHttpPost(String httpUrl, Map<String, String> maps) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		// 创建参数队列
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		for (String key : maps.keySet()) {
			nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
		}
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpPost(httpPost);
	}

	/**
	 * 发送Post请求
	 * @param httpPost
	 * @return
	 */
	private String sendHttpPost(HttpPost httpPost) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			SSLContext sslContext = new SSLContextBuilder()
					.loadTrustMaterial(null, (certificate, authType) -> true).build();
			httpClient = HttpClients.custom().setSSLContext(sslContext)
					.setSSLHostnameVerifier(new NoopHostnameVerifier())
					.build();
//			httpClient = createDefault();
			httpPost.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() != 200) {
				return null;
			}
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}

}
