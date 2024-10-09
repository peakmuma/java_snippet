package me.peak;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
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

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.*;
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

	public static final String TLBOARD_TAB_HBDATA_RESOURCE_FORMAT = "apollo_vtwo_button_tlboard%s_tab%s";


	private static List<String> getDimCodeListExcludeSpecify(String[] geoDimArray, String specifyDim) {
		List<String> geoDimList = new ArrayList<>();
		for (String geoDim : geoDimArray) {
			if (!Objects.equals(geoDim, specifyDim)) {
				geoDimList.add(geoDim);
			}
		}
		return geoDimList;
	}

    public static void main(String[] args) throws Exception {

//		String url1 = "http://10.169.145.20:8080/api/v2/domain_model/summary/build/detail";
//		String url2 = "http://10.195.11.193:8080/api/v2/domain_model/summary/build/detail";
//		String param = "{\"modelId\":107807,\"modelVersionId\":87908,\"action\":\"\"}";
//		Map<String, String> header = new HashMap<>();
//		header.put("cookie", "biz_group=23649;com.sankuai.data.wanxiang.wanxiang_ssoid=eAGFjs0rRUEYxhskWemuZHWSBbccM3Nn5sxrxdXF0lcpG70zZw6Scxbn6lpYuHYsZUPJR7ck5SMWys5C-QukbHH_ACskV2Jr99TzPL9-TaTlc_etzrt-_bi_ZLzdJgt-ivH8Is75IRbRL2G8NIfxzF_o9bSlMqeoFWilAABtlcnqKBTaiEjbKP9OMl2TzoxbF7tlEVAFjPYHg7wggeVB1k6qMBBQnucFQb3Kw-bOFesk_F-w_lbtaxwu3509XrCRm4OV2yO2SrLNjaNjA0noMpmnymH1fP957fhlr1w9qVRPy60N3srVVlfnz3iDNP2KbRNfBpwzyzhyG6qIixCsMSgBrHTSAZtmCkQQgOZKKHlAOkrOLOcCBBEaIQIBAinXXGBkwERYw0jqpjxHQWtmnDFZFJir8SLFahUwJSWPcJW09SS4WJzlPu1Ba12adheTeRf7Q4WJdVKfpskXzuaH6g**eAEFwQERADEIAzBLUMpD5ez44V_CEi1rmMLdatPE8UMGamSVER_auiawgt9jzB9GseipNT0LZg-4**VXAqq2_r9zCu_yt2uFBQnHhkqAniDZNQi_aFU6riQaoIU95npu9Nn7ZZeQ4kJ2YtGqGgl_9xFbLWQr6cLXJlaA**NjE5NzI0NixnYW9sZWkzMyzpq5jno4osZ2FvbGVpMzNAbWVpdHVhbi5jb20sMSwwMzI1MDMzMCwxNjk3NzcwMzIyNDkx");
//		System.out.println(compareVinciResult(url1, url2, param, header));

//		System.out.println(LocalDate.now().minusDays(60).toString());

//		String sql1 = "##Description##\n门店覆盖分析-月\n\n##TaskInfo##\ncreator = 'liuchangfu02'\nsource = {\n    'db': META['hmart_grocery_dm'],\n}\nstream = {\n    'format': '',\n}\ntarget = {\n    'db': META['hmart_grocery_dm'],\n    'table': 'aggr_inp_gh_shop_cover_month',\n}\n\n##Load##\nSET hive.exec.dynamic.partition.mode=nonstrict;\nSET hive.exec.dynamic.partition=true;\nSET hive.exec.max.dynamic.partitions=1000;\nSET hive.default.fileformat=orc;\n## 触发spark合并小文件的阈值(20M)\nSET spark.sql.mergeSmallFileSize=20971520;\nSET spark.hadoopRDD.targetBytesInPartition=67108864;\nSET spark.sql.adaptive.shuffle.targetPostShuffleInputSize=134217728;\n## spark shuffle默认分区数\nSET spark.sql.shuffle.partitions=1000;\nSET spark.dynamicAllocation.minExecutors=100;\nSET spark.dynamicAllocation.maxExecutors=1500;\nSET spark.executor.memory=3G;\n## 启用mapjoin(500M)\nset spark.sql.autoBroadcastJoinThreshold=524288000;\n## 慢节点超时设置：\nset spark.shuffle.maxFetchWaitTime=7200s;\nset spark.dynamicAllocation.executorIdleTimeout=30;\n\nwith `model_108764` as\n(\n    select\n        coalesce(coalesce(`t_0`.`cooperation_type_code`,-999), '-999') as `cooperation_type_code`,\n        coalesce(`t_0`.`sale_biz_area_id`, '-999') as `sale_biz_area_id`,\n        coalesce(`t_0`.`sale_net_subarea_id`, '-999') as `sale_net_subarea_id`,\n        coalesce(`t_0`.`sale_city_id`, '-999') as `sale_city_id`,\n        coalesce(`t_0`.`sale_channel_subarea_id`, '-999') as `sale_channel_subarea_id`,\n        coalesce(`t_0`.`sale_group_id`, '-999') as `sale_group_id`,\n        coalesce(`t_0`.`combine_district_id`, '-999') as `combine_district_id`,\n        coalesce(`t_0`.`small_cellular_code`, '-999') as `small_cellular_code`,\n        `t_0`.`manage_gh_id` as `model_108764_open_poi_cnt_0`,\n        coalesce(`t_0`.`dt`, '-999') as `dt`\n    from\n        `mart_grocery_dm`.`topic_wide__gh_shop_dt` as `t_0`\n    where\n        `t_0`.`dt` between '$now.month_begin_date.datekey' and '$now.datekey'\n),\n`model_109824` as\n(\n    select\n        coalesce(`t_0`.`cooperation_type_code`, '-999') as `cooperation_type_code`,\n        coalesce(`t_3`.`sale_biz_area_id`, '-999') as `sale_biz_area_id`,\n        coalesce(`t_3`.`sale_net_subarea_id`, '-999') as `sale_net_subarea_id`,\n        coalesce(`t_3`.`sale_city_id`, '-999') as `sale_city_id`,\n        coalesce(`t_3`.`sale_channel_subarea_id`, '-999') as `sale_channel_subarea_id`,\n        coalesce(`t_3`.`sale_group_id`, '-999') as `sale_group_id`,\n        coalesce(`t_3`.`combine_district_id`, '-999') as `combine_district_id`,\n        coalesce(`t_3`.`small_cellular_code`, '-999') as `small_cellular_code`,\n        coalesce(CASE WHEN `t_2`.`open_days` BETWEEN 1 AND 7 THEN 1 WHEN `t_2`.`open_days` BETWEEN 8 AND 14 THEN 2 WHEN `t_2`.`open_days` >=15 THEN 3 else 0 end, '-999') as `open_days_level_br`,\n        `t_0`.`user_id` as `model_109824_newuser_num_0`,\n        coalesce(`t_0`.`dt`, '-999') as `dt`\n    from\n        `mart_grocery`.`app_inp_usr_invite_attribute_keep_dt` as `t_0`\n    left join\n        `mart_grocery`.`dim_poi_poi_snapshot` as `t_2`\n    on\n        `t_0`.`dt` = `t_2`.`dt`\n        and\n        `t_0`.`poi_id` = `t_2`.`poi_id`\n    left join\n        `mart_grocery`.`dim_inp_gh_crm_org_relation_da` as `t_3`\n    on\n        `t_0`.`dt` = `t_3`.`dt`\n        and\n        `t_0`.`poi_id` = `t_3`.`poi_id`\n    where\n        `t_0`.`dt` between '$now.month_begin_date.datekey' and '$now.datekey'\n),\n`model_119067` as\n(\n    select\n        coalesce(coalesce(`t_0`.`cooperation_type_code`,-999), '-999') as `cooperation_type_code`,\n        coalesce(`t_6`.`sale_biz_area_id`, '-999') as `sale_biz_area_id`,\n        coalesce(`t_6`.`sale_net_subarea_id`, '-999') as `sale_net_subarea_id`,\n        coalesce(`t_6`.`sale_city_id`, '-999') as `sale_city_id`,\n        coalesce(`t_6`.`sale_channel_subarea_id`, '-999') as `sale_channel_subarea_id`,\n        coalesce(`t_6`.`sale_group_id`, '-999') as `sale_group_id`,\n        coalesce(`t_6`.`combine_district_id`, '-999') as `combine_district_id`,\n        coalesce(`t_6`.`small_cellular_code`, '-999') as `small_cellular_code`,\n        `t_0`.`mt_permanent_user_cnt` as `model_119067_mt_home_user_cnt_0`,\n        `t_0`.`around_50m_mt_home_user_cnt` as `model_119067_around_50m_mt_home_user_cnt_0`,\n        `t_0`.`around_200m_mt_home_user_cnt` as `model_119067_around_200m_mt_home_user_cnt_0`,\n        coalesce(`t_0`.`dt`, '-999') as `dt`\n    from\n        `mart_grocery_dm`.`topic_inp_gh_lbs_h3_dt` as `t_0`\n    left join\n    (\n        select\n            `t_6`.`dt`,\n            `t_6`.`sale_biz_area_id`,\n            `t_6`.`sale_net_subarea_id`,\n            `t_6`.`sale_channel_subarea_id`,\n            `t_6`.`sale_city_id`,\n            `t_6`.`sale_group_id`,\n            `t_6`.`combine_district_id`,\n            `t_6`.`big_cellular_code`,\n            `t_6`.`small_cellular_code`\n        from\n            `mart_grocery`.`dim_inp_gh_crm_org_relation_da` as `t_6`\n        group by\n            `t_6`.`dt`,\n            `t_6`.`sale_biz_area_id`,\n            `t_6`.`sale_net_subarea_id`,\n            `t_6`.`sale_channel_subarea_id`,\n            `t_6`.`sale_city_id`,\n            `t_6`.`sale_group_id`,\n            `t_6`.`combine_district_id`,\n            `t_6`.`big_cellular_code`,\n            `t_6`.`small_cellular_code`\n    ) as `t_6`\n    on\n        `t_0`.`dt` = `t_6`.`dt`\n        and\n        `t_0`.`small_cellular_code` = `t_6`.`small_cellular_code`\n    where\n        `t_0`.`dt`='$now.datekey'\n),\n`model_127977` as\n(\n    select\n        coalesce(coalesce(`t_0`.`cooperation_type_code`,-999), '-999') as `cooperation_type_code`,\n        coalesce(`t_63`.`sale_biz_area_id`, '-999') as `sale_biz_area_id`,\n        coalesce(`t_63`.`sale_net_subarea_id`, '-999') as `sale_net_subarea_id`,\n        coalesce(`t_63`.`sale_city_id`, '-999') as `sale_city_id`,\n        coalesce(`t_63`.`sale_channel_subarea_id`, '-999') as `sale_channel_subarea_id`,\n        coalesce(`t_63`.`sale_group_id`, '-999') as `sale_group_id`,\n        coalesce(`t_63`.`combine_district_id`, '-999') as `combine_district_id`,\n        coalesce(`t_63`.`small_cellular_code`, '-999') as `small_cellular_code`,\n        coalesce(CASE WHEN `t_41`.`open_days` BETWEEN 1 AND 7 THEN 1 WHEN `t_41`.`open_days` BETWEEN 8 AND 14 THEN 2 WHEN `t_41`.`open_days` >=15 THEN 3 else 0 end, '-999') as `open_days_level_br`,\n        `t_0`.`sale_num` as `model_127977_sale_num_0`,\n        if(`t_0`.`pay_main_order_id` is not null, `t_0`.`poi_id`, null) as `model_127977_ord_shop_cnt_0`,\n        `t_0`.`sale_amt` as `model_127977_sale_amt_0`,\n        `t_0`.`buy_user_id` as `model_127977_buy_user_cnt_0`,\n        `t_0`.`sale_main_order_id` as `model_127977_main_order_num_0`,\n        coalesce(`t_0`.`dt`, '-999') as `dt`\n    from\n        `mart_grocery_dm`.`topic_tx_ord_sku_wide` as `t_0`\n    left join\n    (\n        select\n            `t_41`.`dt`,\n            `t_41`.`unique_area_id`,\n            `t_41`.`poi_id`,\n            `t_41`.`open_days`\n        from\n            `mart_grocery`.`dim_poi_poi_snapshot` as `t_41`\n        where\n            \n    `t_41`.`dt` between '$now.month_begin_date.datekey' and '$now.datekey'\n\n    ) as `t_41`\n    on\n        `t_0`.`dt` = `t_41`.`dt`\n        and\n        `t_0`.`poi_id` = `t_41`.`poi_id`\n    left join\n    (\n        select\n            `t_63`.`dt`,\n            `t_63`.`sale_channel_subarea_id`,\n            `t_63`.`small_cellular_code`,\n            `t_63`.`sale_city_id`,\n            `t_63`.`sale_group_id`,\n            `t_63`.`sale_biz_area_id`,\n            `t_63`.`poi_id`,\n            `t_63`.`combine_district_id`,\n            `t_63`.`sale_net_subarea_id`\n        from\n            `mart_grocery`.`dim_inp_gh_crm_org_relation_da` as `t_63`\n        where\n            \n    `t_63`.`dt` between '$now.month_begin_date.datekey' and '$now.datekey'\n\n    ) as `t_63`\n    on\n        `t_0`.`dt` = `t_63`.`dt`\n        and\n        `t_0`.`poi_id` = `t_63`.`poi_id`\n    where\n        `t_0`.`period_type` in ('1')\n        and\n        (`t_0`.`dt` between '$now.month_begin_date.datekey' and '$now.datekey')\n),\n`model_134012` as\n(\n    select\n        coalesce(`t_0`.`cooperation_type_code`, '-999') as `cooperation_type_code`,\n        coalesce(`t_1`.`sale_biz_area_id`, '-999') as `sale_biz_area_id`,\n        coalesce(`t_1`.`sale_net_subarea_id`, '-999') as `sale_net_subarea_id`,\n        coalesce(`t_1`.`sale_city_id`, '-999') as `sale_city_id`,\n        coalesce(`t_1`.`sale_channel_subarea_id`, '-999') as `sale_channel_subarea_id`,\n        coalesce(`t_1`.`sale_group_id`, '-999') as `sale_group_id`,\n        coalesce(`t_1`.`combine_district_id`, '-999') as `combine_district_id`,\n        coalesce(`t_1`.`small_cellular_code`, '-999') as `small_cellular_code`,\n        `t_0`.`new_online_manage_gh_id` as `model_134012_new_online_manage_shop_cnt_0`,\n        `t_0`.`restore_gh_id` as `model_134012_restore_shop_cnt_0`,\n        `t_0`.`manage_to_close_gh_id` as `model_134012_manage_to_close_shop_cnt_0`,\n        `t_0`.`manage_to_rest_gh_id` as `model_134012_manage_to_rest_shop_cnt_0`,\n        coalesce(`t_0`.`dt`, '-999') as `dt`\n    from\n        `mart_grocery_dm`.`dim_inp_gh_status_da_dwm_view` as `t_0`\n    left join\n    (\n        select\n            `t_1`.`dt`,\n            `t_1`.`sale_channel_subarea_id`,\n            `t_1`.`small_cellular_code`,\n            `t_1`.`gh_id`,\n            `t_1`.`sale_city_id`,\n            `t_1`.`sale_group_id`,\n            `t_1`.`sale_biz_area_id`,\n            `t_1`.`combine_district_id`,\n            `t_1`.`sale_net_subarea_id`\n        from\n            `mart_grocery`.`dim_inp_gh_crm_org_relation_da` as `t_1`\n        where\n            \n    `t_1`.`dt`='$now.datekey'\n\n    ) as `t_1`\n    on\n        `t_0`.`dt` = `t_1`.`dt`\n        and\n        `t_0`.`gh_id` = `t_1`.`gh_id`\n    where\n        `t_0`.`dt`='$now.datekey'\n        and `t_0`.`dwm_date_type` in ('month')\n),\n`model_134134` as\n(\n    select\n        coalesce(`t_0`.`business_type`, '-999') as `cooperation_type_code`,\n        coalesce(`t_1`.`sale_biz_area_id`, '-999') as `sale_biz_area_id`,\n        coalesce(`t_1`.`sale_net_subarea_id`, '-999') as `sale_net_subarea_id`,\n        coalesce(`t_1`.`sale_city_id`, '-999') as `sale_city_id`,\n        coalesce(`t_1`.`sale_channel_subarea_id`, '-999') as `sale_channel_subarea_id`,\n        coalesce(`t_1`.`sale_group_id`, '-999') as `sale_group_id`,\n        coalesce(`t_1`.`combine_district_id`, '-999') as `combine_district_id`,\n        coalesce(`t_1`.`small_cellular_code`, '-999') as `small_cellular_code`,\n        concat(`t_0`.`leads_type`,`t_0`.`recruit_leads_id`) as `model_134134_leads_mission_assign_cnt_0`,\n        concat(`t_0`.`leads_type`,`t_0`.`processing_leads_id`) as `model_134134_leads_mission_process_cnt_0`,\n        concat(`t_0`.`leads_type`,`t_0`.`translation_leads_id`) as `model_134134_leads_mission_reach_cnt_0`,\n        coalesce(`t_0`.`dt`, '-999') as `dt`\n    from\n        `mart_grocery_dm`.`topic_crm_mission_leads_process_status_da` as `t_0`\n    left join\n    (\n        select\n            `t_1`.`dt`,\n            `t_1`.`sale_biz_area_id`,\n            `t_1`.`sale_net_subarea_id`,\n            `t_1`.`sale_channel_subarea_id`,\n            `t_1`.`sale_city_id`,\n            `t_1`.`sale_group_id`,\n            `t_1`.`combine_district_id`,\n            `t_1`.`big_cellular_code`,\n            `t_1`.`small_cellular_code`\n        from\n            `mart_grocery`.`dim_inp_gh_crm_org_relation_da` as `t_1`\n        group by\n            `t_1`.`dt`,\n            `t_1`.`sale_biz_area_id`,\n            `t_1`.`sale_net_subarea_id`,\n            `t_1`.`sale_channel_subarea_id`,\n            `t_1`.`sale_city_id`,\n            `t_1`.`sale_group_id`,\n            `t_1`.`combine_district_id`,\n            `t_1`.`big_cellular_code`,\n            `t_1`.`small_cellular_code`\n    ) as `t_1`\n    on\n        `t_0`.`dt` = `t_1`.`dt`\n        and\n        `t_0`.`small_cellular_code` = `t_1`.`small_cellular_code`\n    where\n        `t_0`.`dt` between '$now.month_begin_date.datekey' and '$now.datekey'\n)\ninsert overwrite table `${target.table}`\npartition\n(\n    `dt` = '$now.datekey'\n)\nselect\n    `cooperation_type_code`,\n    `sale_biz_area_id`,\n    `sale_net_subarea_id`,\n    `sale_city_id`,\n    `sale_channel_subarea_id`,\n    `sale_group_id`,\n    `combine_district_id`,\n    `small_cellular_code`,\n    sum(mt_home_user_cnt_0) as `mt_home_user_cnt`,\n    sum(around_50m_mt_home_user_cnt_0) as `around_50m_mt_home_user_cnt`,\n    sum(around_50m_mt_home_user_cnt_0) /sum(mt_home_user_cnt_0) as `around_manage_gh_coverage_ratio_50m`,\n    sum(around_200m_mt_home_user_cnt_0) as `around_200m_mt_home_user_cnt`,\n    sum(around_200m_mt_home_user_cnt_0) /sum(mt_home_user_cnt_0) as `around_manage_gh_coverage_ratio_200m`,\n    count(distinct leads_mission_assign_cnt_0) as `leads_mission_assign_cnt`,\n    count(distinct leads_mission_process_cnt_0) as `leads_mission_process_cnt`,\n    count(distinct leads_mission_process_cnt_0) /count(distinct leads_mission_assign_cnt_0) as `leads_mission_process_ratio`,\n    count(distinct leads_mission_reach_cnt_0) as `leads_mission_reach_cnt`,\n    count(distinct leads_mission_reach_cnt_0) /count(distinct leads_mission_assign_cnt_0) as `leads_mission_reach_ratio`,\n    count(distinct new_online_manage_shop_cnt_0) +count(distinct restore_shop_cnt_0) -count(distinct manage_to_rest_shop_cnt_0) -count(distinct manage_to_close_shop_cnt_0) as `net_increase_manage_shop_cnt`,\n    count(distinct new_online_manage_shop_cnt_0) as `new_online_manage_shop_cnt`,\n    count(distinct restore_shop_cnt_0) as `restore_shop_cnt`,\n    count(distinct manage_to_rest_shop_cnt_0) as `manage_to_rest_shop_cnt`,\n    count(distinct manage_to_close_shop_cnt_0) as `manage_to_close_shop_cnt`,\n    sum(if(`open_days_level_br` in ('2' ), sale_num_0, null)) /count(distinct if(`open_days_level_br` in ('2' ), ord_shop_cnt_0, null)) as `new_shop_rec_8to14d_sale_num_per_poi`,\n    sum(if(`open_days_level_br` in ('2' ), sale_amt_0, null)) /count(distinct if(`open_days_level_br` in ('2' ), ord_shop_cnt_0, null)) as `new_shop_rec_8to14d_sale_amt_avg_shop`,\n    count(distinct if(`open_days_level_br` in ('2' ), ord_shop_cnt_0, null)) as `new_shop_rec_8to14d_sale_poi_num`,\n    count(distinct if(`open_days_level_br` in ('2' ), buy_user_cnt_0, null)) /count(distinct if(`open_days_level_br` in ('2' ), ord_shop_cnt_0, null)) as `new_shop_rec_8to14d_buy_user_cnt_avg_shop`,\n    count(distinct if(`open_days_level_br` in ('2' ), main_order_num_0, null)) /count(distinct if(`open_days_level_br` in ('2' ), ord_shop_cnt_0, null)) as `new_shop_rec_8to14d_order_num_avg_shop`,\n    count(distinct if(`open_days_level_br` in ('2' ), ord_shop_cnt_0, null)) /count(distinct open_poi_cnt_0) as `new_shop_rec_8to14d_sale_poi_ratio`,\n    count(distinct if(`open_days_level_br` in ('2' ), newuser_num_0, null)) /count(distinct if(`open_days_level_br` in ('2' ), ord_shop_cnt_0, null)) as `new_shop_rec_8to14d_newuser_num_avg_shop`\nfrom\n(\n    select\n        `cooperation_type_code`,\n        `sale_biz_area_id`,\n        `sale_net_subarea_id`,\n        `sale_city_id`,\n        `sale_channel_subarea_id`,\n        `sale_group_id`,\n        `combine_district_id`,\n        `small_cellular_code`,\n        null as `mt_home_user_cnt_0`,\n        null as `around_50m_mt_home_user_cnt_0`,\n        null as `around_200m_mt_home_user_cnt_0`,\n        null as `leads_mission_assign_cnt_0`,\n        null as `leads_mission_process_cnt_0`,\n        null as `leads_mission_reach_cnt_0`,\n        null as `new_online_manage_shop_cnt_0`,\n        null as `restore_shop_cnt_0`,\n        null as `manage_to_close_shop_cnt_0`,\n        null as `manage_to_rest_shop_cnt_0`,\n        null as `open_days_level_br`,\n        null as `sale_num_0`,\n        null as `ord_shop_cnt_0`,\n        null as `sale_amt_0`,\n        null as `buy_user_cnt_0`,\n        null as `main_order_num_0`,\n        `model_108764_open_poi_cnt_0` as `open_poi_cnt_0`,\n        null as `newuser_num_0`,\n        `dt`\n    from\n        `model_108764`\n\n    union all\n\n    select\n        `cooperation_type_code`,\n        `sale_biz_area_id`,\n        `sale_net_subarea_id`,\n        `sale_city_id`,\n        `sale_channel_subarea_id`,\n        `sale_group_id`,\n        `combine_district_id`,\n        `small_cellular_code`,\n        null as `mt_home_user_cnt_0`,\n        null as `around_50m_mt_home_user_cnt_0`,\n        null as `around_200m_mt_home_user_cnt_0`,\n        null as `leads_mission_assign_cnt_0`,\n        null as `leads_mission_process_cnt_0`,\n        null as `leads_mission_reach_cnt_0`,\n        null as `new_online_manage_shop_cnt_0`,\n        null as `restore_shop_cnt_0`,\n        null as `manage_to_close_shop_cnt_0`,\n        null as `manage_to_rest_shop_cnt_0`,\n        `open_days_level_br`,\n        null as `sale_num_0`,\n        null as `ord_shop_cnt_0`,\n        null as `sale_amt_0`,\n        null as `buy_user_cnt_0`,\n        null as `main_order_num_0`,\n        null as `open_poi_cnt_0`,\n        `model_109824_newuser_num_0` as `newuser_num_0`,\n        `dt`\n    from\n        `model_109824`\n\n    union all\n\n    select\n        `cooperation_type_code`,\n        `sale_biz_area_id`,\n        `sale_net_subarea_id`,\n        `sale_city_id`,\n        `sale_channel_subarea_id`,\n        `sale_group_id`,\n        `combine_district_id`,\n        `small_cellular_code`,\n        `model_119067_mt_home_user_cnt_0` as `mt_home_user_cnt_0`,\n        `model_119067_around_50m_mt_home_user_cnt_0` as `around_50m_mt_home_user_cnt_0`,\n        `model_119067_around_200m_mt_home_user_cnt_0` as `around_200m_mt_home_user_cnt_0`,\n        null as `leads_mission_assign_cnt_0`,\n        null as `leads_mission_process_cnt_0`,\n        null as `leads_mission_reach_cnt_0`,\n        null as `new_online_manage_shop_cnt_0`,\n        null as `restore_shop_cnt_0`,\n        null as `manage_to_close_shop_cnt_0`,\n        null as `manage_to_rest_shop_cnt_0`,\n        null as `open_days_level_br`,\n        null as `sale_num_0`,\n        null as `ord_shop_cnt_0`,\n        null as `sale_amt_0`,\n        null as `buy_user_cnt_0`,\n        null as `main_order_num_0`,\n        null as `open_poi_cnt_0`,\n        null as `newuser_num_0`,\n        `dt`\n    from\n        `model_119067`\n\n    union all\n\n    select\n        `cooperation_type_code`,\n        `sale_biz_area_id`,\n        `sale_net_subarea_id`,\n        `sale_city_id`,\n        `sale_channel_subarea_id`,\n        `sale_group_id`,\n        `combine_district_id`,\n        `small_cellular_code`,\n        null as `mt_home_user_cnt_0`,\n        null as `around_50m_mt_home_user_cnt_0`,\n        null as `around_200m_mt_home_user_cnt_0`,\n        null as `leads_mission_assign_cnt_0`,\n        null as `leads_mission_process_cnt_0`,\n        null as `leads_mission_reach_cnt_0`,\n        null as `new_online_manage_shop_cnt_0`,\n        null as `restore_shop_cnt_0`,\n        null as `manage_to_close_shop_cnt_0`,\n        null as `manage_to_rest_shop_cnt_0`,\n        `open_days_level_br`,\n        `model_127977_sale_num_0` as `sale_num_0`,\n        `model_127977_ord_shop_cnt_0` as `ord_shop_cnt_0`,\n        `model_127977_sale_amt_0` as `sale_amt_0`,\n        `model_127977_buy_user_cnt_0` as `buy_user_cnt_0`,\n        `model_127977_main_order_num_0` as `main_order_num_0`,\n        null as `open_poi_cnt_0`,\n        null as `newuser_num_0`,\n        `dt`\n    from\n        `model_127977`\n\n    union all\n\n    select\n        `cooperation_type_code`,\n        `sale_biz_area_id`,\n        `sale_net_subarea_id`,\n        `sale_city_id`,\n        `sale_channel_subarea_id`,\n        `sale_group_id`,\n        `combine_district_id`,\n        `small_cellular_code`,\n        null as `mt_home_user_cnt_0`,\n        null as `around_50m_mt_home_user_cnt_0`,\n        null as `around_200m_mt_home_user_cnt_0`,\n        null as `leads_mission_assign_cnt_0`,\n        null as `leads_mission_process_cnt_0`,\n        null as `leads_mission_reach_cnt_0`,\n        `model_134012_new_online_manage_shop_cnt_0` as `new_online_manage_shop_cnt_0`,\n        `model_134012_restore_shop_cnt_0` as `restore_shop_cnt_0`,\n        `model_134012_manage_to_close_shop_cnt_0` as `manage_to_close_shop_cnt_0`,\n        `model_134012_manage_to_rest_shop_cnt_0` as `manage_to_rest_shop_cnt_0`,\n        null as `open_days_level_br`,\n        null as `sale_num_0`,\n        null as `ord_shop_cnt_0`,\n        null as `sale_amt_0`,\n        null as `buy_user_cnt_0`,\n        null as `main_order_num_0`,\n        null as `open_poi_cnt_0`,\n        null as `newuser_num_0`,\n        `dt`\n    from\n        `model_134012`\n\n    union all\n\n    select\n        `cooperation_type_code`,\n        `sale_biz_area_id`,\n        `sale_net_subarea_id`,\n        `sale_city_id`,\n        `sale_channel_subarea_id`,\n        `sale_group_id`,\n        `combine_district_id`,\n        `small_cellular_code`,\n        null as `mt_home_user_cnt_0`,\n        null as `around_50m_mt_home_user_cnt_0`,\n        null as `around_200m_mt_home_user_cnt_0`,\n        `model_134134_leads_mission_assign_cnt_0` as `leads_mission_assign_cnt_0`,\n        `model_134134_leads_mission_process_cnt_0` as `leads_mission_process_cnt_0`,\n        `model_134134_leads_mission_reach_cnt_0` as `leads_mission_reach_cnt_0`,\n        null as `new_online_manage_shop_cnt_0`,\n        null as `restore_shop_cnt_0`,\n        null as `manage_to_close_shop_cnt_0`,\n        null as `manage_to_rest_shop_cnt_0`,\n        null as `open_days_level_br`,\n        null as `sale_num_0`,\n        null as `ord_shop_cnt_0`,\n        null as `sale_amt_0`,\n        null as `buy_user_cnt_0`,\n        null as `main_order_num_0`,\n        null as `open_poi_cnt_0`,\n        null as `newuser_num_0`,\n        `dt`\n    from\n        `model_134134`\n) as `model`\ngroup by\n    `cooperation_type_code`,\n    `sale_biz_area_id`,\n    `sale_net_subarea_id`,\n    `sale_city_id`,\n    `sale_channel_subarea_id`,\n    `sale_group_id`,\n    `combine_district_id`,\n    `small_cellular_code`\ngrouping sets\n(\n    (`sale_biz_area_id`),\n    (`sale_biz_area_id`, `sale_net_subarea_id`),\n    (`sale_biz_area_id`, `sale_net_subarea_id`, `sale_city_id`),\n    (`sale_biz_area_id`, `sale_net_subarea_id`, `sale_city_id`, `sale_group_id`),\n    (`sale_biz_area_id`, `sale_net_subarea_id`, `sale_city_id`, `sale_group_id`, `small_cellular_code`),\n    (`sale_biz_area_id`, `sale_net_subarea_id`, `sale_channel_subarea_id`),\n    (`sale_biz_area_id`, `sale_net_subarea_id`, `sale_channel_subarea_id`, `sale_city_id`),\n    (`sale_biz_area_id`, `sale_net_subarea_id`, `sale_channel_subarea_id`, `sale_city_id`, `combine_district_id`),\n    (`cooperation_type_code`),\n    (`cooperation_type_code`, `sale_biz_area_id`),\n    (`cooperation_type_code`, `sale_biz_area_id`, `sale_net_subarea_id`),\n    (`cooperation_type_code`, `sale_biz_area_id`, `sale_net_subarea_id`, `sale_city_id`),\n    (`cooperation_type_code`, `sale_biz_area_id`, `sale_net_subarea_id`, `sale_city_id`, `sale_group_id`),\n    (`cooperation_type_code`, `sale_biz_area_id`, `sale_net_subarea_id`, `sale_city_id`, `sale_group_id`, `small_cellular_code`),\n    (`cooperation_type_code`, `sale_biz_area_id`, `sale_net_subarea_id`, `sale_channel_subarea_id`),\n    (`cooperation_type_code`, `sale_biz_area_id`, `sale_net_subarea_id`, `sale_channel_subarea_id`, `sale_city_id`),\n    (`cooperation_type_code`, `sale_biz_area_id`, `sale_net_subarea_id`, `sale_channel_subarea_id`, `sale_city_id`, `combine_district_id`),\n    ()\n)\n;\n\n##TargetDDL##\ncreate table if not exists `${target.table}`\n(\n    `cooperation_type_code`                     bigint          comment '业务模式',\n    `sale_biz_area_id`                          bigint          comment '销售业务区域id',\n    `sale_net_subarea_id`                       bigint          comment '销售网店分区id',\n    `sale_city_id`                              bigint          comment '销售城市id',\n    `sale_channel_subarea_id`                   bigint          comment '销售渠道分区id',\n    `sale_group_id`                             bigint          comment '销售组id',\n    `combine_district_id`                       bigint          comment '合并区县id',\n    `small_cellular_code`                       string          comment '小蜂窝code',\n    `mt_home_user_cnt`                          bigint          comment '美团居住用户数',\n    `around_50m_mt_home_user_cnt`               bigint          comment '50m美团居住用户数',\n    `around_manage_gh_coverage_ratio_50m`       decimal(26,6)   comment '50m用户覆盖率',\n    `around_200m_mt_home_user_cnt`              bigint          comment '200m美团居住用户数',\n    `around_manage_gh_coverage_ratio_200m`      decimal(26,6)   comment '200m用户覆盖率',\n    `leads_mission_assign_cnt`                  bigint          comment '下发拓团线索数',\n    `leads_mission_process_cnt`                 bigint          comment '处理拓团线索数',\n    `leads_mission_process_ratio`               decimal(26,6)   comment '线索处理率',\n    `leads_mission_reach_cnt`                   bigint          comment '转化拓团线索数',\n    `leads_mission_reach_ratio`                 decimal(26,6)   comment '线索转化率',\n    `net_increase_manage_shop_cnt`              bigint          comment '净增营业门店数',\n    `new_online_manage_shop_cnt`                bigint          comment '新签营业门店数',\n    `restore_shop_cnt`                          bigint          comment '复开营业门店数',\n    `manage_to_rest_shop_cnt`                   bigint          comment '新增歇业门店数',\n    `manage_to_close_shop_cnt`                  bigint          comment '新增停业门店数',\n    `new_shop_rec_8to14d_sale_num_per_poi`      decimal(26,6)   comment '新建店次8-次14日店均销售件数',\n    `new_shop_rec_8to14d_sale_amt_avg_shop`     decimal(26,6)   comment '新建店次8-次14日店均销售额',\n    `new_shop_rec_8to14d_sale_poi_num`          bigint          comment '新建店次8-次14日动销门店数',\n    `new_shop_rec_8to14d_buy_user_cnt_avg_shop` bigint          comment '新建店次8-次14日店均购买用户数',\n    `new_shop_rec_8to14d_order_num_avg_shop`    decimal(26,6)   comment '新建店次8-次14日店均订单量',\n    `new_shop_rec_8to14d_sale_poi_ratio`        decimal(26,6)   comment '新建店次8-次14日门店动销率',\n    `new_shop_rec_8to14d_newuser_num_avg_shop`  decimal(26,6)   comment '新建店次8-14日店均新客数'\n)\ncomment '门店覆盖分析-月(dt静态分区)'\npartitioned by\n(\n    `dt`                                        string          comment 'dt'\n)\nstored as orc\n;";
//		String sql2 = "##Description##\n门店覆盖分析-月\n\n##TaskInfo##\ncreator = 'liuchangfu02'\nsource = {\n    'db': META['hmart_grocery_dm'],\n}\nstream = {\n    'format': '',\n}\ntarget = {\n    'db': META['hmart_grocery_dm'],\n    'table': 'aggr_inp_gh_shop_cover_month',\n}\n\n##Load##\nSET hive.exec.dynamic.partition.mode=nonstrict;\nSET hive.exec.dynamic.partition=true;\nSET hive.exec.max.dynamic.partitions=1000;\nSET hive.default.fileformat=orc;\n## 触发spark合并小文件的阈值(20M)\nSET spark.sql.mergeSmallFileSize=20971520;\nSET spark.hadoopRDD.targetBytesInPartition=67108864;\nSET spark.sql.adaptive.shuffle.targetPostShuffleInputSize=134217728;\n## spark shuffle默认分区数\nSET spark.sql.shuffle.partitions=1000;\nSET spark.dynamicAllocation.minExecutors=100;\nSET spark.dynamicAllocation.maxExecutors=1500;\nSET spark.executor.memory=3G;\n## 启用mapjoin(500M)\nset spark.sql.autoBroadcastJoinThreshold=524288000;\n## 慢节点超时设置：\nset spark.shuffle.maxFetchWaitTime=7200s;\nset spark.dynamicAllocation.executorIdleTimeout=30;\n\nwith `model_108764` as\n(\n    select\n        coalesce(coalesce(`t_0`.`cooperation_type_code`,-999), '-999') as `cooperation_type_code`,\n        coalesce(`t_0`.`sale_biz_area_id`, '-999') as `sale_biz_area_id`,\n        coalesce(`t_0`.`sale_net_subarea_id`, '-999') as `sale_net_subarea_id`,\n        coalesce(`t_0`.`sale_city_id`, '-999') as `sale_city_id`,\n        coalesce(`t_0`.`sale_channel_subarea_id`, '-999') as `sale_channel_subarea_id`,\n        coalesce(`t_0`.`sale_group_id`, '-999') as `sale_group_id`,\n        coalesce(`t_0`.`combine_district_id`, '-999') as `combine_district_id`,\n        coalesce(`t_0`.`small_cellular_code`, '-999') as `small_cellular_code`,\n        `t_0`.`manage_gh_id` as `model_108764_open_poi_cnt_0`,\n        coalesce(`t_0`.`dt`, '-999') as `dt`\n    from\n        `mart_grocery_dm`.`topic_wide__gh_shop_dt` as `t_0`\n    where\n        `t_0`.`dt` between '$now.month_begin_date.datekey' and '$now.datekey'\n),\n`model_109824` as\n(\n    select\n        coalesce(`t_0`.`cooperation_type_code`, '-999') as `cooperation_type_code`,\n        coalesce(`t_3`.`sale_biz_area_id`, '-999') as `sale_biz_area_id`,\n        coalesce(`t_3`.`sale_net_subarea_id`, '-999') as `sale_net_subarea_id`,\n        coalesce(`t_3`.`sale_city_id`, '-999') as `sale_city_id`,\n        coalesce(`t_3`.`sale_channel_subarea_id`, '-999') as `sale_channel_subarea_id`,\n        coalesce(`t_3`.`sale_group_id`, '-999') as `sale_group_id`,\n        coalesce(`t_3`.`combine_district_id`, '-999') as `combine_district_id`,\n        coalesce(`t_3`.`small_cellular_code`, '-999') as `small_cellular_code`,\n        coalesce(CASE WHEN `t_2`.`open_days` BETWEEN 1 AND 7 THEN 1 WHEN `t_2`.`open_days` BETWEEN 8 AND 14 THEN 2 WHEN `t_2`.`open_days` >=15 THEN 3 else 0 end, '-999') as `open_days_level_br`,\n        `t_0`.`user_id` as `model_109824_newuser_num_0`,\n        coalesce(`t_0`.`dt`, '-999') as `dt`\n    from\n        `mart_grocery`.`app_inp_usr_invite_attribute_keep_dt` as `t_0`\n    left join\n        `mart_grocery`.`dim_poi_poi_snapshot` as `t_2`\n    on\n        `t_0`.`dt` = `t_2`.`dt`\n        and\n        `t_0`.`poi_id` = `t_2`.`poi_id`\n    left join\n        `mart_grocery`.`dim_inp_gh_crm_org_relation_da` as `t_3`\n    on\n        `t_0`.`dt` = `t_3`.`dt`\n        and\n        `t_0`.`poi_id` = `t_3`.`poi_id`\n    where\n        `t_0`.`dt` between '$now.month_begin_date.datekey' and '$now.datekey'\n),\n`model_119067` as\n(\n    select\n        coalesce(coalesce(`t_0`.`cooperation_type_code`,-999), '-999') as `cooperation_type_code`,\n        coalesce(`t_6`.`sale_biz_area_id`, '-999') as `sale_biz_area_id`,\n        coalesce(`t_6`.`sale_net_subarea_id`, '-999') as `sale_net_subarea_id`,\n        coalesce(`t_6`.`sale_city_id`, '-999') as `sale_city_id`,\n        coalesce(`t_6`.`sale_channel_subarea_id`, '-999') as `sale_channel_subarea_id`,\n        coalesce(`t_6`.`sale_group_id`, '-999') as `sale_group_id`,\n        coalesce(`t_6`.`combine_district_id`, '-999') as `combine_district_id`,\n        coalesce(`t_6`.`small_cellular_code`, '-999') as `small_cellular_code`,\n        `t_0`.`mt_permanent_user_cnt` as `model_119067_mt_home_user_cnt_0`,\n        `t_0`.`around_50m_mt_home_user_cnt` as `model_119067_around_50m_mt_home_user_cnt_0`,\n        `t_0`.`around_200m_mt_home_user_cnt` as `model_119067_around_200m_mt_home_user_cnt_0`,\n        coalesce(`t_0`.`dt`, '-999') as `dt`\n    from\n        `mart_grocery_dm`.`topic_inp_gh_lbs_h3_dt` as `t_0`\n    left join\n    (\n        select\n            `t_6`.`dt`,\n            `t_6`.`sale_biz_area_id`,\n            `t_6`.`sale_net_subarea_id`,\n            `t_6`.`sale_channel_subarea_id`,\n            `t_6`.`sale_city_id`,\n            `t_6`.`sale_group_id`,\n            `t_6`.`combine_district_id`,\n            `t_6`.`big_cellular_code`,\n            `t_6`.`small_cellular_code`\n        from\n            `mart_grocery`.`dim_inp_gh_crm_org_relation_da` as `t_6`\n        group by\n            `t_6`.`dt`,\n            `t_6`.`sale_biz_area_id`,\n            `t_6`.`sale_net_subarea_id`,\n            `t_6`.`sale_channel_subarea_id`,\n            `t_6`.`sale_city_id`,\n            `t_6`.`sale_group_id`,\n            `t_6`.`combine_district_id`,\n            `t_6`.`big_cellular_code`,\n            `t_6`.`small_cellular_code`\n    ) as `t_6`\n    on\n        `t_0`.`dt` = `t_6`.`dt`\n        and\n        `t_0`.`small_cellular_code` = `t_6`.`small_cellular_code`\n    where\n        `t_0`.`dt`='$now.datekey'\n),\n`model_127977` as\n(\n    select\n        coalesce(coalesce(`t_0`.`cooperation_type_code`,-999), '-999') as `cooperation_type_code`,\n        coalesce(`t_63`.`sale_biz_area_id`, '-999') as `sale_biz_area_id`,\n        coalesce(`t_63`.`sale_net_subarea_id`, '-999') as `sale_net_subarea_id`,\n        coalesce(`t_63`.`sale_city_id`, '-999') as `sale_city_id`,\n        coalesce(`t_63`.`sale_channel_subarea_id`, '-999') as `sale_channel_subarea_id`,\n        coalesce(`t_63`.`sale_group_id`, '-999') as `sale_group_id`,\n        coalesce(`t_63`.`combine_district_id`, '-999') as `combine_district_id`,\n        coalesce(`t_63`.`small_cellular_code`, '-999') as `small_cellular_code`,\n        coalesce(CASE WHEN `t_41`.`open_days` BETWEEN 1 AND 7 THEN 1 WHEN `t_41`.`open_days` BETWEEN 8 AND 14 THEN 2 WHEN `t_41`.`open_days` >=15 THEN 3 else 0 end, '-999') as `open_days_level_br`,\n        `t_0`.`sale_num` as `model_127977_sale_num_0`,\n        if(`t_0`.`pay_main_order_id` is not null, `t_0`.`poi_id`, null) as `model_127977_ord_shop_cnt_0`,\n        `t_0`.`sale_amt` as `model_127977_sale_amt_0`,\n        `t_0`.`buy_user_id` as `model_127977_buy_user_cnt_0`,\n        `t_0`.`sale_main_order_id` as `model_127977_main_order_num_0`,\n        coalesce(`t_0`.`dt`, '-999') as `dt`\n    from\n        `mart_grocery_dm`.`topic_tx_ord_sku_wide` as `t_0`\n    left join\n    (\n        select\n            `t_41`.`dt`,\n            `t_41`.`unique_area_id`,\n            `t_41`.`poi_id`,\n            `t_41`.`open_days`\n        from\n            `mart_grocery`.`dim_poi_poi_snapshot` as `t_41`\n        where\n            \n    `t_41`.`dt` between '$now.month_begin_date.datekey' and '$now.datekey'\n\n    ) as `t_41`\n    on\n        `t_0`.`dt` = `t_41`.`dt`\n        and\n        `t_0`.`poi_id` = `t_41`.`poi_id`\n    left join\n    (\n        select\n            `t_63`.`dt`,\n            `t_63`.`sale_channel_subarea_id`,\n            `t_63`.`small_cellular_code`,\n            `t_63`.`sale_city_id`,\n            `t_63`.`sale_group_id`,\n            `t_63`.`sale_biz_area_id`,\n            `t_63`.`poi_id`,\n            `t_63`.`combine_district_id`,\n            `t_63`.`sale_net_subarea_id`\n        from\n            `mart_grocery`.`dim_inp_gh_crm_org_relation_da` as `t_63`\n        where\n            \n    `t_63`.`dt` between '$now.month_begin_date.datekey' and '$now.datekey'\n\n    ) as `t_63`\n    on\n        `t_0`.`dt` = `t_63`.`dt`\n        and\n        `t_0`.`poi_id` = `t_63`.`poi_id`\n    where\n        `t_0`.`period_type` in ('1')\n        and\n        (`t_0`.`dt` between '$now.month_begin_date.datekey' and '$now.datekey')\n),\n`model_134012` as\n(\n    select\n        coalesce(`t_0`.`cooperation_type_code`, '-999') as `cooperation_type_code`,\n        coalesce(`t_1`.`sale_biz_area_id`, '-999') as `sale_biz_area_id`,\n        coalesce(`t_1`.`sale_net_subarea_id`, '-999') as `sale_net_subarea_id`,\n        coalesce(`t_1`.`sale_city_id`, '-999') as `sale_city_id`,\n        coalesce(`t_1`.`sale_channel_subarea_id`, '-999') as `sale_channel_subarea_id`,\n        coalesce(`t_1`.`sale_group_id`, '-999') as `sale_group_id`,\n        coalesce(`t_1`.`combine_district_id`, '-999') as `combine_district_id`,\n        coalesce(`t_1`.`small_cellular_code`, '-999') as `small_cellular_code`,\n        `t_0`.`new_online_manage_gh_id` as `model_134012_new_online_manage_shop_cnt_0`,\n        `t_0`.`restore_gh_id` as `model_134012_restore_shop_cnt_0`,\n        `t_0`.`manage_to_close_gh_id` as `model_134012_manage_to_close_shop_cnt_0`,\n        `t_0`.`manage_to_rest_gh_id` as `model_134012_manage_to_rest_shop_cnt_0`,\n        coalesce(`t_0`.`dt`, '-999') as `dt`\n    from\n        `mart_grocery_dm`.`dim_inp_gh_status_da_dwm_view` as `t_0`\n    left join\n    (\n        select\n            `t_1`.`dt`,\n            `t_1`.`sale_channel_subarea_id`,\n            `t_1`.`small_cellular_code`,\n            `t_1`.`gh_id`,\n            `t_1`.`sale_city_id`,\n            `t_1`.`sale_group_id`,\n            `t_1`.`sale_biz_area_id`,\n            `t_1`.`combine_district_id`,\n            `t_1`.`sale_net_subarea_id`\n        from\n            `mart_grocery`.`dim_inp_gh_crm_org_relation_da` as `t_1`\n        where\n            \n    `t_1`.`dt`='$now.datekey'\n\n    ) as `t_1`\n    on\n        `t_0`.`dt` = `t_1`.`dt`\n        and\n        `t_0`.`gh_id` = `t_1`.`gh_id`\n    where\n        `t_0`.`dt`='$now.datekey'\n        and `t_0`.`dwm_date_type` in ('month')\n),\n`model_134134` as\n(\n    select\n        coalesce(`t_0`.`business_type`, '-999') as `cooperation_type_code`,\n        coalesce(`t_1`.`sale_biz_area_id`, '-999') as `sale_biz_area_id`,\n        coalesce(`t_1`.`sale_net_subarea_id`, '-999') as `sale_net_subarea_id`,\n        coalesce(`t_1`.`sale_city_id`, '-999') as `sale_city_id`,\n        coalesce(`t_1`.`sale_channel_subarea_id`, '-999') as `sale_channel_subarea_id`,\n        coalesce(`t_1`.`sale_group_id`, '-999') as `sale_group_id`,\n        coalesce(`t_1`.`combine_district_id`, '-999') as `combine_district_id`,\n        coalesce(`t_1`.`small_cellular_code`, '-999') as `small_cellular_code`,\n        concat(`t_0`.`leads_type`,`t_0`.`recruit_leads_id`) as `model_134134_leads_mission_assign_cnt_0`,\n        concat(`t_0`.`leads_type`,`t_0`.`processing_leads_id`) as `model_134134_leads_mission_process_cnt_0`,\n        concat(`t_0`.`leads_type`,`t_0`.`translation_leads_id`) as `model_134134_leads_mission_reach_cnt_0`,\n        coalesce(`t_0`.`dt`, '-999') as `dt`\n    from\n        `mart_grocery_dm`.`topic_crm_mission_leads_process_status_da` as `t_0`\n    left join\n    (\n        select\n            `t_1`.`dt`,\n            `t_1`.`sale_biz_area_id`,\n            `t_1`.`sale_net_subarea_id`,\n            `t_1`.`sale_channel_subarea_id`,\n            `t_1`.`sale_city_id`,\n            `t_1`.`sale_group_id`,\n            `t_1`.`combine_district_id`,\n            `t_1`.`big_cellular_code`,\n            `t_1`.`small_cellular_code`\n        from\n            `mart_grocery`.`dim_inp_gh_crm_org_relation_da` as `t_1`\n        group by\n            `t_1`.`dt`,\n            `t_1`.`sale_biz_area_id`,\n            `t_1`.`sale_net_subarea_id`,\n            `t_1`.`sale_channel_subarea_id`,\n            `t_1`.`sale_city_id`,\n            `t_1`.`sale_group_id`,\n            `t_1`.`combine_district_id`,\n            `t_1`.`big_cellular_code`,\n            `t_1`.`small_cellular_code`\n    ) as `t_1`\n    on\n        `t_0`.`dt` = `t_1`.`dt`\n        and\n        `t_0`.`small_cellular_code` = `t_1`.`small_cellular_code`\n    where\n        `t_0`.`dt` between '$now.month_begin_date.datekey' and '$now.datekey'\n)\ninsert overwrite table `${target.table}`\npartition\n(\n    `dt` = '$now.datekey'\n)\nselect\n    `cooperation_type_code`,\n    `sale_biz_area_id`,\n    `sale_net_subarea_id`,\n    `sale_city_id`,\n    `sale_channel_subarea_id`,\n    `sale_group_id`,\n    `combine_district_id`,\n    `small_cellular_code`,\n    sum(mt_home_user_cnt_0) as `mt_home_user_cnt`,\n    sum(around_50m_mt_home_user_cnt_0) as `around_50m_mt_home_user_cnt`,\n    sum(around_50m_mt_home_user_cnt_0) /sum(mt_home_user_cnt_0) as `around_manage_gh_coverage_ratio_50m`,\n    sum(around_200m_mt_home_user_cnt_0) as `around_200m_mt_home_user_cnt`,\n    sum(around_200m_mt_home_user_cnt_0) /sum(mt_home_user_cnt_0) as `around_manage_gh_coverage_ratio_200m`,\n    count(distinct leads_mission_assign_cnt_0) as `leads_mission_assign_cnt`,\n    count(distinct leads_mission_process_cnt_0) as `leads_mission_process_cnt`,\n    count(distinct leads_mission_process_cnt_0) /count(distinct leads_mission_assign_cnt_0) as `leads_mission_process_ratio`,\n    count(distinct leads_mission_reach_cnt_0) as `leads_mission_reach_cnt`,\n    count(distinct leads_mission_reach_cnt_0) /count(distinct leads_mission_assign_cnt_0) as `leads_mission_reach_ratio`,\n    count(distinct new_online_manage_shop_cnt_0) +count(distinct restore_shop_cnt_0) -count(distinct manage_to_rest_shop_cnt_0) -count(distinct manage_to_close_shop_cnt_0) as `net_increase_manage_shop_cnt`,\n    count(distinct new_online_manage_shop_cnt_0) as `new_online_manage_shop_cnt`,\n    count(distinct restore_shop_cnt_0) as `restore_shop_cnt`,\n    count(distinct manage_to_rest_shop_cnt_0) as `manage_to_rest_shop_cnt`,\n    count(distinct manage_to_close_shop_cnt_0) as `manage_to_close_shop_cnt`,\n    sum(if(`open_days_level_br` in ('2' ), sale_num_0, null)) /count(distinct if(`open_days_level_br` in ('2' ), ord_shop_cnt_0, null)) as `new_shop_rec_8to14d_sale_num_per_poi`,\n    sum(if(`open_days_level_br` in ('2' ), sale_amt_0, null)) /count(distinct if(`open_days_level_br` in ('2' ), ord_shop_cnt_0, null)) as `new_shop_rec_8to14d_sale_amt_avg_shop`,\n    count(distinct if(`open_days_level_br` in ('2' ), ord_shop_cnt_0, null)) as `new_shop_rec_8to14d_sale_poi_num`,\n    count(distinct if(`open_days_level_br` in ('2' ), buy_user_cnt_0, null)) /count(distinct if(`open_days_level_br` in ('2' ), ord_shop_cnt_0, null)) as `new_shop_rec_8to14d_buy_user_cnt_avg_shop`,\n    count(distinct if(`open_days_level_br` in ('2' ), main_order_num_0, null)) /count(distinct if(`open_days_level_br` in ('2' ), ord_shop_cnt_0, null)) as `new_shop_rec_8to14d_order_num_avg_shop`,\n    count(distinct if(`open_days_level_br` in ('2' ), ord_shop_cnt_0, null)) /count(distinct open_poi_cnt_0) as `new_shop_rec_8to14d_sale_poi_ratio`,\n    count(distinct if(`open_days_level_br` in ('2' ), newuser_num_0, null)) /count(distinct if(`open_days_level_br` in ('2' ), ord_shop_cnt_0, null)) as `new_shop_rec_8to14d_newuser_num_avg_shop`\nfrom\n(\n    select\n        `cooperation_type_code`,\n        `sale_biz_area_id`,\n        `sale_net_subarea_id`,\n        `sale_city_id`,\n        `sale_channel_subarea_id`,\n        `sale_group_id`,\n        `combine_district_id`,\n        `small_cellular_code`,\n        null as `mt_home_user_cnt_0`,\n        null as `around_50m_mt_home_user_cnt_0`,\n        null as `around_200m_mt_home_user_cnt_0`,\n        null as `leads_mission_assign_cnt_0`,\n        null as `leads_mission_process_cnt_0`,\n        null as `leads_mission_reach_cnt_0`,\n        null as `new_online_manage_shop_cnt_0`,\n        null as `restore_shop_cnt_0`,\n        null as `manage_to_close_shop_cnt_0`,\n        null as `manage_to_rest_shop_cnt_0`,\n        null as `open_days_level_br`,\n        null as `sale_num_0`,\n        null as `ord_shop_cnt_0`,\n        null as `sale_amt_0`,\n        null as `buy_user_cnt_0`,\n        null as `main_order_num_0`,\n        `model_108764_open_poi_cnt_0` as `open_poi_cnt_0`,\n        null as `newuser_num_0`,\n        `dt`\n    from\n        `model_108764`\n\n    union all\n\n    select\n        `cooperation_type_code`,\n        `sale_biz_area_id`,\n        `sale_net_subarea_id`,\n        `sale_city_id`,\n        `sale_channel_subarea_id`,\n        `sale_group_id`,\n        `combine_district_id`,\n        `small_cellular_code`,\n        null as `mt_home_user_cnt_0`,\n        null as `around_50m_mt_home_user_cnt_0`,\n        null as `around_200m_mt_home_user_cnt_0`,\n        null as `leads_mission_assign_cnt_0`,\n        null as `leads_mission_process_cnt_0`,\n        null as `leads_mission_reach_cnt_0`,\n        null as `new_online_manage_shop_cnt_0`,\n        null as `restore_shop_cnt_0`,\n        null as `manage_to_close_shop_cnt_0`,\n        null as `manage_to_rest_shop_cnt_0`,\n        `open_days_level_br`,\n        null as `sale_num_0`,\n        null as `ord_shop_cnt_0`,\n        null as `sale_amt_0`,\n        null as `buy_user_cnt_0`,\n        null as `main_order_num_0`,\n        null as `open_poi_cnt_0`,\n        `model_109824_newuser_num_0` as `newuser_num_0`,\n        `dt`\n    from\n        `model_109824`\n\n    union all\n\n    select\n        `cooperation_type_code`,\n        `sale_biz_area_id`,\n        `sale_net_subarea_id`,\n        `sale_city_id`,\n        `sale_channel_subarea_id`,\n        `sale_group_id`,\n        `combine_district_id`,\n        `small_cellular_code`,\n        `model_119067_mt_home_user_cnt_0` as `mt_home_user_cnt_0`,\n        `model_119067_around_50m_mt_home_user_cnt_0` as `around_50m_mt_home_user_cnt_0`,\n        `model_119067_around_200m_mt_home_user_cnt_0` as `around_200m_mt_home_user_cnt_0`,\n        null as `leads_mission_assign_cnt_0`,\n        null as `leads_mission_process_cnt_0`,\n        null as `leads_mission_reach_cnt_0`,\n        null as `new_online_manage_shop_cnt_0`,\n        null as `restore_shop_cnt_0`,\n        null as `manage_to_close_shop_cnt_0`,\n        null as `manage_to_rest_shop_cnt_0`,\n        null as `open_days_level_br`,\n        null as `sale_num_0`,\n        null as `ord_shop_cnt_0`,\n        null as `sale_amt_0`,\n        null as `buy_user_cnt_0`,\n        null as `main_order_num_0`,\n        null as `open_poi_cnt_0`,\n        null as `newuser_num_0`,\n        `dt`\n    from\n        `model_119067`\n\n    union all\n\n    select\n        `cooperation_type_code`,\n        `sale_biz_area_id`,\n        `sale_net_subarea_id`,\n        `sale_city_id`,\n        `sale_channel_subarea_id`,\n        `sale_group_id`,\n        `combine_district_id`,\n        `small_cellular_code`,\n        null as `mt_home_user_cnt_0`,\n        null as `around_50m_mt_home_user_cnt_0`,\n        null as `around_200m_mt_home_user_cnt_0`,\n        null as `leads_mission_assign_cnt_0`,\n        null as `leads_mission_process_cnt_0`,\n        null as `leads_mission_reach_cnt_0`,\n        null as `new_online_manage_shop_cnt_0`,\n        null as `restore_shop_cnt_0`,\n        null as `manage_to_close_shop_cnt_0`,\n        null as `manage_to_rest_shop_cnt_0`,\n        `open_days_level_br`,\n        `model_127977_sale_num_0` as `sale_num_0`,\n        `model_127977_ord_shop_cnt_0` as `ord_shop_cnt_0`,\n        `model_127977_sale_amt_0` as `sale_amt_0`,\n        `model_127977_buy_user_cnt_0` as `buy_user_cnt_0`,\n        `model_127977_main_order_num_0` as `main_order_num_0`,\n        null as `open_poi_cnt_0`,\n        null as `newuser_num_0`,\n        `dt`\n    from\n        `model_127977`\n\n    union all\n\n    select\n        `cooperation_type_code`,\n        `sale_biz_area_id`,\n        `sale_net_subarea_id`,\n        `sale_city_id`,\n        `sale_channel_subarea_id`,\n        `sale_group_id`,\n        `combine_district_id`,\n        `small_cellular_code`,\n        null as `mt_home_user_cnt_0`,\n        null as `around_50m_mt_home_user_cnt_0`,\n        null as `around_200m_mt_home_user_cnt_0`,\n        null as `leads_mission_assign_cnt_0`,\n        null as `leads_mission_process_cnt_0`,\n        null as `leads_mission_reach_cnt_0`,\n        `model_134012_new_online_manage_shop_cnt_0` as `new_online_manage_shop_cnt_0`,\n        `model_134012_restore_shop_cnt_0` as `restore_shop_cnt_0`,\n        `model_134012_manage_to_close_shop_cnt_0` as `manage_to_close_shop_cnt_0`,\n        `model_134012_manage_to_rest_shop_cnt_0` as `manage_to_rest_shop_cnt_0`,\n        null as `open_days_level_br`,\n        null as `sale_num_0`,\n        null as `ord_shop_cnt_0`,\n        null as `sale_amt_0`,\n        null as `buy_user_cnt_0`,\n        null as `main_order_num_0`,\n        null as `open_poi_cnt_0`,\n        null as `newuser_num_0`,\n        `dt`\n    from\n        `model_134012`\n\n    union all\n\n    select\n        `cooperation_type_code`,\n        `sale_biz_area_id`,\n        `sale_net_subarea_id`,\n        `sale_city_id`,\n        `sale_channel_subarea_id`,\n        `sale_group_id`,\n        `combine_district_id`,\n        `small_cellular_code`,\n        null as `mt_home_user_cnt_0`,\n        null as `around_50m_mt_home_user_cnt_0`,\n        null as `around_200m_mt_home_user_cnt_0`,\n        `model_134134_leads_mission_assign_cnt_0` as `leads_mission_assign_cnt_0`,\n        `model_134134_leads_mission_process_cnt_0` as `leads_mission_process_cnt_0`,\n        `model_134134_leads_mission_reach_cnt_0` as `leads_mission_reach_cnt_0`,\n        null as `new_online_manage_shop_cnt_0`,\n        null as `restore_shop_cnt_0`,\n        null as `manage_to_close_shop_cnt_0`,\n        null as `manage_to_rest_shop_cnt_0`,\n        null as `open_days_level_br`,\n        null as `sale_num_0`,\n        null as `ord_shop_cnt_0`,\n        null as `sale_amt_0`,\n        null as `buy_user_cnt_0`,\n        null as `main_order_num_0`,\n        null as `open_poi_cnt_0`,\n        null as `newuser_num_0`,\n        `dt`\n    from\n        `model_134134`\n) as `model`\ngroup by\n    `cooperation_type_code`,\n    `sale_biz_area_id`,\n    `sale_net_subarea_id`,\n    `sale_city_id`,\n    `sale_channel_subarea_id`,\n    `sale_group_id`,\n    `combine_district_id`,\n    `small_cellular_code`\ngrouping sets\n(\n    (`sale_biz_area_id`),\n    (`sale_biz_area_id`, `sale_net_subarea_id`),\n    (`sale_biz_area_id`, `sale_net_subarea_id`, `sale_city_id`),\n    (`sale_biz_area_id`, `sale_net_subarea_id`, `sale_city_id`, `sale_group_id`),\n    (`sale_biz_area_id`, `sale_net_subarea_id`, `sale_city_id`, `sale_group_id`, `small_cellular_code`),\n    (`sale_biz_area_id`, `sale_net_subarea_id`, `sale_channel_subarea_id`),\n    (`sale_biz_area_id`, `sale_net_subarea_id`, `sale_channel_subarea_id`, `sale_city_id`),\n    (`sale_biz_area_id`, `sale_net_subarea_id`, `sale_channel_subarea_id`, `sale_city_id`, `combine_district_id`),\n    (`cooperation_type_code`),\n    (`cooperation_type_code`, `sale_biz_area_id`),\n    (`cooperation_type_code`, `sale_biz_area_id`, `sale_net_subarea_id`),\n    (`cooperation_type_code`, `sale_biz_area_id`, `sale_net_subarea_id`, `sale_city_id`),\n    (`cooperation_type_code`, `sale_biz_area_id`, `sale_net_subarea_id`, `sale_city_id`, `sale_group_id`),\n    (`cooperation_type_code`, `sale_biz_area_id`, `sale_net_subarea_id`, `sale_city_id`, `sale_group_id`, `small_cellular_code`),\n    (`cooperation_type_code`, `sale_biz_area_id`, `sale_net_subarea_id`, `sale_channel_subarea_id`),\n    (`cooperation_type_code`, `sale_biz_area_id`, `sale_net_subarea_id`, `sale_channel_subarea_id`, `sale_city_id`),\n    (`cooperation_type_code`, `sale_biz_area_id`, `sale_net_subarea_id`, `sale_channel_subarea_id`, `sale_city_id`, `combine_district_id`),\n)\n;\n\n##TargetDDL##\ncreate table if not exists `${target.table}`\n(\n    `cooperation_type_code`                     bigint          comment '业务模式',\n    `sale_biz_area_id`                          bigint          comment '销售业务区域id',\n    `sale_net_subarea_id`                       bigint          comment '销售网店分区id',\n    `sale_city_id`                              bigint          comment '销售城市id',\n    `sale_channel_subarea_id`                   bigint          comment '销售渠道分区id',\n    `sale_group_id`                             bigint          comment '销售组id',\n    `combine_district_id`                       bigint          comment '合并区县id',\n    `small_cellular_code`                       string          comment '小蜂窝code',\n    `mt_home_user_cnt`                          bigint          comment '美团居住用户数',\n    `around_50m_mt_home_user_cnt`               bigint          comment '50m美团居住用户数',\n    `around_manage_gh_coverage_ratio_50m`       decimal(26,6)   comment '50m用户覆盖率',\n    `around_200m_mt_home_user_cnt`              bigint          comment '200m美团居住用户数',\n    `around_manage_gh_coverage_ratio_200m`      decimal(26,6)   comment '200m用户覆盖率',\n    `leads_mission_assign_cnt`                  bigint          comment '下发拓团线索数',\n    `leads_mission_process_cnt`                 bigint          comment '处理拓团线索数',\n    `leads_mission_process_ratio`               decimal(26,6)   comment '线索处理率',\n    `leads_mission_reach_cnt`                   bigint          comment '转化拓团线索数',\n    `leads_mission_reach_ratio`                 decimal(26,6)   comment '线索转化率',\n    `net_increase_manage_shop_cnt`              bigint          comment '净增营业门店数',\n    `new_online_manage_shop_cnt`                bigint          comment '新签营业门店数',\n    `restore_shop_cnt`                          bigint          comment '复开营业门店数',\n    `manage_to_rest_shop_cnt`                   bigint          comment '新增歇业门店数',\n    `manage_to_close_shop_cnt`                  bigint          comment '新增停业门店数',\n    `new_shop_rec_8to14d_sale_num_per_poi`      decimal(26,6)   comment '新建店次8-次14日店均销售件数',\n    `new_shop_rec_8to14d_sale_amt_avg_shop`     decimal(26,6)   comment '新建店次8-次14日店均销售额',\n    `new_shop_rec_8to14d_sale_poi_num`          bigint          comment '新建店次8-次14日动销门店数',\n    `new_shop_rec_8to14d_buy_user_cnt_avg_shop` bigint          comment '新建店次8-次14日店均购买用户数',\n    `new_shop_rec_8to14d_order_num_avg_shop`    decimal(26,6)   comment '新建店次8-次14日店均订单量',\n    `new_shop_rec_8to14d_sale_poi_ratio`        decimal(26,6)   comment '新建店次8-次14日门店动销率',\n    `new_shop_rec_8to14d_newuser_num_avg_shop`  decimal(26,6)   comment '新建店次8-14日店均新客数'\n)\ncomment '门店覆盖分析-月(dt静态分区)'\npartitioned by\n(\n    `dt`                                        string          comment 'dt'\n)\nstored as orc\n;";
//		System.out.println(sql1.equals(sql2));

//		List<String> inputList1 = Arrays.asList("abc", "def", "ghi", "jkl", "mno", "pqr", "stu", "vwx", "yz");
//		inputList1 = Arrays.asList("", "", "", "", "", "", "", "", "");
//		List<String> a = generateDimConfigStrs(inputList1);
//		System.out.println(a);
//		System.out.println(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE) + " 09:30:00");

//		String[] list = new String[]{"/areaRadar/staffDiagnosis/queryRegionInfo","/areaRadar/staffDiagnosis/getT0StaffCard","/areaRadar/staffDiagnosis/getT1FenceCard","/areaRadar/staffDiagnosis/queryT1DetailCard","/areaRadar/staffDiagnosis/queryT0MetricInfo","/areaRadar/staffDiagnosis/queryT1MetricInfo","/areaRadar/staffDiagnosis/queryRegionMetricInfo","/areaRadar/staffDiagnosis/queryT0DetailMetricInfo","/areaRadar/staffDiagnosis/queryT1DetailMetricInfo","/areaRadar/staffDiagnosis/getT0RealTimePosition","/areaRadar/staffDiagnosis/getT0Detail","/areaRadar/staffDiagnosis/getStaffPhoneNum","/areaRadar/staffDiagnosis/queryUserTrace"};
//		String template = "dataList = new ArrayList<>();\n" +
//				"dataList.add(AppControllerAuthRuleDO.builder().url(\"{0}\").authTypeParamName(\"bizType\").authTypeParamValue(\"1\").authValueParamName(\"regionCode\").rule(AppAuthRule.regionOrMapperRegion).build());\n" +
//				"dataList.add(AppControllerAuthRuleDO.builder().url(\"{0}\").authTypeParamName(\"bizType\").authTypeParamValue(\"2\").authValueParamName(\"regionCode\").rule(AppAuthRule.region).build());\n" +
//				"UrlAuthPolicyMap.put(\"{0}\", dataList);";
//
//		for (String str : list) {
//			System.out.println(MessageFormat.format(template, str));
//		}

		String a = "1,2;3,3;5,6";
		String[] b = a.split("[,;]");
		System.out.println(b[0]);

//		String orgJSON = "[{\"orgId\":\"114419\",\"orgName\":\"技术部\",\"orgHeaderMisId\":\"huangbinqiang\",\"orgHeaderName\":\"黄斌强\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/\"},{\"orgId\":\"150858\",\"orgName\":\"质量效能中心\",\"orgHeaderMisId\":\"majiankun02\",\"orgHeaderName\":\"马建堃\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/质量效能中心\"},{\"orgId\":\"150851\",\"orgName\":\"用户终端测试组\",\"orgHeaderMisId\":\"shixiaoyan02\",\"orgHeaderName\":\"石晓艳\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/质量效能中心/用户终端测试组\"},{\"orgId\":\"150863\",\"orgName\":\"服务端测试组\",\"orgHeaderMisId\":\"dingbaixia\",\"orgHeaderName\":\"丁佰霞\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/质量效能中心/服务端测试组\"},{\"orgId\":\"150864\",\"orgName\":\"效能提升组\",\"orgHeaderMisId\":\"luokai13\",\"orgHeaderName\":\"罗凯\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/质量效能中心/效能提升组\"},{\"orgId\":\"150868\",\"orgName\":\"运营环境和资产测试组\",\"orgHeaderMisId\":\"hemiao13\",\"orgHeaderName\":\"何淼\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/质量效能中心/运营环境和资产测试组\"},{\"orgId\":\"161654\",\"orgName\":\"运营测试组\",\"orgHeaderMisId\":\"gaoguozheng\",\"orgHeaderName\":\"高国政\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/质量效能中心/运营测试组\"},{\"orgId\":\"150865\",\"orgName\":\"基础服务研发中心\",\"orgHeaderMisId\":\"zhangxi\",\"orgHeaderName\":\"张熙\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/基础服务研发中心\"},{\"orgId\":\"114417\",\"orgName\":\"高可用保障组\",\"orgHeaderMisId\":\"donggan02\",\"orgHeaderName\":\"董干\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/基础服务研发中心/高可用保障组\"},{\"orgId\":\"150859\",\"orgName\":\"CRM研发组\",\"orgHeaderMisId\":\"jicai\",\"orgHeaderName\":\"冀才\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/基础服务研发中心/CRM研发组\"},{\"orgId\":\"150861\",\"orgName\":\"围栏服务组\",\"orgHeaderMisId\":\"zhangshengjie02\",\"orgHeaderName\":\"张胜杰\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/基础服务研发中心/围栏服务组\"},{\"orgId\":\"161660\",\"orgName\":\"IoT平台组\",\"orgHeaderMisId\":\"donggan02\",\"orgHeaderName\":\"董干\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/基础服务研发中心/IoT平台组\"},{\"orgId\":\"150867\",\"orgName\":\"前端研发中心\",\"orgHeaderMisId\":\"amuguleng\",\"orgHeaderName\":\"阿木古楞\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/前端研发中心\"},{\"orgId\":\"150850\",\"orgName\":\"管理系统组\",\"orgHeaderMisId\":\"nihongjin\",\"orgHeaderName\":\"倪洪金\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/前端研发中心/管理系统组\"},{\"orgId\":\"150852\",\"orgName\":\"运营研发组\",\"orgHeaderMisId\":\"zhouyicheng03\",\"orgHeaderName\":\"周奕成\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/前端研发中心/运营研发组\"},{\"orgId\":\"150860\",\"orgName\":\"小程序技术组\",\"orgHeaderMisId\":\"zhangyingye\",\"orgHeaderName\":\"张颖晔\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/前端研发中心/小程序技术组\"},{\"orgId\":\"150869\",\"orgName\":\"用户研发组\",\"orgHeaderMisId\":\"amuguleng\",\"orgHeaderName\":\"阿木古楞\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/前端研发中心/用户研发组\"},{\"orgId\":\"151787\",\"orgName\":\"数据智能中心\",\"orgHeaderMisId\":\"zhangjinlu\",\"orgHeaderName\":\"张金璐\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/数据智能中心\"},{\"orgId\":\"151788\",\"orgName\":\"策略开发组\",\"orgHeaderMisId\":\"qinjian05\",\"orgHeaderName\":\"秦健\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/数据智能中心/策略开发组\"},{\"orgId\":\"151789\",\"orgName\":\"数据服务组\",\"orgHeaderMisId\":\"weili03\",\"orgHeaderName\":\"魏立\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/数据智能中心/数据服务组\"},{\"orgId\":\"151790\",\"orgName\":\"基础数据组\",\"orgHeaderMisId\":\"zhangjinlu\",\"orgHeaderName\":\"张金璐\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/数据智能中心/基础数据组\"},{\"orgId\":\"151792\",\"orgName\":\"资产保全算法组\",\"orgHeaderMisId\":\"linjianfeng03\",\"orgHeaderName\":\"林剑峰\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/数据智能中心/资产保全算法组\"},{\"orgId\":\"40001674\",\"orgName\":\"用户数据BP组\",\"orgHeaderMisId\":\"zhangjinlu\",\"orgHeaderName\":\"张金璐\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/数据智能中心/用户数据BP组\"},{\"orgId\":\"40001675\",\"orgName\":\"运营数据BP组\",\"orgHeaderMisId\":\"zhaobaolong03\",\"orgHeaderName\":\"赵宝龙\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/数据智能中心/运营数据BP组\"},{\"orgId\":\"40001676\",\"orgName\":\"风控策略组\",\"orgHeaderMisId\":\"qinjian05\",\"orgHeaderName\":\"秦健\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/数据智能中心/风控策略组\"},{\"orgId\":\"40005148\",\"orgName\":\"用户营销算法组\",\"orgHeaderMisId\":\"linjianfeng03\",\"orgHeaderName\":\"林剑峰\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/数据智能中心/用户营销算法组\"},{\"orgId\":\"40005149\",\"orgName\":\"智能调度算法组\",\"orgHeaderMisId\":\"dongzhaochen\",\"orgHeaderName\":\"董钊辰\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/数据智能中心/智能调度算法组\"},{\"orgId\":\"157076\",\"orgName\":\"运营管理研发中心\",\"orgHeaderMisId\":\"huangbinqiang\",\"orgHeaderName\":\"黄斌强\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/运营管理研发中心\"},{\"orgId\":\"150854\",\"orgName\":\"财务结算组\",\"orgHeaderMisId\":\"mazheng06\",\"orgHeaderName\":\"马峥\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/运营管理研发中心/财务结算组\"},{\"orgId\":\"157090\",\"orgName\":\"库外运营研发组\",\"orgHeaderMisId\":\"wengshijin\",\"orgHeaderName\":\"翁世进\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/运营管理研发中心/库外运营研发组\"},{\"orgId\":\"157092\",\"orgName\":\"资产和库内研发组\",\"orgHeaderMisId\":\"zhaolingzhi\",\"orgHeaderName\":\"赵凌志\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/运营管理研发中心/资产和库内研发组\"},{\"orgId\":\"157094\",\"orgName\":\"供应链系统组\",\"orgHeaderMisId\":\"haoxuewu\",\"orgHeaderName\":\"郝学武\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/运营管理研发中心/供应链系统组\"},{\"orgId\":\"157095\",\"orgName\":\"运营支撑组\",\"orgHeaderMisId\":\"kangzhangxi\",\"orgHeaderName\":\"亢章熙\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/运营管理研发中心/运营支撑组\"},{\"orgId\":\"40001673\",\"orgName\":\"用户交易研发中心\",\"orgHeaderMisId\":\"haoxuewu\",\"orgHeaderName\":\"郝学武\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/用户交易研发中心\"},{\"orgId\":\"157089\",\"orgName\":\"骑行体验组\",\"orgHeaderMisId\":\"zhangyihu\",\"orgHeaderName\":\"张翼虎\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/用户交易研发中心/骑行体验组\"},{\"orgId\":\"157091\",\"orgName\":\"骑行服务组\",\"orgHeaderMisId\":\"raojia03\",\"orgHeaderName\":\"饶佳\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/用户交易研发中心/骑行服务组\"},{\"orgId\":\"157093\",\"orgName\":\"订单服务组\",\"orgHeaderMisId\":\"sundameng\",\"orgHeaderName\":\"孙大猛\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/用户交易研发中心/订单服务组\"},{\"orgId\":\"40001677\",\"orgName\":\"定位服务组\",\"orgHeaderMisId\":\"haoxuewu\",\"orgHeaderName\":\"郝学武\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/用户交易研发中心/定位服务组\"},{\"orgId\":\"40003202\",\"orgName\":\"运营环境研发组\",\"orgHeaderMisId\":\"haoxuewu\",\"orgHeaderName\":\"郝学武\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/用户交易研发中心/运营环境研发组\"},{\"orgId\":\"40003203\",\"orgName\":\"营销支撑组\",\"orgHeaderMisId\":\"zhuzhan02\",\"orgHeaderName\":\"朱展\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/用户交易研发中心/营销支撑组\"},{\"orgId\":\"40003204\",\"orgName\":\"营销业务组\",\"orgHeaderMisId\":\"zhuzhan02\",\"orgHeaderName\":\"朱展\",\"leaderInfoList\":[],\"memberNumber\":0,\"system\":null,\"orgPath\":\"公司/美团/骑行事业部/技术部/用户交易研发中心/营销业务组\"}]";
//		JSONArray json = JSONArray.parseArray(orgJSON);
//		for (int i = 0; i < json.size(); i++) {
//			JSONObject org = json.getJSONObject(i);
//			String orgPath = org.getString("orgPath").substring("公司/美团/骑行事业部/技术部/".length());
//			String orgHeaderName = org.getString("orgHeaderName");
//			String orgHeaderMis = org.getString("orgHeaderMisId");
//			System.out.print(orgPath + " " + orgHeaderName + " " + orgHeaderMis);
//			System.out.println();
//		}

//		LocalDate start = LocalDate.of(2022, 1, 2);
//		start = start.plusWeeks(-51);
//		System.out.println(start);

//		String res = "{\"resultCode\":\"200\",\"resultMsg\":\"OK\",\"content\":[{\"name\":\"首页\",\"url\":\"/home\",\"children\":[],\"icon\":null,\"key\":\"\",\"type\":1},{\"name\":\"数据看板\",\"url\":\"/dataBoard\",\"children\":[{\"name\":\"核心指标\",\"url\":null,\"children\":[{\"name\":\"单车区域经营分析看板\",\"url\":\"/dataBoard/analyse/operate/area?tlBoardId=3&menu_tlboard3_v11\",\"children\":[],\"icon\":null,\"key\":\"menu_tlboard3_v11\",\"type\":1},{\"name\":\"单车输入输出看板\",\"url\":\"/dataBoard/ioboard?id=1&menu_ioboard1_v11\",\"children\":[],\"icon\":null,\"key\":\"menu_ioboard1_v11\",\"type\":1},{\"name\":\"电单车输入输出看板\",\"url\":\"/dataBoard/ioboard?id=2&menu_ioboard2_v11\",\"children\":[],\"icon\":null,\"key\":\"menu_ioboard2_v11\",\"type\":1},{\"name\":\"用户运营输入输出看板\",\"url\":null,\"children\":[{\"name\":\"单车UO输入输出看板\",\"url\":\"/dataBoard/ioboard?id=4&menu_ioboard4_v11\",\"children\":[],\"icon\":null,\"key\":\"menu_ioboard4_v11\",\"type\":1},{\"name\":\"电单车UO输入输出看板\",\"url\":\"/dataBoard/ioboard?id=5&menu_ioboard5_v11\",\"children\":[],\"icon\":null,\"key\":\"menu_ioboard5_v11\",\"type\":1}],\"icon\":null,\"key\":\"menu_userio\",\"type\":1},{\"name\":\"电单车城市诊断看板\",\"url\":\"/dataBoard/analyse/operate/area?tlBoardId=6&menu_tlboard6_v11\",\"children\":[],\"icon\":null,\"key\":\"menu_tlboard6_v11\",\"type\":1},{\"name\":\"电单车库外运营日报\",\"url\":\"/dataBoard/spock/dispatch\",\"children\":[],\"icon\":null,\"key\":\"menu_spock_dispatch_v11\",\"type\":1},{\"name\":\"单车首页\",\"url\":\"/dataBoard/main\",\"children\":[],\"icon\":null,\"key\":\"menu_main_v11\",\"type\":1}],\"icon\":null,\"key\":\"menu_21820_v11\",\"type\":1},{\"name\":\"单车\",\"url\":null,\"children\":[{\"name\":\"实时监控\",\"url\":null,\"children\":[{\"name\":\"实时核心指标监控\",\"url\":\"/dataBoard/report?id=245&menu_22320_v11\",\"children\":[],\"icon\":null,\"key\":\"menu_22320_v11\",\"type\":1}],\"icon\":null,\"key\":\"menu_21930_v11\",\"type\":1},{\"name\":\"全局分析\",\"url\":null,\"children\":[{\"name\":\"全局分析\",\"url\":\"/dataBoard/report?id=741&menu_22350_v11\",\"children\":[],\"icon\":null,\"key\":\"menu_22350_v11\",\"type\":1}],\"icon\":null,\"key\":\"menu_21940_v11\",\"type\":1},{\"name\":\"资产状态\",\"url\":null,\"children\":[{\"name\":\"资产健康\",\"url\":\"/dataBoard/report?id=1195&menu_22380_v11\",\"children\":[],\"icon\":null,\"key\":\"menu_22380_v11\",\"type\":1},{\"name\":\"日报\",\"url\":\"/dataBoard/report?id=740&menu_22390_v11\",\"children\":[],\"icon\":null,\"key\":\"menu_22390_v11\",\"type\":1},{\"name\":\"单车状态树V6\",\"url\":\"/dataBoard/report?id=1190&report_1190\",\"children\":[],\"icon\":null,\"key\":\"report_1190\",\"type\":1},{\"name\":\"单车故障全链路宽表\",\"url\":\"/dataBoard/report?id=1188&report_1188\",\"children\":[],\"icon\":null,\"key\":\"report_1188\",\"type\":1},{\"name\":\"故障扫码至故障触达指标看板\",\"url\":\"/dataBoard/tableIndex?tableId=10890&menu_scan_to_reach\",\"children\":[],\"icon\":null,\"key\":\"menu_scan_to_reach\",\"type\":1}],\"icon\":null,\"key\":\"menu_21950_v11\",\"type\":1},{\"name\":\"库外运营\",\"url\":null,\"children\":[{\"name\":\"运营日报\",\"url\":null,\"children\":[{\"name\":\"运营日报\",\"url\":\"/dataBoard/report?id=269&menu_22930_v11\",\"children\":[],\"icon\":null,\"key\":\"menu_22930_v11\",\"type\":1},{\"name\":\"美团单车日报\",\"url\":\"/dataBoard/report?id=555&menu_22950_v11\",\"children\":[],\"icon\":null,\"key\":\"menu_22950_v11\",\"type\":1},{\"name\":\"Airlock运营日报\",\"url\":\"/dataBoard/report?id=1042&menu_Airlockdaily\",\"children\":[],\"icon\":null,\"key\":\"menu_Airlockdaily\",\"type\":1}],\"icon\":null,\"key\":\"menu_22410_v11\",\"type\":1},{\"name\":\"运力管理\",\"url\":null,\"children\":[{\"name\":\"干预围栏\",\"url\":\"/dataBoard/report?id=585&menu_22980_v11\",\"children\":[],\"icon\":null,\"key\":\"menu_22980_v11\",\"type\":1},{\"name\":\"干预详情表\",\"url\":\"/dataBoard/report?id=1212&menu_xiangqing\",\"children\":[],\"icon\":null,\"key\":\"menu_xiangqing\",\"type\":1},{\"name\":\"路面维修干预指标\",\"url\":\"/dataBoard/report?id=1228&menu_gy\",\"children\":[],\"icon\":null,\"key\":\"menu_gy\",\"type\":1}],\"icon\":null,\"key\":\"menu_22420_v11\",\"type\":1},{\"name\":\"运营武器\",\"url\":null,\"children\":[{\"name\":\"运营效率\",\"url\":\"/dataBoard/report?id=848&menu_23105_v11\",\"children\":[],\"icon\":null,\"key\":\"menu_23105_v11\",\"type\":1},{\"name\":\"运营全链路时效\",\"url\":\"/dataBoard/report?id=758&menu_23040_v11\",\"children\":[],\"icon\":null,\"key\":\"menu_23040_v11\",\"type\":1},{\"name\":\"网格化报表\",\"url\":\"/dataBoard/report?id=679&menu_23050_v11\",\"children\":[],\"icon\":null,\"key\":\"menu_23050_v11\",\"type\":1},{\"name\":\"围栏工具\",\"url\":\"/dataBoard/report?id=728&menu_23060_v11\",\"children\":[],\"icon\":null,\"key\":\"menu_23060_v11\",\"type\":1},{\"name\":\"B端宽表\",\"url\":\"/dataBoard/tableIndex?tableId=5302&menu_23070_v11\",\"children\":[],\"icon\":null,\"key\":\"menu_23070_v11\",\"type\":1},{\"name\":\"政府扣车\",\"url\":\"/dataBoard/report?id=872&apollo_vtow_menu_23085_v11\",\"children\":[],\"icon\":null,\"key\":\"apollo_vtow_menu_23085_v11\",\"type\":1},{\"name\":\"故障车导出工具v5\",\"url\":\"/dataBoard/tableIndex?tableId=4852&menu_23080_v11\",\"children\":[],\"icon\":null,\"key\":\"menu_23080_v11\",\"type\":1},{\"name\":\"异常移动资产监控\",\"url\":\"/dataBoard/report?id=720&menu_23090_v11\",\"children\":[],\"icon\":null,\"key\":\"menu_23090_v11\",\"type\":1}],\"icon\":null,\"key\":\"menu_22440_v11\",\"type\":1}],\"icon\":null,\"key\":\"menu_21960_v11\",\"type\":1},{\"name\":\"库内管理\",\"url\":null,\"children\":[{\"name\":\"库内维修\",\"url\":null,\"children\":[{\"name\":\"维修日报\",\"url\":null,\"children\":[{\"name\":\"维修日报\",\"url\":\"/dataBoard/report?id=1011&api_weixiuribaobaobiao\",\"children\":[],\"icon\":null,\"key\":\"api_weixiuribaobaobiao\",\"type\":1}],\"icon\":null,\"key\":\"menu_weixiuribao\",\"type\":1},{\"name\":\"库内维修\",\"url\":null,\"children\":[{\"name\":\"维修产能\",\"url\":\"/dataBoard/report?id=1012&api_weixiuchanneng\",\"children\":[],\"icon\":null,\"key\":\"api_weixiuchanneng\",\"type\":1},{\"name\":\"维修质量效率\",\"url\":\"/dataBoard/report?id=1013&api_weixiuzhiliangxiaolv\",\"children\":[],\"icon\":null,\"key\":\"api_weixiuzhiliangxiaolv\",\"type\":1}],\"icon\":null,\"key\":\"api_kuneiweixiufenlei\",\"type\":1},{\"name\":\"路面维修\",\"url\":null,\"children\":[{\"name\":\"路面维修产能\",\"url\":\"/dataBoard/report?id=1014&api_lumianweixiuchanneng\",\"children\":[],\"icon\":null,\"key\":\"api_lumianweixiuchanneng\",\"type\":1},{\"name\":\"路面维修效率\",\"url\":\"/dataBoard/report?id=1015&api_lumianweixiuxiaolv\",\"children\":[],\"icon\":null,\"key\":\"api_lumianweixiuxiaolv\",\"type\":1},{\"name\":\"故障车\",\"url\":\"/dataBoard/report?id=1016&api_guzhangche\",\"children\":[],\"icon\":null,\"key\":\"api_guzhangche\",\"type\":1},{\"name\":\"骑行效果\",\"url\":\"/dataBoard/report?id=1121&api_qixingxiaoguo\",\"children\":[],\"icon\":null,\"key\":\"api_qixingxiaoguo\",\"type\":1}],\"icon\":null,\"key\":\"api_lumianweixiu\",\"type\":1},{\"name\":\"配件管理\",\"url\":null,\"children\":[{\"name\":\"配件耗用\",\"url\":\"/dataBoard/report?id=1017&api_peijianhaoyong\",\"children\":[],\"icon\":null,\"key\":\"api_peijianhaoyong\",\"type\":1},{\"name\":\"库存周转\",\"url\":\"/dataBoard/report?id=1018&api_kucunzhouzhuan\",\"children\":[],\"icon\":null,\"key\":\"api_kucunzhouzhuan\",\"type\":1},{\"name\":\"配件缺货率\",\"url\":\"/dataBoard/report?id=1019&api_peijianquehuolv\",\"children\":[],\"icon\":null,\"key\":\"api_peijianquehuolv\",\"type\":1}],\"icon\":null,\"key\":\"api_peijianguanli\",\"type\":1},{\"name\":\"车辆资产\",\"url\":null,\"children\":[{\"name\":\"日常报废管理看板\",\"url\":\"/dataBoard/report?id=1020&api_richangbaofeiguanlikanban\",\"children\":[],\"icon\":null,\"key\":\"api_richangbaofeiguanlikanban\",\"type\":1},{\"name\":\"集中报废\",\"url\":\"/dataBoard/report?id=1021&api_jizhongbaofei\",\"children\":[],\"icon\":null,\"key\":\"api_jizhongbaofei\",\"type\":1},{\"name\":\"整车盘点\",\"url\":\"/dataBoard/report?id=1022&api_zhengchepandian\",\"children\":[],\"icon\":null,\"key\":\"api_zhengchepandian\",\"type\":1}],\"icon\":null,\"key\":\"api_cheliangzichan\",\"type\":1},{\"name\":\"人员管理\",\"url\":null,\"children\":[{\"name\":\"人员效率\",\"url\":\"/dataBoard/report?id=488&api_renyuanxiaolvbaobiao\",\"children\":[],\"icon\":null,\"key\":\"api_renyuanxiaolvbaobiao\",\"type\":1},{\"name\":\"资产管理人员指标看板\",\"url\":\"/dataBoard/report?id=1152&api_zcglryzbkb\",\"children\":[],\"icon\":null,\"key\":\"api_zcglryzbkb\",\"type\":1}],\"icon\":null,\"key\":\"api_renyuanguanli\",\"type\":1}],\"icon\":null,\"key\":\"menu_22450_v11\",\"type\":1},{\"name\":\"质量管理\",\"url\":null,\"children\":[{\"name\":\"DOA\",\"url\":\"/dataBoard/report?id=1070&api_bikeDOA\",\"children\":[],\"icon\":null,\"key\":\"api_bikeDOA\",\"type\":1},{\"name\":\"新增故障\",\"url\":\"/dataBoard/report?id=1068&api_xzgz\",\"children\":[],\"icon\":null,\"key\":\"api_xzgz\",\"type\":1},{\"name\":\"车质量\",\"url\":null,\"children\":[{\"name\":\"车型维修率\",\"url\":\"/dataBoard/report?id=1093&api_chexingweixiulv\",\"children\":[],\"icon\":null,\"key\":\"api_chexingweixiulv\",\"type\":1},{\"name\":\"车型换件率\",\"url\":\"/dataBoard/report?id=1094&api_车型换件率\",\"children\":[],\"icon\":null,\"key\":\"api_车型换件率\",\"type\":1}],\"icon\":null,\"key\":\"api_chezhiliang\",\"type\":1},{\"name\":\"零件质量\",\"url\":\"/dataBoard/report?id=1095&api_lingjianzhiliang\",\"children\":[],\"icon\":null,\"key\":\"api_lingjianzhiliang\",\"type\":1}],\"icon\":null,\"key\":\"menu_22460_v11\",\"type\":1},{\"name\":\"供应链备件管理\",\"url\":null,\"children\":[{\"name\":\"库存周转\",\"url\":\"/dataBoard/report?id=1127&api_stgor\",\"children\":[],\"icon\":null,\"key\":\"api_stgor\",\"type\":1}],\"icon\":null,\"key\":\"menu_supplychain\",\"type\":1}],\"icon\":null,\"key\":\"menu_21970_v11\",\"type\":1},{\"name\":\"用户增长\",\"url\":null,\"children\":[{\"name\":\"骑行用户留存\",\"url\":\"/dataBoard/report?id=683&menu_22490_v11\",\"children\":[],\"icon\":null,\"key\":\"menu_22490_v11\",\"type\":1},{\"name\":\"应用统计分析\",\"url\":null,\"children\":[{\"name\":\"单车业务线DAU\",\"url\":\"/dataBoard/tableIndex?tableId=10008&menu_bike_dau\",\"children\":[],\"icon\":null,\"key\":\"menu_bike_dau\",\"type\":1}],\"icon\":null,\"key\":\"menu_22500_v11\",\"type\":1},{\"name\":\"订单分析\",\"url\":null,\"children\":[{\"name\":\"骑行用户核心指标\",\"url\":\"/dataBoard/report?id=568&menu_23220_v11\",\"children\":[],\"icon\":null,\"key\":\"menu_23220_v11\",\"type\":1}],\"icon\":null,\"key\":\"menu_22510_v11\",\"type\":1},{\"name\":\"异业合作分析看板\",\"url\":\"/dataBoard/tableIndex?tableId=8843&menu_8843\",\"children\":[],\"icon\":null,\"key\":\"menu_8843\",\"type\":1},{\"name\":\"套餐出价效果分析\",\"url\":\"/dataBoard/report?id=1176&page_bike_showprice\",\"children\":[],\"icon\":null,\"key\":\"page_bike_showprice\",\"type\":1}],\"icon\":null,\"key\":\"menu_21980_v11\",\"type\":1},{\"name\":\"用户体验\",\"url\":null,\"children\":[{\"name\":\"关锁结费\",\"url\":null,\"children\":[{\"name\":\"关锁成功率\",\"url\":\"/dataBoard/report?id=731&menu_23290_v11\",\"children\":[],\"icon\":null,\"key\":\"menu_23290_v11\",\"type\":1}],\"icon\":null,\"key\":\"menu_22540_v11\",\"type\":1},{\"name\":\"易还相关\",\"url\":null,\"children\":[{\"name\":\"单车首次还车失败热力图\",\"url\":\"/dataBoard/tableIndex?tableId=9114&menu_bike_return_fail_map\",\"children\":[],\"icon\":null,\"key\":\"menu_bike_return_fail_map\",\"type\":1},{\"name\":\"违停判罚明细及策略\",\"url\":\"/dataBoard/report?id=1200&menu_weiting\",\"children\":[],\"icon\":null,\"key\":\"menu_weiting\",\"type\":1},{\"name\":\"停车点覆盖率及围栏明细\",\"url\":\"/dataBoard/report?id=1201&menu_weilan\",\"children\":[],\"icon\":null,\"key\":\"menu_weilan\",\"type\":1},{\"name\":\"违停订单地图\",\"url\":\"/dataBoard/report?id=1202&menu_weiting_2\",\"children\":[],\"icon\":null,\"key\":\"menu_weiting_2\",\"type\":1},{\"name\":\"单车易还核心数据\",\"url\":\"/dataBoard/report?id=1234&menu_yihuanhexin\",\"children\":[],\"icon\":null,\"key\":\"menu_yihuanhexin\",\"type\":1}],\"icon\":null,\"key\":\"menu_user_return_easily\",\"type\":1},{\"name\":\"CPO分析\",\"url\":\"/dataBoard/report?id=1192&page_bikecpo\",\"children\":[],\"icon\":null,\"key\":\"page_bikecpo\",\"type\":1}],\"icon\":null,\"key\":\"menu_21990_v11\",\"type\":1},{\"name\":\"收入套餐\",\"url\":null,\"children\":[{\"name\":\"单车收入\",\"url\":\"/dataBoard/report?id=575&menu_22560_v11\",\"children\":[],\"icon\":null,\"key\":\"menu_22560_v11\",\"type\":1},{\"name\":\"套餐复购\",\"url\":\"/dataBoard/report?id=705&menu_22590_v11\",\"children\":[],\"icon\":null,\"key\":\"menu_22590_v11\",\"type\":1}],\"icon\":null,\"key\":\"menu_22000_v11\",\"type\":1},{\"name\":\"指挥系统\",\"url\":null,\"children\":[{\"name\":\"车辆运营\",\"url\":null,\"children\":[{\"name\":\"车辆分布地图（小时切片）\",\"url\":\"/dataBoard/tableIndex?tableId=8276&api_bike_map\",\"children\":[],\"icon\":null,\"key\":\"api_bike_map\",\"type\":1},{\"name\":\"车辆分布地图（实时）\",\"url\":\"/dataBoard/tableIndex?tableId=9093&api_bike_map_rt\",\"children\":[],\"icon\":null,\"key\":\"api_bike_map_rt\",\"type\":1},{\"name\":\"小时区域车辆流动情况\",\"url\":\"/dataBoard/tableIndex?tableId=8450&api_bike_flow\",\"children\":[],\"icon\":null,\"key\":\"api_bike_flow\",\"type\":1},{\"name\":\"每日区域车辆流动情况\",\"url\":\"/dataBoard/tableIndex?tableId=8500&api_bike_flow_day\",\"children\":[],\"icon\":null,\"key\":\"api_bike_flow_day\",\"type\":1},{\"name\":\"街道运力\",\"url\":\"/dataBoard/report?id=1153&api_transport\",\"children\":[],\"icon\":null,\"key\":\"api_transport\",\"type\":1}],\"icon\":null,\"key\":\"api_bike\",\"type\":1},{\"name\":\"点位管理\",\"url\":null,\"children\":[{\"name\":\"1023点位看板\",\"url\":\"/dataBoard/report?id=1118&api_1023\",\"children\":[],\"icon\":null,\"key\":\"api_1023\",\"type\":1},{\"name\":\"中转点进销存\",\"url\":\"/dataBoard/report?id=1043&api_tempport\",\"children\":[],\"icon\":null,\"key\":\"api_tempport\",\"type\":1},{\"name\":\"点位经营看板\",\"url\":\"/dataBoard/report?id=1154&api_dianweijignying_v1\",\"children\":[],\"icon\":null,\"key\":\"api_dianweijignying_v1\",\"type\":1},{\"name\":\"走线明细\",\"url\":\"/dataBoard/tableIndex?tableId=9115&api_route_detail\",\"children\":[],\"icon\":null,\"key\":\"api_route_detail\",\"type\":1},{\"name\":\"装卸收益\",\"url\":\"/dataBoard/report?id=1221&api_dispatch_rev\",\"children\":[],\"icon\":null,\"key\":\"api_dispatch_rev\",\"type\":1},{\"name\":\"单车点位覆盖\",\"url\":\"/dataBoard/tableIndex?tableId=7345&menu_dctcdfgl\",\"children\":[],\"icon\":null,\"key\":\"menu_dctcdfgl\",\"type\":1},{\"name\":\"路网主动清淤\",\"url\":\"/dataBoard/report?id=1213&menu_luwangzhudongqingyu\",\"children\":[],\"icon\":null,\"key\":\"menu_luwangzhudongqingyu\",\"type\":1}],\"icon\":null,\"key\":\"api_fence_ctrl\",\"type\":1},{\"name\":\"人员管理\",\"url\":null,\"children\":[{\"name\":\"T0人员工作量\",\"url\":\"/dataBoard/tableIndex?tableId=8826&api_t0_workload\",\"children\":[],\"icon\":null,\"key\":\"api_t0_workload\",\"type\":1},{\"name\":\"人效产能监控\",\"url\":\"/dataBoard/report?id=1172&api_T0\",\"children\":[],\"icon\":null,\"key\":\"api_T0\",\"type\":1},{\"name\":\"调度明细（兼容版）\",\"url\":\"/dataBoard/report?id=1168&api_dispatch_detail\",\"children\":[],\"icon\":null,\"key\":\"api_dispatch_detail\",\"type\":1},{\"name\":\"库外一体化工作明细\",\"url\":\"/dataBoard/report?id=1169&api_kuwaigongzuomingxi\",\"children\":[],\"icon\":null,\"key\":\"api_kuwaigongzuomingxi\",\"type\":1},{\"name\":\"产能复苏\",\"url\":\"/dataBoard/tableIndex?tableId=8932&api_capacity\",\"children\":[],\"icon\":null,\"key\":\"api_capacity\",\"type\":1},{\"name\":\"无车人员作业情况-Region\",\"url\":\"/dataBoard/report?id=1242&api_wuche_region\",\"children\":[],\"icon\":null,\"key\":\"api_wuche_region\",\"type\":1},{\"name\":\"无车人员作业情况-T0\",\"url\":\"/dataBoard/report?id=1243&api_wuche_T0\",\"children\":[],\"icon\":null,\"key\":\"api_wuche_T0\",\"type\":1}],\"icon\":null,\"key\":\"menu_renyuanguanli\",\"type\":1},{\"name\":\"主题宽表\",\"url\":null,\"children\":[{\"name\":\"Region\",\"url\":\"/dataBoard/tableIndex?tableId=9268&api_topic_region\",\"children\":[],\"icon\":null,\"key\":\"api_topic_region\",\"type\":1},{\"name\":\"921网格\",\"url\":\"/dataBoard/tableIndex?tableId=9269&api_topic_921\",\"children\":[],\"icon\":null,\"key\":\"api_topic_921\",\"type\":1},{\"name\":\"923街道\",\"url\":\"/dataBoard/tableIndex?tableId=9270&api_topic_923\",\"children\":[],\"icon\":null,\"key\":\"api_topic_923\",\"type\":1},{\"name\":\"通用计件\",\"url\":\"/dataBoard/report?id=970&api_tongyongjijian\",\"children\":[],\"icon\":null,\"key\":\"api_tongyongjijian\",\"type\":1}],\"icon\":null,\"key\":\"menu_topic\",\"type\":1},{\"name\":\"及时响应\",\"url\":null,\"children\":[{\"name\":\"清淤摆车（汇总+明细）\",\"url\":\"/dataBoard/report?id=1164&menu_7621\",\"children\":[],\"icon\":null,\"key\":\"menu_7621\",\"type\":1},{\"name\":\"作业成本和时效分析\",\"url\":\"/dataBoard/report?id=1184&menu_8628\",\"children\":[],\"icon\":null,\"key\":\"menu_8628\",\"type\":1},{\"name\":\"工单明细\",\"url\":\"/dataBoard/tableIndex?tableId=7821&menu_7821\",\"children\":[],\"icon\":null,\"key\":\"menu_7821\",\"type\":1}],\"icon\":null,\"key\":\"menu_8276\",\"type\":1}],\"icon\":null,\"key\":\"api_command_center_1117\",\"type\":1},{\"name\":\"校园经营\",\"url\":null,\"children\":[{\"name\":\"校园输入输出看板\",\"url\":\"/dataBoard/ioboard?id=19&menu_ioboard19_v11\",\"children\":[],\"icon\":null,\"key\":\"menu_ioboard19_v11\",\"type\":1},{\"name\":\"校园区域经营分析看板\",\"url\":\"/dataBoard/analyse/operate/area?tlBoardId=4&menu_tlboard4_v11\",\"children\":[],\"icon\":null,\"key\":\"menu_tlboard4_v11\",\"type\":1},{\"name\":\"校园实时车辆数\",\"url\":\"/dataBoard/report?id=1245&menu_xysscheliang\",\"children\":[],\"icon\":null,\"key\":\"menu_xysscheliang\",\"type\":1},{\"name\":\"校园运营看板\",\"url\":\"/dataBoard/report?id=1219&menu_yunying\",\"children\":[],\"icon\":null,\"key\":\"menu_yunying\",\"type\":1},{\"name\":\"校园指标\",\"url\":\"/dataBoard/report?id=1167&menu_xiaoyuanzhibiao\",\"children\":[],\"icon\":null,\"key\":\"menu_xiaoyuanzhibiao\",\"type\":1},{\"name\":\"O端城市运营\",\"url\":null,\"children\":[{\"name\":\"校园调度点位报表\",\"url\":\"/dataBoard/report?id=1241&menu_xydiaodudianw\",\"children\":[],\"icon\":null,\"key\":\"menu_xydiaodudianw\",\"type\":1},{\"name\":\"校园车辆调度明细表\",\"url\":\"/dataBoard/report?id=1163&menu_xyclddmxb\",\"children\":[],\"icon\":null,\"key\":\"menu_xyclddmxb\",\"type\":1},{\"name\":\"校园车辆调度明细表—按调出点\",\"url\":\"/dataBoard/tableIndex?tableId=9226&menu_xyclddmxdcd\",\"children\":[],\"icon\":null,\"key\":\"menu_xyclddmxdcd\",\"type\":1}],\"icon\":null,\"key\":\"menu_odcsyy\",\"type\":1},{\"name\":\"C端用户运营\",\"url\":\"/dataBoard/report?id=1158&menu_cduanyonghu\",\"children\":[],\"icon\":null,\"key\":\"menu_cduanyonghu\",\"type\":1},{\"name\":\"绩效管理\",\"url\":null,\"children\":[{\"name\":\"校园拓展绩效看板\",\"url\":\"/dataBoard/campus/expand\",\"children\":[],\"icon\":null,\"key\":\"menu_campus_performance_expand_2_v11\",\"type\":1},{\"name\":\"校园运营绩效看板\",\"url\":\"/dataBoard/campus/operate\",\"children\":[],\"icon\":null,\"key\":\"menu_campus_performance_operate_2_v11\",\"type\":1}],\"icon\":null,\"key\":\"menu_performance_manage_v11\",\"type\":1}],\"icon\":null,\"key\":\"menu_xyjy\",\"type\":1},{\"name\":\"绩效管理\",\"url\":null,\"children\":[{\"name\":\"校园拓展绩效看板\",\"url\":\"/dataBoard/campus/expand\",\"children\":[],\"icon\":null,\"key\":\"menu_campus_performance_expand_v11\",\"type\":1},{\"name\":\"校园运营绩效看板\",\"url\":\"/dataBoard/campus/operate\",\"children\":[],\"icon\":null,\"key\":\"menu_campus_performance_operate_v11\",\"type\":1}],\"icon\":null,\"key\":\"menu_jxgl\",\"type\":1},{\"name\":\"市场拓展\",\"url\":null,\"children\":[{\"name\":\"市场情报\",\"url\":null,\"children\":[{\"name\":\"单车YY停车点\",\"url\":\"/dataBoard/tableIndex?tableId=9164&menu_dancheYYtcd\",\"children\":[],\"icon\":null,\"key\":\"menu_dancheYYtcd\",\"type\":1}],\"icon\":null,\"key\":\"menu_shichangqingbao\",\"type\":1}],\"icon\":null,\"key\":\"menu_shichangtz\",\"type\":1},{\"name\":\"治理者满意\",\"url\":null,\"children\":[{\"name\":\"治理者关系管理\",\"url\":null,\"children\":[{\"name\":\"基层治理者看板\",\"url\":\"/dataBoard/report?id=1229&menu_jicengzhilizhekabb\",\"children\":[],\"icon\":null,\"key\":\"menu_jicengzhilizhekabb\",\"type\":1}],\"icon\":null,\"key\":\"menu_zhilizhezd\",\"type\":1},{\"name\":\"及时响应\",\"url\":null,\"children\":[{\"name\":\"投诉管理\",\"url\":null,\"children\":[{\"name\":\"及时响应明细记录\",\"url\":\"/dataBoard/report?id=1117&menu_jixxiangymingxjil\",\"children\":[],\"icon\":null,\"key\":\"menu_jixxiangymingxjil\",\"type\":1},{\"name\":\"GO联动执行效果\",\"url\":\"/dataBoard/report?id=1216&menu_GOLIANXD\",\"children\":[],\"icon\":null,\"key\":\"menu_GOLIANXD\",\"type\":1}],\"icon\":null,\"key\":\"menu_tousuguanli\",\"type\":1},{\"name\":\"作业管理\",\"url\":null,\"children\":[{\"name\":\"及时响应-秩序清淤汇总(新版)\",\"url\":\"/dataBoard/report?id=1164&menu_jisxiangyzxqyhz\",\"children\":[],\"icon\":null,\"key\":\"menu_jisxiangyzxqyhz\",\"type\":1},{\"name\":\"及时响应-作业成本和时效分析\",\"url\":\"/dataBoard/report?id=1184&menu_zycbsxfx\",\"children\":[],\"icon\":null,\"key\":\"menu_zycbsxfx\",\"type\":1},{\"name\":\"工单明细\",\"url\":\"/dataBoard/tableIndex?tableId=7821&menu_gdmingxi\",\"children\":[],\"icon\":null,\"key\":\"menu_gdmingxi\",\"type\":1}],\"icon\":null,\"key\":\"menu_zuoyeguanli\",\"type\":1}],\"icon\":null,\"key\":\"menu_zhiliztsgl\",\"type\":1},{\"name\":\"配额制胜\",\"url\":null,\"children\":[{\"name\":\"单车配额落地\",\"url\":\"/dataBoard/report?id=1224&api_danchepeieluodi\",\"children\":[],\"icon\":null,\"key\":\"api_danchepeieluodi\",\"type\":1}],\"icon\":null,\"key\":\"menu_peiezhis\",\"type\":1},{\"name\":\"单车违停分析（toG）\",\"url\":\"/dataBoard/report?id=1149&menu_dancheweit\",\"children\":[],\"icon\":null,\"key\":\"menu_dancheweit\",\"type\":1}],\"icon\":null,\"key\":\"menu_zlzmyi\",\"type\":1}],\"icon\":null,\"key\":\"menu_21830_v11\",\"type\":1},{\"name\":\"电单车\",\"url\":null,\"children\":[{\"name\":\"全局决策\",\"url\":null,\"children\":[{\"name\":\"电单车日报\",\"url\":\"/dataBoard/report?id=594&0c3787fe-06ae-4cd2-a4f2-9cdf559dcec2_v11\",\"children\":[],\"icon\":null,\"key\":\"0c3787fe-06ae-4cd2-a4f2-9cdf559dcec2_v11\",\"type\":1},{\"name\":\"全局分析\",\"url\":\"/dataBoard/report?id=483&4f6029be-49c9-40fa-8420-a0050882c471_v11\",\"children\":[],\"icon\":null,\"key\":\"4f6029be-49c9-40fa-8420-a0050882c471_v11\",\"type\":1},{\"name\":\"财务分析\",\"url\":null,\"children\":[{\"name\":\"电单车收入\",\"url\":\"/dataBoard/report?id=766&2f9c0b98-13fd-497b-a63e-a46e170f9c6f_v11\",\"children\":[],\"icon\":null,\"key\":\"2f9c0b98-13fd-497b-a63e-a46e170f9c6f_v11\",\"type\":1}],\"icon\":null,\"key\":\"703926c3-8eae-4c19-a236-40fcfeb5b933_v11\",\"type\":1}],\"icon\":null,\"key\":\"cd4061a8-d1f5-4135-b020-2946a43b620c_v11\",\"type\":1},{\"name\":\"作战地图\",\"url\":null,\"children\":[{\"name\":\"管理看板\",\"url\":\"/dataBoard/tableIndex?tableId=6880&ed8606a2-4afe-423c-964f-187cb030e562_v11\",\"children\":[],\"icon\":null,\"key\":\"ed8606a2-4afe-423c-964f-187cb030e562_v11\",\"type\":1},{\"name\":\"开城拓展\",\"url\":null,\"children\":[{\"name\":\"开城流程报表\",\"url\":\"/dataBoard/report?id=912&menu_cityProcessReport_v11\",\"children\":[],\"icon\":null,\"key\":\"menu_cityProcessReport_v11\",\"type\":1}],\"icon\":null,\"key\":\"menu_cityExpand_v11\",\"type\":1}],\"icon\":null,\"key\":\"7b9a0a26-cdce-43ce-95c1-7b58055883d4_v11\",\"type\":1},{\"name\":\"实时大盘\",\"url\":null,\"children\":[{\"name\":\"订单实况\",\"url\":null,\"children\":[{\"name\":\"实时订单\",\"url\":\"/dataBoard/report?id=1062&3eae9b7f-58f0-4669-88fb-50a41ce49ede_v11\",\"children\":[],\"icon\":null,\"key\":\"3eae9b7f-58f0-4669-88fb-50a41ce49ede_v11\",\"type\":1},{\"name\":\"实时订单_分车型\",\"url\":\"/dataBoard/tableIndex?tableId=7018&7080bd83-144a-4b63-890c-331dfb8688fd_v11\",\"children\":[],\"icon\":null,\"key\":\"7080bd83-144a-4b63-890c-331dfb8688fd_v11\",\"type\":1},{\"name\":\"实时订单地图\",\"url\":\"/dataBoard/report?id=1125&menu_shishiquanliangdingdan\",\"children\":[],\"icon\":null,\"key\":\"menu_shishiquanliangdingdan\",\"type\":1},{\"name\":\"实时违停订单地图\",\"url\":\"/dataBoard/report?id=1030&menu_shishiweitingdingdan\",\"children\":[],\"icon\":null,\"key\":\"menu_shishiweitingdingdan\",\"type\":1}],\"icon\":null,\"key\":\"3cbbf1ac-94c0-45f2-9984-1309b6ad1401_v11\",\"type\":1},{\"name\":\"车辆实况\",\"url\":null,\"children\":[{\"name\":\"异常移动\",\"url\":\"/dataBoard/report?id=806&8904782a-b390-4ac5-a74e-874d31ff3667_v11\",\"children\":[],\"icon\":null,\"key\":\"8904782a-b390-4ac5-a74e-874d31ff3667_v11\",\"type\":1}],\"icon\":null,\"key\":\"c3c769f2-d466-43be-80d7-cd445a14b7b2_v11\",\"type\":1},{\"name\":\"停车点实况\",\"url\":\"/dataBoard/report?id=984&menu_dingchedianshikuang\",\"children\":[],\"icon\":null,\"key\":\"menu_dingchedianshikuang\",\"type\":1},{\"name\":\"换电实况\",\"url\":\"/dataBoard/report?id=1142&menu_battery_replace\",\"children\":[],\"icon\":null,\"key\":\"menu_battery_replace\",\"type\":1},{\"name\":\"调度完成实况\",\"url\":\"/dataBoard/report?id=1196&api_dispatch_rt\",\"children\":[],\"icon\":null,\"key\":\"api_dispatch_rt\",\"type\":1}],\"icon\":null,\"key\":\"4620432d-dcdf-4d21-a987-390b4cdb718e_v11\",\"type\":1},{\"name\":\"增长体验\",\"url\":null,\"children\":[{\"name\":\"用户增长\",\"url\":null,\"children\":[{\"name\":\"用户增长\",\"url\":\"/dataBoard/report?id=619&1e420f13-e138-4884-b884-cb244ddd5b42_v11\",\"children\":[],\"icon\":null,\"key\":\"1e420f13-e138-4884-b884-cb244ddd5b42_v11\",\"type\":1},{\"name\":\"uo运营日报\",\"url\":\"/dataBoard/report?id=1156&menu_Operations_day\",\"children\":[],\"icon\":null,\"key\":\"menu_Operations_day\",\"type\":1},{\"name\":\"套餐出价效果分析\",\"url\":\"/dataBoard/report?id=1177&page_spock_showprice\",\"children\":[],\"icon\":null,\"key\":\"page_spock_showprice\",\"type\":1},{\"name\":\"电单车业务线DAU\",\"url\":\"/dataBoard/tableIndex?tableId=10033&menu_spock_dau\",\"children\":[],\"icon\":null,\"key\":\"menu_spock_dau\",\"type\":1}],\"icon\":null,\"key\":\"93e5d401-4ebc-4dba-8f97-48e6cc83e6b1_v11\",\"type\":1},{\"name\":\"用户体验\",\"url\":null,\"children\":[{\"name\":\"有序易还\",\"url\":\"/dataBoard/report?id=1032&menu_yihuan\",\"children\":[],\"icon\":null,\"key\":\"menu_yihuan\",\"type\":1},{\"name\":\"首次还车失败地图\",\"url\":\"/dataBoard/report?id=1207&menu_shouci\",\"children\":[],\"icon\":null,\"key\":\"menu_shouci\",\"type\":1},{\"name\":\"易得\",\"url\":\"/dataBoard/report?id=781&aa52b106-2f6a-44b5-9f89-dede3cb331f6_v11\",\"children\":[],\"icon\":null,\"key\":\"aa52b106-2f6a-44b5-9f89-dede3cb331f6_v11\",\"type\":1},{\"name\":\"体验漏斗\",\"url\":\"/dataBoard/report?id=782&b59ea3d1-458d-40cf-88a7-c397e3ada3a5_v11\",\"children\":[],\"icon\":null,\"key\":\"b59ea3d1-458d-40cf-88a7-c397e3ada3a5_v11\",\"type\":1},{\"name\":\"扫码低电\",\"url\":\"/dataBoard/tableIndex?tableId=6962&ebc56ad5-9a3e-4f5f-9b16-5660f960c355_v11\",\"children\":[],\"icon\":null,\"key\":\"ebc56ad5-9a3e-4f5f-9b16-5660f960c355_v11\",\"type\":1},{\"name\":\"头盔体验\",\"url\":\"/dataBoard/report?id=1115&menu_toukuitiyan\",\"children\":[],\"icon\":null,\"key\":\"menu_toukuitiyan\",\"type\":1},{\"name\":\"CPO分析\",\"url\":\"/dataBoard/report?id=1193&page_spockcpo\",\"children\":[],\"icon\":null,\"key\":\"page_spockcpo\",\"type\":1}],\"icon\":null,\"key\":\"846d3514-0433-4450-8d99-12eebeee95de_v11\",\"type\":1},{\"name\":\"骑行套餐\",\"url\":null,\"children\":[{\"name\":\"骑行券分析\",\"url\":\"/dataBoard/report?id=485&6034bc8d-67f1-4b85-a721-fe5e7386d993_v11\",\"children\":[],\"icon\":null,\"key\":\"6034bc8d-67f1-4b85-a721-fe5e7386d993_v11\",\"type\":1},{\"name\":\"月卡分析\",\"url\":\"/dataBoard/report?id=617&6269f828-02dc-4cdf-82a4-72e78abc1940_v11\",\"children\":[],\"icon\":null,\"key\":\"6269f828-02dc-4cdf-82a4-72e78abc1940_v11\",\"type\":1},{\"name\":\"次卡分析\",\"url\":\"/dataBoard/report?id=767&8fd96f91-8301-4a30-9aec-18e2526d3498_v11\",\"children\":[],\"icon\":null,\"key\":\"8fd96f91-8301-4a30-9aec-18e2526d3498_v11\",\"type\":1},{\"name\":\"实时购卡量\",\"url\":\"/dataBoard/report?id=1203&page_card_order_rt\",\"children\":[],\"icon\":null,\"key\":\"page_card_order_rt\",\"type\":1}],\"icon\":null,\"key\":\"c0b16cc1-8bc5-4b8c-8b5e-c055ba30517a_v11\",\"type\":1},{\"name\":\"营销活动\",\"url\":null,\"children\":[{\"name\":\"发券活动\",\"url\":\"/dataBoard/report?id=1087&api_couponactivity\",\"children\":[],\"icon\":null,\"key\":\"api_couponactivity\",\"type\":1},{\"name\":\"春雷战役活动\",\"url\":\"/dataBoard/report?id=1166&page_chunleiactivity\",\"children\":[],\"icon\":null,\"key\":\"page_chunleiactivity\",\"type\":1},{\"name\":\"营销策略分析\",\"url\":\"/dataBoard/report?id=1171&page_strategyanalysis\",\"children\":[],\"icon\":null,\"key\":\"page_strategyanalysis\",\"type\":1}],\"icon\":null,\"key\":\"menu_totalmenu\",\"type\":1}],\"icon\":null,\"key\":\"a8e84095-04f3-4e33-bc12-25815ab5bc58_v11\",\"type\":1},{\"name\":\"资产健康\",\"url\":null,\"children\":[{\"name\":\"电单车\",\"url\":null,\"children\":[{\"name\":\"状态树\",\"url\":\"/dataBoard/report?id=826&a6469ef7-48ae-4d0a-8451-ea9bf160e622_v11\",\"children\":[],\"icon\":null,\"key\":\"a6469ef7-48ae-4d0a-8451-ea9bf160e622_v11\",\"type\":1},{\"name\":\"资产健康分析\",\"url\":\"/dataBoard/report?id=638&b57f4c40-abed-481d-a32b-897cd20e91d4_v11\",\"children\":[],\"icon\":null,\"key\":\"b57f4c40-abed-481d-a32b-897cd20e91d4_v11\",\"type\":1},{\"name\":\"资产健康_分车型\",\"url\":\"/dataBoard/tableIndex?tableId=9237&be9ffa56-9a25-47a1-bb79-cb008528a8a4_v11\",\"children\":[],\"icon\":null,\"key\":\"be9ffa56-9a25-47a1-bb79-cb008528a8a4_v11\",\"type\":1},{\"name\":\"丢失态\",\"url\":\"/dataBoard/report?id=914&menu_lost_state_v11\",\"children\":[],\"icon\":null,\"key\":\"menu_lost_state_v11\",\"type\":1},{\"name\":\"新增故障率\",\"url\":\"/dataBoard/report?id=1050&menu_newfault\",\"children\":[],\"icon\":null,\"key\":\"menu_newfault\",\"type\":1},{\"name\":\"电单上牌项目数据看板\",\"url\":\"/dataBoard/report?id=1138&menu_license_plate\",\"children\":[],\"icon\":null,\"key\":\"menu_license_plate\",\"type\":1}],\"icon\":null,\"key\":\"bdd0c205-0cd0-428e-ab71-a90261b2a910_v11\",\"type\":1},{\"name\":\"电池\",\"url\":null,\"children\":[{\"name\":\"状态树\",\"url\":\"/dataBoard/report?id=1091&menu_zhuangtaishu\",\"children\":[],\"icon\":null,\"key\":\"menu_zhuangtaishu\",\"type\":1},{\"name\":\"电池盘点\",\"url\":\"/dataBoard/report?id=966&page_dianchipandian\",\"children\":[],\"icon\":null,\"key\":\"page_dianchipandian\",\"type\":1}],\"icon\":null,\"key\":\"0198bb63-d131-4496-8827-f638f3b963d6_v11\",\"type\":1},{\"name\":\"扣车分析\",\"url\":null,\"children\":[{\"name\":\"扣车日报\",\"url\":\"/dataBoard/report?id=665&menu_L4_koucheribao\",\"children\":[],\"icon\":null,\"key\":\"menu_L4_koucheribao\",\"type\":1},{\"name\":\"扣车明细\",\"url\":\"/dataBoard/report?id=922&menu_l4_kouchemingxi\",\"children\":[],\"icon\":null,\"key\":\"menu_l4_kouchemingxi\",\"type\":1},{\"name\":\"扣车围栏档案\",\"url\":\"/dataBoard/report?id=935&menu_l4_koucheweilandangan\",\"children\":[],\"icon\":null,\"key\":\"menu_l4_koucheweilandangan\",\"type\":1},{\"name\":\"扣车被扣位置回溯（热力图）\",\"url\":\"/dataBoard/report?id=1174&page_kouchehuisu\",\"children\":[],\"icon\":null,\"key\":\"page_kouchehuisu\",\"type\":1},{\"name\":\"扣车电池异常丢失\",\"url\":\"/dataBoard/report?id=1246&menu_kcyicds\",\"children\":[],\"icon\":null,\"key\":\"menu_kcyicds\",\"type\":1}],\"icon\":null,\"key\":\"menu_l3_kouche\",\"type\":1},{\"name\":\"分布式资产\",\"url\":null,\"children\":[{\"name\":\"分布式数据看板\",\"url\":\"/dataBoard/report?id=1191&page_fenbushishujukanban\",\"children\":[],\"icon\":null,\"key\":\"page_fenbushishujukanban\",\"type\":1}],\"icon\":null,\"key\":\"menu_fenbushi\",\"type\":1}],\"icon\":null,\"key\":\"34ac79df-81e3-418e-ac14-159ce984ee24_v11\",\"type\":1},{\"name\":\"库内管理\",\"url\":null,\"children\":[{\"name\":\"库内维修\",\"url\":null,\"children\":[{\"name\":\"维修日报\",\"url\":\"/dataBoard/report?id=640&1a90c6f6-4cf9-495b-809f-86351b36dd94_v11\",\"children\":[],\"icon\":null,\"key\":\"1a90c6f6-4cf9-495b-809f-86351b36dd94_v11\",\"type\":1},{\"name\":\"维修明细\",\"url\":\"/dataBoard/report?id=795&13099bb3-34c6-42f5-ab52-78f0ded8409c_v11\",\"children\":[],\"icon\":null,\"key\":\"13099bb3-34c6-42f5-ab52-78f0ded8409c_v11\",\"type\":1}],\"icon\":null,\"key\":\"d3323e04-980b-40dd-8c46-7785e6cacb03_v11\",\"type\":1},{\"name\":\"出入库\",\"url\":null,\"children\":[{\"name\":\"电单车\",\"url\":\"/dataBoard/report?id=889&9cac8a46-f8c4-4c52-ae67-1d7a885b72f4_v11\",\"children\":[],\"icon\":null,\"key\":\"9cac8a46-f8c4-4c52-ae67-1d7a885b72f4_v11\",\"type\":1},{\"name\":\"电池实时出入库\",\"url\":\"/dataBoard/report?id=959&page_dianchishishichuruku\",\"children\":[],\"icon\":null,\"key\":\"page_dianchishishichuruku\",\"type\":1}],\"icon\":null,\"key\":\"d8a5130c-ffc4-4c41-bd3a-0ef032b0a0e9_v11\",\"type\":1},{\"name\":\"质量管理\",\"url\":null,\"children\":[{\"name\":\"维修指标看板\",\"url\":\"/dataBoard/report?id=968&menu_weixiuzhibiaokanban\",\"children\":[],\"icon\":null,\"key\":\"menu_weixiuzhibiaokanban\",\"type\":1},{\"name\":\"维修明细看板\",\"url\":\"/dataBoard/report?id=1002&menu_weixiumingxikanban\",\"children\":[],\"icon\":null,\"key\":\"menu_weixiumingxikanban\",\"type\":1},{\"name\":\"新增故障率看板\",\"url\":\"/dataBoard/report?id=1050&api_xinzengguzhanglv\",\"children\":[],\"icon\":null,\"key\":\"api_xinzengguzhanglv\",\"type\":1},{\"name\":\"新增故障率周期看板\",\"url\":\"/dataBoard/report?id=1096&menu_xinzengguzhanglvzhouqikanban\",\"children\":[],\"icon\":null,\"key\":\"menu_xinzengguzhanglvzhouqikanban\",\"type\":1},{\"name\":\"供应链电池返厂\",\"url\":\"/dataBoard/report?id=1205&page_supply_battary_retun\",\"children\":[],\"icon\":null,\"key\":\"page_supply_battary_retun\",\"type\":1}],\"icon\":null,\"key\":\"api_zhiliangguanli\",\"type\":1}],\"icon\":null,\"key\":\"aea2d696-e2c7-445c-94c0-1368efe4047d_v11\",\"type\":1},{\"name\":\"库外运营\",\"url\":null,\"children\":[{\"name\":\"换电\",\"url\":null,\"children\":[{\"name\":\"换电日报\",\"url\":\"/dataBoard/report?id=974&2d1f6368-7fa3-4634-bbe8-720ead71feb0_v11\",\"children\":[],\"icon\":null,\"key\":\"2d1f6368-7fa3-4634-bbe8-720ead71feb0_v11\",\"type\":1},{\"name\":\"换电人效与商管理\",\"url\":\"/dataBoard/report?id=832&f6438fe5-1dd1-4dfc-a51d-de3a2584aa29_v11\",\"children\":[],\"icon\":null,\"key\":\"f6438fe5-1dd1-4dfc-a51d-de3a2584aa29_v11\",\"type\":1},{\"name\":\"城市换电工具\",\"url\":\"/dataBoard/report?id=824&797938b1-4e0a-43c3-9dd2-94145aa802e3_v11\",\"children\":[],\"icon\":null,\"key\":\"797938b1-4e0a-43c3-9dd2-94145aa802e3_v11\",\"type\":1},{\"name\":\"城市换电分析\",\"url\":\"/dataBoard/report?id=822&12806501-a4d8-4421-8f49-f38d0ceab294_v11\",\"children\":[],\"icon\":null,\"key\":\"12806501-a4d8-4421-8f49-f38d0ceab294_v11\",\"type\":1},{\"name\":\"运营专员换电明细\",\"url\":\"/dataBoard/tableIndex?tableId=5175&f375618c-6d0e-481c-b5e8-4311178c6b24_v11\",\"children\":[],\"icon\":null,\"key\":\"f375618c-6d0e-481c-b5e8-4311178c6b24_v11\",\"type\":1},{\"name\":\"换电成本看板\",\"url\":\"/dataBoard/report?id=1215&api_replace_battery_cost\",\"children\":[],\"icon\":null,\"key\":\"api_replace_battery_cost\",\"type\":1}],\"icon\":null,\"key\":\"f1cb689f-99ba-4bc4-8741-e6c9478fe390_v11\",\"type\":1},{\"name\":\"调度\",\"url\":null,\"children\":[{\"name\":\"调度日报\",\"url\":\"/dataBoard/report?id=1023&menu_diaoduribao\",\"children\":[],\"icon\":null,\"key\":\"menu_diaoduribao\",\"type\":1},{\"name\":\"调度宽表\",\"url\":\"/dataBoard/report?id=637&a5b51f1b-1973-4206-955e-a9c816f64b23_v11\",\"children\":[],\"icon\":null,\"key\":\"a5b51f1b-1973-4206-955e-a9c816f64b23_v11\",\"type\":1},{\"name\":\"调度明细\",\"url\":\"/dataBoard/report?id=883&5ef7fec6-cd4d-49a8-8271-4a4e592c8a1f_v11\",\"children\":[],\"icon\":null,\"key\":\"5ef7fec6-cd4d-49a8-8271-4a4e592c8a1f_v11\",\"type\":1},{\"name\":\"调度ROI\",\"url\":\"/dataBoard/report?id=976&menu_diaoduroi\",\"children\":[],\"icon\":null,\"key\":\"menu_diaoduroi\",\"type\":1},{\"name\":\"调度责任田数据看板\",\"url\":\"/dataBoard/report?id=1092&api_diaoduzerentiankanban\",\"children\":[],\"icon\":null,\"key\":\"api_diaoduzerentiankanban\",\"type\":1}],\"icon\":null,\"key\":\"7696ce11-648f-4c0d-81d3-b97598ca99ef_v11\",\"type\":1},{\"name\":\"干预\",\"url\":null,\"children\":[{\"name\":\"干预量\",\"url\":\"/dataBoard/report?id=942&d572f513-48d1-4519-9649-e63fa91fecdc_v11\",\"children\":[],\"icon\":null,\"key\":\"d572f513-48d1-4519-9649-e63fa91fecdc_v11\",\"type\":1},{\"name\":\"干预明细\",\"url\":\"/dataBoard/tableIndex?tableId=6789&2211c8c4-b353-4c48-b8ba-84bb731c1f55_v11\",\"children\":[],\"icon\":null,\"key\":\"2211c8c4-b353-4c48-b8ba-84bb731c1f55_v11\",\"type\":1},{\"name\":\"找摆网格数据监控\",\"url\":\"/dataBoard/report?id=1197&menu_zhaobai\",\"children\":[],\"icon\":null,\"key\":\"menu_zhaobai\",\"type\":1},{\"name\":\"干预后验数据看板\",\"url\":\"/dataBoard/report?id=1194&api_houyan\",\"children\":[],\"icon\":null,\"key\":\"api_houyan\",\"type\":1},{\"name\":\"电单路面维修\",\"url\":\"/dataBoard/report?id=1250&menu_ddlumwx\",\"children\":[],\"icon\":null,\"key\":\"menu_ddlumwx\",\"type\":1}],\"icon\":null,\"key\":\"b7e7ea4a-190a-4553-ad62-da0ba328f1f1_v11\",\"type\":1},{\"name\":\"寻回\",\"url\":null,\"children\":[{\"name\":\"装车\",\"url\":\"/dataBoard/tableIndex?tableId=6765&8f910a5c-71f0-4a2e-8af7-f5234d74b847_v11\",\"children\":[],\"icon\":null,\"key\":\"8f910a5c-71f0-4a2e-8af7-f5234d74b847_v11\",\"type\":1},{\"name\":\"卸车\",\"url\":\"/dataBoard/tableIndex?tableId=6762&5fc5fee3-190d-44d4-aaef-cbdacff25a9a_v11\",\"children\":[],\"icon\":null,\"key\":\"5fc5fee3-190d-44d4-aaef-cbdacff25a9a_v11\",\"type\":1}],\"icon\":null,\"key\":\"c33200bb-dbdd-46a2-9e92-4158114c68c1_v11\",\"type\":1},{\"name\":\"摆车\",\"url\":null,\"children\":[{\"name\":\"摆车工作量\",\"url\":\"/dataBoard/report?id=1123&menu_baichegongzuoliang\",\"children\":[],\"icon\":null,\"key\":\"menu_baichegongzuoliang\",\"type\":1}],\"icon\":null,\"key\":\"menu_baiche\",\"type\":1}],\"icon\":null,\"key\":\"93d350e2-5185-41b2-b651-757c22703220_v11\",\"type\":1},{\"name\":\"供需匹配\",\"url\":null,\"children\":[{\"name\":\"网格化运营\",\"url\":null,\"children\":[{\"name\":\"电单车网格化管理指标宽表\",\"url\":\"/dataBoard/report?id=961&menu_grid_manage_v11\",\"children\":[],\"icon\":null,\"key\":\"menu_grid_manage_v11\",\"type\":1},{\"name\":\"电单车运营街道管理指标宽表\",\"url\":\"/dataBoard/report?id=962&menu_street_manage_v11\",\"children\":[],\"icon\":null,\"key\":\"menu_street_manage_v11\",\"type\":1}],\"icon\":null,\"key\":\"menu_grid_ops_v11\",\"type\":1},{\"name\":\"时空围栏\",\"url\":null,\"children\":[{\"name\":\"围栏网格取数\",\"url\":\"/dataBoard/tableIndex?tableId=6885&d8b33109-ad2f-486e-8a3e-e43285400f59_v11\",\"children\":[],\"icon\":null,\"key\":\"d8b33109-ad2f-486e-8a3e-e43285400f59_v11\",\"type\":1},{\"name\":\"核心围栏看板\",\"url\":\"/dataBoard/report?id=917&menu_l4_hexinweilankanban\",\"children\":[],\"icon\":null,\"key\":\"menu_l4_hexinweilankanban\",\"type\":1},{\"name\":\"运营围栏看板\",\"url\":\"/dataBoard/report?id=943&menu_ops_board_v11\",\"children\":[],\"icon\":null,\"key\":\"menu_ops_board_v11\",\"type\":1},{\"name\":\"电单网格蜂窝可视化\",\"url\":\"/dataBoard/report?id=997&menu_fengwokeshihua\",\"children\":[],\"icon\":null,\"key\":\"menu_fengwokeshihua\",\"type\":1}],\"icon\":null,\"key\":\"f03f24b6-3bd3-4df6-8f40-6646754685f9_v11\",\"type\":1},{\"name\":\"车辆供需\",\"url\":null,\"children\":[{\"name\":\"街道\",\"url\":\"/dataBoard/tableIndex?tableId=6347&740d91f0-622f-42b3-be2d-729c5f97c9d7_v11\",\"children\":[],\"icon\":null,\"key\":\"740d91f0-622f-42b3-be2d-729c5f97c9d7_v11\",\"type\":1},{\"name\":\"街道分时\",\"url\":\"/dataBoard/tableIndex?tableId=6683&9752fa8c-67c7-4c6e-8730-f0967edb9e70_v11\",\"children\":[],\"icon\":null,\"key\":\"9752fa8c-67c7-4c6e-8730-f0967edb9e70_v11\",\"type\":1}],\"icon\":null,\"key\":\"8b53329b-1556-467b-a3f0-8c4900e0eda9_v11\",\"type\":1},{\"name\":\"停车点分析\",\"url\":null,\"children\":[{\"name\":\"Top点位报表\",\"url\":\"/dataBoard/report?id=983&menu_topdianwei\",\"children\":[],\"icon\":null,\"key\":\"menu_topdianwei\",\"type\":1},{\"name\":\"停车点画像\",\"url\":\"/dataBoard/report?id=938&4cecaf43-83fd-480b-9e11-6b8d225569d1_v11\",\"children\":[],\"icon\":null,\"key\":\"4cecaf43-83fd-480b-9e11-6b8d225569d1_v11\",\"type\":1},{\"name\":\"停车点运营指标分析\",\"url\":\"/dataBoard/report?id=783&49d14990-0bf0-4ad2-8810-f5db4107e359_v11\",\"children\":[],\"icon\":null,\"key\":\"49d14990-0bf0-4ad2-8810-f5db4107e359_v11\",\"type\":1},{\"name\":\"违停订单分布地图\",\"url\":\"/dataBoard/tableIndex?tableId=6346&f5888a44-7a22-4ab0-bc44-d35e85c9f001_v11\",\"children\":[],\"icon\":null,\"key\":\"f5888a44-7a22-4ab0-bc44-d35e85c9f001_v11\",\"type\":1},{\"name\":\"停车点指标诊断\",\"url\":\"/dataBoard/report?id=1082&menu_parkingguide\",\"children\":[],\"icon\":null,\"key\":\"menu_parkingguide\",\"type\":1}],\"icon\":null,\"key\":\"80ca1185-0c73-4937-a5e9-a0a3c14a1169_v11\",\"type\":1},{\"name\":\"未骑行诊断\",\"url\":null,\"children\":[{\"name\":\"N日未骑行\",\"url\":\"/dataBoard/report?id=896&c7f50a85-849d-4544-98e3-4223213f4162_v11\",\"children\":[],\"icon\":null,\"key\":\"c7f50a85-849d-4544-98e3-4223213f4162_v11\",\"type\":1}],\"icon\":null,\"key\":\"0571b51a-95d1-449a-abdf-61abea72d174_v11\",\"type\":1}],\"icon\":null,\"key\":\"7e222ecc-36a0-4e93-8011-a76b00bca1bd_v11\",\"type\":1},{\"name\":\"运营提效\",\"url\":null,\"children\":[{\"name\":\"数据导出\",\"url\":null,\"children\":[{\"name\":\"电单车region数据导出\",\"url\":\"/dataBoard/report?id=641&e7d17c63-9e6b-45c3-ad25-14042f26d7a2_v11\",\"children\":[],\"icon\":null,\"key\":\"e7d17c63-9e6b-45c3-ad25-14042f26d7a2_v11\",\"type\":1}],\"icon\":null,\"key\":\"d6a1ab36-70b6-47f8-9d0c-f2f448ec7a91_v11\",\"type\":1},{\"name\":\"位置地图\",\"url\":\"/dataBoard/tableIndex?tableId=6396&4be3dc84-d49c-429d-bdf4-10b65cb83148_v11\",\"children\":[],\"icon\":null,\"key\":\"4be3dc84-d49c-429d-bdf4-10b65cb83148_v11\",\"type\":1}],\"icon\":null,\"key\":\"86ddaca7-dccc-4a82-b1de-c89a6878ae56_v11\",\"type\":1},{\"name\":\"市场拓展\",\"url\":null,\"children\":[{\"name\":\"市场情报\",\"url\":null,\"children\":[{\"name\":\"电单车YY停车点\",\"url\":\"/dataBoard/tableIndex?tableId=9165&menu_ddcyytcd\",\"children\":[],\"icon\":null,\"key\":\"menu_ddcyytcd\",\"type\":1}],\"icon\":null,\"key\":\"menu_shichangqb\",\"type\":1}],\"icon\":null,\"key\":\"menu_shichangtuozhang\",\"type\":1},{\"name\":\"指挥系统\",\"url\":null,\"children\":[{\"name\":\"车辆运营\",\"url\":null,\"children\":[{\"name\":\"系统生单执行看板\",\"url\":\"/dataBoard/report?id=1189&api_dispatch_sys_order\",\"children\":[],\"icon\":null,\"key\":\"api_dispatch_sys_order\",\"type\":1},{\"name\":\"生派单执行看板\",\"url\":\"/dataBoard/report?id=1240&api_shengpaidan\",\"children\":[],\"icon\":null,\"key\":\"api_shengpaidan\",\"type\":1},{\"name\":\"调度ROI\",\"url\":\"/dataBoard/report?id=976&api_roi\",\"children\":[],\"icon\":null,\"key\":\"api_roi\",\"type\":1},{\"name\":\"调度宽表\",\"url\":\"/dataBoard/report?id=637&api_dispatch_temp1\",\"children\":[],\"icon\":null,\"key\":\"api_dispatch_temp1\",\"type\":1},{\"name\":\"未骑行车辆执行看板\",\"url\":\"/dataBoard/report?id=1251&api_noride\",\"children\":[],\"icon\":null,\"key\":\"api_noride\",\"type\":1}],\"icon\":null,\"key\":\"menu_ebike\",\"type\":1},{\"name\":\"点位管理\",\"url\":null,\"children\":[{\"name\":\"Top点位报表\",\"url\":\"/dataBoard/report?id=983&api_top_point\",\"children\":[],\"icon\":null,\"key\":\"api_top_point\",\"type\":1},{\"name\":\"停车点画像\",\"url\":\"/dataBoard/report?id=938&api_18point\",\"children\":[],\"icon\":null,\"key\":\"api_18point\",\"type\":1},{\"name\":\"停车点变动信息\",\"url\":\"/dataBoard/report?id=1217&api_point_c\",\"children\":[],\"icon\":null,\"key\":\"api_point_c\",\"type\":1}],\"icon\":null,\"key\":\"menu_fence_ctrl_ebike\",\"type\":1},{\"name\":\"人员管理\",\"url\":null,\"children\":[{\"name\":\"库外组长调度数据看板\",\"url\":\"/dataBoard/report?id=1183&api_t0.5\",\"children\":[],\"icon\":null,\"key\":\"api_t0.5\",\"type\":1}],\"icon\":null,\"key\":\"menu_renyuanguanli_ebike\",\"type\":1}],\"icon\":null,\"key\":\"menu_command_center_ebike\",\"type\":1},{\"name\":\"治理者满意\",\"url\":null,\"children\":[{\"name\":\"【及时响应】明细数据-电单\",\"url\":\"/dataBoard/report?id=1120&menu_quick response_spock1\",\"children\":[],\"icon\":null,\"key\":\"menu_quick response_spock1\",\"type\":1}],\"icon\":null,\"key\":\"menu_zhilizmanyi\",\"type\":1}],\"icon\":null,\"key\":\"menu_21840_v11\",\"type\":1},{\"name\":\"我的收藏\",\"url\":null,\"children\":[{\"name\":\"我的收藏\",\"url\":\"/dataBoard/myCollection\",\"children\":[],\"icon\":null,\"key\":\"menu_myCollection_v11\",\"type\":1},{\"name\":\"文件下载管理\",\"url\":\"/dataBoard/fileDownload\",\"children\":[],\"icon\":null,\"key\":\"menu_fileDownload_v11\",\"type\":1}],\"icon\":null,\"key\":\"menu_21860_v11\",\"type\":1}],\"icon\":null,\"key\":\"dataBoard\",\"type\":1},{\"name\":\"自助分析\",\"url\":\"/selfAnalysis\",\"children\":[{\"name\":\"分析工具\",\"url\":null,\"children\":[{\"name\":\"多维取数\",\"url\":\"/selfAnalysis/multidimensionalAccess\",\"children\":[],\"icon\":null,\"key\":\"multidimensionalAccess\",\"type\":1}],\"icon\":null,\"key\":\"analysisTool\",\"type\":1},{\"name\":\"个人看板\",\"url\":null,\"children\":[{\"name\":\"我创建的图表\",\"url\":\"/selfAnalysis/myCreatedReport\",\"children\":[],\"icon\":null,\"key\":\"myCreatedReport\",\"type\":1}],\"icon\":null,\"key\":\"personalBoard\",\"type\":1},{\"name\":\"自助分析（旧阿波罗）\",\"url\":null,\"children\":[{\"name\":\"报表中心\",\"url\":\"/selfAnalysis/reportlist\",\"children\":[],\"icon\":null,\"key\":\"reportlist\",\"type\":1},{\"name\":\"单图列表\",\"url\":\"/selfAnalysis/tablelist\",\"children\":[],\"icon\":null,\"key\":\"tablelist\",\"type\":1},{\"name\":\"数据源列表\",\"url\":\"/selfAnalysis/CDSList\",\"children\":[],\"icon\":null,\"key\":\"CDSList\",\"type\":1}],\"icon\":null,\"key\":\"selfAnalysisOld\",\"type\":1}],\"icon\":null,\"key\":\"selfAnalysis\",\"type\":1},{\"name\":\"系统管理\",\"url\":\"/systemManage\",\"children\":[{\"name\":\"定制化配置\",\"url\":null,\"children\":[{\"name\":\"校园运营绩效配置\",\"url\":\"/systemManage/campus/opConfig\",\"children\":[],\"icon\":null,\"key\":\"campusOpConfig\",\"type\":1},{\"name\":\"校园拓展绩效配置\",\"url\":\"/systemManage/campus/exConfig\",\"children\":[],\"icon\":null,\"key\":\"campusExConfig\",\"type\":1},{\"name\":\"输入输出看板配置\",\"url\":\"/systemManage/ioboard/upload\",\"children\":[],\"icon\":null,\"key\":\"ioboardUpload\",\"type\":1},{\"name\":\"区域经营分析配置\",\"url\":\"/systemManage/spock/dispatch/upload\",\"children\":[],\"icon\":null,\"key\":\"spockDispatchUpload\",\"type\":1}],\"icon\":null,\"key\":\"customConfig\",\"type\":1},{\"name\":\"数据管理\",\"url\":null,\"children\":[{\"name\":\"用户图表管理\",\"url\":\"/systemManage/userReportManage\",\"children\":[],\"icon\":null,\"key\":\"userTableManage\",\"type\":1},{\"name\":\"页面推荐配置\",\"url\":\"/systemManage/recommendConfig\",\"children\":[],\"icon\":null,\"key\":\"recommendConfig\",\"type\":1},{\"name\":\"信息配置管理\",\"url\":\"/systemManage/infoCenter\",\"children\":[],\"icon\":null,\"key\":\"infoCenter\",\"type\":1}],\"icon\":null,\"key\":\"dataManage\",\"type\":1}],\"icon\":null,\"key\":\"systemManage\",\"type\":1}]}";
//		JSONObject resJSON = JSONObject.parseObject(res);
//		parseMenu(resJSON.getJSONArray("content"));
//
//		List<Set<Integer>> list = new ArrayList<>();
//		list.add(Sets.newHashSet(1,2,3));
//		list.add(Sets.newHashSet(5,6));
//		Set<List<Integer>> cartesianProduct = Sets.cartesianProduct(list);
//		System.out.println(list.size());
//		List<Integer> a = Lists.newArrayList(1,2,3,5);
//		List<Integer> b = Lists.newArrayList(3,5,6,7);
//		a.retainAll(b);
//		PrintUtil.printIntList(a);
//
//		List<Integer> c = new ArrayList<>();
//		List<Integer> d = c.stream().map(item -> item + 1).collect(Collectors.toList());
//		PrintUtil.printIntList(d);

//		LocalDate end = LocalDate.now().minusDays(1);
//		LocalDate start = end.minusYears(3).withDayOfMonth(1);
//		generateDataRange(start, end);
//		BigDecimal newValue = new BigDecimal("8628834");
//		BigDecimal oldValue = new BigDecimal("10570218");
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

	private static boolean compareVinciResult(String url1, String url2, String paramStr, Map<String, String> header) {
		JSONObject param = JSONObject.parseObject(paramStr);
		String res1 = HttpClientUtil.getInstance().doPost(url1, param, header);
		String res2 = HttpClientUtil.getInstance().doPost(url2, param, header);
		JSONObject resJson = JSONObject.parseObject(res1);
		if (resJson.getInteger("code") != 0) {
			System.out.println("failed");
			return false;
		}
//		return res1.equals(res2);
		return compareEtl(res1, res2);
	}

	private static boolean compareEtl(String res1, String res2) {
		JSONObject json1 = JSONObject.parseObject(res1);
		JSONObject json2 = JSONObject.parseObject(res2);
		String etl1 = json1.getJSONObject("data").getJSONObject("buildEtlInfo").getJSONObject("etlInfo").getString("etlCode");
		String etl2 = json2.getJSONObject("data").getJSONObject("buildEtlInfo").getJSONObject("etlInfo").getString("etlCode");
		System.out.println(etl1);
		System.out.println(etl2);
		return etl1.equals(etl2);

	}

	private static List<String> generateDimConfigStrs(List<String> metricConfigList) {
		List<String> resultList = new ArrayList<>();
		StringBuilder stringBuilder = new StringBuilder();
		for (String metricConfigV2DO : metricConfigList) {
			if (stringBuilder.length() + metricConfigV2DO.length() > 9) {
				resultList.add(stringBuilder.toString());
				stringBuilder = new StringBuilder(metricConfigV2DO);
			} else {
				if (stringBuilder.length() > 0) {
					stringBuilder.append(";");
				}
				stringBuilder.append(metricConfigV2DO);
			}
		}
		resultList.add(stringBuilder.toString());
		return resultList;
	}

	public static void parseMenu(JSONArray menus) {
		for (int i = 0; i <menus.size(); i++) {
			JSONObject menu = (JSONObject) menus.get(i);
			String url =  menu.getString("url");
			if (url != null && !url.contains("/dataBoard/report?id=") && !url.contains("/dataBoard/tableIndex?tableId=")) {
				String name = menu.getString("name");
				System.out.println(name);
				System.out.println(url);
				System.out.println("--------------------------------------");
			}
			JSONArray children = menu.getJSONArray("children");
			parseMenu(children);
		}
	}

	public static BigDecimal generateRandomBigDecimalFromRange(BigDecimal min, BigDecimal max) {
		BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
		return randomBigDecimal.setScale(4,BigDecimal.ROUND_HALF_UP);
	}

	static void generateDataRange(LocalDate start, LocalDate end) {
		while (start.isBefore(end)) {
			if (start.getDayOfWeek() == DayOfWeek.MONDAY) {
				String s = start.format(DateTimeFormatter.ISO_LOCAL_DATE);
				String e = start.plusDays(6).format(DateTimeFormatter.ISO_LOCAL_DATE);
				System.out.println(s + "-" + e);
			}
			if (start.getDayOfMonth() == 1) {
				String s = start.format(DateTimeFormatter.ISO_LOCAL_DATE);;
				String e = start.withDayOfMonth(start.lengthOfMonth()).format(DateTimeFormatter.ISO_LOCAL_DATE);
				System.out.println(s + "-" + e);
			}
			start = start.plusDays(1);
		}
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


