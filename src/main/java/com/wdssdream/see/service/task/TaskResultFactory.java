package com.wdssdream.see.service.task;

public class TaskResultFactory {

    private static final TaskResult originalResult = new TaskResult();


    public TaskResult createClone(String content) {
        TaskResult cloneResult = null;
        try {
            cloneResult = originalResult.clone();
            //推送状态默认为 1 ，则为推送
            cloneResult.setPushFlag(TaskResult.PUSH_FLAG);
            cloneResult.getMessage().setContent(content);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return cloneResult;
    }
}

