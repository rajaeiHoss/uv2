package com.google.android.gms.internal;

import android.content.Context;
import android.media.AudioManager;

@zzabh
public final class zzanq implements AudioManager.OnAudioFocusChangeListener {
    private final AudioManager mAudioManager;
    private boolean zzdlm;
    private final zzanr zzdog;
    private boolean zzdoh;
    private boolean zzdoi;
    private float zzdoj = 1.0f;

    public zzanq(Context context, zzanr zzanr) {
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
        this.zzdog = zzanr;
    }

    private final void zztr() {
        boolean z;
        boolean z2;
        boolean z3 = false;
        boolean z4 = this.zzdlm && !this.zzdoi && this.zzdoj > 0.0f;
        if (z4 && !(z2 = this.zzdoh)) {
            AudioManager audioManager = this.mAudioManager;
            if (audioManager != null && !z2) {
                if (audioManager.requestAudioFocus(this, 3, 2) == 1) {
                    z3 = true;
                }
                this.zzdoh = z3;
            }
            this.zzdog.zzsn();
        } else if (!z4 && (z = this.zzdoh)) {
            AudioManager audioManager2 = this.mAudioManager;
            if (audioManager2 != null && z) {
                if (audioManager2.abandonAudioFocus(this) == 0) {
                    z3 = true;
                }
                this.zzdoh = z3;
            }
            this.zzdog.zzsn();
        }
    }

    public final float getVolume() {
        float f = this.zzdoi ? 0.0f : this.zzdoj;
        if (this.zzdoh) {
            return f;
        }
        return 0.0f;
    }

    public final void onAudioFocusChange(int i) {
        this.zzdoh = i > 0;
        this.zzdog.zzsn();
    }

    public final void setMuted(boolean z) {
        this.zzdoi = z;
        zztr();
    }

    public final void zzb(float f) {
        this.zzdoj = f;
        zztr();
    }

    public final void zzto() {
        this.zzdlm = true;
        zztr();
    }

    public final void zztp() {
        this.zzdlm = false;
        zztr();
    }
}
