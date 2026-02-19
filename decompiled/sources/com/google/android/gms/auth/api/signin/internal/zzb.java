package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzdb;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbhf;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public final class zzb implements Runnable {
    private static final zzbhf zzenl = new zzbhf("RevokeAccessOperation", new String[0]);
    private final String zzeia;
    private final zzdb zzenm = new zzdb((GoogleApiClient) null);

    private zzb(String str) {
        zzbq.zzgv(str);
        this.zzeia = str;
    }

    public static PendingResult<Status> zzfd(String str) {
        if (str == null) {
            return PendingResults.zza(new Status(4), (GoogleApiClient) null);
        }
        zzb zzb = new zzb(str);
        new Thread(zzb).start();
        return zzb.zzenm;
    }

    public final void run() {
        Status status = Status.zzfts;
        try {
            String valueOf = String.valueOf(this.zzeia);
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(valueOf.length() != 0 ? "https://accounts.google.com/o/oauth2/revoke?token=".concat(valueOf) : new String("https://accounts.google.com/o/oauth2/revoke?token=")).openConnection();
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                status = Status.zzftq;
            } else {
                zzenl.zzc("Unable to revoke access!", new Object[0]);
            }
            zzbhf zzbhf = zzenl;
            StringBuilder sb = new StringBuilder(26);
            sb.append("Response Code: ");
            sb.append(responseCode);
            zzbhf.zzb(sb.toString(), new Object[0]);
        } catch (IOException e) {
            zzbhf zzbhf2 = zzenl;
            String valueOf2 = String.valueOf(e.toString());
            zzbhf2.zzc(valueOf2.length() != 0 ? "IOException when revoking access: ".concat(valueOf2) : new String("IOException when revoking access: "), new Object[0]);
        } catch (Exception e2) {
            zzbhf zzbhf3 = zzenl;
            String valueOf3 = String.valueOf(e2.toString());
            zzbhf3.zzc(valueOf3.length() != 0 ? "Exception when revoking access: ".concat(valueOf3) : new String("Exception when revoking access: "), new Object[0]);
        }
        this.zzenm.setResult(status);
    }
}
