package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.internal.zzbdw;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.jivesoftware.smackx.workgroup.MetaData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MediaInfo extends zzbgl implements ReflectedParcelable {
    public static final Parcelable.Creator<MediaInfo> CREATOR = new zzae();
    public static final int STREAM_TYPE_BUFFERED = 1;
    public static final int STREAM_TYPE_INVALID = -1;
    public static final int STREAM_TYPE_LIVE = 2;
    public static final int STREAM_TYPE_NONE = 0;
    public static final long UNKNOWN_DURATION = -1;
    private String zzesn;
    private final String zzeso;
    private JSONObject zzess;
    private int zzevy;
    private String zzevz;
    private MediaMetadata zzewa;
    private long zzewb;
    private List<MediaTrack> zzewc;
    private TextTrackStyle zzewd;
    private List<AdBreakInfo> zzewe;
    private List<AdBreakClipInfo> zzewf;
    private String zzewg;

    public static class Builder {
        private final MediaInfo zzewh;

        public Builder(String str) throws IllegalArgumentException {
            this.zzewh = new MediaInfo(str);
        }

        public Builder(String str, String str2) throws IllegalArgumentException {
            this.zzewh = new MediaInfo(str, str2);
        }

        public MediaInfo build() {
            return this.zzewh;
        }

        public Builder setAdBreakClips(List<AdBreakClipInfo> list) {
            this.zzewh.zzab(list);
            return this;
        }

        public Builder setAdBreaks(List<AdBreakInfo> list) {
            this.zzewh.zzaa(list);
            return this;
        }

        public Builder setContentType(String str) {
            this.zzewh.setContentType(str);
            return this;
        }

        public Builder setCustomData(JSONObject jSONObject) {
            this.zzewh.setCustomData(jSONObject);
            return this;
        }

        public Builder setEntity(String str) {
            this.zzewh.zzfk(str);
            return this;
        }

        public Builder setMediaTracks(List<MediaTrack> list) {
            this.zzewh.zzz(list);
            return this;
        }

        public Builder setMetadata(MediaMetadata mediaMetadata) {
            this.zzewh.zza(mediaMetadata);
            return this;
        }

        public Builder setStreamDuration(long j) throws IllegalArgumentException {
            this.zzewh.zzw(j);
            return this;
        }

        public Builder setStreamType(int i) throws IllegalArgumentException {
            this.zzewh.setStreamType(i);
            return this;
        }

        public Builder setTextTrackStyle(TextTrackStyle textTrackStyle) {
            this.zzewh.setTextTrackStyle(textTrackStyle);
            return this;
        }
    }

    MediaInfo(String str) throws IllegalArgumentException {
        this(str, -1, (String) null, (MediaMetadata) null, -1, (List<MediaTrack>) null, (TextTrackStyle) null, (String) null, (List<AdBreakInfo>) null, (List<AdBreakClipInfo>) null, (String) null);
        if (str == null) {
            throw new IllegalArgumentException("contentID cannot be null");
        }
    }

    MediaInfo(String str, int i, String str2, MediaMetadata mediaMetadata, long j, List<MediaTrack> list, TextTrackStyle textTrackStyle, String str3, List<AdBreakInfo> list2, List<AdBreakClipInfo> list3, String str4) {
        this.zzeso = str;
        this.zzevy = i;
        this.zzevz = str2;
        this.zzewa = mediaMetadata;
        this.zzewb = j;
        this.zzewc = list;
        this.zzewd = textTrackStyle;
        this.zzesn = str3;
        if (str3 != null) {
            try {
                this.zzess = new JSONObject(this.zzesn);
            } catch (JSONException unused) {
                this.zzess = null;
                this.zzesn = null;
            }
        } else {
            this.zzess = null;
        }
        this.zzewe = list2;
        this.zzewf = list3;
        this.zzewg = str4;
    }

    MediaInfo(String str, String str2) throws IllegalArgumentException {
        this(str, -1, (String) null, (MediaMetadata) null, -1, (List<MediaTrack>) null, (TextTrackStyle) null, (String) null, (List<AdBreakInfo>) null, (List<AdBreakClipInfo>) null, str2);
        if (str == null) {
            throw new IllegalArgumentException("contentID cannot be null");
        }
    }

    MediaInfo(JSONObject jSONObject) throws JSONException {
        this(jSONObject.getString("contentId"), -1, (String) null, (MediaMetadata) null, -1, (List<MediaTrack>) null, (TextTrackStyle) null, (String) null, (List<AdBreakInfo>) null, (List<AdBreakClipInfo>) null, (String) null);
        String string = jSONObject.getString("streamType");
        if ("NONE".equals(string)) {
            this.zzevy = 0;
        } else {
            this.zzevy = "BUFFERED".equals(string) ? 1 : "LIVE".equals(string) ? 2 : -1;
        }
        this.zzevz = jSONObject.getString("contentType");
        if (jSONObject.has(MetaData.ELEMENT_NAME)) {
            JSONObject jSONObject2 = jSONObject.getJSONObject(MetaData.ELEMENT_NAME);
            MediaMetadata mediaMetadata = new MediaMetadata(jSONObject2.getInt("metadataType"));
            this.zzewa = mediaMetadata;
            mediaMetadata.zzt(jSONObject2);
        }
        this.zzewb = -1;
        if (jSONObject.has("duration") && !jSONObject.isNull("duration")) {
            double optDouble = jSONObject.optDouble("duration", FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
            if (!Double.isNaN(optDouble) && !Double.isInfinite(optDouble)) {
                this.zzewb = (long) (optDouble * 1000.0d);
            }
        }
        if (jSONObject.has("tracks")) {
            this.zzewc = new ArrayList();
            JSONArray jSONArray = jSONObject.getJSONArray("tracks");
            for (int i = 0; i < jSONArray.length(); i++) {
                this.zzewc.add(new MediaTrack(jSONArray.getJSONObject(i)));
            }
        } else {
            this.zzewc = null;
        }
        if (jSONObject.has("textTrackStyle")) {
            JSONObject jSONObject3 = jSONObject.getJSONObject("textTrackStyle");
            TextTrackStyle textTrackStyle = new TextTrackStyle();
            textTrackStyle.zzt(jSONObject3);
            this.zzewd = textTrackStyle;
        } else {
            this.zzewd = null;
        }
        zzs(jSONObject);
        this.zzess = jSONObject.optJSONObject("customData");
        if (jSONObject.has("entity")) {
            this.zzewg = jSONObject.getString("entity");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaInfo)) {
            return false;
        }
        MediaInfo mediaInfo = (MediaInfo) obj;
        JSONObject jSONObject = this.zzess;
        boolean z = jSONObject == null;
        JSONObject jSONObject2 = mediaInfo.zzess;
        if (z != (jSONObject2 == null)) {
            return false;
        }
        return (jSONObject == null || jSONObject2 == null || zzq.zzc(jSONObject, jSONObject2)) && zzbdw.zza(this.zzeso, mediaInfo.zzeso) && this.zzevy == mediaInfo.zzevy && zzbdw.zza(this.zzevz, mediaInfo.zzevz) && zzbdw.zza(this.zzewa, mediaInfo.zzewa) && this.zzewb == mediaInfo.zzewb && zzbdw.zza(this.zzewc, mediaInfo.zzewc) && zzbdw.zza(this.zzewd, mediaInfo.zzewd) && zzbdw.zza(this.zzewe, mediaInfo.zzewe) && zzbdw.zza(this.zzewf, mediaInfo.zzewf) && zzbdw.zza(this.zzewg, mediaInfo.zzewg);
    }

    public List<AdBreakClipInfo> getAdBreakClips() {
        List<AdBreakClipInfo> list = this.zzewf;
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(list);
    }

    public List<AdBreakInfo> getAdBreaks() {
        List<AdBreakInfo> list = this.zzewe;
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(list);
    }

    public String getContentId() {
        return this.zzeso;
    }

    public String getContentType() {
        return this.zzevz;
    }

    public JSONObject getCustomData() {
        return this.zzess;
    }

    public String getEntity() {
        return this.zzewg;
    }

    public List<MediaTrack> getMediaTracks() {
        return this.zzewc;
    }

    public MediaMetadata getMetadata() {
        return this.zzewa;
    }

    public long getStreamDuration() {
        return this.zzewb;
    }

    public int getStreamType() {
        return this.zzevy;
    }

    public TextTrackStyle getTextTrackStyle() {
        return this.zzewd;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzeso, Integer.valueOf(this.zzevy), this.zzevz, this.zzewa, Long.valueOf(this.zzewb), String.valueOf(this.zzess), this.zzewc, this.zzewd, this.zzewe, this.zzewf, this.zzewg});
    }

    /* access modifiers changed from: package-private */
    public final void setContentType(String str) {
        this.zzevz = str;
    }

    /* access modifiers changed from: package-private */
    public final void setCustomData(JSONObject jSONObject) {
        this.zzess = jSONObject;
    }

    /* access modifiers changed from: package-private */
    public final void setStreamType(int i) throws IllegalArgumentException {
        if (i < -1 || i > 2) {
            throw new IllegalArgumentException("invalid stream type");
        }
        this.zzevy = i;
    }

    public void setTextTrackStyle(TextTrackStyle textTrackStyle) {
        this.zzewd = textTrackStyle;
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("contentId", this.zzeso);
            int i = this.zzevy;
            jSONObject.put("streamType", i != 1 ? i != 2 ? "NONE" : "LIVE" : "BUFFERED");
            String str = this.zzevz;
            if (str != null) {
                jSONObject.put("contentType", str);
            }
            MediaMetadata mediaMetadata = this.zzewa;
            if (mediaMetadata != null) {
                jSONObject.put(MetaData.ELEMENT_NAME, mediaMetadata.toJson());
            }
            long j = this.zzewb;
            if (j <= -1) {
                jSONObject.put("duration", JSONObject.NULL);
            } else {
                jSONObject.put("duration", ((double) j) / 1000.0d);
            }
            if (this.zzewc != null) {
                JSONArray jSONArray = new JSONArray();
                for (MediaTrack json : this.zzewc) {
                    jSONArray.put(json.toJson());
                }
                jSONObject.put("tracks", jSONArray);
            }
            TextTrackStyle textTrackStyle = this.zzewd;
            if (textTrackStyle != null) {
                jSONObject.put("textTrackStyle", textTrackStyle.toJson());
            }
            JSONObject jSONObject2 = this.zzess;
            if (jSONObject2 != null) {
                jSONObject.put("customData", jSONObject2);
            }
            String str2 = this.zzewg;
            if (str2 != null) {
                jSONObject.put("entity", str2);
            }
            if (this.zzewe != null) {
                JSONArray jSONArray2 = new JSONArray();
                for (AdBreakInfo json2 : this.zzewe) {
                    jSONArray2.put(json2.toJson());
                }
                jSONObject.put("breaks", jSONArray2);
            }
            if (this.zzewf != null) {
                JSONArray jSONArray3 = new JSONArray();
                for (AdBreakClipInfo json3 : this.zzewf) {
                    jSONArray3.put(json3.toJson());
                }
                jSONObject.put("breakClips", jSONArray3);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public void writeToParcel(Parcel parcel, int i) {
        JSONObject jSONObject = this.zzess;
        this.zzesn = jSONObject == null ? null : jSONObject.toString();
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, getContentId(), false);
        zzbgo.zzc(parcel, 3, getStreamType());
        zzbgo.zza(parcel, 4, getContentType(), false);
        zzbgo.zza(parcel, 5, (Parcelable) getMetadata(), i, false);
        zzbgo.zza(parcel, 6, getStreamDuration());
        zzbgo.zzc(parcel, 7, getMediaTracks(), false);
        zzbgo.zza(parcel, 8, (Parcelable) getTextTrackStyle(), i, false);
        zzbgo.zza(parcel, 9, this.zzesn, false);
        zzbgo.zzc(parcel, 10, getAdBreaks(), false);
        zzbgo.zzc(parcel, 11, getAdBreakClips(), false);
        zzbgo.zza(parcel, 12, getEntity(), false);
        zzbgo.zzai(parcel, zze);
    }

    /* access modifiers changed from: package-private */
    public final void zza(MediaMetadata mediaMetadata) {
        this.zzewa = mediaMetadata;
    }

    public final void zzaa(List<AdBreakInfo> list) {
        this.zzewe = list;
    }

    /* access modifiers changed from: package-private */
    public final void zzab(List<AdBreakClipInfo> list) {
        this.zzewf = list;
    }

    public final void zzfk(String str) {
        this.zzewg = str;
    }

    /* access modifiers changed from: package-private */
    public final void zzs(JSONObject jSONObject) throws JSONException {
        int i = 0;
        if (jSONObject.has("breaks")) {
            JSONArray jSONArray = jSONObject.getJSONArray("breaks");
            this.zzewe = new ArrayList(jSONArray.length());
            int i2 = 0;
            while (true) {
                if (i2 < jSONArray.length()) {
                    AdBreakInfo zzq = AdBreakInfo.zzq(jSONArray.getJSONObject(i2));
                    if (zzq == null) {
                        this.zzewe.clear();
                        break;
                    } else {
                        this.zzewe.add(zzq);
                        i2++;
                    }
                } else {
                    break;
                }
            }
        }
        if (jSONObject.has("breakClips")) {
            JSONArray jSONArray2 = jSONObject.getJSONArray("breakClips");
            this.zzewf = new ArrayList(jSONArray2.length());
            while (i < jSONArray2.length()) {
                AdBreakClipInfo zzp = AdBreakClipInfo.zzp(jSONArray2.getJSONObject(i));
                if (zzp != null) {
                    this.zzewf.add(zzp);
                    i++;
                } else {
                    this.zzewf.clear();
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzw(long j) throws IllegalArgumentException {
        if (j >= 0 || j == -1) {
            this.zzewb = j;
            return;
        }
        throw new IllegalArgumentException("Invalid stream duration");
    }

    /* access modifiers changed from: package-private */
    public final void zzz(List<MediaTrack> list) {
        this.zzewc = list;
    }
}
