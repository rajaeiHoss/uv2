package com.google.android.gms.internal;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.FirebaseDatabase;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

public class zzegm {
    protected long cacheSize = 10485760;
    protected FirebaseApp zzmxo;
    private zzemn zzmzw;
    protected boolean zzmzx;
    private String zzmzz;
    private boolean zzndv = false;
    protected zzegt zznei;
    protected zzegd zznej;
    protected zzeig zznek;
    protected String zznel;
    protected zzemo zznem = zzemo.INFO;
    private boolean zznen = false;
    private zzegw zzneo;

    private final ScheduledExecutorService zzbwm() {
        zzeig zzeig = this.zznek;
        if (zzeig instanceof zzeou) {
            return ((zzeou) zzeig).zzbwm();
        }
        throw new RuntimeException("Custom run loops are not supported!");
    }

    private final zzegw zzbyd() {
        if (this.zzneo == null) {
            zzbye();
        }
        return this.zzneo;
    }

    private final synchronized void zzbye() {
        this.zzneo = new zzedg(this.zzmxo);
    }

    public final boolean isPersistenceEnabled() {
        return this.zzmzx;
    }

    /* access modifiers changed from: package-private */
    public final void stop() {
        this.zznen = true;
        this.zznei.shutdown();
        this.zznek.shutdown();
    }

    public final zzeew zza(zzeeu zzeeu, zzeex zzeex) {
        return zzbyd().zza(this, new zzees(this.zzmzw, new zzegn(this.zznej), zzbwm(), this.zzmzx, FirebaseDatabase.getSdkVersion(), this.zzmzz, zzbyd().zzbvj().getAbsolutePath()), zzeeu, zzeex);
    }

    public final String zzbwo() {
        return this.zzmzz;
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zzbxu() {
        if (!this.zzndv) {
            this.zzndv = true;
            if (this.zzmzw == null) {
                this.zzmzw = zzbyd().zza(this, this.zznem, (List<String>) null);
            }
            zzbyd();
            if (this.zzmzz == null) {
                String zzc = zzbyd().zzc(this);
                this.zzmzz = "Firebase/" + "5/" + FirebaseDatabase.getSdkVersion() + "/" + zzc;
            }
            if (this.zznei == null) {
                this.zznei = zzbyd().zza(this);
            }
            if (this.zznek == null) {
                this.zznek = this.zzneo.zzb(this);
            }
            if (this.zznel == null) {
                this.zznel = "default";
            }
            if (this.zznej == null) {
                this.zznej = zzbyd().zza(zzbwm());
            }
        }
    }

    public final void zzbyf() {
        if (this.zznen) {
            this.zznei.restart();
            this.zznek.restart();
            this.zznen = false;
        }
    }

    /* access modifiers changed from: protected */
    public final void zzbyg() {
        if (this.zzndv) {
            throw new DatabaseException("Modifications to DatabaseConfig objects must occur before they are in use");
        }
    }

    public final zzemo zzbyh() {
        return this.zznem;
    }

    public final long zzbyi() {
        return this.cacheSize;
    }

    public final zzegt zzbyj() {
        return this.zznei;
    }

    public final String zzbyk() {
        return this.zznel;
    }

    public final zzemm zzqb(String str) {
        return new zzemm(this.zzmzw, str);
    }

    /* access modifiers changed from: package-private */
    public final zzeki zzqc(String str) {
        return this.zzmzx ? this.zzneo.zza(this, str) : new zzekh();
    }
}
