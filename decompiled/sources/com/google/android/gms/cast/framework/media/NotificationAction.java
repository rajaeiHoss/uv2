package com.google.android.gms.cast.framework.media;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public class NotificationAction extends zzbgl {
    public static final Parcelable.Creator<NotificationAction> CREATOR = new zzk();
    private final String zzdxe;
    private final int zzfea;
    private final String zzfeb;

    public static final class Builder {
        private String zzdxe;
        private int zzfea;
        private String zzfeb;

        public final NotificationAction build() {
            return new NotificationAction(this.zzdxe, this.zzfea, this.zzfeb);
        }

        public final Builder setAction(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.zzdxe = str;
                return this;
            }
            throw new IllegalArgumentException("action cannot be null or an empty string.");
        }

        public final Builder setContentDescription(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.zzfeb = str;
                return this;
            }
            throw new IllegalArgumentException("contentDescription cannot be null  or an empty string.");
        }

        public final Builder setIconResId(int i) {
            this.zzfea = i;
            return this;
        }
    }

    NotificationAction(String str, int i, String str2) {
        this.zzdxe = str;
        this.zzfea = i;
        this.zzfeb = str2;
    }

    public String getAction() {
        return this.zzdxe;
    }

    public String getContentDescription() {
        return this.zzfeb;
    }

    public int getIconResId() {
        return this.zzfea;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, getAction(), false);
        zzbgo.zzc(parcel, 3, getIconResId());
        zzbgo.zza(parcel, 4, getContentDescription(), false);
        zzbgo.zzai(parcel, zze);
    }
}
