package org.ksoap2.transport;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Proxy;
import java.util.List;
import java.util.zip.GZIPInputStream;
import org.ksoap2.SoapEnvelope;
import org.xmlpull.v1.XmlPullParserException;

public class HttpTransportSE extends Transport {
    public HttpTransportSE(String str) {
        super((Proxy) null, str);
    }

    public HttpTransportSE(Proxy proxy, String str) {
        super(proxy, str);
    }

    public HttpTransportSE(String str, int i) {
        super(str, i);
    }

    public HttpTransportSE(Proxy proxy, String str, int i) {
        super(proxy, str, i);
    }

    public HttpTransportSE(String str, int i, int i2) {
        super(str, i);
    }

    public HttpTransportSE(Proxy proxy, String str, int i, int i2) {
        super(proxy, str, i);
    }

    public void call(String str, SoapEnvelope soapEnvelope) throws HttpResponseException, IOException, XmlPullParserException {
        call(str, soapEnvelope, (List) null);
    }

    public List call(String str, SoapEnvelope soapEnvelope, List list) throws HttpResponseException, IOException, XmlPullParserException {
        return call(str, soapEnvelope, list, (File) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:63:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x015a  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x017c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List call(java.lang.String r17, org.ksoap2.SoapEnvelope r18, java.util.List r19, java.io.File r20) throws org.ksoap2.transport.HttpResponseException, java.io.IOException, org.xmlpull.v1.XmlPullParserException {
        /*
            r16 = this;
            r1 = r16
            r2 = r18
            r0 = r19
            r3 = r20
            if (r17 != 0) goto L_0x000d
            java.lang.String r4 = "\"\""
            goto L_0x000f
        L_0x000d:
            r4 = r17
        L_0x000f:
            java.lang.String r5 = "UTF-8"
            byte[] r5 = r1.createRequestData(r2, r5)
            boolean r6 = r1.debug
            r7 = 0
            if (r6 == 0) goto L_0x0020
            java.lang.String r6 = new java.lang.String
            r6.<init>(r5)
            goto L_0x0021
        L_0x0020:
            r6 = r7
        L_0x0021:
            r1.requestDump = r6
            r1.responseDump = r7
            org.ksoap2.transport.ServiceConnection r6 = r16.getServiceConnection()
            java.lang.String r8 = "User-Agent"
            java.lang.String r9 = "ksoap2-android/2.6.0+"
            r6.setRequestProperty(r8, r9)
            int r8 = r2.version
            r9 = 120(0x78, float:1.68E-43)
            if (r8 == r9) goto L_0x003b
            java.lang.String r8 = "SOAPAction"
            r6.setRequestProperty(r8, r4)
        L_0x003b:
            int r4 = r2.version
            java.lang.String r8 = "Content-Type"
            if (r4 != r9) goto L_0x0047
            java.lang.String r4 = "application/soap+xml;charset=utf-8"
            r6.setRequestProperty(r8, r4)
            goto L_0x004c
        L_0x0047:
            java.lang.String r4 = "text/xml;charset=utf-8"
            r6.setRequestProperty(r8, r4)
        L_0x004c:
            java.lang.String r4 = "Accept-Encoding"
            java.lang.String r9 = "gzip"
            r6.setRequestProperty(r4, r9)
            java.lang.StringBuffer r4 = new java.lang.StringBuffer
            r4.<init>()
            java.lang.String r10 = ""
            r4.append(r10)
            int r10 = r5.length
            r4.append(r10)
            java.lang.String r4 = r4.toString()
            java.lang.String r10 = "Content-Length"
            r6.setRequestProperty(r10, r4)
            int r4 = r5.length
            r6.setFixedLengthStreamingMode(r4)
            r4 = 0
            if (r0 == 0) goto L_0x008c
            r10 = 0
        L_0x0072:
            int r11 = r19.size()
            if (r10 >= r11) goto L_0x008c
            java.lang.Object r11 = r0.get(r10)
            org.ksoap2.HeaderProperty r11 = (org.ksoap2.HeaderProperty) r11
            java.lang.String r12 = r11.getKey()
            java.lang.String r11 = r11.getValue()
            r6.setRequestProperty(r12, r11)
            int r10 = r10 + 1
            goto L_0x0072
        L_0x008c:
            java.lang.String r0 = "POST"
            r6.setRequestMethod(r0)
            java.io.OutputStream r0 = r6.openOutputStream()
            int r10 = r5.length
            r0.write(r5, r4, r10)
            r0.flush()
            r0.close()
            int r0 = r6.getResponseCode()
            java.util.List r7 = r6.getResponseProperties()     // Catch:{ IOException -> 0x0146 }
            r10 = 0
            r11 = 0
            r12 = 8192(0x2000, float:1.14794E-41)
        L_0x00ab:
            int r13 = r7.size()     // Catch:{ IOException -> 0x0142 }
            if (r4 >= r13) goto L_0x010d
            java.lang.Object r13 = r7.get(r4)     // Catch:{ IOException -> 0x0142 }
            org.ksoap2.HeaderProperty r13 = (org.ksoap2.HeaderProperty) r13     // Catch:{ IOException -> 0x0142 }
            java.lang.String r14 = r13.getKey()     // Catch:{ IOException -> 0x0142 }
            r15 = 1
            if (r14 != 0) goto L_0x00bf
            goto L_0x010a
        L_0x00bf:
            java.lang.String r14 = r13.getKey()     // Catch:{ IOException -> 0x0142 }
            java.lang.String r5 = "content-length"
            boolean r5 = r14.equalsIgnoreCase(r5)     // Catch:{ IOException -> 0x0142 }
            if (r5 == 0) goto L_0x00dc
            java.lang.String r5 = r13.getValue()     // Catch:{ IOException -> 0x0142 }
            if (r5 == 0) goto L_0x00dc
            java.lang.String r5 = r13.getValue()     // Catch:{ NumberFormatException -> 0x00da }
            int r12 = java.lang.Integer.parseInt(r5)     // Catch:{ NumberFormatException -> 0x00da }
            goto L_0x00dc
        L_0x00da:
            r12 = 8192(0x2000, float:1.14794E-41)
        L_0x00dc:
            java.lang.String r5 = r13.getKey()     // Catch:{ IOException -> 0x0142 }
            boolean r5 = r5.equalsIgnoreCase(r8)     // Catch:{ IOException -> 0x0142 }
            if (r5 == 0) goto L_0x00f3
            java.lang.String r5 = r13.getValue()     // Catch:{ IOException -> 0x0142 }
            java.lang.String r14 = "xml"
            boolean r5 = r5.contains(r14)     // Catch:{ IOException -> 0x0142 }
            if (r5 == 0) goto L_0x00f3
            r11 = 1
        L_0x00f3:
            java.lang.String r5 = r13.getKey()     // Catch:{ IOException -> 0x0142 }
            java.lang.String r14 = "Content-Encoding"
            boolean r5 = r5.equalsIgnoreCase(r14)     // Catch:{ IOException -> 0x0142 }
            if (r5 == 0) goto L_0x010a
            java.lang.String r5 = r13.getValue()     // Catch:{ IOException -> 0x0142 }
            boolean r5 = r5.equalsIgnoreCase(r9)     // Catch:{ IOException -> 0x0142 }
            if (r5 == 0) goto L_0x010a
            r10 = 1
        L_0x010a:
            int r4 = r4 + 1
            goto L_0x00ab
        L_0x010d:
            r4 = 200(0xc8, float:2.8E-43)
            if (r0 != r4) goto L_0x012b
            if (r10 == 0) goto L_0x0121
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0142 }
            java.io.InputStream r4 = r6.openInputStream()     // Catch:{ IOException -> 0x0142 }
            r0.<init>(r4, r12)     // Catch:{ IOException -> 0x0142 }
            java.io.InputStream r0 = r1.getUnZippedInputStream(r0)     // Catch:{ IOException -> 0x0142 }
            goto L_0x0178
        L_0x0121:
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0142 }
            java.io.InputStream r4 = r6.openInputStream()     // Catch:{ IOException -> 0x0142 }
            r0.<init>(r4, r12)     // Catch:{ IOException -> 0x0142 }
            goto L_0x0178
        L_0x012b:
            org.ksoap2.transport.HttpResponseException r4 = new org.ksoap2.transport.HttpResponseException     // Catch:{ IOException -> 0x0142 }
            java.lang.StringBuffer r5 = new java.lang.StringBuffer     // Catch:{ IOException -> 0x0142 }
            r5.<init>()     // Catch:{ IOException -> 0x0142 }
            java.lang.String r8 = "HTTP request failed, HTTP status: "
            r5.append(r8)     // Catch:{ IOException -> 0x0142 }
            r5.append(r0)     // Catch:{ IOException -> 0x0142 }
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x0142 }
            r4.<init>((java.lang.String) r5, (int) r0)     // Catch:{ IOException -> 0x0142 }
            throw r4     // Catch:{ IOException -> 0x0142 }
        L_0x0142:
            r0 = move-exception
            r4 = r10
            r5 = r12
            goto L_0x014a
        L_0x0146:
            r0 = move-exception
            r5 = 8192(0x2000, float:1.14794E-41)
            r11 = 0
        L_0x014a:
            if (r4 == 0) goto L_0x015a
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream
            java.io.InputStream r8 = r6.getErrorStream()
            r4.<init>(r8, r5)
            java.io.InputStream r4 = r1.getUnZippedInputStream(r4)
            goto L_0x0163
        L_0x015a:
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream
            java.io.InputStream r8 = r6.getErrorStream()
            r4.<init>(r8, r5)
        L_0x0163:
            boolean r8 = r0 instanceof org.ksoap2.transport.HttpResponseException
            if (r8 == 0) goto L_0x0176
            if (r11 != 0) goto L_0x0176
            boolean r2 = r1.debug
            if (r2 == 0) goto L_0x0172
            if (r4 == 0) goto L_0x0172
            r1.readDebug(r4, r5, r3)
        L_0x0172:
            r6.disconnect()
            throw r0
        L_0x0176:
            r0 = r4
            r12 = r5
        L_0x0178:
            boolean r4 = r1.debug
            if (r4 == 0) goto L_0x0180
            java.io.InputStream r0 = r1.readDebug(r0, r12, r3)
        L_0x0180:
            r1.parseResponse(r2, r0)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.ksoap2.transport.HttpTransportSE.call(java.lang.String, org.ksoap2.SoapEnvelope, java.util.List, java.io.File):java.util.List");
    }

    private InputStream readDebug(InputStream inputStream, int i, File file) throws IOException {
        OutputStream outputStream;
        if (file != null) {
            outputStream = new FileOutputStream(file);
        } else {
            if (i <= 0) {
                i = 262144;
            }
            outputStream = new ByteArrayOutputStream(i);
        }
        byte[] bArr = new byte[256];
        while (true) {
            int read = inputStream.read(bArr, 0, 256);
            if (read == -1) {
                break;
            }
            outputStream.write(bArr, 0, read);
        }
        outputStream.flush();
        if (outputStream instanceof ByteArrayOutputStream) {
            bArr = ((ByteArrayOutputStream) outputStream).toByteArray();
        }
        this.responseDump = new String(bArr);
        inputStream.close();
        if (file != null) {
            return new FileInputStream(file);
        }
        return new ByteArrayInputStream(bArr);
    }

    private InputStream getUnZippedInputStream(InputStream inputStream) throws IOException {
        try {
            return (GZIPInputStream) inputStream;
        } catch (ClassCastException unused) {
            return new GZIPInputStream(inputStream);
        }
    }

    public ServiceConnection getServiceConnection() throws IOException {
        return new ServiceConnectionSE(this.proxy, this.url, this.timeout);
    }
}
