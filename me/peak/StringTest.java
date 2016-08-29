package me.peak;

public class StringTest {
	public static void main(String[] args){
		String s1="123";
		String s2=new String("123");
		System.out.println(s1==s2);//false
		s2=s2.intern();
		System.out.println(s1==s2);//true
	}
}
