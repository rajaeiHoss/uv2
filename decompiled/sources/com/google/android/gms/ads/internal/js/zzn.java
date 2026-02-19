package com.google.android.gms.ads.internal.js;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaij;
import com.google.android.gms.internal.zzajm;
import com.google.android.gms.internal.zzakf;
import com.google.android.gms.internal.zzala;
import com.google.android.gms.internal.zzaly;
import com.google.android.gms.internal.zzcv;

@zzabh
public final class zzn {
    private final Context mContext;
    /* access modifiers changed from: private */
    public final Object mLock;
    private final zzala zzapq;
    private final String zzcfh;
    /* access modifiers changed from: private */
    public zzajm<zzc> zzcfi;
    private zzajm<zzc> zzcfj;
    /* access modifiers changed from: private */
    public zzae zzcfk;
    /* access modifiers changed from: private */
    public int zzcfl;

    public zzn(Context context, zzala zzala, String str) {
        this.mLock = new Object();
        this.zzcfl = 1;
        this.zzcfh = str;
        this.mContext = context.getApplicationContext();
        this.zzapq = zzala;
        this.zzcfi = new zzz();
        this.zzcfj = new zzz();
    }

    public zzn(Context context, zzala zzala, String str, zzajm<zzc> zzajm, zzajm<zzc> zzajm2) {
        this(context, zzala, str);
        this.zzcfi = zzajm;
        this.zzcfj = zzajm2;
    }

    /* access modifiers changed from: protected */
    public final zzae zza(zzcv zzcv) {
        zzae zzae = new zzae(this.zzcfj);
        zzaly.zzdjt.execute(new zzo(this, zzcv, zzae));
        zzae.zza(new zzw(this, zzae), new zzx(this, zzae));
        return zzae;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0029, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ void zza(com.google.android.gms.ads.internal.js.zzae r4, com.google.android.gms.ads.internal.js.zzc r5) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            int r1 = r4.getStatus()     // Catch:{ all -> 0x002a }
            r2 = -1
            if (r1 == r2) goto L_0x0028
            int r1 = r4.getStatus()     // Catch:{ all -> 0x002a }
            r2 = 1
            if (r1 != r2) goto L_0x0012
            goto L_0x0028
        L_0x0012:
            r4.reject()     // Catch:{ all -> 0x002a }
            java.util.concurrent.Executor r4 = com.google.android.gms.internal.zzaly.zzdjt     // Catch:{ all -> 0x002a }
            r5.getClass()     // Catch:{ all -> 0x002a }
            java.lang.Runnable r5 = com.google.android.gms.ads.internal.js.zzr.zza(r5)     // Catch:{ all -> 0x002a }
            r4.execute(r5)     // Catch:{ all -> 0x002a }
            java.lang.String r4 = "Could not receive loaded message in a timely manner. Rejecting."
            com.google.android.gms.internal.zzahw.v(r4)     // Catch:{ all -> 0x002a }
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
            return
        L_0x0028:
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
            return
        L_0x002a:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.js.zzn.zza(com.google.android.gms.ads.internal.js.zzae, com.google.android.gms.ads.internal.js.zzc):void");
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzcv zzcv, zzae zzae) {
        try {
            zze zze = new zze(this.mContext, this.zzapq, zzcv, (zzv) null);
            zze.zza(new zzp(this, zzae, zze));
            zze.zza("/jsLoaded", new zzs(this, zzae, zze));
            zzakf zzakf = new zzakf();
            zzt zzt = new zzt(this, zzcv, zze, zzakf);
            zzakf.set(zzt);
            zze.zza("/requestReload", zzt);
            if (this.zzcfh.endsWith(".js")) {
                zze.zzbb(this.zzcfh);
            } else if (this.zzcfh.startsWith("<html>")) {
                zze.zzbd(this.zzcfh);
            } else {
                zze.zzbc(this.zzcfh);
            }
            zzaij.zzdfn.postDelayed(new zzu(this, zzae, zze), (long) zzy.zzcfy);
        } catch (Throwable th) {
            zzahw.zzb("Error creating webview.", th);
            zzbt.zzep().zza(th, "SdkJavascriptFactory.loadJavascriptEngine");
            zzae.reject();
        }
    }

    public final zzaa zzb(zzcv zzcv) {
        synchronized (this.mLock) {
            zzae zzae = this.zzcfk;
            if (zzae != null) {
                if (zzae.getStatus() != -1) {
                    int i = this.zzcfl;
                    if (i == 0) {
                        zzaa zzma = this.zzcfk.zzma();
                        return zzma;
                    } else if (i == 1) {
                        this.zzcfl = 2;
                        zza((zzcv) null);
                        zzaa zzma2 = this.zzcfk.zzma();
                        return zzma2;
                    } else if (i == 2) {
                        zzaa zzma3 = this.zzcfk.zzma();
                        return zzma3;
                    } else {
                        zzaa zzma4 = this.zzcfk.zzma();
                        return zzma4;
                    }
                }
            }
            this.zzcfl = 2;
            zzae zza = zza((zzcv) null);
            this.zzcfk = zza;
            zzaa zzma5 = zza.zzma();
            return zzma5;
        }
    }
}
