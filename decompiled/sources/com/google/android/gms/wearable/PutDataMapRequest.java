package com.google.android.gms.wearable;

import android.net.Uri;
import android.util.Log;
import com.google.android.gms.common.internal.zzc;
import com.google.android.gms.internal.zzdng;
import com.google.android.gms.internal.zzdnh;
import com.google.android.gms.internal.zzfls;

public class PutDataMapRequest {
    private final DataMap zzlqv;
    private final PutDataRequest zzlqw;

    private PutDataMapRequest(PutDataRequest putDataRequest, DataMap dataMap) {
        this.zzlqw = putDataRequest;
        DataMap dataMap2 = new DataMap();
        this.zzlqv = dataMap2;
        if (dataMap != null) {
            dataMap2.putAll(dataMap);
        }
    }

    public static PutDataMapRequest create(String str) {
        zzc.zzb(str, "path must not be null");
        return new PutDataMapRequest(PutDataRequest.create(str), (DataMap) null);
    }

    public static PutDataMapRequest createFromDataMapItem(DataMapItem dataMapItem) {
        zzc.zzb(dataMapItem, "source must not be null");
        return new PutDataMapRequest(PutDataRequest.zzs(dataMapItem.getUri()), dataMapItem.getDataMap());
    }

    public static PutDataMapRequest createWithAutoAppendedId(String str) {
        zzc.zzb(str, "pathPrefix must not be null");
        return new PutDataMapRequest(PutDataRequest.createWithAutoAppendedId(str), (DataMap) null);
    }

    public PutDataRequest asPutDataRequest() {
        zzdnh zza = zzdng.zza(this.zzlqv);
        this.zzlqw.setData(zzfls.zzc(zza.zzlwk));
        int size = zza.zzlwl.size();
        int i = 0;
        while (i < size) {
            String num = Integer.toString(i);
            Asset asset = zza.zzlwl.get(i);
            if (num == null) {
                String valueOf = String.valueOf(asset);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 26);
                sb.append("asset key cannot be null: ");
                sb.append(valueOf);
                throw new IllegalStateException(sb.toString());
            } else if (asset == null) {
                String valueOf2 = String.valueOf(num);
                throw new IllegalStateException(valueOf2.length() != 0 ? "asset cannot be null: key=".concat(valueOf2) : new String("asset cannot be null: key="));
            } else {
                if (Log.isLoggable(DataMap.TAG, 3)) {
                    String valueOf3 = String.valueOf(asset);
                    StringBuilder sb2 = new StringBuilder(String.valueOf(num).length() + 33 + String.valueOf(valueOf3).length());
                    sb2.append("asPutDataRequest: adding asset: ");
                    sb2.append(num);
                    sb2.append(" ");
                    sb2.append(valueOf3);
                    Log.d(DataMap.TAG, sb2.toString());
                }
                this.zzlqw.putAsset(num, asset);
                i++;
            }
        }
        return this.zzlqw;
    }

    public DataMap getDataMap() {
        return this.zzlqv;
    }

    public Uri getUri() {
        return this.zzlqw.getUri();
    }

    public boolean isUrgent() {
        return this.zzlqw.isUrgent();
    }

    public PutDataMapRequest setUrgent() {
        this.zzlqw.setUrgent();
        return this;
    }
}
