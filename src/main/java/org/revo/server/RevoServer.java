package org.revo.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.rtsp.RtspRequestDecoder;
import io.netty.handler.codec.rtsp.RtspResponseEncoder;
import org.revo.server.handler.ServerHandler;
import org.revo.server.handler.UdpHandler;

import java.io.FileNotFoundException;

public class RevoServer {
    private static ServerBootstrap rtspstream = new ServerBootstrap();
    private static Bootstrap udpstrap = new Bootstrap();
    private static ServerConfig serverConfig = new ServerConfig();

    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            initUdp(bossGroup);
            initRtsp(bossGroup, workerGroup);
            Channel ch1 = rtspstream.bind(serverConfig.port).sync().channel();
            Channel ch2 = udpstrap.bind(serverConfig.port).sync().channel();
            System.err.println("Connect to rtsp://127.0.0.1:" + serverConfig.port);
            ch1.closeFuture().sync();
            ch2.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


    public static void initUdp(EventLoopGroup bossGroup) {
        udpstrap.group(bossGroup)
                .channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_SNDBUF, 1024 * 1024)
                .handler(new ChannelInitializer<NioDatagramChannel>() {
                    @Override
                    protected void initChannel(NioDatagramChannel nioDatagramChannel) throws FileNotFoundException {
                        ChannelPipeline p = nioDatagramChannel.pipeline();
                        p.addLast(new UdpHandler());
                    }
                })
                .option(ChannelOption.SO_BROADCAST, false);
    }

    public static void initRtsp(EventLoopGroup bossGroup, EventLoopGroup workerGroup) {
        rtspstream.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new RtspRequestDecoder());
                        p.addLast(new RtspResponseEncoder());
                        p.addLast(new ServerHandler(serverConfig));
                    }
                });
    }

}