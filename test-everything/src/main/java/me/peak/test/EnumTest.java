package me.peak.test;

public enum EnumTest {
    abc("1"),
    bcd("2");

    private String name;

    EnumTest(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        System.out.println(EnumTest.abc.toString());
    }
}
