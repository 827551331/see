package com.wdssdream.see.service;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.wdssdream.see.service.message.Message;
import com.wdssdream.see.service.message.MessageFactory;
import com.wdssdream.see.service.message.MessagePush;
import com.wdssdream.see.service.message.PushService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.*;

/**
 * description: SeeService
 * date: 2023/4/29 00:48
 *
 * @author wang_yw
 */
@Slf4j
@Service
public class SeeService {

    @Autowired
    private MessagePush messagePush;

    public static final Set<String> holidaySet = new HashSet<>();

    public static int number = 0;


    //@Scheduled(cron = "*/5 * * * * ?")
//    @Scheduled(cron = "0 */5 * * * ?")
    public void exec() {
        log.info("定时执行扫描任务：");
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
                    //最多通知 3 次
                    if (number < 3) {
                        messagePush.push(MessageFactory.createMessage(msg));
                        number++;
                    }
                } else {
                    msg = "无法预约，日期为：" + date + ",医生为：" + noSchTimeMap.get(date);
                }
                log.info("扫描结果:{}", msg);
            });
        } else {
            //同时利用这个特点，在出错之后放弃一直发送
            number = 4;
            messagePush.push(MessageFactory.createMessage("获取可挂号信息失败！请检查token"));
        }
    }


    @PostConstruct
    private void initHoliday() {
        log.info("开始初始化节假日信息：");

        String url = "https://timor.tech/api/holiday/year/" + LocalDate.now().getYear() + "/?week=Y";
        JSONObject result = JSON.parseObject(HttpUtil.get(url));
        if (result.getInteger("code") == 0) {
            log.info("获取节假日信息成功:{}", result);
            JSONObject holiday = result.getJSONObject("holiday");
            for (Map.Entry<String, Object> stringObjectEntry : holiday.entrySet()) {
                JSONObject day = (JSONObject) stringObjectEntry.getValue();
                if (day.getBoolean("holiday")) {
                    String dateStr = day.getString("date");
                    holidaySet.add(dateStr);
                }
            }
            log.info("holidaySet:{}", holidaySet);
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
