package com.hjq.base.action;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.drive.DriveFile;

public interface ActivityAction {
    Activity getActivity();

    Context getContext();

    void startActivity(Intent intent);

    void startActivity(Class<? extends Activity> cls);

    /* renamed from: com.hjq.base.action.ActivityAction$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        /* JADX WARNING: Removed duplicated region for block: B:5:0x000b  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static android.app.Activity $default$getActivity(com.hjq.base.action.ActivityAction r3) {
            /*
                android.content.Context r0 = r3.getContext()
            L_0x0004:
                boolean r1 = r0 instanceof android.app.Activity
                if (r1 == 0) goto L_0x000b
                android.app.Activity r0 = (android.app.Activity) r0
                return r0
            L_0x000b:
                boolean r1 = r0 instanceof android.content.ContextWrapper
                r2 = 0
                if (r1 == 0) goto L_0x0018
                android.content.ContextWrapper r0 = (android.content.ContextWrapper) r0
                android.content.Context r0 = r0.getBaseContext()
                if (r0 != 0) goto L_0x0004
            L_0x0018:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.hjq.base.action.ActivityAction.CC.$default$getActivity(com.hjq.base.action.ActivityAction):android.app.Activity");
        }

        public static void $default$startActivity(ActivityAction _this, Class cls) {
            _this.startActivity(new Intent(_this.getContext(), cls));
        }

        public static void $default$startActivity(ActivityAction _this, Intent intent) {
            if (!(_this.getContext() instanceof Activity)) {
                intent.addFlags(DriveFile.MODE_READ_ONLY);
            }
            _this.getContext().startActivity(intent);
        }
    }
}
