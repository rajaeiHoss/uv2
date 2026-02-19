package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.collection.ArrayMap;
import com.google.android.gms.internal.zzaym;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbhq;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import java.util.Map;

public class zzo extends zzaym {
    public static final Parcelable.Creator<zzo> CREATOR = new zzp();
    private static final ArrayMap<String, zzbhq<?, ?>> zzeju;
    private int zzehz;
    private List<String> zzejv;
    private List<String> zzejw;
    private List<String> zzejx;
    private List<String> zzejy;
    private List<String> zzejz;

    static {
        ArrayMap<String, zzbhq<?, ?>> arrayMap = new ArrayMap<>();
        zzeju = arrayMap;
        arrayMap.put("registered", zzbhq.zzm("registered", 2));
        arrayMap.put("in_progress", zzbhq.zzm("in_progress", 3));
        arrayMap.put(FirebaseAnalytics.Param.SUCCESS, zzbhq.zzm(FirebaseAnalytics.Param.SUCCESS, 4));
        arrayMap.put("failed", zzbhq.zzm("failed", 5));
        arrayMap.put("escrowed", zzbhq.zzm("escrowed", 6));
    }

    public zzo() {
        this.zzehz = 1;
    }

    zzo(int i, List<String> list, List<String> list2, List<String> list3, List<String> list4, List<String> list5) {
        this.zzehz = i;
        this.zzejv = list;
        this.zzejw = list2;
        this.zzejx = list3;
        this.zzejy = list4;
        this.zzejz = list5;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zzehz);
        zzbgo.zzb(parcel, 2, this.zzejv, false);
        zzbgo.zzb(parcel, 3, this.zzejw, false);
        zzbgo.zzb(parcel, 4, this.zzejx, false);
        zzbgo.zzb(parcel, 5, this.zzejy, false);
        zzbgo.zzb(parcel, 6, this.zzejz, false);
        zzbgo.zzai(parcel, zze);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zzbhq zzbhq) {
        return true;
    }

    public final Map<String, zzbhq<?, ?>> zzabz() {
        return zzeju;
    }

    /* access modifiers changed from: protected */
    public final Object zzb(zzbhq zzbhq) {
        switch (zzbhq.zzane()) {
            case 1:
                return Integer.valueOf(this.zzehz);
            case 2:
                return this.zzejv;
            case 3:
                return this.zzejw;
            case 4:
                return this.zzejx;
            case 5:
                return this.zzejy;
            case 6:
                return this.zzejz;
            default:
                int zzane = zzbhq.zzane();
                StringBuilder sb = new StringBuilder(37);
                sb.append("Unknown SafeParcelable id=");
                sb.append(zzane);
                throw new IllegalStateException(sb.toString());
        }
    }
}
