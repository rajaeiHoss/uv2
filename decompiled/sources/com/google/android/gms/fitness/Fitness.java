package com.google.android.gms.fitness;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbws;
import com.google.android.gms.internal.zzbwy;
import com.google.android.gms.internal.zzbxe;
import com.google.android.gms.internal.zzbxj;
import com.google.android.gms.internal.zzbxp;
import com.google.android.gms.internal.zzbxv;
import com.google.android.gms.internal.zzbyb;
import com.google.android.gms.internal.zzbzw;
import com.google.android.gms.internal.zzcae;
import com.google.android.gms.internal.zzcaj;
import com.google.android.gms.internal.zzcam;
import com.google.android.gms.internal.zzcaw;
import com.google.android.gms.internal.zzcbd;
import com.google.android.gms.internal.zzcbh;
import com.google.android.gms.internal.zzcbt;
import java.util.concurrent.TimeUnit;

public class Fitness {
    public static final String ACTION_TRACK = "vnd.google.fitness.TRACK";
    public static final String ACTION_VIEW = "vnd.google.fitness.VIEW";
    public static final String ACTION_VIEW_GOAL = "vnd.google.fitness.VIEW_GOAL";
    @Deprecated
    public static final Void API = null;
    public static final Api<Api.ApiOptions.NoOptions> BLE_API = zzbws.API;
    public static final BleApi BleApi = (Build.VERSION.SDK_INT >= 18 ? new zzbzw() : new zzcbt());
    public static final Api<Api.ApiOptions.NoOptions> CONFIG_API = zzbwy.API;
    public static final ConfigApi ConfigApi = new zzcae();
    public static final String EXTRA_END_TIME = "vnd.google.fitness.end_time";
    public static final String EXTRA_START_TIME = "vnd.google.fitness.start_time";
    public static final Api<Api.ApiOptions.NoOptions> GOALS_API = zzbxe.API;
    public static final GoalsApi GoalsApi = new zzcaj();
    public static final Api<Api.ApiOptions.NoOptions> HISTORY_API = zzbxj.API;
    public static final HistoryApi HistoryApi = new zzcam();
    public static final Api<Api.ApiOptions.NoOptions> RECORDING_API = zzbxp.API;
    public static final RecordingApi RecordingApi = new zzcaw();
    public static final Scope SCOPE_ACTIVITY_READ = new Scope(Scopes.FITNESS_ACTIVITY_READ);
    public static final Scope SCOPE_ACTIVITY_READ_WRITE = new Scope(Scopes.FITNESS_ACTIVITY_READ_WRITE);
    public static final Scope SCOPE_BODY_READ = new Scope(Scopes.FITNESS_BODY_READ);
    public static final Scope SCOPE_BODY_READ_WRITE = new Scope(Scopes.FITNESS_BODY_READ_WRITE);
    public static final Scope SCOPE_LOCATION_READ = new Scope(Scopes.FITNESS_LOCATION_READ);
    public static final Scope SCOPE_LOCATION_READ_WRITE = new Scope(Scopes.FITNESS_LOCATION_READ_WRITE);
    public static final Scope SCOPE_NUTRITION_READ = new Scope(Scopes.FITNESS_NUTRITION_READ);
    public static final Scope SCOPE_NUTRITION_READ_WRITE = new Scope(Scopes.FITNESS_NUTRITION_READ_WRITE);
    public static final Api<Api.ApiOptions.NoOptions> SENSORS_API = zzbxv.API;
    public static final Api<Api.ApiOptions.NoOptions> SESSIONS_API = zzbyb.API;
    public static final SensorsApi SensorsApi = new zzcbd();
    public static final SessionsApi SessionsApi = new zzcbh();

    private Fitness() {
    }

    public static BleClient getBleClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount);
        return new BleClient(activity, FitnessOptions.zzb(googleSignInAccount).build());
    }

    public static BleClient getBleClient(Context context, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount);
        return new BleClient(context, FitnessOptions.zzb(googleSignInAccount).build());
    }

    public static ConfigClient getConfigClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount);
        return new ConfigClient(activity, FitnessOptions.zzb(googleSignInAccount).build());
    }

    public static ConfigClient getConfigClient(Context context, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount);
        return new ConfigClient(context, FitnessOptions.zzb(googleSignInAccount).build());
    }

    public static long getEndTime(Intent intent, TimeUnit timeUnit) {
        long longExtra = intent.getLongExtra(EXTRA_END_TIME, -1);
        if (longExtra == -1) {
            return -1;
        }
        return timeUnit.convert(longExtra, TimeUnit.MILLISECONDS);
    }

    public static GoalsClient getGoalsClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount);
        return new GoalsClient(activity, FitnessOptions.zzb(googleSignInAccount).build());
    }

    public static GoalsClient getGoalsClient(Context context, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount);
        return new GoalsClient(context, FitnessOptions.zzb(googleSignInAccount).build());
    }

    public static HistoryClient getHistoryClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount);
        return new HistoryClient(activity, FitnessOptions.zzb(googleSignInAccount).build());
    }

    public static HistoryClient getHistoryClient(Context context, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount);
        return new HistoryClient(context, FitnessOptions.zzb(googleSignInAccount).build());
    }

    public static RecordingClient getRecordingClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount);
        return new RecordingClient(activity, FitnessOptions.zzb(googleSignInAccount).build());
    }

    public static RecordingClient getRecordingClient(Context context, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount);
        return new RecordingClient(context, FitnessOptions.zzb(googleSignInAccount).build());
    }

    public static SensorsClient getSensorsClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount);
        return new SensorsClient(activity, FitnessOptions.zzb(googleSignInAccount).build());
    }

    public static SensorsClient getSensorsClient(Context context, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount);
        return new SensorsClient(context, FitnessOptions.zzb(googleSignInAccount).build());
    }

    public static SessionsClient getSessionsClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount);
        return new SessionsClient(activity, FitnessOptions.zzb(googleSignInAccount).build());
    }

    public static SessionsClient getSessionsClient(Context context, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount);
        return new SessionsClient(context, FitnessOptions.zzb(googleSignInAccount).build());
    }

    public static long getStartTime(Intent intent, TimeUnit timeUnit) {
        long longExtra = intent.getLongExtra(EXTRA_START_TIME, -1);
        if (longExtra == -1) {
            return -1;
        }
        return timeUnit.convert(longExtra, TimeUnit.MILLISECONDS);
    }
}
