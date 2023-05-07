package com.wdssdream.see.service.message;

/**
 * description: MessageFactory
 * date: 2023/5/7 09:32
 *
 * @author wang_yw
 */
public class MessageFactory {

    private static final String DEFAULT_TITLE = "Default Title";

    public static Message createMessage(String content) {
        Message message = new Message(DEFAULT_TITLE, content);

        try {
            // 创建并返回 Message 对象的克隆副本
            return (Message) message.clone();
        } catch (CloneNotSupportedException e) {
            // 处理 CloneNotSupportedException 异常
            throw new RuntimeException("Failed to clone Message object", e);
        }
    }
}
