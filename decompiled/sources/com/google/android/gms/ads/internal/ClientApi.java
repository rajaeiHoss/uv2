package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.os.RemoteException;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzq;
import com.google.android.gms.ads.internal.overlay.zzr;
import com.google.android.gms.ads.internal.overlay.zzx;
import com.google.android.gms.ads.internal.overlay.zzy;
import com.google.android.gms.common.zzs;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzaeq;
import com.google.android.gms.internal.zzaex;
import com.google.android.gms.internal.zzaij;
import com.google.android.gms.internal.zzala;
import com.google.android.gms.internal.zzko;
import com.google.android.gms.internal.zzlo;
import com.google.android.gms.internal.zzlt;
import com.google.android.gms.internal.zzmc;
import com.google.android.gms.internal.zzmh;
import com.google.android.gms.internal.zzqj;
import com.google.android.gms.internal.zzql;
import com.google.android.gms.internal.zzqw;
import com.google.android.gms.internal.zzrb;
import com.google.android.gms.internal.zzwf;
import com.google.android.gms.internal.zzyq;
import com.google.android.gms.internal.zzza;
import java.util.HashMap;

@zzabh
public class ClientApi extends zzmc {
    public zzlo createAdLoaderBuilder(IObjectWrapper iObjectWrapper, String str, zzwf zzwf, int i) {
        Context context = (Context) zzn.zzy(iObjectWrapper);
        zzbt.zzel();
        return new zzaj(context, str, zzwf, new zzala(zzs.GOOGLE_PLAY_SERVICES_VERSION_CODE, i, true, zzaij.zzas(context)), zzv.zzc(context));
    }

    public zzyq createAdOverlay(IObjectWrapper iObjectWrapper) {
        Activity activity = (Activity) zzn.zzy(iObjectWrapper);
        AdOverlayInfoParcel zzc = AdOverlayInfoParcel.zzc(activity.getIntent());
        if (zzc == null) {
            return new zzr(activity);
        }
        int i = zzc.zzcns;
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? new zzr(activity) : new com.google.android.gms.ads.internal.overlay.zzs(activity, zzc) : new zzy(activity) : new zzx(activity) : new zzq(activity);
    }

    public zzlt createBannerAdManager(IObjectWrapper iObjectWrapper, zzko zzko, String str, zzwf zzwf, int i) throws RemoteException {
        Context context = (Context) zzn.zzy(iObjectWrapper);
        zzbt.zzel();
        return new com.google.android.gms.ads.internal.zzx(context, zzko, str, zzwf, new zzala(zzs.GOOGLE_PLAY_SERVICES_VERSION_CODE, i, true, zzaij.zzas(context)), zzv.zzc(context));
    }

    public zzza createInAppPurchaseManager(IObjectWrapper iObjectWrapper) {
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0034, code lost:
        if (((java.lang.Boolean) com.google.android.gms.internal.zzlc.zzio().zzd(com.google.android.gms.internal.zzoi.zzbpr)).booleanValue() == false) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0048, code lost:
        if (((java.lang.Boolean) com.google.android.gms.internal.zzlc.zzio().zzd(com.google.android.gms.internal.zzoi.zzbps)).booleanValue() != false) goto L_0x004c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.zzlt createInterstitialAdManager(com.google.android.gms.dynamic.IObjectWrapper r8, com.google.android.gms.internal.zzko r9, java.lang.String r10, com.google.android.gms.internal.zzwf r11, int r12) throws android.os.RemoteException {
        /*
            r7 = this;
            java.lang.Object r8 = com.google.android.gms.dynamic.zzn.zzy(r8)
            r1 = r8
            android.content.Context r1 = (android.content.Context) r1
            com.google.android.gms.internal.zzoi.initialize(r1)
            com.google.android.gms.internal.zzala r5 = new com.google.android.gms.internal.zzala
            com.google.android.gms.ads.internal.zzbt.zzel()
            boolean r8 = com.google.android.gms.internal.zzaij.zzas(r1)
            r0 = 12211000(0xba5338, float:1.7111256E-38)
            r2 = 1
            r5.<init>(r0, r12, r2, r8)
            java.lang.String r8 = r9.zzbia
            java.lang.String r12 = "reward_mb"
            boolean r8 = r12.equals(r8)
            if (r8 != 0) goto L_0x0036
            com.google.android.gms.internal.zzny<java.lang.Boolean> r12 = com.google.android.gms.internal.zzoi.zzbpr
            com.google.android.gms.internal.zzog r0 = com.google.android.gms.internal.zzlc.zzio()
            java.lang.Object r12 = r0.zzd(r12)
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            if (r12 != 0) goto L_0x004c
        L_0x0036:
            if (r8 == 0) goto L_0x004b
            com.google.android.gms.internal.zzny<java.lang.Boolean> r8 = com.google.android.gms.internal.zzoi.zzbps
            com.google.android.gms.internal.zzog r12 = com.google.android.gms.internal.zzlc.zzio()
            java.lang.Object r8 = r12.zzd(r8)
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L_0x004b
            goto L_0x004c
        L_0x004b:
            r2 = 0
        L_0x004c:
            if (r2 == 0) goto L_0x005d
            com.google.android.gms.internal.zzut r8 = new com.google.android.gms.internal.zzut
            com.google.android.gms.ads.internal.zzv r9 = com.google.android.gms.ads.internal.zzv.zzc(r1)
            r0 = r8
            r2 = r10
            r3 = r11
            r4 = r5
            r5 = r9
            r0.<init>(r1, r2, r3, r4, r5)
            return r8
        L_0x005d:
            com.google.android.gms.ads.internal.zzak r8 = new com.google.android.gms.ads.internal.zzak
            com.google.android.gms.ads.internal.zzv r6 = com.google.android.gms.ads.internal.zzv.zzc(r1)
            r0 = r8
            r2 = r9
            r3 = r10
            r4 = r11
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.ClientApi.createInterstitialAdManager(com.google.android.gms.dynamic.IObjectWrapper, com.google.android.gms.internal.zzko, java.lang.String, com.google.android.gms.internal.zzwf, int):com.google.android.gms.internal.zzlt");
    }

    public zzqw createNativeAdViewDelegate(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) {
        return new zzqj((FrameLayout) zzn.zzy(iObjectWrapper), (FrameLayout) zzn.zzy(iObjectWrapper2));
    }

    public zzrb createNativeAdViewHolderDelegate(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        return new zzql((View) zzn.zzy(iObjectWrapper), (HashMap) zzn.zzy(iObjectWrapper2), (HashMap) zzn.zzy(iObjectWrapper3));
    }

    public zzaex createRewardedVideoAd(IObjectWrapper iObjectWrapper, zzwf zzwf, int i) {
        Context context = (Context) zzn.zzy(iObjectWrapper);
        zzbt.zzel();
        return new zzaeq(context, zzv.zzc(context), zzwf, new zzala(zzs.GOOGLE_PLAY_SERVICES_VERSION_CODE, i, true, zzaij.zzas(context)));
    }

    public zzlt createSearchAdManager(IObjectWrapper iObjectWrapper, zzko zzko, String str, int i) throws RemoteException {
        Context context = (Context) zzn.zzy(iObjectWrapper);
        zzbt.zzel();
        return new zzbn(context, zzko, str, new zzala(zzs.GOOGLE_PLAY_SERVICES_VERSION_CODE, i, true, zzaij.zzas(context)));
    }

    public zzmh getMobileAdsSettingsManager(IObjectWrapper iObjectWrapper) {
        return null;
    }

    public zzmh getMobileAdsSettingsManagerWithClientJarVersion(IObjectWrapper iObjectWrapper, int i) {
        Context context = (Context) zzn.zzy(iObjectWrapper);
        zzbt.zzel();
        return zzax.zza(context, new zzala(zzs.GOOGLE_PLAY_SERVICES_VERSION_CODE, i, true, zzaij.zzas(context)));
    }
}
