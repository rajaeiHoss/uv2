package com.google.android.gms.cast;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzbdw;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CastDevice extends zzbgl implements ReflectedParcelable {
    public static final int CAPABILITY_AUDIO_IN = 8;
    public static final int CAPABILITY_AUDIO_OUT = 4;
    public static final int CAPABILITY_MULTIZONE_GROUP = 32;
    public static final int CAPABILITY_VIDEO_IN = 2;
    public static final int CAPABILITY_VIDEO_OUT = 1;
    public static final Parcelable.Creator<CastDevice> CREATOR = new zzn();
    private int zzcfl;
    private String zzetq;
    private String zzetr;
    private Inet4Address zzets;
    private String zzett;
    private String zzetu;
    private String zzetv;
    private int zzetw;
    private List<WebImage> zzetx;
    private int zzety;
    private String zzetz;
    private String zzeua;
    private int zzeub;
    private String zzeuc;
    private byte[] zzeud;

    CastDevice(String str, String str2, String str3, String str4, String str5, int i, List<WebImage> list, int i2, int i3, String str6, String str7, int i4, String str8, byte[] bArr) {
        this.zzetq = zzfj(str);
        String zzfj = zzfj(str2);
        this.zzetr = zzfj;
        if (!TextUtils.isEmpty(zzfj)) {
            try {
                InetAddress byName = InetAddress.getByName(this.zzetr);
                if (byName instanceof Inet4Address) {
                    this.zzets = (Inet4Address) byName;
                }
            } catch (UnknownHostException e) {
                String str9 = this.zzetr;
                String message = e.getMessage();
                StringBuilder sb = new StringBuilder(String.valueOf(str9).length() + 48 + String.valueOf(message).length());
                sb.append("Unable to convert host address (");
                sb.append(str9);
                sb.append(") to ipaddress: ");
                sb.append(message);
                Log.i("CastDevice", sb.toString());
            }
        }
        this.zzett = zzfj(str3);
        this.zzetu = zzfj(str4);
        this.zzetv = zzfj(str5);
        this.zzetw = i;
        this.zzetx = list != null ? list : new ArrayList<>();
        this.zzety = i2;
        this.zzcfl = i3;
        this.zzetz = zzfj(str6);
        this.zzeua = str7;
        this.zzeub = i4;
        this.zzeuc = str8;
        this.zzeud = bArr;
    }

    public static CastDevice getFromBundle(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        bundle.setClassLoader(CastDevice.class.getClassLoader());
        return (CastDevice) bundle.getParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE");
    }

    private static String zzfj(String str) {
        return str == null ? "" : str;
    }

    public boolean equals(Object obj) {
        byte[] bArr;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CastDevice)) {
            return false;
        }
        CastDevice castDevice = (CastDevice) obj;
        String str = this.zzetq;
        return str == null ? castDevice.zzetq == null : zzbdw.zza(str, castDevice.zzetq) && zzbdw.zza(this.zzets, castDevice.zzets) && zzbdw.zza(this.zzetu, castDevice.zzetu) && zzbdw.zza(this.zzett, castDevice.zzett) && zzbdw.zza(this.zzetv, castDevice.zzetv) && this.zzetw == castDevice.zzetw && zzbdw.zza(this.zzetx, castDevice.zzetx) && this.zzety == castDevice.zzety && this.zzcfl == castDevice.zzcfl && zzbdw.zza(this.zzetz, castDevice.zzetz) && zzbdw.zza(Integer.valueOf(this.zzeub), Integer.valueOf(castDevice.zzeub)) && zzbdw.zza(this.zzeuc, castDevice.zzeuc) && zzbdw.zza(this.zzeua, castDevice.zzeua) && zzbdw.zza(this.zzetv, castDevice.getDeviceVersion()) && this.zzetw == castDevice.getServicePort() && (((bArr = this.zzeud) == null && castDevice.zzeud == null) || Arrays.equals(bArr, castDevice.zzeud));
    }

    public String getDeviceId() {
        return this.zzetq.startsWith("__cast_nearby__") ? this.zzetq.substring(16) : this.zzetq;
    }

    public String getDeviceVersion() {
        return this.zzetv;
    }

    public String getFriendlyName() {
        return this.zzett;
    }

    public WebImage getIcon(int i, int i2) {
        WebImage webImage = null;
        if (this.zzetx.isEmpty()) {
            return null;
        }
        if (i <= 0 || i2 <= 0) {
            return this.zzetx.get(0);
        }
        WebImage webImage2 = null;
        for (WebImage next : this.zzetx) {
            int width = next.getWidth();
            int height = next.getHeight();
            if (width < i || height < i2) {
                if (width < i && height < i2) {
                    if (webImage2 == null || (webImage2.getWidth() < width && webImage2.getHeight() < height)) {
                        webImage2 = next;
                    }
                }
            } else if (webImage == null || (webImage.getWidth() > width && webImage.getHeight() > height)) {
                webImage = next;
            }
        }
        return webImage != null ? webImage : webImage2 != null ? webImage2 : this.zzetx.get(0);
    }

    public List<WebImage> getIcons() {
        return Collections.unmodifiableList(this.zzetx);
    }

    public Inet4Address getIpAddress() {
        return this.zzets;
    }

    public String getModelName() {
        return this.zzetu;
    }

    public int getServicePort() {
        return this.zzetw;
    }

    public boolean hasCapabilities(int[] iArr) {
        if (iArr == null) {
            return false;
        }
        for (int hasCapability : iArr) {
            if (!hasCapability(hasCapability)) {
                return false;
            }
        }
        return true;
    }

    public boolean hasCapability(int i) {
        return (this.zzety & i) == i;
    }

    public boolean hasIcons() {
        return !this.zzetx.isEmpty();
    }

    public int hashCode() {
        String str = this.zzetq;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    public boolean isOnLocalNetwork() {
        return !this.zzetq.startsWith("__cast_nearby__");
    }

    public boolean isSameDevice(CastDevice castDevice) {
        String str;
        String str2;
        if (castDevice == null) {
            return false;
        }
        if (!TextUtils.isEmpty(getDeviceId()) && !getDeviceId().startsWith("__cast_ble__") && !TextUtils.isEmpty(castDevice.getDeviceId()) && !castDevice.getDeviceId().startsWith("__cast_ble__")) {
            str = getDeviceId();
            str2 = castDevice.getDeviceId();
        } else if (TextUtils.isEmpty(this.zzeuc) || TextUtils.isEmpty(castDevice.zzeuc)) {
            return false;
        } else {
            str = this.zzeuc;
            str2 = castDevice.zzeuc;
        }
        return zzbdw.zza(str, str2);
    }

    public void putInBundle(Bundle bundle) {
        if (bundle != null) {
            bundle.putParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE", this);
        }
    }

    public String toString() {
        return String.format("\"%s\" (%s)", new Object[]{this.zzett, this.zzetq});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzetq, false);
        zzbgo.zza(parcel, 3, this.zzetr, false);
        zzbgo.zza(parcel, 4, getFriendlyName(), false);
        zzbgo.zza(parcel, 5, getModelName(), false);
        zzbgo.zza(parcel, 6, getDeviceVersion(), false);
        zzbgo.zzc(parcel, 7, getServicePort());
        zzbgo.zzc(parcel, 8, getIcons(), false);
        zzbgo.zzc(parcel, 9, this.zzety);
        zzbgo.zzc(parcel, 10, this.zzcfl);
        zzbgo.zza(parcel, 11, this.zzetz, false);
        zzbgo.zza(parcel, 12, this.zzeua, false);
        zzbgo.zzc(parcel, 13, this.zzeub);
        zzbgo.zza(parcel, 14, this.zzeuc, false);
        zzbgo.zza(parcel, 15, this.zzeud, false);
        zzbgo.zzai(parcel, zze);
    }
}
