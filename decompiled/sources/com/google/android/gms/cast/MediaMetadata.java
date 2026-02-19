package com.google.android.gms.cast;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.internal.zzbep;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.plus.PlusShare;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class MediaMetadata extends zzbgl {
    public static final Parcelable.Creator<MediaMetadata> CREATOR = new zzag();
    public static final String KEY_ALBUM_ARTIST = "com.google.android.gms.cast.metadata.ALBUM_ARTIST";
    public static final String KEY_ALBUM_TITLE = "com.google.android.gms.cast.metadata.ALBUM_TITLE";
    public static final String KEY_ARTIST = "com.google.android.gms.cast.metadata.ARTIST";
    public static final String KEY_BROADCAST_DATE = "com.google.android.gms.cast.metadata.BROADCAST_DATE";
    public static final String KEY_COMPOSER = "com.google.android.gms.cast.metadata.COMPOSER";
    public static final String KEY_CREATION_DATE = "com.google.android.gms.cast.metadata.CREATION_DATE";
    public static final String KEY_DISC_NUMBER = "com.google.android.gms.cast.metadata.DISC_NUMBER";
    public static final String KEY_EPISODE_NUMBER = "com.google.android.gms.cast.metadata.EPISODE_NUMBER";
    public static final String KEY_HEIGHT = "com.google.android.gms.cast.metadata.HEIGHT";
    public static final String KEY_LOCATION_LATITUDE = "com.google.android.gms.cast.metadata.LOCATION_LATITUDE";
    public static final String KEY_LOCATION_LONGITUDE = "com.google.android.gms.cast.metadata.LOCATION_LONGITUDE";
    public static final String KEY_LOCATION_NAME = "com.google.android.gms.cast.metadata.LOCATION_NAME";
    public static final String KEY_RELEASE_DATE = "com.google.android.gms.cast.metadata.RELEASE_DATE";
    public static final String KEY_SEASON_NUMBER = "com.google.android.gms.cast.metadata.SEASON_NUMBER";
    public static final String KEY_SERIES_TITLE = "com.google.android.gms.cast.metadata.SERIES_TITLE";
    public static final String KEY_STUDIO = "com.google.android.gms.cast.metadata.STUDIO";
    public static final String KEY_SUBTITLE = "com.google.android.gms.cast.metadata.SUBTITLE";
    public static final String KEY_TITLE = "com.google.android.gms.cast.metadata.TITLE";
    public static final String KEY_TRACK_NUMBER = "com.google.android.gms.cast.metadata.TRACK_NUMBER";
    public static final String KEY_WIDTH = "com.google.android.gms.cast.metadata.WIDTH";
    public static final int MEDIA_TYPE_GENERIC = 0;
    public static final int MEDIA_TYPE_MOVIE = 1;
    public static final int MEDIA_TYPE_MUSIC_TRACK = 3;
    public static final int MEDIA_TYPE_PHOTO = 4;
    public static final int MEDIA_TYPE_TV_SHOW = 2;
    public static final int MEDIA_TYPE_USER = 100;
    private static final String[] zzewo = {null, "String", "int", "double", "ISO-8601 date String"};
    private static final zza zzewp = new zza().zzb(KEY_CREATION_DATE, "creationDateTime", 4).zzb(KEY_RELEASE_DATE, "releaseDate", 4).zzb(KEY_BROADCAST_DATE, "originalAirdate", 4).zzb(KEY_TITLE, PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, 1).zzb(KEY_SUBTITLE, "subtitle", 1).zzb(KEY_ARTIST, "artist", 1).zzb(KEY_ALBUM_ARTIST, "albumArtist", 1).zzb(KEY_ALBUM_TITLE, "albumName", 1).zzb(KEY_COMPOSER, "composer", 1).zzb(KEY_DISC_NUMBER, "discNumber", 2).zzb(KEY_TRACK_NUMBER, "trackNumber", 2).zzb(KEY_SEASON_NUMBER, "season", 2).zzb(KEY_EPISODE_NUMBER, "episode", 2).zzb(KEY_SERIES_TITLE, "seriesTitle", 1).zzb(KEY_STUDIO, "studio", 1).zzb(KEY_WIDTH, "width", 2).zzb(KEY_HEIGHT, "height", 2).zzb(KEY_LOCATION_NAME, FirebaseAnalytics.Param.LOCATION, 1).zzb(KEY_LOCATION_LATITUDE, "latitude", 3).zzb(KEY_LOCATION_LONGITUDE, "longitude", 3);
    private final List<WebImage> zzbxw;
    private Bundle zzewq;
    private int zzewr;

    static class zza {
        private final Map<String, String> zzews = new HashMap();
        private final Map<String, String> zzewt = new HashMap();
        private final Map<String, Integer> zzewu = new HashMap();

        public final zza zzb(String str, String str2, int i) {
            this.zzews.put(str, str2);
            this.zzewt.put(str2, str);
            this.zzewu.put(str, Integer.valueOf(i));
            return this;
        }

        public final String zzfl(String str) {
            return this.zzews.get(str);
        }

        public final String zzfm(String str) {
            return this.zzewt.get(str);
        }

        public final int zzfn(String str) {
            Integer num = this.zzewu.get(str);
            if (num != null) {
                return num.intValue();
            }
            return 0;
        }
    }

    public MediaMetadata() {
        this(0);
    }

    public MediaMetadata(int i) {
        this(new ArrayList(), new Bundle(), i);
    }

    MediaMetadata(List<WebImage> list, Bundle bundle, int i) {
        this.zzbxw = list;
        this.zzewq = bundle;
        this.zzewr = i;
    }

    private final void zza(JSONObject jSONObject, String... strArr) {
        try {
            for (String str : strArr) {
                if (this.zzewq.containsKey(str)) {
                    zza zza2 = zzewp;
                    int zzfn = zza2.zzfn(str);
                    if (zzfn != 1) {
                        if (zzfn == 2) {
                            jSONObject.put(zza2.zzfl(str), this.zzewq.getInt(str));
                        } else if (zzfn == 3) {
                            jSONObject.put(zza2.zzfl(str), this.zzewq.getDouble(str));
                        } else if (zzfn != 4) {
                        }
                    }
                    jSONObject.put(zza2.zzfl(str), this.zzewq.getString(str));
                }
            }
            for (String str2 : this.zzewq.keySet()) {
                if (!str2.startsWith("com.google.")) {
                    Object obj = this.zzewq.get(str2);
                    if (!(obj instanceof String)) {
                        if (!(obj instanceof Integer)) {
                            if (!(obj instanceof Double)) {
                            }
                        }
                    }
                    jSONObject.put(str2, obj);
                }
            }
        } catch (JSONException unused) {
        }
    }

    private final void zzb(JSONObject jSONObject, String... strArr) {
        Bundle bundle;
        HashSet hashSet = new HashSet(Arrays.asList(strArr));
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!"metadataType".equals(next)) {
                    zza zza2 = zzewp;
                    String zzfm = zza2.zzfm(next);
                    if (zzfm == null) {
                        Object obj = jSONObject.get(next);
                        if (obj instanceof String) {
                            this.zzewq.putString(next, (String) obj);
                        } else if (obj instanceof Integer) {
                            this.zzewq.putInt(next, ((Integer) obj).intValue());
                        } else if (obj instanceof Double) {
                            this.zzewq.putDouble(next, ((Double) obj).doubleValue());
                        }
                    } else if (hashSet.contains(zzfm)) {
                        try {
                            Object obj2 = jSONObject.get(next);
                            if (obj2 != null) {
                                int zzfn = zza2.zzfn(zzfm);
                                if (zzfn != 1) {
                                    if (zzfn != 2) {
                                        if (zzfn != 3) {
                                            if (zzfn == 4) {
                                                if ((obj2 instanceof String) && zzbep.zzgb((String) obj2) != null) {
                                                    this.zzewq.putString(zzfm, (String) obj2);
                                                }
                                            }
                                        } else if (obj2 instanceof Double) {
                                            this.zzewq.putDouble(zzfm, ((Double) obj2).doubleValue());
                                        }
                                    } else if (obj2 instanceof Integer) {
                                        this.zzewq.putInt(zzfm, ((Integer) obj2).intValue());
                                    }
                                } else if (obj2 instanceof String) {
                                    this.zzewq.putString(zzfm, (String) obj2);
                                }
                            }
                        } catch (JSONException unused) {
                        }
                    }
                }
            }
        } catch (JSONException unused2) {
        }
    }

    private final boolean zzb(Bundle bundle, Bundle bundle2) {
        if (bundle.size() != bundle2.size()) {
            return false;
        }
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            Object obj2 = bundle2.get(str);
            if ((obj instanceof Bundle) && (obj2 instanceof Bundle) && !zzb((Bundle) obj, (Bundle) obj2)) {
                return false;
            }
            if (obj == null) {
                if (obj2 != null || !bundle2.containsKey(str)) {
                    return false;
                }
            } else if (!obj.equals(obj2)) {
                return false;
            }
        }
        return true;
    }

    private static void zzf(String str, int i) throws IllegalArgumentException {
        if (!TextUtils.isEmpty(str)) {
            int zzfn = zzewp.zzfn(str);
            if (zzfn != i && zzfn != 0) {
                String str2 = zzewo[i];
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 21 + String.valueOf(str2).length());
                sb.append("Value for ");
                sb.append(str);
                sb.append(" must be a ");
                sb.append(str2);
                throw new IllegalArgumentException(sb.toString());
            }
            return;
        }
        throw new IllegalArgumentException("null and empty keys are not allowed");
    }

    public void addImage(WebImage webImage) {
        this.zzbxw.add(webImage);
    }

    public void clear() {
        this.zzewq.clear();
        this.zzbxw.clear();
    }

    public void clearImages() {
        this.zzbxw.clear();
    }

    public boolean containsKey(String str) {
        return this.zzewq.containsKey(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaMetadata)) {
            return false;
        }
        MediaMetadata mediaMetadata = (MediaMetadata) obj;
        return zzb(this.zzewq, mediaMetadata.zzewq) && this.zzbxw.equals(mediaMetadata.zzbxw);
    }

    public Calendar getDate(String str) {
        zzf(str, 4);
        String string = this.zzewq.getString(str);
        if (string != null) {
            return zzbep.zzgb(string);
        }
        return null;
    }

    public String getDateAsString(String str) {
        zzf(str, 4);
        return this.zzewq.getString(str);
    }

    public double getDouble(String str) {
        zzf(str, 3);
        return this.zzewq.getDouble(str);
    }

    public List<WebImage> getImages() {
        return this.zzbxw;
    }

    public int getInt(String str) {
        zzf(str, 2);
        return this.zzewq.getInt(str);
    }

    public int getMediaType() {
        return this.zzewr;
    }

    public String getString(String str) {
        zzf(str, 1);
        return this.zzewq.getString(str);
    }

    public boolean hasImages() {
        List<WebImage> list = this.zzbxw;
        return list != null && !list.isEmpty();
    }

    public int hashCode() {
        int i = 17;
        for (String str : this.zzewq.keySet()) {
            i = (i * 31) + this.zzewq.get(str).hashCode();
        }
        return (i * 31) + this.zzbxw.hashCode();
    }

    public Set<String> keySet() {
        return this.zzewq.keySet();
    }

    public void putDate(String str, Calendar calendar) {
        zzf(str, 4);
        this.zzewq.putString(str, zzbep.zza(calendar));
    }

    public void putDouble(String str, double d) {
        zzf(str, 3);
        this.zzewq.putDouble(str, d);
    }

    public void putInt(String str, int i) {
        zzf(str, 2);
        this.zzewq.putInt(str, i);
    }

    public void putString(String str, String str2) {
        zzf(str, 1);
        this.zzewq.putString(str, str2);
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("metadataType", this.zzewr);
        } catch (JSONException unused) {
        }
        zzbep.zza(jSONObject, this.zzbxw);
        int i = this.zzewr;
        if (i == 0) {
            zza(jSONObject, KEY_TITLE, KEY_ARTIST, KEY_SUBTITLE, KEY_RELEASE_DATE);
        } else if (i == 1) {
            zza(jSONObject, KEY_TITLE, KEY_STUDIO, KEY_SUBTITLE, KEY_RELEASE_DATE);
        } else if (i == 2) {
            zza(jSONObject, KEY_TITLE, KEY_SERIES_TITLE, KEY_SEASON_NUMBER, KEY_EPISODE_NUMBER, KEY_BROADCAST_DATE);
        } else if (i == 3) {
            zza(jSONObject, KEY_TITLE, KEY_ARTIST, KEY_ALBUM_TITLE, KEY_ALBUM_ARTIST, KEY_COMPOSER, KEY_TRACK_NUMBER, KEY_DISC_NUMBER, KEY_RELEASE_DATE);
        } else if (i != 4) {
            zza(jSONObject, new String[0]);
        } else {
            zza(jSONObject, KEY_TITLE, KEY_ARTIST, KEY_LOCATION_NAME, KEY_LOCATION_LATITUDE, KEY_LOCATION_LONGITUDE, KEY_WIDTH, KEY_HEIGHT, KEY_CREATION_DATE);
        }
        return jSONObject;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, getImages(), false);
        zzbgo.zza(parcel, 3, this.zzewq, false);
        zzbgo.zzc(parcel, 4, getMediaType());
        zzbgo.zzai(parcel, zze);
    }

    public final void zzt(JSONObject jSONObject) {
        clear();
        this.zzewr = 0;
        try {
            this.zzewr = jSONObject.getInt("metadataType");
        } catch (JSONException unused) {
        }
        zzbep.zza(this.zzbxw, jSONObject);
        int i = this.zzewr;
        if (i == 0) {
            zzb(jSONObject, KEY_TITLE, KEY_ARTIST, KEY_SUBTITLE, KEY_RELEASE_DATE);
        } else if (i == 1) {
            zzb(jSONObject, KEY_TITLE, KEY_STUDIO, KEY_SUBTITLE, KEY_RELEASE_DATE);
        } else if (i == 2) {
            zzb(jSONObject, KEY_TITLE, KEY_SERIES_TITLE, KEY_SEASON_NUMBER, KEY_EPISODE_NUMBER, KEY_BROADCAST_DATE);
        } else if (i == 3) {
            zzb(jSONObject, KEY_TITLE, KEY_ALBUM_TITLE, KEY_ARTIST, KEY_ALBUM_ARTIST, KEY_COMPOSER, KEY_TRACK_NUMBER, KEY_DISC_NUMBER, KEY_RELEASE_DATE);
        } else if (i != 4) {
            zzb(jSONObject, new String[0]);
        } else {
            zzb(jSONObject, KEY_TITLE, KEY_ARTIST, KEY_LOCATION_NAME, KEY_LOCATION_LATITUDE, KEY_LOCATION_LONGITUDE, KEY_WIDTH, KEY_HEIGHT, KEY_CREATION_DATE);
        }
    }
}
