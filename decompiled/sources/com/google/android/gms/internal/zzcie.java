package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import org.xbill.DNS.TTL;

final class zzcie {
    private final String zzcm;
    private String zzina;
    private final zzckj zzjev;
    private String zzjfk;
    private String zzjfl;
    private String zzjfm;
    private String zzjfn;
    private long zzjfo;
    private long zzjfp;
    private long zzjfq;
    private long zzjfr;
    private String zzjfs;
    private long zzjft;
    private long zzjfu;
    private boolean zzjfv;
    private long zzjfw;
    private boolean zzjfx;
    private long zzjfy;
    private long zzjfz;
    private long zzjga;
    private long zzjgb;
    private long zzjgc;
    private long zzjgd;
    private String zzjge;
    private boolean zzjgf;
    private long zzjgg;
    private long zzjgh;

    zzcie(zzckj zzckj, String str) {
        zzbq.checkNotNull(zzckj);
        zzbq.zzgv(str);
        this.zzjev = zzckj;
        this.zzcm = str;
        zzckj.zzayo().zzwj();
    }

    public final String getAppId() {
        this.zzjev.zzayo().zzwj();
        return this.zzcm;
    }

    public final String getAppInstanceId() {
        this.zzjev.zzayo().zzwj();
        return this.zzjfk;
    }

    public final String getGmpAppId() {
        this.zzjev.zzayo().zzwj();
        return this.zzjfl;
    }

    public final void setAppVersion(String str) {
        this.zzjev.zzayo().zzwj();
        this.zzjgf |= !zzcno.zzas(this.zzina, str);
        this.zzina = str;
    }

    public final void setMeasurementEnabled(boolean z) {
        this.zzjev.zzayo().zzwj();
        this.zzjgf |= this.zzjfv != z;
        this.zzjfv = z;
    }

    public final void zzal(long j) {
        this.zzjev.zzayo().zzwj();
        this.zzjgf |= this.zzjfp != j;
        this.zzjfp = j;
    }

    public final void zzam(long j) {
        this.zzjev.zzayo().zzwj();
        this.zzjgf |= this.zzjfq != j;
        this.zzjfq = j;
    }

    public final void zzan(long j) {
        this.zzjev.zzayo().zzwj();
        this.zzjgf |= this.zzjfr != j;
        this.zzjfr = j;
    }

    public final void zzao(long j) {
        this.zzjev.zzayo().zzwj();
        this.zzjgf |= this.zzjft != j;
        this.zzjft = j;
    }

    public final void zzap(long j) {
        this.zzjev.zzayo().zzwj();
        this.zzjgf |= this.zzjfu != j;
        this.zzjfu = j;
    }

    public final void zzaq(long j) {
        boolean z = true;
        zzbq.checkArgument(j >= 0);
        this.zzjev.zzayo().zzwj();
        boolean z2 = this.zzjgf;
        if (this.zzjfo == j) {
            z = false;
        }
        this.zzjgf = z | z2;
        this.zzjfo = j;
    }

    public final void zzar(long j) {
        this.zzjev.zzayo().zzwj();
        this.zzjgf |= this.zzjgg != j;
        this.zzjgg = j;
    }

    public final void zzas(long j) {
        this.zzjev.zzayo().zzwj();
        this.zzjgf |= this.zzjgh != j;
        this.zzjgh = j;
    }

    public final void zzat(long j) {
        this.zzjev.zzayo().zzwj();
        this.zzjgf |= this.zzjfy != j;
        this.zzjfy = j;
    }

    public final void zzau(long j) {
        this.zzjev.zzayo().zzwj();
        this.zzjgf |= this.zzjfz != j;
        this.zzjfz = j;
    }

    public final void zzav(long j) {
        this.zzjev.zzayo().zzwj();
        this.zzjgf |= this.zzjga != j;
        this.zzjga = j;
    }

    public final void zzaw(long j) {
        this.zzjev.zzayo().zzwj();
        this.zzjgf |= this.zzjgb != j;
        this.zzjgb = j;
    }

    public final void zzax(long j) {
        this.zzjev.zzayo().zzwj();
        this.zzjgf |= this.zzjgd != j;
        this.zzjgd = j;
    }

    public final void zzay(long j) {
        this.zzjev.zzayo().zzwj();
        this.zzjgf |= this.zzjgc != j;
        this.zzjgc = j;
    }

    public final void zzays() {
        this.zzjev.zzayo().zzwj();
        this.zzjgf = false;
    }

    public final String zzayt() {
        this.zzjev.zzayo().zzwj();
        return this.zzjfm;
    }

    public final String zzayu() {
        this.zzjev.zzayo().zzwj();
        return this.zzjfn;
    }

    public final long zzayv() {
        this.zzjev.zzayo().zzwj();
        return this.zzjfp;
    }

    public final long zzayw() {
        this.zzjev.zzayo().zzwj();
        return this.zzjfq;
    }

    public final long zzayx() {
        this.zzjev.zzayo().zzwj();
        return this.zzjfr;
    }

    public final String zzayy() {
        this.zzjev.zzayo().zzwj();
        return this.zzjfs;
    }

    public final long zzayz() {
        this.zzjev.zzayo().zzwj();
        return this.zzjft;
    }

    public final void zzaz(long j) {
        this.zzjev.zzayo().zzwj();
        this.zzjgf |= this.zzjfw != j;
        this.zzjfw = j;
    }

    public final long zzaza() {
        this.zzjev.zzayo().zzwj();
        return this.zzjfu;
    }

    public final boolean zzazb() {
        this.zzjev.zzayo().zzwj();
        return this.zzjfv;
    }

    public final long zzazc() {
        this.zzjev.zzayo().zzwj();
        return this.zzjfo;
    }

    public final long zzazd() {
        this.zzjev.zzayo().zzwj();
        return this.zzjgg;
    }

    public final long zzaze() {
        this.zzjev.zzayo().zzwj();
        return this.zzjgh;
    }

    public final void zzazf() {
        this.zzjev.zzayo().zzwj();
        long j = this.zzjfo + 1;
        if (j > TTL.MAX_VALUE) {
            this.zzjev.zzayp().zzbaw().zzj("Bundle index overflow. appId", zzcjj.zzjs(this.zzcm));
            j = 0;
        }
        this.zzjgf = true;
        this.zzjfo = j;
    }

    public final long zzazg() {
        this.zzjev.zzayo().zzwj();
        return this.zzjfy;
    }

    public final long zzazh() {
        this.zzjev.zzayo().zzwj();
        return this.zzjfz;
    }

    public final long zzazi() {
        this.zzjev.zzayo().zzwj();
        return this.zzjga;
    }

    public final long zzazj() {
        this.zzjev.zzayo().zzwj();
        return this.zzjgb;
    }

    public final long zzazk() {
        this.zzjev.zzayo().zzwj();
        return this.zzjgd;
    }

    public final long zzazl() {
        this.zzjev.zzayo().zzwj();
        return this.zzjgc;
    }

    public final String zzazm() {
        this.zzjev.zzayo().zzwj();
        return this.zzjge;
    }

    public final String zzazn() {
        this.zzjev.zzayo().zzwj();
        String str = this.zzjge;
        zzjd((String) null);
        return str;
    }

    public final long zzazo() {
        this.zzjev.zzayo().zzwj();
        return this.zzjfw;
    }

    public final boolean zzazp() {
        this.zzjev.zzayo().zzwj();
        return this.zzjfx;
    }

    public final void zzbq(boolean z) {
        this.zzjev.zzayo().zzwj();
        this.zzjgf = this.zzjfx != z;
        this.zzjfx = z;
    }

    public final void zziy(String str) {
        this.zzjev.zzayo().zzwj();
        this.zzjgf |= !zzcno.zzas(this.zzjfk, str);
        this.zzjfk = str;
    }

    public final void zziz(String str) {
        this.zzjev.zzayo().zzwj();
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.zzjgf |= !zzcno.zzas(this.zzjfl, str);
        this.zzjfl = str;
    }

    public final void zzja(String str) {
        this.zzjev.zzayo().zzwj();
        this.zzjgf |= !zzcno.zzas(this.zzjfm, str);
        this.zzjfm = str;
    }

    public final void zzjb(String str) {
        this.zzjev.zzayo().zzwj();
        this.zzjgf |= !zzcno.zzas(this.zzjfn, str);
        this.zzjfn = str;
    }

    public final void zzjc(String str) {
        this.zzjev.zzayo().zzwj();
        this.zzjgf |= !zzcno.zzas(this.zzjfs, str);
        this.zzjfs = str;
    }

    public final void zzjd(String str) {
        this.zzjev.zzayo().zzwj();
        this.zzjgf |= !zzcno.zzas(this.zzjge, str);
        this.zzjge = str;
    }

    public final String zzwo() {
        this.zzjev.zzayo().zzwj();
        return this.zzina;
    }
}
