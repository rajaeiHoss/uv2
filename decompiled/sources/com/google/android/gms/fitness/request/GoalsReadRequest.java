package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbwi;
import com.google.android.gms.internal.zzbyt;
import com.google.android.gms.internal.zzbyu;
import com.google.android.gms.internal.zzfmk;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoalsReadRequest extends zzbgl {
    public static final Parcelable.Creator<GoalsReadRequest> CREATOR = new zzad();
    private final List<DataType> zzhhz;
    private final List<Integer> zzhla;
    private final zzbyt zzhor;
    private final List<Integer> zzhos;

    public static class Builder {
        /* access modifiers changed from: private */
        public final List<DataType> zzhhz = new ArrayList();
        /* access modifiers changed from: private */
        public final List<Integer> zzhla = new ArrayList();
        /* access modifiers changed from: private */
        public final List<Integer> zzhos = new ArrayList();

        public Builder addActivity(String str) {
            int zzuc = zzfmk.zzuc(str);
            zzbq.zza(zzuc != 4, (Object) "Attempting to add an unknown activity");
            zzbwi.zza(Integer.valueOf(zzuc), this.zzhla);
            return this;
        }

        public Builder addDataType(DataType dataType) {
            zzbq.checkNotNull(dataType, "Attempting to use a null data type");
            if (!this.zzhhz.contains(dataType)) {
                this.zzhhz.add(dataType);
            }
            return this;
        }

        public Builder addObjectiveType(int i) {
            boolean z = true;
            if (!(i == 1 || i == 2 || i == 3)) {
                z = false;
            }
            zzbq.zza(z, (Object) "Attempting to add an invalid objective type");
            if (!this.zzhos.contains(Integer.valueOf(i))) {
                this.zzhos.add(Integer.valueOf(i));
            }
            return this;
        }

        public GoalsReadRequest build() {
            zzbq.zza(!this.zzhhz.isEmpty(), (Object) "At least one data type should be specified.");
            return new GoalsReadRequest(this);
        }
    }

    GoalsReadRequest(IBinder iBinder, List<DataType> list, List<Integer> list2, List<Integer> list3) {
        this.zzhor = iBinder == null ? null : zzbyu.zzaw(iBinder);
        this.zzhhz = list;
        this.zzhos = list2;
        this.zzhla = list3;
    }

    private GoalsReadRequest(Builder builder) {
        this((zzbyt) null, (List<DataType>) builder.zzhhz, (List<Integer>) builder.zzhos, (List<Integer>) builder.zzhla);
    }

    public GoalsReadRequest(GoalsReadRequest goalsReadRequest, zzbyt zzbyt) {
        this(zzbyt, goalsReadRequest.getDataTypes(), goalsReadRequest.zzhos, goalsReadRequest.zzhla);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    private GoalsReadRequest(zzbyt zzbyt, List<DataType> list, List<Integer> list2, List<Integer> list3) {
        this(zzbyt == null ? null : zzbyt.asBinder(), list, list2, list3);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof GoalsReadRequest) {
                GoalsReadRequest goalsReadRequest = (GoalsReadRequest) obj;
                if (zzbg.equal(this.zzhhz, goalsReadRequest.zzhhz) && zzbg.equal(this.zzhos, goalsReadRequest.zzhos) && zzbg.equal(this.zzhla, goalsReadRequest.zzhla)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public List<String> getActivityNames() {
        if (this.zzhla.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Integer intValue : this.zzhla) {
            arrayList.add(zzfmk.getName(intValue.intValue()));
        }
        return arrayList;
    }

    public List<DataType> getDataTypes() {
        return this.zzhhz;
    }

    public List<Integer> getObjectiveTypes() {
        if (this.zzhos.isEmpty()) {
            return null;
        }
        return this.zzhos;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzhhz, this.zzhos, getActivityNames()});
    }

    public String toString() {
        return zzbg.zzx(this).zzg("dataTypes", this.zzhhz).zzg("objectiveTypes", this.zzhos).zzg("activities", getActivityNames()).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzhor.asBinder(), false);
        zzbgo.zzd(parcel, 2, getDataTypes(), false);
        zzbgo.zzd(parcel, 3, this.zzhos, false);
        zzbgo.zzd(parcel, 4, this.zzhla, false);
        zzbgo.zzai(parcel, zze);
    }
}
