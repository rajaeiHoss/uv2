package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataItem;

public final class zzcx implements DataEvent {
    private int zzenu;
    private DataItem zzltz;

    public zzcx(DataEvent dataEvent) {
        this.zzenu = dataEvent.getType();
        this.zzltz = (DataItem) dataEvent.getDataItem().freeze();
    }

    public final DataEvent freeze() {
        return this;
    }

    public final DataItem getDataItem() {
        return this.zzltz;
    }

    public final int getType() {
        return this.zzenu;
    }

    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        String str = getType() == 1 ? "changed" : getType() == 2 ? "deleted" : "unknown";
        String valueOf = String.valueOf(getDataItem());
        StringBuilder sb = new StringBuilder(str.length() + 35 + String.valueOf(valueOf).length());
        sb.append("DataEventEntity{ type=");
        sb.append(str);
        sb.append(", dataitem=");
        sb.append(valueOf);
        sb.append(" }");
        return sb.toString();
    }
}
