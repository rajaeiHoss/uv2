package com.google.android.gms.ads.doubleclick;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzmu;
import com.google.android.gms.internal.zzmv;
import java.util.Date;
import java.util.List;
import java.util.Set;

public final class PublisherAdRequest {
    public static final String DEVICE_ID_EMULATOR = "B3EEABB8EE11C2BE770B684D95219ECB";
    public static final int ERROR_CODE_INTERNAL_ERROR = 0;
    public static final int ERROR_CODE_INVALID_REQUEST = 1;
    public static final int ERROR_CODE_NETWORK_ERROR = 2;
    public static final int ERROR_CODE_NO_FILL = 3;
    public static final int GENDER_FEMALE = 2;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_UNKNOWN = 0;
    private final zzmu zzalq;

    public static final class Builder {
        /* access modifiers changed from: private */
        public final zzmv zzalr = new zzmv();

        public final Builder addCategoryExclusion(String str) {
            this.zzalr.zzaj(str);
            return this;
        }

        public final Builder addCustomEventExtrasBundle(Class<? extends CustomEvent> cls, Bundle bundle) {
            this.zzalr.zzb(cls, bundle);
            return this;
        }

        public final Builder addCustomTargeting(String str, String str2) {
            this.zzalr.zzd(str, str2);
            return this;
        }

        public final Builder addCustomTargeting(String str, List<String> list) {
            if (list != null) {
                this.zzalr.zzd(str, TextUtils.join(",", list));
            }
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
            return this;
        }

        public final Builder addTestDevice(String str) {
            this.zzalr.zzae(str);
            return this;
        }

        public final PublisherAdRequest build() {
            return new PublisherAdRequest(this);
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

        @Deprecated
        public final Builder setManualImpressionsEnabled(boolean z) {
            this.zzalr.setManualImpressionsEnabled(z);
            return this;
        }

        public final Builder setPublisherProvidedId(String str) {
            this.zzalr.zzah(str);
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

    private PublisherAdRequest(Builder builder) {
        this.zzalq = new zzmu(builder.zzalr);
    }

    public static void updateCorrelator() {
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

    public final Bundle getCustomTargeting() {
        return this.zzalq.getCustomTargeting();
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

    public final boolean getManualImpressionsEnabled() {
        return this.zzalq.getManualImpressionsEnabled();
    }

    @Deprecated
    public final <T extends NetworkExtras> T getNetworkExtras(Class<T> cls) {
        return this.zzalq.getNetworkExtras(cls);
    }

    public final <T extends MediationAdapter> Bundle getNetworkExtrasBundle(Class<T> cls) {
        return this.zzalq.getNetworkExtrasBundle(cls);
    }

    public final String getPublisherProvidedId() {
        return this.zzalq.getPublisherProvidedId();
    }

    public final boolean isTestDevice(Context context) {
        return this.zzalq.isTestDevice(context);
    }

    public final zzmu zzbe() {
        return this.zzalq;
    }
}
