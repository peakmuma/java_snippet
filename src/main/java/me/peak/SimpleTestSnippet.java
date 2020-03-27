package me.peak;


import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
//		removeElementTest();
//		debugTest();
//		floatRoundTest();

//		testSwitch(1);
//		System.out.println("-------1 result end----------------");
//		testSwitch(2);
//		System.out.println("--------2 result end----------------");
//		testSwitch(3);
//		System.out.println("--------3 result end----------------");
//		testSwitch(4);
//		System.out.println("---------4 result end----------------");
//		testSwitch(5);
//		System.out.println("---------5 result end----------------");
//		testSwitch(6);
//		System.out.println("---------6 result end----------------");

//		addDouble();

//		testChangeSubArray();
//		System.out.println(divide(1, 2));
//		System.out.println(divide(0, 2));
//		System.out.println(divide(1, 0));
//		System.out.println(divide(5, 3));
//		System.out.println(divide(5, 4));

//		for (int i = 0; i < 100; i++) {
//			System.out.println(i + " " + i % 60);
//		}

//		JSONObject jsonObject = new JSONObject();
//		JSONObject jsonObject1 = new JSONObject();
//		jsonObject.putAll(jsonObject1);
        long sessionId = 0x192222222L;
		System.out.println(sessionId >>> 32);
		System.out.println((int)sessionId);


	}


	private static String divide(Integer num1, Integer num2) {
		if ( num1 == null || num2 == null || num1 == 0 || num2 == 0 ) {
			return "0";
		}
//		NumberFormat numberFormat = NumberFormat.getInstance();
//		numberFormat.setMaximumFractionDigits(2);
//		return numberFormat.format(((double) num1) / num2);

		NumberFormat percentInstance = NumberFormat.getPercentInstance();
		percentInstance.setMaximumFractionDigits(2); // 保留小数两位
		return percentInstance.format(((double) num1) / num2);
	}

	public static void testStringGetBytes() {
		try {
			String a = new String("测试一下有啥区别".getBytes("GBK"), "GBK")	;
			String b = new String("测试一下有啥区别".getBytes("GBK"), "ISO8859-1")	;
			System.out.println(a.getBytes().length);
			System.out.println(b.getBytes().length);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static void testRandom() {
		Random random = new Random();
		int[] arr = new int[5];
		for (int i = 0 ; i < 100; i++) {
			int r = random.nextInt(5);
			System.out.println(r);
			arr[r]++;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

	public static void testChangeSubArray() {
    	//改变父, 子会变化, 改变子, 父也会变化
		List<String> parent = new ArrayList<>();
		parent.add("a");
		parent.add("a");
		parent.add("a");
		parent.add("a");
		parent.add("a");

		List<String> child = parent.subList(1,3);
//		child.set(0, "c");
		parent.set(2, "c");
		for (String a : parent) {
			System.out.println(a);
		}
		System.out.println("-------------");
		for (String a : child) {
			System.out.println(a);
		}
	}

	public static void addDouble() {
    	//精度问题
		Double a = 1.19;
		Double b = 0.99;
		System.out.println(Double.sum(a, b));
	}

	public static void testSwitch(int i) {
    	//case的行为是, 从匹配上的那一个开始, 会一直执行下去(不管是否能匹配上) 直到break或者函数结尾.
		//default 会匹配上所有情况
		switch (i) {
			case 1:
				System.out.println(1);
				break;
			case 2:
				System.out.println(2);
			case 3:
				System.out.println(3);
				break;
			case 5:
				System.out.println(5);
			default:
				System.out.println("default");
		}
	}



	//test the breakpoint whether suspend other thread
	public static void debugTest() {

		Runnable a = new Runnable() {
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("this is a runnable");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("this is a runnable");
			}
		};

		Runnable b = new Runnable() {
			public void run() {
				//debug here, look the a thread print
				System.out.println("this is b runnable");
			}
		};

		new Thread(a).start();
		new Thread(b).start();
	}


	public static void floatRoundTest() {
		List<Float> nums = new ArrayList<>();
		nums.add(2050.4999f);
		nums.add(4480.59f);
		nums.add(4480.4f);
		nums.add(4480.5f);
		for (Float num : nums) {
			System.out.println(Math.round(num));
		}
	}

	public static int getRandom(){
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
    public static void threadStatusTest() {
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

    public static void removeElementTest() {
    	List<String> strList = new ArrayList<String>();
    	strList.add("1");
		strList.add("2");
		strList.add("3");
		strList.add("4");
		strList.add("5");

		//下标遍历, 不报错, 但是会少遍历一个
//		for (int i=0; i<strList.size(); i++) {
//			if (strList.get(i).equals("3")) {
//				strList.remove("3");
//			} else {
//				System.out.println(strList.get(i));
//			}
//		}

		//迭代器遍历, 有两种删除方式
//		Iterator<String> iterator = strList.iterator();
//		while (iterator.hasNext()) {
//			String str = iterator.next();
//			if (str.equals("3")) {
////				strList.remove(str);  //报错 ConcurrentModificationException
//				iterator.remove();  //正确写法, 不报错也不会少遍历元素
//			} else {
//				System.out.println(str);
//			}
//		}

		//这种实际上也是迭代器遍历, 抛异常 ConcurrentModificationException
		for (String str : strList) {
			if (str.equals("3")) {
				strList.remove(str);
			} else {
				System.out.println(str);
			}
		}
	}

}


