package com.google.android.gms.wearable;

import android.net.Uri;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzc;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public class Asset extends zzbgl implements ReflectedParcelable {
    public static final Parcelable.Creator<Asset> CREATOR = new zze();
    private Uri uri;
    private byte[] zzigl;
    private String zzlqn;
    private ParcelFileDescriptor zzlqo;

    Asset(byte[] bArr, String str, ParcelFileDescriptor parcelFileDescriptor, Uri uri2) {
        this.zzigl = bArr;
        this.zzlqn = str;
        this.zzlqo = parcelFileDescriptor;
        this.uri = uri2;
    }

    public static Asset createFromBytes(byte[] bArr) {
        zzc.zzv(bArr);
        return new Asset(bArr, (String) null, (ParcelFileDescriptor) null, (Uri) null);
    }

    public static Asset createFromFd(ParcelFileDescriptor parcelFileDescriptor) {
        zzc.zzv(parcelFileDescriptor);
        return new Asset((byte[]) null, (String) null, parcelFileDescriptor, (Uri) null);
    }

    public static Asset createFromRef(String str) {
        zzc.zzv(str);
        return new Asset((byte[]) null, str, (ParcelFileDescriptor) null, (Uri) null);
    }

    public static Asset createFromUri(Uri uri2) {
        zzc.zzv(uri2);
        return new Asset((byte[]) null, (String) null, (ParcelFileDescriptor) null, uri2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Asset)) {
            return false;
        }
        Asset asset = (Asset) obj;
        return Arrays.equals(this.zzigl, asset.zzigl) && zzbg.equal(this.zzlqn, asset.zzlqn) && zzbg.equal(this.zzlqo, asset.zzlqo) && zzbg.equal(this.uri, asset.uri);
    }

    public final byte[] getData() {
        return this.zzigl;
    }

    public String getDigest() {
        return this.zzlqn;
    }

    public ParcelFileDescriptor getFd() {
        return this.zzlqo;
    }

    public Uri getUri() {
        return this.uri;
    }

    public int hashCode() {
        return Arrays.deepHashCode(new Object[]{this.zzigl, this.zzlqn, this.zzlqo, this.uri});
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("Asset[@");
        sb.append(Integer.toHexString(hashCode()));
        if (this.zzlqn == null) {
            str = ", nodigest";
        } else {
            sb.append(", ");
            str = this.zzlqn;
        }
        sb.append(str);
        if (this.zzigl != null) {
            sb.append(", size=");
            sb.append(this.zzigl.length);
        }
        if (this.zzlqo != null) {
            sb.append(", fd=");
            sb.append(this.zzlqo);
        }
        if (this.uri != null) {
            sb.append(", uri=");
            sb.append(this.uri);
        }
        sb.append("]");
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzc.zzv(parcel);
        int i2 = i | 1;
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzigl, false);
        zzbgo.zza(parcel, 3, getDigest(), false);
        zzbgo.zza(parcel, 4, (Parcelable) this.zzlqo, i2, false);
        zzbgo.zza(parcel, 5, (Parcelable) this.uri, i2, false);
        zzbgo.zzai(parcel, zze);
    }
}
