package com.streamax.client.ui.dialog;

import android.content.Context;
import android.widget.TextView;
import com.hjq.base.BaseDialog;
import com.hjq.base.action.AnimAction;
import com.zycs.UView.R;

public final class WaitDialog {

    public static final class Builder extends BaseDialog.Builder<Builder> {
        private final TextView mMessageView = ((TextView) findViewById(R.id.tv_wait_message));

        public Builder(Context context) {
            super(context);
            setContentView((int) R.layout.wait_dialog);
            setAnimStyle(AnimAction.ANIM_TOAST);
            setBackgroundDimEnabled(false);
            setCancelable(false);
        }

        public Builder setMessage(int i) {
            return setMessage((CharSequence) getString(i));
        }

        public Builder setMessage(CharSequence charSequence) {
            this.mMessageView.setText(charSequence);
            this.mMessageView.setVisibility(charSequence == null ? 8 : 0);
            return this;
        }
    }
}
