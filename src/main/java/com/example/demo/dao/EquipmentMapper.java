package com.example.demo.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.Equipment;
import org.springframework.stereotype.Repository;

/**
 * 使用mybatis plus 简化代码
 */
@Repository
public interface EquipmentMapper extends BaseMapper<Equipment> {
}
