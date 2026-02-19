package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;

public final class zzb extends TurnBasedMatchConfig {
    private final int zzifd;
    private final Bundle zzifw;
    private final String[] zzify;
    private final int zzigh;

    zzb(TurnBasedMatchConfig.Builder builder) {
        this.zzifd = builder.zzifd;
        this.zzigh = builder.zzigh;
        this.zzifw = builder.zzifw;
        this.zzify = (String[]) builder.zzifv.toArray(new String[builder.zzifv.size()]);
    }

    public final Bundle getAutoMatchCriteria() {
        return this.zzifw;
    }

    public final String[] getInvitedPlayerIds() {
        return this.zzify;
    }

    public final int getVariant() {
        return this.zzifd;
    }

    public final int zzavv() {
        return this.zzigh;
    }
}
