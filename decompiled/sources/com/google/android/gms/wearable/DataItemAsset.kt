package com.google.android.gms.wearable

import com.google.android.gms.common.data.Freezable

interface DataItemAsset : Freezable<DataItemAsset> {
    val dataItemKey: String

    val id: String
}
