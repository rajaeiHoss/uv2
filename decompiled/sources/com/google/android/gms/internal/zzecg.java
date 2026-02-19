package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class zzecg extends zzbgl {
    public static final Parcelable.Creator<zzecg> CREATOR = new zzech();
    private int zzehz;
    private List<String> zzmtb;

    public zzecg() {
        this((List<String>) null);
    }

    zzecg(int i, List<String> list) {
        List<String> emptyList;
        this.zzehz = i;
        if (list == null || list.isEmpty()) {
            emptyList = Collections.emptyList();
        } else {
            for (int i2 = 0; i2 < list.size(); i2++) {
                String str = list.get(i2);
                if (TextUtils.isEmpty(str)) {
                    str = null;
                }
                list.set(i2, str);
            }
            emptyList = Collections.unmodifiableList(list);
        }
        this.zzmtb = emptyList;
    }

    private zzecg(List<String> list) {
        this.zzehz = 1;
        this.zzmtb = new ArrayList();
        if (list != null && !list.isEmpty()) {
            this.zzmtb.addAll(list);
        }
    }

    public static zzecg zza(zzecg zzecg) {
        return new zzecg(zzecg != null ? zzecg.zzmtb : null);
    }

    public static zzecg zzbul() {
        return new zzecg((List<String>) null);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zzehz);
        zzbgo.zzb(parcel, 2, this.zzmtb, false);
        zzbgo.zzai(parcel, zze);
    }

    public final List<String> zzbuk() {
        return this.zzmtb;
    }
}
