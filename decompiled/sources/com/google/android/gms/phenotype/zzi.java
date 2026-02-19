package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.Comparator;

public final class zzi extends zzbgl implements Comparable<zzi> {
    public static final Parcelable.Creator<zzi> CREATOR = new zzk();
    private static Comparator<zzi> zzkgq = new zzj();
    public final String name;
    private String zzgim;
    private boolean zzili;
    private double zzilk;
    private long zzkgm;
    private byte[] zzkgn;
    private int zzkgo;
    public final int zzkgp;

    public zzi(String str, long j, boolean z, double d, String str2, byte[] bArr, int i, int i2) {
        this.name = str;
        this.zzkgm = j;
        this.zzili = z;
        this.zzilk = d;
        this.zzgim = str2;
        this.zzkgn = bArr;
        this.zzkgo = i;
        this.zzkgp = i2;
    }

    private static int compare(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }

    public final int compareTo(zzi zzi) {
        int compareTo = this.name.compareTo(zzi.name);
        if (compareTo != 0) {
            return compareTo;
        }
        int compare = compare(this.zzkgo, zzi.zzkgo);
        if (compare != 0) {
            return compare;
        }
        int i = this.zzkgo;
        if (i == 1) {
            int i2 = (this.zzkgm > zzi.zzkgm ? 1 : (this.zzkgm == zzi.zzkgm ? 0 : -1));
            if (i2 < 0) {
                return -1;
            }
            return i2 == 0 ? 0 : 1;
        } else if (i == 2) {
            boolean z = this.zzili;
            if (z == zzi.zzili) {
                return 0;
            }
            return z ? 1 : -1;
        } else if (i == 3) {
            return Double.compare(this.zzilk, zzi.zzilk);
        } else {
            if (i == 4) {
                String str = this.zzgim;
                String str2 = zzi.zzgim;
                if (str == str2) {
                    return 0;
                }
                if (str == null) {
                    return -1;
                }
                if (str2 == null) {
                    return 1;
                }
                return str.compareTo(str2);
            } else if (i == 5) {
                byte[] bArr = this.zzkgn;
                byte[] bArr2 = zzi.zzkgn;
                if (bArr == bArr2) {
                    return 0;
                }
                if (bArr == null) {
                    return -1;
                }
                if (bArr2 == null) {
                    return 1;
                }
                for (int i3 = 0; i3 < Math.min(this.zzkgn.length, zzi.zzkgn.length); i3++) {
                    int i4 = this.zzkgn[i3] - zzi.zzkgn[i3];
                    if (i4 != 0) {
                        return i4;
                    }
                }
                return compare(this.zzkgn.length, zzi.zzkgn.length);
            } else {
                int i5 = this.zzkgo;
                StringBuilder sb = new StringBuilder(31);
                sb.append("Invalid enum value: ");
                sb.append(i5);
                throw new AssertionError(sb.toString());
            }
        }
    }

    public final boolean equals(Object obj) {
        int i;
        if (obj instanceof zzi) {
            zzi zzi = (zzi) obj;
            if (zzn.equals(this.name, zzi.name) && (i = this.zzkgo) == zzi.zzkgo && this.zzkgp == zzi.zzkgp) {
                if (i != 1) {
                    if (i == 2) {
                        return this.zzili == zzi.zzili;
                    }
                    if (i == 3) {
                        return this.zzilk == zzi.zzilk;
                    }
                    if (i == 4) {
                        return zzn.equals(this.zzgim, zzi.zzgim);
                    }
                    if (i == 5) {
                        return Arrays.equals(this.zzkgn, zzi.zzkgn);
                    }
                    int i2 = this.zzkgo;
                    StringBuilder sb = new StringBuilder(31);
                    sb.append("Invalid enum value: ");
                    sb.append(i2);
                    throw new AssertionError(sb.toString());
                } else if (this.zzkgm == zzi.zzkgm) {
                    return true;
                }
            }
        }
        return false;
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("Flag(");
        sb.append(this.name);
        sb.append(", ");
        int i = this.zzkgo;
        if (i == 1) {
            sb.append(this.zzkgm);
        } else if (i == 2) {
            sb.append(this.zzili);
        } else if (i != 3) {
            if (i == 4) {
                sb.append("'");
                str = this.zzgim;
            } else if (i != 5) {
                String str2 = this.name;
                int i2 = this.zzkgo;
                StringBuilder sb2 = new StringBuilder(String.valueOf(str2).length() + 27);
                sb2.append("Invalid type: ");
                sb2.append(str2);
                sb2.append(", ");
                sb2.append(i2);
                throw new AssertionError(sb2.toString());
            } else if (this.zzkgn == null) {
                str = null;
            } else {
                sb.append("'");
                str = Base64.encodeToString(this.zzkgn, 3);
            }
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
                sb.append("'");
            }
        } else {
            sb.append(this.zzilk);
        }
        sb.append(", ");
        sb.append(this.zzkgo);
        sb.append(", ");
        sb.append(this.zzkgp);
        sb.append(")");
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.name, false);
        zzbgo.zza(parcel, 3, this.zzkgm);
        zzbgo.zza(parcel, 4, this.zzili);
        zzbgo.zza(parcel, 5, this.zzilk);
        zzbgo.zza(parcel, 6, this.zzgim, false);
        zzbgo.zza(parcel, 7, this.zzkgn, false);
        zzbgo.zzc(parcel, 8, this.zzkgo);
        zzbgo.zzc(parcel, 9, this.zzkgp);
        zzbgo.zzai(parcel, zze);
    }
}
