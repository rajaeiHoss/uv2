package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class PaymentDataRequest extends zzbgl {
    public static final Parcelable.Creator<PaymentDataRequest> CREATOR = new zzae();
    ArrayList<Integer> zzlld;
    boolean zzlnc;
    boolean zzlnd;
    CardRequirements zzlne;
    boolean zzlnf;
    ShippingAddressRequirements zzlng;
    PaymentMethodTokenizationParameters zzlnh;
    TransactionInfo zzlni;
    boolean zzlnj;

    public final class Builder {
        private Builder() {
        }

        public final Builder addAllowedPaymentMethod(int i) {
            if (PaymentDataRequest.this.zzlld == null) {
                PaymentDataRequest.this.zzlld = new ArrayList<>();
            }
            PaymentDataRequest.this.zzlld.add(Integer.valueOf(i));
            return this;
        }

        public final Builder addAllowedPaymentMethods(Collection<Integer> collection) {
            zzbq.checkArgument(collection != null && !collection.isEmpty(), "allowedPaymentMethods can't be null or empty!");
            if (PaymentDataRequest.this.zzlld == null) {
                PaymentDataRequest.this.zzlld = new ArrayList<>();
            }
            PaymentDataRequest.this.zzlld.addAll(collection);
            return this;
        }

        public final PaymentDataRequest build() {
            zzbq.checkNotNull(PaymentDataRequest.this.zzlld, "Allowed payment methods must be set! You can set it through addAllowedPaymentMethod() or addAllowedPaymentMethods() in the PaymentDataRequest Builder.");
            zzbq.checkNotNull(PaymentDataRequest.this.zzlne, "Card requirements must be set!");
            if (PaymentDataRequest.this.zzlnh != null) {
                zzbq.checkNotNull(PaymentDataRequest.this.zzlni, "Transaction info must be set if paymentMethodTokenizationParameters is set!");
            }
            return PaymentDataRequest.this;
        }

        public final Builder setCardRequirements(CardRequirements cardRequirements) {
            PaymentDataRequest.this.zzlne = cardRequirements;
            return this;
        }

        public final Builder setEmailRequired(boolean z) {
            PaymentDataRequest.this.zzlnc = z;
            return this;
        }

        public final Builder setPaymentMethodTokenizationParameters(PaymentMethodTokenizationParameters paymentMethodTokenizationParameters) {
            PaymentDataRequest.this.zzlnh = paymentMethodTokenizationParameters;
            return this;
        }

        public final Builder setPhoneNumberRequired(boolean z) {
            PaymentDataRequest.this.zzlnd = z;
            return this;
        }

        public final Builder setShippingAddressRequired(boolean z) {
            PaymentDataRequest.this.zzlnf = z;
            return this;
        }

        public final Builder setShippingAddressRequirements(ShippingAddressRequirements shippingAddressRequirements) {
            PaymentDataRequest.this.zzlng = shippingAddressRequirements;
            return this;
        }

        public final Builder setTransactionInfo(TransactionInfo transactionInfo) {
            PaymentDataRequest.this.zzlni = transactionInfo;
            return this;
        }

        public final Builder setUiRequired(boolean z) {
            PaymentDataRequest.this.zzlnj = z;
            return this;
        }
    }

    private PaymentDataRequest() {
        this.zzlnj = true;
    }

    PaymentDataRequest(boolean z, boolean z2, CardRequirements cardRequirements, boolean z3, ShippingAddressRequirements shippingAddressRequirements, ArrayList<Integer> arrayList, PaymentMethodTokenizationParameters paymentMethodTokenizationParameters, TransactionInfo transactionInfo, boolean z4) {
        this.zzlnc = z;
        this.zzlnd = z2;
        this.zzlne = cardRequirements;
        this.zzlnf = z3;
        this.zzlng = shippingAddressRequirements;
        this.zzlld = arrayList;
        this.zzlnh = paymentMethodTokenizationParameters;
        this.zzlni = transactionInfo;
        this.zzlnj = z4;
    }

    public static Builder newBuilder() {
        PaymentDataRequest paymentDataRequest = new PaymentDataRequest();
        return paymentDataRequest.new Builder();
    }

    public final ArrayList<Integer> getAllowedPaymentMethods() {
        return this.zzlld;
    }

    public final CardRequirements getCardRequirements() {
        return this.zzlne;
    }

    public final PaymentMethodTokenizationParameters getPaymentMethodTokenizationParameters() {
        return this.zzlnh;
    }

    public final ShippingAddressRequirements getShippingAddressRequirements() {
        return this.zzlng;
    }

    public final TransactionInfo getTransactionInfo() {
        return this.zzlni;
    }

    public final boolean isEmailRequired() {
        return this.zzlnc;
    }

    public final boolean isPhoneNumberRequired() {
        return this.zzlnd;
    }

    public final boolean isShippingAddressRequired() {
        return this.zzlnf;
    }

    public final boolean isUiRequired() {
        return this.zzlnj;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzlnc);
        zzbgo.zza(parcel, 2, this.zzlnd);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzlne, i, false);
        zzbgo.zza(parcel, 4, this.zzlnf);
        zzbgo.zza(parcel, 5, (Parcelable) this.zzlng, i, false);
        zzbgo.zza(parcel, 6, (List<Integer>) this.zzlld, false);
        zzbgo.zza(parcel, 7, (Parcelable) this.zzlnh, i, false);
        zzbgo.zza(parcel, 8, (Parcelable) this.zzlni, i, false);
        zzbgo.zza(parcel, 9, this.zzlnj);
        zzbgo.zzai(parcel, zze);
    }
}
