package com.google.android.gms.games.multiplayer.realtime;

import android.os.Bundle;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import java.util.Objects;

public final class zzd extends RoomConfig {
    private final String zzeha;
    private final int zzifd;
    @Deprecated
    private final RoomUpdateListener zzifo;
    @Deprecated
    private final RoomStatusUpdateListener zzifp;
    @Deprecated
    private final RealTimeMessageReceivedListener zzifq;
    private final RoomUpdateCallback zzifr;
    private final RoomStatusUpdateCallback zzifs;
    private final OnRealTimeMessageReceivedListener zzift;
    private final Bundle zzifw;
    private final zzg zzifx;
    private final String[] zzify;

    zzd(RoomConfig.Builder builder) {
        this.zzifo = builder.zzifo;
        this.zzifp = builder.zzifp;
        RealTimeMessageReceivedListener realTimeMessageReceivedListener = builder.zzifq;
        this.zzifq = realTimeMessageReceivedListener;
        RoomUpdateCallback roomUpdateCallback = builder.zzifr;
        this.zzifr = roomUpdateCallback;
        RoomStatusUpdateCallback roomStatusUpdateCallback = builder.zzifs;
        this.zzifs = roomStatusUpdateCallback;
        OnRealTimeMessageReceivedListener onRealTimeMessageReceivedListener = builder.zzift;
        this.zzift = onRealTimeMessageReceivedListener;
        if (roomStatusUpdateCallback != null) {
            this.zzifx = new zzg(roomUpdateCallback, roomStatusUpdateCallback, onRealTimeMessageReceivedListener);
        } else {
            this.zzifx = null;
        }
        this.zzeha = builder.zzifu;
        this.zzifd = builder.zzifd;
        this.zzifw = builder.zzifw;
        this.zzify = (String[]) builder.zzifv.toArray(new String[builder.zzifv.size()]);
        if (onRealTimeMessageReceivedListener == null) {
            Objects.requireNonNull(realTimeMessageReceivedListener, "Must specify a message listener");
        }
    }

    public final Bundle getAutoMatchCriteria() {
        return this.zzifw;
    }

    public final String getInvitationId() {
        return this.zzeha;
    }

    public final String[] getInvitedPlayerIds() {
        return this.zzify;
    }

    @Deprecated
    public final RealTimeMessageReceivedListener getMessageReceivedListener() {
        return this.zzifq;
    }

    public final OnRealTimeMessageReceivedListener getOnMessageReceivedListener() {
        return this.zzift;
    }

    public final RoomStatusUpdateCallback getRoomStatusUpdateCallback() {
        return this.zzifs;
    }

    @Deprecated
    public final RoomStatusUpdateListener getRoomStatusUpdateListener() {
        return this.zzifp;
    }

    public final RoomUpdateCallback getRoomUpdateCallback() {
        return this.zzifr;
    }

    @Deprecated
    public final RoomUpdateListener getRoomUpdateListener() {
        return this.zzifo;
    }

    public final int getVariant() {
        return this.zzifd;
    }

    public final zzh zzavu() {
        return this.zzifx;
    }
}
