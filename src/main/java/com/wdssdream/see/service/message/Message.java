package com.wdssdream.see.service.message;

import lombok.Builder;
import lombok.Data;

/**
 * description: Message
 * date: 2023/5/6 23:25
 *
 * @author wang_yw
 */
public class Message implements Cloneable {

    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;

    public String getTitle() {
        return title;
    }

    public Message setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Message setContent(String content) {
        this.content = content;
        return this;
    }

    // 构造函数
    public Message(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
