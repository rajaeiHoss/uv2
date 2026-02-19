package com.streamax.client.ui.devlist;

public class DevInfoEvent {
    public String mGroupName;
    public int mId;
    public boolean mSingle;

    public DevInfoEvent() {
    }

    public DevInfoEvent(int i, String str) {
        this.mGroupName = str;
        this.mId = i;
    }
}
