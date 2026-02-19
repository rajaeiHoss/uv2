package com.google.android.gms.games.multiplayer.realtime;

import android.os.Bundle;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.games.multiplayer.Multiplayer;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class RoomConfig {

    public static final class Builder {
        int zzifd;
        @Deprecated
        final RoomUpdateListener zzifo;
        @Deprecated
        RoomStatusUpdateListener zzifp;
        @Deprecated
        RealTimeMessageReceivedListener zzifq;
        final RoomUpdateCallback zzifr;
        RoomStatusUpdateCallback zzifs;
        OnRealTimeMessageReceivedListener zzift;
        String zzifu;
        ArrayList<String> zzifv;
        Bundle zzifw;

        private Builder(RoomUpdateCallback roomUpdateCallback) {
            this.zzifu = null;
            this.zzifd = -1;
            this.zzifv = new ArrayList<>();
            this.zzifr = (RoomUpdateCallback) zzbq.checkNotNull(roomUpdateCallback, "Must provide a RoomUpdateCallback");
            this.zzifo = null;
        }

        @Deprecated
        private Builder(RoomUpdateListener roomUpdateListener) {
            this.zzifu = null;
            this.zzifd = -1;
            this.zzifv = new ArrayList<>();
            this.zzifo = (RoomUpdateListener) zzbq.checkNotNull(roomUpdateListener, "Must provide a RoomUpdateListener");
            this.zzifr = null;
        }

        public final Builder addPlayersToInvite(ArrayList<String> arrayList) {
            zzbq.checkNotNull(arrayList);
            this.zzifv.addAll(arrayList);
            return this;
        }

        public final Builder addPlayersToInvite(String... strArr) {
            zzbq.checkNotNull(strArr);
            this.zzifv.addAll(Arrays.asList(strArr));
            return this;
        }

        public final RoomConfig build() {
            return new zzd(this);
        }

        public final Builder setAutoMatchCriteria(Bundle bundle) {
            this.zzifw = bundle;
            return this;
        }

        public final Builder setInvitationIdToAccept(String str) {
            zzbq.checkNotNull(str);
            this.zzifu = str;
            return this;
        }

        @Deprecated
        public final Builder setMessageReceivedListener(RealTimeMessageReceivedListener realTimeMessageReceivedListener) {
            this.zzifq = realTimeMessageReceivedListener;
            return this;
        }

        public final Builder setOnMessageReceivedListener(OnRealTimeMessageReceivedListener onRealTimeMessageReceivedListener) {
            this.zzift = onRealTimeMessageReceivedListener;
            return this;
        }

        public final Builder setRoomStatusUpdateCallback(RoomStatusUpdateCallback roomStatusUpdateCallback) {
            this.zzifs = roomStatusUpdateCallback;
            return this;
        }

        @Deprecated
        public final Builder setRoomStatusUpdateListener(RoomStatusUpdateListener roomStatusUpdateListener) {
            this.zzifp = roomStatusUpdateListener;
            return this;
        }

        public final Builder setVariant(int i) {
            zzbq.checkArgument(i == -1 || i > 0, "Variant must be a positive integer or Room.ROOM_VARIANT_ANY");
            this.zzifd = i;
            return this;
        }
    }

    protected RoomConfig() {
    }

    public static Builder builder(RoomUpdateCallback roomUpdateCallback) {
        return new Builder(roomUpdateCallback);
    }

    @Deprecated
    public static Builder builder(RoomUpdateListener roomUpdateListener) {
        return new Builder(roomUpdateListener);
    }

    public static Bundle createAutoMatchCriteria(int i, int i2, long j) {
        Bundle bundle = new Bundle();
        bundle.putInt(Multiplayer.EXTRA_MIN_AUTOMATCH_PLAYERS, i);
        bundle.putInt(Multiplayer.EXTRA_MAX_AUTOMATCH_PLAYERS, i2);
        bundle.putLong(Multiplayer.EXTRA_EXCLUSIVE_BIT_MASK, j);
        return bundle;
    }

    public abstract Bundle getAutoMatchCriteria();

    public abstract String getInvitationId();

    public abstract String[] getInvitedPlayerIds();

    @Deprecated
    public abstract RealTimeMessageReceivedListener getMessageReceivedListener();

    public abstract OnRealTimeMessageReceivedListener getOnMessageReceivedListener();

    public abstract RoomStatusUpdateCallback getRoomStatusUpdateCallback();

    @Deprecated
    public abstract RoomStatusUpdateListener getRoomStatusUpdateListener();

    public abstract RoomUpdateCallback getRoomUpdateCallback();

    @Deprecated
    public abstract RoomUpdateListener getRoomUpdateListener();

    public abstract int getVariant();

    public abstract zzh zzavu();
}
