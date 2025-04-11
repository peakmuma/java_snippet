package me.peak.test;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamTest {

    public static void main(String[] args) {
        People p1 = People.builder().name("1").age(1).build();
        People p2 = People.builder().name("2").age(1).build();
        People p3 = People.builder().name("3").age(2).build();
        List<People> peopleList = Lists.newArrayList(p1, p2, p3);
        Map<Integer, List<String>> ageNameMap = peopleList.stream().collect(Collectors.groupingBy(
                People::getAge, Collectors.mapping(People::getName, Collectors.toList())));
        System.out.println("end");
    }
}
