package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.awareness.state.Weather;

public final class zzbac extends zzbgl implements Weather {
    public static final Parcelable.Creator<zzbac> CREATOR = new zzbad();
    private final float zzese;
    private final float zzesf;
    private final float zzesg;
    private final int zzesh;
    private final int[] zzesi;

    public zzbac(float f, float f2, float f3, int i, int[] iArr) {
        this.zzese = f;
        this.zzesf = f2;
        this.zzesg = f3;
        this.zzesh = i;
        this.zzesi = iArr;
    }

    private static float zza(int i, float f) {
        if (i == 1) {
            return f;
        }
        if (i == 2) {
            return ((f - 32.0f) * 5.0f) / 9.0f;
        }
        zzfi.zza("WeatherImpl", "Invalid temperature unit %s", (Object) Integer.valueOf(i));
        throw new IllegalArgumentException("Invalid temperature unit");
    }

    public final int[] getConditions() {
        return this.zzesi;
    }

    public final float getDewPoint(int i) {
        return zza(i, this.zzesg);
    }

    public final float getFeelsLikeTemperature(int i) {
        return zza(i, this.zzesf);
    }

    public final int getHumidity() {
        return this.zzesh;
    }

    public final float getTemperature(int i) {
        return zza(i, this.zzese);
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("Temp=");
        boolean z = true;
        sb.append(getTemperature(1));
        sb.append("F/");
        sb.append(getTemperature(2));
        sb.append("C, Feels=");
        sb.append(getFeelsLikeTemperature(1));
        sb.append("F/");
        sb.append(getFeelsLikeTemperature(2));
        sb.append("C, Dew=");
        sb.append(getDewPoint(1));
        sb.append("F/");
        sb.append(getDewPoint(2));
        sb.append("C, Humidity=");
        sb.append(getHumidity());
        sb.append(", Condition=");
        if (getConditions() == null) {
            str = "unknown";
        } else {
            sb.append("[");
            int[] conditions = getConditions();
            int length = conditions.length;
            int i = 0;
            while (i < length) {
                int i2 = conditions[i];
                if (!z) {
                    sb.append(",");
                }
                sb.append(i2);
                i++;
                z = false;
            }
            str = "]";
        }
        sb.append(str);
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzese);
        zzbgo.zza(parcel, 3, this.zzesf);
        zzbgo.zza(parcel, 4, this.zzesg);
        zzbgo.zzc(parcel, 5, getHumidity());
        zzbgo.zza(parcel, 6, getConditions(), false);
        zzbgo.zzai(parcel, zze);
    }
}
