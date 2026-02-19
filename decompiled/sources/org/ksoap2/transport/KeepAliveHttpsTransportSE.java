package org.ksoap2.transport;

import java.io.IOException;

public class KeepAliveHttpsTransportSE extends HttpsTransportSE {
    public KeepAliveHttpsTransportSE(String str, int i, String str2, int i2) {
        super(str, i, str2, i2);
    }

    public ServiceConnection getServiceConnection() throws IOException {
        HttpsServiceConnectionSEIgnoringConnectionClose httpsServiceConnectionSEIgnoringConnectionClose = new HttpsServiceConnectionSEIgnoringConnectionClose(this.host, this.port, this.file, this.timeout);
        httpsServiceConnectionSEIgnoringConnectionClose.setRequestProperty("Connection", "keep-alive");
        return httpsServiceConnectionSEIgnoringConnectionClose;
    }
}
