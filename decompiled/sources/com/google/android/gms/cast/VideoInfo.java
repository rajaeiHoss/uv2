package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public final class VideoInfo extends zzbgl {
    public static final Parcelable.Creator<VideoInfo> CREATOR = new zzbo();
    public static final int HDR_TYPE_DV = 3;
    public static final int HDR_TYPE_HDR = 4;
    public static final int HDR_TYPE_HDR10 = 2;
    public static final int HDR_TYPE_SDR = 1;
    public static final int HDR_TYPE_UNKNOWN = 0;
    private int zzalt;
    private int zzalu;
    private int zzezp;

    VideoInfo(int i, int i2, int i3) {
        this.zzalt = i;
        this.zzalu = i2;
        this.zzezp = i3;
    }

    static VideoInfo zzv(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            String string = jSONObject.getString("hdrType");
            char c = 65535;
            int hashCode = string.hashCode();
            int i = 3;
            if (hashCode != 3218) {
                if (hashCode != 103158) {
                    if (hashCode != 113729) {
                        if (hashCode == 99136405) {
                            if (string.equals("hdr10")) {
                                c = 1;
                            }
                        }
                    } else if (string.equals("sdr")) {
                        c = 3;
                    }
                } else if (string.equals("hdr")) {
                    c = 2;
                }
            } else if (string.equals("dv")) {
                c = 0;
            }
            if (c != 0) {
                if (c == 1) {
                    i = 2;
                } else if (c == 2) {
                    i = 4;
                } else if (c != 3) {
                    Log.d("VideoInfo", String.format(Locale.ROOT, "Unknown HDR type: %s", new Object[]{string}));
                    i = 0;
                } else {
                    i = 1;
                }
            }
            return new VideoInfo(jSONObject.getInt("width"), jSONObject.getInt("height"), i);
        } catch (JSONException e) {
            Log.d("VideoInfo", String.format(Locale.ROOT, "Error while creating a VideoInfo instance from JSON: %s", new Object[]{e.getMessage()}));
            return null;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VideoInfo)) {
            return false;
        }
        VideoInfo videoInfo = (VideoInfo) obj;
        return this.zzalu == videoInfo.getHeight() && this.zzalt == videoInfo.getWidth() && this.zzezp == videoInfo.getHdrType();
    }

    public final int getHdrType() {
        return this.zzezp;
    }

    public final int getHeight() {
        return this.zzalu;
    }

    public final int getWidth() {
        return this.zzalt;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzalu), Integer.valueOf(this.zzalt), Integer.valueOf(this.zzezp)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, getWidth());
        zzbgo.zzc(parcel, 3, getHeight());
        zzbgo.zzc(parcel, 4, getHdrType());
        zzbgo.zzai(parcel, zze);
    }
}
