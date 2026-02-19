package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.List;

@Deprecated
public final class zzal extends zzbgl {
    public static final Parcelable.Creator<zzal> CREATOR = new zzam();
    private String address;
    private String name;
    private String zziyh;
    private String zziyi;
    private List<String> zziyj;

    public zzal(String str, String str2, String str3, String str4, List<String> list) {
        this.name = str;
        this.address = str2;
        this.zziyh = str3;
        this.zziyi = str4;
        this.zziyj = list;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzal)) {
            return false;
        }
        zzal zzal = (zzal) obj;
        return zzbg.equal(this.name, zzal.name) && zzbg.equal(this.address, zzal.address) && zzbg.equal(this.zziyh, zzal.zziyh) && zzbg.equal(this.zziyi, zzal.zziyi) && zzbg.equal(this.zziyj, zzal.zziyj);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.name, this.address, this.zziyh, this.zziyi});
    }

    public final String toString() {
        return zzbg.zzx(this).zzg("name", this.name).zzg("address", this.address).zzg("internationalPhoneNumber", this.zziyh).zzg("regularOpenHours", this.zziyi).zzg("attributions", this.zziyj).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.name, false);
        zzbgo.zza(parcel, 2, this.address, false);
        zzbgo.zza(parcel, 3, this.zziyh, false);
        zzbgo.zza(parcel, 4, this.zziyi, false);
        zzbgo.zzb(parcel, 5, this.zziyj, false);
        zzbgo.zzai(parcel, zze);
    }
}
