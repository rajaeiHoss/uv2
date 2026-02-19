package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.List;

public final class Cart extends zzbgl implements ReflectedParcelable {
    public static final Parcelable.Creator<Cart> CREATOR = new zzg();
    String zzljt;
    String zzlju;
    ArrayList<LineItem> zzljv;

    public final class Builder {
        private Builder() {
        }

        public final Builder addLineItem(LineItem lineItem) {
            Cart.this.zzljv.add(lineItem);
            return this;
        }

        public final Cart build() {
            return Cart.this;
        }

        public final Builder setCurrencyCode(String str) {
            Cart.this.zzlju = str;
            return this;
        }

        public final Builder setLineItems(List<LineItem> list) {
            Cart.this.zzljv.clear();
            Cart.this.zzljv.addAll(list);
            return this;
        }

        public final Builder setTotalPrice(String str) {
            Cart.this.zzljt = str;
            return this;
        }
    }

    Cart() {
        this.zzljv = new ArrayList<>();
    }

    Cart(String str, String str2, ArrayList<LineItem> arrayList) {
        this.zzljt = str;
        this.zzlju = str2;
        this.zzljv = arrayList;
    }

    public static Builder newBuilder() {
        return new Cart().new Builder();
    }

    public final String getCurrencyCode() {
        return this.zzlju;
    }

    public final ArrayList<LineItem> getLineItems() {
        return this.zzljv;
    }

    public final String getTotalPrice() {
        return this.zzljt;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzljt, false);
        zzbgo.zza(parcel, 3, this.zzlju, false);
        zzbgo.zzc(parcel, 4, this.zzljv, false);
        zzbgo.zzai(parcel, zze);
    }
}
