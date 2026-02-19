package com.google.firebase.appindexing.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzn implements OnCompleteListener<Status> {
    final /* synthetic */ int zzaji;
    final /* synthetic */ TaskCompletionSource zzeuo;
    final /* synthetic */ zzk zzmoi;
    final /* synthetic */ zzm zzmoj;

    zzn(zzm zzm, int i, zzk zzk, TaskCompletionSource taskCompletionSource) {
        this.zzmoj = zzm;
        this.zzaji = i;
        this.zzmoi = zzk;
        this.zzeuo = taskCompletionSource;
    }

    public final void onComplete(Task<Status> task) {
        if (this.zzaji < 10 && zzm.zzf(task)) {
            zzo zzo = new zzo(this);
            long pow = (long) (Math.pow(1.5d, (double) this.zzaji) * 250.0d * ((((Math.random() * 2.0d) - 1.0d) * 0.25d) + 1.0d));
            if (this.zzmoj.handler.postDelayed(zzo, pow)) {
                StringBuilder sb = new StringBuilder(47);
                sb.append("Task will be retried in ");
                sb.append(pow);
                sb.append(" ms");
                zzw.zzoz(sb.toString());
                return;
            }
            zzw.zzoz("Failed to schedule retry -- failing immediately!");
        }
        if (task.isSuccessful()) {
            Status result = task.getResult();
            if (result.isSuccess()) {
                this.zzeuo.setResult(null);
            } else {
                this.zzeuo.setException(zzab.zzb(result, "Indexing error, please try again."));
            }
        } else {
            this.zzeuo.setException(task.getException());
        }
    }
}
