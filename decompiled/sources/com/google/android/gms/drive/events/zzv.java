package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzblw;
import java.util.Arrays;
import java.util.List;

public final class zzv extends zzbgl implements DriveEvent {
    public static final Parcelable.Creator<zzv> CREATOR = new zzw();
    private String zzehk;
    private List<zzblw> zzgsk;

    public zzv(String str, List<zzblw> list) {
        this.zzehk = str;
        this.zzgsk = list;
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            if (obj == this) {
                return true;
            }
            zzv zzv = (zzv) obj;
            return zzbg.equal(this.zzehk, zzv.zzehk) && zzbg.equal(this.zzgsk, zzv.zzgsk);
        }
        return false;
    }

    public final int getType() {
        return 7;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzehk, this.zzgsk});
    }

    public final String toString() {
        return String.format("TransferStateEvent[%s]", new Object[]{TextUtils.join("','", this.zzgsk)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzehk, false);
        zzbgo.zzc(parcel, 3, this.zzgsk, false);
        zzbgo.zzai(parcel, zze);
    }
}
