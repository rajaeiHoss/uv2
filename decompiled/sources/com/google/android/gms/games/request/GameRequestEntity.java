package com.google.android.gms.games.request;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.internal.zzc;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Deprecated
public final class GameRequestEntity extends zzc implements GameRequest {
    public static final Parcelable.Creator<GameRequestEntity> CREATOR = new zza();
    private final long mCreationTimestamp;
    private final int zzcfl;
    private final String zzcwj;
    private final int zzenu;
    private final GameEntity zzibx;
    private final byte[] zzigl;
    private final PlayerEntity zzihf;
    private final ArrayList<PlayerEntity> zzihg;
    private final long zzihh;
    private final Bundle zzihi;

    GameRequestEntity(GameEntity gameEntity, PlayerEntity playerEntity, byte[] bArr, String str, ArrayList<PlayerEntity> arrayList, int i, long j, long j2, Bundle bundle, int i2) {
        this.zzibx = gameEntity;
        this.zzihf = playerEntity;
        this.zzigl = bArr;
        this.zzcwj = str;
        this.zzihg = arrayList;
        this.zzenu = i;
        this.mCreationTimestamp = j;
        this.zzihh = j2;
        this.zzihi = bundle;
        this.zzcfl = i2;
    }

    public GameRequestEntity(GameRequest gameRequest) {
        this.zzibx = new GameEntity(gameRequest.getGame());
        this.zzihf = new PlayerEntity(gameRequest.getSender());
        this.zzcwj = gameRequest.getRequestId();
        this.zzenu = gameRequest.getType();
        this.mCreationTimestamp = gameRequest.getCreationTimestamp();
        this.zzihh = gameRequest.getExpirationTimestamp();
        this.zzcfl = gameRequest.getStatus();
        byte[] data = gameRequest.getData();
        if (data == null) {
            this.zzigl = null;
        } else {
            byte[] bArr = new byte[data.length];
            this.zzigl = bArr;
            System.arraycopy(data, 0, bArr, 0, data.length);
        }
        List<Player> recipients = gameRequest.getRecipients();
        int size = recipients.size();
        this.zzihg = new ArrayList<>(size);
        this.zzihi = new Bundle();
        for (int i = 0; i < size; i++) {
            Player player = (Player) recipients.get(i).freeze();
            String playerId = player.getPlayerId();
            this.zzihg.add((PlayerEntity) player);
            this.zzihi.putInt(playerId, gameRequest.getRecipientStatus(playerId));
        }
    }

    static int zza(GameRequest gameRequest) {
        return Arrays.hashCode(new Object[]{gameRequest.getGame(), gameRequest.getRecipients(), gameRequest.getRequestId(), gameRequest.getSender(), zzb(gameRequest), Integer.valueOf(gameRequest.getType()), Long.valueOf(gameRequest.getCreationTimestamp()), Long.valueOf(gameRequest.getExpirationTimestamp())});
    }

    static boolean zza(GameRequest gameRequest, Object obj) {
        if (!(obj instanceof GameRequest)) {
            return false;
        }
        if (gameRequest == obj) {
            return true;
        }
        GameRequest gameRequest2 = (GameRequest) obj;
        return zzbg.equal(gameRequest2.getGame(), gameRequest.getGame()) && zzbg.equal(gameRequest2.getRecipients(), gameRequest.getRecipients()) && zzbg.equal(gameRequest2.getRequestId(), gameRequest.getRequestId()) && zzbg.equal(gameRequest2.getSender(), gameRequest.getSender()) && Arrays.equals(zzb(gameRequest2), zzb(gameRequest)) && zzbg.equal(Integer.valueOf(gameRequest2.getType()), Integer.valueOf(gameRequest.getType())) && zzbg.equal(Long.valueOf(gameRequest2.getCreationTimestamp()), Long.valueOf(gameRequest.getCreationTimestamp())) && zzbg.equal(Long.valueOf(gameRequest2.getExpirationTimestamp()), Long.valueOf(gameRequest.getExpirationTimestamp()));
    }

    private static int[] zzb(GameRequest gameRequest) {
        List<Player> recipients = gameRequest.getRecipients();
        int size = recipients.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = gameRequest.getRecipientStatus(recipients.get(i).getPlayerId());
        }
        return iArr;
    }

    static String zzc(GameRequest gameRequest) {
        return zzbg.zzx(gameRequest).zzg("Game", gameRequest.getGame()).zzg("Sender", gameRequest.getSender()).zzg("Recipients", gameRequest.getRecipients()).zzg("Data", gameRequest.getData()).zzg("RequestId", gameRequest.getRequestId()).zzg("Type", Integer.valueOf(gameRequest.getType())).zzg("CreationTimestamp", Long.valueOf(gameRequest.getCreationTimestamp())).zzg("ExpirationTimestamp", Long.valueOf(gameRequest.getExpirationTimestamp())).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    public final GameRequest freeze() {
        return this;
    }

    public final long getCreationTimestamp() {
        return this.mCreationTimestamp;
    }

    public final byte[] getData() {
        return this.zzigl;
    }

    public final long getExpirationTimestamp() {
        return this.zzihh;
    }

    public final Game getGame() {
        return this.zzibx;
    }

    public final int getRecipientStatus(String str) {
        return this.zzihi.getInt(str, 0);
    }

    public final List<Player> getRecipients() {
        return new ArrayList(this.zzihg);
    }

    public final String getRequestId() {
        return this.zzcwj;
    }

    public final Player getSender() {
        return this.zzihf;
    }

    public final int getStatus() {
        return this.zzcfl;
    }

    public final int getType() {
        return this.zzenu;
    }

    public final int hashCode() {
        return zza(this);
    }

    public final boolean isConsumed(String str) {
        return getRecipientStatus(str) == 1;
    }

    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        return zzc(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) getGame(), i, false);
        zzbgo.zza(parcel, 2, (Parcelable) getSender(), i, false);
        zzbgo.zza(parcel, 3, getData(), false);
        zzbgo.zza(parcel, 4, getRequestId(), false);
        zzbgo.zzc(parcel, 5, getRecipients(), false);
        zzbgo.zzc(parcel, 7, getType());
        zzbgo.zza(parcel, 9, getCreationTimestamp());
        zzbgo.zza(parcel, 10, getExpirationTimestamp());
        zzbgo.zza(parcel, 11, this.zzihi, false);
        zzbgo.zzc(parcel, 12, getStatus());
        zzbgo.zzai(parcel, zze);
    }
}
