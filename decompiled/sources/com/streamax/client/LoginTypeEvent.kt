package com.streamax.client

class LoginTypeEvent {
    @JvmField var mLoginType: Int = 0

    constructor()
    constructor(loginType: Int) {
        mLoginType = loginType
    }
}
