package com.example.demo.form;

import lombok.Data;

import java.util.List;

/**
 * 请求参数的表单
 * 可以用来做参数校验
 */
@Data
public class Param {
    private List<String> list;
}
