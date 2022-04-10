package com.example.dubbo;

/**
 * @author: luozijian
 * @date: 2021/12/9 16:59:49
 * @description:
 */
public interface Invoker<T>{
    // 执行方法， Invocation 为执行信息（包括执行 哪个方法，参数等信息），Result为执行结果
    Result invoker(Invocation invocation);
    // 省略其它 dubbo中的方法
}
