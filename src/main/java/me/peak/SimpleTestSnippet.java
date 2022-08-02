package me.peak;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Sets;
import com.uber.h3core.H3Core;
import com.uber.h3core.util.GeoCoord;
import me.peak.algo.PrintUtil;
import me.peak.util.DBUtil;
import me.peak.util.HttpClientUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.ehcache.impl.internal.util.ThreadFactoryUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by peak on 2016/11/26.
 */
public class SimpleTestSnippet {

	private static Logger logger = LoggerFactory.getLogger(SimpleTestSnippet.class);

    public static void main(String[] args) throws Exception {


		BigDecimal newValue = new BigDecimal("8628834");
		BigDecimal oldValue = new BigDecimal("10570218");
//		String a = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
//		System.out.println(a);
//		String b = "";
//		Integer a = 0;
//		System.out.println(b.hashCode());
//		System.out.println(a.hashCode());
//		LocalDate start = LocalDate.of(2022,6,1);
//		LocalDate end = LocalDate.of(2022,7,20);
//		List<String> res = showDate(start, end);
//		for(String s : res) {
//			System.out.print("'" + s + "'," );
//		}

//		String start = "2022-06-01-2022-06-";
//		for (int i = 1; i < 31; i++) {
//			System.out.print("'" + start + String.format("%02d",i) + "'");
//			System.out.print(",");
//		}

//		PrintUtil.printStrList(getParentPath("/BIKE_MBK_86"));
//		System.out.println("---");
//		PrintUtil.printStrList(getParentPath("/SPOCK_MBK_86/2"));
//		System.out.println("---");
//		PrintUtil.printStrList(getParentPath("/BIKE_MBK_86/-999/0595"));
//		System.out.println("---");
//		PrintUtil.printStrList(getParentPath("/SPOCK_MBK_86/12/846/1511/R10025011"));
//		ExecutorService executorService = Executors.newFixedThreadPool(10, ThreadFactoryUtil.threadFactory("testkill"));
//		for (int i = 0; i < 10; i++) {
//			executorService.submit(() -> {
//				String name = Thread.currentThread().getName();
//				System.out.println(name + " start");
//				try {
//					Thread.sleep(10000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				System.out.println(name + " end");
//			});
//		}
//		executorService.shutdown();

//		LocalTime time = LocalTime.now();
//		LocalTime time0930 = LocalTime.of(9,30);
//		System.out.println(time.isAfter(time0930));


//		System.out.println( LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

//		addAreaResource();
//		deleteAreaResource();
//		updateZoneResource();
//		updateAreaResource();

//		System.out.println(zone.size());

//		JSONObject json8865 = JSONObject.parseObject("{\"operate\":{\"filter\":{\"AND\":[{\"feature\":\"normal\",\"column\":\"dt\",\"type\":\"Date\",\"caliberDescRelevance\":\"\",\"value\":[\"1,day\"],\"dimOrMetric\":1,\"operator\":\"PREVIOUS\"}]},\"view\":[{\"func\":\"raw\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"dt\",\"name\":\"dt\",\"rollUpName\":\"\",\"alias\":\"日期\",\"fireworksColumnId\":162359,\"dimOrMetric\":1},{\"feature\":\"normal\",\"aliasEn\":\"city_code\",\"name\":\"city_code\",\"rollUpName\":\"\",\"alias\":\"城市（单车region）\",\"caliberDescRelevance\":\"\",\"fireworksColumnId\":162324,\"dimOrMetric\":1}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"7amto7pm_average_air_temperature_place_aggr\",\"name\":\"7amto7pm_average_air_temperature_place_aggr\",\"alias\":\"平均气温(地理聚合)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":468295,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"weather_score\",\"name\":\"weather_score\",\"alias\":\"天气分\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":375017,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_total_riding_revenue\",\"name\":\"bike_total_riding_revenue\",\"alias\":\"收入\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":375459,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_valid_trips\",\"name\":\"bike_valid_trips\",\"alias\":\"骑行订单量\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":377010,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"rpb_ma\",\"name\":\"rpb_ma\",\"alias\":\"车日均收入(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":376834,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_extensive_bike_ma_avg\",\"name\":\"bike_extensive_bike_ma_avg\",\"alias\":\"车均泛月活用户数(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":478478,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"all_put_bike_turnover_ma\",\"name\":\"all_put_bike_turnover_ma\",\"alias\":\"总投放周转(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":375300,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"healthy_turnover_ma\",\"name\":\"healthy_turnover_ma\",\"alias\":\"健康车周转(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":468290,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_dau\",\"name\":\"bike_dau\",\"alias\":\"单车活跃用户数\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":374706,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"standard_parking_bike_rate\",\"name\":\"standard_parking_bike_rate\",\"alias\":\"规范停放车比例\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":468302,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"all_put_bike_num_ma\",\"name\":\"all_put_bike_num_ma\",\"alias\":\"总投放车数(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":375306,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"healthy_bike_ma\",\"name\":\"healthy_bike_ma\",\"alias\":\"健康车数(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":374303,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"detain_bike_ma\",\"name\":\"detain_bike_ma\",\"alias\":\"扣车数(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":375353,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"other_not_usable_bike_num_ma\",\"name\":\"other_not_usable_bike_num_ma\",\"alias\":\"其他非可用车(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":417449,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"healthy_rate_ma\",\"name\":\"healthy_rate_ma\",\"alias\":\"健康度(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":374290,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"detain_bike_rate_ma\",\"name\":\"detain_bike_rate_ma\",\"alias\":\"扣车占总投放比(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":375325,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"other_not_usable_bike_num_rate_ma\",\"name\":\"other_not_usable_bike_num_rate_ma\",\"alias\":\"其他非可用车占比(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":417450,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"sleeping_bike_num_rate_ma\",\"name\":\"sleeping_bike_num_rate_ma\",\"alias\":\"休眠车占比(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":417443,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"pending_touched_bike_num_rate_ma\",\"name\":\"pending_touched_bike_num_rate_ma\",\"alias\":\"待运营触达占比(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":417456,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"operator_touched_bike_num_rate_ma\",\"name\":\"operator_touched_bike_num_rate_ma\",\"alias\":\"已运营触达占比(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":417454,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"healthy_to_fault_bike_num\",\"name\":\"healthy_to_fault_bike_num\",\"alias\":\"新增故障车数\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":375549,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"new_fault_month_rate\",\"name\":\"new_fault_month_rate\",\"alias\":\"月化新增故障率\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":417459,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"detain_cnt_ma\",\"name\":\"detain_cnt_ma\",\"alias\":\"扣入数(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":375321,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"kouchequhui_dispatch_num_ma\",\"name\":\"kouchequhui_dispatch_num_ma\",\"alias\":\"扣车取回数(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":375340,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_campus_ride_order_cnt\",\"name\":\"bike_campus_ride_order_cnt\",\"alias\":\"校园骑行订单量\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":468301,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"campus_healthy_bike_num\",\"name\":\"campus_healthy_bike_num\",\"alias\":\"校园拆分健康车数\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":417461,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"campus_healthy_bike_turnover\",\"name\":\"campus_healthy_bike_turnover\",\"alias\":\"校园拆分健康车周转\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":417460,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"active_om_campus_num\",\"name\":\"active_om_campus_num\",\"alias\":\"主动运维校园数量\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":458142,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_active_op_campus_order_cnt\",\"name\":\"bike_active_op_campus_order_cnt\",\"alias\":\"主动运维校园骑行订单量\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":468289,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"active_om_campus_healthy_bike_num\",\"name\":\"active_om_campus_healthy_bike_num\",\"alias\":\"主动运维校园拆分健康车数\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":417442,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"active_om_campus_healthy_bike_turnover\",\"name\":\"active_om_campus_healthy_bike_turnover\",\"alias\":\"主动运维校园拆分健康车周转\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":468288,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"new_city_healthy_bike_num\",\"name\":\"new_city_healthy_bike_num\",\"alias\":\"新城市健康车数\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":417458,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"健康车平均车龄\",\"name\":\"健康车平均车龄\",\"alias\":\"健康车平均车龄\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":417447,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_ride_once_return_succ_rat\",\"name\":\"bike_ride_once_return_succ_rat\",\"alias\":\"单车一次还车成功率\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":374502,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"dab_rate\",\"name\":\"dab_rate\",\"alias\":\"日活车辆占比\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":375614,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"wab_rate\",\"name\":\"wab_rate\",\"alias\":\"周活车辆占比\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":374912,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_user_ride_succ_rat\",\"name\":\"bike_user_ride_succ_rat\",\"alias\":\"单车用户骑行成功率\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":374722,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"clean_bike_percentage_30d\",\"name\":\"clean_bike_percentage_30d\",\"alias\":\"30天内清洁车占比\u200B\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":373802,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_general_monthly_active_user\",\"name\":\"bike_general_monthly_active_user\",\"alias\":\"泛月活用户数\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":375832,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_mtapp_no_card_general_4war\",\"name\":\"bike_mtapp_no_card_general_4war\",\"alias\":\"团无卡泛月活\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":374934,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_mtwx_no_card_general_4war\",\"name\":\"bike_mtwx_no_card_general_4war\",\"alias\":\"微信无卡泛月活\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":375290,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_pay_month_card_holder_num\",\"name\":\"bike_pay_month_card_holder_num\",\"alias\":\"持卡泛月活用户数\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":375398,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_card_extensive_bike_ma_avg\",\"name\":\"bike_card_extensive_bike_ma_avg\",\"alias\":\"车均持卡泛月活用户数(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":478477,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_arpu\",\"name\":\"bike_arpu\",\"alias\":\"ARPU\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":373917,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_mtapp_no_card_arpu\",\"name\":\"bike_mtapp_no_card_arpu\",\"alias\":\"团无卡ARPU\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":374930,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_mtwx_no_card_arpu\",\"name\":\"bike_mtwx_no_card_arpu\",\"alias\":\"微信无卡ARPU\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":375286,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_card_arpu\",\"name\":\"bike_card_arpu\",\"alias\":\"持卡ARPU\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":478465,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_total_riding_rpt\",\"name\":\"bike_total_riding_rpt\",\"alias\":\"单均收入\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":374440,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"mt_no_card_broad_month_active_revenue\",\"name\":\"mt_no_card_broad_month_active_revenue\",\"alias\":\"团无卡泛月活收入\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":374935,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"wx_no_card_broad_month_active_revenue\",\"name\":\"wx_no_card_broad_month_active_revenue\",\"alias\":\"微信无卡泛月活收入\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":375291,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_extensive_mar_active\",\"name\":\"bike_extensive_mar_active\",\"alias\":\"活跃度\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":478475,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_mt_no_card_extensive_mar_active\",\"name\":\"bike_mt_no_card_extensive_mar_active\",\"alias\":\"团无卡活跃度\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":478462,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_wx_no_card_extensive_mar_active\",\"name\":\"bike_wx_no_card_extensive_mar_active\",\"alias\":\"微信无卡活跃度\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":478464,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_card_extensive_mar_active\",\"name\":\"bike_card_extensive_mar_active\",\"alias\":\"持卡活跃度\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":478469,\"dimOrMetric\":0}]}],\"size\":1000,\"orderBy\":[{\"feature\":\"normal\",\"func\":\"sum\",\"column\":\"bike_valid_trips\",\"sort\":\"desc\",\"caliberDescRelevance\":\"\"}],\"udfs\":[],\"groupBy\":[{\"feature\":\"normal\",\"name\":\"dt\",\"caliberDescRelevance\":\"\"},{\"feature\":\"normal\",\"name\":\"city_code\",\"caliberDescRelevance\":\"\"}],\"derives\":[{\"dims\":[{\"values\":[\"022\"],\"column\":\"111\",\"name\":\"111\",\"operator\":\"=\"}],\"isCross\":\"0\",\"column\":\"111\",\"name\":\"1111\"}],\"expressions\":[]},\"datasource\":\"origin\",\"regionTags\":[],\"tableId\":\"999999\",\"type\":\"query\"}");
//		JSONObject json8882 = JSONObject.parseObject("{\"operate\":{\"filter\":{\"AND\":[{\"feature\":\"normal\",\"column\":\"dt\",\"type\":\"Date\",\"caliberDescRelevance\":\"\",\"value\":[\"1,day\"],\"dimOrMetric\":1,\"operator\":\"PREVIOUS\"}]},\"view\":[{\"func\":\"raw\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"dt\",\"name\":\"dt\",\"rollUpName\":\"\",\"alias\":\"日期\",\"fireworksColumnId\":162359,\"dimOrMetric\":1},{\"feature\":\"normal\",\"aliasEn\":\"city_code\",\"name\":\"city_code\",\"rollUpName\":\"\",\"alias\":\"城市（单车region）\",\"caliberDescRelevance\":\"\",\"fireworksColumnId\":162324,\"dimOrMetric\":1}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_total_riding_revenue\",\"name\":\"bike_total_riding_revenue\",\"alias\":\"收入\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":375459,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"健康车平均车龄\",\"name\":\"健康车平均车龄\",\"alias\":\"健康车平均车龄\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":417447,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"wx_no_card_broad_month_active_revenue\",\"name\":\"wx_no_card_broad_month_active_revenue\",\"alias\":\"微信无卡泛月活收入\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":375291,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"mt_no_card_broad_month_active_revenue\",\"name\":\"mt_no_card_broad_month_active_revenue\",\"alias\":\"团无卡泛月活收入\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":374935,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"clean_bike_percentage_30d\",\"name\":\"clean_bike_percentage_30d\",\"alias\":\"30天内清洁车占比\u200B\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":373802,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"kouchequhui_dispatch_num_ma\",\"name\":\"kouchequhui_dispatch_num_ma\",\"alias\":\"扣车取回数(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":375340,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"detain_cnt_ma\",\"name\":\"detain_cnt_ma\",\"alias\":\"扣入数(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":375321,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"standard_parking_bike_rate\",\"name\":\"standard_parking_bike_rate\",\"alias\":\"规范停放车比例\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":468302,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"weather_score\",\"name\":\"weather_score\",\"alias\":\"天气分\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":375017,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_card_extensive_bike_ma_avg\",\"name\":\"bike_card_extensive_bike_ma_avg\",\"alias\":\"车均持卡泛月活用户数(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":478477,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"active_om_campus_num\",\"name\":\"active_om_campus_num\",\"alias\":\"主动运维校园数量\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":458142,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"7amto7pm_average_air_temperature_place_aggr\",\"name\":\"7amto7pm_average_air_temperature_place_aggr\",\"alias\":\"平均气温(地理聚合)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":468295,\"dimOrMetric\":0}]}],\"size\":1000,\"orderBy\":[],\"udfs\":[],\"groupBy\":[{\"feature\":\"normal\",\"name\":\"dt\",\"caliberDescRelevance\":\"\"},{\"feature\":\"normal\",\"name\":\"city_code\",\"caliberDescRelevance\":\"\"}],\"derives\":[{\"dims\":[{\"values\":[\"022\"],\"column\":\"111\",\"name\":\"111\",\"operator\":\"=\"}],\"isCross\":\"0\",\"column\":\"111\",\"name\":\"1111\"}],\"expressions\":[]},\"datasource\":\"origin\",\"regionTags\":[],\"tableId\":\"999999\",\"type\":\"query\"}");
//		JSONObject json8884 = JSONObject.parseObject("{\"operate\":{\"filter\":{\"AND\":[{\"feature\":\"normal\",\"column\":\"dt\",\"type\":\"Date\",\"caliberDescRelevance\":\"\",\"value\":[\"1,day\"],\"dimOrMetric\":1,\"operator\":\"PREVIOUS\"}]},\"view\":[{\"func\":\"raw\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"dt\",\"name\":\"dt\",\"rollUpName\":\"\",\"alias\":\"日期\",\"fireworksColumnId\":162359,\"dimOrMetric\":1},{\"feature\":\"normal\",\"aliasEn\":\"city_code\",\"name\":\"city_code\",\"rollUpName\":\"\",\"alias\":\"城市（单车region）\",\"caliberDescRelevance\":\"\",\"fireworksColumnId\":162324,\"dimOrMetric\":1}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_dau\",\"name\":\"bike_dau\",\"alias\":\"单车活跃用户数\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":374706,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"healthy_to_fault_bike_num\",\"name\":\"healthy_to_fault_bike_num\",\"alias\":\"新增故障车数\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":375549,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"new_fault_month_rate\",\"name\":\"new_fault_month_rate\",\"alias\":\"月化新增故障率\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":417459,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_mtapp_no_card_general_4war\",\"name\":\"bike_mtapp_no_card_general_4war\",\"alias\":\"团无卡泛月活\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":374934,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_mt_no_card_extensive_mar_active\",\"name\":\"bike_mt_no_card_extensive_mar_active\",\"alias\":\"团无卡活跃度\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":478462,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_mtwx_no_card_general_4war\",\"name\":\"bike_mtwx_no_card_general_4war\",\"alias\":\"微信无卡泛月活\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":375290,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_wx_no_card_extensive_mar_active\",\"name\":\"bike_wx_no_card_extensive_mar_active\",\"alias\":\"微信无卡活跃度\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":478464,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_mtwx_no_card_arpu\",\"name\":\"bike_mtwx_no_card_arpu\",\"alias\":\"微信无卡ARPU\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":375286,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_mtapp_no_card_arpu\",\"name\":\"bike_mtapp_no_card_arpu\",\"alias\":\"团无卡ARPU\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":374930,\"dimOrMetric\":0}]}],\"size\":1000,\"orderBy\":[],\"udfs\":[],\"groupBy\":[{\"feature\":\"normal\",\"name\":\"dt\",\"caliberDescRelevance\":\"\"},{\"feature\":\"normal\",\"name\":\"city_code\",\"caliberDescRelevance\":\"\"}],\"derives\":[{\"dims\":[{\"values\":[\"022\"],\"column\":\"111\",\"name\":\"111\",\"operator\":\"=\"}],\"isCross\":\"0\",\"column\":\"111\",\"name\":\"1111\"}],\"expressions\":[]},\"datasource\":\"origin\",\"regionTags\":[],\"tableId\":\"999999\",\"type\":\"query\"}");
//		JSONObject json8885 = JSONObject.parseObject("{\"operate\":{\"filter\":{\"AND\":[{\"feature\":\"normal\",\"column\":\"dt\",\"type\":\"Date\",\"caliberDescRelevance\":\"\",\"value\":[\"1,day\"],\"dimOrMetric\":1,\"operator\":\"PREVIOUS\"}]},\"view\":[{\"func\":\"raw\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"dt\",\"name\":\"dt\",\"rollUpName\":\"\",\"alias\":\"日期\",\"fireworksColumnId\":162359,\"dimOrMetric\":1},{\"feature\":\"normal\",\"aliasEn\":\"city_code\",\"name\":\"city_code\",\"rollUpName\":\"\",\"alias\":\"城市（单车region）\",\"caliberDescRelevance\":\"\",\"fireworksColumnId\":162324,\"dimOrMetric\":1}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_extensive_mar_active\",\"name\":\"bike_extensive_mar_active\",\"alias\":\"活跃度\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":478475,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_total_riding_rpt\",\"name\":\"bike_total_riding_rpt\",\"alias\":\"单均收入\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":374440,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_user_ride_succ_rat\",\"name\":\"bike_user_ride_succ_rat\",\"alias\":\"单车用户骑行成功率\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":374722,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_ride_once_return_succ_rat\",\"name\":\"bike_ride_once_return_succ_rat\",\"alias\":\"单车一次还车成功率\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":374502,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_valid_trips\",\"name\":\"bike_valid_trips\",\"alias\":\"骑行订单量\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":377010,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_card_extensive_mar_active\",\"name\":\"bike_card_extensive_mar_active\",\"alias\":\"持卡活跃度\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":478469,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_arpu\",\"name\":\"bike_arpu\",\"alias\":\"ARPU\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":373917,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_card_arpu\",\"name\":\"bike_card_arpu\",\"alias\":\"持卡ARPU\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":478465,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_general_monthly_active_user\",\"name\":\"bike_general_monthly_active_user\",\"alias\":\"泛月活用户数\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":375832,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_pay_month_card_holder_num\",\"name\":\"bike_pay_month_card_holder_num\",\"alias\":\"持卡泛月活用户数\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":375398,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"rpb_ma\",\"name\":\"rpb_ma\",\"alias\":\"车日均收入(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":376834,\"dimOrMetric\":0}]}],\"size\":1000,\"orderBy\":[],\"udfs\":[],\"groupBy\":[{\"feature\":\"normal\",\"name\":\"dt\",\"caliberDescRelevance\":\"\"},{\"feature\":\"normal\",\"name\":\"city_code\",\"caliberDescRelevance\":\"\"}],\"derives\":[{\"dims\":[{\"values\":[\"022\"],\"column\":\"111\",\"name\":\"111\",\"operator\":\"=\"}],\"isCross\":\"0\",\"column\":\"111\",\"name\":\"1111\"}],\"expressions\":[]},\"datasource\":\"origin\",\"regionTags\":[],\"tableId\":\"999999\",\"type\":\"query\"}");
//		JSONObject json8886 = JSONObject.parseObject("{\"operate\":{\"filter\":{\"AND\":[{\"feature\":\"normal\",\"column\":\"dt\",\"type\":\"Date\",\"caliberDescRelevance\":\"\",\"value\":[\"1,day\"],\"dimOrMetric\":1,\"operator\":\"PREVIOUS\"}]},\"view\":[{\"func\":\"raw\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"dt\",\"name\":\"dt\",\"rollUpName\":\"\",\"alias\":\"日期\",\"fireworksColumnId\":162359,\"dimOrMetric\":1},{\"feature\":\"normal\",\"aliasEn\":\"city_code\",\"name\":\"city_code\",\"rollUpName\":\"\",\"alias\":\"城市（单车region）\",\"caliberDescRelevance\":\"\",\"fireworksColumnId\":162324,\"dimOrMetric\":1}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_active_op_campus_order_cnt\",\"name\":\"bike_active_op_campus_order_cnt\",\"alias\":\"主动运维校园骑行订单量\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":468289,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"active_om_campus_healthy_bike_turnover\",\"name\":\"active_om_campus_healthy_bike_turnover\",\"alias\":\"主动运维校园拆分健康车周转\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":468288,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_campus_ride_order_cnt\",\"name\":\"bike_campus_ride_order_cnt\",\"alias\":\"校园骑行订单量\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":468301,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_valid_trips_ma\",\"name\":\"healthy_turnover_ma\",\"alias\":\"健康车周转(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":468290,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"healthy_turnover_ma\",\"name\":\"all_put_bike_turnover_ma\",\"alias\":\"总投放周转(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":375300,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"all_put_bike_turnover_ma\",\"name\":\"active_om_campus_healthy_bike_num\",\"alias\":\"主动运维校园拆分健康车数\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":417442,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"active_om_campus_healthy_bike_num\",\"name\":\"campus_healthy_bike_turnover\",\"alias\":\"校园拆分健康车周转\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":417460,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"campus_healthy_bike_turnover\",\"name\":\"campus_healthy_bike_num\",\"alias\":\"校园拆分健康车数\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":417461,\"dimOrMetric\":0}]}],\"size\":1000,\"orderBy\":[],\"udfs\":[],\"groupBy\":[{\"feature\":\"normal\",\"name\":\"dt\",\"caliberDescRelevance\":\"\"},{\"feature\":\"normal\",\"name\":\"city_code\",\"caliberDescRelevance\":\"\"}],\"derives\":[{\"dims\":[{\"values\":[\"022\"],\"column\":\"111\",\"name\":\"111\",\"operator\":\"=\"}],\"isCross\":\"0\",\"column\":\"111\",\"name\":\"1111\"}],\"expressions\":[]},\"datasource\":\"origin\",\"regionTags\":[],\"tableId\":\"999999\",\"type\":\"query\"}");
//		JSONObject json8887 = JSONObject.parseObject("{\"operate\":{\"filter\":{\"AND\":[{\"feature\":\"normal\",\"column\":\"dt\",\"type\":\"Date\",\"caliberDescRelevance\":\"\",\"value\":[\"1,day\"],\"dimOrMetric\":1,\"operator\":\"PREVIOUS\"}]},\"view\":[{\"func\":\"raw\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"dt\",\"name\":\"dt\",\"rollUpName\":\"\",\"alias\":\"日期\",\"fireworksColumnId\":162359,\"dimOrMetric\":1},{\"feature\":\"normal\",\"aliasEn\":\"city_code\",\"name\":\"city_code\",\"rollUpName\":\"\",\"alias\":\"城市（单车region）\",\"caliberDescRelevance\":\"\",\"fireworksColumnId\":162324,\"dimOrMetric\":1}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"all_put_bike_num_ma\",\"name\":\"all_put_bike_num_ma\",\"alias\":\"总投放车数(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":375306,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"bike_extensive_bike_ma_avg\",\"name\":\"bike_extensive_bike_ma_avg\",\"alias\":\"车均泛月活用户数(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":478478,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"healthy_bike_ma\",\"name\":\"healthy_bike_ma\",\"alias\":\"健康车数(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":374303,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"healthy_rate_ma\",\"name\":\"healthy_rate_ma\",\"alias\":\"健康度(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":374290,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"detain_bike_rate_ma\",\"name\":\"detain_bike_rate_ma\",\"alias\":\"扣车占总投放比(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":375325,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"other_not_usable_bike_num_rate_ma\",\"name\":\"other_not_usable_bike_num_rate_ma\",\"alias\":\"其他非可用车占比(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":417450,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"detain_bike_ma\",\"name\":\"detain_bike_ma\",\"alias\":\"扣车数(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":375353,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"other_not_usable_bike_num_ma\",\"name\":\"other_not_usable_bike_num_ma\",\"alias\":\"其他非可用车(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":417449,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"sleeping_bike_num_rate_ma\",\"name\":\"sleeping_bike_num_rate_ma\",\"alias\":\"休眠车占比(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":417443,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"pending_touched_bike_num_rate_ma\",\"name\":\"pending_touched_bike_num_rate_ma\",\"alias\":\"待运营触达占比(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":417456,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"operator_touched_bike_num_rate_ma\",\"name\":\"operator_touched_bike_num_rate_ma\",\"alias\":\"已运营触达占比(MA)\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":417454,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"new_city_healthy_bike_num\",\"name\":\"new_city_healthy_bike_num\",\"alias\":\"新城市健康车数\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":417458,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"dab_rate\",\"name\":\"dab_rate\",\"alias\":\"日活车辆占比\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":375614,\"dimOrMetric\":0}]},{\"func\":\"sum\",\"columns\":[{\"feature\":\"normal\",\"aliasEn\":\"wab_rate\",\"name\":\"wab_rate\",\"alias\":\"周活车辆占比\",\"caliberDescRelevance\":\"\",\"deriveDimFilters\":[],\"fireworksColumnId\":374912,\"dimOrMetric\":0}]}],\"size\":1000,\"orderBy\":[],\"udfs\":[],\"groupBy\":[{\"feature\":\"normal\",\"name\":\"dt\",\"caliberDescRelevance\":\"\"},{\"feature\":\"normal\",\"name\":\"city_code\",\"caliberDescRelevance\":\"\"}],\"derives\":[{\"dims\":[{\"values\":[\"022\"],\"column\":\"111\",\"name\":\"111\",\"operator\":\"=\"}],\"isCross\":\"0\",\"column\":\"111\",\"name\":\"1111\"}],\"expressions\":[]},\"datasource\":\"origin\",\"regionTags\":[],\"tableId\":\"999999\",\"type\":\"query\"}");
//
//		printOriginId(8882, json8882);
//		printOriginId(8884, json8884);
//		printOriginId(8885, json8885);
//		printOriginId(8886, json8886);
//		printOriginId(8887, json8887);

//		List<String> names = new ArrayList<>();
//		JSONObject operate = json8865.getJSONObject("operate");
//		JSONArray view = operate.getJSONArray("view");
//		for (int i = 0; i < view.size(); i++) {
//			JSONObject viewChild = view.getJSONObject(i);
//			JSONArray columns = viewChild.getJSONArray("columns");
//			for (int j = 0; j < columns.size(); j++) {
//				JSONObject column = columns.getJSONObject(j);
//				names.add(column.getString("name"));
//			}
//		}


//		for (int i = -1; i < 4; i++) {
//			System.out.println(addZero(Integer.toBinaryString(i << 29)));
//		}
//		System.out.println(addZero(Integer.toBinaryString((1 << 29) - 1)));

//		ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap();
//		for (int i = 0; i < 100; i++) {
//			map.put(i, i);
//		}

//		System.out.println(DigestUtils.md5Hex("13512341234H!@6VAdtuduiiPAj"));

//		System.out.println(isDateValid("2021/02/31"));
//		System.out.println(isDateValid("2021/4/30"));
//		System.out.println(isDateValid("2021/04/30"));

//		classTypeTest();
//		stringInternTest();
//		threadStatusTest();
//		removeElementTest();
//		debugTest();
//		floatRoundTest();
//		testRandom();

//		testSwitch(1);
//		System.out.println("-------1 result end----------------");
//		testSwitch(2);
//		System.out.println("--------2 result end----------------");
//		testSwitch(3);
//		System.out.println("--------3 result end----------------");
//		testSwitch(4);
//		System.out.println("---------4 result end----------------");
//		testSwitch(5);
//		System.out.println("---------5 result end----------------");
//		testSwitch(6);
//		System.out.println("---------6 result end----------------");

//		addDouble();

//		testChangeSubArray();
//		System.out.println(divide(1, 2));
//		System.out.println(divide(0, 2));
//		System.out.println(divide(1, 0));
//		System.out.println(divide(5, 3));
//		System.out.println(divide(5, 4));

//		for (int i = 0; i < 100; i++) {
//			System.out.println(i + " " + i % 60);
//		}


//		JSONObject jsonObject = new JSONObject();
//		long mobiePhone = 13589016445L;
//		String url = "http://127.0.0.1:280/user-identify/getUUID";
//		for (int i = 0; i < 50; i++) {
//			mobiePhone ++;
//			jsonObject.put("mobilePhone", mobiePhone);
//			long start = System.currentTimeMillis();
//			HttpClientUtil.getInstance().doPost(url, jsonObject);
//			logger.info("{} cost {}", mobiePhone, System.currentTimeMillis() - start);
//		}

//		Executors.newCachedThreadPool();
	}

	static List<String> showDate(LocalDate start, LocalDate end) {
		List<String> res = new ArrayList<>();
		for (int i = 0; i < 1000; i++) {
			LocalDate cur = start.plusDays(i);
			if (cur.isAfter(end)) {
				break;
			}
			DayOfWeek dayOfWeek = cur.getDayOfWeek();
			int dayOfMonth = cur.getDayOfMonth();
			if (dayOfWeek == DayOfWeek.MONDAY) {
				String s = cur.format(DateTimeFormatter.ISO_LOCAL_DATE);
				String e = cur.plusDays(6).format(DateTimeFormatter.ISO_LOCAL_DATE);
				res.add(s + "-" + e);
			}
			if (dayOfMonth == 1) {
				String s = cur.format(DateTimeFormatter.ISO_LOCAL_DATE);;
				String e = cur.withDayOfMonth(cur.lengthOfMonth()).format(DateTimeFormatter.ISO_LOCAL_DATE);
				res.add(s + "-" + e);
			}
		}
		return res;
	}

	static List<String> getParentPath(String path) {
		char slash = '/';
		int index = path.indexOf(slash, 1);
		List<String> parentPathList = new ArrayList<>();
		while (index > 0) {
			parentPathList.add(path.substring(0, index));
			index = path.indexOf(slash, index + 1);
		}
		return parentPathList;
	}

	static void deleteAreaResource() {
		String uri = "http://10.34.0.53:8088/hbdata/deleteResource";
		String[] para = "17,18,19,20".split(",");
		for(String s : para) {
			String newUri = uri + "?resourceCode=apollo_vtwo_resource_ebike_parent_region_area_" + s + "&isSub=true";
			HttpClientUtil.getInstance().doGet(newUri);
		}

	}

	static void deleteZoneResource() {
		String uri = "http://10.34.0.53:8088/hbdata/deleteResource";
		String[] para = "62,65,66,67,68,113,1014,70,71,72,73,74,75,76,77,78,1226,1543,61,63,64".split(",");
		for(String s : para) {
			String newUri = uri + "?resourceCode=apollo_vtwo_resource_ebike_parent_region_zone_" + s + "&isSub=true";
			HttpClientUtil.getInstance().doGet(newUri);
		}
	}

	static void addResource(int i) {
		String uri = "http://10.22.69.50:8088/hbdata/createResource";
		JSONObject param = new JSONObject();
		param.put("createUser", "gaolei33");
		param.put("pcode", "apollo_vtwo_index_root");
		param.put("resourceCode", "apollo_vtwo_index_test_" + i);
		param.put("resourceName", "测试" + i);
		param.put("resourceOwner", "gaolei33");
		param.put("resourceType", "index");
		param.put("sourceId", "test_" + i);
		param.put("system", "apollo_vtwo");
		HttpClientUtil.getInstance().doPost(uri, param);
	}

	static void addAreaResource() {
		String uri = "http://10.34.0.53:8088/hbdata/createResource";
		JSONObject para = JSONObject.parseObject("{\"attrType\":0,\"createTime\":1642699206000,\"id\":3483852,\"isOpen\":0,\"modifyType\":0,\"pcode\":\"apollo_vtwo_resource_ebike_parent_region_root\",\"resourceCode\":\"apollo_vtwo_resource_ebike_parent_region_zone_62\",\"resourceName\":\"河南片区\",\"resourcePath\":\"/apollo_vtwo_resource_ebike_parent_region_root/apollo_vtwo_resource_ebike_parent_region_zone_62/\",\"resourceStatus\":0,\"resourceType\":\"dim\",\"sortNum\":0,\"system\":\"apollo_vtwo\",\"updateTime\":1642699206000}");

		String paraStr = "1606,经营四区\n" +
				"1623,经营三区\n" +
				"1629,经营二区\n" +
				"1635,经营一区";

		String[] lineArr = paraStr.split("\n");
		for (int i = 0; i < lineArr.length; i++) {
			String line = lineArr[i];
			String[] a = line.split(",");
			para.put("resourceCode", "apollo_vtwo_resource_ebike_parent_region_area_" + a[0]);
			para.put("resourceName", a[1]);
			para.put("resourcePath", "/apollo_vtwo_resource_ebike_parent_region_root/apollo_vtwo_resource_ebike_parent_region_area_" + a[0] + "/");
			System.out.println(para.toJSONString());
			HttpClientUtil.getInstance().doPost(uri, para);
		}
	}

	static void addZoneResource() {
		String uri = "http://10.34.0.53:8088/hbdata/createResource";
		JSONObject para = JSONObject.parseObject("{\"attrType\":0,\"createTime\":1642699206000,\"id\":3483852,\"isOpen\":0,\"modifyType\":0,\"pcode\":\"apollo_vtwo_resource_ebike_parent_region_root\",\"resourceCode\":\"apollo_vtwo_resource_ebike_parent_region_zone_62\",\"resourceName\":\"河南片区\",\"resourcePath\":\"/apollo_vtwo_resource_ebike_parent_region_root/apollo_vtwo_resource_ebike_parent_region_zone_62/\",\"resourceStatus\":0,\"resourceType\":\"dim\",\"sortNum\":0,\"system\":\"apollo_vtwo\",\"updateTime\":1642699206000}");

		String paraStr = "1010,桂海片区\n" +
				"1224,西区未挂T2片区\n" +
				"1607,川云青藏片区\n" +
				"1610,陕甘宁新蒙片区\n" +
				"1611,福建粤东片区\n" +
				"1612,广西贵州片区\n" +
				"1613,粤西海南湖南片区\n" +
				"1624,两河片区\n" +
				"1625,鄂赣苏皖片区\n" +
				"1626,晋鲁黑吉辽片区\n" +
				"1630,汕佛中沙城市群\n" +
				"1631,渝呼包城市群\n" +
				"1632,锦银城市群\n" +
				"1633,南桂钦城市群\n" +
				"1634,昆明\n" +
				"1636,昌襄汉城市群\n" +
				"1637,石津唐济保同城市群\n" +
				"1638,合宁杭城市群\n" +
				"1639,哈沈城市群";

		String[] lineArr = paraStr.split("\n");
		for (int i = 0; i < lineArr.length; i++) {
			String line = lineArr[i];
			String[] a = line.split(",");
			para.put("resourceCode", "apollo_vtwo_resource_ebike_parent_region_zone_" + a[0]);
			para.put("resourceName", a[1]);
			para.put("resourcePath", "/apollo_vtwo_resource_ebike_parent_region_root/apollo_vtwo_resource_ebike_parent_region_zone_" + a[0] + "/");
			System.out.println(para.toJSONString());
			HttpClientUtil.getInstance().doPost(uri, para);
		}
	}

	static void updateZoneResource() {
		String uri = "http://10.34.0.53:8088/hbdata/updateResource";
		JSONObject para = JSONObject.parseObject("{\"attrType\":0,\"createTime\":1642699206000,\"id\":3483852,\"isOpen\":0,\"modifyType\":0,\"pcode\":\"apollo_vtwo_resource_ebike_parent_region_root\",\"resourceCode\":\"apollo_vtwo_resource_ebike_parent_region_zone_62\",\"resourceName\":\"河南片区\",\"resourcePath\":\"/apollo_vtwo_resource_ebike_parent_region_root/apollo_vtwo_resource_ebike_parent_region_zone_62/\",\"resourceStatus\":0,\"resourceType\":\"dim\",\"sortNum\":0,\"system\":\"apollo_vtwo\",\"updateTime\":1642699206000}");

		String paraStr = "1004,东北片区\n" +
				"1005,河北片区\n" +
				"1006,河南片区\n" +
				"1007,内蒙古片区\n" +
				"1008,山西片区\n" +
				"1009,浙闽片区\n" +
				"1010,桂海片区\n" +
				"1011,苏皖片区\n" +
				"1012,湘鄂黔片区\n" +
				"1013,粤赣片区\n" +
				"1016,滇藏片区\n" +
				"1017,甘青宁片区\n" +
				"1018,新疆片区\n" +
				"1224,西区未挂T2片区\n" +
				"1228,东南区未挂T2片区\n" +
				"1511,山东片区\n" +
				"1512,陕川渝片区";

		String[] lineArr = paraStr.split("\n");
		for (int i = 0; i < lineArr.length; i++) {
			String line = lineArr[i];
			String[] a = line.split(",");
			para.put("resourceCode", "apollo_vtwo_resource_ebike_parent_region_zone_" + a[0]);
			para.put("resourceName", a[1] + "-渠道");
			para.put("resourcePath", "/apollo_vtwo_resource_ebike_parent_region_root/apollo_vtwo_resource_ebike_parent_region_zone_" + a[0] + "/");
			System.out.println(para.toJSONString());
			HttpClientUtil.getInstance().doPost(uri, para);
		}
	}

	static void updateAreaResource() {
		String uri = "http://10.34.0.53:8088/hbdata/updateResource";
		JSONObject para = JSONObject.parseObject("{\"attrType\":0,\"createTime\":1642699206000,\"id\":3483852,\"isOpen\":0,\"modifyType\":0,\"pcode\":\"apollo_vtwo_resource_ebike_parent_region_root\",\"resourceCode\":\"apollo_vtwo_resource_ebike_parent_region_zone_62\",\"resourceName\":\"河南片区\",\"resourcePath\":\"/apollo_vtwo_resource_ebike_parent_region_root/apollo_vtwo_resource_ebike_parent_region_zone_62/\",\"resourceStatus\":0,\"resourceType\":\"dim\",\"sortNum\":0,\"system\":\"apollo_vtwo\",\"updateTime\":1642699206000}");

		String paraStr = "846,北区\n" +
				"847,西区\n" +
				"849,东南区";

		String[] lineArr = paraStr.split("\n");
		for (int i = 0; i < lineArr.length; i++) {
			String line = lineArr[i];
			String[] a = line.split(",");
			para.put("resourceCode", "apollo_vtwo_resource_ebike_parent_region_area_" + a[0]);
			para.put("resourceName", a[1] + "-渠道");
			para.put("resourcePath", "/apollo_vtwo_resource_ebike_parent_region_root/apollo_vtwo_resource_ebike_parent_region_area_" + a[0] + "/");
			System.out.println(para.toJSONString());
			HttpClientUtil.getInstance().doPost(uri, para);
		}
	}


		static Float getFloatValue(Object object) {
		if (object instanceof Float) {
			return (Float) object;
		}
		if (object instanceof String) {
			if (object.equals("-") || StringUtils.isEmpty((String) object)) {
				return 0f;
			} else {
				return Float.parseFloat((String) object);
			}
		}
		return 0f;
	}

	public static void printOriginId(int tableId, JSONObject json) {
		JSONObject operate = json.getJSONObject("operate");
		JSONArray view = operate.getJSONArray("view");
		for (int i = 0; i < view.size(); i++) {
			JSONObject viewChild = view.getJSONObject(i);
			JSONArray columns = viewChild.getJSONArray("columns");
			for (int j = 0; j < columns.size(); j++) {
				JSONObject column = columns.getJSONObject(j);
				System.out.println(tableId + " " + column.getString("fireworksColumnId"));
			}
		}
	}


	public static class TestTask implements Callable {

		int time;
		public TestTask(int time) {
			this.time = time;
		}

		@Override
		public Integer call() throws Exception {
			Thread.sleep(time * 1000L);
			return time;
		}
	}

	static String buildQueryCountSql(String tableName, String dimCodeColumnName, String filterCondition){
		StringBuilder stringBuilder = new StringBuilder("select count(*) from ( select ");
		stringBuilder.append(dimCodeColumnName).append(" from ").append(tableName);
		if (!StringUtils.isEmpty(filterCondition)) {
			stringBuilder.append(" where ").append(filterCondition);
		}
		stringBuilder.append(" group by ").append(dimCodeColumnName).append(") t");
		return stringBuilder.toString();
	}

	static String getDBNameInSql(String sql) {
		String lowerCaseSql = sql.toLowerCase();
		int startIndex = 0;
		do {
			int fromIndex = lowerCaseSql.indexOf("from", startIndex);
			if (fromIndex == -1) {
				return null;
			}
			String dbName = getDBNameInSql(lowerCaseSql, fromIndex + 5);
			System.out.println("dbName " + dbName);
			if (dbName != null) {
				return dbName;
			}
			startIndex = fromIndex + 5;
		} while (startIndex < lowerCaseSql.length());
		return null;
	}

	static String getDBNameInSql(String sql, int startIndex) {
		int tableStartIndex = -1, tableEndIndex = -1;
		for (int i = startIndex; i < sql.length(); i++) {
			char c = sql.charAt(i);
			if (c != ' ' && tableStartIndex == -1) {
				tableStartIndex = i;
			} else if (c == ' ' && tableStartIndex > 0) {
				tableEndIndex = i + 1;
				break;
			}
		}
		String tableName = sql.substring(tableStartIndex, tableEndIndex);
		System.out.println("tableName " + tableName);
		int dotIndex = tableName.indexOf('.');
		if (dotIndex != -1) {
			return tableName.substring(0, dotIndex);
		}
		return null;
	}

	static String parseWhere(String sql) {
		if (sql == null) {
			return null;
		}
		int start = sql.lastIndexOf("where");
		if (start == -1) {
			return null;
		}
		start += 5;
		int end = sql.length() - 1;
		for (int i = end; i >= start ; i--) {
			char c = sql.charAt(i);
			if (c != ';' && c != ' ') {
				end = i + 1;
				break;
			}
		}
		return sql.substring(start, end);
	}

	static String addZero(String s) {
    	int res = s.length();
		for (int i = 0; i < 32-res; i++) {
		    s = "0" + s;
		}
		return s;
	}

	private static void testSplit() {
		String a = "1.2.3";
		String[] arr = a.split("\\.");
		System.out.println(arr.length);
	}


	private static String divide(Integer num1, Integer num2) {
		if ( num1 == null || num2 == null || num1 == 0 || num2 == 0 ) {
			return "0";
		}
//		NumberFormat numberFormat = NumberFormat.getInstance();
//		numberFormat.setMaximumFractionDigits(2);
//		return numberFormat.format(((double) num1) / num2);

		NumberFormat percentInstance = NumberFormat.getPercentInstance();
		percentInstance.setMaximumFractionDigits(2); // 保留小数两位
		return percentInstance.format(((double) num1) / num2);
	}

	public static void testStringGetBytes() {
		try {
			String a = new String("测试一下有啥区别".getBytes("GBK"), "GBK");
			String b = new String("测试一下有啥区别".getBytes("GBK"), "ISO8859-1");
			System.out.println(a.getBytes().length);
			System.out.println(b.getBytes().length);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * random.nextInt(5) 的结果是只会生成0到4的数字, 不会生成5
	 * 结果如下
	 * 0 count is : 14
	 * 1 count is : 13
	 * 2 count is : 26
	 * 3 count is : 19
	 * 4 count is : 28
	 * 5 count is : 0
	 */
	public static void testRandom() {
		Random random = new Random();
		int[] arr = new int[6];
		for (int i = 0 ; i < 100; i++) {
			int r = random.nextInt(1);
			arr[r]++;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.println(i + " count is : " + arr[i]);
		}
	}

	//改变父, 子会变化, 改变子, 父也会变化
	public static void testChangeSubArray() {
		List<String> parent = new ArrayList<>();
		parent.add("a");
		parent.add("a");
		parent.add("a");
		parent.add("a");
		parent.add("a");

		List<String> child = parent.subList(1,3);
//		child.set(0, "c");
		parent.set(2, "c");
		for (String a : parent) {
			System.out.println(a);
		}
		System.out.println("-------------");
		for (String a : child) {
			System.out.println(a);
		}
	}

	public static void addDouble() {
    	//精度问题
		Double a = 1.19;
		Double b = 0.99;
		System.out.println(Double.sum(a, b));
	}

	//case的行为是, 从匹配上的那一个开始, 会一直执行下去(不管是否能匹配上) 直到break或者函数结尾.
	//default 会匹配上所有情况
	public static void testSwitch(int i) {
		switch (i) {
			case 1:
				System.out.println(1);
				break;
			case 2:
				System.out.println(2);
			case 3:
				System.out.println(3);
				break;
			case 5:
				System.out.println(5);
			default:
				System.out.println("default");
		}
	}



	//test the breakpoint whether suspend other thread
	public static void debugTest() {

		Runnable a = new Runnable() {
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("this is a runnable");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("this is a runnable");
			}
		};

		Runnable b = new Runnable() {
			public void run() {
				//debug here, look the a thread print
				System.out.println("this is b runnable");
			}
		};

		new Thread(a).start();
		new Thread(b).start();
	}


	public static void floatRoundTest() {
		List<Float> nums = new ArrayList<>();
		nums.add(2050.4999f);
		nums.add(4480.59f);
		nums.add(4480.4f);
		nums.add(4480.5f);
		for (Float num : nums) {
			System.out.println(Math.round(num));
		}
	}

	public static int getRandom(){
		Random r = new Random();
		int random = r.nextInt(10) + 1;
		return random;
	}

	public static boolean isPhoneNumber(String mobile) {
		if (mobile == null) {
			return false;
		}
		String regExp = "^1\\d{10}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(mobile);
		return m.matches();
	}

    public static void classTypeTest(){
        Object o = new String();
        System.out.println(o.getClass()); // string
    }

    public static void stringInternTest(){
        String s1="123";
        String s2=new String("123");
        System.out.println(s1==s2);//false
        s2=s2.intern();
        System.out.println(s1==s2);//true
    }

    /**
     the result:
     before start, thread state is: NEW
     after start, thread state is: RUNNABLE
     thread run method over
     start over, thread state is: TERMINATED
     */
    public static void threadStatusTest() {
        try {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("thread run method over");
				}
			});
            System.out.println("before start, thread state is: " + t.getState().name());
            t.start();
            System.out.println("after start, thread state is: " + t.getState().name());
            Thread.sleep(1000);
            System.out.println("start over, thread state is: " + t.getState().name());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void removeElementTest() {
    	List<String> strList = new ArrayList<String>();
    	strList.add("1");
		strList.add("2");
		strList.add("3");
		strList.add("4");
		strList.add("5");

		//下标遍历, 不报错, 但是会少遍历一个
//		for (int i=0; i<strList.size(); i++) {
//			if (strList.get(i).equals("3")) {
//				strList.remove("3");
//			} else {
//				System.out.println(strList.get(i));
//			}
//		}

		//迭代器遍历, 有两种删除方式
//		Iterator<String> iterator = strList.iterator();
//		while (iterator.hasNext()) {
//			String str = iterator.next();
//			if (str.equals("3")) {
////				strList.remove(str);  //报错 ConcurrentModificationException
//				iterator.remove();  //正确写法, 不报错也不会少遍历元素
//			} else {
//				System.out.println(str);
//			}
//		}

		//这种实际上也是迭代器遍历, 抛异常 ConcurrentModificationException
		for (String str : strList) {
			if (str.equals("3")) {
				strList.remove(str);
			} else {
				System.out.println(str);
			}
		}
	}

}


