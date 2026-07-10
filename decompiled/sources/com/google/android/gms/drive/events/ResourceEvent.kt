package com.google.android.gms.drive.events

import com.google.android.gms.drive.DriveId

interface ResourceEvent : DriveEvent {
    val driveId: DriveId
}
