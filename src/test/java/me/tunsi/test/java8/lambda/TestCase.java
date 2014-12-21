package me.tunsi.test.java8.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import me.tunsi.test.java8.inter.Converter;

import org.junit.Test;

public class TestCase {

	/**
	 * Lambda表达式
	 */
	@Test
	public void testCase1() {
		List<String> names = Arrays.asList("cd", "gh", "ab", "ef");

		Collections.sort(names, (a, b) -> b.compareTo(a));

		names.forEach((a) -> System.out.println(a));
	}

	/**
	 * 我们可以访问在lambda表示式之外的本地final变量
	 */
	@Test
	public void testCase2() {
		final int num = 1;
		Converter<Integer, String> converter = (from) -> String.valueOf(from + num);
		converter.convert(2);
	}

	/**
	 * 变量num不必被声明为final。但是实际上，变量num在编译期是被隐式的转换为fianl类型的。
	 */
	@Test
	public void testCase3() {
		int num = 1;
		Converter<Integer, String> converter = (from) -> String.valueOf(from + num);
		converter.convert(2);

		// 下面的代码是不能被成功的编译的
		// num = 4;
	}

	static int outerStaticNum;
	int outerNum;

	/**
	 * 访问对象字段和静态变量
	 */
	@Test
	public void testCase4() {
		Converter<Integer, String> converter1 = (from) -> {
			outerNum = 23;
			outerStaticNum = 24;
			return String.valueOf(from);
		};

		converter1.convert(1);
	}
	
	/**
	 * 接口默认方法不能被lambda表示式内部的代码访问
	 */
	@Test
	public void testCase5() {
//		Formula formula = (a) -> sqrt(a*100);
	}
	
	
}
