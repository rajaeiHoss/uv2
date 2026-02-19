package org.ksoap2.transport;

import java.io.IOException;

public class HttpsTransportSE extends HttpTransportSE {
    static final String PROTOCOL = "https";
    private static final String PROTOCOL_FULL = "https://";
    protected final String file;
    protected final String host;
    protected final int port;

    public HttpsTransportSE(java.lang.String r3, int r4, java.lang.String r5, int r6) {
        super(new StringBuilder().append(PROTOCOL_FULL).append(r3).append(':').append(r4).append(r5).toString(), r6);
        this.host = r3;
        this.port = r4;
        this.file = r5;
    }

    public HttpsTransportSE(java.net.Proxy r3, java.lang.String r4, int r5, java.lang.String r6, int r7) {
        super(r3, new StringBuilder().append(PROTOCOL_FULL).append(r4).append(':').append(r5).append(r6).toString());
        this.host = r4;
        this.port = r5;
        this.file = r6;
        this.timeout = r7;
    }

    public ServiceConnection getServiceConnection() throws IOException {
        return new HttpsServiceConnectionSE(this.proxy, this.host, this.port, this.file, this.timeout);
    }
}
