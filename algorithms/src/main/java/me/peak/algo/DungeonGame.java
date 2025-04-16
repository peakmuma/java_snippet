package me.peak.algo;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/dungeon-game/
 */
public class DungeonGame {
    public static void main(String[] args) {

    }

    public static int calculateMinimumHP(int[][] dungeon) {
        int[] health = new int[dungeon[0].length];
        Arrays.fill(health, 1);
        int minHealth = 1;
        for (int i = dungeon.length - 1; i >= 0; i--) {
            for (int j = dungeon[0].length - 1; j >= 0; j--) {
                if (j == dungeon[0].length - 1) {
                    minHealth = health[j] - dungeon[i][j];
                } else if (i == dungeon.length - 1){
                    minHealth = health[j+1] - dungeon[i][j];
                } else {
                    minHealth = Math.min(health[j+1], health[j]) - dungeon[i][j];
                }
                health[j] = minHealth <= 0 ? 1 : minHealth;
            }
        }
        return health[0];
    }
}
