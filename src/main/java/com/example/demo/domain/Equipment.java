package com.example.demo.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@TableName("pcr")
public class Equipment {
    @TableId(value = "id")
    @JsonIgnore
    private Integer id;
    @TableField("title")
    private String title;
    @JsonIgnore
    @TableField("url")
    private String url;
    @JsonIgnore
    @TableField("map")
    private String map;
    @JsonIgnore
    @TableField("enable")
    private Boolean enable;
    @JsonIgnore
    @TableField("hot")
    private Integer hot;
}
