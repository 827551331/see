package com.wdssdream.see.service.message;

/**
 * description: MessagePush
 * date: 2023/5/7 08:55
 *
 * @author wang_yw
 */
public interface MessagePush {

    /**
     * description: 无返回
     * date: 2023/5/7 09:06
     * author: wang_yw
     *
     * @since 1.0
     */
    void push(Message message);

    /**
     * description: 有返回
     * date: 2023/5/7 09:07
     * author: wang_yw
     *
     * @return com.wdssdream.see.service.message.PushResult
     * @since 1.0
     */
    PushResult pushReturn(Message message);

}
