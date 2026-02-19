package com.google.android.gms.cast;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.internal.zzbdw;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ApplicationMetadata extends zzbgl {
    public static final Parcelable.Creator<ApplicationMetadata> CREATOR = new zzd();
    private String mName;
    private List<WebImage> zzbxw;
    private String zzetb;
    private List<String> zzetc;
    private String zzetd;
    private Uri zzete;

    private ApplicationMetadata() {
        this.zzbxw = new ArrayList();
        this.zzetc = new ArrayList();
    }

    ApplicationMetadata(String str, String str2, List<WebImage> list, List<String> list2, String str3, Uri uri) {
        this.zzetb = str;
        this.mName = str2;
        this.zzbxw = list;
        this.zzetc = list2;
        this.zzetd = str3;
        this.zzete = uri;
    }

    public boolean areNamespacesSupported(List<String> list) {
        List<String> list2 = this.zzetc;
        return list2 != null && list2.containsAll(list);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ApplicationMetadata)) {
            return false;
        }
        ApplicationMetadata applicationMetadata = (ApplicationMetadata) obj;
        return zzbdw.zza(this.zzetb, applicationMetadata.zzetb) && zzbdw.zza(this.zzbxw, applicationMetadata.zzbxw) && zzbdw.zza(this.mName, applicationMetadata.mName) && zzbdw.zza(this.zzetc, applicationMetadata.zzetc) && zzbdw.zza(this.zzetd, applicationMetadata.zzetd) && zzbdw.zza(this.zzete, applicationMetadata.zzete);
    }

    public String getApplicationId() {
        return this.zzetb;
    }

    public List<WebImage> getImages() {
        return this.zzbxw;
    }

    public String getName() {
        return this.mName;
    }

    public String getSenderAppIdentifier() {
        return this.zzetd;
    }

    public List<String> getSupportedNamespaces() {
        return Collections.unmodifiableList(this.zzetc);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzetb, this.mName, this.zzbxw, this.zzetc, this.zzetd, this.zzete});
    }

    public boolean isNamespaceSupported(String str) {
        List<String> list = this.zzetc;
        return list != null && list.contains(str);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("applicationId: ");
        sb.append(this.zzetb);
        sb.append(", name: ");
        sb.append(this.mName);
        sb.append(", images.count: ");
        List<WebImage> list = this.zzbxw;
        int i = 0;
        sb.append(list == null ? 0 : list.size());
        sb.append(", namespaces.count: ");
        List<String> list2 = this.zzetc;
        if (list2 != null) {
            i = list2.size();
        }
        sb.append(i);
        sb.append(", senderAppIdentifier: ");
        sb.append(this.zzetd);
        sb.append(", senderAppLaunchUrl: ");
        sb.append(this.zzete);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, getApplicationId(), false);
        zzbgo.zza(parcel, 3, getName(), false);
        zzbgo.zzc(parcel, 4, getImages(), false);
        zzbgo.zzb(parcel, 5, getSupportedNamespaces(), false);
        zzbgo.zza(parcel, 6, getSenderAppIdentifier(), false);
        zzbgo.zza(parcel, 7, (Parcelable) this.zzete, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
