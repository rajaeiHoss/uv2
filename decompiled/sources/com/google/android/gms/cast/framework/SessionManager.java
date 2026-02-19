package com.google.android.gms.cast.framework;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.widget.Toast;
import com.google.android.gms.R;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzbei;

public class SessionManager {
    private static final zzbei zzeui = new zzbei("SessionManager");
    private final Context mContext;
    private final zzv zzfbj;

    public SessionManager(zzv zzv, Context context) {
        this.zzfbj = zzv;
        this.mContext = context;
    }

    /* access modifiers changed from: package-private */
    public final void addCastStateListener(CastStateListener castStateListener) throws NullPointerException {
        zzbq.checkNotNull(castStateListener);
        try {
            this.zzfbj.zza((com.google.android.gms.cast.framework.zzn) new zzd(castStateListener));
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "addCastStateListener", zzv.class.getSimpleName());
        }
    }

    public void addSessionManagerListener(SessionManagerListener<Session> sessionManagerListener) throws NullPointerException {
        zzbq.zzgn("Must be called from the main thread.");
        addSessionManagerListener(sessionManagerListener, Session.class);
    }

    public <T extends Session> void addSessionManagerListener(SessionManagerListener<T> sessionManagerListener, Class<T> cls) throws NullPointerException {
        zzbq.checkNotNull(sessionManagerListener);
        zzbq.checkNotNull(cls);
        zzbq.zzgn("Must be called from the main thread.");
        try {
            this.zzfbj.zza((zzx) new zzae(sessionManagerListener, cls));
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "addSessionManagerListener", zzv.class.getSimpleName());
        }
    }

    public void endCurrentSession(boolean z) {
        zzbq.zzgn("Must be called from the main thread.");
        try {
            this.zzfbj.zzb(true, z);
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "endCurrentSession", zzv.class.getSimpleName());
        }
    }

    /* access modifiers changed from: package-private */
    public final int getCastState() {
        try {
            return this.zzfbj.getCastState();
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "addCastStateListener", zzv.class.getSimpleName());
            return 1;
        }
    }

    public CastSession getCurrentCastSession() {
        zzbq.zzgn("Must be called from the main thread.");
        Session currentSession = getCurrentSession();
        if (currentSession == null || !(currentSession instanceof CastSession)) {
            return null;
        }
        return (CastSession) currentSession;
    }

    public Session getCurrentSession() {
        zzbq.zzgn("Must be called from the main thread.");
        try {
            return (Session) zzn.zzy(this.zzfbj.zzaek());
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "getWrappedCurrentSession", zzv.class.getSimpleName());
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final void removeCastStateListener(CastStateListener castStateListener) {
        if (castStateListener != null) {
            try {
                this.zzfbj.zzb((com.google.android.gms.cast.framework.zzn) new zzd(castStateListener));
            } catch (RemoteException e) {
                zzeui.zzb(e, "Unable to call %s on %s.", "removeCastStateListener", zzv.class.getSimpleName());
            }
        }
    }

    public void removeSessionManagerListener(SessionManagerListener<Session> sessionManagerListener) {
        zzbq.zzgn("Must be called from the main thread.");
        removeSessionManagerListener(sessionManagerListener, Session.class);
    }

    public <T extends Session> void removeSessionManagerListener(SessionManagerListener<T> sessionManagerListener, Class cls) {
        zzbq.checkNotNull(cls);
        zzbq.zzgn("Must be called from the main thread.");
        if (sessionManagerListener != null) {
            try {
                this.zzfbj.zzb((zzx) new zzae(sessionManagerListener, cls));
            } catch (RemoteException e) {
                zzeui.zzb(e, "Unable to call %s on %s.", "removeSessionManagerListener", zzv.class.getSimpleName());
            }
        }
    }

    public void startSession(Intent intent) {
        try {
            Bundle extras = intent.getExtras();
            if (extras == null) {
                return;
            }
            if (extras.getString("CAST_INTENT_TO_CAST_ROUTE_ID_KEY") != null) {
                String string = extras.getString("CAST_INTENT_TO_CAST_DEVICE_NAME_KEY");
                if (!extras.getBoolean("CAST_INTENT_TO_CAST_NO_TOAST_KEY")) {
                    Toast.makeText(this.mContext, this.mContext.getString(R.string.cast_connecting_to_device, new Object[]{string}), 0).show();
                }
                this.zzfbj.zzh(new Bundle(extras));
                intent.removeExtra("CAST_INTENT_TO_CAST_ROUTE_ID_KEY");
            }
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "startSession", zzv.class.getSimpleName());
        }
    }

    public final IObjectWrapper zzaec() {
        try {
            return this.zzfbj.zzaei();
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "getWrappedThis", zzv.class.getSimpleName());
            return null;
        }
    }
}
