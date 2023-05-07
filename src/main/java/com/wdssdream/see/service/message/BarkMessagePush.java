package com.wdssdream.see.service.message;

import cn.hutool.http.HttpUtil;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * description: BarkMessagePush
 * date: 2023/5/7 09:12
 *
 * @author wang_yw
 */
@Service
@Primary
public class BarkMessagePush extends BaseMessagePush {


    /**
     * description: 无返回
     * date: 2023/5/7 09:06
     * author: wang_yw
     *
     * @since 1.0
     */
    @Override
    public void push(Message message) {

        //AKeE3SpVfUstu4HsZrWJ8m  坨宝
        //jFWdG9WVRixtt5tkrVUiQD  小宝
        String pushUrl = "https://api.day.app/";

        String[] urlArray = new String[]{"https://api.day.app/AKeE3SpVfUstu4HsZrWJ8m/",
                "https://api.day.app/jFWdG9WVRixtt5tkrVUiQD/"};
        for (String url : urlArray) {
            HttpUtil.get(url + message.getContent());
        }
    }

    /**
     * description: 有返回
     * date: 2023/5/7 09:07
     * author: wang_yw
     *
     * @return com.wdssdream.see.service.message.PushResult
     * @since 1.0
     */
    @Override
    public PushResult pushReturn(Message message) {
        return null;
    }
}
