package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.ads.internal.gmsg.zzt;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaof;
import com.google.android.gms.internal.zzwr;
import com.google.android.gms.internal.zzwu;
import java.util.Map;

final class zzaw implements zzt<zzaof> {
    private /* synthetic */ zzwr zzarb;
    private /* synthetic */ zzab zzarc;
    private /* synthetic */ zzwu zzard;

    zzaw(zzwr zzwr, zzab zzab, zzwu zzwu) {
        this.zzarb = zzwr;
        this.zzarc = zzab;
        this.zzard = zzwu;
    }

    public final void zza(zzaof zzaof, Map<String, String> map) {
        View view = zzaof.getView();
        if (view != null) {
            try {
                zzwr zzwr = this.zzarb;
                if (zzwr == null) {
                    zzwu zzwu = this.zzard;
                    if (zzwu == null) {
                        return;
                    }
                    if (!zzwu.getOverrideClickHandling()) {
                        this.zzard.zzh(zzn.zzz(view));
                        this.zzarc.zzaow.onAdClicked();
                        return;
                    }
                    zzar.zzc(zzaof);
                } else if (!zzwr.getOverrideClickHandling()) {
                    this.zzarb.zzh(zzn.zzz(view));
                    this.zzarc.zzaow.onAdClicked();
                } else {
                    zzar.zzc(zzaof);
                }
            } catch (RemoteException e) {
                zzahw.zzc("Unable to call handleClick on mapper", e);
            }
        }
    }
}
