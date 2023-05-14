package com.wdssdream.see.service.task;

import com.wdssdream.see.service.message.Message;

/**
 * description: 每个 task 运行之后都会生成一个 taskResult 作为推送的依据和信息
 * date: 2023/5/12 22:49
 *
 * @author wang_yw
 */
public class TaskResult implements Cloneable {

    public static final Integer PUSH_FLAG = 1;

    /**
     * 这里用 Integer 方便以后扩展
     * 若为 1 则直接推送，其他情况留作扩展
     */
    private Integer pushFlag;

    /**
     * 消息体
     */
    private Message message;

    public Integer getPushFlag() {
        return pushFlag;
    }

    public TaskResult setPushFlag(Integer pushFlag) {
        this.pushFlag = pushFlag;
        return this;
    }

    public Message getMessage() {
        return message;
    }

    public TaskResult setMessage(Message message) {
        this.message = message;
        return this;
    }

    public TaskResult clone() throws CloneNotSupportedException {
        TaskResult cloneResult = (TaskResult) super.clone();
        cloneResult.message = this.message.clone(); // 克隆消息体对象
        return cloneResult;
    }
}
