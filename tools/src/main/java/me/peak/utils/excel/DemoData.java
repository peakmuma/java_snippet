package me.peak.utils.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

import java.util.Date;

public class DemoData {

    @ExcelProperty("字符串标题")
    private String string;

//    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")   导出时候转换浮点数格式为百分比
    @ExcelProperty("日期标题")
    private Date date;

    @ExcelIgnore
    private String ignore;

    @ExcelProperty("整数标题")
    private int intData;

//    @NumberFormat("#.##%")  导出时候转换浮点数格式为百分比
    @ExcelProperty("浮点数标题")
    private double doubleData;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIntData() {
        return intData;
    }

    public void setIntData(int intData) {
        this.intData = intData;
    }

    public String getIgnore() {
        return ignore;
    }

    public void setIgnore(String ignore) {
        this.ignore = ignore;
    }

    public double getDoubleData() {
        return doubleData;
    }

    public void setDoubleData(double doubleData) {
        this.doubleData = doubleData;
    }
}
