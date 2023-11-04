package com.github.ismail2ov.lambdas;

@FunctionalInterface
public interface Evaluate<T> {
    boolean test(T t);
}
