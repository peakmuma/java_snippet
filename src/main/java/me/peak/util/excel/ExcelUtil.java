package me.peak.util.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 这个类是基于阿里开源的 EasyExcel 做了一点小小的封装, 使解析excel方法更简单了一点, 顺便把生成excel的方法也加进来了.
 * 在测试类 ExcelUtilTest 给出了简单的使用示例
 * EasyExcel 的官网是 https://alibaba-easyexcel.github.io/index.html 这里面有更多高级的用法
 * 如果大家在使用这个类的时候还有更多的需求, 可以在这里面增加方法或者直接使用 EasyExcel
 */

public class ExcelUtil {

    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    public static <T> List<T> read (InputStream inputStream, Class head) {
        ExcelReadListener<T> readListener = new ExcelReadListener<>();
        EasyExcel.read(inputStream, head, readListener).sheet().doRead();
        return readListener.getDataList();
    }

    public static <T> List<T> read (String path, Class head) {
        ExcelReadListener<T> readListener = new ExcelReadListener<>();
        EasyExcel.read(path, head, readListener).sheet().doRead();
        return readListener.getDataList();
    }

    public static <T> void write(OutputStream outputStream, Class<T> head, List<? extends T> data) {
        EasyExcel.write(outputStream, head).sheet().doWrite(data);
    }

    public static <T> void write(String path, Class<T> head, List <? extends T> data) {
        EasyExcel.write(path, head).sheet().doWrite(data);
    }

    private static class ExcelReadListener<T> extends AnalysisEventListener<T> {

        List<T> dataList = new ArrayList<>();

        @Override
        public void invoke(T data, AnalysisContext context) {
            dataList.add(data);
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            logger.info("analysis excel data size {}", dataList.size());
        }

        List<T> getDataList() {
            return dataList;
        }
    }

}
