package com.google.android.gms.fido.fido2.api.common;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbgq;
import java.util.Arrays;

public class BrowserMakeCredentialOptions extends BrowserRequestOptions {
    public static final Parcelable.Creator<BrowserMakeCredentialOptions> CREATOR = new zzh();
    private final MakeCredentialOptions zzhet;
    private final Uri zzheu;

    public static final class Builder {
        private Uri zzheu;
        private MakeCredentialOptions zzhev;

        public final BrowserMakeCredentialOptions build() {
            return new BrowserMakeCredentialOptions(this.zzhev, this.zzheu);
        }

        public final Builder setMakeCredentialOptions(MakeCredentialOptions makeCredentialOptions) {
            this.zzhev = makeCredentialOptions;
            return this;
        }

        public final Builder setOrigin(Uri uri) {
            this.zzheu = uri;
            return this;
        }
    }

    BrowserMakeCredentialOptions(MakeCredentialOptions makeCredentialOptions, Uri uri) {
        this.zzhet = (MakeCredentialOptions) zzbq.checkNotNull(makeCredentialOptions);
        zzbq.checkNotNull(uri);
        boolean z = true;
        zzbq.checkArgument(uri.getScheme() != null, "origin scheme must be non-empty");
        zzbq.checkArgument(uri.getAuthority() == null ? false : z, "origin authority must be non-empty");
        this.zzheu = uri;
    }

    public static BrowserMakeCredentialOptions deserializeFromBytes(byte[] bArr) {
        return (BrowserMakeCredentialOptions) zzbgq.zza(bArr, CREATOR);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            BrowserMakeCredentialOptions browserMakeCredentialOptions = (BrowserMakeCredentialOptions) obj;
            return zzbg.equal(this.zzhet, browserMakeCredentialOptions.zzhet) && zzbg.equal(this.zzheu, browserMakeCredentialOptions.zzheu);
        }
        return false;
    }

    public byte[] getChallenge() {
        return this.zzhet.getChallenge();
    }

    public MakeCredentialOptions getMakeCredentialOptions() {
        return this.zzhet;
    }

    public Uri getOrigin() {
        return this.zzheu;
    }

    public Integer getRequestId() {
        return this.zzhet.getRequestId();
    }

    public Double getTimeoutSeconds() {
        return this.zzhet.getTimeoutSeconds();
    }

    public TokenBindingIdValue getTokenBindingIdValue() {
        return this.zzhet.getTokenBindingIdValue();
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzhet, this.zzheu});
    }

    public byte[] serializeToBytes() {
        return zzbgq.zza(this);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) getMakeCredentialOptions(), i, false);
        zzbgo.zza(parcel, 3, (Parcelable) getOrigin(), i, false);
        zzbgo.zzai(parcel, zze);
    }
}
