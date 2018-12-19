package org.revo.server.action;

import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;

public class OnDescribe extends BaseAction{
    public OnDescribe(DefaultHttpRequest req) {
        super(req);
    }

    @Override
    public FullHttpResponse call() throws Exception {
        FullHttpResponse response = super.call();
        response.headers().set(HttpHeaders.Names.CONTENT_BASE,super.getReq().uri());
        response.headers().set(HttpHeaders.Names.CONTENT_TYPE,"application/sdp");
        return response;
    }
}
