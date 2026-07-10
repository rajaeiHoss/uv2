package com.dvr.net

import android.util.Log
import com.Zxing.decoding.Intents
import com.device.discovery.Device
import com.device.discovery.DeviceType
import com.device.discovery.IPMode
import com.dvr.avstream.AVStream
import com.dvr.avstream.AudioTrackInterface
import com.dvr.common.CommonFunction
import com.google.android.gms.measurement.AppMeasurement
import com.google.firebase.analytics.FirebaseAnalytics
import com.mdvr.BlackBox.BlackBoxFrame
import java.util.Collections
import java.util.HashMap
import java.util.UUID
import java.util.Vector
import org.json.JSONException
import org.json.JSONObject

class DvrNet {
    private var CmsHandle: IntArray

    @JvmField
    var Talkbackdata: ByteArray? = null

    @JvmField
    var ac: AudioTrackInterface? = null

    private var av: Array<AVStream?>

    @JvmField
    var dc: DownVideoInterface? = null

    private var handle: Long

    @JvmField
    var mAudio: Array<ByteArray>

    private val mIOTCListeners: MutableList<IRegisterIOTCListener> =
        Collections.synchronizedList(Vector<IRegisterIOTCListener>())

    private var mMessageCallback: MessageCallback? = null

    @JvmField
    var mNetType: Int

    @JvmField
    var mPTZState: Int

    @JvmField
    var mPixels: Array<ByteArray?>

    @JvmField
    var mRealplayChannel: IntArray

    @JvmField
    var mStreamTypeArray: IntArray

    @JvmField
    var mType: Int

    @JvmField
    var mc: MultiplaybackInterface? = null

    @JvmField
    var nBBCount: Int

    @JvmField
    var nChannelCount: Int

    @JvmField
    var nDevClass: Int

    @JvmField
    var nLevel: Int

    @JvmField
    var sType: Int

    private var serialnum: String

    @JvmField
    var strDevType: String

    @JvmField
    var tc: TalkbackListener? = null

    private external fun DownVideoControl(nativeHandle: Long, control: Int): Int
    private external fun DownVideoStart(nativeHandle: Long, destinationPath: String?, tempPath: String?, diskType: Int, channelBits: Int, downloadFormat: Int, startTime: String?, endTime: String?, fileName: String?): Int
    private external fun DownVideoStop(nativeHandle: Long, cancel: Boolean): Int
    private external fun DownloadBlackBox(nativeHandle: Long, dataType: Int, startTime: String?, endTime: String?): Int
    private external fun DownloadControl(nativeHandle: Long, control: Int, position: String?): Int
    private external fun ExportParam(nativeHandle: Long, requestJson: String?): Int
    private external fun FormatStorage(nativeHandle: Long, fileSystem: Int): Int
    private external fun GetAdasCali(nativeHandle: Long, channel: Int, horizonOut: IntArray?, verticalOut: IntArray?, cameraHeightOut: IntArray?, vehicleWidthOut: IntArray?, cameraToHeadOut: IntArray?): Int
    private external fun GetCaptureFromRemote(nativeHandle: Long, captureCommand: Int, channel: Int, streamType: Int, imageFormat: Int, imageQuality: Int, outputPath: String?): Int
    private external fun GetCmsConnectStatus(nativeHandle: Long): Array<ServerState?>?
    private external fun GetConfig(nativeHandle: Long, requestJson: String?): String?
    private external fun GetDeviceVersion(nativeHandle: Long): String?
    private external fun GetEventStatus(nativeHandle: Long, eventType: Int, statusOut: IntArray?): Int
    private external fun GetFileSizeByTime(nativeHandle: Long, diskType: Int, fileType: Int, streamType: Int, startTime: String?, endTime: String?, channelBits: Int, fileSizeOut: LongArray?): Int
    private external fun GetIOLinkageAlarmChannel(nativeHandle: Long, channel: Int, alarmChannelOut: IntArray?): Int
    private external fun GetIPCVers(nativeHandle: Long, channel: Int, versionOut: Array<String?>?): Int
    private external fun GetMotionDetection(nativeHandle: Long, channel: Int): String?
    private external fun GetRemoteDeviceList(nativeHandle: Long, outputBuffer: ByteArray?): Int
    private external fun GetRemoteDeviceList(nativeHandle: Long, deviceListOut: Array<String?>?): Int
    private external fun GetStorageInfo(nativeHandle: Long): String?
    private external fun GetUTCTime(nativeHandle: Long, timezoneOut: IntArray?, utcTimeOut: Array<String?>?): Int
    private external fun GetUpgradeSWVersion(nativeHandle: Long): String?
    private external fun GetUserRigth(nativeHandle: Long, requestJson: String?): String?
    private external fun GetVerByUsed(nativeHandle: Long, versionType: Int, versionOut: Array<String?>?): Int
    private external fun GetVideoQuality(nativeHandle: Long, channel: Int, brightnessOut: IntArray?, contrastOut: IntArray?, saturationOut: IntArray?, hueOut: IntArray?, sharpnessOut: IntArray?): Int
    private external fun GetWebPort(nativeHandle: Long, webPortOut: IntArray?): Int
    private external fun GetYunweiInfo(nativeHandle: Long, dateType: Int, infoType: Int): String?
    private external fun ImportParam(nativeHandle: Long, filePath: String?): Int
    private external fun InputTalkbackAudioData(nativeHandle: Long, channel: Int, audioData: ByteArray?, dataLength: Int): Int
    private external fun Logout(nativeHandle: Long): Int
    private external fun MuitiPlayCapture(nativeHandle: Long, outputPath: String?): Array<Any?>?
    private external fun MuitiPlaySetSpeed(nativeHandle: Long, speed: Int): Int
    private external fun MultiPlay(nativeHandle: Long, diskType: Int, channelBits: Int, streamType: Int, startTime: String?, endTime: String?, fileName: String?): Int
    private external fun MultiPlayControl(nativeHandle: Long, control: Int): Int
    private external fun MultiPlayNextFrame(nativeHandle: Long): Int
    private external fun MultiPlaySeek(nativeHandle: Long, seekTime: String?): Int
    private external fun MultiPlaySetMute(nativeHandle: Long, channel: Int, muted: Boolean): Int
    private external fun MultiPlayStop(nativeHandle: Long): Int
    private external fun NetLogin(serverIp: String?, mediaPort: Int, deviceAddress: String?, username: String?, password: String?, localMacAddress: String?): String?
    private external fun PTZControl(nativeHandle: Long, channel: Int, command: Int, speed: Int, preset: Int): Int
    private external fun PTZState(nativeHandle: Long, state: Int): Int
    private external fun RealPlay(nativeHandle: Long, channel: Int, streamType: Int): Int
    private external fun RealPlayControl(nativeHandle: Long, channel: Int, streamType: Int, control: Int): Int
    private external fun RemoteClip(nativeHandle: Long, channel: Int, fileType: Int, startTime: String?, endTime: String?, format: Int, resultOut: IntArray?): Int
    private external fun RequestIFrame(nativeHandle: Long, channel: Int, streamType: Int): Int
    private external fun SearchAudioFileList(nativeHandle: Long, diskType: Int, fileType: Int, channelBits: Int, startTime: String?, endTime: String?): Array<RemoteFileInfo?>?
    private external fun SearchImageFileList(nativeHandle: Long, diskType: Int, fileType: Int, channel: Int, imageFormat: Int, imageQuality: Int, startTime: String?, endTime: String?): Array<RemoteFileInfo?>?
    private external fun SearchMonth(nativeHandle: Long, year: Int, month: Int, channel: Int, fileType: Int, streamType: Int): Array<CalendarData?>?
    private external fun SearchVideoFileList(nativeHandle: Long, diskType: Int, streamType: Int, fileType: Int, channelBits: Int, startTime: String?, endTime: String?): Array<RemoteFileInfo?>?
    private external fun SendCtrlCommand(nativeHandle: Long, command: Int): Int
    private external fun SetAdasCali(nativeHandle: Long, channel: Int, horizon: Int, vertical: Int, cameraHeight: Int, vehicleWidth: Int, cameraToHead: Int): Int
    private external fun SetConfig(nativeHandle: Long, requestJson: String?): Int
    private external fun SetCtrlUTC(nativeHandle: Long, timezoneOffset: Int, utcTime: String?): Int
    private external fun SetDevStatusParam(nativeHandle: Long, reportMode: Int, gpsInterval: Int, statusInterval: Int, alarmInterval: Int): Int
    private external fun SetDownload(nativeHandle: Long, downloadType: Int, dataType: Int, startTime: String?, endTime: String?, channelBits: Int, resultOut: IntArray?): Int
    private external fun SetGPSInfo(nativeHandle: Long, gpsInfo: String?): Int
    private external fun SetGPSStateParam(nativeHandle: Long, reportMode: Int, distanceInterval: Int, timeInterval: Int, speedLimit: Int): Int
    private external fun SetLock(nativeHandle: Long, lockType: Int, fileType: Int, startTimeType: Int, endTimeType: Int, startTime: String?, endTime: String?): Int
    private external fun SetMotionDetection(nativeHandle: Long, channel: Int, enabled: Int, region: String?, sensitivity: Int): Int
    private external fun SetOverlay(nativeHandle: Long, channel: Int, overlayType: Int, dateOverlay: String?, timeOverlay: String?, channelOverlay: String?, deviceOverlay: String?, gpsOverlay: String?): Int
    private external fun SetRecFileBackup(nativeHandle: Long, diskType: Int, fileType: Int, streamType: Int, channelBits: Int, startTime: String?, endTime: String?, backupFormat: Int, resultOut: IntArray?): Int
    private external fun SetRemoteUpgrade(nativeHandle: Long, filePath: String?, version: String?, options: String?): Int
    private external fun SetRestoreDefault(nativeHandle: Long): Int
    private external fun SetRestoreDefault2(nativeHandle: Long, resetMask: Long): Int
    private external fun SetRestoreDefault3(nativeHandle: Long, resetMasks: LongArray?): Int
    private external fun SetStreamSound(nativeHandle: Long, channel: Int, streamType: Int, enabled: Boolean): Int
    private external fun SetTransData(nativeHandle: Long, payload: ByteArray?, payloadLength: Int): Int
    private external fun SetUpload(nativeHandle: Long, uploadType: Int, filePath: String?, options: String?, channelBits: Int, resultOut: IntArray?): Int
    private external fun SetUserRigth(nativeHandle: Long, userType: Int, username: String?, rightsJson: String?): Int
    private external fun SetVideoQuality(nativeHandle: Long, channel: Int, brightness: Int, contrast: Int, saturation: Int, hue: Int, sharpness: Int): Int
    private external fun StartDeviceVideoRecord(nativeHandle: Long, channel: Int, streamType: Int): Int
    private external fun StartDeviceVoiceRecord(nativeHandle: Long, channel: Int): Int
    private external fun StartTalk(nativeHandle: Long, channel: Int): Int
    private external fun StopDeviceVideoRecord(nativeHandle: Long, channel: Int): Int
    private external fun StopDeviceVoiceRecord(nativeHandle: Long, channel: Int): Int
    private external fun StopDownloadBlackBox(nativeHandle: Long): Int
    private external fun StopRealPlay(nativeHandle: Long, channel: Int): Int
    private external fun StopTalk(nativeHandle: Long, channel: Int): Int
    private external fun StopTask(nativeHandle: Long, taskType: Int): Int
    private external fun TransLogin(messageServerIp: String?, messageServerPort: Int, gatewayServerIp: String?, gatewayPort: Int, command: Int, deviceAddress: String?, username: String?, password: String?): String?
    private external fun UpgradeIPCHost(nativeHandle: Long, channel: Int, upgradeType: Int, fileType: Int, resultOut: IntArray?): Int
    private external fun login(deviceIp: String?, mediaPort: Int, username: String?, password: String?, loginType: Int, localMacAddress: String?): String?

    init {
        nBBCount = 0
        mPixels = arrayOfNulls(16)
        mAudio = Array(1) { ByteArray(1024) }
        CmsHandle = IntArray(16)
        mRealplayChannel = IntArray(32)
        mStreamTypeArray = IntArray(32)
        mPTZState = 0
        nChannelCount = 0
        strDevType = ""
        nLevel = 0
        nDevClass = 0
        mNetType = 0
        mType = -1
        sType = -1
        av = arrayOfNulls(32)
        serialnum = ""
        handle = 0
        for (i in 0 until 32) {
            mRealplayChannel[i] = -1
            mStreamTypeArray[i] = -1
        }
    }

    fun MyHeartbeatCallbackFun(nativeHandle: Long, state: Int): Int {
        Log.v(TAG, "[HeartbeatCallbackFun]nState = $state")
        synchronized(mIOTCListeners) {
            Log.v(TAG, "mIOTCListeners.size() =${mIOTCListeners.size}")
            for (listener in mIOTCListeners) {
                Log.v(TAG, "[receiveHearbeatInfo]listener =$listener")
                listener.receiveHearbeatInfo(state)
            }
        }
        return 0
    }

    fun MyCommandCallbackFunc(nativeHandle: Long, command: Int, payload: ByteArray?, payloadLength: Int): Int {
        var payloadText = ""
        if (command == 18) {
            synchronized(mIOTCListeners) {
                for (listener in mIOTCListeners) {
                    listener.receiveTransData(payload, payloadLength)
                }
            }
            return 0
        }
        payloadText = try {
            String(payload ?: ByteArray(0), Charsets.UTF_8)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
        return try {
            val obj = JSONObject(payloadText)
            val operation = obj.getString("OPERATION")
            Log.v(TAG, "operation = $operation")

            if (operation.compareTo("REQUESTALIVEVIDEO") == 0) {
                Log.v(TAG, "switch REQUESTALIVEVIDEO")
                val response = obj.getJSONObject("RESPONSE")
                val errorCode = response.getInt("ERRORCODE")
                val errorCause = response.getString("ERRORCAUSE")
                val ssrc = if (!response.has("SSRC") || response.isNull("SSRC")) 0 else response.getInt("SSRC")
                synchronized(mIOTCListeners) {
                    for (listener in mIOTCListeners) {
                        listener.receiveSessionInfo(ssrc, errorCode, errorCause)
                    }
                }
                return 0
            }

            if (operation.compareTo("REQUESTREMOTEPLAYBACK") == 0) {
                Log.v(TAG, "switch REQUESTREMOTEPLAYBACK")
                val response = obj.getJSONObject("RESPONSE")
                Log.v(TAG, "obj.toString() = ${response}")
            } else if (operation.compareTo("UPEVENTSTATUS") == 0) {
                Log.v(TAG, "switch UPEVENTSTATUS")
                val response = obj.getJSONObject("RESPONSE")
                val ssrc = response.getInt("SSRC")
                val pro = response.getInt("PRO")
                val errorCode = response.getInt("ERRORCODE")
                synchronized(mIOTCListeners) {
                    for (listener in mIOTCListeners) {
                        Log.v(TAG, "[receiveSessionInfo]listener =${listener}")
                        listener.receiveEventStatusInfo(ssrc, pro, errorCode)
                    }
                }
            } else if (operation.compareTo("MEDIATASKSTOP") == 0) {
                if (obj.getJSONObject("PARAMETER").getInt("PT") == 2) {
                    // No-op.
                }
            } else if (operation.compareTo("REQUESTREMOTEPLAYBACK") == 0) {
                val response = obj.getJSONObject("RESPONSE")
                val errorCode = response.getInt("ERRORCODE")
                val errorCause = response.getString("ERRORCAUSE")
                synchronized(mIOTCListeners) {
                    for (listener in mIOTCListeners) {
                        listener.receivePlaybackInfo(operation, errorCode, errorCause)
                    }
                }
            } else if (operation.compareTo("CONTROLREMOTEPLAYBACK") == 0) {
                val response = obj.getJSONObject("RESPONSE")
                val errorCode = response.getInt("ERRORCODE")
                val errorCause = response.getString("ERRORCAUSE")
                synchronized(mIOTCListeners) {
                    for (listener in mIOTCListeners) {
                        listener.receivePlaybackInfo(operation, errorCode, errorCause)
                    }
                }
            } else if (operation.compareTo("REMOTEPLAYBACKSTOP") == 0) {
                val response = obj.getJSONObject("RESPONSE")
                val errorCode = response.getInt("ERRORCODE")
                val errorCause = response.getString("ERRORCAUSE")
                synchronized(mIOTCListeners) {
                    for (listener in mIOTCListeners) {
                        listener.receivePlaybackInfo(operation, errorCode, errorCause)
                    }
                }
            } else if (operation.compareTo("SPI") == 0) {
                val parameter = obj.getJSONObject("PARAMETER")
                synchronized(mIOTCListeners) {
                    for (listener in mIOTCListeners) {
                        listener.receiveParameter(operation, parameter.toString())
                    }
                }
            } else if (operation.compareTo("SENDALARMINFO") == 0) {
                val parameter = obj.getJSONObject("PARAMETER")
                synchronized(mIOTCListeners) {
                    for (listener in mIOTCListeners) {
                        listener.receiveParameter(operation, parameter.toString())
                    }
                }
            } else if (operation.compareTo("SENDRECORDSTATUS") == 0) {
                val parameter = obj.getJSONObject("PARAMETER")
                Log.v(TAG, "obj = $parameter")
                synchronized(mIOTCListeners) {
                    for (listener in mIOTCListeners) {
                        listener.receiveParameter(operation, parameter.toString())
                    }
                }
            } else if (operation.compareTo("SENDCMSCONNECTSTATUS") == 0) {
                val parameter = obj.getJSONObject("PARAMETER")
                Log.v(TAG, "obj = $parameter")
                synchronized(mIOTCListeners) {
                    for (listener in mIOTCListeners) {
                        listener.receiveParameter(operation, parameter.toString())
                    }
                }
            }
            0
        } catch (e: JSONException) {
            e.printStackTrace()
            -1
        }
    }

    fun MyFrameCallbackFunc(nativeHandle: Long, width: Int, height: Int, channel: Int, frameTimestamp: Long, dataLength: Int, frameId: Long): Int {
        synchronized(mIOTCListeners) {
            for (listener in mIOTCListeners) {
                listener.receiveFrameData(channel, width, height, frameTimestamp, dataLength, frameId)
            }
        }
        val stream = av[channel]
        return if (stream != null) {
            stream.InputFrame(frameTimestamp, dataLength, width, height, frameId)
        } else {
            0
        }
    }

    fun TalkbackCallbackFunc(nativeHandle: Long, channel: Int, dataLength: Int): Int {
        val talkbackListener = tc ?: return 0
        talkbackListener.receiveTalkbackPCMData(Talkbackdata, dataLength)
        return 0
    }

    fun MyMultiPlayCallbacFunc(nativeHandle: Long, codecType: Int, frameType: Int, channel: Int, frameTimestamp: Long, audioLength: Int, width: Int, height: Int, playbackSecond: Int) {
        if (frameType == 1 || frameType == 2) {
            mc?.MultiplayCallback(nativeHandle, channel, codecType, frameType, mPixels[0], width, height, playbackSecond)
        } else if (frameType == 4) {
            ac?.InputAudioData(channel, mAudio[0], audioLength)
        } else if (frameType == 0) {
            mMessageCallback?.sendMessage(0)
        }
    }

    fun BlackBoxInfoCallbackFunc(nativeHandle: Long, blackBoxFrameArr: Array<BlackBoxFrame?>?): Int {
        synchronized(mIOTCListeners) {
            for (listener in mIOTCListeners) {
                Log.v(TAG, "[receiveSessionInfo]listener =${listener}")
                listener.receiveBlackBoxFrame(0, blackBoxFrameArr)
            }
        }
        return 0
    }

    fun MyDownVideoCallbackFunc(nativeHandle: Long, status: Int, totalBytes: Int, currentBytes: Int) {
        dc?.DownVideoCallback(nativeHandle, status, totalBytes, currentBytes)
    }

    private fun fillLoginBaseInfo(obj: JSONObject, map: MutableMap<String, Any>, includeDevClass: Boolean, includeType: Boolean, stypeFromMtype: Boolean) {
        if (obj.has("SERIALNUM")) {
            serialnum = obj.getString("SERIALNUM")
        }
        if (obj.has("CHANNEL")) {
            nChannelCount = obj.getInt("CHANNEL")
        }
        if (includeDevClass && obj.has("DEVCLASS")) {
            nDevClass = obj.getInt("DEVCLASS")
        }
        if (obj.has("LEVEL")) {
            nLevel = obj.getInt("LEVEL")
        }
        if (includeType && obj.has(Intents.WifiConnect.TYPE)) {
            strDevType = obj.getString(Intents.WifiConnect.TYPE)
        }
        if (obj.has("MTYPE")) {
            mType = obj.getInt("MTYPE")
        }
        if (obj.has("STYPE")) {
            sType = obj.getInt("STYPE")
        }

        map["serialnum"] = serialnum
        map["channel"] = nChannelCount
        if (includeDevClass) {
            map["devclass"] = nDevClass
        }
        map[FirebaseAnalytics.Param.LEVEL] = nLevel
        if (includeType) {
            map[AppMeasurement.Param.TYPE] = strDevType
            map["mtype"] = mType
            map["stype"] = if (stypeFromMtype) mType else sType
        }
    }

    fun GetDeviceHandle(deviceIp: String?, mediaPort: Int, username: String?, password: String?, localMacAddress: String?): Map<String, Any> {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val response = login(deviceIp, mediaPort, username, password, 0, localMacAddress)
        val map: MutableMap<String, Any> = HashMap()
        var errorCode = -1
        try {
            val obj = JSONObject(response)
            errorCode = obj.getInt("ERRORCODE")
            handle = obj.getLong("HANDLE")
            if (errorCode == 0 && handle != -1L && handle != 0L) {
                fillLoginBaseInfo(obj, map, includeDevClass = true, includeType = true, stypeFromMtype = false)
            }
            map["errorcode"] = errorCode
            map["handle"] = handle
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        if (errorCode != 0 || handle == -1L) {
            handle = 0
            return map
        }
        mNetType = 0
        return map
    }

    fun GetDeviceHandle(serverIp: String?, mediaPort: Int, localMacAddress: String?, deviceAddress: String?, username: String?, password: String?): Map<String, Any> {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val response = NetLogin(serverIp, mediaPort, deviceAddress, username, password, localMacAddress)
        val map: MutableMap<String, Any> = HashMap()
        Log.v(TAG, "[NetLogin]response =$response")
        var errorCode = -1
        try {
            val obj = JSONObject(response)
            errorCode = obj.getInt("ERRORCODE")
            handle = obj.getLong("HANDLE")
            if (errorCode == 0 && handle != -1L) {
                fillLoginBaseInfo(obj, map, includeDevClass = true, includeType = true, stypeFromMtype = true)
            }
            map["errorcode"] = errorCode
            map["handle"] = handle
            Log.v(TAG, "[NetLogin]nErrorCode = $errorCode")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        if (errorCode != 0 || handle == -1L) {
            handle = 0
            return map
        }
        mNetType = 1
        return map
    }

    fun GetDeviceHandle(messageServerIp: String?, messageServerPort: Int, gatewayServerIp: String?, gatewayPort: Int, command: Int, deviceAddress: String?, username: String?, password: String?): Map<String, Any> {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]")
        val response = TransLogin(messageServerIp, messageServerPort, gatewayServerIp, gatewayPort, command, deviceAddress, username, password)
        val map: MutableMap<String, Any> = HashMap()
        Log.v(TAG, "[TransLogin]response =$response")
        var errorCode = -1
        try {
            val obj = JSONObject(response)
            errorCode = obj.getInt("ERRORCODE")
            handle = obj.getLong("HANDLE")
            if (errorCode == 0 && handle != -1L) {
                if (obj.has("SERIALNUM")) {
                    serialnum = obj.getString("SERIALNUM")
                }
                if (obj.has("CHANNEL")) {
                    nChannelCount = obj.getInt("CHANNEL")
                }
                if (obj.has("LEVEL")) {
                    nLevel = obj.getInt("LEVEL")
                }
                map["serialnum"] = serialnum
                map["channel"] = nChannelCount
                map[FirebaseAnalytics.Param.LEVEL] = nLevel
            }
            map["errorcode"] = errorCode
            map["handle"] = handle
            Log.v(TAG, "[TransLogin]nErrorCode = $errorCode")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        if (errorCode != 0 || handle == -1L) {
            handle = 0
            return map
        }
        mNetType = 2
        return map
    }

    fun registerIOTCListener(listener: IRegisterIOTCListener): Boolean {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        synchronized(mIOTCListeners) {
            return if (!mIOTCListeners.contains(listener)) {
                Log.v(TAG, "register IOTC listener")
                mIOTCListeners.add(listener)
                true
            } else {
                false
            }
        }
    }

    fun unregisterIOTCListener(listener: IRegisterIOTCListener): Boolean {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        synchronized(mIOTCListeners) {
            return if (mIOTCListeners.contains(listener)) {
                Log.i("IOTCamera", "unregister IOTC listener")
                mIOTCListeners.remove(listener)
                true
            } else {
                false
            }
        }
    }

    fun getSerialNum(): String {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        return serialnum
    }

    fun CloseDeviceHandle(): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        if (handle == 0L) {
            return 0
        }
        Log.v(TAG, "CloseDeviceHandle_handle =$handle")
        Logout(handle)
        handle = 0
        return 0
    }

    fun StartRealAv(channel: Int, streamType: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        val realPlay = RealPlay(deviceHandle, channel, streamType)
        mRealplayChannel[channel] = realPlay
        if (realPlay == 0) {
            mStreamTypeArray[channel] = streamType
        } else {
            mStreamTypeArray[channel] = -1
        }
        return realPlay
    }

    fun RequestIFrame(channel: Int, streamType: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return RequestIFrame(deviceHandle, channel, streamType)
    }

    fun GetIOLinkageAlarmChannel(channel: Int, alarmChannelOut: IntArray?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return GetIOLinkageAlarmChannel(deviceHandle, channel, alarmChannelOut)
    }

    fun GetMotionDetection(channel: Int, enabledOut: IntArray, regionOut: Array<String?>, sensitivityOut: IntArray): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return -1
        }
        val response = GetMotionDetection(deviceHandle, channel)
        Log.v(TAG, "[GetMotionDetection]response =$response")
        if (!response.isNullOrEmpty()) {
            try {
                val obj = JSONObject(response)
                enabledOut[0] = obj.getInt("EN")
                regionOut[0] = obj.getString("RGN")
                sensitivityOut[0] = obj.getInt("SST")
                return 0
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        return -1
    }

    fun SetMotionDetection(channel: Int, enabled: Int, region: String?, sensitivity: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return SetMotionDetection(deviceHandle, channel, enabled, region, sensitivity)
    }

    fun GetFileSizeByTime(diskType: Int, fileType: Int, streamType: Int, startTime: String?, endTime: String?, channelBits: Int, fileSizeOut: LongArray?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return GetFileSizeByTime(deviceHandle, diskType, fileType, streamType, startTime, endTime, channelBits, fileSizeOut)
    }

    fun GetCaptureFromRemote(captureCommand: Int, channel: Int, streamType: Int, imageFormat: Int, imageQuality: Int, outputPath: String?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return GetCaptureFromRemote(deviceHandle, captureCommand, channel, streamType, imageFormat, imageQuality, outputPath)
    }

    fun SetOverlay(channel: Int, overlayType: Int, dateOverlay: String?, timeOverlay: String?, channelOverlay: String?, deviceOverlay: String?, gpsOverlay: String?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return SetOverlay(deviceHandle, channel, overlayType, dateOverlay, timeOverlay, channelOverlay, deviceOverlay, gpsOverlay)
    }

    fun PTZState(): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L || PTZState(j, mPTZState) != 0) {
            return 0
        }
        Log.v(TAG, "mPTZState =$mPTZState")
        return mPTZState
    }

    fun PTZControl(channel: Int, command: Int, speed: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return PTZControl(deviceHandle, channel, command, speed, 0)
    }

    fun GetWebPort(webPortOut: IntArray?): Int {
        val deviceHandle = handle
        return if (deviceHandle != 0L) {
            GetWebPort(deviceHandle, webPortOut)
        } else {
            Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
            0
        }
    }

    fun RealPlayControl(channel: Int, streamType: Int, control: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        if (streamType == -1) {
            Log.v(TAG, "[${CommonFunction._FUNC_()}]nStreamType = $streamType")
            return 0
        }
        val realPlayControl = RealPlayControl(deviceHandle, channel, streamType, control)
        if (realPlayControl == 0 && control == 3) {
            mStreamTypeArray[channel] = streamType
        }
        return realPlayControl
    }

    fun SetStreamSound(channel: Int, streamType: Int, enabled: Boolean): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return SetStreamSound(deviceHandle, channel, streamType, enabled)
    }

    fun StopRealAv(channel: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        val stopRealPlay = StopRealPlay(deviceHandle, channel)
        mRealplayChannel[channel] = -1
        mStreamTypeArray[channel] = -1
        return stopRealPlay
    }

    fun SearchMonth(year: Int, month: Int, channel: Int, fileType: Int, streamType: Int): Array<CalendarData?>? {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        return if (deviceHandle != 0L) {
            SearchMonth(deviceHandle, year, month, channel, fileType, streamType)
        } else {
            Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
            null
        }
    }

    fun SetLock(lockType: Int, fileType: Int, startTimeType: Int, endTimeType: Int, startTime: String?, endTime: String?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return SetLock(deviceHandle, lockType, fileType, startTimeType, endTimeType, startTime, endTime)
    }

    fun FormatStorage(fileSystem: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return FormatStorage(deviceHandle, fileSystem)
    }

    fun DownloadBlackBox(dataType: Int, startTime: String?, endTime: String?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return DownloadBlackBox(deviceHandle, dataType, startTime, endTime)
    }

    fun StopDownloadBlackBox(): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return StopDownloadBlackBox(deviceHandle)
    }

    fun DownloadControl(control: Int, position: String?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return DownloadControl(deviceHandle, control, position)
    }

    fun MultiPlay(diskType: Int, channelBits: Int, streamType: Int, startTime: String?, endTime: String?, fileName: String?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        if (handle == 0L) {
            return 0
        }
        Log.v(TAG, "nChannelBits =$channelBits")
        return MultiPlay(handle, diskType, channelBits, streamType, startTime, endTime, fileName)
    }

    fun MultiPlayStop(): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return MultiPlayStop(deviceHandle)
    }

    fun MultiPlayPause(paused: Boolean): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return if (paused) {
            MultiPlayControl(deviceHandle, 2)
        } else {
            MultiPlayControl(deviceHandle, 1)
        }
    }

    fun MultiPlaySeek(seekTime: String?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return MultiPlaySeek(deviceHandle, seekTime)
    }

    fun SearchVideoFileList(diskType: Int, fileType: Int, streamType: Int, channelBits: Int, startTime: String?, endTime: String?): Array<RemoteFileInfo?>? {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return null
        }
        return SearchVideoFileList(deviceHandle, diskType, streamType, fileType, channelBits, startTime, endTime)
    }

    fun SearchImageFileList(diskType: Int, fileType: Int, channel: Int, imageFormat: Int, imageQuality: Int, startTime: String?, endTime: String?): Array<RemoteFileInfo?>? {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return null
        }
        return SearchImageFileList(deviceHandle, diskType, fileType, channel, imageFormat, imageQuality, startTime, endTime)
    }

    fun SearchAudioFileList(diskType: Int, fileType: Int, channelBits: Int, startTime: String?, endTime: String?): Array<RemoteFileInfo?>? {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return null
        }
        return SearchAudioFileList(deviceHandle, diskType, fileType, channelBits, startTime, endTime)
    }

    fun MultiPlayCaptureBitmap(outputPath: String?): Array<BitmapFileInfo?>? {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        val multiPlayCapture = if (deviceHandle == 0L) null else MuitiPlayCapture(deviceHandle, outputPath)
        if (multiPlayCapture == null) {
            return null
        }
        val bitmapFileInfoArr = arrayOfNulls<BitmapFileInfo>(multiPlayCapture.size)
        for (index in multiPlayCapture.indices) {
            bitmapFileInfoArr[index] = multiPlayCapture[index] as BitmapFileInfo
        }
        return bitmapFileInfoArr
    }

    fun MuitiPlaySetSpeed(speed: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return MuitiPlaySetSpeed(deviceHandle, speed)
    }

    fun MultiPlayNextFrame(): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return MultiPlayNextFrame(deviceHandle)
    }

    fun SetAVStream(channel: Int, stream: AVStream?) {
        av[channel] = stream
    }

    fun SetMultiplayInterface(multiplaybackInterface: MultiplaybackInterface?) {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        mc = multiplaybackInterface
    }

    fun SetDownVideoInterface(downVideoInterface: DownVideoInterface?) {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        dc = downVideoInterface
    }

    fun SetAudioInterface(audioTrackInterface: AudioTrackInterface?) {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        ac = audioTrackInterface
    }

    fun SetTalkbackListener(talkbackListener: TalkbackListener?) {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        tc = talkbackListener
    }

    fun setMessageCallback(messageCallback: MessageCallback?) {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        mMessageCallback = messageCallback
    }

    fun getChannelState(channel: Int): Int {
        return mRealplayChannel[channel]
    }

    fun getStreamType(channel: Int): Int {
        return mStreamTypeArray[channel]
    }

    fun GetVideoQuality(channel: Int, brightnessOut: IntArray?, contrastOut: IntArray?, saturationOut: IntArray?, hueOut: IntArray?, sharpnessOut: IntArray?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return GetVideoQuality(deviceHandle, channel, brightnessOut, contrastOut, saturationOut, hueOut, sharpnessOut)
    }

    fun SetVideoQuality(channel: Int, brightness: Int, contrast: Int, saturation: Int, hue: Int, sharpness: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return SetVideoQuality(deviceHandle, channel, brightness, contrast, saturation, hue, sharpness)
    }

    fun GetDeviceVersion(): String {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return ""
        }
        return GetDeviceVersion(deviceHandle) ?: ""
    }

    fun GetUpgradeSWVersion(): String {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return ""
        }
        return GetUpgradeSWVersion(deviceHandle) ?: ""
    }

    fun SetRestoreDefault(): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return SetRestoreDefault(deviceHandle)
    }

    fun StartTalk(): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return StartTalk(deviceHandle, 0)
    }

    fun InputTalkbackAudioData(audioData: ByteArray?, dataLength: Int): Int {
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return InputTalkbackAudioData(deviceHandle, 0, audioData, dataLength)
    }

    fun StopTalk(): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return StopTalk(deviceHandle, 0)
    }

    fun GetStorageInfo(): String {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return ""
        }
        return GetStorageInfo(deviceHandle) ?: ""
    }

    fun GetYunweiInfo(dateType: Int, infoType: Int): String {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return ""
        }
        return GetYunweiInfo(deviceHandle, dateType, infoType) ?: ""
    }

    fun SetRecFileBackup(diskType: Int, fileType: Int, streamType: Int, channelBits: Int, startTime: String?, endTime: String?, backupFormat: Int, resultOut: IntArray?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return SetRecFileBackup(deviceHandle, diskType, fileType, streamType, channelBits, startTime, endTime, backupFormat, resultOut)
    }

    fun OnekeyDownload(dataType: Int, channelBits: Int, resultOut: IntArray?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return SetDownload(deviceHandle, 1, dataType, "", "", channelBits, resultOut)
    }

    fun SetDownload(dataType: Int, startTime: String?, endTime: String?, channelBits: Int, resultOut: IntArray?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return SetDownload(deviceHandle, 0, dataType, startTime, endTime, channelBits, resultOut)
    }

    fun SetUpload(uploadType: Int, filePath: String?, options: String?, channelBits: Int, resultOut: IntArray?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return SetUpload(deviceHandle, uploadType, filePath, options, channelBits, resultOut)
    }

    fun UpgradeIPCHost(channel: Int, upgradeType: Int, fileType: Int, resultOut: IntArray?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return UpgradeIPCHost(deviceHandle, channel, upgradeType, fileType, resultOut)
    }

    fun RemoteClip(fileType: Int, channel: Int, startTime: String?, endTime: String?, format: Int, resultOut: IntArray?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return RemoteClip(deviceHandle, channel, fileType, startTime, endTime, format, resultOut)
    }

    fun StopTask(taskType: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return StopTask(deviceHandle, taskType)
    }

    fun GetEventStatus(eventType: Int, statusOut: IntArray?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return GetEventStatus(deviceHandle, eventType, statusOut)
    }

    fun SetGPSStateParam(reportMode: Int, distanceInterval: Int, timeInterval: Int, speedLimit: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return SetGPSStateParam(deviceHandle, reportMode, distanceInterval, timeInterval, speedLimit)
    }

    fun GetUTCTime(timezoneOut: IntArray?, utcTimeOut: Array<String?>?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return GetUTCTime(deviceHandle, timezoneOut, utcTimeOut)
    }

    fun SetCtrlUTC(timezoneOffset: Int, utcTime: String?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return SetCtrlUTC(deviceHandle, timezoneOffset, utcTime)
    }

    fun StartDeviceVideoRecord(channel: Int, streamType: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return StartDeviceVideoRecord(deviceHandle, channel, streamType)
    }

    fun StopDeviceVideoRecord(channel: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return StopDeviceVideoRecord(deviceHandle, channel)
    }

    fun StartDeviceVoiceRecord(channel: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return StartDeviceVoiceRecord(deviceHandle, channel)
    }

    fun StopDeviceVoiceRecord(channel: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return StopDeviceVoiceRecord(deviceHandle, channel)
    }

    fun SetDevStatusParam(reportMode: Int, gpsInterval: Int, statusInterval: Int, alarmInterval: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return SetDevStatusParam(deviceHandle, reportMode, gpsInterval, statusInterval, alarmInterval)
    }

    fun SetRemoteUpgrade(filePath: String?, version: String?, options: String?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return SetRemoteUpgrade(deviceHandle, filePath, version, options)
    }

    fun SetGPSInfo(gpsInfo: String?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return SetGPSInfo(deviceHandle, gpsInfo)
    }

    fun SetTransData(payload: ByteArray?, payloadLength: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return SetTransData(deviceHandle, payload, payloadLength)
    }

    fun GetIPCVers(channel: Int, versionOut: Array<String?>?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return GetIPCVers(deviceHandle, channel, versionOut)
    }

    fun GetVerByUsed(versionType: Int, versionOut: Array<String?>?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return GetVerByUsed(deviceHandle, versionType, versionOut)
    }

    fun GetRemoteDeviceList(deviceListOut: Array<String?>?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return GetRemoteDeviceList(deviceHandle, deviceListOut)
    }

    fun GetRemoteDeviceList(outputBuffer: ByteArray?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return GetRemoteDeviceList(deviceHandle, outputBuffer)
    }

    fun GetCmsConnectStatus(): Array<ServerState?>? {
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return null
        }
        return GetCmsConnectStatus(deviceHandle)
    }

    fun ImportParam(filePath: String?): Int {
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return ImportParam(deviceHandle, filePath)
    }

    fun ExportParam(requestJson: String?): Int {
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return ExportParam(deviceHandle, requestJson)
    }

    fun GetConfig(requestJson: String?): String {
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return ""
        }
        return GetConfig(deviceHandle, requestJson) ?: ""
    }

    fun SetConfig(requestJson: String?): Int {
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return SetConfig(deviceHandle, requestJson)
    }

    fun GetUserRigth(requestJson: String?): String {
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return ""
        }
        return GetUserRigth(deviceHandle, requestJson) ?: ""
    }

    fun SetUserRigth(userType: Int, username: String?, rightsJson: String?): Int {
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return SetUserRigth(deviceHandle, userType, username, rightsJson)
    }

    fun SetRestoreDefault2(resetMask: Long): Int {
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return SetRestoreDefault2(deviceHandle, resetMask)
    }

    fun SendCtrlCommand(command: Int): Int {
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 1
        }
        return SendCtrlCommand(deviceHandle, command)
    }

    fun GetAdasCali(channel: Int, horizonOut: IntArray?, verticalOut: IntArray?, cameraHeightOut: IntArray?, vehicleWidthOut: IntArray?, cameraToHeadOut: IntArray?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return -1
        }
        return GetAdasCali(deviceHandle, channel, horizonOut, verticalOut, cameraHeightOut, vehicleWidthOut, cameraToHeadOut)
    }

    fun SetAdasCali(channel: Int, horizon: Int, vertical: Int, cameraHeight: Int, vehicleWidth: Int, cameraToHead: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return -1
        }
        return SetAdasCali(deviceHandle, channel, horizon, vertical, cameraHeight, vehicleWidth, cameraToHead)
    }

    fun DownVideoStart(destinationPath: String?, tempPath: String?, diskType: Int, channelBits: Int, downloadFormat: Int, startTime: String?, endTime: String?, fileName: String?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        if (handle == 0L) {
            return 0
        }
        Log.v(TAG, "nChannelBits =$channelBits")
        return DownVideoStart(handle, destinationPath, tempPath, diskType, channelBits, downloadFormat, startTime, endTime, fileName)
    }

    fun DownVideoControl(control: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return DownVideoControl(deviceHandle, control)
    }

    fun DownVideoStop(cancel: Boolean): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return DownVideoStop(deviceHandle, cancel)
    }

    fun SetRestoreDefault3(resetMasks: LongArray?): Int {
        val deviceHandle = handle
        if (deviceHandle == 0L) {
            return 0
        }
        return SetRestoreDefault3(deviceHandle, resetMasks)
    }

    companion object {
        const val AUDIO_DATA = 3
        const val BLACKBOX_DATATYPE_ACC = 4
        const val BLACKBOX_DATATYPE_ALARMLOG = 2
        const val BLACKBOX_DATATYPE_CANDATA = 32
        const val BLACKBOX_DATATYPE_DEVSTATUS = 8
        const val BLACKBOX_DATATYPE_DIALLOG = 64
        const val BLACKBOX_DATATYPE_GPS = 1
        const val BLACKBOX_DATATYPE_USERLOG = 16
        const val CALENDAR_FILETYPE_ALARM = 2
        const val CALENDAR_FILETYPE_ALARM_INSPECT = 4
        const val CALENDAR_FILETYPE_ALARM_VIOLATION = 16
        const val CALENDAR_FILETYPE_LOCK = 64
        const val CALENDAR_FILETYPE_NORMAL = 1
        const val CALENDAR_FILETYPE_WARNING_INSPECT = 8
        const val CALENDAR_FILETYPE_WARNING_VIOLATION = 32
        const val CAPTURE_CMD_CONTINUOUS = 4
        const val CAPTURE_CMD_DOCKING = 1
        const val CAPTURE_CMD_HISTORY = 3
        const val CAPTURE_CMD_MANUAL = 0
        const val CAPTURE_CMD_TIMING = 2
        const val DEVCLASS_DVR = 0
        const val DEVCLASS_IPC = 1
        const val DEVCLASS_MDVR = 4
        const val DEVCLASS_MIPC = 3
        const val DEVCLASS_NVR = 2
        const val DOWNLOAD_CONTROL_DRAG = 3
        const val DOWNLOAD_CONTROL_PAUSE = 1
        const val DOWNLOAD_CONTROL_RESTORE = 2
        const val DOWNLOAD_EVENTTYPE_ACCINFO = 8
        const val DOWNLOAD_EVENTTYPE_ALARMINFO = 4
        const val DOWNLOAD_EVENTTYPE_CANDATA = 2048
        const val DOWNLOAD_EVENTTYPE_DEVSTATE = 16
        const val DOWNLOAD_EVENTTYPE_DIALLOG = 4096
        const val DOWNLOAD_EVENTTYPE_ElLECTRONICFENCE = 256
        const val DOWNLOAD_EVENTTYPE_GPSINFO = 2
        const val DOWNLOAD_EVENTTYPE_GREENDRIVER = 1024
        const val DOWNLOAD_EVENTTYPE_IMAGE = 512
        const val DOWNLOAD_EVENTTYPE_PARAMETER = 32
        const val DOWNLOAD_EVENTTYPE_RECFILE = 1
        const val DOWNLOAD_EVENTTYPE_STATIONREPORT = 128
        const val DOWNLOAD_EVENTTYPE_USERLOG = 64
        const val DOWNLOAD_FORMAT_AVI = 1
        const val DOWNLOAD_FORMAT_H264 = 0
        const val DOWNLOAD_RECORDTYPE_ALARM = 1
        const val DOWNLOAD_RECORDTYPE_NORMAL = 2
        const val DOWNLOAD_TYPE_CONDITION = 0
        const val DOWNLOAD_TYPE_ONEKEY = 1
        const val EXTERNAL_DEV_VER_CP4 = 1
        const val FORMAT_FILESYSTEM_FAT32 = 1
        const val FORMAT_FILESYSTEM_N9M = 0
        const val FRAMETYPE_A = 4
        const val FRAMETYPE_I = 1
        const val FRAMETYPE_NONE = 0
        const val FRAMETYPE_P = 2
        const val FRAMETYPE_VIDEO = 3
        const val HEARTBEAT_END = 5
        const val HEARTBEAT_LINKED = 2
        const val HEARTBEAT_RELINKING = 4
        const val HEARTBEAT_START = 1
        const val HEARTBEAT_UNLINK = 3
        const val IMAGE_DATA = 4
        const val IMAGE_FORMAT_BMP = 2
        const val IMAGE_FORMAT_GIF = 3
        const val IMAGE_FORMAT_H264 = 4
        const val IMAGE_FORMAT_JPG = 1
        const val LEVEL_ADMIN = 2
        const val LEVEL_SUBUSER = 1
        const val LEVEL_UNKNOWN = 0
        const val LEVEL_USER = 3
        const val LOCKTYPE_LOCK = 1
        const val LOCKTYPE_UNLOCK = 0
        const val LOCK_FILETYPE_CARDLOG = 2
        const val LOCK_FILETYPE_PIC = 1
        const val LOCK_FILETYPE_VIDEO = 0
        const val MAIN_STREAM = 1
        const val MIRROR_STREAM = 2
        const val NETTYPE_TRANS = 2
        const val NETTYPE_UDT = 1
        const val NETTYPE_UPNP = 0
        const val PHONE_STREAM = 2
        private const val PLAYLOAD_TYPE_H264 = 2
        private const val PLAYLOAD_TYPE_JPEG = 6
        private const val PLAYLOAD_TYPE_LOG = 9
        private const val PLAYLOAD_TYPE_METADATA = 1
        private const val PLAYLOAD_TYPE_PARAMETER = 10
        private const val PLAYLOAD_TYPE_PLAYBACK = 4
        private const val PLAYLOAD_TYPE_RAWFILE = 7
        private const val PLAYLOAD_TYPE_SIGNAL = 0
        private const val PLAYLOAD_TYPE_STREAMFILE = 3
        private const val PLAYLOAD_TYPE_TALKBACK = 5
        private const val PLAYLOAD_TYPE_UPGRADE = 8
        const val REPORT_DEVSTATUS_DENPENDENCE = 1
        const val REPORT_DEVSTATUS_INDENPENDENCE = 0
        const val REPORT_GPS_DISTANCE_INTERVAL_MODE = 1
        const val REPORT_GPS_TIME_INVERTAL_MODE = 2
        const val STORAGE_DEFAULT_EXTERNAL = 0
        const val STORAGE_SDCARD = 2
        const val STORAGE_UDISK = 1
        const val STREAM_CONTROL_PAUSE = 2
        const val STREAM_CONTROL_RESTORE = 1
        const val STREAM_CONTROL_SWTICH = 3
        const val SUB_STREAM = 0
        private const val TAG = "DvrNet"
        const val UNKNOWN_STREAM = -1
        const val UPLOAD_EVENTTYPE_ElLECTRONICFENCE = 8
        const val UPLOAD_EVENTTYPE_IMPORTPARAMETER = 1
        const val UPLOAD_EVENTTYPE_STATIONREPORT = 2
        const val UPLOAD_EVENTTYPE_UPGRADE = 4
        const val UPLOAD_EVENTTYPE_UPGRADECP4 = 32
        const val UPLOAD_EVENTTYPE_UPGRADEIPC = 16
        const val YUNWEI_INFO_DATETYPE_HISTORY = 1
        const val YUNWEI_INFO_DATETYPE_TODAY = 0
        const val YUNWEI_INFO_FAULTREPORT = 0
        const val YUNWEI_INFO_STATEREPORT = 1

        init {
            System.loadLibrary("ijkffmpeg")
            System.loadLibrary("ijknet")
        }

        @JvmStatic
        private external fun LocalSearch(queryId: String?): String?

        @JvmStatic
        external fun SetLocalIp(localIp: String?): Int

        @JvmStatic
        fun SearchLocalDevice(): Array<Device?>? {
            val localSearch = LocalSearch(UUID.randomUUID().toString())
            if (localSearch.isNullOrEmpty()) {
                return null
            }

            val details = localSearch.split('|')
            Log.v(TAG, "[SearchLocalDevice]detail.length =${details.size}")
            if (details.isEmpty()) {
                return null
            }

            val devices = arrayOfNulls<Device>(details.size)
            for (index in details.indices) {
                val detail = details[index]
                if (detail.isEmpty()) {
                    continue
                }
                try {
                    val item = JSONObject(detail)
                    val device = Device()

                    if (item.has("DEVICETYPE")) {
                        when (item.getInt("DEVICETYPE")) {
                            0 -> device.deviceType = DeviceType.DEVICETYPE_PC
                            1 -> device.deviceType = DeviceType.DEVICETYPE_DVR
                            2 -> device.deviceType = DeviceType.DEVICETYPE_IPC
                            3 -> device.deviceType = DeviceType.DEVICETYPE_NVR
                        }
                    }

                    if (item.has("SN")) {
                        device.sn = item.getString("SN")
                    }

                    if (item.has("RESPONSE")) {
                        val response = item.getJSONObject("RESPONSE")

                        if (response.has("ALIVENET")) {
                            device.nAliveNet = response.getInt("ALIVENET")
                        }
                        if (response.has("APNAME")) {
                            device.ApName = response.getString("APNAME")
                        }
                        if (response.has("DEVICENAME")) {
                            device.deviceName = response.getString("DEVICENAME")
                        }
                        if (response.has("MEDIAPORT")) {
                            device.nMediaPort = response.getInt("MEDIAPORT")
                        }
                        if (response.has("ONVIFPORT")) {
                            device.nOnvifPort = response.getInt("ONVIFPORT")
                        }
                        if (response.has("MOBILEPORT")) {
                            device.nMobilePort = response.getInt("MOBILEPORT")
                        }
                        if (response.has("RTSPPORT")) {
                            device.nRtspPort = response.getInt("RTSPPORT")
                        }
                        if (response.has("WEBPORT")) {
                            device.nWebPort = response.getInt("WEBPORT")
                        }
                        if (response.has("USERNAME")) {
                            device.Username = response.getString("USERNAME")
                        }
                        if (response.has("USERROLE")) {
                            device.UserRole = response.getString("USERROLE")
                        }
                        if (response.has("VIDEOCHANEL")) {
                            device.nChannelCount = response.getInt("VIDEOCHANEL")
                        }

                        if (response.has("ETHERNET")) {
                            val ethernet = response.getJSONObject("ETHERNET")
                            if (ethernet.has("ADDRESS")) {
                                device.wiredNetwork.Address = ethernet.getString("ADDRESS")
                            }
                            if (ethernet.has("ALTERNATDNS")) {
                                device.wiredNetwork.AlternateDNS = ethernet.getString("ALTERNATDNS")
                            }
                            if (ethernet.has("GATEWAY")) {
                                device.wiredNetwork.Gateway = ethernet.getString("GATEWAY")
                            }
                            if (ethernet.has("IPMODE")) {
                                when (ethernet.getInt("IPMODE")) {
                                    0 -> device.wiredNetwork.ipMode = IPMode.IPMODE_STATIC
                                    1 -> device.wiredNetwork.ipMode = IPMode.IPMODE_DHCP
                                }
                            }
                            if (ethernet.has("MAC")) {
                                device.wiredNetwork.mac = ethernet.getString("MAC")
                            }
                            if (ethernet.has("NETMASK")) {
                                device.wiredNetwork.Netmask = ethernet.getString("NETMASK")
                            }
                            if (ethernet.has("PRIMARYDNS")) {
                                device.wiredNetwork.PrimaryDNS = ethernet.getString("PRIMARYDNS")
                            }
                        }

                        if (response.has("WIFI")) {
                            val wifi = response.getJSONObject("WIFI")
                            if (wifi.has("ADDRESS")) {
                                device.wirelessNetwork.Address = wifi.getString("ADDRESS")
                            }
                            if (wifi.has("ALTERNATDNS")) {
                                device.wirelessNetwork.AlternateDNS = wifi.getString("ALTERNATDNS")
                            }
                            if (wifi.has("APEN")) {
                                device.wirelessNetwork.APMode = wifi.getInt("APEN")
                            }
                            if (wifi.has("ENABLE")) {
                                device.wirelessNetwork.nEnable = wifi.getInt("ENABLE")
                            }
                            if (wifi.has("NETMASK")) {
                                device.wirelessNetwork.Netmask = wifi.getString("NETMASK")
                            }
                            if (wifi.has("GATEWAY")) {
                                device.wirelessNetwork.Gateway = wifi.getString("GATEWAY")
                            }
                            if (wifi.has("MAC")) {
                                device.wirelessNetwork.mac = wifi.getString("MAC")
                            }
                            if (wifi.has("PRIMARYDNS")) {
                                device.wirelessNetwork.PrimayDNS = wifi.getString("PRIMARYDNS")
                            }
                            if (wifi.has("IPMODE")) {
                                when (wifi.getInt("IPMODE")) {
                                    0 -> device.wirelessNetwork.ipMode = IPMode.IPMODE_STATIC
                                    1 -> device.wirelessNetwork.ipMode = IPMode.IPMODE_DHCP
                                }
                            }
                        }
                    }

                    devices[index] = device
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }

            return devices
        }
    }
}
