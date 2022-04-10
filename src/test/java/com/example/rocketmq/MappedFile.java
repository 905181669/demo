package com.example.rocketmq;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: luozijian
 * @date: 2021/11/26 15:35:50
 * @description:
 */
public class MappedFile {





    private static final AtomicLong TOTAL_MAPPED_VIRTUAL_MEMORY = new AtomicLong(0);

    private static final AtomicInteger TOTAL_MAPPED_FILES = new AtomicInteger(0);
    protected final AtomicInteger wrotePosition = new AtomicInteger(0);
    protected final AtomicInteger committedPosition = new AtomicInteger(0);
    private final AtomicInteger flushedPosition = new AtomicInteger(0);
    protected int fileSize;
    protected FileChannel fileChannel;
    /**
     * Message will put to here first, and then reput to FileChannel if writeBuffer is not null.
     */
    protected ByteBuffer writeBuffer = null;
    private String fileName;
    private long fileFromOffset;
    private File file;
    private MappedByteBuffer mappedByteBuffer;
    private volatile long storeTimestamp = 0;
    private boolean firstCreateInQueue = false;

    private void init(final String fileName, final int fileSize) throws IOException {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.file = new File(fileName);
        this.fileFromOffset = Long.parseLong(this.file.getName());
        boolean ok = false;

        try {
            this.fileChannel = new RandomAccessFile(this.file, "rw").getChannel();
            this.mappedByteBuffer = this.fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileSize);
            TOTAL_MAPPED_VIRTUAL_MEMORY.addAndGet(fileSize);
            TOTAL_MAPPED_FILES.incrementAndGet();
            ok = true;
        }  catch (IOException e) {
            throw e;
        } finally {
            if (!ok && this.fileChannel != null) {
                this.fileChannel.close();
            }
        }
    }

    public MappedFile(final String fileName, final int fileSize) throws IOException {
        init(fileName, fileSize);
    }

    public boolean appendMessage(final byte[] data) {
        int currentPos = this.wrotePosition.get();

        if ((currentPos + data.length) <= this.fileSize) {
            try {
                this.fileChannel.position(currentPos);
                this.fileChannel.write(ByteBuffer.wrap(data));
            } catch (Throwable e) {
                e.printStackTrace();
            }
            this.wrotePosition.addAndGet(data.length);
            return true;
        }

        return false;
    }

    public boolean appendMessage(final byte[] data, final int offset, final int length) {
        int currentPos = this.wrotePosition.get();

        if ((currentPos + length) <= this.fileSize) {
            try {
                this.fileChannel.position(currentPos);
                this.fileChannel.write(ByteBuffer.wrap(data, offset, length));
            } catch (Throwable e) {
                e.printStackTrace();
            }
            this.wrotePosition.addAndGet(length);
            return true;
        }

        return false;
    }

    public void flush(){
        try{
            this.fileChannel.force(false);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception{

        MappedFile file = new MappedFile("/Users/luozijian/Downloads/000000", 1024);

        file.appendMessage("hello".getBytes(StandardCharsets.UTF_8));

        file.appendMessage(" luozjian".getBytes(StandardCharsets.UTF_8));

        file.appendMessage(" phone".getBytes(StandardCharsets.UTF_8));


    }
}