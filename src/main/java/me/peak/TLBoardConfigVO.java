package me.peak;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TLBoardConfigVO {
    /**
     * 与前端约定，"ALL"表示标记一整行
     */
    public static final String MARK_WHOLE_LINE_KEY = "ALL";

    //必填
    @ExcelProperty("指标code")
    private String metricCode;

    //选填，默认值是 TLModuleCode.table.getName()
    @ExcelProperty("模块名称")
    private String moduleCodeName;

    //选填，默认值是空字符串
    @ExcelProperty("TAB主题")
    private String tabCode;

    //选填，默认值是 0
    @ExcelProperty("横版")
    private Integer tableHorizontal;

    //选填，默认值是 0
    @ExcelProperty("竖版")
    private Integer tableVertical;

    //选填，默认值是 0
    @ExcelProperty("横版是否默认展示")
    private Integer tableHorizontalDefaultDisplayed;

    //选填，默认值是 0
    @ExcelProperty("竖版是否默认展示")
    private Integer tableVerticalDefaultDisplayed;

    //选填，默认值是空字符串
    @ExcelProperty("指标展示名称")
    private String metricDisplayName;

    //选填，默认值是 0
    @ExcelProperty("指标展示层级")
    private Integer metricDisplayLevel;

    //选填，默认值是空字符串
    @ExcelProperty("一级分类")
    private String primaryCategory;

    //选填，默认值是空字符串
    @ExcelProperty("二级分类")
    private String secondaryCategory;

    //当moduleCodeName = TLModuleCode.core.getName() 时候，该字段必填；其他情况该字段值会被忽略
    @ExcelProperty("指标展示位置")
    private String cardDisplayLocation;

    //必填
    @ExcelProperty("数据类型")
    private String dataTypeName;

    //必填
    @ExcelProperty("同环比计算方式")
    private String rateCalcTypeName;

    //选填，默认值空字符串
    @ExcelProperty("同环比单位")
    private String rateUnit;

    //选填，默认值"无格式"
    @ExcelProperty("展示格式")
    private String displayFormatTypeName;

    //选填，默认值0
    @ExcelProperty("小数位数")
    private Integer decimalScale;

    //选填，默认值"整周"
    @ExcelProperty("周类型")
    private String weekTypeName;

    //必填
    @ExcelProperty("周期类型")
    private String periodType;

    //必填
    @ExcelProperty("汇总方式")
    private String aggregateTypeName;

    //必填
    @ExcelProperty("地域类型")
    private String regionTypeName;

    //选填，默认值1
    @ExcelProperty("更新周期")
    private Integer updateCycle;

    //选填，默认值空字符串
    @ExcelProperty("地域维度")
    private String geoDimList;

    //选填，默认值空字符串
    @ExcelProperty("其他维度")
    private String customDimList;

    //选填，默认值空字符串
    @ExcelProperty("特殊过滤条件")
    private String specialFilterCondition;

    //选填，默认值 SysConstants.FIELD_TM
    @ExcelProperty("实时趋势统计维度")
    private String realtimeDateDim;

    //选填，默认值空字符串
    @ExcelProperty("扩展字段")
    private String extInfo;

    /**
     * 起源指标名 must todo gaolei
     */
    @ExcelIgnore
    private String originMetricName;

    /**
     * 需要标记的字段名：错误配置、修改前后不同配置
     */
    @ExcelIgnore
    private List<String> markedConfigNames = new ArrayList<>();

    /**
     * 约定同一模块，相同一二级分类下metricCode唯一
     * @return
     */
    public String generateUniqueKey() {
        return this.moduleCodeName + "_"
                + this.metricCode + "_"
                + this.primaryCategory + "_"
                + this.secondaryCategory;
    }

    public void addWrongConfigName(String wrongConfigName) {
        if (markedConfigNames == null) {
            markedConfigNames = new ArrayList<>();
        }
        markedConfigNames.add(wrongConfigName);
    }

    @Slf4j
    public static class Listener extends AnalysisEventListener<TLBoardConfigVO> {
        @Getter
        private final List<TLBoardConfigVO> rows = new ArrayList<>();

        @Getter
        private final List<String> heads = new ArrayList<>();


        @Override
        public void invoke(TLBoardConfigVO tlBoardConfigVO, AnalysisContext analysisContext) {
            rows.add(tlBoardConfigVO);
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext analysisContext) {
            log.info("上传配置完成 , TLBoardConfigVOListener List size : {}" , rows.size());
        }

        @Override
        public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
            log.info("表头数据 excelHead= {}", headMap);
            for (int i = 0; i < headMap.size(); i++) {
                heads.add(headMap.get(i));
            }
        }

    }
}
