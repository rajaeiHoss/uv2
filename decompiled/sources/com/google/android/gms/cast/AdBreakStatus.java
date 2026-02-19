package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.internal.zzbdw;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class AdBreakStatus extends zzbgl {
    public static final int AD_BREAK_CLIP_NOT_SKIPPABLE = -1;
    public static final Parcelable.Creator<AdBreakStatus> CREATOR = new zzc();
    private final long zzesq;
    private final long zzesx;
    private final long zzesy;
    private final String zzesz;
    private final String zzeta;

    AdBreakStatus(long j, long j2, String str, String str2, long j3) {
        this.zzesx = j;
        this.zzesy = j2;
        this.zzesz = str;
        this.zzeta = str2;
        this.zzesq = j3;
    }

    static AdBreakStatus zzr(JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject;
        if (jSONObject2 == null || !jSONObject2.has("currentBreakTime") || !jSONObject2.has("currentBreakClipTime")) {
            return null;
        }
        try {
            long j = (long) (((double) jSONObject2.getLong("currentBreakTime")) * 1000.0d);
            long j2 = (long) (((double) jSONObject2.getLong("currentBreakClipTime")) * 1000.0d);
            String optString = jSONObject2.optString("breakId", (String) null);
            String optString2 = jSONObject2.optString("breakClipId", (String) null);
            long optLong = jSONObject2.optLong("whenSkippable", -1);
            return new AdBreakStatus(j, j2, optString, optString2, optLong != -1 ? (long) (((double) optLong) * 1000.0d) : optLong);
        } catch (JSONException e) {
            Log.d("AdBreakInfo", String.format(Locale.ROOT, "Error while creating an AdBreakClipInfo from JSON: %s", new Object[]{e.getMessage()}));
            return null;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AdBreakStatus)) {
            return false;
        }
        AdBreakStatus adBreakStatus = (AdBreakStatus) obj;
        return this.zzesx == adBreakStatus.zzesx && this.zzesy == adBreakStatus.zzesy && zzbdw.zza(this.zzesz, adBreakStatus.zzesz) && zzbdw.zza(this.zzeta, adBreakStatus.zzeta) && this.zzesq == adBreakStatus.zzesq;
    }

    public String getBreakClipId() {
        return this.zzeta;
    }

    public String getBreakId() {
        return this.zzesz;
    }

    public long getCurrentBreakClipTimeInMs() {
        return this.zzesy;
    }

    public long getCurrentBreakTimeInMs() {
        return this.zzesx;
    }

    public long getWhenSkippableInMs() {
        return this.zzesq;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.zzesx), Long.valueOf(this.zzesy), this.zzesz, this.zzeta, Long.valueOf(this.zzesq)});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, getCurrentBreakTimeInMs());
        zzbgo.zza(parcel, 3, getCurrentBreakClipTimeInMs());
        zzbgo.zza(parcel, 4, getBreakId(), false);
        zzbgo.zza(parcel, 5, getBreakClipId(), false);
        zzbgo.zza(parcel, 6, getWhenSkippableInMs());
        zzbgo.zzai(parcel, zze);
    }
}
