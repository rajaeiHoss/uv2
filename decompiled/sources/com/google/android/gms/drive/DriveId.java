package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzal;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbof;
import com.google.android.gms.internal.zzbok;
import com.google.android.gms.internal.zzbql;
import com.google.android.gms.internal.zzbuh;
import com.google.android.gms.internal.zzbui;
import com.google.android.gms.internal.zzflr;
import com.google.android.gms.internal.zzfls;

public class DriveId extends zzbgl implements ReflectedParcelable {
    public static final Parcelable.Creator<DriveId> CREATOR = new zzl();
    public static final int RESOURCE_TYPE_FILE = 0;
    public static final int RESOURCE_TYPE_FOLDER = 1;
    public static final int RESOURCE_TYPE_UNKNOWN = -1;
    private static final zzal zzgpv = new zzal("DriveId", "");
    private long zzgoz;
    private volatile String zzgpb = null;
    private String zzgpw;
    private long zzgpx;
    private int zzgpy;
    private volatile String zzgpz = null;

    public DriveId(String str, long j, long j2, int i) {
        this.zzgpw = str;
        boolean z = true;
        zzbq.checkArgument(!"".equals(str));
        if (str == null && j == -1) {
            z = false;
        }
        zzbq.checkArgument(z);
        this.zzgpx = j;
        this.zzgoz = j2;
        this.zzgpy = i;
    }

    public static DriveId decodeFromString(String str) {
        boolean startsWith = str.startsWith("DriveId:");
        String valueOf = String.valueOf(str);
        zzbq.checkArgument(startsWith, valueOf.length() != 0 ? "Invalid DriveId: ".concat(valueOf) : new String("Invalid DriveId: "));
        return zzo(Base64.decode(str.substring(8), 10));
    }

    public static DriveId zzhe(String str) {
        zzbq.checkNotNull(str);
        return new DriveId(str, -1, -1, -1);
    }

    private static DriveId zzo(byte[] bArr) {
        try {
            zzbuh zzbuh = (zzbuh) zzfls.zza(new zzbuh(), bArr);
            return new DriveId("".equals(zzbuh.zzgyf) ? null : zzbuh.zzgyf, zzbuh.zzgyg, zzbuh.zzgyd, zzbuh.zzgyh);
        } catch (zzflr unused) {
            throw new IllegalArgumentException();
        }
    }

    public DriveFile asDriveFile() {
        if (this.zzgpy != 1) {
            return new zzbof(this);
        }
        throw new IllegalStateException("This DriveId corresponds to a folder. Call asDriveFolder instead.");
    }

    public DriveFolder asDriveFolder() {
        if (this.zzgpy != 0) {
            return new zzbok(this);
        }
        throw new IllegalStateException("This DriveId corresponds to a file. Call asDriveFile instead.");
    }

    public DriveResource asDriveResource() {
        int i = this.zzgpy;
        return i == 1 ? asDriveFolder() : i == 0 ? asDriveFile() : new zzbql(this);
    }

    public final String encodeToString() {
        if (this.zzgpb == null) {
            zzbuh zzbuh = new zzbuh();
            zzbuh.versionCode = 1;
            String str = this.zzgpw;
            if (str == null) {
                str = "";
            }
            zzbuh.zzgyf = str;
            zzbuh.zzgyg = this.zzgpx;
            zzbuh.zzgyd = this.zzgoz;
            zzbuh.zzgyh = this.zzgpy;
            String valueOf = String.valueOf(Base64.encodeToString(zzfls.zzc(zzbuh), 10));
            this.zzgpb = valueOf.length() != 0 ? "DriveId:".concat(valueOf) : new String("DriveId:");
        }
        return this.zzgpb;
    }

    public boolean equals(Object obj) {
        String str;
        if (obj != null && obj.getClass() == DriveId.class) {
            DriveId driveId = (DriveId) obj;
            if (driveId.zzgoz != this.zzgoz) {
                return false;
            }
            long j = driveId.zzgpx;
            if (j == -1 && this.zzgpx == -1) {
                return driveId.zzgpw.equals(this.zzgpw);
            }
            String str2 = this.zzgpw;
            if (str2 != null && (str = driveId.zzgpw) != null) {
                if (j == this.zzgpx) {
                    if (str.equals(str2)) {
                        return true;
                    }
                    zzgpv.zzv("DriveId", "Unexpected unequal resourceId for same DriveId object.");
                }
                return false;
            } else if (j == this.zzgpx) {
                return true;
            }
        }
        return false;
    }

    public String getResourceId() {
        return this.zzgpw;
    }

    public int getResourceType() {
        return this.zzgpy;
    }

    public int hashCode() {
        if (this.zzgpx == -1) {
            return this.zzgpw.hashCode();
        }
        String valueOf = String.valueOf(String.valueOf(this.zzgoz));
        String valueOf2 = String.valueOf(String.valueOf(this.zzgpx));
        return (valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)).hashCode();
    }

    public final String toInvariantString() {
        if (this.zzgpz == null) {
            zzbui zzbui = new zzbui();
            zzbui.zzgyg = this.zzgpx;
            zzbui.zzgyd = this.zzgoz;
            this.zzgpz = Base64.encodeToString(zzfls.zzc(zzbui), 10);
        }
        return this.zzgpz;
    }

    public String toString() {
        return encodeToString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzgpw, false);
        zzbgo.zza(parcel, 3, this.zzgpx);
        zzbgo.zza(parcel, 4, this.zzgoz);
        zzbgo.zzc(parcel, 5, this.zzgpy);
        zzbgo.zzai(parcel, zze);
    }
}
