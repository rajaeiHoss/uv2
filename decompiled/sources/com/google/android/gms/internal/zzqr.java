package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.NativeAd;
import java.util.ArrayList;
import java.util.List;

@zzabh
public final class zzqr extends NativeAd.AdChoicesInfo {
    private final List<NativeAd.Image> zzbxk = new ArrayList();
    private final zzqo zzcag;
    private String zzcah;

    public zzqr(zzqo zzqo) {
        zzqs zzqs;
        IBinder iBinder;
        this.zzcag = zzqo;
        try {
            this.zzcah = zzqo.getText();
        } catch (RemoteException e) {
            zzaky.zzb("Error while obtaining attribution text.", e);
            this.zzcah = "";
        }
        try {
            for (zzqs next : zzqo.zzjw()) {
                if (!(next instanceof IBinder) || (iBinder = (IBinder) next) == null) {
                    zzqs = null;
                } else {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
                    zzqs = queryLocalInterface instanceof zzqs ? (zzqs) queryLocalInterface : new zzqu(iBinder);
                }
                if (zzqs != null) {
                    this.zzbxk.add(new zzqv(zzqs));
                }
            }
        } catch (RemoteException e2) {
            zzaky.zzb("Error while obtaining image.", e2);
        }
    }

    public final List<NativeAd.Image> getImages() {
        return this.zzbxk;
    }

    public final CharSequence getText() {
        return this.zzcah;
    }
}
