package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.stats.zza;

public final class zzcms implements ServiceConnection, zzf, zzg {
    final /* synthetic */ zzcme zzjri;
    /* access modifiers changed from: private */
    public volatile boolean zzjrp;
    private volatile zzcji zzjrq;

    protected zzcms(zzcme zzcme) {
        this.zzjri = zzcme;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0022 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onConnected(android.os.Bundle r4) {
        /*
            r3 = this;
            java.lang.String r4 = "MeasurementServiceConnection.onConnected"
            com.google.android.gms.common.internal.zzbq.zzgn(r4)
            monitor-enter(r3)
            r4 = 0
            com.google.android.gms.internal.zzcji r0 = r3.zzjrq     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            android.os.IInterface r0 = r0.zzalw()     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            com.google.android.gms.internal.zzcjb r0 = (com.google.android.gms.internal.zzcjb) r0     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            r3.zzjrq = r4     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            com.google.android.gms.internal.zzcme r1 = r3.zzjri     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            com.google.android.gms.internal.zzcke r1 = r1.zzayo()     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            com.google.android.gms.internal.zzcmv r2 = new com.google.android.gms.internal.zzcmv     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            r2.<init>(r3, r0)     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            r1.zzh(r2)     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            goto L_0x0027
        L_0x0020:
            r4 = move-exception
            goto L_0x0029
        L_0x0022:
            r3.zzjrq = r4     // Catch:{ all -> 0x0020 }
            r4 = 0
            r3.zzjrp = r4     // Catch:{ all -> 0x0020 }
        L_0x0027:
            monitor-exit(r3)     // Catch:{ all -> 0x0020 }
            return
        L_0x0029:
            monitor-exit(r3)     // Catch:{ all -> 0x0020 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcms.onConnected(android.os.Bundle):void");
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        zzbq.zzgn("MeasurementServiceConnection.onConnectionFailed");
        zzcjj zzbbo = this.zzjri.zzjev.zzbbo();
        if (zzbbo != null) {
            zzbbo.zzbaw().zzj("Service connection failed", connectionResult);
        }
        synchronized (this) {
            this.zzjrp = false;
            this.zzjrq = null;
        }
        this.zzjri.zzayo().zzh(new zzcmx(this));
    }

    public final void onConnectionSuspended(int i) {
        zzbq.zzgn("MeasurementServiceConnection.onConnectionSuspended");
        this.zzjri.zzayp().zzbaz().log("Service connection suspended");
        this.zzjri.zzayo().zzh(new zzcmw(this));
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:22|23) */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r3.zzjri.zzayp().zzbau().log("Service connect failed to get IMeasurementService");
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x0062 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onServiceConnected(android.content.ComponentName r4, android.os.IBinder r5) {
        /*
            r3 = this;
            java.lang.String r4 = "MeasurementServiceConnection.onServiceConnected"
            com.google.android.gms.common.internal.zzbq.zzgn(r4)
            monitor-enter(r3)
            r4 = 0
            if (r5 != 0) goto L_0x001f
            r3.zzjrp = r4     // Catch:{ all -> 0x001c }
            com.google.android.gms.internal.zzcme r4 = r3.zzjri     // Catch:{ all -> 0x001c }
            com.google.android.gms.internal.zzcjj r4 = r4.zzayp()     // Catch:{ all -> 0x001c }
            com.google.android.gms.internal.zzcjl r4 = r4.zzbau()     // Catch:{ all -> 0x001c }
            java.lang.String r5 = "Service connected with null binder"
            r4.log(r5)     // Catch:{ all -> 0x001c }
            monitor-exit(r3)     // Catch:{ all -> 0x001c }
            return
        L_0x001c:
            r4 = move-exception
            goto L_0x0098
        L_0x001f:
            r0 = 0
            java.lang.String r1 = r5.getInterfaceDescriptor()     // Catch:{ RemoteException -> 0x0062 }
            java.lang.String r2 = "com.google.android.gms.measurement.internal.IMeasurementService"
            boolean r2 = r2.equals(r1)     // Catch:{ RemoteException -> 0x0062 }
            if (r2 == 0) goto L_0x0052
            if (r5 != 0) goto L_0x002f
            goto L_0x0042
        L_0x002f:
            java.lang.String r1 = "com.google.android.gms.measurement.internal.IMeasurementService"
            android.os.IInterface r1 = r5.queryLocalInterface(r1)     // Catch:{ RemoteException -> 0x0062 }
            boolean r2 = r1 instanceof com.google.android.gms.internal.zzcjb     // Catch:{ RemoteException -> 0x0062 }
            if (r2 == 0) goto L_0x003c
            com.google.android.gms.internal.zzcjb r1 = (com.google.android.gms.internal.zzcjb) r1     // Catch:{ RemoteException -> 0x0062 }
            goto L_0x0041
        L_0x003c:
            com.google.android.gms.internal.zzcjd r1 = new com.google.android.gms.internal.zzcjd     // Catch:{ RemoteException -> 0x0062 }
            r1.<init>(r5)     // Catch:{ RemoteException -> 0x0062 }
        L_0x0041:
            r0 = r1
        L_0x0042:
            com.google.android.gms.internal.zzcme r5 = r3.zzjri     // Catch:{ RemoteException -> 0x0062 }
            com.google.android.gms.internal.zzcjj r5 = r5.zzayp()     // Catch:{ RemoteException -> 0x0062 }
            com.google.android.gms.internal.zzcjl r5 = r5.zzbba()     // Catch:{ RemoteException -> 0x0062 }
            java.lang.String r1 = "Bound to IMeasurementService interface"
            r5.log(r1)     // Catch:{ RemoteException -> 0x0062 }
            goto L_0x0071
        L_0x0052:
            com.google.android.gms.internal.zzcme r5 = r3.zzjri     // Catch:{ RemoteException -> 0x0062 }
            com.google.android.gms.internal.zzcjj r5 = r5.zzayp()     // Catch:{ RemoteException -> 0x0062 }
            com.google.android.gms.internal.zzcjl r5 = r5.zzbau()     // Catch:{ RemoteException -> 0x0062 }
            java.lang.String r2 = "Got binder with a wrong descriptor"
            r5.zzj(r2, r1)     // Catch:{ RemoteException -> 0x0062 }
            goto L_0x0071
        L_0x0062:
            com.google.android.gms.internal.zzcme r5 = r3.zzjri     // Catch:{ all -> 0x001c }
            com.google.android.gms.internal.zzcjj r5 = r5.zzayp()     // Catch:{ all -> 0x001c }
            com.google.android.gms.internal.zzcjl r5 = r5.zzbau()     // Catch:{ all -> 0x001c }
            java.lang.String r1 = "Service connect failed to get IMeasurementService"
            r5.log(r1)     // Catch:{ all -> 0x001c }
        L_0x0071:
            if (r0 != 0) goto L_0x0088
            r3.zzjrp = r4     // Catch:{ all -> 0x001c }
            com.google.android.gms.common.stats.zza.zzanm()     // Catch:{ IllegalArgumentException -> 0x0096 }
            com.google.android.gms.internal.zzcme r4 = r3.zzjri     // Catch:{ IllegalArgumentException -> 0x0096 }
            android.content.Context r4 = r4.getContext()     // Catch:{ IllegalArgumentException -> 0x0096 }
            com.google.android.gms.internal.zzcme r5 = r3.zzjri     // Catch:{ IllegalArgumentException -> 0x0096 }
            com.google.android.gms.internal.zzcms r5 = r5.zzjrb     // Catch:{ IllegalArgumentException -> 0x0096 }
            r4.unbindService(r5)     // Catch:{ IllegalArgumentException -> 0x0096 }
            goto L_0x0096
        L_0x0088:
            com.google.android.gms.internal.zzcme r4 = r3.zzjri     // Catch:{ all -> 0x001c }
            com.google.android.gms.internal.zzcke r4 = r4.zzayo()     // Catch:{ all -> 0x001c }
            com.google.android.gms.internal.zzcmt r5 = new com.google.android.gms.internal.zzcmt     // Catch:{ all -> 0x001c }
            r5.<init>(r3, r0)     // Catch:{ all -> 0x001c }
            r4.zzh(r5)     // Catch:{ all -> 0x001c }
        L_0x0096:
            monitor-exit(r3)     // Catch:{ all -> 0x001c }
            return
        L_0x0098:
            monitor-exit(r3)     // Catch:{ all -> 0x001c }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcms.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        zzbq.zzgn("MeasurementServiceConnection.onServiceDisconnected");
        this.zzjri.zzayp().zzbaz().log("Service disconnected");
        this.zzjri.zzayo().zzh(new zzcmu(this, componentName));
    }

    public final void zzbcm() {
        this.zzjri.zzwj();
        Context context = this.zzjri.getContext();
        synchronized (this) {
            if (this.zzjrp) {
                this.zzjri.zzayp().zzbba().log("Connection attempt already in progress");
            } else if (this.zzjrq != null) {
                this.zzjri.zzayp().zzbba().log("Already awaiting connection attempt");
            } else {
                this.zzjrq = new zzcji(context, Looper.getMainLooper(), this, this);
                this.zzjri.zzayp().zzbba().log("Connecting to remote service");
                this.zzjrp = true;
                this.zzjrq.zzals();
            }
        }
    }

    public final void zzm(Intent intent) {
        this.zzjri.zzwj();
        Context context = this.zzjri.getContext();
        zza zzanm = zza.zzanm();
        synchronized (this) {
            if (this.zzjrp) {
                this.zzjri.zzayp().zzbba().log("Connection attempt already in progress");
                return;
            }
            this.zzjri.zzayp().zzbba().log("Using local app measurement service");
            this.zzjrp = true;
            zzanm.zza(context, intent, this.zzjri.zzjrb, 129);
        }
    }
}
