package com.google.android.gms.fitness

import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.PendingResult
import com.google.android.gms.common.api.Status
import com.google.android.gms.fitness.data.BleDevice
import com.google.android.gms.fitness.request.BleScanCallback
import com.google.android.gms.fitness.request.StartBleScanRequest
import com.google.android.gms.fitness.result.BleDevicesResult

interface BleApi {
    fun claimBleDevice(
        googleApiClient: GoogleApiClient,
        bleDevice: BleDevice,
    ): PendingResult<Status>

    fun claimBleDevice(
        googleApiClient: GoogleApiClient,
        deviceAddress: String,
    ): PendingResult<Status>

    fun listClaimedBleDevices(googleApiClient: GoogleApiClient): PendingResult<BleDevicesResult>

    fun startBleScan(
        googleApiClient: GoogleApiClient,
        request: StartBleScanRequest,
    ): PendingResult<Status>

    fun stopBleScan(
        googleApiClient: GoogleApiClient,
        callback: BleScanCallback,
    ): PendingResult<Status>

    fun unclaimBleDevice(
        googleApiClient: GoogleApiClient,
        bleDevice: BleDevice,
    ): PendingResult<Status>

    fun unclaimBleDevice(
        googleApiClient: GoogleApiClient,
        deviceAddress: String,
    ): PendingResult<Status>
}
