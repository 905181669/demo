package com.example.zookeeper;

import lombok.Data;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZKUtil;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: luozijian
 * @date: 7/15/21 14:00:30
 * @description:
 */
@Data
@Slf4j
public class ZkWatcherDemo {

    private String workerPath = "/test/listener/node";
    private String subWorkerPath = "/test/listener/node/id-";


    private static String ZK_URL = "localhost:2181";
    private static CuratorFramework curatorFramework = null;

    public static CuratorFramework getCuratorFramework(){
        if (curatorFramework == null){
            curatorFramework = CuratorFrameworkFactory.newClient(ZK_URL,
                    5000,5000, new ExponentialBackoffRetry(1000,5));
        }
        curatorFramework.start();
        return curatorFramework;
    }

    @Test
    public void testWatcher() throws Exception {
        CuratorFramework curatorFramework = getCuratorFramework();
        //fluent模式,优雅的模式

        /**
         * 新增,递归创建,CreateMode.PERSISTENT 创建持久化节点（默认），
         */
        String path = curatorFramework.create().creatingParentContainersIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .forPath("/node/node1","123".getBytes());

        System.out.println(path);

        byte[] nodeBytes = curatorFramework.getData().forPath("/node");
        System.out.println("node的值："+new String(nodeBytes));

        /**
         * 修改,如果第二个参数不填  会被修改为本机IP
         */
        curatorFramework.setData().forPath("/node/node1","node1".getBytes());

        /**
         * 获取stat信息
         */
        Stat stat=new Stat();
        byte[] bytes = curatorFramework.getData().storingStatIn(stat).forPath("/node/node1");
        System.out.println("修改后的值："+new String(bytes)+"-->"+stat);
        /**
         * 删除
         */
        Void aVoid = curatorFramework.delete().deletingChildrenIfNeeded().forPath("/node");
        System.out.println(aVoid);

        /**
         * 事务操作（curator独有的）, 不会创建成功！
         */
        curatorFramework.inTransaction().create().withMode(CreateMode.PERSISTENT)
                .forPath("/node","123".getBytes())
                .and().setData().forPath("/curator","123".getBytes());

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                .inBackground(new BackgroundCallback() {
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {

                        System.out.println("接收到watcher回调");

                        System.out.println(Thread.currentThread().getName()
                                +"->resultCode:"+curatorEvent.getResultCode()+"->"
                                +curatorEvent.getType());
                    }
                },executorService).forPath("/node/node1");

        System.in.read();

    }


    @Test
    public void delete() throws Exception{
        CuratorFramework curatorFramework = getCuratorFramework();
        curatorFramework.delete().deletingChildrenIfNeeded().forPath("/node");
    }


    @Test
    public void watch() throws Exception{
        CuratorFramework curatorFramework = getCuratorFramework();

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                .inBackground(new BackgroundCallback() {
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {

                        System.out.println("接收到watcher回调");

                        System.out.println(Thread.currentThread().getName()
                                +"->resultCode:"+curatorEvent.getResultCode()+"->"
                                +curatorEvent.getType());
                    }
                },executorService).forPath("/node/node1");

        System.in.read();
    }





}