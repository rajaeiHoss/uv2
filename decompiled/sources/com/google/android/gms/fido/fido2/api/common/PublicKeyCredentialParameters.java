package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.fido.fido2.api.common.AlgorithmIdentifier;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialType;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public class PublicKeyCredentialParameters extends zzbgl {
    public static final Parcelable.Creator<PublicKeyCredentialParameters> CREATOR = new zzn();
    private final PublicKeyCredentialType zzhfj;
    private final AlgorithmIdentifier zzhfm;

    public PublicKeyCredentialParameters(String str, String str2) {
        zzbq.checkNotNull(str);
        try {
            this.zzhfj = PublicKeyCredentialType.fromString(str);
            zzbq.checkNotNull(str2);
            try {
                this.zzhfm = AlgorithmIdentifier.fromString(str2);
            } catch (AlgorithmIdentifier.UnsupportedAlgorithmIdentifierException e) {
                throw new IllegalArgumentException(e);
            }
        } catch (PublicKeyCredentialType.UnsupportedPublicKeyCredTypeException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PublicKeyCredentialParameters publicKeyCredentialParameters = (PublicKeyCredentialParameters) obj;
        return this.zzhfj.equals(publicKeyCredentialParameters.zzhfj) && this.zzhfm.equals(publicKeyCredentialParameters.zzhfm);
    }

    public AlgorithmIdentifier getAlgorithm() {
        return this.zzhfm;
    }

    public PublicKeyCredentialType getType() {
        return this.zzhfj;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzhfj, this.zzhfm});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzhfj.toString(), false);
        zzbgo.zza(parcel, 3, this.zzhfm.toString(), false);
        zzbgo.zzai(parcel, zze);
    }
}
