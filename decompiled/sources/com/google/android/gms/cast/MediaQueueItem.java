package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.internal.zzbdw;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MediaQueueItem extends zzbgl {
    public static final Parcelable.Creator<MediaQueueItem> CREATOR = new zzai();
    public static final double DEFAULT_PLAYBACK_DURATION = Double.POSITIVE_INFINITY;
    public static final int INVALID_ITEM_ID = 0;
    private String zzesn;
    private JSONObject zzess;
    private boolean zzewi;
    private long[] zzewl;
    private MediaInfo zzewv;
    private int zzeww;
    private double zzewx;
    private double zzewy;
    private double zzewz;

    public static class Builder {
        private final MediaQueueItem zzexa;

        public Builder(MediaInfo mediaInfo) throws IllegalArgumentException {
            this.zzexa = new MediaQueueItem(mediaInfo);
        }

        public Builder(MediaQueueItem mediaQueueItem) throws IllegalArgumentException {
            this.zzexa = new MediaQueueItem(mediaQueueItem);
        }

        public Builder(JSONObject jSONObject) throws JSONException {
            this.zzexa = new MediaQueueItem(jSONObject);
        }

        public MediaQueueItem build() {
            this.zzexa.zzadu();
            return this.zzexa;
        }

        public Builder clearItemId() {
            this.zzexa.zzba(0);
            return this;
        }

        public Builder setActiveTrackIds(long[] jArr) {
            this.zzexa.zza(jArr);
            return this;
        }

        public Builder setAutoplay(boolean z) {
            this.zzexa.zzba(z);
            return this;
        }

        public Builder setCustomData(JSONObject jSONObject) {
            this.zzexa.setCustomData(jSONObject);
            return this;
        }

        public Builder setPlaybackDuration(double d) {
            this.zzexa.zzd(d);
            return this;
        }

        public Builder setPreloadTime(double d) throws IllegalArgumentException {
            this.zzexa.zze(d);
            return this;
        }

        public Builder setStartTime(double d) throws IllegalArgumentException {
            this.zzexa.zzc(d);
            return this;
        }
    }

    private MediaQueueItem(MediaInfo mediaInfo) throws IllegalArgumentException {
        this(mediaInfo, 0, true, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, Double.POSITIVE_INFINITY, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, (long[]) null, (String) null);
        if (mediaInfo == null) {
            throw new IllegalArgumentException("media cannot be null.");
        }
    }

    MediaQueueItem(MediaInfo mediaInfo, int i, boolean z, double d, double d2, double d3, long[] jArr, String str) {
        this.zzewv = mediaInfo;
        this.zzeww = i;
        this.zzewi = z;
        this.zzewx = d;
        this.zzewy = d2;
        this.zzewz = d3;
        this.zzewl = jArr;
        this.zzesn = str;
        if (str != null) {
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

    private MediaQueueItem(MediaQueueItem mediaQueueItem) throws IllegalArgumentException {
        this(mediaQueueItem.getMedia(), mediaQueueItem.getItemId(), mediaQueueItem.getAutoplay(), mediaQueueItem.getStartTime(), mediaQueueItem.getPlaybackDuration(), mediaQueueItem.getPreloadTime(), mediaQueueItem.getActiveTrackIds(), (String) null);
        if (this.zzewv != null) {
            this.zzess = mediaQueueItem.getCustomData();
            return;
        }
        throw new IllegalArgumentException("media cannot be null.");
    }

    MediaQueueItem(JSONObject jSONObject) throws JSONException {
        this((MediaInfo) null, 0, true, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, Double.POSITIVE_INFINITY, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, (long[]) null, (String) null);
        zzu(jSONObject);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaQueueItem)) {
            return false;
        }
        MediaQueueItem mediaQueueItem = (MediaQueueItem) obj;
        JSONObject jSONObject = this.zzess;
        boolean z = jSONObject == null;
        JSONObject jSONObject2 = mediaQueueItem.zzess;
        if (z != (jSONObject2 == null)) {
            return false;
        }
        return (jSONObject == null || jSONObject2 == null || zzq.zzc(jSONObject, jSONObject2)) && zzbdw.zza(this.zzewv, mediaQueueItem.zzewv) && this.zzeww == mediaQueueItem.zzeww && this.zzewi == mediaQueueItem.zzewi && this.zzewx == mediaQueueItem.zzewx && this.zzewy == mediaQueueItem.zzewy && this.zzewz == mediaQueueItem.zzewz && Arrays.equals(this.zzewl, mediaQueueItem.zzewl);
    }

    public long[] getActiveTrackIds() {
        return this.zzewl;
    }

    public boolean getAutoplay() {
        return this.zzewi;
    }

    public JSONObject getCustomData() {
        return this.zzess;
    }

    public int getItemId() {
        return this.zzeww;
    }

    public MediaInfo getMedia() {
        return this.zzewv;
    }

    public double getPlaybackDuration() {
        return this.zzewy;
    }

    public double getPreloadTime() {
        return this.zzewz;
    }

    public double getStartTime() {
        return this.zzewx;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzewv, Integer.valueOf(this.zzeww), Boolean.valueOf(this.zzewi), Double.valueOf(this.zzewx), Double.valueOf(this.zzewy), Double.valueOf(this.zzewz), Integer.valueOf(Arrays.hashCode(this.zzewl)), String.valueOf(this.zzess)});
    }

    /* access modifiers changed from: package-private */
    public final void setCustomData(JSONObject jSONObject) {
        this.zzess = jSONObject;
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("media", this.zzewv.toJson());
            int i = this.zzeww;
            if (i != 0) {
                jSONObject.put("itemId", i);
            }
            jSONObject.put("autoplay", this.zzewi);
            jSONObject.put("startTime", this.zzewx);
            double d = this.zzewy;
            if (d != Double.POSITIVE_INFINITY) {
                jSONObject.put("playbackDuration", d);
            }
            jSONObject.put("preloadTime", this.zzewz);
            if (this.zzewl != null) {
                JSONArray jSONArray = new JSONArray();
                for (long put : this.zzewl) {
                    jSONArray.put(put);
                }
                jSONObject.put("activeTrackIds", jSONArray);
            }
            JSONObject jSONObject2 = this.zzess;
            if (jSONObject2 != null) {
                jSONObject.put("customData", jSONObject2);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public void writeToParcel(Parcel parcel, int i) {
        JSONObject jSONObject = this.zzess;
        this.zzesn = jSONObject == null ? null : jSONObject.toString();
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) getMedia(), i, false);
        zzbgo.zzc(parcel, 3, getItemId());
        zzbgo.zza(parcel, 4, getAutoplay());
        zzbgo.zza(parcel, 5, getStartTime());
        zzbgo.zza(parcel, 6, getPlaybackDuration());
        zzbgo.zza(parcel, 7, getPreloadTime());
        zzbgo.zza(parcel, 8, getActiveTrackIds(), false);
        zzbgo.zza(parcel, 9, this.zzesn, false);
        zzbgo.zzai(parcel, zze);
    }

    /* access modifiers changed from: package-private */
    public final void zza(long[] jArr) {
        this.zzewl = jArr;
    }

    /* access modifiers changed from: package-private */
    public final void zzadu() throws IllegalArgumentException {
        if (this.zzewv == null) {
            throw new IllegalArgumentException("media cannot be null.");
        } else if (Double.isNaN(this.zzewx) || this.zzewx < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            throw new IllegalArgumentException("startTime cannot be negative or NaN.");
        } else if (Double.isNaN(this.zzewy)) {
            throw new IllegalArgumentException("playbackDuration cannot be NaN.");
        } else if (Double.isNaN(this.zzewz) || this.zzewz < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            throw new IllegalArgumentException("preloadTime cannot be negative or Nan.");
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzba(int i) {
        this.zzeww = 0;
    }

    /* access modifiers changed from: package-private */
    public final void zzba(boolean z) {
        this.zzewi = z;
    }

    /* access modifiers changed from: package-private */
    public final void zzc(double d) throws IllegalArgumentException {
        if (Double.isNaN(d) || d < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            throw new IllegalArgumentException("startTime cannot be negative or NaN.");
        }
        this.zzewx = d;
    }

    /* access modifiers changed from: package-private */
    public final void zzd(double d) throws IllegalArgumentException {
        if (!Double.isNaN(d)) {
            this.zzewy = d;
            return;
        }
        throw new IllegalArgumentException("playbackDuration cannot be NaN.");
    }

    /* access modifiers changed from: package-private */
    public final void zze(double d) throws IllegalArgumentException {
        if (Double.isNaN(d) || d < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            throw new IllegalArgumentException("preloadTime cannot be negative or NaN.");
        }
        this.zzewz = d;
    }

    public final boolean zzu(JSONObject jSONObject) throws JSONException {
        boolean z;
        boolean z2;
        int i;
        boolean z3 = false;
        if (jSONObject.has("media")) {
            this.zzewv = new MediaInfo(jSONObject.getJSONObject("media"));
            z = true;
        } else {
            z = false;
        }
        if (jSONObject.has("itemId") && this.zzeww != (i = jSONObject.getInt("itemId"))) {
            this.zzeww = i;
            z = true;
        }
        if (jSONObject.has("autoplay") && this.zzewi != (z2 = jSONObject.getBoolean("autoplay"))) {
            this.zzewi = z2;
            z = true;
        }
        if (jSONObject.has("startTime")) {
            double d = jSONObject.getDouble("startTime");
            if (Math.abs(d - this.zzewx) > 1.0E-7d) {
                this.zzewx = d;
                z = true;
            }
        }
        if (jSONObject.has("playbackDuration")) {
            double d2 = jSONObject.getDouble("playbackDuration");
            if (Math.abs(d2 - this.zzewy) > 1.0E-7d) {
                this.zzewy = d2;
                z = true;
            }
        }
        if (jSONObject.has("preloadTime")) {
            double d3 = jSONObject.getDouble("preloadTime");
            if (Math.abs(d3 - this.zzewz) > 1.0E-7d) {
                this.zzewz = d3;
                z = true;
            }
        }
        long[] jArr = null;
        if (jSONObject.has("activeTrackIds")) {
            JSONArray jSONArray = jSONObject.getJSONArray("activeTrackIds");
            int length = jSONArray.length();
            long[] jArr2 = new long[length];
            for (int i2 = 0; i2 < length; i2++) {
                jArr2[i2] = jSONArray.getLong(i2);
            }
            long[] jArr3 = this.zzewl;
            if (jArr3 != null && jArr3.length == length) {
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        jArr = jArr2;
                        break;
                    } else if (this.zzewl[i3] != jArr2[i3]) {
                        break;
                    } else {
                        i3++;
                    }
                }
            }
            jArr = jArr2;
            z3 = true;
        }
        if (z3) {
            this.zzewl = jArr;
            z = true;
        }
        if (!jSONObject.has("customData")) {
            return z;
        }
        this.zzess = jSONObject.getJSONObject("customData");
        return true;
    }
}
