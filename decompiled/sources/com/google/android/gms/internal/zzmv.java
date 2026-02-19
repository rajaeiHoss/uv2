package com.google.android.gms.internal;

import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public final class zzmv {
    /* access modifiers changed from: private */
    public boolean zzamp = false;
    /* access modifiers changed from: private */
    public int zzbhm = -1;
    /* access modifiers changed from: private */
    public int zzbhp = -1;
    /* access modifiers changed from: private */
    public String zzbhq;
    /* access modifiers changed from: private */
    public String zzbhs;
    /* access modifiers changed from: private */
    public final Bundle zzbhu = new Bundle();
    /* access modifiers changed from: private */
    public String zzbhw;
    /* access modifiers changed from: private */
    public boolean zzbhy;
    /* access modifiers changed from: private */
    public final Bundle zzbji = new Bundle();
    /* access modifiers changed from: private */
    public final HashSet<String> zzbjn = new HashSet<>();
    /* access modifiers changed from: private */
    public final HashMap<Class<? extends NetworkExtras>, NetworkExtras> zzbjo = new HashMap<>();
    /* access modifiers changed from: private */
    public final HashSet<String> zzbjp = new HashSet<>();
    /* access modifiers changed from: private */
    public final HashSet<String> zzbjq = new HashSet<>();
    /* access modifiers changed from: private */
    public Date zzhh;
    /* access modifiers changed from: private */
    public Location zzhl;

    public final void setManualImpressionsEnabled(boolean z) {
        this.zzamp = z;
    }

    @Deprecated
    public final void zza(NetworkExtras networkExtras) {
        if (networkExtras instanceof AdMobExtras) {
            zza(AdMobAdapter.class, ((AdMobExtras) networkExtras).getExtras());
        } else {
            this.zzbjo.put(networkExtras.getClass(), networkExtras);
        }
    }

    public final void zza(Class<? extends MediationAdapter> cls, Bundle bundle) {
        this.zzbji.putBundle(cls.getName(), bundle);
    }

    public final void zza(Date date) {
        this.zzhh = date;
    }

    public final void zzad(String str) {
        this.zzbjn.add(str);
    }

    public final void zzae(String str) {
        this.zzbjp.add(str);
    }

    public final void zzaf(String str) {
        this.zzbjp.remove(str);
    }

    public final void zzag(String str) {
        this.zzbhs = str;
    }

    public final void zzah(String str) {
        this.zzbhq = str;
    }

    public final void zzai(String str) {
        this.zzbhw = str;
    }

    public final void zzaj(String str) {
        this.zzbjq.add(str);
    }

    public final void zzb(Location location) {
        this.zzhl = location;
    }

    public final void zzb(Class<? extends CustomEvent> cls, Bundle bundle) {
        if (this.zzbji.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter") == null) {
            this.zzbji.putBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter", new Bundle());
        }
        this.zzbji.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter").putBundle(cls.getName(), bundle);
    }

    public final void zzd(String str, String str2) {
        this.zzbhu.putString(str, str2);
    }

    public final void zzj(boolean z) {
        this.zzbhp = z ? 1 : 0;
    }

    public final void zzk(boolean z) {
        this.zzbhy = z;
    }

    public final void zzr(int i) {
        this.zzbhm = i;
    }
}
