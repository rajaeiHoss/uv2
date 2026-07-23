package com.streamax.client

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.http.SslError
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.util.Base64
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import android.webkit.SslErrorHandler
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.AdapterView
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TableLayout
import android.widget.TextView
import com.dvr.avstream.AVStream
import com.dvr.avstream.AuTrack
import com.dvr.avstream.MyCallInterface
import com.dvr.calendar.DayStyle
import com.dvr.net.DvrNet
import com.google.android.gms.nearby.messages.Strategy
import com.streamax.Configs
import com.streamax.client.ui.devlist.ClickFullEvent
import com.streamax.client.ui.devlist.DeleteDeviceEvent
import com.streamax.client.ui.devlist.DevInfoEvent
import com.streamax.client.ui.devlist.db.GroupBean
import com.streamax.client.ui.devlist.db.GroupDao
import com.streamax.client.ui.devlist.db.GroupDaoForNormal
import com.streamax.client.ui.devlist.db.GroupDaoForServer
import com.streamax.proxy.ConnDeviceProxy
import com.streamax.utils.AppProxy
import com.streamax.utils.SpUtils
import com.zycs.UView.R
import de.greenrobot.event.EventBus
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.StreamCorruptedException
import java.util.ArrayList
import java.util.Calendar
import java.util.HashMap
import java.util.Hashtable
import java.util.Stack

 class LiveViewUi:Activity(), Runnable, MyCallInterface, View.OnClickListener, PopupWindow.OnDismissListener, SurfaceCallBackInterface {
 var bConfiguration:Boolean = false
private var initMode:Int = 0
 var landscape = false
 var mAVStream = arrayOfNulls<AVStream>(32)
 lateinit var mApp:MyApp
 var mAudioTrack = AuTrack()
private var mBeanDatas:List<GroupBean>? = null
 /* access modifiers changed from: private */
     lateinit var mBtnPlay:Button
 var mBtnSelect:Button? = null
 /* access modifiers changed from: private */
     var mChCount:Int = 0
 /* access modifiers changed from: private */
     var mChannel:Int = 0
 lateinit var mContext:Context
internal var mCount:Int = 0
private var mDao:GroupDao? = null
private var mDevId:Int = 0
private var mDevInfo:DevInfoBean? = null
private val mDevInfoBean:DevInfoEvent? = null
 lateinit var mDevLists:List<DevInfoBean>
 var mGroupLists:List<GroupBean>? = null
private var mGroupName:String? = null
 lateinit var mGroupNameDatas:MutableList<String>
 var mHandler = AppProxy.getHandler()
 lateinit var mInflater:LayoutInflater
 lateinit var mLiveView:View
 var mPopupCapture:PopupWindow? = null
 lateinit var mPreviewLayout:LinearLayout
 var mRecordPathMap:MutableMap<Int, String> = Hashtable()
 var mRecordStack:Stack<Int> = Stack()
 lateinit var mScrollView:ScrollView
 var mSelectChannel:Int = 0
 var mSelectId:Int = 0
 var mSelectMap:Map<String, Any> = HashMap()
 /* access modifiers changed from: private */
     lateinit var mStopPbLoading:ProgressBar
private var mTime:Int = 0
 lateinit var mTitlebar:FrameLayout
private var mTvTitle:TextView? = null
 lateinit var mUrl:String
 var mVideoGroup:VideoGroup? = null
 lateinit var mVideoGroupLayout:LinearLayout
 lateinit var mVideoInfo:FrameLayout
 /* access modifiers changed from: private */
     var mViewIndexDatas:MutableList<String> = ArrayList()
 var mViewInfoArray = arrayOfNulls<SingleViewInfo>(32)
 lateinit var mWebView:WebView
 var mbMute:Boolean = false
 var mbPreview:Boolean = false
 var mbPtz:Boolean = false
 var mbRecordArray = BooleanArray(32)
 var mbState:Boolean = false
 /* access modifiers changed from: private */
     var mbSwitch:Boolean = false
 lateinit var mbmpList:List<Map<String, Any>>
 var mdbHelper:DbHelper? = null
 var mnMaxView = 16
 lateinit var mpopViewer:View
 lateinit var mtvVideoInformation:TextView
 var pop:PopupWindow? = null

 val localMacAddress:String?
get() {
val defaultMac = "00-00-00-00-00-00"
val wifiManager = getSystemService(Configs.Key.WifiStatus) as WifiManager
if (wifiManager == null)
{
return defaultMac
}
val connectionInfo = wifiManager!!.getConnectionInfo()
if (connectionInfo == null)
{
return null
}
val macAddress = connectionInfo!!.getMacAddress()
if (macAddress == null)
{
return defaultMac
}
val normalizedMac = macAddress!!.replace(":", "-")
return if (normalizedMac.length > 0) normalizedMac else defaultMac
}

 fun SetPtzState(enabled:Boolean) {}

 override fun onDismiss() {}

 override fun run() {}

 fun setPlayStatus(status:Int) {}

 fun setPtz(direction:Int, speed:Int) {}

 /* access modifiers changed from: protected */
     override fun onCreate(bundle:Bundle?) {
super.onCreate(bundle)
EventBus.getDefault().register(this)
this.mContext = AppProxy.getContext()
this.mApp = getApplication() as MyApp
val from = LayoutInflater.from(this)
this.mInflater = from
val inflate = from.inflate(R.layout.previewpage, null as ViewGroup?)
this.mLiveView = inflate
this.mStopPbLoading = inflate.findViewById<View>(R.id.preview_pbloading) as ProgressBar
this.mdbHelper = DbHelper(this.mContext, DbHelper.DATABASENAME, null as SQLiteDatabase.CursorFactory?, 1)
setContentView(this.mLiveView)
FindViews()
this.mTvTitle!!.setText(MyApp.serverip)
localMacAddress
Thread(SwitchChannelRunnable()).start()
Thread(object:Runnable {
 override fun run() {
this@LiveViewUi.AutoPlay()
}
}).start()
}

 fun onEventMainThread(clickFullEvent:ClickFullEvent) {
val fullscreen = !this.landscape
this.landscape = fullscreen
if (fullscreen)
{
setRequestedOrientation(0)
setVideoGroupMax(true)
return
}
setRequestedOrientation(1)
setVideoGroupMax(false)
}

 fun popMenu(view:View, view2:View?) {
val displayMetrics = DisplayMetrics()
getWindowManager().getDefaultDisplay().getMetrics(displayMetrics)
val popupWidth = displayMetrics.widthPixels
val popupHeight = displayMetrics.heightPixels
val popupWindow = this.pop
if (popupWindow == null)
{
val popupWindow2 = PopupWindow(view, popupWidth / 2, popupHeight / 2, true)
this.pop = popupWindow2
popupWindow2.setBackgroundDrawable(this.mContext.getResources().getDrawable(R.drawable.select_device_bg))
this.pop!!.setTouchInterceptor(object:View.OnTouchListener {
 override fun onTouch(view:View, motionEvent:MotionEvent):Boolean {
if (motionEvent.getAction() !== 4)
{
return false
}
this@LiveViewUi.pop!!.dismiss()
return false
}
})
this.pop!!.setOutsideTouchable(true)
this.pop!!.showAsDropDown(view2, 1, 0)
this.pop!!.update()
}
else if (popupWindow!!.isShowing())
{
this.pop!!.dismiss()
this.pop = null
}
else
{
this.pop = null
val popupWindow3 = PopupWindow(view, popupWidth / 2, popupHeight / 2, true)
this.pop = popupWindow3
popupWindow3.setBackgroundDrawable(this.mContext.getResources().getDrawable(R.drawable.select_device_bg))
this.pop!!.setOutsideTouchable(false)
this.pop!!.showAsDropDown(view2, 1, 0)
this.pop!!.update()
}
}

 fun FindViews() {
this.mPreviewLayout = this.mLiveView.findViewById<View>(R.id.preview_layout) as LinearLayout
this.mVideoGroupLayout = this.mLiveView.findViewById<View>(R.id.videogroup_layout) as LinearLayout
val videoGroup = this.mLiveView.findViewById<View>(R.id.preview_videogroup) as VideoGroup
this.mVideoGroup = videoGroup
if (videoGroup != null)
{
videoGroup!!.SetActivity(this)
this.mVideoGroup!!.setCanFull(true)
this.mVideoGroup!!.LoadViews()
initViewCounts(this.mApp.mDevInfo.mChCounts)
}
this.mTitlebar = this.mLiveView.findViewById<View>(R.id.title_preview) as FrameLayout
this.mVideoInfo = this.mLiveView.findViewById<View>(R.id.preview_videoinformation) as FrameLayout
this.mtvVideoInformation = this.mLiveView.findViewById<View>(R.id.preview_videoinformation_text) as TextView
this.mTvTitle = this.mLiveView.findViewById<View>(R.id.preview_title_text) as TextView
val button = this.mLiveView.findViewById<View>(R.id.preview_title_button_play) as Button
this.mBtnPlay = button
button.setOnClickListener(this)
this.mScrollView = this.mLiveView.findViewById<View>(R.id.scroll_view) as ScrollView
this.mUrl = "https://www.baidu.com/"
this.mUrl = String.format("http://%s/RealInfo", MyApp.serverip)
val webView = this.mLiveView.findViewById<View>(R.id.webview) as WebView
this.mWebView = webView
webView.setHorizontalScrollBarEnabled(false)
this.mWebView.setHorizontalScrollbarOverlay(false)
this.mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true)
this.mWebView.getSettings().setJavaScriptEnabled(true)
this.mWebView.getSettings().setSupportZoom(true)
this.mWebView.getSettings().setBuiltInZoomControls(true)
this.mWebView.getSettings().setUseWideViewPort(true)
this.mWebView.getSettings().setLoadWithOverviewMode(true)
try
{
	WebSettings::class.java.getMethod("setAppCacheEnabled", Boolean::class.javaPrimitiveType).invoke(this.mWebView.getSettings(), true)
}
catch (unused:Exception) {}

this.mWebView.getSettings().setDomStorageEnabled(true)
this.mWebView.setWebViewClient(object:WebViewClient() {
 override fun onReceivedSslError(webView:WebView, sslErrorHandler:SslErrorHandler?, sslError:SslError) {
if (sslErrorHandler != null)
{
sslErrorHandler!!.proceed()
}
}

 override fun shouldOverrideUrlLoading(webView:WebView?, url:String?):Boolean {
if (webView == null || url == null)
{
return false
}
if (!url!!.startsWith("http") && !url!!.startsWith("https"))
{
return true
}
webView!!.loadUrl(url)
return true
}
})
this.mWebView.loadUrl(this.mUrl)
}

 fun AutoPlay() {
var devInfoBean:DevInfoBean?
if (this.mApp.mbAutoPlay)
{
for (previewIndex in 0 until this.mnMaxView)
{
val sharedPreferences = getSharedPreferences(String.format("preview%02d", previewIndex), 0)
val savedDeviceInfo = sharedPreferences.getString("info", "")
val savedChannel = sharedPreferences.getInt("channel", 0)
val savedLoginType = SpUtils.getInt(Configs.Key.LoginType, -1)
if (!(savedDeviceInfo == null || savedDeviceInfo!!.length === 0 || savedLoginType != MyApp.loginType))
{
try
{
try
{
val savedDevice = ObjectInputStream(ByteArrayInputStream(Base64.decode(savedDeviceInfo, 0))).readObject() as DevInfoBean
savedDevice!!.PrintOut()
if (MyApp.loginType === 0)
{
devInfoBean = this.mdbHelper!!.query(savedDevice!!.mDevName!!)
}
else
{
if (this.mApp.mWebService == null)
{
this.mApp.mWebService = WebService(MyApp.LastServerHostName, MyApp.username, MyApp.password)
}
if (this.mApp.mWebService!!.mDevList != null && this.mApp.mWebService!!.mDevList!!.size === 0)
{
this.mApp.mWebService!!.GetTerminalInfoAndroid(true)
}
devInfoBean = this.mApp.mWebService!!.query(savedDevice!!.mDevName!!)
}
if (savedDevice != null)
{
if (devInfoBean != null)
{
if (savedDevice!!.mDevIp!!.compareTo(devInfoBean!!.mDevIp!!) === 0 && savedDevice!!.mMediaPort === devInfoBean!!.mMediaPort)
{
StartPlay(devInfoBean!!.mDevId, savedChannel, previewIndex)
}
}
}
}
catch (e:ClassNotFoundException) {
e.printStackTrace()
}

}
catch (e2:StreamCorruptedException) {
e2.printStackTrace()
}
catch (e3:IOException) {
e3.printStackTrace()
}

}
}
}
}

 fun StartRecord(i:Int) {
if (!Environment.getExternalStorageState().equals("mounted"))
{
this.mtvVideoInformation.setText(this.mContext.getString(R.string.ExternalStorageerror))
return
}
val instance = Calendar.getInstance()
val format = String.format("%04d%02d%02d%02d%02d%02d", Integer.valueOf(instance.get(1)), Integer.valueOf(instance.get(2) + 1), Integer.valueOf(instance.get(5)), Integer.valueOf(instance.get(11)), Integer.valueOf(instance.get(12)), Integer.valueOf(instance.get(13)))
if (this.mAVStream[i] != null)
{
val format2 = String.format("%02d.264", this.mViewInfoArray[i]!!.mChannel + 1)
this.mRecordStack.push(i)
this.mRecordPathMap.put(i, format + format2)
this.mAVStream[i]!!.StartRecord(Configs.Key.RecordDirPath + format + format2)
val singleViewInfoArr = this.mViewInfoArray
if (singleViewInfoArr[i] != null && singleViewInfoArr[i]!!.getNet() != null)
{
this.mViewInfoArray[i]!!.getNet()!!.RequestIFrame(this.mViewInfoArray[i]!!.mChannel, 0)
this.mVideoGroup!!.SetRecState(i, true)
}
}
}

 fun StopRecord(i:Int) {
val aVStreamArr = this.mAVStream
if (aVStreamArr[i] != null)
{
aVStreamArr[i]!!.StopRecord()
this.mVideoGroup!!.post(object:Runnable {
 override fun run() {
this@LiveViewUi.mVideoGroup!!.SetRecState(i, false)
}
})
this.mbRecordArray[i] = false
}
}

 fun CaptureImage(startChannel:Int, channelCount:Int):List<Map<String, Any>> {
val captureDir = Environment.getExternalStorageDirectory().toString() + "/streaming/capture/"
val instance = Calendar.getInstance()
val format = String.format("%04d%02d%02d%02d%02d%02d", Integer.valueOf(instance.get(1)), Integer.valueOf(instance.get(2) + 1), Integer.valueOf(instance.get(5)), Integer.valueOf(instance.get(11)), Integer.valueOf(instance.get(12)), Integer.valueOf(instance.get(13)))
val captures = ArrayList<Map<String, Any>>()
for (channelIndex in startChannel until channelCount + startChannel)
{
if (this.mAVStream[channelIndex] != null)
{
val capture = HashMap<String, Any>()
val capturePath = captureDir + format + String.format("%02d.bmp", channelIndex + 1)
capture.put("channel", channelIndex)
capture.put("path", capturePath)
if (this.mAVStream[channelIndex]!!.Capture(capturePath) === 0)
{
captures.add(capture)
}
}
}
return captures
}

 fun popImageViewer(captures:List<Map<String, Any>>) {
if (captures.size !== 0)
{
this.mbmpList = captures
val captureCount = captures.size
val options = BitmapFactory.Options()
options.inSampleSize = 1
options.inTempStorage = ByteArray(829440)
this.mpopViewer = this.mInflater.inflate(R.layout.captureimageviewer, null as ViewGroup?)
val imageGrid = LinearLayout(this.mContext)
imageGrid.setOrientation(1)
imageGrid.setGravity(17)
imageGrid.setBackgroundColor(Color.argb(200, 40, 40, 40))
imageGrid.setPadding(0, 0, 0, 0)
val layoutParams = TableLayout.LayoutParams()
layoutParams.weight = 1.0f
layoutParams.height = -2
layoutParams.width = -2
layoutParams.setMargins(1, 1, 1, 1)
val gridSize = Math.sqrt((captureCount + 1).toDouble()) as Int
val displayMetrics = DisplayMetrics()
getWindowManager().getDefaultDisplay().getMetrics(displayMetrics)
var rowIndex = 0
while (rowIndex < gridSize)
{
val imageRow = LinearLayout(this.mContext)
imageRow.setOrientation(0)
imageRow.setPadding(0, 0, 0, 0)
imageRow.setBackgroundColor(DayStyle.iColorBkg)
val layoutParams2 = TableLayout.LayoutParams()
layoutParams2.weight = 1.0f
layoutParams2.width = displayMetrics.widthPixels / gridSize
if (this.bConfiguration && this.mVideoGroup!!.GetCurArrayMode() !== 1)
{
layoutParams2.height = (displayMetrics.heightPixels / gridSize) - 60
}
else if (this.bConfiguration && this.mVideoGroup!!.GetCurArrayMode() === 1)
{
layoutParams2.height = (displayMetrics.heightPixels / gridSize) - 120
}
else
{
layoutParams2.height = (layoutParams2.width / 4) * 3
}
for (columnIndex in 0 until gridSize)
{
val imageView = ImageView(this.mContext)
val captureIndex = (rowIndex * gridSize) + columnIndex
if (captureIndex < captureCount)
{
val imagePath = captures.get(captureIndex).get("path").toString()
imageView.setScaleType(ImageView.ScaleType.FIT_XY)
imageView.setImageBitmap(BitmapFactory.decodeFile(imagePath))
imageRow.addView(imageView, layoutParams2)
}
else
{
imageView.setScaleType(ImageView.ScaleType.FIT_XY)
imageView.setPadding(0, 0, 0, 0)
imageRow.addView(imageView, layoutParams2)
}
}
imageGrid.addView(imageRow, layoutParams)
rowIndex++
}
(this.mpopViewer.findViewById<View>(R.id.preview_capture_imagegroup) as LinearLayout).addView(imageGrid)
this.mPopupCapture = null
val popupWindow = PopupWindow(this.mpopViewer, -1, -1, true)
this.mPopupCapture = popupWindow
popupWindow.setOnDismissListener(this)
this.mPopupCapture!!.setOutsideTouchable(false)
this.mPopupCapture!!.setBackgroundDrawable(this.mContext.getResources().getDrawable(R.drawable.select_device_bg))
this.mPopupCapture!!.showAtLocation(this.mLiveView, 17, 0, 0)
this.mPopupCapture!!.update()
this.mpopViewer.findViewById<View>(R.id.preview_capture_save).setOnClickListener(object:View.OnClickListener {
 override fun onClick(view:View) {
this@LiveViewUi.mtvVideoInformation.setText(this@LiveViewUi.mContext.getString(R.string.save_successful))
this@LiveViewUi.mPopupCapture!!.dismiss()
}
})
this.mpopViewer.findViewById<View>(R.id.preview_capture_cancel).setOnClickListener(object:View.OnClickListener {
 override fun onClick(view:View) {
for (captureIndex in 0 until this@LiveViewUi.mbmpList.size)
{
val imagePath = this@LiveViewUi.mbmpList.get(captureIndex).get("path").toString()
if (imagePath != null)
{
File(imagePath).delete()
}
}
this@LiveViewUi.mtvVideoInformation.setText(this@LiveViewUi.mContext.getString(R.string.save_cancel))
this@LiveViewUi.mPopupCapture!!.dismiss()
}
})
}
}

 fun setVideoGroupMax(maximized:Boolean) {
if (maximized)
{
getWindow().getDecorView().setSystemUiVisibility(4)
this.mTitlebar.setVisibility(8)
val layoutParams = this.mPreviewLayout.getLayoutParams()
layoutParams.height = -1
this.mPreviewLayout.setLayoutParams(layoutParams)
val layoutParams2 = this.mVideoGroupLayout.getLayoutParams()
layoutParams2.height = -1
this.mVideoGroupLayout.setLayoutParams(layoutParams2)
this.mVideoInfo.setVisibility(8)
this.mScrollView.setVisibility(8)
return
}
getWindow().getDecorView().setSystemUiVisibility(0)
this.mTitlebar.setVisibility(0)
val layoutParams3 = this.mPreviewLayout.getLayoutParams()
layoutParams3.height = -2
this.mPreviewLayout.setLayoutParams(layoutParams3)
val layoutParams4 = this.mVideoGroupLayout.getLayoutParams()
layoutParams4.height = TypedValue.applyDimension(1, Strategy.TTL_SECONDS_DEFAULT as Float, getResources().getDisplayMetrics()) as Int
this.mVideoGroupLayout.setLayoutParams(layoutParams4)
this.mVideoInfo.setVisibility(0)
this.mScrollView.setVisibility(0)
}

 fun SetStreamDecodeState(streamIndex:Int, enabled:Boolean) {
val aVStreamArr = this.mAVStream
if (aVStreamArr[streamIndex] != null)
{
aVStreamArr[streamIndex]!!.SetStreamDecodeState(enabled)
}
}

 fun StartPlay(deviceId:Int, sourceChannel:Int, targetViewIndex:Int) {
val devInfoBean:DevInfoBean?
this.mbPreview = true
synchronized (lock) {
var channel = targetViewIndex
var connectionErrorCode = -1
if (this.mDevId != -1)
{
devInfoBean = this.mDevInfo
}
else if (MyApp.loginType === 0)
{
devInfoBean = this.mdbHelper!!.query(deviceId)
}
else
{
devInfoBean = this.mApp.mWebService!!.query(deviceId)
}
if (devInfoBean != null)
{
if (channel == -1)
{
channel = this.mVideoGroup!!.GetFocusChannel()
}
val singleViewInfoArr = this.mViewInfoArray
if (singleViewInfoArr[channel] == null || !singleViewInfoArr[channel]!!.check(devInfoBean) || sourceChannel != this.mViewInfoArray[channel]!!.mChannel)
{
val targetChannel = channel
this.mVideoGroup!!.post(object:Runnable {
 override fun run() {
this@LiveViewUi.mVideoGroup!!.SetBusyState(targetChannel, true)
}
})
val aVStreamArr = this.mAVStream
if (aVStreamArr[channel] != null)
{
aVStreamArr[channel]!!.StopRecord()
this.mAVStream[channel]!!.StopPlay()
this.mAVStream[channel]!!.CloseHandle()
}
else
{
aVStreamArr[channel] = AVStream()
}
var dvrNet:DvrNet? = null
val singleViewInfoArr2 = this.mViewInfoArray
if (singleViewInfoArr2[channel] == null)
{
singleViewInfoArr2[channel] = SingleViewInfo()
}
if (channel > 0)
{
val singleViewInfoArr3 = this.mViewInfoArray
val previousChannel = channel - 1
if (!(singleViewInfoArr3[previousChannel] == null || singleViewInfoArr3[previousChannel]!!.getDeviceInfoBean() == null || this.mViewInfoArray[previousChannel]!!.getDeviceInfoBean().mDevIp == null || !this.mViewInfoArray[previousChannel]!!.getDeviceInfoBean().mDevIp.equals(devInfoBean!!.mDevIp)))
{
dvrNet = this.mViewInfoArray[previousChannel]!!.getNet()
}
}
if (dvrNet == null)
{
this.mViewInfoArray[channel] = SingleViewInfo()
this.mViewInfoArray[channel]!!.setNet(DvrNet())
val connDeviceProxy = ConnDeviceProxy.connDeviceProxy(this.mViewInfoArray[channel]!!.getNet()!!, devInfoBean, this.mApp)
if (connDeviceProxy != null)
{
connectionErrorCode = (connDeviceProxy!!.get("errorcode") as Int)
}
if (connectionErrorCode == 0)
{
val aVStreamArr2 = this.mAVStream
if (aVStreamArr2[channel] == null)
{
aVStreamArr2[channel] = AVStream()
}
this.mAVStream[channel]!!.GetHandle(channel)
this.mVideoGroup!!.SetPlayState(channel, true)
this.mViewInfoArray[channel]!!.setDeviceInfo(devInfoBean)
this.mViewInfoArray[channel]!!.mChannel = sourceChannel
this.mAudioTrack.mPlayer.play()
this.mAudioTrack.SetMute(this.mbMute)
if (channel == this.mVideoGroup!!.GetFocusChannel())
{
this.mAVStream[channel]!!.SetMute(false)
this.mAudioTrack.SwitchChannels(channel)
}
else
{
this.mAVStream[channel]!!.SetMute(true)
}
this.mAVStream[channel]!!.StartPlay()
this.mAVStream[channel]!!.SetVideoInterface(this)
this.mAVStream[channel]!!.SetAudioInterface(this.mAudioTrack)
this.mViewInfoArray[channel]!!.getNet()!!.SetAVStream(sourceChannel, this.mAVStream[channel])
this.mViewInfoArray[channel]!!.getNet()!!.StartRealAv(sourceChannel, 0)
this.mCount++
}
else
{
if (connectionErrorCode == 64)
{
this.mtvVideoInformation.post(object:Runnable {
 override fun run() {
this@LiveViewUi.mtvVideoInformation.setText(this@LiveViewUi.mContext.getString(R.string.permissiondenied))
}
})
}
else if (connectionErrorCode == 63)
{
this.mtvVideoInformation.post(object:Runnable {
 override fun run() {
this@LiveViewUi.mtvVideoInformation.setText(this@LiveViewUi.mContext.getString(R.string.macillegal))
}
})
}
else if (connectionErrorCode == 24)
{
this.mtvVideoInformation.post(object:Runnable {
 override fun run() {
this@LiveViewUi.mtvVideoInformation.setText(this@LiveViewUi.mContext.getString(R.string.moreuseronline))
}
})
}
else if (connectionErrorCode == 5)
{
this.mtvVideoInformation.post(object:Runnable {
 override fun run() {
this@LiveViewUi.mtvVideoInformation.setText(this@LiveViewUi.mContext.getString(R.string.usernameorpassworderror))
}
})
}
this.mViewInfoArray[channel]!!.reset()
this.mViewInfoArray[channel]!!.setConnState(1)
}
}
else
{
val singleViewInfoArr4 = this.mViewInfoArray
if (singleViewInfoArr4[channel] == null)
{
singleViewInfoArr4[channel] = SingleViewInfo()
}
this.mViewInfoArray[channel]!!.setNet(dvrNet)
this.mAudioTrack.mPlayer.play()
this.mAudioTrack.SetMute(this.mbMute)
val aVStreamArr3 = this.mAVStream
if (aVStreamArr3[channel] == null)
{
aVStreamArr3[channel] = AVStream()
}
this.mAVStream[channel]!!.GetHandle(channel)
this.mVideoGroup!!.SetPlayState(channel, true)
this.mViewInfoArray[channel]!!.setDeviceInfo(devInfoBean)
this.mViewInfoArray[channel]!!.mChannel = sourceChannel
if (channel == this.mVideoGroup!!.GetFocusChannel())
{
this.mAudioTrack.SwitchChannels(channel)
this.mAVStream[channel]!!.SetMute(true)
}
else
{
this.mAVStream[channel]!!.SetMute(false)
}
this.mAVStream[channel]!!.StartPlay()
this.mAVStream[channel]!!.SetVideoInterface(this)
this.mAVStream[channel]!!.SetAudioInterface(this.mAudioTrack)
this.mViewInfoArray[channel]!!.getNet()!!.SetAVStream(sourceChannel, this.mAVStream[channel])
this.mViewInfoArray[channel]!!.getNet()!!.StartRealAv(sourceChannel, 0)
}
this.mVideoGroup!!.post(object:Runnable {
 override fun run() {
this@LiveViewUi.mVideoGroup!!.SetBusyState(targetChannel, false)
}
})
}
}
}
}

 fun StopPlay(i:Int) {
this.mbPreview = false
synchronized (lock) {
val singleViewInfoArr = this.mViewInfoArray
if (singleViewInfoArr[i] != null)
{
if (singleViewInfoArr[i]!!.getNet() != null)
{
this.mViewInfoArray[i]!!.getNet()!!.StopRealAv(this.mViewInfoArray[i]!!.mChannel)
var shouldCloseDevice = true
for (viewIndex in 0 until this.mnMaxView)
{
if (i != viewIndex)
{
val singleViewInfoArr2 = this.mViewInfoArray
if (singleViewInfoArr2[viewIndex] != null)
{
if (singleViewInfoArr2[viewIndex]!!.getNet() != null)
{
if (this.mViewInfoArray[viewIndex]!!.getNet() === this.mViewInfoArray[i]!!.getNet())
{
shouldCloseDevice = false
}
}
}
}
}
if (shouldCloseDevice)
{
this.mViewInfoArray[i]!!.getNet()!!.CloseDeviceHandle()
}
StopRecord(i)
val aVStreamArr = this.mAVStream
if (aVStreamArr[i] != null)
{
aVStreamArr[i]!!.StopPlay()
this.mAVStream[i]!!.CloseHandle()
this.mAVStream[i] = null
}
this.mVideoGroup!!.post(object:Runnable {
 override fun run() {
this@LiveViewUi.mVideoGroup!!.SetPlayState(this@LiveViewUi.mVideoGroup!!.GetFocusChannel(), false)
this@LiveViewUi.mtvVideoInformation.setText(this@LiveViewUi.mContext.getString(R.string.closevideo))
this@LiveViewUi.mbRecordArray[i] = false
this@LiveViewUi.setPlayStatus(1)
this@LiveViewUi.mVideoGroup!!.getVideoView(i).setVisibility(4)
}
})
this.mViewInfoArray[i]!!.reset()
}
}
}
}

 fun openSound(i:Int) {
if (this.mAVStream[i] != null)
{
val singleViewInfoArr = this.mViewInfoArray
if (singleViewInfoArr[i] != null && singleViewInfoArr[i]!!.getNet() != null)
{
this.mViewInfoArray[i]!!.getNet()!!.SetStreamSound(this.mViewInfoArray[i]!!.mChannel, this.mApp.mStreamType, true)
this.mAVStream[i]!!.SetMute(false)
this.mAudioTrack.SwitchChannels(i)
}
}
}

 override fun fuc(channel:Int, streamType:Int, frameData:ByteArray?, dataLength:Int, width:Int, height:Int) {
val videoView = this.mVideoGroup!!.getVideoView(channel)
if (videoView != null)
{
videoView!!.writeIn(frameData, width, height)
}
}

 /* access modifiers changed from: protected */
     override fun onPause() {
this.mbState = false
this.mAudioTrack.SetMute(true)
stopPlayVideo0()
this.mBtnPlay.setBackgroundResource(R.drawable.live_preview)
super.onPause()
}

 /* access modifiers changed from: protected */
     override fun onResume() {
this.mbState = true
this.mAudioTrack.SetMute(this.mbMute)
for (i in 0 until this.mnMaxView)
{
val singleViewInfoArr = this.mViewInfoArray
if (!(singleViewInfoArr[i] == null || singleViewInfoArr[i]!!.mDevInfo == null || this.mViewInfoArray[i]!!.getNet() == null))
{
this.mViewInfoArray[i]!!.getNet()!!.RealPlayControl(this.mViewInfoArray[i]!!.mChannel, 0, 1)
}
}
super.onResume()
}

 fun onEventMainThread(devInfoBean:DevInfoBean) {
this.mDevInfo = devInfoBean
initViewCounts(devInfoBean.mChCounts)
}

private fun playDev() {
stopPlayVideo0()
startPlayVideo0()
}

private fun stopPlayVideo0() {
MyApp.CONNTYPES = -1
this.mHandler.post(object:Runnable {
 override fun run() {
this@LiveViewUi.mVideoGroup!!.setVisibility(8)
this@LiveViewUi.mStopPbLoading.setVisibility(0)
}
})
this.initMode = this.mVideoGroup!!.getInitMode()
for (i in 0 until this.initMode)
{
StopPlay(i)
}
this.mAudioTrack.mPlayer.Stop()
this.mHandler.post(object:Runnable {
 override fun run() {
this@LiveViewUi.mVideoGroup!!.ClearViews()
this@LiveViewUi.mStopPbLoading.setVisibility(8)
this@LiveViewUi.mVideoGroup!!.setVisibility(0)
}
})
}

 /* access modifiers changed from: private */
     fun stopPlayVideo() {
MyApp.CONNTYPES = -1
this.mHandler.post(object:Runnable {
 override fun run() {
this@LiveViewUi.mVideoGroup!!.setVisibility(8)
this@LiveViewUi.mStopPbLoading.setVisibility(0)
}
})
this.initMode = this.mVideoGroup!!.getInitMode()
for (i in 0 until this.initMode)
{
StopPlay(i)
}
this.mAudioTrack.mPlayer.Stop()
this.mHandler.post(object:Runnable {
 override fun run() {
this@LiveViewUi.mVideoGroup!!.ClearViews()
this@LiveViewUi.mStopPbLoading.setVisibility(8)
this@LiveViewUi.mVideoGroup!!.setVisibility(0)
this@LiveViewUi.mRecordStack.clear()
}
})
}

private fun startPlayVideo0() {
this.mCount = 0
this.mDevId = 0
val devInfoBean = this.mDevInfo
if (devInfoBean != null)
{
this.mChannel = devInfoBean!!.mChCounts
}
this.mHandler.post(object:Runnable {
 override fun run() {
val liveViewUi = this@LiveViewUi
liveViewUi.initViewCounts(liveViewUi.mChannel)
for (i in 0 until this@LiveViewUi.mChannel)
{
this@LiveViewUi.mVideoGroup!!.getVideoView(i).setVisibility(0)
}
}
})
for (i in 0 until this.mChannel)
{
if (i > 0)
{
val singleViewInfoArr = this.mViewInfoArray
val previousViewIndex = i - 1
if (singleViewInfoArr[previousViewIndex] != null && singleViewInfoArr[previousViewIndex]!!.getConnState() === 1)
{
return
}
}
StartPlay(this.mDevId, i, i)
if (this.mVideoGroup!!.getVideoFrameVisibility(i) === 0)
{
if (!this.mViewIndexDatas.contains("" + i))
{
this.mViewIndexDatas.add("" + i)
}
}
}
this.mtvVideoInformation.post(object:Runnable {
 override fun run() {
if (this@LiveViewUi.mCount == 0)
{
this@LiveViewUi.mtvVideoInformation.setText(R.string.closevideo)
}
else
{
this@LiveViewUi.mBtnPlay.setBackgroundResource(R.drawable.live_preview)
}
}
})
}

private fun startPlayVideo(devInfoEvent:DevInfoEvent) {
var i = 0
this.mCount = 0
this.mtvVideoInformation.post(object:Runnable {
 override fun run() {
this@LiveViewUi.mtvVideoInformation.setText(R.string.openvideo)
}
})
this.mTime = -1
this.mDevId = devInfoEvent.mId
Log.e("mDevId", "" + this.mDevId)
if (this.mDevId != -1)
{
if (MyApp.loginType === 0)
{
this.mDevInfo = this.mdbHelper!!.query(this.mDevId)
}
else
{
this.mDevInfo = this.mApp.mWebService!!.query(this.mDevId)
}
val devInfoBean = this.mDevInfo
if (devInfoBean != null)
{
this.mChannel = devInfoBean!!.mChCounts
}
this.mHandler.post(object:Runnable {
 override fun run() {
val liveViewUi = this@LiveViewUi
liveViewUi.initViewCounts(liveViewUi.mChannel)
for (i in 0 until this@LiveViewUi.mChannel)
{
this@LiveViewUi.mVideoGroup!!.getVideoView(i).setVisibility(0)
}
}
})
while (i < this.mChannel)
{
if (i > 0)
{
val singleViewInfoArr = this.mViewInfoArray
val previousViewIndex = i - 1
if (singleViewInfoArr[previousViewIndex] != null && singleViewInfoArr[previousViewIndex]!!.getConnState() === 1)
{
return
}
}
StartPlay(this.mDevId, i, i)
if (this.mVideoGroup!!.getVideoFrameVisibility(i) === 0)
{
if (!this.mViewIndexDatas.contains("" + i))
{
this.mViewIndexDatas.add("" + i)
}
}
i++
}
this.mtvVideoInformation.post(object:Runnable {
 override fun run() {
if (this@LiveViewUi.mCount == 0)
{
this@LiveViewUi.mtvVideoInformation.setText(R.string.closevideo)
this@LiveViewUi.setPlayStatus(1)
return
}
this@LiveViewUi.setPlayStatus(0)
}
})
return
}
this.mChCount = 0
this.mGroupName = devInfoEvent.mGroupName
if (MyApp.loginType === 0)
{
this.mDao = GroupDaoForNormal()
}
else
{
this.mDao = GroupDaoForServer()
}
this.mBeanDatas = this.mDao!!.getGroupDatasByName(this.mGroupName!!)
for (groupIndex in 0 until this.mBeanDatas!!.size)
{
if (this.mBeanDatas!!.get(groupIndex).dbFlag === 1)
{
this.mChCount++
}
}
this.mHandler.post(object:Runnable {
 override fun run() {
val liveViewUi = this@LiveViewUi
liveViewUi.initViewCounts(liveViewUi.mChCount)
}
})
while (i < this.mBeanDatas!!.size)
{
val groupBean = this.mBeanDatas!!.get(i)
if (groupBean.dbFlag === 1)
{
val nextViewIndex = this.mTime + 1
this.mTime = nextViewIndex
if (nextViewIndex < 32)
{
StartPlay(groupBean.dbId, groupBean.dbChannel, this.mTime)
}
else
{
val startedCount = this.mCount
if (startedCount == 0)
{
this.mtvVideoInformation.post(object:Runnable {
 override fun run() {
this@LiveViewUi.mtvVideoInformation.setText(R.string.closevideo)
this@LiveViewUi.setPlayStatus(1)
}
})
}
else if (startedCount > 0)
{
this.mtvVideoInformation.post(object:Runnable {
 override fun run() {
this@LiveViewUi.setPlayStatus(0)
}
})
}
}
}
i++
}
}

 /* access modifiers changed from: private */
     fun initViewCounts(i:Int) {
if (i == 1)
{
this.mVideoGroup!!.SetInitMode(1)
setSurfaceListener(1)
}
else if (i <= 4)
{
this.mVideoGroup!!.SetInitMode(4)
setSurfaceListener(4)
}
else if (i <= 9)
{
this.mVideoGroup!!.SetInitMode(9)
setSurfaceListener(9)
}
else if (i <= 16)
{
this.mVideoGroup!!.SetInitMode(16)
setSurfaceListener(16)
}
else if (i <= 32)
{
this.mVideoGroup!!.SetInitMode(32)
setSurfaceListener(32)
}
}

 /* access modifiers changed from: protected */
     override fun onDestroy() {
EventBus.getDefault().unregister(this)
val dbhelper = this.mdbHelper
if (dbhelper != null)
{
dbhelper!!.close()
}
for (i in 0 until this.mnMaxView)
{
if (this.mViewInfoArray[i] == null)
{
getSharedPreferences(String.format("preview%02d", i), 0).edit().clear().commit()
}
else
{
if (this.mApp.mbAutoPlay)
{
try
{
this.mViewInfoArray[i]!!.mDevInfo.PrintOut()
val sharedPreferences = getSharedPreferences(String.format("preview%02d", i), 0)
val byteArrayOutputStream = ByteArrayOutputStream()
ObjectOutputStream(byteArrayOutputStream).writeObject(this.mViewInfoArray[i]!!.mDevInfo)
val encodedInfo = String(Base64.encode(byteArrayOutputStream.toByteArray(), 0))
sharedPreferences.edit().putInt("channel", this.mViewInfoArray[i]!!.mChannel).commit()
sharedPreferences.edit().putString("info", encodedInfo).commit()
}
catch (e:IOException) {
e.printStackTrace()
}

}
StopPlay(i)
}
}
this.mAudioTrack.mPlayer.Stop()
super.onDestroy()
}

 fun SwitchPlay() {
for (i in 0 until this.mVideoGroup!!.getInitViewCount())
{
val singleViewInfoArr = this.mViewInfoArray
if (!(singleViewInfoArr[i] == null || singleViewInfoArr[i]!!.getNet() == null || this.mViewInfoArray[i]!!.mDevInfo == null || this.mViewInfoArray[i]!!.getNet()!!.mNetType === 2 || this.mVideoGroup!!.getVideoFrameVisibility(i) !== 0))
{
val list = this.mViewIndexDatas
if (!list.contains("" + i))
{
val list2 = this.mViewIndexDatas
list2.add("" + i)
}
}
}
this.mbSwitch = true
}

inner class SwitchChannelRunnable:Runnable {

 override fun run() {
while (true)
{
while (!this@LiveViewUi.mbSwitch)
{
try
{
Thread.sleep(500)
}
catch (e:InterruptedException) {
e.printStackTrace()
}

}
if (this@LiveViewUi.mViewIndexDatas.size === 0)
{
continue
}
this@LiveViewUi.mbSwitch = false
this@LiveViewUi.mHandler.post(object:Runnable {
 override fun run() {}
})
var streamType = 0
if (this@LiveViewUi.mVideoGroup!!.getCurViewCount() === 1 && MyApp.smartStreamStatus === 1)
{
val singleStreamType = MyApp.singleStreamType
if (singleStreamType == 1)
{
streamType = 1
}
else if (singleStreamType == 3)
{
streamType = 2
}
else if (singleStreamType == 0)
{
val focusView = this@LiveViewUi.mVideoGroup!!.getFocusView()
val viewInfoArray = this@LiveViewUi.mViewInfoArray
if (viewInfoArray[focusView] != null && viewInfoArray[focusView]!!.getNet() != null && viewInfoArray[focusView]!!.getNet()!!.nDevClass === 2)
{
streamType = 2
}
else
{
streamType = 1
}
}
}
for (i in 0 until this@LiveViewUi.mVideoGroup!!.getInitViewCount())
{
if (!this@LiveViewUi.mViewIndexDatas.contains("" + i))
{
val viewInfo = this@LiveViewUi.mViewInfoArray[i]
if (viewInfo == null || viewInfo!!.getNet() == null || viewInfo!!.mChannel < 0)
{
continue
}
if (viewInfo!!.getNet()!!.getChannelState(viewInfo!!.mChannel) === 0)
{
viewInfo!!.getNet()!!.StopRealAv(viewInfo!!.mChannel)
}
if (this@LiveViewUi.mVideoGroup!!.getVideoFrameVisibility(i) !== 8)
{
continue
}
val avStreams = this@LiveViewUi.mAVStream
if (avStreams[i] == null)
{
continue
}
avStreams[i]!!.StopPlay()
viewInfo!!.getNet()!!.SetAVStream(viewInfo!!.mChannel, null)
avStreams[i]!!.SetVideoInterface(null)
avStreams[i]!!.SetAudioInterface(null)
viewInfo!!.getNet()!!.StopRealAv(viewInfo!!.mChannel)
avStreams[i]!!.CloseHandle()
avStreams[i] = null
continue
}
val viewInfo2 = this@LiveViewUi.mViewInfoArray[i]
val net2 = viewInfo2?.getNet()
if (viewInfo2 == null || net2 == null || viewInfo2.mChannel < 0)
{
continue
}
if (net2.getChannelState(viewInfo2.mChannel) === 0)
{
if (this@LiveViewUi.mVideoGroup!!.getCurViewCount() === 1)
{
if (i == this@LiveViewUi.mVideoGroup!!.getFocusView())
{
if (MyApp.CONNTYPES === 2)
{
continue
}
val net = net2
val channel = viewInfo2.mChannel
net!!.RealPlayControl(channel, streamType, 3)
net.RealPlayControl(channel, streamType, 1)
net.RealPlayControl(channel, streamType, 4)
this@LiveViewUi.mVideoGroup!!.post(object:Runnable {
 override fun run() {}
})
continue
}
else
{
val net = net2
val channel = viewInfo2.mChannel
if (net!!.getChannelState(channel) === 0)
{
net!!.RealPlayControl(channel, 0, 2)
}
this@LiveViewUi.mVideoGroup!!.post(object:Runnable {
 override fun run() {}
})
continue
}
}
else
{
this@LiveViewUi.setSurfaceVisibility()
if (i == this@LiveViewUi.mVideoGroup!!.getFocusView())
{
net2.RealPlayControl(viewInfo2.mChannel, 0, 3)
}
else
{
net2.RealPlayControl(viewInfo2.mChannel, 0, 1)
}
net2.RealPlayControl(viewInfo2.mChannel, 0, 4)
continue
}
}
if (this@LiveViewUi.mAVStream[i] == null)
{
this@LiveViewUi.mAVStream[i] = AVStream()
}
this@LiveViewUi.mAVStream[i]!!.GetHandle(i)
if (this@LiveViewUi.mVideoGroup!!.getFocusView() === i)
{
this@LiveViewUi.mAVStream[i]!!.SetMute(false)
this@LiveViewUi.mAudioTrack.SwitchChannels(i)
}
else
{
this@LiveViewUi.mAVStream[i]!!.SetMute(true)
}
this@LiveViewUi.mAVStream[i]!!.StartPlay()
this@LiveViewUi.mAVStream[i]!!.SetVideoInterface(this@LiveViewUi)
this@LiveViewUi.mAVStream[i]!!.SetAudioInterface(this@LiveViewUi.mAudioTrack)
net2.SetAVStream(viewInfo2.mChannel, this@LiveViewUi.mAVStream[i])
net2.StartRealAv(viewInfo2.mChannel, streamType)
}
this@LiveViewUi.mHandler.post(object:Runnable {
 override fun run() {}
})
}
}
}

 override fun onClick(view:View) {
if (view.getId() === R.id.preview_title_button_play)
{
playDev()
}
}

private fun selectDev() {
val linearLayout = this.mInflater.inflate(R.layout.popupwindow_device, null as ViewGroup?) as LinearLayout
val deviceSelect = linearLayout.findViewById<View>(R.id.device_select_device_view) as DeviceSelect
deviceSelect.setOnItemClickListener(object:AdapterView.OnItemClickListener {
 override fun onItemClick(adapterView:AdapterView<*>, view:View, i:Int, j:Long) {
if (i < this@LiveViewUi.mDevLists.size)
{
EventBus.getDefault().post(DevInfoEvent((view as TextView).getTag() as Int, ""))
}
else
{
EventBus.getDefault().post(DevInfoEvent(-1, this@LiveViewUi.mGroupNameDatas.get(i - this@LiveViewUi.mDevLists.size)))
}
this@LiveViewUi.pop!!.dismiss()
this@LiveViewUi.pop = null
}
})
if (MyApp.loginType === 0)
{
this.mDao = GroupDaoForNormal()
this.mDevLists = this.mdbHelper!!.getlist()
}
else
{
this.mDao = GroupDaoForServer()
this.mDevLists = this.mApp.mWebService!!.GetTerminalInfoAndroid(true)
}
this.mGroupLists = this.mDao!!.getGroupDatas()
this.mGroupNameDatas = ArrayList()
val list = this.mGroupLists
if (list != null && list!!.size > 0)
{
for (i in 0 until this.mGroupLists!!.size)
{
val groupBean = this.mGroupLists!!.get(i)
if (!this.mGroupNameDatas.contains(groupBean.dbGroupName))
{
this.mGroupNameDatas.add(groupBean.dbGroupName!!)
}
}
}
deviceSelect.SetData(this.mDevLists, this.mGroupNameDatas)
popMenu(linearLayout, this.mBtnSelect)
}

 fun onEventMainThread(deleteDeviceEvent:DeleteDeviceEvent) {
if (deleteDeviceEvent.mDeviceId !== 0)
{
Thread(object:Runnable {
 override fun run() {
this@LiveViewUi.stopPlayVideo()
}
}).start()
}
}

 fun setSurfaceListener(viewCount:Int) {
for (viewIndex in 0 until viewCount)
{
this.mVideoGroup!!.getVideoView(viewIndex).setCallBack(this, viewIndex)
}
}

 override fun surfaceChanged(surfaceHolder:SurfaceHolder, format:Int, width:Int, height:Int, videoIndex:Int) {
Log.e("surfaceChanged", "" + videoIndex)
}

 override fun surfaceCreated(surfaceHolder:SurfaceHolder, videoIndex:Int) {
Log.e("surfaceCreated", "" + videoIndex)
}

 override fun surfaceDestroyed(surfaceHolder:SurfaceHolder, videoIndex:Int) {
Log.e("surfaceDestroyed", "" + videoIndex)
}

 fun setSurfaceVisibility() {
val initViewCount = this.mVideoGroup!!.getInitViewCount()
val curViewCount = this.mVideoGroup!!.getCurViewCount()
val firstIndex = this.mVideoGroup!!.getFirstIndex()
if (curViewCount == 4)
{
if (firstIndex >= 0 && firstIndex <= 28 && firstIndex % 4 == 0)
{
setVisibleRange(initViewCount, firstIndex, firstIndex + 3)
}
}
else if (curViewCount == 9)
{
if (firstIndex == 0 || firstIndex == 7 || firstIndex == 9 || firstIndex == 18)
{
setVisibleRange(initViewCount, firstIndex, firstIndex + 8)
}
else if (firstIndex == 27)
{
setWrappedVisibleRange(initViewCount, 27, 3)
}
}
else if (curViewCount == 16)
{
if (firstIndex == 0)
{
setVisibleRange(initViewCount, 0, 15)
}
else if (firstIndex == 16)
{
setVisibleRange(initViewCount, 16, 31)
}
}
}

private fun setVisibleRange(initViewCount:Int, firstIndex:Int, lastIndex:Int) {
for (viewIndex in 0 until initViewCount)
{
isVisible(viewIndex, viewIndex >= firstIndex && viewIndex <= lastIndex)
}
}

private fun setWrappedVisibleRange(initViewCount:Int, firstIndex:Int, lastIndex:Int) {
val maxIndex = CHANNEL_NUMBER - 1
for (viewIndex in 0 until initViewCount)
{
val visible = (viewIndex >= firstIndex && viewIndex <= maxIndex) || viewIndex <= lastIndex
isVisible(viewIndex, visible)
}
}

 fun isVisible(viewIndex:Int, visible:Boolean) {
if (visible)
{
this.mVideoGroup!!.post(object:Runnable {
 override fun run() {
this@LiveViewUi.mVideoGroup!!.getVideoView(viewIndex).setVisibility(0)
}
})
}
else
{
this.mVideoGroup!!.post(object:Runnable {
 override fun run() {
this@LiveViewUi.mVideoGroup!!.getVideoView(viewIndex).setVisibility(8)
}
})
}
}

companion object {
 val CHANNEL_NUMBER = 32
 val GroupPlay = -1
private val PlayStatusPlaying = 0
private val PlayStatusStopped = 1
 var lock = Any()

 fun removeDuplicate(list:List<String>):List<String> {
 val arrayList = ArrayList<String>()
for (i in 0 until list.size)
{
if (!arrayList.contains(list.get(i)))
{
arrayList.add(list.get(i))
}
}
return arrayList
}
}
}
