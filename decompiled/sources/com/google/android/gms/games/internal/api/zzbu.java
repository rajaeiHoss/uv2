package com.google.android.gms.games.internal.api;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import java.util.List;

public final class zzbu implements RealTimeMultiplayer {
    private static zzci<RoomUpdateListener> zza(GoogleApiClient googleApiClient, RoomConfig roomConfig) {
        return googleApiClient.zzt(roomConfig.getRoomUpdateCallback() != null ? roomConfig.getRoomUpdateCallback() : roomConfig.getRoomUpdateListener());
    }

    private static <L> zzci<L> zza(GoogleApiClient googleApiClient, L l) {
        if (l == null) {
            return null;
        }
        return googleApiClient.zzt(l);
    }

    private static zzci<RoomStatusUpdateListener> zzb(GoogleApiClient googleApiClient, RoomConfig roomConfig) {
        return zza(googleApiClient, roomConfig.getRoomStatusUpdateCallback() != null ? roomConfig.getRoomStatusUpdateCallback() : roomConfig.getRoomStatusUpdateListener());
    }

    private static zzci<RealTimeMessageReceivedListener> zzc(GoogleApiClient googleApiClient, RoomConfig roomConfig) {
        return googleApiClient.zzt(roomConfig.getOnMessageReceivedListener() != null ? roomConfig.getOnMessageReceivedListener() : roomConfig.getMessageReceivedListener());
    }

    public final void create(GoogleApiClient googleApiClient, RoomConfig roomConfig) {
        GamesClientImpl zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzb((zzci<? extends RoomUpdateListener>) zza(googleApiClient, roomConfig), (zzci<? extends RoomStatusUpdateListener>) zzb(googleApiClient, roomConfig), (zzci<? extends RealTimeMessageReceivedListener>) zzc(googleApiClient, roomConfig), roomConfig);
        }
    }

    public final void declineInvitation(GoogleApiClient googleApiClient, String str) {
        GamesClientImpl zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzt(str, 0);
        }
    }

    public final void dismissInvitation(GoogleApiClient googleApiClient, String str) {
        GamesClientImpl zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzr(str, 0);
        }
    }

    public final Intent getSelectOpponentsIntent(GoogleApiClient googleApiClient, int i, int i2) {
        return Games.zzg(googleApiClient).zze(i, i2, true);
    }

    public final Intent getSelectOpponentsIntent(GoogleApiClient googleApiClient, int i, int i2, boolean z) {
        return Games.zzg(googleApiClient).zze(i, i2, z);
    }

    public final Intent getWaitingRoomIntent(GoogleApiClient googleApiClient, Room room, int i) {
        return Games.zzg(googleApiClient).zzb(room, i);
    }

    public final void join(GoogleApiClient googleApiClient, RoomConfig roomConfig) {
        GamesClientImpl zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            googleApiClient.zzt(roomConfig.getRoomUpdateListener());
            zza(googleApiClient, roomConfig.getRoomStatusUpdateListener());
            zza(googleApiClient, roomConfig.getMessageReceivedListener());
            zza.zzd(zza(googleApiClient, roomConfig), zzb(googleApiClient, roomConfig), zzc(googleApiClient, roomConfig), roomConfig);
        }
    }

    public final void leave(GoogleApiClient googleApiClient, RoomUpdateListener roomUpdateListener, String str) {
        GamesClientImpl zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zza((zzci<? extends RoomUpdateListener>) googleApiClient.zzt(roomUpdateListener), str);
        }
    }

    public final int sendReliableMessage(GoogleApiClient googleApiClient, RealTimeMultiplayer.ReliableMessageSentCallback reliableMessageSentCallback, byte[] bArr, String str, String str2) {
        return Games.zzg(googleApiClient).zzb((zzci<RealTimeMultiplayer.ReliableMessageSentCallback>) zza(googleApiClient, reliableMessageSentCallback), bArr, str, str2);
    }

    public final int sendUnreliableMessage(GoogleApiClient googleApiClient, byte[] bArr, String str, String str2) {
        return Games.zzg(googleApiClient).zza(bArr, str, new String[]{str2});
    }

    public final int sendUnreliableMessage(GoogleApiClient googleApiClient, byte[] bArr, String str, List<String> list) {
        return Games.zzg(googleApiClient).zza(bArr, str, (String[]) list.toArray(new String[list.size()]));
    }

    public final int sendUnreliableMessageToOthers(GoogleApiClient googleApiClient, byte[] bArr, String str) {
        return Games.zzg(googleApiClient).zze(bArr, str);
    }
}
