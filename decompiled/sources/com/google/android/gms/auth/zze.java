package com.google.android.gms.auth;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.zzayl;
import com.google.android.gms.internal.zzbhf;
import com.google.android.gms.internal.zzez;
import java.io.IOException;

final class zze implements zzj<TokenData> {
    private /* synthetic */ Bundle val$options;
    private /* synthetic */ Account zzehs;
    private /* synthetic */ String zzeht;

    zze(Account account, String str, Bundle bundle) {
        this.zzehs = account;
        this.zzeht = str;
        this.val$options = bundle;
    }

    public final TokenData zzac(IBinder iBinder) throws RemoteException, IOException, GoogleAuthException {
        Bundle bundle = (Bundle) zzd.zzp(zzez.zza(iBinder).zza(this.zzehs, this.zzeht, this.val$options));
        TokenData zzc = TokenData.zzc(bundle, "tokenDetails");
        if (zzc != null) {
            return zzc;
        }
        String string = bundle.getString("Error");
        Intent intent = (Intent) bundle.getParcelable("userRecoveryIntent");
        zzayl zzfi = zzayl.zzfi(string);
        boolean z = false;
        if (!zzayl.zza(zzfi)) {
            if (zzayl.NETWORK_ERROR.equals(zzfi) || zzayl.SERVICE_UNAVAILABLE.equals(zzfi)) {
                z = true;
            }
            if (z) {
                throw new IOException(string);
            }
            throw new GoogleAuthException(string);
        }
        zzbhf zzabu = zzd.zzehr;
        String valueOf = String.valueOf(zzfi);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 31);
        sb.append("isUserRecoverableError status: ");
        sb.append(valueOf);
        zzabu.zzf("GoogleAuthUtil", sb.toString());
        throw new UserRecoverableAuthException(string, intent);
    }
}
