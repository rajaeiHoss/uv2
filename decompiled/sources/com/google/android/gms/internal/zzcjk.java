package com.google.android.gms.internal;

final class zzcjk implements Runnable {
    private /* synthetic */ String val$message;
    private /* synthetic */ int zzjkr;
    private /* synthetic */ Object zzjks;
    private /* synthetic */ Object zzjkt;
    private /* synthetic */ Object zzjku;
    private /* synthetic */ zzcjj zzjkv;

    zzcjk(zzcjj zzcjj, int i, String str, Object obj, Object obj2, Object obj3) {
        this.zzjkv = zzcjj;
        this.zzjkr = i;
        this.val$message = str;
        this.zzjks = obj;
        this.zzjkt = obj2;
        this.zzjku = obj3;
    }

    public final void run() {
        char c;
        zzcjj zzcjjVar;
        zzcju zzayq = this.zzjkv.zzjev.zzayq();
        if (!zzayq.isInitialized()) {
            this.zzjkv.zzm(6, "Persisted config not initialized. Not logging error/warn");
            return;
        }
        if (this.zzjkv.zzjkg == 0) {
            if (this.zzjkv.zzayr().zzzu()) {
                zzcjjVar = this.zzjkv;
                c = 'C';
            } else {
                zzcjjVar = this.zzjkv;
                c = 'c';
            }
            char unused = zzcjjVar.zzjkg = c;
        }
        if (this.zzjkv.zzjft < 0) {
            long unused2 = this.zzjkv.zzjft = 12211;
        }
        char charAt = "01VDIWEA?".charAt(this.zzjkr);
        char zza = this.zzjkv.zzjkg;
        long zzb = this.zzjkv.zzjft;
        String zza2 = zzcjj.zza(true, this.val$message, this.zzjks, this.zzjkt, this.zzjku);
        StringBuilder sb = new StringBuilder(String.valueOf(zza2).length() + 24);
        sb.append("2");
        sb.append(charAt);
        sb.append(zza);
        sb.append(zzb);
        sb.append(":");
        sb.append(zza2);
        String sb2 = sb.toString();
        if (sb2.length() > 1024) {
            sb2 = this.val$message.substring(0, 1024);
        }
        zzayq.zzjlm.zzf(sb2, 1);
    }
}
