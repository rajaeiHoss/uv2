package com.google.android.gms.games.internal.api;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.games.Games;

public class zzp extends GoogleApi<Games.GamesOptions> {
    protected zzp(Activity activity, Games.GamesOptions gamesOptions) {
        super(activity, Games.API, gamesOptions, GoogleApi.zza.zzfsr);
    }

    protected zzp(Context context, Games.GamesOptions gamesOptions) {
        super(context, Games.API, gamesOptions, GoogleApi.zza.zzfsr);
    }
}
