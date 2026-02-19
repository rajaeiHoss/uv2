package com.dvr.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.util.ArrayList
import java.util.UUID
import java.util.concurrent.locks.ReentrantLock
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class BluetoothChat(@JvmField var mContext: Context) {
    private var intercommandThread: InterComandThread? = null
    private val mBluetoothDeviceList: MutableList<BluetoothDevice> = ArrayList()
    private var mBluetoothSocket: BluetoothSocket? = null
    private var mBtAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()

    private val mReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            Log.v(TAG, "[action] =$action")
            if ("android.bluetooth.device.action.FOUND" == action) {
                val bluetoothDevice = intent.getParcelableExtra<BluetoothDevice>("android.bluetooth.device.extra.DEVICE") ?: return
                if (bluetoothDevice.bondState != 12) {
                    Log.v(TAG, "bond state = ${bluetoothDevice.bondState} name =${bluetoothDevice.name}")
                    var i = 0
                    while (i < mBluetoothDeviceList.size) {
                        if (bluetoothDevice.address.compareTo(mBluetoothDeviceList[i].address) != 0) {
                            i++
                        } else {
                            return
                        }
                    }
                    mBluetoothDeviceList.add(bluetoothDevice)
                }
            } else if ("android.bluetooth.adapter.action.DISCOVERY_STARTED" != action) {
                if ("android.bluetooth.adapter.action.DISCOVERY_FINISHED" == action) {
                    mbReturn[RET_INDEX_DISCOVERY] = true
                } else if ("android.bluetooth.device.action.BOND_STATE_CHANGED" == action) {
                    val bluetoothDevice = intent.getParcelableExtra<BluetoothDevice>("android.bluetooth.device.extra.DEVICE") ?: return
                    Log.v(
                        TAG,
                        "[ACTION_BOND_STATE_CHANGED][name]${bluetoothDevice.name}[address]${bluetoothDevice.address}[bondstate]${bluetoothDevice.bondState}"
                    )
                    if (bluetoothDevice.bondState == 10) {
                        mbReturn[RET_INDEX_REMOVEBOND] = true
                    } else if (bluetoothDevice.bondState != 11 && bluetoothDevice.bondState == 12) {
                        mbReturn[RET_INDEX_CRATEBOND] = true
                    }
                }
            }
        }
    }

    private val mbReturn = BooleanArray(COMMAND_MAX)
    private val mbyteReturn = arrayOfNulls<ByteArray>(COMMAND_MAX)

    init {
        mBluetoothDeviceList.clear()
    }

    fun Init() {
        val btAdapter = mBtAdapter
        if (btAdapter != null && !btAdapter.isEnabled) {
            mContext.startActivity(Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"))
        }
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.bluetooth.device.action.FOUND")
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_STARTED")
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED")
        intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED")
        intentFilter.addAction("android.bluetooth.adapter.action.SCAN_MODE_CHANGED")
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED")
        intentFilter.addAction("android.bluetooth.device.action.PAIRING_REQUEST")
        mContext.registerReceiver(mReceiver, intentFilter)
    }

    fun Quit() {
        mContext.unregisterReceiver(mReceiver)
    }

    fun GetBluetoothDevice(): List<BluetoothDevice> = mBluetoothDeviceList

    fun StartDiscovery(): Int {
        val btAdapter = mBtAdapter ?: return -1
        if (btAdapter.isDiscovering) {
            btAdapter.cancelDiscovery()
        }
        mbReturn[RET_INDEX_DISCOVERY] = false
        mBluetoothDeviceList.clear()
        val bondedDevices = btAdapter.bondedDevices
        if (bondedDevices != null && bondedDevices.isNotEmpty()) {
            mBluetoothDeviceList.addAll(bondedDevices)
        }
        btAdapter.startDiscovery()
        var i = 0
        while (!mbReturn[RET_INDEX_DISCOVERY]) {
            val i2 = i + 1
            if (i >= 50) {
                break
            }
            try {
                Thread.sleep(100)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            i = i2
        }
        return 0
    }

    fun StopDiscovery(): Int {
        val btAdapter = mBtAdapter ?: return -1
        if (!btAdapter.isDiscovering) {
            return 0
        }
        btAdapter.cancelDiscovery()
        return 0
    }

    fun CreateBond(bluetoothDevice: BluetoothDevice): Int {
        Log.v(TAG, "CreateBond")
        Log.v(TAG, "[CreateBond]device.getBondState() =${bluetoothDevice.bondState}")
        if (bluetoothDevice.bondState == 12) {
            return 0
        }
        mBtAdapter?.cancelDiscovery()
        if (!BluetoothAdapter.checkBluetoothAddress(bluetoothDevice.address)) {
            return -1
        }
        mbReturn[RET_INDEX_CRATEBOND] = false
        return try {
            val result = BluetoothDevice::class.java.getMethod("createBond").invoke(bluetoothDevice) as Boolean
            if (!result) {
                return -1
            }
            var i = 0
            while (true) {
                if (mbReturn[RET_INDEX_CRATEBOND]) {
                    break
                }
                val i2 = i + 1
                if (i >= 200) {
                    break
                }
                Thread.sleep(100)
                i = i2
            }
            if (!mbReturn[RET_INDEX_CRATEBOND]) -1 else 0
        } catch (e: Exception) {
            e.printStackTrace()
            -1
        }
    }

    fun RemoveBond(bluetoothDevice: BluetoothDevice): Int {
        if (bluetoothDevice.bondState != 12) {
            return 0
        }
        return try {
            val result = BluetoothDevice::class.java.getMethod("removeBond").invoke(bluetoothDevice) as Boolean
            if (result) 0 else -1
        } catch (e: Exception) {
            e.printStackTrace()
            -1
        }
    }

    fun Connect(bluetoothDevice: BluetoothDevice?): Int {
        if (bluetoothDevice == null) {
            Log.v(TAG, "[Connect]device == null")
            return -1
        }
        if (bluetoothDevice.bondState != 12) {
            Log.v(TAG, "[Connect]device.getBondState() != BluetoothDevice.BOND_BONDED")
            return -1
        }
        if (bluetoothDevice.address == null) {
            Log.v(TAG, "[Connect]address == null")
            return -1
        }
        return try {
            mBluetoothSocket?.close()
            mBluetoothSocket = null

            val socket = bluetoothDevice.createRfcommSocketToServiceRecord(
                UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
            )
            mBluetoothSocket = socket
            if (socket == null) {
                Log.v(TAG, "[Connect]socket == null")
                return -1
            }
            socket.connect()
            if (mBluetoothSocket?.isConnected == true) {
                val thread = InterComandThread(bluetoothDevice, mBluetoothSocket!!)
                intercommandThread = thread
                thread.start()
                return 0
            }
            Log.v(TAG, "[Connect]mBluetoothSocket.isConnected() == false")
            Log.v(TAG, "[Connect] error return -1")
            -1
        } catch (e: IOException) {
            e.printStackTrace()
            -1
        }
    }

    fun Disconnect() {
        val thread = intercommandThread
        if (thread != null) {
            thread.StopThread()
            while (thread.isAlive) {
                try {
                    Thread.sleep(500)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            intercommandThread = null
        }
        val socket = mBluetoothSocket
        if (socket != null) {
            try {
                socket.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            mBluetoothSocket = null
        }
    }

    private inner class InterComandThread(
        private val device: BluetoothDevice,
        private val socket: BluetoothSocket
    ) : Thread() {
        private val lock = ReentrantLock()
        private var mbExit = false

        fun StopThread() {
            Log.v(TAG, "[StopThread] start")
            lock.lock()
            try {
                mbExit = true
            } finally {
                lock.unlock()
            }
            Log.v(TAG, "[StopThread] end")
        }

        override fun run() {
            Log.v(TAG, "[InterComandThread] start")
            val bArr = ByteArray(1024)
            val inputStream: InputStream? = try {
                socket.inputStream
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }

            val streamBuffer = StreamBuffer(1024)
            val bArr2 = ByteArray(1024)
            val iArr = IntArray(1)

            while (!mbExit && mBluetoothSocket != null && mBluetoothSocket!!.isConnected) {
                try {
                    val read = if (inputStream != null && inputStream.available() > 0) {
                        inputStream.read(bArr)
                    } else {
                        try {
                            Thread.sleep(100)
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }
                        0
                    }
                    Log.v(TAG, "read end bytes= $read")
                    if (read > 0) {
                        streamBuffer.AddData(bArr, read)
                        while (streamBuffer.GetFrame(bArr2, iArr)) {
                            Log.v(TAG, "GetFrame = true")
                            try {
                                val jsonObject = JSONObject(String(bArr2, Charsets.UTF_8))
                                val cmd = jsonObject.getString("CMD")
                                if (cmd.compareTo("res-account") == 0) {
                                    val array = jsonObject.getJSONArray("PARAM")
                                    for (i in 0 until array.length()) {
                                        val item = array.getJSONObject(i)
                                        val username = item.getString("USER")
                                        val password = item.getString("PWD")
                                        val role = item.getInt("ROLE")
                                        Log.v(TAG, "username = $username")
                                        Log.v(TAG, "password = $password")
                                        Log.v(TAG, "role = $role")
                                    }
                                    val retBuf = mbyteReturn[RET_INDEX_REQACCOUNT]
                                    if (retBuf != null) {
                                        System.arraycopy(bArr2, 0, retBuf, 0, iArr[0])
                                        mbReturn[RET_INDEX_REQACCOUNT] = true
                                    }
                                } else if (cmd.compareTo("res-saveadmin") == 0) {
                                    val crc = jsonObject.getInt("CRC")
                                    val result = jsonObject.getInt("RESULT")
                                    Log.v(TAG, "nCRC =$crc nResult =$result")
                                    val retBuf = mbyteReturn[RET_INDEX_REQSAVEADMIN]
                                    if (retBuf != null) {
                                        System.arraycopy(bArr2, 0, retBuf, 0, iArr[0])
                                        mbReturn[RET_INDEX_REQSAVEADMIN] = true
                                    }
                                }
                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }
                        }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            super.run()
            Log.v(TAG, "[InterComandThread] end")
        }
    }

    fun GetDeviceAccount(): List<DeviceAccount> {
        val arrayList: MutableList<DeviceAccount> = ArrayList()
        mbReturn[RET_INDEX_REQACCOUNT] = false
        mbyteReturn[RET_INDEX_REQACCOUNT] = ByteArray(1024)
        val socket = mBluetoothSocket ?: return arrayList

        val req = JSONObject()
        try {
            req.put("CMD", "req-account")
            req.put("CRC", 0)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return try {
            val outputStream = socket.outputStream
            outputStream.write((req.toString() + '#').toByteArray())

            var i = 0
            while (!mbReturn[RET_INDEX_REQACCOUNT]) {
                val i2 = i + 1
                if (i >= 30) {
                    break
                }
                try {
                    Thread.sleep(500, 0)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                i = i2
            }

            if (!mbReturn[RET_INDEX_REQACCOUNT]) {
                return arrayList
            }

            try {
                val jsonObject = JSONObject(String(mbyteReturn[RET_INDEX_REQACCOUNT] ?: ByteArray(0), Charsets.UTF_8))
                if (jsonObject.getString("CMD").compareTo("res-account") == 0) {
                    val paramArray: JSONArray = jsonObject.getJSONArray("PARAM")
                    Log.v(TAG, "paramArray.length() = ${paramArray.length()}")
                    for (index in 0 until paramArray.length()) {
                        val item = paramArray.getJSONObject(index)
                        val deviceAccount = DeviceAccount()
                        deviceAccount.username = item.getString("USER")
                        deviceAccount.password = item.getString("PWD")
                        deviceAccount.role = item.getInt("ROLE")
                        arrayList.add(deviceAccount)
                    }
                }
            } catch (e: JSONException) {
                e.printStackTrace()
                Log.v(TAG, "e.printStackTrace();---1")
            }
            arrayList
        } catch (e: IOException) {
            e.printStackTrace()
            Log.v(TAG, "e.printStackTrace();----2")
            arrayList
        }
    }

    fun SendWifiInfoToDevice(str: String?, str2: String?): Int {
        val socket = mBluetoothSocket ?: return -1
        val jsonObject = JSONObject()
        try {
            jsonObject.put("MODULE", "DISCOVERY")
            jsonObject.put("MAGIC", "RM_475A4AA5")
            val wifiObj = JSONObject()
            wifiObj.put("ESSID", str)
            wifiObj.put("PWD", str2)
            wifiObj.put("IPMODE", 1)
            wifiObj.put("APEN", 1)
            wifiObj.put("ENABLE", 1)
            val parameter = JSONObject()
            parameter.put("WIFI", wifiObj)
            jsonObject.put("PARAMETER", parameter)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return try {
            val outputStream = socket.outputStream
            outputStream.write((jsonObject.toString() + '#').toByteArray())
            0
        } catch (e: IOException) {
            e.printStackTrace()
            -1
        }
    }

    fun SaveUserInfoToDevice(str: String?, str2: String?): Int {
        mbReturn[RET_INDEX_REQSAVEADMIN] = false
        mbyteReturn[RET_INDEX_REQSAVEADMIN] = ByteArray(1024)
        val socket = mBluetoothSocket ?: return -1

        val jsonObject = JSONObject()
        try {
            jsonObject.put("CMD", "req-saveadmin")
            jsonObject.put("CRC", 0)
            val param = JSONObject()
            param.put("USER", str)
            param.put("PWD", str2)
            jsonObject.put("PARAM", param)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        try {
            val outputStream = socket.outputStream
            outputStream.write((jsonObject.toString() + '#').toByteArray())
        } catch (e: IOException) {
            e.printStackTrace()
        }

        var i = 0
        while (!mbReturn[RET_INDEX_REQSAVEADMIN]) {
            val i2 = i + 1
            if (i >= 30) {
                break
            }
            try {
                Thread.sleep(500, 0)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            i = i2
        }

        if (!mbReturn[RET_INDEX_REQSAVEADMIN]) {
            return -1
        }

        val str3 = String(mbyteReturn[RET_INDEX_REQSAVEADMIN] ?: ByteArray(0), Charsets.UTF_8)

        return try {
            val response = JSONObject(str3)
            if (response.getString("CMD").compareTo("res-saveadmin") == 0) {
                val crc = response.getInt("CRC")
                val result = response.getInt("RESULT")
                Log.v(TAG, "nCRC =$crc")
                Log.v(TAG, "nResult =$result")
                result
            } else {
                -1
            }
        } catch (e: JSONException) {
            e.printStackTrace()
            -1
        }
    }

    companion object {
        private const val COMMAND_MAX = 10
        private const val RET_INDEX_CRATEBOND = 4
        private const val RET_INDEX_DISCOVERY = 3
        private const val RET_INDEX_REMOVEBOND = 5
        private const val RET_INDEX_REQACCOUNT = 1
        private const val RET_INDEX_REQSAVEADMIN = 2

        const val TAG = "BluetoothChat"
    }
}
