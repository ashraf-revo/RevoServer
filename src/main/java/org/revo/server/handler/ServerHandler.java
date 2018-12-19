package org.revo.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.rtsp.RtspMethods;
import org.revo.server.ServerConfig;
import org.revo.server.action.*;

public class ServerHandler extends SimpleChannelInboundHandler<DefaultHttpRequest> {
    private ServerConfig serverConfig;

    public ServerHandler(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DefaultHttpRequest req) throws Exception {
        if (req.method() == RtspMethods.OPTIONS) {
            ctx.writeAndFlush(new OnOptions(req).call());
        } else if (req.method() == RtspMethods.ANNOUNCE) {
            ctx.writeAndFlush(new OnAnnounce(req).call());
        } else if (req.method() == RtspMethods.SETUP) {
            ctx.writeAndFlush(new OnSetup(req, serverConfig).call());
        } else if (req.method() == RtspMethods.RECORD) {
            ctx.writeAndFlush(new OnRecord(req).call());
        } else if (req.method() == RtspMethods.DESCRIBE) {
            ctx.writeAndFlush(new OnDescribe(req).call());
        } else if (req.method() == RtspMethods.TEARDOWN) {
            ctx.writeAndFlush(new OnTearDown(req).call());
        }
        else {
            System.out.println(req.getMethod());
        }
    }
}
