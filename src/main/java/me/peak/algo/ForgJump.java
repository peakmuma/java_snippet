package me.peak.algo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/frog-jump/
 */
public class ForgJump {

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,3,5,6,8,12,17};
        System.out.println(canCross(nums));
        nums = new int[]{0,1,2,3,4,8,9,11};
        System.out.println(canCross(nums));
        nums = new int[]{0,2};
        System.out.println(canCross(nums));
        nums = new int[]{0,1};
        System.out.println(canCross(nums));
    }

    public static boolean canCross(int[] stones) {
        List<Set> list = new ArrayList<>(stones.length);//第n个节点，下一次跳i个单位能到达终点; 最终判断list.get(0).contains(1)
        for (int i = 0; i < stones.length - 1; i++) {
            Set<Integer> set = new HashSet<>();
            set.add(stones[stones.length - 1] - stones[i]);
            list.add(set);
        }
        for (int i = stones.length - 2; i >= 0; i--) {
            Set<Integer> set = list.get(i);
            for (int j = i - 1; j >= 0; j--) {
                int diff = stones[i] - stones[j];
                if (diff > stones[j] + 1) {
                    break;
                }
                if (set.contains(diff) || set.contains(diff - 1) || set.contains(diff + 1)) {
                    list.get(j).add(diff);
                }
            }
        }
        return list.get(0).contains(1);
    }

}
