package com.google.android.gms.common.api;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.internal.zzah;
import com.google.android.gms.common.api.internal.zzbm;
import com.google.android.gms.common.api.internal.zzbo;
import com.google.android.gms.common.api.internal.zzbw;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.api.internal.zzcm;
import com.google.android.gms.common.api.internal.zzcq;
import com.google.android.gms.common.api.internal.zzcv;
import com.google.android.gms.common.api.internal.zzda;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.common.api.internal.zzdo;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.common.api.internal.zzh;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzs;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collection;
import java.util.Collections;

public class GoogleApi<O extends Api.ApiOptions> {
    private final Context mContext;
    private final int mId;
    private final Looper zzalj;
    private final Api<O> zzfop;
    private final O zzfsm;
    private final zzh<O> zzfsn;
    private final GoogleApiClient zzfso;
    private final zzda zzfsp;
    protected final zzbm zzfsq;

    public static class zza {
        public static final zza zzfsr = new zzd().zzahy();
        public final zzda zzfss;
        public final Looper zzfst;

        private zza(zzda zzda, Account account, Looper looper) {
            this.zzfss = zzda;
            this.zzfst = looper;
        }

        zza(zzda zzda, Looper looper) {
            this(zzda, (Account) null, looper);
        }
    }

    public GoogleApi(Activity activity, Api<O> api, O o, zza zza2) {
        zzbq.checkNotNull(activity, "Null activity is not permitted.");
        zzbq.checkNotNull(api, "Api must not be null.");
        zzbq.checkNotNull(zza2, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        Context applicationContext = activity.getApplicationContext();
        this.mContext = applicationContext;
        this.zzfop = api;
        this.zzfsm = o;
        this.zzalj = zza2.zzfst;
        zzh<O> zza3 = zzh.zza(api, o);
        this.zzfsn = zza3;
        this.zzfso = new zzbw(this);
        zzbm zzck = zzbm.zzck(applicationContext);
        this.zzfsq = zzck;
        this.mId = zzck.zzaka();
        this.zzfsp = zza2.zzfss;
        zzah.zza(activity, zzck, zza3);
        zzck.zza((GoogleApi<?>) this);
    }

    @Deprecated
    public GoogleApi(Activity activity, Api<O> api, O o, zzda zzda) {
        this(activity, api, o, new zzd().zza(zzda).zza(activity.getMainLooper()).zzahy());
    }

    protected GoogleApi(Context context, Api<O> api, Looper looper) {
        zzbq.checkNotNull(context, "Null context is not permitted.");
        zzbq.checkNotNull(api, "Api must not be null.");
        zzbq.checkNotNull(looper, "Looper must not be null.");
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.zzfop = api;
        this.zzfsm = null;
        this.zzalj = looper;
        this.zzfsn = zzh.zzb(api);
        this.zzfso = new zzbw(this);
        zzbm zzck = zzbm.zzck(applicationContext);
        this.zzfsq = zzck;
        this.mId = zzck.zzaka();
        this.zzfsp = new zzg();
    }

    @Deprecated
    public GoogleApi(Context context, Api<O> api, O o, Looper looper, zzda zzda) {
        this(context, api, o, new zzd().zza(looper).zza(zzda).zzahy());
    }

    public GoogleApi(Context context, Api<O> api, O o, zza zza2) {
        zzbq.checkNotNull(context, "Null context is not permitted.");
        zzbq.checkNotNull(api, "Api must not be null.");
        zzbq.checkNotNull(zza2, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.zzfop = api;
        this.zzfsm = o;
        this.zzalj = zza2.zzfst;
        this.zzfsn = zzh.zza(api, o);
        this.zzfso = new zzbw(this);
        zzbm zzck = zzbm.zzck(applicationContext);
        this.zzfsq = zzck;
        this.mId = zzck.zzaka();
        this.zzfsp = zza2.zzfss;
        zzck.zza((GoogleApi<?>) this);
    }

    @Deprecated
    public GoogleApi(Context context, Api<O> api, O o, zzda zzda) {
        this(context, api, o, new zzd().zza(zzda).zzahy());
    }

    private final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zza(int i, T t) {
        t.zzaiq();
        this.zzfsq.zza(this, i, (zzm<? extends Result, Api.zzb>) t);
        return t;
    }

    private final <TResult, A extends Api.zzb> Task<TResult> zza(int i, zzde<A, TResult> zzde) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zzfsq.zza(this, i, (zzde<Api.zzb, TResult>) zzde, taskCompletionSource, this.zzfsp);
        return taskCompletionSource.getTask();
    }

    private final zzs zzahx() {
        Account account;
        GoogleSignInAccount googleSignInAccount;
        GoogleSignInAccount googleSignInAccount2;
        zzs zzs = new zzs();
        O o = this.zzfsm;
        if (!(o instanceof Api.ApiOptions.HasGoogleSignInAccountOptions) || (googleSignInAccount2 = ((Api.ApiOptions.HasGoogleSignInAccountOptions) o).getGoogleSignInAccount()) == null) {
            O o2 = this.zzfsm;
            account = o2 instanceof Api.ApiOptions.HasAccountOptions ? ((Api.ApiOptions.HasAccountOptions) o2).getAccount() : null;
        } else {
            account = googleSignInAccount2.getAccount();
        }
        zzs zze = zzs.zze(account);
        O o3 = this.zzfsm;
        Collection<Scope> scopes;
        if (!(o3 instanceof Api.ApiOptions.HasGoogleSignInAccountOptions) || (googleSignInAccount = ((Api.ApiOptions.HasGoogleSignInAccountOptions) o3).getGoogleSignInAccount()) == null) {
            scopes = Collections.emptySet();
        } else {
            scopes = googleSignInAccount.zzacf();
        }
        return zze.zze(scopes);
    }

    public final Context getApplicationContext() {
        return this.mContext;
    }

    public final int getInstanceId() {
        return this.mId;
    }

    public final Looper getLooper() {
        return this.zzalj;
    }

    public Api.zze zza(Looper looper, zzbo<O> zzbo) {
        return this.zzfop.zzahl().zza(this.mContext, looper, zzahx().zzgo(this.mContext.getPackageName()).zzgp(this.mContext.getClass().getName()).zzamn(), this.zzfsm, zzbo, zzbo);
    }

    public final <L> zzci<L> zza(L l, String str) {
        return zzcm.zzb(l, this.zzalj, str);
    }

    public zzcv zza(Context context, Handler handler) {
        return new zzcv(context, handler, zzahx().zzamn());
    }

    public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zza(T t) {
        return zza(0, t);
    }

    public final Task<Boolean> zza(zzck<?> zzck) {
        zzbq.checkNotNull(zzck, "Listener key cannot be null.");
        return this.zzfsq.zza(this, zzck);
    }

    public final <A extends Api.zzb, T extends zzcq<A, ?>, U extends zzdo<A, ?>> Task<Void> zza(T t, U u) {
        zzbq.checkNotNull(t);
        zzbq.checkNotNull(u);
        zzbq.checkNotNull(t.zzakx(), "Listener has already been released.");
        zzbq.checkNotNull(u.zzakx(), "Listener has already been released.");
        zzbq.checkArgument(t.zzakx().equals(u.zzakx()), "Listener registration and unregistration methods must be constructed with the same ListenerHolder.");
        return this.zzfsq.zza(this, (zzcq<Api.zzb, ?>) t, (zzdo<Api.zzb, ?>) u);
    }

    public final <TResult, A extends Api.zzb> Task<TResult> zza(zzde<A, TResult> zzde) {
        return zza(0, zzde);
    }

    public final Api<O> zzaht() {
        return this.zzfop;
    }

    public final O zzahu() {
        return this.zzfsm;
    }

    public final zzh<O> zzahv() {
        return this.zzfsn;
    }

    public final GoogleApiClient zzahw() {
        return this.zzfso;
    }

    public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zzb(T t) {
        return zza(1, t);
    }

    public final <TResult, A extends Api.zzb> Task<TResult> zzb(zzde<A, TResult> zzde) {
        return zza(1, zzde);
    }

    public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zzc(T t) {
        return zza(2, t);
    }
}
