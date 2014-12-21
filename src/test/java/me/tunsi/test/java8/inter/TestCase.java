package me.tunsi.test.java8.inter;

import org.junit.Test;

public class TestCase {

	/**
	 * 接口的默认方法. 在抽象方法calculator之外，接口Formula还定义了一个默认方法sqrt。实现类只需要实现抽象方法calculate。
	 * 默认方法sqrt可以在定义之外使用。
	 */
	@Test
	public void testCase1() {
		Formula formula = new Formula() {
			@Override
			public double calculate(int a) {
				return 0;
			}
		};

		formula.calculate(1);
		formula.sqrt(2);
	}

	/**
	 * 功能性接口
	 */
	@Test
	public void testCase2() {
		Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
		Integer converted = converter.convert("123");
		System.out.println(converted + 1);
	}

	/**
	 * 静态方法引用
	 */
	@Test
	public void testCase3() {
		Converter<String, Integer> converter = Integer::valueOf;
		Integer converted = converter.convert("123");
		System.out.println(converted + 1);
	}

	/**
	 * 对象方法引用
	 */
	@Test
	public void testCase4() {
		Something something = new Something();
		Converter<String, String> converter = something::startsWith;
		String converted = converter.convert("java");
		System.out.println(converted);
	}

	/**
	 * 构造函数引用
	 */
	@Test
	public void testCase5() {
		PersonFactory<Person> personFactory = Person::new;
		Person person = personFactory.create("Xiangxi", "Kong");

		System.out.println(person.getFirstName() + " " + person.getLastName());
	}
}
