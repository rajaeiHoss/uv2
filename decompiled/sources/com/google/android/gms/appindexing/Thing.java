package com.google.android.gms.appindexing;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.plus.PlusShare;
import java.util.ArrayList;

@Deprecated
public class Thing {
    final Bundle zzegs;

    @Deprecated
    public static class Builder {
        final Bundle zzegt = new Bundle();

        public Thing build() {
            return new Thing(this.zzegt);
        }

        public Builder put(String str, Thing thing) {
            zzbq.checkNotNull(str);
            if (thing != null) {
                this.zzegt.putParcelable(str, thing.zzegs);
            }
            return this;
        }

        public Builder put(String str, String str2) {
            zzbq.checkNotNull(str);
            if (str2 != null) {
                this.zzegt.putString(str, str2);
            }
            return this;
        }

        public Builder put(String str, boolean z) {
            zzbq.checkNotNull(str);
            this.zzegt.putBoolean(str, z);
            return this;
        }

        public Builder put(String str, Thing[] thingArr) {
            zzbq.checkNotNull(str);
            if (thingArr != null) {
                ArrayList arrayList = new ArrayList();
                for (Thing thing : thingArr) {
                    if (thing != null) {
                        arrayList.add(thing.zzegs);
                    }
                }
                this.zzegt.putParcelableArray(str, (Parcelable[]) arrayList.toArray(new Bundle[arrayList.size()]));
            }
            return this;
        }

        public Builder put(String str, String[] strArr) {
            zzbq.checkNotNull(str);
            if (strArr != null) {
                this.zzegt.putStringArray(str, strArr);
            }
            return this;
        }

        public Builder setDescription(String str) {
            put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, str);
            return this;
        }

        public Builder setId(String str) {
            if (str != null) {
                put("id", str);
            }
            return this;
        }

        public Builder setName(String str) {
            zzbq.checkNotNull(str);
            put("name", str);
            return this;
        }

        public Builder setType(String str) {
            put(AppMeasurement.Param.TYPE, str);
            return this;
        }

        public Builder setUrl(Uri uri) {
            zzbq.checkNotNull(uri);
            put(PlusShare.KEY_CALL_TO_ACTION_URL, uri.toString());
            return this;
        }
    }

    Thing(Bundle bundle) {
        this.zzegs = bundle;
    }

    public final Bundle zzabs() {
        return this.zzegs;
    }
}
