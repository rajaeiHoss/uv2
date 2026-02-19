package com.google.android.gms.measurement;

import android.content.Context;
import android.os.Bundle;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.api.internal.zzbz;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzckj;
import com.google.android.gms.internal.zzclz;
import com.google.android.gms.internal.zzcnl;
import com.google.android.gms.internal.zzcno;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import java.util.Map;

@Deprecated
public class AppMeasurement {
    public static final String CRASH_ORIGIN = "crash";
    public static final String FCM_ORIGIN = "fcm";
    private final zzckj zzjev;

    public static class ConditionalUserProperty {
        public boolean mActive;
        public String mAppId;
        public long mCreationTimestamp;
        public String mExpiredEventName;
        public Bundle mExpiredEventParams;
        public String mName;
        public String mOrigin;
        public long mTimeToLive;
        public String mTimedOutEventName;
        public Bundle mTimedOutEventParams;
        public String mTriggerEventName;
        public long mTriggerTimeout;
        public String mTriggeredEventName;
        public Bundle mTriggeredEventParams;
        public long mTriggeredTimestamp;
        public Object mValue;

        public ConditionalUserProperty() {
        }

        public ConditionalUserProperty(ConditionalUserProperty conditionalUserProperty) {
            zzbq.checkNotNull(conditionalUserProperty);
            this.mAppId = conditionalUserProperty.mAppId;
            this.mOrigin = conditionalUserProperty.mOrigin;
            this.mCreationTimestamp = conditionalUserProperty.mCreationTimestamp;
            this.mName = conditionalUserProperty.mName;
            Object obj = conditionalUserProperty.mValue;
            if (obj != null) {
                Object zzag = zzcno.zzag(obj);
                this.mValue = zzag;
                if (zzag == null) {
                    this.mValue = conditionalUserProperty.mValue;
                }
            }
            this.mValue = conditionalUserProperty.mValue;
            this.mActive = conditionalUserProperty.mActive;
            this.mTriggerEventName = conditionalUserProperty.mTriggerEventName;
            this.mTriggerTimeout = conditionalUserProperty.mTriggerTimeout;
            this.mTimedOutEventName = conditionalUserProperty.mTimedOutEventName;
            if (conditionalUserProperty.mTimedOutEventParams != null) {
                this.mTimedOutEventParams = new Bundle(conditionalUserProperty.mTimedOutEventParams);
            }
            this.mTriggeredEventName = conditionalUserProperty.mTriggeredEventName;
            if (conditionalUserProperty.mTriggeredEventParams != null) {
                this.mTriggeredEventParams = new Bundle(conditionalUserProperty.mTriggeredEventParams);
            }
            this.mTriggeredTimestamp = conditionalUserProperty.mTriggeredTimestamp;
            this.mTimeToLive = conditionalUserProperty.mTimeToLive;
            this.mExpiredEventName = conditionalUserProperty.mExpiredEventName;
            if (conditionalUserProperty.mExpiredEventParams != null) {
                this.mExpiredEventParams = new Bundle(conditionalUserProperty.mExpiredEventParams);
            }
        }
    }

    public static final class Event extends FirebaseAnalytics.Event {
        public static final String AD_REWARD = "_ar";
        public static final String APP_EXCEPTION = "_ae";
        public static final String[] zzjew = {"app_clear_data", "app_exception", "app_remove", "app_upgrade", "app_install", "app_update", "firebase_campaign", "error", "first_open", "first_visit", "in_app_purchase", "notification_dismiss", "notification_foreground", "notification_open", "notification_receive", "os_update", "session_start", "user_engagement", "ad_exposure", "adunit_exposure", "ad_query", "ad_activeview", "ad_impression", "ad_click", "ad_reward", "screen_view", "ga_extra_parameter"};
        public static final String[] zzjex = {"_cd", APP_EXCEPTION, "_ui", "_ug", "_in", "_au", "_cmp", "_err", "_f", "_v", "_iap", "_nd", "_nf", "_no", "_nr", "_ou", "_s", "_e", "_xa", "_xu", "_aq", "_aa", "_ai", "_ac", AD_REWARD, "_vs", "_ep"};

        private Event() {
        }

        public static String zzix(String str) {
            return zzcno.zza(str, zzjew, zzjex);
        }
    }

    public interface EventInterceptor {
        void interceptEvent(String str, String str2, Bundle bundle, long j);
    }

    public interface OnEventListener {
        void onEvent(String str, String str2, Bundle bundle, long j);
    }

    public static final class Param extends FirebaseAnalytics.Param {
        public static final String FATAL = "fatal";
        public static final String TIMESTAMP = "timestamp";
        public static final String TYPE = "type";
        public static final String[] zzjey = {"firebase_conversion", "engagement_time_msec", "exposure_time", "ad_event_id", "ad_unit_id", "firebase_error", "firebase_error_value", "firebase_error_length", "firebase_event_origin", "firebase_screen", "firebase_screen_class", "firebase_screen_id", "firebase_previous_screen", "firebase_previous_class", "firebase_previous_id", "message_device_time", "message_id", "message_name", "message_time", "previous_app_version", "previous_os_version", "topic", "update_with_analytics", "previous_first_open_count", "system_app", "system_app_update", "previous_install_count", "ga_event_id", "ga_extra_params_ct", "ga_group_name", "ga_list_length", "ga_index", "ga_event_name"};
        public static final String[] zzjez = {"_c", "_et", "_xt", "_aeid", "_ai", "_err", "_ev", "_el", "_o", "_sn", "_sc", "_si", "_pn", "_pc", "_pi", "_ndt", "_nmid", "_nmn", "_nmt", "_pv", "_po", "_nt", "_uwa", "_pfo", "_sys", "_sysu", "_pin", "_eid", "_epc", "_gn", "_ll", "_i", "_en"};

        private Param() {
        }

        public static String zzix(String str) {
            return zzcno.zza(str, zzjey, zzjez);
        }
    }

    public static final class UserProperty extends FirebaseAnalytics.UserProperty {
        public static final String FIREBASE_LAST_NOTIFICATION = "_ln";
        public static final String[] zzjfa = {"firebase_last_notification", "first_open_time", "first_visit_time", "last_deep_link_referrer", "user_id", "first_open_after_install", "lifetime_user_engagement"};
        public static final String[] zzjfb = {FIREBASE_LAST_NOTIFICATION, "_fot", "_fvt", "_ldl", "_id", "_fi", "_lte"};

        private UserProperty() {
        }

        public static String zzix(String str) {
            return zzcno.zza(str, zzjfa, zzjfb);
        }
    }

    public interface zza {
        boolean zza(zzclz zzclz, zzclz zzclz2);
    }

    public AppMeasurement(zzckj zzckj) {
        zzbq.checkNotNull(zzckj);
        this.zzjev = zzckj;
    }

    @Deprecated
    public static AppMeasurement getInstance(Context context) {
        return zzckj.zzed(context).zzbbq();
    }

    public void beginAdUnitExposure(String str) {
        this.zzjev.zzayb().beginAdUnitExposure(str);
    }

    /* access modifiers changed from: protected */
    public void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
        this.zzjev.zzayd().clearConditionalUserProperty(str, str2, bundle);
    }

    /* access modifiers changed from: protected */
    public void clearConditionalUserPropertyAs(String str, String str2, String str3, Bundle bundle) {
        this.zzjev.zzayd().clearConditionalUserPropertyAs(str, str2, str3, bundle);
    }

    public void endAdUnitExposure(String str) {
        this.zzjev.zzayb().endAdUnitExposure(str);
    }

    public long generateEventId() {
        return this.zzjev.zzayl().zzbcq();
    }

    public String getAppInstanceId() {
        return this.zzjev.zzayd().zzbbf();
    }

    /* access modifiers changed from: protected */
    public List<ConditionalUserProperty> getConditionalUserProperties(String str, String str2) {
        return this.zzjev.zzayd().getConditionalUserProperties(str, str2);
    }

    /* access modifiers changed from: protected */
    public List<ConditionalUserProperty> getConditionalUserPropertiesAs(String str, String str2, String str3) {
        return this.zzjev.zzayd().getConditionalUserPropertiesAs(str, str2, str3);
    }

    public String getCurrentScreenClass() {
        zzclz zzbch = this.zzjev.zzayh().zzbch();
        if (zzbch != null) {
            return zzbch.zzjqk;
        }
        return null;
    }

    public String getCurrentScreenName() {
        zzclz zzbch = this.zzjev.zzayh().zzbch();
        if (zzbch != null) {
            return zzbch.zzjqj;
        }
        return null;
    }

    public String getGmpAppId() {
        try {
            return zzbz.zzakq();
        } catch (IllegalStateException e) {
            this.zzjev.zzayp().zzbau().zzj("getGoogleAppId failed with exception", e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public int getMaxUserProperties(String str) {
        this.zzjev.zzayd();
        zzbq.zzgv(str);
        return 25;
    }

    /* access modifiers changed from: protected */
    public Map<String, Object> getUserProperties(String str, String str2, boolean z) {
        return this.zzjev.zzayd().getUserProperties(str, str2, z);
    }

    public Map<String, Object> getUserProperties(boolean z) {
        List<zzcnl> zzbv = this.zzjev.zzayd().zzbv(z);
        ArrayMap arrayMap = new ArrayMap(zzbv.size());
        for (zzcnl next : zzbv) {
            arrayMap.put(next.name, next.getValue());
        }
        return arrayMap;
    }

    /* access modifiers changed from: protected */
    public Map<String, Object> getUserPropertiesAs(String str, String str2, String str3, boolean z) {
        return this.zzjev.zzayd().getUserPropertiesAs(str, str2, str3, z);
    }

    public void logEvent(String str, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.zzjev.zzayd().zza("app", str, bundle, true);
    }

    public void logEventInternal(String str, String str2, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.zzjev.zzayd().zzd(str, str2, bundle);
    }

    public void logEventInternalNoInterceptor(String str, String str2, Bundle bundle, long j) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.zzjev.zzayd().zza(str, str2, bundle, j);
    }

    public void registerOnMeasurementEventListener(OnEventListener onEventListener) {
        this.zzjev.zzayd().registerOnMeasurementEventListener(onEventListener);
    }

    public void registerOnScreenChangeCallback(zza zza2) {
        this.zzjev.zzayh().registerOnScreenChangeCallback(zza2);
    }

    /* access modifiers changed from: protected */
    public void setConditionalUserProperty(ConditionalUserProperty conditionalUserProperty) {
        this.zzjev.zzayd().setConditionalUserProperty(conditionalUserProperty);
    }

    /* access modifiers changed from: protected */
    public void setConditionalUserPropertyAs(ConditionalUserProperty conditionalUserProperty) {
        this.zzjev.zzayd().setConditionalUserPropertyAs(conditionalUserProperty);
    }

    public void setEventInterceptor(EventInterceptor eventInterceptor) {
        this.zzjev.zzayd().setEventInterceptor(eventInterceptor);
    }

    @Deprecated
    public void setMeasurementEnabled(boolean z) {
        this.zzjev.zzayd().setMeasurementEnabled(z);
    }

    public void setMinimumSessionDuration(long j) {
        this.zzjev.zzayd().setMinimumSessionDuration(j);
    }

    public void setSessionTimeoutDuration(long j) {
        this.zzjev.zzayd().setSessionTimeoutDuration(j);
    }

    public void setUserProperty(String str, String str2) {
        int zzkj = this.zzjev.zzayl().zzkj(str);
        if (zzkj != 0) {
            this.zzjev.zzayl();
            this.zzjev.zzayl().zza(zzkj, "_ev", zzcno.zza(str, 24, true), str != null ? str.length() : 0);
            return;
        }
        setUserPropertyInternal("app", str, str2);
    }

    public void setUserPropertyInternal(String str, String str2, Object obj) {
        this.zzjev.zzayd().zzb(str, str2, obj);
    }

    public void unregisterOnMeasurementEventListener(OnEventListener onEventListener) {
        this.zzjev.zzayd().unregisterOnMeasurementEventListener(onEventListener);
    }

    public void unregisterOnScreenChangeCallback(zza zza2) {
        this.zzjev.zzayh().unregisterOnScreenChangeCallback(zza2);
    }
}
