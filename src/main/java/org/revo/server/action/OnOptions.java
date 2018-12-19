package org.revo.server.action;

import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.rtsp.RtspHeaders;

public class OnOptions extends BaseAction {

    public OnOptions(DefaultHttpRequest req) {
        super(req);
    }

    @Override
    public FullHttpResponse call() throws Exception {
        FullHttpResponse response = super.call();
        response.headers().add(RtspHeaders.Names.PUBLIC, "SETUP,PLAY,PAUSE,TEARDOWN,GET_PARAMETER,OPTION,RECORD,DESCRIBE,ANNOUNCE");
        return response;
    }
}
