package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.zzh;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class zzegx implements zzeex {
    private final zzeia zzmwr;
    /* access modifiers changed from: private */
    public zzeew zzncx;
    /* access modifiers changed from: private */
    public final zzeoz zznex = new zzeoz(new zzeot(), 0);
    /* access modifiers changed from: private */
    public zzeik zzney;
    /* access modifiers changed from: private */
    public zzeil zznez;
    /* access modifiers changed from: private */
    public zzelc<List<zzehy>> zznfa;
    private boolean zznfb = false;
    private final zzelp zznfc;
    private final zzegm zznfd;
    /* access modifiers changed from: private */
    public final zzemm zznfe;
    private final zzemm zznff;
    private final zzemm zznfg;
    private long zznfh = 0;
    private long zznfi = 1;
    /* access modifiers changed from: private */
    public zzeir zznfj;
    /* access modifiers changed from: private */
    public zzeir zznfk;
    private FirebaseDatabase zznfl;
    private boolean zznfm = false;
    private long zznfn = 0;

    zzegx(zzeia zzeia, zzegm zzegm, FirebaseDatabase firebaseDatabase) {
        this.zzmwr = zzeia;
        this.zznfd = zzegm;
        this.zznfl = firebaseDatabase;
        this.zznfe = zzegm.zzqb("RepoOperation");
        this.zznff = zzegm.zzqb("Transaction");
        this.zznfg = zzegm.zzqb("DataOperation");
        this.zznfc = new zzelp(zzegm);
        zzp(new zzegy(this));
    }

    private final zzenn zza(zzegu zzegu, List<Long> list) {
        zzenn zzc = this.zznfk.zzc(zzegu, list);
        return zzc == null ? zzene.zzcco() : zzc;
    }

    /* access modifiers changed from: private */
    public final void zza(long j, zzegu zzegu, DatabaseError databaseError) {
        if (databaseError == null || databaseError.getCode() != -25) {
            List<? extends zzell> zza = this.zznfk.zza(j, !(databaseError == null), true, (zzeos) this.zznex);
            if (zza.size() > 0) {
                zzn(zzegu);
            }
            zzaw(zza);
        }
    }

    /* access modifiers changed from: private */
    public final void zza(zzelc<List<zzehy>> zzelc) {
        if (zzelc.getValue() != null) {
            List<zzehy> zzc = zzc(zzelc);
            Boolean bool = true;
            Iterator<zzehy> it = zzc.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().zzngo != zzehz.zzngx) {
                        bool = false;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (bool.booleanValue()) {
                zzegu zzbvh = zzelc.zzbvh();
                ArrayList arrayList = new ArrayList();
                for (zzehy zzb : zzc) {
                    arrayList.add(Long.valueOf(zzb.zzngs));
                }
                zzenn zza = zza(zzbvh, (List<Long>) arrayList);
                String zzccc = zza.zzccc();
                for (zzehy next : zzc) {
                    int unused = next.zzngo = zzehz.zzngy;
                    zzehy.zzd(next);
                    zza = zza.zzl(zzegu.zza(zzbvh, next.zzmxa), next.zzngu);
                }
                this.zzncx.zza(zzbvh.zzbyp(), zza.getValue(true), zzccc, new zzehf(this, zzbvh, zzc, this));
            }
        } else if (zzelc.hasChildren()) {
            zzelc.zza(new zzehe(this));
        }
    }

    /* access modifiers changed from: private */
    public final void zza(zzelc<List<zzehy>> zzelc, int i) {
        DatabaseError databaseError;
        zzelc<List<zzehy>> zzelc2 = zzelc;
        int i2 = i;
        List value = zzelc.getValue();
        ArrayList arrayList = new ArrayList();
        if (value != null) {
            ArrayList arrayList2 = new ArrayList();
            if (i2 == -9) {
                databaseError = DatabaseError.zzpm("overriddenBySet");
            } else {
                boolean z = i2 == -25;
                StringBuilder sb = new StringBuilder(45);
                sb.append("Unknown transaction abort reason: ");
                sb.append(i2);
                zzepd.zzb(z, sb.toString());
                databaseError = DatabaseError.zzhg(-25);
            }
            int i3 = -1;
            for (int i4 = 0; i4 < value.size(); i4++) {
                zzehy zzehy = (zzehy) value.get(i4);
                if (zzehy.zzngo != zzehz.zznha) {
                    if (zzehy.zzngo == zzehz.zzngy) {
                        int unused = zzehy.zzngo = zzehz.zznha;
                        DatabaseError unused2 = zzehy.zzngr = databaseError;
                        i3 = i4;
                    } else {
                        zze((zzegr) new zzejp(this, zzehy.zzngn, zzelu.zzam(zzehy.zzmxa)));
                        if (i2 == -9) {
                            arrayList.addAll(this.zznfk.zza(zzehy.zzngs, true, false, (zzeos) this.zznex));
                        } else {
                            boolean z2 = i2 == -25;
                            StringBuilder sb2 = new StringBuilder(45);
                            sb2.append("Unknown transaction abort reason: ");
                            sb2.append(i2);
                            zzepd.zzb(z2, sb2.toString());
                        }
                        arrayList2.add(new zzeho(this, zzehy, databaseError));
                    }
                }
            }
            if (i3 == -1) {
                zzelc2.setValue(null);
            } else {
                zzelc2.setValue(value.subList(0, i3 + 1));
            }
            zzaw(arrayList);
            ArrayList arrayList3 = arrayList2;
            int size = arrayList3.size();
            int i5 = 0;
            while (i5 < size) {
                Object obj = arrayList3.get(i5);
                i5++;
                zzn((Runnable) obj);
            }
        }
    }

    private final void zza(zzemq zzemq, Object obj) {
        if (zzemq.equals(zzegl.zznef)) {
            this.zznex.zzbz(((Long) obj).longValue());
        }
        zzegu zzegu = new zzegu(zzegl.zznee, zzemq);
        try {
            zzenn zza = zzenq.zza(obj, zzene.zzcco());
            this.zzney.zzg(zzegu, zza);
            zzaw(this.zznfj.zzi(zzegu, zza));
        } catch (DatabaseException e) {
            this.zznfe.zze("Failed to parse info update", e);
        }
    }

    /* access modifiers changed from: private */
    public final void zza(String str, zzegu zzegu, DatabaseError databaseError) {
        if (databaseError != null && databaseError.getCode() != -1 && databaseError.getCode() != -25) {
            zzemm zzemm = this.zznfe;
            String zzegu2 = zzegu.toString();
            String databaseError2 = databaseError.toString();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 13 + String.valueOf(zzegu2).length() + String.valueOf(databaseError2).length());
            sb.append(str);
            sb.append(" at ");
            sb.append(zzegu2);
            sb.append(" failed: ");
            sb.append(databaseError2);
            zzemm.zzf(sb.toString(), (Throwable) null);
        }
    }

    /* access modifiers changed from: private */
    public final void zza(List<zzehy> list, zzelc<List<zzehy>> zzelc) {
        List value = zzelc.getValue();
        if (value != null) {
            list.addAll(value);
        }
        zzelc.zza(new zzehl(this, list));
    }

    /* access modifiers changed from: private */
    public final void zzaw(List<? extends zzell> list) {
        if (!list.isEmpty()) {
            this.zznfc.zzay(list);
        }
    }

    /* access modifiers changed from: private */
    public final zzegu zzb(zzegu zzegu, int i) {
        zzegu zzbvh = zzo(zzegu).zzbvh();
        if (this.zznff.zzcbu()) {
            zzemm zzemm = this.zznfe;
            String valueOf = String.valueOf(zzegu);
            String valueOf2 = String.valueOf(zzbvh);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 44 + String.valueOf(valueOf2).length());
            sb.append("Aborting transactions for path: ");
            sb.append(valueOf);
            sb.append(". Affected: ");
            sb.append(valueOf2);
            zzemm.zzb(sb.toString(), (Throwable) null, new Object[0]);
        }
        zzelc<List<zzehy>> zzak = this.zznfa.zzak(zzegu);
        zzak.zza(new zzehm(this, i), false);
        zza(zzak, i);
        zzak.zza(new zzehn(this, i), false, false);
        return zzbvh;
    }

    /* access modifiers changed from: private */
    public final void zzb(zzelc<List<zzehy>> zzelc) {
        List value = zzelc.getValue();
        if (value != null) {
            int i = 0;
            while (i < value.size()) {
                if (((zzehy) value.get(i)).zzngo == zzehz.zzngz) {
                    value.remove(i);
                } else {
                    i++;
                }
            }
            if (value.size() <= 0) {
                value = null;
            }
            zzelc.setValue(value);
        }
        zzelc.zza(new zzehh(this));
    }

    /* access modifiers changed from: private */
    public static DatabaseError zzbd(String str, String str2) {
        if (str != null) {
            return DatabaseError.zzbb(str, str2);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public final void zzbyu() {
        this.zzncx = this.zznfd.zza(new zzeeu(this.zzmwr.host, this.zzmwr.zzkal, this.zzmwr.secure), this);
        this.zznfd.zznej.zza(new zzehk(this));
        this.zzncx.initialize();
        zzeki zzqc = this.zznfd.zzqc(this.zzmwr.host);
        this.zzney = new zzeik();
        this.zznez = new zzeil();
        this.zznfa = new zzelc<>();
        this.zznfj = new zzeir(this.zznfd, new zzekh(), new zzehp(this));
        this.zznfk = new zzeir(this.zznfd, zzqc, new zzehr(this));
        List<zzejn> zzbvk = zzqc.zzbvk();
        Map<String, Object> zza = zzeih.zza(this.zznex);
        long j = Long.MIN_VALUE;
        for (zzejn next : zzbvk) {
            zzeht zzeht = new zzeht(this, next);
            if (j < next.zzbzh()) {
                j = next.zzbzh();
                this.zznfi = next.zzbzh() + 1;
                if (next.zzbzk()) {
                    if (this.zznfe.zzcbu()) {
                        zzemm zzemm = this.zznfe;
                        long zzbzh = next.zzbzh();
                        StringBuilder sb = new StringBuilder(48);
                        sb.append("Restoring overwrite with id ");
                        sb.append(zzbzh);
                        zzemm.zzb(sb.toString(), (Throwable) null, new Object[0]);
                    }
                    this.zzncx.zza(next.zzbvh().zzbyp(), next.zzbzi().getValue(true), (zzefo) zzeht);
                    this.zznfk.zza(next.zzbvh(), next.zzbzi(), zzeih.zza(next.zzbzi(), zza), next.zzbzh(), true, false);
                } else {
                    if (this.zznfe.zzcbu()) {
                        zzemm zzemm2 = this.zznfe;
                        long zzbzh2 = next.zzbzh();
                        StringBuilder sb2 = new StringBuilder(44);
                        sb2.append("Restoring merge with id ");
                        sb2.append(zzbzh2);
                        zzemm2.zzb(sb2.toString(), (Throwable) null, new Object[0]);
                    }
                    this.zzncx.zza(next.zzbvh().zzbyp(), next.zzbzj().zzcu(true), (zzefo) zzeht);
                    this.zznfk.zza(next.zzbvh(), next.zzbzj(), zzeih.zza(next.zzbzj(), zza), next.zzbzh(), false);
                }
            } else {
                throw new IllegalStateException("Write ids were not in order.");
            }
        }
        zza(zzegl.zzneg, (Object) false);
        zza(zzegl.zzneh, (Object) false);
    }

    private final long zzbyy() {
        long j = this.zznfi;
        this.zznfi = 1 + j;
        return j;
    }

    /* access modifiers changed from: private */
    public final void zzbyz() {
        zzelc<List<zzehy>> zzelc = this.zznfa;
        zzb(zzelc);
        zza(zzelc);
    }

    private final List<zzehy> zzc(zzelc<List<zzehy>> zzelc) {
        ArrayList arrayList = new ArrayList();
        zza((List<zzehy>) arrayList, zzelc);
        Collections.sort(arrayList);
        return arrayList;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x006c, code lost:
        if (r10.getCode() != -25) goto L_0x0088;
     */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0137  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x003e A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzegu zzn(com.google.android.gms.internal.zzegu r22) {
        /*
            r21 = this;
            r1 = r21
            com.google.android.gms.internal.zzelc r0 = r21.zzo(r22)
            com.google.android.gms.internal.zzegu r2 = r0.zzbvh()
            java.util.List r0 = r1.zzc((com.google.android.gms.internal.zzelc<java.util.List<com.google.android.gms.internal.zzehy>>) r0)
            boolean r3 = r0.isEmpty()
            if (r3 != 0) goto L_0x017c
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.Iterator r5 = r0.iterator()
        L_0x0022:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x003a
            java.lang.Object r6 = r5.next()
            com.google.android.gms.internal.zzehy r6 = (com.google.android.gms.internal.zzehy) r6
            long r6 = r6.zzngs
            java.lang.Long r6 = java.lang.Long.valueOf(r6)
            r4.add(r6)
            goto L_0x0022
        L_0x003a:
            java.util.Iterator r5 = r0.iterator()
        L_0x003e:
            boolean r0 = r5.hasNext()
            r6 = 0
            if (r0 == 0) goto L_0x0162
            java.lang.Object r0 = r5.next()
            r7 = r0
            com.google.android.gms.internal.zzehy r7 = (com.google.android.gms.internal.zzehy) r7
            com.google.android.gms.internal.zzegu r0 = r7.zzmxa
            com.google.android.gms.internal.zzegu.zza(r2, r0)
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            int r0 = r7.zzngo
            int r9 = com.google.android.gms.internal.zzehz.zznhb
            r10 = 0
            r11 = 1
            if (r0 != r9) goto L_0x0072
            com.google.firebase.database.DatabaseError r10 = r7.zzngr
            int r0 = r10.getCode()
            r6 = -25
            if (r0 == r6) goto L_0x006f
            goto L_0x0088
        L_0x006f:
            r6 = 1
            goto L_0x0132
        L_0x0072:
            int r0 = r7.zzngo
            int r9 = com.google.android.gms.internal.zzehz.zzngx
            if (r0 != r9) goto L_0x0132
            int r0 = r7.retryCount
            r9 = 25
            if (r0 < r9) goto L_0x009d
            java.lang.String r0 = "maxretries"
            com.google.firebase.database.DatabaseError r10 = com.google.firebase.database.DatabaseError.zzpm(r0)
        L_0x0088:
            com.google.android.gms.internal.zzeir r12 = r1.zznfk
            long r13 = r7.zzngs
            r15 = 1
            r16 = 0
            com.google.android.gms.internal.zzeoz r0 = r1.zznex
            r17 = r0
            java.util.List r0 = r12.zza((long) r13, (boolean) r15, (boolean) r16, (com.google.android.gms.internal.zzeos) r17)
            r8.addAll(r0)
            goto L_0x006f
        L_0x009d:
            com.google.android.gms.internal.zzegu r0 = r7.zzmxa
            com.google.android.gms.internal.zzenn r0 = r1.zza((com.google.android.gms.internal.zzegu) r0, (java.util.List<java.lang.Long>) r4)
            com.google.android.gms.internal.zzenn unused = r7.zzngt = r0
            com.google.firebase.database.MutableData r0 = com.google.firebase.database.zzh.zza(r0)
            com.google.firebase.database.Transaction$Handler r9 = r7.zzngm     // Catch:{ all -> 0x00b6 }
            com.google.firebase.database.Transaction$Result r0 = r9.doTransaction(r0)     // Catch:{ all -> 0x00b6 }
            r9 = r10
            goto L_0x00c4
        L_0x00b6:
            r0 = move-exception
            com.google.firebase.database.DatabaseError r0 = com.google.firebase.database.DatabaseError.fromException(r0)
            com.google.firebase.database.Transaction$Result r9 = com.google.firebase.database.Transaction.abort()
            r20 = r9
            r9 = r0
            r0 = r20
        L_0x00c4:
            boolean r12 = r0.isSuccess()
            if (r12 == 0) goto L_0x011b
            long r11 = r7.zzngs
            java.lang.Long r9 = java.lang.Long.valueOf(r11)
            com.google.android.gms.internal.zzeoz r11 = r1.zznex
            java.util.Map r11 = com.google.android.gms.internal.zzeih.zza(r11)
            com.google.android.gms.internal.zzenn r14 = r0.zzbve()
            com.google.android.gms.internal.zzenn r15 = com.google.android.gms.internal.zzeih.zza((com.google.android.gms.internal.zzenn) r14, (java.util.Map<java.lang.String, java.lang.Object>) r11)
            com.google.android.gms.internal.zzenn unused = r7.zzngu = r14
            com.google.android.gms.internal.zzenn unused = r7.zzngv = r15
            long r11 = r21.zzbyy()
            long unused = r7.zzngs = r11
            r4.remove(r9)
            com.google.android.gms.internal.zzeir r12 = r1.zznfk
            com.google.android.gms.internal.zzegu r13 = r7.zzmxa
            long r16 = r7.zzngs
            boolean r18 = r7.zzngq
            r19 = 0
            java.util.List r0 = r12.zza(r13, r14, r15, r16, r18, r19)
            r8.addAll(r0)
            com.google.android.gms.internal.zzeir r11 = r1.zznfk
            long r12 = r9.longValue()
            r14 = 1
            r15 = 0
            com.google.android.gms.internal.zzeoz r0 = r1.zznex
            r16 = r0
            java.util.List r0 = r11.zza((long) r12, (boolean) r14, (boolean) r15, (com.google.android.gms.internal.zzeos) r16)
            r8.addAll(r0)
            goto L_0x0132
        L_0x011b:
            com.google.android.gms.internal.zzeir r12 = r1.zznfk
            long r13 = r7.zzngs
            r15 = 1
            r16 = 0
            com.google.android.gms.internal.zzeoz r0 = r1.zznex
            r17 = r0
            java.util.List r0 = r12.zza((long) r13, (boolean) r15, (boolean) r16, (com.google.android.gms.internal.zzeos) r17)
            r8.addAll(r0)
            r10 = r9
            goto L_0x006f
        L_0x0132:
            r1.zzaw(r8)
            if (r6 == 0) goto L_0x003e
            int r0 = com.google.android.gms.internal.zzehz.zzngz
            int unused = r7.zzngo = r0
            com.google.android.gms.internal.zzegu r0 = r7.zzmxa
            com.google.firebase.database.DatabaseReference r0 = com.google.firebase.database.zzh.zza((com.google.android.gms.internal.zzegx) r1, (com.google.android.gms.internal.zzegu) r0)
            com.google.android.gms.internal.zzenn r6 = r7.zzngt
            com.google.android.gms.internal.zzeng r6 = com.google.android.gms.internal.zzeng.zzj(r6)
            com.google.firebase.database.DataSnapshot r0 = com.google.firebase.database.zzh.zza((com.google.firebase.database.DatabaseReference) r0, (com.google.android.gms.internal.zzeng) r6)
            com.google.android.gms.internal.zzehi r6 = new com.google.android.gms.internal.zzehi
            r6.<init>(r1, r7)
            r1.zzp(r6)
            com.google.android.gms.internal.zzehj r6 = new com.google.android.gms.internal.zzehj
            r6.<init>(r1, r7, r10, r0)
            r3.add(r6)
            goto L_0x003e
        L_0x0162:
            com.google.android.gms.internal.zzelc<java.util.List<com.google.android.gms.internal.zzehy>> r0 = r1.zznfa
            r1.zzb((com.google.android.gms.internal.zzelc<java.util.List<com.google.android.gms.internal.zzehy>>) r0)
        L_0x0167:
            int r0 = r3.size()
            if (r6 >= r0) goto L_0x0179
            java.lang.Object r0 = r3.get(r6)
            java.lang.Runnable r0 = (java.lang.Runnable) r0
            r1.zzn((java.lang.Runnable) r0)
            int r6 = r6 + 1
            goto L_0x0167
        L_0x0179:
            r21.zzbyz()
        L_0x017c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzegx.zzn(com.google.android.gms.internal.zzegu):com.google.android.gms.internal.zzegu");
    }

    private final zzelc<List<zzehy>> zzo(zzegu zzegu) {
        zzelc<List<zzehy>> zzelc = this.zznfa;
        while (!zzegu.isEmpty() && zzelc.getValue() == null) {
            zzelc = zzelc.zzak(new zzegu(zzegu.zzbyq()));
            zzegu = zzegu.zzbyr();
        }
        return zzelc;
    }

    public final FirebaseDatabase getDatabase() {
        return this.zznfl;
    }

    /* access modifiers changed from: package-private */
    public final void interrupt() {
        this.zzncx.interrupt("repo_interrupt");
    }

    public final void onDisconnect() {
        zza(zzegl.zzneh, (Object) false);
        Map<String, Object> zza = zzeih.zza(this.zznex);
        zzeil zzeil = this.zznez;
        zzeil zzeil2 = new zzeil();
        zzeil.zza(new zzegu(""), new zzeii(zzeil2, zza));
        ArrayList arrayList = new ArrayList();
        zzeil2.zza(zzegu.zzbyn(), new zzehb(this, arrayList));
        this.zznez = new zzeil();
        zzaw(arrayList);
    }

    public final void purgeOutstandingWrites() {
        if (this.zznfe.zzcbu()) {
            this.zznfe.zzb("Purging writes", (Throwable) null, new Object[0]);
        }
        zzaw(this.zznfk.zzbze());
        zzb(zzegu.zzbyn(), -25);
        this.zzncx.purgeOutstandingWrites();
    }

    /* access modifiers changed from: package-private */
    public final void resume() {
        this.zzncx.resume("repo_interrupt");
    }

    public final String toString() {
        return this.zzmwr.toString();
    }

    public final void zza(zzegu zzegu, zzegi zzegi, DatabaseReference.CompletionListener completionListener, Map<String, Object> map) {
        if (this.zznfe.zzcbu()) {
            zzemm zzemm = this.zznfe;
            String valueOf = String.valueOf(zzegu);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 8);
            sb.append("update: ");
            sb.append(valueOf);
            zzemm.zzb(sb.toString(), (Throwable) null, new Object[0]);
        }
        if (this.zznfg.zzcbu()) {
            zzemm zzemm2 = this.zznfg;
            String valueOf2 = String.valueOf(zzegu);
            String valueOf3 = String.valueOf(map);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 9 + String.valueOf(valueOf3).length());
            sb2.append("update: ");
            sb2.append(valueOf2);
            sb2.append(" ");
            sb2.append(valueOf3);
            zzemm2.zzb(sb2.toString(), (Throwable) null, new Object[0]);
        }
        if (zzegi.isEmpty()) {
            if (this.zznfe.zzcbu()) {
                this.zznfe.zzb("update called with no changes. No-op", (Throwable) null, new Object[0]);
            }
            zza(completionListener, (DatabaseError) null, zzegu);
            return;
        }
        zzegi zza = zzeih.zza(zzegi, zzeih.zza(this.zznex));
        long zzbyy = zzbyy();
        zzaw(this.zznfk.zza(zzegu, zzegi, zza, zzbyy, true));
        this.zzncx.zza(zzegu.zzbyp(), map, (zzefo) new zzehw(this, zzegu, zzbyy, completionListener));
        Iterator<Map.Entry<zzegu, zzenn>> it = zzegi.iterator();
        while (it.hasNext()) {
            zzn(zzb(zzegu.zzh((zzegu) it.next().getKey()), -9));
        }
    }

    public final void zza(zzegu zzegu, zzenn zzenn, DatabaseReference.CompletionListener completionListener) {
        if (this.zznfe.zzcbu()) {
            zzemm zzemm = this.zznfe;
            String valueOf = String.valueOf(zzegu);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 5);
            sb.append("set: ");
            sb.append(valueOf);
            zzemm.zzb(sb.toString(), (Throwable) null, new Object[0]);
        }
        if (this.zznfg.zzcbu()) {
            zzemm zzemm2 = this.zznfg;
            String valueOf2 = String.valueOf(zzegu);
            String valueOf3 = String.valueOf(zzenn);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 6 + String.valueOf(valueOf3).length());
            sb2.append("set: ");
            sb2.append(valueOf2);
            sb2.append(" ");
            sb2.append(valueOf3);
            zzemm2.zzb(sb2.toString(), (Throwable) null, new Object[0]);
        }
        zzenn zza = zzeih.zza(zzenn, zzeih.zza(this.zznex));
        long zzbyy = zzbyy();
        zzaw(this.zznfk.zza(zzegu, zzenn, zza, zzbyy, true, true));
        this.zzncx.zza(zzegu.zzbyp(), zzenn.getValue(true), (zzefo) new zzehv(this, zzegu, zzbyy, completionListener));
        zzn(zzb(zzegu, -9));
    }

    public final void zza(zzegu zzegu, DatabaseReference.CompletionListener completionListener) {
        this.zzncx.zza(zzegu.zzbyp(), (zzefo) new zzeha(this, zzegu, completionListener));
    }

    public final void zza(zzegu zzegu, Transaction.Handler handler, boolean z) {
        DatabaseError databaseError;
        Transaction.Result result;
        zzegu zzegu2 = zzegu;
        Transaction.Handler handler2 = handler;
        if (this.zznfe.zzcbu()) {
            zzemm zzemm = this.zznfe;
            String valueOf = String.valueOf(zzegu);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 13);
            sb.append("transaction: ");
            sb.append(valueOf);
            zzemm.zzb(sb.toString(), (Throwable) null, new Object[0]);
        }
        if (this.zznfg.zzcbu()) {
            zzemm zzemm2 = this.zznfe;
            String valueOf2 = String.valueOf(zzegu);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 13);
            sb2.append("transaction: ");
            sb2.append(valueOf2);
            zzemm2.zzb(sb2.toString(), (Throwable) null, new Object[0]);
        }
        if (this.zznfd.zzmzx && !this.zznfm) {
            this.zznfm = true;
            this.zznff.info("runTransaction() usage detected while persistence is enabled. Please be aware that transactions *will not* be persisted across database restarts.  See https://www.firebase.com/docs/android/guide/offline-capabilities.html#section-handling-transactions-offline for more details.");
        }
        DatabaseReference zza = zzh.zza(this, zzegu);
        zzehc zzehc = new zzehc(this);
        zzf((zzegr) new zzejp(this, zzehc, zza.zzbvi()));
        int i = zzehz.zzngw;
        long j = this.zznfn;
        this.zznfn = 1 + j;
        zzehy zzehy = new zzehy(zzegu, handler, zzehc, i, z, j, (zzegy) null);
        zzenn zza2 = zza(zzegu2, (List<Long>) new ArrayList());
        zzenn unused = zzehy.zzngt = zza2;
        try {
            result = handler2.doTransaction(zzh.zza(zza2));
            if (result != null) {
                databaseError = null;
                if (!result.isSuccess()) {
                    zzenn unused2 = zzehy.zzngu = null;
                    zzenn unused3 = zzehy.zzngv = null;
                    zzn((Runnable) new zzehd(this, handler2, databaseError, zzh.zza(zza, zzeng.zzj(zzehy.zzngt))));
                    return;
                }
                int unused4 = zzehy.zzngo = zzehz.zzngx;
                zzelc<List<zzehy>> zzak = this.zznfa.zzak(zzegu2);
                List value = zzak.getValue();
                if (value == null) {
                    value = new ArrayList();
                }
                value.add(zzehy);
                zzak.setValue(value);
                Map<String, Object> zza3 = zzeih.zza(this.zznex);
                zzenn zzbve = result.zzbve();
                zzenn zza4 = zzeih.zza(zzbve, zza3);
                zzenn unused5 = zzehy.zzngu = zzbve;
                zzenn unused6 = zzehy.zzngv = zza4;
                long unused7 = zzehy.zzngs = zzbyy();
                zzaw(this.zznfk.zza(zzegu, zzbve, zza4, zzehy.zzngs, z, false));
                zzbyz();
                return;
            }
            throw new NullPointerException("Transaction returned null as result");
        } catch (Throwable th) {
            databaseError = DatabaseError.fromException(th);
            result = Transaction.abort();
        }
    }

    public final void zza(zzegu zzegu, Map<zzegu, zzenn> map, DatabaseReference.CompletionListener completionListener, Map<String, Object> map2) {
        this.zzncx.zzb(zzegu.zzbyp(), map2, (zzefo) new zzegz(this, zzegu, map, completionListener));
    }

    public final void zza(zzelu zzelu, boolean z) {
        this.zznfk.zza(zzelu, z);
    }

    /* access modifiers changed from: package-private */
    public final void zza(DatabaseReference.CompletionListener completionListener, DatabaseError databaseError, zzegu zzegu) {
        if (completionListener != null) {
            zzemq zzbyt = zzegu.zzbyt();
            if (zzbyt != null && zzbyt.zzcca()) {
                zzegu = zzegu.zzbys();
            }
            zzn((Runnable) new zzehu(this, completionListener, databaseError, zzh.zza(this, zzegu)));
        }
    }

    public final void zza(List<String> list, Object obj, boolean z, Long l) {
        List<? extends zzell> list2;
        zzegu zzegu = new zzegu(list);
        if (this.zznfe.zzcbu()) {
            zzemm zzemm = this.zznfe;
            String valueOf = String.valueOf(zzegu);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 14);
            sb.append("onDataUpdate: ");
            sb.append(valueOf);
            zzemm.zzb(sb.toString(), (Throwable) null, new Object[0]);
        }
        if (this.zznfg.zzcbu()) {
            zzemm zzemm2 = this.zznfe;
            String valueOf2 = String.valueOf(zzegu);
            String valueOf3 = String.valueOf(obj);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 15 + String.valueOf(valueOf3).length());
            sb2.append("onDataUpdate: ");
            sb2.append(valueOf2);
            sb2.append(" ");
            sb2.append(valueOf3);
            zzemm2.zzb(sb2.toString(), (Throwable) null, new Object[0]);
        }
        this.zznfh++;
        if (l != null) {
            try {
                zzejk zzejk = new zzejk(l.longValue());
                if (z) {
                    HashMap<zzegu, zzenn> hashMap = new HashMap<>();
                    for (Map.Entry<String, Object> entry : ((Map<String, Object>) obj).entrySet()) {
                        hashMap.put(new zzegu(entry.getKey()), zzenq.zza(entry.getValue(), zzene.zzcco()));
                    }
                    list2 = this.zznfk.zza(zzegu, (Map<zzegu, zzenn>) hashMap, zzejk);
                } else {
                    list2 = this.zznfk.zza(zzegu, zzenq.zza(obj, zzene.zzcco()), zzejk);
                }
            } catch (DatabaseException e) {
                this.zznfe.zze("FIREBASE INTERNAL ERROR", e);
                return;
            }
        } else if (z) {
            HashMap<zzegu, zzenn> hashMap2 = new HashMap<>();
            for (Map.Entry<String, Object> entry2 : ((Map<String, Object>) obj).entrySet()) {
                hashMap2.put(new zzegu(entry2.getKey()), zzenq.zza(entry2.getValue(), zzene.zzcco()));
            }
            list2 = this.zznfk.zza(zzegu, (Map<zzegu, zzenn>) hashMap2);
        } else {
            list2 = this.zznfk.zzi(zzegu, zzenq.zza(obj, zzene.zzcco()));
        }
        if (list2.size() > 0) {
            zzn(zzegu);
        }
        zzaw(list2);
    }

    public final void zza(List<String> list, List<zzefn> list2, Long l) {
        zzegu zzegu = new zzegu(list);
        if (this.zznfe.zzcbu()) {
            zzemm zzemm = this.zznfe;
            String valueOf = String.valueOf(zzegu);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 20);
            sb.append("onRangeMergeUpdate: ");
            sb.append(valueOf);
            zzemm.zzb(sb.toString(), (Throwable) null, new Object[0]);
        }
        if (this.zznfg.zzcbu()) {
            zzemm zzemm2 = this.zznfe;
            String valueOf2 = String.valueOf(zzegu);
            String valueOf3 = String.valueOf(list2);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 21 + String.valueOf(valueOf3).length());
            sb2.append("onRangeMergeUpdate: ");
            sb2.append(valueOf2);
            sb2.append(" ");
            sb2.append(valueOf3);
            zzemm2.zzb(sb2.toString(), (Throwable) null, new Object[0]);
        }
        this.zznfh++;
        ArrayList arrayList = new ArrayList(list2.size());
        for (zzefn zzenu : list2) {
            arrayList.add(new zzenu(zzenu));
        }
        zzeir zzeir = this.zznfk;
        List<? extends zzell> zza = l != null ? zzeir.zza(zzegu, (List<zzenu>) arrayList, new zzejk(l.longValue())) : zzeir.zzb(zzegu, (List<zzenu>) arrayList);
        if (zza.size() > 0) {
            zzn(zzegu);
        }
        zzaw(zza);
    }

    public final void zzak(Map<String, Object> map) {
        for (Map.Entry<String, Object> next : map.entrySet()) {
            zza(zzemq.zzqf(next.getKey()), next.getValue());
        }
    }

    public final void zzb(zzegu zzegu, zzenn zzenn, DatabaseReference.CompletionListener completionListener) {
        this.zzncx.zzb(zzegu.zzbyp(), zzenn.getValue(true), (zzefo) new zzehx(this, zzegu, zzenn, completionListener));
    }

    public final void zzbwt() {
        zza(zzegl.zzneh, (Object) true);
    }

    public final zzeia zzbyv() {
        return this.zzmwr;
    }

    public final long zzbyw() {
        return this.zznex.millis();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzbyx() {
        return !this.zznfj.isEmpty() || !this.zznfk.isEmpty();
    }

    public final void zzcs(boolean z) {
        zza(zzegl.zzneg, (Object) Boolean.valueOf(z));
    }

    public final void zze(zzegr zzegr) {
        zzaw((zzegl.zznee.equals(zzegr.zzbxy().zzbvh().zzbyq()) ? this.zznfj : this.zznfk).zzh(zzegr));
    }

    public final void zzf(zzegr zzegr) {
        zzemq zzbyq = zzegr.zzbxy().zzbvh().zzbyq();
        zzaw(((zzbyq == null || !zzbyq.equals(zzegl.zznee)) ? this.zznfk : this.zznfj).zzg(zzegr));
    }

    public final void zzn(Runnable runnable) {
        this.zznfd.zzbyf();
        this.zznfd.zznei.zzn(runnable);
    }

    public final void zzp(Runnable runnable) {
        this.zznfd.zzbyf();
        this.zznfd.zznek.zzp(runnable);
    }
}
