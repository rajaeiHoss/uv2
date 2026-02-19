package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.cast.games.GameManagerClient;
import com.google.android.gms.cast.games.GameManagerState;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.measurement.AppMeasurement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzbcl extends zzbdg {
    private static String NAMESPACE = zzbdw.zzfw("com.google.cast.games");
    /* access modifiers changed from: private */
    public static final zzbei zzeus = new zzbei("GameManagerChannel");
    private final zze zzata;
    private final SharedPreferences zzbkx;
    /* access modifiers changed from: private */
    public final Cast.CastApi zzfam;
    /* access modifiers changed from: private */
    public final GoogleApiClient zzffo;
    private final Map<String, String> zzfkh = new ConcurrentHashMap();
    private final List<zzbeo> zzfki;
    private final String zzfkj;
    /* access modifiers changed from: private */
    public zzbcy zzfkk;
    private boolean zzfkl = false;
    private GameManagerState zzfkm;
    private GameManagerState zzfkn;
    private String zzfko;
    private JSONObject zzfkp;
    private long zzfkq = 0;
    private GameManagerClient.Listener zzfkr;
    /* access modifiers changed from: private */
    public String zzfks;

    public zzbcl(GoogleApiClient googleApiClient, String str, Cast.CastApi castApi) throws IllegalArgumentException, IllegalStateException {
        super(NAMESPACE, "CastGameManagerChannel", (String) null);
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("castSessionId cannot be null.");
        } else if (googleApiClient == null || !googleApiClient.isConnected() || !googleApiClient.hasConnectedApi(Cast.API)) {
            throw new IllegalArgumentException("googleApiClient needs to be connected and contain the Cast.API API.");
        } else {
            this.zzata = zzi.zzanq();
            this.zzfki = new ArrayList();
            this.zzfkj = str;
            this.zzfam = castApi;
            this.zzffo = googleApiClient;
            Context applicationContext = googleApiClient.getContext().getApplicationContext();
            this.zzbkx = applicationContext.getApplicationContext().getSharedPreferences(String.format(Locale.ROOT, "%s.%s", new Object[]{applicationContext.getPackageName(), "game_manager_channel_data"}), 0);
            this.zzfkn = null;
            this.zzfkm = new zzbda(0, 0, "", (JSONObject) null, new ArrayList(), "", -1);
        }
    }

    private final synchronized boolean isInitialized() {
        return this.zzfkk != null;
    }

    private final JSONObject zza(long j, String str, int i, JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("requestId", j);
            jSONObject2.put(AppMeasurement.Param.TYPE, i);
            jSONObject2.put("extraMessageData", jSONObject);
            jSONObject2.put("playerId", str);
            jSONObject2.put("playerToken", zzft(str));
            return jSONObject2;
        } catch (JSONException e) {
            zzeus.zzf("JSONException when trying to create a message: %s", e.getMessage());
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0088, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized void zza(com.google.android.gms.internal.zzbcz r10) {
        /*
            r9 = this;
            monitor-enter(r9)
            int r0 = r10.zzflj     // Catch:{ all -> 0x0089 }
            r1 = 1
            if (r0 != r1) goto L_0x0007
            goto L_0x0008
        L_0x0007:
            r1 = 0
        L_0x0008:
            com.google.android.gms.cast.games.GameManagerState r0 = r9.zzfkm     // Catch:{ all -> 0x0089 }
            r9.zzfkn = r0     // Catch:{ all -> 0x0089 }
            if (r1 == 0) goto L_0x0016
            com.google.android.gms.internal.zzbcy r0 = r10.zzfkk     // Catch:{ all -> 0x0089 }
            if (r0 == 0) goto L_0x0016
            com.google.android.gms.internal.zzbcy r0 = r10.zzfkk     // Catch:{ all -> 0x0089 }
            r9.zzfkk = r0     // Catch:{ all -> 0x0089 }
        L_0x0016:
            boolean r0 = r9.isInitialized()     // Catch:{ all -> 0x0089 }
            if (r0 != 0) goto L_0x001e
            monitor-exit(r9)
            return
        L_0x001e:
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ all -> 0x0089 }
            r6.<init>()     // Catch:{ all -> 0x0089 }
            java.util.List<com.google.android.gms.internal.zzbdc> r0 = r10.zzfln     // Catch:{ all -> 0x0089 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0089 }
        L_0x0029:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x0089 }
            if (r1 == 0) goto L_0x0050
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x0089 }
            com.google.android.gms.internal.zzbdc r1 = (com.google.android.gms.internal.zzbdc) r1     // Catch:{ all -> 0x0089 }
            java.lang.String r2 = r1.getPlayerId()     // Catch:{ all -> 0x0089 }
            com.google.android.gms.internal.zzbdb r3 = new com.google.android.gms.internal.zzbdb     // Catch:{ all -> 0x0089 }
            int r4 = r1.getPlayerState()     // Catch:{ all -> 0x0089 }
            org.json.JSONObject r1 = r1.getPlayerData()     // Catch:{ all -> 0x0089 }
            java.util.Map<java.lang.String, java.lang.String> r5 = r9.zzfkh     // Catch:{ all -> 0x0089 }
            boolean r5 = r5.containsKey(r2)     // Catch:{ all -> 0x0089 }
            r3.<init>(r2, r4, r1, r5)     // Catch:{ all -> 0x0089 }
            r6.add(r3)     // Catch:{ all -> 0x0089 }
            goto L_0x0029
        L_0x0050:
            com.google.android.gms.internal.zzbda r0 = new com.google.android.gms.internal.zzbda     // Catch:{ all -> 0x0089 }
            int r2 = r10.zzflm     // Catch:{ all -> 0x0089 }
            int r3 = r10.zzfll     // Catch:{ all -> 0x0089 }
            java.lang.String r4 = r10.zzflp     // Catch:{ all -> 0x0089 }
            org.json.JSONObject r5 = r10.zzflo     // Catch:{ all -> 0x0089 }
            com.google.android.gms.internal.zzbcy r1 = r9.zzfkk     // Catch:{ all -> 0x0089 }
            java.lang.String r7 = r1.zzagk()     // Catch:{ all -> 0x0089 }
            com.google.android.gms.internal.zzbcy r1 = r9.zzfkk     // Catch:{ all -> 0x0089 }
            int r8 = r1.getMaxPlayers()     // Catch:{ all -> 0x0089 }
            r1 = r0
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x0089 }
            r9.zzfkm = r0     // Catch:{ all -> 0x0089 }
            java.lang.String r1 = r10.zzfld     // Catch:{ all -> 0x0089 }
            com.google.android.gms.cast.games.PlayerInfo r0 = r0.getPlayer(r1)     // Catch:{ all -> 0x0089 }
            if (r0 == 0) goto L_0x0087
            boolean r0 = r0.isControllable()     // Catch:{ all -> 0x0089 }
            if (r0 == 0) goto L_0x0087
            int r0 = r10.zzflj     // Catch:{ all -> 0x0089 }
            r1 = 2
            if (r0 != r1) goto L_0x0087
            java.lang.String r0 = r10.zzfld     // Catch:{ all -> 0x0089 }
            r9.zzfko = r0     // Catch:{ all -> 0x0089 }
            org.json.JSONObject r10 = r10.zzflf     // Catch:{ all -> 0x0089 }
            r9.zzfkp = r10     // Catch:{ all -> 0x0089 }
        L_0x0087:
            monitor-exit(r9)
            return
        L_0x0089:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbcl.zza(com.google.android.gms.internal.zzbcz):void");
    }

    /* access modifiers changed from: private */
    public final void zza(String str, int i, JSONObject jSONObject, zzben zzben) {
        long j = this.zzfkq + 1;
        this.zzfkq = j;
        JSONObject zza = zza(j, str, i, jSONObject);
        if (zza == null) {
            zzben.zza(-1, 2001, (Object) null);
            zzeus.zzf("Not sending request because it was invalid.", new Object[0]);
            return;
        }
        zzbeo zzbeo = new zzbeo(this.zzata, NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS);
        zzbeo.zza(j, zzben);
        this.zzfki.add(zzbeo);
        zzbg(true);
        this.zzfam.sendMessage(this.zzffo, getNamespace(), zza.toString()).setResultCallback(new zzbcq(this, j));
    }

    private final synchronized void zzagg() throws IllegalStateException {
        if (!isInitialized()) {
            throw new IllegalStateException("Attempted to perform an operation on the GameManagerChannel before it is initialized.");
        } else if (isDisposed()) {
            throw new IllegalStateException("Attempted to perform an operation on the GameManagerChannel after it has been disposed.");
        }
    }

    /* access modifiers changed from: private */
    public final synchronized void zzagh() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("castSessionId", this.zzfkj);
            jSONObject.put("playerTokenMap", new JSONObject(this.zzfkh));
            this.zzbkx.edit().putString("save_data", jSONObject.toString()).commit();
        } catch (JSONException e) {
            zzeus.zzf("Error while saving data: %s", e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0046, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zzagi() {
        /*
            r5 = this;
            monitor-enter(r5)
            android.content.SharedPreferences r0 = r5.zzbkx     // Catch:{ all -> 0x005b }
            java.lang.String r1 = "save_data"
            r2 = 0
            java.lang.String r0 = r0.getString(r1, r2)     // Catch:{ all -> 0x005b }
            if (r0 != 0) goto L_0x000e
            monitor-exit(r5)
            return
        L_0x000e:
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0047 }
            r1.<init>(r0)     // Catch:{ JSONException -> 0x0047 }
            java.lang.String r0 = "castSessionId"
            java.lang.String r0 = r1.getString(r0)     // Catch:{ JSONException -> 0x0047 }
            java.lang.String r2 = r5.zzfkj     // Catch:{ JSONException -> 0x0047 }
            boolean r0 = r2.equals(r0)     // Catch:{ JSONException -> 0x0047 }
            if (r0 == 0) goto L_0x0045
            java.lang.String r0 = "playerTokenMap"
            org.json.JSONObject r0 = r1.getJSONObject(r0)     // Catch:{ JSONException -> 0x0047 }
            java.util.Iterator r1 = r0.keys()     // Catch:{ JSONException -> 0x0047 }
        L_0x002b:
            boolean r2 = r1.hasNext()     // Catch:{ JSONException -> 0x0047 }
            if (r2 == 0) goto L_0x0041
            java.lang.Object r2 = r1.next()     // Catch:{ JSONException -> 0x0047 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ JSONException -> 0x0047 }
            java.util.Map<java.lang.String, java.lang.String> r3 = r5.zzfkh     // Catch:{ JSONException -> 0x0047 }
            java.lang.String r4 = r0.getString(r2)     // Catch:{ JSONException -> 0x0047 }
            r3.put(r2, r4)     // Catch:{ JSONException -> 0x0047 }
            goto L_0x002b
        L_0x0041:
            r0 = 0
            r5.zzfkq = r0     // Catch:{ JSONException -> 0x0047 }
        L_0x0045:
            monitor-exit(r5)
            return
        L_0x0047:
            r0 = move-exception
            com.google.android.gms.internal.zzbei r1 = zzeus     // Catch:{ all -> 0x005b }
            java.lang.String r2 = "Error while loading data: %s"
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x005b }
            r4 = 0
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x005b }
            r3[r4] = r0     // Catch:{ all -> 0x005b }
            r1.zzf(r2, r3)     // Catch:{ all -> 0x005b }
            monitor-exit(r5)
            return
        L_0x005b:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbcl.zzagi():void");
    }

    private final void zzb(long j, int i, Object obj) {
        Iterator<zzbeo> it = this.zzfki.iterator();
        while (it.hasNext()) {
            if (it.next().zzc(j, i, obj)) {
                it.remove();
            }
        }
    }

    private final synchronized String zzft(String str) throws IllegalStateException {
        if (str == null) {
            return null;
        }
        return this.zzfkh.get(str);
    }

    public final synchronized void dispose() throws IllegalStateException {
        if (!this.zzfkl) {
            this.zzfkm = null;
            this.zzfkn = null;
            this.zzfko = null;
            this.zzfkp = null;
            this.zzfkl = true;
            try {
                this.zzfam.removeMessageReceivedCallbacks(this.zzffo, getNamespace());
            } catch (IOException e) {
                zzeus.zzf("Exception while detaching game manager channel.", e);
            }
        }
    }

    public final synchronized GameManagerState getCurrentState() throws IllegalStateException {
        zzagg();
        return this.zzfkm;
    }

    public final synchronized String getLastUsedPlayerId() throws IllegalStateException {
        zzagg();
        return this.zzfks;
    }

    public final synchronized boolean isDisposed() {
        return this.zzfkl;
    }

    public final synchronized void sendGameMessage(String str, JSONObject jSONObject) throws IllegalStateException {
        zzagg();
        long j = this.zzfkq + 1;
        this.zzfkq = j;
        JSONObject zza = zza(j, str, 7, jSONObject);
        if (zza != null) {
            this.zzfam.sendMessage(this.zzffo, getNamespace(), zza.toString());
        }
    }

    public final synchronized PendingResult<GameManagerClient.GameManagerResult> sendGameRequest(String str, JSONObject jSONObject) throws IllegalStateException {
        zzagg();
        return this.zzffo.zze(new zzbcp(this, str, jSONObject));
    }

    public final synchronized void setListener(GameManagerClient.Listener listener) {
        this.zzfkr = listener;
    }

    public final synchronized PendingResult<GameManagerClient.GameManagerInstanceResult> zza(GameManagerClient gameManagerClient) throws IllegalArgumentException {
        return this.zzffo.zze(new zzbcm(this, gameManagerClient));
    }

    public final synchronized PendingResult<GameManagerClient.GameManagerResult> zza(String str, int i, JSONObject jSONObject) throws IllegalStateException {
        zzagg();
        return this.zzffo.zze(new zzbco(this, i, str, jSONObject));
    }

    public final void zzc(long j, int i) {
        zzb(j, i, (Object) null);
    }

    public final void zzfu(String str) {
        String str2;
        zzbei zzbei = zzeus;
        int i = 0;
        zzbei.zzb("message received: %s", str);
        try {
            zzbcz zzw = zzbcz.zzw(new JSONObject(str));
            if (zzw == null) {
                zzbei.zzf("Could not parse game manager message from string: %s", str);
            } else if ((isInitialized() || zzw.zzfkk != null) && !isDisposed()) {
                boolean z = zzw.zzflj == 1;
                if (z && !TextUtils.isEmpty(zzw.zzflq)) {
                    this.zzfkh.put(zzw.zzfld, zzw.zzflq);
                    zzagh();
                }
                if (zzw.zzcc == 0) {
                    zza(zzw);
                } else {
                    zzbei.zzf("Not updating from game message because the message contains error code: %d", Integer.valueOf(zzw.zzcc));
                }
                int i2 = zzw.zzcc;
                if (i2 != 0) {
                    if (i2 == 1) {
                        i = 2001;
                    } else if (i2 == 2) {
                        i = CastStatusCodes.NOT_ALLOWED;
                    } else if (i2 == 3) {
                        i = GameManagerClient.STATUS_INCORRECT_VERSION;
                    } else if (i2 != 4) {
                        StringBuilder sb = new StringBuilder(53);
                        sb.append("Unknown GameManager protocol status code: ");
                        sb.append(i2);
                        zzbei.zzf(sb.toString(), new Object[0]);
                        i = 13;
                    } else {
                        i = GameManagerClient.STATUS_TOO_MANY_PLAYERS;
                    }
                }
                if (z) {
                    zzb(zzw.zzfle, i, zzw);
                }
                if (isInitialized() && i == 0) {
                    if (this.zzfkr != null) {
                        GameManagerState gameManagerState = this.zzfkn;
                        if (gameManagerState != null && !this.zzfkm.equals(gameManagerState)) {
                            this.zzfkr.onStateChanged(this.zzfkm, this.zzfkn);
                        }
                        JSONObject jSONObject = this.zzfkp;
                        if (!(jSONObject == null || (str2 = this.zzfko) == null)) {
                            this.zzfkr.onGameMessageReceived(str2, jSONObject);
                        }
                    }
                    this.zzfkn = null;
                    this.zzfko = null;
                    this.zzfkp = null;
                }
            }
        } catch (JSONException e) {
            zzeus.zzf("Message is malformed (%s); ignoring: %s", e.getMessage(), str);
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zzz(long j) {
        Iterator<zzbeo> it = this.zzfki.iterator();
        while (it.hasNext()) {
            if (it.next().zzd(j, 15)) {
                it.remove();
            }
        }
        boolean z = false;
        synchronized (zzbeo.zzakj) {
            Iterator<zzbeo> it2 = this.zzfki.iterator();
            while (true) {
                if (it2.hasNext()) {
                    if (it2.next().zzaha()) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return z;
    }
}
