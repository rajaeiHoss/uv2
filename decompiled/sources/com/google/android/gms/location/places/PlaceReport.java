package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Arrays;

public class PlaceReport extends zzbgl implements ReflectedParcelable {
    public static final Parcelable.Creator<PlaceReport> CREATOR = new zzl();
    private final String mTag;
    private final String zzdwr;
    private int zzehz;
    private final String zzivz;

    PlaceReport(int i, String str, String str2, String str3) {
        this.zzehz = i;
        this.zzivz = str;
        this.mTag = str2;
        this.zzdwr = str3;
    }

    public static PlaceReport create(String str, String str2) {
        zzbq.checkNotNull(str);
        zzbq.zzgv(str2);
        zzbq.zzgv("unknown");
        zzbq.checkArgument(true, "Invalid source");
        return new PlaceReport(1, str, str2, "unknown");
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlaceReport)) {
            return false;
        }
        PlaceReport placeReport = (PlaceReport) obj;
        return zzbg.equal(this.zzivz, placeReport.zzivz) && zzbg.equal(this.mTag, placeReport.mTag) && zzbg.equal(this.zzdwr, placeReport.zzdwr);
    }

    public String getPlaceId() {
        return this.zzivz;
    }

    public String getTag() {
        return this.mTag;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzivz, this.mTag, this.zzdwr});
    }

    public String toString() {
        zzbi zzx = zzbg.zzx(this);
        zzx.zzg("placeId", this.zzivz);
        zzx.zzg("tag", this.mTag);
        if (!"unknown".equals(this.zzdwr)) {
            zzx.zzg(FirebaseAnalytics.Param.SOURCE, this.zzdwr);
        }
        return zzx.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zzehz);
        zzbgo.zza(parcel, 2, getPlaceId(), false);
        zzbgo.zza(parcel, 3, getTag(), false);
        zzbgo.zza(parcel, 4, this.zzdwr, false);
        zzbgo.zzai(parcel, zze);
    }
}
