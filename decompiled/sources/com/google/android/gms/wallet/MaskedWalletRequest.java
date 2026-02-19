package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.identity.intents.model.CountrySpecification;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class MaskedWalletRequest extends zzbgl implements ReflectedParcelable {
    public static final Parcelable.Creator<MaskedWalletRequest> CREATOR = new zzz();
    String zzcyf;
    String zzlju;
    String zzlkd;
    Cart zzlkn;
    boolean zzlmh;
    boolean zzlmi;
    boolean zzlmj;
    String zzlmk;
    String zzlml;
    private boolean zzlmm;
    boolean zzlmn;
    private CountrySpecification[] zzlmo;
    boolean zzlmp;
    boolean zzlmq;
    ArrayList<CountrySpecification> zzlmr;
    PaymentMethodTokenizationParameters zzlms;
    ArrayList<Integer> zzlmt;

    public final class Builder {
        private Builder() {
        }

        public final Builder addAllowedCardNetwork(int i) {
            if (MaskedWalletRequest.this.zzlmt == null) {
                MaskedWalletRequest.this.zzlmt = new ArrayList<>();
            }
            MaskedWalletRequest.this.zzlmt.add(Integer.valueOf(i));
            return this;
        }

        public final Builder addAllowedCardNetworks(Collection<Integer> collection) {
            if (collection != null) {
                if (MaskedWalletRequest.this.zzlmt == null) {
                    MaskedWalletRequest.this.zzlmt = new ArrayList<>();
                }
                MaskedWalletRequest.this.zzlmt.addAll(collection);
            }
            return this;
        }

        public final Builder addAllowedCountrySpecificationForShipping(CountrySpecification countrySpecification) {
            if (MaskedWalletRequest.this.zzlmr == null) {
                MaskedWalletRequest.this.zzlmr = new ArrayList<>();
            }
            MaskedWalletRequest.this.zzlmr.add(countrySpecification);
            return this;
        }

        public final Builder addAllowedCountrySpecificationsForShipping(Collection<CountrySpecification> collection) {
            if (collection != null) {
                if (MaskedWalletRequest.this.zzlmr == null) {
                    MaskedWalletRequest.this.zzlmr = new ArrayList<>();
                }
                MaskedWalletRequest.this.zzlmr.addAll(collection);
            }
            return this;
        }

        public final MaskedWalletRequest build() {
            return MaskedWalletRequest.this;
        }

        public final Builder setAllowDebitCard(boolean z) {
            MaskedWalletRequest.this.zzlmq = z;
            return this;
        }

        public final Builder setAllowPrepaidCard(boolean z) {
            MaskedWalletRequest.this.zzlmp = z;
            return this;
        }

        public final Builder setCart(Cart cart) {
            MaskedWalletRequest.this.zzlkn = cart;
            return this;
        }

        public final Builder setCountryCode(String str) {
            MaskedWalletRequest.this.zzcyf = str;
            return this;
        }

        public final Builder setCurrencyCode(String str) {
            MaskedWalletRequest.this.zzlju = str;
            return this;
        }

        public final Builder setEstimatedTotalPrice(String str) {
            MaskedWalletRequest.this.zzlmk = str;
            return this;
        }

        @Deprecated
        public final Builder setIsBillingAgreement(boolean z) {
            MaskedWalletRequest.this.zzlmn = z;
            return this;
        }

        public final Builder setMerchantName(String str) {
            MaskedWalletRequest.this.zzlml = str;
            return this;
        }

        public final Builder setMerchantTransactionId(String str) {
            MaskedWalletRequest.this.zzlkd = str;
            return this;
        }

        public final Builder setPaymentMethodTokenizationParameters(PaymentMethodTokenizationParameters paymentMethodTokenizationParameters) {
            MaskedWalletRequest.this.zzlms = paymentMethodTokenizationParameters;
            return this;
        }

        public final Builder setPhoneNumberRequired(boolean z) {
            MaskedWalletRequest.this.zzlmh = z;
            return this;
        }

        public final Builder setShippingAddressRequired(boolean z) {
            MaskedWalletRequest.this.zzlmi = z;
            return this;
        }

        @Deprecated
        public final Builder setUseMinimalBillingAddress(boolean z) {
            MaskedWalletRequest.this.zzlmj = z;
            return this;
        }
    }

    MaskedWalletRequest() {
        this.zzlmp = true;
        this.zzlmq = true;
    }

    MaskedWalletRequest(String str, boolean z, boolean z2, boolean z3, String str2, String str3, String str4, Cart cart, boolean z4, boolean z5, CountrySpecification[] countrySpecificationArr, boolean z6, boolean z7, ArrayList<CountrySpecification> arrayList, PaymentMethodTokenizationParameters paymentMethodTokenizationParameters, ArrayList<Integer> arrayList2, String str5) {
        this.zzlkd = str;
        this.zzlmh = z;
        this.zzlmi = z2;
        this.zzlmj = z3;
        this.zzlmk = str2;
        this.zzlju = str3;
        this.zzlml = str4;
        this.zzlkn = cart;
        this.zzlmm = z4;
        this.zzlmn = z5;
        this.zzlmo = countrySpecificationArr;
        this.zzlmp = z6;
        this.zzlmq = z7;
        this.zzlmr = arrayList;
        this.zzlms = paymentMethodTokenizationParameters;
        this.zzlmt = arrayList2;
        this.zzcyf = str5;
    }

    public static Builder newBuilder() {
        return new MaskedWalletRequest().new Builder();
    }

    public final boolean allowDebitCard() {
        return this.zzlmq;
    }

    public final boolean allowPrepaidCard() {
        return this.zzlmp;
    }

    public final ArrayList<Integer> getAllowedCardNetworks() {
        return this.zzlmt;
    }

    public final ArrayList<CountrySpecification> getAllowedCountrySpecificationsForShipping() {
        return this.zzlmr;
    }

    public final CountrySpecification[] getAllowedShippingCountrySpecifications() {
        return this.zzlmo;
    }

    public final Cart getCart() {
        return this.zzlkn;
    }

    public final String getCountryCode() {
        return this.zzcyf;
    }

    public final String getCurrencyCode() {
        return this.zzlju;
    }

    public final String getEstimatedTotalPrice() {
        return this.zzlmk;
    }

    public final String getMerchantName() {
        return this.zzlml;
    }

    public final String getMerchantTransactionId() {
        return this.zzlkd;
    }

    public final PaymentMethodTokenizationParameters getPaymentMethodTokenizationParameters() {
        return this.zzlms;
    }

    @Deprecated
    public final boolean isBillingAgreement() {
        return this.zzlmn;
    }

    public final boolean isPhoneNumberRequired() {
        return this.zzlmh;
    }

    public final boolean isShippingAddressRequired() {
        return this.zzlmi;
    }

    @Deprecated
    public final boolean useMinimalBillingAddress() {
        return this.zzlmj;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzlkd, false);
        zzbgo.zza(parcel, 3, this.zzlmh);
        zzbgo.zza(parcel, 4, this.zzlmi);
        zzbgo.zza(parcel, 5, this.zzlmj);
        zzbgo.zza(parcel, 6, this.zzlmk, false);
        zzbgo.zza(parcel, 7, this.zzlju, false);
        zzbgo.zza(parcel, 8, this.zzlml, false);
        zzbgo.zza(parcel, 9, (Parcelable) this.zzlkn, i, false);
        zzbgo.zza(parcel, 10, this.zzlmm);
        zzbgo.zza(parcel, 11, this.zzlmn);
        zzbgo.zza(parcel, 12, this.zzlmo, i, false);
        zzbgo.zza(parcel, 13, this.zzlmp);
        zzbgo.zza(parcel, 14, this.zzlmq);
        zzbgo.zzc(parcel, 15, this.zzlmr, false);
        zzbgo.zza(parcel, 16, (Parcelable) this.zzlms, i, false);
        zzbgo.zza(parcel, 17, (List<Integer>) this.zzlmt, false);
        zzbgo.zza(parcel, 18, this.zzcyf, false);
        zzbgo.zzai(parcel, zze);
    }
}
