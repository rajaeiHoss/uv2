package com.google.android.gms.ads;

import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzns;

@zzabh
public final class VideoOptions {
    private final boolean zzamc;
    private final boolean zzamd;
    private final boolean zzame;

    public static final class Builder {
        /* access modifiers changed from: private */
        public boolean zzamc = true;
        /* access modifiers changed from: private */
        public boolean zzamd = false;
        /* access modifiers changed from: private */
        public boolean zzame = false;

        public final VideoOptions build() {
            return new VideoOptions(this);
        }

        public final Builder setClickToExpandRequested(boolean z) {
            this.zzame = z;
            return this;
        }

        public final Builder setCustomControlsRequested(boolean z) {
            this.zzamd = z;
            return this;
        }

        public final Builder setStartMuted(boolean z) {
            this.zzamc = z;
            return this;
        }
    }

    private VideoOptions(Builder builder) {
        this.zzamc = builder.zzamc;
        this.zzamd = builder.zzamd;
        this.zzame = builder.zzame;
    }

    public VideoOptions(zzns zzns) {
        this.zzamc = zzns.zzbkn;
        this.zzamd = zzns.zzbko;
        this.zzame = zzns.zzbkp;
    }

    public final boolean getClickToExpandRequested() {
        return this.zzame;
    }

    public final boolean getCustomControlsRequested() {
        return this.zzamd;
    }

    public final boolean getStartMuted() {
        return this.zzamc;
    }
}
