package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.api.internal.zzcm;
import com.google.android.gms.common.api.internal.zzcq;
import com.google.android.gms.common.api.internal.zzdo;
import com.google.android.gms.tasks.Task;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class zzcon {
    private static zzcon zzjwx;
    private final Map<String, Set<zzck<?>>> zzjwy = new HashMap();
    /* access modifiers changed from: private */
    public final Set<zzck<?>> zzjwz = new HashSet();
    private final Map<String, zzci<String>> zzjxa = new HashMap();

    private zzcon() {
    }

    private final void zza(String str, zzck<?> zzck) {
        Set set = this.zzjwy.get(str);
        if (set == null) {
            set = new HashSet();
            this.zzjwy.put(str, set);
        }
        set.add(zzck);
    }

    public static synchronized zzcon zzbdd() {
        zzcon zzcon;
        synchronized (zzcon.class) {
            if (zzjwx == null) {
                zzjwx = new zzcon();
            }
            zzcon = zzjwx;
        }
        return zzcon;
    }

    public final synchronized <T> zzci<T> zza(GoogleApi googleApi, T t, String str) {
        zzci<T> zza;
        zza = googleApi.zza(t, str);
        zza(str, (zzck<?>) zza.zzakx());
        return zza;
    }

    public final synchronized zzci<String> zza(GoogleApi googleApi, String str, String str2) {
        if (!this.zzjxa.containsKey(str) || !this.zzjxa.get(str).zzafr()) {
            zzci<String> zza = googleApi.zza(str, str2);
            zza(str2, (zzck<?>) zza.zzakx());
            this.zzjxa.put(str, zza);
            return zza;
        }
        return this.zzjxa.get(str);
    }

    public final synchronized void zza(GoogleApi googleApi, String str) {
        Set<zzck<?>> set = this.zzjwy.get(str);
        if (set != null) {
            for (zzck<?> zzck : set) {
                if (this.zzjwz.contains(zzck)) {
                    zzb(googleApi, zzck);
                }
            }
            this.zzjwy.remove(str);
        }
    }

    public final synchronized <T> zzck<T> zzb(GoogleApi googleApi, T t, String str) {
        if (t instanceof String) {
            return (zzck<T>) zza(googleApi, (String) t, str).zzakx();
        }
        return zzcm.zzb(t, str);
    }

    public final synchronized Task<Boolean> zzb(GoogleApi googleApi, zzck<?> zzck) {
        this.zzjwz.remove(zzck);
        return googleApi.zza(zzck);
    }

    public final synchronized Task<Void> zzb(GoogleApi googleApi, zzcq zzcq, zzdo zzdo) {
        this.zzjwz.add(zzcq.zzakx());
        return googleApi.zza(zzcq, zzdo).addOnFailureListener(new zzcoo(this, zzcq));
    }
}
