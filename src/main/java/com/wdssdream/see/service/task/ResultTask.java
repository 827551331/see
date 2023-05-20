package com.wdssdream.see.service.task;

import java.util.Properties;

/**
 * description: ResultTask
 * date: 2023/5/20 23:19
 *
 * @author wang_yw
 */
public interface ResultTask {

    /**
     * 带参数的执行方法
     *
     * @param properties：参数信息
     * @return TaskResult
     */
    TaskResult execute(Properties properties);
}
