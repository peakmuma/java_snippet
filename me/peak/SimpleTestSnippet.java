package me.peak;

import java.util.Objects;

/**
 * Created by peak on 2016/11/26.
 */
public class SimpleTestSnippet {
    public static void main(String[] args){
        classTypeTest();
        stringInternTest();
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
}
