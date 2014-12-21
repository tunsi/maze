package me.tunsi.test.junit4;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ParameterizedTestCase {

	private String username;
	private String password;

	public ParameterizedTestCase(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Parameters
	public static Collection<?> generateParameters() {
		return Arrays.asList(new Object[][] { { "zhangsan", "123456" }, { "lisi", "678901" } });
	}

	@Test
	public void login() {
		System.out.println(username + ":" + password);
	}
}
