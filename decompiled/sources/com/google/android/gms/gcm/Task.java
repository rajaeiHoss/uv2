package com.google.android.gms.gcm;

import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbq;

public class Task implements ReflectedParcelable {
    public static final int EXTRAS_LIMIT_BYTES = 10240;
    public static final int NETWORK_STATE_ANY = 2;
    public static final int NETWORK_STATE_CONNECTED = 0;
    public static final int NETWORK_STATE_UNMETERED = 1;
    protected static final long UNINITIALIZED = -1;
    private final Bundle mExtras;
    private final String mTag;
    private final String zziks;
    private final boolean zzikt;
    private final boolean zziku;
    private final int zzikv;
    private final boolean zzikw;
    private final boolean zzikx;
    private final zzi zziky;

    public static abstract class Builder {
        protected Bundle extras;
        protected String gcmTaskService;
        protected boolean isPersisted;
        protected int requiredNetworkState;
        protected boolean requiresCharging;
        protected String tag;
        protected boolean updateCurrent;
        protected zzi zzikz = zzi.zzikn;

        public abstract Task build();

        /* access modifiers changed from: protected */
        public void checkConditions() {
            zzbq.checkArgument(this.gcmTaskService != null, "Must provide an endpoint for this task by calling setService(ComponentName).");
            GcmNetworkManager.zzid(this.tag);
            zzi zzi = this.zzikz;
            if (zzi != null) {
                int zzawi = zzi.zzawi();
                if (zzawi == 1 || zzawi == 0) {
                    int zzawj = zzi.zzawj();
                    int zzawk = zzi.zzawk();
                    if (zzawi == 0 && zzawj < 0) {
                        StringBuilder sb = new StringBuilder(52);
                        sb.append("InitialBackoffSeconds can't be negative: ");
                        sb.append(zzawj);
                        throw new IllegalArgumentException(sb.toString());
                    } else if (zzawi == 1 && zzawj < 10) {
                        throw new IllegalArgumentException("RETRY_POLICY_LINEAR must have an initial backoff at least 10 seconds.");
                    } else if (zzawk < zzawj) {
                        int zzawk2 = zzi.zzawk();
                        StringBuilder sb2 = new StringBuilder(77);
                        sb2.append("MaximumBackoffSeconds must be greater than InitialBackoffSeconds: ");
                        sb2.append(zzawk2);
                        throw new IllegalArgumentException(sb2.toString());
                    }
                } else {
                    StringBuilder sb3 = new StringBuilder(45);
                    sb3.append("Must provide a valid RetryPolicy: ");
                    sb3.append(zzawi);
                    throw new IllegalArgumentException(sb3.toString());
                }
            }
            if (this.isPersisted) {
                Task.zzw(this.extras);
            }
        }

        public abstract Builder setExtras(Bundle bundle);

        public abstract Builder setPersisted(boolean z);

        public abstract Builder setRequiredNetwork(int i);

        public abstract Builder setRequiresCharging(boolean z);

        public abstract Builder setService(Class<? extends GcmTaskService> cls);

        public abstract Builder setTag(String str);

        public abstract Builder setUpdateCurrent(boolean z);
    }

    @Deprecated
    Task(Parcel parcel) {
        Log.e("Task", "Constructing a Task object using a parcel.");
        this.zziks = parcel.readString();
        this.mTag = parcel.readString();
        boolean z = true;
        this.zzikt = parcel.readInt() == 1;
        this.zziku = parcel.readInt() != 1 ? false : z;
        this.zzikv = 2;
        this.zzikw = false;
        this.zzikx = false;
        this.zziky = zzi.zzikn;
        this.mExtras = null;
    }

    Task(Builder builder) {
        this.zziks = builder.gcmTaskService;
        this.mTag = builder.tag;
        this.zzikt = builder.updateCurrent;
        this.zziku = builder.isPersisted;
        this.zzikv = builder.requiredNetworkState;
        this.zzikw = builder.requiresCharging;
        this.zzikx = false;
        this.mExtras = builder.extras;
        this.zziky = builder.zzikz != null ? builder.zzikz : zzi.zzikn;
    }

    public static void zzw(Bundle bundle) {
        if (bundle != null) {
            Parcel obtain = Parcel.obtain();
            bundle.writeToParcel(obtain, 0);
            int dataSize = obtain.dataSize();
            obtain.recycle();
            if (dataSize <= 10240) {
                for (String str : bundle.keySet()) {
                    Object obj = bundle.get(str);
                    if (!((obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Double) || (obj instanceof String) || (obj instanceof Boolean))) {
                        if (obj instanceof Bundle) {
                            zzw((Bundle) obj);
                        } else {
                            throw new IllegalArgumentException("Only the following extra parameter types are supported: Integer, Long, Double, String, Boolean, and nested Bundles with the same restrictions.");
                        }
                    }
                }
                return;
            }
            StringBuilder sb = new StringBuilder(55);
            sb.append("Extras exceeding maximum size(10240 bytes): ");
            sb.append(dataSize);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public int describeContents() {
        return 0;
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    public int getRequiredNetwork() {
        return this.zzikv;
    }

    public boolean getRequiresCharging() {
        return this.zzikw;
    }

    public String getServiceName() {
        return this.zziks;
    }

    public String getTag() {
        return this.mTag;
    }

    public boolean isPersisted() {
        return this.zziku;
    }

    public boolean isUpdateCurrent() {
        return this.zzikt;
    }

    public void toBundle(Bundle bundle) {
        bundle.putString("tag", this.mTag);
        bundle.putBoolean("update_current", this.zzikt);
        bundle.putBoolean("persisted", this.zziku);
        bundle.putString(NotificationCompat.CATEGORY_SERVICE, this.zziks);
        bundle.putInt("requiredNetwork", this.zzikv);
        bundle.putBoolean("requiresCharging", this.zzikw);
        bundle.putBoolean("requiresIdle", false);
        bundle.putBundle("retryStrategy", this.zziky.zzv(new Bundle()));
        bundle.putBundle("extras", this.mExtras);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.zziks);
        parcel.writeString(this.mTag);
        parcel.writeInt(this.zzikt ? 1 : 0);
        parcel.writeInt(this.zziku ? 1 : 0);
    }
}
