package com.hjq.toast;

import android.app.Application;
import android.content.res.Resources;
import com.hjq.toast.config.IToastInterceptor;
import com.hjq.toast.config.IToastStrategy;
import com.hjq.toast.config.IToastStyle;
import com.hjq.toast.style.BlackToastStyle;
import com.hjq.toast.style.LocationToastStyle;
import com.hjq.toast.style.ViewToastStyle;

public final class ToastUtils {
    private static Application sApplication;
    private static Boolean sDebugMode;
    private static IToastInterceptor sToastInterceptor;
    private static IToastStrategy sToastStrategy;
    private static IToastStyle<?> sToastStyle;

    private ToastUtils() {
    }

    public static void init(Application application) {
        init(application, sToastStyle);
    }

    public static void init(Application application, IToastStyle<?> iToastStyle) {
        sApplication = application;
        if (sToastStrategy == null) {
            setStrategy(new ToastStrategy());
        }
        if (iToastStyle == null) {
            iToastStyle = new BlackToastStyle();
        }
        setStyle(iToastStyle);
    }

    public static boolean isInit() {
        return (sApplication == null || sToastStrategy == null || sToastStyle == null) ? false : true;
    }

    public static void show(Object obj) {
        show((CharSequence) obj != null ? obj.toString() : "null");
    }

    public static void debugShow(Object obj) {
        if (isDebugMode()) {
            show(obj);
        }
    }

    public static void show(int i) {
        try {
            show(sApplication.getResources().getText(i));
        } catch (Resources.NotFoundException unused) {
            show((CharSequence) String.valueOf(i));
        }
    }

    public static void debugShow(int i) {
        if (isDebugMode()) {
            show(i);
        }
    }

    public static void show(CharSequence charSequence) {
        if (charSequence != null && charSequence.length() != 0) {
            IToastInterceptor iToastInterceptor = sToastInterceptor;
            if (iToastInterceptor == null || !iToastInterceptor.intercept(charSequence)) {
                sToastStrategy.showToast(charSequence);
            }
        }
    }

    public static void debugShow(CharSequence charSequence) {
        if (isDebugMode()) {
            show(charSequence);
        }
    }

    public static void cancel() {
        sToastStrategy.cancelToast();
    }

    public static void setGravity(int i) {
        setGravity(i, 0, 0);
    }

    public static void setGravity(int i, int i2, int i3) {
        setGravity(i, i2, i3, 0.0f, 0.0f);
    }

    public static void setGravity(int i, int i2, int i3, float f, float f2) {
        sToastStrategy.bindStyle(new LocationToastStyle(sToastStyle, i, i2, i3, f, f2));
    }

    public static void setView(int i) {
        if (i > 0) {
            setStyle(new ViewToastStyle(i, sToastStyle));
        }
    }

    public static void setStyle(IToastStyle<?> iToastStyle) {
        sToastStyle = iToastStyle;
        sToastStrategy.bindStyle(iToastStyle);
    }

    public static IToastStyle<?> getStyle() {
        return sToastStyle;
    }

    public static void setStrategy(IToastStrategy iToastStrategy) {
        sToastStrategy = iToastStrategy;
        iToastStrategy.registerStrategy(sApplication);
    }

    public static IToastStrategy getStrategy() {
        return sToastStrategy;
    }

    public static void setInterceptor(IToastInterceptor iToastInterceptor) {
        sToastInterceptor = iToastInterceptor;
    }

    public static IToastInterceptor getInterceptor() {
        return sToastInterceptor;
    }

    public static void setDebugMode(boolean z) {
        sDebugMode = Boolean.valueOf(z);
    }

    private static boolean isDebugMode() {
        if (sDebugMode == null) {
            sDebugMode = Boolean.valueOf((sApplication.getApplicationInfo().flags & 2) != 0);
        }
        return sDebugMode.booleanValue();
    }
}
