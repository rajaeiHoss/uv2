package com.google.android.gms.drive;

import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.zzbuj;
import com.google.android.gms.internal.zzbuw;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

public final class MetadataChangeSet {
    public static final int CUSTOM_PROPERTY_SIZE_LIMIT_BYTES = 124;
    public static final int INDEXABLE_TEXT_SIZE_LIMIT_BYTES = 131072;
    public static final int MAX_PRIVATE_PROPERTIES_PER_RESOURCE_PER_APP = 30;
    public static final int MAX_PUBLIC_PROPERTIES_PER_RESOURCE = 30;
    public static final int MAX_TOTAL_PROPERTIES_PER_RESOURCE = 100;
    public static final MetadataChangeSet zzgqo = new MetadataChangeSet(MetadataBundle.zzaqz());
    private final MetadataBundle zzgqp;

    public static class Builder {
        private final MetadataBundle zzgqp = MetadataBundle.zzaqz();
        private AppVisibleCustomProperties.zza zzgqq;

        private final AppVisibleCustomProperties.zza zzapw() {
            if (this.zzgqq == null) {
                this.zzgqq = new AppVisibleCustomProperties.zza();
            }
            return this.zzgqq;
        }

        private static int zzhf(String str) {
            if (str == null) {
                return 0;
            }
            return str.getBytes().length;
        }

        private static void zzi(String str, int i, int i2) {
            zzbq.checkArgument(i2 <= i, String.format("%s must be no more than %d bytes, but is %d bytes.", new Object[]{str, Integer.valueOf(i), Integer.valueOf(i2)}));
        }

        public MetadataChangeSet build() {
            if (this.zzgqq != null) {
                this.zzgqp.zzc(zzbuj.zzgyz, this.zzgqq.zzaqw());
            }
            return new MetadataChangeSet(this.zzgqp);
        }

        public Builder deleteCustomProperty(CustomPropertyKey customPropertyKey) {
            zzbq.checkNotNull(customPropertyKey, "key");
            zzapw().zza(customPropertyKey, (String) null);
            return this;
        }

        public Builder setCustomProperty(CustomPropertyKey customPropertyKey, String str) {
            zzbq.checkNotNull(customPropertyKey, "key");
            zzbq.checkNotNull(str, FirebaseAnalytics.Param.VALUE);
            zzi("The total size of key string and value string of a custom property", 124, zzhf(customPropertyKey.getKey()) + zzhf(str));
            zzapw().zza(customPropertyKey, str);
            return this;
        }

        public Builder setDescription(String str) {
            this.zzgqp.zzc(zzbuj.zzgza, str);
            return this;
        }

        public Builder setIndexableText(String str) {
            zzi("Indexable text size", 131072, zzhf(str));
            this.zzgqp.zzc(zzbuj.zzgzg, str);
            return this;
        }

        public Builder setLastViewedByMeDate(Date date) {
            this.zzgqp.zzc(zzbuw.zzhaq, date);
            return this;
        }

        public Builder setMimeType(String str) {
            zzbq.checkNotNull(str);
            this.zzgqp.zzc(zzbuj.zzgzu, str);
            return this;
        }

        public Builder setPinned(boolean z) {
            this.zzgqp.zzc(zzbuj.zzgzm, Boolean.valueOf(z));
            return this;
        }

        public Builder setStarred(boolean z) {
            this.zzgqp.zzc(zzbuj.zzhab, Boolean.valueOf(z));
            return this;
        }

        public Builder setTitle(String str) {
            zzbq.checkNotNull(str, "Title cannot be null.");
            this.zzgqp.zzc(zzbuj.zzhad, str);
            return this;
        }

        public Builder setViewed() {
            this.zzgqp.zzc(zzbuj.zzgzt, true);
            return this;
        }

        @Deprecated
        public Builder setViewed(boolean z) {
            if (z) {
                this.zzgqp.zzc(zzbuj.zzgzt, true);
            } else if (this.zzgqp.zzd(zzbuj.zzgzt)) {
                this.zzgqp.zzc(zzbuj.zzgzt);
            }
            return this;
        }
    }

    public MetadataChangeSet(MetadataBundle metadataBundle) {
        this.zzgqp = metadataBundle.zzara();
    }

    public final Map<CustomPropertyKey, String> getCustomPropertyChangeMap() {
        AppVisibleCustomProperties appVisibleCustomProperties = (AppVisibleCustomProperties) this.zzgqp.zza(zzbuj.zzgyz);
        return appVisibleCustomProperties == null ? Collections.emptyMap() : appVisibleCustomProperties.zzaqv();
    }

    public final String getDescription() {
        return (String) this.zzgqp.zza(zzbuj.zzgza);
    }

    public final String getIndexableText() {
        return (String) this.zzgqp.zza(zzbuj.zzgzg);
    }

    public final Date getLastViewedByMeDate() {
        return (Date) this.zzgqp.zza(zzbuw.zzhaq);
    }

    public final String getMimeType() {
        return (String) this.zzgqp.zza(zzbuj.zzgzu);
    }

    public final String getTitle() {
        return (String) this.zzgqp.zza(zzbuj.zzhad);
    }

    public final Boolean isPinned() {
        return (Boolean) this.zzgqp.zza(zzbuj.zzgzm);
    }

    public final Boolean isStarred() {
        return (Boolean) this.zzgqp.zza(zzbuj.zzhab);
    }

    public final Boolean isViewed() {
        return (Boolean) this.zzgqp.zza(zzbuj.zzgzt);
    }

    public final <T> MetadataChangeSet zza(MetadataField<T> metadataField, T t) {
        MetadataChangeSet metadataChangeSet = new MetadataChangeSet(this.zzgqp);
        metadataChangeSet.zzgqp.zzc(metadataField, t);
        return metadataChangeSet;
    }

    public final MetadataBundle zzapv() {
        return this.zzgqp;
    }
}
