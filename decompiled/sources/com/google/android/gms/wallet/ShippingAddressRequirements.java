package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Collection;

public final class ShippingAddressRequirements extends zzbgl {
    public static final Parcelable.Creator<ShippingAddressRequirements> CREATOR = new zzam();
    ArrayList<String> zzlnu;

    public final class Builder {
        private Builder() {
        }

        public final Builder addAllowedCountryCode(String str) {
            zzbq.zzh(str, "allowedCountryCode can't be null or empty! If you don't have restrictions, just leave it unset.");
            if (ShippingAddressRequirements.this.zzlnu == null) {
                ShippingAddressRequirements.this.zzlnu = new ArrayList<>();
            }
            ShippingAddressRequirements.this.zzlnu.add(str);
            return this;
        }

        public final Builder addAllowedCountryCodes(Collection<String> collection) {
            if (collection == null || collection.isEmpty()) {
                throw new IllegalArgumentException("allowedCountryCodes can't be null or empty! If you don't have restrictions, just leave it unset.");
            }
            if (ShippingAddressRequirements.this.zzlnu == null) {
                ShippingAddressRequirements.this.zzlnu = new ArrayList<>();
            }
            ShippingAddressRequirements.this.zzlnu.addAll(collection);
            return this;
        }

        public final ShippingAddressRequirements build() {
            return ShippingAddressRequirements.this;
        }
    }

    private ShippingAddressRequirements() {
    }

    ShippingAddressRequirements(ArrayList<String> arrayList) {
        this.zzlnu = arrayList;
    }

    public static Builder newBuilder() {
        ShippingAddressRequirements shippingAddressRequirements = new ShippingAddressRequirements();
        return shippingAddressRequirements.new Builder();
    }

    public final ArrayList<String> getAllowedCountryCodes() {
        return this.zzlnu;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzb(parcel, 1, this.zzlnu, false);
        zzbgo.zzai(parcel, zze);
    }
}
