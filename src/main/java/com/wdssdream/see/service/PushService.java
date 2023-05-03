package com.wdssdream.see.service;

import cn.hutool.http.HttpUtil;
import org.springframework.stereotype.Service;

/**
 * description: PushService
 * date: 2023/5/3 19:50
 *
 * @author wang_yw
 */
@Service
public class PushService {

    public void pushToBark(String msg) {
        //
        String[] urlArray = new String[]{"https://api.day.app/AKeE3SpVfUstu4HsZrWJ8m/", "https://api.day.app/jFWdG9WVRixtt5tkrVUiQD/"};
        for (String url : urlArray) {
            HttpUtil.get(url + msg);
        }
    }
}
