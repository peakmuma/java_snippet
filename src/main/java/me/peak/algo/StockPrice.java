package me.peak.algo;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * https://leetcode.cn/problems/stock-price-fluctuation/description/
 */
public class StockPrice {

    int maxTimeStamp;
    HashMap<Integer, Integer> timePriceMap;
    TreeMap<Integer, Integer> priceCountMap;

    public StockPrice() {
        maxTimeStamp = 0;
        timePriceMap = new HashMap<>();
        priceCountMap = new TreeMap<>();
    }

    public void update(int timestamp, int price) {
        maxTimeStamp = Math.max(timestamp, maxTimeStamp);
        Integer prevPrice = timePriceMap.put(timestamp, price);
        if (prevPrice != null) {
            priceCountMap.put(prevPrice, priceCountMap.get(prevPrice) - 1);
            if (priceCountMap.get(prevPrice) == 0) {
                priceCountMap.remove(prevPrice);
            }
        }
        priceCountMap.put(price, priceCountMap.getOrDefault(price, 0) + 1);

    }

    public int current() {
        return timePriceMap.get(maxTimeStamp);
    }

    public int maximum() {
        return priceCountMap.lastKey();
    }

    public int minimum() {
        return priceCountMap.firstKey();
    }
}
