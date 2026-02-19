package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.ArrayList;
import java.util.List;

@zzabh
public final class zzrj extends NativeAppInstallAd {
    private final VideoController zzbjt = new VideoController();
    private final zzrg zzcaj;
    private final List<NativeAd.Image> zzcak = new ArrayList();
    private final zzqv zzcal;
    private final NativeAd.AdChoicesInfo zzcam;

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0074 A[Catch:{ RemoteException -> 0x0081 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzrj(com.google.android.gms.internal.zzrg r6) {
        /*
            r5 = this;
            java.lang.String r0 = "Failed to get image."
            r5.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r5.zzcak = r1
            com.google.android.gms.ads.VideoController r1 = new com.google.android.gms.ads.VideoController
            r1.<init>()
            r5.zzbjt = r1
            r5.zzcaj = r6
            r1 = 0
            java.util.List r6 = r6.getImages()     // Catch:{ RemoteException -> 0x0053 }
            if (r6 == 0) goto L_0x0057
            java.util.Iterator r6 = r6.iterator()     // Catch:{ RemoteException -> 0x0053 }
        L_0x0020:
            boolean r2 = r6.hasNext()     // Catch:{ RemoteException -> 0x0053 }
            if (r2 == 0) goto L_0x0057
            java.lang.Object r2 = r6.next()     // Catch:{ RemoteException -> 0x0053 }
            boolean r3 = r2 instanceof android.os.IBinder     // Catch:{ RemoteException -> 0x0053 }
            if (r3 == 0) goto L_0x0045
            android.os.IBinder r2 = (android.os.IBinder) r2     // Catch:{ RemoteException -> 0x0053 }
            if (r2 == 0) goto L_0x0045
            java.lang.String r3 = "com.google.android.gms.ads.internal.formats.client.INativeAdImage"
            android.os.IInterface r3 = r2.queryLocalInterface(r3)     // Catch:{ RemoteException -> 0x0053 }
            boolean r4 = r3 instanceof com.google.android.gms.internal.zzqs     // Catch:{ RemoteException -> 0x0053 }
            if (r4 == 0) goto L_0x003f
            com.google.android.gms.internal.zzqs r3 = (com.google.android.gms.internal.zzqs) r3     // Catch:{ RemoteException -> 0x0053 }
            goto L_0x0046
        L_0x003f:
            com.google.android.gms.internal.zzqu r3 = new com.google.android.gms.internal.zzqu     // Catch:{ RemoteException -> 0x0053 }
            r3.<init>(r2)     // Catch:{ RemoteException -> 0x0053 }
            goto L_0x0046
        L_0x0045:
            r3 = r1
        L_0x0046:
            if (r3 == 0) goto L_0x0020
            java.util.List<com.google.android.gms.ads.formats.NativeAd$Image> r2 = r5.zzcak     // Catch:{ RemoteException -> 0x0053 }
            com.google.android.gms.internal.zzqv r4 = new com.google.android.gms.internal.zzqv     // Catch:{ RemoteException -> 0x0053 }
            r4.<init>(r3)     // Catch:{ RemoteException -> 0x0053 }
            r2.add(r4)     // Catch:{ RemoteException -> 0x0053 }
            goto L_0x0020
        L_0x0053:
            r6 = move-exception
            com.google.android.gms.internal.zzaky.zzb(r0, r6)
        L_0x0057:
            com.google.android.gms.internal.zzrg r6 = r5.zzcaj     // Catch:{ RemoteException -> 0x0065 }
            com.google.android.gms.internal.zzqs r6 = r6.zzkc()     // Catch:{ RemoteException -> 0x0065 }
            if (r6 == 0) goto L_0x0069
            com.google.android.gms.internal.zzqv r2 = new com.google.android.gms.internal.zzqv     // Catch:{ RemoteException -> 0x0065 }
            r2.<init>(r6)     // Catch:{ RemoteException -> 0x0065 }
            goto L_0x006a
        L_0x0065:
            r6 = move-exception
            com.google.android.gms.internal.zzaky.zzb(r0, r6)
        L_0x0069:
            r2 = r1
        L_0x006a:
            r5.zzcal = r2
            com.google.android.gms.internal.zzrg r6 = r5.zzcaj     // Catch:{ RemoteException -> 0x0081 }
            com.google.android.gms.internal.zzqo r6 = r6.zzki()     // Catch:{ RemoteException -> 0x0081 }
            if (r6 == 0) goto L_0x0087
            com.google.android.gms.internal.zzqr r6 = new com.google.android.gms.internal.zzqr     // Catch:{ RemoteException -> 0x0081 }
            com.google.android.gms.internal.zzrg r0 = r5.zzcaj     // Catch:{ RemoteException -> 0x0081 }
            com.google.android.gms.internal.zzqo r0 = r0.zzki()     // Catch:{ RemoteException -> 0x0081 }
            r6.<init>(r0)     // Catch:{ RemoteException -> 0x0081 }
            r1 = r6
            goto L_0x0087
        L_0x0081:
            r6 = move-exception
            java.lang.String r0 = "Failed to get attribution info."
            com.google.android.gms.internal.zzaky.zzb(r0, r6)
        L_0x0087:
            r5.zzcam = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzrj.<init>(com.google.android.gms.internal.zzrg):void");
    }

    /* access modifiers changed from: private */
    /* renamed from: zzkd */
    public final IObjectWrapper zzbi() {
        try {
            return this.zzcaj.zzkd();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to retrieve native ad engine.", e);
            return null;
        }
    }

    public final void destroy() {
        try {
            this.zzcaj.destroy();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to destroy", e);
        }
    }

    public final NativeAd.AdChoicesInfo getAdChoicesInfo() {
        return this.zzcam;
    }

    public final CharSequence getBody() {
        try {
            return this.zzcaj.getBody();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get body.", e);
            return null;
        }
    }

    public final CharSequence getCallToAction() {
        try {
            return this.zzcaj.getCallToAction();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get call to action.", e);
            return null;
        }
    }

    public final Bundle getExtras() {
        try {
            return this.zzcaj.getExtras();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get extras", e);
            return null;
        }
    }

    public final CharSequence getHeadline() {
        try {
            return this.zzcaj.getHeadline();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get headline.", e);
            return null;
        }
    }

    public final NativeAd.Image getIcon() {
        return this.zzcal;
    }

    public final List<NativeAd.Image> getImages() {
        return this.zzcak;
    }

    public final CharSequence getMediationAdapterClassName() {
        try {
            return this.zzcaj.getMediationAdapterClassName();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get mediation adapter class name.", e);
            return null;
        }
    }

    public final CharSequence getPrice() {
        try {
            return this.zzcaj.getPrice();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get price.", e);
            return null;
        }
    }

    public final Double getStarRating() {
        try {
            double starRating = this.zzcaj.getStarRating();
            if (starRating == -1.0d) {
                return null;
            }
            return Double.valueOf(starRating);
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get star rating.", e);
            return null;
        }
    }

    public final CharSequence getStore() {
        try {
            return this.zzcaj.getStore();
        } catch (RemoteException e) {
            zzaky.zzb("Failed to get store", e);
            return null;
        }
    }

    public final VideoController getVideoController() {
        try {
            if (this.zzcaj.getVideoController() != null) {
                this.zzbjt.zza(this.zzcaj.getVideoController());
            }
        } catch (RemoteException e) {
            zzaky.zzb("Exception occurred while getting video controller", e);
        }
        return this.zzbjt;
    }

    public final void performClick(Bundle bundle) {
        try {
            this.zzcaj.performClick(bundle);
        } catch (RemoteException e) {
            zzaky.zzb("Failed to perform click.", e);
        }
    }

    public final boolean recordImpression(Bundle bundle) {
        try {
            return this.zzcaj.recordImpression(bundle);
        } catch (RemoteException e) {
            zzaky.zzb("Failed to record impression.", e);
            return false;
        }
    }

    public final void reportTouchEvent(Bundle bundle) {
        try {
            this.zzcaj.reportTouchEvent(bundle);
        } catch (RemoteException e) {
            zzaky.zzb("Failed to report touch event.", e);
        }
    }
}
