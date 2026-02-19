package com.google.android.gms.fitness;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.zzbj;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.result.SessionReadResponse;
import com.google.android.gms.internal.zzbyb;
import com.google.android.gms.internal.zzcbh;
import com.google.android.gms.tasks.Task;
import java.util.List;

public class SessionsClient extends GoogleApi<FitnessOptions> {
    private static final SessionsApi zzhhu = new zzcbh();

    SessionsClient(Activity activity, FitnessOptions fitnessOptions) {
        super(activity, zzbyb.zzhmg, fitnessOptions, GoogleApi.zza.zzfsr);
    }

    SessionsClient(Context context, FitnessOptions fitnessOptions) {
        super(context, zzbyb.zzhmg, fitnessOptions, GoogleApi.zza.zzfsr);
    }

    public Task<Void> insertSession(SessionInsertRequest sessionInsertRequest) {
        return zzbj.zzb(zzhhu.insertSession(zzahw(), sessionInsertRequest));
    }

    public Task<SessionReadResponse> readSession(SessionReadRequest sessionReadRequest) {
        return zzbj.zza(zzhhu.readSession(zzahw(), sessionReadRequest), new SessionReadResponse());
    }

    public Task<Void> registerForSessions(PendingIntent pendingIntent) {
        return zzbj.zzb(zzhhu.registerForSessions(zzahw(), pendingIntent));
    }

    public Task<Void> startSession(Session session) {
        return zzbj.zzb(zzhhu.startSession(zzahw(), session));
    }

    public Task<List<Session>> stopSession(String str) {
        return zzbj.zza(zzhhu.stopSession(zzahw(), str), zzp.zzgui);
    }

    public Task<Void> unregisterForSessions(PendingIntent pendingIntent) {
        return zzbj.zzb(zzhhu.unregisterForSessions(zzahw(), pendingIntent));
    }
}
