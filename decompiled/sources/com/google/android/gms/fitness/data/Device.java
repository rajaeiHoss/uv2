package com.google.android.gms.fitness.data;

import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.Settings;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbwl;
import com.google.android.gms.internal.zzcbx;
import java.util.Arrays;

public final class Device extends zzbgl {
    public static final Parcelable.Creator<Device> CREATOR = new zzo();
    public static final int TYPE_CHEST_STRAP = 4;
    public static final int TYPE_HEAD_MOUNTED = 6;
    public static final int TYPE_PHONE = 1;
    public static final int TYPE_SCALE = 5;
    public static final int TYPE_TABLET = 2;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_WATCH = 3;
    private final int type;
    private final String zzbeg;
    private final String zzhju;
    private final String zzhjv;
    private final int zzhjw;

    public Device(String str, String str2, String str3, int i) {
        this(str, str2, "", str3, i, 0);
    }

    public Device(String str, String str2, String str3, int i, int i2) {
        this.zzhju = (String) zzbq.checkNotNull(str);
        this.zzbeg = (String) zzbq.checkNotNull(str2);
        if (str3 != null) {
            this.zzhjv = str3;
            this.type = i;
            this.zzhjw = i2;
            return;
        }
        throw new IllegalStateException("Device UID is null.");
    }

    @Deprecated
    private Device(String str, String str2, String str3, String str4, int i, int i2) {
        this(str, str2, str4, i, i2);
    }

    public static Device getLocalDevice(Context context) {
        int zzdi = zzbwl.zzdi(context);
        return new Device(Build.MANUFACTURER, Build.MODEL, Build.VERSION.RELEASE, Settings.Secure.getString(context.getContentResolver(), "android_id"), zzdi, 2);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Device)) {
            return false;
        }
        Device device = (Device) obj;
        return zzbg.equal(this.zzhju, device.zzhju) && zzbg.equal(this.zzbeg, device.zzbeg) && zzbg.equal(this.zzhjv, device.zzhjv) && this.type == device.type && this.zzhjw == device.zzhjw;
    }

    public final String getManufacturer() {
        return this.zzhju;
    }

    public final String getModel() {
        return this.zzbeg;
    }

    /* access modifiers changed from: package-private */
    public final String getStreamIdentifier() {
        return String.format("%s:%s:%s", new Object[]{this.zzhju, this.zzbeg, this.zzhjv});
    }

    public final int getType() {
        return this.type;
    }

    public final String getUid() {
        return this.zzhjv;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzhju, this.zzbeg, this.zzhjv, Integer.valueOf(this.type)});
    }

    public final String toString() {
        return String.format("Device{%s:%s:%s}", new Object[]{getStreamIdentifier(), Integer.valueOf(this.type), Integer.valueOf(this.zzhjw)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, getManufacturer(), false);
        zzbgo.zza(parcel, 2, getModel(), false);
        zzbgo.zza(parcel, 4, this.zzhjw == 1 ? this.zzhjv : zzcbx.zzhw(this.zzhjv), false);
        zzbgo.zzc(parcel, 5, getType());
        zzbgo.zzc(parcel, 6, this.zzhjw);
        zzbgo.zzai(parcel, zze);
    }
}
