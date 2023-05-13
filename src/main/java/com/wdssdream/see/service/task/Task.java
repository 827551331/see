package com.wdssdream.see.service.task;

/**
 * description: Task
 * date: 2023/5/10 23:22
 *
 * @author wang_yw
 */
public interface Task {

    /**
     * 任务执行方法
     *
     * @return TaskResult
     */
    TaskResult execute();
}
