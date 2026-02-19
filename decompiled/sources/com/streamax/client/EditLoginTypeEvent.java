package com.streamax.client;

public class EditLoginTypeEvent {
    public boolean mIsAdd;
    public int mPosition;
    public String mServerName;

    public EditLoginTypeEvent() {
    }

    public EditLoginTypeEvent(int i, String str, boolean z) {
        this.mPosition = i;
        this.mServerName = str;
        this.mIsAdd = z;
    }

    public String getmServerName() {
        return this.mServerName;
    }

    public void setmServerName(String str) {
        this.mServerName = str;
    }

    public int getmPosition() {
        return this.mPosition;
    }

    public void setmPosition(int i) {
        this.mPosition = i;
    }

    public boolean ismIsAdd() {
        return this.mIsAdd;
    }

    public void setmIsAdd(boolean z) {
        this.mIsAdd = z;
    }
}
