package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class CardRequirements extends zzbgl {
    public static final Parcelable.Creator<CardRequirements> CREATOR = new zze();
    ArrayList<Integer> zzljo;
    boolean zzljp;
    boolean zzljq;
    int zzljr;

    public final class Builder {
        private Builder() {
        }

        public final Builder addAllowedCardNetwork(int i) {
            if (CardRequirements.this.zzljo == null) {
                CardRequirements.this.zzljo = new ArrayList<>();
            }
            CardRequirements.this.zzljo.add(Integer.valueOf(i));
            return this;
        }

        public final Builder addAllowedCardNetworks(Collection<Integer> collection) {
            zzbq.checkArgument(collection != null && !collection.isEmpty(), "allowedCardNetworks can't be null or empty! You must provide a valid value from WalletConstants.CardNetwork.");
            if (CardRequirements.this.zzljo == null) {
                CardRequirements.this.zzljo = new ArrayList<>();
            }
            CardRequirements.this.zzljo.addAll(collection);
            return this;
        }

        public final CardRequirements build() {
            zzbq.checkNotNull(CardRequirements.this.zzljo, "Allowed card networks must be non-empty! You can set it through addAllowedCardNetwork() or addAllowedCardNetworks() in the CardRequirements Builder.");
            return CardRequirements.this;
        }

        public final Builder setAllowPrepaidCards(boolean z) {
            CardRequirements.this.zzljp = z;
            return this;
        }

        public final Builder setBillingAddressFormat(int i) {
            CardRequirements.this.zzljr = i;
            return this;
        }

        public final Builder setBillingAddressRequired(boolean z) {
            CardRequirements.this.zzljq = z;
            return this;
        }
    }

    private CardRequirements() {
        this.zzljp = true;
    }

    CardRequirements(ArrayList<Integer> arrayList, boolean z, boolean z2, int i) {
        this.zzljo = arrayList;
        this.zzljp = z;
        this.zzljq = z2;
        this.zzljr = i;
    }

    public static Builder newBuilder() {
        CardRequirements cardRequirements = new CardRequirements();
        return cardRequirements.new Builder();
    }

    public final boolean allowPrepaidCards() {
        return this.zzljp;
    }

    public final ArrayList<Integer> getAllowedCardNetworks() {
        return this.zzljo;
    }

    public final int getBillingAddressFormat() {
        return this.zzljr;
    }

    public final boolean isBillingAddressRequired() {
        return this.zzljq;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (List<Integer>) this.zzljo, false);
        zzbgo.zza(parcel, 2, this.zzljp);
        zzbgo.zza(parcel, 3, this.zzljq);
        zzbgo.zzc(parcel, 4, this.zzljr);
        zzbgo.zzai(parcel, zze);
    }
}
