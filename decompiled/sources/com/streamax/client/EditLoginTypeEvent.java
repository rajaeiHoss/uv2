package com.streamax.client;

public class EditLoginTypeEvent {
    public boolean mIsAdd;
    public int mPosition;
    public String mServerName;

    public EditLoginTypeEvent() {
    }

    public EditLoginTypeEvent(int position, String serverName, boolean isAdd) {
        this.mPosition = position;
        this.mServerName = serverName;
        this.mIsAdd = isAdd;
    }

    public String getmServerName() {
        return this.mServerName;
    }

    public void setmServerName(String serverName) {
        this.mServerName = serverName;
    }

    public int getmPosition() {
        return this.mPosition;
    }

    public void setmPosition(int position) {
        this.mPosition = position;
    }

    public boolean ismIsAdd() {
        return this.mIsAdd;
    }

    public void setmIsAdd(boolean isAdd) {
        this.mIsAdd = isAdd;
    }
}
