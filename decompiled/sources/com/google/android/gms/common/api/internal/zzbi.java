package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.zzf;
import com.google.android.gms.internal.zzcyj;
import com.google.android.gms.internal.zzcyk;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public final class zzbi implements zzcc, zzu {
    private final Context mContext;
    private Api.zza<? extends zzcyj, zzcyk> zzfth;
    final zzba zzfvq;
    /* access modifiers changed from: private */
    public final Lock zzfwa;
    private zzr zzfwf;
    private Map<Api<?>, Boolean> zzfwi;
    private final zzf zzfwk;
    final Map<Api.zzc<?>, Api.zze> zzfyj;
    private final Condition zzfyw;
    private final zzbk zzfyx;
    final Map<Api.zzc<?>, ConnectionResult> zzfyy = new HashMap();
    /* access modifiers changed from: private */
    public volatile zzbh zzfyz;
    private ConnectionResult zzfza = null;
    int zzfzb;
    final zzcd zzfzc;

    public zzbi(Context context, zzba zzba, Lock lock, Looper looper, zzf zzf, Map<Api.zzc<?>, Api.zze> map, zzr zzr, Map<Api<?>, Boolean> map2, Api.zza<? extends zzcyj, zzcyk> zza, ArrayList<zzt> arrayList, zzcd zzcd) {
        this.mContext = context;
        this.zzfwa = lock;
        this.zzfwk = zzf;
        this.zzfyj = map;
        this.zzfwf = zzr;
        this.zzfwi = map2;
        this.zzfth = zza;
        this.zzfvq = zzba;
        this.zzfzc = zzcd;
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            ((zzt) obj).zza(this);
        }
        this.zzfyx = new zzbk(this, looper);
        this.zzfyw = lock.newCondition();
        this.zzfyz = new zzaz(this);
    }

    public final ConnectionResult blockingConnect() {
        connect();
        while (isConnecting()) {
            try {
                this.zzfyw.await();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, (PendingIntent) null);
            }
        }
        if (isConnected()) {
            return ConnectionResult.zzfqt;
        }
        ConnectionResult connectionResult = this.zzfza;
        return connectionResult != null ? connectionResult : new ConnectionResult(13, (PendingIntent) null);
    }

    public final ConnectionResult blockingConnect(long j, TimeUnit timeUnit) {
        connect();
        long nanos = timeUnit.toNanos(j);
        while (isConnecting()) {
            if (nanos <= 0) {
                disconnect();
                return new ConnectionResult(14, (PendingIntent) null);
            } else {
                try {
                    nanos = this.zzfyw.awaitNanos(nanos);
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                    return new ConnectionResult(15, (PendingIntent) null);
                }
            }
        }
        if (isConnected()) {
            return ConnectionResult.zzfqt;
        }
        ConnectionResult connectionResult = this.zzfza;
        return connectionResult != null ? connectionResult : new ConnectionResult(13, (PendingIntent) null);
    }

    public final void connect() {
        this.zzfyz.connect();
    }

    public final void disconnect() {
        if (this.zzfyz.disconnect()) {
            this.zzfyy.clear();
        }
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String concat = String.valueOf(str).concat("  ");
        printWriter.append(str).append("mState=").println(this.zzfyz);
        for (Api next : this.zzfwi.keySet()) {
            printWriter.append(str).append(next.getName()).println(":");
            this.zzfyj.get(next.zzahm()).dump(concat, fileDescriptor, printWriter, strArr);
        }
    }

    public final ConnectionResult getConnectionResult(Api<?> api) {
        Api.zzc<?> zzahm = api.zzahm();
        if (!this.zzfyj.containsKey(zzahm)) {
            return null;
        }
        if (this.zzfyj.get(zzahm).isConnected()) {
            return ConnectionResult.zzfqt;
        }
        if (this.zzfyy.containsKey(zzahm)) {
            return this.zzfyy.get(zzahm);
        }
        return null;
    }

    public final boolean isConnected() {
        return this.zzfyz instanceof zzal;
    }

    public final boolean isConnecting() {
        return this.zzfyz instanceof zzao;
    }

    public final void onConnected(Bundle bundle) {
        this.zzfwa.lock();
        try {
            this.zzfyz.onConnected(bundle);
        } finally {
            this.zzfwa.unlock();
        }
    }

    public final void onConnectionSuspended(int i) {
        this.zzfwa.lock();
        try {
            this.zzfyz.onConnectionSuspended(i);
        } finally {
            this.zzfwa.unlock();
        }
    }

    public final void zza(ConnectionResult connectionResult, Api<?> api, boolean z) {
        this.zzfwa.lock();
        try {
            this.zzfyz.zza(connectionResult, api, z);
        } finally {
            this.zzfwa.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzbj zzbj) {
        this.zzfyx.sendMessage(this.zzfyx.obtainMessage(1, zzbj));
    }

    public final boolean zza(zzcu zzcu) {
        return false;
    }

    public final void zzaia() {
    }

    public final void zzais() {
        if (isConnected()) {
            ((zzal) this.zzfyz).zzaji();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzajv() {
        this.zzfwa.lock();
        try {
            this.zzfyz = new zzao(this, this.zzfwf, this.zzfwi, this.zzfwk, this.zzfth, this.zzfwa, this.mContext);
            this.zzfyz.begin();
            this.zzfyw.signalAll();
        } finally {
            this.zzfwa.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzajw() {
        this.zzfwa.lock();
        try {
            this.zzfvq.zzajs();
            this.zzfyz = new zzal(this);
            this.zzfyz.begin();
            this.zzfyw.signalAll();
        } finally {
            this.zzfwa.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb(RuntimeException runtimeException) {
        this.zzfyx.sendMessage(this.zzfyx.obtainMessage(2, runtimeException));
    }

    public final <A extends Api.zzb, R extends Result, T extends zzm<R, A>> T zzd(T t) {
        t.zzaiq();
        return this.zzfyz.zzd(t);
    }

    public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zze(T t) {
        t.zzaiq();
        return this.zzfyz.zze(t);
    }

    /* access modifiers changed from: package-private */
    public final void zzg(ConnectionResult connectionResult) {
        this.zzfwa.lock();
        try {
            this.zzfza = connectionResult;
            this.zzfyz = new zzaz(this);
            this.zzfyz.begin();
            this.zzfyw.signalAll();
        } finally {
            this.zzfwa.unlock();
        }
    }
}
