package me.peak.proxy;



import java.lang.reflect.Proxy;

public class TestProxy {
	public static void main(String[] args) {
		MyInvocationHandler handler = new MyInvocationHandler();
		Animal animal = (Animal) Proxy.newProxyInstance(TestProxy.class.getClassLoader(), new Class[]{Animal.class}, handler);
		animal.name();
	}
}
