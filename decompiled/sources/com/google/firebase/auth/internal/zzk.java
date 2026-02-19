package com.google.firebase.auth.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzebw;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.zzd;
import java.util.ArrayList;
import java.util.List;

public class zzk extends FirebaseUser {
    public static final Parcelable.Creator<zzk> CREATOR = new zzl();
    private boolean zzmso;
    private zzd zzmsp;
    private zzebw zzmtn;
    private zzh zzmto;
    private String zzmtp;
    private String zzmtq;
    private List<zzh> zzmtr;
    private List<String> zzmts;
    private String zzmtt;
    private boolean zzmtu;
    private zzm zzmtv;

    zzk(zzebw zzebw, zzh zzh, String str, String str2, List<zzh> list, List<String> list2, String str3, boolean z, zzm zzm, boolean z2, zzd zzd) {
        this.zzmtn = zzebw;
        this.zzmto = zzh;
        this.zzmtp = str;
        this.zzmtq = str2;
        this.zzmtr = list;
        this.zzmts = list2;
        this.zzmtt = str3;
        this.zzmtu = z;
        this.zzmtv = zzm;
        this.zzmso = z2;
        this.zzmsp = zzd;
    }

    public zzk(FirebaseApp firebaseApp, List<? extends UserInfo> list) {
        zzbq.checkNotNull(firebaseApp);
        this.zzmtp = firebaseApp.getName();
        this.zzmtq = "com.google.firebase.auth.internal.DefaultFirebaseUser";
        this.zzmtt = "2";
        zzar(list);
    }

    public String getDisplayName() {
        return this.zzmto.getDisplayName();
    }

    public String getEmail() {
        return this.zzmto.getEmail();
    }

    public FirebaseUserMetadata getMetadata() {
        return this.zzmtv;
    }

    public String getPhoneNumber() {
        return this.zzmto.getPhoneNumber();
    }

    public Uri getPhotoUrl() {
        return this.zzmto.getPhotoUrl();
    }

    public List<? extends UserInfo> getProviderData() {
        return this.zzmtr;
    }

    public String getProviderId() {
        return this.zzmto.getProviderId();
    }

    public final List<String> getProviders() {
        return this.zzmts;
    }

    public String getUid() {
        return this.zzmto.getUid();
    }

    public boolean isAnonymous() {
        return this.zzmtu;
    }

    public boolean isEmailVerified() {
        return this.zzmto.isEmailVerified();
    }

    public final boolean isNewUser() {
        return this.zzmso;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) zzbtm(), i, false);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzmto, i, false);
        zzbgo.zza(parcel, 3, this.zzmtp, false);
        zzbgo.zza(parcel, 4, this.zzmtq, false);
        zzbgo.zzc(parcel, 5, this.zzmtr, false);
        zzbgo.zzb(parcel, 6, getProviders(), false);
        zzbgo.zza(parcel, 7, this.zzmtt, false);
        zzbgo.zza(parcel, 8, isAnonymous());
        zzbgo.zza(parcel, 9, (Parcelable) getMetadata(), i, false);
        zzbgo.zza(parcel, 10, this.zzmso);
        zzbgo.zza(parcel, 11, (Parcelable) this.zzmsp, i, false);
        zzbgo.zzai(parcel, zze);
    }

    public final void zza(zzebw zzebw) {
        this.zzmtn = (zzebw) zzbq.checkNotNull(zzebw);
    }

    public final void zza(zzm zzm) {
        this.zzmtv = zzm;
    }

    public final FirebaseUser zzar(List<? extends UserInfo> list) {
        zzbq.checkNotNull(list);
        this.zzmtr = new ArrayList(list.size());
        this.zzmts = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            UserInfo userInfo = (UserInfo) list.get(i);
            if (userInfo.getProviderId().equals(FirebaseAuthProvider.PROVIDER_ID)) {
                this.zzmto = (zzh) userInfo;
            } else {
                this.zzmts.add(userInfo.getProviderId());
            }
            this.zzmtr.add((zzh) userInfo);
        }
        if (this.zzmto == null) {
            this.zzmto = this.zzmtr.get(0);
        }
        return this;
    }

    public final void zzb(zzd zzd) {
        this.zzmsp = zzd;
    }

    public final FirebaseApp zzbtl() {
        return FirebaseApp.getInstance(this.zzmtp);
    }

    public final zzebw zzbtm() {
        return this.zzmtn;
    }

    public final String zzbtn() {
        return this.zzmtn.zzack();
    }

    public final String zzbto() {
        return zzbtm().getAccessToken();
    }

    public final zzd zzbud() {
        return this.zzmsp;
    }

    public final List<zzh> zzbum() {
        return this.zzmtr;
    }

    public final /* synthetic */ FirebaseUser zzck(boolean z) {
        this.zzmtu = z;
        return this;
    }

    public final void zzco(boolean z) {
        this.zzmso = z;
    }

    public final zzk zzpi(String str) {
        this.zzmtt = str;
        return this;
    }
}
