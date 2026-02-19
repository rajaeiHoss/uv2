package com.google.android.gms.fido.fido2.api.common;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbgq;
import java.util.Arrays;

public class BrowserPublicKeyCredentialRequestOptions extends BrowserRequestOptions {
    public static final Parcelable.Creator<BrowserPublicKeyCredentialRequestOptions> CREATOR = new zzi();
    private final Uri zzheu;
    private final PublicKeyCredentialRequestOptions zzhew;

    public static final class Builder {
        private Uri zzheu;
        private PublicKeyCredentialRequestOptions zzhex;

        public final BrowserPublicKeyCredentialRequestOptions build() {
            return new BrowserPublicKeyCredentialRequestOptions(this.zzhex, this.zzheu);
        }

        public final Builder setOrigin(Uri uri) {
            this.zzheu = uri;
            return this;
        }

        public final Builder setRequestOptions(PublicKeyCredentialRequestOptions publicKeyCredentialRequestOptions) {
            this.zzhex = publicKeyCredentialRequestOptions;
            return this;
        }
    }

    BrowserPublicKeyCredentialRequestOptions(PublicKeyCredentialRequestOptions publicKeyCredentialRequestOptions, Uri uri) {
        this.zzhew = (PublicKeyCredentialRequestOptions) zzbq.checkNotNull(publicKeyCredentialRequestOptions);
        zzbq.checkNotNull(uri);
        boolean z = true;
        zzbq.checkArgument(uri.getScheme() != null, "origin scheme must be non-empty");
        zzbq.checkArgument(uri.getAuthority() == null ? false : z, "origin authority must be non-empty");
        this.zzheu = uri;
    }

    public static BrowserPublicKeyCredentialRequestOptions deserializeFromBytes(byte[] bArr) {
        return (BrowserPublicKeyCredentialRequestOptions) zzbgq.zza(bArr, CREATOR);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            BrowserPublicKeyCredentialRequestOptions browserPublicKeyCredentialRequestOptions = (BrowserPublicKeyCredentialRequestOptions) obj;
            return zzbg.equal(this.zzhew, browserPublicKeyCredentialRequestOptions.zzhew) && zzbg.equal(this.zzheu, browserPublicKeyCredentialRequestOptions.zzheu);
        }
        return false;
    }

    public byte[] getChallenge() {
        return this.zzhew.getChallenge();
    }

    public Uri getOrigin() {
        return this.zzheu;
    }

    public PublicKeyCredentialRequestOptions getPublicKeyCredentialRequestOptions() {
        return this.zzhew;
    }

    public Integer getRequestId() {
        return this.zzhew.getRequestId();
    }

    public Double getTimeoutSeconds() {
        return this.zzhew.getTimeoutSeconds();
    }

    public TokenBindingIdValue getTokenBindingIdValue() {
        return this.zzhew.getTokenBindingIdValue();
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzhew, this.zzheu});
    }

    public byte[] serializeToBytes() {
        return zzbgq.zza(this);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) getPublicKeyCredentialRequestOptions(), i, false);
        zzbgo.zza(parcel, 3, (Parcelable) getOrigin(), i, false);
        zzbgo.zzai(parcel, zze);
    }
}
