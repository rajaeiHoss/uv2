package com.google.android.gms.internal;

import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzbt;

@zzabh
public final class zzif {
    private Context mContext;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    private final Runnable zzbav = new zzig(this);
    /* access modifiers changed from: private */
    public zzim zzbaw;
    /* access modifiers changed from: private */
    public zziq zzbax;

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void connect() {
        /*
            r6 = this;
            java.lang.Object r0 = r6.mLock
            monitor-enter(r0)
            android.content.Context r1 = r6.mContext     // Catch:{ all -> 0x002e }
            if (r1 == 0) goto L_0x002c
            com.google.android.gms.internal.zzim r1 = r6.zzbaw     // Catch:{ all -> 0x002e }
            if (r1 == 0) goto L_0x000c
            goto L_0x002c
        L_0x000c:
            com.google.android.gms.internal.zzii r1 = new com.google.android.gms.internal.zzii     // Catch:{ all -> 0x002e }
            r1.<init>(r6)     // Catch:{ all -> 0x002e }
            com.google.android.gms.internal.zzij r2 = new com.google.android.gms.internal.zzij     // Catch:{ all -> 0x002e }
            r2.<init>(r6)     // Catch:{ all -> 0x002e }
            com.google.android.gms.internal.zzim r3 = new com.google.android.gms.internal.zzim     // Catch:{ all -> 0x002e }
            android.content.Context r4 = r6.mContext     // Catch:{ all -> 0x002e }
            com.google.android.gms.internal.zzakb r5 = com.google.android.gms.ads.internal.zzbt.zzfa()     // Catch:{ all -> 0x002e }
            android.os.Looper r5 = r5.zzrt()     // Catch:{ all -> 0x002e }
            r3.<init>(r4, r5, r1, r2)     // Catch:{ all -> 0x002e }
            r6.zzbaw = r3     // Catch:{ all -> 0x002e }
            r3.zzals()     // Catch:{ all -> 0x002e }
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            return
        L_0x002c:
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            return
        L_0x002e:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzif.connect():void");
    }

    /* access modifiers changed from: private */
    public final void disconnect() {
        synchronized (this.mLock) {
            zzim zzim = this.zzbaw;
            if (zzim != null) {
                if (zzim.isConnected() || this.zzbaw.isConnecting()) {
                    this.zzbaw.disconnect();
                }
                this.zzbaw = null;
                this.zzbax = null;
                Binder.flushPendingCommands();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0047, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void initialize(android.content.Context r3) {
        /*
            r2 = this;
            if (r3 != 0) goto L_0x0003
            return
        L_0x0003:
            java.lang.Object r0 = r2.mLock
            monitor-enter(r0)
            android.content.Context r1 = r2.mContext     // Catch:{ all -> 0x0048 }
            if (r1 == 0) goto L_0x000c
            monitor-exit(r0)     // Catch:{ all -> 0x0048 }
            return
        L_0x000c:
            android.content.Context r3 = r3.getApplicationContext()     // Catch:{ all -> 0x0048 }
            r2.mContext = r3     // Catch:{ all -> 0x0048 }
            com.google.android.gms.internal.zzny<java.lang.Boolean> r3 = com.google.android.gms.internal.zzoi.zzbue     // Catch:{ all -> 0x0048 }
            com.google.android.gms.internal.zzog r1 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ all -> 0x0048 }
            java.lang.Object r3 = r1.zzd(r3)     // Catch:{ all -> 0x0048 }
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ all -> 0x0048 }
            boolean r3 = r3.booleanValue()     // Catch:{ all -> 0x0048 }
            if (r3 == 0) goto L_0x0028
            r2.connect()     // Catch:{ all -> 0x0048 }
            goto L_0x0046
        L_0x0028:
            com.google.android.gms.internal.zzny<java.lang.Boolean> r3 = com.google.android.gms.internal.zzoi.zzbud     // Catch:{ all -> 0x0048 }
            com.google.android.gms.internal.zzog r1 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ all -> 0x0048 }
            java.lang.Object r3 = r1.zzd(r3)     // Catch:{ all -> 0x0048 }
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ all -> 0x0048 }
            boolean r3 = r3.booleanValue()     // Catch:{ all -> 0x0048 }
            if (r3 == 0) goto L_0x0046
            com.google.android.gms.internal.zzih r3 = new com.google.android.gms.internal.zzih     // Catch:{ all -> 0x0048 }
            r3.<init>(r2)     // Catch:{ all -> 0x0048 }
            com.google.android.gms.internal.zzhi r1 = com.google.android.gms.ads.internal.zzbt.zzeo()     // Catch:{ all -> 0x0048 }
            r1.zza(r3)     // Catch:{ all -> 0x0048 }
        L_0x0046:
            monitor-exit(r0)     // Catch:{ all -> 0x0048 }
            return
        L_0x0048:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0048 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzif.initialize(android.content.Context):void");
    }

    public final zzik zza(zzin zzin) {
        synchronized (this.mLock) {
            zziq zziq = this.zzbax;
            if (zziq == null) {
                zzik zzik = new zzik();
                return zzik;
            }
            try {
                zzik zza = zziq.zza(zzin);
                return zza;
            } catch (RemoteException e) {
                zzahw.zzb("Unable to call into cache service.", e);
                return new zzik();
            }
        }
    }

    public final void zzhi() {
        if (((Boolean) zzlc.zzio().zzd(zzoi.zzbuf)).booleanValue()) {
            synchronized (this.mLock) {
                connect();
                zzbt.zzel();
                zzaij.zzdfn.removeCallbacks(this.zzbav);
                zzbt.zzel();
                zzaij.zzdfn.postDelayed(this.zzbav, ((Long) zzlc.zzio().zzd(zzoi.zzbug)).longValue());
            }
        }
    }
}
