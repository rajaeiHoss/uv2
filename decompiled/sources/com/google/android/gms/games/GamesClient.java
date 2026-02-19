package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.api.zzp;
import com.google.android.gms.tasks.Task;

public class GamesClient extends zzp {
    GamesClient(Activity activity, Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    GamesClient(Context context, Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    public Task<Bundle> getActivationHint() {
        return zza(new zzs(this));
    }

    public Task<String> getAppId() {
        return zza(new zzq(this));
    }

    public Task<String> getCurrentAccountName() {
        return zza(new com.google.android.gms.games.zzp(this));
    }

    public Task<Integer> getSdkVariant() {
        return zza(new zzt(this));
    }

    public Task<Intent> getSettingsIntent() {
        return zza(new zzr(this));
    }

    public Task<Void> setGravityForPopups(int i) {
        return zzb(new zzn(this, i));
    }

    public Task<Void> setViewForPopups(View view) {
        return zzb(new zzo(this, view));
    }
}
