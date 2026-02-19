package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.awareness.fence.AwarenessFence;
import com.google.android.gms.common.internal.zzbq;
import java.util.ArrayList;
import java.util.Collection;

public final class zzbke extends AwarenessFence {
    public static final Parcelable.Creator<zzbke> CREATOR = new zzbkf();
    private zzffl zzgni;
    private byte[] zzgnj;

    private zzbke(zzffl zzffl) {
        this.zzgni = (zzffl) zzbq.checkNotNull(zzffl);
        this.zzgnj = null;
        zzaot();
    }

    public zzbke(byte[] bArr) {
        this.zzgni = null;
        this.zzgnj = bArr;
        zzaot();
    }

    public static zzbke zza(zzbjt zzbjt) {
        zzbq.checkNotNull(zzbjt);
        zzffl zzcp = zzcp(7);
        zzcp.zzplg = zzbjt.zzaou();
        return new zzbke(zzcp);
    }

    public static zzbke zza(zzbju zzbju) {
        zzbq.checkNotNull(zzbju);
        zzffl zzcp = zzcp(11);
        zzcp.zzplk = zzbju.zzaox();
        return new zzbke(zzcp);
    }

    public static zzbke zza(zzbjy zzbjy) {
        zzbq.checkNotNull(zzbjy);
        zzffl zzcp = zzcp(12);
        zzcp.zzpll = zzbjy.zzaoy();
        return new zzbke(zzcp);
    }

    public static zzbke zza(zzbke zzbke) {
        zzbq.checkNotNull(zzbke);
        ArrayList arrayList = new ArrayList();
        arrayList.add(zzbke);
        zzffl zzcp = zzcp(3);
        zzcp.zzplc = zzh(arrayList);
        return new zzbke(zzcp);
    }

    public static zzbke zza(zzbku zzbku) {
        zzbq.checkNotNull(zzbku);
        zzffl zzcp = zzcp(5);
        zzcp.zzple = zzbku.zzaoz();
        return new zzbke(zzcp);
    }

    public static zzbke zza(zzbkw zzbkw) {
        zzbq.checkNotNull(zzbkw);
        zzffl zzcp = zzcp(19);
        zzcp.zzplt = zzbkw.zzapa();
        return new zzbke(zzcp);
    }

    public static zzbke zza(zzbkx zzbkx) {
        zzffl zzffl;
        zzbq.checkNotNull(zzbkx);
        if (zzbkx.zzapb().zzpmz) {
            zzffl = zzcp(20);
            zzffl.zzplu = zzbkx.zzapb();
        } else {
            zzffl = zzcp(4);
            zzffl.zzpld = zzbkx.zzapb();
        }
        return new zzbke(zzffl);
    }

    public static zzbke zza(zzbky zzbky) {
        zzbq.checkNotNull(zzbky);
        zzffl zzcp = zzcp(15);
        zzcp.zzplp = zzbky.zzapc();
        return new zzbke(zzcp);
    }

    private final void zzaos() {
        if (!(this.zzgni != null)) {
            try {
                this.zzgni = (zzffl) zzfls.zza(new zzffl(), this.zzgnj);
                this.zzgnj = null;
            } catch (zzflr e) {
                zzfi.zza("ContextFenceStub", "Could not deserialize context fence bytes.", (Throwable) e);
                throw new IllegalStateException(e);
            }
        }
        zzaot();
    }

    private final void zzaot() {
        zzffl zzffl = this.zzgni;
        if (zzffl == null && this.zzgnj != null) {
            return;
        }
        if (zzffl != null && this.zzgnj == null) {
            return;
        }
        if (zzffl != null && this.zzgnj != null) {
            throw new IllegalStateException("Invalid internal representation - full");
        } else if (zzffl == null && this.zzgnj == null) {
            throw new IllegalStateException("Invalid internal representation - empty");
        } else {
            throw new IllegalStateException("Impossible");
        }
    }

    private static zzffl zzcp(int i) {
        zzffl zzffl = new zzffl();
        zzffl.type = i;
        return zzffl;
    }

    public static zzbke zzf(Collection<zzbke> collection) {
        zzbq.checkNotNull(collection);
        zzbq.checkArgument(!collection.isEmpty());
        zzffl zzcp = zzcp(1);
        zzcp.zzplc = zzh(collection);
        return new zzbke(zzcp);
    }

    public static zzbke zzg(Collection<zzbke> collection) {
        zzbq.checkNotNull(collection);
        zzbq.checkArgument(!collection.isEmpty());
        zzffl zzcp = zzcp(2);
        zzcp.zzplc = zzh(collection);
        return new zzbke(zzcp);
    }

    private static zzffl[] zzh(Collection<zzbke> collection) {
        zzffl[] zzfflArr = new zzffl[collection.size()];
        int i = 0;
        for (zzbke next : collection) {
            next.zzaos();
            zzfflArr[i] = next.zzgni;
            i++;
        }
        return zzfflArr;
    }

    public final String toString() {
        zzaos();
        return this.zzgni.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        byte[] bArr = this.zzgnj;
        if (bArr == null) {
            bArr = zzfls.zzc(this.zzgni);
        }
        zzbgo.zza(parcel, 2, bArr, false);
        zzbgo.zzai(parcel, zze);
    }
}
