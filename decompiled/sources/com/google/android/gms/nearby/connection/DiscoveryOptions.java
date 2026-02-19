package com.google.android.gms.nearby.connection;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class DiscoveryOptions extends zzbgl {
    public static final Parcelable.Creator<DiscoveryOptions> CREATOR = new zzg();
    /* access modifiers changed from: private */
    public Strategy zzjvv;
    /* access modifiers changed from: private */
    public boolean zzjvy;
    /* access modifiers changed from: private */
    public boolean zzjvz;
    /* access modifiers changed from: private */
    public boolean zzjwj;

    public static final class Builder {
        private final DiscoveryOptions zzjwk;

        public Builder() {
            this.zzjwk = new DiscoveryOptions();
        }

        public Builder(DiscoveryOptions discoveryOptions) {
            DiscoveryOptions discoveryOptions2 = new DiscoveryOptions();
            this.zzjwk = discoveryOptions2;
            Strategy unused = discoveryOptions2.zzjvv = discoveryOptions.zzjvv;
            boolean unused2 = discoveryOptions2.zzjwj = discoveryOptions.zzjwj;
            boolean unused3 = discoveryOptions2.zzjvy = discoveryOptions.zzjvy;
            boolean unused4 = discoveryOptions2.zzjvz = discoveryOptions.zzjvz;
        }

        public final DiscoveryOptions build() {
            return this.zzjwk;
        }

        public final Builder setStrategy(Strategy strategy) {
            Strategy unused = this.zzjwk.zzjvv = strategy;
            return this;
        }
    }

    private DiscoveryOptions() {
        this.zzjwj = false;
        this.zzjvy = true;
        this.zzjvz = true;
    }

    @Deprecated
    public DiscoveryOptions(Strategy strategy) {
        this.zzjwj = false;
        this.zzjvy = true;
        this.zzjvz = true;
        this.zzjvv = strategy;
    }

    public DiscoveryOptions(Strategy strategy, boolean z, boolean z2, boolean z3) {
        this.zzjwj = false;
        this.zzjvy = true;
        this.zzjvz = true;
        this.zzjvv = strategy;
        this.zzjwj = z;
        this.zzjvy = z2;
        this.zzjvz = z3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DiscoveryOptions) {
            DiscoveryOptions discoveryOptions = (DiscoveryOptions) obj;
            return zzbg.equal(this.zzjvv, discoveryOptions.zzjvv) && zzbg.equal(Boolean.valueOf(this.zzjwj), Boolean.valueOf(discoveryOptions.zzjwj)) && zzbg.equal(Boolean.valueOf(this.zzjvy), Boolean.valueOf(discoveryOptions.zzjvy)) && zzbg.equal(Boolean.valueOf(this.zzjvz), Boolean.valueOf(discoveryOptions.zzjvz));
        }
        return false;
    }

    public final Strategy getStrategy() {
        return this.zzjvv;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzjvv, Boolean.valueOf(this.zzjwj), Boolean.valueOf(this.zzjvy), Boolean.valueOf(this.zzjvz)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) getStrategy(), i, false);
        zzbgo.zza(parcel, 2, this.zzjwj);
        zzbgo.zza(parcel, 3, this.zzjvy);
        zzbgo.zza(parcel, 4, this.zzjvz);
        zzbgo.zzai(parcel, zze);
    }
}
