package com.example.demo.controller;

import com.example.demo.form.Param;
import com.example.demo.service.EquipmentService;
import com.example.demo.util.result.Result;
import com.example.demo.util.result.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
public class Controller {

    private EquipmentService equipmentService;

    @Autowired
    public Controller(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping("get")
    @ApiOperation("获得装备列表")
    public Result get() {
        return ResultUtil.success(equipmentService.getList());
    }

    @PostMapping("send")
    @ApiOperation("根据装备名获取掉落地图")
    public Result send(@RequestBody Param param) throws ParseException {
        return ResultUtil.success(equipmentService.getMap(param.getList()));
    }
}
