package com.google.android.gms.games.video;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class VideoConfiguration extends zzbgl {
    public static final int CAPTURE_MODE_FILE = 0;
    public static final int CAPTURE_MODE_STREAM = 1;
    public static final int CAPTURE_MODE_UNKNOWN = -1;
    public static final Parcelable.Creator<VideoConfiguration> CREATOR = new zzb();
    public static final int NUM_CAPTURE_MODE = 2;
    public static final int NUM_QUALITY_LEVEL = 4;
    public static final int QUALITY_LEVEL_FULLHD = 3;
    public static final int QUALITY_LEVEL_HD = 1;
    public static final int QUALITY_LEVEL_SD = 0;
    public static final int QUALITY_LEVEL_UNKNOWN = -1;
    public static final int QUALITY_LEVEL_XHD = 2;
    private final int zziio;
    private final int zziiv;
    private final String zziiw;
    private final String zziix;
    private final String zziiy;
    private final String zziiz;
    private final boolean zzija;

    public static final class Builder {
        private int zziio;
        private int zziiv;
        private String zziiw = null;
        private String zziix = null;
        private String zziiy = null;
        private String zziiz = null;
        private boolean zzija = true;

        public Builder(int i, int i2) {
            this.zziiv = i;
            this.zziio = i2;
        }

        public final VideoConfiguration build() {
            return new VideoConfiguration(this.zziiv, this.zziio, (String) null, (String) null, (String) null, (String) null, this.zzija);
        }

        public final Builder setCaptureMode(int i) {
            this.zziio = i;
            return this;
        }

        public final Builder setQualityLevel(int i) {
            this.zziiv = i;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ValidCaptureModes {
    }

    public VideoConfiguration(int i, int i2, String str, String str2, String str3, String str4, boolean z) {
        boolean z2 = false;
        zzbq.checkArgument(isValidQualityLevel(i, false));
        zzbq.checkArgument(isValidCaptureMode(i2, false));
        this.zziiv = i;
        this.zziio = i2;
        this.zzija = z;
        if (i2 == 1) {
            this.zziix = str2;
            this.zziiw = str;
            this.zziiy = str3;
            this.zziiz = str4;
            return;
        }
        zzbq.checkArgument(str2 == null, "Stream key should be null when not streaming");
        zzbq.checkArgument(str == null, "Stream url should be null when not streaming");
        zzbq.checkArgument(str3 == null, "Stream title should be null when not streaming");
        zzbq.checkArgument(str4 == null ? true : z2, "Stream description should be null when not streaming");
        this.zziix = null;
        this.zziiw = null;
        this.zziiy = null;
        this.zziiz = null;
    }

    public static boolean isValidCaptureMode(int i, boolean z) {
        if (i != -1) {
            z = true;
            if (i == 0 || i == 1) {
                return z;
            }
            return false;
        }
        return z;
    }

    public static boolean isValidQualityLevel(int i, boolean z) {
        if (i != -1) {
            z = true;
            if (i == 0 || i == 1 || i == 2 || i == 3) {
                return z;
            }
            return false;
        }
        return z;
    }

    public final int getCaptureMode() {
        return this.zziio;
    }

    public final int getQualityLevel() {
        return this.zziiv;
    }

    public final String getStreamUrl() {
        return this.zziiw;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, getQualityLevel());
        zzbgo.zzc(parcel, 2, getCaptureMode());
        zzbgo.zza(parcel, 3, getStreamUrl(), false);
        zzbgo.zza(parcel, 4, this.zziix, false);
        zzbgo.zza(parcel, 5, this.zziiy, false);
        zzbgo.zza(parcel, 6, this.zziiz, false);
        zzbgo.zza(parcel, 7, this.zzija);
        zzbgo.zzai(parcel, zze);
    }
}
