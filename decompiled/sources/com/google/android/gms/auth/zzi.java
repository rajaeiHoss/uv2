package com.google.android.gms.auth;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.zzayl;
import com.google.android.gms.internal.zzbhf;
import com.google.android.gms.internal.zzez;
import java.io.IOException;

final class zzi implements zzj<Boolean> {
    private /* synthetic */ String zzehx;

    zzi(String str) {
        this.zzehx = str;
    }

    public final Boolean zzac(IBinder iBinder) throws RemoteException, IOException, GoogleAuthException {
        Bundle bundle = (Bundle) zzd.zzp(zzez.zza(iBinder).zzp(this.zzehx));
        String string = bundle.getString("Error");
        Intent intent = (Intent) bundle.getParcelable("userRecoveryIntent");
        zzayl zzfi = zzayl.zzfi(string);
        if (zzayl.SUCCESS.equals(zzfi)) {
            return true;
        }
        if (zzayl.zza(zzfi)) {
            zzbhf zzabu = zzd.zzehr;
            String valueOf = String.valueOf(zzfi);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 31);
            sb.append("isUserRecoverableError status: ");
            sb.append(valueOf);
            zzabu.zzf("GoogleAuthUtil", sb.toString());
            throw new UserRecoverableAuthException(string, intent);
        }
        throw new GoogleAuthException(string);
    }
}
