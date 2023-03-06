package com.wcx.excelword.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("excel_info")
public class ExcelInfo {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer height;
    private Integer width;
    private String content;
    private Integer isSuccess;
    private Integer isVisible;
    private Date createtime;
    private Date updatetime;
}
