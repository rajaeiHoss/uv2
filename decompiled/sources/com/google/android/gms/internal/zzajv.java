package com.google.android.gms.internal;

final class zzajv implements zzy {
    private /* synthetic */ String zzcrd;
    private /* synthetic */ zzajy zzdhe;

    zzajv(zzajr zzajr, String str, zzajy zzajy) {
        this.zzcrd = str;
        this.zzdhe = zzajy;
    }

    public final void zzd(zzae zzae) {
        String str = this.zzcrd;
        String zzae2 = zzae.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 21 + String.valueOf(zzae2).length());
        sb.append("Failed to load URL: ");
        sb.append(str);
        sb.append("\n");
        sb.append(zzae2);
        zzahw.zzcz(sb.toString());
        this.zzdhe.zzb(null);
    }
}
