package com.streamax.client.ui.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.hjq.base.BaseDialog;
import com.zycs.UView.R;

public final class CommonDialog {

    public static class Builder<B extends Builder<B>> extends BaseDialog.Builder<B> {
        private boolean mAutoDismiss = true;
        private final TextView mCancelView;
        private final TextView mConfirmView;
        private final ViewGroup mContainerLayout;
        private final View mLineView;
        private final TextView mTitleView;

        public Builder(Context context) {
            super(context);
            setContentView((int) R.layout.ui_dialog);
            setAnimStyle(BaseDialog.ANIM_IOS);
            setGravity(17);
            this.mContainerLayout = (ViewGroup) findViewById(R.id.ll_ui_container);
            this.mTitleView = (TextView) findViewById(R.id.tv_ui_title);
            TextView textView = (TextView) findViewById(R.id.tv_ui_cancel);
            this.mCancelView = textView;
            this.mLineView = findViewById(R.id.v_ui_line);
            TextView textView2 = (TextView) findViewById(R.id.tv_ui_confirm);
            this.mConfirmView = textView2;
            setOnClickListener(textView, textView2);
        }

        @SuppressWarnings("unchecked")
        private B self() {
            return (B) this;
        }

        public B setCustomView(int layoutResId) {
            return setCustomView(LayoutInflater.from(getContext()).inflate(layoutResId, this.mContainerLayout, false));
        }

        public B setCustomView(View view) {
            this.mContainerLayout.addView(view, 1);
            return self();
        }

        public B setTitle(int titleResId) {
            return setTitle((CharSequence) getString(titleResId));
        }

        public B setTitle(CharSequence charSequence) {
            this.mTitleView.setText(charSequence);
            return self();
        }

        public B setCancel(int cancelResId) {
            return setCancel((CharSequence) getString(cancelResId));
        }

        public B setCancel(CharSequence charSequence) {
            this.mCancelView.setText(charSequence);
            this.mLineView.setVisibility((charSequence == null || "".equals(charSequence.toString())) ? 8 : 0);
            return self();
        }

        public B setConfirm(int confirmResId) {
            return setConfirm((CharSequence) getString(confirmResId));
        }

        public B setConfirm(CharSequence charSequence) {
            this.mConfirmView.setText(charSequence);
            return self();
        }

        public B setAutoDismiss(boolean autoDismiss) {
            this.mAutoDismiss = autoDismiss;
            return self();
        }

        public void autoDismiss() {
            if (this.mAutoDismiss) {
                dismiss();
            }
        }
    }
}
