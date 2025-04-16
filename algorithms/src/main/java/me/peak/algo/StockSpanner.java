package me.peak.algo;

import java.util.ArrayDeque;
import java.util.Deque;

public class StockSpanner {

    Deque<int[]> stack;
    int day;

    public StockSpanner() {
        stack = new ArrayDeque<>();
        day = 1;
    }

    public int next(int price) {
        while (!stack.isEmpty() && stack.peek()[1] <= price) {
            stack.pop();
        }
        int ret;
        if (stack.isEmpty()) {
            ret = day;
        } else {
            ret = day - stack.peek()[0];
        }
        stack.push(new int[]{day, price});
        day++;
        return ret;
    }
}
