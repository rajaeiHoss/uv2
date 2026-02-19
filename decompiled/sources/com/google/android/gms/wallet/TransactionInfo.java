package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class TransactionInfo extends zzbgl {
    public static final Parcelable.Creator<TransactionInfo> CREATOR = new zzao();
    int zzlnw;
    String zzlnx;
    String zzlny;

    public final class Builder {
        private Builder() {
        }

        public final TransactionInfo build() {
            zzbq.zzh(TransactionInfo.this.zzlny, "currencyCode must be set!");
            boolean z = true;
            if (!(TransactionInfo.this.zzlnw == 1 || TransactionInfo.this.zzlnw == 2 || TransactionInfo.this.zzlnw == 3)) {
                z = false;
            }
            if (z) {
                if (TransactionInfo.this.zzlnw == 2) {
                    zzbq.zzh(TransactionInfo.this.zzlnx, "An estimated total price must be set if totalPriceStatus is set to WalletConstants.TOTAL_PRICE_STATUS_ESTIMATED!");
                }
                if (TransactionInfo.this.zzlnw == 3) {
                    zzbq.zzh(TransactionInfo.this.zzlnx, "An final total price must be set if totalPriceStatus is set to WalletConstants.TOTAL_PRICE_STATUS_FINAL!");
                }
                return TransactionInfo.this;
            }
            throw new IllegalArgumentException("totalPriceStatus must be set to one of WalletConstants.TotalPriceStatus!");
        }

        public final Builder setCurrencyCode(String str) {
            TransactionInfo.this.zzlny = str;
            return this;
        }

        public final Builder setTotalPrice(String str) {
            TransactionInfo.this.zzlnx = str;
            return this;
        }

        public final Builder setTotalPriceStatus(int i) {
            TransactionInfo.this.zzlnw = i;
            return this;
        }
    }

    private TransactionInfo() {
    }

    public TransactionInfo(int i, String str, String str2) {
        this.zzlnw = i;
        this.zzlnx = str;
        this.zzlny = str2;
    }

    public static Builder newBuilder() {
        TransactionInfo transactionInfo = new TransactionInfo();
        return transactionInfo.new Builder();
    }

    public final String getCurrencyCode() {
        return this.zzlny;
    }

    public final String getTotalPrice() {
        return this.zzlnx;
    }

    public final int getTotalPriceStatus() {
        return this.zzlnw;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zzlnw);
        zzbgo.zza(parcel, 2, this.zzlnx, false);
        zzbgo.zza(parcel, 3, this.zzlny, false);
        zzbgo.zzai(parcel, zze);
    }
}
