package me.peak.tools;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SchedulingSolution {

    public static class ScheduleResult {
        int[] schedule;
        double score;
        double[] dayOfWeekVariance;
        double[] intervalVariance;

        public ScheduleResult(int[] schedule) {
            this.schedule = schedule.clone();
        }
    }

    public List<ScheduleResult> generateAllSchedules(int year, int month, int[] schedule, int[] dayCountLimits,
                                                     Map<Integer, int[]> availableWeekDays, Map<Integer, int[]> unwillingDays) {
        // 将results声明成小顶堆，按照score字段排序
        PriorityQueue<ScheduleResult> resultQueue = new PriorityQueue<>(Comparator.comparingDouble(a -> a.score));

        //将availableDays转换为具体日期
        Map<Integer, Set<Integer>> availableDates = convertToAvailableDates(year, month, availableWeekDays);

        // 计算需要排班的人员剩余可使用天数
        int[] remainDayCounts = dayCountLimits.clone();
        for (int i = 1; i < schedule.length; i++) {
            if (schedule[i] > 0) {
                remainDayCounts[schedule[i]]--;
            }
        }

        // 生成所有可能的排班
        generateSchedulesRecursive(schedule, 1, remainDayCounts, availableDates, year, month, availableWeekDays, unwillingDays, resultQueue);

        // 按分数从高到低排序并返回
        List<ScheduleResult> results = new ArrayList<>(resultQueue);
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

    private void generateSchedulesRecursive(int[] schedule, int day, int[] remainDayCounts, Map<Integer, Set<Integer>> availableDates,
                                            int year, int month, Map<Integer, int[]> availableWeekDays, Map<Integer, int[]> unwillingDays,
                                            PriorityQueue<ScheduleResult> results) {
        if (day == schedule.length) {
            ScheduleResult result = new ScheduleResult(schedule);
            calculateAndSetScore(result, year, month, availableWeekDays, unwillingDays);
            if (results.size() < 10) {
                results.add(result);
            } else if (result.score > results.peek().score) {
                results.poll();
                results.add(result);
            }
            return;
        }

        if (schedule[day] != 0 ) {
            generateSchedulesRecursive( schedule, day + 1, remainDayCounts, availableDates, year, month, availableWeekDays, unwillingDays, results);
        } else {
            for (int staff = 1;  staff < remainDayCounts.length; staff++) {
                if (remainDayCounts[staff] > 0 &&
                        availableDates.containsKey(staff) && availableDates.get(staff).contains(day)
                        && (day == 1 || schedule[day - 1] != staff)
                ) {
                    schedule[day] = staff;
                    remainDayCounts[staff]--;
                    generateSchedulesRecursive( schedule, day + 1, remainDayCounts, availableDates, year, month, availableWeekDays, unwillingDays, results);
                    remainDayCounts[staff]++;
                    schedule[day] = 0;
                }
            }
        }
    }

    private void calculateAndSetScore(ScheduleResult result, int year, int month, Map<Integer, int[]> availableWeekDays,
                                      Map<Integer, int[]> unwillingDays) {
        double score = 10000;
        LocalDate date = LocalDate.of(year, month, 1);
        int daysInMonth = date.lengthOfMonth();

        // 检查不愿意值班的情况
        for (int day = 1; day <= daysInMonth; day++) {
            int person = result.schedule[day];
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

        //计算分布在周几的方差 和 日期间隔的方差
        Map<Integer, int[]> dayOfWeekDistMap = new HashMap<>();
        Map<Integer, List<Integer>> intervalsMap = new HashMap<>();
        // Map存储每个员工的上一次值班日期
        Map<Integer, Integer> lastDutyDay = new HashMap<>();
        int[] totalCounts = new int[availableWeekDays.size() + 1];
        for (int day = 13; day <= daysInMonth; day++) {
            int person = result.schedule[day];
            if (person > 0) {
                totalCounts[person]++;
                date = date.withDayOfMonth(day);
                int weekday = date.getDayOfWeek().getValue();
                dayOfWeekDistMap.computeIfAbsent(person, k -> new int[8])[weekday]++;

                // 如果该员工之前有值班记录，计算间隔
                if (lastDutyDay.containsKey(person)) {
                    int interval = day - lastDutyDay.get(person);
                    intervalsMap.computeIfAbsent(person, k -> new ArrayList<>()).add(interval);
                }
                // 更新该员工的最近值班日期
                lastDutyDay.put(person, day);            }
        }

        int personCount = availableWeekDays.size();
        result.dayOfWeekVariance = new double[personCount + 1];
        result.intervalVariance = new double[personCount + 1];
        for (int person = 1; person <= personCount; person++) {
            // 计算周几的方差
            int[] dayOfWeekDist = dayOfWeekDistMap.getOrDefault(person, new int[8]);
            int[] availableWeekDay = availableWeekDays.get(person);
            double avgDayCountOfWeek = (double) totalCounts[person] / availableWeekDay.length;
            double variance = 0;
            for (int dayOfWeek : availableWeekDay) {
                variance += Math.pow(dayOfWeekDist[dayOfWeek] - avgDayCountOfWeek, 2);
            }
            double varianceSum = variance / availableWeekDay.length;
            result.dayOfWeekVariance[person] = varianceSum;
            //计算完后减去分数
            score -= varianceSum * 20;
            //日期间隔的方差
            List<Integer> intervals = intervalsMap.getOrDefault(person, Collections.emptyList());
            if (intervals.isEmpty()) {
                continue;
            }
            double avgInterval = (double) daysInMonth / totalCounts[person];
            variance = 0;
            for (int interval : intervals) {
                variance += Math.pow(interval - avgInterval, 2);
            }
            varianceSum = variance / intervals.size();
            result.intervalVariance[person] = varianceSum;
            //计算完后减去分数
            score -= varianceSum * 20;
        }
        result.score = score;
    }

    private static String[] STAFF_NAMES = new String[]{"未排班", "刘", "苑", "黄", "杜", "陈"};

    private static String getChineseDayOfWeek(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case MONDAY: return "一";
            case TUESDAY: return "二";
            case WEDNESDAY: return "三";
            case THURSDAY: return "四";
            case FRIDAY: return "五";
            case SATURDAY: return "六";
            case SUNDAY: return "日";
            default: return "";
        }
    }

//    String status = result.schedule[statusIndex] > 0 ? STAFF_NAMES[result.schedule[statusIndex]] : "假   ";

    private void printMonthly(int year, int month, ScheduleResult result) {
        //按日期输出
        System.out.println("-------------------");
        System.out.println("按日期的排班表：");
        // 固定前置距离
        final String PREFIX = "               ";

        // 获取当月第一天
        LocalDate firstDay = LocalDate.of(year, month, 1);

        // 打印表头
        System.out.println(PREFIX + "一   二   三   四   五   六   日");

        // 初始化变量
        LocalDate currentDate = firstDay;
        LocalDate weekStart = firstDay;
        StringBuilder weekStatus = new StringBuilder();
        int day = 1;

        // 中文日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M.dd");
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M.d", Locale.CHINA);

        // 如果1号不是周一，填充空格
        int dayOfWeek = firstDay.getDayOfWeek().getValue(); // 周一=1, 周日=7
        for (int i = 1; i < dayOfWeek; i++) {
//            weekStatus.append("\u3000\u3000\u3000"); // 空日补字符
            weekStatus.append("   \u3000"); // 空日补字符
        }

        // 遍历当月每一天
        while (currentDate.getMonthValue() == month) {
            // 获取当前天的状态
            String status = result.schedule[day] >= 0 ? STAFF_NAMES[result.schedule[day]] : "休  ";
            weekStatus.append(String.format("%-3s ", status)); // 状态占3字符+1空格
            day++;

            // 如果是周日或月末，打印当前周
            if (currentDate.getDayOfWeek().getValue() == 7 || currentDate.plusDays(1).getMonthValue() != month) {
                // 格式化周范围
                String weekRange = String.format("%s ~ %s",
                        weekStart.format(formatter),
                        currentDate.format(formatter));

                // 打印周范围和状态
                System.out.printf("%-14s %s%n", weekRange, weekStatus);

                // 重置下一周
                weekStatus = new StringBuilder();
                weekStart = currentDate.plusDays(1);
            }

            currentDate = currentDate.plusDays(1);
        }
    }

    private void printScore(int year, int month, ScheduleResult result) {
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
                System.out.printf("   周几方差是: %.2f", result.dayOfWeekVariance[i]);
                System.out.printf("   值班日间隔方差是: %.2f", result.intervalVariance[i]);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        SchedulingSolution solution = new SchedulingSolution();

        int year = 2025;
        int month = 4;

        LocalDate baseDate = LocalDate.of(year, month, 1);
        int daysInMonth = baseDate.lengthOfMonth();

        int[] schedule = new int[daysInMonth + 1];
        schedule[1] = 5; schedule[2] = 2; schedule[3] = 1; schedule[4] = -1; schedule[5] = 1; schedule[6] = 2;
        schedule[7] = 5; schedule[8] = 2; schedule[9] = 4; schedule[10] = 5; schedule[11] = 1; schedule[12] = 1; schedule[13] = 2;
        schedule[27] = 3;

        int[] dayCountLimits = new int[]{0, 7, 7, 1, 7, 7};
//        int[] dayCountLimits = new int[]{0, 7, 8, 2, 7, 7};

        Map<Integer, int[]> availableWeekDays = new HashMap<>();
        availableWeekDays.put(1, new int[]{2, 3, 4, 5, 6});
        availableWeekDays.put(2, new int[]{2, 3, 5, 7});
        availableWeekDays.put(3, new int[]{6, 7});
        availableWeekDays.put(4, new int[]{1, 3, 4});
        availableWeekDays.put(5, new int[]{1, 2, 3, 4, 5});

        Map<Integer, int[]> unwillingDays = new HashMap<>();
        unwillingDays.put(1, new int[]{8, 15, 22, 29});
//        unwillingDays.put(1, new int[]{6, 13, 20, 27});

        long start = System.currentTimeMillis();
        List<ScheduleResult> results = solution.generateAllSchedules(year, month, schedule, dayCountLimits,
                availableWeekDays, unwillingDays);
        long end = System.currentTimeMillis();

        System.out.println("Total time: " + (end - start) + "ms");
        System.out.println("Total schedules generated: " + results.size());

        // 输出前10个结果
        for (int i = 0; i < results.size(); i++) {
            solution.printScore(year, month, results.get(i));
            solution.printMonthly(year, month, results.get(i));
        }

        long maxMemory = runtime.maxMemory(); // 最大内存
        long totalMemory = runtime.totalMemory(); // 已分配内存
        long freeMemory = runtime.freeMemory(); // 空闲内存
        System.out.println("Max: " + maxMemory / 1024 / 1024 + "MB");
        System.out.println("Total: " + totalMemory / 1024 / 1024 + "MB");
        System.out.println("Free: " + freeMemory / 1024 / 1024 + "MB");
    }
}
