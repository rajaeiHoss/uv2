package org.jivesoftware.smack.proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.SocketFactory;
import org.jivesoftware.smack.proxy.ProxyInfo;
import org.jivesoftware.smack.util.Base64;

class HTTPProxySocketFactory extends SocketFactory {
    private static final Pattern RESPONSE_PATTERN = Pattern.compile("HTTP/\\S+\\s(\\d+)\\s(.*)\\s*");
    private ProxyInfo proxy;

    public HTTPProxySocketFactory(ProxyInfo proxyInfo) {
        this.proxy = proxyInfo;
    }

    public Socket createSocket(String str, int i) throws IOException, UnknownHostException {
        return httpProxifiedSocket(str, i);
    }

    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException, UnknownHostException {
        return httpProxifiedSocket(str, i);
    }

    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        return httpProxifiedSocket(inetAddress.getHostAddress(), i);
    }

    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        return httpProxifiedSocket(inetAddress.getHostAddress(), i);
    }

    private Socket httpProxifiedSocket(String str, int i) throws IOException {
        String str2;
        String proxyAddress = this.proxy.getProxyAddress();
        Socket socket = new Socket(proxyAddress, this.proxy.getProxyPort());
        String str3 = "CONNECT " + str + ":" + i;
        String proxyUsername = this.proxy.getProxyUsername();
        if (proxyUsername == null) {
            str2 = "";
        } else {
            String proxyPassword = this.proxy.getProxyPassword();
            StringBuilder sb = new StringBuilder();
            sb.append("\r\nProxy-Authorization: Basic ");
            sb.append(new String(Base64.encodeBytes((proxyUsername + ":" + proxyPassword).getBytes("UTF-8"))));
            str2 = sb.toString();
        }
        socket.getOutputStream().write((str3 + " HTTP/1.1\r\nHost: " + str3 + str2 + "\r\n\r\n").getBytes("UTF-8"));
        InputStream inputStream = socket.getInputStream();
        StringBuilder sb2 = new StringBuilder(100);
        int i2 = 0;
        do {
            char read = (char) inputStream.read();
            sb2.append(read);
            if (sb2.length() > 1024) {
                throw new ProxyException(ProxyInfo.ProxyType.HTTP, "Recieved header of >1024 characters from " + proxyAddress + ", cancelling connection");
            } else if (read != 65535) {
                i2 = (((i2 == 0 || i2 == 2) && read == 13) || ((i2 == 1 || i2 == 3) && read == 10)) ? i2 + 1 : 0;
            } else {
                throw new ProxyException(ProxyInfo.ProxyType.HTTP);
            }
        } while (i2 != 4);
        if (i2 == 4) {
            String readLine = new BufferedReader(new StringReader(sb2.toString())).readLine();
            if (readLine != null) {
                Matcher matcher = RESPONSE_PATTERN.matcher(readLine);
                if (!matcher.matches()) {
                    throw new ProxyException(ProxyInfo.ProxyType.HTTP, "Unexpected proxy response from " + proxyAddress + ": " + readLine);
                } else if (Integer.parseInt(matcher.group(1)) == 200) {
                    return socket;
                } else {
                    throw new ProxyException(ProxyInfo.ProxyType.HTTP);
                }
            } else {
                throw new ProxyException(ProxyInfo.ProxyType.HTTP, "Empty proxy response from " + proxyAddress + ", cancelling");
            }
        } else {
            throw new ProxyException(ProxyInfo.ProxyType.HTTP, "Never received blank line from " + proxyAddress + ", cancelling connection");
        }
    }
}
