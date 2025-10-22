package org.operatorfoundation.ion

import org.operatorfoundation.ion.Connection
import java.util.concurrent.ArrayBlockingQueue

class PipeEnd(
    private val readBuffer: ArrayBlockingQueue<Byte>,
    private val writeBuffer: ArrayBlockingQueue<Byte>
) : Connection {
    override fun readOne(): Byte {
        return readBuffer.take()
    }
    
    override fun read(size: Int): ByteArray {
        val result = mutableListOf<Byte>()
        repeat(size) {
            val byte = readBuffer.poll() ?: return result.toByteArray()
            result.add(byte)
        }
        return result.toByteArray()
    }
    
    override fun write(data: ByteArray) {
        for (byte in data) {
            writeBuffer.put(byte)
        }
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
