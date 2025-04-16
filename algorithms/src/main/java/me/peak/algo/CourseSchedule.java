package me.peak.algo;

import java.util.*;

/**
 *  https://leetcode-cn.com/problems/course-schedule/
 *  https://leetcode-cn.com/problems/course-schedule-ii/
 */
public class CourseSchedule {

    public static void main(String[] args) {
        int numCourses = 5;
        int[][] prerequisites = {{0,1},{2,3},{1,4}};
        int[] visited = new int[numCourses];
        Arrays.fill(visited, 0);
        Map<Integer, Set<Integer>> dependMap = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            dependMap.putIfAbsent(prerequisite[0], new HashSet<>());
            dependMap.get(prerequisite[0]).add(prerequisite[1]);
        }
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                visit(visited, dependMap, i);
            }
        }
        boolean canFinish = true;
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 1) {
                canFinish = false;
                break;
            }
        }
        System.out.println(canFinish);
    }

    static void visit(int[] visited, Map<Integer, Set<Integer>> dependMap, int index) {
        visited[index] = 1;
        for (Integer dependCourseIndex : dependMap.getOrDefault(index, new HashSet<>())) {
            if (visited[dependCourseIndex] == 1) {
                return;
            }
            visit(visited, dependMap, dependCourseIndex);
        }
        visited[index] = 2;
    }
}
