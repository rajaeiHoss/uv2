package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzp;
import com.google.android.gms.games.internal.zzc;
import com.google.android.gms.games.internal.zzf;
import com.google.android.gms.internal.zzbgo;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public final class zza extends zzc implements SnapshotContents {
    public static final Parcelable.Creator<zza> CREATOR = new zzb();
    private static final Object zzihj = new Object();
    private com.google.android.gms.drive.zzc zzgul;

    public zza(com.google.android.gms.drive.zzc zzc) {
        this.zzgul = zzc;
    }

    private final boolean zza(int i, byte[] bArr, int i2, int i3, boolean z) {
        zzbq.zza(!isClosed(), (Object) "Must provide a previously opened SnapshotContents");
        synchronized (zzihj) {
            FileOutputStream fileOutputStream = new FileOutputStream(this.zzgul.getParcelFileDescriptor().getFileDescriptor());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            try {
                FileChannel channel = fileOutputStream.getChannel();
                channel.position((long) i);
                bufferedOutputStream.write(bArr, i2, i3);
                if (z) {
                    channel.truncate((long) bArr.length);
                }
                bufferedOutputStream.flush();
            } catch (IOException e) {
                zzf.zzb("SnapshotContentsEntity", "Failed to write snapshot data", e);
                return false;
            }
        }
        return true;
    }

    public final void close() {
        this.zzgul = null;
    }

    public final ParcelFileDescriptor getParcelFileDescriptor() {
        zzbq.zza(!isClosed(), (Object) "Cannot mutate closed contents!");
        return this.zzgul.getParcelFileDescriptor();
    }

    public final boolean isClosed() {
        return this.zzgul == null;
    }

    public final boolean modifyBytes(int i, byte[] bArr, int i2, int i3) {
        return zza(i, bArr, i2, bArr.length, false);
    }

    public final byte[] readFully() throws IOException {
        byte[] zza;
        zzbq.zza(!isClosed(), (Object) "Must provide a previously opened Snapshot");
        synchronized (zzihj) {
            FileInputStream fileInputStream = new FileInputStream(this.zzgul.getParcelFileDescriptor().getFileDescriptor());
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            try {
                fileInputStream.getChannel().position(0);
                zza = zzp.zza(bufferedInputStream, false);
                fileInputStream.getChannel().position(0);
            } catch (IOException e) {
                zzf.zzc("SnapshotContentsEntity", "Failed to read snapshot data", e);
                throw e;
            }
        }
        return zza;
    }

    public final boolean writeBytes(byte[] bArr) {
        return zza(0, bArr, 0, bArr.length, true);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) this.zzgul, i, false);
        zzbgo.zzai(parcel, zze);
    }

    public final com.google.android.gms.drive.zzc zzapl() {
        return this.zzgul;
    }
}
