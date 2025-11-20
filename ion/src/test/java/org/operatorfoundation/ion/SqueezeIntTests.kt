package org.operatorfoundation.ion

import org.junit.Test

class SqueezeIntTests
{
    @Test
    fun testSqueezeInt0()
    {
        println("Testing squeeze int 0...")
        val input = 0
        val data = squeeze_int(input)
        val (value, rest) = expand_int(data)
        assert(value is Varint.IonInt) { "Expected Varint.IonInt, got ${value::class}" }
        if(value is Varint.IonInt)
        {
            val output = value.value
            assert(input == output) { "Expected $input, got $output" }
        }
        assert(rest.isEmpty()) { "Expected empty rest, got ${rest.size} bytes" }
        println("✓ testSqueezeInt0 passed")
    }

    @Test
    fun testSqueezeInt1()
    {
        println("Testing squeeze int 1...")
        val input = 1
        val data = squeeze_int(input)
        val (value, rest) = expand_int(data)
        assert(value is Varint.IonInt) { "Expected Varint.IonInt, got ${value::class}" }
        if(value is Varint.IonInt)
        {
            val output = value.value
            assert(input == output) { "Expected $input, got $output" }
        }
        assert(rest.isEmpty()) { "Expected empty rest, got ${rest.size} bytes" }
        println("✓ testSqueezeInt1 passed")
    }

    @Test
    fun testSqueezeIntNeg1()
    {
        println("Testing squeeze int -1...")
        val input = -1
        val data = squeeze_int(input)
        val (value, rest) = expand_int(data)
        assert(value is Varint.IonInt) { "Expected Varint.IonInt, got ${value::class}" }
        if(value is Varint.IonInt)
        {
            val output = value.value
            assert(input == output) { "Expected $input, got $output" }
        }
        assert(rest.isEmpty()) { "Expected empty rest, got ${rest.size} bytes" }
        println("✓ testSqueezeIntNeg1 passed")
    }

    @Test
    fun testSqueezeInt256()
    {
        println("Testing squeeze int 256...")
        val input = 256
        val data = squeeze_int(input)
        val (value, rest) = expand_int(data)
        assert(value is Varint.IonInt) { "Expected Varint.IonInt, got ${value::class}" }
        if(value is Varint.IonInt)
        {
            val output = value.value
            assert(input == output) { "Expected $input, got $output" }
        }
        assert(rest.isEmpty()) { "Expected empty rest, got ${rest.size} bytes" }
        println("✓ testSqueezeInt256 passed")
    }

    @Test
    fun testSqueezeIntNeg256()
    {
        println("Testing squeeze int -256...")
        val input = -256
        val data = squeeze_int(input)
        val (value, rest) = expand_int(data)
        assert(value is Varint.IonInt) { "Expected Varint.IonInt, got ${value::class}" }
        if(value is Varint.IonInt)
        {
            val output = value.value
            assert(input == output) { "Expected $input, got $output" }
        }
        assert(rest.isEmpty()) { "Expected empty rest, got ${rest.size} bytes" }
        println("✓ testSqueezeIntNeg256 passed")
    }

    @Test
    fun testSqueezeIntBit7Set()
    {
        println("Testing squeeze int with bit 7 set (128)...")
        val input = 128 // 0x80 - bit 7 set
        val data = squeeze_int(input)
        val (value, rest) = expand_int(data)
        assert(value is Varint.IonInt) { "Expected Varint.IonInt, got ${value::class}" }
        if(value is Varint.IonInt)
        {
            val output = value.value
            assert(input == output) { "Expected $input, got $output" }
        }
        assert(rest.isEmpty()) { "Expected empty rest, got ${rest.size} bytes" }
        println("✓ testSqueezeIntBit7Set passed")
    }

    @Test
    fun testSqueezeIntBit7SetNegative()
    {
        println("Testing squeeze int with bit 7 set negative (-128)...")
        val input = -128 // -0x80
        val data = squeeze_int(input)
        val (value, rest) = expand_int(data)
        assert(value is Varint.IonInt) { "Expected Varint.IonInt, got ${value::class}" }
        if(value is Varint.IonInt)
        {
            val output = value.value
            assert(input == output) { "Expected $input, got $output" }
        }
        assert(rest.isEmpty()) { "Expected empty rest, got ${rest.size} bytes" }
        println("✓ testSqueezeIntBit7SetNegative passed")
    }

    @Test
    fun testSqueezeIntMax2ByteSigned()
    {
        println("Testing squeeze int max 2-byte signed (32767)...")
        val input = 32767 // 0x7FFF
        val data = squeeze_int(input)
        val (value, rest) = expand_int(data)
        assert(value is Varint.IonInt) { "Expected Varint.IonInt, got ${value::class}" }
        if(value is Varint.IonInt)
        {
            val output = value.value
            assert(input == output) { "Expected $input, got $output" }
        }
        assert(rest.isEmpty()) { "Expected empty rest, got ${rest.size} bytes" }
        println("✓ testSqueezeIntMax2ByteSigned passed")
    }

    @Test
    fun testSqueezeIntBit15Set()
    {
        println("Testing squeeze int with bit 15 set (32768)...")
        val input = 32768 // 0x8000
        val data = squeeze_int(input)
        val (value, rest) = expand_int(data)
        assert(value is Varint.IonInt) { "Expected Varint.IonInt, got ${value::class}" }
        if(value is Varint.IonInt)
        {
            val output = value.value
            assert(input == output) { "Expected $input, got $output" }
        }
        assert(rest.isEmpty()) { "Expected empty rest, got ${rest.size} bytes" }
        println("✓ testSqueezeIntBit15Set passed")
    }

    @Test
    fun testSqueezeIntBit15SetNegative()
    {
        println("Testing squeeze int with bit 15 set negative (-32768)...")
        val input = -32768
        val data = squeeze_int(input)
        val (value, rest) = expand_int(data)
        assert(value is Varint.IonInt) { "Expected Varint.IonInt, got ${value::class}" }
        if(value is Varint.IonInt)
        {
            val output = value.value
            assert(input == output) { "Expected $input, got $output" }
        }
        assert(rest.isEmpty()) { "Expected empty rest, got ${rest.size} bytes" }
        println("✓ testSqueezeIntBit15SetNegative passed")
    }

    @Test
    fun testSqueezeIntBit31Near()
    {
        println("Testing squeeze int near bit 31 (1014850000)...")
        val input = 1014850000 // 0x3C7626D0 - high bit usage
        val data = squeeze_int(input)
        val (value, rest) = expand_int(data)
        assert(value is Varint.IonInt) { "Expected Varint.IonInt, got ${value::class}" }
        if(value is Varint.IonInt)
        {
            val output = value.value
            assert(input == output) { "Expected $input, got $output" }
        }
        assert(rest.isEmpty()) { "Expected empty rest, got ${rest.size} bytes" }
        println("✓ testSqueezeIntBit31Near passed")
    }

    @Test
    fun testSqueezeIntBit31Near2()
    {
        println("Testing squeeze int near bit 31 (1014860000)...")
        val input = 1014860000
        val data = squeeze_int(input)
        val (value, rest) = expand_int(data)
        assert(value is Varint.IonInt) { "Expected Varint.IonInt, got ${value::class}" }
        if(value is Varint.IonInt)
        {
            val output = value.value
            assert(input == output) { "Expected $input, got $output" }
        }
        assert(rest.isEmpty()) { "Expected empty rest, got ${rest.size} bytes" }
        println("✓ testSqueezeIntBit31Near2 passed")
    }

    @Test
    fun testSqueezeIntMaxValue()
    {
        println("Testing squeeze int Int.MAX_VALUE (2147483647)...")
        val input = Int.MAX_VALUE // 0x7FFFFFFF
        val data = squeeze_int(input)
        val (value, rest) = expand_int(data)
        assert(value is Varint.IonInt) { "Expected Varint.IonInt, got ${value::class}" }
        if(value is Varint.IonInt)
        {
            val output = value.value
            assert(input == output) { "Expected $input, got $output" }
        }
        assert(rest.isEmpty()) { "Expected empty rest, got ${rest.size} bytes" }
        println("✓ testSqueezeIntMaxValue passed")
    }

    @Test
    fun testSqueezeIntMinValue()
    {
        println("Testing squeeze int Int.MIN_VALUE (-2147483648)...")
        val input = Int.MIN_VALUE // 0x80000000 as signed
        val data = squeeze_int(input)
        println("  Encoded bytes: ${data.joinToString(" ") { String.format("%02X", it) }}")
        val (value, rest) = expand_int(data)
        println("  Decoded type: ${value::class.simpleName}")
        assert(value is Varint.IonInt) { "Expected Varint.IonInt, got ${value::class}" }
        if(value is Varint.IonInt)
        {
            val output = value.value
            assert(input == output) { "Expected $input, got $output" }
        }
        else if(value is Varint.IonInts)
        {
            println("  ERROR: Got IonInts with values: ${value.value}")
        }
        assert(rest.isEmpty()) { "Expected empty rest, got ${rest.size} bytes" }
        println("✓ testSqueezeIntMinValue passed")
    }

    @Test
    fun testSqueezeIntNegativeLarge()
    {
        println("Testing squeeze int negative large (-1014850000)...")
        val input = -1014850000
        val data = squeeze_int(input)
        val (value, rest) = expand_int(data)
        assert(value is Varint.IonInt) { "Expected Varint.IonInt, got ${value::class}" }
        if(value is Varint.IonInt)
        {
            val output = value.value
            assert(input == output) { "Expected $input, got $output" }
        }
        assert(rest.isEmpty()) { "Expected empty rest, got ${rest.size} bytes" }
        println("✓ testSqueezeIntNegativeLarge passed")
    }

    @Test
    fun testSqueezeIntHighBits()
    {
        println("Testing squeeze int with many high bits (-16843009)...")
        val input = -16843009 // 0xFEFEFEFF as signed
        val data = squeeze_int(input)
        val (value, rest) = expand_int(data)
        assert(value is Varint.IonInt) { "Expected Varint.IonInt, got ${value::class}" }
        if(value is Varint.IonInt)
        {
            val output = value.value
            assert(input == output) { "Expected $input, got $output" }
        }
        assert(rest.isEmpty()) { "Expected empty rest, got ${rest.size} bytes" }
        println("✓ testSqueezeIntHighBits passed")
    }

    @Test
    fun testSqueezeIntPowersOf2()
    {
        println("Testing squeeze int powers of 2...")
        val testValues = listOf(
            1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096,
            8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576
        )

        for (input in testValues)
        {
            val data = squeeze_int(input)
            val (value, rest) = expand_int(data)
            assert(value is Varint.IonInt) { "Expected Varint.IonInt for $input, got ${value::class}" }
            if(value is Varint.IonInt)
            {
                val output = value.value
                assert(input == output) { "Expected $input, got $output" }
            }
            assert(rest.isEmpty()) { "Expected empty rest for $input" }
        }
        println("✓ testSqueezeIntPowersOf2 passed")
    }

    @Test
    fun testSqueezeIntNegativePowersOf2()
    {
        println("Testing squeeze int negative powers of 2...")
        val testValues = listOf(
            -1, -2, -4, -8, -16, -32, -64, -128, -256, -512, -1024, -2048, -4096,
            -8192, -16384, -32768, -65536, -131072, -262144, -524288, -1048576
        )

        for (input in testValues)
        {
            val data = squeeze_int(input)
            val (value, rest) = expand_int(data)
            assert(value is Varint.IonInt) { "Expected Varint.IonInt for $input, got ${value::class}" }
            if(value is Varint.IonInt)
            {
                val output = value.value
                assert(input == output) { "Expected $input, got $output" }
            }
            assert(rest.isEmpty()) { "Expected empty rest for $input" }
        }
        println("✓ testSqueezeIntNegativePowersOf2 passed")
    }

    @Test
    fun testSqueezeIntBoundaryValues()
    {
        println("Testing squeeze int boundary values...")
        val testValues = listOf(
            0, 1, -1,
            127, 128, -127, -128,
            255, 256, -255, -256,
            32767, 32768, -32767, -32768,
            65535, 65536, -65535, -65536,
            Int.MAX_VALUE, Int.MIN_VALUE,
            1014850000, 1014860000,
            -1014850000, -1014860000
        )

        for (input in testValues)
        {
            val data = squeeze_int(input)
            val (value, rest) = expand_int(data)
            assert(value is Varint.IonInt) { "Expected Varint.IonInt for $input, got ${value::class}" }
            if(value is Varint.IonInt)
            {
                val output = value.value
                assert(input == output) { "Expected $input, got $output" }
            }
            assert(rest.isEmpty()) { "Expected empty rest for $input" }
        }
        println("✓ testSqueezeIntBoundaryValues passed")
    }

    @Test
    fun runAllTests()
    {
        println("=== Running Squeeze Int Tests ===")
        try {
            testSqueezeInt0()
            testSqueezeInt1()
            testSqueezeIntNeg1()
            testSqueezeInt256()
            testSqueezeIntNeg256()
            testSqueezeIntBit7Set()
            testSqueezeIntBit7SetNegative()
            testSqueezeIntMax2ByteSigned()
            testSqueezeIntBit15Set()
            testSqueezeIntBit15SetNegative()
            testSqueezeIntBit31Near()
            testSqueezeIntBit31Near2()
            testSqueezeIntMaxValue()
            testSqueezeIntMinValue()
            testSqueezeIntNegativeLarge()
            testSqueezeIntHighBits()
            testSqueezeIntPowersOf2()
            testSqueezeIntNegativePowersOf2()
            testSqueezeIntBoundaryValues()
            println("=== All squeeze int tests passed! ===")
        } catch (e: AssertionError) {
            println("❌ Test failed: ${e.message}")
            throw e
        }
    }
}