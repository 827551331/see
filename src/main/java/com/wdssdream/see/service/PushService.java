package com.wdssdream.see.service;

import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * description: PushService
 * date: 2023/5/3 19:50
 *
 * @author wang_yw
 */
@Slf4j
@Service
public class PushService {

    public void pushToBark(String msg) {
        log.info("");
        //
        String[] urlArray = new String[]{"https://api.day.app/AKeE3SpVfUstu4HsZrWJ8m/", "https://api.day.app/jFWdG9WVRixtt5tkrVUiQD/"};
        for (String url : urlArray) {
            HttpUtil.get(url + msg);
        }
    }
}
