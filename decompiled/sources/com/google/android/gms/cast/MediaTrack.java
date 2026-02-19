package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.internal.zzbdw;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.Arrays;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public final class MediaTrack extends zzbgl implements ReflectedParcelable {
    public static final Parcelable.Creator<MediaTrack> CREATOR = new zzak();
    public static final int SUBTYPE_CAPTIONS = 2;
    public static final int SUBTYPE_CHAPTERS = 4;
    public static final int SUBTYPE_DESCRIPTIONS = 3;
    public static final int SUBTYPE_METADATA = 5;
    public static final int SUBTYPE_NONE = 0;
    public static final int SUBTYPE_SUBTITLES = 1;
    public static final int SUBTYPE_UNKNOWN = -1;
    public static final int TYPE_AUDIO = 2;
    public static final int TYPE_TEXT = 1;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_VIDEO = 3;
    private String mName;
    private String zzdxa;
    private long zzehj;
    private int zzenu;
    private String zzesn;
    private String zzeso;
    private JSONObject zzess;
    private String zzevz;
    private int zzexr;

    public static class Builder {
        private final MediaTrack zzexs;

        public Builder(long j, int i) throws IllegalArgumentException {
            this.zzexs = new MediaTrack(j, i);
        }

        public MediaTrack build() {
            return this.zzexs;
        }

        public Builder setContentId(String str) {
            this.zzexs.setContentId(str);
            return this;
        }

        public Builder setContentType(String str) {
            this.zzexs.setContentType(str);
            return this;
        }

        public Builder setCustomData(JSONObject jSONObject) {
            this.zzexs.setCustomData(jSONObject);
            return this;
        }

        public Builder setLanguage(String str) {
            this.zzexs.setLanguage(str);
            return this;
        }

        public Builder setLanguage(Locale locale) {
            this.zzexs.setLanguage(zzbdw.zzb(locale));
            return this;
        }

        public Builder setName(String str) {
            this.zzexs.setName(str);
            return this;
        }

        public Builder setSubtype(int i) throws IllegalArgumentException {
            this.zzexs.zzbb(i);
            return this;
        }
    }

    MediaTrack(long j, int i) throws IllegalArgumentException {
        this(0, 0, (String) null, (String) null, (String) null, (String) null, -1, (String) null);
        this.zzehj = j;
        if (i <= 0 || i > 3) {
            StringBuilder sb = new StringBuilder(24);
            sb.append("invalid type ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }
        this.zzenu = i;
    }

    MediaTrack(long j, int i, String str, String str2, String str3, String str4, int i2, String str5) {
        this.zzehj = j;
        this.zzenu = i;
        this.zzeso = str;
        this.zzevz = str2;
        this.mName = str3;
        this.zzdxa = str4;
        this.zzexr = i2;
        this.zzesn = str5;
        if (str5 != null) {
            try {
                this.zzess = new JSONObject(this.zzesn);
            } catch (JSONException unused) {
                this.zzess = null;
                this.zzesn = null;
            }
        } else {
            this.zzess = null;
        }
    }

    MediaTrack(JSONObject jSONObject) throws JSONException {
        this(0, 0, (String) null, (String) null, (String) null, (String) null, -1, (String) null);
        int i;
        this.zzehj = jSONObject.getLong("trackId");
        String string = jSONObject.getString(AppMeasurement.Param.TYPE);
        if ("TEXT".equals(string)) {
            this.zzenu = 1;
        } else if ("AUDIO".equals(string)) {
            this.zzenu = 2;
        } else if ("VIDEO".equals(string)) {
            this.zzenu = 3;
        } else {
            String valueOf = String.valueOf(string);
            throw new JSONException(valueOf.length() != 0 ? "invalid type: ".concat(valueOf) : new String("invalid type: "));
        }
        this.zzeso = jSONObject.optString("trackContentId", (String) null);
        this.zzevz = jSONObject.optString("trackContentType", (String) null);
        this.mName = jSONObject.optString("name", (String) null);
        this.zzdxa = jSONObject.optString("language", (String) null);
        if (jSONObject.has("subtype")) {
            String string2 = jSONObject.getString("subtype");
            if ("SUBTITLES".equals(string2)) {
                this.zzexr = 1;
            } else if ("CAPTIONS".equals(string2)) {
                this.zzexr = 2;
            } else if ("DESCRIPTIONS".equals(string2)) {
                this.zzexr = 3;
            } else if ("CHAPTERS".equals(string2)) {
                i = 4;
            } else if ("METADATA".equals(string2)) {
                i = 5;
            } else {
                String valueOf2 = String.valueOf(string2);
                throw new JSONException(valueOf2.length() != 0 ? "invalid subtype: ".concat(valueOf2) : new String("invalid subtype: "));
            }
            this.zzess = jSONObject.optJSONObject("customData");
        }
        i = 0;
        this.zzexr = i;
        this.zzess = jSONObject.optJSONObject("customData");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaTrack)) {
            return false;
        }
        MediaTrack mediaTrack = (MediaTrack) obj;
        JSONObject jSONObject = this.zzess;
        boolean z = jSONObject == null;
        JSONObject jSONObject2 = mediaTrack.zzess;
        if (z != (jSONObject2 == null)) {
            return false;
        }
        return (jSONObject == null || jSONObject2 == null || zzq.zzc(jSONObject, jSONObject2)) && this.zzehj == mediaTrack.zzehj && this.zzenu == mediaTrack.zzenu && zzbdw.zza(this.zzeso, mediaTrack.zzeso) && zzbdw.zza(this.zzevz, mediaTrack.zzevz) && zzbdw.zza(this.mName, mediaTrack.mName) && zzbdw.zza(this.zzdxa, mediaTrack.zzdxa) && this.zzexr == mediaTrack.zzexr;
    }

    public final String getContentId() {
        return this.zzeso;
    }

    public final String getContentType() {
        return this.zzevz;
    }

    public final JSONObject getCustomData() {
        return this.zzess;
    }

    public final long getId() {
        return this.zzehj;
    }

    public final String getLanguage() {
        return this.zzdxa;
    }

    public final String getName() {
        return this.mName;
    }

    public final int getSubtype() {
        return this.zzexr;
    }

    public final int getType() {
        return this.zzenu;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.zzehj), Integer.valueOf(this.zzenu), this.zzeso, this.zzevz, this.mName, this.zzdxa, Integer.valueOf(this.zzexr), String.valueOf(this.zzess)});
    }

    public final void setContentId(String str) {
        this.zzeso = str;
    }

    public final void setContentType(String str) {
        this.zzevz = str;
    }

    /* access modifiers changed from: package-private */
    public final void setCustomData(JSONObject jSONObject) {
        this.zzess = jSONObject;
    }

    /* access modifiers changed from: package-private */
    public final void setLanguage(String str) {
        this.zzdxa = str;
    }

    /* access modifiers changed from: package-private */
    public final void setName(String str) {
        this.mName = str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x002a A[Catch:{ JSONException -> 0x007c }] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0033 A[Catch:{ JSONException -> 0x007c }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003c A[Catch:{ JSONException -> 0x007c }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0049 A[Catch:{ JSONException -> 0x007c }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0070 A[Catch:{ JSONException -> 0x007c }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0077 A[Catch:{ JSONException -> 0x007c }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final org.json.JSONObject toJson() {
        /*
            r6 = this;
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r1 = "trackId"
            long r2 = r6.zzehj     // Catch:{ JSONException -> 0x007c }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x007c }
            int r1 = r6.zzenu     // Catch:{ JSONException -> 0x007c }
            r2 = 3
            r3 = 2
            r4 = 1
            java.lang.String r5 = "type"
            if (r1 == r4) goto L_0x0023
            if (r1 == r3) goto L_0x0020
            if (r1 == r2) goto L_0x001a
            goto L_0x0026
        L_0x001a:
            java.lang.String r1 = "VIDEO"
        L_0x001c:
            r0.put(r5, r1)     // Catch:{ JSONException -> 0x007c }
            goto L_0x0026
        L_0x0020:
            java.lang.String r1 = "AUDIO"
            goto L_0x001c
        L_0x0023:
            java.lang.String r1 = "TEXT"
            goto L_0x001c
        L_0x0026:
            java.lang.String r1 = r6.zzeso     // Catch:{ JSONException -> 0x007c }
            if (r1 == 0) goto L_0x002f
            java.lang.String r5 = "trackContentId"
            r0.put(r5, r1)     // Catch:{ JSONException -> 0x007c }
        L_0x002f:
            java.lang.String r1 = r6.zzevz     // Catch:{ JSONException -> 0x007c }
            if (r1 == 0) goto L_0x0038
            java.lang.String r5 = "trackContentType"
            r0.put(r5, r1)     // Catch:{ JSONException -> 0x007c }
        L_0x0038:
            java.lang.String r1 = r6.mName     // Catch:{ JSONException -> 0x007c }
            if (r1 == 0) goto L_0x0041
            java.lang.String r5 = "name"
            r0.put(r5, r1)     // Catch:{ JSONException -> 0x007c }
        L_0x0041:
            java.lang.String r1 = r6.zzdxa     // Catch:{ JSONException -> 0x007c }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ JSONException -> 0x007c }
            if (r1 != 0) goto L_0x0050
            java.lang.String r1 = "language"
            java.lang.String r5 = r6.zzdxa     // Catch:{ JSONException -> 0x007c }
            r0.put(r1, r5)     // Catch:{ JSONException -> 0x007c }
        L_0x0050:
            int r1 = r6.zzexr     // Catch:{ JSONException -> 0x007c }
            java.lang.String r5 = "subtype"
            if (r1 == r4) goto L_0x0070
            if (r1 == r3) goto L_0x006d
            if (r1 == r2) goto L_0x006a
            r2 = 4
            if (r1 == r2) goto L_0x0067
            r2 = 5
            if (r1 == r2) goto L_0x0061
            goto L_0x0073
        L_0x0061:
            java.lang.String r1 = "METADATA"
        L_0x0063:
            r0.put(r5, r1)     // Catch:{ JSONException -> 0x007c }
            goto L_0x0073
        L_0x0067:
            java.lang.String r1 = "CHAPTERS"
            goto L_0x0063
        L_0x006a:
            java.lang.String r1 = "DESCRIPTIONS"
            goto L_0x0063
        L_0x006d:
            java.lang.String r1 = "CAPTIONS"
            goto L_0x0063
        L_0x0070:
            java.lang.String r1 = "SUBTITLES"
            goto L_0x0063
        L_0x0073:
            org.json.JSONObject r1 = r6.zzess     // Catch:{ JSONException -> 0x007c }
            if (r1 == 0) goto L_0x007c
            java.lang.String r2 = "customData"
            r0.put(r2, r1)     // Catch:{ JSONException -> 0x007c }
        L_0x007c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.MediaTrack.toJson():org.json.JSONObject");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        JSONObject jSONObject = this.zzess;
        this.zzesn = jSONObject == null ? null : jSONObject.toString();
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, getId());
        zzbgo.zzc(parcel, 3, getType());
        zzbgo.zza(parcel, 4, getContentId(), false);
        zzbgo.zza(parcel, 5, getContentType(), false);
        zzbgo.zza(parcel, 6, getName(), false);
        zzbgo.zza(parcel, 7, getLanguage(), false);
        zzbgo.zzc(parcel, 8, getSubtype());
        zzbgo.zza(parcel, 9, this.zzesn, false);
        zzbgo.zzai(parcel, zze);
    }

    /* access modifiers changed from: package-private */
    public final void zzbb(int i) throws IllegalArgumentException {
        if (i < 0 || i > 5) {
            StringBuilder sb = new StringBuilder(27);
            sb.append("invalid subtype ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        } else if (i == 0 || this.zzenu == 1) {
            this.zzexr = i;
        } else {
            throw new IllegalArgumentException("subtypes are only valid for text tracks");
        }
    }
}
