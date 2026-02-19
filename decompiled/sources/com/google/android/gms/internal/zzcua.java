package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.common.util.zzp;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

final class zzcua implements Runnable {
    private /* synthetic */ long zzjyw;
    private /* synthetic */ InputStream zzjzq;
    private /* synthetic */ OutputStream zzjzr;
    private /* synthetic */ OutputStream zzjzs;
    private /* synthetic */ zzctz zzjzt;

    zzcua(zzctz zzctz, InputStream inputStream, OutputStream outputStream, long j, OutputStream outputStream2) {
        this.zzjzt = zzctz;
        this.zzjzq = inputStream;
        this.zzjzr = outputStream;
        this.zzjyw = j;
        this.zzjzs = outputStream2;
    }

    public final void run() {
        Throwable th;
        InputStream unused = this.zzjzt.zzjzo = this.zzjzq;
        boolean z = false;
        try {
            zzp.zza(this.zzjzq, this.zzjzr, false, 65536);
            zzp.closeQuietly(this.zzjzq);
            zzctz.zza(this.zzjzs, false, this.zzjyw);
        } catch (IOException e) {
            if (!this.zzjzt.zzjzp) {
                Log.w("NearbyConnections", String.format("Exception copying stream for Payload %d", new Object[]{Long.valueOf(this.zzjyw)}), e);
            } else {
                Log.d("NearbyConnections", String.format("Terminating copying stream for Payload %d due to shutdown of OutgoingPayloadStreamer.", new Object[]{Long.valueOf(this.zzjyw)}));
            }
            zzp.closeQuietly(this.zzjzq);
            zzctz.zza(this.zzjzs, true, this.zzjyw);
        } catch (Throwable th2) {
            th = th2;
            z = true;
            zzp.closeQuietly(this.zzjzq);
            zzctz.zza(this.zzjzs, z, this.zzjyw);
            zzp.closeQuietly(this.zzjzr);
            InputStream unused2 = this.zzjzt.zzjzo = null;
            throw new RuntimeException(th);
        }
        zzp.closeQuietly(this.zzjzr);
        InputStream unused3 = this.zzjzt.zzjzo = null;
    }
}
