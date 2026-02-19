package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;

public enum Attachment implements Parcelable {
    PLATFORM("platform"),
    CROSS_PLATFORM("cross-platform");
    
    public static final Parcelable.Creator<Attachment> CREATOR = new zzb();
    private final String mValue;

    public static class UnsupportedAttachmentException extends Exception {
        public UnsupportedAttachmentException(String str) {
            super(String.format("Attachment %s not supported", new Object[]{str}));
        }
    }

    private Attachment(String str) {
        this.mValue = str;
    }

    public static Attachment fromString(String str) throws UnsupportedAttachmentException {
        for (Attachment attachment : values()) {
            if (str.equals(attachment.mValue)) {
                return attachment;
            }
        }
        throw new UnsupportedAttachmentException(str);
    }

    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return this.mValue;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mValue);
    }
}
