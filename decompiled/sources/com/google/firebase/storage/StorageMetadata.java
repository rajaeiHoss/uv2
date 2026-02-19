package com.google.firebase.storage;

import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzfbd;
import com.google.android.gms.internal.zzfbh;
import com.google.android.gms.internal.zzfbm;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jivesoftware.smackx.workgroup.MetaData;
import org.json.JSONException;
import org.json.JSONObject;

public class StorageMetadata {
    /* access modifiers changed from: private */
    public String mPath;
    /* access modifiers changed from: private */
    public StorageReference zzotm;
    private FirebaseStorage zzouf;
    /* access modifiers changed from: private */
    public String zzoug;
    /* access modifiers changed from: private */
    public String zzouh;
    /* access modifiers changed from: private */
    public zza<String> zzoui;
    /* access modifiers changed from: private */
    public String zzouj;
    /* access modifiers changed from: private */
    public String zzouk;
    /* access modifiers changed from: private */
    public String zzoul;
    /* access modifiers changed from: private */
    public long zzoum;
    /* access modifiers changed from: private */
    public String zzoun;
    /* access modifiers changed from: private */
    public zza<String> zzouo;
    /* access modifiers changed from: private */
    public zza<String> zzoup;
    /* access modifiers changed from: private */
    public zza<String> zzouq;
    /* access modifiers changed from: private */
    public zza<String> zzour;
    /* access modifiers changed from: private */
    public zza<Map<String, String>> zzous;
    private String[] zzout;

    public static class Builder {
        private StorageMetadata zzouu;
        private boolean zzouv;

        public Builder() {
            this.zzouu = new StorageMetadata();
        }

        public Builder(StorageMetadata storageMetadata) {
            this.zzouu = new StorageMetadata(storageMetadata, false);
        }

        private Builder(JSONObject jSONObject) throws JSONException {
            this.zzouu = new StorageMetadata();
            if (jSONObject != null) {
                zzab(jSONObject);
                this.zzouv = true;
            }
        }

        Builder(JSONObject jSONObject, StorageReference storageReference) throws JSONException {
            this(jSONObject);
            StorageReference unused = this.zzouu.zzotm = storageReference;
        }

        private final void zzab(JSONObject jSONObject) throws JSONException {
            String unused = this.zzouu.zzouh = jSONObject.optString("generation");
            String unused2 = this.zzouu.mPath = jSONObject.optString("name");
            String unused3 = this.zzouu.zzoug = jSONObject.optString("bucket");
            String unused4 = this.zzouu.zzouj = jSONObject.optString("metageneration");
            String unused5 = this.zzouu.zzouk = jSONObject.optString("timeCreated");
            String unused6 = this.zzouu.zzoul = jSONObject.optString("updated");
            long unused7 = this.zzouu.zzoum = jSONObject.optLong("size");
            String unused8 = this.zzouu.zzoun = jSONObject.optString("md5Hash");
            this.zzouu.zzsm(jSONObject.optString("downloadTokens"));
            if (jSONObject.has(MetaData.ELEMENT_NAME) && !jSONObject.isNull(MetaData.ELEMENT_NAME)) {
                JSONObject jSONObject2 = jSONObject.getJSONObject(MetaData.ELEMENT_NAME);
                Iterator<String> keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    setCustomMetadata(next, jSONObject2.getString(next));
                }
            }
            String zze = zze(jSONObject, "contentType");
            if (zze != null) {
                setContentType(zze);
            }
            String zze2 = zze(jSONObject, "cacheControl");
            if (zze2 != null) {
                setCacheControl(zze2);
            }
            String zze3 = zze(jSONObject, "contentDisposition");
            if (zze3 != null) {
                setContentDisposition(zze3);
            }
            String zze4 = zze(jSONObject, "contentEncoding");
            if (zze4 != null) {
                setContentEncoding(zze4);
            }
            String zze5 = zze(jSONObject, "contentLanguage");
            if (zze5 != null) {
                setContentLanguage(zze5);
            }
        }

        private static String zze(JSONObject jSONObject, String str) throws JSONException {
            if (!jSONObject.has(str) || jSONObject.isNull(str)) {
                return null;
            }
            return jSONObject.getString(str);
        }

        public StorageMetadata build() {
            return new StorageMetadata(this.zzouu, this.zzouv);
        }

        public Builder setCacheControl(String str) {
            zza unused = this.zzouu.zzouo = zza.zzco(str);
            return this;
        }

        public Builder setContentDisposition(String str) {
            zza unused = this.zzouu.zzoup = zza.zzco(str);
            return this;
        }

        public Builder setContentEncoding(String str) {
            zza unused = this.zzouu.zzouq = zza.zzco(str);
            return this;
        }

        public Builder setContentLanguage(String str) {
            zza unused = this.zzouu.zzour = zza.zzco(str);
            return this;
        }

        public Builder setContentType(String str) {
            zza unused = this.zzouu.zzoui = zza.zzco(str);
            return this;
        }

        public Builder setCustomMetadata(String str, String str2) {
            if (!this.zzouu.zzous.zzcnv()) {
                zza unused = this.zzouu.zzous = zza.zzco(new HashMap());
            }
            ((Map) this.zzouu.zzous.getValue()).put(str, str2);
            return this;
        }
    }

    static class zza<T> {
        private final T value;
        private final boolean zzouw;

        private zza(T t, boolean z) {
            this.zzouw = z;
            this.value = t;
        }

        static <T> zza<T> zzcn(T t) {
            return new zza<>(t, false);
        }

        static <T> zza<T> zzco(T t) {
            return new zza<>(t, true);
        }

        /* access modifiers changed from: package-private */
        public final T getValue() {
            return this.value;
        }

        /* access modifiers changed from: package-private */
        public final boolean zzcnv() {
            return this.zzouw;
        }
    }

    public StorageMetadata() {
        this.mPath = null;
        this.zzouf = null;
        this.zzotm = null;
        this.zzoug = null;
        this.zzouh = null;
        this.zzoui = zza.zzcn("");
        this.zzouj = null;
        this.zzouk = null;
        this.zzoul = null;
        this.zzoun = null;
        this.zzouo = zza.zzcn("");
        this.zzoup = zza.zzcn("");
        this.zzouq = zza.zzcn("");
        this.zzour = zza.zzcn("");
        this.zzous = zza.zzcn(Collections.emptyMap());
        this.zzout = null;
    }

    private StorageMetadata(StorageMetadata storageMetadata, boolean z) {
        this.mPath = null;
        this.zzouf = null;
        this.zzotm = null;
        this.zzoug = null;
        this.zzouh = null;
        this.zzoui = zza.zzcn("");
        this.zzouj = null;
        this.zzouk = null;
        this.zzoul = null;
        this.zzoun = null;
        this.zzouo = zza.zzcn("");
        this.zzoup = zza.zzcn("");
        this.zzouq = zza.zzcn("");
        this.zzour = zza.zzcn("");
        this.zzous = zza.zzcn(Collections.emptyMap());
        this.zzout = null;
        zzbq.checkNotNull(storageMetadata);
        this.mPath = storageMetadata.mPath;
        this.zzouf = storageMetadata.zzouf;
        this.zzotm = storageMetadata.zzotm;
        this.zzoug = storageMetadata.zzoug;
        this.zzoui = storageMetadata.zzoui;
        this.zzouo = storageMetadata.zzouo;
        this.zzoup = storageMetadata.zzoup;
        this.zzouq = storageMetadata.zzouq;
        this.zzour = storageMetadata.zzour;
        this.zzous = storageMetadata.zzous;
        this.zzout = storageMetadata.zzout;
        if (z) {
            this.zzoun = storageMetadata.zzoun;
            this.zzoum = storageMetadata.zzoum;
            this.zzoul = storageMetadata.zzoul;
            this.zzouk = storageMetadata.zzouk;
            this.zzouj = storageMetadata.zzouj;
            this.zzouh = storageMetadata.zzouh;
        }
    }

    /* access modifiers changed from: private */
    public final void zzsm(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.zzout = str.split(",");
        }
    }

    public String getBucket() {
        return this.zzoug;
    }

    public String getCacheControl() {
        return this.zzouo.getValue();
    }

    public String getContentDisposition() {
        return this.zzoup.getValue();
    }

    public String getContentEncoding() {
        return this.zzouq.getValue();
    }

    public String getContentLanguage() {
        return this.zzour.getValue();
    }

    public String getContentType() {
        return this.zzoui.getValue();
    }

    public long getCreationTimeMillis() {
        return zzfbh.zzsq(this.zzouk);
    }

    public String getCustomMetadata(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return (String) this.zzous.getValue().get(str);
    }

    public Set<String> getCustomMetadataKeys() {
        return this.zzous.getValue().keySet();
    }

    public Uri getDownloadUrl() {
        List<Uri> downloadUrls = getDownloadUrls();
        if (downloadUrls == null || downloadUrls.size() <= 0) {
            return null;
        }
        return downloadUrls.get(0);
    }

    public List<Uri> getDownloadUrls() {
        StorageReference storageReference;
        ArrayList arrayList = new ArrayList();
        if (!(this.zzout == null || (storageReference = this.zzotm) == null)) {
            try {
                String zzu = zzfbm.zzi(storageReference.getStorage().getApp()).zzu(this.zzotm.zzcnx());
                if (!TextUtils.isEmpty(zzu)) {
                    for (String str : this.zzout) {
                        if (!TextUtils.isEmpty(str)) {
                            StringBuilder sb = new StringBuilder(String.valueOf(zzu).length() + 17 + String.valueOf(str).length());
                            sb.append(zzu);
                            sb.append("?alt=media&token=");
                            sb.append(str);
                            arrayList.add(Uri.parse(sb.toString()));
                        }
                    }
                }
            } catch (RemoteException e) {
                Log.e("StorageMetadata", "Unexpected error getting DownloadUrls.", e);
            }
        }
        return arrayList;
    }

    public String getGeneration() {
        return this.zzouh;
    }

    public String getMd5Hash() {
        return this.zzoun;
    }

    public String getMetadataGeneration() {
        return this.zzouj;
    }

    public String getName() {
        String path = getPath();
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        int lastIndexOf = path.lastIndexOf(47);
        return lastIndexOf != -1 ? path.substring(lastIndexOf + 1) : path;
    }

    public String getPath() {
        String str = this.mPath;
        return str != null ? str : "";
    }

    public StorageReference getReference() {
        StorageReference storageReference = this.zzotm;
        if (storageReference != null || this.zzouf == null) {
            return storageReference;
        }
        String bucket = getBucket();
        String path = getPath();
        if (TextUtils.isEmpty(bucket) || TextUtils.isEmpty(path)) {
            return null;
        }
        try {
            return new StorageReference(new Uri.Builder().scheme("gs").authority(bucket).encodedPath(zzfbd.zzsn(path)).build(), this.zzouf);
        } catch (UnsupportedEncodingException e) {
            StringBuilder sb = new StringBuilder(String.valueOf(bucket).length() + 38 + String.valueOf(path).length());
            sb.append("Unable to create a valid default Uri. ");
            sb.append(bucket);
            sb.append(path);
            Log.e("StorageMetadata", sb.toString(), e);
            throw new IllegalStateException(e);
        }
    }

    public long getSizeBytes() {
        return this.zzoum;
    }

    public long getUpdatedTimeMillis() {
        return zzfbh.zzsq(this.zzoul);
    }

    /* access modifiers changed from: package-private */
    public final JSONObject zzcnu() throws JSONException {
        HashMap hashMap = new HashMap();
        if (this.zzoui.zzcnv()) {
            hashMap.put("contentType", getContentType());
        }
        if (this.zzous.zzcnv()) {
            hashMap.put(MetaData.ELEMENT_NAME, new JSONObject(this.zzous.getValue()));
        }
        if (this.zzouo.zzcnv()) {
            hashMap.put("cacheControl", getCacheControl());
        }
        if (this.zzoup.zzcnv()) {
            hashMap.put("contentDisposition", getContentDisposition());
        }
        if (this.zzouq.zzcnv()) {
            hashMap.put("contentEncoding", getContentEncoding());
        }
        if (this.zzour.zzcnv()) {
            hashMap.put("contentLanguage", getContentLanguage());
        }
        return new JSONObject(hashMap);
    }
}
