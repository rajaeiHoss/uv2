package com.google.android.gms.internal;

import android.os.Binder;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzz;
import com.google.android.gms.common.zzs;
import com.google.android.gms.common.zzt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public final class zzcko extends zzcjc {
    /* access modifiers changed from: private */
    public final zzckj zzjev;
    private Boolean zzjph;
    private String zzjpi;

    public zzcko(zzckj zzckj) {
        this(zzckj, (String) null);
    }

    private zzcko(zzckj zzckj, String str) {
        zzbq.checkNotNull(zzckj);
        this.zzjev = zzckj;
        this.zzjpi = null;
    }

    private final void zzb(zzcif zzcif, boolean z) {
        zzbq.checkNotNull(zzcif);
        zzg(zzcif.packageName, false);
        this.zzjev.zzayl().zzkn(zzcif.zzjfl);
    }

    private final void zzg(String str, boolean z) {
        boolean z2;
        if (!TextUtils.isEmpty(str)) {
            if (z) {
                try {
                    if (this.zzjph == null) {
                        if (!"com.google.android.gms".equals(this.zzjpi) && !zzz.zze(this.zzjev.getContext(), Binder.getCallingUid())) {
                            if (!zzt.zzcj(this.zzjev.getContext()).zzbp(Binder.getCallingUid())) {
                                z2 = false;
                                this.zzjph = Boolean.valueOf(z2);
                            }
                        }
                        z2 = true;
                        this.zzjph = Boolean.valueOf(z2);
                    }
                    if (this.zzjph.booleanValue()) {
                        return;
                    }
                } catch (SecurityException e) {
                    this.zzjev.zzayp().zzbau().zzj("Measurement Service called with invalid calling package. appId", zzcjj.zzjs(str));
                    throw e;
                }
            }
            if (this.zzjpi == null && zzs.zzb(this.zzjev.getContext(), Binder.getCallingUid(), str)) {
                this.zzjpi = str;
            }
            if (!str.equals(this.zzjpi)) {
                throw new SecurityException(String.format("Unknown calling package name '%s'.", new Object[]{str}));
            }
            return;
        }
        this.zzjev.zzayp().zzbau().log("Measurement Service called without app package");
        throw new SecurityException("Measurement Service called without app package");
    }

    public final List<zzcnl> zza(zzcif zzcif, boolean z) {
        zzb(zzcif, false);
        try {
            List<zzcnn> list = (List) this.zzjev.zzayo().zzc(new zzcle(this, zzcif)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzcnn zzcnn : list) {
                if (z || !zzcno.zzkp(zzcnn.name)) {
                    arrayList.add(new zzcnl(zzcnn));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zzjev.zzayp().zzbau().zze("Failed to get user attributes. appId", zzcjj.zzjs(zzcif.packageName), e);
            return null;
        }
    }

    public final List<zzcii> zza(String str, String str2, zzcif zzcif) {
        zzb(zzcif, false);
        try {
            return (List) this.zzjev.zzayo().zzc(new zzckw(this, zzcif, str, str2)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zzjev.zzayp().zzbau().zzj("Failed to get conditional user properties", e);
            return Collections.emptyList();
        }
    }

    public final List<zzcnl> zza(String str, String str2, String str3, boolean z) {
        zzg(str, true);
        try {
            List<zzcnn> list = (List) this.zzjev.zzayo().zzc(new zzckv(this, str, str2, str3)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzcnn zzcnn : list) {
                if (z || !zzcno.zzkp(zzcnn.name)) {
                    arrayList.add(new zzcnl(zzcnn));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zzjev.zzayp().zzbau().zze("Failed to get user attributes. appId", zzcjj.zzjs(str), e);
            return Collections.emptyList();
        }
    }

    public final List<zzcnl> zza(String str, String str2, boolean z, zzcif zzcif) {
        zzb(zzcif, false);
        try {
            List<zzcnn> list = (List) this.zzjev.zzayo().zzc(new zzcku(this, zzcif, str, str2)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzcnn zzcnn : list) {
                if (z || !zzcno.zzkp(zzcnn.name)) {
                    arrayList.add(new zzcnl(zzcnn));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zzjev.zzayp().zzbau().zze("Failed to get user attributes. appId", zzcjj.zzjs(zzcif.packageName), e);
            return Collections.emptyList();
        }
    }

    public final void zza(long j, String str, String str2, String str3) {
        this.zzjev.zzayo().zzh(new zzclg(this, str2, str3, str, j));
    }

    public final void zza(zzcif zzcif) {
        zzb(zzcif, false);
        zzclf zzclf = new zzclf(this, zzcif);
        if (this.zzjev.zzayo().zzbbk()) {
            zzclf.run();
        } else {
            this.zzjev.zzayo().zzh(zzclf);
        }
    }

    public final void zza(zzcii zzcii, zzcif zzcif) {
        zzcke zzayo;
        Runnable zzckr;
        zzbq.checkNotNull(zzcii);
        zzbq.checkNotNull(zzcii.zzjgn);
        zzb(zzcif, false);
        zzcii zzcii2 = new zzcii(zzcii);
        zzcii2.packageName = zzcif.packageName;
        if (zzcii.zzjgn.getValue() == null) {
            zzayo = this.zzjev.zzayo();
            zzckr = new zzckq(this, zzcii2, zzcif);
        } else {
            zzayo = this.zzjev.zzayo();
            zzckr = new zzckr(this, zzcii2, zzcif);
        }
        zzayo.zzh(zzckr);
    }

    public final void zza(zzcix zzcix, zzcif zzcif) {
        zzbq.checkNotNull(zzcix);
        zzb(zzcif, false);
        this.zzjev.zzayo().zzh(new zzckz(this, zzcix, zzcif));
    }

    public final void zza(zzcix zzcix, String str, String str2) {
        zzbq.checkNotNull(zzcix);
        zzbq.zzgv(str);
        zzg(str, true);
        this.zzjev.zzayo().zzh(new zzcla(this, zzcix, str));
    }

    public final void zza(zzcnl zzcnl, zzcif zzcif) {
        zzcke zzayo;
        Runnable zzcld;
        zzbq.checkNotNull(zzcnl);
        zzb(zzcif, false);
        if (zzcnl.getValue() == null) {
            zzayo = this.zzjev.zzayo();
            zzcld = new zzclc(this, zzcnl, zzcif);
        } else {
            zzayo = this.zzjev.zzayo();
            zzcld = new zzcld(this, zzcnl, zzcif);
        }
        zzayo.zzh(zzcld);
    }

    public final byte[] zza(zzcix zzcix, String str) {
        zzbq.zzgv(str);
        zzbq.checkNotNull(zzcix);
        zzg(str, true);
        this.zzjev.zzayp().zzbaz().zzj("Log and bundle. event", this.zzjev.zzayk().zzjp(zzcix.name));
        long nanoTime = this.zzjev.zzxx().nanoTime() / 1000000;
        try {
            byte[] bArr = (byte[]) this.zzjev.zzayo().zzd(new zzclb(this, zzcix, str)).get();
            if (bArr == null) {
                this.zzjev.zzayp().zzbau().zzj("Log and bundle returned null. appId", zzcjj.zzjs(str));
                bArr = new byte[0];
            }
            this.zzjev.zzayp().zzbaz().zzd("Log and bundle processed. event, size, time_ms", this.zzjev.zzayk().zzjp(zzcix.name), Integer.valueOf(bArr.length), Long.valueOf((this.zzjev.zzxx().nanoTime() / 1000000) - nanoTime));
            return bArr;
        } catch (InterruptedException | ExecutionException e) {
            this.zzjev.zzayp().zzbau().zzd("Failed to log and bundle. appId, event, error", zzcjj.zzjs(str), this.zzjev.zzayk().zzjp(zzcix.name), e);
            return null;
        }
    }

    public final void zzb(zzcif zzcif) {
        zzb(zzcif, false);
        this.zzjev.zzayo().zzh(new zzckp(this, zzcif));
    }

    public final void zzb(zzcii zzcii) {
        zzcke zzayo;
        Runnable zzckt;
        zzbq.checkNotNull(zzcii);
        zzbq.checkNotNull(zzcii.zzjgn);
        zzg(zzcii.packageName, true);
        zzcii zzcii2 = new zzcii(zzcii);
        if (zzcii.zzjgn.getValue() == null) {
            zzayo = this.zzjev.zzayo();
            zzckt = new zzcks(this, zzcii2);
        } else {
            zzayo = this.zzjev.zzayo();
            zzckt = new zzckt(this, zzcii2);
        }
        zzayo.zzh(zzckt);
    }

    public final String zzc(zzcif zzcif) {
        zzb(zzcif, false);
        return this.zzjev.zzkf(zzcif.packageName);
    }

    public final void zzd(zzcif zzcif) {
        zzg(zzcif.packageName, false);
        this.zzjev.zzayo().zzh(new zzcky(this, zzcif));
    }

    public final List<zzcii> zzk(String str, String str2, String str3) {
        zzg(str, true);
        try {
            return (List) this.zzjev.zzayo().zzc(new zzckx(this, str, str2, str3)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zzjev.zzayp().zzbau().zzj("Failed to get conditional user properties", e);
            return Collections.emptyList();
        }
    }
}
