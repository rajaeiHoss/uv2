package com.kenai.jbosh;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

final class HTTPExchange {
    private static final Logger LOG = Logger.getLogger(HTTPExchange.class.getName());
    private final Lock lock;
    private final Condition ready;
    private final AbstractBody request;
    private HTTPResponse response;

    HTTPExchange(AbstractBody abstractBody) {
        ReentrantLock reentrantLock = new ReentrantLock();
        this.lock = reentrantLock;
        this.ready = reentrantLock.newCondition();
        if (abstractBody != null) {
            this.request = abstractBody;
            return;
        }
        throw new IllegalArgumentException("Request body cannot be null");
    }

    /* access modifiers changed from: package-private */
    public AbstractBody getRequest() {
        return this.request;
    }

    /* access modifiers changed from: package-private */
    public void setHTTPResponse(HTTPResponse hTTPResponse) {
        this.lock.lock();
        try {
            if (this.response == null) {
                this.response = hTTPResponse;
                this.ready.signalAll();
                return;
            }
            throw new IllegalStateException("HTTPResponse was already set");
        } finally {
            this.lock.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public HTTPResponse getHTTPResponse() {
        this.lock.lock();
        while (true) {
            try {
                HTTPResponse hTTPResponse = this.response;
                if (hTTPResponse == null) {
                    this.ready.await();
                } else {
                    this.lock.unlock();
                    return hTTPResponse;
                }
            } catch (InterruptedException e) {
                LOG.log(Level.FINEST, "Interrupted", e);
            } catch (Throwable th) {
                this.lock.unlock();
                throw th;
            }
        }
    }
}
