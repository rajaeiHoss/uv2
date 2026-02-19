package com.google.android.gms.auth.api.accounttransfer;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.collection.ArraySet;
import com.google.android.gms.internal.zzaym;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbhq;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class zzt extends zzaym {
    public static final Parcelable.Creator<zzt> CREATOR = new zzu();
    private static final HashMap<String, zzbhq<?, ?>> zzejp;
    private int zzcfl;
    private int zzehz;
    private Set<Integer> zzejq;
    private String zzekb;
    private byte[] zzekc;
    private PendingIntent zzekd;
    private DeviceMetaData zzeke;

    static {
        HashMap<String, zzbhq<?, ?>> hashMap = new HashMap<>();
        zzejp = hashMap;
        hashMap.put("accountType", zzbhq.zzl("accountType", 2));
        hashMap.put("status", zzbhq.zzj("status", 3));
        hashMap.put("transferBytes", zzbhq.zzn("transferBytes", 4));
    }

    public zzt() {
        this.zzejq = new ArraySet(3);
        this.zzehz = 1;
    }

    zzt(Set<Integer> set, int i, String str, int i2, byte[] bArr, PendingIntent pendingIntent, DeviceMetaData deviceMetaData) {
        this.zzejq = set;
        this.zzehz = i;
        this.zzekb = str;
        this.zzcfl = i2;
        this.zzekc = bArr;
        this.zzekd = pendingIntent;
        this.zzeke = deviceMetaData;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        Set<Integer> set = this.zzejq;
        if (set.contains(1)) {
            zzbgo.zzc(parcel, 1, this.zzehz);
        }
        if (set.contains(2)) {
            zzbgo.zza(parcel, 2, this.zzekb, true);
        }
        if (set.contains(3)) {
            zzbgo.zzc(parcel, 3, this.zzcfl);
        }
        if (set.contains(4)) {
            zzbgo.zza(parcel, 4, this.zzekc, true);
        }
        if (set.contains(5)) {
            zzbgo.zza(parcel, 5, (Parcelable) this.zzekd, i, true);
        }
        if (set.contains(6)) {
            zzbgo.zza(parcel, 6, (Parcelable) this.zzeke, i, true);
        }
        zzbgo.zzai(parcel, zze);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zzbhq zzbhq) {
        return this.zzejq.contains(Integer.valueOf(zzbhq.zzane()));
    }

    public final /* synthetic */ Map zzabz() {
        return zzejp;
    }

    /* access modifiers changed from: protected */
    public final Object zzb(zzbhq zzbhq) {
        int i;
        int zzane = zzbhq.zzane();
        if (zzane == 1) {
            i = this.zzehz;
        } else if (zzane == 2) {
            return this.zzekb;
        } else {
            if (zzane == 3) {
                i = this.zzcfl;
            } else if (zzane == 4) {
                return this.zzekc;
            } else {
                int zzane2 = zzbhq.zzane();
                StringBuilder sb = new StringBuilder(37);
                sb.append("Unknown SafeParcelable id=");
                sb.append(zzane2);
                throw new IllegalStateException(sb.toString());
            }
        }
        return Integer.valueOf(i);
    }
}
