package com.google.android.gms.fitness.data;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbgq;
import com.streamax.config.constant.Constants;
import org.jivesoftware.smackx.packet.CapsExtension;

public class DataSource extends zzbgl {
    public static final Parcelable.Creator<DataSource> CREATOR = new zzk();
    public static final int DATA_QUALITY_BLOOD_GLUCOSE_ISO151972003 = 8;
    public static final int DATA_QUALITY_BLOOD_GLUCOSE_ISO151972013 = 9;
    public static final int DATA_QUALITY_BLOOD_PRESSURE_AAMI = 3;
    public static final int DATA_QUALITY_BLOOD_PRESSURE_BHS_A_A = 4;
    public static final int DATA_QUALITY_BLOOD_PRESSURE_BHS_A_B = 5;
    public static final int DATA_QUALITY_BLOOD_PRESSURE_BHS_B_A = 6;
    public static final int DATA_QUALITY_BLOOD_PRESSURE_BHS_B_B = 7;
    public static final int DATA_QUALITY_BLOOD_PRESSURE_ESH2002 = 1;
    public static final int DATA_QUALITY_BLOOD_PRESSURE_ESH2010 = 2;
    public static final String EXTRA_DATA_SOURCE = "vnd.google.fitness.data_source";
    public static final int TYPE_DERIVED = 1;
    public static final int TYPE_RAW = 0;
    private static final int[] zzhio = new int[0];
    private final String name;
    private final int type;
    private final DataType zzhhj;
    private final Device zzhip;
    private final zzb zzhiq;
    private final String zzhir;
    private final int[] zzhis;
    private final String zzhit;

    public static final class Builder {
        /* access modifiers changed from: private */
        public String name;
        /* access modifiers changed from: private */
        public int type = -1;
        /* access modifiers changed from: private */
        public DataType zzhhj;
        /* access modifiers changed from: private */
        public Device zzhip;
        /* access modifiers changed from: private */
        public zzb zzhiq;
        /* access modifiers changed from: private */
        public String zzhir = "";
        /* access modifiers changed from: private */
        public int[] zzhis;

        public final DataSource build() {
            boolean z = true;
            zzbq.zza(this.zzhhj != null, (Object) "Must set data type");
            if (this.type < 0) {
                z = false;
            }
            zzbq.zza(z, (Object) "Must set data source type");
            return new DataSource(this);
        }

        public final Builder setAppPackageName(Context context) {
            return setAppPackageName(context.getPackageName());
        }

        public final Builder setAppPackageName(String str) {
            this.zzhiq = zzb.zzhl(str);
            return this;
        }

        public final Builder setDataQualityStandards(int... iArr) {
            this.zzhis = iArr;
            return this;
        }

        public final Builder setDataType(DataType dataType) {
            this.zzhhj = dataType;
            return this;
        }

        public final Builder setDevice(Device device) {
            this.zzhip = device;
            return this;
        }

        public final Builder setName(String str) {
            this.name = str;
            return this;
        }

        public final Builder setStreamName(String str) {
            zzbq.checkArgument(str != null, "Must specify a valid stream name");
            this.zzhir = str;
            return this;
        }

        public final Builder setType(int i) {
            this.type = i;
            return this;
        }
    }

    private DataSource(Builder builder) {
        this.zzhhj = builder.zzhhj;
        this.type = builder.type;
        this.name = builder.name;
        this.zzhip = builder.zzhip;
        this.zzhiq = builder.zzhiq;
        this.zzhir = builder.zzhir;
        this.zzhit = zzarv();
        this.zzhis = builder.zzhis;
    }

    DataSource(DataType dataType, String str, int i, Device device, zzb zzb, String str2, int[] iArr) {
        this.zzhhj = dataType;
        this.type = i;
        this.name = str;
        this.zzhip = device;
        this.zzhiq = zzb;
        this.zzhir = str2;
        this.zzhit = zzarv();
        this.zzhis = iArr == null ? zzhio : iArr;
    }

    public static DataSource extract(Intent intent) {
        if (intent == null) {
            return null;
        }
        return (DataSource) zzbgq.zza(intent, EXTRA_DATA_SOURCE, CREATOR);
    }

    private final String getTypeString() {
        int i = this.type;
        return i != 0 ? i != 2 ? i != 3 ? "derived" : "converted" : "cleaned" : "raw";
    }

    private final String zzarv() {
        StringBuilder sb = new StringBuilder();
        sb.append(getTypeString());
        sb.append(":");
        sb.append(this.zzhhj.getName());
        if (this.zzhiq != null) {
            sb.append(":");
            sb.append(this.zzhiq.getPackageName());
        }
        if (this.zzhip != null) {
            sb.append(":");
            sb.append(this.zzhip.getStreamIdentifier());
        }
        if (this.zzhir != null) {
            sb.append(":");
            sb.append(this.zzhir);
        }
        return sb.toString();
    }

    public static String zzdd(int i) {
        switch (i) {
            case 1:
                return "blood_pressure_esh2002";
            case 2:
                return "blood_pressure_esh2010";
            case 3:
                return "blood_pressure_aami";
            case 4:
                return "blood_pressure_bhs_a_a";
            case 5:
                return "blood_pressure_bhs_a_b";
            case 6:
                return "blood_pressure_bhs_b_a";
            case 7:
                return "blood_pressure_bhs_b_b";
            case 8:
                return "blood_glucose_iso151972003";
            case 9:
                return "blood_glucose_iso151972013";
            default:
                return "unknown";
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DataSource)) {
            return false;
        }
        return this.zzhit.equals(((DataSource) obj).zzhit);
    }

    public String getAppPackageName() {
        zzb zzb = this.zzhiq;
        if (zzb == null) {
            return null;
        }
        return zzb.getPackageName();
    }

    public int[] getDataQualityStandards() {
        return this.zzhis;
    }

    public DataType getDataType() {
        return this.zzhhj;
    }

    public Device getDevice() {
        return this.zzhip;
    }

    public String getName() {
        return this.name;
    }

    public String getStreamIdentifier() {
        return this.zzhit;
    }

    public String getStreamName() {
        return this.zzhir;
    }

    public int getType() {
        return this.type;
    }

    public int hashCode() {
        return this.zzhit.hashCode();
    }

    public final String toDebugString() {
        String str;
        String str2;
        int i = this.type;
        String str3 = i != 0 ? i != 1 ? i != 2 ? i != 3 ? "?" : "v" : CapsExtension.NODE_NAME : "d" : "r";
        String zzary = this.zzhhj.zzary();
        zzb zzb = this.zzhiq;
        String str4 = "";
        if (zzb == null) {
            str = str4;
        } else if (zzb.equals(zzb.zzhhw)) {
            str = ":gms";
        } else {
            String valueOf = String.valueOf(this.zzhiq.getPackageName());
            str = valueOf.length() != 0 ? ":".concat(valueOf) : new String(":");
        }
        Device device = this.zzhip;
        if (device != null) {
            String model = device.getModel();
            String uid = this.zzhip.getUid();
            StringBuilder sb = new StringBuilder(String.valueOf(model).length() + 2 + String.valueOf(uid).length());
            sb.append(":");
            sb.append(model);
            sb.append(":");
            sb.append(uid);
            str2 = sb.toString();
        } else {
            str2 = str4;
        }
        String str5 = this.zzhir;
        if (str5 != null) {
            String valueOf2 = String.valueOf(str5);
            str4 = valueOf2.length() != 0 ? ":".concat(valueOf2) : new String(":");
        }
        StringBuilder sb2 = new StringBuilder(str3.length() + 1 + String.valueOf(zzary).length() + String.valueOf(str).length() + String.valueOf(str2).length() + String.valueOf(str4).length());
        sb2.append(str3);
        sb2.append(":");
        sb2.append(zzary);
        sb2.append(str);
        sb2.append(str2);
        sb2.append(str4);
        return sb2.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("DataSource{");
        sb.append(getTypeString());
        if (this.name != null) {
            sb.append(":");
            sb.append(this.name);
        }
        if (this.zzhiq != null) {
            sb.append(":");
            sb.append(this.zzhiq);
        }
        if (this.zzhip != null) {
            sb.append(":");
            sb.append(this.zzhip);
        }
        if (this.zzhir != null) {
            sb.append(":");
            sb.append(this.zzhir);
        }
        sb.append(":");
        sb.append(this.zzhhj);
        sb.append(Constants.JsonSstringSuffix);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) getDataType(), i, false);
        zzbgo.zza(parcel, 2, getName(), false);
        zzbgo.zzc(parcel, 3, getType());
        zzbgo.zza(parcel, 4, (Parcelable) getDevice(), i, false);
        zzbgo.zza(parcel, 5, (Parcelable) this.zzhiq, i, false);
        zzbgo.zza(parcel, 6, getStreamName(), false);
        zzbgo.zza(parcel, 8, getDataQualityStandards(), false);
        zzbgo.zzai(parcel, zze);
    }

    public final zzb zzaru() {
        return this.zzhiq;
    }
}
