package org.jivesoftware.smackx.filetransfer

interface FileTransferListener {
    fun fileTransferRequest(fileTransferRequest: FileTransferRequest)
}
