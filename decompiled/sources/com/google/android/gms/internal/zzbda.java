package com.google.android.gms.internal;

import com.google.android.gms.cast.games.GameManagerState;
import com.google.android.gms.cast.games.PlayerInfo;
import com.google.android.gms.common.util.zzq;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public final class zzbda implements GameManagerState {
    private final String zzflg;
    private final int zzflh;
    private final int zzfll;
    private final int zzflm;
    private final JSONObject zzflo;
    private final String zzflp;
    private final Map<String, PlayerInfo> zzflr;

    public zzbda(int i, int i2, String str, JSONObject jSONObject, Collection<PlayerInfo> collection, String str2, int i3) {
        this.zzflm = i;
        this.zzfll = i2;
        this.zzflp = str;
        this.zzflo = jSONObject;
        this.zzflg = str2;
        this.zzflh = i3;
        this.zzflr = new HashMap(collection.size());
        for (PlayerInfo next : collection) {
            this.zzflr.put(next.getPlayerId(), next);
        }
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof GameManagerState)) {
            GameManagerState gameManagerState = (GameManagerState) obj;
            if (getPlayers().size() != gameManagerState.getPlayers().size()) {
                return false;
            }
            for (PlayerInfo next : getPlayers()) {
                boolean z = false;
                for (PlayerInfo next2 : gameManagerState.getPlayers()) {
                    if (zzbdw.zza(next.getPlayerId(), next2.getPlayerId())) {
                        if (!zzbdw.zza(next, next2)) {
                            return false;
                        }
                        z = true;
                    }
                }
                if (!z) {
                    return false;
                }
            }
            return this.zzflm == gameManagerState.getLobbyState() && this.zzfll == gameManagerState.getGameplayState() && this.zzflh == gameManagerState.getMaxPlayers() && zzbdw.zza(this.zzflg, gameManagerState.getApplicationName()) && zzbdw.zza(this.zzflp, gameManagerState.getGameStatusText()) && zzq.zzc(this.zzflo, gameManagerState.getGameData());
        }
        return false;
    }

    public final CharSequence getApplicationName() {
        return this.zzflg;
    }

    public final List<PlayerInfo> getConnectedControllablePlayers() {
        ArrayList arrayList = new ArrayList();
        for (PlayerInfo next : getPlayers()) {
            if (next.isConnected() && next.isControllable()) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public final List<PlayerInfo> getConnectedPlayers() {
        ArrayList arrayList = new ArrayList();
        for (PlayerInfo next : getPlayers()) {
            if (next.isConnected()) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public final List<PlayerInfo> getControllablePlayers() {
        ArrayList arrayList = new ArrayList();
        for (PlayerInfo next : getPlayers()) {
            if (next.isControllable()) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public final JSONObject getGameData() {
        return this.zzflo;
    }

    public final CharSequence getGameStatusText() {
        return this.zzflp;
    }

    public final int getGameplayState() {
        return this.zzfll;
    }

    public final Collection<String> getListOfChangedPlayers(GameManagerState gameManagerState) {
        HashSet hashSet = new HashSet();
        for (PlayerInfo next : getPlayers()) {
            PlayerInfo player = gameManagerState.getPlayer(next.getPlayerId());
            if (player == null || !next.equals(player)) {
                hashSet.add(next.getPlayerId());
            }
        }
        for (PlayerInfo next2 : gameManagerState.getPlayers()) {
            if (getPlayer(next2.getPlayerId()) == null) {
                hashSet.add(next2.getPlayerId());
            }
        }
        return hashSet;
    }

    public final int getLobbyState() {
        return this.zzflm;
    }

    public final int getMaxPlayers() {
        return this.zzflh;
    }

    public final PlayerInfo getPlayer(String str) {
        if (str == null) {
            return null;
        }
        return this.zzflr.get(str);
    }

    public final Collection<PlayerInfo> getPlayers() {
        return Collections.unmodifiableCollection(this.zzflr.values());
    }

    public final List<PlayerInfo> getPlayersInState(int i) {
        ArrayList arrayList = new ArrayList();
        for (PlayerInfo next : getPlayers()) {
            if (next.getPlayerState() == i) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public final boolean hasGameDataChanged(GameManagerState gameManagerState) {
        return !zzq.zzc(this.zzflo, gameManagerState.getGameData());
    }

    public final boolean hasGameStatusTextChanged(GameManagerState gameManagerState) {
        return !zzbdw.zza(this.zzflp, gameManagerState.getGameStatusText());
    }

    public final boolean hasGameplayStateChanged(GameManagerState gameManagerState) {
        return this.zzfll != gameManagerState.getGameplayState();
    }

    public final boolean hasLobbyStateChanged(GameManagerState gameManagerState) {
        return this.zzflm != gameManagerState.getLobbyState();
    }

    public final boolean hasPlayerChanged(String str, GameManagerState gameManagerState) {
        return !zzbdw.zza(getPlayer(str), gameManagerState.getPlayer(str));
    }

    public final boolean hasPlayerDataChanged(String str, GameManagerState gameManagerState) {
        PlayerInfo player = getPlayer(str);
        PlayerInfo player2 = gameManagerState.getPlayer(str);
        if (player == null && player2 == null) {
            return false;
        }
        return player == null || player2 == null || !zzq.zzc(player.getPlayerData(), player2.getPlayerData());
    }

    public final boolean hasPlayerStateChanged(String str, GameManagerState gameManagerState) {
        PlayerInfo player = getPlayer(str);
        PlayerInfo player2 = gameManagerState.getPlayer(str);
        if (player == null && player2 == null) {
            return false;
        }
        return player == null || player2 == null || player.getPlayerState() != player2.getPlayerState();
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzflm), Integer.valueOf(this.zzfll), this.zzflr, this.zzflp, this.zzflo, this.zzflg, Integer.valueOf(this.zzflh)});
    }
}
