package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseArray;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.internal.zzbdw;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class MediaStatus extends zzbgl {
    public static final long COMMAND_PAUSE = 1;
    public static final long COMMAND_SEEK = 2;
    public static final long COMMAND_SET_VOLUME = 4;
    public static final long COMMAND_SKIP_BACKWARD = 32;
    public static final long COMMAND_SKIP_FORWARD = 16;
    public static final long COMMAND_TOGGLE_MUTE = 8;
    public static final Parcelable.Creator<MediaStatus> CREATOR = new zzaj();
    public static final int IDLE_REASON_CANCELED = 2;
    public static final int IDLE_REASON_ERROR = 4;
    public static final int IDLE_REASON_FINISHED = 1;
    public static final int IDLE_REASON_INTERRUPTED = 3;
    public static final int IDLE_REASON_NONE = 0;
    public static final int PLAYER_STATE_BUFFERING = 4;
    public static final int PLAYER_STATE_IDLE = 1;
    public static final int PLAYER_STATE_PAUSED = 3;
    public static final int PLAYER_STATE_PLAYING = 2;
    public static final int PLAYER_STATE_UNKNOWN = 0;
    public static final int REPEAT_MODE_REPEAT_ALL = 1;
    public static final int REPEAT_MODE_REPEAT_ALL_AND_SHUFFLE = 3;
    public static final int REPEAT_MODE_REPEAT_OFF = 0;
    public static final int REPEAT_MODE_REPEAT_SINGLE = 2;
    private String zzesn;
    private JSONObject zzess;
    private MediaInfo zzewh;
    private double zzewk;
    private long[] zzewl;
    private long zzexb;
    private int zzexc;
    private int zzexd;
    private int zzexe;
    private long zzexf;
    private long zzexg;
    private double zzexh;
    private boolean zzexi;
    private int zzexj;
    private int zzexk;
    private int zzexl;
    private ArrayList<MediaQueueItem> zzexm;
    private boolean zzexn;
    private AdBreakStatus zzexo;
    private VideoInfo zzexp;
    private final SparseArray<Integer> zzexq;

    MediaStatus(MediaInfo mediaInfo, long j, int i, double d, int i2, int i3, long j2, long j3, double d2, boolean z, long[] jArr, int i4, int i5, String str, int i6, List<MediaQueueItem> list, boolean z2, AdBreakStatus adBreakStatus, VideoInfo videoInfo) {
        String str2 = str;
        List<MediaQueueItem> list2 = list;
        this.zzexm = new ArrayList<>();
        this.zzexq = new SparseArray<>();
        this.zzewh = mediaInfo;
        this.zzexb = j;
        this.zzexc = i;
        this.zzewk = d;
        this.zzexd = i2;
        this.zzexe = i3;
        this.zzexf = j2;
        this.zzexg = j3;
        this.zzexh = d2;
        this.zzexi = z;
        this.zzewl = jArr;
        this.zzexj = i4;
        this.zzexk = i5;
        this.zzesn = str2;
        if (str2 != null) {
            try {
                this.zzess = new JSONObject(this.zzesn);
            } catch (JSONException unused) {
                this.zzess = null;
                this.zzesn = null;
            }
        } else {
            this.zzess = null;
        }
        this.zzexl = i6;
        if (list2 != null && !list.isEmpty()) {
            zza((MediaQueueItem[]) list2.toArray(new MediaQueueItem[list.size()]));
        }
        this.zzexn = z2;
        this.zzexo = adBreakStatus;
        this.zzexp = videoInfo;
    }

    public MediaStatus(JSONObject jSONObject) throws JSONException {
        this((MediaInfo) null, 0, 0, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 0, 0, 0, 0, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, false, (long[]) null, 0, 0, (String) null, 0, (List<MediaQueueItem>) null, false, (AdBreakStatus) null, (VideoInfo) null);
        zza(jSONObject, 0);
    }

    private final void zza(MediaQueueItem[] mediaQueueItemArr) {
        this.zzexm.clear();
        this.zzexq.clear();
        for (int i = 0; i < mediaQueueItemArr.length; i++) {
            MediaQueueItem mediaQueueItem = mediaQueueItemArr[i];
            this.zzexm.add(mediaQueueItem);
            this.zzexq.put(mediaQueueItem.getItemId(), Integer.valueOf(i));
        }
    }

    public boolean equals(Object obj) {
        JSONObject jSONObject;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaStatus)) {
            return false;
        }
        MediaStatus mediaStatus = (MediaStatus) obj;
        if ((this.zzess == null) == (mediaStatus.zzess == null) && this.zzexb == mediaStatus.zzexb && this.zzexc == mediaStatus.zzexc && this.zzewk == mediaStatus.zzewk && this.zzexd == mediaStatus.zzexd && this.zzexe == mediaStatus.zzexe && this.zzexf == mediaStatus.zzexf && this.zzexh == mediaStatus.zzexh && this.zzexi == mediaStatus.zzexi && this.zzexj == mediaStatus.zzexj && this.zzexk == mediaStatus.zzexk && this.zzexl == mediaStatus.zzexl && Arrays.equals(this.zzewl, mediaStatus.zzewl) && zzbdw.zza(Long.valueOf(this.zzexg), Long.valueOf(mediaStatus.zzexg)) && zzbdw.zza(this.zzexm, mediaStatus.zzexm) && zzbdw.zza(this.zzewh, mediaStatus.zzewh)) {
            JSONObject jSONObject2 = this.zzess;
            return (jSONObject2 == null || (jSONObject = mediaStatus.zzess) == null || zzq.zzc(jSONObject2, jSONObject)) && this.zzexn == mediaStatus.isPlayingAd();
        }
        return false;
    }

    public long[] getActiveTrackIds() {
        return this.zzewl;
    }

    public AdBreakStatus getAdBreakStatus() {
        return this.zzexo;
    }

    public AdBreakInfo getCurrentAdBreak() {
        List<AdBreakInfo> adBreaks;
        AdBreakStatus adBreakStatus = this.zzexo;
        if (!(adBreakStatus == null || this.zzewh == null)) {
            String breakId = adBreakStatus.getBreakId();
            if (!TextUtils.isEmpty(breakId) && (adBreaks = this.zzewh.getAdBreaks()) != null && !adBreaks.isEmpty()) {
                for (AdBreakInfo next : adBreaks) {
                    if (breakId.equals(next.getId())) {
                        return next;
                    }
                }
            }
        }
        return null;
    }

    public AdBreakClipInfo getCurrentAdBreakClip() {
        List<AdBreakClipInfo> adBreakClips;
        AdBreakStatus adBreakStatus = this.zzexo;
        if (!(adBreakStatus == null || this.zzewh == null)) {
            String breakClipId = adBreakStatus.getBreakClipId();
            if (!TextUtils.isEmpty(breakClipId) && (adBreakClips = this.zzewh.getAdBreakClips()) != null && !adBreakClips.isEmpty()) {
                for (AdBreakClipInfo next : adBreakClips) {
                    if (breakClipId.equals(next.getId())) {
                        return next;
                    }
                }
            }
        }
        return null;
    }

    public int getCurrentItemId() {
        return this.zzexc;
    }

    public JSONObject getCustomData() {
        return this.zzess;
    }

    public int getIdleReason() {
        return this.zzexe;
    }

    public Integer getIndexById(int i) {
        return this.zzexq.get(i);
    }

    public MediaQueueItem getItemById(int i) {
        Integer num = this.zzexq.get(i);
        if (num == null) {
            return null;
        }
        return this.zzexm.get(num.intValue());
    }

    public MediaQueueItem getItemByIndex(int i) {
        if (i < 0 || i >= this.zzexm.size()) {
            return null;
        }
        return this.zzexm.get(i);
    }

    public int getLoadingItemId() {
        return this.zzexj;
    }

    public MediaInfo getMediaInfo() {
        return this.zzewh;
    }

    public double getPlaybackRate() {
        return this.zzewk;
    }

    public int getPlayerState() {
        return this.zzexd;
    }

    public int getPreloadedItemId() {
        return this.zzexk;
    }

    public MediaQueueItem getQueueItem(int i) {
        return getItemByIndex(i);
    }

    public MediaQueueItem getQueueItemById(int i) {
        return getItemById(i);
    }

    public int getQueueItemCount() {
        return this.zzexm.size();
    }

    public List<MediaQueueItem> getQueueItems() {
        return this.zzexm;
    }

    public int getQueueRepeatMode() {
        return this.zzexl;
    }

    public long getStreamPosition() {
        return this.zzexf;
    }

    public double getStreamVolume() {
        return this.zzexh;
    }

    public VideoInfo getVideoInfo() {
        return this.zzexp;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzewh, Long.valueOf(this.zzexb), Integer.valueOf(this.zzexc), Double.valueOf(this.zzewk), Integer.valueOf(this.zzexd), Integer.valueOf(this.zzexe), Long.valueOf(this.zzexf), Long.valueOf(this.zzexg), Double.valueOf(this.zzexh), Boolean.valueOf(this.zzexi), Integer.valueOf(Arrays.hashCode(this.zzewl)), Integer.valueOf(this.zzexj), Integer.valueOf(this.zzexk), String.valueOf(this.zzess), Integer.valueOf(this.zzexl), this.zzexm, Boolean.valueOf(this.zzexn)});
    }

    public boolean isMediaCommandSupported(long j) {
        return (j & this.zzexg) != 0;
    }

    public boolean isMute() {
        return this.zzexi;
    }

    public boolean isPlayingAd() {
        return this.zzexn;
    }

    public void writeToParcel(Parcel parcel, int i) {
        JSONObject jSONObject = this.zzess;
        this.zzesn = jSONObject == null ? null : jSONObject.toString();
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) getMediaInfo(), i, false);
        zzbgo.zza(parcel, 3, this.zzexb);
        zzbgo.zzc(parcel, 4, getCurrentItemId());
        zzbgo.zza(parcel, 5, getPlaybackRate());
        zzbgo.zzc(parcel, 6, getPlayerState());
        zzbgo.zzc(parcel, 7, getIdleReason());
        zzbgo.zza(parcel, 8, getStreamPosition());
        zzbgo.zza(parcel, 9, this.zzexg);
        zzbgo.zza(parcel, 10, getStreamVolume());
        zzbgo.zza(parcel, 11, isMute());
        zzbgo.zza(parcel, 12, getActiveTrackIds(), false);
        zzbgo.zzc(parcel, 13, getLoadingItemId());
        zzbgo.zzc(parcel, 14, getPreloadedItemId());
        zzbgo.zza(parcel, 15, this.zzesn, false);
        zzbgo.zzc(parcel, 16, this.zzexl);
        zzbgo.zzc(parcel, 17, this.zzexm, false);
        zzbgo.zza(parcel, 18, isPlayingAd());
        zzbgo.zza(parcel, 19, (Parcelable) getAdBreakStatus(), i, false);
        zzbgo.zza(parcel, 20, (Parcelable) getVideoInfo(), i, false);
        zzbgo.zzai(parcel, zze);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x01e0, code lost:
        if (r7 != 3) goto L_0x01e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x01e3, code lost:
        if (r15 == 2) goto L_0x01da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x01e6, code lost:
        if (r8 != 0) goto L_0x01da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x02d2, code lost:
        if (r15 == false) goto L_0x02f1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x01ec  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x024b  */
    /* JADX WARNING: Removed duplicated region for block: B:179:0x02d5  */
    /* JADX WARNING: Removed duplicated region for block: B:190:0x030b  */
    /* JADX WARNING: Removed duplicated region for block: B:203:0x033a  */
    /* JADX WARNING: Removed duplicated region for block: B:213:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0149  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zza(org.json.JSONObject r14, int r15) throws org.json.JSONException {
        /*
            r13 = this;
            java.lang.String r0 = "mediaSessionId"
            long r0 = r14.getLong(r0)
            long r2 = r13.zzexb
            r4 = 0
            r5 = 1
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 == 0) goto L_0x0012
            r13.zzexb = r0
            r0 = 1
            goto L_0x0013
        L_0x0012:
            r0 = 0
        L_0x0013:
            java.lang.String r1 = "playerState"
            boolean r2 = r14.has(r1)
            r3 = 3
            r6 = 2
            if (r2 == 0) goto L_0x0091
            java.lang.String r1 = r14.getString(r1)
            java.lang.String r2 = "IDLE"
            boolean r2 = r1.equals(r2)
            r7 = 4
            if (r2 == 0) goto L_0x002c
            r1 = 1
            goto L_0x004b
        L_0x002c:
            java.lang.String r2 = "PLAYING"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0036
            r1 = 2
            goto L_0x004b
        L_0x0036:
            java.lang.String r2 = "PAUSED"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0040
            r1 = 3
            goto L_0x004b
        L_0x0040:
            java.lang.String r2 = "BUFFERING"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x004a
            r1 = 4
            goto L_0x004b
        L_0x004a:
            r1 = 0
        L_0x004b:
            int r2 = r13.zzexd
            if (r1 == r2) goto L_0x0053
            r13.zzexd = r1
            r0 = r0 | 2
        L_0x0053:
            if (r1 != r5) goto L_0x0091
            java.lang.String r1 = "idleReason"
            boolean r2 = r14.has(r1)
            if (r2 == 0) goto L_0x0091
            java.lang.String r1 = r14.getString(r1)
            java.lang.String r2 = "CANCELLED"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x006b
            r7 = 2
            goto L_0x0089
        L_0x006b:
            java.lang.String r2 = "INTERRUPTED"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0075
            r7 = 3
            goto L_0x0089
        L_0x0075:
            java.lang.String r2 = "FINISHED"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x007f
            r7 = 1
            goto L_0x0089
        L_0x007f:
            java.lang.String r2 = "ERROR"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0088
            goto L_0x0089
        L_0x0088:
            r7 = 0
        L_0x0089:
            int r1 = r13.zzexe
            if (r7 == r1) goto L_0x0091
            r13.zzexe = r7
            r0 = r0 | 2
        L_0x0091:
            java.lang.String r1 = "playbackRate"
            boolean r2 = r14.has(r1)
            if (r2 == 0) goto L_0x00a7
            double r1 = r14.getDouble(r1)
            double r7 = r13.zzewk
            int r9 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r9 == 0) goto L_0x00a7
            r13.zzewk = r1
            r0 = r0 | 2
        L_0x00a7:
            java.lang.String r1 = "currentTime"
            boolean r2 = r14.has(r1)
            if (r2 == 0) goto L_0x00c9
            r2 = r15 & 2
            if (r2 != 0) goto L_0x00c9
            double r1 = r14.getDouble(r1)
            r7 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r1 = r1 * r7
            long r1 = (long) r1
            long r7 = r13.zzexf
            int r9 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x00c9
            r13.zzexf = r1
            r0 = r0 | 2
        L_0x00c9:
            java.lang.String r1 = "supportedMediaCommands"
            boolean r2 = r14.has(r1)
            if (r2 == 0) goto L_0x00df
            long r1 = r14.getLong(r1)
            long r7 = r13.zzexg
            int r9 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x00df
            r13.zzexg = r1
            r0 = r0 | 2
        L_0x00df:
            java.lang.String r1 = "volume"
            boolean r2 = r14.has(r1)
            if (r2 == 0) goto L_0x010c
            r15 = r15 & r5
            if (r15 != 0) goto L_0x010c
            org.json.JSONObject r15 = r14.getJSONObject(r1)
            java.lang.String r1 = "level"
            double r1 = r15.getDouble(r1)
            double r7 = r13.zzexh
            int r9 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x00fe
            r13.zzexh = r1
            r0 = r0 | 2
        L_0x00fe:
            java.lang.String r1 = "muted"
            boolean r15 = r15.getBoolean(r1)
            boolean r1 = r13.zzexi
            if (r15 == r1) goto L_0x010c
            r13.zzexi = r15
            r0 = r0 | 2
        L_0x010c:
            java.lang.String r15 = "activeTrackIds"
            boolean r1 = r14.has(r15)
            r2 = 0
            if (r1 == 0) goto L_0x014c
            org.json.JSONArray r15 = r14.getJSONArray(r15)
            int r1 = r15.length()
            long[] r7 = new long[r1]
            r8 = 0
        L_0x0120:
            if (r8 >= r1) goto L_0x012b
            long r9 = r15.getLong(r8)
            r7[r8] = r9
            int r8 = r8 + 1
            goto L_0x0120
        L_0x012b:
            long[] r15 = r13.zzewl
            if (r15 != 0) goto L_0x0131
        L_0x012f:
            r15 = 1
            goto L_0x0147
        L_0x0131:
            int r15 = r15.length
            if (r15 == r1) goto L_0x0135
            goto L_0x012f
        L_0x0135:
            r15 = 0
        L_0x0136:
            if (r15 >= r1) goto L_0x0146
            long[] r8 = r13.zzewl
            r9 = r8[r15]
            r11 = r7[r15]
            int r8 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r8 == 0) goto L_0x0143
            goto L_0x012f
        L_0x0143:
            int r15 = r15 + 1
            goto L_0x0136
        L_0x0146:
            r15 = 0
        L_0x0147:
            if (r15 == 0) goto L_0x0154
            r13.zzewl = r7
            goto L_0x0154
        L_0x014c:
            long[] r15 = r13.zzewl
            r7 = r2
            if (r15 == 0) goto L_0x0153
            r15 = 1
            goto L_0x0154
        L_0x0153:
            r15 = 0
        L_0x0154:
            if (r15 == 0) goto L_0x015a
            r13.zzewl = r7
            r0 = r0 | 2
        L_0x015a:
            java.lang.String r15 = "customData"
            boolean r1 = r14.has(r15)
            if (r1 == 0) goto L_0x016c
            org.json.JSONObject r15 = r14.getJSONObject(r15)
            r13.zzess = r15
            r13.zzesn = r2
            r0 = r0 | 2
        L_0x016c:
            java.lang.String r15 = "media"
            boolean r1 = r14.has(r15)
            if (r1 == 0) goto L_0x0197
            org.json.JSONObject r15 = r14.getJSONObject(r15)
            com.google.android.gms.cast.MediaInfo r1 = new com.google.android.gms.cast.MediaInfo
            r1.<init>((org.json.JSONObject) r15)
            com.google.android.gms.cast.MediaInfo r2 = r13.zzewh
            if (r2 == 0) goto L_0x0189
            if (r2 == 0) goto L_0x018d
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L_0x018d
        L_0x0189:
            r13.zzewh = r1
            r0 = r0 | 2
        L_0x018d:
            java.lang.String r1 = "metadata"
            boolean r15 = r15.has(r1)
            if (r15 == 0) goto L_0x0197
            r0 = r0 | 4
        L_0x0197:
            java.lang.String r15 = "currentItemId"
            boolean r1 = r14.has(r15)
            if (r1 == 0) goto L_0x01ab
            int r15 = r14.getInt(r15)
            int r1 = r13.zzexc
            if (r1 == r15) goto L_0x01ab
            r13.zzexc = r15
            r0 = r0 | 2
        L_0x01ab:
            java.lang.String r15 = "preloadedItemId"
            int r15 = r14.optInt(r15, r4)
            int r1 = r13.zzexk
            if (r1 == r15) goto L_0x01b9
            r13.zzexk = r15
            r0 = r0 | 16
        L_0x01b9:
            java.lang.String r15 = "loadingItemId"
            int r15 = r14.optInt(r15, r4)
            int r1 = r13.zzexj
            if (r1 == r15) goto L_0x01c7
            r13.zzexj = r15
            r0 = r0 | 2
        L_0x01c7:
            com.google.android.gms.cast.MediaInfo r15 = r13.zzewh
            r1 = -1
            if (r15 != 0) goto L_0x01ce
            r15 = -1
            goto L_0x01d2
        L_0x01ce:
            int r15 = r15.getStreamType()
        L_0x01d2:
            int r2 = r13.zzexd
            int r7 = r13.zzexe
            int r8 = r13.zzexj
            if (r2 == r5) goto L_0x01dc
        L_0x01da:
            r15 = 0
            goto L_0x01ea
        L_0x01dc:
            if (r7 == r5) goto L_0x01e6
            if (r7 == r6) goto L_0x01e3
            if (r7 == r3) goto L_0x01e6
            goto L_0x01e9
        L_0x01e3:
            if (r15 != r6) goto L_0x01e9
            goto L_0x01da
        L_0x01e6:
            if (r8 == 0) goto L_0x01e9
            goto L_0x01da
        L_0x01e9:
            r15 = 1
        L_0x01ea:
            if (r15 != 0) goto L_0x02d5
            java.lang.String r15 = "repeatMode"
            boolean r2 = r14.has(r15)
            if (r2 == 0) goto L_0x0242
            int r2 = r13.zzexl
            java.lang.String r15 = r14.getString(r15)
            r15.hashCode()
            int r7 = r15.hashCode()
            switch(r7) {
                case -1118317585: goto L_0x0226;
                case -962896020: goto L_0x021b;
                case 1645938909: goto L_0x0210;
                case 1645952171: goto L_0x0205;
                default: goto L_0x0204;
            }
        L_0x0204:
            goto L_0x0230
        L_0x0205:
            java.lang.String r7 = "REPEAT_OFF"
            boolean r15 = r15.equals(r7)
            if (r15 != 0) goto L_0x020e
            goto L_0x0230
        L_0x020e:
            r1 = 3
            goto L_0x0230
        L_0x0210:
            java.lang.String r7 = "REPEAT_ALL"
            boolean r15 = r15.equals(r7)
            if (r15 != 0) goto L_0x0219
            goto L_0x0230
        L_0x0219:
            r1 = 2
            goto L_0x0230
        L_0x021b:
            java.lang.String r7 = "REPEAT_SINGLE"
            boolean r15 = r15.equals(r7)
            if (r15 != 0) goto L_0x0224
            goto L_0x0230
        L_0x0224:
            r1 = 1
            goto L_0x0230
        L_0x0226:
            java.lang.String r7 = "REPEAT_ALL_AND_SHUFFLE"
            boolean r15 = r15.equals(r7)
            if (r15 != 0) goto L_0x022f
            goto L_0x0230
        L_0x022f:
            r1 = 0
        L_0x0230:
            switch(r1) {
                case 0: goto L_0x023a;
                case 1: goto L_0x0239;
                case 2: goto L_0x0237;
                case 3: goto L_0x0235;
                default: goto L_0x0233;
            }
        L_0x0233:
            r3 = r2
            goto L_0x023a
        L_0x0235:
            r3 = 0
            goto L_0x023a
        L_0x0237:
            r3 = 1
            goto L_0x023a
        L_0x0239:
            r3 = 2
        L_0x023a:
            int r15 = r13.zzexl
            if (r15 == r3) goto L_0x0242
            r13.zzexl = r3
            r15 = 1
            goto L_0x0243
        L_0x0242:
            r15 = 0
        L_0x0243:
            java.lang.String r1 = "items"
            boolean r2 = r14.has(r1)
            if (r2 == 0) goto L_0x02d2
            org.json.JSONArray r1 = r14.getJSONArray(r1)
            int r2 = r1.length()
            android.util.SparseArray r3 = new android.util.SparseArray
            r3.<init>()
            r6 = 0
        L_0x0259:
            if (r6 >= r2) goto L_0x026f
            org.json.JSONObject r7 = r1.getJSONObject(r6)
            java.lang.String r8 = "itemId"
            int r7 = r7.getInt(r8)
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r3.put(r6, r7)
            int r6 = r6 + 1
            goto L_0x0259
        L_0x026f:
            com.google.android.gms.cast.MediaQueueItem[] r6 = new com.google.android.gms.cast.MediaQueueItem[r2]
            r7 = 0
        L_0x0272:
            if (r7 >= r2) goto L_0x02c6
            java.lang.Object r8 = r3.get(r7)
            java.lang.Integer r8 = (java.lang.Integer) r8
            org.json.JSONObject r9 = r1.getJSONObject(r7)
            int r10 = r8.intValue()
            com.google.android.gms.cast.MediaQueueItem r10 = r13.getItemById(r10)
            if (r10 == 0) goto L_0x029e
            boolean r9 = r10.zzu(r9)
            r15 = r15 | r9
            r6[r7] = r10
            int r8 = r8.intValue()
            java.lang.Integer r8 = r13.getIndexById(r8)
            int r8 = r8.intValue()
            if (r7 == r8) goto L_0x02c3
            goto L_0x02c2
        L_0x029e:
            int r15 = r8.intValue()
            int r8 = r13.zzexc
            if (r15 != r8) goto L_0x02bb
            com.google.android.gms.cast.MediaInfo r15 = r13.zzewh
            if (r15 == 0) goto L_0x02bb
            com.google.android.gms.cast.MediaQueueItem$Builder r8 = new com.google.android.gms.cast.MediaQueueItem$Builder
            r8.<init>((com.google.android.gms.cast.MediaInfo) r15)
            com.google.android.gms.cast.MediaQueueItem r15 = r8.build()
            r6[r7] = r15
            r15 = r6[r7]
            r15.zzu(r9)
            goto L_0x02c2
        L_0x02bb:
            com.google.android.gms.cast.MediaQueueItem r15 = new com.google.android.gms.cast.MediaQueueItem
            r15.<init>((org.json.JSONObject) r9)
            r6[r7] = r15
        L_0x02c2:
            r15 = 1
        L_0x02c3:
            int r7 = r7 + 1
            goto L_0x0272
        L_0x02c6:
            java.util.ArrayList<com.google.android.gms.cast.MediaQueueItem> r1 = r13.zzexm
            int r1 = r1.size()
            if (r1 == r2) goto L_0x02cf
            r15 = 1
        L_0x02cf:
            r13.zza(r6)
        L_0x02d2:
            if (r15 == 0) goto L_0x02f1
            goto L_0x02ef
        L_0x02d5:
            r13.zzexc = r4
            r13.zzexj = r4
            r13.zzexk = r4
            java.util.ArrayList<com.google.android.gms.cast.MediaQueueItem> r15 = r13.zzexm
            boolean r15 = r15.isEmpty()
            if (r15 != 0) goto L_0x02f1
            r13.zzexl = r4
            java.util.ArrayList<com.google.android.gms.cast.MediaQueueItem> r15 = r13.zzexm
            r15.clear()
            android.util.SparseArray<java.lang.Integer> r15 = r13.zzexq
            r15.clear()
        L_0x02ef:
            r0 = r0 | 8
        L_0x02f1:
            java.lang.String r15 = "breakStatus"
            org.json.JSONObject r15 = r14.optJSONObject(r15)
            com.google.android.gms.cast.AdBreakStatus r15 = com.google.android.gms.cast.AdBreakStatus.zzr(r15)
            com.google.android.gms.cast.AdBreakStatus r1 = r13.zzexo
            if (r1 != 0) goto L_0x0301
            if (r15 != 0) goto L_0x0309
        L_0x0301:
            if (r1 == 0) goto L_0x0312
            boolean r1 = r1.equals(r15)
            if (r1 != 0) goto L_0x0312
        L_0x0309:
            if (r15 == 0) goto L_0x030c
            r4 = 1
        L_0x030c:
            r13.zzexn = r4
            r13.zzexo = r15
            r0 = r0 | 32
        L_0x0312:
            java.lang.String r15 = "videoInfo"
            org.json.JSONObject r15 = r14.optJSONObject(r15)
            com.google.android.gms.cast.VideoInfo r15 = com.google.android.gms.cast.VideoInfo.zzv(r15)
            com.google.android.gms.cast.VideoInfo r1 = r13.zzexp
            if (r1 != 0) goto L_0x0322
            if (r15 != 0) goto L_0x032a
        L_0x0322:
            if (r1 == 0) goto L_0x032e
            boolean r1 = r1.equals(r15)
            if (r1 != 0) goto L_0x032e
        L_0x032a:
            r13.zzexp = r15
            r0 = r0 | 64
        L_0x032e:
            java.lang.String r15 = "breakInfo"
            boolean r1 = r14.has(r15)
            if (r1 == 0) goto L_0x0343
            com.google.android.gms.cast.MediaInfo r1 = r13.zzewh
            if (r1 == 0) goto L_0x0343
            org.json.JSONObject r14 = r14.getJSONObject(r15)
            r1.zzs(r14)
            r0 = r0 | 2
        L_0x0343:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.MediaStatus.zza(org.json.JSONObject, int):int");
    }

    public final long zzadv() {
        return this.zzexb;
    }

    public final void zzbb(boolean z) {
        this.zzexn = z;
    }
}
