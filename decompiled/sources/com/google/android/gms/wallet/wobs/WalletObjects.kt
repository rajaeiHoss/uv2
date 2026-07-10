package com.google.android.gms.wallet.wobs

import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.wallet.CreateWalletObjectsRequest

interface WalletObjects {
    fun createWalletObjects(
        googleApiClient: GoogleApiClient,
        request: CreateWalletObjectsRequest,
        requestCode: Int,
    )
}
