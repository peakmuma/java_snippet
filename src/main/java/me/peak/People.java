package me.peak;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class People {

    boolean testValue;

    boolean testValue1 = true;

    String name;

    int age;

}
