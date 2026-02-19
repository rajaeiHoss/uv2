package com.google.android.gms.wallet.fragment;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;

public final class WalletFragmentInitParams extends zzbgl implements ReflectedParcelable {
    public static final Parcelable.Creator<WalletFragmentInitParams> CREATOR = new zzd();
    /* access modifiers changed from: private */
    public String zzehk;
    /* access modifiers changed from: private */
    public MaskedWalletRequest zzlou;
    /* access modifiers changed from: private */
    public MaskedWallet zzlov;
    /* access modifiers changed from: private */
    public int zzlpi;

    public final class Builder {
        private Builder() {
        }

        public final WalletFragmentInitParams build() {
            boolean z = true;
            zzbq.zza((WalletFragmentInitParams.this.zzlov != null && WalletFragmentInitParams.this.zzlou == null) || (WalletFragmentInitParams.this.zzlov == null && WalletFragmentInitParams.this.zzlou != null), (Object) "Exactly one of MaskedWallet or MaskedWalletRequest is required");
            if (WalletFragmentInitParams.this.zzlpi < 0) {
                z = false;
            }
            zzbq.zza(z, (Object) "masked wallet request code is required and must be non-negative");
            return WalletFragmentInitParams.this;
        }

        public final Builder setAccountName(String str) {
            String unused = WalletFragmentInitParams.this.zzehk = str;
            return this;
        }

        public final Builder setMaskedWallet(MaskedWallet maskedWallet) {
            MaskedWallet unused = WalletFragmentInitParams.this.zzlov = maskedWallet;
            return this;
        }

        public final Builder setMaskedWalletRequest(MaskedWalletRequest maskedWalletRequest) {
            MaskedWalletRequest unused = WalletFragmentInitParams.this.zzlou = maskedWalletRequest;
            return this;
        }

        public final Builder setMaskedWalletRequestCode(int i) {
            int unused = WalletFragmentInitParams.this.zzlpi = i;
            return this;
        }
    }

    private WalletFragmentInitParams() {
        this.zzlpi = -1;
    }

    WalletFragmentInitParams(String str, MaskedWalletRequest maskedWalletRequest, int i, MaskedWallet maskedWallet) {
        this.zzehk = str;
        this.zzlou = maskedWalletRequest;
        this.zzlpi = i;
        this.zzlov = maskedWallet;
    }

    public static Builder newBuilder() {
        return new WalletFragmentInitParams().new Builder();
    }

    public final String getAccountName() {
        return this.zzehk;
    }

    public final MaskedWallet getMaskedWallet() {
        return this.zzlov;
    }

    public final MaskedWalletRequest getMaskedWalletRequest() {
        return this.zzlou;
    }

    public final int getMaskedWalletRequestCode() {
        return this.zzlpi;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, getAccountName(), false);
        zzbgo.zza(parcel, 3, (Parcelable) getMaskedWalletRequest(), i, false);
        zzbgo.zzc(parcel, 4, getMaskedWalletRequestCode());
        zzbgo.zza(parcel, 5, (Parcelable) getMaskedWallet(), i, false);
        zzbgo.zzai(parcel, zze);
    }
}
