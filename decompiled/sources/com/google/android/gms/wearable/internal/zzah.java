package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.wearable.CapabilityInfo;
import com.google.android.gms.wearable.Node;
import com.streamax.config.constant.Constants;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class zzah extends zzbgl implements CapabilityInfo {
    public static final Parcelable.Creator<zzah> CREATOR = new zzai();
    private final Object mLock = new Object();
    private final String mName;
    private Set<Node> zzlsr;
    private final List<zzfo> zzlsx;

    public zzah(String str, List<zzfo> list) {
        this.mName = str;
        this.zzlsx = list;
        this.zzlsr = null;
        zzbq.checkNotNull(str);
        zzbq.checkNotNull(list);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzah zzah = (zzah) obj;
        String str = this.mName;
        if (str == null ? zzah.mName != null : !str.equals(zzah.mName)) {
            return false;
        }
        List<zzfo> list = this.zzlsx;
        List<zzfo> list2 = zzah.zzlsx;
        return list == null ? list2 == null : list.equals(list2);
    }

    public final String getName() {
        return this.mName;
    }

    public final Set<Node> getNodes() {
        Set<Node> set;
        synchronized (this.mLock) {
            if (this.zzlsr == null) {
                this.zzlsr = new HashSet(this.zzlsx);
            }
            set = this.zzlsr;
        }
        return set;
    }

    public final int hashCode() {
        String str = this.mName;
        int i = 0;
        int hashCode = ((str != null ? str.hashCode() : 0) + 31) * 31;
        List<zzfo> list = this.zzlsx;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode + i;
    }

    public final String toString() {
        String str = this.mName;
        String valueOf = String.valueOf(this.zzlsx);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 18 + String.valueOf(valueOf).length());
        sb.append("CapabilityInfo{");
        sb.append(str);
        sb.append(", ");
        sb.append(valueOf);
        sb.append(Constants.JsonSstringSuffix);
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, getName(), false);
        zzbgo.zzc(parcel, 3, this.zzlsx, false);
        zzbgo.zzai(parcel, zze);
    }
}
