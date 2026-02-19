package com.google.android.gms.internal;

import android.content.Context;
import android.os.Binder;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.ads.internal.zzbt;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zzabh
public final class zzte implements zzm {
    private final Context mContext;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    /* access modifiers changed from: private */
    public zzsx zzcdb;
    /* access modifiers changed from: private */
    public boolean zzcdc;

    public zzte(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: private */
    public final void disconnect() {
        synchronized (this.mLock) {
            zzsx zzsx = this.zzcdb;
            if (zzsx != null) {
                zzsx.disconnect();
                this.zzcdb = null;
                Binder.flushPendingCommands();
            }
        }
    }

    private final Future<ParcelFileDescriptor> zzb(zzsy zzsy) {
        zztf zztf = new zztf(this);
        zztg zztg = new zztg(this, zztf, zzsy);
        zztj zztj = new zztj(this, zztf);
        synchronized (this.mLock) {
            zzsx zzsx = new zzsx(this.mContext, zzbt.zzfa().zzrt(), zztg, zztj);
            this.zzcdb = zzsx;
            zzsx.zzals();
        }
        return zztf;
    }

    /* JADX INFO: finally extract failed */
    public final zzp zzc(zzr<?> zzr) throws zzae {
        zzp zzp;
        zzsy zzh = zzsy.zzh(zzr);
        long intValue = (long) ((Integer) zzlc.zzio().zzd(zzoi.zzbun)).intValue();
        long elapsedRealtime = zzbt.zzes().elapsedRealtime();
        try {
            zzta response = (zzta) new zzacv(zzb(zzh).get(intValue, TimeUnit.MILLISECONDS)).zza(zzta.CREATOR);
            if (!response.zzccz) {
                if (response.zzccx.length != response.zzccy.length) {
                    zzp = null;
                } else {
                    HashMap hashMap = new HashMap();
                    for (int i = 0; i < response.zzccx.length; i++) {
                        hashMap.put(response.zzccx[i], response.zzccy[i]);
                    }
                    zzp = new zzp(response.statusCode, response.data, (Map<String, String>) hashMap, response.zzac, response.zzad);
                }
                StringBuilder sb = new StringBuilder(52);
                sb.append("Http assets remote cache took ");
                sb.append(zzbt.zzes().elapsedRealtime() - elapsedRealtime);
                sb.append("ms");
                zzahw.v(sb.toString());
                return zzp;
            }
            throw new zzae(response.zzcda);
        } catch (InterruptedException | ExecutionException | TimeoutException unused) {
            StringBuilder sb2 = new StringBuilder(52);
            sb2.append("Http assets remote cache took ");
            sb2.append(zzbt.zzes().elapsedRealtime() - elapsedRealtime);
            sb2.append("ms");
            zzahw.v(sb2.toString());
            return null;
        } catch (Throwable th) {
            StringBuilder sb3 = new StringBuilder(52);
            sb3.append("Http assets remote cache took ");
            sb3.append(zzbt.zzes().elapsedRealtime() - elapsedRealtime);
            sb3.append("ms");
            zzahw.v(sb3.toString());
            throw th;
        }
    }
}
