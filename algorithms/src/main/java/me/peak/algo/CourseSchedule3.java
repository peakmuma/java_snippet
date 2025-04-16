package me.peak.algo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.cn/problems/course-schedule-iii/
 */
public class CourseSchedule3 {

    public static void main(String[] args) {
        int[][] courses = new int[][]{{5,5},{4,6},{2,6}};
        scheduleCourse(courses);
    }

    public static int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, Comparator.comparingInt(course -> course[1]));
        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b) -> (b-a));
        int maxCourseCount = 0;
        int maxDuration = 0;
        for (int i = 0; i < courses.length; i++) {
            if (maxDuration + courses[i][0] <= courses[i][1]) {
                maxCourseCount++;
                maxDuration += courses[i][0];
                queue.add(courses[i][0]);
            } else if (!queue.isEmpty() && maxDuration + courses[i][0] - queue.peek() <= courses[i][1] && courses[i][0] < queue.peek()){
                maxDuration += (courses[i][0] - queue.poll());
                queue.add(courses[i][0]);
            }
        }
        return maxCourseCount;
    }
}
