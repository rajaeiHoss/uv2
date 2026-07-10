package com.google.android.gms.wearable

import android.net.Uri
import com.google.android.gms.common.data.Freezable

interface DataItem : Freezable<DataItem> {
    val assets: Map<String, DataItemAsset>

    val data: ByteArray?

    val uri: Uri

    fun setData(data: ByteArray?): DataItem
}
