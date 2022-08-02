package me.peak;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class Test {

    private static final int TEST_DATA_SIZE = 10000000;

    public static void main(String[] args) {


        System.out.println("hw");
//        test();
        new LinkedBlockingQueue<>(0);
    }

    private static int index=0;

    private static void test() {
        long startTime=System.currentTimeMillis();
        List<String> testData = load();
        long loadFinish=System.currentTimeMillis();
        Random random=new Random();
        String findStr=String.valueOf(random.nextInt(TEST_DATA_SIZE/100));
        List<Integer> result=new LinkedList<>();
        testData.stream().forEach(s -> {
            if(s.indexOf(findStr)>-1){
                result.add(index);
            }
            index++;
        });
        long findFinish=System.currentTimeMillis();
        System.out.println(String.format("%s,%s,%s",startTime,loadFinish-startTime,findFinish-loadFinish));
        System.out.println(String.format("%s",result.size()));
        result.stream().forEach(r->{
            System.out.println("r:"+r);
            System.out.println("rv:"+testData.get(r));
        });

    }

    private static List<String> load() {
        List<String> testList = new LinkedList<>();
        Random random=new Random();
        for (int i = 0; i < TEST_DATA_SIZE; i++) {
            testList.add("###"+String.valueOf(random.nextInt(TEST_DATA_SIZE/100))+"$$$");
        }
        return testList;
    }
}