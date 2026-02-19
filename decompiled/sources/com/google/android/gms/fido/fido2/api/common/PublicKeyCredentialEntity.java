package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public class PublicKeyCredentialEntity extends zzbgl {
    public static final Parcelable.Creator<PublicKeyCredentialEntity> CREATOR = new zzm();
    private final String mName;
    private final String zzbzd;
    private final String zzhfl;

    public PublicKeyCredentialEntity(String str, String str2, String str3) {
        this.zzbzd = (String) zzbq.checkNotNull(str);
        this.mName = str2;
        this.zzhfl = str3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PublicKeyCredentialEntity publicKeyCredentialEntity = (PublicKeyCredentialEntity) obj;
        if (!this.zzbzd.equals(publicKeyCredentialEntity.zzbzd)) {
            return false;
        }
        String str = this.mName;
        if (str == null) {
            if (publicKeyCredentialEntity.mName != null) {
                return false;
            }
        } else if (!str.equals(publicKeyCredentialEntity.mName)) {
            return false;
        }
        String str2 = this.zzhfl;
        String str3 = publicKeyCredentialEntity.zzhfl;
        if (str2 == null) {
            if (str3 != null) {
                return false;
            }
        } else if (!str2.equals(str3)) {
            return false;
        }
        return true;
    }

    public String getIcon() {
        return this.zzhfl;
    }

    public String getId() {
        return this.zzbzd;
    }

    public String getName() {
        return this.mName;
    }

    public int hashCode() {
        int hashCode = (this.zzbzd.hashCode() + 31) * 31;
        String str = this.mName;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.zzhfl;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode2 + i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, getId(), false);
        zzbgo.zza(parcel, 3, getName(), false);
        zzbgo.zza(parcel, 4, getIcon(), false);
        zzbgo.zzai(parcel, zze);
    }
}
