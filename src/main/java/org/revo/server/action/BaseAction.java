package org.revo.server.action;

import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.rtsp.RtspHeaders;
import io.netty.handler.codec.rtsp.RtspResponseStatuses;
import io.netty.handler.codec.rtsp.RtspVersions;

import java.util.Map;
import java.util.concurrent.Callable;

public abstract class BaseAction implements Callable<FullHttpResponse> {
    private DefaultHttpRequest req;

    public BaseAction(DefaultHttpRequest req) {
        this.req = req;
//        debug();
    }

    private void debug() {
        System.out.println("******************  " + req.getMethod() + "  ********************");
        for (Map.Entry<String, String> header : this.req.headers()) {
            System.out.println(header.getKey() + "      " + header.getValue());
        }
        System.out.println("**************************************");
    }

    @Override
    public FullHttpResponse call() throws Exception {
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.OK);
        if (this.getReq().headers().get(RtspHeaders.Names.CSEQ) != null)
            response.headers().add(RtspHeaders.Names.CSEQ, this.getReq().headers().get(RtspHeaders.Names.CSEQ));
        return response;
    }

    public DefaultHttpRequest getReq() {
        return req;
    }

    public void setReq(DefaultHttpRequest req) {
        this.req = req;
    }
}
