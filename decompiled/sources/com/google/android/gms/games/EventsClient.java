package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.event.EventBuffer;
import com.google.android.gms.games.event.Events;
import com.google.android.gms.games.internal.api.zzp;
import com.google.android.gms.games.internal.zzg;
import com.google.android.gms.tasks.Task;

public class EventsClient extends zzp {
    private static final zzbo<Events.LoadEventsResult, EventBuffer> zzhqu = new com.google.android.gms.games.zzg();

    EventsClient(Activity activity, Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    EventsClient(Context context, Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    public void increment(String str, int i) {
        zzb(new zzf(this, str, i));
    }

    public Task<AnnotatedData<EventBuffer>> load(boolean z) {
        return zzg.zzc(Games.Events.load(zzahw(), z), zzhqu);
    }

    public Task<AnnotatedData<EventBuffer>> loadByIds(boolean z, String... strArr) {
        return zzg.zzc(Games.Events.loadByIds(zzahw(), z, strArr), zzhqu);
    }
}
