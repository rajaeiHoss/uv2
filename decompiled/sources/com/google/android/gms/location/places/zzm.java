package com.google.android.gms.location.places;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzchx;
import com.google.android.gms.location.places.internal.zzy;

public class zzm extends zzy {
    private static final String TAG = "zzm";
    private final zzd zziwe;
    private final zza zziwf;
    private final zze zziwg;
    private final zzf zziwh;
    private final zzc zziwi;

    public static abstract class zza<A extends Api.zze> extends zzb<AutocompletePredictionBuffer, A> {
        public zza(Api api, GoogleApiClient googleApiClient) {
            super(api, googleApiClient);
        }

        /* access modifiers changed from: protected */
        public final AutocompletePredictionBuffer zzb(Status status) {
            return new AutocompletePredictionBuffer(DataHolder.zzbz(status.getStatusCode()));
        }
    }

    public static abstract class zzb<R extends Result, A extends Api.zze> extends com.google.android.gms.common.api.internal.zzm<R, A> {
        public zzb(Api api, GoogleApiClient googleApiClient) {
            super((Api<?>) api, googleApiClient);
        }
    }

    public static abstract class zzc<A extends Api.zze> extends zzb<PlaceBuffer, A> {
        public zzc(Api api, GoogleApiClient googleApiClient) {
            super(api, googleApiClient);
        }

        /* access modifiers changed from: protected */
        public final PlaceBuffer zzb(Status status) {
            return new PlaceBuffer(DataHolder.zzbz(status.getStatusCode()));
        }
    }

    public static abstract class zzd<A extends Api.zze> extends zzb<PlaceLikelihoodBuffer, A> {
        public zzd(Api api, GoogleApiClient googleApiClient) {
            super(api, googleApiClient);
        }

        /* access modifiers changed from: protected */
        public final PlaceLikelihoodBuffer zzb(Status status) {
            return new PlaceLikelihoodBuffer(DataHolder.zzbz(status.getStatusCode()), 100);
        }
    }

    @Deprecated
    public static abstract class zze<A extends Api.zze> extends zzb<zzchx, A> {
        public zze(Api api, GoogleApiClient googleApiClient) {
            super(api, googleApiClient);
        }
    }

    public static abstract class zzf<A extends Api.zze> extends zzb<Status, A> {
        public zzf(Api api, GoogleApiClient googleApiClient) {
            super(api, googleApiClient);
        }

        /* access modifiers changed from: protected */
        public final Status zzb(Status status) {
            return status;
        }
    }

    public zzm(zza zza2) {
        this.zziwe = null;
        this.zziwf = zza2;
        this.zziwg = null;
        this.zziwh = null;
        this.zziwi = null;
    }

    public zzm(zzc zzc2) {
        this.zziwe = null;
        this.zziwf = null;
        this.zziwg = null;
        this.zziwh = null;
        this.zziwi = zzc2;
    }

    public zzm(zzd zzd2) {
        this.zziwe = zzd2;
        this.zziwf = null;
        this.zziwg = null;
        this.zziwh = null;
        this.zziwi = null;
    }

    public zzm(zzf zzf2) {
        this.zziwe = null;
        this.zziwf = null;
        this.zziwg = null;
        this.zziwh = zzf2;
        this.zziwi = null;
    }

    public final void zzao(Status status) throws RemoteException {
        this.zziwh.setResult(status);
    }

    public final void zzbk(DataHolder dataHolder) throws RemoteException {
        zzbq.zza(this.zziwe != null, (Object) "placeEstimator cannot be null");
        if (dataHolder == null) {
            String str = TAG;
            if (Log.isLoggable(str, 6)) {
                Log.e(str, "onPlaceEstimated received null DataHolder", new Throwable());
            }
            this.zziwe.zzu(Status.zzfts);
            return;
        }
        Bundle zzahs = dataHolder.zzahs();
        this.zziwe.setResult(new PlaceLikelihoodBuffer(dataHolder, zzahs == null ? 100 : PlaceLikelihoodBuffer.zzab(zzahs)));
    }

    public final void zzbl(DataHolder dataHolder) throws RemoteException {
        if (dataHolder == null) {
            String str = TAG;
            if (Log.isLoggable(str, 6)) {
                Log.e(str, "onAutocompletePrediction received null DataHolder", new Throwable());
            }
            this.zziwf.zzu(Status.zzfts);
            return;
        }
        this.zziwf.setResult(new AutocompletePredictionBuffer(dataHolder));
    }

    public final void zzbm(DataHolder dataHolder) throws RemoteException {
        com.google.android.gms.common.api.internal.zzm zzm = null;
        if (dataHolder == null) {
            String str = TAG;
            if (Log.isLoggable(str, 6)) {
                Log.e(str, "onPlaceUserDataFetched received null DataHolder", new Throwable());
            }
            zzm.zzu(Status.zzfts);
            return;
        }
        zzm.setResult(new zzchx(dataHolder));
    }

    public final void zzbn(DataHolder dataHolder) throws RemoteException {
        this.zziwi.setResult(new PlaceBuffer(dataHolder));
    }
}
