package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesMetadata;
import com.google.android.gms.games.internal.api.zzp;
import com.google.android.gms.games.internal.zzg;
import com.google.android.gms.games.internal.zzo;
import com.google.android.gms.tasks.Task;

public class GamesMetadataClient extends zzp {
    private static final zzbo<GamesMetadata.LoadGamesResult, Game> zzhsm = new zzv();
    private static final zzo<GamesMetadata.LoadGamesResult> zzhsn = new zzw();

    GamesMetadataClient(Activity activity, Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    GamesMetadataClient(Context context, Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    public Task<Game> getCurrentGame() {
        return zza(new zzu(this));
    }

    public Task<AnnotatedData<Game>> loadGame() {
        return zzg.zza(Games.GamesMetadata.loadGame(zzahw()), zzhsm, zzhsn);
    }
}
