package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.util.zzp;
import java.io.DataInputStream;
import java.io.IOException;

@zzabh
public final class zzacv extends zzbgl {
    public static final Parcelable.Creator<zzacv> CREATOR = new zzacx();
    private ParcelFileDescriptor zzcup;
    private Parcelable zzcuq;
    private boolean zzcur;

    public zzacv(ParcelFileDescriptor parcelFileDescriptor) {
        this.zzcup = parcelFileDescriptor;
        this.zzcuq = null;
        this.zzcur = true;
    }

    public zzacv(zzbgp zzbgp) {
        this.zzcup = null;
        this.zzcuq = zzbgp;
        this.zzcur = false;
    }

    private final <T> ParcelFileDescriptor zze(byte[] bArr) {
        ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream;
        try {
            ParcelFileDescriptor[] createPipe = ParcelFileDescriptor.createPipe();
            autoCloseOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(createPipe[1]);
            new Thread(new zzacw(this, autoCloseOutputStream, bArr)).start();
            return createPipe[0];
        } catch (IOException e2) {
            autoCloseOutputStream = null;
            zzahw.zzb("Error transporting the ad response", e2);
            zzbt.zzep().zza(e2, "LargeParcelTeleporter.pipeData.2");
            zzp.closeQuietly(autoCloseOutputStream);
            return null;
        }
    }

    /* JADX INFO: finally extract failed */
    private final ParcelFileDescriptor zzob() {
        if (this.zzcup == null) {
            Parcel obtain = Parcel.obtain();
            try {
                this.zzcuq.writeToParcel(obtain, 0);
                byte[] marshall = obtain.marshall();
                obtain.recycle();
                this.zzcup = zze(marshall);
            } catch (Throwable th) {
                obtain.recycle();
                throw th;
            }
        }
        return this.zzcup;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        zzob();
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzcup, i, false);
        zzbgo.zzai(parcel, zze);
    }

    /* JADX INFO: finally extract failed */
    public final <T extends zzbgp> T zza(Parcelable.Creator<T> creator) {
        if (this.zzcur) {
            if (this.zzcup == null) {
                zzahw.e("File descriptor is empty, returning null.");
                return null;
            }
            DataInputStream dataInputStream = new DataInputStream(new ParcelFileDescriptor.AutoCloseInputStream(this.zzcup));
            try {
                int readInt = dataInputStream.readInt();
                byte[] bArr = new byte[readInt];
                dataInputStream.readFully(bArr, 0, readInt);
                zzp.closeQuietly(dataInputStream);
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.unmarshall(bArr, 0, readInt);
                    obtain.setDataPosition(0);
                    this.zzcuq = (zzbgp) creator.createFromParcel(obtain);
                    obtain.recycle();
                    this.zzcur = false;
                } catch (Throwable th) {
                    obtain.recycle();
                    throw th;
                }
            } catch (IOException e) {
                zzahw.zzb("Could not read from parcel file descriptor", e);
                zzp.closeQuietly(dataInputStream);
                return null;
            } catch (Throwable th2) {
                zzp.closeQuietly(dataInputStream);
                throw th2;
            }
        }
        @SuppressWarnings("unchecked")
        T result = (T) this.zzcuq;
        return result;
    }
}
