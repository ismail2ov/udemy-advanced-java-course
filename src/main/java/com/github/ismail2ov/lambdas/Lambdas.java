package com.github.ismail2ov.lambdas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Lambdas {
    public static void main(String[] args) {
        Lambdas lambdas = new Lambdas();
        lambdas.consumer();
        lambdas.supplier();
        lambdas.predicate();
        lambdas.function();

        List<Person> listPeople = Lambdas.getPeople();
        lambdas.sortAge(listPeople);
        lambdas.sortName(listPeople);
        lambdas.sortHeight(listPeople);
    }

    private void consumer() {
        // Exercise 1a
        Printable<String> printable = s -> System.out.println(s);
        printable.print("Printable lambda");

        // Exercise 1b
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("Consumer lambda");

        Consumer<String> consumerMethodReference = System.out::println;
        consumerMethodReference.accept("Consumer lambda with method reference");
    }

    private void supplier() {
        // Exercise 2a
        Retrievable<Integer> retrievable = () -> 77;
        System.out.println("Retrievable: " + retrievable.retrieve());

        // Exercise 2b
        Supplier<Integer> supplier = () -> 77;
        System.out.println("Supplier: " + supplier.get());
    }

    private void predicate() {
        // Exercise 3a
        Evaluate<Integer> isNegative = arg -> arg < 0;
        System.out.println("Evaluate if -1 is negative: " + isNegative.test(-1));
        System.out.println("Evaluate if 1 is negative: " + isNegative.test(1));

        // Exercise 3b
        Predicate<Integer> predicate = arg -> arg < 0;
        System.out.println("Predicate if -1 is negative: " + predicate.test(-1));
        System.out.println("Predicate if 1 is negative: " + predicate.test(1));

        // Exercise 3c
        Predicate<Integer> isEven = i -> i % 2 == 0;
        System.out.println("Is 4 even? " + check(4, isEven));
        System.out.println("Is 7 even? " + check(7, isEven));

        Predicate<String> startWithMr = s -> s.startsWith("Mr.");
        System.out.println("Does Mr. Joe Bloggs start with Mr.? " + check("Mr. Joe Bloggs", startWithMr));
        System.out.println("Does Ms. Ann Bloggs start with Mr.? " + check("Ms. Ann Bloggs", startWithMr));

        Predicate<Person> isAdult = p -> p.age() >= 18;
        Person mike = new Person("Mike", 33, 1.8);
        Person ann = new Person("Ann", 13, 1.4);

        System.out.println("Is " + mike.name() + " an adult? " + check(mike, isAdult));
        System.out.println("Is " + ann.name() + " an adult? " + check(ann, isAdult));
    }

    public <T> boolean check(T t, Predicate<T> predicate) {
        return predicate.test(t);
    }

    private void function() {
        // Exercise 4a
        Functionable<Integer, String> functionable = i -> "Number is: " + i;
        System.out.println(functionable.apply(25));

        // Exercise 4b
        Function<Integer, String> function = i -> "Number is: " + i;
        System.out.println(function.apply(25));

    }

    private static List<Person> getPeople() {

        List<Person> result = new ArrayList<>();
        result.add(new Person("Mike", 33, 1.8));
        result.add(new Person("Mary", 25, 1.4));
        result.add(new Person("Alan", 34, 1.7));
        result.add(new Person("Zoe", 30, 1.5));

        return result;
    }

    private void sortAge(List<Person> listPeople) {
        // Exercise 6a
        listPeople.sort(Comparator.comparing(Person::age));

        // Exercise 6b
        listPeople.forEach(System.out::println);
    }

    private void sortName(List<Person> listPeople) {
        // Exercise 7a
        listPeople.sort(Comparator.comparing(Person::name));

        // Exercise 7b
        listPeople.forEach(System.out::println);
    }

    private void sortHeight(List<Person> listPeople) {
        // Exercise 8a
        listPeople.sort(Comparator.comparing(Person::height));

        // Exercise 8b
        listPeople.forEach(System.out::println);
    }
}
