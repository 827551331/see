package com.wdssdream.see.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("d_daily")
public class Daily implements Cloneable{

    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField
    private String title;
    @TableField
    private String content;
    @TableField("week_of_month")
    private int weekOfMonth;
    @TableField("week_of_year")
    private int weekOfYear;
    @TableField("daily_month")
    private String dailyMonth;
    @TableField("daily_year")
    private String dailyYear;
    @TableField("create_time")
    private LocalDateTime createTime;
    @TableField("update_time")
    private LocalDateTime updateTime;
    @TableField("create_person")
    private String createPerson;
    @TableField("update_person")
    private String updatePerson;


    @Override
    public Daily clone() {
        try {
            Daily clone = (Daily) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

