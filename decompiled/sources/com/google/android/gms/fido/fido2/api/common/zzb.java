package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.Attachment;

final class zzb implements Parcelable.Creator<Attachment> {
    zzb() {
    }

    private static Attachment zzh(Parcel parcel) {
        try {
            return Attachment.fromString(parcel.readString());
        } catch (Attachment.UnsupportedAttachmentException e) {
            throw new RuntimeException(e);
        }
    }

    public final /* synthetic */ Attachment createFromParcel(Parcel parcel) {
        return zzh(parcel);
    }

    public final /* synthetic */ Attachment[] newArray(int i) {
        return new Attachment[i];
    }
}
