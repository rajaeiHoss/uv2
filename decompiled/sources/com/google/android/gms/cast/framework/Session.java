package com.google.android.gms.cast.framework;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzbae;
import com.google.android.gms.internal.zzbei;

public abstract class Session {
    private static final zzbei zzeui = new zzbei("session");
    private final zzt zzfbg;
    private final zza zzfbh;

    class zza extends zzac {
        private zza() {
        }

        public final void end(boolean z) {
            Session.this.end(z);
        }

        public final long getSessionRemainingTimeMs() {
            return Session.this.getSessionRemainingTimeMs();
        }

        public final void onResuming(Bundle bundle) {
            Session.this.onResuming(bundle);
        }

        public final void onStarting(Bundle bundle) {
            Session.this.onStarting(bundle);
        }

        public final void resume(Bundle bundle) {
            Session.this.resume(bundle);
        }

        public final void start(Bundle bundle) {
            Session.this.start(bundle);
        }

        public final int zzadx() {
            return 12211278;
        }

        public final IObjectWrapper zzael() {
            return zzn.zzz(Session.this);
        }
    }

    protected Session(Context context, String str, String str2) {
        zza zza2 = new zza();
        this.zzfbh = zza2;
        this.zzfbg = zzbae.zza(context, str, str2, (zzab) zza2);
    }

    /* access modifiers changed from: protected */
    public abstract void end(boolean z);

    public final String getCategory() {
        zzbq.zzgn("Must be called from the main thread.");
        try {
            return this.zzfbg.getCategory();
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "getCategory", zzt.class.getSimpleName());
            return null;
        }
    }

    public final String getSessionId() {
        zzbq.zzgn("Must be called from the main thread.");
        try {
            return this.zzfbg.getSessionId();
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "getSessionId", zzt.class.getSimpleName());
            return null;
        }
    }

    public long getSessionRemainingTimeMs() {
        zzbq.zzgn("Must be called from the main thread.");
        return 0;
    }

    public boolean isConnected() {
        zzbq.zzgn("Must be called from the main thread.");
        try {
            return this.zzfbg.isConnected();
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "isConnected", zzt.class.getSimpleName());
            return false;
        }
    }

    public boolean isConnecting() {
        zzbq.zzgn("Must be called from the main thread.");
        try {
            return this.zzfbg.isConnecting();
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "isConnecting", zzt.class.getSimpleName());
            return false;
        }
    }

    public boolean isDisconnected() {
        zzbq.zzgn("Must be called from the main thread.");
        try {
            return this.zzfbg.isDisconnected();
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "isDisconnected", zzt.class.getSimpleName());
            return true;
        }
    }

    public boolean isDisconnecting() {
        zzbq.zzgn("Must be called from the main thread.");
        try {
            return this.zzfbg.isDisconnecting();
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "isDisconnecting", zzt.class.getSimpleName());
            return false;
        }
    }

    public boolean isResuming() {
        zzbq.zzgn("Must be called from the main thread.");
        try {
            return this.zzfbg.isResuming();
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "isResuming", zzt.class.getSimpleName());
            return false;
        }
    }

    public boolean isSuspended() {
        zzbq.zzgn("Must be called from the main thread.");
        try {
            return this.zzfbg.isSuspended();
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "isSuspended", zzt.class.getSimpleName());
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public final void notifyFailedToResumeSession(int i) {
        try {
            this.zzfbg.notifyFailedToResumeSession(i);
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "notifyFailedToResumeSession", zzt.class.getSimpleName());
        }
    }

    /* access modifiers changed from: protected */
    public final void notifyFailedToStartSession(int i) {
        try {
            this.zzfbg.notifyFailedToStartSession(i);
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "notifyFailedToStartSession", zzt.class.getSimpleName());
        }
    }

    /* access modifiers changed from: protected */
    public final void notifySessionEnded(int i) {
        try {
            this.zzfbg.notifySessionEnded(i);
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "notifySessionEnded", zzt.class.getSimpleName());
        }
    }

    /* access modifiers changed from: protected */
    public final void notifySessionResumed(boolean z) {
        try {
            this.zzfbg.notifySessionResumed(z);
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "notifySessionResumed", zzt.class.getSimpleName());
        }
    }

    /* access modifiers changed from: protected */
    public final void notifySessionStarted(String str) {
        try {
            this.zzfbg.notifySessionStarted(str);
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "notifySessionStarted", zzt.class.getSimpleName());
        }
    }

    /* access modifiers changed from: protected */
    public final void notifySessionSuspended(int i) {
        try {
            this.zzfbg.notifySessionSuspended(i);
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "notifySessionSuspended", zzt.class.getSimpleName());
        }
    }

    /* access modifiers changed from: protected */
    public void onResuming(Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public void onStarting(Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public abstract void resume(Bundle bundle);

    /* access modifiers changed from: protected */
    public abstract void start(Bundle bundle);

    public final IObjectWrapper zzaej() {
        try {
            return this.zzfbg.zzaej();
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "getWrappedObject", zzt.class.getSimpleName());
            return null;
        }
    }
}
