package me.peak.util;

import com.github.freva.asciitable.*;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;

/**
 * 通用表格打印工具，支持任意类型的对象列表
 */
public class PrintListToTableUtil {

    /**
     * 打印任意类型的列表为表格，自动使用字段名作为表头
     */
    public static <T> String printTable(List<T> list) {
        String table = createTable(list);
        System.out.println(table);
        return table;
    }

    /**
     * 打印任意类型的列表为表格，排除指定的字段
     */
    public static <T> String printTable(List<T> list, List<String> excludeFields) {
        String table = createTable(list, excludeFields);
        System.out.println(table);
        return table;
    }

    /**
     * 打印任意类型的列表为表格，只包含指定的字段
     */
    public static <T> String printTable(List<T> list, String... fieldNames) {
        String table = createTable(list, fieldNames);
        System.out.println(table);
        return table;
    }

    /**
     * 创建任意类型列表的表格，自动使用所有字段
     */
    public static <T> String createTable(List<T> list) {
        if (list == null || list.isEmpty()) {
            return "Empty list";
        }

        T firstItem = list.get(0);

        // 处理不同类型的数据
        if (firstItem instanceof Map) {
            return createMapTable((List<Map<?, ?>>) list);
        } else {
            return createObjectTable(list, null, null);
        }
    }

    /**
     * 创建任意类型列表的表格，排除指定字段
     */
    public static <T> String createTable(List<T> list, List<String> excludeFields) {
        if (list == null || list.isEmpty()) {
            return "Empty list";
        }

        T firstItem = list.get(0);

        // 处理不同类型的数据
        if (firstItem instanceof Map) {
            return createMapTable((List<Map<?, ?>>) list);
        } else {
            return createObjectTable(list, null, excludeFields);
        }
    }

    /**
     * 创建任意类型列表的表格，只包含指定的字段
     */
    public static <T> String createTable(List<T> list, String... fieldNames) {
        if (list == null || list.isEmpty()) {
            return "Empty list";
        }

        T firstItem = list.get(0);

        // 处理不同类型的数据
        if (firstItem instanceof Map) {
            return createMapTable((List<Map<?, ?>>) list, fieldNames);
        } else {
            return createObjectTable(list, fieldNames, null);
        }
    }

    /**
     * 为对象列表创建表格
     */
    private static <T> String createObjectTable(List<T> list, String[] fieldNames, List<String> excludeField) {
        Class<?> clazz = list.get(0).getClass();
        List<ColumnData<T>> columns = new ArrayList<>();
        List<String> fieldsToInclude = fieldNames != null ? Arrays.asList(fieldNames) : null;

        // 基本类型直接处理
        if (isPrimitiveOrWrapper(clazz)) {
            ColumnData<T> column = new Column()
                    .header("Value")
                    .with(Object::toString);
            return AsciiTable.getTable(list, Collections.singletonList(column));
        }

        // 获取所有字段
        for (Field field : getAllFields(clazz)) {
            field.setAccessible(true);
            final String fieldName = field.getName();

            if (excludeField != null && excludeField.contains(fieldName)) {
                continue;
            }
            if (fieldsToInclude == null || fieldsToInclude.contains(fieldName)) {
                columns.add(new Column()
                        .header(fieldName)
                        .maxWidth(50, OverflowBehaviour.NEWLINE)
                        .with(createFieldExtractor(field)));
            }
        }

        return AsciiTable.getTable(list, columns);
    }

    /**
     * 为Map列表创建表格
     */
    private static String createMapTable(List<Map<?, ?>> list, String... keyNames) {
        List<ColumnData<Map<?, ?>>> columns = new ArrayList<>();

        // 如果没有指定键，使用第一个Map的所有键
        if (keyNames == null || keyNames.length == 0) {
            Map<?, ?> firstItem = list.get(0);
            keyNames = firstItem.keySet().stream()
                    .map(Object::toString)
                    .toArray(String[]::new);
        }

        // 为每个键创建一列
        for (String key : keyNames) {
            columns.add(new Column()
                    .header(key)
                    .with(map -> {
                        Object value = map.get(key);
                        return value != null ? value.toString() : "null";
                    }));
        }

        return AsciiTable.getTable(list, columns);
    }

    /**
     * 创建字段值提取器
     */
    private static <T> Function<T, String> createFieldExtractor(Field field) {
        return obj -> {
            try {
                Object value = field.get(obj);

                // 特殊处理集合类型
                if (value instanceof List) {
                    return "[" + String.join(", ", ((List<?>) value).stream()
                            .map(Object::toString)
                            .toArray(String[]::new)) + "]";
                }

                return value != null ? value.toString() : "null";
            } catch (IllegalAccessException e) {
                return "ERROR";
            }
        };
    }

    /**
     * 检查是否是基本类型或其包装类
     */
    private static boolean isPrimitiveOrWrapper(Class<?> clazz) {
        return clazz.isPrimitive() ||
                clazz == Boolean.class ||
                clazz == Character.class ||
                clazz == Byte.class ||
                clazz == Short.class ||
                clazz == Integer.class ||
                clazz == Long.class ||
                clazz == Float.class ||
                clazz == Double.class ||
                clazz == String.class;
    }

    /**
     * 自定义表格样式的高级版本
     */
    public static <T> String createCustomTable(List<T> list, ColumnConfig... configs) {
        List<ColumnData<T>> columns = new ArrayList<>();

        for (ColumnConfig config : configs) {
            ColumnData column = new Column()
                    .header(config.header)
                    .with(config.valueExtractor);

            if (config.maxWidth > 0) {
                column.maxWidth(config.maxWidth);
            }

            if (config.align != null) {
                column.headerAlign(config.align);
                column.dataAlign(config.align);
                column.footerAlign(config.align);
            }

            columns.add(column);
        }

        return AsciiTable.getTable(list, columns);
    }

    /**
     * 列配置类，用于自定义列
     */
    public static class ColumnConfig<T> {
        private String header;
        private Function<T, String> valueExtractor;
        private int maxWidth = -1;
        private HorizontalAlign align = null;

        public ColumnConfig(String header, Function<T, String> valueExtractor) {
            this.header = header;
            this.valueExtractor = valueExtractor;
        }

        public ColumnConfig maxWidth(int width) {
            this.maxWidth = width;
            return this;
        }

        public ColumnConfig align(HorizontalAlign align) {
            this.align = align;
            return this;
        }
    }

    // 使用示例
    public static void main(String[] args) {
        // 创建一些测试数据
        List<Person> people = Arrays.asList(
                new Person(1, "Alice", 28, Arrays.asList("Reading", "Hiking")),
                new Person(2, "Bob", 35, Arrays.asList("Cycling", "Photography")),
                new Person(3, "Charlie", 42, Arrays.asList("Swimming", "Cooking", "Chess"))
        );

        // 示例1: 打印所有字段
        System.out.println("=== 所有字段 ===");
        printTable(people);

        // 示例2: 只打印选定字段
        System.out.println("\n=== 选定字段 ===");
        printTable(people, "name", "age");

        // 示例3: 自定义表格
        System.out.println("\n=== 自定义表格 ===");
        System.out.println(createCustomTable(people,
                new ColumnConfig("编号", p -> String.valueOf(((Person)p).id)).align(HorizontalAlign.RIGHT),
                new ColumnConfig("姓名", p -> ((Person)p).name).maxWidth(10),
                new ColumnConfig("年龄", p -> String.valueOf(((Person)p).age)),
                new ColumnConfig("爱好", p -> String.join(", ", ((Person)p).hobbies)).maxWidth(20)
        ));
    }

    static class Person {
        private int id;
        private String name;
        private int age;
        private List<String> hobbies;

        public Person(int id, String name, int age, List<String> hobbies) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.hobbies = hobbies;
        }
    }

    public static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<>();

        // 先获取父类的所有字段
        if (clazz.getSuperclass() != null && clazz.getSuperclass() != Object.class) {
            fieldList.addAll(getAllFields(clazz.getSuperclass())); // 递归父类
        }

        // 获取当前类的所有字段
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            fieldList.add(field);
        }

        return fieldList;
    }

}