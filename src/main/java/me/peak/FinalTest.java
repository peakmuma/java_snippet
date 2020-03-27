package me.peak;

public class FinalTest {
	/*
	 * 关于final类型变量的初始化:
	 * 	非static类型：可以再声明中或在构造函数中初始化，只能初始化一次
	 * 	static类型：在声明中或者在static块中初始化
	 */
	public final FinalTest finaltest=null;
	
	public FinalTest(){
		
	}

	public static void main(String[] args){
		
		FinalTest finaltest1;
		finaltest1 = new FinalTest();
	}
}
