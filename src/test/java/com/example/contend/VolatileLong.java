package com.example.contend;



import lombok.AllArgsConstructor;
import lombok.Data;
import sun.misc.Contended;


/**
 * @author: luozijian
 * @date: 6/17/21 21:26:26
 * @description:
 */
@Data
@AllArgsConstructor
public class VolatileLong {
    @Contended("group1")
    private volatile long value1;
    @Contended("group2")
    private volatile long value2;
    @Contended("group3")
    private volatile long value3;
    @Contended("group4")
    private volatile long value4;

}