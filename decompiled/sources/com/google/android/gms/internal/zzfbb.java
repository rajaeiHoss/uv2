package com.google.android.gms.internal;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;

public final class zzfbb {
    private static final Runtime zzoxk = Runtime.getRuntime();
    private byte[] buffer = new byte[262144];
    private final InputStream zzoxl;
    private int zzoxm = 0;
    private boolean zzoxn = false;
    private boolean zzoxo = true;

    public zzfbb(InputStream inputStream, int i) {
        this.zzoxl = inputStream;
    }

    private final int zzjc(int i) {
        int max = Math.max(this.buffer.length << 1, i);
        Runtime runtime = zzoxk;
        long maxMemory = runtime.maxMemory() - (runtime.totalMemory() - runtime.freeMemory());
        if (!this.zzoxo || ((long) max) >= maxMemory) {
            Log.w("AdaptiveStreamBuffer", "Turning off adaptive buffer resizing to conserve memory.");
        } else {
            try {
                byte[] bArr = new byte[max];
                System.arraycopy(this.buffer, 0, bArr, 0, this.zzoxm);
                this.buffer = bArr;
            } catch (OutOfMemoryError unused) {
                Log.w("AdaptiveStreamBuffer", "Turning off adaptive buffer resizing due to low memory.");
                this.zzoxo = false;
            }
        }
        return this.buffer.length;
    }

    public final int available() {
        return this.zzoxm;
    }

    public final void close() throws IOException {
        this.zzoxl.close();
    }

    public final boolean isFinished() {
        return this.zzoxn;
    }

    public final byte[] zzcom() {
        return this.buffer;
    }

    public final int zzja(int i) throws IOException {
        int i2 = this.zzoxm;
        int i3 = 0;
        if (i <= i2) {
            int i4 = i2 - i;
            this.zzoxm = i4;
            byte[] bArr = this.buffer;
            System.arraycopy(bArr, i, bArr, 0, i4);
            return i;
        }
        this.zzoxm = 0;
        while (i3 < i) {
            long skip = this.zzoxl.skip((long) (i - i3));
            int i5 = (skip > 0 ? 1 : (skip == 0 ? 0 : -1));
            if (i5 <= 0) {
                if (i5 == 0) {
                    if (this.zzoxl.read() == -1) {
                        break;
                    }
                    i3++;
                } else {
                    continue;
                }
            } else {
                i3 = (int) (((long) i3) + skip);
            }
        }
        return i3;
    }

    public final int zzjb(int i) throws IOException {
        if (i > this.buffer.length) {
            i = Math.min(i, zzjc(i));
        }
        while (true) {
            int i2 = this.zzoxm;
            if (i2 >= i) {
                break;
            }
            int read = this.zzoxl.read(this.buffer, i2, i - i2);
            if (read == -1) {
                this.zzoxn = true;
                break;
            }
            this.zzoxm += read;
        }
        return this.zzoxm;
    }
}
