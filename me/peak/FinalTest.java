package me.peak;

public class FinalTest {
	/*
	 * ����final���ͱ����ĳ�ʼ��
	 * ��static���ͣ������������л��ڹ��캯���г�ʼ����ֻ�ܳ�ʼ��һ��
	 *  static���ͣ��������л�����static���г�ʼ��
	 */
	public final FinalTest finaltest=null;
	
	public FinalTest(){
		
	}

	public static void main(String[] args){
		
		FinalTest finaltest1;
		finaltest1 = new FinalTest();
	}
}
