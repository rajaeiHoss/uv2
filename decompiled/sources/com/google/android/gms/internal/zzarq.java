package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import com.google.android.gms.common.internal.zzbq;

public final class zzarq implements ServiceConnection {
    final /* synthetic */ zzaro zzdzq;
    private volatile zzasz zzdzr;
    private volatile boolean zzdzs;

    protected zzarq(zzaro zzaro) {
        this.zzdzq = zzaro;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:23|24) */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:10|11|12|(2:(1:15)(3:16|(1:18)(1:19)|20)|21)(1:22)|(2:26|27)(3:28|29|(1:31)(1:32))|33|34|35|36) */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r2.zzdzq.zzee("Service connect failed to get IAnalyticsService");
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x004a */
    /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0082 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onServiceConnected(android.content.ComponentName r3, android.os.IBinder r4) {
        /*
            r2 = this;
            java.lang.String r3 = "AnalyticsServiceConnection.onServiceConnected"
            com.google.android.gms.common.internal.zzbq.zzgn(r3)
            monitor-enter(r2)
            if (r4 != 0) goto L_0x0017
            com.google.android.gms.internal.zzaro r3 = r2.zzdzq     // Catch:{ all -> 0x0014 }
            java.lang.String r4 = "Service connected with null binder"
            r3.zzee(r4)     // Catch:{ all -> 0x0014 }
            r2.notifyAll()     // Catch:{ all -> 0x008b }
            monitor-exit(r2)     // Catch:{ all -> 0x008b }
            return
        L_0x0014:
            r3 = move-exception
            goto L_0x0087
        L_0x0017:
            r3 = 0
            java.lang.String r0 = r4.getInterfaceDescriptor()     // Catch:{ RemoteException -> 0x004a }
            java.lang.String r1 = "com.google.android.gms.analytics.internal.IAnalyticsService"
            boolean r1 = r1.equals(r0)     // Catch:{ RemoteException -> 0x004a }
            if (r1 == 0) goto L_0x0042
            if (r4 != 0) goto L_0x0027
            goto L_0x003a
        L_0x0027:
            java.lang.String r0 = "com.google.android.gms.analytics.internal.IAnalyticsService"
            android.os.IInterface r0 = r4.queryLocalInterface(r0)     // Catch:{ RemoteException -> 0x004a }
            boolean r1 = r0 instanceof com.google.android.gms.internal.zzasz     // Catch:{ RemoteException -> 0x004a }
            if (r1 == 0) goto L_0x0034
            com.google.android.gms.internal.zzasz r0 = (com.google.android.gms.internal.zzasz) r0     // Catch:{ RemoteException -> 0x004a }
            goto L_0x0039
        L_0x0034:
            com.google.android.gms.internal.zzata r0 = new com.google.android.gms.internal.zzata     // Catch:{ RemoteException -> 0x004a }
            r0.<init>(r4)     // Catch:{ RemoteException -> 0x004a }
        L_0x0039:
            r3 = r0
        L_0x003a:
            com.google.android.gms.internal.zzaro r4 = r2.zzdzq     // Catch:{ RemoteException -> 0x004a }
            java.lang.String r0 = "Bound to IAnalyticsService interface"
            r4.zzea(r0)     // Catch:{ RemoteException -> 0x004a }
            goto L_0x0051
        L_0x0042:
            com.google.android.gms.internal.zzaro r4 = r2.zzdzq     // Catch:{ RemoteException -> 0x004a }
            java.lang.String r1 = "Got binder with a wrong descriptor"
            r4.zze(r1, r0)     // Catch:{ RemoteException -> 0x004a }
            goto L_0x0051
        L_0x004a:
            com.google.android.gms.internal.zzaro r4 = r2.zzdzq     // Catch:{ all -> 0x0014 }
            java.lang.String r0 = "Service connect failed to get IAnalyticsService"
            r4.zzee(r0)     // Catch:{ all -> 0x0014 }
        L_0x0051:
            if (r3 != 0) goto L_0x0066
            com.google.android.gms.common.stats.zza.zzanm()     // Catch:{ IllegalArgumentException -> 0x0082 }
            com.google.android.gms.internal.zzaro r3 = r2.zzdzq     // Catch:{ IllegalArgumentException -> 0x0082 }
            android.content.Context r3 = r3.getContext()     // Catch:{ IllegalArgumentException -> 0x0082 }
            com.google.android.gms.internal.zzaro r4 = r2.zzdzq     // Catch:{ IllegalArgumentException -> 0x0082 }
            com.google.android.gms.internal.zzarq r4 = r4.zzdzm     // Catch:{ IllegalArgumentException -> 0x0082 }
            r3.unbindService(r4)     // Catch:{ IllegalArgumentException -> 0x0082 }
            goto L_0x0082
        L_0x0066:
            boolean r4 = r2.zzdzs     // Catch:{ all -> 0x0014 }
            if (r4 != 0) goto L_0x0080
            com.google.android.gms.internal.zzaro r4 = r2.zzdzq     // Catch:{ all -> 0x0014 }
            java.lang.String r0 = "onServiceConnected received after the timeout limit"
            r4.zzed(r0)     // Catch:{ all -> 0x0014 }
            com.google.android.gms.internal.zzaro r4 = r2.zzdzq     // Catch:{ all -> 0x0014 }
            com.google.android.gms.analytics.zzk r4 = r4.zzya()     // Catch:{ all -> 0x0014 }
            com.google.android.gms.internal.zzarr r0 = new com.google.android.gms.internal.zzarr     // Catch:{ all -> 0x0014 }
            r0.<init>(r2, r3)     // Catch:{ all -> 0x0014 }
            r4.zzd(r0)     // Catch:{ all -> 0x0014 }
            goto L_0x0082
        L_0x0080:
            r2.zzdzr = r3     // Catch:{ all -> 0x0014 }
        L_0x0082:
            r2.notifyAll()     // Catch:{ all -> 0x008b }
            monitor-exit(r2)     // Catch:{ all -> 0x008b }
            return
        L_0x0087:
            r2.notifyAll()     // Catch:{ all -> 0x008b }
            throw r3     // Catch:{ all -> 0x008b }
        L_0x008b:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x008b }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzarq.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        zzbq.zzgn("AnalyticsServiceConnection.onServiceDisconnected");
        this.zzdzq.zzya().zzd(new zzars(this, componentName));
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:9|10|11|12|13|(1:15)) */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0075, code lost:
        return r0;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x005e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzasz zzyy() {
        /*
            r6 = this;
            com.google.android.gms.analytics.zzk.zzwj()
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "com.google.android.gms.analytics.service.START"
            r0.<init>(r1)
            android.content.ComponentName r1 = new android.content.ComponentName
            java.lang.String r2 = "com.google.android.gms"
            java.lang.String r3 = "com.google.android.gms.analytics.service.AnalyticsService"
            r1.<init>(r2, r3)
            r0.setComponent(r1)
            com.google.android.gms.internal.zzaro r1 = r6.zzdzq
            android.content.Context r1 = r1.getContext()
            java.lang.String r2 = "app_package_name"
            java.lang.String r3 = r1.getPackageName()
            r0.putExtra(r2, r3)
            com.google.android.gms.common.stats.zza r2 = com.google.android.gms.common.stats.zza.zzanm()
            monitor-enter(r6)
            r3 = 0
            r6.zzdzr = r3     // Catch:{ all -> 0x0076 }
            r4 = 1
            r6.zzdzs = r4     // Catch:{ all -> 0x0076 }
            com.google.android.gms.internal.zzaro r4 = r6.zzdzq     // Catch:{ all -> 0x0076 }
            com.google.android.gms.internal.zzarq r4 = r4.zzdzm     // Catch:{ all -> 0x0076 }
            r5 = 129(0x81, float:1.81E-43)
            boolean r0 = r2.zza(r1, r0, r4, r5)     // Catch:{ all -> 0x0076 }
            com.google.android.gms.internal.zzaro r1 = r6.zzdzq     // Catch:{ all -> 0x0076 }
            java.lang.String r2 = "Bind to service requested"
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x0076 }
            r1.zza(r2, r4)     // Catch:{ all -> 0x0076 }
            r1 = 0
            if (r0 != 0) goto L_0x004e
            r6.zzdzs = r1     // Catch:{ all -> 0x0076 }
            monitor-exit(r6)     // Catch:{ all -> 0x0076 }
            return r3
        L_0x004e:
            com.google.android.gms.internal.zzasu<java.lang.Long> r0 = com.google.android.gms.internal.zzast.zzecw     // Catch:{ InterruptedException -> 0x005e }
            java.lang.Object r0 = r0.get()     // Catch:{ InterruptedException -> 0x005e }
            java.lang.Long r0 = (java.lang.Long) r0     // Catch:{ InterruptedException -> 0x005e }
            long r4 = r0.longValue()     // Catch:{ InterruptedException -> 0x005e }
            r6.wait(r4)     // Catch:{ InterruptedException -> 0x005e }
            goto L_0x0065
        L_0x005e:
            com.google.android.gms.internal.zzaro r0 = r6.zzdzq     // Catch:{ all -> 0x0076 }
            java.lang.String r2 = "Wait for service connect was interrupted"
            r0.zzed(r2)     // Catch:{ all -> 0x0076 }
        L_0x0065:
            r6.zzdzs = r1     // Catch:{ all -> 0x0076 }
            com.google.android.gms.internal.zzasz r0 = r6.zzdzr     // Catch:{ all -> 0x0076 }
            r6.zzdzr = r3     // Catch:{ all -> 0x0076 }
            if (r0 != 0) goto L_0x0074
            com.google.android.gms.internal.zzaro r1 = r6.zzdzq     // Catch:{ all -> 0x0076 }
            java.lang.String r2 = "Successfully bound to service but never got onServiceConnected callback"
            r1.zzee(r2)     // Catch:{ all -> 0x0076 }
        L_0x0074:
            monitor-exit(r6)     // Catch:{ all -> 0x0076 }
            return r0
        L_0x0076:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0076 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzarq.zzyy():com.google.android.gms.internal.zzasz");
    }
}
