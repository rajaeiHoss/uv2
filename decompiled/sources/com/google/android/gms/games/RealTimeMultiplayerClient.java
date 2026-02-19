package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzcm;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.api.zzp;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.zzh;
import com.google.android.gms.tasks.Task;
import java.util.List;

public class RealTimeMultiplayerClient extends zzp {

    public interface ReliableMessageSentCallback extends RealTimeMultiplayer.ReliableMessageSentCallback {
        void onRealTimeMessageSent(int i, int i2, String str);
    }

    RealTimeMultiplayerClient(Activity activity, Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    RealTimeMultiplayerClient(Context context, Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    public Task<Void> create(RoomConfig roomConfig) {
        zzci zza = zza(roomConfig.zzavu(), zzh.class.getSimpleName());
        return zza(new zzbj(this, zza, zza, roomConfig), new zzbk(this, zza.zzakx()));
    }

    public Task<Void> declineInvitation(String str) {
        return zzb(new zzbe(this, str));
    }

    public Task<Void> dismissInvitation(String str) {
        return zzb(new zzbf(this, str));
    }

    public Task<Intent> getSelectOpponentsIntent(int i, int i2) {
        return getSelectOpponentsIntent(i, i2, true);
    }

    public Task<Intent> getSelectOpponentsIntent(int i, int i2, boolean z) {
        return zza(new zzbi(this, i, i2, z));
    }

    public Task<Intent> getWaitingRoomIntent(Room room, int i) {
        return zza(new zzba(this, room, i));
    }

    public Task<Void> join(RoomConfig roomConfig) {
        zzci zza = zza(roomConfig.zzavu(), zzh.class.getSimpleName());
        return zza(new zzbl(this, zza, zza, roomConfig), new zzbm(this, zza.zzakx()));
    }

    public Task<Void> leave(RoomConfig roomConfig, String str) {
        zzci zza = zza(roomConfig.zzavu(), zzh.class.getSimpleName());
        return zza(new zzbg(this, str)).continueWithTask(new zzbq(this, zza)).continueWithTask(new zzbn(this, zza, str, roomConfig));
    }

    public Task<Integer> sendReliableMessage(byte[] bArr, String str, String str2, ReliableMessageSentCallback reliableMessageSentCallback) {
        return zzb(new zzbr(this, reliableMessageSentCallback != null ? zzcm.zzb(reliableMessageSentCallback, Looper.getMainLooper(), ReliableMessageSentCallback.class.getSimpleName()) : null, bArr, str, str2));
    }

    public Task<Void> sendUnreliableMessage(byte[] bArr, String str, String str2) {
        return zzb(new zzbb(this, bArr, str, str2));
    }

    public Task<Void> sendUnreliableMessage(byte[] bArr, String str, List<String> list) {
        return zzb(new zzbc(this, list, bArr, str));
    }

    public Task<Void> sendUnreliableMessageToOthers(byte[] bArr, String str) {
        return zzb(new zzbd(this, bArr, str));
    }
}
