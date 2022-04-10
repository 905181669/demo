package com.example.dubbo;

/**
 * @author: luozijian
 * @date: 2021/12/9 17:00:40
 * @description:
 */
public class NativeInvocation implements Invocation {
    private String methodName;
    private Class<?>[] parameterTypes;
    private Object[] arguments;
    public NativeInvocation(String methodName, Class<?>[] parameterTypes, Object[] arguments) {
        super();
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.arguments = arguments;
    }
    @Override
    public String getMethodName() {
        return methodName;
    }
    @Override
    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }
    @Override
    public Object[] getArguments() {
        return arguments;
    }
}
