package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.zzf;
import com.google.android.gms.internal.zzcyj;
import com.google.android.gms.internal.zzcyk;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

final class zzv implements zzcc {
    private final Context mContext;
    private final Looper zzalj;
    private final zzba zzfvq;
    /* access modifiers changed from: private */
    public final zzbi zzfvr;
    /* access modifiers changed from: private */
    public final zzbi zzfvs;
    private final Map<Api.zzc<?>, zzbi> zzfvt;
    private final Set<zzcu> zzfvu = Collections.newSetFromMap(new WeakHashMap());
    private final Api.zze zzfvv;
    private Bundle zzfvw;
    /* access modifiers changed from: private */
    public ConnectionResult zzfvx = null;
    /* access modifiers changed from: private */
    public ConnectionResult zzfvy = null;
    /* access modifiers changed from: private */
    public boolean zzfvz = false;
    /* access modifiers changed from: private */
    public final Lock zzfwa;
    private int zzfwb = 0;

    private zzv(Context context, zzba zzba, Lock lock, Looper looper, zzf zzf, Map<Api.zzc<?>, Api.zze> map, Map<Api.zzc<?>, Api.zze> map2, zzr zzr, Api.zza<? extends zzcyj, zzcyk> zza, Api.zze zze, ArrayList<zzt> arrayList, ArrayList<zzt> arrayList2, Map<Api<?>, Boolean> map3, Map<Api<?>, Boolean> map4) {
        this.mContext = context;
        this.zzfvq = zzba;
        this.zzfwa = lock;
        this.zzalj = looper;
        this.zzfvv = zze;
        Context context2 = context;
        zzba zzba2 = zzba;
        Lock lock2 = lock;
        Looper looper2 = looper;
        zzf zzf2 = zzf;
        this.zzfvr = new zzbi(context2, zzba2, lock2, looper2, zzf2, map2, (zzr) null, map4, (Api.zza<? extends zzcyj, zzcyk>) null, arrayList2, new zzx(this, (zzw) null));
        this.zzfvs = new zzbi(context2, zzba2, lock2, looper2, zzf2, map, zzr, map3, zza, arrayList, new zzy(this, (zzw) null));
        ArrayMap arrayMap = new ArrayMap();
        for (Api.zzc<?> put : map2.keySet()) {
            arrayMap.put(put, this.zzfvr);
        }
        for (Api.zzc<?> put2 : map.keySet()) {
            arrayMap.put(put2, this.zzfvs);
        }
        this.zzfvt = Collections.unmodifiableMap(arrayMap);
    }

    public static zzv zza(Context context, zzba zzba, Lock lock, Looper looper, zzf zzf, Map<Api.zzc<?>, Api.zze> map, zzr zzr, Map<Api<?>, Boolean> map2, Api.zza<? extends zzcyj, zzcyk> zza, ArrayList<zzt> arrayList) {
        Map<Api<?>, Boolean> map3 = map2;
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        Api.zze zze = null;
        for (Map.Entry next : map.entrySet()) {
            Api.zze zze2 = (Api.zze) next.getValue();
            if (zze2.zzacn()) {
                zze = zze2;
            }
            boolean zzacc = zze2.zzacc();
            Api.zzc zzc = (Api.zzc) next.getKey();
            if (zzacc) {
                arrayMap.put(zzc, zze2);
            } else {
                arrayMap2.put(zzc, zze2);
            }
        }
        zzbq.zza(!arrayMap.isEmpty(), (Object) "CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
        ArrayMap arrayMap3 = new ArrayMap();
        ArrayMap arrayMap4 = new ArrayMap();
        for (Api next2 : map2.keySet()) {
            Api.zzc<?> zzahm = next2.zzahm();
            if (arrayMap.containsKey(zzahm)) {
                arrayMap3.put(next2, map3.get(next2));
            } else if (arrayMap2.containsKey(zzahm)) {
                arrayMap4.put(next2, map3.get(next2));
            } else {
                throw new IllegalStateException("Each API in the isOptionalMap must have a corresponding client in the clients map.");
            }
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = arrayList;
        int size = arrayList4.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList4.get(i);
            i++;
            zzt zzt = (zzt) obj;
            if (arrayMap3.containsKey(zzt.zzfop)) {
                arrayList2.add(zzt);
            } else if (arrayMap4.containsKey(zzt.zzfop)) {
                arrayList3.add(zzt);
            } else {
                throw new IllegalStateException("Each ClientCallbacks must have a corresponding API in the isOptionalMap");
            }
        }
        return new zzv(context, zzba, lock, looper, zzf, arrayMap, arrayMap2, zzr, zza, zze, arrayList2, arrayList3, arrayMap3, arrayMap4);
    }

    private final void zza(ConnectionResult connectionResult) {
        int i = this.zzfwb;
        if (i != 1) {
            if (i != 2) {
                Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new Exception());
                this.zzfwb = 0;
            }
            this.zzfvq.zzc(connectionResult);
        }
        zzaiu();
        this.zzfwb = 0;
    }

    /* access modifiers changed from: private */
    public final void zzait() {
        if (zzb(this.zzfvx)) {
            if (zzb(this.zzfvy) || zzaiv()) {
                int i = this.zzfwb;
                if (i != 1) {
                    if (i != 2) {
                        Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new AssertionError());
                        this.zzfwb = 0;
                        return;
                    }
                    this.zzfvq.zzk(this.zzfvw);
                }
                zzaiu();
                this.zzfwb = 0;
                return;
            }
            ConnectionResult connectionResult = this.zzfvy;
            if (connectionResult == null) {
                return;
            }
            if (this.zzfwb == 1) {
                zzaiu();
                return;
            }
            zza(connectionResult);
            this.zzfvr.disconnect();
        } else if (this.zzfvx == null || !zzb(this.zzfvy)) {
            ConnectionResult connectionResult2 = this.zzfvx;
            if (connectionResult2 != null && this.zzfvy != null) {
                if (this.zzfvs.zzfzb < this.zzfvr.zzfzb) {
                    connectionResult2 = this.zzfvy;
                }
                zza(connectionResult2);
            }
        } else {
            this.zzfvs.disconnect();
            zza(this.zzfvx);
        }
    }

    private final void zzaiu() {
        for (zzcu zzacm : this.zzfvu) {
            zzacm.zzacm();
        }
        this.zzfvu.clear();
    }

    private final boolean zzaiv() {
        ConnectionResult connectionResult = this.zzfvy;
        return connectionResult != null && connectionResult.getErrorCode() == 4;
    }

    private final PendingIntent zzaiw() {
        if (this.zzfvv == null) {
            return null;
        }
        return PendingIntent.getActivity(this.mContext, System.identityHashCode(this.zzfvq), this.zzfvv.getSignInIntent(), 134217728);
    }

    private static boolean zzb(ConnectionResult connectionResult) {
        return connectionResult != null && connectionResult.isSuccess();
    }

    /* access modifiers changed from: private */
    public final void zze(int i, boolean z) {
        this.zzfvq.zzf(i, z);
        this.zzfvy = null;
        this.zzfvx = null;
    }

    private final boolean zzf(zzm<? extends Result, ? extends Api.zzb> zzm) {
        Api.zzc<? extends Api.zzb> zzahm = zzm.zzahm();
        zzbq.checkArgument(this.zzfvt.containsKey(zzahm), "GoogleApiClient is not configured to use the API required for this call.");
        return this.zzfvt.get(zzahm).equals(this.zzfvs);
    }

    /* access modifiers changed from: private */
    public final void zzj(Bundle bundle) {
        Bundle bundle2 = this.zzfvw;
        if (bundle2 == null) {
            this.zzfvw = bundle;
        } else if (bundle != null) {
            bundle2.putAll(bundle);
        }
    }

    public final ConnectionResult blockingConnect() {
        throw new UnsupportedOperationException();
    }

    public final ConnectionResult blockingConnect(long j, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public final void connect() {
        this.zzfwb = 2;
        this.zzfvz = false;
        this.zzfvy = null;
        this.zzfvx = null;
        this.zzfvr.connect();
        this.zzfvs.connect();
    }

    public final void disconnect() {
        this.zzfvy = null;
        this.zzfvx = null;
        this.zzfwb = 0;
        this.zzfvr.disconnect();
        this.zzfvs.disconnect();
        zzaiu();
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append(str).append("authClient").println(":");
        this.zzfvs.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
        printWriter.append(str).append("anonClient").println(":");
        this.zzfvr.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
    }

    public final ConnectionResult getConnectionResult(Api<?> api) {
        return this.zzfvt.get(api.zzahm()).equals(this.zzfvs) ? zzaiv() ? new ConnectionResult(4, zzaiw()) : this.zzfvs.getConnectionResult(api) : this.zzfvr.getConnectionResult(api);
    }

    public final boolean isConnected() {
        this.zzfwa.lock();
        try {
            boolean z = true;
            if (!this.zzfvr.isConnected() || (!this.zzfvs.isConnected() && !zzaiv() && this.zzfwb != 1)) {
                z = false;
            }
            return z;
        } finally {
            this.zzfwa.unlock();
        }
    }

    public final boolean isConnecting() {
        this.zzfwa.lock();
        try {
            return this.zzfwb == 2;
        } finally {
            this.zzfwa.unlock();
        }
    }

    public final boolean zza(zzcu zzcu) {
        this.zzfwa.lock();
        try {
            if ((isConnecting() || isConnected()) && !this.zzfvs.isConnected()) {
                this.zzfvu.add(zzcu);
                if (this.zzfwb == 0) {
                    this.zzfwb = 1;
                }
                this.zzfvy = null;
                this.zzfvs.connect();
                return true;
            }
            this.zzfwa.unlock();
            return false;
        } finally {
            this.zzfwa.unlock();
        }
    }

    public final void zzaia() {
        this.zzfwa.lock();
        try {
            boolean isConnecting = isConnecting();
            this.zzfvs.disconnect();
            this.zzfvy = new ConnectionResult(4);
            if (isConnecting) {
                new Handler(this.zzalj).post(new zzw(this));
            } else {
                zzaiu();
            }
        } finally {
            this.zzfwa.unlock();
        }
    }

    public final void zzais() {
        this.zzfvr.zzais();
        this.zzfvs.zzais();
    }

    public final <A extends Api.zzb, R extends Result, T extends zzm<R, A>> T zzd(T t) {
        if (!zzf((zzm<? extends Result, ? extends Api.zzb>) t)) {
            return this.zzfvr.zzd(t);
        }
        if (!zzaiv()) {
            return this.zzfvs.zzd(t);
        }
        t.zzu(new Status(4, (String) null, zzaiw()));
        return t;
    }

    public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zze(T t) {
        if (!zzf((zzm<? extends Result, ? extends Api.zzb>) t)) {
            return this.zzfvr.zze(t);
        }
        if (!zzaiv()) {
            return this.zzfvs.zze(t);
        }
        t.zzu(new Status(4, (String) null, zzaiw()));
        return t;
    }
}
