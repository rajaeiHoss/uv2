package com.google.android.gms.internal;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

public final class zzegc extends Reader {
    private boolean closed;
    private List<String> zzndq;
    private int zzndr;
    private int zznds;
    private int zzndt;
    private int zzndu;
    private boolean zzndv;

    public zzegc() {
        this.zzndq = null;
        this.closed = false;
        this.zzndt = this.zzndr;
        this.zzndu = this.zznds;
        this.zzndv = false;
        this.zzndq = new ArrayList();
    }

    private final long zzbv(long j) {
        long j2 = 0;
        while (this.zznds < this.zzndq.size() && j2 < j) {
            long j3 = j - j2;
            long zzbxw = (long) zzbxw();
            if (j3 < zzbxw) {
                this.zzndr = (int) (((long) this.zzndr) + j3);
                j2 += j3;
            } else {
                j2 += zzbxw;
                this.zzndr = 0;
                this.zznds++;
            }
        }
        return j2;
    }

    private final String zzbxv() {
        if (this.zznds < this.zzndq.size()) {
            return this.zzndq.get(this.zznds);
        }
        return null;
    }

    private final int zzbxw() {
        String zzbxv = zzbxv();
        if (zzbxv == null) {
            return 0;
        }
        return zzbxv.length() - this.zzndr;
    }

    private final void zzbxx() throws IOException {
        if (this.closed) {
            throw new IOException("Stream already closed");
        } else if (!this.zzndv) {
            throw new IOException("Reader needs to be frozen before read operations can be called");
        }
    }

    public final void close() throws IOException {
        zzbxx();
        this.closed = true;
    }

    public final void mark(int i) throws IOException {
        zzbxx();
        this.zzndt = this.zzndr;
        this.zzndu = this.zznds;
    }

    public final boolean markSupported() {
        return true;
    }

    public final int read() throws IOException {
        zzbxx();
        String zzbxv = zzbxv();
        if (zzbxv == null) {
            return -1;
        }
        char charAt = zzbxv.charAt(this.zzndr);
        zzbv(1);
        return charAt;
    }

    public final int read(CharBuffer charBuffer) throws IOException {
        zzbxx();
        int remaining = charBuffer.remaining();
        String zzbxv = zzbxv();
        int i = 0;
        while (remaining > 0 && zzbxv != null) {
            int min = Math.min(zzbxv.length() - this.zzndr, remaining);
            int i2 = this.zzndr;
            charBuffer.put(this.zzndq.get(this.zznds), i2, i2 + min);
            remaining -= min;
            i += min;
            zzbv((long) min);
            zzbxv = zzbxv();
        }
        if (i > 0 || zzbxv != null) {
            return i;
        }
        return -1;
    }

    public final int read(char[] cArr, int i, int i2) throws IOException {
        zzbxx();
        String zzbxv = zzbxv();
        int i3 = 0;
        while (zzbxv != null && i3 < i2) {
            int min = Math.min(zzbxw(), i2 - i3);
            int i4 = this.zzndr;
            zzbxv.getChars(i4, i4 + min, cArr, i + i3);
            i3 += min;
            zzbv((long) min);
            zzbxv = zzbxv();
        }
        if (i3 > 0 || zzbxv != null) {
            return i3;
        }
        return -1;
    }

    public final boolean ready() throws IOException {
        zzbxx();
        return true;
    }

    public final void reset() throws IOException {
        this.zzndr = this.zzndt;
        this.zznds = this.zzndu;
    }

    public final long skip(long j) throws IOException {
        zzbxx();
        return zzbv(j);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        for (String append : this.zzndq) {
            sb.append(append);
        }
        return sb.toString();
    }

    public final void zzbxu() {
        if (!this.zzndv) {
            this.zzndv = true;
            return;
        }
        throw new IllegalStateException("Trying to freeze frozen StringListReader");
    }

    public final void zzpz(String str) {
        if (this.zzndv) {
            throw new IllegalStateException("Trying to add string after reading");
        } else if (str.length() > 0) {
            this.zzndq.add(str);
        }
    }
}
