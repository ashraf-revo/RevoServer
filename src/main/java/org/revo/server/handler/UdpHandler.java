package org.revo.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

import java.io.FileNotFoundException;
import java.io.IOException;

public class UdpHandler extends SimpleChannelInboundHandler<DatagramPacket> {

//    FileOutputStream out = new FileOutputStream("/home/ashraf/Downloads/Temp/ssa.h264");

    public UdpHandler() throws FileNotFoundException {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws IOException {
//        byte[] b = new byte[msg.content().readableBytes()];
//        msg.content().readBytes(b);
//        out.write(b);
        System.out.println("sssssssssssss");
    }


}
