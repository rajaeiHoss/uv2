package com.hjq.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatEditText;
import com.hjq.widget.R;
import java.util.regex.Pattern;

public class RegexEditText extends AppCompatEditText implements InputFilter {
    public static final String REGEX_CHINESE = "[\\u4e00-\\u9fa5]*";
    public static final String REGEX_COUNT = "[1-9]\\d*";
    public static final String REGEX_ENGLISH = "[a-zA-Z]*";
    public static final String REGEX_MOBILE = "[1]\\d{0,10}";
    public static final String REGEX_NAME = "[[\\u4e00-\\u9fa5]|[a-zA-Z]|\\d]*";
    public static final String REGEX_NONNULL = "\\S+";
    public static final String REGEX_NUMBER = "\\d*";
    private Pattern mPattern;

    public RegexEditText(Context context) {
        this(context, (AttributeSet) null);
    }

    public RegexEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842862);
    }

    public RegexEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RegexEditText);
        if (!obtainStyledAttributes.hasValue(R.styleable.RegexEditText_inputRegex)) {
            if (obtainStyledAttributes.hasValue(R.styleable.RegexEditText_regexType)) {
                switch (obtainStyledAttributes.getInt(R.styleable.RegexEditText_regexType, 0)) {
                    case 1:
                        setInputRegex(REGEX_MOBILE);
                        break;
                    case 2:
                        setInputRegex(REGEX_CHINESE);
                        break;
                    case 3:
                        setInputRegex(REGEX_ENGLISH);
                        break;
                    case 4:
                        setInputRegex(REGEX_NUMBER);
                        break;
                    case 5:
                        setInputRegex(REGEX_COUNT);
                        break;
                    case 6:
                        setInputRegex(REGEX_NAME);
                        break;
                    case 7:
                        setInputRegex(REGEX_NONNULL);
                        break;
                }
            }
        } else {
            setInputRegex(obtainStyledAttributes.getString(R.styleable.RegexEditText_inputRegex));
        }
        obtainStyledAttributes.recycle();
    }

    public boolean hasInputType(int i) {
        return (i & getInputType()) != 0;
    }

    public void addInputType(int i) {
        setInputType(i | getInputType());
    }

    public void removeInputType(int i) {
        setInputType((~i) & getInputType());
    }

    public void setInputRegex(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mPattern = Pattern.compile(str);
            addFilters(this);
        }
    }

    public String getInputRegex() {
        Pattern pattern = this.mPattern;
        if (pattern == null) {
            return null;
        }
        return pattern.pattern();
    }

    public void addFilters(InputFilter inputFilter) {
        InputFilter[] inputFilterArr;
        if (inputFilter != null) {
            InputFilter[] filters = getFilters();
            if (filters == null || filters.length <= 0) {
                inputFilterArr = new InputFilter[]{inputFilter};
            } else {
                inputFilterArr = new InputFilter[(filters.length + 1)];
                System.arraycopy(filters, 0, inputFilterArr, 0, filters.length);
                inputFilterArr[filters.length] = inputFilter;
            }
            super.setFilters(inputFilterArr);
        }
    }

    public void clearFilters() {
        super.setFilters(new InputFilter[0]);
    }

    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        if (this.mPattern == null) {
            return charSequence;
        }
        String substring = spanned.toString().substring(0, i3);
        String str = substring + charSequence + spanned.toString().substring((i4 - i3) + i3, (spanned.toString().length() - substring.length()) + i3);
        if (i3 <= i4 - 1) {
            return (this.mPattern.matcher(str).matches() || "".equals(str)) ? charSequence : spanned.toString().substring(i3, i4);
        }
        if (!this.mPattern.matcher(str).matches()) {
            return "";
        }
        return charSequence;
    }
}
