package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public class ActionCodeSettings extends zzbgl {
    public static final Parcelable.Creator<ActionCodeSettings> CREATOR = new zzb();
    private final String zzag;
    private int zzejs;
    private final String zzmoo;
    private final String zzmop;
    private final String zzmoq;
    private final boolean zzmor;
    private final String zzmos;
    private final boolean zzmot;
    private String zzmou;

    public static class Builder {
        /* access modifiers changed from: private */
        public String zzag;
        /* access modifiers changed from: private */
        public String zzmoo;
        /* access modifiers changed from: private */
        public String zzmoq;
        /* access modifiers changed from: private */
        public boolean zzmor;
        /* access modifiers changed from: private */
        public String zzmos;
        /* access modifiers changed from: private */
        public boolean zzmot;

        private Builder() {
            this.zzmot = false;
        }

        public ActionCodeSettings build() {
            return new ActionCodeSettings(this);
        }

        public Builder setAndroidPackageName(String str, boolean z, String str2) {
            this.zzmoq = str;
            this.zzmor = z;
            this.zzmos = str2;
            return this;
        }

        public Builder setHandleCodeInApp(boolean z) {
            this.zzmot = z;
            return this;
        }

        public Builder setIOSBundleId(String str) {
            this.zzmoo = str;
            return this;
        }

        public Builder setUrl(String str) {
            this.zzag = str;
            return this;
        }
    }

    private ActionCodeSettings(Builder builder) {
        this.zzag = builder.zzag;
        this.zzmoo = builder.zzmoo;
        this.zzmop = null;
        this.zzmoq = builder.zzmoq;
        this.zzmor = builder.zzmor;
        this.zzmos = builder.zzmos;
        this.zzmot = builder.zzmot;
    }

    ActionCodeSettings(String str, String str2, String str3, String str4, boolean z, String str5, boolean z2, String str6, int i) {
        this.zzag = str;
        this.zzmoo = str2;
        this.zzmop = str3;
        this.zzmoq = str4;
        this.zzmor = z;
        this.zzmos = str5;
        this.zzmot = z2;
        this.zzmou = str6;
        this.zzejs = i;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public boolean canHandleCodeInApp() {
        return this.zzmot;
    }

    public boolean getAndroidInstallApp() {
        return this.zzmor;
    }

    public String getAndroidMinimumVersion() {
        return this.zzmos;
    }

    public String getAndroidPackageName() {
        return this.zzmoq;
    }

    public String getIOSBundle() {
        return this.zzmoo;
    }

    public String getUrl() {
        return this.zzag;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, getUrl(), false);
        zzbgo.zza(parcel, 2, getIOSBundle(), false);
        zzbgo.zza(parcel, 3, this.zzmop, false);
        zzbgo.zza(parcel, 4, getAndroidPackageName(), false);
        zzbgo.zza(parcel, 5, getAndroidInstallApp());
        zzbgo.zza(parcel, 6, getAndroidMinimumVersion(), false);
        zzbgo.zza(parcel, 7, canHandleCodeInApp());
        zzbgo.zza(parcel, 8, this.zzmou, false);
        zzbgo.zzc(parcel, 9, this.zzejs);
        zzbgo.zzai(parcel, zze);
    }

    public final void zzhc(int i) {
        this.zzejs = i;
    }

    public final void zzpa(String str) {
        this.zzmou = str;
    }
}
