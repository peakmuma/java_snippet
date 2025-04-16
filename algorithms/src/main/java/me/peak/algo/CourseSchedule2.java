package me.peak.algo;

import java.util.*;

/**
 * https://leetcode.cn/problems/course-schedule-ii/
 */
public class CourseSchedule2 {

    public static void main(String[] args) {

        int[] res = findOrder(2, new int[][]{{1,0}, {0,1}});
        System.out.println(res.length);
    }

    static class DAG {
        int value;
        List<DAG> parent; //父节点
        int level = 0; //当前层级，取值是父节点最大层级+1
        DAG(int value) {
            this.value = value;
            parent = new ArrayList<>();
        }
    }

    static Map<Integer, DAG> nodeMap;  //key是任务编号，value是DAG节点
    static TreeMap<Integer, List<Integer>> levelMap;  //key是任务对应的层级，value是任务编号

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        //初始化每一个节点
        nodeMap = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            nodeMap.put(i, new DAG(i));
        }

        //设置每一个DAG的parent
        for (int[] ints : prerequisites) {
            nodeMap.get(ints[0]).parent.add(nodeMap.get(ints[1]));
        }
        //递归计算level
        for (int i = 0; i < numCourses; i++) {
            if (setLevel(nodeMap.get(i), numCourses, new int[]{0}) < 0 ) {
                return new int[]{};
            }
        }
        //放到levelMap中
        levelMap = new TreeMap<>();
        for (int i = 0; i < numCourses; i++) {
            Integer level = nodeMap.get(i).level;
            if (!levelMap.containsKey(level)) {
                levelMap.put(level, new ArrayList<>());
            }
            levelMap.get(level).add(i);
        }
        int[] res = new int[numCourses];
        int i = 0;
        for (Map.Entry<Integer, List<Integer>> entry : levelMap.entrySet()) {
            for (int j = 0; j < entry.getValue().size(); j++) {
                res[i++] = entry.getValue().get(j);
            }
        }
        return res;
    }

    static int setLevel(DAG dag, int numCourses, int[] execCount) {
        if (dag.level > 0) {
            return dag.level;
        }
        if (execCount[0] > numCourses) {
            dag.level = -1;
            return -1;
        }
        int maxLevel = 0;
        execCount[0] = execCount[0]+1;
        for (DAG parent: dag.parent) {
            if (parent.level == 0) {
                setLevel(parent, numCourses, execCount);
            }
            if (parent.level == -1) {
                dag.level = -1;
                return -1;
            }
            if (parent.level > maxLevel) {
                maxLevel = parent.level;
            }
        }
        dag.level = maxLevel + 1;
        return dag.level;
    }
}
