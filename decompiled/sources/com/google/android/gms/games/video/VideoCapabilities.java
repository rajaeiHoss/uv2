package com.google.android.gms.games.video;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.games.internal.zzc;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class VideoCapabilities extends zzc {
    public static final Parcelable.Creator<VideoCapabilities> CREATOR = new zza();
    private final boolean zziiq;
    private final boolean zziir;
    private final boolean zziis;
    private final boolean[] zziit;
    private final boolean[] zziiu;

    public VideoCapabilities(boolean z, boolean z2, boolean z3, boolean[] zArr, boolean[] zArr2) {
        this.zziiq = z;
        this.zziir = z2;
        this.zziis = z3;
        this.zziit = zArr;
        this.zziiu = zArr2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof VideoCapabilities)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        VideoCapabilities videoCapabilities = (VideoCapabilities) obj;
        return zzbg.equal(videoCapabilities.getSupportedCaptureModes(), getSupportedCaptureModes()) && zzbg.equal(videoCapabilities.getSupportedQualityLevels(), getSupportedQualityLevels()) && zzbg.equal(Boolean.valueOf(videoCapabilities.isCameraSupported()), Boolean.valueOf(isCameraSupported())) && zzbg.equal(Boolean.valueOf(videoCapabilities.isMicSupported()), Boolean.valueOf(isMicSupported())) && zzbg.equal(Boolean.valueOf(videoCapabilities.isWriteStorageSupported()), Boolean.valueOf(isWriteStorageSupported()));
    }

    public final boolean[] getSupportedCaptureModes() {
        return this.zziit;
    }

    public final boolean[] getSupportedQualityLevels() {
        return this.zziiu;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{getSupportedCaptureModes(), getSupportedQualityLevels(), Boolean.valueOf(isCameraSupported()), Boolean.valueOf(isMicSupported()), Boolean.valueOf(isWriteStorageSupported())});
    }

    public final boolean isCameraSupported() {
        return this.zziiq;
    }

    public final boolean isFullySupported(int i, int i2) {
        return this.zziiq && this.zziir && this.zziis && supportsCaptureMode(i) && supportsQualityLevel(i2);
    }

    public final boolean isMicSupported() {
        return this.zziir;
    }

    public final boolean isWriteStorageSupported() {
        return this.zziis;
    }

    public final boolean supportsCaptureMode(int i) {
        zzbq.checkState(VideoConfiguration.isValidCaptureMode(i, false));
        return this.zziit[i];
    }

    public final boolean supportsQualityLevel(int i) {
        zzbq.checkState(VideoConfiguration.isValidQualityLevel(i, false));
        return this.zziiu[i];
    }

    public final String toString() {
        return zzbg.zzx(this).zzg("SupportedCaptureModes", getSupportedCaptureModes()).zzg("SupportedQualityLevels", getSupportedQualityLevels()).zzg("CameraSupported", Boolean.valueOf(isCameraSupported())).zzg("MicSupported", Boolean.valueOf(isMicSupported())).zzg("StorageWriteSupported", Boolean.valueOf(isWriteStorageSupported())).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, isCameraSupported());
        zzbgo.zza(parcel, 2, isMicSupported());
        zzbgo.zza(parcel, 3, isWriteStorageSupported());
        zzbgo.zza(parcel, 4, getSupportedCaptureModes(), false);
        zzbgo.zza(parcel, 5, getSupportedQualityLevels(), false);
        zzbgo.zzai(parcel, zze);
    }
}
