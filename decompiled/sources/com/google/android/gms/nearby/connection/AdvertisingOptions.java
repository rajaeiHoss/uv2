package com.google.android.gms.nearby.connection;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class AdvertisingOptions extends zzbgl {
    public static final Parcelable.Creator<AdvertisingOptions> CREATOR = new zzb();
    /* access modifiers changed from: private */
    public Strategy zzjvv;
    /* access modifiers changed from: private */
    public boolean zzjvw;
    /* access modifiers changed from: private */
    public boolean zzjvx;
    /* access modifiers changed from: private */
    public boolean zzjvy;
    /* access modifiers changed from: private */
    public boolean zzjvz;
    /* access modifiers changed from: private */
    public byte[] zzjwa;

    public static final class Builder {
        private final AdvertisingOptions zzjwb;

        public Builder() {
            this.zzjwb = new AdvertisingOptions();
        }

        public Builder(AdvertisingOptions advertisingOptions) {
            AdvertisingOptions advertisingOptions2 = new AdvertisingOptions();
            this.zzjwb = advertisingOptions2;
            Strategy unused = advertisingOptions2.zzjvv = advertisingOptions.zzjvv;
            boolean unused2 = advertisingOptions2.zzjvw = advertisingOptions.zzjvw;
            boolean unused3 = advertisingOptions2.zzjvx = advertisingOptions.zzjvx;
            boolean unused4 = advertisingOptions2.zzjvy = advertisingOptions.zzjvy;
            boolean unused5 = advertisingOptions2.zzjvz = advertisingOptions.zzjvz;
            byte[] unused6 = advertisingOptions2.zzjwa = advertisingOptions.zzjwa;
        }

        public final AdvertisingOptions build() {
            return this.zzjwb;
        }

        public final Builder setStrategy(Strategy strategy) {
            Strategy unused = this.zzjwb.zzjvv = strategy;
            return this;
        }
    }

    private AdvertisingOptions() {
        this.zzjvw = true;
        this.zzjvx = true;
        this.zzjvy = true;
        this.zzjvz = true;
    }

    @Deprecated
    public AdvertisingOptions(Strategy strategy) {
        this.zzjvw = true;
        this.zzjvx = true;
        this.zzjvy = true;
        this.zzjvz = true;
        this.zzjvv = strategy;
    }

    public AdvertisingOptions(Strategy strategy, boolean z, boolean z2, boolean z3, boolean z4, byte[] bArr) {
        this.zzjvw = true;
        this.zzjvx = true;
        this.zzjvy = true;
        this.zzjvz = true;
        this.zzjvv = strategy;
        this.zzjvw = z;
        this.zzjvx = z2;
        this.zzjvy = z3;
        this.zzjvz = z4;
        this.zzjwa = bArr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AdvertisingOptions) {
            AdvertisingOptions advertisingOptions = (AdvertisingOptions) obj;
            return zzbg.equal(this.zzjvv, advertisingOptions.zzjvv) && zzbg.equal(Boolean.valueOf(this.zzjvw), Boolean.valueOf(advertisingOptions.zzjvw)) && zzbg.equal(Boolean.valueOf(this.zzjvx), Boolean.valueOf(advertisingOptions.zzjvx)) && zzbg.equal(Boolean.valueOf(this.zzjvy), Boolean.valueOf(advertisingOptions.zzjvy)) && zzbg.equal(Boolean.valueOf(this.zzjvz), Boolean.valueOf(advertisingOptions.zzjvz)) && Arrays.equals(this.zzjwa, advertisingOptions.zzjwa);
        }
        return false;
    }

    public final Strategy getStrategy() {
        return this.zzjvv;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzjvv, Boolean.valueOf(this.zzjvw), Boolean.valueOf(this.zzjvx), Boolean.valueOf(this.zzjvy), Boolean.valueOf(this.zzjvz), Integer.valueOf(Arrays.hashCode(this.zzjwa))});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) getStrategy(), i, false);
        zzbgo.zza(parcel, 2, this.zzjvw);
        zzbgo.zza(parcel, 3, this.zzjvx);
        zzbgo.zza(parcel, 4, this.zzjvy);
        zzbgo.zza(parcel, 5, this.zzjvz);
        zzbgo.zza(parcel, 6, this.zzjwa, false);
        zzbgo.zzai(parcel, zze);
    }
}
