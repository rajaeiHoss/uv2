package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.security.NetworkSecurityPolicy;
import com.google.android.gms.ads.internal.zzbt;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzabh
public final class zzahy {
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    /* access modifiers changed from: private */
    public SharedPreferences zzbkx;
    /* access modifiers changed from: private */
    public boolean zzcxd = true;
    /* access modifiers changed from: private */
    public boolean zzcxe = true;
    /* access modifiers changed from: private */
    public boolean zzcxf = true;
    /* access modifiers changed from: private */
    public boolean zzcxm = false;
    /* access modifiers changed from: private */
    public String zzddm = "";
    /* access modifiers changed from: private */
    public int zzdef = -1;
    private zzalt<?> zzdet;
    /* access modifiers changed from: private */
    public CopyOnWriteArraySet<zzaic> zzdeu = new CopyOnWriteArraySet<>();
    SharedPreferences.Editor zzdev;
    /* access modifiers changed from: private */
    public boolean zzdew = false;
    /* access modifiers changed from: private */
    public String zzdex;
    /* access modifiers changed from: private */
    public String zzdey;
    /* access modifiers changed from: private */
    public long zzdez = 0;
    /* access modifiers changed from: private */
    public long zzdfa = 0;
    /* access modifiers changed from: private */
    public long zzdfb = 0;
    /* access modifiers changed from: private */
    public int zzdfc = 0;
    /* access modifiers changed from: private */
    public Set<String> zzdfd = Collections.emptySet();
    /* access modifiers changed from: private */
    public JSONObject zzdfe = new JSONObject();

    /* access modifiers changed from: private */
    public final void zzc(Bundle bundle) {
        new zzaia(this, bundle).zzns();
    }

    /* access modifiers changed from: private */
    public static boolean zzql() {
        return Build.VERSION.SDK_INT >= 23 && !NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted();
    }

    private final void zzqm() {
        zzalt<?> zzalt = this.zzdet;
        if (zzalt != null && !zzalt.isDone()) {
            try {
                this.zzdet.get(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                zzahw.zzc("Interrupted while waiting for preferences loaded.", e);
            } catch (CancellationException | ExecutionException | TimeoutException e2) {
                zzahw.zzb("Fail to initialize AdSharedPreferenceManager.", e2);
            }
        }
    }

    /* access modifiers changed from: private */
    public final Bundle zzqn() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("listener_registration_bundle", true);
        synchronized (this.mLock) {
            bundle.putBoolean("use_https", this.zzcxd);
            bundle.putBoolean("content_url_opted_out", this.zzcxe);
            bundle.putBoolean("content_vertical_opted_out", this.zzcxf);
            bundle.putBoolean("auto_collect_location", this.zzcxm);
            bundle.putInt("version_code", this.zzdfc);
            Set<String> set = this.zzdfd;
            bundle.putStringArray("never_pool_slots", (String[]) set.toArray(new String[set.size()]));
            bundle.putString("app_settings_json", this.zzddm);
            bundle.putLong("app_settings_last_update_ms", this.zzdez);
            bundle.putLong("app_last_background_time_ms", this.zzdfa);
            bundle.putInt("request_in_session_count", this.zzdef);
            bundle.putLong("first_ad_req_time_ms", this.zzdfb);
            bundle.putString("native_advanced_settings", this.zzdfe.toString());
            String str = this.zzdex;
            if (str != null) {
                bundle.putString("content_url_hashes", str);
            }
            String str2 = this.zzdey;
            if (str2 != null) {
                bundle.putString("content_vertical_hashes", str2);
            }
        }
        return bundle;
    }

    public final void initialize(Context context) {
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        this.zzdet = (zzalt) new zzahz(this, context).zzns();
    }

    public final void zza(zzaic zzaic) {
        synchronized (this.mLock) {
            zzalt<?> zzalt = this.zzdet;
            if (zzalt != null && zzalt.isDone()) {
                zzaic.zzb(zzqn());
            }
            this.zzdeu.add(zzaic);
        }
    }

    public final void zza(String str, String str2, boolean z) {
        zzqm();
        synchronized (this.mLock) {
            JSONArray optJSONArray = this.zzdfe.optJSONArray(str);
            if (optJSONArray == null) {
                optJSONArray = new JSONArray();
            }
            int length = optJSONArray.length();
            int i = 0;
            while (true) {
                if (i < optJSONArray.length()) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        if (!str2.equals(optJSONObject.optString("template_id"))) {
                            i++;
                        } else if (!z || optJSONObject.optBoolean("uses_media_view", false) != z) {
                            length = i;
                            break;
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                } else {
                    break;
                }
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("template_id", str2);
                jSONObject.put("uses_media_view", z);
                jSONObject.put("timestamp_ms", zzbt.zzes().currentTimeMillis());
                optJSONArray.put(length, jSONObject);
                this.zzdfe.put(str, optJSONArray);
            } catch (JSONException e) {
                zzahw.zzc("Could not update native advanced settings", e);
            }
            SharedPreferences.Editor editor = this.zzdev;
            if (editor != null) {
                editor.putString("native_advanced_settings", this.zzdfe.toString());
                this.zzdev.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putString("native_advanced_settings", this.zzdfe.toString());
            zzc(bundle);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzaa(boolean r4) {
        /*
            r3 = this;
            r3.zzqm()
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            boolean r1 = r3.zzcxd     // Catch:{ all -> 0x002f }
            if (r1 != r4) goto L_0x000c
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            return
        L_0x000c:
            r3.zzcxd = r4     // Catch:{ all -> 0x002f }
            android.content.SharedPreferences$Editor r1 = r3.zzdev     // Catch:{ all -> 0x002f }
            if (r1 == 0) goto L_0x001c
            java.lang.String r2 = "use_https"
            r1.putBoolean(r2, r4)     // Catch:{ all -> 0x002f }
            android.content.SharedPreferences$Editor r1 = r3.zzdev     // Catch:{ all -> 0x002f }
            r1.apply()     // Catch:{ all -> 0x002f }
        L_0x001c:
            boolean r1 = r3.zzdew     // Catch:{ all -> 0x002f }
            if (r1 != 0) goto L_0x002d
            android.os.Bundle r1 = new android.os.Bundle     // Catch:{ all -> 0x002f }
            r1.<init>()     // Catch:{ all -> 0x002f }
            java.lang.String r2 = "use_https"
            r1.putBoolean(r2, r4)     // Catch:{ all -> 0x002f }
            r3.zzc((android.os.Bundle) r1)     // Catch:{ all -> 0x002f }
        L_0x002d:
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            return
        L_0x002f:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzahy.zzaa(boolean):void");
    }

    public final void zzab(boolean z) {
        zzqm();
        synchronized (this.mLock) {
            if (this.zzcxe != z) {
                this.zzcxe = z;
                SharedPreferences.Editor editor = this.zzdev;
                if (editor != null) {
                    editor.putBoolean("content_url_opted_out", z);
                    this.zzdev.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putBoolean("content_url_opted_out", this.zzcxe);
                bundle.putBoolean("content_vertical_opted_out", this.zzcxf);
                zzc(bundle);
            }
        }
    }

    public final void zzac(int i) {
        zzqm();
        synchronized (this.mLock) {
            if (this.zzdfc != i) {
                this.zzdfc = i;
                SharedPreferences.Editor editor = this.zzdev;
                if (editor != null) {
                    editor.putInt("version_code", i);
                    this.zzdev.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putInt("version_code", i);
                zzc(bundle);
            }
        }
    }

    public final void zzac(boolean z) {
        zzqm();
        synchronized (this.mLock) {
            if (this.zzcxf != z) {
                this.zzcxf = z;
                SharedPreferences.Editor editor = this.zzdev;
                if (editor != null) {
                    editor.putBoolean("content_vertical_opted_out", z);
                    this.zzdev.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putBoolean("content_url_opted_out", this.zzcxe);
                bundle.putBoolean("content_vertical_opted_out", this.zzcxf);
                zzc(bundle);
            }
        }
    }

    public final void zzad(int i) {
        zzqm();
        synchronized (this.mLock) {
            if (this.zzdef != i) {
                this.zzdef = i;
                SharedPreferences.Editor editor = this.zzdev;
                if (editor != null) {
                    editor.putInt("request_in_session_count", i);
                    this.zzdev.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putInt("request_in_session_count", i);
                zzc(bundle);
            }
        }
    }

    public final void zzad(boolean z) {
        zzqm();
        synchronized (this.mLock) {
            if (this.zzcxm != z) {
                this.zzcxm = z;
                SharedPreferences.Editor editor = this.zzdev;
                if (editor != null) {
                    editor.putBoolean("auto_collect_location", z);
                    this.zzdev.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putBoolean("auto_collect_location", z);
                zzc(bundle);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0031, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzcb(java.lang.String r4) {
        /*
            r3 = this;
            r3.zzqm()
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            if (r4 == 0) goto L_0x0030
            java.lang.String r1 = r3.zzdex     // Catch:{ all -> 0x0032 }
            boolean r1 = r4.equals(r1)     // Catch:{ all -> 0x0032 }
            if (r1 == 0) goto L_0x0011
            goto L_0x0030
        L_0x0011:
            r3.zzdex = r4     // Catch:{ all -> 0x0032 }
            android.content.SharedPreferences$Editor r1 = r3.zzdev     // Catch:{ all -> 0x0032 }
            if (r1 == 0) goto L_0x0021
            java.lang.String r2 = "content_url_hashes"
            r1.putString(r2, r4)     // Catch:{ all -> 0x0032 }
            android.content.SharedPreferences$Editor r1 = r3.zzdev     // Catch:{ all -> 0x0032 }
            r1.apply()     // Catch:{ all -> 0x0032 }
        L_0x0021:
            android.os.Bundle r1 = new android.os.Bundle     // Catch:{ all -> 0x0032 }
            r1.<init>()     // Catch:{ all -> 0x0032 }
            java.lang.String r2 = "content_url_hashes"
            r1.putString(r2, r4)     // Catch:{ all -> 0x0032 }
            r3.zzc((android.os.Bundle) r1)     // Catch:{ all -> 0x0032 }
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            return
        L_0x0030:
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            return
        L_0x0032:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzahy.zzcb(java.lang.String):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0031, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzcc(java.lang.String r4) {
        /*
            r3 = this;
            r3.zzqm()
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            if (r4 == 0) goto L_0x0030
            java.lang.String r1 = r3.zzdey     // Catch:{ all -> 0x0032 }
            boolean r1 = r4.equals(r1)     // Catch:{ all -> 0x0032 }
            if (r1 == 0) goto L_0x0011
            goto L_0x0030
        L_0x0011:
            r3.zzdey = r4     // Catch:{ all -> 0x0032 }
            android.content.SharedPreferences$Editor r1 = r3.zzdev     // Catch:{ all -> 0x0032 }
            if (r1 == 0) goto L_0x0021
            java.lang.String r2 = "content_vertical_hashes"
            r1.putString(r2, r4)     // Catch:{ all -> 0x0032 }
            android.content.SharedPreferences$Editor r1 = r3.zzdev     // Catch:{ all -> 0x0032 }
            r1.apply()     // Catch:{ all -> 0x0032 }
        L_0x0021:
            android.os.Bundle r1 = new android.os.Bundle     // Catch:{ all -> 0x0032 }
            r1.<init>()     // Catch:{ all -> 0x0032 }
            java.lang.String r2 = "content_vertical_hashes"
            r1.putString(r2, r4)     // Catch:{ all -> 0x0032 }
            r3.zzc((android.os.Bundle) r1)     // Catch:{ all -> 0x0032 }
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            return
        L_0x0030:
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            return
        L_0x0032:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzahy.zzcc(java.lang.String):void");
    }

    public final void zzcd(String str) {
        zzqm();
        synchronized (this.mLock) {
            if (!this.zzdfd.contains(str)) {
                this.zzdfd.add(str);
                SharedPreferences.Editor editor = this.zzdev;
                if (editor != null) {
                    editor.putStringSet("never_pool_slots", this.zzdfd);
                    this.zzdev.apply();
                }
                Bundle bundle = new Bundle();
                Set<String> set = this.zzdfd;
                bundle.putStringArray("never_pool_slots", (String[]) set.toArray(new String[set.size()]));
                zzc(bundle);
            }
        }
    }

    public final void zzce(String str) {
        zzqm();
        synchronized (this.mLock) {
            if (this.zzdfd.contains(str)) {
                this.zzdfd.remove(str);
                SharedPreferences.Editor editor = this.zzdev;
                if (editor != null) {
                    editor.putStringSet("never_pool_slots", this.zzdfd);
                    this.zzdev.apply();
                }
                Bundle bundle = new Bundle();
                Set<String> set = this.zzdfd;
                bundle.putStringArray("never_pool_slots", (String[]) set.toArray(new String[set.size()]));
                zzc(bundle);
            }
        }
    }

    public final boolean zzcf(String str) {
        boolean contains;
        zzqm();
        synchronized (this.mLock) {
            contains = this.zzdfd.contains(str);
        }
        return contains;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0047, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzcg(java.lang.String r6) {
        /*
            r5 = this;
            r5.zzqm()
            java.lang.Object r0 = r5.mLock
            monitor-enter(r0)
            com.google.android.gms.common.util.zze r1 = com.google.android.gms.ads.internal.zzbt.zzes()     // Catch:{ all -> 0x0048 }
            long r1 = r1.currentTimeMillis()     // Catch:{ all -> 0x0048 }
            r5.zzdez = r1     // Catch:{ all -> 0x0048 }
            if (r6 == 0) goto L_0x0046
            java.lang.String r3 = r5.zzddm     // Catch:{ all -> 0x0048 }
            boolean r3 = r6.equals(r3)     // Catch:{ all -> 0x0048 }
            if (r3 == 0) goto L_0x001b
            goto L_0x0046
        L_0x001b:
            r5.zzddm = r6     // Catch:{ all -> 0x0048 }
            android.content.SharedPreferences$Editor r3 = r5.zzdev     // Catch:{ all -> 0x0048 }
            if (r3 == 0) goto L_0x0032
            java.lang.String r4 = "app_settings_json"
            r3.putString(r4, r6)     // Catch:{ all -> 0x0048 }
            android.content.SharedPreferences$Editor r3 = r5.zzdev     // Catch:{ all -> 0x0048 }
            java.lang.String r4 = "app_settings_last_update_ms"
            r3.putLong(r4, r1)     // Catch:{ all -> 0x0048 }
            android.content.SharedPreferences$Editor r3 = r5.zzdev     // Catch:{ all -> 0x0048 }
            r3.apply()     // Catch:{ all -> 0x0048 }
        L_0x0032:
            android.os.Bundle r3 = new android.os.Bundle     // Catch:{ all -> 0x0048 }
            r3.<init>()     // Catch:{ all -> 0x0048 }
            java.lang.String r4 = "app_settings_json"
            r3.putString(r4, r6)     // Catch:{ all -> 0x0048 }
            java.lang.String r6 = "app_settings_last_update_ms"
            r3.putLong(r6, r1)     // Catch:{ all -> 0x0048 }
            r5.zzc((android.os.Bundle) r3)     // Catch:{ all -> 0x0048 }
            monitor-exit(r0)     // Catch:{ all -> 0x0048 }
            return
        L_0x0046:
            monitor-exit(r0)     // Catch:{ all -> 0x0048 }
            return
        L_0x0048:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0048 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzahy.zzcg(java.lang.String):void");
    }

    public final void zzj(long j) {
        zzqm();
        synchronized (this.mLock) {
            if (this.zzdfa != j) {
                this.zzdfa = j;
                SharedPreferences.Editor editor = this.zzdev;
                if (editor != null) {
                    editor.putLong("app_last_background_time_ms", j);
                    this.zzdev.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putLong("app_last_background_time_ms", j);
                zzc(bundle);
            }
        }
    }

    public final void zzk(long j) {
        zzqm();
        synchronized (this.mLock) {
            if (this.zzdfb != j) {
                this.zzdfb = j;
                SharedPreferences.Editor editor = this.zzdev;
                if (editor != null) {
                    editor.putLong("first_ad_req_time_ms", j);
                    this.zzdev.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putLong("first_ad_req_time_ms", j);
                zzc(bundle);
            }
        }
    }

    public final boolean zzqo() {
        boolean z;
        zzqm();
        synchronized (this.mLock) {
            if (!this.zzcxd) {
                if (!this.zzdew) {
                    z = false;
                }
            }
            z = true;
        }
        return z;
    }

    public final boolean zzqp() {
        boolean z;
        zzqm();
        synchronized (this.mLock) {
            z = this.zzcxe;
        }
        return z;
    }

    public final String zzqq() {
        String str;
        zzqm();
        synchronized (this.mLock) {
            str = this.zzdex;
        }
        return str;
    }

    public final boolean zzqr() {
        boolean z;
        zzqm();
        synchronized (this.mLock) {
            z = this.zzcxf;
        }
        return z;
    }

    public final String zzqs() {
        String str;
        zzqm();
        synchronized (this.mLock) {
            str = this.zzdey;
        }
        return str;
    }

    public final boolean zzqt() {
        boolean z;
        zzqm();
        synchronized (this.mLock) {
            z = this.zzcxm;
        }
        return z;
    }

    public final int zzqu() {
        int i;
        zzqm();
        synchronized (this.mLock) {
            i = this.zzdfc;
        }
        return i;
    }

    public final zzahh zzqv() {
        zzahh zzahh;
        zzqm();
        synchronized (this.mLock) {
            zzahh = new zzahh(this.zzddm, this.zzdez);
        }
        return zzahh;
    }

    public final long zzqw() {
        long j;
        zzqm();
        synchronized (this.mLock) {
            j = this.zzdfa;
        }
        return j;
    }

    public final int zzqx() {
        int i;
        zzqm();
        synchronized (this.mLock) {
            i = this.zzdef;
        }
        return i;
    }

    public final long zzqy() {
        long j;
        zzqm();
        synchronized (this.mLock) {
            j = this.zzdfb;
        }
        return j;
    }

    public final JSONObject zzqz() {
        JSONObject jSONObject;
        zzqm();
        synchronized (this.mLock) {
            jSONObject = this.zzdfe;
        }
        return jSONObject;
    }

    public final void zzra() {
        zzqm();
        synchronized (this.mLock) {
            this.zzdfe = new JSONObject();
            SharedPreferences.Editor editor = this.zzdev;
            if (editor != null) {
                editor.remove("native_advanced_settings");
                this.zzdev.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putString("native_advanced_settings", "{}");
            zzc(bundle);
        }
    }
}
