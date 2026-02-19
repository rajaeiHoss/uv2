package com.google.android.gms.drive.query;

import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.SearchableOrderedMetadataField;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.drive.query.internal.zzb;
import com.google.android.gms.drive.query.internal.zzd;
import com.google.android.gms.drive.query.internal.zzn;
import com.google.android.gms.drive.query.internal.zzp;
import com.google.android.gms.drive.query.internal.zzr;
import com.google.android.gms.drive.query.internal.zzv;
import com.google.android.gms.drive.query.internal.zzx;
import com.google.android.gms.drive.query.internal.zzz;

public class Filters {
    public static Filter and(Filter filter, Filter... filterArr) {
        zzbq.checkNotNull(filter, "Filter may not be null.");
        zzbq.checkNotNull(filterArr, "Additional filters may not be null.");
        return new zzr(zzx.zzhcg, filter, filterArr);
    }

    public static Filter and(Iterable<Filter> iterable) {
        zzbq.checkNotNull(iterable, "Filters may not be null");
        return new zzr(zzx.zzhcg, iterable);
    }

    public static Filter contains(SearchableMetadataField<String> searchableMetadataField, String str) {
        zzbq.checkNotNull(searchableMetadataField, "Field may not be null.");
        zzbq.checkNotNull(str, "Value may not be null.");
        return new zzb(zzx.zzhcj, searchableMetadataField, str);
    }

    public static Filter eq(CustomPropertyKey customPropertyKey, String str) {
        zzbq.checkNotNull(customPropertyKey, "Custom property key may not be null.");
        zzbq.checkNotNull(str, "Custom property value may not be null.");
        return new zzn(SearchableField.zzhbg, new AppVisibleCustomProperties.zza().zza(customPropertyKey, str).zzaqw());
    }

    public static <T> Filter eq(SearchableMetadataField<T> searchableMetadataField, T t) {
        zzbq.checkNotNull(searchableMetadataField, "Field may not be null.");
        zzbq.checkNotNull(t, "Value may not be null.");
        return new zzb(zzx.zzhcb, searchableMetadataField, t);
    }

    public static <T extends Comparable<T>> Filter greaterThan(SearchableOrderedMetadataField<T> searchableOrderedMetadataField, T t) {
        zzbq.checkNotNull(searchableOrderedMetadataField, "Field may not be null.");
        zzbq.checkNotNull(t, "Value may not be null.");
        return new zzb(zzx.zzhce, searchableOrderedMetadataField, t);
    }

    public static <T extends Comparable<T>> Filter greaterThanEquals(SearchableOrderedMetadataField<T> searchableOrderedMetadataField, T t) {
        zzbq.checkNotNull(searchableOrderedMetadataField, "Field may not be null.");
        zzbq.checkNotNull(t, "Value may not be null.");
        return new zzb(zzx.zzhcf, searchableOrderedMetadataField, t);
    }

    public static <T> Filter in(SearchableCollectionMetadataField<T> searchableCollectionMetadataField, T t) {
        zzbq.checkNotNull(searchableCollectionMetadataField, "Field may not be null.");
        zzbq.checkNotNull(t, "Value may not be null.");
        return new zzp(searchableCollectionMetadataField, t);
    }

    public static <T extends Comparable<T>> Filter lessThan(SearchableOrderedMetadataField<T> searchableOrderedMetadataField, T t) {
        zzbq.checkNotNull(searchableOrderedMetadataField, "Field may not be null.");
        zzbq.checkNotNull(t, "Value may not be null.");
        return new zzb(zzx.zzhcc, searchableOrderedMetadataField, t);
    }

    public static <T extends Comparable<T>> Filter lessThanEquals(SearchableOrderedMetadataField<T> searchableOrderedMetadataField, T t) {
        zzbq.checkNotNull(searchableOrderedMetadataField, "Field may not be null.");
        zzbq.checkNotNull(t, "Value may not be null.");
        return new zzb(zzx.zzhcd, searchableOrderedMetadataField, t);
    }

    public static Filter not(Filter filter) {
        zzbq.checkNotNull(filter, "Filter may not be null");
        return new zzv(filter);
    }

    public static Filter openedByMe() {
        return new zzd((SearchableMetadataField<?>) SearchableField.LAST_VIEWED_BY_ME);
    }

    public static Filter or(Filter filter, Filter... filterArr) {
        zzbq.checkNotNull(filter, "Filter may not be null.");
        zzbq.checkNotNull(filterArr, "Additional filters may not be null.");
        return new zzr(zzx.zzhch, filter, filterArr);
    }

    public static Filter or(Iterable<Filter> iterable) {
        zzbq.checkNotNull(iterable, "Filters may not be null");
        return new zzr(zzx.zzhch, iterable);
    }

    public static Filter ownedByMe() {
        return new zzz();
    }

    public static Filter sharedWithMe() {
        return new zzd((SearchableMetadataField<?>) SearchableField.zzhbf);
    }
}
