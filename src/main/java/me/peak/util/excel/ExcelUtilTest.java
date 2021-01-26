package me.peak.util.excel;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ExcelUtilTest {

    public static void main(String[] args) {
//        writeExample();
        readExample();
    }

    private static void writeExample() {
        List<DemoData> dataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            DemoData data =  new DemoData();
            data.setDate(new Date());
            data.setIntData(i);
            data.setString("字符串" + i);
            data.setDoubleData(0.1);
            dataList.add(data);
        }
        ExcelUtil.write("e:/test.xlsx", DemoData.class, dataList);
    }

    public static void readExample() {
        List<DemoData> list = ExcelUtil.read("e:/test.xlsx", DemoData.class);
        for (DemoData data : list) {
            System.out.println(JSONObject.toJSONString(data));
        }
    }

}
