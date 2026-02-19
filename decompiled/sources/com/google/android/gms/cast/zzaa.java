package com.google.android.gms.cast;

import android.view.Display;
import com.google.android.gms.cast.CastRemoteDisplayLocalService;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

final class zzaa implements OnCompleteListener<Void> {
    private /* synthetic */ CastRemoteDisplayLocalService zzevk;

    zzaa(CastRemoteDisplayLocalService castRemoteDisplayLocalService) {
        this.zzevk = castRemoteDisplayLocalService;
    }

    public final void onComplete(Task<Void> task) {
        if (!task.isSuccessful()) {
            this.zzevk.zzeb("Unable to stop the remote display, result unsuccessful");
            if (this.zzevk.zzeuw.get() != null) {
                ((CastRemoteDisplayLocalService.Callbacks) this.zzevk.zzeuw.get()).onRemoteDisplaySessionError(new Status(CastStatusCodes.ERROR_STOPPING_SERVICE_FAILED));
            }
        } else {
            this.zzevk.zzeb("remote display stopped");
        }
        Display unused = this.zzevk.zzdmh = null;
    }
}
