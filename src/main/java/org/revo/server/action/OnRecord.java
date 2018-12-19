package org.revo.server.action;

import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.rtsp.RtspHeaderNames;

public class OnRecord extends BaseAction {
    public OnRecord(DefaultHttpRequest req) {
        super(req);
    }

    @Override
    public FullHttpResponse call() throws Exception {
        FullHttpResponse response = super.call();
        response.headers().set(RtspHeaderNames.SESSION, super.getReq().headers().get(RtspHeaderNames.SESSION));
        response.headers().set(RtspHeaderNames.RANGE, super.getReq().headers().get(RtspHeaderNames.RANGE));
        return response;
    }
}
