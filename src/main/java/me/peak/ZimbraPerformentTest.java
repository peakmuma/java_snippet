package me.peak;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import me.peak.util.HttpClientUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class ZimbraPerformentTest {

	public static ConcurrentHashMap<String, String> authTokenMap = new ConcurrentHashMap<>(25000);
	public static List<Integer> timeRes = new ArrayList<>(1000);
	public static String content;
	public static int startAccountId  = 2000;
	public static int accountNum = 200;
	public static int endAccountId  = startAccountId + accountNum;
	public static int everyAccountSendCount = 10;
	public static int maxThreadNum = 50;
	public static int testIndex = 12;
	public static String accountPrefix = "performance_";

	static {
		StringBuilder contentSb = new StringBuilder("this is test content, length is about 10k");
		for (int i = 0; i< 2000; i++){
			contentSb.append("aaaaa");
		}
		content = contentSb.toString();
	}

	public static void main(String[] args) throws InterruptedException {
//		sendMsg("rdtest5", "rdtest6", "the subject");
		CountDownLatch countDownLatch = new CountDownLatch(maxThreadNum);
		for (int threadIndex = 0; threadIndex < maxThreadNum; threadIndex++) {
			Thread thread = new Thread(new TestZimbraThread(threadIndex, countDownLatch));
			thread.start();
		}
		long startTime = System.currentTimeMillis();
		countDownLatch.await();
		System.out.println("total time is " + (System.currentTimeMillis() - startTime) / 1000 + "s" );
		System.out.println("thread num is " + maxThreadNum);
		System.out.println("total mail num is " + (accountNum)  * everyAccountSendCount);
		System.out.println("send success num is " + timeRes.size());
		Collections.sort(timeRes);
		System.out.println("min time is " + timeRes.get(0) + "ms");
		int index = (int) (timeRes.size() * 0.5);
		System.out.println("50% request less than " + timeRes.get(index) + "ms");
		index = (int) (timeRes.size() * 0.9);
		System.out.println("90% request less than " + timeRes.get(index) + "ms");
		index = (int) (timeRes.size() * 0.99);
		System.out.println("99% request less than " + timeRes.get(index) + "ms");
		System.out.println("max time is " + timeRes.get(timeRes.size() -1 ) + "ms");
	}

	public static String getAuthToken(String userName) {
		String authToken = authTokenMap.get(userName);
		int i = 0;
		while (authToken == null || i++ < 4) {
			String url = "https://mail.shangdejigou.cn/service/soap/AuthRequest";
			String param = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\"><soap:Header><context xmlns=\"urn:zimbra\"><userAgent name=\"ZimbraWebClient - GC69 (Win)\"/><session/><authTokenControl voidOnExpired=\"1\"/><format type=\"js\"/></context></soap:Header><soap:Body><AuthRequest xmlns=\"urn:zimbraAccount\" ><account by=\"name\"> " + userName + "</account><password>rdtest</password></AuthRequest></soap:Body></soap:Envelope>";
			String res = HttpClientUtil.getInstance().sendHttpSoapPost(url, param, null);
			if (res == null) {
				continue;
			}
			JSONObject json = JSON.parseObject(res);
			authToken = json.getJSONObject("Body").getJSONObject("AuthResponse").getJSONArray("authToken").getJSONObject(0).getString("_content");
			authTokenMap.put(userName, authToken);
		}
		return authTokenMap.get(userName);
	}


	public static void sendMsg(String from, String to, String subject) {
		String url = "https://mail.shangdejigou.cn/service/soap/SendMsgRequest";
		String param = "{\"Header\":{\"context\":{\"_jsns\":\"urn:zimbra\",\"userAgent\":{\"name\":\"ZimbraWebClient - GC69 (Win)\",\"version\":\"8.8.8_GA_2009\"},\"account\":{\"_content\":\"" + from + "@shangdejigou.cn\",\"by\":\"name\"}}},\"Body\":{\"SendMsgRequest\":{\"_jsns\":\"urn:zimbraMail\",\"m\":{\"e\":[{\"t\":\"t\",\"a\":\"" + to + "@shangdejigou.cn\"},{\"t\":\"f\",\"a\":\"" + from + "@shangdejigou.cn\"}],\"su\":{\"_content\":\"" + subject +"\"},\"mp\":[{\"ct\":\"multipart/alternative\",\"mp\":[{\"ct\":\"text/plain\",\"content\":{\"_content\":\" " + content + " \"}}]}]}}}}";
		String cookie = "ZM_AUTH_TOKEN=" + getAuthToken(from);
		long start = System.nanoTime();
		String res = HttpClientUtil.getInstance().sendHttpSoapPost(url, param, cookie);
		long end = System.nanoTime();
		if (res != null) {
			int a = (int)((end-start)/1000000);
			addTimeRes(a);
		}
	}

	public static synchronized void addTimeRes(int a) {
		timeRes.add(a);
	}


	public static class TestZimbraThread implements Runnable{

		int threadIndex;
		CountDownLatch countDownLatch;

		public TestZimbraThread(int threadIndex, CountDownLatch countDownLatch) {
			this.threadIndex = threadIndex;
			this.countDownLatch = countDownLatch;
		}

		@Override
		public void run() {
			try {
				for (int fromIndex = threadIndex + startAccountId; fromIndex < endAccountId; fromIndex += maxThreadNum) {
					String from = accountPrefix + fromIndex;
					for (int toIndex = 1; toIndex <= everyAccountSendCount; toIndex++) {
						String to = accountPrefix + ((fromIndex + toIndex) % (startAccountId + accountNum) );
						String subject = "the " + testIndex + " test from " + from;
						sendMsg(from, to, subject);
					}
					System.out.println(from + " send over");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				countDownLatch.countDown();
			}
		}
	}
}
