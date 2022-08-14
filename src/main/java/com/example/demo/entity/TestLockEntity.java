package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: luozijian
 * @date: 2022/4/17
 * @description:
 */
@Data
@Accessors(chain = true)
@TableName(value = "test_lock_tbl", autoResultMap = true)
public class TestLockEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String a;

    private String b;

}