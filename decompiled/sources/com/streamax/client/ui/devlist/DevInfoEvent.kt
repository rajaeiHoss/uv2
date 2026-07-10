package com.streamax.client.ui.devlist

class DevInfoEvent {
    @JvmField var mGroupName: String? = null
    @JvmField var mId: Int = 0
    @JvmField var mSingle: Boolean = false

    constructor()
    constructor(id: Int, groupName: String?) {
        mGroupName = groupName
        mId = id
    }
}
