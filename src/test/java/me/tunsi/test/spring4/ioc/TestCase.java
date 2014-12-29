package me.tunsi.test.spring4.ioc;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCase {

	@Test
	public void test1() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("me/tunsi/test/spring4/ioc/applicationContext.xml");
		Bean1 bean1 = (Bean1)context.getBean("alias1");
		bean1.doSomething();
		context.close();
	}
}
