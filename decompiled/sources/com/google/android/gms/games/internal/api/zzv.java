package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.event.EventBuffer;
import com.google.android.gms.games.event.Events;

final class zzv implements Events.LoadEventsResult {
    private /* synthetic */ Status zzetp;

    zzv(zzu zzu, Status status) {
        this.zzetp = status;
    }

    public final EventBuffer getEvents() {
        return new EventBuffer(DataHolder.zzbz(14));
    }

    public final Status getStatus() {
        return this.zzetp;
    }

    public final void release() {
    }
}
