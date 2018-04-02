package me.peak;

import me.peak.util.HttpClientUtil;
import org.apache.http.client.utils.HttpClientUtils;
import sun.net.www.http.HttpClient;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by peak on 2016/11/26.
 */
public class SimpleTestSnippet {
    public static void main(String[] args){
//        classTypeTest();
//        stringInternTest();
//        threadStatusTest();
//		Cache cache = new Cache();
//		System.out.println(cache.toString());

		int count = 0;
		for (int i = 0; i<1000000 ; i++) {
			int j = getRandom();
			if (j >=1 && j<=10) {
				count++;
			}
		}
		System.out.println(count);

//		System.out.println(getClassOpenNewChoiceResult(null));
//		System.out.println(getClassOpenNewChoiceResult("9"));
//		System.out.println(getClassOpenNewChoiceResult("1"));
//		System.out.println(getClassOpenNewChoiceResult("12221"));

//		for (int i = 1; i<100; i++){
//			System.out.println("ALTER TABLE ent_paper_user_question_" + String.format("%02d",i)  + " MODIFY score DECIMAL(4,1) DEFAULT '0' COMMENT '学员得分';");
////			System.out.println("CREATE TABLE ent_paper_user_question_" + String.format("%02d",i) +" LIKE ent_paper_user_question_00;");
//		}
//		System.out.println(new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));

//		List<String> strList1 = new ArrayList<>();
//		strList1.add("1");
//		strList1.add("2");
//		strList1.add("3");
//		strList1.add("4");
//		strList1.add("5");
//		strList1.add("6");
//
//		for (int i=0; i<strList1.size(); i++) {
//			if (strList1.get(i).equals("3")) {
//				strList1.remove("3");
//				i--;
//			} else {
//				System.out.println(strList1.get(i));
//			}
//		}

//		Iterator<String> iterator = strList1.iterator();
//		while (iterator.hasNext()) {
//			String str = iterator.next();
//			if (str.equals("3")) {
//				strList1.remove(str);
//			} else {
//				System.out.println(str);
//			}
//		}
//		for (String str : strList1) {
//			if (str.equals("3")) {
//				strList1.remove(str);
//			} else {
//				System.out.println(str);
//			}
//
//		}
	}

	public static int getRandom (){
		Random r = new Random();
		int random = r.nextInt(10) + 1;
		return random;
	}

	public static boolean isPhoneNumber(String mobile) {
		if (mobile == null) {
			return false;
		}
		String regExp = "^1\\d{10}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(mobile);
		return m.matches();
	}


	private static String getNoticeNewChoiceResult(String choiceResult) {
		if (choiceResult == null) {
			return "0";
		}
		//初始值0
		StringBuilder newChoiceResult = new StringBuilder("0");
		for (int i = 0; i < choiceResult.length(); i++) {
			char c = choiceResult.charAt(i);
			if (c == '1' || c == '2') {
				//1或2为合法值, 设置到相应的位置
				newChoiceResult.setCharAt(0, c);
			}
		}
		return newChoiceResult.toString();
	}


	private static String getClassOpenNewChoiceResult(String choiceResult) {
		if (choiceResult == null) {
			return "00";
		}
		//初始值00
		StringBuilder newChoiceResult = new StringBuilder("00");
		for (int i = 0; i < choiceResult.length(); i++) {
			char c = choiceResult.charAt(i);
			if (c == '1' || c == '2') {
				//1或2为合法值, 设置到相应的位置
				newChoiceResult.setCharAt(i % 2, c);
			}
		}
		return newChoiceResult.toString();
	}





    public static void classTypeTest(){
        Object o = new String();
        System.out.println(o.getClass()); // string
    }

    public static void stringInternTest(){
        String s1="123";
        String s2=new String("123");
        System.out.println(s1==s2);//false
        s2=s2.intern();
        System.out.println(s1==s2);//true
    }

    /**
     the result:
     before start, thread state is: NEW
     after start, thread state is: RUNNABLE
     thread run method over
     start over, thread state is: TERMINATED
     */
    public static void threadStatusTest(){
        try {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("thread run method over");
				}
			});
            System.out.println("before start, thread state is: " + t.getState().name());
            t.start();
            System.out.println("after start, thread state is: " + t.getState().name());
            Thread.sleep(1000);
            System.out.println("start over, thread state is: " + t.getState().name());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


