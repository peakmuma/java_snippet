package me.peak;

import java.util.Objects;

/**
 * Created by peak on 2016/11/26.
 */
public class SimpleTestSnippet {
    public static void main(String[] args){
//        classTypeTest();
//        stringInternTest();
        threadStatusTest();
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
