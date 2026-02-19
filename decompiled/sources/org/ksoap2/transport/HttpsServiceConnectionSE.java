package org.ksoap2.transport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Proxy;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.ksoap2.HeaderProperty;

public class HttpsServiceConnectionSE implements ServiceConnection {
    private HttpsURLConnection connection;

    public HttpsServiceConnectionSE(String str, int i, String str2, int i2) throws IOException {
        this((Proxy) null, str, i, str2, i2);
    }

    public HttpsServiceConnectionSE(Proxy proxy, String str, int i, String str2, int i2) throws IOException {
        if (proxy == null) {
            this.connection = (HttpsURLConnection) new URL("https", str, i, str2).openConnection();
        } else {
            this.connection = (HttpsURLConnection) new URL("https", str, i, str2).openConnection(proxy);
        }
        this.connection = (HttpsURLConnection) new URL("https", str, i, str2).openConnection();
        updateConnectionParameters(i2);
    }

    private void updateConnectionParameters(int i) {
        this.connection.setConnectTimeout(i);
        this.connection.setReadTimeout(i);
        this.connection.setUseCaches(false);
        this.connection.setDoOutput(true);
        this.connection.setDoInput(true);
    }

    public void connect() throws IOException {
        this.connection.connect();
    }

    public void disconnect() {
        this.connection.disconnect();
    }

    public List getResponseProperties() {
        Map headerFields = this.connection.getHeaderFields();
        Set<String> keySet = headerFields.keySet();
        LinkedList linkedList = new LinkedList();
        for (String str : keySet) {
            List list = (List) headerFields.get(str);
            for (int i = 0; i < list.size(); i++) {
                linkedList.add(new HeaderProperty(str, (String) list.get(i)));
            }
        }
        return linkedList;
    }

    public int getResponseCode() throws IOException {
        return this.connection.getResponseCode();
    }

    public void setRequestProperty(String str, String str2) {
        this.connection.setRequestProperty(str, str2);
    }

    public void setRequestMethod(String str) throws IOException {
        this.connection.setRequestMethod(str);
    }

    public void setFixedLengthStreamingMode(int i) {
        this.connection.setFixedLengthStreamingMode(i);
    }

    public OutputStream openOutputStream() throws IOException {
        return this.connection.getOutputStream();
    }

    public InputStream openInputStream() throws IOException {
        return this.connection.getInputStream();
    }

    public InputStream getErrorStream() {
        return this.connection.getErrorStream();
    }

    public String getHost() {
        return this.connection.getURL().getHost();
    }

    public int getPort() {
        return this.connection.getURL().getPort();
    }

    public String getPath() {
        return this.connection.getURL().getPath();
    }

    public void setSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.connection.setSSLSocketFactory(sSLSocketFactory);
    }
}
