package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.DataItemAsset;

public final class zzcz implements DataItemAsset {
    private final String zzbkr;
    private final String zzbzd;

    public zzcz(DataItemAsset dataItemAsset) {
        this.zzbzd = dataItemAsset.getId();
        this.zzbkr = dataItemAsset.getDataItemKey();
    }

    public final DataItemAsset freeze() {
        return this;
    }

    public final String getDataItemKey() {
        return this.zzbkr;
    }

    public final String getId() {
        return this.zzbzd;
    }

    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("DataItemAssetEntity[");
        sb.append("@");
        sb.append(Integer.toHexString(hashCode()));
        if (this.zzbzd == null) {
            str = ",noid";
        } else {
            sb.append(",");
            str = this.zzbzd;
        }
        sb.append(str);
        sb.append(", key=");
        sb.append(this.zzbkr);
        sb.append("]");
        return sb.toString();
    }
}
