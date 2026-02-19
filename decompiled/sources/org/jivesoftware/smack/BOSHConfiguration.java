package org.jivesoftware.smack;

import java.net.URI;
import java.net.URISyntaxException;
import org.jivesoftware.smack.proxy.ProxyInfo;

public class BOSHConfiguration extends ConnectionConfiguration {
    private String file;
    private boolean ssl;

    public BOSHConfiguration(String str) {
        super(str, 7070);
        setSASLAuthenticationEnabled(true);
        this.ssl = false;
        this.file = "/http-bind/";
    }

    public BOSHConfiguration(String str, int i) {
        super(str, i);
        setSASLAuthenticationEnabled(true);
        this.ssl = false;
        this.file = "/http-bind/";
    }

    public BOSHConfiguration(boolean z, String str, int i, String str2, String str3) {
        super(str, i, str3);
        setSASLAuthenticationEnabled(true);
        this.ssl = z;
        this.file = str2 == null ? "/" : str2;
    }

    public BOSHConfiguration(boolean z, String str, int i, String str2, ProxyInfo proxyInfo, String str3) {
        super(str, i, str3, proxyInfo);
        setSASLAuthenticationEnabled(true);
        this.ssl = z;
        this.file = str2 == null ? "/" : str2;
    }

    public boolean isProxyEnabled() {
        return (this.proxy == null || this.proxy.getProxyType() == ProxyInfo.ProxyType.NONE) ? false : true;
    }

    public ProxyInfo getProxyInfo() {
        return this.proxy;
    }

    public String getProxyAddress() {
        if (this.proxy != null) {
            return this.proxy.getProxyAddress();
        }
        return null;
    }

    public int getProxyPort() {
        if (this.proxy != null) {
            return this.proxy.getProxyPort();
        }
        return 8080;
    }

    public boolean isUsingSSL() {
        return this.ssl;
    }

    public URI getURI() throws URISyntaxException {
        if (this.file.charAt(0) != '/') {
            this.file = '/' + this.file;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.ssl ? "https://" : "http://");
        sb.append(getHost());
        sb.append(":");
        sb.append(getPort());
        sb.append(this.file);
        return new URI(sb.toString());
    }
}
