package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.fido.fido2.api.common.Attachment;
import com.google.android.gms.fido.fido2.api.common.UserVerificationRequirement;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public class AuthenticatorSelectionCriteria extends zzbgl {
    public static final Parcelable.Creator<AuthenticatorSelectionCriteria> CREATOR = new zzg();
    private final Attachment zzheq;
    private final Boolean zzher;
    private final UserVerificationRequirement zzhes;

    public AuthenticatorSelectionCriteria(String str, Boolean bool, String str2) {
        if (str == null) {
            this.zzheq = null;
        } else {
            try {
                this.zzheq = Attachment.fromString(str);
            } catch (Attachment.UnsupportedAttachmentException e) {
                throw new IllegalArgumentException(e);
            }
        }
        this.zzher = bool;
        if (str2 == null) {
            this.zzhes = null;
            return;
        }
        try {
            this.zzhes = UserVerificationRequirement.fromString(str2);
        } catch (UserVerificationRequirement.UnsupportedUserVerificationRequirementException e2) {
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
        AuthenticatorSelectionCriteria authenticatorSelectionCriteria = (AuthenticatorSelectionCriteria) obj;
        return zzbg.equal(this.zzheq, authenticatorSelectionCriteria.zzheq) && zzbg.equal(this.zzher, authenticatorSelectionCriteria.zzher) && zzbg.equal(this.zzhes, authenticatorSelectionCriteria.zzhes);
    }

    public Attachment getAttachment() {
        return this.zzheq;
    }

    public String getAttachmentAsString() {
        Attachment attachment = this.zzheq;
        if (attachment == null) {
            return null;
        }
        return attachment.toString();
    }

    public Boolean getRequireResidentKey() {
        return this.zzher;
    }

    public UserVerificationRequirement getRequireUserVerification() {
        return this.zzhes;
    }

    public String getRequireUserVerificationAsString() {
        UserVerificationRequirement userVerificationRequirement = this.zzhes;
        if (userVerificationRequirement == null) {
            return null;
        }
        return userVerificationRequirement.toString();
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzheq, this.zzher, this.zzhes});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, getAttachmentAsString(), false);
        zzbgo.zza(parcel, 3, getRequireResidentKey(), false);
        zzbgo.zza(parcel, 4, getRequireUserVerificationAsString(), false);
        zzbgo.zzai(parcel, zze);
    }
}
