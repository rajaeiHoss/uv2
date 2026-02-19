package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Configuration extends zzbgl implements Comparable<Configuration> {
    public static final Parcelable.Creator<Configuration> CREATOR = new zzc();
    private Map<String, zzi> zzfqk = new TreeMap();
    private int zzkfq;
    private zzi[] zzkfr;
    private String[] zzkfs;

    public Configuration(int i, zzi[] zziArr, String[] strArr) {
        this.zzkfq = i;
        this.zzkfr = zziArr;
        for (zzi zzi : zziArr) {
            this.zzfqk.put(zzi.name, zzi);
        }
        this.zzkfs = strArr;
        if (strArr != null) {
            Arrays.sort(strArr);
        }
    }

    public int compareTo(Configuration configuration) {
        return this.zzkfq - configuration.zzkfq;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Configuration) {
            Configuration configuration = (Configuration) obj;
            return this.zzkfq == configuration.zzkfq && zzn.equals(this.zzfqk, configuration.zzfqk) && Arrays.equals(this.zzkfs, configuration.zzkfs);
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Configuration(");
        sb.append(this.zzkfq);
        sb.append(", ");
        sb.append("(");
        for (zzi append : this.zzfqk.values()) {
            sb.append(append);
            sb.append(", ");
        }
        sb.append(")");
        sb.append(", ");
        sb.append("(");
        String[] strArr = this.zzkfs;
        if (strArr != null) {
            for (String append2 : strArr) {
                sb.append(append2);
                sb.append(", ");
            }
        } else {
            sb.append("null");
        }
        sb.append(")");
        sb.append(")");
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.zzkfq);
        zzbgo.zza(parcel, 3, this.zzkfr, i, false);
        zzbgo.zza(parcel, 4, this.zzkfs, false);
        zzbgo.zzai(parcel, zze);
    }
}
