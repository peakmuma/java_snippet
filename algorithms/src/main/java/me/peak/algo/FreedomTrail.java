package me.peak.algo;

public class FreedomTrail {

    public static void main(String[] args) {
//        System.out.println(findRotateSteps("ababcab", "acba"));
//        System.out.println(findRotateSteps("ababcab", "acbaa"));
//        System.out.println(findRotateSteps("ababcab", "acbaac"));
//        System.out.println(findRotateSteps("ababcab", "acbaacb"));
        System.out.println(findRotateSteps("ababcab", "acbaacba"));
    }

    public static int findRotateSteps(String ring, String key) {
        int[][] res =  new int[ring.length()][key.length()];
        return findMinSteps(ring, key, 0, 0,  res);
    }

    static int findMinSteps(String ring, String key, int ringIndex, int keyIndex, int[][] res) {
        if (keyIndex == key.length()) {
            return 0;
        }
        if (res[ringIndex][keyIndex] != 0) {
            return res[ringIndex][keyIndex];
        }
        if (ring.charAt(ringIndex) == key.charAt(keyIndex)) {
            int step = findMinSteps(ring, key, ringIndex, keyIndex + 1, res) + 1;
            res[ringIndex][keyIndex] =  step;
            return step;
        }

        int forwardRightIndex = ringIndex;
        int rightStep = 0;
        while (ring.charAt(forwardRightIndex) != key.charAt(keyIndex)) {
            forwardRightIndex++;
            rightStep++;
            if (forwardRightIndex == ring.length()) {
                forwardRightIndex = 0;
            }
        }

        int forwardLeftIndex = ringIndex;
        int leftStep = 0;
        while (ring.charAt(forwardLeftIndex) != key.charAt(keyIndex)) {
            forwardLeftIndex--;
            leftStep++;
            if (forwardLeftIndex == -1) {
                forwardLeftIndex = ring.length() - 1;
            }
        }

        int rightMinStep = findMinSteps(ring, key, forwardRightIndex, keyIndex + 1, res);
        int leftMinStep = findMinSteps(ring, key, forwardLeftIndex, keyIndex + 1, res);
        int step = Math.min( rightStep + 1 + rightMinStep, leftStep + 1 + leftMinStep);
        res[ringIndex][keyIndex] = step;
        return step;
    }
}
