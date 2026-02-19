package com.hjq.http.body

import androidx.lifecycle.LifecycleOwner
import com.hjq.http.EasyLog
import com.hjq.http.EasyUtils
import com.hjq.http.lifecycle.HttpLifecycleManager
import com.hjq.http.listener.OnUpdateListener
import java.io.IOException
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.Buffer
import okio.BufferedSink
import okio.ForwardingSink
import okio.Okio
import okio.Sink

open class ProgressBody(
    private val mRequestBody: RequestBody,
    private val mLifecycleOwner: LifecycleOwner,
    private val mListener: OnUpdateListener<*>?
) : RequestBody() {
    private var mTotalByte: Long = 0
    private var mUpdateByte: Long = 0
    private var mUpdateProgress: Int = 0

    override fun contentType(): MediaType? {
        return mRequestBody.contentType()
    }

    @Throws(IOException::class)
    override fun contentLength(): Long {
        return mRequestBody.contentLength()
    }

    @Throws(IOException::class)
    override fun writeTo(bufferedSink: BufferedSink) {
        mTotalByte = contentLength()
        val buffer = Okio.buffer(WrapperSink(bufferedSink))
        mRequestBody.writeTo(buffer)
        buffer.flush()
    }

    private inner class WrapperSink(sink: Sink) : ForwardingSink(sink) {
        @Throws(IOException::class)
        override fun write(buffer: Buffer, j: Long) {
            super.write(buffer, j)
            mUpdateByte += j
            EasyUtils.post { onWriteProgress() }
        }

        private fun onWriteProgress() {
            if (mListener != null && HttpLifecycleManager.isLifecycleActive(mLifecycleOwner)) {
                mListener.onByte(mTotalByte, mUpdateByte)
            }
            val progressProgress = EasyUtils.getProgressProgress(mTotalByte, mUpdateByte)
            if (progressProgress != mUpdateProgress) {
                mUpdateProgress = progressProgress
                if (mListener != null && HttpLifecycleManager.isLifecycleActive(mLifecycleOwner)) {
                    mListener.onProgress(progressProgress)
                }
                EasyLog.print("正在进行上传，总字节：$mTotalByte，已上传：$mUpdateByte，进度：$progressProgress%")
            }
        }
    }
}
