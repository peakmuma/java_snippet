package me.peak.classType;

public class ClassTypeTest {

	/*
	 * getClass方法返回的是实际类型
	 */
	public static void main(String[] args) {
		A a = new B();
		System.out.println(a.getClass());//B
	}
}
