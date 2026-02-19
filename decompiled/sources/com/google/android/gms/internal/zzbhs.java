package com.google.android.gms.internal;

public abstract class zzbhs extends zzbhp implements zzbgp {
    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!getClass().isInstance(obj)) {
            return false;
        }
        zzbhp zzbhp = (zzbhp) obj;
        for (zzbhq next : zzabz().values()) {
            if (zza(next)) {
                if (!zzbhp.zza(next) || !zzb(next).equals(zzbhp.zzb(next))) {
                    return false;
                }
            } else if (zzbhp.zza(next)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        for (zzbhq next : zzabz().values()) {
            if (zza(next)) {
                i = (i * 31) + zzb(next).hashCode();
            }
        }
        return i;
    }

    public Object zzgx(String str) {
        return null;
    }

    public boolean zzgy(String str) {
        return false;
    }
}
