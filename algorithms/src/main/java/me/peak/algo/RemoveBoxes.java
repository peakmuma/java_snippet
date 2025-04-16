package me.peak.algo;

import java.util.*;

public class RemoveBoxes {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,2,1,1,1,2,1,1,2,1,2,1,1,2,2,1,1,2,2,1,1,1,2,2,2,2,1,2,1,1,2,2,1,2,1,2,2,2,2,2,1,2,1,2,2,1,1,1,2,2,1,2,1,2,2,1,2,1,1,1,2,2,2,2,2,1,2,2,2,2,2,1,1,1,1,1,2,2,2,2,2,1,1,1,1,2,2,1,1,1,1,1,1,1,2,1,2,2,1};
        System.out.println(removeBoxes(nums));
    }

    public static int removeBoxes(int[] boxes) {
        return getMaxBox(boxes, new HashMap<>());
    }

    static int getMaxBox(int[] boxes, Map<String, Integer> resMap) {
        String key = Arrays.toString(boxes);
        if (resMap.containsKey(key)) {
            return resMap.get(key);
        }
        int i = 0;
        int maxRes = 0;
        while (i < boxes.length) {
            while (i < boxes.length && boxes[i] == 0) i++;
            if (i == boxes.length) {
                resMap.put(key, maxRes);
                return maxRes;
            }
            int startIndex = i;
            int colour = boxes[startIndex];
            List<Integer> sameContinueColourIndex = new ArrayList<>();
            while (i < boxes.length) {
                if (boxes[i] == colour) {
                    sameContinueColourIndex.add(i);
                    boxes[i] = 0;
                    i++;
                } else if (boxes[i] == 0) {
                    i++;
                } else {
                    break;
                }
            }
            int length = sameContinueColourIndex.size();
            int res = length * length + getMaxBox(boxes, resMap);
            maxRes = Math.max(res, maxRes);
            for (Integer continueColourIndex : sameContinueColourIndex) {
                boxes[continueColourIndex] = colour;
            }
        }
        resMap.put(key, maxRes);
        return maxRes;
    }
}
