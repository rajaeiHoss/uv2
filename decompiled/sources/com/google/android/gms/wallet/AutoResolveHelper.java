package com.google.android.gms.wallet;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.util.SparseArray;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AutoResolveHelper {
    public static final int RESULT_ERROR = 1;
    /* access modifiers changed from: private */
    public static final long zzliv = TimeUnit.MINUTES.toMillis(10);
    static long zzliw = SystemClock.elapsedRealtime();

    static class zza<TResult extends AutoResolvableResult> implements OnCompleteListener<TResult>, Runnable {
        private static Handler zzlix = new Handler(Looper.getMainLooper());
        static final SparseArray<zza<?>> zzliy = new SparseArray<>(2);
        private static final AtomicInteger zzliz = new AtomicInteger();
        int zzlja;
        private zzb zzljb;
        private Task<TResult> zzljc;

        zza() {
        }

        private final void zzblr() {
            if (this.zzljc != null && this.zzljb != null) {
                zzliy.delete(this.zzlja);
                zzlix.removeCallbacks(this);
                this.zzljb.zze(this.zzljc);
            }
        }

        public static <TResult extends AutoResolvableResult> zza<TResult> zzd(Task<TResult> task) {
            zza<TResult> zza = new zza<>();
            int incrementAndGet = zzliz.incrementAndGet();
            zza.zzlja = incrementAndGet;
            zzliy.put(incrementAndGet, zza);
            zzlix.postDelayed(zza, AutoResolveHelper.zzliv);
            task.addOnCompleteListener(zza);
            return zza;
        }

        public final void onComplete(Task<TResult> task) {
            this.zzljc = task;
            zzblr();
        }

        public final void run() {
            zzliy.delete(this.zzlja);
        }

        public final void zza(zzb zzb) {
            this.zzljb = zzb;
            zzblr();
        }

        public final void zzb(zzb zzb) {
            if (this.zzljb == zzb) {
                this.zzljb = null;
            }
        }
    }

    public static class zzb extends Fragment {
        private static String zzljd = "resolveCallId";
        private static String zzlje = "requestCode";
        private static String zzljf = "initializationElapsedRealtime";
        private static String zzljg = "delivered";
        private int zzftn;
        private zza<?> zzljh;
        private boolean zzlji;

        private final void zzbls() {
            zza<?> zza = this.zzljh;
            if (zza != null) {
                zza.zzb(this);
            }
        }

        /* access modifiers changed from: private */
        public final void zze(Task<? extends AutoResolvableResult> task) {
            if (!this.zzlji) {
                this.zzlji = true;
                Activity activity = getActivity();
                activity.getFragmentManager().beginTransaction().remove(this).commit();
                if (task != null) {
                    AutoResolveHelper.zza(activity, this.zzftn, task);
                } else {
                    AutoResolveHelper.zza(activity, this.zzftn, 0, new Intent());
                }
            }
        }

        /* access modifiers changed from: private */
        public static Fragment zzp(int i, int i2) {
            Bundle bundle = new Bundle();
            bundle.putInt(zzljd, i);
            bundle.putInt(zzlje, i2);
            bundle.putLong(zzljf, AutoResolveHelper.zzliw);
            zzb zzb = new zzb();
            zzb.setArguments(bundle);
            return zzb;
        }

        public final void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            this.zzftn = getArguments().getInt(zzlje);
            this.zzljh = AutoResolveHelper.zzliw != getArguments().getLong(zzljf) ? null : zza.zzliy.get(getArguments().getInt(zzljd));
            this.zzlji = bundle != null && bundle.getBoolean(zzljg);
        }

        public final void onPause() {
            super.onPause();
            zzbls();
        }

        public final void onResume() {
            super.onResume();
            zza<?> zza = this.zzljh;
            if (zza != null) {
                zza.zza(this);
                return;
            }
            if (Log.isLoggable("AutoResolveHelper", 5)) {
                Log.w("AutoResolveHelper", "Sending canceled result for garbage collected task!");
            }
            zze((Task<? extends AutoResolvableResult>) null);
        }

        public final void onSaveInstanceState(Bundle bundle) {
            super.onSaveInstanceState(bundle);
            bundle.putBoolean(zzljg, this.zzlji);
            zzbls();
        }
    }

    private AutoResolveHelper() {
    }

    public static Status getStatusFromIntent(Intent intent) {
        if (intent == null) {
            return null;
        }
        return (Status) intent.getParcelableExtra("com.google.android.gms.common.api.AutoResolveHelper.status");
    }

    public static void putStatusIntoIntent(Intent intent, Status status) {
        if (status == null) {
            intent.removeExtra("com.google.android.gms.common.api.AutoResolveHelper.status");
        } else {
            intent.putExtra("com.google.android.gms.common.api.AutoResolveHelper.status", status);
        }
    }

    public static <TResult extends AutoResolvableResult> void resolveTask(Task<TResult> task, Activity activity, int i) {
        zza<TResult> zzd = zza.zzd(task);
        FragmentTransaction beginTransaction = activity.getFragmentManager().beginTransaction();
        Fragment zzq = zzb.zzp(zzd.zzlja, i);
        int i2 = zzd.zzlja;
        StringBuilder sb = new StringBuilder(58);
        sb.append("com.google.android.gms.wallet.AutoResolveHelper");
        sb.append(i2);
        beginTransaction.add(zzq, sb.toString()).commit();
    }

    /* access modifiers changed from: private */
    public static void zza(Activity activity, int i, int i2, Intent intent) {
        PendingIntent createPendingResult = activity.createPendingResult(i, intent, BasicMeasure.EXACTLY);
        if (createPendingResult != null) {
            try {
                createPendingResult.send(i2);
            } catch (PendingIntent.CanceledException e) {
                if (Log.isLoggable("AutoResolveHelper", 6)) {
                    Log.e("AutoResolveHelper", "Exception sending pending result", e);
                }
            }
        } else if (Log.isLoggable("AutoResolveHelper", 5)) {
            Log.w("AutoResolveHelper", "Null pending result returned when trying to deliver task result!");
        }
    }

    /* access modifiers changed from: private */
    public static void zza(Activity activity, int i, Task<? extends AutoResolvableResult> task) {
        if (activity.isFinishing()) {
            if (Log.isLoggable("AutoResolveHelper", 3)) {
                Log.d("AutoResolveHelper", "Ignoring task result for, Activity is finishing.");
            }
        } else if (task.getException() instanceof ResolvableApiException) {
            try {
                ((ResolvableApiException) task.getException()).startResolutionForResult(activity, i);
            } catch (IntentSender.SendIntentException e) {
                if (Log.isLoggable("AutoResolveHelper", 6)) {
                    Log.e("AutoResolveHelper", "Error starting pending intent!", e);
                }
            }
        } else {
            Intent intent = new Intent();
            int i2 = 1;
            if (task.isSuccessful()) {
                i2 = -1;
                ((AutoResolvableResult) task.getResult()).putIntoIntent(intent);
            } else if (task.getException() instanceof ApiException) {
                ApiException apiException = (ApiException) task.getException();
                putStatusIntoIntent(intent, new Status(apiException.getStatusCode(), apiException.getMessage(), (PendingIntent) null));
            } else {
                if (Log.isLoggable("AutoResolveHelper", 6)) {
                    Log.e("AutoResolveHelper", "Unexpected non API exception!", task.getException());
                }
                putStatusIntoIntent(intent, new Status(8, "Unexpected non API exception when trying to deliver the task result to an activity!"));
            }
            zza(activity, i, i2, intent);
        }
    }

    public static <TResult> void zza(Status status, TResult tresult, TaskCompletionSource<TResult> taskCompletionSource) {
        if (status.isSuccess()) {
            taskCompletionSource.setResult(tresult);
        } else {
            taskCompletionSource.setException(com.google.android.gms.common.internal.zzb.zzy(status));
        }
    }
}
