package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class zzc extends zzbgl {
    public static final Parcelable.Creator<zzc> CREATOR = new zzd();
    final String mValue;
    final CustomPropertyKey zzgyq;

    public zzc(CustomPropertyKey customPropertyKey, String str) {
        zzbq.checkNotNull(customPropertyKey, "key");
        this.zzgyq = customPropertyKey;
        this.mValue = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && obj.getClass() == getClass()) {
            zzc zzc = (zzc) obj;
            return zzbg.equal(this.zzgyq, zzc.zzgyq) && zzbg.equal(this.mValue, zzc.mValue);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzgyq, this.mValue});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgyq, i, false);
        zzbgo.zza(parcel, 3, this.mValue, false);
        zzbgo.zzai(parcel, zze);
    }
}
