package org.operatorfoundation.ion

import org.operatorfoundation.transmission.BaseConnection
import java.util.concurrent.ArrayBlockingQueue
import java.util.logging.Logger

class PipeEnd(
    private val readBuffer: ArrayBlockingQueue<Byte>,
    private val writeBuffer: ArrayBlockingQueue<Byte>,
    logger: Logger? = null
) : BaseConnection(logger) {
    override fun networkRead(size: Int): ByteArray {
        val result = mutableListOf<Byte>()
        repeat(size) {
            val byte = readBuffer.poll() ?: return result.toByteArray()
            result.add(byte)
        }
        return result.toByteArray()
    }
    
    override fun networkWrite(data: ByteArray): Boolean {
        for (byte in data) {
            writeBuffer.put(byte)
        }

        return true
    }

    override fun networkClose() {
        return
    }

    fun available(): Int = readBuffer.size
    
    fun writeSpace(): Int = writeBuffer.remainingCapacity()
}

class Pipe {
    private val bufferAtoB = ArrayBlockingQueue<Byte>(4096)
    private val bufferBtoA = ArrayBlockingQueue<Byte>(4096)
    
    val endA: PipeEnd = PipeEnd(bufferBtoA, bufferAtoB)
    val endB: PipeEnd = PipeEnd(bufferAtoB, bufferBtoA)
}
