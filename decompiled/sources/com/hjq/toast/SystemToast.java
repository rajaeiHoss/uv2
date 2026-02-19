package com.hjq.toast;

import android.app.Application;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.hjq.toast.config.IToast;

public class SystemToast extends Toast implements IToast {
    private TextView mMessageView;

    public /* synthetic */ TextView findMessageView(View view) {
        return IToast.CC.$default$findMessageView(this, view);
    }

    public SystemToast(Application application) {
        super(application);
    }

    public void setView(View view) {
        super.setView(view);
        if (view == null) {
            this.mMessageView = null;
        } else {
            this.mMessageView = findMessageView(view);
        }
    }

    public void setText(CharSequence charSequence) {
        super.setText(charSequence);
        TextView textView = this.mMessageView;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }
}
