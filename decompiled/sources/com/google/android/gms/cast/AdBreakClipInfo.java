package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.internal.zzbdw;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.plus.PlusShare;
import java.util.Arrays;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class AdBreakClipInfo extends zzbgl {
    public static final long AD_BREAK_CLIP_NOT_SKIPPABLE = -1;
    public static final Parcelable.Creator<AdBreakClipInfo> CREATOR = new zza();
    private final String zzbhs;
    private final String zzbzd;
    private final String zzesj;
    private final long zzesk;
    private final String zzesl;
    private final String zzesm;
    private String zzesn;
    private String zzeso;
    private String zzesp;
    private final long zzesq;
    private final String zzesr;
    private JSONObject zzess;

    public static class Builder {
        private String zzbhs = null;
        private String zzbzd = null;
        private String zzesj = null;
        private long zzesk = 0;
        private String zzesl = null;
        private String zzesm = null;
        private String zzesn = null;
        private String zzeso = null;
        private String zzesp = null;
        private long zzesq = -1;
        private String zzesr;

        public Builder(String str) {
            this.zzbzd = str;
        }

        public AdBreakClipInfo build() {
            return new AdBreakClipInfo(this.zzbzd, this.zzesj, this.zzesk, this.zzbhs, this.zzesl, this.zzesm, this.zzesn, this.zzeso, this.zzesp, this.zzesq, this.zzesr);
        }

        public Builder setClickThroughUrl(String str) {
            this.zzesm = str;
            return this;
        }

        public Builder setContentId(String str) {
            this.zzeso = str;
            return this;
        }

        public Builder setContentUrl(String str) {
            this.zzbhs = str;
            return this;
        }

        public Builder setCustomDataJsonString(String str) {
            this.zzesn = str;
            return this;
        }

        public Builder setDurationInMs(long j) {
            this.zzesk = j;
            return this;
        }

        public Builder setHlsSegmentFormat(String str) {
            this.zzesr = str;
            return this;
        }

        public Builder setImageUrl(String str) {
            this.zzesp = str;
            return this;
        }

        public Builder setMimeType(String str) {
            this.zzesl = str;
            return this;
        }

        public Builder setTitle(String str) {
            this.zzesj = str;
            return this;
        }

        public Builder setWhenSkippableInMs(long j) {
            this.zzesq = j;
            return this;
        }
    }

    AdBreakClipInfo(String str, String str2, long j, String str3, String str4, String str5, String str6, String str7, String str8, long j2, String str9) {
        JSONObject jSONObject;
        this.zzbzd = str;
        this.zzesj = str2;
        this.zzesk = j;
        this.zzbhs = str3;
        this.zzesl = str4;
        this.zzesm = str5;
        this.zzesn = str6;
        this.zzeso = str7;
        this.zzesp = str8;
        this.zzesq = j2;
        this.zzesr = str9;
        if (!TextUtils.isEmpty(str6)) {
            try {
                this.zzess = new JSONObject(str6);
            } catch (JSONException e) {
                Log.w("AdBreakClipInfo", String.format(Locale.ROOT, "Error creating AdBreakClipInfo: %s", new Object[]{e.getMessage()}));
                this.zzesn = null;
                jSONObject = new JSONObject();
            }
        } else {
            jSONObject = new JSONObject();
            this.zzess = jSONObject;
        }
    }

    static AdBreakClipInfo zzp(JSONObject jSONObject) {
        long j;
        String str;
        JSONObject jSONObject2 = jSONObject;
        if (jSONObject2 == null || !jSONObject2.has("id")) {
            return null;
        }
        try {
            String string = jSONObject2.getString("id");
            long optLong = (long) (((double) jSONObject2.optLong("duration")) * 1000.0d);
            String optString = jSONObject2.optString("clickThroughUrl", (String) null);
            String optString2 = jSONObject2.optString("contentUrl", (String) null);
            String optString3 = jSONObject2.optString("mimeType", (String) null);
            if (optString3 == null) {
                optString3 = jSONObject2.optString("contentType", (String) null);
            }
            String str2 = optString3;
            String optString4 = jSONObject2.optString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, (String) null);
            JSONObject optJSONObject = jSONObject2.optJSONObject("customData");
            String optString5 = jSONObject2.optString("contentId", (String) null);
            String optString6 = jSONObject2.optString("imageUrl", (String) null);
            long j2 = -1;
            if (jSONObject2.has("whenSkippable")) {
                j = optLong;
                j2 = (long) (((double) ((Integer) jSONObject2.get("whenSkippable")).intValue()) * 1000.0d);
            } else {
                j = optLong;
            }
            String optString7 = jSONObject2.optString("hlsSegmentFormat", (String) null);
            if (optJSONObject != null) {
                if (optJSONObject.length() != 0) {
                    str = optJSONObject.toString();
                    return new AdBreakClipInfo(string, optString4, j, optString2, str2, optString, str, optString5, optString6, j2, optString7);
                }
            }
            str = null;
            return new AdBreakClipInfo(string, optString4, j, optString2, str2, optString, str, optString5, optString6, j2, optString7);
        } catch (JSONException e) {
            Log.d("AdBreakClipInfo", String.format(Locale.ROOT, "Error while creating an AdBreakClipInfo from JSON: %s", new Object[]{e.getMessage()}));
            return null;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AdBreakClipInfo)) {
            return false;
        }
        AdBreakClipInfo adBreakClipInfo = (AdBreakClipInfo) obj;
        return zzbdw.zza(this.zzbzd, adBreakClipInfo.zzbzd) && zzbdw.zza(this.zzesj, adBreakClipInfo.zzesj) && this.zzesk == adBreakClipInfo.zzesk && zzbdw.zza(this.zzbhs, adBreakClipInfo.zzbhs) && zzbdw.zza(this.zzesl, adBreakClipInfo.zzesl) && zzbdw.zza(this.zzesm, adBreakClipInfo.zzesm) && zzbdw.zza(this.zzesn, adBreakClipInfo.zzesn) && zzbdw.zza(this.zzeso, adBreakClipInfo.zzeso) && zzbdw.zza(this.zzesp, adBreakClipInfo.zzesp) && this.zzesq == adBreakClipInfo.zzesq && zzbdw.zza(this.zzesr, adBreakClipInfo.zzesr);
    }

    public String getClickThroughUrl() {
        return this.zzesm;
    }

    public String getContentId() {
        return this.zzeso;
    }

    public String getContentUrl() {
        return this.zzbhs;
    }

    public JSONObject getCustomData() {
        return this.zzess;
    }

    public long getDurationInMs() {
        return this.zzesk;
    }

    public String getHlsSegmentFormat() {
        return this.zzesr;
    }

    public String getId() {
        return this.zzbzd;
    }

    public String getImageUrl() {
        return this.zzesp;
    }

    public String getMimeType() {
        return this.zzesl;
    }

    public String getTitle() {
        return this.zzesj;
    }

    public long getWhenSkippableInMs() {
        return this.zzesq;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzbzd, this.zzesj, Long.valueOf(this.zzesk), this.zzbhs, this.zzesl, this.zzesm, this.zzesn, this.zzeso, this.zzesp, Long.valueOf(this.zzesq), this.zzesr});
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.zzbzd);
            jSONObject.put("duration", ((double) this.zzesk) / 1000.0d);
            long j = this.zzesq;
            if (j != -1) {
                jSONObject.put("whenSkippable", ((double) j) / 1000.0d);
            }
            String str = this.zzeso;
            if (str != null) {
                jSONObject.put("contentId", str);
            }
            String str2 = this.zzesl;
            if (str2 != null) {
                jSONObject.put("contentType", str2);
            }
            String str3 = this.zzesj;
            if (str3 != null) {
                jSONObject.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, str3);
            }
            String str4 = this.zzbhs;
            if (str4 != null) {
                jSONObject.put("contentUrl", str4);
            }
            String str5 = this.zzesm;
            if (str5 != null) {
                jSONObject.put("clickThroughUrl", str5);
            }
            JSONObject jSONObject2 = this.zzess;
            if (jSONObject2 != null) {
                jSONObject.put("customData", jSONObject2);
            }
            String str6 = this.zzesp;
            if (str6 != null) {
                jSONObject.put("imageUrl", str6);
            }
            String str7 = this.zzesr;
            if (str7 != null) {
                jSONObject.put("hlsSegmentFormat", str7);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, getId(), false);
        zzbgo.zza(parcel, 3, getTitle(), false);
        zzbgo.zza(parcel, 4, getDurationInMs());
        zzbgo.zza(parcel, 5, getContentUrl(), false);
        zzbgo.zza(parcel, 6, getMimeType(), false);
        zzbgo.zza(parcel, 7, getClickThroughUrl(), false);
        zzbgo.zza(parcel, 8, this.zzesn, false);
        zzbgo.zza(parcel, 9, getContentId(), false);
        zzbgo.zza(parcel, 10, getImageUrl(), false);
        zzbgo.zza(parcel, 11, getWhenSkippableInMs());
        zzbgo.zza(parcel, 12, getHlsSegmentFormat(), false);
        zzbgo.zzai(parcel, zze);
    }
}
