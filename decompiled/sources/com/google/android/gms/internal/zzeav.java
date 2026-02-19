package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.dynamite.DynamiteModule;

public final class zzeav extends zzab<zzeaz> implements zzeau {
    private static zzbhf zzehr = new zzbhf("FirebaseAuth", "FirebaseAuth:");
    private final Context mContext;
    private final zzebd zzmrc;

    public zzeav(Context context, Looper looper, zzr zzr, zzebd zzebd, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 112, zzr, connectionCallbacks, onConnectionFailedListener);
        this.mContext = (Context) zzbq.checkNotNull(context);
        this.zzmrc = zzebd;
    }

    /* access modifiers changed from: protected */
    public final Bundle zzabt() {
        Bundle zzabt = super.zzabt();
        if (zzabt == null) {
            zzabt = new Bundle();
        }
        zzebd zzebd = this.zzmrc;
        if (zzebd != null) {
            zzabt.putString("com.google.firebase.auth.API_KEY", zzebd.getApiKey());
        }
        return zzabt;
    }

    public final boolean zzahn() {
        return DynamiteModule.zzx(this.mContext, "com.google.firebase.auth") == 0;
    }

    /* access modifiers changed from: protected */
    public final String zzalq() {
        String property = zzebr.getProperty("firebear.preference");
        String str = "default";
        if (TextUtils.isEmpty(property)) {
            property = str;
        }
        property.hashCode();
        if (property.equals("local") || property.equals(str)) {
            str = property;
        }
        str.hashCode();
        if (!str.equals("local")) {
            zzehr.zze("Loading module via FirebaseOptions.", new Object[0]);
            if (this.zzmrc.zzmqj) {
                zzehr.zze("Preparing to create service connection to fallback implementation", new Object[0]);
                return this.mContext.getPackageName();
            }
            zzehr.zze("Preparing to create service connection to gms implementation", new Object[0]);
            return "com.google.android.gms";
        }
        zzehr.zze("Loading fallback module override.", new Object[0]);
        return this.mContext.getPackageName();
    }

    public final /* synthetic */ zzeaz zzbtv() throws DeadObjectException {
        return (zzeaz) super.zzalw();
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ zzeaz zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthService");
        return queryLocalInterface instanceof zzeaz ? (zzeaz) queryLocalInterface : new zzeba(iBinder);
    }

    /* access modifiers changed from: protected */
    public final String zzhm() {
        return "com.google.firebase.auth.api.gms.service.START";
    }

    /* access modifiers changed from: protected */
    public final String zzhn() {
        return "com.google.firebase.auth.api.internal.IFirebaseAuthService";
    }
}
