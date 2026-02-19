package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.drive.DriveFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

final class zzhj implements Runnable {
    private /* synthetic */ String zzehu;
    private /* synthetic */ Uri zzkff;
    private /* synthetic */ long zzltk;
    private /* synthetic */ long zzltl;
    private /* synthetic */ zzn zzlvv;
    private /* synthetic */ zzhg zzlvw;

    zzhj(zzhg zzhg, Uri uri, zzn zzn, String str, long j, long j2) {
        this.zzlvw = zzhg;
        this.zzkff = uri;
        this.zzlvv = zzn;
        this.zzehu = str;
        this.zzltk = j;
        this.zzltl = j2;
    }

    public final void run() {
        if (Log.isLoggable("WearableClient", 2)) {
            Log.v("WearableClient", "Executing sendFileToChannelTask");
        }
        if (!"file".equals(this.zzkff.getScheme())) {
            Log.w("WearableClient", "Channel.sendFile used with non-file URI");
            this.zzlvv.zzu(new Status(10, "Channel.sendFile used with non-file URI"));
            return;
        }
        File file = new File(this.zzkff.getPath());
        try {
            ParcelFileDescriptor open = ParcelFileDescriptor.open(file, DriveFile.MODE_READ_ONLY);
            try {
                ((zzep) this.zzlvw.zzalw()).zza(new zzhc(this.zzlvv), this.zzehu, open, this.zzltk, this.zzltl);
                try {
                    open.close();
                } catch (IOException e) {
                    Log.w("WearableClient", "Failed to close sourceFd", e);
                }
            } catch (RemoteException e2) {
                Log.w("WearableClient", "Channel.sendFile failed.", e2);
                this.zzlvv.zzu(new Status(8));
                try {
                    open.close();
                } catch (IOException e3) {
                    Log.w("WearableClient", "Failed to close sourceFd", e3);
                }
            } catch (Throwable th) {
                try {
                    open.close();
                } catch (IOException e4) {
                    Log.w("WearableClient", "Failed to close sourceFd", e4);
                }
                throw th;
            }
        } catch (FileNotFoundException unused) {
            String valueOf = String.valueOf(file);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 46);
            sb.append("File couldn't be opened for Channel.sendFile: ");
            sb.append(valueOf);
            Log.w("WearableClient", sb.toString());
            this.zzlvv.zzu(new Status(13));
        }
    }
}
