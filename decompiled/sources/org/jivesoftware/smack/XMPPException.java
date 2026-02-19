package org.jivesoftware.smack;

import java.io.PrintStream;
import java.io.PrintWriter;
import org.jivesoftware.smack.packet.StreamError;
import org.jivesoftware.smack.packet.XMPPError;

public class XMPPException extends Exception {
    private XMPPError error = null;
    private StreamError streamError = null;
    private Throwable wrappedThrowable = null;

    public XMPPException() {
    }

    public XMPPException(String str) {
        super(str);
    }

    public XMPPException(Throwable th) {
        this.wrappedThrowable = th;
    }

    public XMPPException(StreamError streamError2) {
        this.streamError = streamError2;
    }

    public XMPPException(XMPPError xMPPError) {
        this.error = xMPPError;
    }

    public XMPPException(String str, Throwable th) {
        super(str);
        this.wrappedThrowable = th;
    }

    public XMPPException(String str, XMPPError xMPPError, Throwable th) {
        super(str);
        this.error = xMPPError;
        this.wrappedThrowable = th;
    }

    public XMPPException(String str, XMPPError xMPPError) {
        super(str);
        this.error = xMPPError;
    }

    public XMPPError getXMPPError() {
        return this.error;
    }

    public StreamError getStreamError() {
        return this.streamError;
    }

    public Throwable getWrappedThrowable() {
        return this.wrappedThrowable;
    }

    public void printStackTrace() {
        printStackTrace(System.err);
    }

    public void printStackTrace(PrintStream printStream) {
        super.printStackTrace(printStream);
        if (this.wrappedThrowable != null) {
            printStream.println("Nested Exception: ");
            this.wrappedThrowable.printStackTrace(printStream);
        }
    }

    public void printStackTrace(PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        if (this.wrappedThrowable != null) {
            printWriter.println("Nested Exception: ");
            this.wrappedThrowable.printStackTrace(printWriter);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0011, code lost:
        r1 = r2.streamError;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getMessage() {
        /*
            r2 = this;
            java.lang.String r0 = super.getMessage()
            if (r0 != 0) goto L_0x000f
            org.jivesoftware.smack.packet.XMPPError r1 = r2.error
            if (r1 == 0) goto L_0x000f
            java.lang.String r0 = r1.toString()
            return r0
        L_0x000f:
            if (r0 != 0) goto L_0x0019
            org.jivesoftware.smack.packet.StreamError r1 = r2.streamError
            if (r1 == 0) goto L_0x0019
            java.lang.String r0 = r1.toString()
        L_0x0019:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.XMPPException.getMessage():java.lang.String");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String message = super.getMessage();
        if (message != null) {
            sb.append(message);
            sb.append(": ");
        }
        XMPPError xMPPError = this.error;
        if (xMPPError != null) {
            sb.append(xMPPError);
        }
        StreamError streamError2 = this.streamError;
        if (streamError2 != null) {
            sb.append(streamError2);
        }
        if (this.wrappedThrowable != null) {
            sb.append("\n  -- caused by: ");
            sb.append(this.wrappedThrowable);
        }
        return sb.toString();
    }
}
