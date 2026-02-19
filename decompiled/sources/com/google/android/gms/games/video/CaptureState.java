package com.google.android.gms.games.video;

import android.os.Bundle;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;

public final class CaptureState {
    private final boolean zzarz;
    private final boolean zzfbr;
    private final boolean zziin;
    private final int zziio;
    private final int zziip;

    private CaptureState(boolean z, int i, int i2, boolean z2, boolean z3) {
        zzbq.checkArgument(VideoConfiguration.isValidCaptureMode(i, true));
        zzbq.checkArgument(VideoConfiguration.isValidQualityLevel(i2, true));
        this.zziin = z;
        this.zziio = i;
        this.zziip = i2;
        this.zzfbr = z2;
        this.zzarz = z3;
    }

    public static CaptureState zzq(Bundle bundle) {
        if (bundle == null || bundle.get("IsCapturing") == null) {
            return null;
        }
        return new CaptureState(bundle.getBoolean("IsCapturing", false), bundle.getInt("CaptureMode", -1), bundle.getInt("CaptureQuality", -1), bundle.getBoolean("IsOverlayVisible", false), bundle.getBoolean("IsPaused", false));
    }

    public final int getCaptureMode() {
        return this.zziio;
    }

    public final int getCaptureQuality() {
        return this.zziip;
    }

    public final boolean isCapturing() {
        return this.zziin;
    }

    public final boolean isOverlayVisible() {
        return this.zzfbr;
    }

    public final boolean isPaused() {
        return this.zzarz;
    }

    public final String toString() {
        return zzbg.zzx(this).zzg("IsCapturing", Boolean.valueOf(this.zziin)).zzg("CaptureMode", Integer.valueOf(this.zziio)).zzg("CaptureQuality", Integer.valueOf(this.zziip)).zzg("IsOverlayVisible", Boolean.valueOf(this.zzfbr)).zzg("IsPaused", Boolean.valueOf(this.zzarz)).toString();
    }
}
