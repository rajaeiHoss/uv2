package com.google.android.gms.cast.framework;

import android.os.RemoteException;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;

public final class zzae<T extends Session> extends zzy {
    private final SessionManagerListener<T> zzfbk;
    private final Class<T> zzfbl;

    public zzae(SessionManagerListener<T> sessionManagerListener, Class<T> cls) {
        this.zzfbk = sessionManagerListener;
        this.zzfbl = cls;
    }

    public final void zza(IObjectWrapper iObjectWrapper, boolean z) throws RemoteException {
        Session session = (Session) zzn.zzy(iObjectWrapper);
        if (this.zzfbl.isInstance(session)) {
            this.zzfbk.onSessionResumed(this.zzfbl.cast(session), z);
        }
    }

    public final int zzadx() {
        return 12211278;
    }

    public final IObjectWrapper zzady() {
        return zzn.zzz(this.zzfbk);
    }

    public final void zzc(IObjectWrapper iObjectWrapper, String str) throws RemoteException {
        Session session = (Session) zzn.zzy(iObjectWrapper);
        if (this.zzfbl.isInstance(session)) {
            this.zzfbk.onSessionStarted(this.zzfbl.cast(session), str);
        }
    }

    public final void zzd(IObjectWrapper iObjectWrapper, String str) throws RemoteException {
        Session session = (Session) zzn.zzy(iObjectWrapper);
        if (this.zzfbl.isInstance(session)) {
            this.zzfbk.onSessionResuming(this.zzfbl.cast(session), str);
        }
    }

    public final void zze(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        Session session = (Session) zzn.zzy(iObjectWrapper);
        if (this.zzfbl.isInstance(session)) {
            this.zzfbk.onSessionStartFailed(this.zzfbl.cast(session), i);
        }
    }

    public final void zzf(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        Session session = (Session) zzn.zzy(iObjectWrapper);
        if (this.zzfbl.isInstance(session)) {
            this.zzfbk.onSessionEnded(this.zzfbl.cast(session), i);
        }
    }

    public final void zzg(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        Session session = (Session) zzn.zzy(iObjectWrapper);
        if (this.zzfbl.isInstance(session)) {
            this.zzfbk.onSessionResumeFailed(this.zzfbl.cast(session), i);
        }
    }

    public final void zzh(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        Session session = (Session) zzn.zzy(iObjectWrapper);
        if (this.zzfbl.isInstance(session)) {
            this.zzfbk.onSessionSuspended(this.zzfbl.cast(session), i);
        }
    }

    public final void zzu(IObjectWrapper iObjectWrapper) throws RemoteException {
        Session session = (Session) zzn.zzy(iObjectWrapper);
        if (this.zzfbl.isInstance(session)) {
            this.zzfbk.onSessionStarting(this.zzfbl.cast(session));
        }
    }

    public final void zzv(IObjectWrapper iObjectWrapper) throws RemoteException {
        Session session = (Session) zzn.zzy(iObjectWrapper);
        if (this.zzfbl.isInstance(session)) {
            this.zzfbk.onSessionEnding(this.zzfbl.cast(session));
        }
    }
}
