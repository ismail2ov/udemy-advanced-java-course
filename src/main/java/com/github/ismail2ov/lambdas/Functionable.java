package com.github.ismail2ov.lambdas;

@FunctionalInterface
public interface Functionable<T, R> {
    R apply(T t);
}
