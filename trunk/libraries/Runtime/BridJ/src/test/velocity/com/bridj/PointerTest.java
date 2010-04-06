package com.bridj;

import org.junit.Test;

import com.bridj.DefaultDisorderedPointer;

import java.nio.ByteOrder;
import static org.junit.Assert.*;

public class PointerTest {

#foreach ($prim in $primitivesNoBool)
	@Test 
    public void testPointerTo_${prim.Name}_Values() {
		Pointer<${prim.WrapperName}> p = Pointer.pointerTo${prim.CapName}s((${prim.Name})1, (${prim.Name})2, (${prim.Name})3);
		assertEquals((${prim.Name})1, (${prim.Name})p.get(0), 0);
		assertEquals((${prim.Name})2, (${prim.Name})p.get(1), 0);
		assertEquals((${prim.Name})3, (${prim.Name})p.get(2), 0);
	}
	
	@Test 
    public void testAllocateBounds_${prim.Name}_ok() {
		assertEquals((double)(${prim.Name})0, (double)Pointer.allocate${prim.CapName}().get(0), 0);
		assertEquals((double)(${prim.Name})0, (double)Pointer.allocate${prim.CapName}s(1).get(0), 0);
		assertEquals((double)(${prim.Name})0, (double)Pointer.allocate${prim.CapName}s(2).offset(${prim.Size}).get(-1), 0);
		
		//TODO slide, slideBytes
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
    public void testAllocateBounds_${prim.Name}_failAfter() {
		Pointer.allocate${prim.CapName}().get(1);
	}
	@Test(expected=IndexOutOfBoundsException.class)
    public void testAllocateBounds_${prim.Name}_failBefore() throws IndexOutOfBoundsException {
		Pointer.allocate${prim.CapName}().get(-1);
	}

    @Test
    public void test${prim.CapName}DisorderClass() {
    	Class<?> c = Pointer.allocate${prim.CapName}().order(ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN) ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN).getClass();
    	assertTrue(DefaultDisorderedPointer.class.isAssignableFrom(c));
    }
	#if (($prim.Name == "short") || ($prim.Name == "int") || ($prim.Name == "long"))
	@Test
	public void test${prim.CapName}Endianness() {
		for (${prim.Name} value : new ${prim.Name}[] { (${prim.Name})0, (${prim.Name})1, (${prim.Name})-1 }) {
			test${prim.CapName}Endianness(ByteOrder.LITTLE_ENDIAN, value);
			test${prim.CapName}Endianness(ByteOrder.BIG_ENDIAN, value);
		}
	}
	void test${prim.CapName}Endianness(ByteOrder order, ${prim.Name} value) {
		Pointer<${prim.WrapperName}> p = Pointer.allocate${prim.CapName}().order(order);
		p.set(value);
        assertEquals(order, p.order());
        assertEquals(order, p.get${prim.BufferName}(0, 1).order());
		assertEquals(value, p.get${prim.BufferName}(0, 1).get(), 0); // check that the NIO buffer was created with the correct order by default
		assertEquals(value, p.getByteBuffer(0, ${prim.Size}).order(order).as${prim.BufferName}().get(), 0);
	}
	#end

#end
}