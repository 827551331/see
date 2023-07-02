package com.wdssdream.see.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wdssdream.see.entity.Daily;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DailyMapper extends BaseMapper<Daily> {
    // 可以不定义任何方法，直接继承 BaseMapper 接口
    // MyBatis-Plus 会根据实体类和数据库表的映射关系自动生成常用的 CRUD 方法
}


