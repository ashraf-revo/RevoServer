package org.revo.server.action;

import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

public class OnAnnounce extends BaseAction {
    public OnAnnounce(DefaultHttpRequest req) {
        super(req);
    }

    @Override
    public FullHttpResponse call() throws Exception {
        return super.call();
    }
}
