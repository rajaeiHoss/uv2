package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.Locale;

public final class zzb extends zzbgl implements DriveEvent {
    public static final Parcelable.Creator<zzb> CREATOR = new zzc();
    private String zzehk;
    private zze zzgrn;

    public zzb(String str, zze zze) {
        this.zzehk = str;
        this.zzgrn = zze;
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            if (obj == this) {
                return true;
            }
            zzb zzb = (zzb) obj;
            return zzbg.equal(this.zzgrn, zzb.zzgrn) && zzbg.equal(this.zzehk, zzb.zzehk);
        }
        return false;
    }

    public final int getType() {
        return 4;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzgrn, this.zzehk});
    }

    public final String toString() {
        return String.format(Locale.US, "ChangesAvailableEvent [changesAvailableOptions=%s]", new Object[]{this.zzgrn});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzehk, false);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzgrn, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
