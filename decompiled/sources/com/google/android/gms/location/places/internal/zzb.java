package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class zzb extends zzbgl {
    public static final Parcelable.Creator<zzb> CREATOR = new zzax();
    final int mLength;
    final int mOffset;

    public zzb(int i, int i2) {
        this.mOffset = i;
        this.mLength = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzb)) {
            return false;
        }
        zzb zzb = (zzb) obj;
        return zzbg.equal(Integer.valueOf(this.mOffset), Integer.valueOf(zzb.mOffset)) && zzbg.equal(Integer.valueOf(this.mLength), Integer.valueOf(zzb.mLength));
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.mOffset), Integer.valueOf(this.mLength)});
    }

    public final String toString() {
        return zzbg.zzx(this).zzg("offset", Integer.valueOf(this.mOffset)).zzg("length", Integer.valueOf(this.mLength)).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.mOffset);
        zzbgo.zzc(parcel, 2, this.mLength);
        zzbgo.zzai(parcel, zze);
    }
}
