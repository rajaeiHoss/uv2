package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.awareness.state.BeaconState;
import java.util.ArrayList;
import java.util.List;

public final class zzayo extends zzbgl implements BeaconState {
    public static final Parcelable.Creator<zzayo> CREATOR = new zzayr();
    private final ArrayList<zzayp> zzeqy;

    public zzayo(ArrayList<zzayp> arrayList) {
        this.zzeqy = arrayList;
    }

    public final List<BeaconState.BeaconInfo> getBeaconInfo() {
        return (List) this.zzeqy;
    }

    public final String toString() {
        ArrayList<zzayp> arrayList = this.zzeqy;
        if (arrayList == null || arrayList.isEmpty()) {
            return "BeaconState: empty";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("BeaconState: ");
        ArrayList arrayList2 = this.zzeqy;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            sb.append((BeaconState.BeaconInfo) obj);
        }
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.zzeqy, false);
        zzbgo.zzai(parcel, zze);
    }
}
