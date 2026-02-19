package com.google.android.gms.internal;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.wallet.WalletConstants;
import java.lang.ref.WeakReference;

final class zzdmw extends zzdmx {
    private final WeakReference<Activity> zzehd;
    private final int zzftn;

    public zzdmw(Activity activity, int i) {
        this.zzehd = new WeakReference<>(activity);
        this.zzftn = i;
    }

    /* JADX WARNING: type inference failed for: r6v6, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(int r4, com.google.android.gms.wallet.FullWallet r5, android.os.Bundle r6) {
        /*
            r3 = this;
            java.lang.ref.WeakReference<android.app.Activity> r0 = r3.zzehd
            java.lang.Object r0 = r0.get()
            android.app.Activity r0 = (android.app.Activity) r0
            java.lang.String r1 = "WalletClientImpl"
            if (r0 != 0) goto L_0x0012
            java.lang.String r4 = "Ignoring onFullWalletLoaded, Activity has gone"
            android.util.Log.d(r1, r4)
            return
        L_0x0012:
            r2 = 0
            if (r6 == 0) goto L_0x001e
            java.lang.String r2 = "com.google.android.gms.wallet.EXTRA_PENDING_INTENT"
            android.os.Parcelable r6 = r6.getParcelable(r2)
            r2 = r6
            android.app.PendingIntent r2 = (android.app.PendingIntent) r2
        L_0x001e:
            com.google.android.gms.common.ConnectionResult r6 = new com.google.android.gms.common.ConnectionResult
            r6.<init>(r4, r2)
            boolean r2 = r6.hasResolution()
            if (r2 == 0) goto L_0x0036
            int r4 = r3.zzftn     // Catch:{ SendIntentException -> 0x002f }
            r6.startResolutionForResult(r0, r4)     // Catch:{ SendIntentException -> 0x002f }
            return
        L_0x002f:
            r4 = move-exception
            java.lang.String r5 = "Exception starting pending intent"
            android.util.Log.w(r1, r5, r4)
            return
        L_0x0036:
            android.content.Intent r2 = new android.content.Intent
            r2.<init>()
            boolean r6 = r6.isSuccess()
            if (r6 == 0) goto L_0x0048
            r4 = -1
            java.lang.String r6 = "com.google.android.gms.wallet.EXTRA_FULL_WALLET"
            r2.putExtra(r6, r5)
            goto L_0x0055
        L_0x0048:
            r5 = 408(0x198, float:5.72E-43)
            if (r4 != r5) goto L_0x004e
            r5 = 0
            goto L_0x004f
        L_0x004e:
            r5 = 1
        L_0x004f:
            java.lang.String r6 = "com.google.android.gms.wallet.EXTRA_ERROR_CODE"
            r2.putExtra(r6, r4)
            r4 = r5
        L_0x0055:
            int r5 = r3.zzftn
            r6 = 1073741824(0x40000000, float:2.0)
            android.app.PendingIntent r5 = r0.createPendingResult(r5, r2, r6)
            if (r5 != 0) goto L_0x0065
            java.lang.String r4 = "Null pending result returned for onFullWalletLoaded"
            android.util.Log.w(r1, r4)
            return
        L_0x0065:
            r5.send(r4)     // Catch:{ CanceledException -> 0x0069 }
            return
        L_0x0069:
            r4 = move-exception
            java.lang.String r5 = "Exception setting pending result"
            android.util.Log.w(r1, r5, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzdmw.zza(int, com.google.android.gms.wallet.FullWallet, android.os.Bundle):void");
    }

    /* JADX WARNING: type inference failed for: r6v6, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(int r4, com.google.android.gms.wallet.MaskedWallet r5, android.os.Bundle r6) {
        /*
            r3 = this;
            java.lang.ref.WeakReference<android.app.Activity> r0 = r3.zzehd
            java.lang.Object r0 = r0.get()
            android.app.Activity r0 = (android.app.Activity) r0
            java.lang.String r1 = "WalletClientImpl"
            if (r0 != 0) goto L_0x0012
            java.lang.String r4 = "Ignoring onMaskedWalletLoaded, Activity has gone"
            android.util.Log.d(r1, r4)
            return
        L_0x0012:
            r2 = 0
            if (r6 == 0) goto L_0x001e
            java.lang.String r2 = "com.google.android.gms.wallet.EXTRA_PENDING_INTENT"
            android.os.Parcelable r6 = r6.getParcelable(r2)
            r2 = r6
            android.app.PendingIntent r2 = (android.app.PendingIntent) r2
        L_0x001e:
            com.google.android.gms.common.ConnectionResult r6 = new com.google.android.gms.common.ConnectionResult
            r6.<init>(r4, r2)
            boolean r2 = r6.hasResolution()
            if (r2 == 0) goto L_0x0036
            int r4 = r3.zzftn     // Catch:{ SendIntentException -> 0x002f }
            r6.startResolutionForResult(r0, r4)     // Catch:{ SendIntentException -> 0x002f }
            return
        L_0x002f:
            r4 = move-exception
            java.lang.String r5 = "Exception starting pending intent"
            android.util.Log.w(r1, r5, r4)
            return
        L_0x0036:
            android.content.Intent r2 = new android.content.Intent
            r2.<init>()
            boolean r6 = r6.isSuccess()
            if (r6 == 0) goto L_0x0048
            r4 = -1
            java.lang.String r6 = "com.google.android.gms.wallet.EXTRA_MASKED_WALLET"
            r2.putExtra(r6, r5)
            goto L_0x0055
        L_0x0048:
            r5 = 408(0x198, float:5.72E-43)
            if (r4 != r5) goto L_0x004e
            r5 = 0
            goto L_0x004f
        L_0x004e:
            r5 = 1
        L_0x004f:
            java.lang.String r6 = "com.google.android.gms.wallet.EXTRA_ERROR_CODE"
            r2.putExtra(r6, r4)
            r4 = r5
        L_0x0055:
            int r5 = r3.zzftn
            r6 = 1073741824(0x40000000, float:2.0)
            android.app.PendingIntent r5 = r0.createPendingResult(r5, r2, r6)
            if (r5 != 0) goto L_0x0065
            java.lang.String r4 = "Null pending result returned for onMaskedWalletLoaded"
            android.util.Log.w(r1, r4)
            return
        L_0x0065:
            r5.send(r4)     // Catch:{ CanceledException -> 0x0069 }
            return
        L_0x0069:
            r4 = move-exception
            java.lang.String r5 = "Exception setting pending result"
            android.util.Log.w(r1, r5, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzdmw.zza(int, com.google.android.gms.wallet.MaskedWallet, android.os.Bundle):void");
    }

    public final void zza(int i, boolean z, Bundle bundle) {
        Activity activity = (Activity) this.zzehd.get();
        if (activity == null) {
            Log.d("WalletClientImpl", "Ignoring onPreAuthorizationDetermined, Activity has gone");
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(WalletConstants.EXTRA_IS_USER_PREAUTHORIZED, z);
        PendingIntent createPendingResult = activity.createPendingResult(this.zzftn, intent, BasicMeasure.EXACTLY);
        if (createPendingResult == null) {
            Log.w("WalletClientImpl", "Null pending result returned for onPreAuthorizationDetermined");
            return;
        }
        try {
            createPendingResult.send(-1);
        } catch (PendingIntent.CanceledException e) {
            Log.w("WalletClientImpl", "Exception setting pending result", e);
        }
    }

    public final void zzl(int i, Bundle bundle) {
        zzbq.checkNotNull(bundle, "Bundle should not be null");
        Activity activity = (Activity) this.zzehd.get();
        if (activity == null) {
            Log.d("WalletClientImpl", "Ignoring onWalletObjectsCreated, Activity has gone");
            return;
        }
        ConnectionResult connectionResult = new ConnectionResult(i, (PendingIntent) bundle.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT"));
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(activity, this.zzftn);
            } catch (IntentSender.SendIntentException e) {
                Log.w("WalletClientImpl", "Exception starting pending intent", e);
            }
        } else {
            String valueOf = String.valueOf(connectionResult);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 75);
            sb.append("Create Wallet Objects confirmation UI will not be shown connection result: ");
            sb.append(valueOf);
            Log.e("WalletClientImpl", sb.toString());
            Intent intent = new Intent();
            intent.putExtra(WalletConstants.EXTRA_ERROR_CODE, WalletConstants.ERROR_CODE_UNKNOWN);
            PendingIntent createPendingResult = activity.createPendingResult(this.zzftn, intent, BasicMeasure.EXACTLY);
            if (createPendingResult == null) {
                Log.w("WalletClientImpl", "Null pending result returned for onWalletObjectsCreated");
                return;
            }
            try {
                createPendingResult.send(1);
            } catch (PendingIntent.CanceledException e2) {
                Log.w("WalletClientImpl", "Exception setting pending result", e2);
            }
        }
    }
}
