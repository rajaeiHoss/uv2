package com.google.android.gms.ads;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzmu;
import com.google.android.gms.internal.zzmv;
import java.util.Date;
import java.util.Set;

public final class AdRequest {
    public static final String DEVICE_ID_EMULATOR = "B3EEABB8EE11C2BE770B684D95219ECB";
    public static final int ERROR_CODE_INTERNAL_ERROR = 0;
    public static final int ERROR_CODE_INVALID_REQUEST = 1;
    public static final int ERROR_CODE_NETWORK_ERROR = 2;
    public static final int ERROR_CODE_NO_FILL = 3;
    public static final int GENDER_FEMALE = 2;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_UNKNOWN = 0;
    public static final int MAX_CONTENT_URL_LENGTH = 512;
    private final zzmu zzalq;

    public static final class Builder {
        /* access modifiers changed from: private */
        public final zzmv zzalr;

        public Builder() {
            zzmv zzmv = new zzmv();
            this.zzalr = zzmv;
            zzmv.zzae("B3EEABB8EE11C2BE770B684D95219ECB");
        }

        public final Builder addCustomEventExtrasBundle(Class<? extends CustomEvent> cls, Bundle bundle) {
            this.zzalr.zzb(cls, bundle);
            return this;
        }

        public final Builder addKeyword(String str) {
            this.zzalr.zzad(str);
            return this;
        }

        public final Builder addNetworkExtras(NetworkExtras networkExtras) {
            this.zzalr.zza(networkExtras);
            return this;
        }

        public final Builder addNetworkExtrasBundle(Class<? extends MediationAdapter> cls, Bundle bundle) {
            this.zzalr.zza(cls, bundle);
            if (cls.equals(AdMobAdapter.class) && bundle.getBoolean("_emulatorLiveAds")) {
                this.zzalr.zzaf("B3EEABB8EE11C2BE770B684D95219ECB");
            }
            return this;
        }

        public final Builder addTestDevice(String str) {
            this.zzalr.zzae(str);
            return this;
        }

        public final AdRequest build() {
            return new AdRequest(this);
        }

        public final Builder setBirthday(Date date) {
            this.zzalr.zza(date);
            return this;
        }

        public final Builder setContentUrl(String str) {
            zzbq.checkNotNull(str, "Content URL must be non-null.");
            zzbq.zzh(str, "Content URL must be non-empty.");
            zzbq.zzb(str.length() <= 512, "Content URL must not exceed %d in length.  Provided length was %d.", 512, Integer.valueOf(str.length()));
            this.zzalr.zzag(str);
            return this;
        }

        public final Builder setGender(int i) {
            this.zzalr.zzr(i);
            return this;
        }

        public final Builder setIsDesignedForFamilies(boolean z) {
            this.zzalr.zzk(z);
            return this;
        }

        public final Builder setLocation(Location location) {
            this.zzalr.zzb(location);
            return this;
        }

        public final Builder setRequestAgent(String str) {
            this.zzalr.zzai(str);
            return this;
        }

        public final Builder tagForChildDirectedTreatment(boolean z) {
            this.zzalr.zzj(z);
            return this;
        }
    }

    private AdRequest(Builder builder) {
        this.zzalq = new zzmu(builder.zzalr);
    }

    public final Date getBirthday() {
        return this.zzalq.getBirthday();
    }

    public final String getContentUrl() {
        return this.zzalq.getContentUrl();
    }

    public final <T extends CustomEvent> Bundle getCustomEventExtrasBundle(Class<T> cls) {
        return this.zzalq.getCustomEventExtrasBundle(cls);
    }

    public final int getGender() {
        return this.zzalq.getGender();
    }

    public final Set<String> getKeywords() {
        return this.zzalq.getKeywords();
    }

    public final Location getLocation() {
        return this.zzalq.getLocation();
    }

    @Deprecated
    public final <T extends NetworkExtras> T getNetworkExtras(Class<T> cls) {
        return this.zzalq.getNetworkExtras(cls);
    }

    public final <T extends MediationAdapter> Bundle getNetworkExtrasBundle(Class<T> cls) {
        return this.zzalq.getNetworkExtrasBundle(cls);
    }

    public final boolean isTestDevice(Context context) {
        return this.zzalq.isTestDevice(context);
    }

    public final zzmu zzbe() {
        return this.zzalq;
    }
}
