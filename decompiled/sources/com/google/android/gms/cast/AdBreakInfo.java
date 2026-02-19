package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.internal.zzbdw;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdBreakInfo extends zzbgl {
    public static final Parcelable.Creator<AdBreakInfo> CREATOR = new zzb();
    private final String zzbzd;
    private final long zzesk;
    private final long zzest;
    private final boolean zzesu;
    private String[] zzesv;
    private final boolean zzesw;

    public static class Builder {
        private String zzbzd = null;
        private long zzesk = 0;
        private long zzest = 0;
        private boolean zzesu = false;
        private String[] zzesv = null;
        private boolean zzesw = false;

        public Builder(long j) {
            this.zzest = j;
        }

        public AdBreakInfo build() {
            return new AdBreakInfo(this.zzest, this.zzbzd, this.zzesk, this.zzesu, this.zzesv, this.zzesw);
        }

        public Builder setBreakClipIds(String[] strArr) {
            this.zzesv = strArr;
            return this;
        }

        public Builder setDurationInMs(long j) {
            this.zzesk = j;
            return this;
        }

        public Builder setId(String str) {
            this.zzbzd = str;
            return this;
        }

        public Builder setIsEmbedded(boolean z) {
            this.zzesw = z;
            return this;
        }

        public Builder setIsWatched(boolean z) {
            this.zzesu = z;
            return this;
        }
    }

    public AdBreakInfo(long j, String str, long j2, boolean z, String[] strArr, boolean z2) {
        this.zzest = j;
        this.zzbzd = str;
        this.zzesk = j2;
        this.zzesu = z;
        this.zzesv = strArr;
        this.zzesw = z2;
    }

    static AdBreakInfo zzq(JSONObject jSONObject) {
        String[] strArr;
        if (jSONObject != null && jSONObject.has("id") && jSONObject.has("position")) {
            try {
                String string = jSONObject.getString("id");
                long j = (long) (((double) jSONObject.getLong("position")) * 1000.0d);
                boolean optBoolean = jSONObject.optBoolean("isWatched");
                long optLong = (long) (((double) jSONObject.optLong("duration")) * 1000.0d);
                JSONArray optJSONArray = jSONObject.optJSONArray("breakClipIds");
                if (optJSONArray != null) {
                    String[] strArr2 = new String[optJSONArray.length()];
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        strArr2[i] = optJSONArray.getString(i);
                    }
                    strArr = strArr2;
                } else {
                    strArr = null;
                }
                return new AdBreakInfo(j, string, optLong, optBoolean, strArr, jSONObject.optBoolean("isEmbedded"));
            } catch (JSONException e) {
                Log.d("AdBreakInfo", String.format(Locale.ROOT, "Error while creating an AdBreakInfo from JSON: %s", new Object[]{e.getMessage()}));
            }
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AdBreakInfo)) {
            return false;
        }
        AdBreakInfo adBreakInfo = (AdBreakInfo) obj;
        return zzbdw.zza(this.zzbzd, adBreakInfo.zzbzd) && this.zzest == adBreakInfo.zzest && this.zzesk == adBreakInfo.zzesk && this.zzesu == adBreakInfo.zzesu && Arrays.equals(this.zzesv, adBreakInfo.zzesv) && this.zzesw == adBreakInfo.zzesw;
    }

    public String[] getBreakClipIds() {
        return this.zzesv;
    }

    public long getDurationInMs() {
        return this.zzesk;
    }

    public String getId() {
        return this.zzbzd;
    }

    public long getPlaybackPositionInMs() {
        return this.zzest;
    }

    public int hashCode() {
        return this.zzbzd.hashCode();
    }

    public boolean isEmbedded() {
        return this.zzesw;
    }

    public boolean isWatched() {
        return this.zzesu;
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.zzbzd);
            jSONObject.put("position", ((double) this.zzest) / 1000.0d);
            jSONObject.put("isWatched", this.zzesu);
            jSONObject.put("isEmbedded", this.zzesw);
            jSONObject.put("duration", ((double) this.zzesk) / 1000.0d);
            if (this.zzesv != null) {
                JSONArray jSONArray = new JSONArray();
                for (String put : this.zzesv) {
                    jSONArray.put(put);
                }
                jSONObject.put("breakClipIds", jSONArray);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, getPlaybackPositionInMs());
        zzbgo.zza(parcel, 3, getId(), false);
        zzbgo.zza(parcel, 4, getDurationInMs());
        zzbgo.zza(parcel, 5, isWatched());
        zzbgo.zza(parcel, 6, getBreakClipIds(), false);
        zzbgo.zza(parcel, 7, isEmbedded());
        zzbgo.zzai(parcel, zze);
    }
}
