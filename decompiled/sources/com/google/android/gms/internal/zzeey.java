package com.google.android.gms.internal;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import kotlin.text.Typography;
import org.jivesoftware.smackx.packet.CapsExtension;

public final class zzeey implements zzeen, zzeew {
    private static long zzmzj;
    private final ScheduledExecutorService zzmxn;
    /* access modifiers changed from: private */
    public final zzemm zzmxz;
    private final zzeeu zzmzk;
    /* access modifiers changed from: private */
    public final zzeeq zzmzv;
    /* access modifiers changed from: private */
    public final zzeex zznab;
    private String zznac;
    private HashSet<String> zznad = new HashSet<>();
    private boolean zznae = true;
    private long zznaf;
    /* access modifiers changed from: private */
    public zzeem zznag;
    /* access modifiers changed from: private */
    public zzefi zznah = zzefi.Disconnected;
    private long zznai = 0;
    private long zznaj = 0;
    private Map<Long, zzefh> zznak;
    private List<zzefk> zznal;
    /* access modifiers changed from: private */
    public Map<Long, zzefm> zznam;
    /* access modifiers changed from: private */
    public Map<zzefj, zzefl> zznan;
    /* access modifiers changed from: private */
    public String zznao;
    /* access modifiers changed from: private */
    public boolean zznap;
    private final zzees zznaq;
    /* access modifiers changed from: private */
    public final zzefz zznar;
    private String zznas;
    /* access modifiers changed from: private */
    public long zznat = 0;
    /* access modifiers changed from: private */
    public int zznau = 0;
    /* access modifiers changed from: private */
    public ScheduledFuture<?> zznav = null;
    private long zznaw;
    private boolean zznax;

    public zzeey(zzees zzees, zzeeu zzeeu, zzeex zzeex) {
        this.zznab = zzeex;
        this.zznaq = zzees;
        ScheduledExecutorService zzbwm = zzees.zzbwm();
        this.zzmxn = zzbwm;
        this.zzmzv = zzees.zzbwl();
        this.zzmzk = zzeeu;
        this.zznan = new HashMap();
        this.zznak = new HashMap();
        this.zznam = new HashMap();
        this.zznal = new ArrayList();
        this.zznar = new zzegb(zzbwm, zzees.zzbwk(), "ConnectionRetryHelper").zzbt(1000).zzi(1.3d).zzbu(NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS).zzj(0.7d).zzbxt();
        long j = zzmzj;
        zzmzj = 1 + j;
        zzemn zzbwk = zzees.zzbwk();
        StringBuilder sb = new StringBuilder(23);
        sb.append("pc_");
        sb.append(j);
        this.zzmxz = new zzemm(zzbwk, "PersistentConnection", sb.toString());
        this.zznas = null;
        zzbxa();
    }

    private final boolean isIdle() {
        return this.zznan.isEmpty() && this.zznak.isEmpty() && !this.zznax && this.zznam.isEmpty();
    }

    /* access modifiers changed from: private */
    public final zzefl zza(zzefj zzefj) {
        if (this.zzmxz.zzcbu()) {
            zzemm zzemm = this.zzmxz;
            String valueOf = String.valueOf(zzefj);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 15);
            sb.append("removing query ");
            sb.append(valueOf);
            zzemm.zzb(sb.toString(), (Throwable) null, new Object[0]);
        }
        if (!this.zznan.containsKey(zzefj)) {
            if (this.zzmxz.zzcbu()) {
                zzemm zzemm2 = this.zzmxz;
                String valueOf2 = String.valueOf(zzefj);
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 64);
                sb2.append("Trying to remove listener for QuerySpec ");
                sb2.append(valueOf2);
                sb2.append(" but no listener exists.");
                zzemm2.zzb(sb2.toString(), (Throwable) null, new Object[0]);
            }
            return null;
        }
        zzefl zzefl = this.zznan.get(zzefj);
        this.zznan.remove(zzefj);
        zzbxa();
        return zzefl;
    }

    private final void zza(zzefl zzefl) {
        HashMap hashMap = new HashMap();
        hashMap.put("p", zzeet.zzau(zzefl.zzbxd().zznbn));
        Long zzbxe = zzefl.zzbxe();
        if (zzbxe != null) {
            hashMap.put("q", zzefl.zznbs.zznbo);
            hashMap.put("t", zzbxe);
        }
        zzeev zzbxf = zzefl.zzbxf();
        hashMap.put("h", zzbxf.zzbwq());
        if (zzbxf.zzbwr()) {
            zzeel zzbws = zzbxf.zzbws();
            ArrayList arrayList = new ArrayList();
            for (List<String> zzau : zzbws.zzbwi()) {
                arrayList.add(zzeet.zzau(zzau));
            }
            HashMap hashMap2 = new HashMap();
            hashMap2.put("hs", zzbws.zzbwj());
            hashMap2.put("ps", arrayList);
            hashMap.put("ch", hashMap2);
        }
        zza("q", (Map<String, Object>) hashMap, (zzefh) new zzefe(this, zzefl));
    }

    private final void zza(String str, List<String> list, Object obj, zzefo zzefo) {
        HashMap hashMap = new HashMap();
        hashMap.put("p", zzeet.zzau(list));
        hashMap.put("d", obj);
        zza(str, (Map<String, Object>) hashMap, (zzefh) new zzefb(this, zzefo));
    }

    private final void zza(String str, List<String> list, Object obj, String str2, zzefo zzefo) {
        HashMap hashMap = new HashMap();
        hashMap.put("p", zzeet.zzau(list));
        hashMap.put("d", obj);
        if (str2 != null) {
            hashMap.put("h", str2);
        }
        long j = this.zznai;
        this.zznai = 1 + j;
        this.zznam.put(Long.valueOf(j), new zzefm(str, hashMap, zzefo, (zzeez) null));
        if (zzbwv()) {
            zzbq(j);
        }
        this.zznaw = System.currentTimeMillis();
        zzbxa();
    }

    private final void zza(String str, Map<String, Object> map, zzefh zzefh) {
        zza(str, false, map, zzefh);
    }

    private final void zza(String str, boolean z, Map<String, Object> map, zzefh zzefh) {
        long j = this.zznaj;
        this.zznaj = 1 + j;
        HashMap hashMap = new HashMap();
        hashMap.put("r", Long.valueOf(j));
        hashMap.put("a", str);
        hashMap.put("b", map);
        this.zznag.zza(hashMap, z);
        this.zznak.put(Long.valueOf(j), zzefh);
    }

    /* access modifiers changed from: private */
    public final void zza(List<String> list, zzefj zzefj) {
        if (list.contains("no_index")) {
            String valueOf = String.valueOf(zzefj.zznbo.get("i"));
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 14);
            sb.append("\".indexOn\": \"");
            sb.append(valueOf);
            sb.append(Typography.quote);
            String sb2 = sb.toString();
            zzemm zzemm = this.zzmxz;
            String zzau = zzeet.zzau(zzefj.zznbn);
            StringBuilder sb3 = new StringBuilder(String.valueOf(sb2).length() + 175 + String.valueOf(zzau).length());
            sb3.append("Using an unspecified index. Your data will be downloaded and filtered on the client. Consider adding '");
            sb3.append(sb2);
            sb3.append("' at ");
            sb3.append(zzau);
            sb3.append(" to your security and Firebase Database rules for better performance");
            zzemm.zzf(sb3.toString(), (Throwable) null);
        }
    }

    private final void zzav(List<String> list) {
        int i = 0;
        if (this.zzmxz.zzcbu()) {
            zzemm zzemm = this.zzmxz;
            String valueOf = String.valueOf(list);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 29);
            sb.append("removing all listens at path ");
            sb.append(valueOf);
            zzemm.zzb(sb.toString(), (Throwable) null, new Object[0]);
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : this.zznan.entrySet()) {
            zzefl zzefl = (zzefl) next.getValue();
            if (((zzefj) next.getKey()).zznbn.equals(list)) {
                arrayList.add(zzefl);
            }
        }
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj = arrayList2.get(i2);
            i2++;
            this.zznan.remove(((zzefl) obj).zzbxd());
        }
        zzbxa();
        int size2 = arrayList2.size();
        while (i < size2) {
            Object obj2 = arrayList2.get(i);
            i++;
            ((zzefl) obj2).zznbr.zzbc("permission_denied", (String) null);
        }
    }

    private final void zzbq(long j) {
        zzefm zzefm = this.zznam.get(Long.valueOf(j));
        zzefo zzbxc = zzefm.zzbxc();
        String action = zzefm.getAction();
        zzefm.zzbxh();
        zza(action, zzefm.zzbxg(), (zzefh) new zzefd(this, action, j, zzefm, zzbxc));
    }

    private final boolean zzbwu() {
        return this.zznah == zzefi.Authenticating || this.zznah == zzefi.Connected;
    }

    private final boolean zzbwv() {
        return this.zznah == zzefi.Connected;
    }

    private final boolean zzbww() {
        return this.zznad.size() == 0;
    }

    /* access modifiers changed from: private */
    public final void zzbwx() {
        if (zzbww()) {
            zzeet.zzc(this.zznah == zzefi.Disconnected, "Not in disconnected state: %s", this.zznah);
            boolean z = this.zznap;
            this.zzmxz.zzb("Scheduling connection attempt", (Throwable) null, new Object[0]);
            this.zznap = false;
            this.zznar.zzo(new zzeez(this, z));
        }
    }

    private final void zzbwy() {
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<Long, zzefm>> it = this.zznam.entrySet().iterator();
        while (it.hasNext()) {
            zzefm zzefm = (zzefm) it.next().getValue();
            if (zzefm.zzbxg().containsKey("h") && zzefm.zzbxi()) {
                arrayList.add(zzefm);
                it.remove();
            }
        }
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            ((zzefm) obj).zzbxc().zzbc("disconnected", (String) null);
        }
    }

    /* access modifiers changed from: private */
    public final void zzbwz() {
        int i = 0;
        zzeet.zzc(this.zznah == zzefi.Connected, "Should be connected if we're restoring state, but we are: %s", this.zznah);
        if (this.zzmxz.zzcbu()) {
            this.zzmxz.zzb("Restoring outstanding listens", (Throwable) null, new Object[0]);
        }
        for (zzefl next : this.zznan.values()) {
            if (this.zzmxz.zzcbu()) {
                zzemm zzemm = this.zzmxz;
                String valueOf = String.valueOf(next.zzbxd());
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 17);
                sb.append("Restoring listen ");
                sb.append(valueOf);
                zzemm.zzb(sb.toString(), (Throwable) null, new Object[0]);
            }
            zza(next);
        }
        if (this.zzmxz.zzcbu()) {
            this.zzmxz.zzb("Restoring writes.", (Throwable) null, new Object[0]);
        }
        ArrayList arrayList = new ArrayList(this.zznam.keySet());
        Collections.sort(arrayList);
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            zzbq(((Long) obj).longValue());
        }
        for (zzefk next2 : this.zznal) {
            zza(next2.getAction(), next2.getPath(), next2.getData(), next2.zzbxc());
        }
        this.zznal.clear();
    }

    /* access modifiers changed from: private */
    public final void zzbxa() {
        if (isIdle()) {
            ScheduledFuture<?> scheduledFuture = this.zznav;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            this.zznav = this.zzmxn.schedule(new zzefg(this), 60000, TimeUnit.MILLISECONDS);
        } else if (isInterrupted("connection_idle")) {
            zzeet.zzc(!isIdle(), "", new Object[0]);
            resume("connection_idle");
        }
    }

    /* access modifiers changed from: private */
    public final boolean zzbxb() {
        return isIdle() && System.currentTimeMillis() > this.zznaw + 60000;
    }

    static /* synthetic */ long zzc(zzeey zzeey) {
        long j = zzeey.zznat;
        zzeey.zznat = 1 + j;
        return j;
    }

    private final void zzct(boolean z) {
        String str;
        zzeet.zzc(zzbwu(), "Must be connected to send auth, but was: %s", this.zznah);
        zzeet.zzc(this.zznao != null, "Auth token must be set to authenticate!", new Object[0]);
        zzefc zzefc = new zzefc(this, z);
        HashMap hashMap = new HashMap();
        zzeoq zzqg = zzeoq.zzqg(this.zznao);
        if (zzqg != null) {
            hashMap.put("cred", zzqg.getToken());
            if (zzqg.zzcdo() != null) {
                hashMap.put("authvar", zzqg.zzcdo());
            }
            str = "gauth";
        } else {
            hashMap.put("cred", this.zznao);
            str = "auth";
        }
        zza(str, true, (Map<String, Object>) hashMap, (zzefh) zzefc);
    }

    static /* synthetic */ int zzj(zzeey zzeey) {
        int i = zzeey.zznau;
        zzeey.zznau = i + 1;
        return i;
    }

    public final void initialize() {
        zzbwx();
    }

    public final void interrupt(String str) {
        if (this.zzmxz.zzcbu()) {
            zzemm zzemm = this.zzmxz;
            String valueOf = String.valueOf(str);
            zzemm.zzb(valueOf.length() != 0 ? "Connection interrupted for: ".concat(valueOf) : new String("Connection interrupted for: "), (Throwable) null, new Object[0]);
        }
        this.zznad.add(str);
        zzeem zzeem = this.zznag;
        if (zzeem != null) {
            zzeem.close();
            this.zznag = null;
        } else {
            this.zznar.cancel();
            this.zznah = zzefi.Disconnected;
        }
        this.zznar.zzbxr();
    }

    public final boolean isInterrupted(String str) {
        return this.zznad.contains(str);
    }

    public final void purgeOutstandingWrites() {
        for (zzefm next : this.zznam.values()) {
            if (next.zznbq != null) {
                next.zznbq.zzbc("write_canceled", (String) null);
            }
        }
        for (zzefk next2 : this.zznal) {
            if (next2.zznbq != null) {
                next2.zznbq.zzbc("write_canceled", (String) null);
            }
        }
        this.zznam.clear();
        this.zznal.clear();
        if (!zzbwu()) {
            this.zznax = false;
        }
        zzbxa();
    }

    public final void refreshAuthToken() {
        this.zzmxz.zzb("Auth token refresh requested", (Throwable) null, new Object[0]);
        interrupt("token_refresh");
        resume("token_refresh");
    }

    public final void resume(String str) {
        if (this.zzmxz.zzcbu()) {
            zzemm zzemm = this.zzmxz;
            String valueOf = String.valueOf(str);
            zzemm.zzb(valueOf.length() != 0 ? "Connection no longer interrupted for: ".concat(valueOf) : new String("Connection no longer interrupted for: "), (Throwable) null, new Object[0]);
        }
        this.zznad.remove(str);
        if (zzbww() && this.zznah == zzefi.Disconnected) {
            zzbwx();
        }
    }

    public final void shutdown() {
        interrupt("shutdown");
    }

    public final void zza(List<String> list, zzefo zzefo) {
        if (zzbwv()) {
            zza("oc", list, (Object) null, zzefo);
        } else {
            this.zznal.add(new zzefk("oc", list, (Object) null, zzefo, (zzeez) null));
        }
        zzbxa();
    }

    public final void zza(List<String> list, Object obj, zzefo zzefo) {
        zza("p", list, obj, (String) null, zzefo);
    }

    public final void zza(List<String> list, Object obj, String str, zzefo zzefo) {
        zza("p", list, obj, str, zzefo);
    }

    public final void zza(List<String> list, Map<String, Object> map) {
        zzefj zzefj = new zzefj(list, map);
        if (this.zzmxz.zzcbu()) {
            zzemm zzemm = this.zzmxz;
            String valueOf = String.valueOf(zzefj);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 15);
            sb.append("unlistening on ");
            sb.append(valueOf);
            zzemm.zzb(sb.toString(), (Throwable) null, new Object[0]);
        }
        zzefl zza = zza(zzefj);
        if (zza != null && zzbwu()) {
            HashMap hashMap = new HashMap();
            hashMap.put("p", zzeet.zzau(zza.zznbs.zznbn));
            Long zzbxe = zza.zzbxe();
            if (zzbxe != null) {
                hashMap.put("q", zza.zzbxd().zznbo);
                hashMap.put("t", zzbxe);
            }
            zza("n", (Map<String, Object>) hashMap, (zzefh) null);
        }
        zzbxa();
    }

    public final void zza(List<String> list, Map<String, Object> map, zzeev zzeev, Long l, zzefo zzefo) {
        zzefj zzefj = new zzefj(list, map);
        if (this.zzmxz.zzcbu()) {
            zzemm zzemm = this.zzmxz;
            String valueOf = String.valueOf(zzefj);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 13);
            sb.append("Listening on ");
            sb.append(valueOf);
            zzemm.zzb(sb.toString(), (Throwable) null, new Object[0]);
        }
        zzeet.zzc(!this.zznan.containsKey(zzefj), "listen() called twice for same QuerySpec.", new Object[0]);
        if (this.zzmxz.zzcbu()) {
            zzemm zzemm2 = this.zzmxz;
            String valueOf2 = String.valueOf(zzefj);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 21);
            sb2.append("Adding listen query: ");
            sb2.append(valueOf2);
            zzemm2.zzb(sb2.toString(), (Throwable) null, new Object[0]);
        }
        zzefl zzefl = new zzefl(zzefo, zzefj, l, zzeev, (zzeez) null);
        this.zznan.put(zzefj, zzefl);
        if (zzbwu()) {
            zza(zzefl);
        }
        zzbxa();
    }

    public final void zza(List<String> list, Map<String, Object> map, zzefo zzefo) {
        zza("m", list, (Object) map, (String) null, zzefo);
    }

    public final void zzaj(Map<String, Object> map) {
        if (map.containsKey("r")) {
            zzefh remove = this.zznak.remove(Long.valueOf((long) ((Integer) map.get("r")).intValue()));
            if (remove != null) {
                remove.zzal((Map) map.get("b"));
            }
        } else if (map.containsKey("error")) {
        } else {
            if (map.containsKey("a")) {
                String str = (String) map.get("a");
                Map map2 = (Map) map.get("b");
                if (this.zzmxz.zzcbu()) {
                    zzemm zzemm = this.zzmxz;
                    String valueOf = String.valueOf(map2);
                    StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 22 + String.valueOf(valueOf).length());
                    sb.append("handleServerMessage: ");
                    sb.append(str);
                    sb.append(" ");
                    sb.append(valueOf);
                    zzemm.zzb(sb.toString(), (Throwable) null, new Object[0]);
                }
                if (str.equals("d") || str.equals("m")) {
                    boolean equals = str.equals("m");
                    String str2 = (String) map2.get("p");
                    Object obj = map2.get("d");
                    Long zzbu = zzeet.zzbu(map2.get("t"));
                    if (!equals || !(obj instanceof Map) || ((Map) obj).size() != 0) {
                        this.zznab.zza(zzeet.zzps(str2), obj, equals, zzbu);
                    } else if (this.zzmxz.zzcbu()) {
                        zzemm zzemm2 = this.zzmxz;
                        String valueOf2 = String.valueOf(str2);
                        zzemm2.zzb(valueOf2.length() != 0 ? "ignoring empty merge for path ".concat(valueOf2) : new String("ignoring empty merge for path "), (Throwable) null, new Object[0]);
                    }
                } else if (str.equals("rm")) {
                    String str3 = (String) map2.get("p");
                    List<String> zzps = zzeet.zzps(str3);
                    Object obj2 = map2.get("d");
                    Long zzbu2 = zzeet.zzbu(map2.get("t"));
                    ArrayList arrayList = new ArrayList();
                    for (Object item : (List) obj2) {
                        Map map3 = (Map) item;
                        String str4 = (String) map3.get("s");
                        String str5 = (String) map3.get("e");
                        arrayList.add(new zzefn(str4 != null ? zzeet.zzps(str4) : null, str5 != null ? zzeet.zzps(str5) : null, map3.get("m")));
                    }
                    if (!arrayList.isEmpty()) {
                        this.zznab.zza(zzps, arrayList, zzbu2);
                    } else if (this.zzmxz.zzcbu()) {
                        zzemm zzemm3 = this.zzmxz;
                        String valueOf3 = String.valueOf(str3);
                        zzemm3.zzb(valueOf3.length() != 0 ? "Ignoring empty range merge for path ".concat(valueOf3) : new String("Ignoring empty range merge for path "), (Throwable) null, new Object[0]);
                    }
                } else if (str.equals(CapsExtension.NODE_NAME)) {
                    zzav(zzeet.zzps((String) map2.get("p")));
                } else if (str.equals("ac")) {
                    String str6 = (String) map2.get("s");
                    String str7 = (String) map2.get("d");
                    zzemm zzemm4 = this.zzmxz;
                    StringBuilder sb2 = new StringBuilder(String.valueOf(str6).length() + 23 + String.valueOf(str7).length());
                    sb2.append("Auth token revoked: ");
                    sb2.append(str6);
                    sb2.append(" (");
                    sb2.append(str7);
                    sb2.append(")");
                    zzemm4.zzb(sb2.toString(), (Throwable) null, new Object[0]);
                    this.zznao = null;
                    this.zznap = true;
                    this.zznab.zzcs(false);
                    this.zznag.close();
                } else if (str.equals("sd")) {
                    this.zzmxz.info((String) map2.get(NotificationCompat.CATEGORY_MESSAGE));
                } else if (this.zzmxz.zzcbu()) {
                    zzemm zzemm5 = this.zzmxz;
                    String valueOf4 = String.valueOf(str);
                    zzemm5.zzb(valueOf4.length() != 0 ? "Unrecognized action from server: ".concat(valueOf4) : new String("Unrecognized action from server: "), (Throwable) null, new Object[0]);
                }
            } else if (this.zzmxz.zzcbu()) {
                zzemm zzemm6 = this.zzmxz;
                String valueOf5 = String.valueOf(map);
                StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf5).length() + 26);
                sb3.append("Ignoring unknown message: ");
                sb3.append(valueOf5);
                zzemm6.zzb(sb3.toString(), (Throwable) null, new Object[0]);
            }
        }
    }

    public final void zzb(zzeeo zzeeo) {
        boolean z = false;
        if (this.zzmxz.zzcbu()) {
            zzemm zzemm = this.zzmxz;
            String valueOf = String.valueOf(zzeeo.name());
            zzemm.zzb(valueOf.length() != 0 ? "Got on disconnect due to ".concat(valueOf) : new String("Got on disconnect due to "), (Throwable) null, new Object[0]);
        }
        this.zznah = zzefi.Disconnected;
        this.zznag = null;
        this.zznax = false;
        this.zznak.clear();
        zzbwy();
        if (zzbww()) {
            long currentTimeMillis = System.currentTimeMillis();
            long j = this.zznaf;
            long j2 = currentTimeMillis - j;
            if (j > 0 && j2 > NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS) {
                z = true;
            }
            if (zzeeo == zzeeo.SERVER_RESET || z) {
                this.zznar.zzbxr();
            }
            zzbwx();
        }
        this.zznaf = 0;
        this.zznab.onDisconnect();
    }

    public final void zzb(List<String> list, Object obj, zzefo zzefo) {
        this.zznax = true;
        if (zzbwv()) {
            zza("o", list, obj, zzefo);
        } else {
            this.zznal.add(new zzefk("o", list, obj, zzefo, (zzeez) null));
        }
        zzbxa();
    }

    public final void zzb(List<String> list, Map<String, Object> map, zzefo zzefo) {
        this.zznax = true;
        if (zzbwv()) {
            zza("om", list, (Object) map, zzefo);
        } else {
            this.zznal.add(new zzefk("om", list, map, zzefo, (zzeez) null));
        }
        zzbxa();
    }

    public final void zzc(long j, String str) {
        if (this.zzmxz.zzcbu()) {
            this.zzmxz.zzb("onReady", (Throwable) null, new Object[0]);
        }
        this.zznaf = System.currentTimeMillis();
        if (this.zzmxz.zzcbu()) {
            this.zzmxz.zzb("handling timestamp", (Throwable) null, new Object[0]);
        }
        HashMap hashMap = new HashMap();
        hashMap.put("serverTimeOffset", Long.valueOf(j - System.currentTimeMillis()));
        this.zznab.zzak(hashMap);
        if (this.zznae) {
            HashMap hashMap2 = new HashMap();
            if (this.zznaq.isPersistenceEnabled()) {
                hashMap2.put("persistence.android.enabled", 1);
            }
            String valueOf = String.valueOf(this.zznaq.zzbwn().replace('.', '-'));
            hashMap2.put(valueOf.length() != 0 ? "sdk.android.".concat(valueOf) : new String("sdk.android."), 1);
            if (this.zzmxz.zzcbu()) {
                this.zzmxz.zzb("Sending first connection stats", (Throwable) null, new Object[0]);
            }
            if (!hashMap2.isEmpty()) {
                HashMap hashMap3 = new HashMap();
                hashMap3.put(CapsExtension.NODE_NAME, hashMap2);
                zza("s", (Map<String, Object>) hashMap3, (zzefh) new zzeff(this));
            } else if (this.zzmxz.zzcbu()) {
                this.zzmxz.zzb("Not sending stats because stats are empty", (Throwable) null, new Object[0]);
            }
        }
        if (this.zzmxz.zzcbu()) {
            this.zzmxz.zzb("calling restore state", (Throwable) null, new Object[0]);
        }
        zzeet.zzc(this.zznah == zzefi.Connecting, "Wanted to restore auth, but was in wrong state: %s", this.zznah);
        if (this.zznao == null) {
            if (this.zzmxz.zzcbu()) {
                this.zzmxz.zzb("Not restoring auth because token is null.", (Throwable) null, new Object[0]);
            }
            this.zznah = zzefi.Connected;
            zzbwz();
        } else {
            if (this.zzmxz.zzcbu()) {
                this.zzmxz.zzb("Restoring auth.", (Throwable) null, new Object[0]);
            }
            this.zznah = zzefi.Authenticating;
            zzct(true);
        }
        this.zznae = false;
        this.zznas = str;
        this.zznab.zzbwt();
    }

    public final void zzpp(String str) {
        this.zznac = str;
    }

    public final void zzpq(String str) {
        if (this.zzmxz.zzcbu()) {
            zzemm zzemm = this.zzmxz;
            String valueOf = String.valueOf(str);
            zzemm.zzb(valueOf.length() != 0 ? "Firebase Database connection was forcefully killed by the server. Will not attempt reconnect. Reason: ".concat(valueOf) : new String("Firebase Database connection was forcefully killed by the server. Will not attempt reconnect. Reason: "), (Throwable) null, new Object[0]);
        }
        interrupt("server_kill");
    }

    public final void zzpt(String str) {
        this.zzmxz.zzb("Auth token refreshed.", (Throwable) null, new Object[0]);
        this.zznao = str;
        if (!zzbwu()) {
            return;
        }
        if (str != null) {
            zzct(false);
            return;
        }
        zzeet.zzc(zzbwu(), "Must be connected to send unauth.", new Object[0]);
        zzeet.zzc(this.zznao == null, "Auth token must not be set.", new Object[0]);
        zza("unauth", (Map<String, Object>) Collections.<String, Object>emptyMap(), (zzefh) null);
    }

    public final void zzpu(String str) {
        zzeet.zzc(this.zznah == zzefi.GettingToken, "Trying to open network connection while in the wrong state: %s", this.zznah);
        if (str == null) {
            this.zznab.zzcs(false);
        }
        this.zznao = str;
        this.zznah = zzefi.Connecting;
        zzeem zzeem = new zzeem(this.zznaq, this.zzmzk, this.zznac, this, this.zznas);
        this.zznag = zzeem;
        zzeem.open();
    }
}
