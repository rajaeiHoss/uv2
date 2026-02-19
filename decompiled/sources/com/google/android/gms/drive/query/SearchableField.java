package com.google.android.gms.drive.query;

import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.SearchableOrderedMetadataField;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.internal.zzbuj;
import com.google.android.gms.internal.zzbuw;
import java.util.Date;

public class SearchableField {
    public static final SearchableMetadataField<Boolean> IS_PINNED = zzbuj.zzgzm;
    public static final SearchableOrderedMetadataField<Date> LAST_VIEWED_BY_ME = zzbuw.zzhaq;
    public static final SearchableMetadataField<String> MIME_TYPE = zzbuj.zzgzu;
    public static final SearchableOrderedMetadataField<Date> MODIFIED_DATE = zzbuw.zzhar;
    public static final SearchableCollectionMetadataField<DriveId> PARENTS = zzbuj.zzgzz;
    public static final SearchableMetadataField<Boolean> STARRED = zzbuj.zzhab;
    public static final SearchableMetadataField<String> TITLE = zzbuj.zzhad;
    public static final SearchableMetadataField<Boolean> TRASHED = zzbuj.zzhae;
    public static final SearchableOrderedMetadataField<Date> zzhbf = zzbuw.zzhat;
    public static final SearchableMetadataField<AppVisibleCustomProperties> zzhbg = zzbuj.zzgyz;
}
