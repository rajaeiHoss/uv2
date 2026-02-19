package com.google.android.gms.cast.framework;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.KeyEvent;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzbae;
import com.google.android.gms.internal.zzbaf;
import com.google.android.gms.internal.zzbaj;
import com.google.android.gms.internal.zzbaw;
import com.google.android.gms.internal.zzbdj;
import com.google.android.gms.internal.zzbei;
import com.google.android.gms.internal.zzbih;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CastContext {
    public static final String OPTIONS_PROVIDER_CLASS_NAME_KEY = "com.google.android.gms.cast.framework.OPTIONS_PROVIDER_CLASS_NAME";
    private static final zzbei zzeui = new zzbei("CastContext");
    private static CastContext zzezt;
    private final Context zzbky;
    private final zzj zzezu;
    private final SessionManager zzezv;
    private final zze zzezw;
    private final PrecacheManager zzezx;
    private final MediaNotificationManager zzezy;
    private final CastOptions zzezz;
    private zzbaw zzfaa;
    private zzbaf zzfab;
    private final List<SessionProvider> zzfac;

    private CastContext(Context context, CastOptions castOptions, List<SessionProvider> list) {
        zzp zzp;
        zzv zzv;
        Context applicationContext = context.getApplicationContext();
        this.zzbky = applicationContext;
        this.zzezz = castOptions;
        this.zzfaa = new zzbaw(MediaRouter.getInstance(applicationContext));
        this.zzfac = list;
        zzaea();
        zzj zza = zzbae.zza(applicationContext, castOptions, (zzbaj) this.zzfaa, zzadz());
        this.zzezu = zza;
        PrecacheManager precacheManager = null;
        try {
            zzp = zza.zzaeh();
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "getDiscoveryManagerImpl", zzj.class.getSimpleName());
            zzp = null;
        }
        this.zzezw = zzp == null ? null : new zze(zzp);
        try {
            zzv = this.zzezu.zzaeg();
        } catch (RemoteException e2) {
            zzeui.zzb(e2, "Unable to call %s on %s.", "getSessionManagerImpl", zzj.class.getSimpleName());
            zzv = null;
        }
        SessionManager sessionManager = zzv == null ? null : new SessionManager(zzv, this.zzbky);
        this.zzezv = sessionManager;
        this.zzezy = new MediaNotificationManager(sessionManager);
        this.zzezx = sessionManager != null ? new PrecacheManager(this.zzezz, sessionManager, new zzbdj(this.zzbky)) : precacheManager;
    }

    public static CastContext getSharedInstance(Context context) throws IllegalStateException {
        zzbq.zzgn("Must be called from the main thread.");
        if (zzezt == null) {
            OptionsProvider zzbv = zzbv(context.getApplicationContext());
            zzezt = new CastContext(context, zzbv.getCastOptions(context.getApplicationContext()), zzbv.getAdditionalSessionProviders(context.getApplicationContext()));
        }
        return zzezt;
    }

    private static boolean zza(CastSession castSession, double d, boolean z) {
        if (z) {
            try {
                double volume = castSession.getVolume() + d;
                if (volume > 1.0d) {
                    volume = 1.0d;
                }
                castSession.setVolume(volume);
            } catch (IOException | IllegalStateException e) {
                zzeui.zzc("Unable to call CastSession.setVolume(double).", e);
            }
        }
        return true;
    }

    private final Map<String, IBinder> zzadz() {
        HashMap hashMap = new HashMap();
        zzbaf zzbaf = this.zzfab;
        if (zzbaf != null) {
            hashMap.put(zzbaf.getCategory(), this.zzfab.zzaet());
        }
        List<SessionProvider> list = this.zzfac;
        if (list != null) {
            for (SessionProvider next : list) {
                zzbq.checkNotNull(next, "Additional SessionProvider must not be null.");
                String zzh = zzbq.zzh(next.getCategory(), "Category for SessionProvider must not be null or empty string.");
                zzbq.checkArgument(!hashMap.containsKey(zzh), String.format("SessionProvider for category %s already added", new Object[]{zzh}));
                hashMap.put(zzh, next.zzaet());
            }
        }
        return hashMap;
    }

    private final void zzaea() {
        this.zzfab = !TextUtils.isEmpty(this.zzezz.getReceiverApplicationId()) ? new zzbaf(this.zzbky, this.zzezz, this.zzfaa) : null;
    }

    public static CastContext zzbu(Context context) throws IllegalStateException {
        zzbq.zzgn("Must be called from the main thread.");
        try {
            return getSharedInstance(context);
        } catch (RuntimeException e) {
            zzeui.zzc("Failed to load module from Google Play services. Cast will not work properly. Might due to outdated Google Play services. Ignoring this failure silently.", e);
            return null;
        }
    }

    private static OptionsProvider zzbv(Context context) throws IllegalStateException {
        try {
            Bundle bundle = zzbih.zzdd(context).getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle == null) {
                zzeui.zzc("Bundle is null", new Object[0]);
            }
            String string = bundle.getString(OPTIONS_PROVIDER_CLASS_NAME_KEY);
            if (string != null) {
                return (OptionsProvider) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            }
            throw new IllegalStateException("The fully qualified name of the implementation of OptionsProvider must be provided as a metadata in the AndroidManifest.xml with key com.google.android.gms.cast.framework.OPTIONS_PROVIDER_CLASS_NAME.");
        } catch (PackageManager.NameNotFoundException | ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | NullPointerException | InvocationTargetException e) {
            throw new IllegalStateException("Failed to initialize CastContext.", e);
        }
    }

    public final void addAppVisibilityListener(AppVisibilityListener appVisibilityListener) throws IllegalStateException, NullPointerException {
        zzbq.zzgn("Must be called from the main thread.");
        zzbq.checkNotNull(appVisibilityListener);
        try {
            this.zzezu.zza(new zza(appVisibilityListener));
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "addVisibilityChangeListener", zzj.class.getSimpleName());
        }
    }

    public final void addCastStateListener(CastStateListener castStateListener) throws IllegalStateException, NullPointerException {
        zzbq.zzgn("Must be called from the main thread.");
        zzbq.checkNotNull(castStateListener);
        this.zzezv.addCastStateListener(castStateListener);
    }

    public final CastOptions getCastOptions() throws IllegalStateException {
        zzbq.zzgn("Must be called from the main thread.");
        return this.zzezz;
    }

    public final int getCastState() {
        zzbq.zzgn("Must be called from the main thread.");
        return this.zzezv.getCastState();
    }

    public final MediaNotificationManager getMediaNotificationManager() {
        zzbq.zzgn("Must be called from the main thread.");
        return this.zzezy;
    }

    public final MediaRouteSelector getMergedSelector() throws IllegalStateException {
        zzbq.zzgn("Must be called from the main thread.");
        try {
            return MediaRouteSelector.fromBundle(this.zzezu.zzaef());
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "getMergedSelectorAsBundle", zzj.class.getSimpleName());
            return null;
        }
    }

    public final PrecacheManager getPrecacheManager() {
        zzbq.zzgn("Must be called from the main thread.");
        return this.zzezx;
    }

    public final SessionManager getSessionManager() throws IllegalStateException {
        zzbq.zzgn("Must be called from the main thread.");
        return this.zzezv;
    }

    public final boolean isAppVisible() throws IllegalStateException {
        zzbq.zzgn("Must be called from the main thread.");
        try {
            return this.zzezu.isAppVisible();
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "isApplicationVisible", zzj.class.getSimpleName());
            return false;
        }
    }

    public final boolean onDispatchVolumeKeyEventBeforeJellyBean(KeyEvent keyEvent) {
        CastSession currentCastSession;
        zzbq.zzgn("Must be called from the main thread.");
        if (zzs.zzans() || (currentCastSession = this.zzezv.getCurrentCastSession()) == null || !currentCastSession.isConnected()) {
            return false;
        }
        double volumeDeltaBeforeIceCreamSandwich = getCastOptions().getVolumeDeltaBeforeIceCreamSandwich();
        boolean z = keyEvent.getAction() == 0;
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 24) {
            zza(currentCastSession, volumeDeltaBeforeIceCreamSandwich, z);
            return true;
        } else if (keyCode != 25) {
            return false;
        } else {
            zza(currentCastSession, -volumeDeltaBeforeIceCreamSandwich, z);
            return true;
        }
    }

    public final void removeAppVisibilityListener(AppVisibilityListener appVisibilityListener) throws IllegalStateException {
        zzbq.zzgn("Must be called from the main thread.");
        if (appVisibilityListener != null) {
            try {
                this.zzezu.zzb(new zza(appVisibilityListener));
            } catch (RemoteException e) {
                zzeui.zzb(e, "Unable to call %s on %s.", "addVisibilityChangeListener", zzj.class.getSimpleName());
            }
        }
    }

    public final void removeCastStateListener(CastStateListener castStateListener) throws IllegalStateException {
        zzbq.zzgn("Must be called from the main thread.");
        if (castStateListener != null) {
            this.zzezv.removeCastStateListener(castStateListener);
        }
    }

    public final void setReceiverApplicationId(String str) {
        zzbq.zzgn("Must be called from the main thread.");
        if (!TextUtils.equals(str, this.zzezz.getReceiverApplicationId())) {
            this.zzezz.setReceiverApplicationId(str);
            zzaea();
            try {
                this.zzezu.zzd(str, zzadz());
            } catch (RemoteException e) {
                zzeui.zzb(e, "Unable to call %s on %s.", "setReceiverApplicationId", zzj.class.getSimpleName());
            }
            CastButtonFactory.zzbt(this.zzbky);
        }
    }

    public final zze zzaeb() {
        zzbq.zzgn("Must be called from the main thread.");
        return this.zzezw;
    }

    public final IObjectWrapper zzaec() {
        try {
            return this.zzezu.zzaei();
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "getWrappedThis", zzj.class.getSimpleName());
            return null;
        }
    }
}
