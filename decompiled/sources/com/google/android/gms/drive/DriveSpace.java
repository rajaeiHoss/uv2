package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzf;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Set;
import java.util.regex.Pattern;

public class DriveSpace extends zzbgl implements ReflectedParcelable {
    public static final Parcelable.Creator<DriveSpace> CREATOR = new zzn();
    public static final DriveSpace zzgqa;
    public static final DriveSpace zzgqb;
    public static final DriveSpace zzgqc;
    private static Set<DriveSpace> zzgqd;
    private static String zzgqe;
    private static final Pattern zzgqf = Pattern.compile("[A-Z0-9_]*");
    private final String mName;

    static {
        DriveSpace driveSpace = new DriveSpace("DRIVE");
        zzgqa = driveSpace;
        DriveSpace driveSpace2 = new DriveSpace("APP_DATA_FOLDER");
        zzgqb = driveSpace2;
        DriveSpace driveSpace3 = new DriveSpace("PHOTOS");
        zzgqc = driveSpace3;
        Set<DriveSpace> zza = zzf.zza(driveSpace, driveSpace2, driveSpace3);
        zzgqd = zza;
        zzgqe = TextUtils.join(",", zza.toArray());
    }

    DriveSpace(String str) {
        this.mName = (String) zzbq.checkNotNull(str);
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != DriveSpace.class) {
            return false;
        }
        return this.mName.equals(((DriveSpace) obj).mName);
    }

    public int hashCode() {
        return this.mName.hashCode() ^ 1247068382;
    }

    public String toString() {
        return this.mName;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.mName, false);
        zzbgo.zzai(parcel, zze);
    }
}
