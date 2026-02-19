package com.google.android.gms.fitness;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.zzbj;
import com.google.android.gms.fitness.data.Goal;
import com.google.android.gms.fitness.request.GoalsReadRequest;
import com.google.android.gms.internal.zzbxe;
import com.google.android.gms.internal.zzcaj;
import com.google.android.gms.tasks.Task;
import java.util.List;

public class GoalsClient extends GoogleApi<FitnessOptions> {
    private static final GoalsApi zzhhi = new zzcaj();

    GoalsClient(Activity activity, FitnessOptions fitnessOptions) {
        super(activity, zzbxe.zzhmg, fitnessOptions, GoogleApi.zza.zzfsr);
    }

    GoalsClient(Context context, FitnessOptions fitnessOptions) {
        super(context, zzbxe.zzhmg, fitnessOptions, GoogleApi.zza.zzfsr);
    }

    public Task<List<Goal>> readCurrentGoals(GoalsReadRequest goalsReadRequest) {
        return zzbj.zza(zzhhi.readCurrentGoals(zzahw(), goalsReadRequest), zzh.zzgui);
    }
}
