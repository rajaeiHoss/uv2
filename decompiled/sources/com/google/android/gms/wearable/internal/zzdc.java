package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.util.Log;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataItemAsset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzdc implements DataItem {
    private Uri mUri;
    private byte[] zzigl;
    private Map<String, DataItemAsset> zzluf;

    public zzdc(DataItem dataItem) {
        this.mUri = dataItem.getUri();
        this.zzigl = dataItem.getData();
        HashMap hashMap = new HashMap();
        for (Map.Entry next : dataItem.getAssets().entrySet()) {
            if (next.getKey() != null) {
                hashMap.put((String) next.getKey(), (DataItemAsset) ((DataItemAsset) next.getValue()).freeze());
            }
        }
        this.zzluf = Collections.unmodifiableMap(hashMap);
    }

    public final DataItem freeze() {
        return this;
    }

    public final Map<String, DataItemAsset> getAssets() {
        return this.zzluf;
    }

    public final byte[] getData() {
        return this.zzigl;
    }

    public final Uri getUri() {
        return this.mUri;
    }

    public final boolean isDataValid() {
        return true;
    }

    public final DataItem setData(byte[] bArr) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        boolean isLoggable = Log.isLoggable("DataItem", 3);
        StringBuilder sb = new StringBuilder("DataItemEntity{ ");
        String valueOf = String.valueOf(this.mUri);
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 4);
        sb2.append("uri=");
        sb2.append(valueOf);
        sb.append(sb2.toString());
        byte[] bArr = this.zzigl;
        String valueOf2 = String.valueOf(bArr == null ? "null" : Integer.valueOf(bArr.length));
        StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf2).length() + 9);
        sb3.append(", dataSz=");
        sb3.append(valueOf2);
        sb.append(sb3.toString());
        int size = this.zzluf.size();
        StringBuilder sb4 = new StringBuilder(23);
        sb4.append(", numAssets=");
        sb4.append(size);
        sb.append(sb4.toString());
        if (isLoggable && !this.zzluf.isEmpty()) {
            sb.append(", assets=[");
            String str = "";
            for (Map.Entry next : this.zzluf.entrySet()) {
                String str2 = (String) next.getKey();
                String id = ((DataItemAsset) next.getValue()).getId();
                StringBuilder sb5 = new StringBuilder(str.length() + 2 + String.valueOf(str2).length() + String.valueOf(id).length());
                sb5.append(str);
                sb5.append(str2);
                sb5.append(": ");
                sb5.append(id);
                sb.append(sb5.toString());
                str = ", ";
            }
            sb.append("]");
        }
        sb.append(" }");
        return sb.toString();
    }
}
