package com.google.android.gms.internal;

import android.content.Context;

@zzabh
public abstract class zzzi extends zzahs {
    protected final Context mContext;
    protected final Object mLock = new Object();
    protected final zzzn zzcoa;
    protected final zzahe zzcob;
    protected zzacj zzcoc;
    protected final Object zzcoe = new Object();

    protected zzzi(Context context, zzahe zzahe, zzzn zzzn) {
        super(true);
        this.mContext = context;
        this.zzcob = zzahe;
        this.zzcoc = zzahe.zzdcw;
        this.zzcoa = zzzn;
    }

    public void onStop() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0033 A[Catch:{ zzzl -> 0x0014 }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003b A[Catch:{ zzzl -> 0x0014 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzdo() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.mLock
            monitor-enter(r0)
            java.lang.String r1 = "AdRendererBackgroundTask started."
            com.google.android.gms.internal.zzahw.zzby(r1)     // Catch:{ all -> 0x0060 }
            com.google.android.gms.internal.zzahe r1 = r5.zzcob     // Catch:{ all -> 0x0060 }
            int r1 = r1.errorCode     // Catch:{ all -> 0x0060 }
            long r2 = android.os.SystemClock.elapsedRealtime()     // Catch:{ zzzl -> 0x0014 }
            r5.zze(r2)     // Catch:{ zzzl -> 0x0014 }
            goto L_0x0050
        L_0x0014:
            r1 = move-exception
            int r2 = r1.getErrorCode()     // Catch:{ all -> 0x0060 }
            r3 = 3
            if (r2 == r3) goto L_0x0028
            r3 = -1
            if (r2 != r3) goto L_0x0020
            goto L_0x0028
        L_0x0020:
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x0060 }
            com.google.android.gms.internal.zzahw.zzcz(r1)     // Catch:{ all -> 0x0060 }
            goto L_0x002f
        L_0x0028:
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x0060 }
            com.google.android.gms.internal.zzahw.zzcy(r1)     // Catch:{ all -> 0x0060 }
        L_0x002f:
            com.google.android.gms.internal.zzacj r1 = r5.zzcoc     // Catch:{ all -> 0x0060 }
            if (r1 != 0) goto L_0x003b
            com.google.android.gms.internal.zzacj r1 = new com.google.android.gms.internal.zzacj     // Catch:{ all -> 0x0060 }
            r1.<init>(r2)     // Catch:{ all -> 0x0060 }
        L_0x0038:
            r5.zzcoc = r1     // Catch:{ all -> 0x0060 }
            goto L_0x0045
        L_0x003b:
            com.google.android.gms.internal.zzacj r1 = new com.google.android.gms.internal.zzacj     // Catch:{ all -> 0x0060 }
            com.google.android.gms.internal.zzacj r3 = r5.zzcoc     // Catch:{ all -> 0x0060 }
            long r3 = r3.zzcic     // Catch:{ all -> 0x0060 }
            r1.<init>(r2, r3)     // Catch:{ all -> 0x0060 }
            goto L_0x0038
        L_0x0045:
            android.os.Handler r1 = com.google.android.gms.internal.zzaij.zzdfn     // Catch:{ all -> 0x0060 }
            com.google.android.gms.internal.zzzj r3 = new com.google.android.gms.internal.zzzj     // Catch:{ all -> 0x0060 }
            r3.<init>(r5)     // Catch:{ all -> 0x0060 }
            r1.post(r3)     // Catch:{ all -> 0x0060 }
            r1 = r2
        L_0x0050:
            com.google.android.gms.internal.zzahd r1 = r5.zzy(r1)     // Catch:{ all -> 0x0060 }
            android.os.Handler r2 = com.google.android.gms.internal.zzaij.zzdfn     // Catch:{ all -> 0x0060 }
            com.google.android.gms.internal.zzzk r3 = new com.google.android.gms.internal.zzzk     // Catch:{ all -> 0x0060 }
            r3.<init>(r5, r1)     // Catch:{ all -> 0x0060 }
            r2.post(r3)     // Catch:{ all -> 0x0060 }
            monitor-exit(r0)     // Catch:{ all -> 0x0060 }
            return
        L_0x0060:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0060 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzzi.zzdo():void");
    }

    /* access modifiers changed from: protected */
    public abstract void zze(long j) throws zzzl;

    /* access modifiers changed from: protected */
    public abstract zzahd zzy(int i);
}
