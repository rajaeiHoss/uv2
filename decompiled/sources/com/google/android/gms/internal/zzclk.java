package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicReference;

public final class zzclk extends zzcli {
    protected zzcly zzjpt;
    private AppMeasurement.EventInterceptor zzjpu;
    private final Set<AppMeasurement.OnEventListener> zzjpv = new CopyOnWriteArraySet();
    private boolean zzjpw;
    private final AtomicReference<String> zzjpx = new AtomicReference<>();

    protected zzclk(zzckj zzckj) {
        super(zzckj);
    }

    private final void zza(AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        long currentTimeMillis = zzxx().currentTimeMillis();
        zzbq.checkNotNull(conditionalUserProperty);
        zzbq.zzgv(conditionalUserProperty.mName);
        zzbq.zzgv(conditionalUserProperty.mOrigin);
        zzbq.checkNotNull(conditionalUserProperty.mValue);
        conditionalUserProperty.mCreationTimestamp = currentTimeMillis;
        String str = conditionalUserProperty.mName;
        Object obj = conditionalUserProperty.mValue;
        if (zzayl().zzkk(str) != 0) {
            zzayp().zzbau().zzj("Invalid conditional user property name", zzayk().zzjr(str));
        } else if (zzayl().zzl(str, obj) != 0) {
            zzayp().zzbau().zze("Invalid conditional user property value", zzayk().zzjr(str), obj);
        } else {
            Object zzm = zzayl().zzm(str, obj);
            if (zzm == null) {
                zzayp().zzbau().zze("Unable to normalize conditional user property value", zzayk().zzjr(str), obj);
                return;
            }
            conditionalUserProperty.mValue = zzm;
            long j = conditionalUserProperty.mTriggerTimeout;
            if (TextUtils.isEmpty(conditionalUserProperty.mTriggerEventName) || (j <= 15552000000L && j >= 1)) {
                long j2 = conditionalUserProperty.mTimeToLive;
                if (j2 > 15552000000L || j2 < 1) {
                    zzayp().zzbau().zze("Invalid conditional user property time to live", zzayk().zzjr(str), Long.valueOf(j2));
                } else {
                    zzayo().zzh(new zzclm(this, conditionalUserProperty));
                }
            } else {
                zzayp().zzbau().zze("Invalid conditional user property timeout", zzayk().zzjr(str), Long.valueOf(j));
            }
        }
    }

    private final void zza(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        Bundle bundle2;
        Bundle bundle3 = bundle;
        if (bundle3 == null) {
            bundle2 = new Bundle();
        } else {
            Bundle bundle4 = new Bundle(bundle3);
            for (String str4 : bundle4.keySet()) {
                Object obj = bundle4.get(str4);
                if (obj instanceof Bundle) {
                    bundle4.putBundle(str4, new Bundle((Bundle) obj));
                } else {
                    int i = 0;
                    if (obj instanceof Parcelable[]) {
                        Parcelable[] parcelableArr = (Parcelable[]) obj;
                        while (i < parcelableArr.length) {
                            if (parcelableArr[i] instanceof Bundle) {
                                parcelableArr[i] = new Bundle((Bundle) parcelableArr[i]);
                            }
                            i++;
                        }
                    } else if (obj instanceof ArrayList) {
                        ArrayList arrayList = (ArrayList) obj;
                        while (i < arrayList.size()) {
                            Object obj2 = arrayList.get(i);
                            if (obj2 instanceof Bundle) {
                                arrayList.set(i, new Bundle((Bundle) obj2));
                            }
                            i++;
                        }
                    }
                }
            }
            bundle2 = bundle4;
        }
        zzayo().zzh(new zzcls(this, str, str2, j, bundle2, z, z2, z3, str3));
    }

    private final void zza(String str, String str2, long j, Object obj) {
        zzayo().zzh(new zzclt(this, str, str2, obj, j));
    }

    private final void zza(String str, String str2, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        zza(str, str2, zzxx().currentTimeMillis(), bundle, true, z2, z3, (String) null);
    }

    /* access modifiers changed from: private */
    public final void zza(String str, String str2, Object obj, long j) {
        zzbq.zzgv(str);
        zzbq.zzgv(str2);
        zzwj();
        zzyk();
        if (!this.zzjev.isEnabled()) {
            zzayp().zzbaz().log("User property not set since app measurement is disabled");
        } else if (this.zzjev.zzbbn()) {
            zzayp().zzbaz().zze("Setting user property (FE)", zzayk().zzjp(str2), obj);
            zzayg().zzb(new zzcnl(str2, j, obj, str));
        }
    }

    private final void zza(String str, String str2, String str3, Bundle bundle) {
        long currentTimeMillis = zzxx().currentTimeMillis();
        zzbq.zzgv(str2);
        AppMeasurement.ConditionalUserProperty conditionalUserProperty = new AppMeasurement.ConditionalUserProperty();
        conditionalUserProperty.mAppId = str;
        conditionalUserProperty.mName = str2;
        conditionalUserProperty.mCreationTimestamp = currentTimeMillis;
        if (str3 != null) {
            conditionalUserProperty.mExpiredEventName = str3;
            conditionalUserProperty.mExpiredEventParams = bundle;
        }
        zzayo().zzh(new zzcln(this, conditionalUserProperty));
    }

    private final Map<String, Object> zzb(String str, String str2, String str3, boolean z) {
        zzcjl zzbaw;
        String str4;
        if (zzayo().zzbbk()) {
            zzbaw = zzayp().zzbau();
            str4 = "Cannot get user properties from analytics worker thread";
        } else {
            zzayo();
            if (zzcke.zzas()) {
                zzbaw = zzayp().zzbau();
                str4 = "Cannot get user properties from main thread";
            } else {
                AtomicReference atomicReference = new AtomicReference();
                synchronized (atomicReference) {
                    this.zzjev.zzayo().zzh(new zzclp(this, atomicReference, str, str2, str3, z));
                    try {
                        atomicReference.wait(5000);
                    } catch (InterruptedException e) {
                        zzayp().zzbaw().zzj("Interrupted waiting for get user properties", e);
                    }
                }
                List<zzcnl> list = (List) atomicReference.get();
                if (list == null) {
                    zzbaw = zzayp().zzbaw();
                    str4 = "Timed out waiting for get user properties";
                } else {
                    ArrayMap arrayMap = new ArrayMap(list.size());
                    for (zzcnl zzcnl : list) {
                        arrayMap.put(zzcnl.name, zzcnl.getValue());
                    }
                    return arrayMap;
                }
            }
        }
        zzbaw.log(str4);
        return Collections.emptyMap();
    }

    /* access modifiers changed from: private */
    public final void zzb(AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        AppMeasurement.ConditionalUserProperty conditionalUserProperty2 = conditionalUserProperty;
        zzwj();
        zzyk();
        zzbq.checkNotNull(conditionalUserProperty);
        zzbq.zzgv(conditionalUserProperty2.mName);
        zzbq.zzgv(conditionalUserProperty2.mOrigin);
        zzbq.checkNotNull(conditionalUserProperty2.mValue);
        if (!this.zzjev.isEnabled()) {
            zzayp().zzbaz().log("Conditional property not sent since Firebase Analytics is disabled");
            return;
        }
        zzcnl zzcnl = new zzcnl(conditionalUserProperty2.mName, conditionalUserProperty2.mTriggeredTimestamp, conditionalUserProperty2.mValue, conditionalUserProperty2.mOrigin);
        try {
            zzcix zza = zzayl().zza(conditionalUserProperty2.mTriggeredEventName, conditionalUserProperty2.mTriggeredEventParams, conditionalUserProperty2.mOrigin, 0, true, false);
            zzcix zza2 = zzayl().zza(conditionalUserProperty2.mTimedOutEventName, conditionalUserProperty2.mTimedOutEventParams, conditionalUserProperty2.mOrigin, 0, true, false);
            zzcix zza3 = zzayl().zza(conditionalUserProperty2.mExpiredEventName, conditionalUserProperty2.mExpiredEventParams, conditionalUserProperty2.mOrigin, 0, true, false);
            String str = conditionalUserProperty2.mAppId;
            String str2 = conditionalUserProperty2.mOrigin;
            long j = conditionalUserProperty2.mCreationTimestamp;
            String str3 = conditionalUserProperty2.mTriggerEventName;
            long j2 = conditionalUserProperty2.mTriggerTimeout;
            zzcii zzcii2 = new zzcii(str, str2, zzcnl, j, false, str3, zza2, j2, zza, conditionalUserProperty2.mTimeToLive, zza3);
            zzayg().zzf(zzcii2);
        } catch (IllegalArgumentException unused) {
        }
    }

    /* access modifiers changed from: private */
    public final void zzb(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        String[] strArr;
        Bundle bundle2;
        int i;
        long j2;
        String str4 = str;
        String str5 = str2;
        Bundle bundle3 = bundle;
        zzbq.zzgv(str);
        zzbq.zzgv(str2);
        zzbq.checkNotNull(bundle);
        zzwj();
        zzyk();
        if (!this.zzjev.isEnabled()) {
            zzayp().zzbaz().log("Event not sent since app measurement is disabled");
            return;
        }
        int i2 = 0;
        if (!this.zzjpw) {
            this.zzjpw = true;
            try {
                Class.forName("com.google.android.gms.tagmanager.TagManagerService").getDeclaredMethod("initialize", new Class[]{Context.class}).invoke((Object) null, new Object[]{getContext()});
            } catch (ClassNotFoundException unused) {
                zzayp().zzbay().log("Tag Manager is not found and thus will not be used");
            } catch (Exception e) {
                zzayp().zzbaw().zzj("Failed to invoke Tag Manager's initialize() method", e);
            }
        }
        if (z3 && !"_iap".equals(str5)) {
            zzcno zzayl = this.zzjev.zzayl();
            int i3 = 2;
            if (zzayl.zzaq("event", str5)) {
                if (!zzayl.zza("event", AppMeasurement.Event.zzjew, str5)) {
                    i3 = 13;
                } else if (zzayl.zzb("event", 40, str5)) {
                    i3 = 0;
                }
            }
            if (i3 != 0) {
                this.zzjev.zzayl();
                String zza = zzcno.zza(str5, 40, true);
                if (str5 != null) {
                    i2 = str2.length();
                }
                this.zzjev.zzayl().zza(i3, "_ev", zza, i2);
                return;
            }
        }
        zzcmd zzbcg = zzayh().zzbcg();
        if (zzbcg != null && !bundle3.containsKey("_sc")) {
            zzbcg.zzjra = true;
        }
        zzcma.zza((zzclz) zzbcg, bundle3, z && z3);
        boolean equals = "am".equals(str4);
        boolean zzkp = zzcno.zzkp(str2);
        if (z && this.zzjpu != null && !zzkp && !equals) {
            zzayp().zzbaz().zze("Passing event to registered event handler (FE)", zzayk().zzjp(str5), zzayk().zzac(bundle3));
            this.zzjpu.interceptEvent(str, str2, bundle, j);
        } else if (this.zzjev.zzbbn()) {
            int zzki = zzayl().zzki(str5);
            if (zzki != 0) {
                zzayl();
                String zza2 = zzcno.zza(str5, 40, true);
                if (str5 != null) {
                    i2 = str2.length();
                }
                this.zzjev.zzayl().zza(str3, zzki, "_ev", zza2, i2);
                return;
            }
            List unmodifiableList = Collections.unmodifiableList(Arrays.asList(new String[]{"_o", "_sn", "_sc", "_si"}));
            String str6 = "_si";
            String str7 = "_sn";
            Bundle zza3 = zzayl().zza(str2, bundle, (List<String>) unmodifiableList, z3, true);
            zzcmd zzcmd = (zza3 == null || !zza3.containsKey("_sc") || !zza3.containsKey(str6)) ? null : new zzcmd(zza3.getString(str7), zza3.getString("_sc"), Long.valueOf(zza3.getLong(str6)).longValue());
            if (zzcmd != null) {
                zzbcg = zzcmd;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(zza3);
            long nextLong = zzayl().zzbcr().nextLong();
            String[] strArr2 = (String[]) zza3.keySet().toArray(new String[bundle.size()]);
            Arrays.sort(strArr2);
            int length = strArr2.length;
            int i4 = 0;
            int i5 = 0;
            while (i5 < length) {
                String str8 = strArr2[i5];
                Object obj = zza3.get(str8);
                zzayl();
                Bundle[] zzaf = zzcno.zzaf(obj);
                if (zzaf != null) {
                    String str9 = "_eid";
                    zza3.putInt(str8, zzaf.length);
                    String str10 = str8;
                    int i6 = 0;
                    while (i6 < zzaf.length) {
                        Bundle bundle4 = zzaf[i6];
                        int i7 = i6;
                        zzcma.zza((zzclz) zzbcg, bundle4, true);
                        String str11 = str9;
                        long j3 = nextLong;
                        String[] strArr3 = strArr2;
                        Bundle bundle5 = zza3;
                        Bundle zza4 = zzayl().zza("_ep", bundle4, (List<String>) unmodifiableList, z3, false);
                        zza4.putString("_en", str5);
                        String str12 = str11;
                        long j4 = j3;
                        zza4.putLong(str12, j4);
                        zza4.putString("_gn", str10);
                        zza4.putInt("_ll", zzaf.length);
                        int i8 = i7;
                        zza4.putInt("_i", i8);
                        arrayList.add(zza4);
                        int i9 = i8 + 1;
                        zza3 = bundle5;
                        strArr2 = strArr3;
                        str9 = str12;
                        nextLong = j4;
                        i4 = i4;
                        i6 = i9;
                    }
                    j2 = nextLong;
                    strArr = strArr2;
                    bundle2 = zza3;
                    i = zzaf.length + i4;
                } else {
                    strArr = strArr2;
                    bundle2 = zza3;
                    i = i4;
                    j2 = nextLong;
                }
                i5++;
                zza3 = bundle2;
                strArr2 = strArr;
                i4 = i;
                nextLong = j2;
            }
            Bundle bundle6 = zza3;
            long j5 = nextLong;
            String str13 = "_eid";
            int i10 = i4;
            long j6 = j5;
            if (i10 != 0) {
                bundle6.putLong(str13, j6);
                bundle6.putInt("_epc", i10);
            }
            int i11 = 0;
            while (i11 < arrayList.size()) {
                Bundle bundle7 = (Bundle) arrayList.get(i11);
                String str14 = i11 != 0 ? "_ep" : str5;
                bundle7.putString("_o", str4);
                if (z2) {
                    bundle7 = zzayl().zzad(bundle7);
                }
                Bundle bundle8 = bundle7;
                zzayp().zzbaz().zze("Logging event (FE)", zzayk().zzjp(str5), zzayk().zzac(bundle8));
                zzayg().zzc(new zzcix(str14, new zzciu(bundle8), str, j), str3);
                if (!equals) {
                    for (AppMeasurement.OnEventListener onEvent : this.zzjpv) {
                        onEvent.onEvent(str, str2, new Bundle(bundle8), j);
                    }
                }
                i11++;
            }
            if (zzayh().zzbcg() != null && AppMeasurement.Event.APP_EXCEPTION.equals(str5)) {
                zzayn().zzbx(true);
            }
        }
    }

    /* access modifiers changed from: private */
    public final void zzbu(boolean z) {
        zzwj();
        zzyk();
        zzayp().zzbaz().zzj("Setting app measurement enabled (FE)", Boolean.valueOf(z));
        zzayq().setMeasurementEnabled(z);
        zzayg().zzbci();
    }

    /* access modifiers changed from: private */
    public final void zzc(AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        AppMeasurement.ConditionalUserProperty conditionalUserProperty2 = conditionalUserProperty;
        zzwj();
        zzyk();
        zzbq.checkNotNull(conditionalUserProperty);
        zzbq.zzgv(conditionalUserProperty2.mName);
        if (!this.zzjev.isEnabled()) {
            zzayp().zzbaz().log("Conditional property not cleared since Firebase Analytics is disabled");
            return;
        }
        zzcnl zzcnl = new zzcnl(conditionalUserProperty2.mName, 0, (Object) null, (String) null);
        try {
            zzcix zza = zzayl().zza(conditionalUserProperty2.mExpiredEventName, conditionalUserProperty2.mExpiredEventParams, conditionalUserProperty2.mOrigin, conditionalUserProperty2.mCreationTimestamp, true, false);
            zzcii zzcii2 = new zzcii(conditionalUserProperty2.mAppId, conditionalUserProperty2.mOrigin, zzcnl, conditionalUserProperty2.mCreationTimestamp, conditionalUserProperty2.mActive, conditionalUserProperty2.mTriggerEventName, (zzcix) null, conditionalUserProperty2.mTriggerTimeout, (zzcix) null, conditionalUserProperty2.mTimeToLive, zza);
            zzayg().zzf(zzcii2);
        } catch (IllegalArgumentException unused) {
        }
    }

    private final List<AppMeasurement.ConditionalUserProperty> zzl(String str, String str2, String str3) {
        zzcjl zzbau;
        String str4;
        if (zzayo().zzbbk()) {
            zzbau = zzayp().zzbau();
            str4 = "Cannot get conditional user properties from analytics worker thread";
        } else {
            zzayo();
            if (zzcke.zzas()) {
                zzbau = zzayp().zzbau();
                str4 = "Cannot get conditional user properties from main thread";
            } else {
                AtomicReference atomicReference = new AtomicReference();
                synchronized (atomicReference) {
                    this.zzjev.zzayo().zzh(new zzclo(this, atomicReference, str, str2, str3));
                    try {
                        atomicReference.wait(5000);
                    } catch (InterruptedException e) {
                        zzayp().zzbaw().zze("Interrupted waiting for get conditional user properties", str, e);
                    }
                }
                List<zzcii> list = (List) atomicReference.get();
                if (list == null) {
                    zzayp().zzbaw().zzj("Timed out waiting for get conditional user properties", str);
                    return Collections.emptyList();
                }
                ArrayList arrayList = new ArrayList(list.size());
                for (zzcii zzcii : list) {
                    AppMeasurement.ConditionalUserProperty conditionalUserProperty = new AppMeasurement.ConditionalUserProperty();
                    conditionalUserProperty.mAppId = str;
                    conditionalUserProperty.mOrigin = str2;
                    conditionalUserProperty.mCreationTimestamp = zzcii.zzjgo;
                    conditionalUserProperty.mName = zzcii.zzjgn.name;
                    conditionalUserProperty.mValue = zzcii.zzjgn.getValue();
                    conditionalUserProperty.mActive = zzcii.zzjgp;
                    conditionalUserProperty.mTriggerEventName = zzcii.zzjgq;
                    if (zzcii.zzjgr != null) {
                        conditionalUserProperty.mTimedOutEventName = zzcii.zzjgr.name;
                        if (zzcii.zzjgr.zzjhr != null) {
                            conditionalUserProperty.mTimedOutEventParams = zzcii.zzjgr.zzjhr.zzbao();
                        }
                    }
                    conditionalUserProperty.mTriggerTimeout = zzcii.zzjgs;
                    if (zzcii.zzjgt != null) {
                        conditionalUserProperty.mTriggeredEventName = zzcii.zzjgt.name;
                        if (zzcii.zzjgt.zzjhr != null) {
                            conditionalUserProperty.mTriggeredEventParams = zzcii.zzjgt.zzjhr.zzbao();
                        }
                    }
                    conditionalUserProperty.mTriggeredTimestamp = zzcii.zzjgn.zzjsi;
                    conditionalUserProperty.mTimeToLive = zzcii.zzjgu;
                    if (zzcii.zzjgv != null) {
                        conditionalUserProperty.mExpiredEventName = zzcii.zzjgv.name;
                        if (zzcii.zzjgv.zzjhr != null) {
                            conditionalUserProperty.mExpiredEventParams = zzcii.zzjgv.zzjhr.zzbao();
                        }
                    }
                    arrayList.add(conditionalUserProperty);
                }
                return arrayList;
            }
        }
        zzbau.log(str4);
        return Collections.emptyList();
    }

    public final void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
        zza((String) null, str, str2, bundle);
    }

    public final void clearConditionalUserPropertyAs(String str, String str2, String str3, Bundle bundle) {
        zzbq.zzgv(str);
        zzaxz();
        zza(str, str2, str3, bundle);
    }

    public final Task<String> getAppInstanceId() {
        try {
            String zzbbf = zzayq().zzbbf();
            return zzbbf != null ? Tasks.forResult(zzbbf) : Tasks.call(zzayo().zzbbl(), new zzclv(this));
        } catch (Exception e) {
            zzayp().zzbaw().log("Failed to schedule task for getAppInstanceId");
            return Tasks.forException(e);
        }
    }

    public final List<AppMeasurement.ConditionalUserProperty> getConditionalUserProperties(String str, String str2) {
        return zzl((String) null, str, str2);
    }

    public final List<AppMeasurement.ConditionalUserProperty> getConditionalUserPropertiesAs(String str, String str2, String str3) {
        zzbq.zzgv(str);
        zzaxz();
        return zzl(str, str2, str3);
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final Map<String, Object> getUserProperties(String str, String str2, boolean z) {
        return zzb((String) null, str, str2, z);
    }

    public final Map<String, Object> getUserPropertiesAs(String str, String str2, String str3, boolean z) {
        zzbq.zzgv(str);
        zzaxz();
        return zzb(str, str2, str3, z);
    }

    public final void registerOnMeasurementEventListener(AppMeasurement.OnEventListener onEventListener) {
        zzyk();
        zzbq.checkNotNull(onEventListener);
        if (!this.zzjpv.add(onEventListener)) {
            zzayp().zzbaw().log("OnEventListener already registered");
        }
    }

    public final void resetAnalyticsData() {
        zzayo().zzh(new zzclx(this));
    }

    public final void setConditionalUserProperty(AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        zzbq.checkNotNull(conditionalUserProperty);
        AppMeasurement.ConditionalUserProperty conditionalUserProperty2 = new AppMeasurement.ConditionalUserProperty(conditionalUserProperty);
        if (!TextUtils.isEmpty(conditionalUserProperty2.mAppId)) {
            zzayp().zzbaw().log("Package name should be null when calling setConditionalUserProperty");
        }
        conditionalUserProperty2.mAppId = null;
        zza(conditionalUserProperty2);
    }

    public final void setConditionalUserPropertyAs(AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        zzbq.checkNotNull(conditionalUserProperty);
        zzbq.zzgv(conditionalUserProperty.mAppId);
        zzaxz();
        zza(new AppMeasurement.ConditionalUserProperty(conditionalUserProperty));
    }

    public final void setEventInterceptor(AppMeasurement.EventInterceptor eventInterceptor) {
        AppMeasurement.EventInterceptor eventInterceptor2;
        zzwj();
        zzyk();
        if (!(eventInterceptor == null || eventInterceptor == (eventInterceptor2 = this.zzjpu))) {
            zzbq.zza(eventInterceptor2 == null, (Object) "EventInterceptor already set.");
        }
        this.zzjpu = eventInterceptor;
    }

    public final void setMeasurementEnabled(boolean z) {
        zzyk();
        zzayo().zzh(new zzcll(this, z));
    }

    public final void setMinimumSessionDuration(long j) {
        zzayo().zzh(new zzclq(this, j));
    }

    public final void setSessionTimeoutDuration(long j) {
        zzayo().zzh(new zzclr(this, j));
    }

    public final void unregisterOnMeasurementEventListener(AppMeasurement.OnEventListener onEventListener) {
        zzyk();
        zzbq.checkNotNull(onEventListener);
        if (!this.zzjpv.remove(onEventListener)) {
            zzayp().zzbaw().log("OnEventListener had not been registered");
        }
    }

    public final void zza(String str, String str2, Bundle bundle, long j) {
        zza(str, str2, j, bundle, false, true, true, (String) null);
    }

    public final void zza(String str, String str2, Bundle bundle, boolean z) {
        zza(str, str2, bundle, true, this.zzjpu == null || zzcno.zzkp(str2), true, (String) null);
    }

    public final /* bridge */ /* synthetic */ void zzaxz() {
        super.zzaxz();
    }

    public final /* bridge */ /* synthetic */ void zzaya() {
        super.zzaya();
    }

    public final /* bridge */ /* synthetic */ zzcia zzayb() {
        return super.zzayb();
    }

    public final /* bridge */ /* synthetic */ zzcih zzayc() {
        return super.zzayc();
    }

    public final /* bridge */ /* synthetic */ zzclk zzayd() {
        return super.zzayd();
    }

    public final /* bridge */ /* synthetic */ zzcje zzaye() {
        return super.zzaye();
    }

    public final /* bridge */ /* synthetic */ zzcir zzayf() {
        return super.zzayf();
    }

    public final /* bridge */ /* synthetic */ zzcme zzayg() {
        return super.zzayg();
    }

    public final /* bridge */ /* synthetic */ zzcma zzayh() {
        return super.zzayh();
    }

    public final /* bridge */ /* synthetic */ zzcjf zzayi() {
        return super.zzayi();
    }

    public final /* bridge */ /* synthetic */ zzcil zzayj() {
        return super.zzayj();
    }

    public final /* bridge */ /* synthetic */ zzcjh zzayk() {
        return super.zzayk();
    }

    public final /* bridge */ /* synthetic */ zzcno zzayl() {
        return super.zzayl();
    }

    public final /* bridge */ /* synthetic */ zzckd zzaym() {
        return super.zzaym();
    }

    public final /* bridge */ /* synthetic */ zzcnd zzayn() {
        return super.zzayn();
    }

    public final /* bridge */ /* synthetic */ zzcke zzayo() {
        return super.zzayo();
    }

    public final /* bridge */ /* synthetic */ zzcjj zzayp() {
        return super.zzayp();
    }

    public final /* bridge */ /* synthetic */ zzcju zzayq() {
        return super.zzayq();
    }

    public final /* bridge */ /* synthetic */ zzcik zzayr() {
        return super.zzayr();
    }

    /* access modifiers changed from: protected */
    public final boolean zzazq() {
        return false;
    }

    public final void zzb(String str, String str2, Object obj) {
        zzbq.zzgv(str);
        long currentTimeMillis = zzxx().currentTimeMillis();
        int zzkk = zzayl().zzkk(str2);
        int i = 0;
        if (zzkk != 0) {
            zzayl();
            String zza = zzcno.zza(str2, 24, true);
            if (str2 != null) {
                i = str2.length();
            }
            this.zzjev.zzayl().zza(zzkk, "_ev", zza, i);
        } else if (obj != null) {
            int zzl = zzayl().zzl(str2, obj);
            if (zzl != 0) {
                zzayl();
                String zza2 = zzcno.zza(str2, 24, true);
                if ((obj instanceof String) || (obj instanceof CharSequence)) {
                    i = String.valueOf(obj).length();
                }
                this.zzjev.zzayl().zza(zzl, "_ev", zza2, i);
                return;
            }
            Object zzm = zzayl().zzm(str2, obj);
            if (zzm != null) {
                zza(str, str2, currentTimeMillis, zzm);
            }
        } else {
            zza(str, str2, currentTimeMillis, (Object) null);
        }
    }

    public final String zzbbf() {
        return this.zzjpx.get();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:10|11|12|13) */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        zzayp().zzbaw().log("Interrupted waiting for app instance id");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002c, code lost:
        return null;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x001d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zzbd(long r4) {
        /*
            r3 = this;
            java.util.concurrent.atomic.AtomicReference r0 = new java.util.concurrent.atomic.AtomicReference
            r0.<init>()
            monitor-enter(r0)
            com.google.android.gms.internal.zzcke r1 = r3.zzayo()     // Catch:{ all -> 0x002d }
            com.google.android.gms.internal.zzclw r2 = new com.google.android.gms.internal.zzclw     // Catch:{ all -> 0x002d }
            r2.<init>(r3, r0)     // Catch:{ all -> 0x002d }
            r1.zzh(r2)     // Catch:{ all -> 0x002d }
            r0.wait(r4)     // Catch:{ InterruptedException -> 0x001d }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            java.lang.Object r4 = r0.get()
            java.lang.String r4 = (java.lang.String) r4
            return r4
        L_0x001d:
            com.google.android.gms.internal.zzcjj r4 = r3.zzayp()     // Catch:{ all -> 0x002d }
            com.google.android.gms.internal.zzcjl r4 = r4.zzbaw()     // Catch:{ all -> 0x002d }
            java.lang.String r5 = "Interrupted waiting for app instance id"
            r4.log(r5)     // Catch:{ all -> 0x002d }
            r4 = 0
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return r4
        L_0x002d:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzclk.zzbd(long):java.lang.String");
    }

    public final List<zzcnl> zzbv(boolean z) {
        zzcjl zzbaw;
        String str;
        zzyk();
        zzayp().zzbaz().log("Fetching user attributes (FE)");
        if (zzayo().zzbbk()) {
            zzbaw = zzayp().zzbau();
            str = "Cannot get all user properties from analytics worker thread";
        } else {
            zzayo();
            if (zzcke.zzas()) {
                zzbaw = zzayp().zzbau();
                str = "Cannot get all user properties from main thread";
            } else {
                AtomicReference atomicReference = new AtomicReference();
                synchronized (atomicReference) {
                    this.zzjev.zzayo().zzh(new zzclu(this, atomicReference, z));
                    try {
                        atomicReference.wait(5000);
                    } catch (InterruptedException e) {
                        zzayp().zzbaw().zzj("Interrupted waiting for get user properties", e);
                    }
                }
                List<zzcnl> list = (List) atomicReference.get();
                if (list != null) {
                    return list;
                }
                zzbaw = zzayp().zzbaw();
                str = "Timed out waiting for get user properties";
            }
        }
        zzbaw.log(str);
        return Collections.emptyList();
    }

    public final void zzd(String str, String str2, Bundle bundle) {
        zza(str, str2, bundle, true, this.zzjpu == null || zzcno.zzkp(str2), false, (String) null);
    }

    /* access modifiers changed from: package-private */
    public final void zzjx(String str) {
        this.zzjpx.set(str);
    }

    public final /* bridge */ /* synthetic */ void zzwj() {
        super.zzwj();
    }

    public final /* bridge */ /* synthetic */ zze zzxx() {
        return super.zzxx();
    }
}
