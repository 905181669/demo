package com.example.dubbo;

/**
 * @author: luozijian
 * @date: 2021/12/9 16:59:25
 * @description:
 */
public class JavassistProxyFactory {
    // proxy： UserServiceImpl实例对象 ， type： IUserService接口的class
    public static <T> Invoker<T> getInvoker(T proxy, Class<?> type) {
        // Wrapperd 是对 IUserService接口 的包装操作，后面会细讲
        final Wrapper wrapper = Wrapper.getWrapper(type);
        return new AbstractProxyInvoker<T>(type, proxy) {
            @Override
            public Object doInvoke(Object proxy, Invocation invocation) throws Throwable {
                return wrapper.invokeMethod(proxy, invocation.getMethodName(),
                        invocation.getParameterTypes(), invocation.getArguments());
            }
        };
    }
}