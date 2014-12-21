package me.tunsi.test.java8.inter;

@FunctionalInterface
public interface Converter<F, T> {
	T convert(F from);
}
