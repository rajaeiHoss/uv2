package org.kobjects.util;

public class ChainedRuntimeException extends RuntimeException {
    Exception chain;

    public static ChainedRuntimeException create(Exception exc, String str) {
        try {
            return ((ChainedRuntimeException) Class.forName("org.kobjects.util.ChainedRuntimeExceptionSE").newInstance())._create(exc, str);
        } catch (Exception unused) {
            return new ChainedRuntimeException(exc, str);
        }
    }

    ChainedRuntimeException() {
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    ChainedRuntimeException(java.lang.Exception r2, java.lang.String r3) {
        /*
            r1 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            if (r3 != 0) goto L_0x0009
            java.lang.String r3 = "rethrown"
        L_0x0009:
            r0.append(r3)
            java.lang.String r3 = ": "
            r0.append(r3)
            java.lang.String r3 = r2.toString()
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            r1.<init>(r3)
            r1.chain = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.kobjects.util.ChainedRuntimeException.<init>(java.lang.Exception, java.lang.String):void");
    }

    /* access modifiers changed from: package-private */
    public ChainedRuntimeException _create(Exception exc, String str) {
        throw new RuntimeException("ERR!");
    }

    public Exception getChained() {
        return this.chain;
    }

    public void printStackTrace() {
        super.printStackTrace();
        Exception exc = this.chain;
        if (exc != null) {
            exc.printStackTrace();
        }
    }
}
