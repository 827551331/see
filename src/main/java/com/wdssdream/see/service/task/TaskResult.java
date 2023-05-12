package com.wdssdream.see.service.task;

/**
 * description: 每个 task 运行之后都会生成一个 taskResult 作为推送的依据和信息
 * date: 2023/5/12 22:49
 *
 * @author wang_yw
 */
public class TaskResult {

    /**
     * 这里用 Integer 方便以后扩展
     * 若为 1 则直接推送，其他情况留作扩展
     */
    private Integer pushFlag;

}
