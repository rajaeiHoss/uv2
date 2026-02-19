package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzf;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Set;

public final class zzo extends zzbgl {
    public static final Parcelable.Creator<zzo> CREATOR = new zzp();
    private static zzo zziwn = zzx("test_type", 1);
    private static zzo zziwo = zzx("labeled_place", 6);
    private static zzo zziwp;
    private static Set<zzo> zziwq;
    private String zzczr;
    private int zziwr;

    static {
        zzo zzx = zzx("here_content", 7);
        zziwp = zzx;
        zziwq = zzf.zza(zziwn, zziwo, zzx);
    }

    zzo(String str, int i) {
        zzbq.zzgv(str);
        this.zzczr = str;
        this.zziwr = i;
    }

    private static zzo zzx(String str, int i) {
        return new zzo(str, i);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzo)) {
            return false;
        }
        zzo zzo = (zzo) obj;
        return this.zzczr.equals(zzo.zzczr) && this.zziwr == zzo.zziwr;
    }

    public final int hashCode() {
        return this.zzczr.hashCode();
    }

    public final String toString() {
        return this.zzczr;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzczr, false);
        zzbgo.zzc(parcel, 2, this.zziwr);
        zzbgo.zzai(parcel, zze);
    }
}
