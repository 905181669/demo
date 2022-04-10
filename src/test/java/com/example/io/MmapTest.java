package com.example.io;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: luozijian
 * @date: 4/27/21 11:49:35
 * @description:
 *
 * mmap 和 sendFile 的区别
 * 1、mmap 适合小数据量读写，sendFile 适合大文件传输。
 *
 * 2、mmap 需要 4 次上下文切换，3 次数据拷贝；sendFile 需要 3 次上下文切换，最少 2 次数据拷贝。
 *
 * 2、sendFile 可以利用 DMA 方式，减少 CPU 拷贝，mmap 则不能（必须从内核拷贝到 Socket 缓冲区）。
 *
 * 链接：https://www.jianshu.com/p/3df7729299ab
 *
 *
 * 由于CPU和IO速度的差异问题，产生了DMA技术，通过DMA搬运来减少CPU的等待时间。
 *
 * 传统的IOread+write方式会产生2次DMA拷贝+2次CPU拷贝，同时有4次上下文切换。
 *
 * 而通过mmap+write方式则产生2次DMA拷贝+1次CPU拷贝，4次上下文切换，通过内存映射减少了一次CPU拷贝，可以减少内存使用，适合大文件的传输。
 *
 * sendfile方式是新增的一个系统调用函数，产生2次DMA拷贝+1次CPU拷贝，但是只有2次上下文切换。因为只有一次调用，减少了上下文的切换，但是用户空间对IO数据不可见，适用于静态文件服务器。
 *
 * sendfile+DMA gather方式产生2次DMA拷贝，没有CPU拷贝，而且也只有2次上下文切换。虽然极大地提升了性能，但是需要依赖新的硬件设备支持。
 *
 * 链接：https://www.jianshu.com/p/9a306e46e105
 *
 */
public class MmapTest {

    /**
     * 1.传统I/O
     * 硬盘—>内核缓冲区—>用户缓冲区—>内核socket缓冲区—>协议引擎
     *
     * 2.mmap
     * 用户态逻辑地址与内核态逻辑地址共享同一个物理地址
     * mmap()系统调用首先会使用DMA的方式将磁盘数据读取到内核缓冲区，然后通过内存映射的方式，
     * 使用户缓冲区和内核读缓冲区的内存地址为同一内存地址，也就是说不需要CPU再讲数据从内核读缓冲区复制到用户缓冲区。
     *
     *
     *
     * 3.sendfile(用户态无法获取文件数据)
     * 硬盘—>内核缓冲区—>内核socket缓冲区—>协议引擎
     *
     * 4.sendfile（ DMA 收集拷贝）
     * 硬盘—>内核缓冲区—>协议引擎
     *
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        File file = new File("/Users/luozijian/Downloads/to_generatorConfig.xml");
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");

        //获取对应的通道
        FileChannel fileChannel = randomAccessFile.getChannel();

        /**
         * 参数1：FileChannel.MapMode.READ_WRITE 使用的读写模式
         * 参数2：可以直接修改的起始位置
         * 参数3: 是映射到内存的大小(不是索引位置) ,即将 test.zip 的多少个字节映射到内存
         * 实际类型 DirectByteBuffer ，DirectByteBuffer是MappedByteBuffer子类
         */
        MappedByteBuffer mappedByteBuffer = fileChannel
                .map(FileChannel.MapMode.READ_WRITE, 0, fileChannel.size());

        System.out.println(mappedByteBuffer.isLoaded());
        mappedByteBuffer.load();

        int size = mappedByteBuffer.limit();
        byte[] bytes = new byte[size];
        for(int i = 0; i < size; i++){
            bytes[i] = mappedByteBuffer.get();
        }


        System.out.println(new String(bytes, "UTF-8"));

    }
}