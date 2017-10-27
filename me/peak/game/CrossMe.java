package me.peak.game;

import java.util.ArrayList;
import java.util.List;

public class CrossMe {
	public static void main(String[] args) {
		int res[] = new int[]{1,0,0,1,0,0,1,1,1,0,0};
		int condition[] = new int[]{1,1,3};
		System.out.println(checkArray(res, condition));
	}

	void initArray(int res[], List<Integer> conditionList){
		int index = 0;
		for (int condition : conditionList) {
			for (int i = 0; i < condition; i++) {
				res[index++] = 1;
			}
			index++;
		}
	}

	static boolean getNextArray(int res[]){
		int lastIndex = res.length - 1;
		while (lastIndex > 0 && res[lastIndex] == 0){
			lastIndex--;
		}
		if (lastIndex < res.length - 1){

		}
		return true;

	}

	static boolean checkArray(int res[], int conditionArray[]){
		int condition = 0;
		List<Integer> calcConditionList = new ArrayList<>();
		for (int i = 0; i < res.length; i++) {
			if (res[i] == 1){
				condition++;
			} else {
				if (condition == 0){
					continue;
				} else {
					calcConditionList.add(condition);
					condition = 0;
				}
			}
		}
		if (calcConditionList.size() != conditionArray.length){
			return false;
		}
		for (int i = 0; i < conditionArray.length; i++){
			if (conditionArray[i] != calcConditionList.get(i)){
				return false;
			}
		}
		return true;
	}
}
