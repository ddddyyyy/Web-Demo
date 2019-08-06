package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.dao.EquipmentMapper;
import com.example.demo.domain.Equipment;
import com.example.demo.util.ValueComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

@Service
public class EquipmentService {
    private EquipmentMapper equipmentMapper;

    @Autowired
    public EquipmentService(EquipmentMapper equipmentMapper) {
        this.equipmentMapper = equipmentMapper;
    }

    /**
     * @return 装备列表
     */
    @SuppressWarnings("unchecked")
    public List<Equipment> getList() {
        QueryWrapper<Equipment> queryWrapper = new QueryWrapper<>();
        return equipmentMapper.selectList(queryWrapper.lambda()
                .select(Equipment::getTitle)
                .eq(Equipment::getEnable, true)
                .orderByDesc(Equipment::getHot));
    }

    /**
     * @return 装备掉落地图
     */
    public Object[] getMap(List<String> strings) throws ParseException {
        NumberFormat nf = NumberFormat.getPercentInstance();
        QueryWrapper<Equipment> queryWrapper = new QueryWrapper<>();
        // 每个装备的掉落地图获取
        List<Equipment> list = equipmentMapper.selectList(queryWrapper.lambda()
                .in(Equipment::getTitle, strings)
                .select(Equipment::getMap, Equipment::getHot, Equipment::getTitle));

        Map<Object, Double> map = new HashMap<>();
        for (Equipment equipment : list) {
            if (equipment.getMap() == null) {
                continue;
            }

            //增加这个装备的热度
            equipment.setHot(equipment.getHot() + 1);
            equipmentMapper.update(equipment, new QueryWrapper<Equipment>()
                    .eq("title", equipment.getTitle()));

            JSON json = JSON.parseObject(equipment.getMap());
            Double value;
            for (Object key : ((JSONObject) json).keySet()) {
                value = nf.parse((String) ((JSONObject) json).get(key)).doubleValue();
                key = ((String) key).replace("\t", "");
                if (map.containsKey(key)) {
                    value += map.get(key);
                }
                map.put(key, value);
            }
        }
        List<Map.Entry<Object, Double>> entryList = new ArrayList<>(map.entrySet());
        ValueComparator vc = new ValueComparator();
        entryList.sort(vc);
        return entryList.stream().map(Map.Entry::getKey).toArray();
    }

}
