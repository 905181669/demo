package com.example.dubbo;

/**
 * @author: luozijian
 * @date: 2021/12/9 17:00:14
 * @description:
 */
public interface Invocation {
    // 获取执行的方法名称
    String getMethodName();
    // 获取方法 参数 类型
    Class<?>[] getParameterTypes();
    //获取方法 参数
    Object[] getArguments();
}
