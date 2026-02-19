package com.google.android.gms.ads.formats;

import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.internal.zzabh;

@zzabh
public final class NativeAdOptions {
    public static final int ADCHOICES_BOTTOM_LEFT = 3;
    public static final int ADCHOICES_BOTTOM_RIGHT = 2;
    public static final int ADCHOICES_TOP_LEFT = 0;
    public static final int ADCHOICES_TOP_RIGHT = 1;
    public static final int ORIENTATION_ANY = 0;
    public static final int ORIENTATION_LANDSCAPE = 2;
    public static final int ORIENTATION_PORTRAIT = 1;
    private final boolean zzamf;
    private final int zzamg;
    private final boolean zzamh;
    private final int zzami;
    private final VideoOptions zzamj;

    public @interface AdChoicesPlacement {
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public boolean zzamf = false;
        /* access modifiers changed from: private */
        public int zzamg = -1;
        /* access modifiers changed from: private */
        public boolean zzamh = false;
        /* access modifiers changed from: private */
        public int zzami = 1;
        /* access modifiers changed from: private */
        public VideoOptions zzamj;

        public final NativeAdOptions build() {
            return new NativeAdOptions(this);
        }

        public final Builder setAdChoicesPlacement(int i) {
            this.zzami = i;
            return this;
        }

        public final Builder setImageOrientation(int i) {
            this.zzamg = i;
            return this;
        }

        public final Builder setRequestMultipleImages(boolean z) {
            this.zzamh = z;
            return this;
        }

        public final Builder setReturnUrlsForImageAssets(boolean z) {
            this.zzamf = z;
            return this;
        }

        public final Builder setVideoOptions(VideoOptions videoOptions) {
            this.zzamj = videoOptions;
            return this;
        }
    }

    private NativeAdOptions(Builder builder) {
        this.zzamf = builder.zzamf;
        this.zzamg = builder.zzamg;
        this.zzamh = builder.zzamh;
        this.zzami = builder.zzami;
        this.zzamj = builder.zzamj;
    }

    public final int getAdChoicesPlacement() {
        return this.zzami;
    }

    public final int getImageOrientation() {
        return this.zzamg;
    }

    public final VideoOptions getVideoOptions() {
        return this.zzamj;
    }

    public final boolean shouldRequestMultipleImages() {
        return this.zzamh;
    }

    public final boolean shouldReturnUrlsForImageAssets() {
        return this.zzamf;
    }
}
