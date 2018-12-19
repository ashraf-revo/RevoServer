package org.revo.server.action;

import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

public class OnTearDown extends BaseAction{
    public OnTearDown(DefaultHttpRequest req) {
        super(req);
    }

    @Override
    public FullHttpResponse call() throws Exception {
        return super.call();
    }
}
