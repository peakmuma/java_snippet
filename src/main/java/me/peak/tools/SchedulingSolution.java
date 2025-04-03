package me.peak.tools;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

public class SchedulingSolution {
    private static final int DAYS_IN_MONTH = 32;

    public static class ScheduleResult {
        int[] schedule;
        double score;

        public ScheduleResult(int[] schedule, double score) {
            this.schedule = schedule.clone();
            this.score = score;
        }
    }

    public List<ScheduleResult> generateAllSchedules(int year, int month, int[] schedule, int[] dayCountLimits,
                                                     Map<Integer, int[]> availableWeekDays, Map<Integer, int[]> unwillingDays) {
        List<ScheduleResult> results = new ArrayList<>();

        //将availableDays转换为具体日期
        Map<Integer, Set<Integer>> availableDates = convertToAvailableDates(year, month, availableWeekDays);

        // 计算需要排班的人员可用性
        int[] remainDayCounts = dayCountLimits.clone();
        for (int i = 1; i < schedule.length; i++) {
            if (schedule[i] > 0) {
                remainDayCounts[schedule[i]]--;
            }
        }

        // 生成所有可能的排班
        generateSchedulesRecursive(schedule, 1, remainDayCounts, availableDates, results);

        // 计算所有排班的分数
        for (ScheduleResult result : results) {
            result.score = calculateScore(year, month, result.schedule, availableWeekDays, unwillingDays);
        }

        // 按分数从高到低排序并返回前10个
        results.sort((a, b) -> Double.compare(b.score, a.score));
        return results.size() > 10 ? results.subList(0, 10) : results;
    }

    private Map<Integer, Set<Integer>> convertToAvailableDates(int year, int month, Map<Integer, int[]> availableDays) {
        Map<Integer, Set<Integer>> availableDates = new HashMap<>();
        LocalDate date = LocalDate.of(year, month, 1);
        int daysInMonth = date.lengthOfMonth();

        for (int person : availableDays.keySet()) {
            Set<Integer> dates = new HashSet<>();
            int[] weekdays = availableDays.get(person);
            for (int day = 1; day <= daysInMonth; day++) {
                int weekday = date.withDayOfMonth(day).getDayOfWeek().getValue();
                for (int availableDay : weekdays) {
                    if (availableDay == weekday) {
                        dates.add(day);
                        break;
                    }
                }
            }
            availableDates.put(person, dates);
        }
        return availableDates;
    }

    private void generateSchedulesRecursive(int[] schedule, int day, int[] remainDayCounts,
                                            Map<Integer, Set<Integer>> availableDates, List<ScheduleResult> results) {
        if (day == schedule.length) {
            results.add(new ScheduleResult(schedule, 0));
            return;
        }

        if (schedule[day] != 0 ) {
            generateSchedulesRecursive( schedule, day + 1, remainDayCounts, availableDates, results);
        } else {
            for (int staff = 1;  staff < remainDayCounts.length; staff++) {
                if (remainDayCounts[staff] > 0 && availableDates.containsKey(staff) && availableDates.get(staff).contains(day)) {
                    schedule[day] = staff;
                    remainDayCounts[staff]--;
                    generateSchedulesRecursive( schedule, day + 1, remainDayCounts, availableDates, results);
                    remainDayCounts[staff]++;
                    schedule[day] = 0;
                }
            }
        }
    }

    private double calculateScore(int year, int month, int[] schedule, Map<Integer, int[]> availableWeekDays,
                                  Map<Integer, int[]> unwillingDays) {
        double score = 10000;
        LocalDate date = LocalDate.of(year, month, 1);
        int daysInMonth = date.lengthOfMonth();

        // 检查不愿意值班的情况
        for (int day = 1; day <= daysInMonth; day++) {
            int person = schedule[day];
            if (person > 0 && unwillingDays.containsKey(person)) {
                int[] unwilling = unwillingDays.get(person);
                for (int unwillingDay : unwilling) {
                    if (unwillingDay == day) {
                        score -= 100;
                        break;
                    }
                }
            }
        }

        // 检查连续值班
        for (int day = 1; day < daysInMonth; day++) {
            if (schedule[day] > 0 && schedule[day] == schedule[day + 1]) {
                score -= 100;
            }
        }

        // 计算周分布方差
        Map<Integer, int[]> weekDist = new HashMap<>();
        int[] totalCounts = new int[availableWeekDays.size() + 1];
        for (int day = 1; day <= daysInMonth; day++) {
            int person = schedule[day];
            if (person > 0) {
                totalCounts[person]++;
                int weekday = date.withDayOfMonth(day).getDayOfWeek().getValue();
                weekDist.computeIfAbsent(person, k -> new int[8])[weekday]++;
            }
        }

        double varianceSum = 0;
        for (int person = 1; person <= availableWeekDays.size(); person++) {
            int[] dist = weekDist.getOrDefault(person, new int[8]);
            int[] availableWeekDay = availableWeekDays.get(person);
            double avg = (double) totalCounts[person] / availableWeekDay.length;

            double variance = 0;
            for (int dayOfWeek : availableWeekDay) {
                variance += Math.pow(dist[dayOfWeek] - avg, 2);
            }
            varianceSum += variance / availableWeekDay.length;
        }
        score -= varianceSum * 10;

        return score;
    }

    private static String[] STAFF_NAMES = new String[]{"未排班", "刘", "苑", "黄", "杜", "陈"};

    private static String getChineseDayOfWeek(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case MONDAY: return "星期一";
            case TUESDAY: return "星期二";
            case WEDNESDAY: return "星期三";
            case THURSDAY: return "星期四";
            case FRIDAY: return "星期五";
            case SATURDAY: return "星期六";
            case SUNDAY: return "星期日";
            default: return "";
        }
    }

    private void printMonthlyView(int year, int month, ScheduleResult result) {
        System.out.println("====================================================");
        System.out.printf("Schedule (Score: %.2f):\n", result.score);

        LocalDate baseDate = LocalDate.of(year, month, 1);


        // 第一部分：按员工输出
        System.out.println("-------------------");
        System.out.println("按员工的排班表：");
        System.out.println("员工姓名\t值班日期");
        System.out.println("-------------------");

        // 为每个员工创建日期列表
        List<List<Integer>> staffSchedules = new ArrayList<>();
        for (int i = 0; i < STAFF_NAMES.length; i++) {
            staffSchedules.add(new ArrayList<>());
        }

        // 填充每个员工的排班日期
        for (int day = 1; day < result.schedule.length; day++) {
            if (result.schedule[day] <= 0) {
                continue;
            }
            staffSchedules.get(result.schedule[day]).add(day);
        }

        // 输出每个员工的排班
        for (int i = 1; i < STAFF_NAMES.length; i++) {
            if (!staffSchedules.get(i).isEmpty()) {
                System.out.print(STAFF_NAMES[i] + "\t");
                List<Integer> days = staffSchedules.get(i);
                for (int j = 0; j < days.size(); j++) {
                    int day = days.get(j);
                    LocalDate date = baseDate.plusDays(day - 1);
                    DayOfWeek dayOfWeek = date.getDayOfWeek();
                    System.out.print(day + "号(" + getChineseDayOfWeek(dayOfWeek) + ")");
                    if (j < days.size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            }
        }

        // 第二部分：按日期输出
        System.out.println("-------------------");
        System.out.println("按日期的排班表：");
        System.out.println("日期\t星期\t值班人员");
        System.out.println("-------------------");
        for (int day = 1; day < result.schedule.length; day++) {
            LocalDate currentDate = baseDate.plusDays(day - 1);
            DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
            String staffName = result.schedule[day] <0 ? "休" : STAFF_NAMES[result.schedule[day]];
            System.out.printf("%d\t%s\t%s%n", day, getChineseDayOfWeek(dayOfWeek), staffName);
        }
    }

    public static void main(String[] args) {
        SchedulingSolution solution = new SchedulingSolution();

        int year = 2025;
        int month = 4;

        LocalDate baseDate = LocalDate.of(year, month, 1);
        int daysInMonth = baseDate.lengthOfMonth();

        int[] schedule = new int[daysInMonth + 1];
        schedule[1] = 5; schedule[2] = 2; schedule[3] = 1; schedule[4] = -1; schedule[5] = 1; schedule[6] = 2;

        int[] dayCountLimits = new int[]{0, 6, 6, 5, 6, 6};
        Map<Integer, int[]> availableWeekDays = new HashMap<>();
        availableWeekDays.put(1, new int[]{2, 3, 4, 5, 6});
        availableWeekDays.put(2, new int[]{2, 3, 5, 7});
        availableWeekDays.put(3, new int[]{2, 3, 5});
        availableWeekDays.put(4, new int[]{1, 3, 4});
        availableWeekDays.put(5, new int[]{1, 2, 3, 4, 5});

        Map<Integer, int[]> unwillingDays = new HashMap<>();
        unwillingDays.put(1, new int[]{8, 15, 22, 29});

        long start = System.currentTimeMillis();
        List<ScheduleResult> results = solution.generateAllSchedules(year, month, schedule, dayCountLimits,
                availableWeekDays, unwillingDays);
        long end = System.currentTimeMillis();

        System.out.println("Total time: " + (end - start) + "ms");

        // 输出前10个结果
        for (int i = 0; i < results.size(); i++) {
            solution.printMonthlyView(year, month, results.get(i));
        }

        System.out.println("Total schedules generated: " + results.size());
    }
}
