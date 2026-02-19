package com.google.android.gms.internal;

import android.app.Service;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.support.v4.media.session.PlaybackStateCompat;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.zzd;
import com.google.android.gms.cast.framework.zzab;
import com.google.android.gms.cast.framework.zzh;
import com.google.android.gms.cast.framework.zzj;
import com.google.android.gms.cast.framework.zzl;
import com.google.android.gms.cast.framework.zzr;
import com.google.android.gms.cast.framework.zzt;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.search.SearchAuth;
import java.util.Map;

public final class zzbae {
    private static final zzbei zzeui = new zzbei("CastDynamiteModule");

    public static zzd zza(Service service, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, CastMediaOptions castMediaOptions) {
        try {
            return zzby(service.getApplicationContext()).zza(zzn.zzz(service), iObjectWrapper, iObjectWrapper2, castMediaOptions);
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "newMediaNotificationServiceImpl", zzbah.class.getSimpleName());
            return null;
        }
    }

    public static zzj zza(Context context, CastOptions castOptions, zzbaj zzbaj, Map<String, IBinder> map) {
        try {
            return zzby(context).zza(zzn.zzz(context.getApplicationContext()), castOptions, zzbaj, (Map) map);
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "newCastContextImpl", zzbah.class.getSimpleName());
            return null;
        }
    }

    public static zzl zza(Context context, CastOptions castOptions, IObjectWrapper iObjectWrapper, zzh zzh) {
        try {
            return zzby(context).zza(castOptions, iObjectWrapper, zzh);
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "newCastSessionImpl", zzbah.class.getSimpleName());
            return null;
        }
    }

    public static zzr zza(Service service, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) {
        try {
            return zzby(service.getApplicationContext()).zzc(zzn.zzz(service), iObjectWrapper, iObjectWrapper2);
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "newReconnectionServiceImpl", zzbah.class.getSimpleName());
            return null;
        }
    }

    public static zzt zza(Context context, String str, String str2, zzab zzab) {
        try {
            return zzby(context).zza(str, str2, zzab);
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "newSessionImpl", zzbah.class.getSimpleName());
            return null;
        }
    }

    public static zzbbe zza(Context context, AsyncTask<Uri, Long, Bitmap> asyncTask, zzbbg zzbbg, int i, int i2, boolean z, long j, int i3, int i4, int i5) {
        try {
            return zzby(context.getApplicationContext()).zza(zzn.zzz(asyncTask), zzbbg, i, i2, z, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE, 5, 333, SearchAuth.StatusCodes.AUTH_DISABLED);
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "newFetchBitmapTaskImpl", zzbah.class.getSimpleName());
            return null;
        }
    }

    private static zzbah zzby(Context context) {
        try {
            IBinder zzhk = DynamiteModule.zza(context, DynamiteModule.zzhdi, "com.google.android.gms.cast.framework.dynamite").zzhk("com.google.android.gms.cast.framework.internal.CastDynamiteModuleImpl");
            if (zzhk == null) {
                return null;
            }
            IInterface queryLocalInterface = zzhk.queryLocalInterface("com.google.android.gms.cast.framework.internal.ICastDynamiteModule");
            return queryLocalInterface instanceof zzbah ? (zzbah) queryLocalInterface : new zzbai(zzhk);
        } catch (DynamiteModule.zzc e) {
            throw new RuntimeException(e);
        }
    }
}
