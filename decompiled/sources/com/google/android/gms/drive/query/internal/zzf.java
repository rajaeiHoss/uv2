package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Locale;

public final class zzf extends zzbgl {
    public static final Parcelable.Creator<zzf> CREATOR = new zzg();
    private String zzgyi;
    private boolean zzhbn;

    public zzf(String str, boolean z) {
        this.zzgyi = str;
        this.zzhbn = z;
    }

    public final String toString() {
        Locale locale = Locale.US;
        Object[] objArr = new Object[2];
        objArr[0] = this.zzgyi;
        objArr[1] = this.zzhbn ? "ASC" : "DESC";
        return String.format(locale, "FieldWithSortOrder[%s %s]", objArr);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzgyi, false);
        zzbgo.zza(parcel, 2, this.zzhbn);
        zzbgo.zzai(parcel, zze);
    }
}
