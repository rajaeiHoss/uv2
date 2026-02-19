package com.hjq.toast;

import android.app.Application;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.hjq.toast.config.IToast;

public class SystemToast extends Toast implements IToast {
    private TextView mMessageView;

    public TextView findMessageView(View view) {
        if (view instanceof TextView) {
            int id = view.getId();
            if (id == -1) {
                view.setId(16908299);
            } else if (id != 16908299) {
                throw new IllegalArgumentException("You must set the ID value of TextView to android.R.id.message");
            }
            return (TextView) view;
        }
        View findViewById = view.findViewById(16908299);
        if (findViewById instanceof TextView) {
            return (TextView) findViewById;
        }
        throw new IllegalArgumentException("You must include a TextView with an ID value of android.R.id.message");
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
