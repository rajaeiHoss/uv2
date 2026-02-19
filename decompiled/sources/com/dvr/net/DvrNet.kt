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

    private external fun DownVideoControl(j: Long, i: Int): Int
    private external fun DownVideoStart(j: Long, str: String?, str2: String?, i: Int, i2: Int, i3: Int, str3: String?, str4: String?, str5: String?): Int
    private external fun DownVideoStop(j: Long, z: Boolean): Int
    private external fun DownloadBlackBox(j: Long, i: Int, str: String?, str2: String?): Int
    private external fun DownloadControl(j: Long, i: Int, str: String?): Int
    private external fun ExportParam(j: Long, str: String?): Int
    private external fun FormatStorage(j: Long, i: Int): Int
    private external fun GetAdasCali(j: Long, i: Int, iArr: IntArray?, iArr2: IntArray?, iArr3: IntArray?, iArr4: IntArray?, iArr5: IntArray?): Int
    private external fun GetCaptureFromRemote(j: Long, i: Int, i2: Int, i3: Int, i4: Int, i5: Int, str: String?): Int
    private external fun GetCmsConnectStatus(j: Long): Array<ServerState?>?
    private external fun GetConfig(j: Long, str: String?): String?
    private external fun GetDeviceVersion(j: Long): String?
    private external fun GetEventStatus(j: Long, i: Int, iArr: IntArray?): Int
    private external fun GetFileSizeByTime(j: Long, i: Int, i2: Int, i3: Int, str: String?, str2: String?, i4: Int, jArr: LongArray?): Int
    private external fun GetIOLinkageAlarmChannel(j: Long, i: Int, iArr: IntArray?): Int
    private external fun GetIPCVers(j: Long, i: Int, strArr: Array<String?>?): Int
    private external fun GetMotionDetection(j: Long, i: Int): String?
    private external fun GetRemoteDeviceList(j: Long, bArr: ByteArray?): Int
    private external fun GetRemoteDeviceList(j: Long, strArr: Array<String?>?): Int
    private external fun GetStorageInfo(j: Long): String?
    private external fun GetUTCTime(j: Long, iArr: IntArray?, strArr: Array<String?>?): Int
    private external fun GetUpgradeSWVersion(j: Long): String?
    private external fun GetUserRigth(j: Long, str: String?): String?
    private external fun GetVerByUsed(j: Long, i: Int, strArr: Array<String?>?): Int
    private external fun GetVideoQuality(j: Long, i: Int, iArr: IntArray?, iArr2: IntArray?, iArr3: IntArray?, iArr4: IntArray?, iArr5: IntArray?): Int
    private external fun GetWebPort(j: Long, iArr: IntArray?): Int
    private external fun GetYunweiInfo(j: Long, i: Int, i2: Int): String?
    private external fun ImportParam(j: Long, str: String?): Int
    private external fun InputTalkbackAudioData(j: Long, i: Int, bArr: ByteArray?, i2: Int): Int
    private external fun Logout(j: Long): Int
    private external fun MuitiPlayCapture(j: Long, str: String?): Array<Any?>?
    private external fun MuitiPlaySetSpeed(j: Long, i: Int): Int
    private external fun MultiPlay(j: Long, i: Int, i2: Int, i3: Int, str: String?, str2: String?, str3: String?): Int
    private external fun MultiPlayControl(j: Long, i: Int): Int
    private external fun MultiPlayNextFrame(j: Long): Int
    private external fun MultiPlaySeek(j: Long, str: String?): Int
    private external fun MultiPlaySetMute(j: Long, i: Int, z: Boolean): Int
    private external fun MultiPlayStop(j: Long): Int
    private external fun NetLogin(str: String?, i: Int, str2: String?, str3: String?, str4: String?, str5: String?): String?
    private external fun PTZControl(j: Long, i: Int, i2: Int, i3: Int, i4: Int): Int
    private external fun PTZState(j: Long, i: Int): Int
    private external fun RealPlay(j: Long, i: Int, i2: Int): Int
    private external fun RealPlayControl(j: Long, i: Int, i2: Int, i3: Int): Int
    private external fun RemoteClip(j: Long, i: Int, i2: Int, str: String?, str2: String?, i3: Int, iArr: IntArray?): Int
    private external fun RequestIFrame(j: Long, i: Int, i2: Int): Int
    private external fun SearchAudioFileList(j: Long, i: Int, i2: Int, i3: Int, str: String?, str2: String?): Array<RemoteFileInfo?>?
    private external fun SearchImageFileList(j: Long, i: Int, i2: Int, i3: Int, i4: Int, i5: Int, str: String?, str2: String?): Array<RemoteFileInfo?>?
    private external fun SearchMonth(j: Long, i: Int, i2: Int, i3: Int, i4: Int, i5: Int): Array<CalendarData?>?
    private external fun SearchVideoFileList(j: Long, i: Int, i2: Int, i3: Int, i4: Int, str: String?, str2: String?): Array<RemoteFileInfo?>?
    private external fun SendCtrlCommand(j: Long, i: Int): Int
    private external fun SetAdasCali(j: Long, i: Int, i2: Int, i3: Int, i4: Int, i5: Int, i6: Int): Int
    private external fun SetConfig(j: Long, str: String?): Int
    private external fun SetCtrlUTC(j: Long, i: Int, str: String?): Int
    private external fun SetDevStatusParam(j: Long, i: Int, i2: Int, i3: Int, i4: Int): Int
    private external fun SetDownload(j: Long, i: Int, i2: Int, str: String?, str2: String?, i3: Int, iArr: IntArray?): Int
    private external fun SetGPSInfo(j: Long, str: String?): Int
    private external fun SetGPSStateParam(j: Long, i: Int, i2: Int, i3: Int, i4: Int): Int
    private external fun SetLock(j: Long, i: Int, i2: Int, i3: Int, i4: Int, str: String?, str2: String?): Int
    private external fun SetMotionDetection(j: Long, i: Int, i2: Int, str: String?, i3: Int): Int
    private external fun SetOverlay(j: Long, i: Int, i2: Int, str: String?, str2: String?, str3: String?, str4: String?, str5: String?): Int
    private external fun SetRecFileBackup(j: Long, i: Int, i2: Int, i3: Int, i4: Int, str: String?, str2: String?, i5: Int, iArr: IntArray?): Int
    private external fun SetRemoteUpgrade(j: Long, str: String?, str2: String?, str3: String?): Int
    private external fun SetRestoreDefault(j: Long): Int
    private external fun SetRestoreDefault2(j: Long, j2: Long): Int
    private external fun SetRestoreDefault3(j: Long, jArr: LongArray?): Int
    private external fun SetStreamSound(j: Long, i: Int, i2: Int, z: Boolean): Int
    private external fun SetTransData(j: Long, bArr: ByteArray?, i: Int): Int
    private external fun SetUpload(j: Long, i: Int, str: String?, str2: String?, i2: Int, iArr: IntArray?): Int
    private external fun SetUserRigth(j: Long, i: Int, str: String?, str2: String?): Int
    private external fun SetVideoQuality(j: Long, i: Int, i2: Int, i3: Int, i4: Int, i5: Int, i6: Int): Int
    private external fun StartDeviceVideoRecord(j: Long, i: Int, i2: Int): Int
    private external fun StartDeviceVoiceRecord(j: Long, i: Int): Int
    private external fun StartTalk(j: Long, i: Int): Int
    private external fun StopDeviceVideoRecord(j: Long, i: Int): Int
    private external fun StopDeviceVoiceRecord(j: Long, i: Int): Int
    private external fun StopDownloadBlackBox(j: Long): Int
    private external fun StopRealPlay(j: Long, i: Int): Int
    private external fun StopTalk(j: Long, i: Int): Int
    private external fun StopTask(j: Long, i: Int): Int
    private external fun TransLogin(str: String?, i: Int, str2: String?, i2: Int, i3: Int, str3: String?, str4: String?, str5: String?): String?
    private external fun UpgradeIPCHost(j: Long, i: Int, i2: Int, i3: Int, iArr: IntArray?): Int
    private external fun login(str: String?, i: Int, str2: String?, str3: String?, i2: Int, str4: String?): String?

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

    fun MyHeartbeatCallbackFun(j: Long, i: Int): Int {
        Log.v(TAG, "[HeartbeatCallbackFun]nState = $i")
        synchronized(mIOTCListeners) {
            Log.v(TAG, "mIOTCListeners.size() =${mIOTCListeners.size}")
            for (listener in mIOTCListeners) {
                Log.v(TAG, "[receiveHearbeatInfo]listener =$listener")
                listener.receiveHearbeatInfo(i)
            }
        }
        return 0
    }

    fun MyCommandCallbackFunc(j: Long, i: Int, bArr: ByteArray?, i2: Int): Int {
        var str = ""
        if (i == 18) {
            synchronized(mIOTCListeners) {
                for (listener in mIOTCListeners) {
                    listener.receiveTransData(bArr, i2)
                }
            }
            return 0
        }
        str = try {
            String(bArr ?: ByteArray(0), Charsets.UTF_8)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
        return try {
            val obj = JSONObject(str)
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

    fun MyFrameCallbackFunc(j: Long, i: Int, i2: Int, i3: Int, j2: Long, i4: Int, j3: Long): Int {
        synchronized(mIOTCListeners) {
            for (listener in mIOTCListeners) {
                listener.receiveFrameData(i3, i, i2, j2, i4, j3)
            }
        }
        val stream = av[i3]
        return if (stream != null) {
            stream.InputFrame(j2, i4, i, i2, j3)
        } else {
            0
        }
    }

    fun TalkbackCallbackFunc(j: Long, i: Int, i2: Int): Int {
        val talkbackListener = tc ?: return 0
        talkbackListener.receiveTalkbackPCMData(Talkbackdata, i2)
        return 0
    }

    fun MyMultiPlayCallbacFunc(j: Long, i: Int, i2: Int, i3: Int, j2: Long, i4: Int, i5: Int, i6: Int, i7: Int) {
        if (i2 == 1 || i2 == 2) {
            mc?.MultiplayCallback(j, i3, i, i2, mPixels[0], i5, i6, i7)
        } else if (i2 == 4) {
            ac?.InputAudioData(i3, mAudio[0], i4)
        } else if (i2 == 0) {
            mMessageCallback?.sendMessage(0)
        }
    }

    fun BlackBoxInfoCallbackFunc(j: Long, blackBoxFrameArr: Array<BlackBoxFrame?>?): Int {
        synchronized(mIOTCListeners) {
            for (listener in mIOTCListeners) {
                Log.v(TAG, "[receiveSessionInfo]listener =${listener}")
                listener.receiveBlackBoxFrame(0, blackBoxFrameArr)
            }
        }
        return 0
    }

    fun MyDownVideoCallbackFunc(j: Long, i: Int, i2: Int, i3: Int) {
        dc?.DownVideoCallback(j, i, i2, i3)
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

    fun GetDeviceHandle(str: String?, i: Int, str2: String?, str3: String?, str4: String?): Map<String, Any> {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val response = login(str, i, str2, str3, 0, str4)
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

    fun GetDeviceHandle(str: String?, i: Int, str2: String?, str3: String?, str4: String?, str5: String?): Map<String, Any> {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val response = NetLogin(str, i, str3, str4, str5, str2)
        val map: MutableMap<String, Any> = HashMap()
        Log.v(TAG, "[NetLogin]str =$response")
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

    fun GetDeviceHandle(str: String?, i: Int, str2: String?, i2: Int, i3: Int, str3: String?, str4: String?, str5: String?): Map<String, Any> {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]")
        val response = TransLogin(str, i, str2, i2, i3, str3, str4, str5)
        val map: MutableMap<String, Any> = HashMap()
        Log.v(TAG, "[TransLogin]str =$response")
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

    fun registerIOTCListener(iRegisterIOTCListener: IRegisterIOTCListener): Boolean {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        synchronized(mIOTCListeners) {
            return if (!mIOTCListeners.contains(iRegisterIOTCListener)) {
                Log.v(TAG, "register IOTC listener")
                mIOTCListeners.add(iRegisterIOTCListener)
                true
            } else {
                false
            }
        }
    }

    fun unregisterIOTCListener(iRegisterIOTCListener: IRegisterIOTCListener): Boolean {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        synchronized(mIOTCListeners) {
            return if (mIOTCListeners.contains(iRegisterIOTCListener)) {
                Log.i("IOTCamera", "unregister IOTC listener")
                mIOTCListeners.remove(iRegisterIOTCListener)
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

    fun StartRealAv(i: Int, i2: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        val realPlay = RealPlay(j, i, i2)
        mRealplayChannel[i] = realPlay
        if (realPlay == 0) {
            mStreamTypeArray[i] = i2
        } else {
            mStreamTypeArray[i] = -1
        }
        return realPlay
    }

    fun RequestIFrame(i: Int, i2: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return RequestIFrame(j, i, i2)
    }

    fun GetIOLinkageAlarmChannel(i: Int, iArr: IntArray?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return GetIOLinkageAlarmChannel(j, i, iArr)
    }

    fun GetMotionDetection(i: Int, iArr: IntArray, strArr: Array<String?>, iArr2: IntArray): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return -1
        }
        val response = GetMotionDetection(j, i)
        Log.v(TAG, "[GetMotionDetection]response =$response")
        if (!response.isNullOrEmpty()) {
            try {
                val obj = JSONObject(response)
                iArr[0] = obj.getInt("EN")
                strArr[0] = obj.getString("RGN")
                iArr2[0] = obj.getInt("SST")
                return 0
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        return -1
    }

    fun SetMotionDetection(i: Int, i2: Int, str: String?, i3: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return SetMotionDetection(j, i, i2, str, i3)
    }

    fun GetFileSizeByTime(i: Int, i2: Int, i3: Int, str: String?, str2: String?, i4: Int, jArr: LongArray?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return GetFileSizeByTime(j, i, i2, i3, str, str2, i4, jArr)
    }

    fun GetCaptureFromRemote(i: Int, i2: Int, i3: Int, i4: Int, i5: Int, str: String?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return GetCaptureFromRemote(j, i, i2, i3, i4, i5, str)
    }

    fun SetOverlay(i: Int, i2: Int, str: String?, str2: String?, str3: String?, str4: String?, str5: String?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return SetOverlay(j, i, i2, str, str2, str3, str4, str5)
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

    fun PTZControl(i: Int, i2: Int, i3: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return PTZControl(j, i, i2, i3, 0)
    }

    fun GetWebPort(iArr: IntArray?): Int {
        val j = handle
        return if (j != 0L) {
            GetWebPort(j, iArr)
        } else {
            Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
            0
        }
    }

    fun RealPlayControl(i: Int, i2: Int, i3: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        if (i2 == -1) {
            Log.v(TAG, "[${CommonFunction._FUNC_()}]nStreamType = $i2")
            return 0
        }
        val realPlayControl = RealPlayControl(j, i, i2, i3)
        if (realPlayControl == 0 && i3 == 3) {
            mStreamTypeArray[i] = i2
        }
        return realPlayControl
    }

    fun SetStreamSound(i: Int, i2: Int, z: Boolean): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return SetStreamSound(j, i, i2, z)
    }

    fun StopRealAv(i: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        val stopRealPlay = StopRealPlay(j, i)
        mRealplayChannel[i] = -1
        mStreamTypeArray[i] = -1
        return stopRealPlay
    }

    fun SearchMonth(i: Int, i2: Int, i3: Int, i4: Int, i5: Int): Array<CalendarData?>? {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        return if (j != 0L) {
            SearchMonth(j, i, i2, i3, i4, i5)
        } else {
            Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
            null
        }
    }

    fun SetLock(i: Int, i2: Int, i3: Int, i4: Int, str: String?, str2: String?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return SetLock(j, i, i2, i3, i4, str, str2)
    }

    fun FormatStorage(i: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return FormatStorage(j, i)
    }

    fun DownloadBlackBox(i: Int, str: String?, str2: String?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return DownloadBlackBox(j, i, str, str2)
    }

    fun StopDownloadBlackBox(): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return StopDownloadBlackBox(j)
    }

    fun DownloadControl(i: Int, str: String?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return DownloadControl(j, i, str)
    }

    fun MultiPlay(i: Int, i2: Int, i3: Int, str: String?, str2: String?, str3: String?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        if (handle == 0L) {
            return 0
        }
        Log.v(TAG, "nChannelBits =$i2")
        return MultiPlay(handle, i, i2, i3, str, str2, str3)
    }

    fun MultiPlayStop(): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return MultiPlayStop(j)
    }

    fun MultiPlayPause(z: Boolean): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return if (z) {
            MultiPlayControl(j, 2)
        } else {
            MultiPlayControl(j, 1)
        }
    }

    fun MultiPlaySeek(str: String?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return MultiPlaySeek(j, str)
    }

    fun SearchVideoFileList(i: Int, i2: Int, i3: Int, i4: Int, str: String?, str2: String?): Array<RemoteFileInfo?>? {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return null
        }
        return SearchVideoFileList(j, i, i3, i2, i4, str, str2)
    }

    fun SearchImageFileList(i: Int, i2: Int, i3: Int, i4: Int, i5: Int, str: String?, str2: String?): Array<RemoteFileInfo?>? {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return null
        }
        return SearchImageFileList(j, i, i2, i3, i4, i5, str, str2)
    }

    fun SearchAudioFileList(i: Int, i2: Int, i3: Int, str: String?, str2: String?): Array<RemoteFileInfo?>? {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return null
        }
        return SearchAudioFileList(j, i, i2, i3, str, str2)
    }

    fun MultiPlayCaptureBitmap(str: String?): Array<BitmapFileInfo?>? {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        val multiPlayCapture = if (j == 0L) null else MuitiPlayCapture(j, str)
        if (multiPlayCapture == null) {
            return null
        }
        val bitmapFileInfoArr = arrayOfNulls<BitmapFileInfo>(multiPlayCapture.size)
        for (i in multiPlayCapture.indices) {
            bitmapFileInfoArr[i] = multiPlayCapture[i] as BitmapFileInfo
        }
        return bitmapFileInfoArr
    }

    fun MuitiPlaySetSpeed(i: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return MuitiPlaySetSpeed(j, i)
    }

    fun MultiPlayNextFrame(): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return MultiPlayNextFrame(j)
    }

    fun SetAVStream(i: Int, aVStream: AVStream?) {
        av[i] = aVStream
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

    fun getChannelState(i: Int): Int {
        return mRealplayChannel[i]
    }

    fun getStreamType(i: Int): Int {
        return mStreamTypeArray[i]
    }

    fun GetVideoQuality(i: Int, iArr: IntArray?, iArr2: IntArray?, iArr3: IntArray?, iArr4: IntArray?, iArr5: IntArray?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return GetVideoQuality(j, i, iArr, iArr2, iArr3, iArr4, iArr5)
    }

    fun SetVideoQuality(i: Int, i2: Int, i3: Int, i4: Int, i5: Int, i6: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return SetVideoQuality(j, i, i2, i3, i4, i5, i6)
    }

    fun GetDeviceVersion(): String {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return ""
        }
        return GetDeviceVersion(j) ?: ""
    }

    fun GetUpgradeSWVersion(): String {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return ""
        }
        return GetUpgradeSWVersion(j) ?: ""
    }

    fun SetRestoreDefault(): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return SetRestoreDefault(j)
    }

    fun StartTalk(): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return StartTalk(j, 0)
    }

    fun InputTalkbackAudioData(bArr: ByteArray?, i: Int): Int {
        val j = handle
        if (j == 0L) {
            return 0
        }
        return InputTalkbackAudioData(j, 0, bArr, i)
    }

    fun StopTalk(): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return StopTalk(j, 0)
    }

    fun GetStorageInfo(): String {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return ""
        }
        return GetStorageInfo(j) ?: ""
    }

    fun GetYunweiInfo(i: Int, i2: Int): String {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return ""
        }
        return GetYunweiInfo(j, i, i2) ?: ""
    }

    fun SetRecFileBackup(i: Int, i2: Int, i3: Int, i4: Int, str: String?, str2: String?, i5: Int, iArr: IntArray?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return SetRecFileBackup(j, i, i2, i3, i4, str, str2, i5, iArr)
    }

    fun OnekeyDownload(i: Int, i2: Int, iArr: IntArray?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return SetDownload(j, 1, i, "", "", i2, iArr)
    }

    fun SetDownload(i: Int, str: String?, str2: String?, i2: Int, iArr: IntArray?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return SetDownload(j, 0, i, str, str2, i2, iArr)
    }

    fun SetUpload(i: Int, str: String?, str2: String?, i2: Int, iArr: IntArray?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return SetUpload(j, i, str, str2, i2, iArr)
    }

    fun UpgradeIPCHost(i: Int, i2: Int, i3: Int, iArr: IntArray?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return UpgradeIPCHost(j, i, i2, i3, iArr)
    }

    fun RemoteClip(i: Int, i2: Int, str: String?, str2: String?, i3: Int, iArr: IntArray?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return RemoteClip(j, i2, i, str, str2, i3, iArr)
    }

    fun StopTask(i: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return StopTask(j, i)
    }

    fun GetEventStatus(i: Int, iArr: IntArray?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return GetEventStatus(j, i, iArr)
    }

    fun SetGPSStateParam(i: Int, i2: Int, i3: Int, i4: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return SetGPSStateParam(j, i, i2, i3, i4)
    }

    fun GetUTCTime(iArr: IntArray?, strArr: Array<String?>?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return GetUTCTime(j, iArr, strArr)
    }

    fun SetCtrlUTC(i: Int, str: String?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return SetCtrlUTC(j, i, str)
    }

    fun StartDeviceVideoRecord(i: Int, i2: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return StartDeviceVideoRecord(j, i, i2)
    }

    fun StopDeviceVideoRecord(i: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return StopDeviceVideoRecord(j, i)
    }

    fun StartDeviceVoiceRecord(i: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return StartDeviceVoiceRecord(j, i)
    }

    fun StopDeviceVoiceRecord(i: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return StopDeviceVoiceRecord(j, i)
    }

    fun SetDevStatusParam(i: Int, i2: Int, i3: Int, i4: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return SetDevStatusParam(j, i, i2, i3, i4)
    }

    fun SetRemoteUpgrade(str: String?, str2: String?, str3: String?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return SetRemoteUpgrade(j, str, str2, str3)
    }

    fun SetGPSInfo(str: String?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return SetGPSInfo(j, str)
    }

    fun SetTransData(bArr: ByteArray?, i: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return SetTransData(j, bArr, i)
    }

    fun GetIPCVers(i: Int, strArr: Array<String?>?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return GetIPCVers(j, i, strArr)
    }

    fun GetVerByUsed(i: Int, strArr: Array<String?>?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return GetVerByUsed(j, i, strArr)
    }

    fun GetRemoteDeviceList(strArr: Array<String?>?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return GetRemoteDeviceList(j, strArr)
    }

    fun GetRemoteDeviceList(bArr: ByteArray?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return GetRemoteDeviceList(j, bArr)
    }

    fun GetCmsConnectStatus(): Array<ServerState?>? {
        val j = handle
        if (j == 0L) {
            return null
        }
        return GetCmsConnectStatus(j)
    }

    fun ImportParam(str: String?): Int {
        val j = handle
        if (j == 0L) {
            return 0
        }
        return ImportParam(j, str)
    }

    fun ExportParam(str: String?): Int {
        val j = handle
        if (j == 0L) {
            return 0
        }
        return ExportParam(j, str)
    }

    fun GetConfig(str: String?): String {
        val j = handle
        if (j == 0L) {
            return ""
        }
        return GetConfig(j, str) ?: ""
    }

    fun SetConfig(str: String?): Int {
        val j = handle
        if (j == 0L) {
            return 0
        }
        return SetConfig(j, str)
    }

    fun GetUserRigth(str: String?): String {
        val j = handle
        if (j == 0L) {
            return ""
        }
        return GetUserRigth(j, str) ?: ""
    }

    fun SetUserRigth(i: Int, str: String?, str2: String?): Int {
        val j = handle
        if (j == 0L) {
            return 0
        }
        return SetUserRigth(j, i, str, str2)
    }

    fun SetRestoreDefault2(j: Long): Int {
        val j2 = handle
        if (j2 == 0L) {
            return 0
        }
        return SetRestoreDefault2(j2, j)
    }

    fun SendCtrlCommand(i: Int): Int {
        val j = handle
        if (j == 0L) {
            return 1
        }
        return SendCtrlCommand(j, i)
    }

    fun GetAdasCali(i: Int, iArr: IntArray?, iArr2: IntArray?, iArr3: IntArray?, iArr4: IntArray?, iArr5: IntArray?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return -1
        }
        return GetAdasCali(j, i, iArr, iArr2, iArr3, iArr4, iArr5)
    }

    fun SetAdasCali(i: Int, i2: Int, i3: Int, i4: Int, i5: Int, i6: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return -1
        }
        return SetAdasCali(j, i, i2, i3, i4, i5, i6)
    }

    fun DownVideoStart(str: String?, str2: String?, i: Int, i2: Int, i3: Int, str3: String?, str4: String?, str5: String?): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        if (handle == 0L) {
            return 0
        }
        Log.v(TAG, "nChannelBits =$i2")
        return DownVideoStart(handle, str, str2, i, i2, i3, str3, str4, str5)
    }

    fun DownVideoControl(i: Int): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return DownVideoControl(j, i)
    }

    fun DownVideoStop(z: Boolean): Int {
        Log.v(TAG, "[${CommonFunction._FUNC_()}]handle = $handle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return DownVideoStop(j, z)
    }

    fun SetRestoreDefault3(jArr: LongArray?): Int {
        val j = handle
        if (j == 0L) {
            return 0
        }
        return SetRestoreDefault3(j, jArr)
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
        private external fun LocalSearch(str: String?): String?

        @JvmStatic
        external fun SetLocalIp(str: String?): Int

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
