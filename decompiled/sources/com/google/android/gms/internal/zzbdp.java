package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.common.internal.BinderWrapper;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzr;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public final class zzbdp extends zzab<zzbeb> {
    /* access modifiers changed from: private */
    public static final zzbei zzeus = new zzbei("CastClientImpl");
    /* access modifiers changed from: private */
    public static final Object zzfmt = new Object();
    /* access modifiers changed from: private */
    public static final Object zzfmu = new Object();
    private final Bundle mExtras;
    /* access modifiers changed from: private */
    public final Cast.Listener zzetn;
    private double zzexh;
    private boolean zzexi;
    /* access modifiers changed from: private */
    public final CastDevice zzfar;
    /* access modifiers changed from: private */
    public ApplicationMetadata zzfmc;
    /* access modifiers changed from: private */
    public final Map<String, Cast.MessageReceivedCallback> zzfmd = new HashMap();
    private final long zzfme;
    private zzbdr zzfmf;
    /* access modifiers changed from: private */
    public String zzfmg;
    private boolean zzfmh;
    private boolean zzfmi;
    private boolean zzfmj;
    private int zzfmk;
    private int zzfml;
    private final AtomicLong zzfmm = new AtomicLong(0);
    /* access modifiers changed from: private */
    public String zzfmn;
    /* access modifiers changed from: private */
    public String zzfmo;
    private Bundle zzfmp;
    /* access modifiers changed from: private */
    public final Map<Long, zzn<Status>> zzfmq = new HashMap();
    /* access modifiers changed from: private */
    public zzn<Cast.ApplicationConnectionResult> zzfmr;
    /* access modifiers changed from: private */
    public zzn<Status> zzfms;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzbdp(Context context, Looper looper, zzr zzr, CastDevice castDevice, long j, Cast.Listener listener, Bundle bundle, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 10, zzr, connectionCallbacks, onConnectionFailedListener);
        this.zzfar = castDevice;
        this.zzetn = listener;
        this.zzfme = j;
        this.mExtras = bundle;
        zzago();
    }

    private final void zza(zzn<Cast.ApplicationConnectionResult> zzn) {
        synchronized (zzfmt) {
            zzn<Cast.ApplicationConnectionResult> zzn2 = this.zzfmr;
            if (zzn2 != null) {
                zzn2.setResult(new zzbdq(new Status(2002)));
            }
            this.zzfmr = zzn;
        }
    }

    /* access modifiers changed from: private */
    public final void zza(zzbdd zzbdd) {
        boolean z;
        String zzagl = zzbdd.zzagl();
        if (!zzbdw.zza(zzagl, this.zzfmg)) {
            this.zzfmg = zzagl;
            z = true;
        } else {
            z = false;
        }
        zzeus.zzb("hasChanged=%b, mFirstApplicationStatusUpdate=%b", Boolean.valueOf(z), Boolean.valueOf(this.zzfmh));
        Cast.Listener listener = this.zzetn;
        if (listener != null && (z || this.zzfmh)) {
            listener.onApplicationStatusChanged();
        }
        this.zzfmh = false;
    }

    /* access modifiers changed from: private */
    public final void zza(zzbdx zzbdx) {
        boolean z;
        boolean z2;
        boolean z3;
        ApplicationMetadata applicationMetadata = zzbdx.getApplicationMetadata();
        if (!zzbdw.zza(applicationMetadata, this.zzfmc)) {
            this.zzfmc = applicationMetadata;
            this.zzetn.onApplicationMetadataChanged(applicationMetadata);
        }
        double volume = zzbdx.getVolume();
        if (Double.isNaN(volume) || Math.abs(volume - this.zzexh) <= 1.0E-7d) {
            z = false;
        } else {
            this.zzexh = volume;
            z = true;
        }
        boolean zzagw = zzbdx.zzagw();
        if (zzagw != this.zzexi) {
            this.zzexi = zzagw;
            z = true;
        }
        zzbei zzbei = zzeus;
        zzbei.zzb("hasVolumeChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z), Boolean.valueOf(this.zzfmi));
        Cast.Listener listener = this.zzetn;
        if (listener != null && (z || this.zzfmi)) {
            listener.onVolumeChanged();
        }
        int activeInputState = zzbdx.getActiveInputState();
        if (activeInputState != this.zzfmk) {
            this.zzfmk = activeInputState;
            z2 = true;
        } else {
            z2 = false;
        }
        zzbei.zzb("hasActiveInputChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z2), Boolean.valueOf(this.zzfmi));
        Cast.Listener listener2 = this.zzetn;
        if (listener2 != null && (z2 || this.zzfmi)) {
            listener2.onActiveInputStateChanged(this.zzfmk);
        }
        int standbyState = zzbdx.getStandbyState();
        if (standbyState != this.zzfml) {
            this.zzfml = standbyState;
            z3 = true;
        } else {
            z3 = false;
        }
        zzbei.zzb("hasStandbyStateChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z3), Boolean.valueOf(this.zzfmi));
        Cast.Listener listener3 = this.zzetn;
        if (listener3 != null && (z3 || this.zzfmi)) {
            listener3.onStandbyStateChanged(this.zzfml);
        }
        this.zzfmi = false;
    }

    /* access modifiers changed from: private */
    public final void zzago() {
        this.zzfmj = false;
        this.zzfmk = -1;
        this.zzfml = -1;
        this.zzfmc = null;
        this.zzfmg = null;
        this.zzexh = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.zzexi = false;
    }

    private final void zzagq() {
        zzeus.zzb("removing all MessageReceivedCallbacks", new Object[0]);
        synchronized (this.zzfmd) {
            this.zzfmd.clear();
        }
    }

    private final void zzagr() throws IllegalStateException {
        zzbdr zzbdr;
        if (!this.zzfmj || (zzbdr = this.zzfmf) == null || zzbdr.isDisposed()) {
            throw new IllegalStateException("Not connected to a device");
        }
    }

    private final void zzc(zzn<Status> zzn) {
        synchronized (zzfmu) {
            if (this.zzfms != null) {
                zzn.setResult(new Status(2001));
            } else {
                this.zzfms = zzn;
            }
        }
    }

    public final void disconnect() {
        zzbei zzbei = zzeus;
        zzbei.zzb("disconnect(); ServiceListener=%s, isConnected=%b", this.zzfmf, Boolean.valueOf(isConnected()));
        zzbdr zzbdr = this.zzfmf;
        this.zzfmf = null;
        if (zzbdr == null || zzbdr.zzagv() == null) {
            zzbei.zzb("already disposed, so short-circuiting", new Object[0]);
            return;
        }
        zzagq();
        try {
            ((zzbeb) super.zzalw()).disconnect();
        } catch (RemoteException | IllegalStateException e) {
            zzeus.zzb(e, "Error while disconnecting the controller interface: %s", e.getMessage());
        } finally {
            super.disconnect();
        }
    }

    public final int getActiveInputState() throws IllegalStateException {
        zzagr();
        return this.zzfmk;
    }

    public final ApplicationMetadata getApplicationMetadata() throws IllegalStateException {
        zzagr();
        return this.zzfmc;
    }

    public final String getApplicationStatus() throws IllegalStateException {
        zzagr();
        return this.zzfmg;
    }

    public final int getStandbyState() throws IllegalStateException {
        zzagr();
        return this.zzfml;
    }

    public final double getVolume() throws IllegalStateException {
        zzagr();
        return this.zzexh;
    }

    public final boolean isMute() throws IllegalStateException {
        zzagr();
        return this.zzexi;
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        super.onConnectionFailed(connectionResult);
        zzagq();
    }

    public final void removeMessageReceivedCallbacks(String str) throws IllegalArgumentException, RemoteException {
        Cast.MessageReceivedCallback remove;
        if (!TextUtils.isEmpty(str)) {
            synchronized (this.zzfmd) {
                remove = this.zzfmd.remove(str);
            }
            if (remove != null) {
                try {
                    ((zzbeb) super.zzalw()).zzfz(str);
                } catch (IllegalStateException e) {
                    zzeus.zzb(e, "Error unregistering namespace (%s): %s", str, e.getMessage());
                }
            }
        } else {
            throw new IllegalArgumentException("Channel namespace cannot be null or empty");
        }
    }

    public final void requestStatus() throws IllegalStateException, RemoteException {
        ((zzbeb) super.zzalw()).requestStatus();
    }

    public final void setMessageReceivedCallbacks(String str, Cast.MessageReceivedCallback messageReceivedCallback) throws IllegalArgumentException, IllegalStateException, RemoteException {
        zzbdw.zzfv(str);
        removeMessageReceivedCallbacks(str);
        if (messageReceivedCallback != null) {
            synchronized (this.zzfmd) {
                this.zzfmd.put(str, messageReceivedCallback);
            }
            ((zzbeb) super.zzalw()).zzfy(str);
        }
    }

    public final void setMute(boolean z) throws IllegalStateException, RemoteException {
        ((zzbeb) super.zzalw()).zza(z, this.zzexh, this.zzexi);
    }

    public final void setVolume(double d) throws IllegalArgumentException, IllegalStateException, RemoteException {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            StringBuilder sb = new StringBuilder(41);
            sb.append("Volume cannot be ");
            sb.append(d);
            throw new IllegalArgumentException(sb.toString());
        }
        ((zzbeb) super.zzalw()).zza(d, this.zzexh, this.zzexi);
    }

    /* access modifiers changed from: protected */
    public final void zza(int i, IBinder iBinder, Bundle bundle, int i2) {
        zzeus.zzb("in onPostInitHandler; statusCode=%d", Integer.valueOf(i));
        if (i == 0 || i == 1001) {
            this.zzfmj = true;
            this.zzfmh = true;
            this.zzfmi = true;
        } else {
            this.zzfmj = false;
        }
        if (i == 1001) {
            Bundle bundle2 = new Bundle();
            this.zzfmp = bundle2;
            bundle2.putBoolean(Cast.EXTRA_APP_NO_LONGER_RUNNING, true);
            i = 0;
        }
        super.zza(i, iBinder, bundle, i2);
    }

    public final void zza(String str, LaunchOptions launchOptions, zzn<Cast.ApplicationConnectionResult> zzn) throws IllegalStateException, RemoteException {
        zza(zzn);
        ((zzbeb) super.zzalw()).zzb(str, launchOptions);
    }

    public final void zza(String str, zzn<Status> zzn) throws IllegalStateException, RemoteException {
        zzc(zzn);
        ((zzbeb) super.zzalw()).zzfp(str);
    }

    public final void zza(String str, String str2, com.google.android.gms.cast.zzab zzab, zzn<Cast.ApplicationConnectionResult> zzn) throws IllegalStateException, RemoteException {
        zza(zzn);
        if (zzab == null) {
            zzab = new com.google.android.gms.cast.zzab();
        }
        ((zzbeb) super.zzalw()).zza(str, str2, zzab);
    }

    public final void zza(String str, String str2, zzn<Status> zzn) throws IllegalArgumentException, IllegalStateException, RemoteException {
        if (TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("The message payload cannot be null or empty");
        } else if (str2.length() <= 65536) {
            zzbdw.zzfv(str);
            zzagr();
            long incrementAndGet = this.zzfmm.incrementAndGet();
            try {
                this.zzfmq.put(Long.valueOf(incrementAndGet), zzn);
                ((zzbeb) super.zzalw()).zzb(str, str2, incrementAndGet);
            } catch (Throwable th) {
                this.zzfmq.remove(Long.valueOf(incrementAndGet));
                throw th;
            }
        } else {
            throw new IllegalArgumentException("Message exceeds maximum size");
        }
    }

    /* access modifiers changed from: protected */
    public final Bundle zzabt() {
        Bundle bundle = new Bundle();
        zzeus.zzb("getRemoteService(): mLastApplicationId=%s, mLastSessionId=%s", this.zzfmn, this.zzfmo);
        this.zzfar.putInBundle(bundle);
        bundle.putLong("com.google.android.gms.cast.EXTRA_CAST_FLAGS", this.zzfme);
        Bundle bundle2 = this.mExtras;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
        zzbdr zzbdr = new zzbdr(this);
        this.zzfmf = zzbdr;
        bundle.putParcelable("listener", new BinderWrapper(zzbdr.asBinder()));
        String str = this.zzfmn;
        if (str != null) {
            bundle.putString("last_application_id", str);
            String str2 = this.zzfmo;
            if (str2 != null) {
                bundle.putString("last_session_id", str2);
            }
        }
        return bundle;
    }

    public final Bundle zzagp() {
        Bundle bundle = this.zzfmp;
        if (bundle == null) {
            return super.zzagp();
        }
        this.zzfmp = null;
        return bundle;
    }

    public final void zzb(zzn<Status> zzn) throws IllegalStateException, RemoteException {
        zzc(zzn);
        ((zzbeb) super.zzalw()).zzagx();
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ zzbeb zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        return queryLocalInterface instanceof zzbeb ? (zzbeb) queryLocalInterface : new zzbec(iBinder);
    }

    /* access modifiers changed from: protected */
    public final String zzhm() {
        return "com.google.android.gms.cast.service.BIND_CAST_DEVICE_CONTROLLER_SERVICE";
    }

    /* access modifiers changed from: protected */
    public final String zzhn() {
        return "com.google.android.gms.cast.internal.ICastDeviceController";
    }
}
