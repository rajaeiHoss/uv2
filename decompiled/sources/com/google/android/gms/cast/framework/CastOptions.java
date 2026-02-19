package com.google.android.gms.cast.framework;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CastOptions extends zzbgl {
    public static final Parcelable.Creator<CastOptions> CREATOR = new zzb();
    private final LaunchOptions zzevx;
    private String zzfad;
    private final List<String> zzfae;
    private final boolean zzfaf;
    private final boolean zzfag;
    private final CastMediaOptions zzfah;
    private final boolean zzfai;
    private final double zzfaj;

    public static final class Builder {
        private LaunchOptions zzevx = new LaunchOptions();
        private String zzfad;
        private List<String> zzfae = new ArrayList();
        private boolean zzfaf;
        private boolean zzfag = true;
        private CastMediaOptions zzfah = new CastMediaOptions.Builder().build();
        private boolean zzfai = true;
        private double zzfaj = 0.05000000074505806d;

        public final CastOptions build() {
            return new CastOptions(this.zzfad, this.zzfae, this.zzfaf, this.zzevx, this.zzfag, this.zzfah, this.zzfai, this.zzfaj);
        }

        public final Builder setCastMediaOptions(CastMediaOptions castMediaOptions) {
            this.zzfah = castMediaOptions;
            return this;
        }

        public final Builder setEnableReconnectionService(boolean z) {
            this.zzfai = z;
            return this;
        }

        public final Builder setLaunchOptions(LaunchOptions launchOptions) {
            this.zzevx = launchOptions;
            return this;
        }

        public final Builder setReceiverApplicationId(String str) {
            this.zzfad = str;
            return this;
        }

        public final Builder setResumeSavedSession(boolean z) {
            this.zzfag = z;
            return this;
        }

        public final Builder setStopReceiverApplicationWhenEndingSession(boolean z) {
            this.zzfaf = z;
            return this;
        }

        public final Builder setSupportedNamespaces(List<String> list) {
            this.zzfae = list;
            return this;
        }

        public final Builder setVolumeDeltaBeforeIceCreamSandwich(double d) throws IllegalArgumentException {
            if (d <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || d > 0.5d) {
                throw new IllegalArgumentException("volumeDelta must be greater than 0 and less or equal to 0.5");
            }
            this.zzfaj = d;
            return this;
        }
    }

    CastOptions(String str, List<String> list, boolean z, LaunchOptions launchOptions, boolean z2, CastMediaOptions castMediaOptions, boolean z3, double d) {
        this.zzfad = TextUtils.isEmpty(str) ? "" : str;
        int size = list == null ? 0 : list.size();
        ArrayList arrayList = new ArrayList(size);
        this.zzfae = arrayList;
        if (size > 0) {
            arrayList.addAll(list);
        }
        this.zzfaf = z;
        this.zzevx = launchOptions == null ? new LaunchOptions() : launchOptions;
        this.zzfag = z2;
        this.zzfah = castMediaOptions;
        this.zzfai = z3;
        this.zzfaj = d;
    }

    public CastMediaOptions getCastMediaOptions() {
        return this.zzfah;
    }

    public boolean getEnableReconnectionService() {
        return this.zzfai;
    }

    public LaunchOptions getLaunchOptions() {
        return this.zzevx;
    }

    public String getReceiverApplicationId() {
        return this.zzfad;
    }

    public boolean getResumeSavedSession() {
        return this.zzfag;
    }

    public boolean getStopReceiverApplicationWhenEndingSession() {
        return this.zzfaf;
    }

    public List<String> getSupportedNamespaces() {
        return Collections.unmodifiableList(this.zzfae);
    }

    public double getVolumeDeltaBeforeIceCreamSandwich() {
        return this.zzfaj;
    }

    public final void setReceiverApplicationId(String str) {
        this.zzfad = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, getReceiverApplicationId(), false);
        zzbgo.zzb(parcel, 3, getSupportedNamespaces(), false);
        zzbgo.zza(parcel, 4, getStopReceiverApplicationWhenEndingSession());
        zzbgo.zza(parcel, 5, (Parcelable) getLaunchOptions(), i, false);
        zzbgo.zza(parcel, 6, getResumeSavedSession());
        zzbgo.zza(parcel, 7, (Parcelable) getCastMediaOptions(), i, false);
        zzbgo.zza(parcel, 8, getEnableReconnectionService());
        zzbgo.zza(parcel, 9, getVolumeDeltaBeforeIceCreamSandwich());
        zzbgo.zzai(parcel, zze);
    }
}
