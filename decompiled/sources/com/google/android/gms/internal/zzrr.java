package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.dynamic.zzn;
import java.util.List;
import java.util.WeakHashMap;

@zzabh
public final class zzrr implements NativeCustomTemplateAd {
    private static WeakHashMap<IBinder, zzrr> zzcap = new WeakHashMap<>();
    private final VideoController zzbjt = new VideoController();
    private final zzro zzcaq;
    private final MediaView zzcar;

    private zzrr(zzro zzro) {
        Context context;
        this.zzcaq = zzro;
        MediaView mediaView = null;
        try {
            context = (Context) zzn.zzy(zzro.zzkk());
        } catch (RemoteException | NullPointerException e) {
            zzaky.zzb("Unable to inflate MediaView.", e);
            context = null;
        }
        if (context != null) {
            MediaView mediaView2 = new MediaView(context);
            try {
                if (this.zzcaq.zzf(zzn.zzz(mediaView2))) {
                    mediaView = mediaView2;
                }
            } catch (RemoteException e2) {
                zzaky.zzb("Unable to render video in MediaView.", e2);
            }
        }
        this.zzcar = mediaView;
    }

    public static zzrr zza(zzro zzro) {
        synchronized (zzcap) {
            zzrr zzrr = zzcap.get(zzro.asBinder());
            if (zzrr != null) {
                return zzrr;
            }
            zzrr zzrr2 = new zzrr(zzro);
            zzcap.put(zzro.asBinder(), zzrr2);
            return zzrr2;
        }
    }

    public final void destroy() {
        try {
            this.zzcaq.destroy();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to destroy ad.", e);
        }
    }

    public final List<String> getAvailableAssetNames() {
        try {
            return this.zzcaq.getAvailableAssetNames();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get available asset names.", e);
            return null;
        }
    }

    public final String getCustomTemplateId() {
        try {
            return this.zzcaq.getCustomTemplateId();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get custom template id.", e);
            return null;
        }
    }

    public final NativeAd.Image getImage(String str) {
        try {
            zzqs zzaq = this.zzcaq.zzaq(str);
            if (zzaq != null) {
                return new zzqv(zzaq);
            }
            return null;
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get image.", e);
            return null;
        }
    }

    public final CharSequence getText(String str) {
        try {
            return this.zzcaq.zzap(str);
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get string.", e);
            return null;
        }
    }

    public final VideoController getVideoController() {
        try {
            zzmm videoController = this.zzcaq.getVideoController();
            if (videoController != null) {
                this.zzbjt.zza(videoController);
            }
        } catch (RemoteException e) {
            zzaky.zzb("Exception occurred while getting video controller", e);
        }
        return this.zzbjt;
    }

    public final MediaView getVideoMediaView() {
        return this.zzcar;
    }

    public final void performClick(String str) {
        try {
            this.zzcaq.performClick(str);
        } catch (RemoteException e) {
            zzaky.zzb("Failed to perform click.", e);
        }
    }

    public final void recordImpression() {
        try {
            this.zzcaq.recordImpression();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to record impression.", e);
        }
    }

    public final zzro zzkx() {
        return this.zzcaq;
    }
}
