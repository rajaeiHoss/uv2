package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class zzbhv extends zzbgl {
    public static final Parcelable.Creator<zzbhv> CREATOR = new zzbhy();
    private int zzehz;
    private final HashMap<String, Map<String, zzbhq<?, ?>>> zzgiy;
    private final ArrayList<zzbhw> zzgiz = null;
    private final String zzgja;

    zzbhv(int i, ArrayList<zzbhw> arrayList, String str) {
        this.zzehz = i;
        HashMap<String, Map<String, zzbhq<?, ?>>> hashMap = new HashMap<>();
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            zzbhw zzbhw = arrayList.get(i2);
            hashMap.put(zzbhw.className, zzbhw.zzank());
        }
        this.zzgiy = hashMap;
        this.zzgja = (String) zzbq.checkNotNull(str);
        zzani();
    }

    private final void zzani() {
        for (String str : this.zzgiy.keySet()) {
            Map<String, zzbhq<?, ?>> map = this.zzgiy.get(str);
            for (String str2 : map.keySet()) {
                ((zzbhq) map.get(str2)).zza(this);
            }
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        for (String next : this.zzgiy.keySet()) {
            sb.append(next);
            sb.append(":\n");
            Map<String, zzbhq<?, ?>> map = this.zzgiy.get(next);
            for (String str : map.keySet()) {
                sb.append("  ");
                sb.append(str);
                sb.append(": ");
                sb.append(map.get(str));
            }
        }
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zzehz);
        ArrayList arrayList = new ArrayList();
        for (String next : this.zzgiy.keySet()) {
            arrayList.add(new zzbhw(next, this.zzgiy.get(next)));
        }
        zzbgo.zzc(parcel, 2, arrayList, false);
        zzbgo.zza(parcel, 3, this.zzgja, false);
        zzbgo.zzai(parcel, zze);
    }

    public final String zzanj() {
        return this.zzgja;
    }

    public final Map<String, zzbhq<?, ?>> zzgz(String str) {
        return this.zzgiy.get(str);
    }
}
