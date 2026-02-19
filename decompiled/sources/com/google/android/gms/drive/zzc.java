package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public final class zzc extends zzbgl {
    public static final Parcelable.Creator<zzc> CREATOR = new zzd();
    private String zzayo;
    private ParcelFileDescriptor zzgdm;
    final int zzgpc;
    private int zzgpd;
    private DriveId zzgpe;
    private boolean zzgpf;

    public zzc(ParcelFileDescriptor parcelFileDescriptor, int i, int i2, DriveId driveId, boolean z, String str) {
        this.zzgdm = parcelFileDescriptor;
        this.zzgpc = i;
        this.zzgpd = i2;
        this.zzgpe = driveId;
        this.zzgpf = z;
        this.zzayo = str;
    }

    public final DriveId getDriveId() {
        return this.zzgpe;
    }

    public final InputStream getInputStream() {
        return new FileInputStream(this.zzgdm.getFileDescriptor());
    }

    public final int getMode() {
        return this.zzgpd;
    }

    public final OutputStream getOutputStream() {
        return new FileOutputStream(this.zzgdm.getFileDescriptor());
    }

    public final ParcelFileDescriptor getParcelFileDescriptor() {
        return this.zzgdm;
    }

    public final int getRequestId() {
        return this.zzgpc;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgdm, i, false);
        zzbgo.zzc(parcel, 3, this.zzgpc);
        zzbgo.zzc(parcel, 4, this.zzgpd);
        zzbgo.zza(parcel, 5, (Parcelable) this.zzgpe, i, false);
        zzbgo.zza(parcel, 7, this.zzgpf);
        zzbgo.zza(parcel, 8, this.zzayo, false);
        zzbgo.zzai(parcel, zze);
    }

    public final boolean zzapd() {
        return this.zzgpf;
    }
}
