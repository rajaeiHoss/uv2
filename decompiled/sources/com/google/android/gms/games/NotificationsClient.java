package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.api.zzp;
import com.google.android.gms.tasks.Task;

public class NotificationsClient extends zzp {
    public static final int NOTIFICATION_TYPES_ALL = 19;
    public static final int NOTIFICATION_TYPES_MULTIPLAYER = 3;
    public static final int NOTIFICATION_TYPE_INVITATION = 1;
    public static final int NOTIFICATION_TYPE_LEVEL_UP = 16;
    public static final int NOTIFICATION_TYPE_MATCH_UPDATE = 2;

    NotificationsClient(Activity activity, Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    NotificationsClient(Context context, Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    public Task<Void> clear(int i) {
        return zzb(new zzao(this, i));
    }

    public Task<Void> clearAll() {
        return clear(19);
    }
}
