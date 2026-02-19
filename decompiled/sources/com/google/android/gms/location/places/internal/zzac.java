package com.google.android.gms.location.places.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceReport;
import com.google.android.gms.location.places.PlacesOptions;
import com.google.android.gms.location.places.zzm;
import java.util.Locale;

public final class zzac extends zzab<zzr> {
    private final Locale zziwm;
    private final zzau zzixk;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzac(Context context, Looper looper, com.google.android.gms.common.internal.zzr zzr, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, String str, PlacesOptions placesOptions) {
        super(context, looper, 67, zzr, connectionCallbacks, onConnectionFailedListener);
        Locale locale = Locale.getDefault();
        this.zziwm = locale;
        this.zzixk = new zzau(str, locale, zzr.getAccount() != null ? zzr.getAccount().name : null, (String) null, 0);
    }

    public final void zza(zzm zzm, PlaceFilter placeFilter) throws RemoteException {
        zzbq.checkNotNull(zzm, "callback == null");
        if (placeFilter == null) {
            placeFilter = PlaceFilter.zzaxd();
        }
        ((zzr) zzalw()).zza(placeFilter, this.zzixk, (zzx) zzm);
    }

    public final void zza(zzm zzm, PlaceReport placeReport) throws RemoteException {
        zzbq.checkNotNull(zzm, "callback == null");
        ((zzr) zzalw()).zza(placeReport, this.zzixk, (zzx) zzm);
    }

    /* access modifiers changed from: protected */
    public final zzr zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
        return queryLocalInterface instanceof zzr ? (zzr) queryLocalInterface : new zzs(iBinder);
    }

    /* access modifiers changed from: protected */
    public final String zzhm() {
        return "com.google.android.gms.location.places.PlaceDetectionApi";
    }

    /* access modifiers changed from: protected */
    public final String zzhn() {
        return "com.google.android.gms.location.places.internal.IGooglePlaceDetectionService";
    }
}
