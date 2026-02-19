package com.google.android.gms.ads.formats;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzkq;
import com.google.android.gms.internal.zzly;
import com.google.android.gms.internal.zzlz;

@zzabh
public final class PublisherAdViewOptions extends zzbgl {
    public static final Parcelable.Creator<PublisherAdViewOptions> CREATOR = new zzc();
    private final boolean zzamp;
    private final zzly zzamq;
    private AppEventListener zzamr;

    public static final class Builder {
        /* access modifiers changed from: private */
        public boolean zzamp = false;
        /* access modifiers changed from: private */
        public AppEventListener zzamr;

        public final PublisherAdViewOptions build() {
            return new PublisherAdViewOptions(this);
        }

        public final Builder setAppEventListener(AppEventListener appEventListener) {
            this.zzamr = appEventListener;
            return this;
        }

        public final Builder setManualImpressionsEnabled(boolean z) {
            this.zzamp = z;
            return this;
        }
    }

    private PublisherAdViewOptions(Builder builder) {
        this.zzamp = builder.zzamp;
        AppEventListener zzb = builder.zzamr;
        this.zzamr = zzb;
        this.zzamq = zzb != null ? new zzkq(this.zzamr) : null;
    }

    PublisherAdViewOptions(boolean z, IBinder iBinder) {
        this.zzamp = z;
        this.zzamq = iBinder != null ? zzlz.zzg(iBinder) : null;
    }

    public final AppEventListener getAppEventListener() {
        return this.zzamr;
    }

    public final boolean getManualImpressionsEnabled() {
        return this.zzamp;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, getManualImpressionsEnabled());
        zzly zzly = this.zzamq;
        zzbgo.zza(parcel, 2, zzly == null ? null : zzly.asBinder(), false);
        zzbgo.zzai(parcel, zze);
    }

    public final zzly zzbk() {
        return this.zzamq;
    }
}
