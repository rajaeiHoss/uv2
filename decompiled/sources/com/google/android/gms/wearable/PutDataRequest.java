package com.google.android.gms.wearable;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzc;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.wearable.internal.DataItemAssetParcelable;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PutDataRequest extends zzbgl {
    public static final Parcelable.Creator<PutDataRequest> CREATOR = new zzh();
    public static final String WEAR_URI_SCHEME = "wear";
    private static final long zzlqx = TimeUnit.MINUTES.toMillis(30);
    private static final Random zzlqy = new SecureRandom();
    private final Uri mUri;
    private byte[] zzigl;
    private final Bundle zzlqz;
    private long zzlra;

    private PutDataRequest(Uri uri) {
        this(uri, new Bundle(), (byte[]) null, zzlqx);
    }

    PutDataRequest(Uri uri, Bundle bundle, byte[] bArr, long j) {
        this.mUri = uri;
        this.zzlqz = bundle;
        bundle.setClassLoader(DataItemAssetParcelable.class.getClassLoader());
        this.zzigl = bArr;
        this.zzlra = j;
    }

    public static PutDataRequest create(String str) {
        zzc.zzb(str, "path must not be null");
        return zzs(zzoc(str));
    }

    public static PutDataRequest createFromDataItem(DataItem dataItem) {
        zzc.zzb(dataItem, "source must not be null");
        PutDataRequest zzs = zzs(dataItem.getUri());
        for (Map.Entry next : dataItem.getAssets().entrySet()) {
            if (((DataItemAsset) next.getValue()).getId() == null) {
                String valueOf = String.valueOf((String) next.getKey());
                throw new IllegalStateException(valueOf.length() != 0 ? "Cannot create an asset for a put request without a digest: ".concat(valueOf) : new String("Cannot create an asset for a put request without a digest: "));
            }
            zzs.putAsset((String) next.getKey(), Asset.createFromRef(((DataItemAsset) next.getValue()).getId()));
        }
        zzs.setData(dataItem.getData());
        return zzs;
    }

    public static PutDataRequest createWithAutoAppendedId(String str) {
        zzc.zzb(str, "pathPrefix must not be null");
        StringBuilder sb = new StringBuilder(str);
        if (!str.endsWith("/")) {
            sb.append("/");
        }
        sb.append("PN");
        sb.append(zzlqy.nextLong());
        return new PutDataRequest(zzoc(sb.toString()));
    }

    private static Uri zzoc(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("An empty path was supplied.");
        } else if (!str.startsWith("/")) {
            throw new IllegalArgumentException("A path must start with a single / .");
        } else if (!str.startsWith("//")) {
            return new Uri.Builder().scheme(WEAR_URI_SCHEME).path(str).build();
        } else {
            throw new IllegalArgumentException("A path must start with a single / .");
        }
    }

    public static PutDataRequest zzs(Uri uri) {
        zzc.zzb(uri, "uri must not be null");
        return new PutDataRequest(uri);
    }

    public Asset getAsset(String str) {
        zzc.zzb(str, "key must not be null");
        return (Asset) this.zzlqz.getParcelable(str);
    }

    public Map<String, Asset> getAssets() {
        HashMap hashMap = new HashMap();
        for (String str : this.zzlqz.keySet()) {
            hashMap.put(str, (Asset) this.zzlqz.getParcelable(str));
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public byte[] getData() {
        return this.zzigl;
    }

    public Uri getUri() {
        return this.mUri;
    }

    public boolean hasAsset(String str) {
        zzc.zzb(str, "key must not be null");
        return this.zzlqz.containsKey(str);
    }

    public boolean isUrgent() {
        return this.zzlra == 0;
    }

    public PutDataRequest putAsset(String str, Asset asset) {
        zzbq.checkNotNull(str);
        zzbq.checkNotNull(asset);
        this.zzlqz.putParcelable(str, asset);
        return this;
    }

    public PutDataRequest removeAsset(String str) {
        zzc.zzb(str, "key must not be null");
        this.zzlqz.remove(str);
        return this;
    }

    public PutDataRequest setData(byte[] bArr) {
        this.zzigl = bArr;
        return this;
    }

    public PutDataRequest setUrgent() {
        this.zzlra = 0;
        return this;
    }

    public String toString() {
        return toString(Log.isLoggable(DataMap.TAG, 3));
    }

    public String toString(boolean z) {
        String str;
        StringBuilder sb = new StringBuilder("PutDataRequest[");
        byte[] bArr = this.zzigl;
        String valueOf = String.valueOf(bArr == null ? "null" : Integer.valueOf(bArr.length));
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 7);
        sb2.append("dataSz=");
        sb2.append(valueOf);
        sb.append(sb2.toString());
        int size = this.zzlqz.size();
        StringBuilder sb3 = new StringBuilder(23);
        sb3.append(", numAssets=");
        sb3.append(size);
        sb.append(sb3.toString());
        String valueOf2 = String.valueOf(this.mUri);
        StringBuilder sb4 = new StringBuilder(String.valueOf(valueOf2).length() + 6);
        sb4.append(", uri=");
        sb4.append(valueOf2);
        sb.append(sb4.toString());
        long j = this.zzlra;
        StringBuilder sb5 = new StringBuilder(35);
        sb5.append(", syncDeadline=");
        sb5.append(j);
        sb.append(sb5.toString());
        if (!z) {
            str = "]";
        } else {
            sb.append("]\n  assets: ");
            for (String str2 : this.zzlqz.keySet()) {
                String valueOf3 = String.valueOf(this.zzlqz.getParcelable(str2));
                StringBuilder sb6 = new StringBuilder(String.valueOf(str2).length() + 7 + String.valueOf(valueOf3).length());
                sb6.append("\n    ");
                sb6.append(str2);
                sb6.append(": ");
                sb6.append(valueOf3);
                sb.append(sb6.toString());
            }
            str = "\n  ]";
        }
        sb.append(str);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzc.zzb(parcel, "dest must not be null");
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) getUri(), i, false);
        zzbgo.zza(parcel, 4, this.zzlqz, false);
        zzbgo.zza(parcel, 5, getData(), false);
        zzbgo.zza(parcel, 6, this.zzlra);
        zzbgo.zzai(parcel, zze);
    }
}
