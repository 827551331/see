package com.wdssdream.see.service.task;

import com.wdssdream.see.service.message.MessageFactory;

public class TaskResultFactory {

    private static final TaskResult originalResult = new TaskResult();


    public TaskResult createClone(String content) {
        originalResult.setPushFlag(0);
        TaskResult cloneResult = new TaskResult();
        cloneResult.setMessage(MessageFactory.createMessage(content));
        cloneResult.setPushFlag(originalResult.getPushFlag());
        return cloneResult.clone();
    }
}

