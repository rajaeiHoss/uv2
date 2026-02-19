package com.google.android.gms.cast.framework;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import androidx.core.view.MenuItemCompat;
import androidx.mediarouter.app.MediaRouteActionProvider;
import androidx.mediarouter.app.MediaRouteButton;
import androidx.mediarouter.app.MediaRouteDialogFactory;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbei;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class CastButtonFactory {
    private static final zzbei zzeus = new zzbei("CastButtonFactory");
    private static final List<WeakReference<MenuItem>> zzezr = new ArrayList();
    private static final List<WeakReference<MediaRouteButton>> zzezs = new ArrayList();

    private CastButtonFactory() {
    }

    public static MenuItem setUpMediaRouteButton(Context context, Menu menu, int i) {
        return zza(context, menu, i, (MediaRouteDialogFactory) null);
    }

    public static void setUpMediaRouteButton(Context context, MediaRouteButton mediaRouteButton) {
        zzbq.zzgn("Must be called from the main thread.");
        if (mediaRouteButton != null) {
            zza(context, mediaRouteButton, (MediaRouteDialogFactory) null);
            zzezs.add(new WeakReference(mediaRouteButton));
        }
    }

    private static MenuItem zza(Context context, Menu menu, int i, MediaRouteDialogFactory mediaRouteDialogFactory) {
        zzbq.zzgn("Must be called from the main thread.");
        zzbq.checkNotNull(menu);
        MenuItem findItem = menu.findItem(i);
        if (findItem != null) {
            try {
                zza(context, findItem, (MediaRouteDialogFactory) null);
                zzezr.add(new WeakReference(findItem));
                return findItem;
            } catch (IllegalArgumentException unused) {
                throw new IllegalArgumentException(String.format(Locale.ROOT, "menu item with ID %d doesn't have a MediaRouteActionProvider.", new Object[]{Integer.valueOf(i)}));
            }
        } else {
            throw new IllegalArgumentException(String.format(Locale.ROOT, "menu doesn't contain a menu item whose ID is %d.", new Object[]{Integer.valueOf(i)}));
        }
    }

    private static void zza(Context context, MenuItem menuItem, MediaRouteDialogFactory mediaRouteDialogFactory) throws IllegalArgumentException {
        zzbq.zzgn("Must be called from the main thread.");
        MediaRouteActionProvider mediaRouteActionProvider = (MediaRouteActionProvider) MenuItemCompat.getActionProvider(menuItem);
        if (mediaRouteActionProvider != null) {
            CastContext zzbu = CastContext.zzbu(context);
            if (zzbu != null) {
                mediaRouteActionProvider.setRouteSelector(zzbu.getMergedSelector());
                return;
            }
            return;
        }
        throw new IllegalArgumentException();
    }

    private static void zza(Context context, MediaRouteButton mediaRouteButton, MediaRouteDialogFactory mediaRouteDialogFactory) {
        zzbq.zzgn("Must be called from the main thread.");
        CastContext zzbu = CastContext.zzbu(context);
        if (zzbu != null) {
            mediaRouteButton.setRouteSelector(zzbu.getMergedSelector());
        }
    }

    public static void zzbt(Context context) {
        for (WeakReference next : zzezr) {
            try {
                if (next.get() != null) {
                    zza(context, (MenuItem) next.get(), (MediaRouteDialogFactory) null);
                }
            } catch (IllegalArgumentException e) {
                zzeus.zzf("Unexpected exception when refreshing MediaRouteSelectors for Cast buttons", e);
            }
        }
        for (WeakReference next2 : zzezs) {
            if (next2.get() != null) {
                zza(context, (MediaRouteButton) next2.get(), (MediaRouteDialogFactory) null);
            }
        }
    }
}
