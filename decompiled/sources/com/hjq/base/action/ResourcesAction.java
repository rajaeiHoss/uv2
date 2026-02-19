package com.hjq.base.action;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;

public interface ResourcesAction {
    int getColor(int i);

    Context getContext();

    Drawable getDrawable(int i);

    Resources getResources();

    String getString(int i);

    String getString(int i, Object... objArr);

    <S> S getSystemService(Class<S> cls);

    /* renamed from: com.hjq.base.action.ResourcesAction$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static Resources $default$getResources(ResourcesAction _this) {
            return _this.getContext().getResources();
        }

        public static String $default$getString(ResourcesAction _this, int i) {
            return _this.getContext().getString(i);
        }

        public static String $default$getString(ResourcesAction _this, int i, Object... objArr) {
            return _this.getResources().getString(i, objArr);
        }

        public static Drawable $default$getDrawable(ResourcesAction _this, int i) {
            return ContextCompat.getDrawable(_this.getContext(), i);
        }

        public static int $default$getColor(ResourcesAction _this, int i) {
            return ContextCompat.getColor(_this.getContext(), i);
        }

        public static <S> Object $default$getSystemService(ResourcesAction _this, Class cls) {
            return ContextCompat.getSystemService(_this.getContext(), cls);
        }
    }
}
