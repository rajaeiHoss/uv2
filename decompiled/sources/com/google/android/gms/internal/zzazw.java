package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.games.GamesActivityResultCodes;
import java.util.ArrayList;
import java.util.Arrays;

public final class zzazw extends zzbgl {
    public static final Parcelable.Creator<zzazw> CREATOR = new zzazx();
    private static final int[] zzery = {GamesActivityResultCodes.RESULT_SIGN_IN_FAILED, GamesActivityResultCodes.RESULT_LICENSE_FAILED, GamesActivityResultCodes.RESULT_APP_MISCONFIGURED, GamesActivityResultCodes.RESULT_LEFT_ROOM, GamesActivityResultCodes.RESULT_NETWORK_FAILURE, GamesActivityResultCodes.RESULT_SEND_REQUEST_FAILED, GamesActivityResultCodes.RESULT_INVALID_ROOM};
    private final int zzerz;
    private final ArrayList<zzayq> zzesa;
    private final int zzesb;
    private long zzesc;

    public zzazw(int i, ArrayList<zzayq> arrayList) {
        this.zzerz = i;
        this.zzesa = arrayList;
        this.zzesb = 0;
        this.zzesc = 0;
    }

    public zzazw(int i, ArrayList<zzayq> arrayList, int i2) {
        this.zzerz = i;
        this.zzesa = arrayList;
        this.zzesb = i2;
        this.zzesc = 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzazw)) {
            return false;
        }
        zzazw zzazw = (zzazw) obj;
        if (this.zzesb != zzazw.zzesb || this.zzerz != zzazw.zzerz) {
            return false;
        }
        ArrayList<zzayq> arrayList = this.zzesa;
        if ((arrayList == null) ^ (zzazw.zzesa == null)) {
            return false;
        }
        if (arrayList != null) {
            if (arrayList.size() != zzazw.zzesa.size()) {
                return false;
            }
            ArrayList arrayList2 = this.zzesa;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj2 = arrayList2.get(i);
                i++;
                if (!zzazw.zzesa.contains((zzayq) obj2)) {
                    return false;
                }
            }
        }
        return true;
    }

    public final int hashCode() {
        int i;
        ArrayList<zzayq> arrayList = this.zzesa;
        if (arrayList != null) {
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i2 = 0;
            i = 0;
            while (i2 < size) {
                Object obj = arrayList2.get(i2);
                i2++;
                i += ((zzayq) obj).hashCode() * 13;
            }
        } else {
            i = 0;
        }
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzerz), Integer.valueOf(i), Integer.valueOf(this.zzesb)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.zzerz);
        zzbgo.zzc(parcel, 3, this.zzesa, false);
        zzbgo.zzc(parcel, 4, this.zzesb);
        zzbgo.zzai(parcel, zze);
    }
}
