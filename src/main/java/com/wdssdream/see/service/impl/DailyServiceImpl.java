package com.wdssdream.see.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdssdream.see.dao.DailyMapper;
import com.wdssdream.see.entity.Daily;
import com.wdssdream.see.service.DailyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyServiceImpl extends ServiceImpl<DailyMapper, Daily> implements DailyService {
}


