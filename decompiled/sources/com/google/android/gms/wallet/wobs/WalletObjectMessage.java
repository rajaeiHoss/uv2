package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class WalletObjectMessage extends zzbgl {
    public static final Parcelable.Creator<WalletObjectMessage> CREATOR = new zzn();
    String body;
    String zzlqg;
    TimeInterval zzlqj;
    UriData zzlqk;
    UriData zzlql;

    public final class Builder {
        private Builder() {
        }

        public final WalletObjectMessage build() {
            return WalletObjectMessage.this;
        }

        public final Builder setActionUri(UriData uriData) {
            WalletObjectMessage.this.zzlqk = uriData;
            return this;
        }

        public final Builder setBody(String str) {
            WalletObjectMessage.this.body = str;
            return this;
        }

        public final Builder setDisplayInterval(TimeInterval timeInterval) {
            WalletObjectMessage.this.zzlqj = timeInterval;
            return this;
        }

        public final Builder setHeader(String str) {
            WalletObjectMessage.this.zzlqg = str;
            return this;
        }

        public final Builder setImageUri(UriData uriData) {
            WalletObjectMessage.this.zzlql = uriData;
            return this;
        }
    }

    WalletObjectMessage() {
    }

    WalletObjectMessage(String str, String str2, TimeInterval timeInterval, UriData uriData, UriData uriData2) {
        this.zzlqg = str;
        this.body = str2;
        this.zzlqj = timeInterval;
        this.zzlqk = uriData;
        this.zzlql = uriData2;
    }

    public static Builder newBuilder() {
        return new WalletObjectMessage().new Builder();
    }

    public final UriData getActionUri() {
        return this.zzlqk;
    }

    public final String getBody() {
        return this.body;
    }

    public final TimeInterval getDisplayInterval() {
        return this.zzlqj;
    }

    public final String getHeader() {
        return this.zzlqg;
    }

    public final UriData getImageUri() {
        return this.zzlql;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzlqg, false);
        zzbgo.zza(parcel, 3, this.body, false);
        zzbgo.zza(parcel, 4, (Parcelable) this.zzlqj, i, false);
        zzbgo.zza(parcel, 5, (Parcelable) this.zzlqk, i, false);
        zzbgo.zza(parcel, 6, (Parcelable) this.zzlql, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
