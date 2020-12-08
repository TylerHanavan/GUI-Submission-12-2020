package test.tylerhanavan.gui;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tylerhanavan.gui.Field;
import com.tylerhanavan.gui.Field.FieldType;

public class FieldTypeTest {

	@Test
	public void testAlphabetical() {
		
		// TEST: Alphabetical field accepts only alphabetical characters
		Field field1 = new Field("field1", 7, 14, FieldType.ALPHABETICAL, false);
		assertTrue(field1.getType().accepts("a"));
		assertTrue(field1.getType().accepts("abcdefg"));
		assertTrue(field1.getType().accepts("xyz"));
		assertTrue(field1.getType().accepts("abcdefghijklmnopqrstuvwxyz"));
		assertTrue(field1.getType().accepts("DEFHJLM"));
		assertTrue(field1.getType().accepts("Z"));
		assertTrue(field1.getType().accepts("abABBB"));
		assertTrue(field1.getType().accepts("abcdefghijklmnopqrstuvwxyzABCa"));
		assertTrue(field1.getType().accepts("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"));
		assertFalse(field1.getType().accepts("1"));
		assertFalse(field1.getType().accepts("1abcdefgh"));
		assertFalse(field1.getType().accepts("abcdefghik8adwoijhdwa"));
		assertFalse(field1.getType().accepts("ABCDEFGHI5"));
		assertFalse(field1.getType().accepts("ABCDEFGHI0acbdef"));
		assertFalse(field1.getType().accepts("#"));
		assertFalse(field1.getType().accepts(" "));
		assertFalse(field1.getType().accepts("abcdef ghi"));
	}

	@Test
	public void testAlphanumerical() {
		
		// TEST: Alphanumerical field accepts only alphanumerical characters
		Field field2 = new Field("field2", 7, 14, FieldType.ALPHANUMERICAL, false);
		assertTrue(field2.getType().accepts("a"));
		assertTrue(field2.getType().accepts("abcdefg"));
		assertTrue(field2.getType().accepts("xyz"));
		assertTrue(field2.getType().accepts("abcdefghijklmnopqrstuvwxyz"));
		assertTrue(field2.getType().accepts("DEFHJLM"));
		assertTrue(field2.getType().accepts("Z"));
		assertTrue(field2.getType().accepts("abABBB"));
		assertTrue(field2.getType().accepts("abcdefghijklmnopqrstuvwxyzABCa"));
		assertTrue(field2.getType().accepts("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"));
		assertTrue(field2.getType().accepts("1"));
		assertTrue(field2.getType().accepts("1234115521577907324234234233"));
		assertTrue(field2.getType().accepts("1234115521577907324234234233a"));
		assertTrue(field2.getType().accepts("1234115521577907324234234233abcdefghik8adwoijhdwa"));
		assertTrue(field2.getType().accepts("1abcdefgh"));
		assertTrue(field2.getType().accepts("abcdefghik8adwoijhdwa"));
		assertTrue(field2.getType().accepts("ABCDEFGHI5"));
		assertTrue(field2.getType().accepts("ABCDEFGHI0acbdef"));
		assertFalse(field2.getType().accepts("#"));
		assertFalse(field2.getType().accepts(" "));
		assertFalse(field2.getType().accepts("abcdef ghi"));
	}

	@Test
	public void testNumerical() {
		
		// TEST: Numerical field accepts only numerical characters
		Field field3 = new Field("field3", 7, 14, FieldType.NUMERICAL, false);
		assertFalse(field3.getType().accepts("a"));
		assertFalse(field3.getType().accepts("abcdefg"));
		assertFalse(field3.getType().accepts("xyz"));
		assertFalse(field3.getType().accepts("abcdefghijklmnopqrstuvwxyz"));
		assertFalse(field3.getType().accepts("DEFHJLM"));
		assertFalse(field3.getType().accepts("Z"));
		assertFalse(field3.getType().accepts("abABBB"));
		assertFalse(field3.getType().accepts("abcdefghijklmnopqrstuvwxyzABCa"));
		assertFalse(field3.getType().accepts("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"));
		assertTrue(field3.getType().accepts("1"));
		assertTrue(field3.getType().accepts("1234567890"));
		assertTrue(field3.getType().accepts("1234115521577907324234234233"));
		assertFalse(field3.getType().accepts("1234115521577907324234234233a"));
		assertFalse(field3.getType().accepts("1234115521577907324234234233abcdefghik8adwoijhdwa"));
		assertFalse(field3.getType().accepts("1abcdefgh"));
		assertFalse(field3.getType().accepts("abcdefghik8adwoijhdwa"));
		assertFalse(field3.getType().accepts("ABCDEFGHI5"));
		assertFalse(field3.getType().accepts("ABCDEFGHI0acbdef"));
		assertFalse(field3.getType().accepts("#"));
		assertFalse(field3.getType().accepts(" "));
		assertFalse(field3.getType().accepts("1234 567"));
	}

	@Test
	public void testAny() {
		
		// TEST: Any field accepts any characters
		Field field4 = new Field("field4", 7, 14, FieldType.ANY, false);
		assertTrue(field4.getType().accepts("a"));
		assertTrue(field4.getType().accepts("abcdefg"));
		assertTrue(field4.getType().accepts("xyz"));
		assertTrue(field4.getType().accepts("abcdefghijklmnopqrstuvwxyz"));
		assertTrue(field4.getType().accepts("DEFHJLM"));
		assertTrue(field4.getType().accepts("Z"));
		assertTrue(field4.getType().accepts("abABBB"));
		assertTrue(field4.getType().accepts("abcdefghijklmnopqrstuvwxyzABCa"));
		assertTrue(field4.getType().accepts("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"));
		assertTrue(field4.getType().accepts("1"));
		assertTrue(field4.getType().accepts("1234567890"));
		assertTrue(field4.getType().accepts("1234115521577907324234234233"));
		assertTrue(field4.getType().accepts("1234115521577907324234234233a"));
		assertTrue(field4.getType().accepts("1234115521577907324234234233abcdefghik8adwoijhdwa"));
		assertTrue(field4.getType().accepts("1abcdefgh"));
		assertTrue(field4.getType().accepts("abcdefghik8adwoijhdwa"));
		assertTrue(field4.getType().accepts("ABCDEFGHI5"));
		assertTrue(field4.getType().accepts("ABCDEFGHI0acbdef"));
		assertTrue(field4.getType().accepts("#"));
		assertTrue(field4.getType().accepts(" "));
		assertTrue(field4.getType().accepts("1234 567"));
		assertTrue(field4.getType().accepts("aB1`~!@#$%^&*()-_=+\\|{[}]\"':;?/>.<,"));
	}

}
