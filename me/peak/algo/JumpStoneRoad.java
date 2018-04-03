package me.peak.algo;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目链接：https://www.nowcoder.com/questionTerminal/4284c8f466814870bae7799a07d49ec8
 */
public class JumpStoneRoad {

	public static void main(String[] args){
		int res = getStepNum(4, 24, 0);
		System.out.println(res);
	}

	public static int getStepNum(int cur, int target, int step) {
		if (cur == target) {
			return step;
		}
		if (cur > target) {
			return -1;
		}
		List<Integer> yueshuList = getYueShu(cur);
		for (int i : yueshuList) {
			int res = getStepNum(cur + i, target, step + 1);
			if (res > 0) {
				return res;
			}
		}
		return -1;

	}

	public static List<Integer> getYueShu(int num) {
		List<Integer> yueshuList = new ArrayList<>();
		for (int i = num/2 ; i>=2 ; i--) {
			if (num % i == 0) {
				yueshuList.add(i);
			}
		}
		return yueshuList;
	}
}
