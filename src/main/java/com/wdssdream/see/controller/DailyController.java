package com.wdssdream.see.controller;

import com.wdssdream.see.entity.Daily;
import com.wdssdream.see.entity.DailyFactory;
import com.wdssdream.see.service.impl.DailyServiceImpl;
import com.wdssdream.see.util.DailyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/daily")
public class DailyController {

    private final DailyServiceImpl dailyService;

    @Autowired
    public DailyController(DailyServiceImpl dailyService) {
        this.dailyService = dailyService;
    }

    @PostMapping("/list")
    public List<Daily> getDailyList() {
        return dailyService.list();
    }

    @GetMapping("/{id}")
    public Daily getDailyById(@PathVariable Long id) {
        return dailyService.getById(id);
    }

    @PostMapping
    public void addDaily(@RequestBody Daily daily) {
        dailyService.save(DailyUtil.wrap(daily));
    }

    @DeleteMapping("/{id}")
    public void deleteDaily(@PathVariable Long id) {
        dailyService.removeById(id);
    }
}

