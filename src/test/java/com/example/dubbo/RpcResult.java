package com.example.dubbo;

/**
 * @author: luozijian
 * @date: 2021/12/9 17:01:17
 * @description:
 */
public class RpcResult implements Result {
    private Object object;

    public RpcResult(Object object) {
        this.object = object;
    }
    @Override
    public Object getResult() {
        return object;
    }
}
