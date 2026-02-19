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
        void AddTextChangedListener(VsEditView vsEditView, CharSequence charSequence, int i, int i2, int i3);
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

        public Builder(VsEditView vsEditView) {
            this.mEditView = vsEditView;
        }

        public int getLength() {
            return StringUtils.getStrLen(this.mEditView);
        }
    }

    public void addTextChangedListener(final VsEditView vsEditView, final TextChangedListener textChangedListener) {
        addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                textChangedListener.AddTextChangedListener(vsEditView, charSequence, i, i2, i3);
            }
        });
    }

    public VsEditView ClearText() {
        setText("");
        return this;
    }

    public VsEditView SetText(CharSequence charSequence) {
        setText(charSequence);
        return this;
    }

    public VsEditView SetText(int i) {
        setText(StringUtils.getString(Integer.valueOf(i)));
        return this;
    }

    public VsEditView SetTextColor(int i) {
        setTextColor(i);
        return this;
    }

    public VsEditView SetEnable(boolean z) {
        setEnabled(z);
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
