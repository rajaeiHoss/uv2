package com.google.android.gms.ads.internal;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzacl;
import com.google.android.gms.internal.zzagq;
import com.google.android.gms.internal.zzaij;
import java.util.Map;

@zzabh
public final class zzw {
    private final Context mContext;
    private boolean zzaoo;
    private zzagq zzaop;
    private zzacl zzaoq;

    public zzw(Context context, zzagq zzagq, zzacl zzacl) {
        this.mContext = context;
        this.zzaop = zzagq;
        this.zzaoq = zzacl;
        if (zzacl == null) {
            this.zzaoq = new zzacl();
        }
    }

    private final boolean zzcy() {
        zzagq zzagq = this.zzaop;
        return (zzagq != null && zzagq.zzpe().zzdbs) || this.zzaoq.zzcun;
    }

    public final void recordClick() {
        this.zzaoo = true;
    }

    public final boolean zzcz() {
        return !zzcy() || this.zzaoo;
    }

    public final void zzt(String str) {
        if (zzcy()) {
            if (str == null) {
                str = "";
            }
            zzagq zzagq = this.zzaop;
            if (zzagq != null) {
                zzagq.zza(str, (Map<String, String>) null, 3);
            } else if (this.zzaoq.zzcun && this.zzaoq.zzcuo != null) {
                for (String next : this.zzaoq.zzcuo) {
                    if (!TextUtils.isEmpty(next)) {
                        String replace = next.replace("{NAVIGATION_URL}", Uri.encode(str));
                        zzbt.zzel();
                        zzaij.zze(this.mContext, "", replace);
                    }
                }
            }
        }
    }
}
