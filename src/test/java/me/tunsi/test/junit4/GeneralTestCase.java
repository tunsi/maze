package me.tunsi.test.junit4;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class GeneralTestCase {

	@BeforeClass
	public static void beforeTestCase() {
		System.out.println("beforeTestCase");
	}

	@AfterClass
	public static void afterTestCase() {
		System.out.println("afterTestCase");
	}

	@Before
	public void beforeEveryTestCase() {
		System.out.println("beforeEveryTestCase");
	}

	@After
	public void afterEveryTestCase() {
		System.out.println("afterEveryTestCase");
	}

	@Test
	public void testCase1() {
		System.out.println("testCase1");

		assertTrue(true);
	}

	@Test
	public void testCase2() {
		System.out.println("testCase2");

		assertEquals(1, 2);
	}

	@Test
	public void testCase3() {
		System.out.println("testCase3");

		assertNull("");
	}

	@Test(expected = RuntimeException.class)
	public void testException() {
		System.out.println("testException");
		throw new RuntimeException();
	}

	@Test(timeout = 1000)
	public void testTimeout() {
		System.out.println("testTimeout");
//		while (true);
	}

	@Test
	@Ignore
	public void testIgnore() {
		System.out.println("testIgnore");
	}

}
