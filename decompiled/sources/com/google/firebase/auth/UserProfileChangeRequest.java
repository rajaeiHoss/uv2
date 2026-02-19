package com.google.firebase.auth;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public class UserProfileChangeRequest extends zzbgl {
    public static final Parcelable.Creator<UserProfileChangeRequest> CREATOR = new zzw();
    private String zzemi;
    private String zzmqb;
    private boolean zzmqc;
    private boolean zzmqd;
    private Uri zzmqe;

    public static class Builder {
        private String zzemi;
        private boolean zzmqc;
        private boolean zzmqd;
        private Uri zzmqe;

        public UserProfileChangeRequest build() {
            String str = this.zzemi;
            Uri uri = this.zzmqe;
            return new UserProfileChangeRequest(str, uri == null ? null : uri.toString(), this.zzmqc, this.zzmqd);
        }

        public Builder setDisplayName(String str) {
            if (str == null) {
                this.zzmqc = true;
            } else {
                this.zzemi = str;
            }
            return this;
        }

        public Builder setPhotoUri(Uri uri) {
            if (uri == null) {
                this.zzmqd = true;
            } else {
                this.zzmqe = uri;
            }
            return this;
        }
    }

    UserProfileChangeRequest(String str, String str2, boolean z, boolean z2) {
        this.zzemi = str;
        this.zzmqb = str2;
        this.zzmqc = z;
        this.zzmqd = z2;
        this.zzmqe = TextUtils.isEmpty(str2) ? null : Uri.parse(str2);
    }

    public String getDisplayName() {
        return this.zzemi;
    }

    public Uri getPhotoUri() {
        return this.zzmqe;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, getDisplayName(), false);
        zzbgo.zza(parcel, 3, this.zzmqb, false);
        zzbgo.zza(parcel, 4, this.zzmqc);
        zzbgo.zza(parcel, 5, this.zzmqd);
        zzbgo.zzai(parcel, zze);
    }
}
