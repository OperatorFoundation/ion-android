package org.operatorfoundation.ion

import org.junit.Test
import org.junit.Assert.*
import ion.Connection
import ion.storage.I
import ion.storage.StorageType
import ion.storage.Word

class TestConnection: Connection
{
    private val writeBuffer = mutableListOf<Byte>()
    private var readData = byteArrayOf()
    private var readIndex = 0

    fun setReadData(data: ByteArray)
    {
        readData = data
        readIndex = 0
    }

    fun getWrittenBytes(): ByteArray = writeBuffer.toByteArray()

    override fun readOne(): Byte
    {
        if (readIndex < readData.size)
        {
            val readByte = readData[readIndex]
            readIndex += 1
            return readByte
        }
        else
        {
            return 0
        }
    }

    override fun read(length: Int): ByteArray
    {
        val result = readData.sliceArray(readIndex until minOf(readIndex + length, readData.size))
        readIndex += length
        return result
    }

    override fun write(bytes: ByteArray)
    {
        writeBuffer.addAll(bytes.toList())
    }

}

class WordTest
{
    @Test
    fun testMakeCreatesStorageWithValidValues()
    {
        val result = Word.make(42, 1)

        assertEquals(1, result.o)
        assertEquals(StorageType.WORD.value, result.t)
        assertTrue(result.i is I.Word)
        assertEquals(42, (result.i as I.Word).value)
    }

    @Test
    fun testToBytesWithWordStorage()
    {
        val storage = Word.make(100, 1)
        val result = Word.to_bytes(storage)

        assertNotNull(result)
    }

    @Test
    fun testToConnWithGetWrittenBytes()
    {
        {
            val storage = Word.make(42, 3) // o=3, value=42
            val conn = TestConnection()

            Word.to_conn(conn, storage)

            val written = conn.getWrittenBytes()
            assertTrue("Should write at least type and object bytes", written.size >= 2)

            // First byte should be storage type (WORD = 0)
            assertEquals(StorageType.WORD.value.toByte(), written[0])

            // Second byte should be object type (3)
            assertEquals(3.toByte(), written[1])

            // Remaining bytes should be squeeze_int(42) result
            assertTrue("Should write more than just type bytes", written.size > 2)
        }
    }
}