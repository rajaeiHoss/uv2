package com.google.android.gms.internal;

import com.google.android.gms.cast.games.PlayerInfo;
import com.google.android.gms.common.util.zzq;
import java.util.Arrays;
import org.json.JSONObject;

public final class zzbdb implements PlayerInfo {
    private final int zzexd;
    private final String zzfld;
    private final JSONObject zzfls;
    private final boolean zzflt;

    public zzbdb(String str, int i, JSONObject jSONObject, boolean z) {
        this.zzfld = str;
        this.zzexd = i;
        this.zzfls = jSONObject;
        this.zzflt = z;
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof PlayerInfo)) {
            PlayerInfo playerInfo = (PlayerInfo) obj;
            return this.zzflt == playerInfo.isControllable() && this.zzexd == playerInfo.getPlayerState() && zzbdw.zza(this.zzfld, playerInfo.getPlayerId()) && zzq.zzc(this.zzfls, playerInfo.getPlayerData());
        }
        return false;
    }

    public final JSONObject getPlayerData() {
        return this.zzfls;
    }

    public final String getPlayerId() {
        return this.zzfld;
    }

    public final int getPlayerState() {
        return this.zzexd;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzfld, Integer.valueOf(this.zzexd), this.zzfls, Boolean.valueOf(this.zzflt)});
    }

    public final boolean isConnected() {
        int i = this.zzexd;
        return i == 3 || i == 4 || i == 5 || i == 6;
    }

    public final boolean isControllable() {
        return this.zzflt;
    }
}
