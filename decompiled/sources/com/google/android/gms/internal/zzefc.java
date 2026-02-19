package com.google.android.gms.internal;

import java.util.Map;

final class zzefc implements zzefh {
    private /* synthetic */ zzeey zznaz;
    private /* synthetic */ boolean zznbd;

    zzefc(zzeey zzeey, boolean z) {
        this.zznaz = zzeey;
        this.zznbd = z;
    }

    public final void zzal(Map<String, Object> map) {
        zzefi unused = this.zznaz.zznah = zzefi.Connected;
        String str = (String) map.get("s");
        if (str.equals("ok")) {
            int unused2 = this.zznaz.zznau = 0;
            this.zznaz.zznab.zzcs(true);
            if (this.zznbd) {
                this.zznaz.zzbwz();
                return;
            }
            return;
        }
        String unused3 = this.zznaz.zznao = null;
        boolean unused4 = this.zznaz.zznap = true;
        this.zznaz.zznab.zzcs(false);
        String str2 = (String) map.get("d");
        zzemm zza = this.zznaz.zzmxz;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 26 + String.valueOf(str2).length());
        sb.append("Authentication failed: ");
        sb.append(str);
        sb.append(" (");
        sb.append(str2);
        sb.append(")");
        zza.zzb(sb.toString(), (Throwable) null, new Object[0]);
        this.zznaz.zznag.close();
        if (str.equals("invalid_token")) {
            zzeey.zzj(this.zznaz);
            if (((long) this.zznaz.zznau) >= 3) {
                this.zznaz.zznar.zzbxs();
                this.zznaz.zzmxz.zzf("Provided authentication credentials are invalid. This usually indicates your FirebaseApp instance was not initialized correctly. Make sure your google-services.json file has the correct firebase_url and api_key. You can re-download google-services.json from https://console.firebase.google.com/.", (Throwable) null);
            }
        }
    }
}
