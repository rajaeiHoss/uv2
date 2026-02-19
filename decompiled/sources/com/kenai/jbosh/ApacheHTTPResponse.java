package com.kenai.jbosh;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

final class ApacheHTTPResponse implements HTTPResponse {
    private static final String ACCEPT_ENCODING = "Accept-Encoding";
    private static final String ACCEPT_ENCODING_VAL = (ZLIBCodec.getID() + ", " + GZIPCodec.getID());
    private static final String CHARSET = "UTF-8";
    private static final String CONTENT_TYPE = "text/xml; charset=utf-8";
    private AbstractBody body;
    private final HttpClient client;
    private final HttpContext context;
    private final Lock lock = new ReentrantLock();
    private final HttpPost post;
    private boolean sent;
    private int statusCode;
    private BOSHException toThrow;

    ApacheHTTPResponse(HttpClient httpClient, BOSHClientConfig bOSHClientConfig, CMSessionParams cMSessionParams, AbstractBody abstractBody) {
        AttrAccept accept;
        this.client = httpClient;
        this.context = new BasicHttpContext();
        HttpPost httpPost = new HttpPost(bOSHClientConfig.getURI().toString());
        this.post = httpPost;
        this.sent = false;
        try {
            byte[] bytes = abstractBody.toXML().getBytes(CHARSET);
            String str = null;
            if (!(!bOSHClientConfig.isCompressionEnabled() || cMSessionParams == null || (accept = cMSessionParams.getAccept()) == null)) {
                if (accept.isAccepted(ZLIBCodec.getID())) {
                    str = ZLIBCodec.getID();
                    bytes = ZLIBCodec.encode(bytes);
                } else if (accept.isAccepted(GZIPCodec.getID())) {
                    str = GZIPCodec.getID();
                    bytes = GZIPCodec.encode(bytes);
                }
            }
            ByteArrayEntity byteArrayEntity = new ByteArrayEntity(bytes);
            byteArrayEntity.setContentType(CONTENT_TYPE);
            if (str != null) {
                byteArrayEntity.setContentEncoding(str);
            }
            httpPost.setEntity(byteArrayEntity);
            if (bOSHClientConfig.isCompressionEnabled()) {
                httpPost.setHeader(ACCEPT_ENCODING, ACCEPT_ENCODING_VAL);
            }
        } catch (Exception e) {
            this.toThrow = new BOSHException("Could not generate request", e);
        }
    }

    public void abort() {
        HttpPost httpPost = this.post;
        if (httpPost != null) {
            httpPost.abort();
            this.toThrow = new BOSHException("HTTP request aborted");
        }
    }

    /* JADX INFO: finally extract failed */
    public AbstractBody getBody() throws InterruptedException, BOSHException {
        BOSHException bOSHException = this.toThrow;
        if (bOSHException == null) {
            this.lock.lock();
            try {
                if (!this.sent) {
                    awaitResponse();
                }
                this.lock.unlock();
                return this.body;
            } catch (Throwable th) {
                this.lock.unlock();
                throw th;
            }
        } else {
            throw bOSHException;
        }
    }

    /* JADX INFO: finally extract failed */
    public int getHTTPStatus() throws InterruptedException, BOSHException {
        BOSHException bOSHException = this.toThrow;
        if (bOSHException == null) {
            this.lock.lock();
            try {
                if (!this.sent) {
                    awaitResponse();
                }
                this.lock.unlock();
                return this.statusCode;
            } catch (Throwable th) {
                this.lock.unlock();
                throw th;
            }
        } else {
            throw bOSHException;
        }
    }

    private synchronized void awaitResponse() throws BOSHException {
        try {
            HttpResponse execute = this.client.execute(this.post, this.context);
            HttpEntity entity = execute.getEntity();
            byte[] byteArray = EntityUtils.toByteArray(entity);
            String value = entity.getContentEncoding() != null ? entity.getContentEncoding().getValue() : null;
            if (ZLIBCodec.getID().equalsIgnoreCase(value)) {
                byteArray = ZLIBCodec.decode(byteArray);
            } else if (GZIPCodec.getID().equalsIgnoreCase(value)) {
                byteArray = GZIPCodec.decode(byteArray);
            }
            this.body = StaticBody.fromString(new String(byteArray, CHARSET));
            this.statusCode = execute.getStatusLine().getStatusCode();
            this.sent = true;
        } catch (IOException e) {
            abort();
            BOSHException bOSHException = new BOSHException("Could not obtain response", e);
            this.toThrow = bOSHException;
            throw bOSHException;
        } catch (RuntimeException e2) {
            abort();
            throw e2;
        }
    }
}
