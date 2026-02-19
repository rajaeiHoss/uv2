package com.streamax.config.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.streamax.config.base.ConfigFragment;
import com.streamax.utils.UiUtils;
import com.zycs.UView.R;

public class OneNumberEditFragment extends ConfigFragment {
    public int curValue;
    public Button mBtnSave;
    public AlertDialog mDialog;
    public EditText mEtValue;
    public String mFragName;
    public String mFragTitle;
    private OneNumberEditInterface mInterface;
    public int maxValue = 0;
    public int minValue = 0;

    public void SetOneNumberEditInterface(OneNumberEditInterface oneNumberEditInterface) {
        this.mInterface = oneNumberEditInterface;
    }

    /* access modifiers changed from: protected */
    public void init() {
        initTitleView();
    }

    public void initTitleView() {
        super.initConfigTitleView();
        this.mBtnBack.setVisibility(0);
        this.mTvTitle.setText(this.mFragTitle);
        this.mTvTitle.setVisibility(0);
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mChildView = this.mInflater.inflate(R.layout.config_one_numberedit, (ViewGroup) null);
        this.mEtValue = (EditText) this.mChildView.findViewById(R.id.config_one_numberedit_ev_value);
        this.mBtnSave = (Button) this.mChildView.findViewById(R.id.config_one_numberedit_btn_save);
        return this.mChildView;
    }

    /* access modifiers changed from: protected */
    public void initData() {
        this.mEtValue.setText("");
        if (this.maxValue > this.minValue) {
            this.mEtValue.setHint(this.minValue + "-" + this.maxValue);
        }
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mBtnBack.setOnClickListener(this);
        this.mBtnSave.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public void popFragment() {
        prePage();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.config_one_numberedit_btn_save) {
            saveOneNumberEdit();
        } else if (id == R.id.config_title_btn_back) {
            popFragment();
        }
    }

    public void onPause() {
        super.onPause();
    }

    public void saveOneNumberEdit() {
        String obj = this.mEtValue.getText().toString();
        if (obj == null || obj.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.mConfigUi);
            builder.setTitle(UiUtils.getString(R.string.config_inputInvalidRange));
            builder.setNegativeButton(UiUtils.getString(R.string.cancel), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    OneNumberEditFragment.this.mDialog.hide();
                }
            });
            this.mDialog = builder.show();
            return;
        }
        int parseInt = Integer.parseInt(obj);
        this.curValue = parseInt;
        if (parseInt < this.minValue || parseInt > this.maxValue) {
            AlertDialog.Builder builder2 = new AlertDialog.Builder(this.mConfigUi);
            builder2.setTitle(UiUtils.getString(R.string.config_inputInvalidRange));
            builder2.setNegativeButton(UiUtils.getString(R.string.cancel), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    OneNumberEditFragment.this.mDialog.hide();
                }
            });
            this.mDialog = builder2.show();
            return;
        }
        OneNumberEditInterface oneNumberEditInterface = this.mInterface;
        if (oneNumberEditInterface != null) {
            oneNumberEditInterface.saveOneNumberEdit(this.mFragName, parseInt);
        }
        popFragment();
    }
}
