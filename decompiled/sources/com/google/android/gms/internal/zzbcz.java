package com.google.android.gms.internal;

import com.google.android.gms.games.Games;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzbcz {
    private static final zzbei zzeus = new zzbei("GameManagerMessage");
    protected final int zzcc;
    protected final zzbcy zzfkk;
    protected final String zzfld;
    protected final long zzfle;
    protected final JSONObject zzflf;
    protected final int zzflj;
    protected final String zzflk;
    protected final int zzfll;
    protected final int zzflm;
    protected final List<zzbdc> zzfln;
    protected final JSONObject zzflo;
    protected final String zzflp;
    protected final String zzflq;

    private zzbcz(int i, int i2, String str, JSONObject jSONObject, int i3, int i4, List<zzbdc> list, JSONObject jSONObject2, String str2, String str3, long j, String str4, zzbcy zzbcy) {
        this.zzflj = i;
        this.zzcc = i2;
        this.zzflk = str;
        this.zzflf = jSONObject;
        this.zzfll = i3;
        this.zzflm = i4;
        this.zzfln = list;
        this.zzflo = jSONObject2;
        this.zzflp = str2;
        this.zzfld = str3;
        this.zzfle = j;
        this.zzflq = str4;
        this.zzfkk = zzbcy;
    }

    private static List<zzbdc> zzb(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                zzbdc zzbdc = null;
                try {
                    zzbdc = new zzbdc(optJSONObject);
                } catch (JSONException e) {
                    zzeus.zzc(e, "Exception when attempting to parse PlayerInfoMessageComponent at index %d", Integer.valueOf(i));
                }
                if (zzbdc != null) {
                    arrayList.add(zzbdc);
                }
            }
        }
        return arrayList;
    }

    protected static zzbcz zzw(JSONObject jSONObject) {
        int i;
        JSONObject jSONObject2 = jSONObject;
        int optInt = jSONObject2.optInt(AppMeasurement.Param.TYPE, -1);
        if (optInt == 1) {
            JSONObject optJSONObject = jSONObject2.optJSONObject("gameManagerConfig");
            zzbcy zzbcy = null;
            if (optJSONObject != null) {
                try {
                    zzbcy = new zzbcy(optJSONObject);
                } catch (JSONException e) {
                    zzeus.zzc(e, "Exception while parsing GameManagerMessage from json", new Object[0]);
                    return null;
                }
            }
            int optInt2 = jSONObject2.optInt("statusCode");
            String optString = jSONObject2.optString("errorDescription");
            JSONObject optJSONObject2 = jSONObject2.optJSONObject("extraMessageData");
            int optInt3 = jSONObject2.optInt("gameplayState");
            int optInt4 = jSONObject2.optInt("lobbyState");
            List<zzbdc> zzb = zzb(jSONObject2.optJSONArray(Games.EXTRA_PLAYER_IDS));
            i = 0;
            return new zzbcz(optInt, optInt2, optString, optJSONObject2, optInt3, optInt4, zzb, jSONObject2.optJSONObject("gameData"), jSONObject2.optString("gameStatusText"), jSONObject2.optString("playerId"), jSONObject2.optLong("requestId"), jSONObject2.optString("playerToken"), zzbcy);
        } else if (optInt != 2) {
            zzeus.zzf("Unrecognized Game Message type %d", Integer.valueOf(optInt));
        } else {
            return new zzbcz(optInt, jSONObject2.optInt("statusCode"), jSONObject2.optString("errorDescription"), jSONObject2.optJSONObject("extraMessageData"), jSONObject2.optInt("gameplayState"), jSONObject2.optInt("lobbyState"), zzb(jSONObject2.optJSONArray(Games.EXTRA_PLAYER_IDS)), jSONObject2.optJSONObject("gameData"), jSONObject2.optString("gameStatusText"), jSONObject2.optString("playerId"), -1, (String) null, (zzbcy) null);
        }
        return null;
    }
}
