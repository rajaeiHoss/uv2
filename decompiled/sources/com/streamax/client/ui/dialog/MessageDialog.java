package com.streamax.client.ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.hjq.base.BaseDialog;
import com.streamax.client.aop.SingleClick;
import com.streamax.client.ui.dialog.CommonDialog;
import com.zycs.UView.R;

public final class MessageDialog {

    public interface OnListener {

        /* renamed from: com.streamax.client.ui.dialog.MessageDialog$OnListener$-CC  reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void $default$onCancel(OnListener onListener, BaseDialog baseDialog) {
            }
        }

        void onCancel(BaseDialog baseDialog);

        void onConfirm(BaseDialog baseDialog);
    }

    public static final class Builder extends CommonDialog.Builder<Builder> {
        private OnListener mListener;
        private final TextView mMessageView = ((TextView) findViewById(R.id.tv_message_message));

        public Builder(Context context) {
            super(context);
            setCustomView((int) R.layout.message_dialog);
        }

        public Builder setMessage(int i) {
            return setMessage((CharSequence) getString(i));
        }

        public Builder setMessage(CharSequence charSequence) {
            this.mMessageView.setText(charSequence);
            return this;
        }

        public Builder setListener(OnListener onListener) {
            this.mListener = onListener;
            return this;
        }

        public BaseDialog create() {
            if (!"".equals(this.mMessageView.getText().toString())) {
                return super.create();
            }
            throw new IllegalArgumentException("Dialog message not null");
        }

        @SingleClick
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.tv_ui_confirm) {
                autoDismiss();
                OnListener onListener = this.mListener;
                if (onListener != null) {
                    onListener.onConfirm(getDialog());
                }
            } else if (id == R.id.tv_ui_cancel) {
                autoDismiss();
                OnListener onListener2 = this.mListener;
                if (onListener2 != null) {
                    onListener2.onCancel(getDialog());
                }
            }
        }
    }
}
