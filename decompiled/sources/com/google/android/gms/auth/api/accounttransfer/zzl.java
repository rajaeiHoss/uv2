package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.internal.zzaym;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbhq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class zzl extends zzaym {
    public static final Parcelable.Creator<zzl> CREATOR = new zzm();
    private static final HashMap<String, zzbhq<?, ?>> zzejp;
    private int zzehz;
    private Set<Integer> zzejq;
    private ArrayList<zzr> zzejr;
    private int zzejs;
    private zzo zzejt;

    static {
        HashMap<String, zzbhq<?, ?>> hashMap = new HashMap<>();
        zzejp = hashMap;
        hashMap.put("authenticatorData", zzbhq.zzb("authenticatorData", 2, zzr.class));
        hashMap.put(NotificationCompat.CATEGORY_PROGRESS, zzbhq.zza(NotificationCompat.CATEGORY_PROGRESS, 4, zzo.class));
    }

    public zzl() {
        this.zzejq = new HashSet(1);
        this.zzehz = 1;
    }

    zzl(Set<Integer> set, int i, ArrayList<zzr> arrayList, int i2, zzo zzo) {
        this.zzejq = set;
        this.zzehz = i;
        this.zzejr = arrayList;
        this.zzejs = i2;
        this.zzejt = zzo;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        Set<Integer> set = this.zzejq;
        if (set.contains(1)) {
            zzbgo.zzc(parcel, 1, this.zzehz);
        }
        if (set.contains(2)) {
            zzbgo.zzc(parcel, 2, this.zzejr, true);
        }
        if (set.contains(3)) {
            zzbgo.zzc(parcel, 3, this.zzejs);
        }
        if (set.contains(4)) {
            zzbgo.zza(parcel, 4, (Parcelable) this.zzejt, i, true);
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
            return this.zzejr;
        }
        if (zzane == 4) {
            return this.zzejt;
        }
        int zzane2 = zzbhq.zzane();
        StringBuilder sb = new StringBuilder(37);
        sb.append("Unknown SafeParcelable id=");
        sb.append(zzane2);
        throw new IllegalStateException(sb.toString());
    }
}
