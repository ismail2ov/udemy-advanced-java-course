package com.github.ismail2ov.methodreferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.*;

public class MethodReferences {
    public static void main(String[] args) {
        staticMR();
        boundMR();
        unboundMR();
        constructorMR();
    }

    private static void staticMR() {
        // Exercise 1a
        List<Integer> numbers = Arrays.asList(1, 2, 7, 4, 5);

        // Exercise 1b
        Consumer<List<Integer>> sort = n -> Collections.sort(n);

        // Exercise 1c
        sort.accept(numbers);

        // Exercise 1d
        System.out.println(numbers);

        // Exercise 1e
        numbers = Arrays.asList(1, 2, 7, 4, 5);

        // Exercise 1f
        Consumer<List<Integer>> sortMR = Collections::sort;

        // Exercise 1g
        sortMR.accept(numbers);

        // Exercise 1h
        System.out.println(numbers);
    }

    private static void boundMR() {
        // Exercise 2a
        String name = "Mr. Joe Bloggs";

        // Exercise 2b
        Predicate<String> predicate = s -> name.startsWith(s);

        // Exercise 2c
        System.out.println(predicate.test("Mr."));

        // Exercise 2d
        System.out.println(predicate.test("Ms."));

        // Exercise 2e
        Predicate<String> predicateMR = name::startsWith;

        // Exercise 2f
        System.out.println(predicateMR.test("Mr."));
        System.out.println(predicateMR.test("Ms."));
    }

    private static void unboundMR() {
        // Exercise 3a
        Predicate<String> predicate = s -> s.isEmpty();

        // Exercise 3b
        System.out.println(predicate.test(""));

        // Exercise 3c
        System.out.println(predicate.test("xyz"));

        // Exercise 3d
        Predicate<String> predicateMR = String::isEmpty;

        // Exercise 3e
        System.out.println(predicateMR.test(""));
        System.out.println(predicateMR.test("xyz"));

        // Exercise 3f
        BiPredicate<String, String> biPredicate = (s, p) -> s.startsWith(p);

        System.out.println(biPredicate.test("Mr. Joe Bloggs", "Mr."));
        System.out.println(biPredicate.test("Mr. Joe Bloggs", "Ms."));

        // Exercise 3g
        BiPredicate<String, String> biPredicateMR = String::startsWith;

        // Exercise 3h
        System.out.println(biPredicateMR.test("Mr. Joe Bloggs", "Mr."));
        System.out.println(biPredicateMR.test("Mr. Joe Bloggs", "Ms."));
    }

    private static void constructorMR() {
        // Exercise 4a
        Supplier<List<String>> supplier = () -> new ArrayList<>();

        // Exercise 4b
        List<String> list = supplier.get();

        // Exercise 4c
        list.add("Lambda");

        // Exercise 4d
        System.out.println(list);

        // Exercise 4e
        Supplier<List<String>> supplierMR = ArrayList::new;
        list = supplierMR.get();
        list.add("Method Reference");
        System.out.println(list);

        // Exercise 4f
        Function<Integer, List<String>> function = n -> new ArrayList<>(n);
        list = function.apply(10);
        list.add("Lambda");
        System.out.println(list);

        // Exercise 4g
        Function<Integer, List<String>> functionMR = ArrayList::new;
        list = functionMR.apply(10);
        list.add("Method Reference");
        System.out.println(list);
    }
}
