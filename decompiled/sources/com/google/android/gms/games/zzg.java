package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.event.EventBuffer;
import com.google.android.gms.games.event.Events;

final class zzg implements zzbo<Events.LoadEventsResult, EventBuffer> {
    zzg() {
    }

    public final EventBuffer zzb(Events.LoadEventsResult result) {
        if (result == null) {
            return null;
        }
        return result.getEvents();
    }
}
