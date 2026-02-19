package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzaym;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbhq;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class zzr extends zzaym {
    public static final Parcelable.Creator<zzr> CREATOR = new zzs();
    private static final HashMap<String, zzbhq<?, ?>> zzejp;
    private String mPackageName;
    private String zzayo;
    private int zzehz;
    private Set<Integer> zzejq;
    private zzt zzeka;

    static {
        HashMap<String, zzbhq<?, ?>> hashMap = new HashMap<>();
        zzejp = hashMap;
        hashMap.put("authenticatorInfo", zzbhq.zza("authenticatorInfo", 2, zzt.class));
        hashMap.put("signature", zzbhq.zzl("signature", 3));
        hashMap.put("package", zzbhq.zzl("package", 4));
    }

    public zzr() {
        this.zzejq = new HashSet(3);
        this.zzehz = 1;
    }

    zzr(Set<Integer> set, int i, zzt zzt, String str, String str2) {
        this.zzejq = set;
        this.zzehz = i;
        this.zzeka = zzt;
        this.zzayo = str;
        this.mPackageName = str2;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        Set<Integer> set = this.zzejq;
        if (set.contains(1)) {
            zzbgo.zzc(parcel, 1, this.zzehz);
        }
        if (set.contains(2)) {
            zzbgo.zza(parcel, 2, (Parcelable) this.zzeka, i, true);
        }
        if (set.contains(3)) {
            zzbgo.zza(parcel, 3, this.zzayo, true);
        }
        if (set.contains(4)) {
            zzbgo.zza(parcel, 4, this.mPackageName, true);
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
        int zzane = zzbhq.zzane();
        if (zzane == 1) {
            return Integer.valueOf(this.zzehz);
        }
        if (zzane == 2) {
            return this.zzeka;
        }
        if (zzane == 3) {
            return this.zzayo;
        }
        if (zzane == 4) {
            return this.mPackageName;
        }
        int zzane2 = zzbhq.zzane();
        StringBuilder sb = new StringBuilder(37);
        sb.append("Unknown SafeParcelable id=");
        sb.append(zzane2);
        throw new IllegalStateException(sb.toString());
    }
}
