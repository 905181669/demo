package com.example.demo.rest.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: luozijian
 * @date: 4/20/21 13:59:08
 * @description:
 *
 *
{
"status": 0, // 处理结果
"message":"success"
"data":{
       "serialNo":"请求带的流水号"
   }
}

status
0：接收并处理成功
1：serialNo流水号重复
-1：认证失败
-2 :其他系统异常，此时可以重试
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ShangriResult<T> {

    private int status;
    private String message;
    T data;


}