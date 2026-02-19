package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.collection.ArraySet;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzcuw;
import com.google.android.gms.internal.zzcux;
import com.google.android.gms.nearby.messages.Message;
import com.streamax.config.constant.Constants;
import java.util.Arrays;

public class Update extends zzbgl implements ReflectedParcelable {
    public static final Parcelable.Creator<Update> CREATOR = new zzci();
    private int zzehz;
    public final Message zzkda;
    private int zzken;
    public final zze zzkeo;
    public final zza zzkep;
    public final zzcux zzkeq;
    private byte[] zzker;

    Update(int i, int i2, Message message, zze zze, zza zza, zzcux zzcux, byte[] bArr) {
        this.zzehz = i;
        if (zzn(i2, 2)) {
            zze = null;
            zza = null;
            zzcux = null;
            bArr = null;
            i2 = 2;
        }
        this.zzken = i2;
        this.zzkda = message;
        this.zzkeo = zze;
        this.zzkep = zza;
        this.zzkeq = zzcux;
        this.zzker = bArr;
    }

    private static boolean zzn(int i, int i2) {
        return (i & i2) != 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Update)) {
            return false;
        }
        Update update = (Update) obj;
        return this.zzken == update.zzken && zzbg.equal(this.zzkda, update.zzkda) && zzbg.equal(this.zzkeo, update.zzkeo) && zzbg.equal(this.zzkep, update.zzkep) && zzbg.equal(this.zzkeq, update.zzkeq) && Arrays.equals(this.zzker, update.zzker);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzken), this.zzkda, this.zzkeo, this.zzkep, this.zzkeq, this.zzker});
    }

    public String toString() {
        ArraySet arraySet = new ArraySet();
        if (zzeu(1)) {
            arraySet.add("FOUND");
        }
        if (zzeu(2)) {
            arraySet.add("LOST");
        }
        if (zzeu(4)) {
            arraySet.add("DISTANCE");
        }
        if (zzeu(8)) {
            arraySet.add("BLE_SIGNAL");
        }
        if (zzeu(16)) {
            arraySet.add("DEVICE");
        }
        if (zzeu(32)) {
            arraySet.add("BLE_RECORD");
        }
        String valueOf = String.valueOf(arraySet);
        String valueOf2 = String.valueOf(this.zzkda);
        String valueOf3 = String.valueOf(this.zzkeo);
        String valueOf4 = String.valueOf(this.zzkep);
        String valueOf5 = String.valueOf(this.zzkeq);
        String valueOf6 = String.valueOf(zzcuw.zzu(this.zzker));
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 68 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length() + String.valueOf(valueOf4).length() + String.valueOf(valueOf5).length() + String.valueOf(valueOf6).length());
        sb.append("Update{types=");
        sb.append(valueOf);
        sb.append(", message=");
        sb.append(valueOf2);
        sb.append(", distance=");
        sb.append(valueOf3);
        sb.append(", bleSignal=");
        sb.append(valueOf4);
        sb.append(", device=");
        sb.append(valueOf5);
        sb.append(", bleRecord=");
        sb.append(valueOf6);
        sb.append(Constants.JsonSstringSuffix);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zzehz);
        zzbgo.zzc(parcel, 2, this.zzken);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzkda, i, false);
        zzbgo.zza(parcel, 4, (Parcelable) this.zzkeo, i, false);
        zzbgo.zza(parcel, 5, (Parcelable) this.zzkep, i, false);
        zzbgo.zza(parcel, 6, (Parcelable) this.zzkeq, i, false);
        zzbgo.zza(parcel, 7, this.zzker, false);
        zzbgo.zzai(parcel, zze);
    }

    public final boolean zzeu(int i) {
        return zzn(this.zzken, i);
    }
}
