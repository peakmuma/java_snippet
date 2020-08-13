package me.peak.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
//import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HttpClient工具类.
 * 需根据实际并发调整参数.
 */
public class HttpClientUtil {
	private final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

	/**
	 * Defines the socket timeout ({@code SO_TIMEOUT}) in milliseconds,
	 * which is the timeout for waiting for data  or, put differently,
	 * a maximum period inactivity between two consecutive data packets).
	 * <p>
	 * A timeout value of zero is interpreted as an infinite timeout.
	 * A negative value is interpreted as undefined (system default).
	 * </p>
	 * <p>
	 * Default: {@code -1}
	 * </p>
	 */
	private int defaultSocketTimeout = 10000;

	/**
	 * Determines the timeout in milliseconds until a connection is established.
	 * A timeout value of zero is interpreted as an infinite timeout.
	 * <p>
	 * A timeout value of zero is interpreted as an infinite timeout.
	 * A negative value is interpreted as undefined (system default).
	 * </p>
	 * <p>
	 * Default: {@code -1}
	 * </p>
	 */
	private int connectTimeout = 10000;

	/**
	 * Returns the timeout in milliseconds used when requesting a connection
	 * from the connection manager. A timeout value of zero is interpreted
	 * as an infinite timeout.
	 * <p>
	 * A timeout value of zero is interpreted as an infinite timeout.
	 * A negative value is interpreted as undefined (system default).
	 * </p>
	 * <p>
	 * Default: {@code -1}
	 * </p>
	 */
	private int connectionRequestTimeout = 10000;

	/**
	 * the maximum total number of connections in the pool.
	 * Default: 20
	 */
	private  int maxConnTotal = 100;

	/**
	 * the maximum number of connections to a particular host.
	 * Default: 2
	 */
	private int maxConnPerRoute = 50;
	/**
	 * single HttpClientUtils instance.
	 */
	private static volatile HttpClientUtil ins;

	/**
	 * single HttpClient instance.
	 */
	private HttpClient client;

	/**
	 * single RequestConfig instance.
	 */
	private RequestConfig defaultConfig;

	private Map<Integer, RequestConfig> configMap;

	private HttpClientUtil() {
		// first init
		if (defaultConfig == null) {
			defaultConfig = RequestConfig.custom()
					.setConnectionRequestTimeout(connectionRequestTimeout)
					.setConnectTimeout(connectTimeout)
					.setSocketTimeout(defaultSocketTimeout).build();
		}

		if (client == null) {
			client = HttpClients.custom().setDefaultRequestConfig(defaultConfig)
					.setMaxConnTotal(maxConnTotal)
					.setMaxConnPerRoute(maxConnPerRoute).build();
		}

		if (configMap == null) {
			configMap = new HashMap<>();
			configMap.put(defaultSocketTimeout, defaultConfig);
		}
	}

	public static HttpClientUtil getInstance() {
		if (ins == null) {
			synchronized (HttpClientUtil.class) {
				if (ins == null) {
					ins = new HttpClientUtil();
				}
			}
		}
		return ins;
	}


	public String doGet(String uri) {
		return doGet(uri, defaultSocketTimeout);
	}

	public String doGet(String uri, int socketTimeout) {
		String json = null;
		log.debug("========= Call [{}] Start ==========", uri);
		HttpGet request = new HttpGet(uri);
		try {
			request.setConfig(getConfig(socketTimeout));
			HttpResponse response = client.execute(request);
			log.debug("Response status code: {}", response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				json = EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
				log.debug("Result json: {}", json);
			} else {
				if (response.getEntity() != null) {
					EntityUtils.consume(response.getEntity());
				}
			}
		} catch (Exception e) {
			log.error("HttpClient has exception! message: {}", e.getMessage(), e);
		} finally {
			request.releaseConnection();
		}
		log.debug("========= Call [{}] End ==========", uri);
		return json;
	}

	public String doPost(String uri, Map<String, String> paramMap) {
		return doPost(uri, paramMap, defaultSocketTimeout);
	}

	public String doPost(String uri, Map<String, String> paramMap, int socketTimeout) {
		log.debug("========= Call [{}] Param {}==========", uri, paramMap);
		List<NameValuePair> params = new ArrayList<>();
		if (paramMap != null && !paramMap.isEmpty()) {
			for (String key : paramMap.keySet()) {
				params.add(new BasicNameValuePair(key, paramMap.get(key)));
			}
		}
		HttpEntity entity = new UrlEncodedFormEntity(params, Charset.forName("UTF-8"));
		return doPost(uri, entity, socketTimeout);
	}

	public String doPost(String uri, JSONObject jsonParameters) {
		log.debug("========= Call [{}] Param {}==========", uri, jsonParameters.toJSONString());
		HttpEntity entity = new StringEntity(jsonParameters.toJSONString(), ContentType.APPLICATION_JSON);
		return doPost(uri, entity, defaultSocketTimeout);
	}

	public String doPost(String uri, String paramString) {
		log.debug("========= Call [{}] Param {}==========", uri, paramString);
		HttpEntity entity = new StringEntity(paramString, Charset.forName("utf-8"));
		return doPost(uri, entity, defaultSocketTimeout);
	}


//	public String doPostFile(String uri, String filedName, String fileName, byte[] bytes, Map<String, String> paramMap, String fileBrowserType){
//		try {
//			fileName = URLEncoder.encode(fileName, "utf-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//		builder.setCharset(Consts.UTF_8);
//		builder.addBinaryBody(filedName, bytes, ContentType.create(fileBrowserType), fileName);
//		if (paramMap != null && !paramMap.isEmpty()) {
//			for (Map.Entry<String, String> e : paramMap.entrySet()) {
//				builder.addTextBody(e.getKey(), e.getValue(), ContentType.create(MimeTypeUtils.TEXT_PLAIN_VALUE, Charset.forName("UTF-8")));
//			}
//		}
//		HttpEntity entity = builder.build();
//		return doPost(uri, entity, defaultSocketTimeout);
//	}
//
//	public String doPostFile(String uri, String filedName, String fileName, byte[] bytes, Map<String, String> paramMap){
//		try {
//			fileName = URLEncoder.encode(fileName, "utf-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//
//		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//		builder.addBinaryBody(filedName, bytes, ContentType.DEFAULT_BINARY, fileName);
//		if (paramMap != null && !paramMap.isEmpty()) {
//			for (Map.Entry<String, String> e : paramMap.entrySet()) {
//				builder.addTextBody(e.getKey(), e.getValue(), ContentType.create(MimeTypeUtils.TEXT_PLAIN_VALUE, Charset.forName("UTF-8")));
//			}
//		}
//		HttpEntity entity = builder.build();
//		return doPost(uri, entity, defaultSocketTimeout);
//	}


	//下载文件
	public byte[] doPostWithByteResult(String uri, Map<String, String> paramMap) {
		log.debug("========= Call [{}] Param {}==========", uri, paramMap);
		List<NameValuePair> params = new ArrayList<>();
		if (paramMap != null && !paramMap.isEmpty()) {
			for (String key : paramMap.keySet()) {
				params.add(new BasicNameValuePair(key, paramMap.get(key)));
			}
		}
		HttpEntity entity = new UrlEncodedFormEntity(params, Charset.forName("UTF-8"));
		return doPostWithByteResult(uri, entity);
	}


	public byte[] doPostWithByteResult(String uri, HttpEntity entity) {
		log.debug("========= Call [{}] Start ==========", uri);
		HttpPost request = new HttpPost(uri);
		try {
			request.setConfig(defaultConfig);
			request.setEntity(entity);
			HttpResponse response = client.execute(request);
			log.debug("Response status code: {}", response.getStatusLine().getStatusCode());
			log.debug("========= Call [{}] End ==========", uri);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				return EntityUtils.toByteArray(response.getEntity());
			} else {
				if (response.getEntity() != null) {
					EntityUtils.consume(response.getEntity());
				}
				return null;
			}
		} catch (Exception e) {
			log.error("HttpClient has exception! message: {}", e.getMessage(), e);
			return null;
		} finally {
			request.releaseConnection();
		}
	}


	public String doPost(String uri, HttpEntity entity, int socketTimeout) {
		log.debug("========= Call [{}] Start ==========", uri);
		HttpPost request = new HttpPost(uri);
		String json = null;
		try {
			request.setConfig(getConfig(socketTimeout));
			request.setEntity(entity);
			HttpResponse response = client.execute(request);
			log.debug("Response status code: {}", response.getStatusLine().getStatusCode());
			log.debug("========= Call [{}] End ==========", uri);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				json = EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
				if (json.length() < 50000) {
					log.debug("Result json : {}", json);
				} else {
					log.debug("Result json length : {}", json.length());
				}
			} else {
				if (response.getEntity() != null) {
					EntityUtils.consume(response.getEntity());
				}
			}
			return json;
		} catch (Exception e) {
			log.error("HttpClient has exception! message: {}", e.getMessage(), e);
			return null;
		} finally {
			request.releaseConnection();
		}
	}

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
		return doPost(httpUrl, httpPost.getEntity(), defaultSocketTimeout);
	}

	private RequestConfig getConfig(int socketTimeout) {
		if (socketTimeout < 0) {
			return defaultConfig;
		}
		RequestConfig config = configMap.get(socketTimeout);
		if (config == null) {
			config = RequestConfig.custom()
					.setConnectionRequestTimeout(connectionRequestTimeout)
					.setConnectTimeout(connectTimeout)
					.setSocketTimeout(socketTimeout).build();
			configMap.put(socketTimeout, config);
		}
		return config;
	}

}
