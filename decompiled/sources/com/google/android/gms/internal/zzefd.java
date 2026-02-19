package com.google.android.gms.internal;

import java.util.Map;

final class zzefd implements zzefh {
    private /* synthetic */ String val$action;
    private /* synthetic */ zzeey zznaz;
    private /* synthetic */ zzefo zznbc;
    private /* synthetic */ long zznbe;
    private /* synthetic */ zzefm zznbf;

    zzefd(zzeey zzeey, String str, long j, zzefm zzefm, zzefo zzefo) {
        this.zznaz = zzeey;
        this.val$action = str;
        this.zznbe = j;
        this.zznbf = zzefm;
        this.zznbc = zzefo;
    }

    public final void zzal(Map<String, Object> map) {
        if (this.zznaz.zzmxz.zzcbu()) {
            zzemm zza = this.zznaz.zzmxz;
            String str = this.val$action;
            String valueOf = String.valueOf(map);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 11 + String.valueOf(valueOf).length());
            sb.append(str);
            sb.append(" response: ");
            sb.append(valueOf);
            zza.zzb(sb.toString(), (Throwable) null, new Object[0]);
        }
        if (((zzefm) this.zznaz.zznam.get(Long.valueOf(this.zznbe))) == this.zznbf) {
            this.zznaz.zznam.remove(Long.valueOf(this.zznbe));
            if (this.zznbc != null) {
                String str2 = (String) map.get("s");
                if (str2.equals("ok")) {
                    this.zznbc.zzbc((String) null, (String) null);
                } else {
                    this.zznbc.zzbc(str2, (String) map.get("d"));
                }
            }
        } else if (this.zznaz.zzmxz.zzcbu()) {
            zzemm zza2 = this.zznaz.zzmxz;
            long j = this.zznbe;
            StringBuilder sb2 = new StringBuilder(81);
            sb2.append("Ignoring on complete for put ");
            sb2.append(j);
            sb2.append(" because it was removed already.");
            zza2.zzb(sb2.toString(), (Throwable) null, new Object[0]);
        }
        this.zznaz.zzbxa();
    }
}
