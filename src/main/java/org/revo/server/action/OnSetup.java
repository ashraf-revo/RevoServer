package org.revo.server.action;

import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.rtsp.RtspHeaderNames;
import org.revo.server.ServerConfig;

public class OnSetup extends BaseAction {
    private final ServerConfig serverConfig;

    public OnSetup(DefaultHttpRequest req, ServerConfig serverConfig) {
        super(req);
        this.serverConfig = serverConfig;
    }

    @Override
    public FullHttpResponse call() throws Exception {
        FullHttpResponse response = super.call();
        response.headers().set(RtspHeaderNames.TRANSPORT, super.getReq().headers().get(RtspHeaderNames.TRANSPORT) + String.format(";server_port=%d-%d", serverConfig.port, serverConfig.port));
        response.headers().set(RtspHeaderNames.SESSION, "123456");
        return response;
    }
}
