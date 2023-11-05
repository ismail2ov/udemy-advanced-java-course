package com.github.ismail2ov.streams;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {

    public static void main(String[] args) {
        Streams streams = new Streams();

        // Exercise 1
        streams.calculateAverage();

        // Exercise 2
        streams.sortItems();

        // Exercise 3
        streams.filterList();

        // Exercise 4
        streams.calculateSumAndMax();
        streams.oldestPerson();
        streams.reduceIntegers();

        // Exercise 5b
        Optional<String> grade1 = getGrade(50);
        Optional<String> grade2 = getGrade(55);

        System.out.println(grade1.orElse("UNKNOWN"));

        if (grade2.isPresent()) {
            grade2.ifPresent(System.out::println);
        } else {
            System.out.println(grade2.orElse("Empty"));
        }



        // Exercise 6
        streams.averageBooksPriceExpensiveThat(10);
        streams.averageBooksPriceExpensiveThat(90);

        // Exercise 7
        streams.getBooksWhoseTitlesStartWithA();

        // Exercise 8
        streams.printBooksPrices();

        // Exercise 9
        streams.printPeopleYoungerThan30();

        // Exercise 10
        streams.optionalsExercise();

        // Exercise 11
        streams.printBookGenres();

        // Exercise 12
        streams.workWithStreams();

        // Exercise 13
        streams.workWithMathInTheStreams();

        // Exercise 14a
        streams.workWithAtomicInteger();
    }

    public void calculateAverage() {
        // Exercise 1
        IntStream primitives = IntStream.range(0, 5);

        OptionalDouble average = primitives.average();

        average.ifPresent(System.out::println);
    }

    public void sortItems() {
        // Exercise 2
        List<Item> items = Arrays.asList(
                new Item(1, "Screw"),
                new Item(2, "Nail"),
                new Item(3, "Bolt")
        );

        items.stream()
                .map(Item::getName)
                .sorted()
                .forEach(System.out::print);
    }

    public void filterList() {
        // Exercise 3
        Stream<List<String>> stream = Stream.of(Arrays.asList("a", "b"), Arrays.asList("a", "c"));

        stream.filter(list -> list.contains("c"))
                .flatMap(list -> list.stream())
                .forEach(s -> System.out.print(s + " "));
    }

    public void calculateSumAndMax() {
        // Exercise 4a
        List<Integer> numbers = List.of(1, 2, 3);
        int sum = numbers.stream()
                .mapToInt(i -> i)
                .sum();
        System.out.println("\nThe sum is: " + sum);

        OptionalInt max = numbers.stream()
                .mapToInt(i -> i)
                .max();
        max.ifPresent(m -> System.out.println("The max value is: " + m));
    }

    public void oldestPerson() {
        // Exercise 4b
        List<Person> people = Arrays.asList(
                new Person("Alan", "Burke", 22),
                new Person("Zoe", "Peters", 20),
                new Person("Peter", "Castle", 29)
        );

        Optional<Person> oldestPerson = people.stream()
                .max(Comparator.comparing(Person::getAge));

        oldestPerson.ifPresent(person -> System.out.println("The max value is: " + person));
    }

    public void reduceIntegers() {
        // Exercise 4c
        List<Integer> numbers = List.of(10, 47, 33, 23);

        Optional<Integer> max1 = numbers.stream()
                .reduce(Integer::max);
        max1.ifPresent(m -> System.out.println("The max value is: " + m));

        Integer max2 = numbers.stream()
                .reduce(Integer.MIN_VALUE, Integer::max);
        System.out.println("The max value is: " + max2);
    }

    public static Optional<String> getGrade(int marks) {
        // Exercise 5a
        Optional<String> grade = Optional.empty();
        if (marks > 50) {
            grade = Optional.of("PASS");
        } else {
            grade.of("FAIL");
        }

        return grade;
    }

    public void averageBooksPriceExpensiveThat(double price) {
        // Exercise 6
        List<Book> books = List.of(
                new Book("Thinking in Java", 30.0),
                new Book("Java in 24 hrs", 20.0),
                new Book("Java Recipes", 10.0)
        );

        double averagePrice = books.stream()
                .filter(book -> book.getPrice() > price)
                .mapToDouble(Book::getPrice)
                .average()
                .orElse(0.0);

        System.out.printf("The average price of books more expensive than %.2f is: %.2f%n", price, averagePrice);
    }

    public void getBooksWhoseTitlesStartWithA() {
        // Exercise 7
        List<Book> books = List.of(
                new Book("Atlas Shrugged", 10.0),
                new Book("Freedom at Midnight", 5.0),
                new Book("Gone with the wind", 5.0)
        );

        Map<String, Double> booksMap = books.stream()
                .collect(Collectors.toMap(Book::getTitle, Book::getPrice));

        BiConsumer<String, Double> lambda = (k, v) -> {
            if (k.startsWith("A")) {
                System.out.println(v);
            }
        };

        booksMap.forEach(lambda);
    }

    public void printBooksPrices() {
        // Exercise 8
        List<Book> books = List.of(
                new Book("Gone with the wind", 5.0),
                new Book("Gone with the wind", 10.0),
                new Book("Atlas shrugged", 15.0)
        );

        Map<String, Double> booksMap = books.stream()
                // .collect(Collectors.toMap(Book::getTitle, Book::getPrice)); // Exception: Duplicate key Gone with the wind (attempted merging values 5.0 and 10.0)
                .collect(Collectors.toMap(Book::getTitle, Book::getPrice, Double::sum));

        booksMap.forEach((k, v) -> System.out.println(k + " " + v));
    }

    public void printPeopleYoungerThan30() {
        // Exercise 9
        List<Person> people = List.of(
                new Person("Bob", "", 31),
                new Person("Paul", "", 32),
                new Person("John", "", 33)
        );

        double averageAge = people.stream()
                .filter(p -> p.getAge() < 30)
                .mapToInt(Person::getAge)
                .average()
                .orElse(0.0);

        System.out.println(averageAge);
    }

    public void optionalsExercise() {
        // Exercise 10a
        Optional<Double> price = Optional.ofNullable(20.0);

        price.ifPresent(System.out::println);
        System.out.println(price.orElse(0.0));
        System.out.println(price.orElseGet(() -> 0.0));

        // Exercise 10b
        Optional<Double> price2 = Optional.ofNullable(null);

        System.out.println(price2);
        if (price2.isEmpty()) {
            System.out.println("empty");
        }

        price2.ifPresent(System.out::println);

        System.out.println(price2.orElse(44.0));

        // Exercise 10c
        Optional<Double> price3 = Optional.ofNullable(null);

        Double z = price3.orElseThrow(() -> new RuntimeException("Bad Code")); //Exception in thread "main" java.lang.RuntimeException: Bad Code
        System.out.println(z); // Exception in thread "main" java.lang.RuntimeException: Bad Code
    }

    public void printBookGenres() {
        // Exercise 11

        List<AnotherBook> books = List.of(
                new AnotherBook("Gone with the wind", "Fiction"),
                new AnotherBook("Bourne Ultimatum", "Thriller"),
                new AnotherBook("The Client", "Thriller")
        );

        List<String> genreList = new ArrayList<>();

        books.stream()
                .map(AnotherBook::getGenre)
                .forEach(genreList::add);

        System.out.println(genreList);
    }

    public void workWithStreams() {
        // Exercise 12a
        DoubleStream streamA = DoubleStream.of(0, 2, 4);
        double sum = streamA.filter(e -> (e % 2 != 0))
                .sum();

        System.out.println("Sum = " + sum);

        // Exercise 12b
        Stream<Double> streamB = Stream.of(1.0, 3.0);
        OptionalDouble average = streamB.mapToDouble(Double::doubleValue)
                .filter(e -> (e % 2 != 0))
                .average();

        average.ifPresent(e -> System.out.println("Average = " + e));

    }

    public void workWithMathInTheStreams() {
        // Exercise 13a
        List<Integer> ls = Arrays.asList(11, 11, 22, 33, 33, 55, 66);

        boolean result1 = ls.stream()
                .distinct()
                .anyMatch(n -> n == 11);

        System.out.println("There are 11 in the stream: " + result1);

        // Exercise 13b
        boolean result2 = ls.stream()
                .distinct()
                .noneMatch(x -> x % 11 > 0);

        System.out.println("There is a number divisible by 11 in the stream: " + result2);

    }

    public void workWithAtomicInteger() {
        // Exercise 14a
        // In a stream, the filter operation (and other intermediate operations) are executed lazily,
        // meaning they are not executed until a terminal operation is called.
        AtomicInteger ai = new AtomicInteger();

        Stream.of(11, 11, 22, 33)
                .parallel()
                .filter(n -> {
                    ai.incrementAndGet();
                    return n % 2 == 0;

                })
                .forEach(System.out::println);

        System.out.println("ai = " + ai);


        // Exercise 14b
        AtomicInteger ai2 = new AtomicInteger();

        Stream<Integer> stream2 = Stream.of(11, 11, 22, 33).parallel();
        Stream<Integer> newStream2 = stream2.filter(e -> {
            ai2.incrementAndGet();
            return e % 2 == 0;
        });
        newStream2.forEach(System.out::println);

        System.out.println("ai2 = " + ai2);

    }
}
