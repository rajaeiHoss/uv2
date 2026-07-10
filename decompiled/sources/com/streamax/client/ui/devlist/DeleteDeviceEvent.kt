package com.streamax.client.ui.devlist

class DeleteDeviceEvent {
    @JvmField var mDeviceId: Int = 0

    constructor()
    constructor(deviceId: Int) {
        mDeviceId = deviceId
    }
}
