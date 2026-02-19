package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.identity.intents.model.UserAddress;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class MaskedWallet extends zzbgl implements ReflectedParcelable {
    public static final Parcelable.Creator<MaskedWallet> CREATOR = new zzx();
    String zzlkc;
    String zzlkd;
    String zzlkf;
    private zza zzlkg;
    private zza zzlkh;
    String[] zzlki;
    UserAddress zzlkj;
    UserAddress zzlkk;
    InstrumentInfo[] zzlkl;
    private LoyaltyWalletObject[] zzlme;
    private OfferWalletObject[] zzlmf;

    public final class Builder {
        private Builder() {
        }

        public final MaskedWallet build() {
            return MaskedWallet.this;
        }

        public final Builder setBuyerBillingAddress(UserAddress userAddress) {
            MaskedWallet.this.zzlkj = userAddress;
            return this;
        }

        public final Builder setBuyerShippingAddress(UserAddress userAddress) {
            MaskedWallet.this.zzlkk = userAddress;
            return this;
        }

        public final Builder setEmail(String str) {
            MaskedWallet.this.zzlkf = str;
            return this;
        }

        public final Builder setGoogleTransactionId(String str) {
            MaskedWallet.this.zzlkc = str;
            return this;
        }

        public final Builder setInstrumentInfos(InstrumentInfo[] instrumentInfoArr) {
            MaskedWallet.this.zzlkl = instrumentInfoArr;
            return this;
        }

        public final Builder setMerchantTransactionId(String str) {
            MaskedWallet.this.zzlkd = str;
            return this;
        }

        public final Builder setPaymentDescriptions(String[] strArr) {
            MaskedWallet.this.zzlki = strArr;
            return this;
        }
    }

    private MaskedWallet() {
    }

    MaskedWallet(String str, String str2, String[] strArr, String str3, zza zza, zza zza2, LoyaltyWalletObject[] loyaltyWalletObjectArr, OfferWalletObject[] offerWalletObjectArr, UserAddress userAddress, UserAddress userAddress2, InstrumentInfo[] instrumentInfoArr) {
        this.zzlkc = str;
        this.zzlkd = str2;
        this.zzlki = strArr;
        this.zzlkf = str3;
        this.zzlkg = zza;
        this.zzlkh = zza2;
        this.zzlme = loyaltyWalletObjectArr;
        this.zzlmf = offerWalletObjectArr;
        this.zzlkj = userAddress;
        this.zzlkk = userAddress2;
        this.zzlkl = instrumentInfoArr;
    }

    public static Builder newBuilderFrom(MaskedWallet maskedWallet) {
        zzbq.checkNotNull(maskedWallet);
        MaskedWallet wallet = new MaskedWallet();
        Builder builder = wallet.new Builder().setGoogleTransactionId(maskedWallet.getGoogleTransactionId()).setMerchantTransactionId(maskedWallet.getMerchantTransactionId()).setPaymentDescriptions(maskedWallet.getPaymentDescriptions()).setInstrumentInfos(maskedWallet.getInstrumentInfos()).setEmail(maskedWallet.getEmail());
        wallet.zzlme = maskedWallet.zzlme;
        wallet.zzlmf = maskedWallet.zzlmf;
        return builder.setBuyerBillingAddress(maskedWallet.getBuyerBillingAddress()).setBuyerShippingAddress(maskedWallet.getBuyerShippingAddress());
    }

    public final UserAddress getBuyerBillingAddress() {
        return this.zzlkj;
    }

    public final UserAddress getBuyerShippingAddress() {
        return this.zzlkk;
    }

    public final String getEmail() {
        return this.zzlkf;
    }

    public final String getGoogleTransactionId() {
        return this.zzlkc;
    }

    public final InstrumentInfo[] getInstrumentInfos() {
        return this.zzlkl;
    }

    public final String getMerchantTransactionId() {
        return this.zzlkd;
    }

    public final String[] getPaymentDescriptions() {
        return this.zzlki;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzlkc, false);
        zzbgo.zza(parcel, 3, this.zzlkd, false);
        zzbgo.zza(parcel, 4, this.zzlki, false);
        zzbgo.zza(parcel, 5, this.zzlkf, false);
        zzbgo.zza(parcel, 6, (Parcelable) this.zzlkg, i, false);
        zzbgo.zza(parcel, 7, (Parcelable) this.zzlkh, i, false);
        zzbgo.zza(parcel, 8, this.zzlme, i, false);
        zzbgo.zza(parcel, 9, this.zzlmf, i, false);
        zzbgo.zza(parcel, 10, (Parcelable) this.zzlkj, i, false);
        zzbgo.zza(parcel, 11, (Parcelable) this.zzlkk, i, false);
        zzbgo.zza(parcel, 12, this.zzlkl, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
