package com.google.android.gms.common.api.internal;

import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import androidx.collection.ArraySet;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzcyj;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzbm implements Handler.Callback {
    /* access modifiers changed from: private */
    public static final Object sLock = new Object();
    public static final Status zzfzg = new Status(4, "Sign-out occurred while this API call was in progress.");
    /* access modifiers changed from: private */
    public static final Status zzfzh = new Status(4, "The user must be signed in to make this API call.");
    private static zzbm zzfzj;
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final Handler mHandler;
    /* access modifiers changed from: private */
    public final GoogleApiAvailability zzftg;
    /* access modifiers changed from: private */
    public final Map<zzh<?>, zzbo<?>> zzfwg = new ConcurrentHashMap(5, 0.75f, 1);
    /* access modifiers changed from: private */
    public long zzfyf = 120000;
    /* access modifiers changed from: private */
    public long zzfyg = 5000;
    /* access modifiers changed from: private */
    public long zzfzi = NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
    /* access modifiers changed from: private */
    public int zzfzk = -1;
    private final AtomicInteger zzfzl = new AtomicInteger(1);
    private final AtomicInteger zzfzm = new AtomicInteger(0);
    /* access modifiers changed from: private */
    public zzah zzfzn = null;
    /* access modifiers changed from: private */
    public final Set<zzh<?>> zzfzo = new ArraySet();
    private final Set<zzh<?>> zzfzp = new ArraySet();

    private zzbm(Context context, Looper looper, GoogleApiAvailability googleApiAvailability) {
        this.mContext = context;
        Handler handler = new Handler(looper, this);
        this.mHandler = handler;
        this.zzftg = googleApiAvailability;
        handler.sendMessage(handler.obtainMessage(6));
    }

    public static zzbm zzajy() {
        zzbm zzbm;
        synchronized (sLock) {
            zzbq.checkNotNull(zzfzj, "Must guarantee manager is non-null before using getInstance");
            zzbm = zzfzj;
        }
        return zzbm;
    }

    public static void zzajz() {
        synchronized (sLock) {
            zzbm zzbm = zzfzj;
            if (zzbm != null) {
                zzbm.zzfzm.incrementAndGet();
                Handler handler = zzbm.mHandler;
                handler.sendMessageAtFrontOfQueue(handler.obtainMessage(10));
            }
        }
    }

    private final void zzakb() {
        for (zzh<?> remove : this.zzfzp) {
            this.zzfwg.remove(remove).signOut();
        }
        this.zzfzp.clear();
    }

    private final void zzb(GoogleApi<?> googleApi) {
        zzh<?> zzahv = googleApi.zzahv();
        zzbo zzbo = this.zzfwg.get(zzahv);
        if (zzbo == null) {
            zzbo = new zzbo(this, googleApi);
            this.zzfwg.put(zzahv, zzbo);
        }
        if (zzbo.zzacc()) {
            this.zzfzp.add(zzahv);
        }
        zzbo.connect();
    }

    public static zzbm zzck(Context context) {
        zzbm zzbm;
        synchronized (sLock) {
            if (zzfzj == null) {
                HandlerThread handlerThread = new HandlerThread("GoogleApiHandler", 9);
                handlerThread.start();
                zzfzj = new zzbm(context.getApplicationContext(), handlerThread.getLooper(), GoogleApiAvailability.getInstance());
            }
            zzbm = zzfzj;
        }
        return zzbm;
    }

    public final boolean handleMessage(Message message) {
        long j = 300000;
        zzbo zzbo = null;
        switch (message.what) {
            case 1:
                if (((Boolean) message.obj).booleanValue()) {
                    j = NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
                }
                this.zzfzi = j;
                this.mHandler.removeMessages(12);
                for (zzh<?> obtainMessage : this.zzfwg.keySet()) {
                    Handler handler = this.mHandler;
                    handler.sendMessageDelayed(handler.obtainMessage(12, obtainMessage), this.zzfzi);
                }
                break;
            case 2:
                zzj zzj = (zzj) message.obj;
                Iterator<zzh<?>> it = zzj.zzaii().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    } else {
                        zzh next = it.next();
                        zzbo zzbo2 = this.zzfwg.get(next);
                        if (zzbo2 == null) {
                            zzj.zza(next, new ConnectionResult(13), (String) null);
                            break;
                        } else if (zzbo2.isConnected()) {
                            zzj.zza(next, ConnectionResult.zzfqt, zzbo2.zzaix().zzahp());
                        } else if (zzbo2.zzakj() != null) {
                            zzj.zza(next, zzbo2.zzakj(), (String) null);
                        } else {
                            zzbo2.zza(zzj);
                        }
                    }
                }
            case 3:
                for (zzbo next2 : this.zzfwg.values()) {
                    next2.zzaki();
                    next2.connect();
                }
                break;
            case 4:
            case 8:
            case 13:
                zzcp zzcp = (zzcp) message.obj;
                zzbo zzbo3 = this.zzfwg.get(zzcp.zzgba.zzahv());
                if (zzbo3 == null) {
                    zzb(zzcp.zzgba);
                    zzbo3 = this.zzfwg.get(zzcp.zzgba.zzahv());
                }
                if (zzbo3.zzacc() && this.zzfzm.get() != zzcp.zzgaz) {
                    zzcp.zzgay.zzs(zzfzg);
                    zzbo3.signOut();
                    break;
                } else {
                    zzbo3.zza(zzcp.zzgay);
                    break;
                }
            case 5:
                int i = message.arg1;
                ConnectionResult connectionResult = (ConnectionResult) message.obj;
                Iterator<zzbo<?>> it2 = this.zzfwg.values().iterator();
                while (it2.hasNext()) {
                    zzbo next3 = it2.next();
                    if (next3.getInstanceId() == i) {
                        zzbo = next3;
                        break;
                    }
                }
                if (zzbo == null) {
                    StringBuilder sb = new StringBuilder(76);
                    sb.append("Could not find API instance ");
                    sb.append(i);
                    sb.append(" while trying to fail enqueued calls.");
                    Log.wtf("GoogleApiManager", sb.toString(), new Exception());
                    break;
                } else {
                    String errorString = this.zzftg.getErrorString(connectionResult.getErrorCode());
                    String errorMessage = connectionResult.getErrorMessage();
                    StringBuilder sb2 = new StringBuilder(String.valueOf(errorString).length() + 69 + String.valueOf(errorMessage).length());
                    sb2.append("Error resolution was canceled by the user, original error message: ");
                    sb2.append(errorString);
                    sb2.append(": ");
                    sb2.append(errorMessage);
                    zzbo.zzw(new Status(17, sb2.toString()));
                    break;
                }
            case 6:
                if (this.mContext.getApplicationContext() instanceof Application) {
                    zzk.zza((Application) this.mContext.getApplicationContext());
                    zzk.zzaij().zza((zzl) new zzbn(this));
                    if (!zzk.zzaij().zzbi(true)) {
                        this.zzfzi = 300000;
                        break;
                    }
                }
                break;
            case 7:
                zzb((GoogleApi<?>) (GoogleApi) message.obj);
                break;
            case 9:
                if (this.zzfwg.containsKey(message.obj)) {
                    this.zzfwg.get(message.obj).resume();
                    break;
                }
                break;
            case 10:
                zzakb();
                break;
            case 11:
                if (this.zzfwg.containsKey(message.obj)) {
                    this.zzfwg.get(message.obj).zzajr();
                    break;
                }
                break;
            case 12:
                if (this.zzfwg.containsKey(message.obj)) {
                    this.zzfwg.get(message.obj).zzakm();
                    break;
                }
                break;
            default:
                int i2 = message.what;
                StringBuilder sb3 = new StringBuilder(31);
                sb3.append("Unknown message id: ");
                sb3.append(i2);
                Log.w("GoogleApiManager", sb3.toString());
                return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final PendingIntent zza(zzh<?> zzh, int i) {
        zzcyj zzakn;
        zzbo zzbo = this.zzfwg.get(zzh);
        if (zzbo == null || (zzakn = zzbo.zzakn()) == null) {
            return null;
        }
        return PendingIntent.getActivity(this.mContext, i, zzakn.getSignInIntent(), 134217728);
    }

    public final <O extends Api.ApiOptions> Task<Boolean> zza(GoogleApi<O> googleApi, zzck<?> zzck) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        zzf zzf = new zzf(zzck, taskCompletionSource);
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(13, new zzcp(zzf, this.zzfzm.get(), googleApi)));
        return taskCompletionSource.getTask();
    }

    public final <O extends Api.ApiOptions> Task<Void> zza(GoogleApi<O> googleApi, zzcq<Api.zzb, ?> zzcq, zzdo<Api.zzb, ?> zzdo) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        zzd zzd = new zzd(new zzcr(zzcq, zzdo), taskCompletionSource);
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(8, new zzcp(zzd, this.zzfzm.get(), googleApi)));
        return taskCompletionSource.getTask();
    }

    public final Task<Map<zzh<?>, String>> zza(Iterable<? extends GoogleApi<?>> iterable) {
        zzj zzj = new zzj(iterable);
        for (GoogleApi googleApi : iterable) {
            zzbo zzbo = this.zzfwg.get(googleApi.zzahv());
            if (zzbo == null || !zzbo.isConnected()) {
                Handler handler = this.mHandler;
                handler.sendMessage(handler.obtainMessage(2, zzj));
                return zzj.getTask();
            }
            zzj.zza(googleApi.zzahv(), ConnectionResult.zzfqt, zzbo.zzaix().zzahp());
        }
        return zzj.getTask();
    }

    public final void zza(ConnectionResult connectionResult, int i) {
        if (!zzc(connectionResult, i)) {
            Handler handler = this.mHandler;
            handler.sendMessage(handler.obtainMessage(5, i, 0, connectionResult));
        }
    }

    public final void zza(GoogleApi<?> googleApi) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(7, googleApi));
    }

    public final <O extends Api.ApiOptions, TResult> void zza(GoogleApi<O> googleApi, int i, zzde<Api.zzb, TResult> zzde, TaskCompletionSource<TResult> taskCompletionSource, zzda zzda) {
        zze zze = new zze(i, zzde, taskCompletionSource, zzda);
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(4, new zzcp(zze, this.zzfzm.get(), googleApi)));
    }

    public final <O extends Api.ApiOptions> void zza(GoogleApi<O> googleApi, int i, zzm<? extends Result, Api.zzb> zzm) {
        zzc zzc = new zzc(i, zzm);
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(4, new zzcp(zzc, this.zzfzm.get(), googleApi)));
    }

    public final void zza(zzah zzah) {
        synchronized (sLock) {
            if (this.zzfzn != zzah) {
                this.zzfzn = zzah;
                this.zzfzo.clear();
                this.zzfzo.addAll(zzah.zzajf());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzaia() {
        this.zzfzm.incrementAndGet();
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(10));
    }

    public final void zzaih() {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(3));
    }

    public final int zzaka() {
        return this.zzfzl.getAndIncrement();
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzah zzah) {
        synchronized (sLock) {
            if (this.zzfzn == zzah) {
                this.zzfzn = null;
                this.zzfzo.clear();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzc(ConnectionResult connectionResult, int i) {
        return this.zzftg.zza(this.mContext, connectionResult, i);
    }
}
