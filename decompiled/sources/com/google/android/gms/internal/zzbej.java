package com.google.android.gms.internal;

import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaLoadOptions;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.cast.TextTrackStyle;
import com.google.android.gms.cast.zzbl;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzbej extends zzbdg {
    public static final String NAMESPACE = zzbdw.zzfw("com.google.cast.media");
    private final List<zzbeo> zzfki;
    private long zzfns;
    private MediaStatus zzfnt;
    private zzbek zzfnu;
    private final zzbeo zzfnv;
    private final zzbeo zzfnw;
    private final zzbeo zzfnx;
    private final zzbeo zzfny;
    private final zzbeo zzfnz;
    private final zzbeo zzfoa;
    private final zzbeo zzfob;
    private final zzbeo zzfoc;
    private final zzbeo zzfod;
    private final zzbeo zzfoe;
    private final zzbeo zzfof;
    private final zzbeo zzfog;
    private final zzbeo zzfoh;
    private final zzbeo zzfoi;
    private final zzbeo zzfoj;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzbej(String str, zze zze) {
        super(NAMESPACE, zze, "MediaControlChannel", (String) null, 1000);
        zzbeo zzbeo = new zzbeo(this.zzata, 86400000);
        this.zzfnv = zzbeo;
        zzbeo zzbeo2 = new zzbeo(this.zzata, 86400000);
        this.zzfnw = zzbeo2;
        zzbeo zzbeo3 = new zzbeo(this.zzata, 86400000);
        this.zzfnx = zzbeo3;
        zzbeo zzbeo4 = new zzbeo(this.zzata, 86400000);
        this.zzfny = zzbeo4;
        zzbeo zzbeo5 = new zzbeo(this.zzata, 86400000);
        this.zzfnz = zzbeo5;
        zzbeo zzbeo6 = new zzbeo(this.zzata, 86400000);
        this.zzfoa = zzbeo6;
        zzbeo zzbeo7 = new zzbeo(this.zzata, 86400000);
        this.zzfob = zzbeo7;
        zzbeo zzbeo8 = new zzbeo(this.zzata, 86400000);
        this.zzfoc = zzbeo8;
        zzbeo zzbeo9 = new zzbeo(this.zzata, 86400000);
        this.zzfod = zzbeo9;
        zzbeo zzbeo10 = new zzbeo(this.zzata, 86400000);
        this.zzfoe = zzbeo10;
        zzbeo zzbeo11 = new zzbeo(this.zzata, 86400000);
        this.zzfof = zzbeo11;
        zzbeo zzbeo12 = new zzbeo(this.zzata, 86400000);
        this.zzfog = zzbeo12;
        zzbeo zzbeo13 = zzbeo12;
        zzbeo zzbeo14 = new zzbeo(this.zzata, 86400000);
        this.zzfoh = zzbeo14;
        zzbeo zzbeo15 = zzbeo14;
        zzbeo zzbeo16 = new zzbeo(this.zzata, 86400000);
        this.zzfoi = zzbeo16;
        zzbeo zzbeo17 = new zzbeo(this.zzata, 86400000);
        this.zzfoj = zzbeo17;
        ArrayList arrayList = new ArrayList();
        this.zzfki = arrayList;
        arrayList.add(zzbeo);
        arrayList.add(zzbeo2);
        arrayList.add(zzbeo3);
        arrayList.add(zzbeo4);
        arrayList.add(zzbeo5);
        arrayList.add(zzbeo6);
        arrayList.add(zzbeo7);
        arrayList.add(zzbeo8);
        arrayList.add(zzbeo9);
        arrayList.add(zzbeo10);
        arrayList.add(zzbeo11);
        arrayList.add(zzbeo13);
        arrayList.add(zzbeo15);
        arrayList.add(zzbeo16);
        arrayList.add(zzbeo17);
        zzagy();
    }

    private final void onMetadataUpdated() {
        zzbek zzbek = this.zzfnu;
        if (zzbek != null) {
            zzbek.onMetadataUpdated();
        }
    }

    private final void onPreloadStatusUpdated() {
        zzbek zzbek = this.zzfnu;
        if (zzbek != null) {
            zzbek.onPreloadStatusUpdated();
        }
    }

    private final void onQueueStatusUpdated() {
        zzbek zzbek = this.zzfnu;
        if (zzbek != null) {
            zzbek.onQueueStatusUpdated();
        }
    }

    private final void onStatusUpdated() {
        zzbek zzbek = this.zzfnu;
        if (zzbek != null) {
            zzbek.onStatusUpdated();
        }
    }

    private static String zza(String str, List<zzbl> list, long j) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("requestId", j);
            jSONObject.put(AppMeasurement.Param.TYPE, "PRECACHE");
            if (str != null) {
                jSONObject.put("precacheData", str);
            }
            if (list != null && !list.isEmpty()) {
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < list.size(); i++) {
                    jSONArray.put(i, list.get(i).toJson());
                }
                jSONObject.put("requestItems", jSONArray);
            }
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }

    private final void zza(long j, JSONObject jSONObject) throws JSONException {
        int i;
        MediaStatus mediaStatus;
        boolean test = this.zzfnv.test(j);
        boolean z = true;
        boolean z2 = this.zzfnz.zzaha() && !this.zzfnz.test(j);
        if ((!this.zzfoa.zzaha() || this.zzfoa.test(j)) && (!this.zzfob.zzaha() || this.zzfob.test(j))) {
            z = false;
        }
        int i2 = z2 ? 2 : 0;
        if (z) {
            i2 |= 1;
        }
        if (test || (mediaStatus = this.zzfnt) == null) {
            this.zzfnt = new MediaStatus(jSONObject);
            this.zzfns = this.zzata.elapsedRealtime();
            i = 127;
        } else {
            i = mediaStatus.zza(jSONObject, i2);
        }
        if ((i & 1) != 0) {
            this.zzfns = this.zzata.elapsedRealtime();
            onStatusUpdated();
        }
        if ((i & 2) != 0) {
            this.zzfns = this.zzata.elapsedRealtime();
            onStatusUpdated();
        }
        if ((i & 4) != 0) {
            onMetadataUpdated();
        }
        if ((i & 8) != 0) {
            onQueueStatusUpdated();
        }
        if ((i & 16) != 0) {
            onPreloadStatusUpdated();
        }
        if ((i & 32) != 0) {
            this.zzfns = this.zzata.elapsedRealtime();
            zzbek zzbek = this.zzfnu;
            if (zzbek != null) {
                zzbek.onAdBreakStatusUpdated();
            }
        }
        if ((i & 64) != 0) {
            this.zzfns = this.zzata.elapsedRealtime();
            onStatusUpdated();
        }
        for (zzbeo zzc : this.zzfki) {
            zzc.zzc(j, 0, (Object) null);
        }
    }

    private final long zzadv() throws zzbel {
        MediaStatus mediaStatus = this.zzfnt;
        if (mediaStatus != null) {
            return mediaStatus.zzadv();
        }
        throw new zzbel();
    }

    private final void zzagy() {
        this.zzfns = 0;
        this.zzfnt = null;
        for (zzbeo clear : this.zzfki) {
            clear.clear();
        }
    }

    public final long getApproximateStreamPosition() {
        MediaInfo mediaInfo = getMediaInfo();
        if (mediaInfo == null || this.zzfns == 0) {
            return 0;
        }
        double playbackRate = this.zzfnt.getPlaybackRate();
        long streamPosition = this.zzfnt.getStreamPosition();
        int playerState = this.zzfnt.getPlayerState();
        if (playbackRate == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || playerState != 2) {
            return streamPosition;
        }
        long streamDuration = mediaInfo.getStreamDuration();
        long elapsedRealtime = this.zzata.elapsedRealtime() - this.zzfns;
        if (elapsedRealtime < 0) {
            elapsedRealtime = 0;
        }
        if (elapsedRealtime == 0) {
            return streamPosition;
        }
        long j = streamPosition + ((long) (((double) elapsedRealtime) * playbackRate));
        if (streamDuration > 0 && j > streamDuration) {
            return streamDuration;
        }
        if (j < 0) {
            return 0;
        }
        return j;
    }

    public final MediaInfo getMediaInfo() {
        MediaStatus mediaStatus = this.zzfnt;
        if (mediaStatus == null) {
            return null;
        }
        return mediaStatus.getMediaInfo();
    }

    public final MediaStatus getMediaStatus() {
        return this.zzfnt;
    }

    public final long getStreamDuration() {
        MediaInfo mediaInfo = getMediaInfo();
        if (mediaInfo != null) {
            return mediaInfo.getStreamDuration();
        }
        return 0;
    }

    public final long zza(zzben zzben) throws IllegalStateException {
        JSONObject jSONObject = new JSONObject();
        long zzagn = zzagn();
        this.zzfoc.zza(zzagn, zzben);
        zzbg(true);
        try {
            jSONObject.put("requestId", zzagn);
            jSONObject.put(AppMeasurement.Param.TYPE, "GET_STATUS");
            MediaStatus mediaStatus = this.zzfnt;
            if (mediaStatus != null) {
                jSONObject.put("mediaSessionId", mediaStatus.zzadv());
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject.toString(), zzagn, (String) null);
        return zzagn;
    }

    public final long zza(zzben zzben, double d, JSONObject jSONObject) throws IllegalStateException, zzbel, IllegalArgumentException {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            StringBuilder sb = new StringBuilder(41);
            sb.append("Volume cannot be ");
            sb.append(d);
            throw new IllegalArgumentException(sb.toString());
        }
        JSONObject jSONObject2 = new JSONObject();
        long zzagn = zzagn();
        this.zzfoa.zza(zzagn, zzben);
        zzbg(true);
        try {
            jSONObject2.put("requestId", zzagn);
            jSONObject2.put(AppMeasurement.Param.TYPE, "SET_VOLUME");
            jSONObject2.put("mediaSessionId", zzadv());
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put(FirebaseAnalytics.Param.LEVEL, d);
            jSONObject2.put(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, jSONObject3);
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject2.toString(), zzagn, (String) null);
        return zzagn;
    }

    public final long zza(zzben zzben, int i, long j, MediaQueueItem[] mediaQueueItemArr, int i2, Integer num, JSONObject jSONObject) throws IllegalArgumentException, IllegalStateException, zzbel {
        String str = null;
        int i3 = (j > -1 ? 1 : (j == -1 ? 0 : -1));
        if (i3 == 0 || j >= 0) {
            JSONObject jSONObject2 = new JSONObject();
            long zzagn = zzagn();
            this.zzfog.zza(zzagn, zzben);
            zzbg(true);
            try {
                jSONObject2.put("requestId", zzagn);
                jSONObject2.put(AppMeasurement.Param.TYPE, "QUEUE_UPDATE");
                jSONObject2.put("mediaSessionId", zzadv());
                if (i != 0) {
                    jSONObject2.put("currentItemId", i);
                }
                if (i2 != 0) {
                    jSONObject2.put("jump", i2);
                }
                if (mediaQueueItemArr != null && mediaQueueItemArr.length > 0) {
                    JSONArray jSONArray = new JSONArray();
                    for (int i4 = 0; i4 < mediaQueueItemArr.length; i4++) {
                        jSONArray.put(i4, mediaQueueItemArr[i4].toJson());
                    }
                    jSONObject2.put("items", jSONArray);
                }
                if (num != null) {
                    int intValue = num.intValue();
                    if (intValue == 0) {
                        str = "REPEAT_OFF";
                    } else if (intValue == 1) {
                        str = "REPEAT_ALL";
                    } else if (intValue == 2) {
                        str = "REPEAT_SINGLE";
                    } else if (intValue == 3) {
                        str = "REPEAT_ALL_AND_SHUFFLE";
                    }
                    if (str != null) {
                        jSONObject2.put("repeatMode", str);
                    }
                }
                if (i3 != 0) {
                    jSONObject2.put("currentTime", ((double) j) / 1000.0d);
                }
                if (jSONObject != null) {
                    jSONObject2.put("customData", jSONObject);
                }
            } catch (JSONException unused) {
            }
            zza(jSONObject2.toString(), zzagn, (String) null);
            return zzagn;
        }
        StringBuilder sb = new StringBuilder(53);
        sb.append("playPosition cannot be negative: ");
        sb.append(j);
        throw new IllegalArgumentException(sb.toString());
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0045 A[Catch:{ JSONException -> 0x004a }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long zza(com.google.android.gms.internal.zzben r7, long r8, int r10, org.json.JSONObject r11) throws java.lang.IllegalStateException, com.google.android.gms.internal.zzbel {
        /*
            r6 = this;
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            long r1 = r6.zzagn()
            com.google.android.gms.internal.zzbeo r3 = r6.zzfnz
            r3.zza(r1, r7)
            r7 = 1
            r6.zzbg(r7)
            java.lang.String r3 = "requestId"
            r0.put(r3, r1)     // Catch:{ JSONException -> 0x004a }
            java.lang.String r3 = "type"
            java.lang.String r4 = "SEEK"
            r0.put(r3, r4)     // Catch:{ JSONException -> 0x004a }
            java.lang.String r3 = "mediaSessionId"
            long r4 = r6.zzadv()     // Catch:{ JSONException -> 0x004a }
            r0.put(r3, r4)     // Catch:{ JSONException -> 0x004a }
            java.lang.String r3 = "currentTime"
            double r8 = (double) r8     // Catch:{ JSONException -> 0x004a }
            r4 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r8 = r8 / r4
            r0.put(r3, r8)     // Catch:{ JSONException -> 0x004a }
            java.lang.String r8 = "resumeState"
            if (r10 != r7) goto L_0x003d
            java.lang.String r7 = "PLAYBACK_START"
        L_0x0039:
            r0.put(r8, r7)     // Catch:{ JSONException -> 0x004a }
            goto L_0x0043
        L_0x003d:
            r7 = 2
            if (r10 != r7) goto L_0x0043
            java.lang.String r7 = "PLAYBACK_PAUSE"
            goto L_0x0039
        L_0x0043:
            if (r11 == 0) goto L_0x004a
            java.lang.String r7 = "customData"
            r0.put(r7, r11)     // Catch:{ JSONException -> 0x004a }
        L_0x004a:
            java.lang.String r7 = r0.toString()
            r8 = 0
            r6.zza(r7, r1, r8)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbej.zza(com.google.android.gms.internal.zzben, long, int, org.json.JSONObject):long");
    }

    public final long zza(zzben zzben, MediaInfo mediaInfo, MediaLoadOptions mediaLoadOptions) throws IllegalStateException, IllegalArgumentException {
        JSONObject jSONObject = new JSONObject();
        long zzagn = zzagn();
        this.zzfnv.zza(zzagn, zzben);
        zzbg(true);
        try {
            jSONObject.put("requestId", zzagn);
            jSONObject.put(AppMeasurement.Param.TYPE, "LOAD");
            jSONObject.put("media", mediaInfo.toJson());
            jSONObject.put("autoplay", mediaLoadOptions.getAutoplay());
            jSONObject.put("currentTime", ((double) mediaLoadOptions.getPlayPosition()) / 1000.0d);
            jSONObject.put("playbackRate", mediaLoadOptions.getPlaybackRate());
            if (mediaLoadOptions.getCredentials() != null) {
                jSONObject.put("credentials", mediaLoadOptions.getCredentials());
            }
            if (mediaLoadOptions.getCredentialsType() != null) {
                jSONObject.put("credentialsType", mediaLoadOptions.getCredentialsType());
            }
            long[] activeTrackIds = mediaLoadOptions.getActiveTrackIds();
            if (activeTrackIds != null) {
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < activeTrackIds.length; i++) {
                    jSONArray.put(i, activeTrackIds[i]);
                }
                jSONObject.put("activeTrackIds", jSONArray);
            }
            JSONObject customData = mediaLoadOptions.getCustomData();
            if (customData != null) {
                jSONObject.put("customData", customData);
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject.toString(), zzagn, (String) null);
        return zzagn;
    }

    public final long zza(zzben zzben, TextTrackStyle textTrackStyle) throws IllegalStateException, zzbel {
        JSONObject jSONObject = new JSONObject();
        long zzagn = zzagn();
        this.zzfoe.zza(zzagn, zzben);
        zzbg(true);
        try {
            jSONObject.put("requestId", zzagn);
            jSONObject.put(AppMeasurement.Param.TYPE, "EDIT_TRACKS_INFO");
            if (textTrackStyle != null) {
                jSONObject.put("textTrackStyle", textTrackStyle.toJson());
            }
            jSONObject.put("mediaSessionId", zzadv());
        } catch (JSONException unused) {
        }
        zza(jSONObject.toString(), zzagn, (String) null);
        return zzagn;
    }

    public final long zza(zzben zzben, JSONObject jSONObject) throws IllegalStateException, zzbel {
        JSONObject jSONObject2 = new JSONObject();
        long zzagn = zzagn();
        this.zzfnw.zza(zzagn, zzben);
        zzbg(true);
        try {
            jSONObject2.put("requestId", zzagn);
            jSONObject2.put(AppMeasurement.Param.TYPE, "PAUSE");
            jSONObject2.put("mediaSessionId", zzadv());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject2.toString(), zzagn, (String) null);
        return zzagn;
    }

    public final long zza(zzben zzben, boolean z, JSONObject jSONObject) throws IllegalStateException, zzbel {
        JSONObject jSONObject2 = new JSONObject();
        long zzagn = zzagn();
        this.zzfob.zza(zzagn, zzben);
        zzbg(true);
        try {
            jSONObject2.put("requestId", zzagn);
            jSONObject2.put(AppMeasurement.Param.TYPE, "SET_VOLUME");
            jSONObject2.put("mediaSessionId", zzadv());
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("muted", z);
            jSONObject2.put(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, jSONObject3);
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject2.toString(), zzagn, (String) null);
        return zzagn;
    }

    public final long zza(zzben zzben, int[] iArr, int i, JSONObject jSONObject) throws IllegalStateException, zzbel, IllegalArgumentException {
        if (iArr == null || iArr.length == 0) {
            throw new IllegalArgumentException("itemIdsToReorder must not be null or empty.");
        }
        JSONObject jSONObject2 = new JSONObject();
        long zzagn = zzagn();
        this.zzfoi.zza(zzagn, zzben);
        zzbg(true);
        try {
            jSONObject2.put("requestId", zzagn);
            jSONObject2.put(AppMeasurement.Param.TYPE, "QUEUE_REORDER");
            jSONObject2.put("mediaSessionId", zzadv());
            JSONArray jSONArray = new JSONArray();
            for (int i2 = 0; i2 < iArr.length; i2++) {
                jSONArray.put(i2, iArr[i2]);
            }
            jSONObject2.put("itemIds", jSONArray);
            if (i != 0) {
                jSONObject2.put("insertBefore", i);
            }
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject2.toString(), zzagn, (String) null);
        return zzagn;
    }

    public final long zza(zzben zzben, int[] iArr, JSONObject jSONObject) throws IllegalStateException, zzbel, IllegalArgumentException {
        if (iArr == null || iArr.length == 0) {
            throw new IllegalArgumentException("itemIdsToRemove must not be null or empty.");
        }
        JSONObject jSONObject2 = new JSONObject();
        long zzagn = zzagn();
        this.zzfoh.zza(zzagn, zzben);
        zzbg(true);
        try {
            jSONObject2.put("requestId", zzagn);
            jSONObject2.put(AppMeasurement.Param.TYPE, "QUEUE_REMOVE");
            jSONObject2.put("mediaSessionId", zzadv());
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < iArr.length; i++) {
                jSONArray.put(i, iArr[i]);
            }
            jSONObject2.put("itemIds", jSONArray);
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject2.toString(), zzagn, (String) null);
        return zzagn;
    }

    public final long zza(zzben zzben, long[] jArr) throws IllegalStateException, zzbel {
        JSONObject jSONObject = new JSONObject();
        long zzagn = zzagn();
        this.zzfod.zza(zzagn, zzben);
        zzbg(true);
        try {
            jSONObject.put("requestId", zzagn);
            jSONObject.put(AppMeasurement.Param.TYPE, "EDIT_TRACKS_INFO");
            jSONObject.put("mediaSessionId", zzadv());
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < jArr.length; i++) {
                jSONArray.put(i, jArr[i]);
            }
            jSONObject.put("activeTrackIds", jSONArray);
        } catch (JSONException unused) {
        }
        zza(jSONObject.toString(), zzagn, (String) null);
        return zzagn;
    }

    public final long zza(zzben zzben, MediaQueueItem[] mediaQueueItemArr, int i, int i2, int i3, long j, JSONObject jSONObject) throws IllegalStateException, zzbel, IllegalArgumentException {
        MediaQueueItem[] mediaQueueItemArr2 = mediaQueueItemArr;
        int i4 = i;
        int i5 = i3;
        long j2 = j;
        JSONObject jSONObject2 = jSONObject;
        if (mediaQueueItemArr2 == null || mediaQueueItemArr2.length == 0) {
            throw new IllegalArgumentException("itemsToInsert must not be null or empty.");
        } else if (i5 == -1 || (i5 >= 0 && i5 < mediaQueueItemArr2.length)) {
            int i6 = (j2 > -1 ? 1 : (j2 == -1 ? 0 : -1));
            if (i6 == 0 || j2 >= 0) {
                JSONObject jSONObject3 = new JSONObject();
                long zzagn = zzagn();
                this.zzfof.zza(zzagn, zzben);
                zzbg(true);
                try {
                    jSONObject3.put("requestId", zzagn);
                    jSONObject3.put(AppMeasurement.Param.TYPE, "QUEUE_INSERT");
                    jSONObject3.put("mediaSessionId", zzadv());
                    JSONArray jSONArray = new JSONArray();
                    for (int i7 = 0; i7 < mediaQueueItemArr2.length; i7++) {
                        jSONArray.put(i7, mediaQueueItemArr2[i7].toJson());
                    }
                    jSONObject3.put("items", jSONArray);
                    if (i4 != 0) {
                        jSONObject3.put("insertBefore", i4);
                    }
                    if (i5 != -1) {
                        jSONObject3.put("currentItemIndex", i5);
                    }
                    if (i6 != 0) {
                        jSONObject3.put("currentTime", ((double) j2) / 1000.0d);
                    }
                    if (jSONObject2 != null) {
                        jSONObject3.put("customData", jSONObject2);
                    }
                } catch (JSONException unused) {
                }
                zza(jSONObject3.toString(), zzagn, (String) null);
                return zzagn;
            }
            StringBuilder sb = new StringBuilder(54);
            sb.append("playPosition can not be negative: ");
            sb.append(j2);
            throw new IllegalArgumentException(sb.toString());
        } else {
            throw new IllegalArgumentException(String.format(Locale.ROOT, "currentItemIndexInItemsToInsert %d out of range [0, %d).", new Object[]{Integer.valueOf(i3), Integer.valueOf(mediaQueueItemArr2.length)}));
        }
    }

    public final long zza(zzben zzben, MediaQueueItem[] mediaQueueItemArr, int i, int i2, long j, JSONObject jSONObject) throws IllegalStateException, IllegalArgumentException {
        String str;
        if (mediaQueueItemArr == null || mediaQueueItemArr.length == 0) {
            throw new IllegalArgumentException("items must not be null or empty.");
        } else if (i < 0 || i >= mediaQueueItemArr.length) {
            StringBuilder sb = new StringBuilder(31);
            sb.append("Invalid startIndex: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        } else {
            int i3 = (j > -1 ? 1 : (j == -1 ? 0 : -1));
            if (i3 == 0 || j >= 0) {
                JSONObject jSONObject2 = new JSONObject();
                long zzagn = zzagn();
                this.zzfnv.zza(zzagn, zzben);
                zzbg(true);
                try {
                    jSONObject2.put("requestId", zzagn);
                    jSONObject2.put(AppMeasurement.Param.TYPE, "QUEUE_LOAD");
                    JSONArray jSONArray = new JSONArray();
                    for (int i4 = 0; i4 < mediaQueueItemArr.length; i4++) {
                        jSONArray.put(i4, mediaQueueItemArr[i4].toJson());
                    }
                    jSONObject2.put("items", jSONArray);
                    if (i2 == 0) {
                        str = "REPEAT_OFF";
                    } else if (i2 == 1) {
                        str = "REPEAT_ALL";
                    } else if (i2 == 2) {
                        str = "REPEAT_SINGLE";
                    } else if (i2 == 3) {
                        str = "REPEAT_ALL_AND_SHUFFLE";
                    } else {
                        StringBuilder sb2 = new StringBuilder(32);
                        sb2.append("Invalid repeat mode: ");
                        sb2.append(i2);
                        throw new IllegalArgumentException(sb2.toString());
                    }
                    jSONObject2.put("repeatMode", str);
                    jSONObject2.put("startIndex", i);
                    if (i3 != 0) {
                        jSONObject2.put("currentTime", ((double) j) / 1000.0d);
                    }
                    if (jSONObject != null) {
                        jSONObject2.put("customData", jSONObject);
                    }
                } catch (JSONException unused) {
                }
                zza(jSONObject2.toString(), zzagn, (String) null);
                return zzagn;
            }
            StringBuilder sb3 = new StringBuilder(54);
            sb3.append("playPosition can not be negative: ");
            sb3.append(j);
            throw new IllegalArgumentException(sb3.toString());
        }
    }

    public final void zza(zzbek zzbek) {
        this.zzfnu = zzbek;
    }

    public final void zzagm() {
        super.zzagm();
        zzagy();
    }

    public final long zzb(zzben zzben, double d, JSONObject jSONObject) throws IllegalStateException, zzbel {
        if (this.zzfnt != null) {
            JSONObject jSONObject2 = new JSONObject();
            long zzagn = zzagn();
            this.zzfoj.zza(zzagn, zzben);
            zzbg(true);
            try {
                jSONObject2.put("requestId", zzagn);
                jSONObject2.put(AppMeasurement.Param.TYPE, "SET_PLAYBACK_RATE");
                jSONObject2.put("playbackRate", d);
                jSONObject2.put("mediaSessionId", this.zzfnt.zzadv());
                if (jSONObject != null) {
                    jSONObject2.put("customData", jSONObject);
                }
            } catch (JSONException unused) {
            }
            zza(jSONObject2.toString(), zzagn, (String) null);
            return zzagn;
        }
        throw new zzbel();
    }

    public final long zzb(zzben zzben, JSONObject jSONObject) throws IllegalStateException, zzbel {
        JSONObject jSONObject2 = new JSONObject();
        long zzagn = zzagn();
        this.zzfny.zza(zzagn, zzben);
        zzbg(true);
        try {
            jSONObject2.put("requestId", zzagn);
            jSONObject2.put(AppMeasurement.Param.TYPE, "STOP");
            jSONObject2.put("mediaSessionId", zzadv());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject2.toString(), zzagn, (String) null);
        return zzagn;
    }

    public final long zzb(String str, List<zzbl> list) throws IllegalStateException {
        long zzagn = zzagn();
        zza(zza(str, list, zzagn), zzagn, (String) null);
        return zzagn;
    }

    public final long zzc(zzben zzben, JSONObject jSONObject) throws IllegalStateException, zzbel {
        JSONObject jSONObject2 = new JSONObject();
        long zzagn = zzagn();
        this.zzfnx.zza(zzagn, zzben);
        zzbg(true);
        try {
            jSONObject2.put("requestId", zzagn);
            jSONObject2.put(AppMeasurement.Param.TYPE, "PLAY");
            jSONObject2.put("mediaSessionId", zzadv());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject2.toString(), zzagn, (String) null);
        return zzagn;
    }

    public final void zzc(long j, int i) {
        for (zzbeo zzc : this.zzfki) {
            zzc.zzc(j, i, (Object) null);
        }
    }

    public final void zzfu(String str) {
        this.zzeui.zzb("message received: %s", str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString(AppMeasurement.Param.TYPE);
            long optLong = jSONObject.optLong("requestId", -1);
            char c = 65535;
            switch (string.hashCode()) {
                case -1830647528:
                    if (string.equals("LOAD_CANCELLED")) {
                        c = 3;
                        break;
                    }
                    break;
                case -1125000185:
                    if (string.equals("INVALID_REQUEST")) {
                        c = 4;
                        break;
                    }
                    break;
                case -262628938:
                    if (string.equals("LOAD_FAILED")) {
                        c = 2;
                        break;
                    }
                    break;
                case 431600379:
                    if (string.equals("INVALID_PLAYER_STATE")) {
                        c = 1;
                        break;
                    }
                    break;
                case 823510221:
                    if (string.equals("MEDIA_STATUS")) {
                        c = 0;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                JSONArray jSONArray = jSONObject.getJSONArray("status");
                if (jSONArray.length() > 0) {
                    zza(optLong, jSONArray.getJSONObject(0));
                    return;
                }
                this.zzfnt = null;
                onStatusUpdated();
                onMetadataUpdated();
                onQueueStatusUpdated();
                onPreloadStatusUpdated();
                this.zzfoc.zzc(optLong, 0, (Object) null);
            } else if (c == 1) {
                this.zzeui.zzf("received unexpected error: Invalid Player State.", new Object[0]);
                JSONObject optJSONObject = jSONObject.optJSONObject("customData");
                for (zzbeo zzc : this.zzfki) {
                    zzc.zzc(optLong, 2100, optJSONObject);
                }
            } else if (c == 2) {
                this.zzfnv.zzc(optLong, 2100, jSONObject.optJSONObject("customData"));
            } else if (c == 3) {
                this.zzfnv.zzc(optLong, RemoteMediaPlayer.STATUS_CANCELED, jSONObject.optJSONObject("customData"));
            } else if (c == 4) {
                this.zzeui.zzf("received unexpected error: Invalid Request.", new Object[0]);
                JSONObject optJSONObject2 = jSONObject.optJSONObject("customData");
                for (zzbeo zzc2 : this.zzfki) {
                    zzc2.zzc(optLong, 2100, optJSONObject2);
                }
            }
        } catch (JSONException e) {
            this.zzeui.zzf("Message is malformed (%s); ignoring: %s", e.getMessage(), str);
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zzz(long j) {
        for (zzbeo zzd : this.zzfki) {
            zzd.zzd(j, RemoteMediaPlayer.STATUS_TIMED_OUT);
        }
        boolean z = false;
        synchronized (zzbeo.zzakj) {
            Iterator<zzbeo> it = this.zzfki.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().zzaha()) {
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
