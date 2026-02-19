package com.google.firebase.storage;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzfbh;
import com.google.firebase.FirebaseApp;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class FirebaseStorage {
    private static final Map<String, Map<String, FirebaseStorage>> zzotu = new HashMap();
    private final FirebaseApp zzotv;
    private final String zzotw;
    private long zzotx = 600000;
    private long zzoty = 600000;
    private long zzotz = 120000;

    private FirebaseStorage(String str, FirebaseApp firebaseApp) {
        this.zzotw = str;
        this.zzotv = firebaseApp;
    }

    public static FirebaseStorage getInstance() {
        FirebaseApp instance = FirebaseApp.getInstance();
        zzbq.checkArgument(instance != null, "You must call FirebaseApp.initialize() first.");
        return getInstance(instance);
    }

    public static FirebaseStorage getInstance(FirebaseApp firebaseApp) {
        zzbq.checkArgument(firebaseApp != null, "Null is not a valid value for the FirebaseApp.");
        String storageBucket = firebaseApp.getOptions().getStorageBucket();
        if (storageBucket == null) {
            return zza(firebaseApp, (Uri) null);
        }
        try {
            String valueOf = String.valueOf(firebaseApp.getOptions().getStorageBucket());
            return zza(firebaseApp, zzfbh.zzg(firebaseApp, valueOf.length() != 0 ? "gs://".concat(valueOf) : new String("gs://")));
        } catch (UnsupportedEncodingException e) {
            String valueOf2 = String.valueOf(storageBucket);
            Log.e("FirebaseStorage", valueOf2.length() != 0 ? "Unable to parse bucket:".concat(valueOf2) : new String("Unable to parse bucket:"), e);
            throw new IllegalArgumentException("The storage Uri could not be parsed.");
        }
    }

    public static FirebaseStorage getInstance(FirebaseApp firebaseApp, String str) {
        zzbq.checkArgument(firebaseApp != null, "Null is not a valid value for the FirebaseApp.");
        if (str.toLowerCase().startsWith("gs://")) {
            try {
                return zza(firebaseApp, zzfbh.zzg(firebaseApp, str));
            } catch (UnsupportedEncodingException e) {
                String valueOf = String.valueOf(str);
                Log.e("FirebaseStorage", valueOf.length() != 0 ? "Unable to parse url:".concat(valueOf) : new String("Unable to parse url:"), e);
                throw new IllegalArgumentException("The storage Uri could not be parsed.");
            }
        } else {
            throw new IllegalArgumentException("Please use a gs:// URL for your Firebase Storage bucket.");
        }
    }

    public static FirebaseStorage getInstance(String str) {
        FirebaseApp instance = FirebaseApp.getInstance();
        zzbq.checkArgument(instance != null, "You must call FirebaseApp.initialize() first.");
        return getInstance(instance, str);
    }

    private static FirebaseStorage zza(FirebaseApp firebaseApp, Uri uri) {
        FirebaseStorage firebaseStorage;
        String host = uri != null ? uri.getHost() : null;
        if (uri == null || TextUtils.isEmpty(uri.getPath())) {
            Map<String, Map<String, FirebaseStorage>> map = zzotu;
            synchronized (map) {
                Map map2 = map.get(firebaseApp.getName());
                if (map2 == null) {
                    map2 = new HashMap();
                    map.put(firebaseApp.getName(), map2);
                }
                firebaseStorage = (FirebaseStorage) map2.get(host);
                if (firebaseStorage == null) {
                    firebaseStorage = new FirebaseStorage(host, firebaseApp);
                    map2.put(host, firebaseStorage);
                }
            }
            return firebaseStorage;
        }
        throw new IllegalArgumentException("The storage Uri cannot contain a path element.");
    }

    private final StorageReference zzt(Uri uri) {
        zzbq.checkNotNull(uri, "uri must not be null");
        String str = this.zzotw;
        zzbq.checkArgument(TextUtils.isEmpty(str) || uri.getAuthority().equalsIgnoreCase(str), "The supplied bucketname does not match the storage bucket of the current instance.");
        return new StorageReference(uri, this);
    }

    public FirebaseApp getApp() {
        return this.zzotv;
    }

    public long getMaxDownloadRetryTimeMillis() {
        return this.zzoty;
    }

    public long getMaxOperationRetryTimeMillis() {
        return this.zzotz;
    }

    public long getMaxUploadRetryTimeMillis() {
        return this.zzotx;
    }

    public StorageReference getReference() {
        if (!TextUtils.isEmpty(this.zzotw)) {
            return zzt(new Uri.Builder().scheme("gs").authority(this.zzotw).path("/").build());
        }
        throw new IllegalStateException("FirebaseApp was not initialized with a bucket name.");
    }

    public StorageReference getReference(String str) {
        zzbq.checkArgument(!TextUtils.isEmpty(str), "location must not be null or empty");
        String lowerCase = str.toLowerCase();
        if (!lowerCase.startsWith("gs://") && !lowerCase.startsWith("https://") && !lowerCase.startsWith("http://")) {
            return getReference().child(str);
        }
        throw new IllegalArgumentException("location should not be a full URL.");
    }

    public StorageReference getReferenceFromUrl(String str) {
        zzbq.checkArgument(!TextUtils.isEmpty(str), "location must not be null or empty");
        String lowerCase = str.toLowerCase();
        if (lowerCase.startsWith("gs://") || lowerCase.startsWith("https://") || lowerCase.startsWith("http://")) {
            try {
                Uri zzg = zzfbh.zzg(this.zzotv, str);
                if (zzg != null) {
                    return zzt(zzg);
                }
                throw new IllegalArgumentException("The storage Uri could not be parsed.");
            } catch (UnsupportedEncodingException e) {
                String valueOf = String.valueOf(str);
                Log.e("FirebaseStorage", valueOf.length() != 0 ? "Unable to parse location:".concat(valueOf) : new String("Unable to parse location:"), e);
                throw new IllegalArgumentException("The storage Uri could not be parsed.");
            }
        } else {
            throw new IllegalArgumentException("The storage Uri could not be parsed.");
        }
    }

    public void setMaxDownloadRetryTimeMillis(long j) {
        this.zzoty = j;
    }

    public void setMaxOperationRetryTimeMillis(long j) {
        this.zzotz = j;
    }

    public void setMaxUploadRetryTimeMillis(long j) {
        this.zzotx = j;
    }
}
