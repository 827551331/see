package com.wdssdream.see.service;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

/**
 * description: SeeService
 * date: 2023/4/29 00:48
 *
 * @author wang_yw
 */
@Service
public class SeeService {

    @Autowired
    private PushService pushService;

    public static final Set<String> holidaySet = new HashSet<>();


    @Scheduled(cron = "0 */5 * * * ?")
    public void exec() {
        if (CollectionUtils.isEmpty(holidaySet)) {
            this.initHoliday();
        }

        String url = "http://hlwyy.9yiban.com/blb1/api/registration/getregdoctorlistbydeptid?uid=36048677&ticket=&_=1682660029145&content=&departmentid=246135&departmentName=%E5%85%8D%E8%B4%B9%E5%AD%95%E5%89%8D%E4%BC%98%E7%94%9F%E7%A7%91&hospitalid=557&regdate=2023-4-28&regtype=0";
        Map<String, Object> paramMap = new HashMap<>();
        JSONObject result = JSONObject.parseObject(HttpRequest.post(url)
                .header("etoken",
                        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0aGRPcGVuSWQiOiJvc1FZTXc1QjA5MnhZT0RSTUVwNVZ6Vzl6ek40IiwiY2xpZW50aWQiOiJTLTQyMDEwMC02MzM4OSIsIm9wZW5pZCI6Im9zUVlNdzVCMDkyeFlPRFJNRXA1VnpXOXp6TjQiLCJuaWNrTmFtZSI6Indkc3NkcmVhbSIsInNleCI6IiIsIm1vYmlsZU5vIjoiM2tFYnhISXMvR2ppblNBWGxVR3lTZz09IiwiaG9zcGl0YWxDb2RlIjoiNjMzODkiLCJ1aWQiOiIzNjA0ODY3NyIsInBpY1VybCI6Imh0dHBzOi8vdGhpcmR3eC5xbG9nby5jbi9tbW9wZW4vdmlfMzIvUTBqNFR3R1RmVElsU3IyU1BtckZBbXR5RFVEbEt4d1Q1S3diZ3NOcjczQzREb2hraWNlSFQzTDJ6SWZoM2JGV05HbmlieHpuazN3Wm14TVdKTTFWd2Z0Zy8xMzIiLCJ0aGRBcHBJZCI6Ind4NjQ2ZTJjMTNlNmY1YTU1MCIsInNldHRpbmdDb2RlIjoid3g2MzM4OTAxIiwicmVxdWVzdHR5cGUiOiI0IiwiaGVhZEltZ1VybCI6Imh0dHBzOi8vdGhpcmR3eC5xbG9nby5jbi9tbW9wZW4vdmlfMzIvUTBqNFR3R1RmVElsU3IyU1BtckZBbXR5RFVEbEt4d1Q1S3diZ3NOcjczQzREb2hraWNlSFQzTDJ6SWZoM2JGV05HbmlieHpuazN3Wm14TVdKTTFWd2Z0Zy8xMzIiLCJsb2NhdGlvbiI6IiJ9.lRRwVc2bkVNGsNxZxKORPZVDDBKci6C_rS0IGZ7QEZA")
                .execute().body());

        Map<String, String> noSchTimeMap = this.parseNoSchTime(result);

        if (!CollectionUtils.isEmpty(noSchTimeMap)) {
            noSchTimeMap.keySet().forEach(date -> {
                String msg;
                if (holidaySet.contains(date)) {
                    msg = "可以预约了，速度上线，可预约的日期为：" + date + ",医生为：" + noSchTimeMap.get(date);
                    pushService.pushToBark(msg);
                } else {
                    msg = "无法预约，日期为：" + date + ",医生为：" + noSchTimeMap.get(date);
                }
                System.out.println(msg);
            });
        } else {
            pushService.pushToBark("获取可挂号信息失败！请检查token");
        }
    }


    private void initHoliday() {
        String url = "https://timor.tech/api/holiday/year/" + LocalDate.now().getYear() + "/?week=Y";
        JSONObject result = JSON.parseObject(HttpUtil.get(url));
        if (result.getInteger("code") == 0) {
            JSONObject holiday = result.getJSONObject("holiday");
            for (Map.Entry<String, Object> stringObjectEntry : holiday.entrySet()) {
                JSONObject day = (JSONObject) stringObjectEntry.getValue();
                if (day.getBoolean("holiday")) {
                    String dateStr = day.getString("date");
                    holidaySet.add(dateStr);
                }
            }
        }
    }

    public Map<String, String> parseNoSchTime(JSONObject json) {

        if (!json.getBooleanValue("result")) {
            return new HashMap<>();
        }

        JSONArray data = json.getJSONArray("data");
        Map<String, String> noSchTimeMap = new HashMap<>();

        for (int i = 0; i < data.size(); i++) {
            JSONObject doctor = data.getJSONObject(i);
            JSONArray noSchTime = doctor.getJSONArray("noSchTime");

            for (int j = 0; j < noSchTime.size(); j++) {
                String date = noSchTime.getString(j);
                noSchTimeMap.put(date, doctor.getString("doctorRank") + " - " + doctor.getString("doctorname"));
            }
        }
        return noSchTimeMap;
    }

}
