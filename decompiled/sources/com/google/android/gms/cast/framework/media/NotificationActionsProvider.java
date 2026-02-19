package com.google.android.gms.cast.framework.media;

import android.content.Context;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.util.List;

public abstract class NotificationActionsProvider {
    private final Context zzbky;
    private final zzf zzfec = new zza();

    class zza extends zzg {
        private zza() {
        }

        public final int[] getCompactViewActionIndices() {
            return NotificationActionsProvider.this.getCompactViewActionIndices();
        }

        public final List<NotificationAction> getNotificationActions() {
            return NotificationActionsProvider.this.getNotificationActions();
        }

        public final int zzadx() {
            return 12211278;
        }

        public final IObjectWrapper zzafg() {
            return zzn.zzz(NotificationActionsProvider.this);
        }
    }

    public NotificationActionsProvider(Context context) {
        this.zzbky = context.getApplicationContext();
    }

    public Context getApplicationContext() {
        return this.zzbky;
    }

    public abstract int[] getCompactViewActionIndices();

    public abstract List<NotificationAction> getNotificationActions();

    public final zzf zzafi() {
        return this.zzfec;
    }
}
