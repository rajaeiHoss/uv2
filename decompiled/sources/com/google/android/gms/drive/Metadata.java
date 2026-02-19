package com.google.android.gms.drive;

import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.internal.zzbuj;
import com.google.android.gms.internal.zzbuw;
import com.google.android.gms.internal.zzbve;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

public abstract class Metadata implements Freezable<Metadata> {
    public static final int CONTENT_AVAILABLE_LOCALLY = 1;
    public static final int CONTENT_NOT_AVAILABLE_LOCALLY = 0;

    public String getAlternateLink() {
        return (String) zza(zzbuj.zzgyy);
    }

    public int getContentAvailability() {
        Integer num = (Integer) zza(zzbve.zzhaw);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public Date getCreatedDate() {
        return (Date) zza(zzbuw.zzhap);
    }

    public Map<CustomPropertyKey, String> getCustomProperties() {
        AppVisibleCustomProperties appVisibleCustomProperties = (AppVisibleCustomProperties) zza(zzbuj.zzgyz);
        return appVisibleCustomProperties == null ? Collections.emptyMap() : appVisibleCustomProperties.zzaqv();
    }

    public String getDescription() {
        return (String) zza(zzbuj.zzgza);
    }

    public DriveId getDriveId() {
        return (DriveId) zza(zzbuj.zzgyx);
    }

    public String getEmbedLink() {
        return (String) zza(zzbuj.zzgzb);
    }

    public String getFileExtension() {
        return (String) zza(zzbuj.zzgzc);
    }

    public long getFileSize() {
        return ((Long) zza(zzbuj.zzgzd)).longValue();
    }

    public Date getLastViewedByMeDate() {
        return (Date) zza(zzbuw.zzhaq);
    }

    public String getMimeType() {
        return (String) zza(zzbuj.zzgzu);
    }

    public Date getModifiedByMeDate() {
        return (Date) zza(zzbuw.zzhas);
    }

    public Date getModifiedDate() {
        return (Date) zza(zzbuw.zzhar);
    }

    public String getOriginalFilename() {
        return (String) zza(zzbuj.zzgzv);
    }

    public long getQuotaBytesUsed() {
        return ((Long) zza(zzbuj.zzhaa)).longValue();
    }

    public Date getSharedWithMeDate() {
        return (Date) zza(zzbuw.zzhat);
    }

    public String getTitle() {
        return (String) zza(zzbuj.zzhad);
    }

    public String getWebContentLink() {
        return (String) zza(zzbuj.zzhaf);
    }

    public String getWebViewLink() {
        return (String) zza(zzbuj.zzhag);
    }

    public boolean isEditable() {
        Boolean bool = (Boolean) zza(zzbuj.zzgzj);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public boolean isExplicitlyTrashed() {
        Boolean bool = (Boolean) zza(zzbuj.zzgzk);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public boolean isFolder() {
        return DriveFolder.MIME_TYPE.equals(getMimeType());
    }

    public boolean isInAppFolder() {
        Boolean bool = (Boolean) zza(zzbuj.zzgzh);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public boolean isPinnable() {
        Boolean bool = (Boolean) zza(zzbve.zzhax);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public boolean isPinned() {
        Boolean bool = (Boolean) zza(zzbuj.zzgzm);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public boolean isRestricted() {
        Boolean bool = (Boolean) zza(zzbuj.zzgzo);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public boolean isShared() {
        Boolean bool = (Boolean) zza(zzbuj.zzgzp);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public boolean isStarred() {
        Boolean bool = (Boolean) zza(zzbuj.zzhab);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public boolean isTrashable() {
        Boolean bool = (Boolean) zza(zzbuj.zzgzs);
        if (bool == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public boolean isTrashed() {
        Boolean bool = (Boolean) zza(zzbuj.zzhae);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public boolean isViewed() {
        Boolean bool = (Boolean) zza(zzbuj.zzgzt);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public abstract <T> T zza(MetadataField<T> metadataField);
}
