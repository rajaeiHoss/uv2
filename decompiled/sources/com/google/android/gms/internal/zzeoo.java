package com.google.android.gms.internal;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import kotlin.jvm.internal.ByteCompanionObject;

final class zzeoo {
    private final Random random = new Random();
    private final Thread zznqc;
    private zzeoe zznqt;
    private volatile boolean zznqw = false;
    private BlockingQueue<ByteBuffer> zznqx;
    private boolean zznqy = false;
    private WritableByteChannel zznqz;

    zzeoo(zzeoe zzeoe, String str, int i) {
        Thread newThread = zzeoe.getThreadFactory().newThread(new zzeop(this));
        this.zznqc = newThread;
        zzeod zzcdb = zzeoe.zzcdb();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 18);
        sb.append(str);
        sb.append("Writer-");
        sb.append(i);
        zzcdb.zza(newThread, sb.toString());
        this.zznqt = zzeoe;
        this.zznqx = new LinkedBlockingQueue();
    }

    private final void zzcdk() throws InterruptedException, IOException {
        this.zznqz.write(this.zznqx.take());
    }

    /* access modifiers changed from: private */
    public final void zzcdm() {
        while (!this.zznqw && !Thread.interrupted()) {
            try {
                zzcdk();
            } catch (IOException e) {
                this.zznqt.zzb(new zzeok("IO Exception", e));
                return;
            } catch (InterruptedException unused) {
                return;
            }
        }
        for (int i = 0; i < this.zznqx.size(); i++) {
            try {
                zzcdk();
            } catch (IOException e2) {
                this.zznqt.zzb(new zzeok("IO Exception", e2));
                return;
            } catch (InterruptedException unused2) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zza(byte b, boolean z, byte[] bArr) throws IOException {
        int i = 6;
        int length = bArr.length;
        if (length >= 126) {
            i = length <= 65535 ? 8 : 14;
        }
        ByteBuffer allocate = ByteBuffer.allocate(bArr.length + i);
        allocate.put((byte) (b | ByteCompanionObject.MIN_VALUE));
        if (length < 126) {
            allocate.put((byte) (length | 128));
        } else if (length <= 65535) {
            allocate.put((byte) -2);
            allocate.putShort((short) length);
        } else {
            allocate.put((byte) -1);
            allocate.putInt(0);
            allocate.putInt(length);
        }
        byte[] bArr2 = new byte[4];
        this.random.nextBytes(bArr2);
        allocate.put(bArr2);
        for (int i2 = 0; i2 < bArr.length; i2++) {
            allocate.put((byte) (bArr[i2] ^ bArr2[i2 % 4]));
        }
        allocate.flip();
        if (this.zznqw) {
            if (this.zznqy || b != 8) {
                throw new zzeok("Shouldn't be sending");
            }
        }
        if (b == 8) {
            this.zznqy = true;
        }
        this.zznqx.add(allocate);
    }

    /* access modifiers changed from: package-private */
    public final void zzb(OutputStream outputStream) {
        this.zznqz = Channels.newChannel(outputStream);
    }

    /* access modifiers changed from: package-private */
    public final void zzcdl() {
        this.zznqw = true;
    }

    /* access modifiers changed from: package-private */
    public final Thread zzcdn() {
        return this.zznqc;
    }
}
