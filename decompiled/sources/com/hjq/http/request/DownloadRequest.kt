package com.hjq.http.request

import android.content.ContentResolver
import android.net.Uri
import androidx.lifecycle.LifecycleOwner
import com.hjq.http.EasyLog
import com.hjq.http.EasyUtils
import com.hjq.http.callback.DownloadCallback
import com.hjq.http.config.IRequestApi
import com.hjq.http.config.IRequestServer
import com.hjq.http.config.RequestApi
import com.hjq.http.config.RequestServer
import com.hjq.http.lifecycle.HttpLifecycleManager
import com.hjq.http.listener.OnDownloadListener
import com.hjq.http.listener.OnHttpListener
import com.hjq.http.model.BodyType
import com.hjq.http.model.CallProxy
import com.hjq.http.model.FileContentResolver
import com.hjq.http.model.FileWrapper
import com.hjq.http.model.HttpHeaders
import com.hjq.http.model.HttpMethod
import com.hjq.http.model.HttpParams
import com.hjq.http.model.ResponseClass
import java.io.File
import okhttp3.Request

class DownloadRequest(
    lifecycleOwner: LifecycleOwner
) : BaseRequest<DownloadRequest>(lifecycleOwner) {
    private var mCallProxy: CallProxy? = null
    private var mFile: FileWrapper? = null
    private var mListener: OnDownloadListener? = null
    private var mMd5: String? = null
    private var mMethod: HttpMethod = HttpMethod.GET

    fun method(httpMethod: HttpMethod): DownloadRequest {
        mMethod = httpMethod
        return this
    }

    fun url(str: String): DownloadRequest {
        server(RequestServer(str) as IRequestServer)
        api(RequestApi("") as IRequestApi)
        return this
    }

    fun file(str: String): DownloadRequest {
        return file(File(str))
    }

    fun file(file: File): DownloadRequest {
        if (file is FileContentResolver) {
            return file(file)
        }
        mFile = FileWrapper(file)
        return this
    }

    fun file(contentResolver: ContentResolver, uri: Uri): DownloadRequest {
        return file(FileContentResolver(contentResolver, uri))
    }

    fun file(fileContentResolver: FileContentResolver): DownloadRequest {
        mFile = fileContentResolver
        return this
    }

    fun md5(str: String?): DownloadRequest {
        mMd5 = str
        return this
    }

    fun listener(onDownloadListener: OnDownloadListener?): DownloadRequest {
        mListener = onDownloadListener
        return this
    }

    override fun createRequest(
        str: String,
        str2: String?,
        httpParams: HttpParams,
        httpHeaders: HttpHeaders,
        bodyType: BodyType
    ): Request {
        return when (mMethod) {
            HttpMethod.GET -> GetRequest(getLifecycleOwner()).createRequest(str, str2, httpParams, httpHeaders, bodyType)
            HttpMethod.POST -> PostRequest(getLifecycleOwner()).createRequest(str, str2, httpParams, httpHeaders, bodyType)
            else -> throw IllegalStateException("method nonsupport")
        }
    }

    fun start(): DownloadRequest {
        val delayMillis = getDelayMillis()
        if (delayMillis > 0) {
            EasyLog.print("RequestDelay", delayMillis.toString())
        }
        val stackTrace = Throwable().stackTrace
        EasyUtils.postDelayed(
            { startInternal(stackTrace) },
            delayMillis
        )
        return this
    }

    private fun startInternal(stackTraceElementArr: Array<StackTraceElement>) {
        if (!HttpLifecycleManager.isLifecycleActive(getLifecycleOwner())) {
            EasyLog.print("宿主已被销毁，请求无法进行")
            return
        }
        EasyLog.print(stackTraceElementArr)
        mCallProxy = CallProxy(createCall())
        DownloadCallback(this)
            .setFile(mFile ?: throw IllegalStateException("The file cannot be empty"))
            .setMd5(mMd5)
            .setListener(mListener)
            .setCall(mCallProxy!!)
            .start()
    }

    fun stop(): DownloadRequest {
        mCallProxy?.cancel()
        return this
    }

    override fun request(onHttpListener: OnHttpListener<*>?) {
        throw IllegalStateException("Call the start method")
    }

    override fun <Bean> execute(responseClass: ResponseClass<Bean>): Bean {
        throw IllegalStateException("Call the start method")
    }

    override fun cancel(): DownloadRequest {
        throw IllegalStateException("Call the start method")
    }

    override fun getRequestMethod(): String {
        return mMethod.toString()
    }
}
