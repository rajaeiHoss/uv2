package com.hjq.http.callback

import android.text.TextUtils
import com.hjq.http.EasyLog
import com.hjq.http.EasyUtils
import com.hjq.http.exception.MD5Exception
import com.hjq.http.exception.NullBodyException
import com.hjq.http.lifecycle.HttpLifecycleManager
import com.hjq.http.listener.OnDownloadListener
import com.hjq.http.model.FileWrapper
import com.hjq.http.request.BaseRequest
import java.io.IOException
import okhttp3.Call
import okhttp3.Response

class DownloadCallback(baseRequest: BaseRequest<*>) : BaseCallback(baseRequest) {
    private val mBaseRequest: BaseRequest<*> = baseRequest
    private var mDownloadByte: Long = 0
    private var mDownloadProgress: Int = 0
    private lateinit var mFile: FileWrapper
    private var mListener: OnDownloadListener? = null
    private var mMd5: String? = null
    private var mTotalByte: Long = 0

    fun setFile(fileWrapper: FileWrapper): DownloadCallback {
        mFile = fileWrapper
        return this
    }

    fun setMd5(str: String?): DownloadCallback {
        mMd5 = str
        return this
    }

    fun setListener(onDownloadListener: OnDownloadListener?): DownloadCallback {
        mListener = onDownloadListener
        return this
    }

    override fun onStart(call: Call) {
        EasyUtils.post {
            if (mListener != null && HttpLifecycleManager.isLifecycleActive(mBaseRequest.lifecycleOwner)) {
                mListener?.onStart(mFile)
            }
        }
    }

    @Throws(Exception::class)
    override fun onResponse(response: Response) {
        if (mMd5 == null) {
            val header = response.header("Content-MD5")
            if (!TextUtils.isEmpty(header) && header!!.matches(FILE_MD5_REGEX.toRegex())) {
                mMd5 = header
            }
        }
        val parentFile = mFile.parentFile
        if (parentFile != null) {
            FileWrapper.createFolder(parentFile)
        }
        val body = response.body() ?: throw NullBodyException("The response body is empty")
        mTotalByte = body.contentLength()
        if (mTotalByte < 0) {
            mTotalByte = 0
        }
        if (TextUtils.isEmpty(mMd5) || !mFile.isFile || !mMd5!!.equals(
                FileWrapper.getFileMd5(mFile.openInputStream()),
                true
            )
        ) {
            mDownloadByte = 0
            val bArr = ByteArray(8192)
            val byteStream = body.byteStream()
            val openOutputStream = mFile.openOutputStream() ?: throw IOException("Failed to open output stream")
            while (true) {
                val read = byteStream.read(bArr)
                if (read == -1) {
                    break
                }
                mDownloadByte += read.toLong()
                openOutputStream.write(bArr, 0, read)
                EasyUtils.post {
                    onDownloadProgress()
                }
            }
            EasyUtils.closeStream(byteStream)
            EasyUtils.closeStream(openOutputStream)
            val fileMd5 = FileWrapper.getFileMd5(mFile.openInputStream())
            if (TextUtils.isEmpty(mMd5) || mMd5!!.equals(fileMd5, true)) {
                EasyUtils.post {
                    onDownloadComplete()
                }
                return
            }
            throw MD5Exception("MD5 verify failure", fileMd5 ?: "")
        }
        EasyUtils.post {
            onDownloadComplete()
        }
    }

    private fun onDownloadProgress() {
        if (mListener != null && HttpLifecycleManager.isLifecycleActive(mBaseRequest.lifecycleOwner)) {
            mListener?.onByte(mFile, mTotalByte, mDownloadByte)
            val progressProgress = EasyUtils.getProgressProgress(mTotalByte, mDownloadByte)
            if (progressProgress != mDownloadProgress) {
                mDownloadProgress = progressProgress
                mListener?.onProgress(mFile, progressProgress)
                EasyLog.print(mFile.path + " 正在下载，总字节：" + mTotalByte + "，已下载：" + mDownloadByte + "，进度：" + progressProgress + " %")
            }
        }
    }

    private fun onDownloadComplete() {
        if (mListener != null && HttpLifecycleManager.isLifecycleActive(mBaseRequest.lifecycleOwner)) {
            mListener?.onComplete(mFile)
            mListener?.onEnd(mFile)
        }
    }

    override fun onFailure(exc: Exception) {
        val requestFail = mBaseRequest.requestHandler.requestFail(
            mBaseRequest.lifecycleOwner,
            mBaseRequest.requestApi,
            exc
        )
        EasyLog.print(requestFail)
        val error = requestFail
        EasyUtils.post {
            if (mListener != null && HttpLifecycleManager.isLifecycleActive(mBaseRequest.lifecycleOwner)) {
                mListener?.onError(mFile, error)
                mListener?.onEnd(mFile)
            }
        }
    }

    companion object {
        private const val FILE_MD5_REGEX = "^[\\w]{32}$"
    }
}
