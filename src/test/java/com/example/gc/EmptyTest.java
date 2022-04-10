package com.example.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: luozijian
 * @date: 7/27/21 09:13:52
 * @description:
 */
public class EmptyTest {

    public static void main(String[] args) throws InterruptedException{
        //加到集合中，使垃圾无法回收
        List<EmptyObject> emptys = new ArrayList<>();
        for(int i=0; i<100; i++){
            emptys.add(new EmptyObject());
        }
        //打开jvisualvm,查看EmptyObject的大小为16字节
        Thread.sleep(600*1000);
    }

    /**
     * 开启指针压缩：64(Mark Word)+32(Compressed oops)+32(int)=128bits=16bytes
     * 不开启：64(Mark Word)+64(Compressed oops)+32(int)=160bits=20bytes + 4byte padding = 24 bytes
     * 利用jvisualvm观察符合预期结果
     */
    private static class EmptyObject{
        private int a = 1;
//        private int b = 1;
    }
}