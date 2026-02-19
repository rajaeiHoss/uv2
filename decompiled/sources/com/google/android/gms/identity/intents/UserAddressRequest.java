package com.google.android.gms.identity.intents;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.identity.intents.model.CountrySpecification;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class UserAddressRequest extends zzbgl implements ReflectedParcelable {
    public static final Parcelable.Creator<UserAddressRequest> CREATOR = new zzd();
    List<CountrySpecification> zziln;

    public final class Builder {
        private Builder() {
        }

        public final Builder addAllowedCountrySpecification(CountrySpecification countrySpecification) {
            if (UserAddressRequest.this.zziln == null) {
                UserAddressRequest.this.zziln = new ArrayList();
            }
            UserAddressRequest.this.zziln.add(countrySpecification);
            return this;
        }

        public final Builder addAllowedCountrySpecifications(Collection<CountrySpecification> collection) {
            if (UserAddressRequest.this.zziln == null) {
                UserAddressRequest.this.zziln = new ArrayList();
            }
            UserAddressRequest.this.zziln.addAll(collection);
            return this;
        }

        public final UserAddressRequest build() {
            if (UserAddressRequest.this.zziln != null) {
                UserAddressRequest userAddressRequest = UserAddressRequest.this;
                userAddressRequest.zziln = Collections.unmodifiableList(userAddressRequest.zziln);
            }
            return UserAddressRequest.this;
        }
    }

    UserAddressRequest() {
    }

    UserAddressRequest(List<CountrySpecification> list) {
        this.zziln = list;
    }

    public static Builder newBuilder() {
        return new UserAddressRequest().new Builder();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.zziln, false);
        zzbgo.zzai(parcel, zze);
    }
}
