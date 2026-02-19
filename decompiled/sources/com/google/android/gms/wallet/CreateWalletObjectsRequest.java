package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class CreateWalletObjectsRequest extends zzbgl {
    public static final Parcelable.Creator<CreateWalletObjectsRequest> CREATOR = new zzj();
    public static final int REQUEST_IMMEDIATE_SAVE = 1;
    public static final int SHOW_SAVE_PROMPT = 0;
    LoyaltyWalletObject zzljx;
    OfferWalletObject zzljy;
    GiftCardWalletObject zzljz;
    int zzlka;

    public final class Builder {
        private Builder() {
        }

        public final CreateWalletObjectsRequest build() {
            boolean z = false;
            if ((CreateWalletObjectsRequest.this.zzljz == null ? 0 : 1) + (CreateWalletObjectsRequest.this.zzljx == null ? 0 : 1) + (CreateWalletObjectsRequest.this.zzljy == null ? 0 : 1) == 1) {
                z = true;
            }
            zzbq.zza(z, (Object) "CreateWalletObjectsRequest must have exactly one Wallet Object");
            return CreateWalletObjectsRequest.this;
        }

        public final Builder setCreateMode(int i) {
            CreateWalletObjectsRequest.this.zzlka = i;
            return this;
        }

        public final Builder setGiftCardWalletObject(GiftCardWalletObject giftCardWalletObject) {
            CreateWalletObjectsRequest.this.zzljz = giftCardWalletObject;
            return this;
        }

        public final Builder setLoyaltyWalletObject(LoyaltyWalletObject loyaltyWalletObject) {
            CreateWalletObjectsRequest.this.zzljx = loyaltyWalletObject;
            return this;
        }

        public final Builder setOfferWalletObject(OfferWalletObject offerWalletObject) {
            CreateWalletObjectsRequest.this.zzljy = offerWalletObject;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface CreateMode {
    }

    CreateWalletObjectsRequest() {
    }

    @Deprecated
    public CreateWalletObjectsRequest(GiftCardWalletObject giftCardWalletObject) {
        this.zzljz = giftCardWalletObject;
    }

    @Deprecated
    public CreateWalletObjectsRequest(LoyaltyWalletObject loyaltyWalletObject) {
        this.zzljx = loyaltyWalletObject;
    }

    CreateWalletObjectsRequest(LoyaltyWalletObject loyaltyWalletObject, OfferWalletObject offerWalletObject, GiftCardWalletObject giftCardWalletObject, int i) {
        this.zzljx = loyaltyWalletObject;
        this.zzljy = offerWalletObject;
        this.zzljz = giftCardWalletObject;
        this.zzlka = i;
    }

    @Deprecated
    public CreateWalletObjectsRequest(OfferWalletObject offerWalletObject) {
        this.zzljy = offerWalletObject;
    }

    public static Builder newBuilder() {
        CreateWalletObjectsRequest createWalletObjectsRequest = new CreateWalletObjectsRequest();
        return createWalletObjectsRequest.new Builder();
    }

    public final int getCreateMode() {
        return this.zzlka;
    }

    public final GiftCardWalletObject getGiftCardWalletObject() {
        return this.zzljz;
    }

    public final LoyaltyWalletObject getLoyaltyWalletObject() {
        return this.zzljx;
    }

    public final OfferWalletObject getOfferWalletObject() {
        return this.zzljy;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzljx, i, false);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzljy, i, false);
        zzbgo.zza(parcel, 4, (Parcelable) this.zzljz, i, false);
        zzbgo.zzc(parcel, 5, this.zzlka);
        zzbgo.zzai(parcel, zze);
    }
}
