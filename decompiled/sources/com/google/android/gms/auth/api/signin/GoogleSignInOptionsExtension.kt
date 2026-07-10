package com.google.android.gms.auth.api.signin

import android.os.Bundle
import com.google.android.gms.common.api.Scope

interface GoogleSignInOptionsExtension {
    val extensionType: Int

    val impliedScopes: List<Scope>

    fun toBundle(): Bundle
}
