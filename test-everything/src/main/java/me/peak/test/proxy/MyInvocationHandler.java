package me.peak.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) {
		System.out.println("-----start-------");
		System.out.println("-----end---------");
		return null;
	}
}
