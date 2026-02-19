package com.streamax.client.action;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

public interface TitleBarAction extends OnTitleBarListener {
    Drawable getLeftIcon();

    CharSequence getLeftTitle();

    Drawable getRightIcon();

    CharSequence getRightTitle();

    TitleBar getTitleBar();

    TitleBar obtainTitleBar(ViewGroup viewGroup);

    void onLeftClick(View view);

    void onRightClick(View view);

    void onTitleClick(View view);

    void setLeftIcon(int i);

    void setLeftIcon(Drawable drawable);

    void setLeftTitle(int i);

    void setLeftTitle(CharSequence charSequence);

    void setRightIcon(int i);

    void setRightIcon(Drawable drawable);

    void setRightTitle(int i);

    void setRightTitle(CharSequence charSequence);

    void setTitle(int i);

    void setTitle(CharSequence charSequence);

    /* renamed from: com.streamax.client.action.TitleBarAction$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onLeftClick(TitleBarAction titleBarAction, View view) {
        }

        public static void $default$onRightClick(TitleBarAction titleBarAction, View view) {
        }

        public static void $default$onTitleClick(TitleBarAction titleBarAction, View view) {
        }

        public static void $default$setTitle(TitleBarAction _this, int i) {
            if (_this.getTitleBar() != null) {
                _this.setTitle((CharSequence) _this.getTitleBar().getResources().getString(i));
            }
        }

        public static void $default$setTitle(TitleBarAction _this, CharSequence charSequence) {
            if (_this.getTitleBar() != null) {
                _this.getTitleBar().setTitle(charSequence);
            }
        }

        public static void $default$setLeftTitle(TitleBarAction _this, int i) {
            if (_this.getTitleBar() != null) {
                _this.getTitleBar().setLeftTitle(i);
            }
        }

        public static void $default$setLeftTitle(TitleBarAction _this, CharSequence charSequence) {
            if (_this.getTitleBar() != null) {
                _this.getTitleBar().setLeftTitle(charSequence);
            }
        }

        public static CharSequence $default$getLeftTitle(TitleBarAction _this) {
            return _this.getTitleBar() != null ? _this.getTitleBar().getLeftTitle() : "";
        }

        public static void $default$setRightTitle(TitleBarAction _this, int i) {
            if (_this.getTitleBar() != null) {
                _this.getTitleBar().setRightTitle(i);
            }
        }

        public static void $default$setRightTitle(TitleBarAction _this, CharSequence charSequence) {
            if (_this.getTitleBar() != null) {
                _this.getTitleBar().setRightTitle(charSequence);
            }
        }

        public static CharSequence $default$getRightTitle(TitleBarAction _this) {
            return _this.getTitleBar() != null ? _this.getTitleBar().getRightTitle() : "";
        }

        public static void $default$setLeftIcon(TitleBarAction _this, int i) {
            if (_this.getTitleBar() != null) {
                _this.getTitleBar().setLeftIcon(i);
            }
        }

        public static void $default$setLeftIcon(TitleBarAction _this, Drawable drawable) {
            if (_this.getTitleBar() != null) {
                _this.getTitleBar().setLeftIcon(drawable);
            }
        }

        public static Drawable $default$getLeftIcon(TitleBarAction _this) {
            if (_this.getTitleBar() != null) {
                return _this.getTitleBar().getLeftIcon();
            }
            return null;
        }

        public static void $default$setRightIcon(TitleBarAction _this, int i) {
            if (_this.getTitleBar() != null) {
                _this.getTitleBar().setRightIcon(i);
            }
        }

        public static void $default$setRightIcon(TitleBarAction _this, Drawable drawable) {
            if (_this.getTitleBar() != null) {
                _this.getTitleBar().setRightIcon(drawable);
            }
        }

        public static Drawable $default$getRightIcon(TitleBarAction _this) {
            if (_this.getTitleBar() != null) {
                return _this.getTitleBar().getRightIcon();
            }
            return null;
        }

        public static TitleBar $default$obtainTitleBar(TitleBarAction _this, ViewGroup viewGroup) {
            TitleBar obtainTitleBar;
            if (viewGroup == null) {
                return null;
            }
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt instanceof TitleBar) {
                    return (TitleBar) childAt;
                }
                if ((childAt instanceof ViewGroup) && (obtainTitleBar = _this.obtainTitleBar((ViewGroup) childAt)) != null) {
                    return obtainTitleBar;
                }
            }
            return null;
        }
    }
}
