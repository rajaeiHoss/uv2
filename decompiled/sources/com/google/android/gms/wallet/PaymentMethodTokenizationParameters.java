package com.google.android.gms.wallet;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class PaymentMethodTokenizationParameters extends zzbgl {
    public static final Parcelable.Creator<PaymentMethodTokenizationParameters> CREATOR = new zzah();
    Bundle zzefr = new Bundle();
    int zzlnl;

    public final class Builder {
        private Builder() {
        }

        public final Builder addParameter(String str, String str2) {
            zzbq.zzh(str, "Tokenization parameter name must not be empty");
            zzbq.zzh(str2, "Tokenization parameter value must not be empty");
            PaymentMethodTokenizationParameters.this.zzefr.putString(str, str2);
            return this;
        }

        public final PaymentMethodTokenizationParameters build() {
            return PaymentMethodTokenizationParameters.this;
        }

        public final Builder setPaymentMethodTokenizationType(int i) {
            PaymentMethodTokenizationParameters.this.zzlnl = i;
            return this;
        }
    }

    private PaymentMethodTokenizationParameters() {
    }

    PaymentMethodTokenizationParameters(int i, Bundle bundle) {
        this.zzlnl = i;
        this.zzefr = bundle;
    }

    public static Builder newBuilder() {
        return new PaymentMethodTokenizationParameters().new Builder();
    }

    public final Bundle getParameters() {
        return new Bundle(this.zzefr);
    }

    public final int getPaymentMethodTokenizationType() {
        return this.zzlnl;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.zzlnl);
        zzbgo.zza(parcel, 3, this.zzefr, false);
        zzbgo.zzai(parcel, zze);
    }
}
