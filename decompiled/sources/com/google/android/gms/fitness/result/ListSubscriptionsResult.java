package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListSubscriptionsResult extends zzbgl implements Result {
    public static final Parcelable.Creator<ListSubscriptionsResult> CREATOR = new zzg();
    private final Status zzefs;
    private final List<Subscription> zzhpy;

    public ListSubscriptionsResult(List<Subscription> list, Status status) {
        this.zzhpy = list;
        this.zzefs = status;
    }

    public static ListSubscriptionsResult zzag(Status status) {
        return new ListSubscriptionsResult(Collections.emptyList(), status);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof ListSubscriptionsResult) {
                ListSubscriptionsResult listSubscriptionsResult = (ListSubscriptionsResult) obj;
                if (this.zzefs.equals(listSubscriptionsResult.zzefs) && zzbg.equal(this.zzhpy, listSubscriptionsResult.zzhpy)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public Status getStatus() {
        return this.zzefs;
    }

    public List<Subscription> getSubscriptions() {
        return this.zzhpy;
    }

    public List<Subscription> getSubscriptions(DataType dataType) {
        ArrayList arrayList = new ArrayList();
        for (Subscription next : this.zzhpy) {
            if (dataType.equals(next.zzasc())) {
                arrayList.add(next);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzefs, this.zzhpy});
    }

    public String toString() {
        return zzbg.zzx(this).zzg("status", this.zzefs).zzg("subscriptions", this.zzhpy).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, getSubscriptions(), false);
        zzbgo.zza(parcel, 2, (Parcelable) getStatus(), i, false);
        zzbgo.zzai(parcel, zze);
    }
}
