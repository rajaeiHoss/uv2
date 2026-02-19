package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.zzf;
import com.google.android.gms.internal.zzbic;
import com.google.android.gms.internal.zzcyj;
import com.google.android.gms.internal.zzcyk;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public final class zzaa implements zzcc {
    private final Looper zzalj;
    private final zzbm zzfsq;
    /* access modifiers changed from: private */
    public final Lock zzfwa;
    private final zzr zzfwf;
    /* access modifiers changed from: private */
    public final Map<Api.zzc<?>, zzz<?>> zzfwg = new HashMap();
    /* access modifiers changed from: private */
    public final Map<Api.zzc<?>, zzz<?>> zzfwh = new HashMap();
    private final Map<Api<?>, Boolean> zzfwi;
    /* access modifiers changed from: private */
    public final zzba zzfwj;
    private final zzf zzfwk;
    /* access modifiers changed from: private */
    public final Condition zzfwl;
    private final boolean zzfwm;
    /* access modifiers changed from: private */
    public final boolean zzfwn;
    private final Queue<zzm<?, ?>> zzfwo = new LinkedList();
    /* access modifiers changed from: private */
    public boolean zzfwp;
    /* access modifiers changed from: private */
    public Map<zzh<?>, ConnectionResult> zzfwq;
    /* access modifiers changed from: private */
    public Map<zzh<?>, ConnectionResult> zzfwr;
    private zzad zzfws;
    /* access modifiers changed from: private */
    public ConnectionResult zzfwt;

    public zzaa(Context context, Lock lock, Looper looper, zzf zzf, Map<Api.zzc<?>, Api.zze> map, zzr zzr, Map<Api<?>, Boolean> map2, Api.zza<? extends zzcyj, zzcyk> zza, ArrayList<zzt> arrayList, zzba zzba, boolean z) {
        boolean z2;
        boolean z3;
        boolean z4;
        this.zzfwa = lock;
        this.zzalj = looper;
        this.zzfwl = lock.newCondition();
        this.zzfwk = zzf;
        this.zzfwj = zzba;
        this.zzfwi = map2;
        this.zzfwf = zzr;
        this.zzfwm = z;
        HashMap hashMap = new HashMap();
        for (Api next : map2.keySet()) {
            hashMap.put(next.zzahm(), next);
        }
        HashMap hashMap2 = new HashMap();
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            zzt zzt = (zzt) obj;
            hashMap2.put(zzt.zzfop, zzt);
        }
        boolean z5 = false;
        boolean z6 = true;
        boolean z7 = false;
        for (Map.Entry next2 : map.entrySet()) {
            Api api = (Api) hashMap.get(next2.getKey());
            Api.zze zze = (Api.zze) next2.getValue();
            if (zze.zzahn()) {
                z3 = z6;
                if (!this.zzfwi.get(api).booleanValue()) {
                    z4 = true;
                    z2 = true;
                } else {
                    z2 = z7;
                    z4 = true;
                }
            } else {
                z4 = z5;
                z2 = z7;
                z3 = false;
            }
            zzz zzz2 = new zzz(context, api, looper, zze, (zzt) hashMap2.get(api), zzr, zza);
            this.zzfwg.put((Api.zzc) next2.getKey(), zzz2);
            if (zze.zzacc()) {
                this.zzfwh.put((Api.zzc) next2.getKey(), zzz2);
            }
            z5 = z4;
            z6 = z3;
            z7 = z2;
        }
        this.zzfwn = z5 && !z6 && !z7;
        this.zzfsq = zzbm.zzajy();
    }

    /* access modifiers changed from: private */
    public final boolean zza(zzz<?> zzz, ConnectionResult connectionResult) {
        return !connectionResult.isSuccess() && !connectionResult.hasResolution() && this.zzfwi.get(zzz.zzaht()).booleanValue() && zzz.zzaix().zzahn() && this.zzfwk.isUserResolvableError(connectionResult.getErrorCode());
    }

    private final boolean zzaiy() {
        this.zzfwa.lock();
        try {
            if (this.zzfwp) {
                if (this.zzfwm) {
                    for (Api.zzc<?> zzb : this.zzfwh.keySet()) {
                        ConnectionResult zzb2 = zzb(zzb);
                        if (zzb2 != null) {
                            if (!zzb2.isSuccess()) {
                            }
                        }
                    }
                    this.zzfwa.unlock();
                    return true;
                }
            }
            return false;
        } finally {
            this.zzfwa.unlock();
        }
    }

    /* access modifiers changed from: private */
    public final void zzaiz() {
        if (this.zzfwf == null) {
            this.zzfwj.zzfyk = Collections.emptySet();
            return;
        }
        HashSet hashSet = new HashSet(this.zzfwf.zzamf());
        Map<Api<?>, com.google.android.gms.common.internal.zzt> zzamh = this.zzfwf.zzamh();
        for (Api next : zzamh.keySet()) {
            ConnectionResult connectionResult = getConnectionResult(next);
            if (connectionResult != null && connectionResult.isSuccess()) {
                hashSet.addAll(zzamh.get(next).zzenh);
            }
        }
        this.zzfwj.zzfyk = hashSet;
    }

    /* access modifiers changed from: private */
    public final void zzaja() {
        while (!this.zzfwo.isEmpty()) {
            zze(this.zzfwo.remove());
        }
        this.zzfwj.zzk((Bundle) null);
    }

    /* access modifiers changed from: private */
    public final ConnectionResult zzajb() {
        int i = 0;
        ConnectionResult connectionResult = null;
        ConnectionResult connectionResult2 = null;
        int i2 = 0;
        for (zzz next : this.zzfwg.values()) {
            Api zzaht = next.zzaht();
            ConnectionResult connectionResult3 = this.zzfwq.get(next.zzahv());
            if (!connectionResult3.isSuccess() && (!this.zzfwi.get(zzaht).booleanValue() || connectionResult3.hasResolution() || this.zzfwk.isUserResolvableError(connectionResult3.getErrorCode()))) {
                if (connectionResult3.getErrorCode() != 4 || !this.zzfwm) {
                    int priority = zzaht.zzahk().getPriority();
                    if (connectionResult == null || i > priority) {
                        connectionResult = connectionResult3;
                        i = priority;
                    }
                } else {
                    int priority2 = zzaht.zzahk().getPriority();
                    if (connectionResult2 == null || i2 > priority2) {
                        connectionResult2 = connectionResult3;
                        i2 = priority2;
                    }
                }
            }
        }
        return (connectionResult == null || connectionResult2 == null || i <= i2) ? connectionResult : connectionResult2;
    }

    private final ConnectionResult zzb(Api.zzc<?> zzc) {
        this.zzfwa.lock();
        try {
            zzz zzz = this.zzfwg.get(zzc);
            Map<zzh<?>, ConnectionResult> map = this.zzfwq;
            if (map != null && zzz != null) {
                return map.get(zzz.zzahv());
            }
            this.zzfwa.unlock();
            return null;
        } finally {
            this.zzfwa.unlock();
        }
    }

    private final <T extends zzm<? extends Result, ? extends Api.zzb>> boolean zzg(T t) {
        Api.zzc zzahm = t.zzahm();
        ConnectionResult zzb = zzb((Api.zzc<?>) zzahm);
        if (zzb == null || zzb.getErrorCode() != 4) {
            return false;
        }
        t.zzu(new Status(4, (String) null, this.zzfsq.zza((zzh<?>) this.zzfwg.get(zzahm).zzahv(), System.identityHashCode(this.zzfwj))));
        return true;
    }

    public final ConnectionResult blockingConnect() {
        connect();
        while (isConnecting()) {
            try {
                this.zzfwl.await();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, (PendingIntent) null);
            }
        }
        if (isConnected()) {
            return ConnectionResult.zzfqt;
        }
        ConnectionResult connectionResult = this.zzfwt;
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
                    nanos = this.zzfwl.awaitNanos(nanos);
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                    return new ConnectionResult(15, (PendingIntent) null);
                }
            }
        }
        if (isConnected()) {
            return ConnectionResult.zzfqt;
        }
        ConnectionResult connectionResult = this.zzfwt;
        return connectionResult != null ? connectionResult : new ConnectionResult(13, (PendingIntent) null);
    }

    public final void connect() {
        this.zzfwa.lock();
        try {
            if (!this.zzfwp) {
                this.zzfwp = true;
                this.zzfwq = null;
                this.zzfwr = null;
                this.zzfws = null;
                this.zzfwt = null;
                this.zzfsq.zzaih();
                this.zzfsq.zza((Iterable<? extends GoogleApi<?>>) this.zzfwg.values()).addOnCompleteListener((Executor) new zzbic(this.zzalj), new zzac(this));
            }
        } finally {
            this.zzfwa.unlock();
        }
    }

    public final void disconnect() {
        this.zzfwa.lock();
        try {
            this.zzfwp = false;
            this.zzfwq = null;
            this.zzfwr = null;
            zzad zzad = this.zzfws;
            if (zzad != null) {
                zzad.cancel();
                this.zzfws = null;
            }
            this.zzfwt = null;
            while (!this.zzfwo.isEmpty()) {
                zzm remove = this.zzfwo.remove();
                remove.zza((zzdn) null);
                remove.cancel();
            }
            this.zzfwl.signalAll();
        } finally {
            this.zzfwa.unlock();
        }
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public final ConnectionResult getConnectionResult(Api<?> api) {
        return zzb(api.zzahm());
    }

    public final boolean isConnected() {
        this.zzfwa.lock();
        try {
            return this.zzfwq != null && this.zzfwt == null;
        } finally {
            this.zzfwa.unlock();
        }
    }

    public final boolean isConnecting() {
        this.zzfwa.lock();
        try {
            return this.zzfwq == null && this.zzfwp;
        } finally {
            this.zzfwa.unlock();
        }
    }

    /* JADX INFO: finally extract failed */
    public final boolean zza(zzcu zzcu) {
        this.zzfwa.lock();
        try {
            if (!this.zzfwp || zzaiy()) {
                this.zzfwa.unlock();
                return false;
            }
            this.zzfsq.zzaih();
            this.zzfws = new zzad(this, zzcu);
            this.zzfsq.zza((Iterable<? extends GoogleApi<?>>) this.zzfwh.values()).addOnCompleteListener((Executor) new zzbic(this.zzalj), this.zzfws);
            this.zzfwa.unlock();
            return true;
        } catch (Throwable th) {
            this.zzfwa.unlock();
            throw th;
        }
    }

    public final void zzaia() {
        this.zzfwa.lock();
        try {
            this.zzfsq.zzaia();
            zzad zzad = this.zzfws;
            if (zzad != null) {
                zzad.cancel();
                this.zzfws = null;
            }
            if (this.zzfwr == null) {
                this.zzfwr = new ArrayMap(this.zzfwh.size());
            }
            ConnectionResult connectionResult = new ConnectionResult(4);
            for (zzz<?> zzahv : this.zzfwh.values()) {
                this.zzfwr.put(zzahv.zzahv(), connectionResult);
            }
            Map<zzh<?>, ConnectionResult> map = this.zzfwq;
            if (map != null) {
                map.putAll(this.zzfwr);
            }
        } finally {
            this.zzfwa.unlock();
        }
    }

    public final void zzais() {
    }

    public final <A extends Api.zzb, R extends Result, T extends zzm<R, A>> T zzd(T t) {
        if (this.zzfwm && zzg(t)) {
            return t;
        }
        if (!isConnected()) {
            this.zzfwo.add(t);
            return t;
        }
        this.zzfwj.zzfyp.zzb(t);
        return this.zzfwg.get(t.zzahm()).zza(t);
    }

    public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zze(T t) {
        Api.zzc zzahm = t.zzahm();
        if (this.zzfwm && zzg(t)) {
            return t;
        }
        this.zzfwj.zzfyp.zzb(t);
        return this.zzfwg.get(zzahm).zzb(t);
    }
}
