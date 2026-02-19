package com.google.android.gms.ads;

import android.content.Context;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.internal.zzmz;
import com.google.android.gms.internal.zznb;

public class MobileAds {

    public static final class Settings {
        private final zznb zzalz = new zznb();

        @Deprecated
        public final String getTrackingId() {
            return null;
        }

        @Deprecated
        public final boolean isGoogleAnalyticsEnabled() {
            return false;
        }

        @Deprecated
        public final Settings setGoogleAnalyticsEnabled(boolean z) {
            return this;
        }

        @Deprecated
        public final Settings setTrackingId(String str) {
            return this;
        }

        /* access modifiers changed from: package-private */
        public final zznb zzbg() {
            return this.zzalz;
        }
    }

    private MobileAds() {
    }

    public static RewardedVideoAd getRewardedVideoAdInstance(Context context) {
        return zzmz.zziz().getRewardedVideoAdInstance(context);
    }

    public static void initialize(Context context) {
        initialize(context, (String) null, (Settings) null);
    }

    public static void initialize(Context context, String str) {
        initialize(context, str, (Settings) null);
    }

    @Deprecated
    public static void initialize(Context context, String str, Settings settings) {
        zzmz.zziz().zza(context, str, settings == null ? null : settings.zzbg());
    }

    public static void openDebugMenu(Context context, String str) {
        zzmz.zziz().openDebugMenu(context, str);
    }

    public static void setAppMuted(boolean z) {
        zzmz.zziz().setAppMuted(z);
    }

    public static void setAppVolume(float f) {
        zzmz.zziz().setAppVolume(f);
    }
}
