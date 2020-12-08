package test.tylerhanavan.gui;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tylerhanavan.gui.DataHelper;

public class DateTest {

	@Test
	public void test() {
		
		// TEST: Dates are properly validated
		assertTrue(DataHelper.isValidDate("20200504"));
		assertFalse(DataHelper.isValidDate("2020050"));
		assertFalse(DataHelper.isValidDate("19733205"));
		assertFalse(DataHelper.isValidDate("18110000"));
		assertTrue(DataHelper.isValidDate("20210101"));
		assertFalse(DataHelper.isValidDate("20210432"));
	}

}
