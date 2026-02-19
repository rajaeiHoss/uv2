package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public class PublicKeyCredentialUserEntity extends zzbgl {
    public static final Parcelable.Creator<PublicKeyCredentialUserEntity> CREATOR = new zzq();
    private final String mName;
    private final String zzbzd;
    private final String zzemi;
    private final String zzhfl;

    public PublicKeyCredentialUserEntity(String str, String str2, String str3, String str4) {
        this.zzbzd = str;
        this.mName = str2;
        this.zzhfl = str3;
        this.zzemi = str4;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PublicKeyCredentialUserEntity publicKeyCredentialUserEntity = (PublicKeyCredentialUserEntity) obj;
        String str = this.zzbzd;
        if (str == null) {
            if (publicKeyCredentialUserEntity.zzbzd != null) {
                return false;
            }
        } else if (!str.equals(publicKeyCredentialUserEntity.zzbzd)) {
            return false;
        }
        String str2 = this.mName;
        if (str2 == null) {
            if (publicKeyCredentialUserEntity.mName != null) {
                return false;
            }
        } else if (!str2.equals(publicKeyCredentialUserEntity.mName)) {
            return false;
        }
        String str3 = this.zzhfl;
        if (str3 == null) {
            if (publicKeyCredentialUserEntity.zzhfl != null) {
                return false;
            }
        } else if (!str3.equals(publicKeyCredentialUserEntity.zzhfl)) {
            return false;
        }
        String str4 = this.zzemi;
        String str5 = publicKeyCredentialUserEntity.zzemi;
        if (str4 == null) {
            if (str5 != null) {
                return false;
            }
        } else if (!str4.equals(str5)) {
            return false;
        }
        return true;
    }

    public String getDisplayName() {
        return this.zzemi;
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
        String str = this.zzbzd;
        int i = 0;
        int hashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
        String str2 = this.mName;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.zzhfl;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.zzemi;
        if (str4 != null) {
            i = str4.hashCode();
        }
        return hashCode3 + i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, getId(), false);
        zzbgo.zza(parcel, 3, getName(), false);
        zzbgo.zza(parcel, 4, getIcon(), false);
        zzbgo.zza(parcel, 5, getDisplayName(), false);
        zzbgo.zzai(parcel, zze);
    }
}
