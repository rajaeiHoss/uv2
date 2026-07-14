package com.streamax.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import com.streamax.utils.StringUtils;

public class VsEditView extends EditText {
    public Builder mBuilder;

    public interface TextChangedListener {
        void AddTextChangedListener(VsEditView editView, CharSequence text, int start, int before, int count);
    }

    public VsEditView(Context context) {
        this(context, (AttributeSet) null);
    }

    public VsEditView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mBuilder = new Builder(this);
    }

    public Builder getBuilder() {
        return this.mBuilder;
    }

    public class Builder {
        public VsEditView mEditView;

        public Builder(VsEditView editView) {
            this.mEditView = editView;
        }

        public int getLength() {
            return StringUtils.getStrLen(this.mEditView);
        }
    }

    public void addTextChangedListener(final VsEditView editView, final TextChangedListener textChangedListener) {
        addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence text, int start, int before, int count) {
                textChangedListener.AddTextChangedListener(editView, text, start, before, count);
            }
        });
    }

    public VsEditView ClearText() {
        setText("");
        return this;
    }

    public VsEditView SetText(CharSequence text) {
        setText(text);
        return this;
    }

    public VsEditView SetText(int stringResId) {
        setText(StringUtils.getString(Integer.valueOf(stringResId)));
        return this;
    }

    public VsEditView SetTextColor(int color) {
        setTextColor(color);
        return this;
    }

    public VsEditView SetEnable(boolean enabled) {
        setEnabled(enabled);
        return this;
    }

    public int getEtLength() {
        try {
            return getString().length();
        } catch (Exception unused) {
            return 0;
        }
    }

    public String getString() {
        try {
            return getText().toString().trim();
        } catch (Exception unused) {
            return "";
        }
    }

    public int ParseInt() {
        try {
            return Integer.valueOf(getString()).intValue();
        } catch (Exception unused) {
            return 0;
        }
    }
}
