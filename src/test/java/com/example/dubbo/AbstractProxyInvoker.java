package com.example.dubbo;

/**
 * @author: luozijian
 * @date: 2021/12/9 17:02:05
 * @description:
 */
/***
 * 抽象 代理 执行 器
 *
 * @author HadLuo
 * @since JDK1.7
 * @history 2018年4月27日 新建
 */
public abstract class AbstractProxyInvoker<T> implements Invoker<T> {
    // spring注入的业务对象，也就是上面的 UserServiceImpl对象
    private final T object;

    public AbstractProxyInvoker(Class<?> type, T object) {
        this.object = object;
    }

    /***
     * 抽象 执行方法 ，交给 Wrapper实现
     *
     * @param proxy
     * @param invocation
     * @return
     * @throws Throwable
     * @author HadLuo 2018年4月27日 新建
     */
    public abstract Object doInvoke(Object proxy, Invocation invocation) throws Throwable;

    @Override
    public Result invoker(Invocation invocation) {
        if (invocation instanceof NativeInvocation) {
            try {
                // 间接调用 doInvoke 方法
                return new RpcResult(doInvoke(object, invocation));
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
//        if(invocation instanceof RpcInvocation){
            // 远程调用, 这里先不管
//        }
        throw new RuntimeException("暂时 只 实现  本地的 调用");
    }
}