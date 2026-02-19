package com.google.android.gms.cast.framework.media;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzbei;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public class CastMediaOptions extends zzbgl {
    public static final Parcelable.Creator<CastMediaOptions> CREATOR = new zza();
    private static final zzbei zzeui = new zzbei("CastMediaOptions");
    private final String zzfdq;
    private final String zzfdr;
    private final zzb zzfds;
    private final NotificationOptions zzfdt;

    public static final class Builder {
        private String zzfdq = MediaIntentReceiver.class.getName();
        private String zzfdr;
        private NotificationOptions zzfdt = new NotificationOptions.Builder().build();
        private ImagePicker zzfdu;

        public final CastMediaOptions build() {
            ImagePicker imagePicker = this.zzfdu;
            return new CastMediaOptions(this.zzfdq, this.zzfdr, imagePicker == null ? null : imagePicker.zzafh().asBinder(), this.zzfdt);
        }

        public final Builder setExpandedControllerActivityClassName(String str) {
            this.zzfdr = str;
            return this;
        }

        public final Builder setImagePicker(ImagePicker imagePicker) {
            this.zzfdu = imagePicker;
            return this;
        }

        public final Builder setMediaIntentReceiverClassName(String str) {
            this.zzfdq = str;
            return this;
        }

        public final Builder setNotificationOptions(NotificationOptions notificationOptions) {
            this.zzfdt = notificationOptions;
            return this;
        }
    }

    CastMediaOptions(String str, String str2, IBinder iBinder, NotificationOptions notificationOptions) {
        zzb zzb;
        this.zzfdq = str;
        this.zzfdr = str2;
        if (iBinder == null) {
            zzb = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.framework.media.IImagePicker");
            zzb = queryLocalInterface instanceof zzb ? (zzb) queryLocalInterface : new zzc(iBinder);
        }
        this.zzfds = zzb;
        this.zzfdt = notificationOptions;
    }

    public String getExpandedControllerActivityClassName() {
        return this.zzfdr;
    }

    public ImagePicker getImagePicker() {
        zzb zzb = this.zzfds;
        if (zzb == null) {
            return null;
        }
        try {
            return (ImagePicker) zzn.zzy(zzb.zzafg());
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "getWrappedClientObject", zzb.class.getSimpleName());
            return null;
        }
    }

    public String getMediaIntentReceiverClassName() {
        return this.zzfdq;
    }

    public NotificationOptions getNotificationOptions() {
        return this.zzfdt;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, getMediaIntentReceiverClassName(), false);
        zzbgo.zza(parcel, 3, getExpandedControllerActivityClassName(), false);
        zzb zzb = this.zzfds;
        zzbgo.zza(parcel, 4, zzb == null ? null : zzb.asBinder(), false);
        zzbgo.zza(parcel, 5, (Parcelable) getNotificationOptions(), i, false);
        zzbgo.zzai(parcel, zze);
    }
}
