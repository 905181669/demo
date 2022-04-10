package com.example.dubbo;

/**
 * @author: luozijian
 * @date: 2021/12/9 16:56:16
 * @description:
 */
public class InvokerTest {

    public static void main(String[] args) {
        // 模仿spring new 业务对象
        IUserSerice userSerice = new UserServiceImpl();
        User user = new User();
        user.setName("hadluo");
        // 用构造 IUserSerice 接口 对应的 Invoker ，dubbo是extensionloader构造的，这里我们直接模拟.
        Invoker<IUserSerice> invoker = JavassistProxyFactory.getInvoker(userSerice, IUserSerice.class);
        //构造调用方法信息，Invocation是调用方法信息，我们这里是 login方法信息
        Invocation invocation = new NativeInvocation("login",new Class<?>[]{User.class},new Object[]{user}) ;
        //执行一次 本地调用
        Result result = invoker.invoker(invocation);
        System.err.println("返回 值:" + ((User)result.getResult()).getName());
    }

}

//User 对象
class User {
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
// 业务 user接口
interface IUserSerice {
    public User login(User user);
}
// 接口实现
class UserServiceImpl implements IUserSerice {
    @Override
    public User login(User user) {
        System.err.println(user.getName() + " login....");
        user.setName("login success");
        return user;
    }
}

