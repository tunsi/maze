package me.tunsi.test.java8.inter;

public interface Formula {

	double calculate(int a);

	default double sqrt(int a) {
		return Math.sqrt(a);
	}
}
