package com.example.zookeeper;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;

/**
 * curator在注册watch事件上，提供了一个usingWatcher方法，使用这个方法注册的watch事件和默认watch事件一样，
 * 监听只会触发一次，监听完毕后就会销毁，也就是一次性的。
 * 而这个方法有两种参数可选，一个是zk原生API的Watcher接口的实现类，
 * 另一个是Curator提供的CuratorWatcher接口的实现类，不过在usingWatcher方法上使用哪一个效果都是一样的，都是一次性的。
 *
 * CuratorWatcher事件监听，注册多次监听多次
 */
public class MyCuratorWatcherTest {

    public static void main(String[] args) throws Exception {

        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .retryPolicy(new RetryNTimes(1, 1000))
                .connectionTimeoutMs(5000)
                .namespace("register");
        CuratorFramework client = builder.build();
        client.start();



        //创建节点
        /**
         * 这个creatingParentContainersIfNeeded()接口非常有用，
         * 因为一般情况开发人员在创建一个子节点必须判断它的父节点是否存在，
         * 如果不存在直接创建会抛出NoNodeException，
         * 使用creatingParentContainersIfNeeded()之后Curator能够自动递归创建所有所需的父节点。
         *
         */
        client.create().creatingParentContainersIfNeeded().withMode(CreateMode.EPHEMERAL)
                .forPath("/demo-service","localhost".getBytes());

        //添加节点监听事件(节点需存在,查询不触发,且只触发一次)
        client.getData().usingWatcher(new MyCuratorWatcher(client)).forPath("/demo-service");

        //线程睡眠，等待监听事件响应
        Thread.sleep(500000);
        client.close();
    }


    public static class MyCuratorWatcher implements CuratorWatcher {

        private CuratorFramework client;

        public MyCuratorWatcher(CuratorFramework client) {
            this.client = client;
        }

        @Override
        public void process(WatchedEvent event) throws Exception {

            System.out.println("接收到CuratorWatcher事件,事件类型:" +event.getType() + " 节点名称:" + event.getPath());

            client.getData().usingWatcher(new MyCuratorWatcher(client)).forPath("/demo-service");
        }
    }


}