package com.google.android.gms.cast.framework;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.IObjectWrapper;

public abstract class SessionProvider {
    private final Context mApplicationContext;
    private final String mCategory;
    private final zza zzfbm = new zza();

    class zza extends zzaa {
        private zza() {
        }

        public final String getCategory() {
            return SessionProvider.this.getCategory();
        }

        public final boolean isSessionRecoverable() {
            return SessionProvider.this.isSessionRecoverable();
        }

        public final int zzadx() {
            return 12211278;
        }

        public final IObjectWrapper zzfq(String str) {
            Session createSession = SessionProvider.this.createSession(str);
            if (createSession == null) {
                return null;
            }
            return createSession.zzaej();
        }
    }

    protected SessionProvider(Context context, String str) {
        this.mApplicationContext = ((Context) zzbq.checkNotNull(context)).getApplicationContext();
        this.mCategory = zzbq.zzgv(str);
    }

    public abstract Session createSession(String str);

    public final String getCategory() {
        return this.mCategory;
    }

    public final Context getContext() {
        return this.mApplicationContext;
    }

    public abstract boolean isSessionRecoverable();

    public final IBinder zzaet() {
        return this.zzfbm;
    }
}
