package me.peak.test;

public class MemoryReorderTest {
    static int x = 0, y = 0;
    static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10000000; i++) {
            reorderTest();
            if (i % 10000 == 0) {
                System.out.println("exec " + i + " times");
            }
        }
    }

    static void reorderTest() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                a = 1; //操作1
                x = b; //操作2
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                b = 1; //操作3
                y = a; //操作4
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        if (x == 0 && y == 0) {
            System.out.println("(" + x + "," + y + ")");
        }
        x = 0;
        y = 0;
        a = 0;
        b = 0;
    }
}
