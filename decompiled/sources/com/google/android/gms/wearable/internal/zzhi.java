package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

final class zzhi implements Runnable {
    private /* synthetic */ String zzehu;
    private /* synthetic */ Uri zzkff;
    private /* synthetic */ boolean zzltj;
    private /* synthetic */ zzn zzlvv;
    private /* synthetic */ zzhg zzlvw;

    zzhi(zzhg zzhg, Uri uri, zzn zzn, boolean z, String str) {
        this.zzlvw = zzhg;
        this.zzkff = uri;
        this.zzlvv = zzn;
        this.zzltj = z;
        this.zzehu = str;
    }

    public final void run() {
        if (Log.isLoggable("WearableClient", 2)) {
            Log.v("WearableClient", "Executing receiveFileFromChannelTask");
        }
        if (!"file".equals(this.zzkff.getScheme())) {
            Log.w("WearableClient", "Channel.receiveFile used with non-file URI");
            this.zzlvv.zzu(new Status(10, "Channel.receiveFile used with non-file URI"));
            return;
        }
        File file = new File(this.zzkff.getPath());
        try {
            ParcelFileDescriptor open = ParcelFileDescriptor.open(file, 671088640 | (this.zzltj ? 33554432 : 0));
            try {
                ((zzep) this.zzlvw.zzalw()).zza((zzek) new zzhf(this.zzlvv), this.zzehu, open);
                try {
                    open.close();
                } catch (IOException e) {
                    Log.w("WearableClient", "Failed to close targetFd", e);
                }
            } catch (RemoteException e2) {
                Log.w("WearableClient", "Channel.receiveFile failed.", e2);
                this.zzlvv.zzu(new Status(8));
                try {
                    open.close();
                } catch (IOException e3) {
                    Log.w("WearableClient", "Failed to close targetFd", e3);
                }
            } catch (Throwable th) {
                try {
                    open.close();
                } catch (IOException e4) {
                    Log.w("WearableClient", "Failed to close targetFd", e4);
                }
                throw th;
            }
        } catch (FileNotFoundException unused) {
            String valueOf = String.valueOf(file);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 49);
            sb.append("File couldn't be opened for Channel.receiveFile: ");
            sb.append(valueOf);
            Log.w("WearableClient", sb.toString());
            this.zzlvv.zzu(new Status(13));
        }
    }
}
