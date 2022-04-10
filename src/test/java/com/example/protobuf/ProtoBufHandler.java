package com.example.protobuf;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: luozijian
 * @date: 9/2/21 18:13:19
 * @description:
 */
public class ProtoBufHandler extends SimpleChannelInboundHandler<Object> {

    /**
     * 客户端在netty-study项目
     * @param channelHandlerContext
     * @param o
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        if(o instanceof ByteBuf){
            ByteBuf buf = (ByteBuf) o;
            byte[] bytes = new byte[buf.readableBytes()];
            buf.readBytes(bytes);
            PersonTestProtos.PersonTest personTestResult = PersonTestProtos.PersonTest.parseFrom(bytes);
            System.out.println(String.format("反序列化得到的信息，姓名：%s，性别：%d，手机号：%s", personTestResult.getName(), personTestResult.getSexValue(), personTestResult.getPhone(0).getNumber()));
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }
}