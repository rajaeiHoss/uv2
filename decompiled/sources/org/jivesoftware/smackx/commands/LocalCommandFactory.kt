package org.jivesoftware.smackx.commands

interface LocalCommandFactory {
    @Throws(InstantiationException::class, IllegalAccessException::class)
    fun getInstance(): LocalCommand
}
