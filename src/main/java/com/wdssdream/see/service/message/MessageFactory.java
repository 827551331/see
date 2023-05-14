package com.wdssdream.see.service.message;

/**
 * description: MessageFactory
 * date: 2023/5/7 09:32
 *
 * @author wang_yw
 */
public class MessageFactory {

    private static final String DEFAULT_TITLE = "Default Title";
    private static final Message message = new Message(DEFAULT_TITLE, "");

    public static Message createMessage(String content) {

        Message result = null;
        // 创建并返回 Message 对象的克隆副本
        try {
            result = message.clone();
            result.setContent(content);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }
}