package me.peak.test;

import java.util.*;
import java.util.concurrent.*;

public class MyTest {
    private static ThreadPoolExecutor executor;

     static{
        int corePoolSize = 50;
        int maximumPoolSize = 100;
        int keepAliveTime = 10;
        BlockingQueue<Runnable> mysqlWorkQueue = new LinkedBlockingQueue<>(100);
        executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MINUTES, mysqlWorkQueue);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Boolean partDataFail = false;
        List<Future> futureList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            FetchDataTask fetchDataTask = new FetchDataTask(partDataFail);
            //任务提交到线程池执行，返回future对象
            Future fetchDataFuture = executor.submit(fetchDataTask);
            futureList.add(fetchDataFuture);
        }
        for (Future future : futureList) {
            future.get();
        }
        System.out.println(partDataFail);
        executor.shutdown();

    }


    public static class FetchDataTask implements Callable {

        Boolean partDataFail;

        public FetchDataTask(Boolean partDataFail) {
            this.partDataFail = partDataFail;
        }

        @Override
        public List call() throws Exception {
            Random random = new Random();
            int num = random.nextInt();
            if (num % 2 == 0) {
                System.out.println("this set true");
                partDataFail = true;
            } else {
                System.out.println("this not set true");
            }
            Thread.sleep(2000);
            return new ArrayList();
        }

    }

//    public static class FetchDataPartFail {
//        private boolean flag;
//        private boolean getFlag() {
//            return this.flag;
//        }
//        private void setFlag (boolean flag) {
//            this.flag = flag;
//        }
//    }

}
