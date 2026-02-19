package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;

@zzabh
public final class zzhi {
    private final Object zzayt = new Object();
    private zzhj zzayu = null;
    private boolean zzayv = false;

    public final Activity getActivity() {
        synchronized (this.zzayt) {
            zzhj zzhj = this.zzayu;
            if (zzhj == null) {
                return null;
            }
            Activity activity = zzhj.getActivity();
            return activity;
        }
    }

    public final Context getContext() {
        synchronized (this.zzayt) {
            zzhj zzhj = this.zzayu;
            if (zzhj == null) {
                return null;
            }
            Context context = zzhj.getContext();
            return context;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0047, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void initialize(android.content.Context r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.zzayt
            monitor-enter(r0)
            boolean r1 = r4.zzayv     // Catch:{ all -> 0x0048 }
            if (r1 != 0) goto L_0x0046
            com.google.android.gms.internal.zzny<java.lang.Boolean> r1 = com.google.android.gms.internal.zzoi.zzbpa     // Catch:{ all -> 0x0048 }
            com.google.android.gms.internal.zzog r2 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ all -> 0x0048 }
            java.lang.Object r1 = r2.zzd(r1)     // Catch:{ all -> 0x0048 }
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ all -> 0x0048 }
            boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x0048 }
            if (r1 != 0) goto L_0x001b
            monitor-exit(r0)     // Catch:{ all -> 0x0048 }
            return
        L_0x001b:
            r1 = 0
            android.content.Context r2 = r5.getApplicationContext()     // Catch:{ all -> 0x0048 }
            if (r2 != 0) goto L_0x0023
            r2 = r5
        L_0x0023:
            boolean r3 = r2 instanceof android.app.Application     // Catch:{ all -> 0x0048 }
            if (r3 == 0) goto L_0x002a
            r1 = r2
            android.app.Application r1 = (android.app.Application) r1     // Catch:{ all -> 0x0048 }
        L_0x002a:
            if (r1 != 0) goto L_0x0033
            java.lang.String r5 = "Can not cast Context to Application"
            com.google.android.gms.internal.zzahw.zzcz(r5)     // Catch:{ all -> 0x0048 }
            monitor-exit(r0)     // Catch:{ all -> 0x0048 }
            return
        L_0x0033:
            com.google.android.gms.internal.zzhj r2 = r4.zzayu     // Catch:{ all -> 0x0048 }
            if (r2 != 0) goto L_0x003e
            com.google.android.gms.internal.zzhj r2 = new com.google.android.gms.internal.zzhj     // Catch:{ all -> 0x0048 }
            r2.<init>()     // Catch:{ all -> 0x0048 }
            r4.zzayu = r2     // Catch:{ all -> 0x0048 }
        L_0x003e:
            com.google.android.gms.internal.zzhj r2 = r4.zzayu     // Catch:{ all -> 0x0048 }
            r2.zza((android.app.Application) r1, (android.content.Context) r5)     // Catch:{ all -> 0x0048 }
            r5 = 1
            r4.zzayv = r5     // Catch:{ all -> 0x0048 }
        L_0x0046:
            monitor-exit(r0)     // Catch:{ all -> 0x0048 }
            return
        L_0x0048:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0048 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzhi.initialize(android.content.Context):void");
    }

    public final void zza(zzhl zzhl) {
        synchronized (this.zzayt) {
            if (((Boolean) zzlc.zzio().zzd(zzoi.zzbpa)).booleanValue()) {
                if (this.zzayu == null) {
                    this.zzayu = new zzhj();
                }
                this.zzayu.zza(zzhl);
            }
        }
    }
}
