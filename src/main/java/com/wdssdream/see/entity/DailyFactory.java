package com.wdssdream.see.entity;

import com.wdssdream.see.util.DailyUtil;

import java.time.LocalDateTime;

/**
 * description: DailyFactory
 * date: 2023/7/3 01:08
 *
 * @author wang_yw
 */
public class DailyFactory {

    private static final Daily dailyClone = new Daily();

    public static Daily createDaily() {
        return DailyUtil.wrap(dailyClone.clone());
    }
}
