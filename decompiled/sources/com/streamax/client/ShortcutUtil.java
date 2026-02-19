package com.streamax.client;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import com.zycs.UView.R;

public class ShortcutUtil {
    private static SharedPreferences getSharedPreferences(String str, int i) {
        return null;
    }

    public static void createShortCut(Activity activity, int i, int i2) {
        if (!hasShortCut(activity)) {
            SharedPreferences sharedPreferences = activity.getSharedPreferences("viewcam", 0);
            if (sharedPreferences == null || !sharedPreferences.getBoolean("icon", false)) {
                Intent intent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
                intent.putExtra("duplicate", false);
                intent.putExtra("android.intent.extra.shortcut.NAME", activity.getString(i2));
                intent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(activity.getApplicationContext(), i));
                intent.putExtra("android.intent.extra.shortcut.INTENT", new Intent(activity.getApplicationContext(), activity.getClass()));
                activity.sendBroadcast(intent);
                sharedPreferences.edit().putBoolean("icon", true).commit();
            }
        }
    }

    public static boolean hasShortCut(Context context) {
        System.out.println(getSystemVersion());
        Cursor query = context.getContentResolver().query(Uri.parse(getSystemVersion() < 8 ? "content://com.android.launcher.settings/favorites?notify=true" : "content://com.android.launcher2.settings/favorites?notify=true"), (String[]) null, "title=?", new String[]{context.getString(R.string.app_name)}, (String) null);
        if (query == null || !query.moveToFirst()) {
            return false;
        }
        query.close();
        return true;
    }

    private static int getSystemVersion() {
        return Build.VERSION.SDK_INT;
    }
}
