package org.revo.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

public class UdpHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    public UdpHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) {
        System.out.println(new String(read(msg)));
    }

    private byte[] read(DatagramPacket msg) {
        byte[] b = new byte[msg.content().readableBytes()];
        msg.content().readBytes(b);
        return b;
    }
}
