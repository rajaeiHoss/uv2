package com.google.android.gms.auth.api.credentials

import com.google.android.gms.common.api.Result

interface CredentialRequestResult : Result {
    val credential: Credential
}
