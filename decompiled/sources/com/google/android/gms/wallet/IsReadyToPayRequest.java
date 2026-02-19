package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class IsReadyToPayRequest extends zzbgl {
    public static final Parcelable.Creator<IsReadyToPayRequest> CREATOR = new zzr();
    ArrayList<Integer> zzljo;
    private String zzllb;
    private String zzllc;
    ArrayList<Integer> zzlld;
    boolean zzlle;

    public final class Builder {
        private Builder() {
        }

        public final Builder addAllowedCardNetwork(int i) {
            if (IsReadyToPayRequest.this.zzljo == null) {
                IsReadyToPayRequest.this.zzljo = new ArrayList<>();
            }
            IsReadyToPayRequest.this.zzljo.add(Integer.valueOf(i));
            return this;
        }

        public final Builder addAllowedCardNetworks(Collection<Integer> collection) {
            zzbq.checkArgument(collection != null && !collection.isEmpty(), "allowedCardNetworks can't be null or empty. If you want the defaults, leave it unset.");
            if (IsReadyToPayRequest.this.zzljo == null) {
                IsReadyToPayRequest.this.zzljo = new ArrayList<>();
            }
            IsReadyToPayRequest.this.zzljo.addAll(collection);
            return this;
        }

        public final Builder addAllowedPaymentMethod(int i) {
            if (IsReadyToPayRequest.this.zzlld == null) {
                IsReadyToPayRequest.this.zzlld = new ArrayList<>();
            }
            IsReadyToPayRequest.this.zzlld.add(Integer.valueOf(i));
            return this;
        }

        public final Builder addAllowedPaymentMethods(Collection<Integer> collection) {
            zzbq.checkArgument(collection != null && !collection.isEmpty(), "allowedPaymentMethods can't be null or empty. If you want the default, leave it unset.");
            if (IsReadyToPayRequest.this.zzlld == null) {
                IsReadyToPayRequest.this.zzlld = new ArrayList<>();
            }
            IsReadyToPayRequest.this.zzlld.addAll(collection);
            return this;
        }

        public final IsReadyToPayRequest build() {
            return IsReadyToPayRequest.this;
        }

        public final Builder setExistingPaymentMethodRequired(boolean z) {
            IsReadyToPayRequest.this.zzlle = z;
            return this;
        }
    }

    IsReadyToPayRequest() {
    }

    IsReadyToPayRequest(ArrayList<Integer> arrayList, String str, String str2, ArrayList<Integer> arrayList2, boolean z) {
        this.zzljo = arrayList;
        this.zzllb = str;
        this.zzllc = str2;
        this.zzlld = arrayList2;
        this.zzlle = z;
    }

    public static Builder newBuilder() {
        IsReadyToPayRequest isReadyToPayRequest = new IsReadyToPayRequest();
        return isReadyToPayRequest.new Builder();
    }

    public final ArrayList<Integer> getAllowedCardNetworks() {
        return this.zzljo;
    }

    public final ArrayList<Integer> getAllowedPaymentMethods() {
        return this.zzlld;
    }

    public final boolean isExistingPaymentMethodRequired() {
        return this.zzlle;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (List<Integer>) this.zzljo, false);
        zzbgo.zza(parcel, 4, this.zzllb, false);
        zzbgo.zza(parcel, 5, this.zzllc, false);
        zzbgo.zza(parcel, 6, (List<Integer>) this.zzlld, false);
        zzbgo.zza(parcel, 7, this.zzlle);
        zzbgo.zzai(parcel, zze);
    }
}
