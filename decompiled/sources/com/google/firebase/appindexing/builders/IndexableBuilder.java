package com.google.firebase.appindexing.builders;

import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.plus.PlusShare;
import com.google.firebase.appindexing.FirebaseAppIndexingInvalidArgumentException;
import com.google.firebase.appindexing.Indexable;
import com.google.firebase.appindexing.builders.IndexableBuilder;
import com.google.firebase.appindexing.internal.Thing;
import com.google.firebase.appindexing.internal.zzw;
import java.util.Arrays;

public class IndexableBuilder<T extends IndexableBuilder<?>> {
    private final String type;
    private String url;
    private final Bundle zzegs = new Bundle();
    private Thing.zza zzmnz;

    protected IndexableBuilder(String str) {
        zzbq.checkNotNull(str);
        zzbq.zzgv(str);
        this.type = str;
    }

    private final T zza(String str, Thing... thingArr) {
        zzbq.checkNotNull(str);
        zzbq.checkNotNull(thingArr);
        if (thingArr.length > 0) {
            int i = 0;
            for (int i2 = 0; i2 < thingArr.length; i2++) {
                thingArr[i] = thingArr[i2];
                if (thingArr[i2] == null) {
                    StringBuilder sb = new StringBuilder(58);
                    sb.append("Thing at ");
                    sb.append(i2);
                    sb.append(" is null and is ignored by put method.");
                    zzw.zzoz(sb.toString());
                } else {
                    i++;
                }
            }
            if (i > 0) {
                this.zzegs.putParcelableArray(str, (Parcelable[]) zzd((Thing[]) Arrays.copyOfRange(thingArr, 0, i)));
            }
        } else {
            zzw.zzoz("Thing array is empty and is ignored by put method.");
        }
        return (T) this;
    }

    public static void zza(Bundle bundle, String str, long... jArr) {
        zzbq.checkNotNull(str);
        zzbq.checkNotNull(jArr);
        if (jArr.length > 0) {
            if (jArr.length >= 100) {
                zzw.zzoz("Input Array of elements is too big, cutting off.");
                jArr = Arrays.copyOf(jArr, 100);
            }
            bundle.putLongArray(str, jArr);
            return;
        }
        zzw.zzoz("Long array is empty and is ignored by put method.");
    }

    private static <S> S[] zzd(S[] sArr) {
        if (sArr.length < 100) {
            return sArr;
        }
        zzw.zzoz("Input Array of elements is too big, cutting off.");
        return Arrays.copyOf(sArr, 100);
    }

    public final Indexable build() {
        Bundle bundle = new Bundle(this.zzegs);
        Thing.zza zza = this.zzmnz;
        if (zza == null) {
            zza = new Indexable.Metadata.Builder().zzbte();
        }
        return new Thing(bundle, zza, this.url, this.type);
    }

    public T put(String str, long... jArr) {
        zza(this.zzegs, str, jArr);
        return (T) this;
    }

    public T put(String str, Indexable... indexableArr) throws FirebaseAppIndexingInvalidArgumentException {
        zzbq.checkNotNull(str);
        zzbq.checkNotNull(indexableArr);
        Thing[] thingArr = new Thing[indexableArr.length];
        int i = 0;
        while (i < indexableArr.length) {
            if (indexableArr[i] == null || (indexableArr[i] instanceof Thing)) {
                thingArr[i] = (Thing) indexableArr[i];
                i++;
            } else {
                throw new FirebaseAppIndexingInvalidArgumentException("Invalid Indexable encountered. Use Indexable.Builder or convenience methods under Indexables to create the Indexable.");
            }
        }
        zza(str, thingArr);
        return (T) this;
    }

    /* access modifiers changed from: protected */
    public <S extends IndexableBuilder> T put(String str, S... sArr) {
        zzbq.checkNotNull(str);
        zzbq.checkNotNull(sArr);
        if (sArr.length > 0) {
            int length = sArr.length;
            Thing[] thingArr = new Thing[length];
            for (int i = 0; i < sArr.length; i++) {
                if (sArr[i] == null) {
                    StringBuilder sb = new StringBuilder(60);
                    sb.append("Builder at ");
                    sb.append(i);
                    sb.append(" is null and is ignored by put method.");
                    zzw.zzoz(sb.toString());
                } else {
                    thingArr[i] = (Thing) sArr[i].build();
                }
            }
            if (length > 0) {
                zza(str, thingArr);
            }
        } else {
            zzw.zzoz("Builder array is empty and is ignored by put method.");
        }
        return (T) this;
    }

    public T put(String str, String... strArr) {
        Bundle bundle = this.zzegs;
        zzbq.checkNotNull(str);
        zzbq.checkNotNull(strArr);
        String[] strArr2 = (String[]) Arrays.copyOf(strArr, strArr.length);
        if (strArr2.length > 0) {
            int i = 0;
            for (int i2 = 0; i2 < Math.min(strArr2.length, 100); i2++) {
                strArr2[i] = strArr2[i2];
                if (strArr2[i2] == null) {
                    StringBuilder sb = new StringBuilder(59);
                    sb.append("String at ");
                    sb.append(i2);
                    sb.append(" is null and is ignored by put method.");
                    zzw.zzoz(sb.toString());
                } else {
                    int i3 = 20000;
                    if (strArr2[i].length() > 20000) {
                        StringBuilder sb2 = new StringBuilder(53);
                        sb2.append("String at ");
                        sb2.append(i2);
                        sb2.append(" is too long, truncating string.");
                        zzw.zzoz(sb2.toString());
                        String str2 = strArr2[i];
                        if (str2.length() > 20000) {
                            if (Character.isHighSurrogate(str2.charAt(19999)) && Character.isLowSurrogate(str2.charAt(20000))) {
                                i3 = 19999;
                            }
                            str2 = str2.substring(0, i3);
                        }
                        strArr2[i] = str2;
                    }
                    i++;
                }
            }
            if (i > 0) {
                bundle.putStringArray(str, (String[]) zzd((String[]) Arrays.copyOfRange(strArr2, 0, i)));
            }
        } else {
            zzw.zzoz("String array is empty and is ignored by put method.");
        }
        return (T) this;
    }

    public T put(String str, boolean... zArr) {
        Bundle bundle = this.zzegs;
        zzbq.checkNotNull(str);
        zzbq.checkNotNull(zArr);
        if (zArr.length > 0) {
            if (zArr.length >= 100) {
                zzw.zzoz("Input Array of elements is too big, cutting off.");
                zArr = Arrays.copyOf(zArr, 100);
            }
            bundle.putBooleanArray(str, zArr);
        } else {
            zzw.zzoz("Boolean array is empty and is ignored by put method.");
        }
        return (T) this;
    }

    public final T setDescription(String str) {
        zzbq.checkNotNull(str);
        return put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, str);
    }

    public final T setImage(String str) {
        zzbq.checkNotNull(str);
        return put("image", str);
    }

    public final T setKeywords(String... strArr) {
        return put("keywords", strArr);
    }

    public T setMetadata(Indexable.Metadata.Builder builder) {
        zzbq.zza(this.zzmnz == null, (Object) "setMetadata may only be called once");
        zzbq.checkNotNull(builder);
        this.zzmnz = builder.zzbte();
        return (T) this;
    }

    public final T setName(String str) {
        zzbq.checkNotNull(str);
        return put("name", str);
    }

    public final T setSameAs(String str) {
        zzbq.checkNotNull(str);
        return put("sameAs", str);
    }

    public final T setUrl(String str) {
        zzbq.checkNotNull(str);
        this.url = str;
        return (T) this;
    }
}
