package com.google.android.gms.internal;

import android.content.Context;
import android.media.AudioManager;

@zzabh
public final class zzaja {
    private float zzcsn = 1.0f;
    private boolean zzcst = false;

    public static float zzav(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        if (audioManager == null) {
            return 0.0f;
        }
        int streamMaxVolume = audioManager.getStreamMaxVolume(3);
        int streamVolume = audioManager.getStreamVolume(3);
        if (streamMaxVolume == 0) {
            return 0.0f;
        }
        return ((float) streamVolume) / ((float) streamMaxVolume);
    }

    private final synchronized boolean zzrm() {
        return this.zzcsn >= 0.0f;
    }

    public final synchronized void setAppMuted(boolean z) {
        this.zzcst = z;
    }

    public final synchronized void setAppVolume(float f) {
        this.zzcsn = f;
    }

    public final synchronized float zzdp() {
        if (!zzrm()) {
            return 1.0f;
        }
        return this.zzcsn;
    }

    public final synchronized boolean zzdq() {
        return this.zzcst;
    }
}
