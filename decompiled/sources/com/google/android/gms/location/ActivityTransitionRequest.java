package com.google.android.gms.location;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbgq;
import com.google.android.gms.internal.zzcfs;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class ActivityTransitionRequest extends zzbgl {
    public static final Parcelable.Creator<ActivityTransitionRequest> CREATOR = new zzf();
    public static final Comparator<ActivityTransition> IS_SAME_TRANSITION = new zze();
    private final String mTag;
    private final List<ActivityTransition> zziqz;
    private final List<zzcfs> zzira;

    public ActivityTransitionRequest(List<ActivityTransition> list) {
        this(list, (String) null, (List<zzcfs>) null);
    }

    public ActivityTransitionRequest(List<ActivityTransition> list, String str, List<zzcfs> list2) {
        zzbq.checkNotNull(list, "transitions can't be null");
        zzbq.checkArgument(list.size() > 0, "transitions can't be empty.");
        TreeSet treeSet = new TreeSet(IS_SAME_TRANSITION);
        for (ActivityTransition next : list) {
            zzbq.checkArgument(treeSet.add(next), String.format("Found duplicated transition: %s.", new Object[]{next}));
        }
        this.zziqz = Collections.unmodifiableList(list);
        this.mTag = str;
        this.zzira = list2 == null ? Collections.emptyList() : Collections.unmodifiableList(list2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            ActivityTransitionRequest activityTransitionRequest = (ActivityTransitionRequest) obj;
            return zzbg.equal(this.zziqz, activityTransitionRequest.zziqz) && zzbg.equal(this.mTag, activityTransitionRequest.mTag) && zzbg.equal(this.zzira, activityTransitionRequest.zzira);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.zziqz.hashCode() * 31;
        String str = this.mTag;
        int i = 0;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        List<zzcfs> list = this.zzira;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode2 + i;
    }

    public void serializeToIntentExtra(Intent intent) {
        zzbgq.zza(this, intent, "com.google.android.location.internal.EXTRA_ACTIVITY_TRANSITION_REQUEST");
    }

    public String toString() {
        String valueOf = String.valueOf(this.zziqz);
        String str = this.mTag;
        String valueOf2 = String.valueOf(this.zzira);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 61 + String.valueOf(str).length() + String.valueOf(valueOf2).length());
        sb.append("ActivityTransitionRequest [mTransitions=");
        sb.append(valueOf);
        sb.append(", mTag='");
        sb.append(str);
        sb.append('\'');
        sb.append(", mClients=");
        sb.append(valueOf2);
        sb.append(']');
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zziqz, false);
        zzbgo.zza(parcel, 2, this.mTag, false);
        zzbgo.zzc(parcel, 3, this.zzira, false);
        zzbgo.zzai(parcel, zze);
    }
}
